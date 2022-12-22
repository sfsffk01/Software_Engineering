package com.mycompany.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    UserController sc = new UserController();
    //public UserService service;
    public String userState;
    RedirectAttributes ra;
    Model model;
    @PostMapping("/user/loginSystem")
    @Test
    void loginUser() {
        UserService service;
        User user = new User();
        user.setId(2);
        user.setUserName("abcjsdksjdk");
        user.setAccountNumber("abcjsdksjdk");
        user.setPassword("abcjsdksjdk");

        String result = sc.loginUser(user, model);
        Assertions.assertEquals("redirect:/article", result);
    }
}