package org.example.controllers;


import org.example.models.User;
import org.example.service.ServiceDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/JM")
public class UserController {
    @Autowired
    private  ServiceDaoImpl serviceDaoImpl;


    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("index", serviceDaoImpl.index());
        System.out.println(serviceDaoImpl.index());
        return "index";
    }
    @GetMapping("/add")
    public String getUser() {
        return "add";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute("addUser") User user) {
        serviceDaoImpl.save(user);
        return "redirect:/JM";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("edit", serviceDaoImpl.show(id));
        return "edit";
    }
    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("edit") User user) {
        serviceDaoImpl.update(user);
        return "redirect:/JM";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        serviceDaoImpl.delete(id);
        return "redirect:/JM";
    }
}
