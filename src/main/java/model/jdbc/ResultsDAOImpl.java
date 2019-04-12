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
import model.DAO.ResultsDAO;
import model.DAO.UserDAO;
import model.entity.Results;
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
public class ResultsDAOImpl implements ResultsDAO {
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Autowired
    private UserDAO userDAO;

    @Override
    public List<Results> getAllResults() {
        String sql = "select * from results order by points desc";
        List<Results> list = jdbcTemplate.query(sql, new ResultSetExtractor<List<Results>>() {
            @Override
            public List<Results> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Results> list = new ArrayList<>();
                while(rs.next()){
                    Results results = new Results();
                    results.setResId(rs.getLong("res_id"));
                    
                    String sql = "select user.uid from user,results where user.uid=results.uid and res_id = "+results.getResId();
                    Long uid = jdbcTemplate.queryForLong(sql);
                    User user = userDAO.getUserById(uid);
                    
                    results.setUser(user);
                    results.setWin(rs.getLong("win"));
                    results.setDraw(rs.getLong("draw"));
                    results.setLose(rs.getLong("lose"));
                    results.setPoints(rs.getLong("points"));
                    list.add(results);
                }
                return list;
            }
        });
        return list;
    }

    @Override
    public Results getUserResults(User user) {
        String sql = "select * from results where uid = ?";
        Results results = (Results) jdbcTemplate.queryForObject(sql, new Object[]{user.getUid()}, new RowMapper<Results>() {
            @Override
            public Results mapRow(ResultSet rs, int i) throws SQLException {
                Results results = new Results();
                results.setResId(rs.getLong("res_id"));
                String sql = "select user.uid from user,results where user.uid=results.uid and res_id = "+results.getResId();
                Long uid = jdbcTemplate.queryForLong(sql);
                User user = userDAO.getUserById(uid);
                results.setUser(user);
                results.setWin(rs.getLong("win"));
                results.setDraw(rs.getLong("draw"));
                results.setLose(rs.getLong("lose"));
                results.setPoints(rs.getLong("points"));
                return results;
            }
        });
        return results;
    }

    @Override
    public void updateUserResults(Results results, User user) {
        String sql = "update results set win=?, draw=?, lose=?, points=? where uid = "+user.getUid();
        jdbcTemplate.update(sql, new Object[] 
        { results.getWin(), results.getDraw(), results.getLose(), results.getPoints()
        });
    }

    @Override
    public void saveUserResults(User user) {
        System.out.println(user.getUid());
        String sql = "insert into results values(default,?, null, null, null,null) ";
        jdbcTemplate.update(sql, new Object[]{
            user.getUid()});
    }
    
}
