package org.example.tourmanagement.repository;

import org.example.tourmanagement.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    Page<Tour> findByCode(Pageable pageable, String code);

    @Query(nativeQuery = true,value = "SELECT * FROM tours t ORDER BY t.price ASC")
    Page<Tour> sortAllByPriceOrderByPriceAsc();
    @Query(nativeQuery = true,value = "SELECT * FROM tours t ORDER BY t.type_id ASC")
    Page<Tour> sortAllByTypeAsc();
}
