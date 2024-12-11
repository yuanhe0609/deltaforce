package com.company.project.service;

import com.company.project.entity.Result;

public interface IItemService {
    Result getItems();
    Result getItemById(Integer id);
}
