import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import UserList from '../views/UserList.vue'
import AddUser from '../views/AddUser.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/users',
    name: 'UserList',
    component: UserList
  },
  {
    path: '/add-user',
    name: 'AddUser',
    component: AddUser
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router