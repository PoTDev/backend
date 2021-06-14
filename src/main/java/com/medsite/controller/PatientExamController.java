package com.medsite.controller;

import com.medsite.Entities.PatientExam;
import com.medsite.Entities.Picture;
import com.medsite.repository.IPatientExamRepository;
import com.medsite.service.PatientExamService;
import com.medsite.service.PictureService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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


    @GetMapping("/add")
    public String addExam(PatientExam patientExam, Model model){
        model.addAttribute("exams", patientExam);

        return add_edit_template;
    }


    @GetMapping("/edit/{id}")
    public String editExam(@PathVariable("id") long id, Model model){


        PatientExam patientExam = patientExamService.get(id);
        model.addAttribute("exams", patientExam);

        Picture image = new Picture();
        image.setPatientExam(new ArrayList<PatientExam>(patientExamService.listAll()));

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

    @GetMapping("/full/{id}")
    public String listFullExam(@PathVariable("id") long id, Model model) {

        PatientExam patientExams = patientExamService.findById(id);

        model.addAttribute("listExams", patientExams);

        return list_full_template;
    }


    /*@PostMapping("/load")
    public  String saveImage(@ModelAttribute(name = "imageData")Picture picture,
                             @RequestParam(name = "patientExam") PatientExam patientExam){

        try {

            picture.setImage_data(picture.getMultipartFile().getBytes()); //в байты
            picture.setName(picture.getMultipartFile().getOriginalFilename()); //сохранить имя

            patientExam.setPictures();
            pictureService.save(picture);

            System.out.println("Image saved.....");

        } catch (IOException e) {
            System.out.println("IO_Exeption");
        }
        return list_redirect+"?success";
    }*/


    @GetMapping("pic/{id}")
    public String viewImage(Model model, @PathVariable("id") long id, HttpServletResponse response) throws IOException{

        response.setContentType("image/png");

        Optional<Picture> picture = pictureService.findById(id);


        InputStream is = new ByteArrayInputStream(picture.get().getImage_data());
        IOUtils.copy(is, response.getOutputStream());


        model.addAttribute("picture", IOUtils.copy(is, response.getOutputStream()));
        return viewImage;
    }
}
