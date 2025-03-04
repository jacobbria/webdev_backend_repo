CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int AUTO_INCREMENT  PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile_number` varchar(20) NOT NULL,
    `password` varchar(20) NOT NULL

);