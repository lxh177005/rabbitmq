drop table message;
CREATE TABLE `record_message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `msg_id` varchar(255) DEFAULT NULL COMMENT '消息id',
  `exchange` varchar(255) DEFAULT NULL COMMENT '交换机',
  `routing_key` varchar(255) DEFAULT NULL COMMENT '路由键',
  `try_count` varchar(1) DEFAULT NULL COMMENT '重试次数',
  `status` int DEFAULT NULL COMMENT '状态 0-发送中 1-成功 2-失败',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `msg_id_unique` (`msg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;

drop table record_message;
CREATE TABLE `record_message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `msg_id` varchar(255) DEFAULT NULL COMMENT '消息id',
  `exchange` varchar(255) DEFAULT NULL COMMENT '交换机',
  `routing_key` varchar(255) DEFAULT NULL COMMENT '路由键',
  `try_count` varchar(1) DEFAULT NULL COMMENT '重试次数',
  `status` int DEFAULT NULL COMMENT '状态 0-发送中 1-成功 2-失败',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `msg_id_unique` (`msg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;