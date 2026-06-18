CREATE DATABASE IF NOT EXISTS `office_fix` default charset utf8 COLLATE utf8_general_ci;

USE `office_fix`;
SET FOREIGN_KEY_CHECKS=0;
drop TABLE if EXISTS `xt_notice`;
drop TABLE if EXISTS `eval_log`;
drop TABLE if EXISTS `pj_req`;
drop TABLE if EXISTS `zsk`;
drop TABLE if EXISTS `fix_gc`;
drop TABLE if EXISTS `bx_log`;
drop TABLE if EXISTS `bgqy`;
drop TABLE if EXISTS `bg_service`;
drop TABLE if EXISTS `depart`;
drop TABLE if EXISTS `user`;
drop TABLE if EXISTS `fix`;
drop TABLE if EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
 `login_name` varchar(255) DEFAULT NULL COMMENT '登录名',
 `password` varchar(255) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='管理员';


CREATE TABLE IF NOT EXISTS `fix` (
 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
 `login_name` varchar(255) NOT NULL COMMENT '登录名',
 `password` varchar(255) NOT NULL COMMENT '密码',
 `name` varchar(255) NOT NULL COMMENT '姓名',
 `celphone` varchar(255) NOT NULL COMMENT '联系电话',
 `head_img` varchar(200) DEFAULT NULL COMMENT '头像',
 `sz_type` varchar(255) NOT NULL COMMENT '擅长类型',
 `fix_zz` varchar(200) DEFAULT NULL COMMENT '维修资质',
 `check_status` TINYINT  NOT NULL COMMENT '审核状态 1-待审核,2-审核通过,3-审核不通过',
CONSTRAINT chk_fix_check_status CHECK (check_status IN (1,2,3)),
 `teval_score` double(10,2) DEFAULT NULL COMMENT '综合评分',
 `zc_time` DATETIME  DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
 `is_delete` TINYINT  NOT NULL COMMENT '是否删除 1-是,2-否',
CONSTRAINT chk_fix_is_delete CHECK (is_delete IN (1,2)),
  PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='维修员';


CREATE TABLE IF NOT EXISTS `user` (
 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
 `login_name` varchar(255) NOT NULL COMMENT '登录名',
 `password` varchar(255) NOT NULL COMMENT '密码',
 `name` varchar(255) NOT NULL COMMENT '姓名',
 `celphone` varchar(255) NOT NULL COMMENT '联系电话',
 `head_img` varchar(200) DEFAULT NULL COMMENT '头像',
 `depart` bigint DEFAULT NULL COMMENT '部门',
 `zc_time` DATETIME  DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
 `is_delete` TINYINT  NOT NULL COMMENT '是否删除 1-是,2-否',
CONSTRAINT chk_user_is_delete CHECK (is_delete IN (1,2)),
  PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户';


CREATE TABLE IF NOT EXISTS `depart` (
 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
 `depart_name` varchar(255) NOT NULL COMMENT '部门名',
 `is_delete` TINYINT  NOT NULL COMMENT '是否删除 1-是,2-否',
CONSTRAINT chk_depart_is_delete CHECK (is_delete IN (1,2)),
  PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='部门';


CREATE TABLE IF NOT EXISTS `bg_service` (
 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
 `service_no` varchar(255) NOT NULL COMMENT '设备编号',
 `service_type` varchar(255) NOT NULL COMMENT '设备类型',
 `service_name` varchar(255) NOT NULL COMMENT '设备名',
 `service_xh` varchar(255) NOT NULL COMMENT '设备型号',
 `cg_date` DATE  NOT NULL COMMENT '采购日期',
 `bgqy` bigint DEFAULT NULL COMMENT '办公区域',
 `is_delete` TINYINT  NOT NULL COMMENT '是否删除 1-是,2-否',
CONSTRAINT chk_bg_service_is_delete CHECK (is_delete IN (1,2)),
 `search_key_word` varchar(255) DEFAULT NULL COMMENT '关键词',
  PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='办公设备';


CREATE TABLE IF NOT EXISTS `bgqy` (
 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
 `qy_name` varchar(255) NOT NULL COMMENT '区域名',
 `is_delete` TINYINT  NOT NULL COMMENT '是否删除 1-是,2-否',
CONSTRAINT chk_bgqy_is_delete CHECK (is_delete IN (1,2)),
  PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='办公区域';


CREATE TABLE IF NOT EXISTS `bx_log` (
 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
 `bx_no` varchar(255) NOT NULL COMMENT '报修编号',
 `bg_service` bigint DEFAULT NULL COMMENT '办公设备',
 `service_type` varchar(255) DEFAULT NULL COMMENT '设备类型',
 `bgqy` bigint DEFAULT NULL COMMENT '办公区域',
 `yxcd` TINYINT  DEFAULT NULL COMMENT '优先程度 1-普通,2-紧急',
CONSTRAINT chk_bx_log_yxcd CHECK (yxcd IN (1,2)),
 `gzms` varchar(1000) DEFAULT NULL COMMENT '故障描述',
 `gz_img` varchar(600) DEFAULT NULL COMMENT '故障图片',
 `gz_veo` varchar(200) DEFAULT NULL COMMENT '故障视频',
 `gz_location` varchar(255) DEFAULT NULL COMMENT '故障位置',
 `bx_status` TINYINT  DEFAULT NULL COMMENT '报修状态 1-待受理,2-已派单,3-维修中,4-已完成,5-已验收,6-已驳回,7-已评价',
CONSTRAINT chk_bx_log_bx_status CHECK (bx_status IN (1,2,3,4,5,6,7)),
 `admin_remark` varchar(255) DEFAULT NULL COMMENT '管理员备注',
 `ys_note` varchar(255) DEFAULT NULL COMMENT '验收说明',
 `fix_remark` varchar(255) DEFAULT NULL COMMENT '维修员备注',
 `fix` bigint DEFAULT NULL COMMENT '维修员',
 `user_id` bigint DEFAULT NULL COMMENT '用户ID',
 `create_time` DATETIME  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 `is_delete` TINYINT  NOT NULL COMMENT '是否删除 1-是,2-否',
CONSTRAINT chk_bx_log_is_delete CHECK (is_delete IN (1,2)),
 `search_key_word` varchar(255) DEFAULT NULL COMMENT '关键词',
  PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='报修记录';


CREATE TABLE IF NOT EXISTS `fix_gc` (
 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
 `fix_bz` varchar(255) NOT NULL COMMENT '维修步骤',
 `ghpj` varchar(255) NOT NULL COMMENT '更换配件',
 `xhgs` double(10,2) NOT NULL COMMENT '消耗工时',
 `fix` bigint DEFAULT NULL COMMENT '维修员',
 `bxid` bigint DEFAULT NULL COMMENT '报修ID',
 `create_time` DATETIME  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='维修过程';


CREATE TABLE IF NOT EXISTS `zsk` (
 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
 `title` varchar(255) NOT NULL COMMENT '标题',
 `subtitle` varchar(1000) NOT NULL COMMENT '副标题',
 `zs_content` text  COLLATE utf8_bin NOT NULL COMMENT '知识内容',
 `fb_time` DATETIME  DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
 `search_key_word` varchar(255) DEFAULT NULL COMMENT '关键词',
  PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='知识库';


CREATE TABLE IF NOT EXISTS `pj_req` (
 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
 `req_no` varchar(255) NOT NULL COMMENT '申请编号',
 `pj_name` varchar(255) NOT NULL COMMENT '配件名',
 `xy_num` int(11) NOT NULL COMMENT '需要数量',
 `req_note` varchar(1000) NOT NULL COMMENT '申请说明',
 `req_status` TINYINT  NOT NULL COMMENT '申请状态 1-已申请,2-已接受申请,3-已拒绝申请',
CONSTRAINT chk_pj_req_req_status CHECK (req_status IN (1,2,3)),
 `check_remark` varchar(255) DEFAULT NULL COMMENT '审核备注',
 `create_time` DATETIME  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 `fix` bigint DEFAULT NULL COMMENT '维修员',
  PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='配件申请';


CREATE TABLE IF NOT EXISTS `eval_log` (
 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
 `bxid` bigint DEFAULT NULL COMMENT '报修ID',
 `fix` bigint DEFAULT NULL COMMENT '维修员',
 `eval_star` TINYINT  NOT NULL COMMENT '评价星级 1-1星,2-2星,3-3星,4-4星,5-5星',
CONSTRAINT chk_eval_log_eval_star CHECK (eval_star IN (1,2,3,4,5)),
 `eval_content` varchar(255) NOT NULL COMMENT '评价内容',
 `user_id` bigint DEFAULT NULL COMMENT '用户ID',
 `eval_time` DATETIME  DEFAULT CURRENT_TIMESTAMP COMMENT '评价时间',
  PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='评价记录';


CREATE TABLE IF NOT EXISTS `xt_notice` (
 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
 `title` varchar(255) NOT NULL COMMENT '标题',
 `subtitle` varchar(1000) NOT NULL COMMENT '副标题',
 `notice_content` text  COLLATE utf8_bin NOT NULL COMMENT '公告内容',
 `fb_time` DATETIME  DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
 `search_key_word` varchar(255) DEFAULT NULL COMMENT '关键词',
  PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统公告';



INSERT INTO `office_fix`.`admin` (`id`,`login_name`,`password`)  VALUES('1','admin','123');
INSERT INTO `office_fix`.`admin` (`id`,`login_name`,`password`)  VALUES('2','admin1','123');
INSERT INTO `office_fix`.`admin` (`id`,`login_name`,`password`)  VALUES('3','admin2','123');
INSERT INTO `office_fix`.`admin` (`id`,`login_name`,`password`)  VALUES('4','admin3','123');
INSERT INTO `office_fix`.`admin` (`id`,`login_name`,`password`)  VALUES('5','admin4','123');
INSERT INTO `office_fix`.`admin` (`id`,`login_name`,`password`)  VALUES('6','admin5','123');
INSERT INTO `office_fix`.`admin` (`id`,`login_name`,`password`)  VALUES('7','admin6','123');
INSERT INTO `office_fix`.`admin` (`id`,`login_name`,`password`)  VALUES('8','admin7','123');
INSERT INTO `office_fix`.`admin` (`id`,`login_name`,`password`)  VALUES('9','admin8','123');
INSERT INTO `office_fix`.`fix` (`id`,`login_name`,`password`,`name`,`celphone`,`head_img`,`sz_type`,`fix_zz`,`check_status`,`teval_score`,`zc_time`,`is_delete`)  VALUES('1','wxy','123','张伟','13052653265','http://localhost:8080/office_fix/static/images/维修员图片/2.jpg','网络故障','http://localhost:8080/office_fix/static/images/维修员图片/2.jpg','1','0.0','2026-02-22 01:08:30','2');
INSERT INTO `office_fix`.`fix` (`id`,`login_name`,`password`,`name`,`celphone`,`head_img`,`sz_type`,`fix_zz`,`check_status`,`teval_score`,`zc_time`,`is_delete`)  VALUES('2','wxy1','123','李娜','13553613261','http://localhost:8080/office_fix/static/images/维修员图片/3.jpg','打印机维修','http://localhost:8080/office_fix/static/images/维修员图片/3.jpg','3','0.0','2026-02-27 01:08:30','2');
INSERT INTO `office_fix`.`fix` (`id`,`login_name`,`password`,`name`,`celphone`,`head_img`,`sz_type`,`fix_zz`,`check_status`,`teval_score`,`zc_time`,`is_delete`)  VALUES('3','wxy2','123','王强','13552623265','http://localhost:8080/office_fix/static/images/维修员图片/4.jpg','电脑硬件','http://localhost:8080/office_fix/static/images/维修员图片/4.jpg','2','0.0','2026-02-24 01:08:30','2');
INSERT INTO `office_fix`.`fix` (`id`,`login_name`,`password`,`name`,`celphone`,`head_img`,`sz_type`,`fix_zz`,`check_status`,`teval_score`,`zc_time`,`is_delete`)  VALUES('4','wxy3','123','刘芳','13052623165','http://localhost:8080/office_fix/static/images/维修员图片/5.jpg','空调维护','http://localhost:8080/office_fix/static/images/维修员图片/5.jpg','2','0.0','2026-02-24 01:08:30','2');
INSERT INTO `office_fix`.`fix` (`id`,`login_name`,`password`,`name`,`celphone`,`head_img`,`sz_type`,`fix_zz`,`check_status`,`teval_score`,`zc_time`,`is_delete`)  VALUES('5','wxy4','123','陈明','13052633163','http://localhost:8080/office_fix/static/images/维修员图片/1.jpg','电路检修','http://localhost:8080/office_fix/static/images/维修员图片/1.jpg','2','0.0','2026-02-23 01:08:30','2');
INSERT INTO `office_fix`.`fix` (`id`,`login_name`,`password`,`name`,`celphone`,`head_img`,`sz_type`,`fix_zz`,`check_status`,`teval_score`,`zc_time`,`is_delete`)  VALUES('6','wxy5','123','赵静','13052651261','http://localhost:8080/office_fix/static/images/维修员图片/2.jpg','办公软件','http://localhost:8080/office_fix/static/images/维修员图片/2.jpg','3','0.0','2026-02-26 01:08:30','2');
INSERT INTO `office_fix`.`fix` (`id`,`login_name`,`password`,`name`,`celphone`,`head_img`,`sz_type`,`fix_zz`,`check_status`,`teval_score`,`zc_time`,`is_delete`)  VALUES('7','wxy6','123','周涛','13052651261','http://localhost:8080/office_fix/static/images/维修员图片/3.jpg','投影设备','http://localhost:8080/office_fix/static/images/维修员图片/3.jpg','3','0.0','2026-02-24 01:08:30','2');
INSERT INTO `office_fix`.`fix` (`id`,`login_name`,`password`,`name`,`celphone`,`head_img`,`sz_type`,`fix_zz`,`check_status`,`teval_score`,`zc_time`,`is_delete`)  VALUES('8','wxy7','123','吴霞','13052633265','http://localhost:8080/office_fix/static/images/维修员图片/4.jpg','门禁系统','http://localhost:8080/office_fix/static/images/维修员图片/4.jpg','1','0.0','2026-02-21 01:08:31','2');
INSERT INTO `office_fix`.`fix` (`id`,`login_name`,`password`,`name`,`celphone`,`head_img`,`sz_type`,`fix_zz`,`check_status`,`teval_score`,`zc_time`,`is_delete`)  VALUES('9','wxy8','123','郑浩','13052653215','http://localhost:8080/office_fix/static/images/维修员图片/5.jpg','监控设备','http://localhost:8080/office_fix/static/images/维修员图片/5.jpg','2','0.0','2026-02-25 01:08:31','2');
INSERT INTO `office_fix`.`user` (`id`,`login_name`,`password`,`name`,`celphone`,`head_img`,`depart`,`zc_time`,`is_delete`)  VALUES('1','user','123','李明','13052653265','http://localhost:8080/office_fix/static/images/用户图片/2.jpg','1','2026-02-22 01:08:31','2');
INSERT INTO `office_fix`.`user` (`id`,`login_name`,`password`,`name`,`celphone`,`head_img`,`depart`,`zc_time`,`is_delete`)  VALUES('2','user1','123','王小红','13553613261','http://localhost:8080/office_fix/static/images/用户图片/3.jpg','7','2026-02-23 01:08:31','2');
INSERT INTO `office_fix`.`user` (`id`,`login_name`,`password`,`name`,`celphone`,`head_img`,`depart`,`zc_time`,`is_delete`)  VALUES('3','user2','123','刘洋','13552623265','http://localhost:8080/office_fix/static/images/用户图片/4.jpg','7','2026-02-24 01:08:31','2');
INSERT INTO `office_fix`.`user` (`id`,`login_name`,`password`,`name`,`celphone`,`head_img`,`depart`,`zc_time`,`is_delete`)  VALUES('4','user3','123','陈思','13052623165','http://localhost:8080/office_fix/static/images/用户图片/5.jpg','3','2026-02-25 01:08:31','2');
INSERT INTO `office_fix`.`user` (`id`,`login_name`,`password`,`name`,`celphone`,`head_img`,`depart`,`zc_time`,`is_delete`)  VALUES('5','user4','123','赵刚','13052633163','http://localhost:8080/office_fix/static/images/用户图片/1.jpg','9','2026-02-22 01:08:31','2');
INSERT INTO `office_fix`.`user` (`id`,`login_name`,`password`,`name`,`celphone`,`head_img`,`depart`,`zc_time`,`is_delete`)  VALUES('6','user5','123','周梅','13052651261','http://localhost:8080/office_fix/static/images/用户图片/2.jpg','8','2026-02-22 01:08:31','2');
INSERT INTO `office_fix`.`user` (`id`,`login_name`,`password`,`name`,`celphone`,`head_img`,`depart`,`zc_time`,`is_delete`)  VALUES('7','user6','123','吴斌','13052651261','http://localhost:8080/office_fix/static/images/用户图片/3.jpg','5','2026-02-24 01:08:31','2');
INSERT INTO `office_fix`.`user` (`id`,`login_name`,`password`,`name`,`celphone`,`head_img`,`depart`,`zc_time`,`is_delete`)  VALUES('8','user7','123','郑雨','13052633265','http://localhost:8080/office_fix/static/images/用户图片/4.jpg','9','2026-02-26 01:08:31','2');
INSERT INTO `office_fix`.`user` (`id`,`login_name`,`password`,`name`,`celphone`,`head_img`,`depart`,`zc_time`,`is_delete`)  VALUES('9','user8','123','孙鹏','13052653215','http://localhost:8080/office_fix/static/images/用户图片/5.jpg','9','2026-02-20 01:08:31','2');
INSERT INTO `office_fix`.`depart` (`id`,`depart_name`,`is_delete`)  VALUES('1','行政部','2');
INSERT INTO `office_fix`.`depart` (`id`,`depart_name`,`is_delete`)  VALUES('2','财务部','2');
INSERT INTO `office_fix`.`depart` (`id`,`depart_name`,`is_delete`)  VALUES('3','人力资源部','2');
INSERT INTO `office_fix`.`depart` (`id`,`depart_name`,`is_delete`)  VALUES('4','技术部','2');
INSERT INTO `office_fix`.`depart` (`id`,`depart_name`,`is_delete`)  VALUES('5','市场部','2');
INSERT INTO `office_fix`.`depart` (`id`,`depart_name`,`is_delete`)  VALUES('6','销售部','2');
INSERT INTO `office_fix`.`depart` (`id`,`depart_name`,`is_delete`)  VALUES('7','客服部','2');
INSERT INTO `office_fix`.`depart` (`id`,`depart_name`,`is_delete`)  VALUES('8','研发部','2');
INSERT INTO `office_fix`.`depart` (`id`,`depart_name`,`is_delete`)  VALUES('9','采购部','2');
INSERT INTO `office_fix`.`bg_service` (`id`,`service_no`,`service_type`,`service_name`,`service_xh`,`cg_date`,`bgqy`,`is_delete`,`search_key_word`)  VALUES('1','DEV2023001','台式电脑','联想台式机','ThinkCentre M70a','2026-02-20','6','2','320095');
INSERT INTO `office_fix`.`bg_service` (`id`,`service_no`,`service_type`,`service_name`,`service_xh`,`cg_date`,`bgqy`,`is_delete`,`search_key_word`)  VALUES('2','DEV2023002','笔记本电脑','戴尔笔记本','Latitude 5420','2026-02-21','7','2','320095');
INSERT INTO `office_fix`.`bg_service` (`id`,`service_no`,`service_type`,`service_name`,`service_xh`,`cg_date`,`bgqy`,`is_delete`,`search_key_word`)  VALUES('3','DEV2023003','打印机','惠普打印机','LaserJet Pro M404','2026-02-23','3','2','320095');
INSERT INTO `office_fix`.`bg_service` (`id`,`service_no`,`service_type`,`service_name`,`service_xh`,`cg_date`,`bgqy`,`is_delete`,`search_key_word`)  VALUES('4','DEV2023004','投影仪','索尼投影仪','VPL-DW270','2026-02-22','8','2','320095');
INSERT INTO `office_fix`.`bg_service` (`id`,`service_no`,`service_type`,`service_name`,`service_xh`,`cg_date`,`bgqy`,`is_delete`,`search_key_word`)  VALUES('5','DEV2023005','空调','格力空调','KFR-35GW','2026-02-22','3','2','320095');
INSERT INTO `office_fix`.`bg_service` (`id`,`service_no`,`service_type`,`service_name`,`service_xh`,`cg_date`,`bgqy`,`is_delete`,`search_key_word`)  VALUES('6','DEV2023006','复印机','佳能复印机','iR-ADV C350','2026-02-21','3','2','320095');
INSERT INTO `office_fix`.`bg_service` (`id`,`service_no`,`service_type`,`service_name`,`service_xh`,`cg_date`,`bgqy`,`is_delete`,`search_key_word`)  VALUES('7','DEV2023007','扫描仪','富士通扫描仪','ScanSnap iX1500','2026-02-23','6','2','320095');
INSERT INTO `office_fix`.`bg_service` (`id`,`service_no`,`service_type`,`service_name`,`service_xh`,`cg_date`,`bgqy`,`is_delete`,`search_key_word`)  VALUES('8','DEV2023008','碎纸机','科密碎纸机','S286','2026-02-25','5','2','320095');
INSERT INTO `office_fix`.`bg_service` (`id`,`service_no`,`service_type`,`service_name`,`service_xh`,`cg_date`,`bgqy`,`is_delete`,`search_key_word`)  VALUES('9','DEV2023009','电话机','西门子电话','Gigaset C530','2026-02-21','2','2','320095');
INSERT INTO `office_fix`.`bgqy` (`id`,`qy_name`,`is_delete`)  VALUES('1','A区前台','2');
INSERT INTO `office_fix`.`bgqy` (`id`,`qy_name`,`is_delete`)  VALUES('2','B区会议室','2');
INSERT INTO `office_fix`.`bgqy` (`id`,`qy_name`,`is_delete`)  VALUES('3','C区办公区','2');
INSERT INTO `office_fix`.`bgqy` (`id`,`qy_name`,`is_delete`)  VALUES('4','D区经理室','2');
INSERT INTO `office_fix`.`bgqy` (`id`,`qy_name`,`is_delete`)  VALUES('5','E区财务室','2');
INSERT INTO `office_fix`.`bgqy` (`id`,`qy_name`,`is_delete`)  VALUES('6','F区休息区','2');
INSERT INTO `office_fix`.`bgqy` (`id`,`qy_name`,`is_delete`)  VALUES('7','G区仓库','2');
INSERT INTO `office_fix`.`bgqy` (`id`,`qy_name`,`is_delete`)  VALUES('8','H区走廊','2');
INSERT INTO `office_fix`.`bgqy` (`id`,`qy_name`,`is_delete`)  VALUES('9','I区培训室','2');
INSERT INTO `office_fix`.`bx_log` (`id`,`bx_no`,`bg_service`,`service_type`,`bgqy`,`yxcd`,`gzms`,`gz_img`,`gz_veo`,`gz_location`,`bx_status`,`admin_remark`,`ys_note`,`fix_remark`,`fix`,`user_id`,`create_time`,`is_delete`,`search_key_word`)  VALUES('1','20260227010831312443','6','复印机','3','1','电脑无法开机','http://localhost:8080/office_fix/static/images/报修记录图片/2.jpg','http://localhost:8080/office_fix/static/sources/2-1.mp4','C区办公区工位3','3','请优先处理','故障已排除测试正常','更换电源后解决','6','5','2026-02-24 01:08:31','2','320095');
INSERT INTO `office_fix`.`bx_log` (`id`,`bx_no`,`bg_service`,`service_type`,`bgqy`,`yxcd`,`gzms`,`gz_img`,`gz_veo`,`gz_location`,`bx_status`,`admin_remark`,`ys_note`,`fix_remark`,`fix`,`user_id`,`create_time`,`is_delete`,`search_key_word`)  VALUES('2','20260227010831125555','2','笔记本电脑','7','2','打印机卡纸','http://localhost:8080/office_fix/static/images/报修记录图片/3.jpg','http://localhost:8080/office_fix/static/sources/2-1.mp4','A区前台旁','2','设备急需使用','设备运行流畅','清理纸屑并润滑','7','6','2026-02-21 01:08:31','2','320095');
INSERT INTO `office_fix`.`bx_log` (`id`,`bx_no`,`bg_service`,`service_type`,`bgqy`,`yxcd`,`gzms`,`gz_img`,`gz_veo`,`gz_location`,`bx_status`,`admin_remark`,`ys_note`,`fix_remark`,`fix`,`user_id`,`create_time`,`is_delete`,`search_key_word`)  VALUES('3','20260227010831031225','5','空调','3','1','空调不制冷','http://localhost:8080/office_fix/static/images/报修记录图片/4.jpg','http://localhost:8080/office_fix/static/sources/2-1.mp4','D区经理室内','5','已通知维修员','制冷效果恢复','补充制冷剂','3','2','2026-02-26 01:08:31','2','320095');
INSERT INTO `office_fix`.`bx_log` (`id`,`bx_no`,`bg_service`,`service_type`,`bgqy`,`yxcd`,`gzms`,`gz_img`,`gz_veo`,`gz_location`,`bx_status`,`admin_remark`,`ys_note`,`fix_remark`,`fix`,`user_id`,`create_time`,`is_delete`,`search_key_word`)  VALUES('4','20260227010831889276','5','空调','3','1','网络连接中断','http://localhost:8080/office_fix/static/images/报修记录图片/5.jpg','http://localhost:8080/office_fix/static/sources/2-1.mp4','全办公区网络','7','故障影响范围较大','网络连接稳定','重启路由器并配置','4','8','2026-02-24 01:08:31','2','320095');
INSERT INTO `office_fix`.`bx_log` (`id`,`bx_no`,`bg_service`,`service_type`,`bgqy`,`yxcd`,`gzms`,`gz_img`,`gz_veo`,`gz_location`,`bx_status`,`admin_remark`,`ys_note`,`fix_remark`,`fix`,`user_id`,`create_time`,`is_delete`,`search_key_word`)  VALUES('5','20260227010831866686','5','空调','3','2','投影仪画面模糊','http://localhost:8080/office_fix/static/images/报修记录图片/1.jpg','http://localhost:8080/office_fix/static/sources/2-1.mp4','B区会议室讲台','4','会议即将开始','投影清晰无异样','清洁镜头并调整焦距','2','6','2026-02-25 01:08:31','2','320095');
INSERT INTO `office_fix`.`bx_log` (`id`,`bx_no`,`bg_service`,`service_type`,`bgqy`,`yxcd`,`gzms`,`gz_img`,`gz_veo`,`gz_location`,`bx_status`,`admin_remark`,`ys_note`,`fix_remark`,`fix`,`user_id`,`create_time`,`is_delete`,`search_key_word`)  VALUES('6','20260227010831120051','6','复印机','3','2','复印机出现异响','http://localhost:8080/office_fix/static/images/报修记录图片/2.jpg','http://localhost:8080/office_fix/static/sources/2-1.mp4','E区财务室角落','4','涉及财务资料安全','异响消失运行平稳','紧固内部螺丝','7','4','2026-02-25 01:08:31','2','320095');
INSERT INTO `office_fix`.`bx_log` (`id`,`bx_no`,`bg_service`,`service_type`,`bgqy`,`yxcd`,`gzms`,`gz_img`,`gz_veo`,`gz_location`,`bx_status`,`admin_remark`,`ys_note`,`fix_remark`,`fix`,`user_id`,`create_time`,`is_delete`,`search_key_word`)  VALUES('7','20260227010831554710','1','台式电脑','6','2','扫描仪无法识别','http://localhost:8080/office_fix/static/images/报修记录图片/3.jpg','http://localhost:8080/office_fix/static/sources/2-1.mp4','G区仓库入口','7','影响入库流程','扫描识别准确','更新驱动程序','2','5','2026-02-24 01:08:31','2','320095');
INSERT INTO `office_fix`.`bx_log` (`id`,`bx_no`,`bg_service`,`service_type`,`bgqy`,`yxcd`,`gzms`,`gz_img`,`gz_veo`,`gz_location`,`bx_status`,`admin_remark`,`ys_note`,`fix_remark`,`fix`,`user_id`,`create_time`,`is_delete`,`search_key_word`)  VALUES('8','20260227010831036767','8','碎纸机','5','2','碎纸机卡住','http://localhost:8080/office_fix/static/images/报修记录图片/4.jpg','http://localhost:8080/office_fix/static/sources/2-1.mp4','F区休息区旁','6','员工休息受影响','碎纸顺畅无卡顿','清除卡纸并复位','5','2','2026-02-25 01:08:31','2','320095');
INSERT INTO `office_fix`.`bx_log` (`id`,`bx_no`,`bg_service`,`service_type`,`bgqy`,`yxcd`,`gzms`,`gz_img`,`gz_veo`,`gz_location`,`bx_status`,`admin_remark`,`ys_note`,`fix_remark`,`fix`,`user_id`,`create_time`,`is_delete`,`search_key_word`)  VALUES('9','20260227010831965896','3','打印机','3','1','电话机无声音','http://localhost:8080/office_fix/static/images/报修记录图片/5.jpg','http://localhost:8080/office_fix/static/sources/2-1.mp4','H区走廊墙壁','6','紧急通讯需要','通话声音清晰','检查线路并更换听筒','3','6','2026-02-22 01:08:31','2','320095');
INSERT INTO `office_fix`.`fix_gc` (`id`,`fix_bz`,`ghpj`,`xhgs`,`fix`,`bxid`,`create_time`)  VALUES('1','检查电源连接','电源适配器','34.5','2','3','2026-02-27 01:08:31');
INSERT INTO `office_fix`.`fix_gc` (`id`,`fix_bz`,`ghpj`,`xhgs`,`fix`,`bxid`,`create_time`)  VALUES('2','打开打印机盖板','搓纸轮','89.8','6','5','2026-02-27 01:08:31');
INSERT INTO `office_fix`.`fix_gc` (`id`,`fix_bz`,`ghpj`,`xhgs`,`fix`,`bxid`,`create_time`)  VALUES('3','检测空调压缩机','制冷剂','57.9','6','6','2026-02-23 01:08:31');
INSERT INTO `office_fix`.`fix_gc` (`id`,`fix_bz`,`ghpj`,`xhgs`,`fix`,`bxid`,`create_time`)  VALUES('4','测试网络端口','网线','92.2','5','7','2026-02-22 01:08:31');
INSERT INTO `office_fix`.`fix_gc` (`id`,`fix_bz`,`ghpj`,`xhgs`,`fix`,`bxid`,`create_time`)  VALUES('5','清洁投影仪镜头','投影灯泡','57.6','5','9','2026-02-23 01:08:31');
INSERT INTO `office_fix`.`fix_gc` (`id`,`fix_bz`,`ghpj`,`xhgs`,`fix`,`bxid`,`create_time`)  VALUES('6','拆卸复印机外壳','定影膜','32.8','6','7','2026-02-23 01:08:31');
INSERT INTO `office_fix`.`fix_gc` (`id`,`fix_bz`,`ghpj`,`xhgs`,`fix`,`bxid`,`create_time`)  VALUES('7','连接扫描仪至电脑','扫描仪数据线','15.5','3','7','2026-02-23 01:08:31');
INSERT INTO `office_fix`.`fix_gc` (`id`,`fix_bz`,`ghpj`,`xhgs`,`fix`,`bxid`,`create_time`)  VALUES('8','清理碎纸机刀片','碎纸机刀片','75.5','1','3','2026-02-22 01:08:31');
INSERT INTO `office_fix`.`fix_gc` (`id`,`fix_bz`,`ghpj`,`xhgs`,`fix`,`bxid`,`create_time`)  VALUES('9','测试电话线路','电话听筒','97.2','2','3','2026-02-27 01:08:31');
INSERT INTO `office_fix`.`zsk` (`id`,`title`,`subtitle`,`zs_content`,`fb_time`,`search_key_word`)  VALUES('1','常见电脑故障解决指南','黑屏死机应对措施','<div><b>&nbsp; &nbsp;</b>这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍。</div><p><b>&nbsp; &nbsp;</b>这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍。</p><h2><br/></h2><h2><br/></h2><h2 id="3lwqw"><br/></h2>
','2026-02-20 01:08:31','320095');
INSERT INTO `office_fix`.`zsk` (`id`,`title`,`subtitle`,`zs_content`,`fb_time`,`search_key_word`)  VALUES('2','打印机日常维护方法','减少卡纸频率技巧','<div><b>&nbsp; &nbsp;</b>这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍。</div><p><b>&nbsp; &nbsp;</b>这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍。</p><h2><br/></h2><h2><br/></h2><h2 id="3lwqw"><br/></h2>
','2026-02-26 01:08:31','320095');
INSERT INTO `office_fix`.`zsk` (`id`,`title`,`subtitle`,`zs_content`,`fb_time`,`search_key_word`)  VALUES('3','空调保养注意事项','延长使用寿命秘诀','<div><b>&nbsp; &nbsp;</b>这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍。</div><p><b>&nbsp; &nbsp;</b>这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍。</p><h2><br/></h2><h2><br/></h2><h2 id="3lwqw"><br/></h2>
','2026-02-20 01:08:31','320095');
INSERT INTO `office_fix`.`zsk` (`id`,`title`,`subtitle`,`zs_content`,`fb_time`,`search_key_word`)  VALUES('4','网络故障排查步骤','快速恢复上网流程','<div><b>&nbsp; &nbsp;</b>这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍。</div><p><b>&nbsp; &nbsp;</b>这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍。</p><h2><br/></h2><h2><br/></h2><h2 id="3lwqw"><br/></h2>
','2026-02-26 01:08:31','320095');
INSERT INTO `office_fix`.`zsk` (`id`,`title`,`subtitle`,`zs_content`,`fb_time`,`search_key_word`)  VALUES('5','投影仪使用技巧','避免画面模糊要点','<div><b>&nbsp; &nbsp;</b>这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍。</div><p><b>&nbsp; &nbsp;</b>这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍。</p><h2><br/></h2><h2><br/></h2><h2 id="3lwqw"><br/></h2>
','2026-02-25 01:08:31','320095');
INSERT INTO `office_fix`.`zsk` (`id`,`title`,`subtitle`,`zs_content`,`fb_time`,`search_key_word`)  VALUES('6','复印机节约耗材建议','降低维修成本策略','<div><b>&nbsp; &nbsp;</b>这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍。</div><p><b>&nbsp; &nbsp;</b>这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍。</p><h2><br/></h2><h2><br/></h2><h2 id="3lwqw"><br/></h2>
','2026-02-22 01:08:31','320095');
INSERT INTO `office_fix`.`zsk` (`id`,`title`,`subtitle`,`zs_content`,`fb_time`,`search_key_word`)  VALUES('7','扫描仪分辨率设置','保证扫描清晰度','<div><b>&nbsp; &nbsp;</b>这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍。</div><p><b>&nbsp; &nbsp;</b>这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍。</p><h2><br/></h2><h2><br/></h2><h2 id="3lwqw"><br/></h2>
','2026-02-25 01:08:31','320095');
INSERT INTO `office_fix`.`zsk` (`id`,`title`,`subtitle`,`zs_content`,`fb_time`,`search_key_word`)  VALUES('8','碎纸机安全操作规范','预防卡纸与过热','<div><b>&nbsp; &nbsp;</b>这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍。</div><p><b>&nbsp; &nbsp;</b>这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍。</p><h2><br/></h2><h2><br/></h2><h2 id="3lwqw"><br/></h2>
','2026-02-27 01:08:31','320095');
INSERT INTO `office_fix`.`zsk` (`id`,`title`,`subtitle`,`zs_content`,`fb_time`,`search_key_word`)  VALUES('9','电话机功能设置说明','内线外线切换教程','<div><b>&nbsp; &nbsp;</b>这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍。</div><p><b>&nbsp; &nbsp;</b>这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍，这是关于知识库的详情介绍。</p><h2><br/></h2><h2><br/></h2><h2 id="3lwqw"><br/></h2>
','2026-02-21 01:08:31','320095');
INSERT INTO `office_fix`.`pj_req` (`id`,`req_no`,`pj_name`,`xy_num`,`req_note`,`req_status`,`check_remark`,`create_time`,`fix`)  VALUES('1','20260227010832203299','电脑内存条','8','电脑运行缓慢需升级','3','库存充足已批准','2026-02-20 01:08:32','5');
INSERT INTO `office_fix`.`pj_req` (`id`,`req_no`,`pj_name`,`xy_num`,`req_note`,`req_status`,`check_remark`,`create_time`,`fix`)  VALUES('2','20260227010832215679','打印机硒鼓','72','打印机缺墨影响工作','2','型号匹配已采购','2026-02-25 01:08:32','7');
INSERT INTO `office_fix`.`pj_req` (`id`,`req_no`,`pj_name`,`xy_num`,`req_note`,`req_status`,`check_remark`,`create_time`,`fix`)  VALUES('3','20260227010832878083','空调滤网','42','空调滤网脏污需更换','1','标准配件已下单','2026-02-23 01:08:32','7');
INSERT INTO `office_fix`.`pj_req` (`id`,`req_no`,`pj_name`,`xy_num`,`req_note`,`req_status`,`check_remark`,`create_time`,`fix`)  VALUES('4','20260227010832554758','网络交换机','55','网络扩展增加端口','2','设备已采购待入库','2026-02-23 01:08:32','4');
INSERT INTO `office_fix`.`pj_req` (`id`,`req_no`,`pj_name`,`xy_num`,`req_note`,`req_status`,`check_remark`,`create_time`,`fix`)  VALUES('5','20260227010832701021','投影仪遥控器','64','遥控器丢失无法操作','1','已联系供应商补发','2026-02-27 01:08:32','3');
INSERT INTO `office_fix`.`pj_req` (`id`,`req_no`,`pj_name`,`xy_num`,`req_note`,`req_status`,`check_remark`,`create_time`,`fix`)  VALUES('6','20260227010832844130','复印机墨粉','46','墨粉不足打印模糊','1','耗材已批准领取','2026-02-24 01:08:32','8');
INSERT INTO `office_fix`.`pj_req` (`id`,`req_no`,`pj_name`,`xy_num`,`req_note`,`req_status`,`check_remark`,`create_time`,`fix`)  VALUES('7','20260227010832454298','扫描仪玻璃板','34','玻璃板有划痕影响扫描','1','需订货预计三天','2026-02-26 01:08:32','4');
INSERT INTO `office_fix`.`pj_req` (`id`,`req_no`,`pj_name`,`xy_num`,`req_note`,`req_status`,`check_remark`,`create_time`,`fix`)  VALUES('8','20260227010832525931','碎纸机收纳袋','19','收纳袋已满急需清理','1','批准领取新袋子','2026-02-25 01:08:32','2');
INSERT INTO `office_fix`.`pj_req` (`id`,`req_no`,`pj_name`,`xy_num`,`req_note`,`req_status`,`check_remark`,`create_time`,`fix`)  VALUES('9','20260227010832764773','电话机手柄','69','手柄损坏无法通话','2','批准更换新手柄','2026-02-20 01:08:32','5');
INSERT INTO `office_fix`.`eval_log` (`id`,`bxid`,`fix`,`eval_star`,`eval_content`,`user_id`,`eval_time`)  VALUES('1','6','7','4','维修迅速态度很好','5','2026-02-23 01:08:32');
INSERT INTO `office_fix`.`eval_log` (`id`,`bxid`,`fix`,`eval_star`,`eval_content`,`user_id`,`eval_time`)  VALUES('2','9','3','1','技术专业解决问题彻底','6','2026-02-22 01:08:32');
INSERT INTO `office_fix`.`eval_log` (`id`,`bxid`,`fix`,`eval_star`,`eval_content`,`user_id`,`eval_time`)  VALUES('3','5','2','4','响应及时服务周到','7','2026-02-26 01:08:32');
INSERT INTO `office_fix`.`eval_log` (`id`,`bxid`,`fix`,`eval_star`,`eval_content`,`user_id`,`eval_time`)  VALUES('4','8','5','2','耐心排查非常负责','1','2026-02-21 01:08:32');
INSERT INTO `office_fix`.`eval_log` (`id`,`bxid`,`fix`,`eval_star`,`eval_content`,`user_id`,`eval_time`)  VALUES('5','3','3','1','效果显著使用顺畅','3','2026-02-23 01:08:32');
INSERT INTO `office_fix`.`eval_log` (`id`,`bxid`,`fix`,`eval_star`,`eval_content`,`user_id`,`eval_time`)  VALUES('6','4','4','1','维修后设备如新','9','2026-02-20 01:08:32');
INSERT INTO `office_fix`.`eval_log` (`id`,`bxid`,`fix`,`eval_star`,`eval_content`,`user_id`,`eval_time`)  VALUES('7','6','7','5','操作规范很放心','7','2026-02-26 01:08:32');
INSERT INTO `office_fix`.`eval_log` (`id`,`bxid`,`fix`,`eval_star`,`eval_content`,`user_id`,`eval_time`)  VALUES('8','5','2','5','细心讲解注意事项','3','2026-02-22 01:08:32');
INSERT INTO `office_fix`.`eval_log` (`id`,`bxid`,`fix`,`eval_star`,`eval_content`,`user_id`,`eval_time`)  VALUES('9','7','2','4','沟通顺畅效率高','2','2026-02-24 01:08:32');
INSERT INTO `office_fix`.`xt_notice` (`id`,`title`,`subtitle`,`notice_content`,`fb_time`,`search_key_word`)  VALUES('1','报修系统升级通知','新增在线预约功能','<div><b>&nbsp; &nbsp;</b>这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍。</div><p><b>&nbsp; &nbsp;</b>这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍。</p><h2><br/></h2><h2><br/></h2><h2 id="3lwqw"><br/></h2>
','2026-02-24 01:08:32','320095');
INSERT INTO `office_fix`.`xt_notice` (`id`,`title`,`subtitle`,`notice_content`,`fb_time`,`search_key_word`)  VALUES('2','夏季空调使用规范','温度设置与节能建议','<div><b>&nbsp; &nbsp;</b>这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍。</div><p><b>&nbsp; &nbsp;</b>这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍。</p><h2><br/></h2><h2><br/></h2><h2 id="3lwqw"><br/></h2>
','2026-02-25 01:08:32','320095');
INSERT INTO `office_fix`.`xt_notice` (`id`,`title`,`subtitle`,`notice_content`,`fb_time`,`search_key_word`)  VALUES('3','网络安全重要提醒','防范钓鱼邮件与病毒','<div><b>&nbsp; &nbsp;</b>这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍。</div><p><b>&nbsp; &nbsp;</b>这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍。</p><h2><br/></h2><h2><br/></h2><h2 id="3lwqw"><br/></h2>
','2026-02-23 01:08:32','320095');
INSERT INTO `office_fix`.`xt_notice` (`id`,`title`,`subtitle`,`notice_content`,`fb_time`,`search_key_word`)  VALUES('4','新设备操作培训安排','时间地点与参与方式','<div><b>&nbsp; &nbsp;</b>这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍。</div><p><b>&nbsp; &nbsp;</b>这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍。</p><h2><br/></h2><h2><br/></h2><h2 id="3lwqw"><br/></h2>
','2026-02-25 01:08:32','320095');
INSERT INTO `office_fix`.`xt_notice` (`id`,`title`,`subtitle`,`notice_content`,`fb_time`,`search_key_word`)  VALUES('5','节约办公耗材倡议','双面打印与墨粉节约','<div><b>&nbsp; &nbsp;</b>这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍。</div><p><b>&nbsp; &nbsp;</b>这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍。</p><h2><br/></h2><h2><br/></h2><h2 id="3lwqw"><br/></h2>
','2026-02-27 01:08:32','320095');
INSERT INTO `office_fix`.`xt_notice` (`id`,`title`,`subtitle`,`notice_content`,`fb_time`,`search_key_word`)  VALUES('6','节假日值班安排','日期人员与联系方式','<div><b>&nbsp; &nbsp;</b>这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍。</div><p><b>&nbsp; &nbsp;</b>这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍。</p><h2><br/></h2><h2><br/></h2><h2 id="3lwqw"><br/></h2>
','2026-02-20 01:08:32','320095');
INSERT INTO `office_fix`.`xt_notice` (`id`,`title`,`subtitle`,`notice_content`,`fb_time`,`search_key_word`)  VALUES('7','维修服务满意度调查','邀请反馈促进改进','<div><b>&nbsp; &nbsp;</b>这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍。</div><p><b>&nbsp; &nbsp;</b>这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍。</p><h2><br/></h2><h2><br/></h2><h2 id="3lwqw"><br/></h2>
','2026-02-22 01:08:32','320095');
INSERT INTO `office_fix`.`xt_notice` (`id`,`title`,`subtitle`,`notice_content`,`fb_time`,`search_key_word`)  VALUES('8','知识库更新日志','新增常见问题解答','<div><b>&nbsp; &nbsp;</b>这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍。</div><p><b>&nbsp; &nbsp;</b>这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍。</p><h2><br/></h2><h2><br/></h2><h2 id="3lwqw"><br/></h2>
','2026-02-23 01:08:32','320095');
INSERT INTO `office_fix`.`xt_notice` (`id`,`title`,`subtitle`,`notice_content`,`fb_time`,`search_key_word`)  VALUES('9','配件申领流程优化','简化审批缩短时间','<div><b>&nbsp; &nbsp;</b>这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍。</div><p><b>&nbsp; &nbsp;</b>这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍，这是关于系统公告的详情介绍。</p><h2><br/></h2><h2><br/></h2><h2 id="3lwqw"><br/></h2>
','2026-02-26 01:08:32','320095');