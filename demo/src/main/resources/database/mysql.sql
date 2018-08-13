CREATE TABLE `tbl_activity` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `date_start` varchar(255) COLLATE utf8_bin NOT NULL,
  `date_end` varchar(255) COLLATE utf8_bin NOT NULL,
  `rule_before` varchar(255) COLLATE utf8_bin NOT NULL,
  `rule_after` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tbl_award` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `activity_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `count` int COLLATE utf8_bin NOT NULL,
  `probility` double COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tbl_record` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `activity_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `user_id` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tbl_user` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `phone` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;