package com.company.project.service;

import com.company.project.entity.Item;
import com.company.project.entity.Result;

import java.util.List;

public interface IItemService {
    Result getItems();
    Result getItemById(Integer id);
    Result addItem(Item item);
    Result addItem(List<Item> item);
}
