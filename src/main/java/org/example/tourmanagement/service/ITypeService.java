package org.example.tourmanagement.service;

import org.example.tourmanagement.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITypeService extends GeneralService<Type> {
    Page<Type> findByName(Pageable pageable, String name);
}
