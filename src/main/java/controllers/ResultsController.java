/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import javax.servlet.http.HttpSession;
import model.DAO.ResultsDAO;
import model.DAO.UserDAO;
import model.entity.Results;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



/**
 *
 * @author andre
 */
@Controller
public class ResultsController {
    
    @Autowired
    private ResultsDAO resultsDAO;
    
    @Autowired
    private UserDAO userDAO;
    
    @RequestMapping(value = "setRankings")
    public ModelAndView setRankings(@RequestParam("userOneId") Long userOneId,
            @RequestParam("userOnePoints") Long userOnePoints,
            @RequestParam("userTwoId") Long userTwoId,
            @RequestParam("userTwoPoints") Long userTwoPoints){
        
        ModelAndView model = new ModelAndView();
        try{
            User userOne = userDAO.getUserById(userOneId);
            Results resultsOne = resultsDAO.getUserResults(userOne);
            User userTwo = userDAO.getUserById(userTwoId);
            Results resultsTwo = resultsDAO.getUserResults(userTwo);
            if(userOnePoints > userTwoPoints){
                resultsOne.setWin(resultsOne.getWin()+1);
                resultsOne.setDraw(resultsOne.getDraw()+0);
                resultsOne.setLose(resultsOne.getLose()+0);
                resultsOne.setPoints(resultsOne.getPoints()+2);
                resultsDAO.updateUserResults(resultsOne, userOne);
                resultsTwo.setWin(resultsTwo.getWin()+0);
                resultsTwo.setDraw(resultsTwo.getDraw()+0);
                resultsTwo.setLose(resultsTwo.getLose()+1);
                resultsTwo.setPoints(resultsTwo.getPoints()+0);
                resultsDAO.updateUserResults(resultsTwo, userTwo);
                System.out.println("user one wins");
            }
            else if(userOnePoints == userTwoPoints){
                resultsOne.setWin(resultsOne.getWin()+0);
                resultsOne.setDraw(resultsOne.getDraw()+1);
                resultsOne.setLose(resultsOne.getLose()+0);
                resultsOne.setPoints(resultsOne.getPoints()+1);
                resultsDAO.updateUserResults(resultsOne, userOne);
                resultsTwo.setWin(resultsTwo.getWin()+0);
                resultsTwo.setDraw(resultsTwo.getDraw()+1);
                resultsTwo.setLose(resultsTwo.getLose()+0);
                resultsTwo.setPoints(resultsTwo.getPoints()+1);
                resultsDAO.updateUserResults(resultsTwo, userTwo);
                System.out.println("draw");
            }
            else{
                resultsOne.setWin(resultsOne.getWin()+0);
                resultsOne.setDraw(resultsOne.getDraw()+0);
                resultsOne.setLose(resultsOne.getLose()+1);
                resultsOne.setPoints(resultsOne.getPoints()+0);
                resultsDAO.updateUserResults(resultsOne, userOne);
                resultsTwo.setWin(resultsTwo.getWin()+1);
                resultsTwo.setDraw(resultsTwo.getDraw()+0);
                resultsTwo.setLose(resultsTwo.getLose()+0);
                resultsTwo.setPoints(resultsTwo.getPoints()+2);
                resultsDAO.updateUserResults(resultsTwo, userTwo);
                System.out.println("user 2 wins");
            }

            return new ModelAndView("redirect:/chooseYourOpponent");
        }
        catch(Exception e){
            return new ModelAndView("error");
        }
    }
    
    @RequestMapping(value = "viewRankings")
    public ModelAndView viewRankings(HttpSession session){
        ModelAndView model = new ModelAndView();
        List<Results> rankings = resultsDAO.getAllResults();
        model.addObject("rankings", rankings);
        if(session.getAttribute("loguser") != null){
            model.setViewName("rankings");
        }
        else{
            model.setViewName("adminrankings");
        }
        return model;
    }
}
