package org.example.tourmanagement.service.impl;

import org.example.tourmanagement.model.Tour;
import org.example.tourmanagement.repository.TourRepository;
import org.example.tourmanagement.service.ITourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TourService implements ITourService {
    @Autowired
    private TourRepository tourRepository;
    @Override
    public Iterable<Tour> findAll() {
        return tourRepository.findAll();
    }

    @Override
    public Optional<Tour> findById(Long id) {
        return tourRepository.findById(id);
    }

    @Override
    public Tour save(Tour tour) {
        return tourRepository.save(tour);
    }

    @Override
    public void remove(Long id) {
        tourRepository.deleteById(id);
    }
}
