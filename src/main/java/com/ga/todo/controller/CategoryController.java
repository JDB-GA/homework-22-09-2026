package com.ga.todo.controller;

import com.ga.todo.model.Category;
import com.ga.todo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Post
    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category categoryObject) {
        return categoryService.createCategory(categoryObject);
    }

    // Get
    @GetMapping("/categories")
    public List<Category> getCategories() {
        System.out.println("Calling getCategories");
        return categoryService.getCategories();
    }

    // Get
    @GetMapping("/category/{id}")
    public Category getCategory(@PathVariable Long id) {
        System.out.println("Calling getCategory");
        return categoryService.getCategory(id);
    }

    // Put
    @PutMapping("/category/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category categoryObject) {
        System.out.println("Calling Update Category");

        return categoryService.updateCategory(id, categoryObject);
    }

    // Delete
    @DeleteMapping("/category/{id}")
    public void deleteCategory(@PathVariable Long id) {
        System.out.println("Calling Delete Category");
        categoryService.deleteCategory(id);
    }

}