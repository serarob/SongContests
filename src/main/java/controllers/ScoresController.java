/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import model.DAO.RelationUserDAO;
import model.DAO.ScoresDAO;
import model.DAO.SongDAO;
import model.entity.Scores;
import model.entity.Song;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author andre
 */
@Controller
public class ScoresController {
    
    @Autowired
    private ScoresDAO scoresDAO;
    
    @Autowired
    private RelationUserDAO relationUserDAO;
    
    @Autowired
    private SongDAO songDAO;
    
    @RequestMapping(value="/chooseYourOpponent", method = RequestMethod.GET)
    public ModelAndView chooseOpponent(HttpSession session, ModelAndView model){
        try{
            if(session.getAttribute("loguser") != null){
                User user = (User) session.getAttribute("loguser");
                List<User> opponentsList = relationUserDAO.getMyFriends(user);
                Map<User,String> map = new LinkedHashMap<>();
                for (User opponent : opponentsList) {
                    if(scoresDAO.getScoresNotificationsFromSpecificUser(user, opponent) == 0l){
                        map.put(opponent, null);
                    }
                    else{
                        Scores score = new Scores();
                        score.setUserOne(user);
                        score.setUserTwo(opponent);
                        score.setNotification(1l);
                        score = scoresDAO.checkScore(score);
                       if(score.getUserOne().getUsername().equals(user.getUsername())){
                           map.put(opponent, "/ Match in progress...");
                       }
                       else{
                           map.put(opponent, "/ Challenge");
                       }
                    }
                }
                model.addObject("map", map);
                model.addObject("user", user);
                model.setViewName("opponents");
                return model;
            }
            else{
                model.setViewName("error");
                return model;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            model.setViewName("error");
            return model;
        }
    }
    
    @RequestMapping(value = "checkAnswer")
    public @ResponseBody ModelAndView choice (ModelAndView model, @RequestParam("songId") Long sid, @RequestParam("songAnswer") String songAnswer,  
            HttpSession session, User user, User opponent){
        if(session.getAttribute("loguser") != null){
            user = (User) session.getAttribute("loguser");
            opponent = (User) session.getAttribute("opponent");
            Song song = songDAO.getSongById(sid);
            Scores score = new Scores();
            try{  
                score.setUserOne(user);
                score.setUserTwo(opponent);
                score = scoresDAO.checkScore(score);
                if(score.getUserOne().getUsername().equals(user.getUsername())){
                    if(songAnswer.equals(song.getCorrect())){
                        score.setPointsOne(score.getPointsOne()+1);
                    }
                    scoresDAO.updateScores(score);
                }
                else{
                    if(songAnswer.equals(song.getCorrect())){
                        score.setPointsTwo(score.getPointsTwo()+1);
                    }
                    scoresDAO.updateScores(score);
                }
            }
            catch(Exception e){
                score.setUserOne(user);
                score.setUserTwo(opponent);
                if(songAnswer.equals(song.getCorrect())){
                        score.setPointsOne(1l);
                }
                scoresDAO.saveScores(score);
                System.out.println("kanw save san user 1");
            }
            List<Song> songList = songDAO.getAllSongQuestions();
            song=songDAO.getSongRand(songList);
            model.addObject("opponent", opponent);
            model.addObject("user", user);
            model.addObject("song", song);
            model.setViewName("anothersong");
            return model;

        }
        else{
            return new ModelAndView("error");
        }
    }
    
    @RequestMapping(value = "gameScore")
    public ModelAndView score(ModelAndView model, HttpSession session){
        try{
            if(session.getAttribute("loguser") != null){
                User user = (User) session.getAttribute("loguser");
                User opponent = (User) session.getAttribute("opponent");
                Scores score = new Scores();
                score.setUserOne(user);
                score.setUserTwo(opponent);
                score = scoresDAO.checkScore(score);
                model.addObject("score", score);
                if(!score.getUserOne().getUsername().equals(user.getUsername())){
                    scoresDAO.clearScoresNotificationsFromSpecificUser(opponent, user);                    
                    model.setViewName("score");
                    return model;
                }
                else{
                    model.setViewName("onescore");
                    return model;
                }
            }
            else{
                return new ModelAndView("error");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return new ModelAndView("redirect:/chooseYourOpponent");
        }
    }
    
    @RequestMapping(value = "exitGame")
    public ModelAndView exit(HttpSession session){
        User user = (User) session.getAttribute("loguser");
        User opponent = (User) session.getAttribute("opponent");
        Scores score = new Scores();
        try{
            if(session.getAttribute("loguser") != null){

                score.setUserOne(user);
                score.setUserTwo(opponent);
                score = scoresDAO.checkScore(score);
                if(!score.getUserOne().getUsername().equals(user.getUsername())){
                    scoresDAO.clearScoresNotificationsFromSpecificUser(opponent, user);
                }
                return new ModelAndView("redirect:/chooseYourOpponent");
            }
            else{
                return new ModelAndView("error");
            }
        }
        catch(Exception e){
            score.setUserOne(user);
            score.setUserTwo(opponent);
            score.setPointsOne(0l);
            scoresDAO.saveScores(score);
            return new ModelAndView("redirect:/chooseYourOpponent");
        }
    }
    
    @RequestMapping(value = "latestScores")
    public ModelAndView getMyLatestScores(HttpSession session){
        if(session.getAttribute("loguser") != null){
            ModelAndView model = new ModelAndView();
            User user = (User) session.getAttribute("loguser");
            List scoresList = scoresDAO.getMyLatestScores(user);
            model.addObject("user", user);
            model.addObject("scoresList", scoresList);
            model.setViewName("latestscores");
            return model;
        }
        else{
            return new ModelAndView("error");
        }
    }
}
