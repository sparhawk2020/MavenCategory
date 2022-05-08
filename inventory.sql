-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 08, 2022 at 03:06 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.2.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inventory`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

use heroku_6e2dac38cfc86ba;

drop table if exists `category`;

CREATE TABLE `category` (
  `catcode` varchar(5) NOT NULL,
  `catdesc` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`catcode`, `catdesc`) VALUES
('1001', 'Kids section'),
('102', 'Kitchenware'),
('103', 'Toiletries'),
('104', 'Gift Section'),
('811D', 'Jaming x'),
('ddd', 'dddd');

-- --------------------------------------------------------

--
-- Table structure for table `category2`
--

CREATE TABLE `category2` (
  `catcode` varchar(5) NOT NULL,
  `catdesc` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category2`
--

INSERT INTO `category2` (`catcode`, `catdesc`) VALUES
('155', 'School Supplies'),
('155', 'School Supplies');

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `catcode` varchar(30) NOT NULL,
  `itemdesc` varchar(30) NOT NULL,
  `itemcode` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`catcode`, `itemdesc`, `itemcode`) VALUES
('1001', 'Jammers', 'JAA01'),
('1002', 'Jokers', 'JAA2'),
('1001', 'klop', 'JAA02');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
