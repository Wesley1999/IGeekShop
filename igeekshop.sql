/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : igeekshop

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 17/02/2020 18:51:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart_item
-- ----------------------------
DROP TABLE IF EXISTS `cart_item`;
CREATE TABLE `cart_item`  (
  `cart_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_id` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`cart_item_id`) USING BTREE,
  INDEX `fk6`(`product_id`) USING BTREE,
  INDEX `fk5`(`user_id`) USING BTREE,
  CONSTRAINT `fk5` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk6` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of cart_item
-- ----------------------------
INSERT INTO `cart_item` VALUES (20, '5436499219164a60ad40dc64ce740d72', 4, 2);
INSERT INTO `cart_item` VALUES (21, '5436499219164a60ad40dc64ce740d72', 2, 38);
INSERT INTO `cart_item` VALUES (22, '5436499219164a60ad40dc64ce740d72', 5, 2);
INSERT INTO `cart_item` VALUES (27, '5436499219164a60ad40dc64ce740d72', 31, 1);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '手机数码', '111111');
INSERT INTO `category` VALUES (2, '电脑办公', '222222');
INSERT INTO `category` VALUES (3, '家具家居', '33333');
INSERT INTO `category` VALUES (4, '鞋靴箱包', '44456346346');
INSERT INTO `category` VALUES (5, '图书音像', '6643565346');
INSERT INTO `category` VALUES (6, '母婴孕婴', '75646');
INSERT INTO `category` VALUES (7, '运动户外', '567547');
INSERT INTO `category` VALUES (12, '床上用品', '床上用品');
INSERT INTO `category` VALUES (17, '个人护理', '个人护理');
INSERT INTO `category` VALUES (20, '测试分类', '测试1');

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `order_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_id` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`order_item_id`) USING BTREE,
  INDEX `fk_0001`(`product_id`) USING BTREE,
  INDEX `fk_0002`(`order_id`) USING BTREE,
  CONSTRAINT `fk3` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk4` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES (6, '66e75b6f017e49648717a777b439e1ab', 2, 1);
