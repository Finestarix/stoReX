-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 06, 2020 at 12:30 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `javah2bp`
--
CREATE DATABASE IF NOT EXISTS `javah2bp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `javah2bp`;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` varchar(40) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `price`, `quantity`, `created_at`, `updated_at`) VALUES
('074c2690-8264-470e-b197-7bf4e1c45ad7', 'Brined Iron Parrot', 25914, 1, '2020-06-01 10:08:06', '2020-06-06 17:26:00'),
('09a0ec00-2b9d-4702-b4ef-aec8a0efb8f0', 'Fire-Grilled Chaos Cheetah', 17882, 30, '2020-06-01 10:08:06', '2020-06-06 17:26:53'),
('0aa44877-7d04-4809-b37f-997e688af9ac', 'Korerula Buns', 44777, 86, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('0aae9a78-eba7-45cc-a80d-33f45ea4bd29', 'Fume Mustard & Thyme Genoise', 4460, 19, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('0cd94f4f-0bc8-43fd-862b-3944b67a47e6', 'Pressure-Fried Thunder Hamster', 165, 39, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('0d1058a5-39fd-4133-8ce6-bd7a3d209d64', 'Simmered Nightmare Toad', 1518, 47, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('0df3b4f8-0780-4c84-979b-f37f2ef779b8', 'Salt Mustard & Garlic Milk', 15755, 43, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('0e0f2790-10a2-44a9-ab7c-90b871619a45', 'Fluffy Peach & Vinegar Pie', 23254, 98, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('0ecbd3bc-9aae-430d-b2eb-e8ce58c7f7a0', 'Oshakra Buns', 5852, 74, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('0f80fd29-25bf-4ab4-a84b-7c15b748650b', 'Chusipaya Cake', 5414, 6, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('10c8e821-1e2e-43b5-aa43-4c343e53062c', 'Slow-Cooked Timeless Chinchilla', 3414, 3, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('13ac5982-1822-4040-864d-e3765318a035', 'Zuweacory Cake', 14538, 92, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('13f8d181-d55f-4a1f-b82e-27c2ee6c3dea', 'Cave Souther-Style Cookies', 1820, 55, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('147fa407-361f-4baf-adae-d57de31033d2', 'Stuffed Giant Prawn', 24345, 72, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('1520750b-c942-4988-b2b2-dcead079fbdc', 'Oven-Grilled River Affenpinscher', 74460, 46, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('1726f312-0bad-4c09-b230-0b59ea75c41a', 'Gentle-Fried Mammoth Tropicbird', 65920, 17, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('198c26a3-c0f8-419e-a1e4-ac5e307c3193', 'Deep-Fried Marsh Katydid', 6968, 100, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('1adce307-e41f-43b8-b591-e1487e048f88', 'Slow-Cooked Falchion Capybara', 8357, 3, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('1c78a45d-4148-4733-9342-b9c2b5f0a413', 'Stralealal Bombe', 2885, 96, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('1d043c6c-424e-4f6e-9fac-bf88ad49239b', 'Marinated Grand Albatross', 53259, 47, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('1d608a12-819d-41aa-8018-06949b9e03e5', 'Fried Pulse Hamster', 4831, 6, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('25b65adc-3e5d-4599-a7fd-230381dce980', 'Oqarola Buns', 78720, 81, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('27a3a3c9-5f09-48ce-bfa1-10109de89161', 'Manticore Mooncake', 53297, 94, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('2983b411-dcbf-4223-ace5-dbdb5f116981', 'Stewed Silk Budgerigar', 31591, 26, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('2d1065f4-2a5b-4b7c-87c6-67354ead7202', 'Stir-Fried Infernal Goat', 11007, 80, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('2e8a5f53-62df-4f65-97a4-2bdbca5cd043', 'Smoke Sour Yogurt', 776, 30, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('2fffff9f-c12d-41bd-9ebd-7742ad48ec33', 'Basilisk Fruit Salad', 3728, 55, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('32f4bc81-6481-44c9-a21f-2f13dd31f488', 'Wolpertinger Toffee', 38676, 86, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('38344ecf-e04e-4771-8a9f-85f19a20f513', 'Baked Night Havanese', 22569, 15, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('39efbbd7-f94e-4395-9afa-b02e62d084b6', 'Dragonturtle Doughnut', 112, 42, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('3c14334b-3dbb-4fc0-9518-192c09c64b88', 'Basilisk Yogurt', 15176, 46, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('3dbddbcd-121f-430e-9db8-bd0d7e5c4f29', 'Unicorn Doughnut', 54053, 90, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('3dc6ad5f-6821-45d1-ae32-608ca2af4a07', 'Sautéed Light Polar Bear', 7406, 30, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('3e5ae57a-9997-465a-addf-d1c89f55a164', 'Roc Yogurt', 14071, 27, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('3e6b911a-3ca8-420a-81ae-d9e1916c2693', 'Dried Hell Gerbil', 18803, 6, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('426dc478-d637-4a88-8f87-49646b7676a6', 'Istraccoli Bread', 31613, 64, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('463819dc-f06b-4f99-8a0a-4d3a0a9dd9d4', 'Stone Apples & Walnut Split', 2649, 95, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('467792c1-0328-4ed2-93e8-8be58670069d', 'Oven-Grilled Frost Siamese', 14786, 57, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('46cc1b09-cf49-4526-91b6-8efa98b05348', 'Storm Easter-Style Cobbler', 6344, 100, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('47cb9bcb-b1b7-4b67-8480-7196ad9b98b4', 'Baked Cave Chicken', 9543, 96, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('49133a3a-d1c8-4b91-ac7d-8686b46663f6', 'Kelpie Ice Cream', 36896, 7, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('497e0b7a-34ba-4002-ac53-d395ff7f8ddf', 'Basilisk Crispies', 17881, 71, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('4e5fa8b4-4edb-4d0b-94c5-43fae9f0b797', 'Baked Silk Chinchilla', 4471, 55, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('50484bf2-6139-414e-b93f-f266e9804357', 'Baked Great Rattlesnake', 73555, 81, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('522d2d1e-37fc-42ca-8756-a115a43ae3d6', 'Stuffed Grand Chameleon', 43094, 60, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('53207117-ab94-45bf-9f5b-d7bfe6eed3ce', 'Silk Nuts & Whip', 47281, 76, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('54271194-7349-4e62-a675-f590ba0f83ba', 'Fried Boulder Cuscus', 23073, 2, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('5d5f1e36-84f0-4cb6-83d5-dc1125a0298a', 'Drake Sorbet', 11758, 33, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('609e835c-96c5-49ed-a0c5-d2324735ad2e', 'Cured Fire Wapiti', 12842, 48, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('6188f21d-481a-4bdc-8748-d515aaeabbb6', 'Broasted Rain Quoll', 48451, 6, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('62c65c09-00ff-4f42-8941-ac47762fb2e5', 'Dry-Roasted Scythe Mastigodryas', 45541, 39, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('6337f934-8536-4962-a19d-b1d137c6d43f', 'Minotaur Cake', 54552, 76, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('64faf7c2-9a6c-4c63-aea4-dac1e600a8b0', 'Moon Rabbit Cookies', 4242, 72, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('65335e01-0d88-4f27-a317-f64c20938144', 'Broasted Nightmare Waterbuck', 3569, 48, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('67427be2-5171-4712-93b9-88a9a5aebd32', 'Fog Hot & Spicy Cake', 31697, 9, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('69a4921a-1245-4808-b9cb-22e27d4e776a', 'Drikkadarin Bread', 12205, 19, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('6b599a30-7c76-4815-9fe3-e5f0d4be2ee1', 'Marsh Souther-Style Custard', 19916, 25, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('6d7a004f-d909-47f1-8d59-82f4fed242d2', 'Fruit Aspargus Pud', 51814, 20, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('6dfe26b5-def2-40ee-91a2-18edb5d8662d', 'Pickled Stone Hartebeest', 7785, 67, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('7258cbf9-8a9e-49ae-b448-ec063317be0e', 'Braised Planar Opossum', 8711, 20, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('741ff7d3-7deb-42e9-83d7-2114140228be', 'Ikliayote Bread', 7419, 50, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('75216f17-1c54-4ba0-ac48-d63569a8fb5c', 'Iggumble Bread', 19661, 61, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('76d7f3b7-dde7-444f-9681-65bca6d3fcdb', 'Barbecued Mountain Coati', 11031, 6, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('779dfdac-1fdd-4009-b731-4b32162e1c3e', 'Broasted Scent Gazelle', 56682, 15, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('7981b33d-769c-4442-bdf7-d2f79bdc3e6e', 'Shaggiaku Bombe', 13845, 36, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('7c4ea7cb-f3d3-485f-a21e-6add570b6b00', 'Iphard Bread', 31983, 55, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('7c99e576-a5a7-458f-97f7-afd12f023178', 'Horrekin Buns', 3461, 50, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('7df09db1-9d77-474f-ac55-b6b593ea4b51', 'Baphumond Bombe', 31453, 63, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('7ea6b7fa-9e04-410b-b1fe-bcc88d73d9e2', 'Fire-Roasted Abyss Chihuahua', 3441, 4, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('7f3cc384-aad7-4d62-9b82-f00f9fff20cd', 'Bunyip Pudding', 5364, 80, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('7f89a1d2-d41c-4bbc-bd36-89b710c01af3', 'Hell Fennel Toffee', 45186, 15, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('7fb6eee8-198b-437f-905e-cc4bbfc97552', 'Fatato Bombe', 32523, 34, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('831caeb9-e830-474a-a1ef-faedf8fcd43e', 'Iron Sweet & Fresh Molten Cake', 5578, 50, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('834fcc81-ab40-4a0b-a1f1-f669c918fc4e', 'Bugbear Jelly', 7334, 83, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('84979aec-fa8b-4115-ad93-dcce19a64590', 'Gentle-Fried Lunar Gar', 35525, 65, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('84c0ece6-a9da-4a7c-b3cb-f544b4e1dde8', 'Droggerola Buns', 15011, 29, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('84c14fa2-8a26-4210-a242-e2112d736ffc', 'Void Fennel & Lime Bonbons', 51926, 52, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('8534b428-12a8-4866-a9e2-a7cd344ab0af', 'Fire-Grilled Sun Sparrowhawk', 9252, 37, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('88f99a58-cad3-425d-a005-d906c6d41bce', 'Jackalope Pancakes', 1622, 51, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('891412f9-01ad-4fa2-902a-59c991344061', 'Steamed Aether Lemur', 704, 81, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('8b5f57e0-334e-496b-b1d6-2faa1011fbeb', 'Cooked Shadow Boar', 46849, 64, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('8c765067-7191-4f8c-8784-1edeb6d2b9bb', 'Classiango Bombe', 22666, 32, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('8ca353d7-c597-4947-ae34-197b1b53a5ce', 'Basted Ethereal Rottweiler', 36809, 19, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('9055627c-cb2e-490c-8137-08945e5adefc', 'Mostriamato Buns', 25287, 6, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('912f9d66-db97-424b-a945-a59494c799e0', 'Cooked Stone Axolot', 1762, 32, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('9af14699-9fe2-41bc-9861-8c44861a9422', 'Qigrile Bread', 10396, 83, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('9bcc6051-1b45-4b88-9a38-c9b4c33d5f3e', 'Fried Ethereal Sheepdog', 4657, 53, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('9db3a405-51c9-40fa-9aa1-201006283272', 'Manticore Sorbet', 30936, 98, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('9e696734-5bc6-4f7a-a307-c3cfa6e95a75', 'Cockatrice Gingerbread', 46473, 97, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('a0b32103-118d-40a6-989e-dce0870a42d5', 'Fluffy Honey Fudge', 58311, 62, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('a313f012-1771-4dfe-9a7a-8804c813f488', 'Placrerant Bombe', 69442, 82, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('a4ec3e31-c901-4dba-bf78-0b02dc076e0e', 'Hixotillo Bread', 16592, 62, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('a715edc3-0357-4e48-8b0d-ef4d9a52d261', 'Griffin Bombe', 517, 47, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('a7c4058d-ddd2-48cb-8550-c484f039886d', 'Cooked Fluffy Marmot', 4299, 84, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('a7dd4ed4-bb61-4836-af33-56f5b6d52b5f', 'Shade Basil & Lime Jelly', 10786, 8, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('a829cd9c-d906-4e92-ae12-56e02022fd85', 'Shallow-Fried Great Robin', 13330, 45, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('abfe7271-c383-46d9-84b1-6128b11e453f', 'Pickled Great Beaver', 39166, 78, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('ac98595e-fd44-4aa1-995f-438c8424e334', 'Radiant Mint Tart', 7643, 39, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('acec4372-427b-41e3-be00-dfdee5baf006', 'Ohilery Buns', 14954, 1, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('ad9681eb-fa5b-4a5a-996a-ef9eb4807135', 'Ejiorant Bonbons', 19143, 40, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('affcb9e8-abf9-482c-b6f2-e2cebb63af7b', 'Gagreamato Bombe', 35080, 11, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('b0b0089e-c8ac-4b60-8625-649f98df338b', 'Lance Mint & Berry Soufflé', 17612, 58, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('b32013db-acb3-48b3-939a-76a7ea657d06', 'Fire-Grilled Planar Fossa', 34802, 78, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('b4524e1f-162b-4ead-b722-665a97ea9ae9', 'Basted Light Civet', 13167, 9, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('b5a884a3-2e2a-48de-9fe3-72345554aaff', 'Scythe Cinnamon Sundae', 8218, 33, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('b830b4ba-1a48-4e7a-be11-80d266d05abe', 'Breaded Fluffy Orca', 32766, 14, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('b99fd560-6130-46e9-b205-63672104ef4d', 'Chimera Jelly', 48071, 89, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('bd8b2fd6-7be3-469f-88d3-442dfd803bf4', 'Poached Grim Barracuda', 79383, 62, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('c1ae90e5-0659-4a9d-9094-6e42d7711f92', 'Slow-Cooked Harmony Triggerfish', 33033, 20, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('c3a89e82-07f1-4d0b-a2fe-4d7d7294c10c', 'Masked Apricots & Honey Mooncake', 4070, 12, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('c57bc06b-6370-4840-a6c6-54a59bf2f634', 'Cured Volcanic Boar', 2450, 13, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('c6857ebc-60df-4ac8-9cf3-2c9b61c9531d', 'Blanched Fluffy Mongrel', 3945, 96, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('ca388ed0-cc5f-4397-9a77-66bb35dd4634', 'Radiant Salty & Sour Candy', 44776, 8, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('caa1f9eb-227b-43b9-afc6-b7af2f82ed82', 'Pressure-Fried Flame Fish', 43453, 80, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('cd0d90f2-8df1-4777-9cd3-09464c9bcd1f', 'Stewed Pygmy Parakeet', 25945, 13, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('cd74db53-39e4-4a7c-9208-0837c2f5b4c0', 'Dry-Roasted Cave Stallion', 1705, 89, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('ce1159e6-72e9-42f1-9d67-514027edc577', 'Dry-Roasted Iron Sunfish', 29737, 75, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('ce82282b-4bf2-4b58-99cb-0dc77c9be227', 'Strix Fruit Salad', 4678, 91, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('d01f2e78-d0c3-46a2-9921-c27acb791a70', 'Biqearian Bread', 3135, 92, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('d2ef585b-617a-461d-8cad-feed988d95f7', 'Tocrarrot Buns', 4818, 54, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('d35fa38b-ba35-49a2-9edf-aef1ac14b3c5', 'Strix Pie', 2066, 26, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('d39f09fe-d302-46a7-bf59-d3c8ec2814b5', 'Ugeonda Cake', 17791, 99, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('d7b5825a-cc83-4337-b86d-0bf4cfd710a9', 'Night Basil & Cinnamon Yogurt', 36105, 32, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('dbfe735f-08f9-46c8-9bc4-9bb5f902b1dc', 'Bunyip Ice Lollies', 11244, 6, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('dc3f00e3-8233-42b8-bab4-a00f1a97f705', 'Lunar Apricot & Basil Genoise', 25108, 45, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('ddd9e867-ad39-43b7-b8a8-d361a51a91d6', 'Golden Parmesan Jelly', 38420, 100, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('de2295a7-6fe9-419b-9a59-3220884ae7c2', 'Bugbear Waffles', 53250, 23, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('e00a9d5a-a6e3-488c-8b55-a0c91439f24c', 'Wolpertinger Surprise', 1950, 13, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('e05639ba-6dad-4279-93fd-b943d3ead4f8', 'Pressure-Fried Arctic Kudu', 23075, 43, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('e2b10248-b570-43cb-a05d-647bb5ea2e58', 'Cockatrice Jam', 32023, 5, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('e4802eec-74b5-4f07-98ec-90f7f49e844e', 'Moon Rabbit Steamed Pudding', 55237, 46, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('e6a895a2-9206-4ad7-99d6-9e04b8c53c37', 'Arctic Hazelnut Crumble', 3510, 33, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('e848e160-153a-4d8c-ac6d-12030c0fd2e6', 'Smoked Aberrant Earwig', 6112, 58, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('e887e13e-d583-4eba-9ac0-03e0b62a5732', 'Britiarian Bread', 40617, 26, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('ea3d35ef-e059-4107-a28e-241172cc985a', 'Solar Cucumber & Lime Tarte Tatin', 4249, 62, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('ede7b0ea-0c26-49db-9008-7adf593be147', 'Omudarin Buns', 35689, 98, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('f0b38935-0037-43da-9c9a-d2b5402470d9', 'Basted Silk Grizzly Bear', 885, 67, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('f1394c55-fc16-42df-9ae5-23225c62bb82', 'Mammoth Easter-Style Cheesecake', 12121, 27, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('f1796c81-79cd-4097-8ac7-4207ac3d4edb', 'Bisheople Bread', 6824, 91, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('f1cec3f4-4740-4925-8c8b-857430ff6e04', 'Grave Garlic Yogurt', 1953, 30, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('f3ef460d-a627-4425-86ab-4220af39d858', 'Goldhorn Cheesecake', 57589, 49, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('f884c53d-914b-482e-a010-f4277edcbb69', 'Glass Orange Delight', 41465, 19, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('fb27103c-eeed-4f74-a165-f44944169661', 'Chimera Delight', 36248, 55, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('fbf003f4-85bd-4944-8a42-401c94e12a3b', 'Hydra Snacks', 4868, 3, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('fd3ae569-92df-440c-84f0-58842f91abcc', 'Marinated Rain Colt', 64742, 76, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('ff0352e4-780c-4c2e-aa11-79ad5767ec75', 'Pegasus Toffee', 1199, 7, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('ff6fc1db-1468-4762-bd66-783a6bcf565f', 'Storm Coconut & Ginger Mooncake', 5180, 30, '2020-06-01 10:08:06', '2020-06-01 17:08:06'),
('ff840a1e-b151-46fa-b873-35a999524032', 'Basted Enchanted Bear', 5112, 42, '2020-06-01 10:08:06', '2020-06-01 17:08:06');

-- --------------------------------------------------------

--
-- Table structure for table `transaction_detail`
--

CREATE TABLE `transaction_detail` (
  `transaction_id` varchar(40) NOT NULL,
  `product_id` varchar(40) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `transaction_header`
--

CREATE TABLE `transaction_header` (
  `id` varchar(40) NOT NULL,
  `user_id` varchar(40) NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp(),
  `report_name` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` varchar(40) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(10) NOT NULL DEFAULT 'User',
  `status` varchar(10) NOT NULL DEFAULT 'Active',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`, `role`, `status`, `created_at`, `updated_at`) VALUES
('08a9cc7a-a49e-11ea-b347-e4e7499a064e', 'Andree Benaya Abyatar', 'andree.abyatar@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:55:07', '2020-06-02 13:55:07'),
('08ad10b8-a49e-11ea-b347-e4e7499a064e', 'Devinca Limto', 'devinca.limto@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:55:07', '2020-06-02 13:55:07'),
('08ad129e-a49e-11ea-b347-e4e7499a064e', 'Nelson Mario Latif', 'nelson.mario@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:55:07', '2020-06-02 13:55:07'),
('08ad1325-a49e-11ea-b347-e4e7499a064e', 'Angelia Salim', 'angelia.salim@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:55:07', '2020-06-02 13:55:07'),
('08ad13a2-a49e-11ea-b347-e4e7499a064e', 'Junaedi Dede', 'junaedi.dede@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:55:07', '2020-06-02 13:55:07'),
('08ad1427-a49e-11ea-b347-e4e7499a064e', 'Ryan Sanjaya', 'ryan.sanjaya@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:55:07', '2020-06-02 13:55:07'),
('08ad14a6-a49e-11ea-b347-e4e7499a064e', 'Nicky Hendrick Sen', 'nicky.sen@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:55:07', '2020-06-02 13:55:07'),
('08ad1531-a49e-11ea-b347-e4e7499a064e', 'Ferdryan Cen', 'ferdryan.cen@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:55:07', '2020-06-02 13:55:07'),
('08ad1600-a49e-11ea-b347-e4e7499a064e', 'Jonathan Gobiel', 'jonathan.gobiel@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:55:07', '2020-06-02 13:55:07'),
('08ad1670-a49e-11ea-b347-e4e7499a064e', 'Eigner', 'eigner@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:55:07', '2020-06-02 13:55:07'),
('08ad16d6-a49e-11ea-b347-e4e7499a064e', 'Kenny', 'kenny@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:55:07', '2020-06-02 13:55:07'),
('25b86cd9-a49b-11ea-b347-e4e7499a064e', 'Anglie Yanto', 'anglie.yanto@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:34:27', '2020-06-03 14:42:15'),
('25bb8ccf-a49b-11ea-b347-e4e7499a064e', 'Jethro Otto', 'jethro.otto@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:34:27', '2020-06-02 13:35:01'),
('25bb8e77-a49b-11ea-b347-e4e7499a064e', 'Danny Wiselee', 'danny.wiselee@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:34:27', '2020-06-02 13:37:26'),
('25bb8ecd-a49b-11ea-b347-e4e7499a064e', 'Karen Prisilia Iing', 'karen.iing@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:34:27', '2020-06-02 13:34:27'),
('25bb8f1b-a49b-11ea-b347-e4e7499a064e', 'Jose Armando', 'jose.armando@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:34:27', '2020-06-02 13:34:27'),
('25bb8f68-a49b-11ea-b347-e4e7499a064e', 'Kevin Surya Wahyudi', 'kevin.wahyudi@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:34:27', '2020-06-02 13:34:27'),
('25bb8ff1-a49b-11ea-b347-e4e7499a064e', 'Alvin Wijaya', 'alvin.wijaya@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:34:27', '2020-06-02 13:34:27'),
('25bb9036-a49b-11ea-b347-e4e7499a064e', 'Britney Lavenda Yauri', 'britney.yauri@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:34:27', '2020-06-02 13:34:27'),
('25bb9074-a49b-11ea-b347-e4e7499a064e', 'Malvin Wikarsa', 'malvin.wikarsa@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:34:27', '2020-06-02 13:34:27'),
('25bb90b1-a49b-11ea-b347-e4e7499a064e', 'Benaya Nusantara Edgardo', 'benaya.edgardo@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:34:27', '2020-06-02 13:34:27'),
('25bb90ef-a49b-11ea-b347-e4e7499a064e', 'Benedictus Danielle', 'benedictus.danielle@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:34:27', '2020-06-02 13:34:27'),
('25bb9135-a49b-11ea-b347-e4e7499a064e', 'Audrey Chen', 'audrey.chen@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:34:27', '2020-06-02 13:34:27'),
('25bb9175-a49b-11ea-b347-e4e7499a064e', 'Alicia', 'alicia@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:34:27', '2020-06-02 13:34:27'),
('29dcf163-a49a-11ea-b347-e4e7499a064e', 'Anglie Yanto', 'anglie.yanto@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:27:25', '2020-06-02 13:27:25'),
('5b654d4d-a49c-11ea-b347-e4e7499a064e', 'Noptovius Halimawan', 'noptovius.halimawan@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:43:07', '2020-06-02 13:43:07'),
('5b6959d3-a49c-11ea-b347-e4e7499a064e', 'Hanni Yolina', 'hanni.yolina@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:43:07', '2020-06-02 13:43:07'),
('5b695c26-a49c-11ea-b347-e4e7499a064e', 'Natasia', 'natasia@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:43:07', '2020-06-02 13:43:07'),
('5b695d09-a49c-11ea-b347-e4e7499a064e', 'Christina Yuanita', 'christina.yuanita@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:43:07', '2020-06-02 13:43:07'),
('5b695d7e-a49c-11ea-b347-e4e7499a064e', 'Faustina Sidik', 'faustina.sidik@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:43:07', '2020-06-02 13:43:07'),
('5b695df8-a49c-11ea-b347-e4e7499a064e', 'Kevin Fausta', 'kevin.fausta@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:43:07', '2020-06-02 13:43:07'),
('5b695e6e-a49c-11ea-b347-e4e7499a064e', 'Antonius Wijaya', 'antonius.wijaya@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'User', 'Active', '2020-06-02 06:43:07', '2020-06-02 13:46:20'),
('b1ba4a4a-9167-48b3-b648-86af79eb7a79', 'Renaldy', 'renaldy@storex.com', 'bcfa5dd59d82a9fbf514e81b7b7aa49e0499f60b49d9e940a1167b367892234a', 'Admin', 'Active', '2020-06-02 05:19:21', '2020-06-02 12:19:21');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaction_detail`
--
ALTER TABLE `transaction_detail`
  ADD PRIMARY KEY (`transaction_id`,`product_id`);

--
-- Indexes for table `transaction_header`
--
ALTER TABLE `transaction_header`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
