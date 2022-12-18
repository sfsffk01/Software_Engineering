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
    @GetMapping("/user_login")
    public String showUserLoginList(Model model) {
        return "user_login.html";
    }
    @GetMapping("/users/new")
    public String showNewFrom(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "user_from";
    }
    @GetMapping("/users/newLogin")
    public String showNewLoginFrom(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "帳號登入");
        return "user_login";
    }


    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra) {
        service.save(user);
        ra.addFlashAttribute("message", "The user has been saved successfully.");
        return "redirect:/users";
    }
    @PostMapping("/user/loginSystem")
    public String loginUser(User user, RedirectAttributes ra , Model model) {
        List<User> listUsers = service.listAll();
        User[] arrayUsers = new User[listUsers.size()];
        listUsers.toArray(arrayUsers);
        int flag = 0;
        userState = "登入失敗!";
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        for(int i=0; i<listUsers.size(); i++) {
            flag=0;
            System.out.println("名稱:" + user.getUserName());
            System.out.println("比對名稱:" + arrayUsers[i].getUserName());
            System.out.println("帳號:" + user.getAccountNumber());
            System.out.println("比對帳號:" + arrayUsers[i].getAccountNumber());
            System.out.println("密碼:" + user.getPassword());
            System.out.println("比對密碼:" + arrayUsers[i].getPassword());

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
                return "/user_successLogin";
            }
        }
        System.out.println("flag:" + flag);
        return "/user_failLogin";
        //return "redirect:/";
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
