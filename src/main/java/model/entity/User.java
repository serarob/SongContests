/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author andre
 */
public class User implements Serializable {
    
    private Long uid;
    
    @NotNull(message = "is required")
    @Size(min = 2, message = "at least 2 characters required")
    private String username;
    
    @NotNull(message = "is required")
    @Size(min = 3, message = "at least 3 characters required")
    private String password;
    
    @NotNull(message = "is required")
    private String firstname;
    
    @NotNull(message = "is required")
    private String lastname;
    
    @NotNull(message = "is required")
    private String email;
    
    
    private Timestamp doc;
   
    private byte [] picture;
    
    private Date dob;

    public User() {
        super();
        this.doc = new Timestamp(System.currentTimeMillis());
    }

    public User(String username, String password, String firstname, String lastname, String email, byte [] picture, Date dob) {
        super();
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.doc = new Timestamp(System.currentTimeMillis());
        this.picture = picture;
        this.dob = dob;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }    
    

    public Timestamp getDoc() {
        return doc;
    }

    public void setDoc(Timestamp doc) {
        this.doc = doc;
    }


    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
    
    

    @Override
    public String toString() {
        return "User{" + "uid=" + uid + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + '}';
    }
    
    

    
    
    
    
}
