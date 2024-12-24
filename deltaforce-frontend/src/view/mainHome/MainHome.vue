<template>
  <Header/>
  <el-divider style="border: 1px;margin: 1px;background-color: cornflowerblue"/>
  <el-row :gutter="0" style="height: 90%">
      <el-col :span=leftSpan style="height: 100%;padding-top: 0;padding-right: 0;">
        <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            :collapse="isCollapse"
            style="overflow: scroll;overflow-x: hidden"
        >
          <el-sub-menu index="1">
            <template #title>
              <el-icon><img src="../../../public/goods.png" class="icon" alt=""></el-icon>
              <span>物品</span>
            </template>

            <el-menu-item index="1-1" @click="setItemFlag('weapon')">
              <el-icon><img src="../../../public/weapon.png" class="icon" alt=""></el-icon>武器
            </el-menu-item>
            <el-menu-item index="1-2" @click="setItemFlag('armor')">
              <el-icon><img src="../../../public/armor.png" class="icon" alt=""></el-icon>装备
            </el-menu-item>
            <el-menu-item index="1-3" @click="setItemFlag('accessory')">
              <el-icon><img src="../../../public/accessory.png" class="icon" alt=""></el-icon>配件
            </el-menu-item>
            <el-menu-item index="1-4" @click="setItemFlag('bullet')">
              <el-icon><img src="../../../public/bullet.png" class="icon" alt=""></el-icon>弹药
            </el-menu-item>
            <el-menu-item index="1-5" @click="setItemFlag('medicine')">
              <el-icon><img src="../../../public/medicine.png" class="icon" alt=""></el-icon>药品
            </el-menu-item>
            <el-menu-item index="1-6" @click="setItemFlag('material')">
              <el-icon><img src="../../../public/material.png" class="icon" alt=""></el-icon>材料
            </el-menu-item>
            <el-menu-item index="1-7" @click="setItemFlag('keyCard')">
              <el-icon><img src="../../../public/keyCard.png" class="icon" alt=""></el-icon>钥匙卡
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
        <WeaponList v-if="itemFlag === 'weapon'" :itemData=weaponData></WeaponList>
<!--        <ItemList v-if="itemFlag === 'armor'" :itemData=armorData></ItemList>-->
<!--        <ItemList v-if="itemFlag === 'accessory'" :itemData=accessoryData></ItemList>-->
<!--        <ItemList v-if="itemFlag === 'bullet'" :itemData=bulletData></ItemList>-->
<!--        <ItemList v-if="itemFlag === 'medicine'" :itemData=medicineData></ItemList>-->
        <MaterialList v-if="itemFlag === 'material'" :itemData=materialData></MaterialList>
<!--        <ItemList v-if="itemFlag === 'keyCard'" :itemData=keyCardData></ItemList>-->

      </el-col>
    </el-row>
  <el-divider style="border: 1px;margin: 1px;background-color: cornflowerblue"/>
  <Footer/>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import MaterialList from "@/view/mainHome/materialList/MaterialList.vue";
import WeaponList from "@/view/mainHome/weaponList/WeaponList.vue";
import Header from "/src/components/header/Header.vue"
import Footer from "/src/components/footer/Footer.vue"
import axios from "axios";
import getCookie, {updateCookie} from "@/assets/cookie.js";
const leftSpan = ref(3)
const rightSpan = ref(21)
const isCollapse = ref(false)
const weaponData = ref([])
const armorData = ref([])
const accessoryData = ref([])
const bulletData = ref([])
const medicineData = ref([])
const materialData = ref([])
const keyCardData = ref([])

const accessToken = ref('')
const refreshToken = ref('')
const itemFlag = ref(' ')
function setItemFlag(flag){
  itemFlag.value = flag
}
function getItemList(){
  accessToken.value = getCookie()[0].value
  refreshToken.value = getCookie()[1].value
  console.log(accessToken.value)
  console.log(refreshToken.value)
  axios.get("http://7fc50b04.r1.cpolar.top/get_item", {headers:{"Access-Token":accessToken.value,"Refresh-Token":refreshToken.value}}).then(res=>{
    if(res.data.code === 300){
      updateCookie(res)
      axios.get("http://7fc50b04.r1.cpolar.top/get_item", {headers:{"Access-Token":accessToken.value,"Refresh-Token":refreshToken.value}}).then(res=>{
        weaponData.value = res.data.data.weapon
        armorData.value = res.data.data.armor
        accessoryData.value = res.data.data.accessory
        bulletData.value = res.data.data.bullet
        medicineData.value = res.data.data.medicine
        materialData.value = res.data.data.material
        keyCardData.value = res.data.data.keyCard
      })
    }else if(res.data.code === 200){
      console.log(res.data.data)
      weaponData.value = res.data.data.weapon
      armorData.value = res.data.data.armor
      accessoryData.value = res.data.data.accessory
      bulletData.value = res.data.data.bullet
      medicineData.value = res.data.data.medicine
      materialData.value = res.data.data.material
      keyCardData.value = res.data.data.keyCard



    }else{
      //TODO 调用错误后，需执行的操作
    }
  })
}
onMounted(()=>{
  getItemList()
})
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