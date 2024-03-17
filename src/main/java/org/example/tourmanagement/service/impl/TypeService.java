package org.example.tourmanagement.service.impl;

import org.example.tourmanagement.model.Type;
import org.example.tourmanagement.repository.TypeRepository;
import org.example.tourmanagement.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TypeService implements ITypeService {
    @Autowired
    private TypeRepository typeRepository;
    @Override
    public Page<Type> findAll(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public Optional<Type> findById(Long id) {
        return typeRepository.findById(id);
    }

    @Override
    public Type save(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public void remove(Long id) {
        typeRepository.deleteById(id);
    }
}
