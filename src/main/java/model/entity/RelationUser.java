/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

/**
 *
 * @author andre
 */
public class RelationUser {
    
    private User userOne;
    
    private User userTwo;
    
    private Relation status;

    public RelationUser() {
    }

    public RelationUser(User userOne, User userTwo, Relation status) {
        this.userOne = userOne;
        this.userTwo = userTwo;
        this.status = status;
    }

    public User getUserOne() {
        return userOne;
    }

    public void setUserOne(User userOne) {
        this.userOne = userOne;
    }

    public User getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(User userTwo) {
        this.userTwo = userTwo;
    }

    public Relation getStatus() {
        return status;
    }

    public void setStatus(Relation status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RelationUser{" + "userOne=" + userOne + ", userTwo=" + userTwo + ", status=" + status + '}';
    }
    
    
}
