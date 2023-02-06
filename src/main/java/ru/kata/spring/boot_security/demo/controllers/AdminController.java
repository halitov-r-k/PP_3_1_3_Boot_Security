package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin/users")
    public String userList(Model model) {
        model.addAttribute("userList", userService.allUsers());
        return "admin/users";
    }

    @GetMapping("/admin")
    public String openAdminPage() {

        return "/admin/admin";
    }

}
