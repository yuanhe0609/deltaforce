<template>
  <el-row :gutter=0 style="height: 50px">
    <el-col :span=10 style="display: flex;align-items: center;">
      <el-radio-group v-model="isCollapse">
        <el-radio-button :value="false" @click="handleOpen">展开</el-radio-button>
        <el-radio-button :value="true" @click="handleClose">隐藏</el-radio-button>
      </el-radio-group>
    </el-col>
    <el-col :span=4 style="display: flex;justify-content: center;align-items: center;"><el-avatar :src="logoUrl"></el-avatar></el-col>
    <el-col :span=10 style="display: flex;justify-content: right;align-items: center;">
      <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"/>
    </el-col>
  </el-row>
  <el-divider style="border: 1px;margin: 1px;background-color: cornflowerblue"/>
    <el-row :gutter="0">
      <el-col :span=leftSpan style="height: 100%;padding-top: 0;padding-right: 0;">
        <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            :collapse="isCollapse"
        >
          <el-sub-menu index="1">
            <template #title>
              <el-icon><img src="../../../public/goods.png" class="icon" alt=""></el-icon>
              <span>物品</span>
            </template>

            <el-menu-item index="1-1">
              <el-icon><img src="../../../public/weapon.png" class="icon" alt=""></el-icon>武器
            </el-menu-item>
            <el-menu-item index="1-2">
              <el-icon><img src="../../../public/armor.png" class="icon" alt=""></el-icon>装备
            </el-menu-item>
            <el-menu-item index="1-3">
              <el-icon><img src="../../../public/accessory.png" class="icon" alt=""></el-icon>配件
            </el-menu-item>
            <el-menu-item index="1-4">
              <el-icon><img src="../../../public/bullet.png" class="icon" alt=""></el-icon>弹药
            </el-menu-item>
            <el-menu-item index="1-5">
              <el-icon><img src="../../../public/medicine.png" class="icon" alt=""></el-icon>药品
            </el-menu-item>
            <el-menu-item index="1-6">
              <el-icon><img src="../../../public/material.png" class="icon" alt=""></el-icon>材料
            </el-menu-item>
            <el-menu-item index="1-7">
              <el-icon><img src="../../../public/keycard.png" class="icon" alt=""></el-icon>钥匙卡
            </el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="2">
            <template #title>
              <el-icon><img src="../../../public/map.png" class="icon" alt=""></el-icon>
              <span>地图</span>
            </template>
            <el-menu-item index="2-1">零号大坝</el-menu-item>
            <el-menu-item index="2-2">长弓溪谷</el-menu-item>
            <el-menu-item index="2-3">巴克什</el-menu-item>
            <el-menu-item index="2-4">航天基地</el-menu-item>
          </el-sub-menu>
          <el-menu-item index="3">
            <el-icon><img src="../../../public/setting.png" class="icon" alt=""></el-icon>
            <template #title><span>设置</span></template>
          </el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span=rightSpan style="height: 100%;padding: 0;">
        <ItemList></ItemList>
      </el-col>
    </el-row>

</template>

<script setup>
import logoUrl from "../../../public/logo.png"
import { ref } from 'vue'
import axios from "axios";
import ItemList from "@/view/itemList/ItemList.vue";
import {Search} from "@element-plus/icons-vue";
const count = ref(0)
const load = () => {
  count.value += 5
}
const leftSpan = ref(3)
const rightSpan = ref(21)
const isCollapse = ref(false)
const handleOpen = () => {
  getData()
  leftSpan.value = 3
  rightSpan.value = 21
}
const handleClose = () => {
  leftSpan.value = 1
  rightSpan.value = 23
}

function getData(){
  axios.get("http://localhost:8000/login").then(res=>{
    console.log(res)
  })
}
function handleMouseEnter(){
  console.log("asd")
}
</script>

<style>
.el-row{
  height: 100%;
}
td {
  padding: 0 !important; /* 设置你想要的 padding */
  border: 0 !important;
}
tr {
  height: 75px !important;
}
.el-menu-vertical-demo:is(.el-menu--collapse) {
  width: 100%;
  height: 100%;
}
.el-menu-vertical-demo{
  height: 100%;
}
.infinite-list {
  height: 300px;
  padding: 0;
  margin: 0;
  list-style: none;
}
.infinite-list .infinite-list-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  background: var(--el-color-primary-light-9);
  margin: 0;

  color: var(--el-color-primary);
}
.infinite-list .infinite-list-item + .list-item {
  margin-top: 10px;
}
.icon{
  height: 25px;
  width: 25px;
}
</style>