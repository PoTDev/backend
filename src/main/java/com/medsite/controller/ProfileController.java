package com.medsite.controller;

import com.medsite.Entities.Role;
import com.medsite.Entities.User;
import com.medsite.service.ProfileService;

import com.medsite.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private RoleService roleService;


    private String add_edit_template="/admin/profile/add-edit-profiles";
    private String list_template="/admin/profile/list-profile";
    private String list_redirect="redirect:/profile/list";


    @GetMapping("/add")
    public String addProfile(User user, Model model){
        model.addAttribute("profiles", user);

        List<Role> roleTypes = roleService.listAll();
        model.addAttribute("roleTypes",roleTypes);

        return add_edit_template;
    }


    @GetMapping("/edit/{id}")
    public String editProfile(@PathVariable("id") long id, Model model){
        User user = profileService.get(id);
        model.addAttribute("profiles", user);

        List<Role> roleTypes = roleService.listAll();
        model.addAttribute("roleTypes",roleTypes);

        return add_edit_template;
    }

    @PostMapping("/save")
    public String saveProfile(@Valid @ModelAttribute("profiles") User user, BindingResult result, Model model){
        model.addAttribute("profiles", user);

        List<Role> roleTypes = roleService.listAll();
        model.addAttribute("roleTypes",roleTypes);

        if(result.hasErrors()){
            return add_edit_template;
        }

        profileService.save(user);
        return list_redirect+"?success";
    }



    @GetMapping("/delete/{id}")
    public String deleteProfile(@PathVariable("id") long id, Model model) {
        profileService.delete(id);

        List<Role> roleTypes = roleService.listAll();
        model.addAttribute("roleTypes",roleTypes);

        return list_redirect+"?deleted";
    }

    @GetMapping("/list")
    public String listProfile( Model model) {

        List<User> listProfiles = profileService.listAll();
        model.addAttribute("listProfiles", listProfiles);

        return list_template;
    }

    @RequestMapping(value = "/list/{search}", method = RequestMethod.GET)
    public String searchProfile(
            @PathVariable("search") String search,
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
            {
                int currentPage = page.orElse(1);
                int pageSize = size.orElse(5);


        Page<User> bookPage  = profileService.findPaginated(search, PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("listProfiles", bookPage);

        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> listProfiles = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", listProfiles);
        }

        return list_template;
    }

    }



}
