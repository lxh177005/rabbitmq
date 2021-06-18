drop table if exists `message`;
CREATE TABLE `message` (
    `id` int NOT NULL AUTO_INCREMENT,
    `msg_id` varchar(255) NOT NULL COMMENT '消息唯一id',
    `msg_data` varchar(255) NOT NULL COMMENT '消息内容',
    `msg_status` int(1) DEFAULT 0 COMMENT '状态0-发送中1-成功2-失败',
    `try_count` int(1) DEFAULT 0 COMMENT '重试次数',
    `creat_time` timestamp NULL DEFAULT NULL,
    `update_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `msg_id_unique` (`msg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;

drop table if exists `record_message`;
CREATE TABLE `record_message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `msg_id` varchar(255) DEFAULT NULL COMMENT '消息id',
  `exchange` varchar(255) DEFAULT NULL COMMENT '交换机',
  `routing_key` varchar(255) DEFAULT NULL COMMENT '路由键',
  `try_count` int(1) DEFAULT 0 COMMENT '重试次数',
  `status` int(1) DEFAULT 0  COMMENT '状态0-发送中1-成功2-失败',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `msg_id_unique` (`msg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;