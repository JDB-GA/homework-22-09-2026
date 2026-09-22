package com.ga.todo.service;

import com.ga.todo.exception.InformationExistException;
import com.ga.todo.exception.InformationNotFoundException;
import com.ga.todo.model.Category;
import com.ga.todo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    // Create Category
    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category categoryObject) {
        Category category = categoryRepository.findByName(categoryObject.getName());
        if (category != null) {
            throw new InformationExistException("Category with name" + category.getName() + " already exists");
        } else {
            return categoryRepository.save(categoryObject);
        }
    }

    // Get Categories
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    // Get Category
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new InformationNotFoundException("Category not found with id: " + id));
    }

    // Update Category
    public Category updateCategory(Long id, Category category) {
        category.setId(id);
        return categoryRepository.save(category);
    }

    // Delete Category
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
