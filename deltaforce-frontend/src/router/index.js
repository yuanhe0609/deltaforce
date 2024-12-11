import { createMemoryHistory, createRouter } from 'vue-router'

import MainHome from "@/view/mainHome/MainHome.vue";


const routes = [
    { path: '/', component: MainHome },
]

const router = createRouter({
    history: createMemoryHistory(),
    routes,
})
export default router