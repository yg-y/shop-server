import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}

export function addSysUser(data) {
  return request({
    url: '/user/add/sysUser',
    method: 'post',
    data
  })
}

export function editSysUser(data) {
  return request({
    url: '/user/edit/sysUser',
    method: 'post',
    data
  })
}

export function sysUserPage(params) {
  return request({
    url: '/user/page',
    method: 'get',
    params
  })
}
