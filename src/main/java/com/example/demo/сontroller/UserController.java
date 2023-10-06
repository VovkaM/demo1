package com.example.demo.сontroller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping
public class UserController {

    private UserService service;

    @GetMapping(value = "/")
    public String getIndex() {
        return "index";
    }

    @GetMapping(value = "/users")
    public String getUsers(ModelMap model, @RequestParam(value = "count", required = false) String number) {
        List<User> list = service.getUsers(number);
        model.addAttribute("users", list);
        return "users";
    }

    @GetMapping("/user")
    public String getUser(@RequestParam(value = "id") String id, Model model) {
        model.addAttribute("user", service.getUser(Long.parseLong(id)));
        return "show";
    }

    @GetMapping("/new")
    public String getNewUserPage(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/create")
    public String addUser(@ModelAttribute("user") User user) {
        service.addUser(user);
        return "redirect:/users";
    }

    @PostMapping("/edit_user")

    public String getEditUserPage(@RequestParam(value = "id") String id, Model model) {
        model.addAttribute("user", service.getUser(Long.parseLong(id)));
        return "edit";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam(value = "id") String id, @ModelAttribute("user") User user) {
        service.updateUser(user, Long.parseLong(id));
        return "redirect:/users";
    }

    @PostMapping("/del_user")
    public String deleteUser(@RequestParam(value = "id") String id) {
        service.deleteUser(Long.parseLong(id));
        return "redirect:/users";
    }

}