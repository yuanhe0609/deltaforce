import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index.js'
import './assets/global.css'



createApp(App).use(ElementPlus).use(router).mount('#app')
