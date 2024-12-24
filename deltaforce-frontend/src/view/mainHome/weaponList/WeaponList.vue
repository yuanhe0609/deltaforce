<template>
  <ul  class="infinite-list" style="overflow: auto;height: 100%;">
    <div v-for="data in itemData" :key="data"  class="item-div">
      <el-row :gutter="0">
        <el-col :span="6" style="display: flex;justify-content: center;align-items: center;">
          <el-image
            style="width: 150px; height: 150px"
            src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
          />
        </el-col>
        <el-col :span="18">
          <el-descriptions
              class="margin-top"
              :column="3"
              border
          >
            <el-descriptions-item label="名称" align="center" width="120">{{data.name}}</el-descriptions-item>
            <el-descriptions-item label="价格" align="center" width="120">{{data.price}}</el-descriptions-item>
            <el-descriptions-item label="尺寸" align="center" width="120">{{data.size}}</el-descriptions-item>
            <el-descriptions-item label="说明" align="center" width="240">{{data.desc}}</el-descriptions-item>
          </el-descriptions>
        </el-col>
      </el-row>
    </div>
  </ul>
</template>

<script setup>
import { toRefs, defineProps } from 'vue'
const props = defineProps({
  //子组件接收父组件传递过来的值
  itemData: Array,
})
//使用父组件传递过来的值
const {itemData} =toRefs(props)
</script>



<style>
.item-div{
  border-bottom: 1px solid #6495ed;
}
td {
  padding: 0 !important; /* 设置你想要的 padding */
  border: 0 !important;
}
tr {
  height: 75px !important;
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
</style>