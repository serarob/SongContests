/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.util.List;
import model.entity.Scores;
import model.entity.User;

/**
 *
 * @author andre
 */
public interface ScoresDAO {
    
    public List<Scores> getMyScores();
    
    public void saveScores(Scores score);
    
    public void updateScores(Scores score);
    
    public Scores checkScore(Scores score);
    
    public void deleteScores(User userOne, User userTwo);
    
    public Long getScoresNotifications(User user);
    
    public Long getScoresNotificationsFromSpecificUser(User userOne, User userTwo);
    
    public void clearScoresNotificationsFromSpecificUser(User userOne, User userTwo);
    
    public List<Scores> getMyLatestScores(User user);
}
