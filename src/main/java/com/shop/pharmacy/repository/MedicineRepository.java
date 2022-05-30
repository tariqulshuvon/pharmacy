package com.shop.pharmacy.repository;

import com.shop.pharmacy.model.Category;
import com.shop.pharmacy.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Optional<Medicine> findByName(String name);

    Optional<Medicine> findByExpiryDate(LocalDate exp);

    Integer countByCategoryId(Long categoryId);

}
