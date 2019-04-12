/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;
import model.entity.Message;
import model.entity.User;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author andre
 */
public interface MessageDAO {
    
    public void saveMessage(Message message, MultipartFile photo) throws IOException;
    
    public Message getMessageById(Long mid);
    
    public List<Message> getMyMessages(final User user);
    
    public List<Message> getChat(final User sender, final User receiver);
    
    public void updateMessage(Message message, MultipartFile photo) throws IOException;
    
    public void deleteMessage(Long mid);
    
    public List<Message> getAllMessages();
    
    public Blob getPictureByMessageId(Long mid);
    
    public Long getMessagesNotifications(User user);
    
    public Long getMessagesNotificationsFromSpecificUser(User sender, User receiver);
    
    public void clearMessagesNotificationsFromSpecificUser(User sender, User receiver);

}
