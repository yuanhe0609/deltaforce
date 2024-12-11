package com.company.project.service.impl;

import com.company.project.entity.Result;
import com.company.project.mapper.ItemMapper;
import com.company.project.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Result getItems() {

        return new Result().success(itemMapper.selectAllItems(),"成功获取物品信息");
    }

    @Override
    public Result getItemById(Integer id) {

        return new Result().success(itemMapper.selectItemById(id),"成功获取物品信息");
    }
}
