/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import encryption.CryptoConverter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import model.DAO.MessageDAO;
import model.DAO.RelationUserDAO;
import model.DAO.ResultsDAO;
import model.DAO.RoleUserDAO;
import model.DAO.UserDAO;
import model.entity.Results;
import model.entity.Role;
import model.entity.RoleUser;
import model.entity.User;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author andre
 */
@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleUserDAO roleuserDAO;
    
    @Autowired
    private MessageDAO messageDAO;
    
    @Autowired
    private ResultsDAO resultsDAO;
    
    @Autowired
    private RelationUserDAO relationUserDAO;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/")
    public String homePage(Model model) {
        model.addAttribute("user", new User());
        return "signin";
    }
    
    @RequestMapping("/upToRegisterForm")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/registerForm", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, 
            BindingResult bindingResult, Model model,
            @RequestParam("photo") MultipartFile photo, 
            @RequestParam("fname") String firstname, 
            @RequestParam("lname") String lastname, 
            @RequestParam("email") String email, 
            @RequestParam("username") String username, 
            @RequestParam("password1") String password1, 
            @RequestParam("password2") String password2, 
            @RequestParam("dob") String dob) {
           
            List<String> usernamesList = new ArrayList<>();
            for (User utilUser : userDAO.getAllUsers()) {
                usernamesList.add(utilUser.getUsername());
            }
            if(!usernamesList.contains(username)){
                try {
                    if(password1.equals(password2)){
                        user.setUsername(username);
                        user.setPassword(CryptoConverter.encrypt(password2));
                        user.setFirstname(firstname);
                        user.setLastname(lastname);
                        user.setEmail(email);
                        if (dob != null) {
                            Date date = null;
                            date = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
                            user.setDob(date);
                        }
                        userDAO.saveUser(user, photo);
                        RoleUser roleuser = new RoleUser();
                        roleuser.setUser(userDAO.getUserById(userDAO.getMaxUserId()));
                        roleuserDAO.saveRoleUser(roleuser);
                        System.out.println(userDAO.getUserById(userDAO.getMaxUserId()));
                        resultsDAO.saveUserResults(userDAO.getUserById(userDAO.getMaxUserId()));
                        return "signin";
                    }
                    else{
                        return "register";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    String requiredValid = "is required";
                    model.addAttribute("requiredValid", requiredValid);
                    String charactersValid = "at least 2 characters required";
                    model.addAttribute("charactersValid", charactersValid);
                    String characters3Valid = "at least 3 characters required";
                    model.addAttribute("characters3Valid", characters3Valid);
                    return "register";
                }
            }
            else{
                String sameUsername = "Username you choose already exists. Choose another username.";
                model.addAttribute("sameUsername", sameUsername);
                return "register";
            }
        
    }

    @RequestMapping("/loginForm")
    public String login(@ModelAttribute("user") User user) {
        return "signin";
    }

    @RequestMapping(value = "/authorizeForm", method = RequestMethod.POST)
    public String authorize(@ModelAttribute("user") User user, HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            user = (User) userDAO.getUserByLogin(username, password);
            if (roleuserDAO.getRoleUserById(user.getUid(), user).getRole().getRid() == 1) {
                session.setAttribute("loguser", user);
                return "home";
            } else {
                session.setAttribute("admin", user);
                return "admin";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "invalidlogin";
        }
    }

    @RequestMapping(value = "/goToUserProfile", method = RequestMethod.GET)
    public ModelAndView goToUserProfile(HttpSession session, ModelAndView model) {
        if (session.getAttribute("loguser") != null) {
            User user = (User) session.getAttribute("loguser");
            Long notifications = messageDAO.getMessagesNotifications(user);
            if(notifications == 0l){
                notifications = null;
            }
            
            Results results = (Results) resultsDAO.getUserResults(user);
            model.addObject("results", results);
            model.addObject("user", user);
            model.addObject("notifications", notifications);
            model.setViewName("profileuser");
            return model;
        } else {
            model.setViewName("error");
            return model;
        }
    }

    @RequestMapping(value = "/logoutForm", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "logout";
    }

    @RequestMapping(value = "/upToAdminPage", method = RequestMethod.GET)
    public String upToAdminPage(HttpSession session) {
        if (session.getAttribute("admin") != null) {
            return "admin";
        } else {
            return "error";
        }

    }
    
    @RequestMapping(value = "/upToHomePage", method = RequestMethod.GET)
    public String upToHomePage(HttpSession session) {
        if (session.getAttribute("loguser") != null) {
            return "home";
        } else {
            return "error";
        }

    }

    
    @RequestMapping(value = "/upToEditProfile")
    public ModelAndView upToEditMyProfile(HttpSession session, ModelAndView model) {

        try {
            if(session.getAttribute("loguser") != null){
                User user = (User) session.getAttribute("loguser");
                model.addObject("user", user);
                model.setViewName("editprofileuser");
                return model;
            }
            else{
                return new ModelAndView("error");
            }

        } catch (Exception e) {
            return new ModelAndView("profileuser", "msg", "Error: " + e.getMessage());
        }
    }
    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public ModelAndView editMyProfile(@RequestParam("photo") MultipartFile photo, 
            HttpSession session, ModelAndView model,
            @RequestParam("username") String username,
            @RequestParam("password1") String password1,
            @RequestParam("password2") String password2,
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname,
            @RequestParam("email") String email,
            @RequestParam("dob") String dob) {
            
            User user = (User) session.getAttribute("loguser");
            List<String> usernamesList = new ArrayList<>();
            for (User utilUser : userDAO.getAllUsers()) {
                usernamesList.add(utilUser.getUsername());
            }
            for(int i = 0; i<usernamesList.size(); i++){
                if(user.getUsername().equals(usernamesList.get(i))){
                    usernamesList.remove(i);
                }
            }
            if(!usernamesList.contains(username)){
                try {
                    if(password1.equals(password2)){
                        model.addObject("user", user);
                        model.setViewName("profileuser");
                        user.setUsername(username);
                        user.setPassword(CryptoConverter.encrypt(password2));
                        user.setFirstname(firstname);
                        user.setLastname(lastname);
                        user.setEmail(email);
                        if (dob != null) {
                            Date date = null;
                            date = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
                            user.setDob(date);
                        }
                        userDAO.updateUser(user, photo);
                        return model;
                    }
                    else{
                        return new ModelAndView("editprofileuser");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ModelAndView("error");
                }
            }
            else{              
                return new ModelAndView("editprofileuser");
            }
    }
    
    @RequestMapping(value = "/getUserPhoto/{uid}")
	public void getUserPhoto(HttpServletResponse response, @PathVariable("uid") Long uid) throws Exception {

            response.setContentType("image/jpeg");

            Blob ph = userDAO.getPictureByUserId(uid);

            byte[] bytes = ph.getBytes(1, (int) ph.length());
            InputStream inputStream = new ByteArrayInputStream(bytes);
            IOUtils.copy(inputStream, response.getOutputStream());
	}
        
    @RequestMapping(value="/allUsers", method = RequestMethod.GET)
    public String allUsers(HttpSession session, Model model){
        try{ 
            List<RoleUser> roleUserList = new ArrayList<>();
            List<User> userList = userDAO.getAllUsers();
            userList.remove(0);
            for (User user : userList) {
                roleUserList.add(roleuserDAO.getRoleUserById(user.getUid(), user));
            }
            model.addAttribute("roleUserList", roleUserList);
            if(session.getAttribute("admin") != null){
                return "allusers";
            }
            else{
                return "error";
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return "error";
        }
    }
    
    @RequestMapping(value = "/delete/{uid}")
    public ModelAndView deleteUserFromAdmin(@ModelAttribute("user") User user,@PathVariable("uid") Long uid, HttpSession session)
    {
        if(session.getAttribute("admin") != null){
            userDAO.deleteUser(uid);

            return new ModelAndView("redirect:/allUsers");
        }
        else{
            return new ModelAndView("error");
        }
    }
    
    @RequestMapping(value = "/deleteMyProfile")
    public ModelAndView deleteMyProfile(@ModelAttribute("user") User user, HttpSession session)
    {
        if(session.getAttribute("loguser") != null){
            user = (User) session.getAttribute("loguser");
            userDAO.deleteUser(user.getUid());

            return new ModelAndView("signin");
        }
        else{
            return new ModelAndView("error");
        }
    }
    
    @RequestMapping(value = "/changeRole/{uid}")
    public ModelAndView changeRoleUser(@ModelAttribute("user") User user,@PathVariable("uid") Long uid, HttpSession session)
    {
        if(session.getAttribute("admin") != null){
            user = userDAO.getUserById(uid);
            RoleUser roleUser = roleuserDAO.getRoleUserById(uid, user);
            if(roleUser.getRole().getRid() == 1){
                roleUser.setRole(Role.ADMIN);
            }
            else{
                roleUser.setRole(Role.USER);
            }
            roleuserDAO.changeRole(roleUser);
            return new ModelAndView("redirect:/allUsers");
        }
        else{
            return new ModelAndView("error");
        }
    }
    
}
