CREATE TABLE `basenews` (
	`ID` INT(11) NOT NULL AUTO_INCREMENT,
	`TITLE` VARCHAR(2000) NULL DEFAULT '0',
	`THUMBNAIL` VARCHAR(2000) NULL DEFAULT '0',
	`CONTENT` TEXT NULL,
	`AUTHOR` VARCHAR(2000) NULL DEFAULT '0',
	`DATETIME` VARCHAR(2000) NULL DEFAULT '0',
	`SOURCE` VARCHAR(200) NULL DEFAULT '0',
	`JIANJIE` VARCHAR(2000) NULL DEFAULT '0',
	`KEYWORDS` VARCHAR(2000) NULL DEFAULT '0',
	`URL` VARCHAR(500) NULL DEFAULT '0',
	PRIMARY KEY (`ID`)
)
COLLATE='utf8_general_ci'
ENGINE=MyISAM
AUTO_INCREMENT=16
;
