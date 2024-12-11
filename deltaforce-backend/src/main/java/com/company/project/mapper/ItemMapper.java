package com.company.project.mapper;

import com.company.project.entity.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {

    List<Item> selectAllItems();

    Item selectItemById(Integer id);
}
