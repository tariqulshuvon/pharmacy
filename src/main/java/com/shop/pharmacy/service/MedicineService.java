package com.shop.pharmacy.service;


import com.shop.pharmacy.model.Medicine;
import com.shop.pharmacy.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {

    @Autowired
    MedicineRepository medicineRepository;

    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }

    public Optional<Medicine> findById(long id) {
    return medicineRepository.findById(id);
    }

    public void save(Medicine medicine) {
        medicineRepository.save(medicine);
    }

    public void delete(long id) {
        medicineRepository.deleteById(id);
    }

    public Optional<Medicine> findByName(String name) {
       return medicineRepository.findByName(name);
    }

    public Optional<Medicine> findByExpiryDate(LocalDate exp) {
       return medicineRepository.findByExpiryDate(exp);
    }


}
