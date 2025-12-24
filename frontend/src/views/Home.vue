<template>
  <div class="home-container">
    <h1>欢迎使用用户管理系统</h1>
    <div class="home-content">
      <p>这是一个功能完善的用户管理系统，提供以下功能：</p>
      <ul>
        <li>用户列表查看</li>
        <li>用户详情查看</li>
        <li>添加新用户</li>
        <li>删除现有用户</li>
      </ul>
      <div class="home-actions">
        <router-link to="/users" class="btn-primary">查看用户列表</router-link>
        <router-link to="/add-user" class="btn-secondary">添加新用户</router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
// 首页组件，提供系统概览和主要功能入口
</script>

<style scoped>
.home-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
}

.home-content {
  margin-top: 40px;
  padding: 30px;
  background-color: #f5f5f5;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.home-content p {
  font-size: 18px;
  margin-bottom: 20px;
}

.home-content ul {
  list-style: none;
  padding: 0;
  margin: 30px 0;
}

.home-content li {
  font-size: 16px;
  margin: 10px 0;
  padding: 10px;
  background-color: white;
  border-radius: 4px;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
}

.home-actions {
  margin-top: 40px;
  display: flex;
  justify-content: center;
  gap: 20px;
}

.btn-primary, .btn-secondary {
  padding: 12px 24px;
  font-size: 16px;
  text-decoration: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.btn-primary {
  background-color: #4CAF50;
  color: white;
}

.btn-primary:hover {
  background-color: #45a049;
}

.btn-secondary {
  background-color: #2196F3;
  color: white;
}

.btn-secondary:hover {
  background-color: #0b7dda;
}
</style>