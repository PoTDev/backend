package com.medsite.service;

import com.medsite.Entities.PatientExam;
import com.medsite.Entities.Picture;
import com.medsite.repository.IPatientExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class PatientExamService {

    @Autowired
    private IPatientExamRepository repoExam;


    public List<PatientExam> listAll() {
        return repoExam.findAll();
    }

    public Page<PatientExam> findPaginated(String search, Pageable pageable) {
        List<PatientExam> patientExams = repoExam.findAll(); //repo.findAll(); searchProduct(search);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<PatientExam> list;

        if (patientExams.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, patientExams.size());
            list = patientExams.subList(startItem, toIndex);
        }

        Page<PatientExam> bookPage = new PageImpl<PatientExam>(list, PageRequest.of(currentPage, pageSize), patientExams.size());

        return bookPage;
    }


    public void save(PatientExam patientExam) {
        repoExam.save(patientExam);
    }

    public PatientExam get(long id) {
        return repoExam.findById(id).get();
    }

    public void delete(long id) {
        repoExam.deleteById(id);
    }

    public PatientExam findById(Long id){
        return repoExam.findById(id).get();
    }

    public List<PatientExam> findAllByEmail(String email){
        return repoExam.findAllByEmail(email);

    }




}
