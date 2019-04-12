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
public class Results implements Serializable {
    
    private Long resId;
    private User user;
    private Long win;
    private Long draw;
    private Long lose;
    private Long points;

    public Results() {
        super();
    }

    public Results(User user, Long win, Long draw, Long lose, Long points) {
        super();
        this.user = user;
        this.win = win;
        this.draw = draw;
        this.lose = lose;
        this.points = points;
    }

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getWin() {
        return win;
    }

    public void setWin(Long win) {
        this.win = win;
    }

    public Long getDraw() {
        return draw;
    }

    public void setDraw(Long draw) {
        this.draw = draw;
    }

    public Long getLose() {
        return lose;
    }

    public void setLose(Long lose) {
        this.lose = lose;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }
    
    @Override
    public String toString() {
        return "Results{" + "user=" + user + ", win=" + win + ", draw=" + draw + ", lose=" + lose + '}';
    }
    
    
}
