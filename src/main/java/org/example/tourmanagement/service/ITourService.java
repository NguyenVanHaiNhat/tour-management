package org.example.tourmanagement.service;

import org.example.tourmanagement.model.Tour;

public interface ITourService extends GeneralService<Tour> {
    Iterable<Tour> sortAllByPriceOrderByPriceAsc();
    Iterable<Tour> sortAllByTypeAsc();
}
