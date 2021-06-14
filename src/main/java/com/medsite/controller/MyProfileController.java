package com.medsite.controller;

import com.medsite.Entities.User;
import com.medsite.repository.IMyProfileRepository;
import com.medsite.service.MyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/myprofile")
public class MyProfileController {


    @Autowired
    private MyProfileService myProfileService;

    @Autowired
    private IMyProfileRepository repo;

    private String list_template = "/admin/myprofile/viewmyprofile";


    @GetMapping("/view")
    public String showMyProfile(Model model, Principal principal) {

        User listUser = repo.findByEmail(principal.getName());

        model.addAttribute("myprofile", listUser);

        return list_template;
    }

}
