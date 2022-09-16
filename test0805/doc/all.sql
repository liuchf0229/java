# 新增区划表
drop table if exists `tb_area`;
CREATE TABLE `tb_area`(
`area_id` int(2) NOT NULL AUTO_INCREMENT,
`area_name` varchar(200) NOT NULL,
`priority` int(2) NOT NULL DEFAULT '0',
`create_time` datetime DEFAULT NULL,
`last_edit_time` datetime DEFAULT NULL,
PRIMARY KEY (`area_id`),
UNIQUE KEY `UK_AREA`(`area_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into `ebook` (area_id, area_name, priority, create_time, last_edit_time) values (1, '东苑', 1, '', '');