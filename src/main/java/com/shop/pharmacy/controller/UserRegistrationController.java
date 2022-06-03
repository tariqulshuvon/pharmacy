package com.shop.pharmacy.controller;



import com.shop.pharmacy.form.UserRegistrationForm;
import com.shop.pharmacy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    @Autowired
    UserService userService;

    @GetMapping
    public String showRegistrationForm(Model model, UserRegistrationForm userRegistrationForm) {
        model.addAttribute("user",userRegistrationForm);
        return "registration/form";
    }


    @PostMapping
    public String saveUser(@ModelAttribute("user") UserRegistrationForm userRegistrationForm, Model model) {
        userService.save(userRegistrationForm);
        return "redirect:/registration?success";
    }

}
