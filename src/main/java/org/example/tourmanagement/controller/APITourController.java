package org.example.tourmanagement.controller;

import org.example.tourmanagement.model.Tour;
import org.example.tourmanagement.service.ITourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/tours")
public class APITourController {
    @Autowired
    private ITourService tourService;

    @GetMapping
    public ResponseEntity<Page<Tour>> getAllTours(Pageable pageable) {
        Page<Tour> tours = tourService.findAll(pageable);
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tour> getTourById(@PathVariable Long id) {
        Optional<Tour> tourOptional = tourService.findById(id);
        return tourOptional.map(tour -> new ResponseEntity<>(tour, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Tour> createTour(@RequestBody Tour tour) {
        Tour savedTour = tourService.save(tour);
        return new ResponseEntity<>(savedTour, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tour> updateTour(@PathVariable Long id, @RequestBody Tour tour) {
        if (!tourService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tour.setId(id);
        Tour updatedTour = tourService.save(tour);
        return new ResponseEntity<>(updatedTour, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTour(@PathVariable Long id) {
        if (!tourService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tourService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
