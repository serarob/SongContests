/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import model.DAO.CheckBlocksDAO;
import model.DAO.RelationUserDAO;
import model.DAO.UserDAO;
import model.entity.Relation;
import model.entity.RelationUser;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author andre
 */
@Controller
public class RelationUserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RelationUserDAO relationUserDAO;

    @Autowired
    private CheckBlocksDAO checkBlocksDAO;

    @RequestMapping(value = "/viewUsers/{page_id}", method = RequestMethod.GET)
    public String viewUsers(HttpSession session, Model model, @PathVariable("page_id") int page_id) {
        try {
            User logUser = (User) session.getAttribute("loguser");
            List<RelationUser> relationUserList = new ArrayList<>();
            int total = 3;
            int numOfPages = userDAO.getUsersCount()-total;
            int previousNext = 1;
            /*if (page_id == 1) {
                // do nothing!
            } else {
                page_id = (page_id - 1) * total + 1;
            }*/
            List<User> userList = userDAO.getUsersByPage(page_id, total);
            
            //userList.remove(0);
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getUsername().equals(logUser.getUsername())  
                       ) {
                    userList.remove(i);
                }
            }
            for (User user : userList) {
                relationUserList.add(relationUserDAO.getRelationAmongUsers(logUser, user));
            }
            model.addAttribute("relationUserList", relationUserList);
            model.addAttribute("page_id", page_id);
            model.addAttribute("numOfPages", numOfPages);
            model.addAttribute("previousNext", previousNext);
            if (session.getAttribute("loguser") != null) {
                return "viewprofileusers";
            } else {
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping(value = "/friendRequest/{uid}/{page_id}")
    public ModelAndView makeFriendRequest(@ModelAttribute("user") User userTwo, @PathVariable("uid") Long uid, @PathVariable("page_id") int page_id, HttpSession session) {
        try {
            User userOne = (User) session.getAttribute("loguser");
            userTwo = userDAO.getUserById(uid);
            RelationUser relationUser = new RelationUser();
            relationUser.setUserOne(userOne);
            relationUser.setUserTwo(userTwo);
            relationUserDAO.makeFriendRequest(relationUser);
            return new ModelAndView("redirect:/viewUsers/"+page_id);
        } catch (Exception e) {
            return new ModelAndView("error");
        }
    }

    @RequestMapping(value = "/deleteRelation/{uid}/{page_id}")
    public ModelAndView deleteFriend(@ModelAttribute("user") User userTwo, @PathVariable("page_id") int page_id, @PathVariable("uid") Long uid, HttpSession session) {
        try {
            User userOne = (User) session.getAttribute("loguser");
            userTwo = userDAO.getUserById(uid);
            RelationUser relationUser = new RelationUser();
            relationUser.setUserOne(userOne);
            relationUser.setUserTwo(userTwo);
            relationUserDAO.deleteFriend(relationUser);
            return new ModelAndView("redirect:/viewUsers/"+page_id);
        } catch (Exception e) {
            return new ModelAndView("error");
        }
    }

    @RequestMapping(value = "/acceptFriendRequest/{status}/{uid}/{page_id}")
    public ModelAndView acceptFriendRequest(@ModelAttribute("user") User userTwo,@PathVariable("page_id") int page_id, @PathVariable("uid") Long uid, @PathVariable("status") Long status, HttpSession session) throws Exception {
        try {
            User userOne = (User) session.getAttribute("loguser");
            userTwo = userDAO.getUserById(uid);
            RelationUser relationUser = new RelationUser();
            relationUser.setUserOne(userOne);
            relationUser.setUserTwo(userTwo);
            relationUser.setStatus(Relation.getRelationFor(status));
            relationUserDAO.acceptFriendRequest(relationUser);
            return new ModelAndView("redirect:/viewUsers/"+page_id);
        } catch (Exception e) {
            return new ModelAndView("error");
        }
    }

    @RequestMapping(value = "/acceptFriendRequest//{uid}")
    public ModelAndView bugAacceptFriendRequest() {
        return new ModelAndView("redirect:/viewUsers");
    }

    @RequestMapping(value = "/blockUser/{status}/{uid}/{page_id}")
    public ModelAndView blockUnblock(@ModelAttribute("user") User userTwo,@PathVariable("page_id") int page_id, @PathVariable("uid") Long uid, @PathVariable("status") Long status, HttpSession session) throws Exception {
        try {
            if (status == 2 || status == 3) {
                RelationUser relationUser = new RelationUser();
                User userOne = (User) session.getAttribute("loguser");
                userTwo = userDAO.getUserById(uid);
                relationUser.setUserOne(userOne);
                relationUser.setUserTwo(userTwo);
                Relation relation = Relation.getRelationFor(status);
                if (relation.getIdrel() == 2) {
                    relationUser.setStatus(Relation.BLOCKED);
                    checkBlocksDAO.createCheckBlock(userOne, userTwo);
                } else {
                    User blocker = checkBlocksDAO.getBlocker(userOne, userTwo);
                    if (blocker.getUid() != null) {
                        relationUser.setStatus(Relation.ACCEPTED);
                        checkBlocksDAO.deleteCheckBlock(userOne, userTwo);
                    }
                }
                try {
                    relationUserDAO.blockUser(relationUser);
                } catch (Exception e) {
                    e.getMessage();
                }
            }
            return new ModelAndView("redirect:/viewUsers/"+page_id);
        } catch (Exception e) {
            return new ModelAndView("error");
        }
    }

    @RequestMapping(value = "/blockUser//{uid}")
    public ModelAndView bugBlockUnblock() {
        return new ModelAndView("redirect:/viewUsers");
    }
}
