package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;



    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("userList", userService.allUsers());
        return "admin/users";
    }

    @RequestMapping("/admin/addUser")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "admin/user-new";
    }

    @PostMapping("/admin/save")
    public String saveUser(@ModelAttribute("user") User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @RequestMapping("admin/getUser/{id}")
    public String getUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "/admin/user-update";
    }
    @PatchMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
