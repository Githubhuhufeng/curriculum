-- phpMyAdmin SQL Dump
-- version 4.6.0
-- http://www.phpmyadmin.net
--
-- Host: db
-- Generation Time: Apr 19, 2016 at 02:16 PM
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
  `time` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `credit` int(4) NOT NULL,
  `dept` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`id`, `name`, `teacherID`, `time`, `location`, `credit`, `dept`) VALUES
(1, '微積分', '1', 'Mon:08-10, Fri:11-12', '資電B22', 2, '資訊系'),
(2, '線性代數', '2', 'Mon:13-15, Wed:11-12', '資電311', 3, '資訊系'),
(3, '計算機概論', '3', 'Tue:10-12, Thu: 09-11', '資電243', 4, '資訊系');

-- --------------------------------------------------------

--
-- Table structure for table `curriculum`
--

CREATE TABLE `curriculum` (
  `userID` int(12) NOT NULL,
  `classID` int(12) NOT NULL,
  `timestamp` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
(9, '1', '1', '*E6CC90B878B948C35E92B003C792C46C58C4AF40');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `curriculum`
--
ALTER TABLE `curriculum`
  ADD PRIMARY KEY (`userID`,`classID`),
  ADD KEY `classID` (`classID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `curriculum`
--
ALTER TABLE `curriculum`
  ADD CONSTRAINT `curriculum_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `curriculum_ibfk_2` FOREIGN KEY (`classID`) REFERENCES `class` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
