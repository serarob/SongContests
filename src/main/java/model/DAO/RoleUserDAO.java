/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.util.List;
import model.entity.RoleUser;
import model.entity.User;

/**
 *
 * @author andre
 */
public interface RoleUserDAO {
   
    public void saveRoleUser(RoleUser roleuser);
    
    public RoleUser getRoleUserById(Long uid, User user);
    
    public void changeRole(RoleUser roleUser);
    
    
}
