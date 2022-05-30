package com.shop.pharmacy.service;


import com.shop.pharmacy.model.Category;
import com.shop.pharmacy.repository.CategoryRepository;
import com.shop.pharmacy.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MedicineRepository medicineRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(long id) {
        return categoryRepository.findById(id);
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public void delete(long id) {
        categoryRepository.deleteById(id);
    }

    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Integer countMedicinesByCategoryId(Long categoryId) {
        return medicineRepository.countByCategoryId(categoryId);
    }
}
