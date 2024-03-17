package org.example.tourmanagement.service;

import org.example.tourmanagement.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITourService extends GeneralService<Tour> {
    Page<Tour> findByCode(Pageable pageable, String code);
    Page<Tour> sortAllByPriceOrderByPriceAsc();
    Page<Tour> sortAllByTypeAsc();
}
