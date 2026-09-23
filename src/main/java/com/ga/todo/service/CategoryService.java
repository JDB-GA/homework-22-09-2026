package com.ga.todo.service;

import com.ga.todo.exception.InformationExistException;
import com.ga.todo.exception.InformationNotFoundException;
import com.ga.todo.model.Category;
import com.ga.todo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Check if Category exists by ID
    private Category checkExistById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new InformationNotFoundException("Category not found with id: " + id)
                );
    }

    // Check if Category exists by Name
    private void checkExistByName(String name) {
        if (categoryRepository.findByName(name) != null) {
            throw new InformationExistException(
                    "Category with name " + name + " already exists"
            );
        }
    }

    // Create Category
    public Category createCategory(Category categoryObject) {
        checkExistByName(categoryObject.getName());

        return categoryRepository.save(categoryObject);
    }

    // Get Categories
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    // Get Category
    public Category getCategory(Long id) {
        return checkExistById(id);
    }

    // Update Category
    public Category updateCategory(Long id, Category categoryObject) {
        Category existingCategory = checkExistById(id);
        existingCategory.setName(categoryObject.getName());
        existingCategory.setDescription(categoryObject.getDescription());

        return categoryRepository.save(existingCategory);
    }


    // Delete Category
    public void deleteCategory(Long id) {
        Category category = checkExistById(id);

        categoryRepository.delete(category);
    }
}
