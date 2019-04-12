/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;

/**
 *
 * @author Padelis
 */
public class Song implements Serializable{
    
    private Long sid;
    private String title;
    private String urlsample;
    private String question;
    private String answerone;
    private String answertwo;
    private String answerthree;
    private String answerfour;
    private String correct;
    private String urlfullsong;

    public Song(Long sid, String title, String urlsample, String question, String answerone, String answertwo, String answerthree, String answerfour, String correct, String urlfullsong) {
        super();
        this.sid = sid;
        this.title = title;
        this.urlsample = urlsample;
        this.question = question;
        this.answerone = answerone;
        this.answertwo = answertwo;
        this.answerthree = answerthree;
        this.answerfour = answerfour;
        this.correct = correct;
        this.urlfullsong = urlfullsong;
    }

    public Song() {
        super();
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlsample() {
        return urlsample;
    }

    public void setUrlsample(String urlsample) {
        this.urlsample = urlsample;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerone() {
        return answerone;
    }

    public void setAnswerone(String answerone) {
        this.answerone = answerone;
    }

    public String getAnswertwo() {
        return answertwo;
    }

    public void setAnswertwo(String answertwo) {
        this.answertwo = answertwo;
    }

    public String getAnswerthree() {
        return answerthree;
    }

    public void setAnswerthree(String answerthree) {
        this.answerthree = answerthree;
    }

    public String getAnswerfour() {
        return answerfour;
    }

    public void setAnswerfour(String answerfour) {
        this.answerfour = answerfour;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getUrlfullsong() {
        return urlfullsong;
    }

    public void setUrlfullsong(String urlfullsong) {
        this.urlfullsong = urlfullsong;
    }

    @Override
    public String toString() {
        return "Song{" + "title=" + title + ", urlsample=" + urlsample + ", question=" + question + ", answerone=" + answerone + ", answertwo=" + answertwo + ", answerthree=" + answerthree + ", answerfour=" + answerfour + ", correct=" + correct + ", urlfullsong=" + urlfullsong + '}';
    }
    
    
    
}
