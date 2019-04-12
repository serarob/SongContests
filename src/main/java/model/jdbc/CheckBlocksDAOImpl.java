/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jdbc;

import model.DAO.CheckBlocksDAO;
import model.DAO.UserDAO;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andre
 */
@Repository
public class CheckBlocksDAOImpl implements CheckBlocksDAO {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private UserDAO userDAO;

    @Override
    public User getBlocker(User userOne, User userTwo) {
        User blocker = new User();
        String sql = "select blocker from checkblocks where blocker = " + userOne.getUid() + " and blocked = " + userTwo.getUid();
        try {
            Long blockerId = jdbcTemplate.queryForLong(sql);
            blocker = userDAO.getUserById(blockerId);
            return blocker;
        } catch (Exception e) {
            return blocker;
        }
    }

    @Override
    public void createCheckBlock(User userOne, User userTwo) {
        try {
            String sql = "insert into checkblocks values(" + userOne.getUid() + "," + userTwo.getUid() + ")";
            jdbcTemplate.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCheckBlock(User userOne, User userTwo) {
        try {
            String sql = "delete from checkblocks where blocker = "+userOne.getUid()+" and blocked = "+userTwo.getUid();
            jdbcTemplate.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
