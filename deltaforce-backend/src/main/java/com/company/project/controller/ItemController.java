package com.company.project.controller;

import com.company.project.entity.Item;
import com.company.project.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private IItemService itemService;

    @RequestMapping("/get_item")
    public String getItem() {
        return itemService.getItems().toJsonString();
    }

    @RequestMapping("/get_item/{id}")
    public String getItem(@PathVariable Integer id) {
        return itemService.getItemById(id).toJsonString();
    }

    @RequestMapping("/add_item")
    public String getItem(Item item) {
        return itemService.addItem(item).toJsonString();
    }
    @RequestMapping("/add_item")
    public String getItem(List<Item> item) {
        return itemService.addItem(item).toJsonString();
    }
}
