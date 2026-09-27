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

    @GetMapping("/categories/{categoryId}/items")
    public List<Item> getAllItems(@PathVariable("categoryId") Long categoryId) {

        return ItemService.getAllItems(categoryId);
    }

    @GetMapping("/categories/{categoryId}/items/{itemId}")
    public Item getItem(@PathVariable("categoryId") Long categoryId, @PathVariable("itemId") Long itemId) {

        return ItemService.getItem(categoryId, itemId);
    }

    @PutMapping("/categories/{categoryId}/items/{itemId}")
    public Item updateItem(@PathVariable("categoryId") Long categoryId, @PathVariable("itemId") Long itemId, @RequestBody Item itemObject) {

        return ItemService.updateItem(categoryId, itemId, itemObject);
    }

    @DeleteMapping("/categories/{categoryId}/items/{itemId}")
    public void deleteItem(@PathVariable("categoryId") Long categoryId, @PathVariable("itemId") Long itemId) {

        ItemService.deleteItem(categoryId, itemId);
    }

}
