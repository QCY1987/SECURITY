package org.example.controllers;

import org.example.dao.PersonDao;
import org.example.models.User;
import org.example.service.ServiceDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class UserController {
    @Autowired
    private  ServiceDaoImpl serviceDaoImpl;

   /* @Autowired
    public UserController(PersonDao personDao) {
        this.personDao = personDao;
    }
*/
    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", serviceDaoImpl.index());
        return "people/index";
    }
    /*
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", serviceDaoImpl.show(id));
        return  "people/show";
    }
    @GetMapping ("/new")
    public String newUser (Model model) {
        model.addAttribute("person", new User());
        return "people/new";

    }
    @PostMapping()
    public String create (@ModelAttribute("person") User user) {
        serviceDaoImpl.save(user);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit (Model model, @PathVariable("id") int id) {
        model.addAttribute("person", serviceDaoImpl.show(id));
        return "people/edit";
    }
    @PatchMapping ("/{id}")
    public String update (@ModelAttribute("person") User user, @PathVariable("id")int id) {
        serviceDaoImpl.update(id, user);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete (@PathVariable("id") int id) {
        serviceDaoImpl.delete(id);
        return "redirect:/people";
    }
*/
}
