import request from '@/utils/request'

export function shopUserPage(params) {
  return request({
    url: '/shop_api/shop-user/page',
    method: 'get',
    params
  })
}
