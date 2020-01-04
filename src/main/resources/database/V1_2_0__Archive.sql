CREATE TABLE `redact_edition` (
  `id` INT PRIMARY KEY AUTO INCREMENT,
  `description` TEXT NOT NULL,
  `deadline` DATE NOT NULL,
  `archived` BOOLEAN NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `redact_article`
  ADD COLUMN `edition` INT AFTER `versions`
  ADD FOREIGN KEY fk_edition(`edition`) REFERENCES redact_edition(id) ON DELETE SET NULL;
