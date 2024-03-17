package org.example.tourmanagement.repository;

import org.example.tourmanagement.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findAllByPriceOrderByPriceAsc(double price);

    Page<Tour> findByCode(Pageable pageable, String code);
}
