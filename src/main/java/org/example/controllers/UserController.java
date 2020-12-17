package org.example.controllers;


import org.example.models.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/JM")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("index", userService.index());
        System.out.println(userService.index());
        return "index";
    }
    @GetMapping("/add")
    public String getUser() {
        return "add";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute("addUser") User user) {
        userService.save(user);
        return "redirect:/JM";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("edit", userService.show(id));
        return "edit";
    }
    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("edit") User user) {
        userService.update(user);
        return "redirect:/JM";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        User user=userService.show(id);
        userService.delete(user);
        return "redirect:/JM";
    }
}
