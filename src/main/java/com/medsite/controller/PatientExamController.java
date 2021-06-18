package com.medsite.controller;

import com.medsite.Entities.PatientExam;
import com.medsite.Entities.Picture;
import com.medsite.repository.IPatientExamRepository;
import com.medsite.repository.PictureRepository;
import com.medsite.service.PatientExamService;
import com.medsite.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/exam")
public class PatientExamController {

    @Autowired
    private PatientExamService patientExamService;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private IPatientExamRepository examrepo;


    private String add_edit_template="/patient/add-edit-exam";
    private String list_template="/patient/list-exam";
    private String list_full_template="/patient/view-full-exam";
    private String list_redirect="redirect:/exam/list";

    private String loadImage="/patient/loadImage";
    private String viewImage="/patient/viewImage";
    private String list_my_exams ="/patient/list-my-exams";


    @GetMapping("/add")
    public String addExam(PatientExam patientExam, Model model){
        model.addAttribute("exams", patientExam);

        return add_edit_template;
    }


    @GetMapping("/edit/{id}")
    public String editExam(@PathVariable("id") long id, Model model){


        PatientExam patientExam = patientExamService.get(id);
        model.addAttribute("exams", patientExam);


        return add_edit_template;
    }

    @PostMapping("/save")
    public String saveExam(@Valid @ModelAttribute("exams") PatientExam patientExam,
                           BindingResult result,
                           Model model){


        model.addAttribute("exams", patientExam);

        if(result.hasErrors()){
            return add_edit_template;
        }

        patientExamService.save(patientExam);
        return list_redirect+"?success";
    }



    @GetMapping("/delete/{id}")
    public String deleteExam(@PathVariable("id") long id, Model model) {
        patientExamService.delete(id);

        return list_redirect+"?deleted";
    }

    @GetMapping("/list")
    public String listExam(Model model) {

        List<PatientExam> patientExams = patientExamService.listAll();
        model.addAttribute("listExams", patientExams);

        return list_template;
    }

    @GetMapping("/mylist")
    public String mylistExam(Model model, Principal principal) {

        List<PatientExam> mepatient = examrepo.findAllByEmail(principal.getName());

        model.addAttribute("mepatient", mepatient);


        return list_my_exams;
    }


    @GetMapping("/full/{id}")
    public String listFullExam(@PathVariable("id") long id, Model model) {

        PatientExam patientExams = patientExamService.findById(id);

        model.addAttribute("listExams", patientExams);

        return list_full_template;
    }

    @Autowired
    private PictureRepository pictureRepository;

    @GetMapping("/load/")
    public String loadImage(Model model){

        Picture image = new Picture();

        model.addAttribute("patientExams", patientExamService.listAll());
        model.addAttribute("imageData", image);
        return loadImage;
    }


    @PostMapping("/load")
    public  String saveImage(@ModelAttribute(name = "imageData")Picture picture,
                             @RequestParam(name = "patientExams") PatientExam patientExam){

        try {
            picture.setImage_data(picture.getMultipartFile().getBytes());
            picture.setName(picture.getMultipartFile().getOriginalFilename());


            pictureService.save(picture);
            System.out.println("Image saved.....");
        } catch (IOException e) {
            System.out.println("IO_Exeption");

        }
        return list_redirect+"?success";
    }


    @GetMapping("pic/{id}")
    public String viewImage(Model model, @PathVariable Long id){
        PatientExam patientExam = patientExamService.findById(id);

        List<Picture> pictures = pictureService.findAllByPatientExams_Id(id);


        System.out.println("_______________________________________");
        System.out.println(patientExam.getId());
        System.out.println(patientExam.getEmail());
        for(Picture i : patientExam.getPictures()) {
            System.out.println(i);
            System.out.println("prod");
        }
        System.out.println("_______________________________________");
        List<String> encodedImages = new ArrayList<>();
        for (Picture picture: pictures) {
            String encodedString = Base64.getEncoder().encodeToString(picture.getImage_data());
            encodedImages.add(encodedString);
        }

        model.addAttribute("picturesList", encodedImages);

        return viewImage;
    }

    @GetMapping("/pic/delete/{id}")
    public String deleteImage(@PathVariable("id") long id, Model model) {
        pictureService.delete(id);

        return list_redirect+"?deleted";
    }




}