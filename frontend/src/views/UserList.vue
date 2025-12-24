<template>
  <div class="user-list-container">
    <h1>欢迎使用用户管理系统</h1>
    <p class="welcome-message">这是一个简单的用户管理系统，您可以在这里查看、添加、编辑和删除用户。</p>
    
    <!-- 用户搜索 -->
    <div class="user-search">
      <input type="text" v-model="searchName" placeholder="输入用户名搜索" />
      <button @click="handleSearchByName">搜索</button>
      <button @click="resetSearch" class="reset-btn">重置</button>
    </div>
    
    <!-- 用户列表 -->
    <div class="user-list">
      <h2>用户列表</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>姓名</th>
            <th>头像</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in users" :key="user.id">
            <td>{{ user.id }}</td>
            <td>{{ user.name }}</td>
            <td>
              <img v-if="user.avatar" :src="user.avatar" alt="头像" class="avatar" />
              <span v-else>无</span>
            </td>
            <td>
              <button @click="handleGetUserById(user.id)">查看详情</button>
              <button @click="handleEditUser(user)" class="edit-btn">编辑</button>
              <button @click="handleDeleteUser(user.id)" class="delete-btn">删除</button>
            </td>
          </tr>
        </tbody>
      </table>
      <p v-if="users.length === 0" class="no-data">暂无用户数据</p>
    </div>
    
    <!-- 用户详情弹窗 -->
    <div v-if="userDetail" class="user-detail-modal">
      <div class="modal-content">
        <span class="close" @click="closeDetailModal">&times;</span>
        <h3>用户详情</h3>
        <p><strong>ID：</strong>{{ userDetail.id }}</p>
        <p><strong>姓名：</strong>{{ userDetail.name }}</p>
        <p><strong>密码：</strong>{{ userDetail.password }}</p>
        <p><strong>头像：</strong></p>
        <img v-if="userDetail.avatar" :src="userDetail.avatar" alt="头像" class="detail-avatar" />
        <p v-else>无头像</p>
      </div>
    </div>

    <!-- 用户编辑弹窗 -->
    <div v-if="isEditModalOpen" class="user-edit-modal">
      <div class="modal-content">
        <span class="close" @click="closeEditModal">&times;</span>
        <h3>编辑用户</h3>
        <form @submit.prevent="handleUpdateUser">
          <input type="hidden" v-model="editingUser.id" />
          <div class="form-group">
            <label for="edit-name">姓名：</label>
            <input type="text" id="edit-name" v-model="editingUser.name" required />
          </div>
          <div class="form-group">
            <label for="edit-password">密码：</label>
            <input type="text" id="edit-password" v-model="editingUser.password" required />
          </div>
          <div class="form-group">
            <label for="edit-avatar">头像URL：</label>
            <input type="text" id="edit-avatar" v-model="editingUser.avatar" />
          </div>
          <div class="form-actions">
            <button type="submit">保存修改</button>
            <button type="button" @click="closeEditModal" class="cancel-btn">取消</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { userApi } from '../api/user';
import { ref, onMounted, reactive } from 'vue';

