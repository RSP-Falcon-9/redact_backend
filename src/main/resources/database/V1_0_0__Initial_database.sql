CREATE TABLE `redact_users` (
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `redact_users` (`username`, `password`) VALUES
('admin', '$2a$10$QmC.Pzot8.WWGuYMHHAtVuS7is7EfIf6JCgLZ5UHvUrHpdKZHGLU6');

CREATE TABLE `redact_user_roles` (
  `username` VARCHAR(50) NOT NULL,
  `role` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`username`, `role`),
  CONSTRAINT `fk_username` FOREIGN KEY (`username`) REFERENCES `redact_users`(`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `redact_user_roles` (`username`, `role`) VALUES
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_USER'),
('admin', 'ROLE_AUTHOR'),
('admin', 'ROLE_REVIEWER'),
('admin', 'ROLE_EDITOR'),
('admin', 'ROLE_CHIEF_EDITOR');

CREATE TABLE `redact_article` (
  `id` VARCHAR(36) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `author_id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_author_id` FOREIGN KEY (`author_id`) REFERENCES `redact_users`(`username`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `redact_arcticle_version` (
  `article_id` VARCHAR(36) NOT NULL,
  `version` TINYINT NOT NULL,
  `file_name` VARCHAR(50) NOT NULL,
  `publish_date` DATE NOT NULL,
  `status` TINYINT NOT NULL,
  PRIMARY KEY (`article_id`, `version`),
  CONSTRAINT `fk_article_id` FOREIGN KEY (`article_id`) REFERENCES `redact_article`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `redact_article_review` (
  `id` VARCHAR(36) NOT NULL,
  `article_id` VARCHAR(36) NOT NULL,
  `version` TINYINT NOT NULL,
  `review_status` TINYINT NOT NULL,
  `reviewer_id` VARCHAR(36),
  `interest` TINYINT,
  `originality` TINYINT,
  `specialization_level` TINYINT,
  `language_level` TINYINT,
  `comment` TEXT,
  `review_date` DATE,
  `appeal` TEXT,
  `appeal_date` DATE,
  `visible_to_author` BOOLEAN NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_reviewer_id` FOREIGN KEY (`reviewer_id`) REFERENCES `redact_users`(`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
