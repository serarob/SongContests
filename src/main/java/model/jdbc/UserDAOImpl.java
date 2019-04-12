/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jdbc;

import encryption.CryptoConverter;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DAO.UserDAO;
import model.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author andre
 */
@Repository
public class UserDAOImpl implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveUser(User user, MultipartFile photo) throws IOException {
        byte[] photoBytes = photo.getBytes();
        String sql = "insert into user values(default, ?, ?, ?, ?, ?, now(), ?, ?) ";
        jdbcTemplate.update(sql, new Object[]{
            user.getUsername(), user.getPassword(), user.getFirstname(), user.getLastname(),
            user.getEmail(), photoBytes, user.getDob()
        });
    }

    @Override
    public User getUserById(Long uid) {
        String sql = "select * from user where uid = ?";
        User user = (User) jdbcTemplate.queryForObject(sql, new Object[]{uid}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int i) throws SQLException {
                User user = new User();
                user.setUid(rs.getLong("uid"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setDoc(rs.getTimestamp("doc"));
                user.setDob(rs.getDate("dob"));
                return user;
            }
        });
        return user;
    }

    @Override
    public void updateUser(User user, MultipartFile photo) throws IOException {
        byte[] photoBytes = photo.getBytes();
        String sql = "UPDATE user SET username=?, password=?, firstname=?, lastname=?, email=?, picture=?, dob=? WHERE uid="+user.getUid();
        jdbcTemplate.update(sql, new Object[] 
        { user.getUsername(), user.getPassword(), user.getFirstname(),
            user.getLastname(), user.getEmail(), photoBytes , user.getDob()
        });
    }

    @Override
    public void deleteUser(Long uid) {
        String sql = "delete from user where uid=?";
        jdbcTemplate.update(sql, new Object[]
        { uid });
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "select * from user";
        List<User> list = jdbcTemplate.query(sql, new ResultSetExtractor<List<User>>() {
            @Override
            public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<User> list = new ArrayList<>();
                while(rs.next()){
                    User user = new User();
                    user.setUid(rs.getLong("uid"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setEmail(rs.getString("email"));
                    user.setDoc(rs.getTimestamp("doc"));
                    user.setPicture(rs.getBytes("picture"));
                    user.setDob(rs.getDate("dob"));
                    list.add(user);
                }
                return list;
            }

        });
        return list;
    }

    @Override
    public User getUserByLogin(String username, String password) {
        String decsql = "select password from user where username = ?";
        User user = jdbcTemplate.queryForObject(decsql, new Object [] {username}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int i) throws SQLException {
                User decuser = new User();
                decuser.setPassword(rs.getString("password"));
                return decuser;
            }
        });

        if(password.equals(CryptoConverter.decrypt(user.getPassword()))){
            password = user.getPassword();
            String sql = "select * from user where username = ? and password = ?";
            user = (User) jdbcTemplate.queryForObject(sql, new Object[]{username, password},
                    new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setUid(rs.getLong("uid"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setEmail(rs.getString("email"));
                    user.setDoc(rs.getTimestamp("doc"));
                    user.setPicture(rs.getBytes("picture"));
                    user.setDob(rs.getDate("dob"));
                    return user;
                }

            });
        }
        return user;
    }

    @Override
    public Long getMaxUserId() {
        String sql = "select max(uid) from user;";
        long id = (Long) jdbcTemplate.queryForLong(sql);
        return id;
    }

    @Override
    public Blob getPictureByUserId(Long uid) {
        String query = "select picture from user where uid=?";

        Blob picture = jdbcTemplate.queryForObject(query, new Object[] { uid }, Blob.class);
        

        return picture;
    }

    @Override
    public List<User> getUsersByPage(int pageid, int total) {
         String sql= "select * from user limit "+pageid+","+total;
 
        return jdbcTemplate.query(sql, new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setUid(rs.getLong("uid"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setDoc(rs.getTimestamp("doc"));
                user.setPicture(rs.getBytes("picture"));
                user.setDob(rs.getDate("dob"));
                return user;
            }
        });
    }

    @Override
    public int getUsersCount() {
        String sql = "select count(*) from user";
        return jdbcTemplate.queryForInt(sql);
    }

}