export default {
  name: 'UserList',
  setup() {
    // 响应式数据
    const users = ref([]);
    const userDetail = ref(null);
    const isEditModalOpen = ref(false);
    const editingUser = reactive({ id: '', name: '', password: '', avatar: '' });
    const searchName = ref('');
    
    // 获取所有用户
    const fetchAllUsers = async () => {
      try {
        const response = await userApi.getAllUsers();
        if (response.code === 200) {
          users.value = response.data;
        } else {
          alert(response.msg);
        }
      } catch (error) {
        console.error('获取用户列表失败:', error);
        alert('获取用户列表失败');
      }
    };
    
    // 根据ID获取用户
    const handleGetUserById = async (id) => {
      try {
        const response = await userApi.getUserById(id);
        if (response.code === 200) {
          userDetail.value = response.data;
        } else {
          alert(response.msg);
        }
      } catch (error) {
        console.error('获取用户详情失败:', error);
        alert('获取用户详情失败');
      }
    };
    
    // 关闭详情弹窗
    const closeDetailModal = () => {
      userDetail.value = null;
    };

    // 打开编辑弹窗
    const handleEditUser = (user) => {
      // 复制用户数据到编辑对象
      editingUser.id = user.id;
      editingUser.name = user.name;
      editingUser.password = user.password;
      editingUser.avatar = user.avatar || '';
      isEditModalOpen.value = true;
    };

    // 关闭编辑弹窗
    const closeEditModal = () => {
      isEditModalOpen.value = false;
      // 重置编辑表单
      editingUser.id = '';
      editingUser.name = '';
      editingUser.password = '';
      editingUser.avatar = '';
    };

    // 更新用户信息
    const handleUpdateUser = async () => {
      try {
        const response = await userApi.updateUser(editingUser.id, editingUser);
        if (response.code === 200) {
          alert('修改用户成功');
          closeEditModal();
          // 重新获取用户列表
          fetchAllUsers();
        } else {
          alert(response.msg);
        }
      } catch (error) {
        console.error('修改用户失败:', error);
        alert('修改用户失败: ' + (error.message || '未知错误'));
      }
    };
    
    // 删除用户
    const handleDeleteUser = async (id) => {
      if (confirm('确定要删除这个用户吗？')) {
        try {
          console.log('尝试删除用户，ID:', id);
          const response = await userApi.deleteUser(id);
          console.log('删除用户响应:', response);
          if (response.code === 200) {
            alert('删除用户成功');
            // 重新获取用户列表
            fetchAllUsers();
          } else {
            alert(response.msg);
          }
        } catch (error) {
          console.error('删除用户失败:', error);
          console.error('错误详情:', error.response);
          alert('删除用户失败: ' + (error.message || '未知错误'));
        }
      }
    };
    
    // 根据用户名搜索用户
    const handleSearchByName = async () => {
      if (!searchName.value.trim()) {
        alert('请输入要搜索的用户名');
        return;
      }
      
      try {
        const response = await userApi.getUserByName(searchName.value);
        if (response.code === 200) {
          // 如果找到用户，展示在列表中
          if (response.data && response.data.length > 0) {
            users.value = response.data;
          } else {
            users.value = [];
            alert('未找到匹配的用户');
          }
        } else {
          alert(response.msg);
        }
      } catch (error) {
        console.error('搜索用户失败:', error);
        alert('搜索用户失败: ' + (error.message || '未知错误'));
      }
    };
    
    // 重置搜索
    const resetSearch = () => {
      searchName.value = '';
      fetchAllUsers();
    };
    
    // 组件挂载时获取所有用户
    onMounted(() => {
      fetchAllUsers();
    });
    
    return {
      users,
      userDetail,
      isEditModalOpen,
      editingUser,
      searchName,
      fetchAllUsers,
      handleGetUserById,
      closeDetailModal,
      handleEditUser,
      closeEditModal,
      handleUpdateUser,
      handleDeleteUser,
      handleSearchByName,
      resetSearch
    };
  }
};
</script>

<style scoped>
.user-list-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.user-search {
  margin-bottom: 20px;
  padding: 15px;
  background-color: #f5f5f5;
  border-radius: 8px;
}

.user-search input {
  padding: 10px;
  width: 250px;
  border: 1px solid #ddd;
  border-radius: 4px;
  margin-right: 10px;
}

.user-search button {
  padding: 10px 16px;
  margin-right: 10px;
}

.reset-btn {
  background-color: #999;
}

.reset-btn:hover {
  background-color: #777;
}

.avatar {
  width: 30px;
  height: 30px;
  border-radius: 50%;
}

.detail-avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  margin: 10px 0;
}

button {
  padding: 8px 16px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-right: 5px;
}

button:hover {
  background-color: #45a049;
}

.edit-btn {
  background-color: #2196F3;
}

.edit-btn:hover {
  background-color: #0b7dda;
}

.delete-btn {
  background-color: #f44336;
}

.delete-btn:hover {
  background-color: #d32f2f;
}

.cancel-btn {
  background-color: #f0f0f0;
  color: #333;
  margin-left: 10px;
}

.cancel-btn:hover {
  background-color: #e0e0e0;
}

.user-list table {
  width: 100%;
  border-collapse: collapse;
}

.user-list th, .user-list td {
  border: 1px solid #ddd;
  padding: 12px;
  text-align: left;
}

.user-list th {
  background-color: #f2f2f2;
}

.user-list tr:hover {
  background-color: #f5f5f5;
}

.no-data {
  text-align: center;
  padding: 20px;
  color: #666;
}

/* 弹窗样式 */
.user-detail-modal,
.user-edit-modal {
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0,0,0,0.4);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  width: 400px;
  position: relative;
}

.close {
  position: absolute;
  right: 10px;
  top: 10px;
  font-size: 28px;
  font-weight: bold;
  cursor: pointer;
}

.close:hover {
  color: #666;
}

/* 表单样式 */
.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.form-group input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-sizing: border-box;
}

.form-actions {
  margin-top: 20px;
  text-align: right;
}
</style>