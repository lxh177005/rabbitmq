drop table message;
CREATE TABLE `message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `msg_id` varchar(255) NOT NULL,
  `msg_data` varchar(255) NOT NULL,
  `msg_status` varchar(1) DEFAULT NULL COMMENT '状态',
  `creat_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;