package com.shop.pharmacy.controller;

import com.shop.pharmacy.form.MedicineForm;
import com.shop.pharmacy.model.Medicine;
import com.shop.pharmacy.service.CategoryService;
import com.shop.pharmacy.service.MedicineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/medicine")
public class MedicineController {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    @Autowired
    MedicineService medicineService;
    @Autowired
    CategoryService categoryService;


    @GetMapping
    public String showMedicineList(Model model) {
        List<Medicine> medicines = medicineService.findAll();
        model.addAttribute("medicines", medicines);
        return "medicine/list";
    }

    @GetMapping("/new")
    public String showMedicineAddingForm(Model model) {
        model.addAttribute("medicine", MedicineForm.builder().build());
        model.addAttribute("categories", categoryService.findAll());
        return "medicine/form";
    }


    @PostMapping
    public String saveMedicine(@Valid @ModelAttribute("medicine") MedicineForm form, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "medicine/form";
        }

        categoryService.findById(form.getCategoryId()).ifPresent(category -> {
            medicineService.save(Medicine.builder()
                    .id(form.getId())
                    .category(category)
                    .name(form.getName())
                    .price(form.getPrice())
                    .expiryDate(LocalDate.parse(form.getExpiryDate(), DateTimeFormatter.ofPattern(YYYY_MM_DD)))
                    .build());
        });

        return "redirect:/medicine";
    }


    @GetMapping("edit/{id}")
    public String showEditProduct(Model model, @PathVariable(name = "id") long id) {
        medicineService.findById(id).ifPresent(p -> {
            MedicineForm form = MedicineForm.builder()
                    .id(p.getId())
                    .categoryId(p.getCategory().getId())
                    .name(p.getName())
                    .price(p.getPrice())
                    .expiryDate(p.getExpiryDate() != null ? p.getExpiryDate().toString() : null)
                    .build();
            model.addAttribute("medicine", form);
            model.addAttribute("categories", categoryService.findAll());
        });
        return "medicine/form";

    }

    @RequestMapping("/delete/{id}")
    public String deleteCategory(@PathVariable(name = "id") long id) {
        medicineService.delete(id);
        return "redirect:/medicine";
    }
}