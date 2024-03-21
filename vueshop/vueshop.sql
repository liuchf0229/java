/*
Navicat MySQL Data Transfer

Source Server         : LocalHost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : vueshop

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2024-01-22 14:49:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app_ad
-- ----------------------------
DROP TABLE IF EXISTS `app_ad`;
CREATE TABLE `app_ad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `position` varchar(32) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `is_show` tinyint(1) DEFAULT NULL,
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  `sort_order` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app_ad
-- ----------------------------
INSERT INTO `app_ad` VALUES ('1', '小米618来袭', 'carousel', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/352cd4824f0febc13d079485735af3cf.jpg?thumb=1&w=1226&h=460&f=webp&q=90', '/product/20', '1', '2022-05-30 09:55:39', '2022-12-28 22:42:52', '1');
INSERT INTO `app_ad` VALUES ('2', '米家只能IH多功能料理锅', 'carousel', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/c6240d955a94333a05c3ce26817a15c7.jpg?thumb=1&w=1226&h=460&f=webp&q=90', '/product/27', '1', '2022-05-30 09:57:37', '2022-12-28 22:42:28', '2');

-- ----------------------------
-- Table structure for app_address
-- ----------------------------
DROP TABLE IF EXISTS `app_address`;
CREATE TABLE `app_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `tel` varchar(255) NOT NULL,
  `province` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `county` varchar(255) NOT NULL,
  `address_detail` varchar(255) NOT NULL,
  `area_code` varchar(12) NOT NULL,
  `postal_code` varchar(255) DEFAULT NULL COMMENT '邮政编码',
  `is_default` tinyint(1) NOT NULL,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app_address
-- ----------------------------
INSERT INTO `app_address` VALUES ('10', '1', '吕小布', '18825048653', '广东省', '广州市', '天河区', '龙洞小区15号', '440106', '524500', '1', '0', '2022-12-29 01:46:07', null);
INSERT INTO `app_address` VALUES ('11', '4', 'eeee6', '18829302226', '天津市', '天津市', '和平区', '2222222222222266', '120101', null, '1', '0', '2024-01-21 13:03:39', '2024-01-22 12:51:33');

-- ----------------------------
-- Table structure for app_cart_item
-- ----------------------------
DROP TABLE IF EXISTS `app_cart_item`;
CREATE TABLE `app_cart_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `sku_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT '1',
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app_cart_item
-- ----------------------------
INSERT INTO `app_cart_item` VALUES ('1', '1', '140', '25', '3', '2023-02-28 16:12:42', '2023-02-28 22:29:00');

-- ----------------------------
-- Table structure for app_category
-- ----------------------------
DROP TABLE IF EXISTS `app_category`;
CREATE TABLE `app_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `icon_url` varchar(255) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `is_show` tinyint(1) NOT NULL,
  `sort_order` int(4) DEFAULT NULL,
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  `is_delete` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app_category
-- ----------------------------
INSERT INTO `app_category` VALUES ('7', '电视', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/7363f6b4-edcf-4dab-b70b-5c0a39b27c47.png', null, '1', '1', '2022-12-27 20:52:12', '2022-12-28 21:35:17', '0');
INSERT INTO `app_category` VALUES ('8', '手机', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/5856068a-0d96-427b-8425-6909bb291380.png', null, '1', '1', '2022-12-27 20:54:15', '2022-12-27 21:17:47', '0');
INSERT INTO `app_category` VALUES ('9', '电脑', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/7e6c9ac6-75d0-4aa1-b259-472ab504612a.png', null, '1', '1', '2022-12-28 21:35:35', null, '0');
INSERT INTO `app_category` VALUES ('10', '家电', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/caabdabe-d237-4a63-ad12-34a04d492d41.png', null, '1', '1', '2022-12-28 21:35:45', null, '0');
INSERT INTO `app_category` VALUES ('11', '99', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/606b11cc-0435-4873-90a7-47919bcdf5eb.png', '99', '1', '1', '2024-01-22 13:08:30', null, '1');

-- ----------------------------
-- Table structure for app_comment
-- ----------------------------
DROP TABLE IF EXISTS `app_comment`;
CREATE TABLE `app_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `order_item_id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `content` varchar(500) NOT NULL,
  `score` int(11) NOT NULL,
  `images` varchar(1255) DEFAULT NULL,
  `reply` varchar(500) DEFAULT NULL,
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  `is_delete` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app_comment
-- ----------------------------
INSERT INTO `app_comment` VALUES ('3', '1', '20', '111', '106', '测试评论，关注公众号MarkerHub', '4', null, null, '2023-03-09 15:54:39', null, '0');

-- ----------------------------
-- Table structure for app_order
-- ----------------------------
DROP TABLE IF EXISTS `app_order`;
CREATE TABLE `app_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `total_amount` decimal(10,0) NOT NULL COMMENT '总价',
  `freight` decimal(10,0) NOT NULL COMMENT '运费',
  `order_status` int(11) NOT NULL COMMENT '订单状态：0->待付款；1->待发货；2->待收货；3-已完成；4->已取消；5-申请退款，6->已退款，7->退款失败',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_trade_no` varchar(32) DEFAULT NULL COMMENT '支付交易号',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `receive_time` datetime DEFAULT NULL COMMENT '确认收货时间',
  `complete_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `refund_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `receiver_name` varchar(255) NOT NULL COMMENT '收货人',
  `receiver_address` varchar(255) NOT NULL,
  `receiver_tel` varchar(255) NOT NULL,
  `receiver_area_code` varchar(255) NOT NULL,
  `receiver_postal_code` varchar(255) DEFAULT NULL,
  `delivery_company` varchar(255) DEFAULT NULL,
  `delivery_sn` varchar(255) DEFAULT NULL,
  `admin_note` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app_order
-- ----------------------------
INSERT INTO `app_order` VALUES ('94', 'D22122901462519', '1', '1499', '0', '4', null, null, null, null, '2022-12-28 18:16:25', '2022-12-28 18:16:25', '吕小布', '广东省广州市天河区龙洞小区15号', '18825048653', '440106', '524500', null, null, '管理员2：订单超时系统自动取消', '注意消毒', '0', '2022-12-29 01:46:25', '2022-12-29 02:16:26');
INSERT INTO `app_order` VALUES ('95', 'D22123016220782', '1', '1499', '0', '1', '2022-12-30 16:24:28', null, null, null, '2022-12-30 08:24:31', '2022-12-30 08:24:31', '吕小布', '广东省广州市天河区龙洞小区15号', '18825048653', '440106', '524500', null, null, null, '这是啥', '0', '2022-12-30 16:22:12', null);
INSERT INTO `app_order` VALUES ('96', 'D23010423358366', '1', '1499', '0', '4', null, null, null, null, '2023-01-04 16:05:16', '2023-01-04 16:05:16', '吕小布', '广东省广州市天河区龙洞小区15号', '18825048653', '440106', '524500', null, null, '管理员2：订单超时系统自动取消', '', '0', '2023-01-04 23:35:16', '2023-01-05 00:05:16');
INSERT INTO `app_order` VALUES ('97', 'D23020412307988', '1', '1499', '0', '4', null, null, null, null, '2023-02-04 05:00:57', '2023-02-04 05:00:57', '吕小布', '广东省广州市天河区龙洞小区15号', '18825048653', '440106', '524500', null, null, '管理员2：订单超时系统自动取消', '', '0', '2023-02-04 12:30:57', '2023-02-04 13:00:57');
INSERT INTO `app_order` VALUES ('98', 'D23020413458604', '1', '2998', '0', '4', null, null, null, null, '2023-02-04 06:15:06', '2023-02-04 06:15:06', '吕小布', '广东省广州市天河区龙洞小区15号', '18825048653', '440106', '524500', null, null, '管理员2：订单超时系统自动取消', '', '0', '2023-02-04 13:45:06', '2023-02-04 14:15:07');
INSERT INTO `app_order` VALUES ('99', 'D23020413489165', '1', '1499', '0', '4', null, null, null, null, '2023-02-04 06:18:15', '2023-02-04 06:18:15', '吕小布', '广东省广州市天河区龙洞小区15号', '18825048653', '440106', '524500', null, null, '管理员2：订单超时系统自动取消', '', '0', '2023-02-04 13:48:15', '2023-02-04 14:18:15');
INSERT INTO `app_order` VALUES ('100', 'D23020413566684', '1', '2199', '0', '4', null, null, null, null, '2023-02-04 06:26:46', '2023-02-04 06:26:46', '吕小布', '广东省广州市天河区龙洞小区15号', '18825048653', '440106', '524500', null, null, '管理员2：订单超时系统自动取消', '', '0', '2023-02-04 13:56:46', '2023-02-04 14:26:47');
INSERT INTO `app_order` VALUES ('101', 'D23021516372253', '1', '419', '0', '4', null, null, null, null, '2023-02-15 09:07:40', '2023-02-15 09:07:40', '吕小布', '广东省广州市天河区龙洞小区15号', '18825048653', '440106', '524500', null, null, '管理员2：订单超时系统自动取消', '', '0', '2023-02-15 16:37:40', '2023-02-15 17:07:40');
INSERT INTO `app_order` VALUES ('102', 'D23021519401657', '1', '419', '0', '4', null, null, null, null, '2023-02-15 12:10:52', '2023-02-15 12:10:52', '吕小布', '广东省广州市天河区龙洞小区15号', '18825048653', '440106', '524500', null, null, '管理员2：订单超时系统自动取消', '', '0', '2023-02-15 19:40:52', '2023-02-15 20:10:53');
INSERT INTO `app_order` VALUES ('103', 'D23030416228417', '1', '1499', '0', '0', null, null, null, null, null, null, '吕小布', '龙洞小区15号', '18825048653', '440106', '524500', null, null, null, null, '0', '2023-03-04 16:22:58', null);
INSERT INTO `app_order` VALUES ('104', 'D23030523180479', '1', '1499', '0', '0', null, null, null, null, null, null, '吕小布', '龙洞小区15号', '18825048653', '440106', '524500', null, null, null, null, '0', '2023-03-05 23:18:30', null);
INSERT INTO `app_order` VALUES ('105', 'D23030523183267', '1', '1499', '0', '3', null, null, null, null, '2023-03-09 15:57:03', '2023-03-09 15:57:03', '吕小布', '龙洞小区15号', '18825048653', '440106', '524500', null, null, null, null, '0', '2023-03-05 23:18:35', null);
INSERT INTO `app_order` VALUES ('106', 'D23030610484047', '1', '1499', '0', '3', null, null, null, null, '2023-03-09 15:51:24', '2023-03-09 15:51:24', '吕小布', '龙洞小区15号', '18825048653', '440106', '524500', null, null, null, null, '0', '2023-03-06 10:48:18', null);
INSERT INTO `app_order` VALUES ('107', 'D23030611090184', '1', '1499', '0', '4', null, null, null, null, '2023-03-07 13:39:45', '2023-03-07 13:39:45', '吕小布', '龙洞小区15号', '18825048653', '440106', '524500', null, null, null, null, '0', '2023-03-06 11:09:30', '2023-03-07 13:39:45');
INSERT INTO `app_order` VALUES ('108', 'D24012113057933', '4', '9499', '0', '4', null, null, null, null, '2024-01-21 13:09:40', '2024-01-21 13:09:40', 'eeee', '天津市天津市和平区22222222222222', '18829302222', '120101', null, null, null, null, '热热热热热热热热', '1', '2024-01-21 13:05:35', '2024-01-21 13:09:37');
INSERT INTO `app_order` VALUES ('109', 'D24012113113781', '4', '3398', '0', '4', null, null, null, null, '2024-01-22 12:40:45', '2024-01-22 12:40:45', 'eeee6', '天津市天津市和平区2222222222222266', '18829302226', '120101', null, null, null, '管理员：2 ：关闭；null', '556565', '0', '2024-01-21 13:11:29', '2024-01-22 12:40:46');
INSERT INTO `app_order` VALUES ('110', 'D24012212511022', '4', '1499', '0', '4', null, null, null, null, '2024-01-22 13:09:43', '2024-01-22 13:09:43', 'eeee6', '天津市天津市和平区2222222222222266', '18829302226', '120101', null, null, null, '管理员：1 ：关闭；null', '', '0', '2024-01-22 12:51:36', '2024-01-22 13:09:43');
INSERT INTO `app_order` VALUES ('111', 'D24012213117801', '4', '2199', '0', '0', null, null, null, null, null, null, 'eeee6', '天津市天津市和平区2222222222222266', '18829302226', '120101', null, null, null, null, '', '0', '2024-01-22 13:11:16', null);

-- ----------------------------
-- Table structure for app_order_item
-- ----------------------------
DROP TABLE IF EXISTS `app_order_item`;
CREATE TABLE `app_order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `sku_id` bigint(20) NOT NULL,
  `sku` varchar(255) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `product_sn` varchar(255) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_image` varchar(255) NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT '1',
  `comment_id` bigint(20) DEFAULT NULL,
  `comment_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app_order_item
-- ----------------------------
INSERT INTO `app_order_item` VALUES ('99', '94', '-1', '默认规格', '8', '20', 'PD2212282210196857', '荣耀X30 骁龙6nm疾速5G芯 66W超级快充 120Hz全视屏 全网通版 8GB+128GB 魅海蓝', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/d2cbb81b-9e8c-4c7f-9595-ff2716f4c2dc.png', '1499', '1', null, null, '2022-12-29 01:46:25', null);
INSERT INTO `app_order_item` VALUES ('100', '95', '-1', '默认规格', '8', '20', 'PD2212282210196857', '荣耀X30 骁龙6nm疾速5G芯 66W超级快充 120Hz全视屏 全网通版 8GB+128GB 魅海蓝', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/d2cbb81b-9e8c-4c7f-9595-ff2716f4c2dc.png', '1499', '1', null, null, '2022-12-30 16:22:12', null);
INSERT INTO `app_order_item` VALUES ('101', '96', '-1', '默认规格', '8', '20', 'PD2212282210196857', '荣耀X30 骁龙6nm疾速5G芯 66W超级快充 120Hz全视屏 全网通版 8GB+128GB 魅海蓝', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/d2cbb81b-9e8c-4c7f-9595-ff2716f4c2dc.png', '1499', '1', null, null, '2023-01-04 23:35:16', null);
INSERT INTO `app_order_item` VALUES ('102', '97', '-1', '默认规格', '8', '20', 'PD2212282210196857', '荣耀X30 骁龙6nm疾速5G芯 66W超级快充 120Hz全视屏 全网通版 8GB+128GB 魅海蓝', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/d2cbb81b-9e8c-4c7f-9595-ff2716f4c2dc.png', '1499', '1', null, null, '2023-02-04 12:30:57', null);
INSERT INTO `app_order_item` VALUES ('103', '98', '-1', '默认规格', '8', '20', 'PD2212282210196857', '荣耀X30 骁龙6nm疾速5G芯 66W超级快充 120Hz全视屏 全网通版 8GB+128GB 魅海蓝', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/d2cbb81b-9e8c-4c7f-9595-ff2716f4c2dc.png', '1499', '2', null, null, '2023-02-04 13:45:07', null);
INSERT INTO `app_order_item` VALUES ('104', '99', '-1', '默认规格', '8', '20', 'PD2212282210196857', '荣耀X30 骁龙6nm疾速5G芯 66W超级快充 120Hz全视屏 全网通版 8GB+128GB 魅海蓝', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/d2cbb81b-9e8c-4c7f-9595-ff2716f4c2dc.png', '1499', '1', null, null, '2023-02-04 13:48:15', null);
INSERT INTO `app_order_item` VALUES ('105', '100', '143', '1.5匹;温湿双控', '10', '25', 'PD2212282229085769', '小米（MI）1.5匹 新能效 变频冷暖 智能自清洁 壁挂式卧室空调挂机 KFR-35GW/N1A3 以旧换新', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/e6269599-c2d6-457a-90d8-1804b265fafa.png', '2199', '1', null, null, '2023-02-04 13:56:46', null);
INSERT INTO `app_order_item` VALUES ('106', '101', '-1', '默认规格', '10', '27', 'PD2212282242160057', '京东超市 米家 小米饭煲智能电饭煲3L 新一代微压IH OLED智显小黑屏 米饭软硬可调IOT电饭煲电饭锅', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/382c21d3-bfee-4615-b5ec-f8f99af68cda.png', '419', '1', null, null, '2023-02-15 16:37:40', null);
INSERT INTO `app_order_item` VALUES ('107', '102', '-1', '默认规格', '10', '27', 'PD2212282242160057', '京东超市 米家 小米饭煲智能电饭煲3L 新一代微压IH OLED智显小黑屏 米饭软硬可调IOT电饭煲电饭锅', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/382c21d3-bfee-4615-b5ec-f8f99af68cda.png', '419', '1', null, null, '2023-02-15 19:40:53', null);
INSERT INTO `app_order_item` VALUES ('108', '103', '-1', '默认规格', '8', '20', 'PD2212282210196857', '荣耀X30 骁龙6nm疾速5G芯 66W超级快充 120Hz全视屏 全网通版 8GB+128GB 魅海蓝', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/d2cbb81b-9e8c-4c7f-9595-ff2716f4c2dc.png', '1499', '1', null, null, '2023-03-04 16:22:58', null);
INSERT INTO `app_order_item` VALUES ('109', '104', '-1', '默认规格', '8', '20', 'PD2212282210196857', '荣耀X30 骁龙6nm疾速5G芯 66W超级快充 120Hz全视屏 全网通版 8GB+128GB 魅海蓝', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/d2cbb81b-9e8c-4c7f-9595-ff2716f4c2dc.png', '1499', '1', null, null, '2023-03-05 23:18:30', null);
INSERT INTO `app_order_item` VALUES ('110', '105', '-1', '默认规格', '8', '20', 'PD2212282210196857', '荣耀X30 骁龙6nm疾速5G芯 66W超级快充 120Hz全视屏 全网通版 8GB+128GB 魅海蓝', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/d2cbb81b-9e8c-4c7f-9595-ff2716f4c2dc.png', '1499', '1', null, null, '2023-03-05 23:18:35', null);
INSERT INTO `app_order_item` VALUES ('111', '106', '-1', '默认规格', '8', '20', 'PD2212282210196857', '荣耀X30 骁龙6nm疾速5G芯 66W超级快充 120Hz全视屏 全网通版 8GB+128GB 魅海蓝', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/d2cbb81b-9e8c-4c7f-9595-ff2716f4c2dc.png', '1499', '1', '3', '2023-03-09 15:54:39', '2023-03-06 10:48:18', null);
INSERT INTO `app_order_item` VALUES ('112', '107', '-1', '默认规格', '8', '20', 'PD2212282210196857', '荣耀X30 骁龙6nm疾速5G芯 66W超级快充 120Hz全视屏 全网通版 8GB+128GB 魅海蓝', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/d2cbb81b-9e8c-4c7f-9595-ff2716f4c2dc.png', '1499', '1', null, null, '2023-03-06 11:09:30', null);
INSERT INTO `app_order_item` VALUES ('113', '108', '-1', '默认规格', '7', '22', 'PD2212282220152122', '华为Vision智慧屏 86英寸超薄全面屏 4K超高清120Hz高刷智能液晶平板电视机 HD86KHAA', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/2534d6f0-728d-4ab1-87fe-7c28b0844795.png', '9499', '1', null, null, '2024-01-21 13:05:35', null);
INSERT INTO `app_order_item` VALUES ('114', '109', '141', '大一匹;温湿双控', '10', '25', 'PD2212282229085769', '小米（MI）1.5匹 新能效 变频冷暖 智能自清洁 壁挂式卧室空调挂机 KFR-35GW/N1A3 以旧换新', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/e6269599-c2d6-457a-90d8-1804b265fafa.png', '1899', '1', null, null, '2024-01-21 13:11:29', null);
INSERT INTO `app_order_item` VALUES ('115', '109', '-1', '默认规格', '8', '20', 'PD2212282210196857', '荣耀X30 骁龙6nm疾速5G芯 66W超级快充 120Hz全视屏 全网通版 8GB+128GB 魅海蓝1', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/d2cbb81b-9e8c-4c7f-9595-ff2716f4c2dc.png', '1499', '1', null, null, '2024-01-21 13:11:29', null);
INSERT INTO `app_order_item` VALUES ('116', '110', '-1', '默认规格', '8', '20', 'PD2212282210196857', '荣耀X30 骁龙6nm疾速5G芯 66W超级快充 120Hz全视屏 全网通版 8GB+128GB 魅海蓝1', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/d2cbb81b-9e8c-4c7f-9595-ff2716f4c2dc.png', '1499', '1', null, null, '2024-01-22 12:51:36', null);
INSERT INTO `app_order_item` VALUES ('117', '111', '143', '1.5匹;温湿双控', '10', '25', 'PD2212282229085769', '小米（MI）1.5匹 新能效 变频冷暖 智能自清洁 壁挂式卧室空调挂机 KFR-35GW/N1A3 以旧换新', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/e6269599-c2d6-457a-90d8-1804b265fafa.png', '2199', '1', null, null, '2024-01-22 13:11:16', null);

-- ----------------------------
-- Table structure for app_product
-- ----------------------------
DROP TABLE IF EXISTS `app_product`;
CREATE TABLE `app_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `pics` text,
  `price` decimal(10,2) NOT NULL,
  `cost` decimal(10,2) NOT NULL,
  `stock` int(255) NOT NULL COMMENT '库存',
  `keywords` varchar(255) DEFAULT NULL,
  `detail` longtext,
  `is_on_sale` tinyint(1) NOT NULL,
  `is_new` tinyint(1) NOT NULL,
  `is_top` tinyint(1) DEFAULT NULL,
  `is_hot` tinyint(1) NOT NULL,
  `sort_order` int(4) DEFAULT NULL,
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  `sale` int(11) NOT NULL DEFAULT '0',
  `comments` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app_product
-- ----------------------------
INSERT INTO `app_product` VALUES ('20', 'PD2212282210196857', '荣耀X30 骁龙6nm疾速5G芯 66W超级快充 120Hz全视屏 全网通版 8GB+128GB 魅海蓝', '8', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/d2cbb81b-9e8c-4c7f-9595-ff2716f4c2dc.png', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/d2cbb81b-9e8c-4c7f-9595-ff2716f4c2dc.png;https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/64561c83-9046-4c44-a657-9a459f43274e.png', '1499.00', '0.00', '994', '荣耀、手机、快充', '![Description](https://img30.360buyimg.com/sku/jfs/t1/223705/29/777/243545/61c054e8Ed1654469/db003f92c59da2e4.jpg)', '1', '1', '1', '1', '1', '2022-12-28 22:10:19', '2024-01-22 13:07:45', '0', '0', '3');
INSERT INTO `app_product` VALUES ('21', 'PD2212282217411710', 'Apple iPhone 14 (A2884) 128GB 星光色 支持移动联通电信5G 双卡双待手机', '8', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/895b66e5-d429-489f-b7d0-88f5d60aa4f2.png', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/895b66e5-d429-489f-b7d0-88f5d60aa4f2.png;https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/f35eee62-cdf7-4b70-b6ba-e21f0a788edd.png;https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/ce922cd6-d198-4248-98db-b0fc10c2268a.png', '5999.00', '0.00', '999', '苹果手机 iphone 手机', '![Description](https://img10.360buyimg.com/cms/jfs/t1/115449/27/29260/131972/631902deE0c0f8dab/9288fcd9d35fddde.jpg)', '1', '0', '1', '1', '1', '2022-12-28 22:17:42', null, '0', '0', '0');
INSERT INTO `app_product` VALUES ('22', 'PD2212282220152122', '华为Vision智慧屏 86英寸超薄全面屏 4K超高清120Hz高刷智能液晶平板电视机 HD86KHAA', '7', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/2534d6f0-728d-4ab1-87fe-7c28b0844795.png', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/2534d6f0-728d-4ab1-87fe-7c28b0844795.png', '9499.00', '0.00', '999', '电视 华为 智慧屏 智能电视', '![Description](https://img30.360buyimg.com/sku/jfs/t1/181038/28/29660/89477/6334fd2dEb0916f4f/5f1f879451359452.jpg)', '1', '1', '1', '0', '1', '2022-12-28 22:20:15', null, '0', '0', '0');
INSERT INTO `app_product` VALUES ('23', 'PD2212282223374033', '海信 Vidda 55V1F-R 55英寸 4K超高清 超薄电视 全面屏电视 智慧屏 1.5G+8G 游戏巨幕智能液晶电视以旧换新', '7', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/3838262c-d084-4d62-ba5b-6890e5373e40.png', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/3838262c-d084-4d62-ba5b-6890e5373e40.png;https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/8ac831f0-3252-4442-befc-10c58919010c.png', '1299.00', '0.00', '999', '电视 海信 超薄 4K', '![Description](https://img30.360buyimg.com/sku/jfs/t1/132481/2/31361/239807/63720664E55b3f1bd/027be3236b7ce554.jpg)', '1', '1', '0', '0', '1', '2022-12-28 22:23:38', null, '0', '0', '0');
INSERT INTO `app_product` VALUES ('24', 'PD2212282226038700', '博世（BOSCH）【灰阶双循环】630升变频大容量精控恒温微嵌入式家用对开双开门电冰箱KAN98V123C 以旧换新', '10', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/fae60a1e-d4c4-46a1-ac5f-3b748c13afcc.png', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/fae60a1e-d4c4-46a1-ac5f-3b748c13afcc.png', '10999.00', '0.00', '999', '冰箱 家电', '![Description](https://img30.360buyimg.com/sku/jfs/t1/130179/34/23154/426982/621f20a6E7aa8b04a/582f0cbea82854ec.jpg)\n![Description](https://img30.360buyimg.com/sku/jfs/t1/211256/26/18392/415425/621f2292E81bf8bf5/5f6c6bfa0dcbcaf0.jpg)', '1', '1', '1', '1', '1', '2022-12-28 22:26:04', null, '1', '0', '0');
INSERT INTO `app_product` VALUES ('25', 'PD2212282229085769', '小米（MI）1.5匹 新能效 变频冷暖 智能自清洁 壁挂式卧室空调挂机 KFR-35GW/N1A3 以旧换新', '10', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/e6269599-c2d6-457a-90d8-1804b265fafa.png', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/e6269599-c2d6-457a-90d8-1804b265fafa.png', '1899.00', '0.00', '999', '空调 小米', '![Description](https://img30.360buyimg.com/sku/jfs/t1/53411/2/17079/119535/6347b4deE591bd8e9/65d7f4bb88c20a2f.jpg)\n\n![Description](https://img30.360buyimg.com/sku/jfs/t1/30897/23/17759/135921/6347b4deEa39149b8/38721a18e1baa2a9.jpg)', '1', '0', '1', '1', '1', '2022-12-28 22:29:09', null, '0', '0', '0');
INSERT INTO `app_product` VALUES ('26', 'PD2212282237283779', '外星人（alienware） 2022全新x14 R1轻薄高性能本14英寸游戏本笔记本电脑12代酷睿 推荐 1743 12代i7/16G/RTX3050 官方标配', '9', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/9fb14ff2-40d6-40cc-9a4f-155d009128aa.png', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/9fb14ff2-40d6-40cc-9a4f-155d009128aa.png', '12469.00', '0.00', '999', '外星人', '![Description](http://img30.360buyimg.com/popWareDetail/jfs/t1/71663/33/17727/60330/62738e34Ecec80389/61f9347e473166b0.jpg)\n\n![Description](http://img30.360buyimg.com/popWareDetail/jfs/t1/68179/17/17724/119599/62738e36E3bdea250/1b9ff9dfd946fb1e.jpg)', '0', '0', '1', '0', '1', '2022-12-28 22:37:29', null, '0', '0', '0');
INSERT INTO `app_product` VALUES ('27', 'PD2212282242160057', '京东超市 米家 小米饭煲智能电饭煲3L 新一代微压IH OLED智显小黑屏 米饭软硬可调IOT电饭煲电饭锅', '10', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/382c21d3-bfee-4615-b5ec-f8f99af68cda.png', 'https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/382c21d3-bfee-4615-b5ec-f8f99af68cda.png', '419.00', '0.00', '999', '电饭煲', '![Description](https://img30.360buyimg.com/sku/jfs/t1/221230/13/15288/437535/62551c85E882bcc66/560122e84dd8db29.jpg)', '0', '0', '1', '1', '1', '2022-12-28 22:42:17', null, '0', '0', '0');

-- ----------------------------
-- Table structure for app_refund
-- ----------------------------
DROP TABLE IF EXISTS `app_refund`;
CREATE TABLE `app_refund` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `order_sn` varchar(255) NOT NULL,
  `sn` varchar(255) NOT NULL,
  `refund_amount` decimal(32,0) NOT NULL,
  `method` varchar(255) NOT NULL COMMENT '退货方式，0-自行寄回，1-上门取货',
  `reason` varchar(255) NOT NULL COMMENT '退款理由',
  `description` varchar(1000) DEFAULT NULL,
  `images` varchar(1000) DEFAULT NULL,
  `operator_id` bigint(20) DEFAULT NULL,
  `operate_remark` varchar(500) DEFAULT NULL,
  `operate_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `receipt_remark` varchar(500) DEFAULT NULL,
  `refund_status` int(4) NOT NULL DEFAULT '0' COMMENT '申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝',
  `refund_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app_refund
-- ----------------------------

-- ----------------------------
-- Table structure for app_sku_stock
-- ----------------------------
DROP TABLE IF EXISTS `app_sku_stock`;
CREATE TABLE `app_sku_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(32) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `stock` int(5) NOT NULL,
  `sku` varchar(255) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app_sku_stock
-- ----------------------------
INSERT INTO `app_sku_stock` VALUES ('140', '25', '1499.00', '66', '大一匹;单冷空调', '2022-12-29 21:23:02', null, '0');
INSERT INTO `app_sku_stock` VALUES ('141', '25', '1899.00', '44', '大一匹;温湿双控', '2022-12-29 21:23:02', null, '0');
INSERT INTO `app_sku_stock` VALUES ('142', '25', '1999.00', '65', '1.5匹;单冷空调', '2022-12-29 21:23:02', null, '0');
INSERT INTO `app_sku_stock` VALUES ('143', '25', '2199.00', '87', '1.5匹;温湿双控', '2022-12-29 21:23:02', null, '0');
INSERT INTO `app_sku_stock` VALUES ('144', '25', '1899.00', '0', '2匹;单冷空调', '2022-12-29 21:23:02', null, '0');
INSERT INTO `app_sku_stock` VALUES ('145', '25', '1899.00', '0', '2匹;温湿双控', '2022-12-29 21:23:02', null, '0');
INSERT INTO `app_sku_stock` VALUES ('146', '20', '1499.00', '99', '5.8', '2024-01-22 13:07:57', null, '0');
INSERT INTO `app_sku_stock` VALUES ('147', '20', '1499.00', '99', '6', '2024-01-22 13:07:57', null, '0');
INSERT INTO `app_sku_stock` VALUES ('148', '20', '1499.00', '0', '6.1', '2024-01-22 13:07:57', null, '0');
INSERT INTO `app_sku_stock` VALUES ('149', '20', '1499.00', '99', '6.5', '2024-01-22 13:07:57', null, '0');
INSERT INTO `app_sku_stock` VALUES ('150', '20', '1499.00', '0', '6.8', '2024-01-22 13:07:57', null, '0');

-- ----------------------------
-- Table structure for app_specification
-- ----------------------------
DROP TABLE IF EXISTS `app_specification`;
CREATE TABLE `app_specification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `options` varchar(255) DEFAULT NULL COMMENT '可选项',
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  `sort_order` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app_specification
-- ----------------------------
INSERT INTO `app_specification` VALUES ('6', null, '内存', '4G;8G;16G', '2022-12-28 00:35:45', '2024-01-22 13:08:11', '2');
INSERT INTO `app_specification` VALUES ('7', null, '尺寸', '5.8;6;6.1;6.5;6.8', '2022-12-28 21:29:44', null, '1');
INSERT INTO `app_specification` VALUES ('8', null, '匹数', '大一匹;1.5匹;2匹', '2022-12-29 21:19:59', null, '1');

-- ----------------------------
-- Table structure for app_specification_value
-- ----------------------------
DROP TABLE IF EXISTS `app_specification_value`;
CREATE TABLE `app_specification_value` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL,
  `spec` varchar(255) NOT NULL,
  `value` varchar(32) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  `sort_order` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app_specification_value
-- ----------------------------
INSERT INTO `app_specification_value` VALUES ('32', '25', '匹数', '大一匹;1.5匹;2匹', '2022-12-29 21:23:02', null, null);
INSERT INTO `app_specification_value` VALUES ('33', '25', '冷暖类型', '单冷空调;温湿双控', '2022-12-29 21:23:02', null, null);
INSERT INTO `app_specification_value` VALUES ('34', '20', '尺寸', '5.8;6;6.1;6.5;6.8', '2024-01-22 13:07:57', null, null);

-- ----------------------------
-- Table structure for app_user
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(32) NOT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `is_delete` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `app_user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of app_user
-- ----------------------------
INSERT INTO `app_user` VALUES ('1', 'test', '21218cca77804d2ba1922c33e0151105', null, '2022-03-15 13:16:23', null, 'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/zhuawaba/5a9f48118166308daba8b6da7e466aab.jpg?t=10:19:17', '2024-01-16 22:10:58', '0');
INSERT INTO `app_user` VALUES ('2', 'test999999', '21218cca77804d2ba1922c33e0151105', null, '2023-02-25 20:58:55', null, null, null, '0');
INSERT INTO `app_user` VALUES ('3', 'testhhh', '21218cca77804d2ba1922c33e0151105', null, '2024-01-17 14:48:13', null, 'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/zhuawaba/5a9f48118166308daba8b6da7e466aab.jpg', '2024-01-17 14:50:52', '0');
INSERT INTO `app_user` VALUES ('4', 'test555', '96e79218965eb72c92a549dd5a330112', null, '2024-01-21 12:53:25', null, 'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/zhuawaba/5a9f48118166308daba8b6da7e466aab.jpg', '2024-01-21 12:53:53', '0');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `title` varchar(64) NOT NULL,
  `path` varchar(255) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(255) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `component` varchar(255) DEFAULT NULL,
  `type` int(5) NOT NULL COMMENT '类型     0：目录   1：菜单   2：按钮',
  `icon` varchar(32) DEFAULT NULL COMMENT '菜单图标',
  `sort_order` int(11) DEFAULT NULL COMMENT '排序',
  `created` datetime NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`title`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', '/sys', 'sys:manage', null, '0', 'el-icon-s-operation', '3', '2022-04-27 16:03:59', '0', '2022-07-26 11:12:27');
INSERT INTO `sys_menu` VALUES ('2', '1', '用户管理', '/sys/user', 'sys:user:list', 'sys/user/index.vue', '1', 'el-icon-s-custom', '1', '2021-01-15 19:03:45', '0', '2021-01-15 19:03:48');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', '/sys/role', 'sys:role:list', 'sys/role/index.vue', '1', 'el-icon-rank', '2', '2021-01-15 19:03:45', '0', '2021-01-15 19:03:48');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', '/sys/menu', 'sys:menu:list', 'sys/menu/index.vue', '1', 'el-icon-menu', '3', '2021-01-15 19:03:45', '0', '2021-01-15 19:03:48');
INSERT INTO `sys_menu` VALUES ('5', '0', '系统工具', null, 'sys:tools', null, '0', 'el-icon-s-tools', '2', '2021-01-15 19:06:11', '0', null);
INSERT INTO `sys_menu` VALUES ('6', '5', '数字字典', '/sys/dict', 'sys:dict:list', 'sys/dict/index.vue', '1', 'el-icon-s-order', '1', '2021-01-15 19:07:18', '0', '2021-01-18 16:32:13');
INSERT INTO `sys_menu` VALUES ('7', '3', '添加角色', null, 'sys:role:save', null, '2', null, '1', '2021-01-15 23:02:25', '0', '2021-01-17 21:53:14');
INSERT INTO `sys_menu` VALUES ('9', '2', '添加用户', null, 'sys:user:save', null, '2', null, '1', '2021-01-17 21:48:32', '0', null);
INSERT INTO `sys_menu` VALUES ('10', '2', '修改用户', null, 'sys:user:update', null, '2', null, '2', '2021-01-17 21:49:03', '0', '2021-01-17 21:53:04');
INSERT INTO `sys_menu` VALUES ('11', '2', '删除用户', null, 'sys:user:delete', null, '2', null, '3', '2021-01-17 21:49:21', '0', null);
INSERT INTO `sys_menu` VALUES ('12', '2', '分配角色', null, 'sys:user:role', null, '2', null, '4', '2021-01-17 21:49:58', '0', null);
INSERT INTO `sys_menu` VALUES ('13', '2', '重置密码', null, 'sys:user:repass', null, '2', null, '5', '2021-01-17 21:50:36', '0', null);
INSERT INTO `sys_menu` VALUES ('14', '3', '修改角色', null, 'sys:role:save', null, '2', null, '2', '2021-01-17 21:51:14', '0', null);
INSERT INTO `sys_menu` VALUES ('15', '3', '删除角色', null, 'sys:role:delete', null, '2', null, '3', '2021-01-17 21:51:39', '0', null);
INSERT INTO `sys_menu` VALUES ('16', '3', '分配权限', null, 'sys:role:perm', null, '2', null, '5', '2021-01-17 21:52:02', '0', null);
INSERT INTO `sys_menu` VALUES ('17', '4', '添加菜单', null, 'sys:menu:save', null, '2', null, '1', '2021-01-17 21:53:53', '0', '2021-01-17 21:55:28');
INSERT INTO `sys_menu` VALUES ('18', '4', '修改菜单', null, 'sys:menu:update', null, '2', null, '2', '2021-01-17 21:56:12', '0', null);
INSERT INTO `sys_menu` VALUES ('19', '4', '删除菜单', null, 'sys:menu:delete', null, '2', null, '3', '2021-01-17 21:56:36', '0', null);
INSERT INTO `sys_menu` VALUES ('20', '5', '测试菜单', '/sys/ninn', 'sys:test222333', null, '1', null, '1', '2022-04-27 11:34:31', '0', '2022-05-18 15:54:31');
INSERT INTO `sys_menu` VALUES ('29', '0', '商品管理', null, 'app:product', null, '0', null, '10', '2022-07-26 11:00:30', '0', '2022-07-26 11:09:20');
INSERT INTO `sys_menu` VALUES ('30', '0', '订单管理', null, 'app:order', null, '0', null, '7', '2022-07-26 11:13:27', '0', null);
INSERT INTO `sys_menu` VALUES ('31', '29', '广告管理', '/admin/carousel', 'admin:carousel', 'admin/carousel/index.vue', '1', null, '6', '2022-07-26 11:21:31', '0', '2022-07-27 14:30:08');
INSERT INTO `sys_menu` VALUES ('33', '29', '商品列表', '/admin/product', 'admin:product', 'admin/product/index.vue', '1', null, '10', '2022-07-26 11:23:27', '0', null);
INSERT INTO `sys_menu` VALUES ('34', '29', '分类管理', '/admin/category', 'admin:category', 'admin/category/index.vue', '1', null, '8', '2022-07-26 11:24:07', '0', null);
INSERT INTO `sys_menu` VALUES ('35', '30', '订单列表', '/admin/order', 'admin:order', 'admin/order/index.vue', '1', null, '10', '2022-07-26 11:25:39', '0', null);
INSERT INTO `sys_menu` VALUES ('36', '30', '退货处理', '/admin/refund', 'admin:refund', 'admin/refund/index.vue', '1', null, '8', '2022-07-26 11:26:53', '0', null);
INSERT INTO `sys_menu` VALUES ('37', '33', '商品编辑', '/admin/product/edit', 'admin:product:edit', 'admin/product/edit.vue', '1', null, '1', '2022-07-28 14:43:10', '0', null);
INSERT INTO `sys_menu` VALUES ('38', '33', '规格编辑', '/admin/product/sku', 'admin:product:sku', 'admin/product/sku.vue', '1', null, '1', '2022-08-02 09:49:39', '0', null);
INSERT INTO `sys_menu` VALUES ('39', '29', '规格管理', '/admin/spec', 'admin:spec', 'admin/spec/index.vue', '1', null, '9', '2022-08-06 08:52:49', '0', '2022-12-23 00:24:25');
INSERT INTO `sys_menu` VALUES ('40', '35', '查看订单', '/admin/order/detail', 'admin:order:detail', 'admin/order/detail.vue', '1', null, '7', '2022-08-08 08:59:10', '0', '2022-08-08 09:03:42');
INSERT INTO `sys_menu` VALUES ('41', '36', '查看退货单', '/admin/refund/detail', 'admin:refund:detail', 'admin/refund/detail.vue', '1', null, '3', '2022-08-26 09:38:44', '0', null);
INSERT INTO `sys_menu` VALUES ('42', '-1', '顶级菜单', null, 'sys:top', null, '0', null, '1', '2022-05-06 10:35:12', '0', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `code` varchar(64) NOT NULL,
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING BTREE,
  UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('3', '普通用户', 'normal', '只有基本查看功能', '2021-01-04 10:09:14', '2021-04-15 00:43:14');
INSERT INTO `sys_role` VALUES ('6', '超级管理员', 'admin', '系统默认最高权限，不可以编辑和任意修改', '2021-01-16 13:29:03', '2021-01-17 15:50:45');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=261 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('251', '3', '29');
INSERT INTO `sys_role_menu` VALUES ('252', '3', '33');
INSERT INTO `sys_role_menu` VALUES ('253', '3', '39');
INSERT INTO `sys_role_menu` VALUES ('254', '3', '34');
INSERT INTO `sys_role_menu` VALUES ('255', '3', '31');
INSERT INTO `sys_role_menu` VALUES ('256', '3', '30');
INSERT INTO `sys_role_menu` VALUES ('257', '3', '35');
INSERT INTO `sys_role_menu` VALUES ('258', '3', '40');
INSERT INTO `sys_role_menu` VALUES ('259', '3', '36');
INSERT INTO `sys_role_menu` VALUES ('260', '3', '41');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `avatar` varchar(255) NOT NULL,
  `email` varchar(64) NOT NULL,
  `phone` varchar(64) DEFAULT NULL,
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `is_delete` int(5) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_USERNAME` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '21218cca77804d2ba1922c33e0151105', 'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/zhuawaba/5a9f48118166308daba8b6da7e466aab.jpg', '7834@qq.com', null, '2022-03-16 13:00:49', '2022-05-18 16:02:02', null, '0', '0');
INSERT INTO `sys_user` VALUES ('2', 'SystemAdmin', '21218cca77804d2ba1922c33e0151105', 'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/zhuawaba/5a9f48118166308daba8b6da7e466aab.jpg', '7834@qq.com', null, '2022-03-16 13:00:49', '2022-05-18 16:02:02', null, '0', '0');
INSERT INTO `sys_user` VALUES ('5', 'test', '21218cca77804d2ba1922c33e0151105', 'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg', 'test@qq.com', '1432323232', '2022-05-17 22:51:15', '2022-05-18 15:43:44', null, '0', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('20', '1', '6');
INSERT INTO `sys_user_role` VALUES ('21', '5', '3');
