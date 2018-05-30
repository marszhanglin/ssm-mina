drop table if exit sms_log;
CREATE TABLE IF NOT EXISTS `sms_log`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `netonline_status` VARCHAR(1) NOT NULL,
   `sender` VARCHAR(40) NOT NULL,
   `receiver` VARCHAR(40) NOT NULL,
   `session_id` VARCHAR(40) NOT NULL,
   `connect_type` VARCHAR(40) NOT NULL,
   `create_time` DATE,
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table sms_log add body VARCHAR(2000);


CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
  `iosession_id` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


alter table users add netonline_status boolean;
ALTER TABLE users MODIFY COLUMN netonline_status VARCHAR(64); 
-- 添加唯一键
alter table users add unique key(name);

