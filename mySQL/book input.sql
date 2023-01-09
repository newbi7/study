
USE library;

DROP TABLE IF EXISTS `bookinf`;

CREATE TABLE `bookinf` (
  `pid` varchar(50) NOT NULL,
  `bisbn` varchar(50) NOT NULL,
  `btitle` varchar(50) DEFAULT NULL,
  `bdate` varchar(50) DEFAULT NULL,
  `bpage` int(20) DEFAULT NULL,
  `bprice` int(20) DEFAULT NULL,
  `bauthor` varchar(100) DEFAULT NULL,
  `btranslator` varchar(100) DEFAULT NULL,
  `bsupplement` varchar(100) DEFAULT NULL,
  `bpublisher` varchar(100) DEFAULT NULL,
  `bimgurl` varchar(100) DEFAULT NULL,
  `bborrowdate` int(20) DEFAULT NULL,
  `bpoint` int(20) DEFAULT NULL,
  `bfuturepoint` int(20) DEFAULT NULL,
  `bhave` varchar(20) DEFAULT NULL,

  PRIMARY KEY (`pid`));
  
  INSERT INTO `bookinf` VALUES ('userandae@naever.com', '89-7914-063-0', 'C로 구현한 알고리즘', '2000년 04월', '624', '25000', '카일 루든(Kyle Loudon)','', '', '', '', '20230105', '100', '10', '대여중');