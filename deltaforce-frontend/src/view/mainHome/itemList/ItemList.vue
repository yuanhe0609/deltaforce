<template>
  <ul v-infinite-scroll="load" class="infinite-list" style="overflow: auto;height: 100%;">
    <div v-for="i in count" :key="i" @click="click(i)" class="item-div">
      <el-descriptions title="" border :column=4>
        <el-descriptions-item
            :rowspan="2"
            :width="150"
            label="图片"
            align="center"
        >
          <el-image
              style="width: 142px; height: 142px"
              src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
          />
        </el-descriptions-item>
        <el-descriptions-item label="品质" align="center">
          <el-tag size="small" type="danger">
          </el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="名称" align="center">火箭燃料</el-descriptions-item>
        <el-descriptions-item label="价格" align="center">1500000</el-descriptions-item>
        <el-descriptions-item label="尺寸" align="center">3*4</el-descriptions-item>
        <el-descriptions-item label="单格价格" align="center">125000</el-descriptions-item>
        <el-descriptions-item label="来源" align="center">航天基地</el-descriptions-item>
      </el-descriptions>
    </div>
  </ul>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import axios from "axios";
const count = ref(0)
const itemData = ref([])
const accessToken = ref('')
const refreshToken = ref('')

const load = () => {
  count.value += 5
}
function click(i){
  console.log("-------------"+i+"----s---------")
}
function getItemList(){

  const cookieArr = document.cookie.split(";");
  cookieArr.forEach(item=>{
    const cookiePair = item.trim().split("=");
    if("Access-Token" === cookiePair[0]){
      accessToken.value = cookiePair[1]
    }
    if("Refresh-Token" === cookiePair[0]){
      refreshToken.value = cookiePair[1]
    }
  })
  axios.get("http://7fc50b04.r1.cpolar.top/get_item", {headers:{"Access-Token":accessToken.value,"Refresh-Token":refreshToken.value}}).then(res=>{
    if(res.data.code === 300){
      updateCookie(res)
      axios.get("http://7fc50b04.r1.cpolar.top/get_item", {headers:{"Access-Token":accessToken.value,"Refresh-Token":refreshToken.value}}).then(res=>{
        itemData.value = res.data.data;
      })
    }else if(res.data.code === 200){
      itemData.value = res.data.data;
    }else{
      //TODO 调用错误后，需执行的操作
    }
  })
}
function updateCookie(res){
  const expairdate = new Date();
  expairdate.setTime(expairdate.getTime() + (24*60*60*1000));
  accessToken.value = res.data.data.AccessToken
  refreshToken.value = res.data.data.RefreshToken
  document.cookie = "Access-Token="+accessToken.value+"; expires="+expairdate.toUTCString()
  document.cookie = "Refresh-Token="+refreshToken.value+"; expires="+expairdate.toUTCString()
}
function deleteCookie(){
  document.cookie = "Access-Token=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
  document.cookie = "Refresh-Token=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
}
onMounted(()=>{
  getItemList()
})

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