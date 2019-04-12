/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;

/**
 *
 * @author andre
 */
public class Scores implements Serializable {
    
    Long scoresId;
    User userOne;
    User userTwo;
    Long pointsOne;
    Long pointsTwo;
    Long notification;

    public Scores() {
        super();
    }

    public Scores(User userOne, User userTwo, Long pointsOne, Long pointsTwo, Long notification) {
        super();
        this.userOne = userOne;
        this.userTwo = userTwo;
        this.pointsOne = pointsOne;
        this.pointsTwo = pointsTwo;
        this.notification = notification;
    }

    public Long getScoresId() {
        return scoresId;
    }

    public void setScoresId(Long scoresId) {
        this.scoresId = scoresId;
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

    public Long getPointsOne() {
        return pointsOne;
    }

    public void setPointsOne(Long pointsOne) {
        this.pointsOne = pointsOne;
    }

    public Long getPointsTwo() {
        return pointsTwo;
    }

    public void setPointsTwo(Long pointsTwo) {
        this.pointsTwo = pointsTwo;
    }

    public Long getNotification() {
        return notification;
    }

    public void setNotification(Long notification) {
        this.notification = notification;
    }

    @Override
    public String toString() {
        return "Scores{" + "userOne=" + userOne + ", userTwo=" + userTwo + ", pointsOne=" + pointsOne + ", pointsTwo=" + pointsTwo + '}';
    }
    
}
