
USE library;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `pid` varchar(50) NOT NULL,
  `ppw` varchar(50) DEFAULT NULL,
  `pname` varchar(20) DEFAULT NULL,
  `pssnumber` varchar(20) DEFAULT NULL,
  `pphone` varchar(20) DEFAULT NULL,
  `pbnumber` int(20) DEFAULT NULL,
  `ppoint` int(20) DEFAULT NULL,
  `ptier` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`pid`));
  
  INSERT INTO `user` VALUES ('admin', 'admin', '정범수', 12, 01010001000, 10, 10, '관리자'),
  ('','','','1','1','1','1','1'),
   ('pid','ppw','pname','pssnumber','111','333','22','ptier');