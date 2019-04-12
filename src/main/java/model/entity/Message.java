/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.validation.constraints.NotNull;

/**
 *
 * @author andre
 */
public class Message implements Serializable {
    private Long mid;
    private String text;
    private byte[] upload;
    private Timestamp doc;
    
    @NotNull(message = "is required")
    private User sender;
    
    @NotNull(message = "is required")
    private User receiver;
    
    private Long notification;

    public Message() {
        super();
        this.doc = new Timestamp(System.currentTimeMillis());
    }

    public Message(String text, User sender, User receiver) {
        super();
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;
        this.doc = new Timestamp(System.currentTimeMillis());
    }

    public Message(byte[] upload, User sender, User receiver) {
        super();
        this.upload = upload;
        this.sender = sender;
        this.receiver = receiver;
        this.doc = new Timestamp(System.currentTimeMillis());
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getUpload() {
        return upload;
    }

    public void setUpload(byte[] upload) {
        this.upload = upload;
    }

    public Timestamp getDoc() {
        return doc;
    }

    public void setDoc(Timestamp doc) {
        this.doc = doc;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Long getNotification() {
        return notification;
    }

    public void setNotification(Long notification) {
        this.notification = notification;
    }
    
    @Override
    public String toString() {
        return "{" + "mid=" + mid + ", text=" + text + ", doc=" + doc + ", sender=" + sender + ", receiver=" + receiver + '}';
    }

    

    

   
    
    
    
    
    
    
    
    
}
