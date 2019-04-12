/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.util.List;
import model.entity.RelationUser;
import model.entity.User;

/**
 *
 * @author andre
 */
public interface RelationUserDAO {
    
    public void makeFriendRequest(RelationUser relationUser);
    
    public void acceptFriendRequest(RelationUser relationUser);
    
    public RelationUser getRelationAmongUsers(User userOne, User userTwo);
    
    public void blockUser(RelationUser relationUser);
    
    public void deleteFriend(RelationUser relationUser);
    
    public List<User> getMyFriends(User user);
}
