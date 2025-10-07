-- liquibase formatted sql
-- Generated: 2023
-- Model: DEV
-- Version: 1.0.0
-- Project: DEV
-- Author: FRANCISCO NARANJO


-- changeset francisco:1
CREATE TABLE IF NOT EXISTS `error_log` (
   `error_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `trace_id` VARCHAR(64) NOT NULL,
    `person_id` CHAR(36) NULL DEFAULT NULL,
    `error_type` VARCHAR(16) NOT NULL,
    `error_description` TEXT NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    PRIMARY KEY (`error_id`)
    ) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Error log';

-- changeset francisco:2
CREATE TABLE IF NOT EXISTS `members` (
                                         `member_id` CHAR(36) NOT NULL COMMENT 'ID from user',
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `middle_initial` VARCHAR(255) NULL DEFAULT NULL,
    `dob` DATE NULL DEFAULT NULL COMMENT 'Date of Birth',
    `nickname` VARCHAR(64) NULL DEFAULT NULL,
    `avatar` VARCHAR(255) NULL DEFAULT NULL,
    `status` VARCHAR(12) NOT NULL COMMENT 'True active, false otherwise',
    `external_id` VARCHAR(255) NULL DEFAULT NULL COMMENT 'ID for external platforms',
    `main_electronic_address` VARCHAR(255) NOT NULL COMMENT 'EMAIl or other electronic contact',
    `main_mobile_number` VARCHAR(24) NULL DEFAULT NULL,
    `gender` VARCHAR(16) NULL,
    `preferred_tz_name` VARCHAR(128) NULL DEFAULT NULL COMMENT 'Timezone for this member',
    `preferred_locale` VARCHAR(36) NULL DEFAULT NULL,
    `parent_id` CHAR(36) NULL DEFAULT NULL,
    `job_title` VARCHAR(150) NULL DEFAULT NULL,
    `created_at` TIMESTAMP NOT NULL,
    `created_by` VARCHAR(255) NOT NULL,
    `last_update` TIMESTAMP NOT NULL,
    `last_update_by` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`member_id`),
    INDEX `fk_members_members1_idx` (`parent_id` ASC),
    INDEX `IDX_MEMBERS_EXT_ID` (`external_id` ASC),
    INDEX `IDX_MEMBERS_FIRST_NAME` (`first_name` ASC),
    INDEX `IDX_MEMBERS_LAST_NAME` (`last_name` ASC),
    INDEX `IDX_MEMBERS_DOB` (`dob` ASC),
    INDEX `IDX_MEMBERS_MOBILE_PHONE_NUMBER` (`main_mobile_number` ASC),
    CONSTRAINT `fk_members_members1`
    FOREIGN KEY (`parent_id`)
    REFERENCES `members` (`member_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci
    COMMENT = 'Platform members';


-- changeset francisco:3

CREATE TABLE IF NOT EXISTS `clients` (
                                         `client_id` CHAR(36) NOT NULL,
    `member_id` CHAR(36) NOT NULL,
    `status` VARCHAR(45) NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    `created_by` VARCHAR(255) NOT NULL,
    `last_update` TIMESTAMP NOT NULL,
    `last_update_by` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`client_id`),
    INDEX `fk_clients_members1_idx` (`member_id` ASC) VISIBLE,
    CONSTRAINT `fk_clients_members1`
    FOREIGN KEY (`member_id`)
    REFERENCES `members` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci
    COMMENT = 'clients';