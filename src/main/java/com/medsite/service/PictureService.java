package com.medsite.service;

import com.medsite.Entities.Picture;
import com.medsite.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PictureService {
    @Autowired
    private PictureRepository pictureRepository;

    public Picture save(Picture picture){
        return pictureRepository.save(picture);
    }

    public Optional<Picture> findById(Long id){
        return pictureRepository.findById(id);
    }


}
