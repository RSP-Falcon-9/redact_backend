CREATE TABLE `redact_users` (
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- hashed password is '1234' for testing purposes
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
('admin', 'ROLE_USER');
