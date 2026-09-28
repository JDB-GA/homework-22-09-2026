package com.ga.todo.service;

import com.ga.todo.exception.InformationNotFoundException;
import com.ga.todo.model.Category;
import com.ga.todo.model.Item;
import com.ga.todo.repository.CategoryRepository;
import com.ga.todo.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository ItemRepository;
    public final CategoryRepository categoryRepository;

    // Only finds the category if it belongs to the logged-in user
    private Category getCategory(Long categoryId) {
        return categoryRepository.findByIdAndUserId(categoryId, CategoryService.getCurrentLoggedInUser().getId()).orElseThrow(
                () -> new InformationNotFoundException("Category with id " + categoryId + " not found")
        );
    }

    public Item createItem(Long categoryId, Item item) {
        Category category = getCategory(categoryId);
        item.setCategory(category);

        return ItemRepository.save(item);
    }

    public List<Item> getAllItems(Long categoryId) {
        getCategory(categoryId);

        return ItemRepository.findByCategoryId(categoryId);
    }

    public Item getItem(Long categoryId, Long itemId) {
        getCategory(categoryId);

        return ItemRepository.findByIdAndCategoryId(itemId, categoryId).orElseThrow(
                () -> new InformationNotFoundException("Item with id " + itemId + " not found in category " + categoryId)
        );
    }

    public Item updateItem(Long categoryId, Long itemId, Item item) {
        Item oldItem = getItem(categoryId, itemId);

        oldItem.setName(item.getName());
        oldItem.setDescription(item.getDescription());
        oldItem.setDueDate(item.getDueDate());

        return ItemRepository.save(oldItem);
    }

    public void deleteItem(Long categoryId, Long ItemId) {
        Item oldItem = getItem(categoryId, ItemId);

        ItemRepository.delete(oldItem);
    }

}
