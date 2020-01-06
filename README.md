# spring-boot-blog

## 需求分析

### 展示部分

#### 首页
- 首页的轮播（轮播文章）点击之后跳转到普通文章页。【标题，内容摘要，图片】
- 最新文章： 按照发布时间来进行倒序排序展示（通过数据库 order by）
- 博客专栏： 展示前6个专栏。
- 推荐文章： 展示浏览量最大的文章。

#### 文章和专栏页
- 大家都能看，不需要登录，展示文章
- 专栏页，展示专栏信息
- 文章页评论功能 （要留联系方式，内容评论）
- 文章分页功能
- 文章页展示浏览量

#### 后台管理页面
- 登录功能（博主自己登录就行）
- 文章管理：
    - 专栏的信息添加和修改。
    - 文章发布
    - 文章删除
    - 文章修改

- 专栏管理：
    - 新增专栏
    - 修改专栏
    - 删除专栏：注意，删除之后，文章不能一起删除，删除对应的文章专栏信息即可。
    


### 12 月 31 号

- 文章页具体样式
- 分类专栏页面 （文章列表页，数据库查询，查询type 为某一字段的这些文章）
- 推荐文章页，按照访问次数降序排序的文章列表页。
- 所有专栏的列表页
- 关于我的一页。


### 数据库设计
```sql
/*
 Navicat Premium Data Transfer

 Source Server         : mysql8
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 05/01/2020 15:39:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章标号，主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章标题',
  `blob` blob NOT NULL COMMENT '文章内容',
  `type` int(11) NULL DEFAULT NULL COMMENT '专栏标号',
  `publishdate` datetime(0) NOT NULL,
  `visitornum` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `outstanding` int(11) NULL DEFAULT NULL COMMENT '是否首页轮播推荐',
  `status` int(11) NOT NULL COMMENT '当前文章的状态 1正常 0正在编辑',
  `imgurl` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `abstruct` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (12, 'Java split 函数字符串分割遇到的坑', 0x3E20E8BF99E698AFE4B880E4B8AAE99D9EE5B8B8E7AE80E58D95E79A84E58A9FE883BDEFBC8CE4BD86E698AFE8BF99E9878CE69C89E59D91EFBC8CE980A0E68890E68891E58699E4BBA3E7A081E79A84E697B6E58099E98187E588B0E4BA86E997AEE9A2980A0A2323232020E9A696E58588E79C8BE997AEE9A298E4BBA3E7A0810A0A6060606A6176610A7075626C696320636C6173732074657374207B0A202020207075626C69632073746174696320766F6964206D61696E28537472696E675B5D206172677329207B0A2020202020202020537472696E672066696C65203D226267312E6A7067223B0A2020202020202020537472696E675B5D2070617274733D66696C652E73706C697428222E22293B0A2020202020202020666F7228696E7420693D303B693C70617274732E6C656E6774683B692B2B297B0A20202020202020202020202053797374656D2E6F75742E7072696E746C6E2870617274735B695D293B0A20202020202020207D0A202020207D0A7D0A6060600A0AE4BBA5E4B88AE7A88BE5BA8FE68891E9A284E69C9FE8BE93E587BAE79A84E698AFEFBC9A2062616731202C206A7067202CE784B6E8808CE4BB80E4B988E983BDE6B2A1E69C89E8BE93E587BAE38082E68891E89299E894BDE4BA86EFBC8CE689BEE4BA86E59084E7A78DE4B9A6E7B18DE6898DE689BEE588B0E58E9FE59BA0EFBC8CE5B086E7A88BE5BA8FE4BFAEE694B9E5A682E4B88BE58DB3E58FAFE6ADA3E7A1AEE8BE93E587BAEFBC9A0A0A2D2D2D0A0A6060606A6176610A7075626C696320636C6173732074657374207B0A202020207075626C69632073746174696320766F6964206D61696E28537472696E675B5D206172677329207B0A2020202020202020537472696E672066696C65203D226267312E6A7067223B0A2020202020202020537472696E675B5D2070617274733D66696C652E73706C697428225C5C2E22293B0A2020202020202020666F7228696E7420693D303B693C70617274732E6C656E6774683B692B2B297B0A20202020202020202020202053797374656D2E6F75742E7072696E746C6E2870617274735B695D293B0A20202020202020207D0A202020207D0A7D0A6060600A23232320E997AEE9A298E680BBE7BB930A0AE587BAE78EB0E8BF99E4B8AAE997AEE9A298E4B8BBE8A681E58E9FE59BA0E698AFE8A681E5AFB9E789B9E5AE9AE5AD97E7ACA6E8BF9BE8A18CE8BDACE4B989E6898DE58FAFE4BBA5E8BF9BE8A18CE58886E589B2EFBC8CE58887E8AEB0EFBC81E58887E8AEB0EFBC81E78CA5E79090E58F91E882B2EFBC8CE588ABE6B5AAEFBC81EFBC81EFBC81, 1, '2020-01-05 14:49:09', '0', 0, 1, '', '这是一篇幅关于 java 语言 split 函数的用法，其实很简单，只是需要注意函数参数中作为分割符的字符如果有特定的含义，一定要进行转义，否则就会有问题！切记切记！');
INSERT INTO `article` VALUES (13, '这里是文章标题', 0xE8BF99E9878CE698AFE69687E7ABA0E58685E5AEB9, 1, '2020-01-05 15:00:30', '0', 0, 1, '', '文章摘要');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `imgurl` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `publishdate` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '编程语言', '这里是我学习Java，C++，Python等语言', '111', '2019-12-25 09:57:55');
INSERT INTO `category` VALUES (2, '计算机视觉', '这里是我学习计算机视觉相关论文的笔记', '222', '2019-12-25 10:57:55');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言的id',
  `aid` int(11) NOT NULL COMMENT '留言对应的文章的id',
  `publishdate` datetime(0) NULL DEFAULT NULL COMMENT '留言发布的日期',
  `content` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '留言的内容',
  `reply` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '博主给留言回复',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '留言人的email',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '留言人的昵称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像图片url 地址',
  `csdn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `github` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('qianqianjun', 'qianqianjun', 'qianqianjun190594@gmail.com', '15801209263', '阿里巴巴', '北京市朝阳区', '/upload/2020-01-04-17-33-57.jpg', 'https://blog.csdn.net/qq_38863413', 'https://github.com/qianqianjun');

SET FOREIGN_KEY_CHECKS = 1;


```
