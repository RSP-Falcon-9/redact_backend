CREATE TABLE `redact_edition` (
  `id` INT AUTO_INCREMENT,
  `description` TEXT NOT NULL,
  `deadline` DATE NOT NULL,
  `archived` BOOLEAN NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `redact_article`
  ADD COLUMN `edition` INT AFTER `author_id`;
  
ALTER TABLE `redact_article`
  ADD FOREIGN KEY fk_edition (`edition`) REFERENCES `redact_edition` (`id`) ON DELETE SET NULL;
