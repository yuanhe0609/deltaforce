package com.company.project.controller;

import com.company.project.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
