import { createWebHashHistory, createRouter } from 'vue-router'
import {getCookie} from '../assets/cookie.js'

import MainHome from "../view/mainHome/MainHome.vue";
import Login from "../view/login/Login.vue"
import Register from "../view/register/Register.vue"
import Retrieve from "@/view/retrieve/Retrieve.vue";

const routes = [
    { path: '/', component: Login, },
    { path: '/main', component: MainHome,meta: {requireAuth: true}},
    { path: '/register', component: Register, },
    { path: '/retrieve', component: Retrieve, },

]

const router = createRouter({
    history: createWebHashHistory(),
    mode:'history',
    routes,
})

router.beforeEach((to, from, next) => {
    if (to.matched.some((r) => r.meta.requireAuth)) {
        const token = getCookie()[0]
        if (token.value != null && token.value !== "undefined") {
            next();
        } else {
            next({
                path: '/',
            });
        }
    } else {
        next()
    }
})
export default router