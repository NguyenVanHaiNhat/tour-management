package org.example.tourmanagement.repository;

import org.example.tourmanagement.model.Tour;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

    @Query(nativeQuery = true,value = "SELECT * FROM tours t ORDER BY t.price ASC")
    Iterable<Tour> sortAllByPriceOrderByPriceAsc();
    @Query(nativeQuery = true,value = "SELECT * FROM tours t ORDER BY t.type_id ASC")
    Iterable<Tour> sortAllByTypeAsc();
}
