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
- 
### 数据库设计
```sql
/*
 Navicat Premium Data Transfer

 Source Server         : mysql8
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 31/12/2019 08:51:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) NOT NULL COMMENT '文章标号，主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章内容',
  `type` int(11) NULL DEFAULT NULL COMMENT '专栏标号',
  `publishdate` datetime(0) NOT NULL,
  `vistornum` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `outstanding` int(11) NULL DEFAULT NULL COMMENT '是否首页轮播推荐',
  `status` int(11) NOT NULL COMMENT '当前文章的状态 1正常 0正在编辑',
  `imgurl` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `abstruct` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, 'Java', '这里是内容，这里是内容，冰语大佬很牛逼！', 1, '2019-12-25 09:46:38', '200', 1, 1, 'images/featured_1.jpg', '冰语大佬牛逼！');
INSERT INTO `article` VALUES (2, 'C++', '我又来了，冰语大佬很牛逼！', 1, '2019-12-25 09:57:55', '200', 1, 1, 'images/featured_2.jpg', '冰语大佬最牛逼，没有人比他牛逼！');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `imgurl` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `publishdate` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '111', '111', '111', '2019-12-25 09:57:55');
INSERT INTO `category` VALUES (2, '222', '222', '222', '2019-12-25 10:57:55');

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
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('qianqianjun', 'qianqianjun', 'qianqianjun@Gmail.com', '110', 'Google', 'Mars');

SET FOREIGN_KEY_CHECKS = 1;

```
