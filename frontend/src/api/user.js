import request from '../utils/request';

// 用户API服务
export const userApi = {
  // 根据ID获取用户
  getUserById: (id) => {
    return request.get(`/api/user/check/id/${id}`);
  },
  
  // 根据用户名模糊查询用户
  getUserByName: (name) => {
    return request.get(`/api/user/check/${name}`);
  },
  
  // 添加用户
  addUser: (userData) => {
    return request.post('/api/user', userData);
  },
  
  // 获取所有用户
  getAllUsers: () => {
    return request.get('/api/user/all');
  },
  
  // 删除用户
  deleteUser: (id) => {
    return request.delete(`/api/user/remove/${id}`);
  },
  
  // 修改用户信息
  updateUser: (id, userData) => {
    return request.post(`/api/user/update/${id}`, userData);
  }
};
