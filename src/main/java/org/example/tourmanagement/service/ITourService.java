package org.example.tourmanagement.service;

import org.example.tourmanagement.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITourService extends GeneralService<Tour> {
    Page<Tour> sortAllByPriceOrderByPriceAsc(Pageable pageable);
    Page<Tour> sortAllByTypeAsc(Pageable pageable);
    Page<Tour> findByCode(Pageable pageable, String code);


}
