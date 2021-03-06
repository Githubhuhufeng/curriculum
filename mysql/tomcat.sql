-- phpMyAdmin SQL Dump
-- version 4.6.0
-- http://www.phpmyadmin.net
--
-- Host: db
-- Generation Time: May 09, 2016 at 05:11 PM
-- Server version: 5.6.27
-- PHP Version: 5.6.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tomcat`
--

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `id` int(12) NOT NULL,
  `name` varchar(255) NOT NULL,
  `teacherID` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `credit` int(4) NOT NULL,
  `dept` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`id`, `name`, `teacherID`, `location`, `credit`, `dept`) VALUES
(1, '微積分', '1', '資電B22', 2, '資訊系'),
(2, '線性代數', '2', '資電311', 3, '資訊系'),
(3, '計算機概論', '3', '資電243', 4, '資訊系');

-- --------------------------------------------------------

--
-- Table structure for table `class_time`
--

CREATE TABLE `class_time` (
  `class_id` int(12) NOT NULL,
  `time_id` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `class_time`
--

INSERT INTO `class_time` (`class_id`, `time_id`) VALUES
(1, '1080'),
(1, '1081'),
(1, '1090'),
(1, '1091'),
(1, '1100'),
(1, '1101'),
(1, '5110'),
(1, '5111'),
(1, '5120'),
(1, '5121'),
(2, '1130'),
(2, '1131'),
(2, '1140'),
(2, '1141'),
(2, '1150'),
(2, '1151'),
(2, '3110'),
(2, '3111'),
(2, '3120'),
(2, '3121'),
(3, '2100'),
(3, '2101'),
(3, '2110'),
(3, '2111'),
(3, '2120'),
(3, '2121'),
(3, '4090'),
(3, '4091'),
(3, '4100'),
(3, '4101'),
(3, '4110'),
(3, '4111');

-- --------------------------------------------------------

--
-- Table structure for table `curriculum`
--

CREATE TABLE `curriculum` (
  `userID` int(12) NOT NULL,
  `classID` int(12) NOT NULL,
  `timestamp` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `curriculum`
--

INSERT INTO `curriculum` (`userID`, `classID`, `timestamp`) VALUES
(9, 1, '2016-04-20 20:30:02'),
(9, 2, '2016-04-20 20:30:08');

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `teacherID` varchar(20) NOT NULL,
  `teacher_Name` varchar(256) NOT NULL,
  `teacher_Email` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`teacherID`, `teacher_Name`, `teacher_Email`) VALUES
('T00001', '陳錫民', 'T00001@fcu.edu.tw'),
('T00002', '王老五', 'T00002@fcu.edu.tw'),
('T00003', '黃小明', 'T00003@fcu.edu.tw');

-- --------------------------------------------------------

--
-- Table structure for table `teach_class`
--

CREATE TABLE `teach_class` (
  `teacherID` varchar(20) NOT NULL,
  `classID` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `teach_class`
--

INSERT INTO `teach_class` (`teacherID`, `classID`) VALUES
('T00001', '01'),
('T00001', '02'),
('T00002', '03'),
('T00003', '04'),
('T00001', '04');

-- --------------------------------------------------------

--
-- Table structure for table `time_reference`
--

CREATE TABLE `time_reference` (
  `time_id` varchar(4) NOT NULL,
  `week` tinyint(4) NOT NULL,
  `section` tinyint(4) NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `time_reference`
--

INSERT INTO `time_reference` (`time_id`, `week`, `section`, `start_time`, `end_time`) VALUES
('1080', 1, 1, '08:00:00', '08:30:00'),
('1081', 1, 1, '08:30:00', '09:00:00'),
('1090', 1, 2, '09:00:00', '09:30:00'),
('1091', 1, 2, '09:30:00', '10:00:00'),
('1100', 1, 3, '10:00:00', '10:30:00'),
('1101', 1, 3, '10:30:00', '11:00:00'),
('1110', 1, 4, '11:00:00', '11:30:00'),
('1111', 1, 4, '11:30:00', '12:00:00'),
('1120', 1, 5, '12:00:00', '12:30:00'),
('1121', 1, 5, '12:30:00', '13:00:00'),
('1130', 1, 6, '13:00:00', '13:30:00'),
('1131', 1, 6, '13:30:00', '14:00:00'),
('1140', 1, 7, '14:00:00', '14:30:00'),
('1141', 1, 7, '14:30:00', '15:00:00'),
('1150', 1, 8, '15:00:00', '15:30:00'),
('1151', 1, 8, '15:30:00', '16:00:00'),
('1160', 1, 9, '16:00:00', '16:30:00'),
('1161', 1, 9, '16:30:00', '17:00:00'),
('1170', 1, 10, '17:00:00', '17:30:00'),
('1171', 1, 10, '17:30:00', '18:00:00'),
('1180', 1, 11, '18:00:00', '18:30:00'),
('1181', 1, 11, '18:30:00', '19:00:00'),
('1190', 1, 12, '19:00:00', '19:30:00'),
('1191', 1, 12, '19:30:00', '20:00:00'),
('1200', 1, 13, '20:00:00', '20:30:00'),
('1201', 1, 13, '20:30:00', '21:00:00'),
('1210', 1, 14, '21:00:00', '21:30:00'),
('1211', 1, 14, '21:30:00', '22:00:00'),
('2080', 2, 1, '08:00:00', '08:30:00'),
('2081', 2, 1, '08:30:00', '09:00:00'),
('2090', 2, 2, '09:00:00', '09:30:00'),
('2091', 2, 2, '09:30:00', '10:00:00'),
('2100', 2, 3, '10:00:00', '10:30:00'),
('2101', 2, 3, '10:30:00', '11:00:00'),
('2110', 2, 4, '11:00:00', '11:30:00'),
('2111', 2, 4, '11:30:00', '12:00:00'),
('2120', 2, 5, '12:00:00', '12:30:00'),
('2121', 2, 5, '12:30:00', '13:00:00'),
('2130', 2, 6, '13:00:00', '13:30:00'),
('2131', 2, 6, '13:30:00', '14:00:00'),
('2140', 2, 7, '14:00:00', '14:30:00'),
('2141', 2, 7, '14:30:00', '15:00:00'),
('2150', 2, 8, '15:00:00', '15:30:00'),
('2151', 2, 8, '15:30:00', '16:00:00'),
('2160', 2, 9, '16:00:00', '16:30:00'),
('2161', 2, 9, '16:30:00', '17:00:00'),
('2170', 2, 10, '17:00:00', '17:30:00'),
('2171', 2, 10, '17:30:00', '18:00:00'),
('2180', 2, 11, '18:00:00', '18:30:00'),
('2181', 2, 11, '18:30:00', '19:00:00'),
('2190', 2, 12, '19:00:00', '19:30:00'),
('2191', 2, 12, '19:30:00', '20:00:00'),
('2200', 2, 13, '20:00:00', '20:30:00'),
('2201', 2, 13, '20:30:00', '21:00:00'),
('2210', 2, 14, '21:00:00', '21:30:00'),
('2211', 2, 14, '21:30:00', '22:00:00'),
('3080', 3, 1, '08:00:00', '08:30:00'),
('3081', 3, 1, '08:30:00', '09:00:00'),
('3090', 3, 2, '09:00:00', '09:30:00'),
('3091', 3, 2, '09:30:00', '10:00:00'),
('3100', 3, 3, '10:00:00', '10:30:00'),
('3101', 3, 3, '10:30:00', '11:00:00'),
('3110', 3, 4, '11:00:00', '11:30:00'),
('3111', 3, 4, '11:30:00', '12:00:00'),
('3120', 3, 5, '12:00:00', '12:30:00'),
('3121', 3, 5, '12:30:00', '13:00:00'),
('3130', 3, 6, '13:00:00', '13:30:00'),
('3131', 3, 6, '13:30:00', '14:00:00'),
('3140', 3, 7, '14:00:00', '14:30:00'),
('3141', 3, 7, '14:30:00', '15:00:00'),
('3150', 3, 8, '15:00:00', '15:30:00'),
('3151', 3, 8, '15:30:00', '16:00:00'),
('3160', 3, 9, '16:00:00', '16:30:00'),
('3161', 3, 9, '16:30:00', '17:00:00'),
('3170', 3, 10, '17:00:00', '17:30:00'),
('3171', 3, 10, '17:30:00', '18:00:00'),
('3180', 3, 11, '18:00:00', '18:30:00'),
('3181', 3, 11, '18:30:00', '19:00:00'),
('3190', 3, 12, '19:00:00', '19:30:00'),
('3191', 3, 12, '19:30:00', '20:00:00'),
('3200', 3, 13, '20:00:00', '20:30:00'),
('3201', 3, 13, '20:30:00', '21:00:00'),
('3210', 3, 14, '21:00:00', '21:30:00'),
('3211', 3, 14, '21:30:00', '22:00:00'),
('4080', 4, 1, '08:00:00', '08:30:00'),
('4081', 4, 1, '08:30:00', '09:00:00'),
('4090', 4, 2, '09:00:00', '09:30:00'),
('4091', 4, 2, '09:30:00', '10:00:00'),
('4100', 4, 3, '10:00:00', '10:30:00'),
('4101', 4, 3, '10:30:00', '11:00:00'),
('4110', 4, 4, '11:00:00', '11:30:00'),
('4111', 4, 4, '11:30:00', '12:00:00'),
('4120', 4, 5, '12:00:00', '12:30:00'),
('4121', 4, 5, '12:30:00', '13:00:00'),
('4130', 4, 6, '13:00:00', '13:30:00'),
('4131', 4, 6, '13:30:00', '14:00:00'),
('4140', 4, 7, '14:00:00', '14:30:00'),
('4141', 4, 7, '14:30:00', '15:00:00'),
('4150', 4, 8, '15:00:00', '15:30:00'),
('4151', 4, 8, '15:30:00', '16:00:00'),
('4160', 4, 9, '16:00:00', '16:30:00'),
('4161', 4, 9, '16:30:00', '17:00:00'),
('4170', 4, 10, '17:00:00', '17:30:00'),
('4171', 4, 10, '17:30:00', '18:00:00'),
('4180', 4, 11, '18:00:00', '18:30:00'),
('4181', 4, 11, '18:30:00', '19:00:00'),
('4190', 4, 12, '19:00:00', '19:30:00'),
('4191', 4, 12, '19:30:00', '20:00:00'),
('4200', 4, 13, '20:00:00', '20:30:00'),
('4201', 4, 13, '20:30:00', '21:00:00'),
('4210', 4, 14, '21:00:00', '21:30:00'),
('4211', 4, 14, '21:30:00', '22:00:00'),
('5080', 5, 1, '08:00:00', '08:30:00'),
('5081', 5, 1, '08:30:00', '09:00:00'),
('5090', 5, 2, '09:00:00', '09:30:00'),
('5091', 5, 2, '09:30:00', '10:00:00'),
('5100', 5, 3, '10:00:00', '10:30:00'),
('5101', 5, 3, '10:30:00', '11:00:00'),
('5110', 5, 4, '11:00:00', '11:30:00'),
('5111', 5, 4, '11:30:00', '12:00:00'),
('5120', 5, 5, '12:00:00', '12:30:00'),
('5121', 5, 5, '12:30:00', '13:00:00'),
('5130', 5, 6, '13:00:00', '13:30:00'),
('5131', 5, 6, '13:30:00', '14:00:00'),
('5140', 5, 7, '14:00:00', '14:30:00'),
('5141', 5, 7, '14:30:00', '15:00:00'),
('5150', 5, 8, '15:00:00', '15:30:00'),
('5151', 5, 8, '15:30:00', '16:00:00'),
('5160', 5, 9, '16:00:00', '16:30:00'),
('5161', 5, 9, '16:30:00', '17:00:00'),
('5170', 5, 10, '17:00:00', '17:30:00'),
('5171', 5, 10, '17:30:00', '18:00:00'),
('5180', 5, 11, '18:00:00', '18:30:00'),
('5181', 5, 11, '18:30:00', '19:00:00'),
('5190', 5, 12, '19:00:00', '19:30:00'),
('5191', 5, 12, '19:30:00', '20:00:00'),
('5200', 5, 13, '20:00:00', '20:30:00'),
('5201', 5, 13, '20:30:00', '21:00:00'),
('5210', 5, 14, '21:00:00', '21:30:00'),
('5211', 5, 14, '21:30:00', '22:00:00'),
('6080', 6, 1, '08:00:00', '08:30:00'),
('6081', 6, 1, '08:30:00', '09:00:00'),
('6090', 6, 2, '09:00:00', '09:30:00'),
('6091', 6, 2, '09:30:00', '10:00:00'),
('6100', 6, 3, '10:00:00', '10:30:00'),
('6101', 6, 3, '10:30:00', '11:00:00'),
('6110', 6, 4, '11:00:00', '11:30:00'),
('6111', 6, 4, '11:30:00', '12:00:00'),
('6120', 6, 5, '12:00:00', '12:30:00'),
('6121', 6, 5, '12:30:00', '13:00:00'),
('6130', 6, 6, '13:00:00', '13:30:00'),
('6131', 6, 6, '13:30:00', '14:00:00'),
('6140', 6, 7, '14:00:00', '14:30:00'),
('6141', 6, 7, '14:30:00', '15:00:00'),
('6150', 6, 8, '15:00:00', '15:30:00'),
('6151', 6, 8, '15:30:00', '16:00:00'),
('6160', 6, 9, '16:00:00', '16:30:00'),
('6161', 6, 9, '16:30:00', '17:00:00'),
('6170', 6, 10, '17:00:00', '17:30:00'),
('6171', 6, 10, '17:30:00', '18:00:00'),
('6180', 6, 11, '18:00:00', '18:30:00'),
('6181', 6, 11, '18:30:00', '19:00:00'),
('6190', 6, 12, '19:00:00', '19:30:00'),
('6191', 6, 12, '19:30:00', '20:00:00'),
('6200', 6, 13, '20:00:00', '20:30:00'),
('6201', 6, 13, '20:30:00', '21:00:00'),
('6210', 6, 14, '21:00:00', '21:30:00'),
('6211', 6, 14, '21:30:00', '22:00:00'),
('7080', 7, 1, '08:00:00', '08:30:00'),
('7081', 7, 1, '08:30:00', '09:00:00'),
('7090', 7, 2, '09:00:00', '09:30:00'),
('7091', 7, 2, '09:30:00', '10:00:00'),
('7100', 7, 3, '10:00:00', '10:30:00'),
('7101', 7, 3, '10:30:00', '11:00:00'),
('7110', 7, 4, '11:00:00', '11:30:00'),
('7111', 7, 4, '11:30:00', '12:00:00'),
('7120', 7, 5, '12:00:00', '12:30:00'),
('7121', 7, 5, '12:30:00', '13:00:00'),
('7130', 7, 6, '13:00:00', '13:30:00'),
('7131', 7, 6, '13:30:00', '14:00:00'),
('7140', 7, 7, '14:00:00', '14:30:00'),
('7141', 7, 7, '14:30:00', '15:00:00'),
('7150', 7, 8, '15:00:00', '15:30:00'),
('7151', 7, 8, '15:30:00', '16:00:00'),
('7160', 7, 9, '16:00:00', '16:30:00'),
('7161', 7, 9, '16:30:00', '17:00:00'),
('7170', 7, 10, '17:00:00', '17:30:00'),
('7171', 7, 10, '17:30:00', '18:00:00'),
('7180', 7, 11, '18:00:00', '18:30:00'),
('7181', 7, 11, '18:30:00', '19:00:00'),
('7190', 7, 12, '19:00:00', '19:30:00'),
('7191', 7, 12, '19:30:00', '20:00:00'),
('7200', 7, 13, '20:00:00', '20:30:00'),
('7201', 7, 13, '20:30:00', '21:00:00'),
('7210', 7, 14, '21:00:00', '21:30:00'),
('7211', 7, 14, '21:30:00', '22:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(12) NOT NULL,
  `email` varchar(64) NOT NULL,
  `name` varchar(255) NOT NULL,
  `encrypted_password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `name`, `encrypted_password`) VALUES
