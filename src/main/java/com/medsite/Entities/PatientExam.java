package com.medsite.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

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


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "picture_id")
    private Collection<Picture> pictures=new ArrayList<>();

}
