/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import javax.servlet.http.HttpSession;
import model.DAO.ScoresDAO;
import model.DAO.SongDAO;
import model.DAO.UserDAO;
import model.entity.Scores;
import model.entity.Song;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Padelis
 */
@Controller
public class SongController {
    
    @Autowired
    private SongDAO songDAO;
    
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private ScoresDAO scoresDAO;

    @RequestMapping(value = "/saveSong",method=RequestMethod.POST)
    public ModelAndView insertSong(@ModelAttribute("song") Song song)
    {
        try
        {
            if(songDAO.getSongById(song.getSid() ) != null);
            songDAO.updateSongQuestion(song);
        }
        catch(EmptyResultDataAccessException e)
        {
            System.out.println("inside catch");
            songDAO.insertSongQuestion(song);
        }
        return new ModelAndView("redirect:/songs");
    }
    
    @RequestMapping(value = "/editSong/{sid}")
    public ModelAndView editSong(@ModelAttribute("song") Song song,@PathVariable("sid") long sid)
    {
        ModelAndView model = new ModelAndView("songs");
        
        song = songDAO.getSongById(sid);
        List<Song> songList = songDAO.getAllSongQuestions();
        
        model.addObject("song",song);        
        model.addObject("songList",songList);
        
        return model;
        
    }
    
    @RequestMapping(value = "/deleteSong/{sid}")
    public ModelAndView deleteSong(@ModelAttribute("song") Song song,@PathVariable("sid") long sid)
    {
        songDAO.deleteSongQuestion(sid);
        
        return new ModelAndView("redirect:/songs");
    }

    @RequestMapping(value = "/songs")
    public ModelAndView listSongs(@ModelAttribute("song") Song song)
    {
        ModelAndView model = new ModelAndView("songs");

        List<Song> songList = songDAO.getAllSongQuestions();
        System.out.println(songList);
        model.addObject("songList", songList);
        
        return model;
    }
    
    @RequestMapping(value = "songcontest")
    public ModelAndView playSong(HttpSession session, @RequestParam("opponentId") Long opponentId)
    {
        if(session.getAttribute("loguser") != null){
            User user = (User) session.getAttribute("loguser");
            User opponent = userDAO.getUserById(opponentId);
            session.setAttribute("opponent", opponent);
            ModelAndView model = new ModelAndView();
            model.addObject("user", user);
            model.addObject("opponent", opponent);
            List<Song> songList = songDAO.getAllSongQuestions();
            Song song=songDAO.getSongRand(songList);
            //model.addObject("songList", songList);
            model.addObject("song", song);
            if(scoresDAO.getScoresNotificationsFromSpecificUser(user, opponent) == 0l){
                model.setViewName("player");
            }
            else{
                Scores score = new Scores();
                score.setUserOne(user);
                score.setUserTwo(opponent);
                score.setNotification(1l);
                score = scoresDAO.checkScore(score);
                if(score.getUserOne().getUsername().equals(user.getUsername())){
                   //waiting for playing player you challenge
                   model.setViewName("redirect:/chooseYourOpponent");
                }
                else{
                    model.setViewName("player");
                }
            }

            return model;
        }
        else{
            return new ModelAndView("error");
        }
    }
    
    
    @RequestMapping(value = "getAnotherSong")
    public @ResponseBody ModelAndView getSong(HttpSession session, @RequestParam("songId") Long sid)
    {
        if(session.getAttribute("loguser") != null){
            ModelAndView model = new ModelAndView();
            User user = (User) session.getAttribute("loguser");
            User opponent = (User) session.getAttribute("opponent");
            System.out.println(sid);
            List<Song> songList = songDAO.getAllSongQuestions();
            Song song=songDAO.getSongRand(songList);
            model.addObject("song", song);
            model.addObject("opponent", opponent);
            model.addObject("user", user);
            model.setViewName("anothersong");

            return model;
        }
        else{
            return new ModelAndView("error");
        }
    }
    
    @RequestMapping(value = "upToConcertFeed")
    public String concertFeed(){
        return "concertfeed";
    }
}

    

