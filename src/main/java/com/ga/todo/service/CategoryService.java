package com.ga.todo.service;

import com.ga.todo.exception.InformationExistException;
import com.ga.todo.exception.InformationNotFoundException;
import com.ga.todo.model.Category;
import com.ga.todo.model.User;
import com.ga.todo.repository.CategoryRepository;
import com.ga.todo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageStorageService imageStorageService;

    // Check if Category exists by ID for the logged-in user
    private Category checkExistById(Long id) {
        return categoryRepository.findByIdAndUserId(id, getCurrentLoggedInUser().getId())
                .orElseThrow(() ->
                        new InformationNotFoundException("Category not found with id: " + id)
                );
    }

    // Check if the logged-in user already has a Category with this name
    private void checkExistByName(String name) {
        if (categoryRepository.findByUserIdAndName(getCurrentLoggedInUser().getId(), name) != null) {
            throw new InformationExistException(
                    "Category with name " + name + " already exists"
            );
        }
    }

    // Create Category
    public Category createCategory(Category categoryObject) {
        checkExistByName(categoryObject.getName());
        categoryObject.setUser(getCurrentLoggedInUser());

        return categoryRepository.save(categoryObject);
    }

    // Get Categories
    public List<Category> getCategories() {
        return categoryRepository.findByUserId(getCurrentLoggedInUser().getId());
    }

    // Get Category
    public Category getCategory(Long id) {
        return checkExistById(id);
    }

    // Update Category
    public Category updateCategory(Long id, Category categoryObject) {
        Category existingCategory = checkExistById(id);
        if (!existingCategory.getName().equals(categoryObject.getName())) {
            checkExistByName(categoryObject.getName());
        }
        existingCategory.setName(categoryObject.getName());
        existingCategory.setDescription(categoryObject.getDescription());

        return categoryRepository.save(existingCategory);
    }

    public Category uploadImage(Long id, MultipartFile image) {
        Category category = checkExistById(id);
        category.setImageUrl(imageStorageService.store(image));

        return categoryRepository.save(category);
    }


    // Delete Category
    public void deleteCategory(Long id) {
        Category category = checkExistById(id);

        categoryRepository.delete(category);
    }

    // Get Current LoggedIn User
    public static User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails)
                Objects.requireNonNull(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                ).getPrincipal();

        assert userDetails != null;

        return userDetails.getUser();
    }
}
