/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;
import model.entity.User;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author andre
 */
public interface UserDAO {
    
    public void saveUser(User user, MultipartFile photo) throws IOException;
    
    public User getUserById(Long uid);
    
    public void updateUser(User user, MultipartFile photo) throws IOException;
    
    public void deleteUser(Long uid);
    
    public List<User> getAllUsers();
    
    public User getUserByLogin(String username, String password);
    
    public Long getMaxUserId();
    
    public Blob getPictureByUserId(Long uid);
    
    public List<User> getUsersByPage(int pageid, int total);
    
    public int getUsersCount();
}
