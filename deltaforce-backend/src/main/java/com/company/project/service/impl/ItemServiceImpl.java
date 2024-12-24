package com.company.project.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.company.project.entity.Item;
import com.company.project.entity.Result;
import com.company.project.mapper.ItemMapper;
import com.company.project.service.IItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ItemServiceImpl implements IItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public Result getItems() {
        List<Item> weaponList = new ArrayList<>();
        List<Item> armorList = new ArrayList<>();
        List<Item> accessoryList = new ArrayList<>();
        List<Item> bulletList = new ArrayList<>();
        List<Item> medicineList = new ArrayList<>();
        List<Item> materialList = new ArrayList<>();
        List<Item> keyCardList = new ArrayList<>();
        Map<String,List<Item>> map = new HashMap<>();
        if(redisTemplate.opsForValue().get("weapon") == null
                || redisTemplate.opsForValue().get("armor") == null
                || redisTemplate.opsForValue().get("accessory") == null
                || redisTemplate.opsForValue().get("bullet") == null
                || redisTemplate.opsForValue().get("medicine") == null
                || redisTemplate.opsForValue().get("material") == null
                || redisTemplate.opsForValue().get("keyCard") == null) {

            List<Item> itemList = itemMapper.selectAllItems();
            for(Item item : itemList){
                String[] rowAndCol = item.getSize().split("\\*");
                item.setRow(Integer.valueOf(rowAndCol[0]));
                item.setCol(Integer.valueOf(rowAndCol[1]));
                switch (item.getType()){
                    case "weapon":
                        weaponList.add(item);
                        break;
                    case "armor":
                        armorList.add(item);
                        break;
                    case "accessory":
                        accessoryList.add(item);
                        break;
                    case "bullet":
                        bulletList.add(item);
                        break;
                    case "medicine":
                        medicineList.add(item);
                        break;
                    case "material":
                        materialList.add(item);
                        break;
                    case "keyCard":
                        keyCardList.add(item);
                        break;
                    default:
                        break;
                }
            }


            redisTemplate.opsForValue().set("weapon",weaponList,3600, TimeUnit.SECONDS);
            redisTemplate.opsForValue().set("armor",armorList,3600, TimeUnit.SECONDS);
            redisTemplate.opsForValue().set("accessory",accessoryList,3600, TimeUnit.SECONDS);
            redisTemplate.opsForValue().set("bullet",bulletList,3600, TimeUnit.SECONDS);
            redisTemplate.opsForValue().set("medicine",medicineList,3600, TimeUnit.SECONDS);
            redisTemplate.opsForValue().set("material",materialList,3600, TimeUnit.SECONDS);
            redisTemplate.opsForValue().set("keyCard",keyCardList,3600, TimeUnit.SECONDS);
        }else{
            String weaponString = JSON.toJSONString(redisTemplate.opsForValue().get("weapon"));
            String armorString = JSON.toJSONString(redisTemplate.opsForValue().get("armor"));
            String accessoryString = JSON.toJSONString(redisTemplate.opsForValue().get("accessory"));
            String bulletString = JSON.toJSONString(redisTemplate.opsForValue().get("bullet"));
            String medicineString = JSON.toJSONString(redisTemplate.opsForValue().get("medicine"));
            String materialString = JSON.toJSONString(redisTemplate.opsForValue().get("material"));
            String keyCardString = JSON.toJSONString(redisTemplate.opsForValue().get("keyCard"));

            TypeReference<List<Item>> typeReference = new TypeReference<>() {};

            weaponList = JSON.parseObject(weaponString, typeReference);
            armorList = JSON.parseObject(armorString, typeReference);
            accessoryList = JSON.parseObject(accessoryString, typeReference);
            bulletList = JSON.parseObject(bulletString, typeReference);
            medicineList = JSON.parseObject(medicineString, typeReference);
            materialList = JSON.parseObject(materialString, typeReference);
            keyCardList = JSON.parseObject(keyCardString, typeReference);
        }

        map.put("weapon",weaponList);
        map.put("armor",armorList);
        map.put("accessory",accessoryList);
        map.put("bullet",bulletList);
        map.put("medicine",medicineList);
        map.put("material",materialList);
        map.put("keyCard",keyCardList);

        return new Result().success(new JSONObject(map),"成功获取物品信息");
    }

    @Override
    public Result getItemById(Integer id) {
        return new Result().success(itemMapper.selectItemById(id),"成功获取物品信息");
    }

    @Override
    public Result addItem(Item item) {
        return new Result().success(itemMapper.insertItem(item),"添加物品成功");
    }

    @Override
    public Result addItem(List<Item> item) {
        return new Result().success(itemMapper.insertItem(item),"添加物品成功");
    }
}
