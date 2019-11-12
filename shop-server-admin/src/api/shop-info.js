import request from '@/utils/request'

export function shopInfoPage(params) {
  return request({
    url: '/shop_api/shop-info/page',
    method: 'get',
    params
  })
}

export function addShopInfo(data) {
  return request({
    url: '/shop_api/shop-info/add/shopInfo',
    method: 'post',
    data
  })
}

export function editShopInfo(data) {
  return request({
    url: '/shop_api/shop-info/edit/shopInfo',
    method: 'post',
    data
  })
}
