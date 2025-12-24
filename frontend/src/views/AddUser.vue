<template>
  <div class="add-user-container">
    <h2>添加用户</h2>
    <form @submit.prevent="handleAddUser" class="add-user-form">
      <div class="form-group">
        <label for="name">姓名：</label>
        <input type="text" id="name" v-model="form.name" required>
      </div>
      <div class="form-group">
        <label for="password">密码：</label>
        <input type="password" id="password" v-model="form.password" required>
      </div>
      <div class="form-group">
        <label for="avatar">头像URL：</label>
        <input type="text" id="avatar" v-model="form.avatar">
      </div>
      <button type="submit">添加用户</button>
      <button type="button" @click="resetForm">重置</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { userApi } from '../api/user'

const router = useRouter()

const form = ref({
  name: '',
  password: '',
  avatar: ''
})

const handleAddUser = async () => {
  try {
    await userApi.addUser(form.value)
    alert('添加成功')
    resetForm()
    router.push('/')
  } catch (error) {
    console.error('添加失败:', error)
    alert('添加失败')
  }
}

const resetForm = () => {
  form.value = {
    name: '',
    password: '',
    avatar: ''
  }
}
</script>

<style scoped>
.add-user-container {
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-top: 20px;
}

.add-user-form {
  display: flex;
  flex-direction: column;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  padding: 10px 20px;
  margin-right: 10px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 10px;
}

button:hover {
  background-color: #45a049;
}

button[type="button"] {
  background-color: #f44336;
}

button[type="button"]:hover {
  background-color: #d32f2f;
}
</style>