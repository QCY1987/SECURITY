package org.example.controllers;

import org.example.models.Role;
import org.example.models.User;
import org.example.service.UserService;
import org.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("index", userService.index());
        System.out.println(userService.index());
        return "index";
    }
    @GetMapping("/add")
    public String getUser(Model model) {
        model.addAttribute("listRole", roleService.listRoles());
        return "add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("addUser") User user,
                      @RequestParam(value = "newRole", required = false) String[] role) {
        user.setRoles(getAddRole(role));
        userService.save(user);
        return "redirect:/admin";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("edit", userService.show(id));
        model.addAttribute("listRole", roleService.listRoles());
        return "edit";
    }
    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("edit") User user,
                         @RequestParam(value = "newRole", required = false) String[] role) {
        user.setRoles(getAddRole(role));
        userService.update(user);
        return "redirect:/admin";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        User user=userService.show(id);
        userService.delete(user);
        return "redirect:/admin";
    }

    private Set<Role> getAddRole (String[] role) {
        Set<Role> roleSet=new HashSet<>();
        for (int i=0; i<role.length; i++) {
            roleSet.add(roleService.getRoleByName(role[i]));
        }
        return roleSet;
    }
}
