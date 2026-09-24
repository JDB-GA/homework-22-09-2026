package com.ga.todo.controller;

import com.ga.todo.model.Item;
import com.ga.todo.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ItemController {
    private ItemService ItemService;


    @PostMapping("/categories/{categoryId}/items")
    public Item createItem(@PathVariable("categoryId") Long categoryId, @RequestBody Item itemObject) {

        return ItemService.createItem(categoryId, itemObject);
    }

    @GetMapping("/categories/items")
    public List<Item> getAllItems() {

        return ItemService.getAllItems();
    }

    @GetMapping("/categories/items/{id}")
    public Item getItem(@PathVariable("id") Long id) {

        return ItemService.getItem(id);
    }

    @PutMapping("/categories/items/{id}")
    public Item updateItem(@PathVariable("id") Long id, @RequestBody Item itemObject) {

        return ItemService.updateItem(id, itemObject);
    }

    @DeleteMapping("/categories/items/{id}")
    public void deleteItem(@PathVariable("id") Long id) {

        ItemService.deleteItem(id);
    }

}
