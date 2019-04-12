/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DAO.RoleUserDAO;
import model.entity.Role;
import model.entity.RoleUser;
import model.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andre
 */
@Repository
public class RoleUserDAOImpl implements RoleUserDAO {
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveRoleUser(RoleUser roleuser) {
        try{
            Role role = Role.USER;
            roleuser.setRole(role);
            String sql = "insert into roleuser values("+roleuser.getUser().getUid()+","+ roleuser.getRole().getRid()+")";
            jdbcTemplate.execute(sql);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public RoleUser getRoleUserById(Long uid, User user) {
        RoleUser roleuser = new RoleUser();
        Role role = null;
        String sql = "select rid from roleuser where uid = "+uid;
        Long rid = jdbcTemplate.queryForLong(sql);
        try {
            role = Role.getRoleFor(rid);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        roleuser.setUser(user);
        roleuser.setRole(role);
        return roleuser;
    }

    @Override
    public void changeRole(RoleUser roleUser) {
        try{
            String sql = "update roleuser set rid=? WHERE uid="+roleUser.getUser().getUid();
            jdbcTemplate.update(sql, new Object[] 
            { 
                roleUser.getRole().getRid()
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
