package org.example.tourmanagement.repository;

import org.example.tourmanagement.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    @Query(nativeQuery = true, value = "select * from type where name like %?%")
    Page<Type> searchName(Pageable pageable, String name);
}
