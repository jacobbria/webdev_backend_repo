CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int AUTO_INCREMENT  PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile_number` varchar(20) NOT NULL,
    `password` varchar(20) NOT NULL,
     `username` varchar(20) NOT NULL


);