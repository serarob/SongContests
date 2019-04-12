/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.util.List;
import model.entity.Results;
import model.entity.User;

/**
 *
 * @author andre
 */
public interface ResultsDAO {
    
    public List<Results> getAllResults();
    
    public Results getUserResults(User user);
    
    public void updateUserResults(Results results, User user);
    
    public void saveUserResults(User user);
}
