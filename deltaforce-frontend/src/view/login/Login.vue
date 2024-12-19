<template>

  <el-input
      v-model="username"
      style="width: 240px"
      placeholder="Please input"
      clearable
  />
  <el-input
      v-model="password"
      style="width: 240px"
      placeholder="Please input"
      clearable
  />
  <el-button type="primary" @click="login">登录</el-button>
</template>
<script  setup>
  import { ref } from 'vue'
  import axios from "axios";
  import router from "@/router/index.js";
  const username = ref('')
  const password = ref('')
  const accessToken = ref('')
  const refreshToken = ref('')
  function updateCookie(res){
    const expairdate = new Date();
    expairdate.setTime(expairdate.getTime() + (24*60*60*1000));
    accessToken.value = res.data.data.AccessToken
    refreshToken.value = res.data.data.RefreshToken
    document.cookie = "Access-Token="+accessToken.value+"; expires="+expairdate.toUTCString()
    document.cookie = "Refresh-Token="+refreshToken.value+"; expires="+expairdate.toUTCString()
  }
  function login(){
    axios.post("http://7fc50b04.r1.cpolar.top/login",{username:username.value,password:password.value}).then(res=>{
      updateCookie(res)
      router.push('/main')
    })
  }
</script>


<style scoped>

</style>