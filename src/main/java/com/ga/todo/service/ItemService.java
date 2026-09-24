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

    public Item createItem(Long categoryId, Item item) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new InformationNotFoundException("Category with id " + categoryId + " not found")
        );
        item.setCategory(category);

        return ItemRepository.save(item);
    }

    public List<Item> getAllItems() {

        return ItemRepository.findAll();
    }

    public Item getItem(Long itemId) {
        return ItemRepository.findById(itemId).orElseThrow(
                () -> new InformationNotFoundException("Item with id " + itemId + " not found")
        );
    }

    public Item updateItem(Long itemId, Item item) {
        Item oldItem = getItem(itemId);

        oldItem.setName(item.getName());
        oldItem.setDescription(item.getDescription());
        oldItem.setDueDate(item.getDueDate());
        oldItem.setCategory(item.getCategory());

        return ItemRepository.save(oldItem);
    }

    public void deleteItem(Long ItemId) {
        Item oldItem = getItem(ItemId);

        ItemRepository.delete(oldItem);
    }

}
