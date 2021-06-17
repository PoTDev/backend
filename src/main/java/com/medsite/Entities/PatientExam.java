package com.medsite.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "patient_exams")
public class PatientExam {

    //-----------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Long id;
    //-----------------------------------
    //ПОЧТА ПАЦИЕНТА

    @Column(name = "email")
    private String email; //ПОЧТА ПАЦИЕНТА
    //-----------------------------------

    @Column(name = "date")
    private String date; //дата осмотра
    //-----------------------------------

    @Column(name = "petition")
    private String petition; //жалобы
    //-----------------------------------

    @Column(name = "anamnez")
    private String anamnez; //анамнез заболевания
    //-----------------------------------

    @Column(name = "commonData")
    private String commonData; //данные об общем осмотре
    //-----------------------------------

    @Column(name = "diagnosis")
    private String diagnosis; //диагноз
    //-----------------------------------

    @Column(name = "advice")
    private String advice; //рекомендации
    //-----------------------------------

    /*public PatientExam(String email,
                        String date,
                        String petition,
                        String anamnez,
                        String commonData,
                        String diagnosis,
                        String advice
                       ){
        this.email = email;
        this.date = date;
        this.petition = petition;
        this.anamnez = anamnez;
        this.commonData = commonData;
        this.diagnosis = diagnosis;
        this.advice = advice;*

    }*/

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "exam_picture",
            joinColumns = @JoinColumn(
                    name = "exam_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "picture_id"))

    private Collection <Picture> pictures;*/

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "picture_id")
    private Collection<Picture> pictures=new ArrayList<>();

}
