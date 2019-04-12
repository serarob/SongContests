/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jdbc;

import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DAO.MessageDAO;
import model.DAO.UserDAO;
import model.entity.Message;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MessageDAOImpl implements MessageDAO {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Autowired
    private UserDAO userDAO;
    
    @Override
    public void saveMessage(Message message, MultipartFile photo) throws IOException {
        
        //byte[] photoBytes = photo.getBytes();
        message.setNotification(1l);
        String sql = "insert into message values(default,?,null,now(),?,?,?)";
        jdbcTemplate.update(sql, new Object[]
        { message.getText(), message.getSender().getUid(), 
            message.getReceiver().getUid(), message.getNotification() });
    }

    @Override
    public Message getMessageById(Long mid) {
        String sql = "select * from message where mid = ?";
        Message message = (Message) jdbcTemplate.queryForObject(sql, new Object[]{mid}, new RowMapper<Message>() {
            @Override
            public Message mapRow(ResultSet rs, int i) throws SQLException {
                Message message = new Message();
                message.setMid(rs.getLong("mid"));
                
                String sql1 = "select uid from user,message where uid=sid and mid = "+message.getMid();
                Long uid = jdbcTemplate.queryForLong(sql1);
                User sender = userDAO.getUserById(uid);

                String sql2 = "select uid from user,message where uid=rid and mid = "+message.getMid();
                uid = jdbcTemplate.queryForLong(sql2);
                User receiver = userDAO.getUserById(uid);

                message.setText(rs.getString("text"));
                message.setUpload(rs.getBytes("upload"));
                message.setSender(sender);
                message.setReceiver(receiver);
                return message;
            }
        });
        return message;
    }

    @Override
    public List<Message> getChat(final User sender, final User receiver) {
        String sql = "select * from message where (sid="+sender.getUid()+" or sid="+receiver.getUid()+") and (rid="+sender.getUid()+" or rid="+receiver.getUid()+") order by doc desc limit 200";
        List<Message> list = jdbcTemplate.query(sql, new ResultSetExtractor<List<Message>>() {
            @Override
            public List<Message> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Message> list = new ArrayList<>();
                while(rs.next()){
                    Message message = new Message();
                    message.setMid(rs.getLong("mid"));
                    
                    String sql1 = "select uid from user,message where uid=sid and mid = "+message.getMid();
                    Long uid = jdbcTemplate.queryForLong(sql1);
                    User userSender = userDAO.getUserById(uid);
     
                    String sql2 = "select uid from user,message where uid=rid and mid = "+message.getMid();
                    uid = jdbcTemplate.queryForLong(sql2);
                    User userReceiver = userDAO.getUserById(uid);
                    
                    message.setText(rs.getString("text"));
                    message.setUpload(rs.getBytes("upload"));
                    message.setDoc(rs.getTimestamp("doc"));
                    message.setSender(userSender);
                    message.setReceiver(userReceiver);
                    list.add(message);
                }
                return list;
            }

        });
        return list;
    }

    @Override
    public void updateMessage(Message message, MultipartFile photo) throws IOException {
        //byte[] photoBytes = photo.getBytes();
        String sql = "update message set text=?, upload=null where mid=?";
        jdbcTemplate.update(sql, new Object[] 
        { message.getText(), message.getMid() });
    }

    @Override
    public void deleteMessage(Long mid) {
        System.out.println("inside deleteDAO");
        String sql = "delete from message where mid=?";
        jdbcTemplate.update(sql, new Object[]
        { mid });
    }

    @Override
    public List<Message> getAllMessages() {
        String sql = "select * from message order by doc desc";
        List<Message> list = jdbcTemplate.query(sql, new ResultSetExtractor<List<Message>>() {
            @Override
            public List<Message> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Message> list = new ArrayList<>();
                while(rs.next()){
                    Message message = new Message();
                    message.setMid(rs.getLong("mid"));
                    
                    String sql1 = "select uid from user,message where uid=sid and mid = "+message.getMid();
                    Long uid = jdbcTemplate.queryForLong(sql1);
                    User userSender = userDAO.getUserById(uid);
                    
                    String sql2 = "select uid from user,message where uid=rid and mid = "+message.getMid();
                    uid = jdbcTemplate.queryForLong(sql2);
                    User userReceiver = userDAO.getUserById(uid);
                    
                    message.setText(rs.getString("text"));
                    message.setUpload(rs.getBytes("upload"));
                    message.setDoc(rs.getTimestamp("doc"));
                    message.setSender(userSender);
                    message.setReceiver(userReceiver);
                    list.add(message);
                }
                return list;
            }

        });
        return list;
    }

    @Override
    public Blob getPictureByMessageId(Long mid) {
        String query = "select upload from message where mid=?";

        Blob picture = jdbcTemplate.queryForObject(query, new Object[] { mid }, Blob.class);
        

        return picture;
    }

    @Override
    public List<Message> getMyMessages(User user) {
        String sql = "select * from message where sid = "+user.getUid()+" or rid = "+user.getUid();
        List<Message> list = jdbcTemplate.query(sql, new ResultSetExtractor<List<Message>>() {
            @Override
            public List<Message> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Message> list = new ArrayList<>();
                while(rs.next()){
                    Message message = new Message();
                    message.setMid(rs.getLong("mid"));
                    
                    String sql1 = "select uid from user,message where uid=sid and mid = "+message.getMid();
                    Long uid = jdbcTemplate.queryForLong(sql1);
                    User userSender = userDAO.getUserById(uid);
                    
                    String sql2 = "select uid from user,message where uid=rid and mid = "+message.getMid();
                    uid = jdbcTemplate.queryForLong(sql2);
                    User userReceiver = userDAO.getUserById(uid);
                    
                    message.setText(rs.getString("text"));
                    message.setUpload(rs.getBytes("upload"));
                    message.setDoc(rs.getTimestamp("doc"));
                    message.setSender(userSender);
                    message.setReceiver(userReceiver);
                    list.add(message);
                }
                return list;
            }

        });
        return list;
    }

    @Override
    public Long getMessagesNotifications(User user) {
        String sql = "select count(notification) from message where rid = "+user.getUid();
        Long notifications = jdbcTemplate.queryForLong(sql);
        return notifications;
    }

    @Override
    public Long getMessagesNotificationsFromSpecificUser(User sender, User receiver) {
        String sql = "select count(notification) from message where "
                + "sid = "+sender.getUid() +" and rid = "+receiver.getUid();
        Long notifications = jdbcTemplate.queryForLong(sql);
        return notifications;
    }

    @Override
    public void clearMessagesNotificationsFromSpecificUser(User sender, User receiver) {
        String sql = "update message set notification = null where sid = "
                + ""+sender.getUid()+ " and rid = "+receiver.getUid();
        jdbcTemplate.execute(sql);
    }
    
}
