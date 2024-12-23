<template>
  <el-row :gutter="0">
    <el-col :span="16"></el-col>
    <el-col :span="8" style="display: flex;align-items: center;">
      <div class="wrapper">
        <form action="#">
          <h1>用户注册</h1>
          <div class="input-box">
            <i class="fas fa-envelope icon"></i>
            <input id="usernameInput" v-model="username" required />
            <label>Username</label>
          </div>
          <div class="input-box">
            <i class="fas fa-lock icon"></i>
            <input id="passwordInput" v-model="password" required />
            <label>Password</label>
          </div>
          <button @click="registerBrungle" class="btn">注册</button>
          <div class="signup-link">
            <p>Do you have an account? <a href="#">return.</a></p>
          </div>
        </form>
      </div>
    </el-col>
  </el-row>
</template>


<script setup>
import {ref} from "vue";

const username = ref('')
const password = ref('')

function registerBrungle(){

}

</script>
<style scoped>
* {
  font-family: "Poppins", sans-serif;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
.wrapper {
  position: relative;
  width: 400px;
  height: 450px;
  background: #3e404d;
  border: 2px solid rgba(255, 255, 255, 0.5);
  border-radius: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  backdrop-filter: blur(15px);
}

.wrapper:hover {
  box-shadow: 0 0 40px rgba(255,255,255,0.5);
  background: #46474e;
}

.wrapper h1 {
  font-size: 2em;
  color: #fff;
  text-align: center;
}

.wrapper .input-box {
  position: relative;
  width: 310px;
  margin: 30px 0;
  border-bottom: 2px solid #fff;
}

.wrapper .input-box input {
  width: 100%;
  height: 50px;
  background: transparent;
  outline: none;
  border: none;
  font-size: 1em;
  color: #fff;
  padding: 0 40px 0 5px;
}

.wrapper .input-box label {
  position: absolute;
  top: 50%;
  left: 5px;
  transform: translateY(-50%);
  font-size: 1em;
  color: #fff;
  pointer-events: none;
  transition: 0.5s;
}

.wrapper .input-box input:focus ~ label,
.wrapper .input-box input:valid ~ label {
  top: -5px;
}

.wrapper .input-box .icon {
  position: absolute;
  right: 8px;
  color: #fff;
  font-size: 1.2em;
  line-height: 57px;
}

.wrapper .row {
  margin: -15px 0 15px;
  font-size: 0.9em;
  color: #fff;
  display: flex;
  justify-content: space-between;
}

.wrapper .row label {
  display: flex;
  align-items: center;
  gap: 5px;
}

.wrapper .row a {
  color: #fff;
  text-decoration: none;
}

.wrapper .options a:hover {
  text-decoration: underline;
}

.wrapper .btn {
  width: 100%;
  height: 40px;
  background: #fff;
  outline: none;
  border: none;
  border-radius: 40px;
  cursor: pointer;
  font-size: 1em;
  font-weight: 500;
  color: #000;
  margin-top: 10px;
}

.btn:hover {
  background: #ffffea;
}

.wrapper .signup-link {
  font-size: 0.9em;
  color: #fff;
  text-align: center;
  margin: 25px 0 10px;
}

.wrapper .signup-link a {
  color: #fff;
  text-decoration: none;
  font-weight: 600;
}

.wrapper .signup-link a:hover {
  text-decoration: underline;
}

@media (max-width: 360px) {
  .wrapper {
    width: 100%;
    height: 100vh;
    border: none;
    border-radius: 0px;
  }

  .wrapper .input-box {
    width: 290px;
  }
  #img {
    z-index: -90;
  }
}


</style>