INSERT INTO `order_item` VALUES (7, 'f0e79f65ab0b4fdbbe8262ba52955b24', 3, 1);
INSERT INTO `order_item` VALUES (8, 'f0e79f65ab0b4fdbbe8262ba52955b24', 5, 1);
INSERT INTO `order_item` VALUES (9, 'e068d2fc9e9846d887ec18c860446222', 4, 1);
INSERT INTO `order_item` VALUES (10, 'bdcacf3ded8c47ee999db29d88c00a6b', 11, 1);
INSERT INTO `order_item` VALUES (11, 'bdcacf3ded8c47ee999db29d88c00a6b', 1, 1);
INSERT INTO `order_item` VALUES (12, '79608031dc3a44f2a3d79f3280c69b3d', 9, 1);
INSERT INTO `order_item` VALUES (13, 'e4fde1841691469e9990bdcabb1ddd56', 34, 1);
INSERT INTO `order_item` VALUES (14, 'a116e65631f0451983884afef1371e7b', 3, 1);
INSERT INTO `order_item` VALUES (15, 'c2bd11d490cb48b3982ac909bd81d705', 4, 2);
INSERT INTO `order_item` VALUES (16, '8ee76a61601d440b9ff2c64c63343445', 34, 1);
INSERT INTO `order_item` VALUES (17, '335ad85985784411b443af7325e7c92c', 2, 2);
INSERT INTO `order_item` VALUES (18, '335ad85985784411b443af7325e7c92c', 1, 1);
INSERT INTO `order_item` VALUES (19, '335ad85985784411b443af7325e7c92c', 40, 1);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_time` datetime NOT NULL,
  `total_price` double(20, 2) NOT NULL,
  `recipient_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int(11) NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `fk1`(`user_id`) USING BTREE,
  CONSTRAINT `fk1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('335ad85985784411b443af7325e7c92c', '2019-08-01 10:19:12', 14696.00, '111', '15927140311', '111', 1, '8752480a18e34d77a66d7dcb2bb734a1');
INSERT INTO `orders` VALUES ('66e75b6f017e49648717a777b439e1ab', '2019-07-26 14:59:51', 2699.00, '王少刚', '13207123727', 'jhun', 1, '5436499219164a60ad40dc64ce740d72');
INSERT INTO `orders` VALUES ('79608031dc3a44f2a3d79f3280c69b3d', '2019-07-30 15:11:02', 2299.00, '1', '13207123727', '1', 1, '5436499219164a60ad40dc64ce740d72');
INSERT INTO `orders` VALUES ('8ee76a61601d440b9ff2c64c63343445', '2019-08-01 10:18:31', 4499.00, '111', '15927140311', '111', 1, '8752480a18e34d77a66d7dcb2bb734a1');
INSERT INTO `orders` VALUES ('a116e65631f0451983884afef1371e7b', '2019-07-30 15:45:09', 1499.00, '1', '13207123727', '1', 1, '5436499219164a60ad40dc64ce740d72');
INSERT INTO `orders` VALUES ('bdcacf3ded8c47ee999db29d88c00a6b', '2019-07-29 22:55:10', 10998.00, 'q', '13207123727', '1', 1, '5436499219164a60ad40dc64ce740d72');
INSERT INTO `orders` VALUES ('c2bd11d490cb48b3982ac909bd81d705', '2019-07-30 16:09:36', 3998.00, '123', '13207123727', '1', 1, '5436499219164a60ad40dc64ce740d72');
INSERT INTO `orders` VALUES ('e068d2fc9e9846d887ec18c860446222', '2019-07-29 20:40:50', 1999.00, '2', '13207123727', '234', 1, '5436499219164a60ad40dc64ce740d72');
INSERT INTO `orders` VALUES ('e4fde1841691469e9990bdcabb1ddd56', '2019-07-30 15:11:31', 4499.00, '123', '13207123727', '123', 1, '5436499219164a60ad40dc64ce740d72');
INSERT INTO `orders` VALUES ('f0e79f65ab0b4fdbbe8262ba52955b24', '2019-07-29 18:18:48', 3198.00, '132', '13207123727', '1', 1, '5436499219164a60ad40dc64ce740d72');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `market_price` double NOT NULL,
  `shop_price` double NOT NULL,
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_new` tinyint(1) NOT NULL,
  `is_hot` tinyint(1) NOT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `fk2`(`category_id`) USING BTREE,
  CONSTRAINT `fk2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '小米20', 2699, 2699, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0001.jpg', '小米20 全面屏游戏智能手机 64GB+8192GB 黑色 全网通6G 双卡双待', 0, 1, 20);
INSERT INTO `product` VALUES (2, '中兴 AXON', 2899, 2699, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0002.jpg', '中兴 AXON 天机 mini 压力屏版 B2015 华尔金 移动联通电信4G 双卡双待', 0, 1, 1);
INSERT INTO `product` VALUES (3, '华为荣耀6', 1599, 1499, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0003.jpg', '荣耀 6 (H60-L01) 3GB内存标准版 黑色 移动4G手机', 1, 0, 1);
INSERT INTO `product` VALUES (4, '联想 P1', 2199, 1999, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0004.jpg', '联想 P1 16G 伯爵金 移动联通4G手机充电5分钟，通话3小时！科技源于超越！品质源于沉淀！5000mAh大电池！高端商务佳配！', 0, 0, 1);
INSERT INTO `product` VALUES (5, '摩托罗拉 moto x（x+1）', 1799, 1699, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0005.jpg', '摩托罗拉 moto x（x+1）(XT1085) 32GB 天然竹 全网通4G手机11月11天！MOTO X震撼特惠来袭！1699元！带你玩转黑科技！天然材质，原生流畅系统！', 1, 1, 1);
INSERT INTO `product` VALUES (6, '魅族 MX5 16GB 银黑色', 1899, 1799, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0006.jpg', '魅族 MX5 16GB 银黑色 移动联通双4G手机 双卡双待送原厂钢化膜+保护壳+耳机！5.5英寸大屏幕，3G运行内存，2070万+500万像素摄像头！长期省才是真的省！', 1, 0, 1);
INSERT INTO `product` VALUES (7, '三星 Galaxy On7', 1499, 1398, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0007.jpg', '三星 Galaxy On7（G6000）昂小七 金色 全网通4G手机 双卡双待新品火爆抢购中！京东尊享千元良机！5.5英寸高清大屏！1300+500W像素！评价赢30元话费券！', 1, 1, 1);
INSERT INTO `product` VALUES (8, 'NUU NU5', 1288, 1190, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0008.jpg', 'NUU NU5 16GB 移动联通双4G智能手机 双卡双待 晒单有礼 晨光金香港品牌 2.5D弧度前后钢化玻璃 随机附赠手机套+钢化贴膜 晒单送移动电源+蓝牙耳机', 1, 0, 1);
INSERT INTO `product` VALUES (9, '乐视（Letv）乐1pro（X800）', 2399, 2299, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0009.jpg', '乐视（Letv）乐1pro（X800）64GB 金色 移动联通4G手机 双卡双待乐视生态UI+5.5英寸2K屏+高通8核处理器+4GB运行内存+64GB存储+1300万摄像头！', 1, 0, 1);
INSERT INTO `product` VALUES (10, '华为P20', 3788, 2599, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0010.jpg', '华为 Ascend Mate7 月光银 移动4G手机 双卡双待双通6英寸高清大屏，纤薄机身，智能超八核，按压式指纹识别！!选择下方“移动老用户4G飞享合约”，无需换号，还有话费每月返还！', 1, 1, 1);
INSERT INTO `product` VALUES (11, 'iPhone X', 8299, 8299, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0011.jpg', 'Apple iPhone X (A1865) 256GB 银色 移动联通电信4G手机', 0, 1, 1);
INSERT INTO `product` VALUES (12, '努比亚（nubia）My 布拉格', 1899, 1799, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0012.jpg', '努比亚（nubia）My 布拉格 银白 移动联通4G手机 双卡双待【嗨11，下单立减100】金属机身，快速充电！布拉格相机全新体验！', 0, 0, 1);
INSERT INTO `product` VALUES (13, '魅族16', 2698, 2698, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0013.jpg', '魅族 16th 全面屏手机 6GB+64GB 静夜黑 全网通移动联通电信4G手机 双卡双待', 0, 1, 1);
INSERT INTO `product` VALUES (14, 'vivo X5M', 1899, 1799, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0014.jpg', 'vivo X5M 移动4G手机 双卡双待 香槟金【购机送蓝牙耳机+蓝牙自拍杆】5.0英寸大屏显示·八核双卡双待·Hi-Fi移动KTV', 0, 0, 1);
INSERT INTO `product` VALUES (15, 'Apple iPhone 6 (A1586)', 4399, 4288, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0015.jpg', 'Apple iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机长期省才是真的省！点击购机送费版，月月送话费，月月享优惠，畅享4G网络，就在联通4G！', 1, 1, 1);
INSERT INTO `product` VALUES (16, '华为 HUAWEI Mate S 臻享版', 4200, 4087, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0016.jpg', '华为 HUAWEI Mate S 臻享版 手机 极昼金 移动联通双4G(高配)满星评价即返30元话费啦；买就送电源+清水套+创意手机支架；优雅弧屏，mate7升级版', 0, 0, 1);
INSERT INTO `product` VALUES (17, '索尼(SONY) E6533 Z3+', 4099, 3999, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0017.jpg', '索尼(SONY) E6533 Z3+ 双卡双4G手机 防水防尘 涧湖绿索尼z3专业防水 2070万像素 移动联通双4G', 1, 0, 1);
INSERT INTO `product` VALUES (18, 'HTC One M9+', 3599, 3499, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0018.jpg', 'HTC One M9+（M9pw） 金银汇 移动联通双4G手机5.2英寸，8核CPU，指纹识别，UltraPixel超像素前置相机+2000万/200万后置双镜头相机！降价特卖，惊喜不断！', 0, 0, 1);
INSERT INTO `product` VALUES (19, 'HTC Desire 826d 32G 臻珠白', 1599, 1469, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0019.jpg', '后置1300万+UltraPixel超像素前置摄像头+【双】前置扬声器+5.5英寸【1080p】大屏！', 1, 1, 1);
INSERT INTO `product` VALUES (20, '小米 红米2A 增强版 白色', 649, 549, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0020.jpg', '新增至2GB 内存+16GB容量！4G双卡双待，联芯 4 核 1.5GHz 处理器！', 0, 0, 1);
INSERT INTO `product` VALUES (21, '魅族 魅蓝note2 16GB 白色', 1099, 999, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0021.jpg', '现货速抢，抢完即止！5.5英寸1080P分辨率屏幕，64位八核1.3GHz处理器，1300万像素摄像头，双色温双闪光灯！', 1, 0, 1);
INSERT INTO `product` VALUES (22, '三星 Galaxy S5 (G9008W) 闪耀白', 2099, 1999, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0022.jpg', '5.1英寸全高清炫丽屏，2.5GHz四核处理器，1600万像素', 0, 1, 1);
INSERT INTO `product` VALUES (23, 'sonim XP7700 4G手机', 1799, 1699, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0023.jpg', '三防智能手机 移动/联通双4G 安全 黑黄色 双4G美国军工IP69 30天长待机 3米防水防摔 北斗', 0, 1, 1);
INSERT INTO `product` VALUES (24, '努比亚（nubia）Z9精英版 金色', 3988, 3888, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0024.jpg', '移动联通电信4G手机 双卡双待真正的无边框！金色尊贵版！4GB+64GB大内存！', 1, 1, 1);
INSERT INTO `product` VALUES (25, 'Apple iPhone 7', 5188, 4988, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0025.jpg', 'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力', 0, 0, 1);
INSERT INTO `product` VALUES (26, 'Apple iPhone 6s (A1700) 64G 玫瑰金色', 6388, 6088, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0026.jpg', 'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力', 1, 0, 1);
INSERT INTO `product` VALUES (27, '三星 Galaxy Note5（N9200）32G版', 5588, 5388, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0027.jpg', '旗舰机型！5.7英寸大屏，4+32G内存！不一样的SPen更优化的浮窗指令！赠无线充电板！', 0, 0, 1);
INSERT INTO `product` VALUES (28, '三星 Galaxy S6 Edge+（G9280）32G版 铂光金', 5999, 5888, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0028.jpg', '赠移动电源+自拍杆+三星OTG金属U盘+无线充电器+透明保护壳', 0, 0, 1);
INSERT INTO `product` VALUES (29, 'LG G4（H818）陶瓷白 国际版', 3018, 2978, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0029.jpg', '李敏镐代言，F1.8大光圈1600万后置摄像头，5.5英寸2K屏，3G+32G内存，LG年度旗舰机！', 0, 0, 1);
INSERT INTO `product` VALUES (30, '微软(Microsoft) Lumia 640 LTE DS (RM-1113)', 1099, 999, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0030.jpg', '微软首款双网双卡双4G手机，5.0英寸高清大屏，双网双卡双4G！', 1, 0, 1);
INSERT INTO `product` VALUES (31, '宏碁（acer）ATC705-N50 台式电脑', 2399, 2299, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0031.jpg', '爆款直降，满千减百，品质宏碁，特惠来袭，何必苦等11.11，早买早便宜！', 0, 0, 2);
INSERT INTO `product` VALUES (32, 'Apple MacBook Air MJVE2CH/A 13.3英寸', 6788, 6688, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0032.jpg', '宽屏笔记本电脑 128GB 闪存', 1, 0, 2);
INSERT INTO `product` VALUES (33, '联想（ThinkPad） 轻薄系列E450C(20EH0001CD)', 4399, 4199, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0033.jpg', '联想（ThinkPad） 轻薄系列E450C(20EH0001CD)14英寸笔记本电脑(i5-4210U 4G 500G 2G独显 Win8.1)', 1, 0, 2);
INSERT INTO `product` VALUES (34, '联想（Lenovo）小新V3000经典版', 4599, 4499, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0034.jpg', '14英寸超薄笔记本电脑（i7-5500U 4G 500G+8G SSHD 2G独显 全高清屏）黑色满1000減100，狂减！火力全开，横扫3天！', 1, 0, 2);
INSERT INTO `product` VALUES (35, '华硕（ASUS）经典系列R557LI', 3799, 3699, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0035.jpg', '15.6英寸笔记本电脑（i5-5200U 4G 7200转500G 2G独显 D刻 蓝牙 Win8.1 黑色）', 1, 0, 2);
INSERT INTO `product` VALUES (36, '华硕（ASUS）X450J', 4599, 4399, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0036.jpg', '14英寸笔记本电脑 （i5-4200H 4G 1TB GT940M 2G独显 蓝牙4.0 D刻 Win8.1 黑色）', 1, 1, 2);
INSERT INTO `product` VALUES (37, '戴尔（DELL）灵越 飞匣3000系列', 3399, 3299, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0037.jpg', 'Ins14C-4528B 14英寸笔记本（i5-5200U 4G 500G GT820M 2G独显 Win8）黑', 0, 0, 2);
INSERT INTO `product` VALUES (38, '惠普(HP)WASD 暗影精灵', 5699, 5499, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0038.jpg', '15.6英寸游戏笔记本电脑(i5-6300HQ 4G 1TB+128G SSD GTX950M 4G独显 Win10)', 1, 1, 2);
INSERT INTO `product` VALUES (39, 'Apple 配备 Retina 显示屏的 MacBook', 11299, 10288, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0039.jpg', 'Pro MF840CH/A 13.3英寸宽屏笔记本电脑 256GB 闪存', 0, 0, 2);
INSERT INTO `product` VALUES (40, '机械革命（MECHREVO）MR X6S-M', 6799, 6599, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0040.jpg', '15.6英寸游戏本(I7-4710MQ 8G 64GSSD+1T GTX960M 2G独显 IPS屏 WIN7)黑色', 0, 1, 2);
INSERT INTO `product` VALUES (41, '神舟（HASEE） 战神K660D-i7D2', 5699, 5499, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0041.jpg', '15.6英寸游戏本(i7-4710MQ 8G 1TB GTX960M 2G独显 1080P)黑色', 1, 1, 2);
INSERT INTO `product` VALUES (42, '微星（MSI）GE62 2QC-264XCN', 6199, 5999, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0042.jpg', '15.6英寸游戏笔记本电脑（i5-4210H 8G 1T GTX960MG DDR5 2G 背光键盘）黑色', 0, 0, 2);
INSERT INTO `product` VALUES (43, '雷神（ThundeRobot）G150S', 5699, 5499, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0043.jpg', '15.6英寸游戏本 ( i7-4710MQ 4G 500G GTX950M 2G独显 包无亮点全高清屏) 金', 1, 1, 2);
INSERT INTO `product` VALUES (44, '惠普（HP）轻薄系列 HP', 3199, 3099, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0044.jpg', '15-r239TX 15.6英寸笔记本电脑（i5-5200U 4G 500G GT820M 2G独显 win8.1）金属灰', 0, 0, 2);
INSERT INTO `product` VALUES (45, '未来人类（Terrans Force）T5', 10999, 9899, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0045.jpg', '15.6英寸游戏本（i7-5700HQ 16G 120G固态+1TB GTX970M 3G GDDR5独显）黑', 0, 0, 2);
INSERT INTO `product` VALUES (46, '戴尔（DELL）Vostro 3800-R6308 台式电脑', 3299, 3199, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0046.jpg', '（i3-4170 4G 500G DVD 三年上门服务 Win7）黑', 1, 1, 2);
INSERT INTO `product` VALUES (47, '联想（Lenovo）H3050 台式电脑', 5099, 4899, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0047.jpg', '（i5-4460 4G 500G GT720 1G独显 DVD 千兆网卡 Win10）23英寸', 0, 0, 2);
INSERT INTO `product` VALUES (48, 'Apple iPad mini 2 ME279CH/A', 2088, 1888, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0048.jpg', '（配备 Retina 显示屏 7.9英寸 16G WLAN 机型 银色）', 1, 0, 2);
INSERT INTO `product` VALUES (49, '小米（MI）7.9英寸平板', 1399, 1299, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0049.jpg', 'WIFI 64GB（NVIDIA Tegra K1 2.2GHz 2G 64G 2048*1536视网膜屏 800W）白色', 0, 0, 2);
INSERT INTO `product` VALUES (50, 'Apple iPad Air 2 MGLW2CH/A', 2399, 2299, 'https://pic-wsg.oss-cn-shanghai.aliyuncs.com/igeekshop/c_0050.jpg', '（9.7英寸 16G WLAN 机型 银色）', 0, 0, 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` int(10) NOT NULL,
  `birthday` date NOT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `active_status` tinyint(1) NOT NULL,
  `activation_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0e503fd8ec3b40899eada0001d07eeed', '干', 'd459762727dad0965454123bfef8968d', '干', '1619994512@qq.com', 1, '2019-07-03', '13476845647', 1, 'c31ecef6581d493ca74be8a312195c5e');
INSERT INTO `user` VALUES ('5436499219164a60ad40dc64ce740d72', '1', '30721d4c2087cf8e9cd1a03e0ca60fb0', '1', 'a13207123727@gmail.com', 1, '2019-01-01', '13207123727', 1, '0');
INSERT INTO `user` VALUES ('70b10db93e81469c9b364e7d9801537e', '王少刚', 'd459762727dad0965454123bfef8968d', '王少刚', '1095151731@qq.com', 1, '2019-08-07', '13207123727', 1, 'b7cf1e07b7404095aff4e32a133faa11');
INSERT INTO `user` VALUES ('8752480a18e34d77a66d7dcb2bb734a1', 'aaaaaa', 'd459762727dad0965454123bfef8968d', 'aaa', '455051964@qq.com', 1, '2019-08-22', '15927140311', 1, '339ab9ee7da44608a83a076fa935b0ec');

SET FOREIGN_KEY_CHECKS = 1;
