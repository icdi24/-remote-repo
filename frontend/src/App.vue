<template>
  <div class="app-container">
    <nav class="main-nav">
      <div class="nav-brand">用户管理系统</div>
      <ul class="nav-menu">
        <li class="nav-item">
          <router-link to="/" class="nav-link">首页</router-link>
        </li>
        <li class="nav-item dropdown">
          <a href="#" class="nav-link dropdown-toggle">用户管理</a>
          <ul class="dropdown-menu">
            <li><router-link to="/users" class="dropdown-item">用户列表</router-link></li>
            <li><router-link to="/add-user" class="dropdown-item">添加用户</router-link></li>
          </ul>
        </li>
      </ul>
    </nav>
    <main class="main-content">
      <router-view />
    </main>
  </div>
</template>

<script setup>
// 不需要导入UserList，路由会自动处理
</script>

<style>
/* 全局样式 */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: Arial, sans-serif;
  background-color: #f5f5f5;
}

.app-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

/* 导航栏样式 */
.main-nav {
  background-color: #333;
  color: white;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
  height: 60px;
}

.nav-brand {
  font-size: 20px;
  font-weight: bold;
}

.nav-menu {
  list-style: none;
  display: flex;
}

.nav-item {
  position: relative;
}

.nav-link {
  color: white;
  text-decoration: none;
  padding: 0 15px;
  height: 60px;
  line-height: 60px;
  display: block;
  transition: background-color 0.3s;
}

.nav-link:hover {
  background-color: #555;
}

/* 下拉菜单样式 */
.dropdown-menu {
  position: absolute;
  top: 100%;
  left: 0;
  background-color: #333;
  list-style: none;
  display: none;
  min-width: 150px;
  z-index: 1000;
}

.dropdown:hover .dropdown-menu {
  display: block;
}

.dropdown-item {
  color: white;
  text-decoration: none;
  padding: 12px 15px;
  display: block;
  transition: background-color 0.3s;
}

.dropdown-item:hover {
  background-color: #555;
}

/* 主内容区域 */
.main-content {
  flex: 1;
  padding: 20px;
}
</style>
