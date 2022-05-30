package com.shop.pharmacy.controller;

import com.shop.pharmacy.form.CategoryForm;
import com.shop.pharmacy.model.Category;
import com.shop.pharmacy.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String showCategoryList(Model model) {
        List<CategoryForm> categories = categoryService.findAll().stream()
                .map(category ->
                        CategoryForm.builder()
                                .id(category.getId())
                                .name(category.getName())
                                .description(category.getDescription())
                                .medicineCount(categoryService.countMedicinesByCategoryId(category.getId()))
                                .build())
                .collect(Collectors.toList());
        model.addAttribute("categories", categories);
        return "category/list";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("form", CategoryForm.builder().build());
        return "category/form";
    }

    @PostMapping
    public String saveCategory(@Valid @ModelAttribute("form") CategoryForm form, BindingResult result) {
        if (form.getId() == null) {
            categoryService.findByName(form.getName()).ifPresent(name ->
                    result.rejectValue("name", "error.form", form.getName() +
                            "already exist this category! try new"));
        }
        if (result.hasErrors()) {
            return "category/form";
        }

        categoryService.save(Category.builder()
                .id(form.getId())
                .name(form.getName())
                .description(form.getDescription())
                .build());
        return "redirect:/category";
    }

    @GetMapping("/edit/{name}")
    public String editCategory(Model model, @PathVariable(name = "name") long id) {
        categoryService.findById(id).ifPresent(form -> {
            CategoryForm categoryForm = CategoryForm.builder()
                    .id(form.getId())
                    .name(form.getName())
                    .description(form.getDescription())
                    .build();
            model.addAttribute("form", categoryForm);
        });
        return "category/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable(name = "id") long id) {
        categoryService.delete(id);
        return "redirect:/category";
    }


}


