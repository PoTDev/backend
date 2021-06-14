package com.medsite.controller;

import com.medsite.Entities.PatientExam;
import com.medsite.Entities.Picture;
import com.medsite.service.PatientExamService;
import com.medsite.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/picture")
public class PictureController {

    @Autowired
    private PatientExamService patientExamService;

    @Autowired
    private PictureService pictureService;

    private String loadImage = "/patient/loadImage";
    private String list_redirect = "redirect:/exam/list";


    //Загрузка картинок
    @GetMapping("/load/{id}")
    public String loadImage(Model model, @PathVariable(value = "id") long id) {

        Picture image = new Picture();
        image.setPatientExam(new ArrayList<PatientExam>(patientExamService.listAll()));

        model.addAttribute("imageData", image);
        return loadImage;
    }


    public Picture pic;

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");

        try {
            pic.setImage_data(file.getBytes()); //в байты
            pic.setName(file.getOriginalFilename()); //сохранить имя
            System.out.println("Image saved.....");
        } catch (IOException e) {
            System.out.println("IO_Exeption");
        }
        return list_redirect + "?success";

    }
}
