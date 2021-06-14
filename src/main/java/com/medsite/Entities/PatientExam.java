package com.medsite.Entities;

import lombok.*;

import javax.persistence.*;
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
    private Long exam_id;
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

    public PatientExam(String email,
                        String date,
                        String petition,
                        String anamnez,
                        String commonData,
                        String diagnosis,
                        String advice){
        this.email = email;
        this.date = date;
        this.petition = petition;
        this.anamnez = anamnez;
        this.commonData = commonData;
        this.diagnosis = diagnosis;
        this.advice = advice;

    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "exam_picture",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id"))

    private List<Picture> pictures;

    public void setPictures(List<Picture> pictures){
        this.pictures = pictures;
    }
    public List<Picture> getPictures(){
        return this.pictures;
    }


    public Long getExamId() { return exam_id; }

    public void setExamId(Long id) { this.exam_id = id; }


    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }


    public String getDate() {
        return date;
    }

    public String getPetition() {
        return petition;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPetition(String petition) {
        this.petition = petition;
    }

    public String getAnamnez() {
        return anamnez;
    }

    public void setAnamnez(String anamnez) {
        this.anamnez = anamnez;
    }

    public String getCommonData() {
        return commonData;
    }

    public void setCommonData(String commonData) {
        this.commonData = commonData;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }


}
