import axios from 'axios';

// 创建axios实例
const request = axios.create({
  baseURL: 'http://localhost:8080', // 后端API基础路径
  timeout: 10000, // 请求超时时间
  headers: {
    'Content-Type': 'application/json'
  }
});

// 请求拦截器
request.interceptors.request.use(
  config => {
    // 可以在这里添加token等认证信息
    return config;
  },
  error => {
    console.error('请求错误:', error);
    return Promise.reject(error);
  }
);

// 响应拦截器
request.interceptors.response.use(
  response => {
    // 直接返回响应数据
    return response.data;
  },
  error => {
    console.error('响应错误:', error);
    return Promise.reject(error);
  }
);

export default request;