/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAO.MessageDAO;
import model.DAO.RelationUserDAO;
import model.DAO.UserDAO;
import model.entity.Message;
import model.entity.User;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import utilities.RestoreInFile;

/**
 *
 * @author andre
 */
@Controller
public class MessageController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private RelationUserDAO relationUserDAO;

    @RequestMapping(value = "/viewAllMessages", method = RequestMethod.GET)
    public String viewAllMessages(HttpSession session, Model model) {
        try {
            List<Message> messageList = messageDAO.getAllMessages();
            model.addAttribute("list", messageList);
            if (session.getAttribute("admin") != null) {
                return "viewallmessages";
            } else {
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ModelAndView messages(HttpSession session, ModelAndView model) {
        try {
            if (session.getAttribute("loguser") != null) {
                User user = (User) session.getAttribute("loguser");
                List<User> list = relationUserDAO.getMyFriends(user);
                Map<User, Long> map = new LinkedHashMap<>();
                for (User sender : list) {
                    if (messageDAO.getMessagesNotificationsFromSpecificUser(sender, user) == 0l) {
                        map.put(sender, null);
                    } else {
                        map.put(sender, messageDAO.getMessagesNotificationsFromSpecificUser(sender, user));
                    }
                }
                model.addObject("map", map);
                model.addObject("user", user);
                model.setViewName("messages");
                return model;
            } else {
                model.setViewName("error");
                return model;
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.setViewName("error");
            return model;
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveMessage(@ModelAttribute("message") Message message, HttpSession session) throws IOException {
        if (session.getAttribute("loguser") != null) {
            User sender = (User) session.getAttribute("loguser");
            User receiver = (User) session.getAttribute("receiver");
            message.setSender(sender);
            message.setReceiver(receiver);
            MultipartFile photo = null;
            try {
                if (messageDAO.getMessageById(message.getMid()) != null);
                messageDAO.updateMessage(message, photo);
            } catch (EmptyResultDataAccessException e) {

                messageDAO.saveMessage(message, photo);
            }
            return new ModelAndView("redirect:/chatForm");
        } else {
            return new ModelAndView("error");
        }
    }

    @RequestMapping(value = "/edit/{mid}/{uid}")
    public ModelAndView editMessage(@ModelAttribute("message") Message message,
            @ModelAttribute("user") User receiver, @PathVariable("mid") Long mid,
            @PathVariable("uid") Long uid, HttpSession session) {
        if (session.getAttribute("loguser") != null) {
            ModelAndView model = new ModelAndView("cchat");
            User logUser = (User) session.getAttribute("loguser");
            receiver = userDAO.getUserById(uid);
            session.setAttribute("receiver", receiver);
            message = messageDAO.getMessageById(mid);
            Map<Message, String> map = new LinkedHashMap<>();
            String divReceiverChat = "<div class=\"d-flex justify-content-start mb-4\">\n"
                    + "                                    <div class=\"msg_cotainer\" >";
            String divLogUserChat = "<div class=\"d-flex justify-content-end mb-4\">\n"
                    + "								<div class=\"msg_cotainer_send\">";
            List<Message> messageList = messageDAO.getChat(logUser, receiver);
            for (Message chatMessage : messageList) {
                if (!chatMessage.getSender().getUsername().equals(logUser.getUsername())) {
                    map.put(chatMessage, divReceiverChat);
                } else {
                    map.put(chatMessage, divLogUserChat);
                }
            }

            model.addObject("message", message);
            model.addObject("logUser", logUser);
            model.addObject("receiver", receiver);
            model.addObject("map", map);

            return model;
        } else {
            return new ModelAndView("error");
        }
    }

    @RequestMapping(value = "/delete/{mid}/{uid}")
    public ModelAndView deleteMessage(@ModelAttribute("message") Message message,
            @ModelAttribute("user") User receiver, @PathVariable("mid") Long mid,
            @PathVariable("uid") Long uid, HttpSession session) {
        if (session.getAttribute("loguser") != null) {
            messageDAO.deleteMessage(mid);

            receiver = userDAO.getUserById(uid);

            session.setAttribute("receiver", receiver);
            ModelAndView model = new ModelAndView("redirect:/chatForm");

            model.addObject("receiver", receiver);
            return model;
        } else {
            return new ModelAndView("error");
        }
    }

    @RequestMapping(value = "/chatForm")
    public ModelAndView chat(@ModelAttribute("message") Message message, @ModelAttribute("receiver") User receiver, HttpSession session) {
        if (session.getAttribute("loguser") != null) {
            try {
                User logUser = (User) session.getAttribute("loguser");
                if (receiver.getUid() != null) {
                    session.setAttribute("receiver", receiver);
                    receiver = (User) userDAO.getUserById(receiver.getUid());
                } else {
                    receiver = (User) session.getAttribute("receiver");
                }
                ModelAndView model = new ModelAndView("cchat");
                receiver = (User) userDAO.getUserById(receiver.getUid());
                Map<Message, String> map = new LinkedHashMap<>();
                String divReceiverChat = "<div class=\"d-flex justify-content-start mb-4\">\n"
                        + "                                    <div class=\"msg_cotainer\" >";
                String divLogUserChat = "<div class=\"d-flex justify-content-end mb-4\">\n"
                        + "								<div class=\"msg_cotainer_send\">";
                List<Message> messageList = messageDAO.getChat(logUser, receiver);
                for (Message chatMessage : messageList) {
                    if (!chatMessage.getSender().getUsername().equals(logUser.getUsername())) {
                        map.put(chatMessage, divReceiverChat);
                    } else {
                        map.put(chatMessage, divLogUserChat);
                    }
                }
                messageDAO.clearMessagesNotificationsFromSpecificUser(receiver, logUser);
                model.addObject("map", map);

                model.addObject("logUser", logUser);
                model.addObject("receiver", receiver);
                return model;
            } catch (Exception e) {
                return new ModelAndView("redirect:/messages");
            }
        } else {
            return new ModelAndView("error");
        }
    }

    @RequestMapping(value = "/clearMessagesNotificationsFromSpecificUser")
    public void clearMessagesNotificationsFromSpecificUser(HttpSession session) {
        User sender = (User) session.getAttribute("loguser");
        User receiver = (User) session.getAttribute("receiver");
        receiver = (User) userDAO.getUserById(receiver.getUid());
        messageDAO.clearMessagesNotificationsFromSpecificUser(receiver, sender);
    }

    @RequestMapping(value = "/upToTable")
    public ModelAndView tableOfMessages(@ModelAttribute("message") Message message, @ModelAttribute("receiver") User receiver, HttpSession session) {
        if (session.getAttribute("loguser") != null) {
            try {
                User logUser = (User) session.getAttribute("loguser");
                if (receiver.getUid() != null) {
                    session.setAttribute("receiver", receiver);
                    receiver = (User) userDAO.getUserById(receiver.getUid());
                } else {
                    receiver = (User) session.getAttribute("receiver");
                }
                ModelAndView model = new ModelAndView("ttable-chat");
                receiver = (User) userDAO.getUserById(receiver.getUid());
                Map<Message, String> map = new LinkedHashMap<>();
                String divReceiverChat = "<div class=\"d-flex justify-content-start mb-4\">\n"
                        + "                                    <div class=\"msg_cotainer\" >";
                String divLogUserChat = "<div class=\"d-flex justify-content-end mb-4\">\n"
                        + "								<div class=\"msg_cotainer_send\">";
                List<Message> messageList = messageDAO.getChat(logUser, receiver);
                for (Message chatMessage : messageList) {
                    if (!chatMessage.getSender().getUsername().equals(logUser.getUsername())) {
                        map.put(chatMessage, divReceiverChat);
                    } else {
                        map.put(chatMessage, divLogUserChat);
                    }
                }
                messageDAO.clearMessagesNotificationsFromSpecificUser(receiver, logUser);
                model.addObject("map", map);

                model.addObject("logUser", logUser);
                model.addObject("receiver", receiver);
                return model;
            } catch (Exception e) {
                return new ModelAndView("redirect:/messages");
            }
        } else {
            return new ModelAndView("error");
        }
    }

    @RequestMapping(value = "/getMessagePhoto/{mid}")
    public void getUserPhoto(HttpServletResponse response, @PathVariable("mid") Long mid) throws Exception {

        response.setContentType("image/jpeg");

        Blob ph = messageDAO.getPictureByMessageId(mid);

        byte[] bytes = ph.getBytes(1, (int) ph.length());
        InputStream inputStream = new ByteArrayInputStream(bytes);
        IOUtils.copy(inputStream, response.getOutputStream());
    }

    @RequestMapping(value = "/downloadMyMessages")
    public ModelAndView downloadMyMessages(HttpSession session) throws Exception {
        if (session.getAttribute("loguser") != null) {
            User user = (User) session.getAttribute("loguser");
            RestoreInFile.createFile(user.getFirstname() + "Messages");
            for (Message message : messageDAO.getMyMessages(user)) {
                RestoreInFile.writeFile(user.getFirstname() + "Messages", message.toString());
            }
            ModelAndView model = new ModelAndView();
            model.addObject("user", user);
            model.setViewName("profileuser");
            return model;
        } else {
            return new ModelAndView("error");
        }
    }

}
