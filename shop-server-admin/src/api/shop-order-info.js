import request from '@/utils/request'

export function shopOrderInfoPage(params) {
  return request({
    url: '/shop_api/shop-order-info/page',
    method: 'get',
    params
  })
}
