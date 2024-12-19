import { createWebHashHistory, createRouter } from 'vue-router'

import MainHome from "../view/mainHome/MainHome.vue";
import Login from "../view/login/Login.vue"


const routes = [
    { path: '/', component: Login },
    { path: '/main', component: MainHome }
]

const router = createRouter({
    history: createWebHashHistory(),
    mode:'history',
    routes,
})
export default router