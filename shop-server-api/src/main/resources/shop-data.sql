/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : shop-data

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-11-12 23:07:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for shop_info
-- ----------------------------
DROP TABLE IF EXISTS `shop_info`;
CREATE TABLE `shop_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '商品名称',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品价格【原价】',
  `discount_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
  `brand` varchar(64) NOT NULL DEFAULT '' COMMENT '品牌',
  `type_id` int(11) NOT NULL DEFAULT '0' COMMENT '分类ID',
  `description` varchar(2048) NOT NULL DEFAULT '' COMMENT '商品描述',
  `title_photo` varchar(255) NOT NULL DEFAULT '' COMMENT '封面大图',
  `detail_photo` varchar(2048) NOT NULL DEFAULT '' COMMENT '详情大图【最多6张，逗号分隔】',
  `create_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of shop_info
-- ----------------------------
INSERT INTO `shop_info` VALUES ('1', 'Apple Iphone 11', '5499.00', '5399.00', 'Apple', '0', '一切都刚刚好。\r\n全新双摄系统取景范围更大，可将你的所见所爱尽收画面之中。iPhone 迄今最快的芯片和从早用到晚的电池续航，让你能做的事更多，却无需频频充电。而有了 iPhone 最高的视频画质，你所记录的那些美好时分，也会更加鲜活。', 'https://www.apple.com/v/iphone-11/c/images/overview/design-gallery/design_gallery_3_purple__sixi1xr7f7ue_medium.jpg', 'https://www.apple.com/v/iphone-11/c/images/overview/design-gallery/design_gallery_3_purple__sixi1xr7f7ue_medium.jpg,https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/iphone-11-pro-select-2019-family?wid=441&amp;hei=529&amp;fmt=jpeg&amp;qlt=95&amp;op_usm=0.5,https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/iphone-11-pro-max-select-2019?wid=441&hei=529&fmt=jpeg&qlt=95&op_usm=0.5,https://www.apple.com/v/iphone/home/ac/images/overview/compare/compare_iphone_11_pro__fvqwhr4dkhiu_large.jpg', '1573384399032', '1573391572972');
INSERT INTO `shop_info` VALUES ('2', 'Apple Iphone 11 Pro', '6199.00', '6099.00', 'Apple', '0', 'Pro 如其名。\n突破性的三摄系统，功能超广却简单易用；电池续航实现了空前的飞跃；极具潜能的芯片，不仅机器学习能力翻倍，更为智能手机开启了新的可能。来，认识一下我们首款足以称之为 Pro 的 iPhone。', 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/iphone-11-pro-select-2019-family?wid=441&amp;hei=529&amp;fmt=jpeg&amp;qlt=95&amp;op_usm=0.5', 'https://www.apple.com/v/iphone-11/c/images/overview/design-gallery/design_gallery_3_purple__sixi1xr7f7ue_medium.jpg,https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/iphone-11-pro-select-2019-family?wid=441&amp;hei=529&amp;fmt=jpeg&amp;qlt=95&amp;op_usm=0.5,https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/iphone-11-pro-max-select-2019?wid=441&hei=529&fmt=jpeg&qlt=95&op_usm=0.5,https://www.apple.com/v/iphone/home/ac/images/overview/compare/compare_iphone_11_pro__fvqwhr4dkhiu_large.jpg', '1573385755492', '1573482789208');
INSERT INTO `shop_info` VALUES ('3', 'Apple Iphone 11 Pro Max', '9599.00', '9499.00', 'Apple', '0', 'Pro 如其名。\n突破性的三摄系统，功能超广却简单易用；电池续航实现了空前的飞跃；极具潜能的芯片，不仅机器学习能力翻倍，更为智能手机开启了新的可能。来，认识一下我们首款足以称之为 Pro 的 iPhone。', 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/iphone-11-pro-max-select-2019?wid=441&hei=529&fmt=jpeg&qlt=95&op_usm=0.5,https://www.apple.com/v/iphone/home/ac/images/overview/compare/compare_iphone_11_pro__fvqwhr4dkhiu_large.jpg', 'https://www.apple.com/v/iphone-11-pro/c/images/overview/hero/triptych_middle_landscape__c14zbto4v402_medium.jpg', '1573386083673', '1573479128709');

-- ----------------------------
-- Table structure for shop_order_info
-- ----------------------------
DROP TABLE IF EXISTS `shop_order_info`;
CREATE TABLE `shop_order_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `shop_info_id` int(11) NOT NULL DEFAULT '0' COMMENT '商品ID',
  `payment_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '付款价格',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '购买用户',
  `order_number` varchar(64) NOT NULL DEFAULT '' COMMENT '订单编号',
  `number` int(5) NOT NULL DEFAULT '0' COMMENT '购买数量',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '订单状态【0 待付款 1 已付款 2 退货中 3 已退货】',
  `back_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '退货金额',
  `create_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of shop_order_info
-- ----------------------------
INSERT INTO `shop_order_info` VALUES ('1', '1', '5499.00', '1', '65465465465464654', '1', '1', '0.00', '1573384399032', '1573384399032');
INSERT INTO `shop_order_info` VALUES ('2', '2', '6199.00', '1', '8709bbd1f1a34b3fbebe7b37175e80e9', '1', '1', '0.00', '1573486220813', '1573486220813');

-- ----------------------------
-- Table structure for shop_type
-- ----------------------------
DROP TABLE IF EXISTS `shop_type`;
CREATE TABLE `shop_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '分类名称',
  `create_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of shop_type
-- ----------------------------

-- ----------------------------
-- Table structure for shop_user
-- ----------------------------
DROP TABLE IF EXISTS `shop_user`;
CREATE TABLE `shop_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email` varchar(255) NOT NULL DEFAULT '' COMMENT '邮箱',
  `phone` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `photo` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态 【0 正常 1 锁定】',
  `create_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of shop_user
-- ----------------------------
INSERT INTO `shop_user` VALUES ('1', 'young@shop.com', '13122222222', '桑之落矣', 'shangzhi', 'https://i.loli.net/2019/11/10/lrTBtzqhHEJsamb.jpg', '1', '1573393988655', '1573393988655');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email` varchar(255) NOT NULL DEFAULT '' COMMENT '邮箱',
  `phone` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `photo` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态 【0 正常 1 锁定】',
  `create_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin@shop.com', '12345678910', 'admin', 'admin', 'www.photo.com', '1', '1573378693169', '1573378693169');
INSERT INTO `sys_user` VALUES ('2', 'cto@shop.com', '13122212212', 'cto', '123456', 'www.baidu.com', '1', '1573389659318', '1573390119620');

-- ----------------------------
-- Table structure for title_img
-- ----------------------------
DROP TABLE IF EXISTS `title_img`;
CREATE TABLE `title_img` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `title_img` varchar(1024) NOT NULL DEFAULT '' COMMENT '轮播图片',
  `shop_info_id` int(11) NOT NULL DEFAULT '0' COMMENT '商品id',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '状态【1 可用 0 禁用】',
  `create_time` bigint(13) NOT NULL DEFAULT '0',
  `update_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of title_img
-- ----------------------------
