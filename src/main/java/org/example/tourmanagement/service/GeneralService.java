package org.example.tourmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GeneralService <T>{
    Page<T> findAll(Pageable pageable);
    Optional<T> findById(Long id);
    T save(T t);
    void remove(Long id);
}
