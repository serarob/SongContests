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
import model.DAO.ScoresDAO;
import model.DAO.UserDAO;
import model.entity.Scores;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andre
 */
@Repository
public class ScoresDAOImpl implements ScoresDAO {
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Autowired
    private UserDAO userDAO;

    @Override
    public List<Scores> getMyScores() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveScores(Scores score) {
        String sql = "insert into scores values(default, ?, ?, ?, null, 1) ";
        jdbcTemplate.update(sql, new Object[]{
            score.getUserOne().getUid(), score.getUserTwo().getUid(), score.getPointsOne()
        });
    }

    @Override
    public void updateScores(Scores score) {
        String sql = "update scores set points_one=?, points_two=? "
                + " where ((user_one = "+score.getUserOne().getUid()+" "
                        + "and user_two = "+score.getUserTwo().getUid()+") or "
                        + "(user_one = "+score.getUserTwo().getUid()+" "
                        + "and user_two = "+score.getUserOne().getUid()+")) and"
                + " notification = 1";
        jdbcTemplate.update(sql, new Object[] 
        { score.getPointsOne(), score.getPointsTwo()
        });
    }

    @Override
    public Scores checkScore(Scores score) {
        if(score.getNotification() == null){
            score.setNotification(1l);
        }
        String sql = "select * from scores where ((user_one = ? and user_two = ?)"
                + " or (user_one = ? and user_two = ?)) and notification = ?";
        score = (Scores) jdbcTemplate.queryForObject(sql, new Object[]
        {score.getUserOne().getUid(), score.getUserTwo().getUid(), 
            score.getUserTwo().getUid(), score.getUserOne().getUid(), 
            score.getNotification()}
                , new RowMapper<Scores>() {
            @Override
            public Scores mapRow(ResultSet rs, int i) throws SQLException {
                Scores score = new Scores();
                score.setScoresId(rs.getLong("scores_id"));
                
                String sql1 = "select uid from user,scores where uid=user_one and scores_id = "+score.getScoresId();
                Long uid = jdbcTemplate.queryForLong(sql1);
                User userOne = userDAO.getUserById(uid);
                
                String sql2 = "select uid from user,scores where uid=user_two and scores_id = "+score.getScoresId();
                uid = jdbcTemplate.queryForLong(sql2);
                User userTwo = userDAO.getUserById(uid);
                
                score.setUserOne(userOne);
                score.setUserTwo(userTwo);
                score.setPointsOne(rs.getLong("points_one"));
                score.setPointsTwo(rs.getLong("points_two"));
                score.setNotification(rs.getLong("notification"));
                return score;
            }
        });
        return score;
    }

    @Override
    public void deleteScores(User userOne, User userTwo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getScoresNotifications(User user) {
        String sql = "select count(notification) from scores where user_one="
                +user.getUid()+" or user_two = "+user.getUid();
        Long notifications = jdbcTemplate.queryForLong(sql);
        return notifications;
    }

    @Override
    public Long getScoresNotificationsFromSpecificUser(User userOne, User userTwo) {
        String sql = "select count(notification) from scores where "
                + "(user_one = "+userOne.getUid() +" and user_two = "
                +userTwo.getUid()+") or (user_one = "+userTwo.getUid() +" and "
                + "user_two = "+userOne.getUid()+")";
        Long notifications = jdbcTemplate.queryForLong(sql);
        return notifications;
    }

    @Override
    public void clearScoresNotificationsFromSpecificUser(User userOne, User userTwo) {
         String sql = "update scores set notification = null where user_one = "
                + ""+userOne.getUid()+ " and user_two = "+userTwo.getUid();
        jdbcTemplate.execute(sql);
    }

    @Override
    public List<Scores> getMyLatestScores(User user) {
        String sql = "select * from scores where user_one = "+user.getUid()+" or user_two = "+user.getUid()+" order by scores_id desc limit 10";
        List<Scores> list = jdbcTemplate.query(sql, new ResultSetExtractor<List<Scores>>() {
            @Override
            public List<Scores> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Scores> list = new ArrayList<>();
                while(rs.next()){
                    Scores score = new Scores();
                    score.setScoresId(rs.getLong("scores_id"));
                    
                    String sql1 = "select user_one from user,scores where uid=user_one and scores_id = "+score.getScoresId();
                    Long uid = jdbcTemplate.queryForLong(sql1);
                    User userOne = userDAO.getUserById(uid);
                    
                    String sql2 = "select user_two from user,scores where uid=user_two and scores_id = "+score.getScoresId();
                    uid = jdbcTemplate.queryForLong(sql2);
                    User userTwo = userDAO.getUserById(uid);
                    
                    score.setUserOne(userOne);
                    score.setUserTwo(userTwo);
                    score.setPointsOne(rs.getLong("points_one"));
                    score.setPointsTwo(rs.getLong("points_two"));
                    list.add(score);
                }
                return list;
            }

        });
        return list;
    }
    
}
