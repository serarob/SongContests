/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DAO.RelationUserDAO;
import model.DAO.UserDAO;
import model.entity.Relation;
import model.entity.RelationUser;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andre
 */
@Repository
public class RelationUserDAOImpl implements RelationUserDAO {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Autowired
    private UserDAO userDAO;

    @Override
    public void makeFriendRequest(RelationUser relationUser) {
        relationUser = getRelationAmongUsers(relationUser.getUserOne(), relationUser.getUserTwo());
        if (relationUser.getStatus() == null) {
            String sql = "insert into relationuser values("
                    + relationUser.getUserOne().getUid() + ","
                    + relationUser.getUserTwo().getUid() + ",1)";
            jdbcTemplate.execute(sql);
        }
    }

    @Override
    public RelationUser getRelationAmongUsers(User userOne, User userTwo) {
        RelationUser relationUser = new RelationUser();
        Relation relation = null;
        relationUser.setUserOne(userOne);
        relationUser.setUserTwo(userTwo);
        try {
            String sql = "select status from relationuser where (user_one = "
                    + "" + userOne.getUid() + " and user_two = " + userTwo.getUid() + ")"
                    + " or (user_one = " + userTwo.getUid() + " and user_two = "
                    + "" + userOne.getUid() + ")";
            Long status = jdbcTemplate.queryForLong(sql);
            if (status != null) {
                relation = Relation.getRelationFor(status);
            }
            relationUser.setStatus(relation);
            return relationUser;
        } catch (Exception ex) {
            relationUser.setStatus(relation);
            return relationUser;
        }

    }

    @Override
    public void blockUser(RelationUser relationUser) {
        String sql = "update relationuser set status = ? where (user_one = "
            + "" + relationUser.getUserOne().getUid() + " "
            + "and user_two = " + relationUser.getUserTwo().getUid() + ") or"
            + " (user_one = " + relationUser.getUserTwo().getUid()
            + " and user_two = " + relationUser.getUserOne().getUid() + ")";
        jdbcTemplate.update(sql, new Object[] 
        { 
            relationUser.getStatus().getIdrel()
        });
    }

    @Override
    public void acceptFriendRequest(RelationUser relationUser) {
        if (relationUser.getStatus().getIdrel() == 1) {
            String sql = "select user_one from relationuser where (user_one = "
                    + "" + relationUser.getUserOne().getUid() + " "
                    + "and user_two = " + relationUser.getUserTwo().getUid() + ") or"
                    + " (user_one = " + relationUser.getUserTwo().getUid()
                    + " and user_two = " + relationUser.getUserOne().getUid() + ")";
            Long idUserMadeFriendRequest = jdbcTemplate.queryForLong(sql);
            if (idUserMadeFriendRequest == relationUser.getUserTwo().getUid()) {
                String sql1 = "update relationuser set status=2 where (user_one = "
                    + "" + relationUser.getUserOne().getUid() + " "
                    + "and user_two = " + relationUser.getUserTwo().getUid() + ") or"
                    + " (user_one = " + relationUser.getUserTwo().getUid()
                    + " and user_two = " + relationUser.getUserOne().getUid() + ")";
                jdbcTemplate.execute(sql1);
            }
        }
    }

    @Override
    public void deleteFriend(RelationUser relationUser) {
        String sql = "delete from relationuser where (user_one = "
                    + "" + relationUser.getUserOne().getUid() + " "
                    + "and user_two = " + relationUser.getUserTwo().getUid() + ") or"
                    + " (user_one = " + relationUser.getUserTwo().getUid()
                    + " and user_two = " + relationUser.getUserOne().getUid() + ")";
        jdbcTemplate.execute(sql);
    }

    @Override
    public List<User> getMyFriends(User user) {
        String sql = "select user_one, user_two from relationuser where (user_one = "+user.getUid()+" or user_two = "+user.getUid()+") and status = 2";
        List<RelationUser> list = jdbcTemplate.query(sql, new ResultSetExtractor<List<RelationUser>>() {
        @Override
        public List<RelationUser> extractData(ResultSet rs) throws SQLException, DataAccessException {
            List<RelationUser> list = new ArrayList<>();
            while(rs.next()){
                User userOne = userDAO.getUserById(rs.getLong("user_one"));
                User userTwo = userDAO.getUserById(rs.getLong("user_two"));
                RelationUser relationUser = new RelationUser();
                relationUser.setUserOne(userOne);
                relationUser.setUserTwo(userTwo);
                list.add(relationUser);
            }
            return list;
            }
        });
        List<User> friendsList = new ArrayList<>();
        for (RelationUser relationUser : list) {
            if(relationUser.getUserOne().getUid() != user.getUid()){
                friendsList.add(relationUser.getUserOne());
            }
            if(relationUser.getUserTwo().getUid() != user.getUid()){
                friendsList.add(relationUser.getUserTwo());
            }
        }
        return friendsList;
    }

}
