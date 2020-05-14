-- create database ssm charset utf-8;

DROP TABLE IF EXISTS `tb_customer`;

CREATE TABLE `tb_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `telephone` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;