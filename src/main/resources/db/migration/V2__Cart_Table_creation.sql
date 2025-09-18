CREATE TABLE `store_api`.`carts` (
  `id` BINARY(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),
  `date_created` DATE NOT NULL DEFAULT (curdate()),
  PRIMARY KEY (`id`));