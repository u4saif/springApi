CREATE TABLE `store_api`.`cart_items` (
  `id` BIGINT(16) NOT NULL AUTO_INCREMENT,
  `cart_id` BINARY(16) NOT NULL,
  `product_id` BIGINT(16) NOT NULL,
  `quantity` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cart_item_product_unique` (`cart_id` ASC, `product_id` ASC) VISIBLE,
  CONSTRAINT `cart_item_cart_id_fk`
    FOREIGN KEY (`cart_id`)
    REFERENCES `store_api`.`carts` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `cart_item_product_id_fk`
    FOREIGN KEY (`product_id`)
    REFERENCES `store_api`.`products` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
);