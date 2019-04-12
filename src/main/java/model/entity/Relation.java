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
public class Relation {
    
    private final Long idrel;
    private final String name;
    
    public static final Relation PENDING  = new Relation(1l, "Pending...");
    public static final Relation ACCEPTED = new Relation(2l, "Friends");
    public static final Relation BLOCKED = new Relation(3l, "Blocked");

    private Relation(Long idrel, String name) {
        this.idrel = idrel;
        this.name = name;
    }

    public Long getIdrel() {
        return idrel;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Relation{" + "idrel=" + idrel + ", name=" + name + '}';
    }
   
    public static final Relation getRelationFor(Long idrel) throws Exception {
        if (idrel == PENDING.getIdrel()) {
            return PENDING;
        }
        else if (idrel == ACCEPTED.getIdrel()) {
            return ACCEPTED;
        }
        else if (idrel == BLOCKED.getIdrel()) {
            return BLOCKED;
        }
        else {
            throw new Exception("Invalid relation id: " + idrel);
        }
    }    
}
