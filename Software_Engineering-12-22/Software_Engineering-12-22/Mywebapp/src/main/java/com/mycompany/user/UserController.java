package com.mycompany.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Service
public class UserController {
    @Autowired
    private UserService service;
    public String userState;

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }
    @GetMapping("/users/new")//註冊介面
    public String showNewFrom(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "註冊");
        return "user_from"; //回到manage user
    }
    @PostMapping("/user/registerSystem")
    public String registerUser(User user, RedirectAttributes ra , Model model) {
        List<User> listUsers = service.listAll();
        User[] arrayUsers = new User[listUsers.size()];
        listUsers.toArray(arrayUsers);
        int flag = 0;
        userState = "註冊成功!";
        for(int i=0; i<listUsers.size(); i++) {
            flag=0;
            if(user.getUserName().equals(arrayUsers[i].getUserName()))
            {
                flag++;
            }
            if(flag>=1)
            {
                userState = "註冊失敗! 有重複的帳號或使用者名稱!";
                return "/user_failRegister";
            }
        }
        return "/user_successRegister";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra) {
        ra.addFlashAttribute("message", "The user has been saved successfully.");
        List<User> listUsers = service.listAll();
        User[] arrayUsers = new User[listUsers.size()];
        listUsers.toArray(arrayUsers);
        int flag = 0;
        userState = "註冊成功!";
        for(int i=0; i<listUsers.size(); i++) {
            flag=0;
            if(user.getAccountNumber().equals(arrayUsers[i].getAccountNumber()))
            {
                flag++;
            }
            if(user.getUserName().equals(arrayUsers[i].getUserName()))
            {
                flag++;
            }
            if(flag>=1)
            {
                userState = "註冊失敗! 有重複的帳號!";
                return "/user_failRegister";
            }
        }
        service.save(user);
        return "/user_successRegister";
        //return "redirect:/users";
    }

    @GetMapping("/users/newLogin")//登入介面
    public String showNewLoginFrom(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "帳號登入");
        return "user_login";
    }
    @GetMapping("/user_login")
    public String showUserLoginList(Model model) {
        return "user_login.html";
    }
    @PostMapping("/user/loginSystem")
    public String loginUser(User user, Model model) {
        List<User> listUsers = service.listAll();
        User[] arrayUsers = new User[listUsers.size()];
        listUsers.toArray(arrayUsers);
        int flag = 0;
        userState = "登入失敗!";
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        for(int i=0; i<listUsers.size(); i++) {
            flag=0;
            if(user.getAccountNumber().equals(arrayUsers[i].getAccountNumber()))
            {
                flag++;
            }
            if(user.getUserName().equals(arrayUsers[i].getUserName()))
            {
                flag++;
            }
            if(user.getPassword().equals(arrayUsers[i].getPassword()))
            {
                flag++;
            }
            if(flag>=3)
            {
                userState = "登入成功!";
                return "redirect:/article";
            }
        }
        return "/user_failLogin";
    }
    /**
     * Immplementation of updated and delete
     */

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");

            return "user_from";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The user id" + id + "has been deleted");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";

    }


}
