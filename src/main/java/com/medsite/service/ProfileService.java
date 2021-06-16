package com.medsite.service;

import com.medsite.Entities.User;

import com.medsite.repository.IProfileRepository;
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
public class ProfileService {

    @Autowired
    private IProfileRepository repo;

    public List<User> listAll() {
        return repo.findAll();
    }

    public Page<User> findPaginated(String search, Pageable pageable) {
        List<User> users = repo.searchProfile(search); //searchProfile(search);findAll(search);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<User> list;

        if (users.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, users.size());
            list = users.subList(startItem, toIndex);
        }

        Page<User> bookPage = new PageImpl<User>(list, PageRequest.of(currentPage, pageSize), users.size());

        return bookPage;
    }


    public void save(User product) {
        repo.save(product);
    }

    public User get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