(1, 'usd@fcu.edu.tw', 'yku', '12356'),
(2, 'ed@fcu.edu.tw', 're', '*EBEFE38F24D7BF6DDD422948D227E9EB3C94527C'),
(3, '', '', ''),
(4, '', '', ''),
(5, '', 'a', ''),
(6, '', '', ''),
(7, '', '', ''),
(8, 'v', 'vv', '*06514050A69C71A34E911324DF6B1F8ED71FC196'),
(9, '1', '1', '*E6CC90B878B948C35E92B003C792C46C58C4AF40'),
(10, 'a', 'a', '*667F407DE7C6AD07358FA38DAED7828A72014B4E');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `class_time`
--
ALTER TABLE `class_time`
  ADD PRIMARY KEY (`class_id`,`time_id`);

--
-- Indexes for table `curriculum`
--
ALTER TABLE `curriculum`
  ADD PRIMARY KEY (`userID`,`classID`),
  ADD KEY `classID` (`classID`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacherID`);

--
-- Indexes for table `time_reference`
--
ALTER TABLE `time_reference`
  ADD PRIMARY KEY (`time_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `class`
--
ALTER TABLE `class`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `class_time`
--
ALTER TABLE `class_time`
  ADD CONSTRAINT `class_time_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`);

--
-- Constraints for table `curriculum`
--
ALTER TABLE `curriculum`
  ADD CONSTRAINT `curriculum_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `curriculum_ibfk_2` FOREIGN KEY (`classID`) REFERENCES `class` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
