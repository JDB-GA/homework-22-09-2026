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

    public Item createItem(Long categoryId, Item Item) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new InformationNotFoundException("Category with id " + categoryId + " not found")
        );
        Item.setCategory(category);

        return ItemRepository.save(Item);
    }

    public List<Item> getAllItems() {

        return ItemRepository.findAll();
    }

    public Item getItem(Long ItemId) {
        return ItemRepository.findById(ItemId).orElseThrow(
                () -> new InformationNotFoundException("Item with id " + ItemId + " not found")
        );
    }

    public Item updateItem(Long ItemId, Item Item) {
        Item oldItem = getItem(ItemId);

        oldItem.setName(Item.getName());
        oldItem.setDescription(Item.getDescription());
        oldItem.setDueDate(Item.getDueDate());
        oldItem.setCategory(Item.getCategory());

        return ItemRepository.save(oldItem);
    }

    public void deleteItem(Long ItemId) {
        Item oldItem = getItem(ItemId);

        ItemRepository.delete(oldItem);
    }

}
