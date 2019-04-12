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
public class Role {
   
    public static final Role USER  = new Role(1l, "Simple User");
    public static final Role ADMIN = new Role(2l, "Administrator");
    
    private final Long rid;
    private final String name;
    

    private Role(Long rid, String name) {
        this.rid = rid;
        this.name = name;
    }

    public Long getRid() {
        return rid;
    }

    

    public String getName() {
        return name;
    }

    

    @Override
    public String toString() {
        return "Role{" + "rid=" + rid + ", name=" + name + '}';
    }
    
    public static final Role getRoleFor(Long rid) throws Exception {
        if (rid == USER.getRid()) {
            return USER;
        }
        else if (rid == ADMIN.getRid()) {
            return ADMIN;
        }
        else {
            throw new Exception("Invalid role id: " + rid);
        }
    }
    
}
