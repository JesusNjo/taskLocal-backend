-- liquibase formatted sql
-- Generated: 2024
-- Model: TASKLOCAL
-- Version: 1.0.0
-- Project: TaskLocal Backend
-- Author: FRANCISCO NARANJO

-- changeset francisco:1
CREATE TABLE IF NOT EXISTS `error_log` (
                                           `error_id` BIGINT NOT NULL AUTO_INCREMENT,
                                           `trace_id` VARCHAR(64) NOT NULL,
                                           `person_id` CHAR(36) NULL DEFAULT NULL,
                                           `error_type` VARCHAR(16) NOT NULL,
                                           `error_description` TEXT NOT NULL,
                                           `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                           PRIMARY KEY (`error_id`),
                                           INDEX `idx_error_log_created_at` (`created_at` DESC),
                                           INDEX `idx_error_log_trace_id` (`trace_id` ASC)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Error log';

-- changeset francisco:2
CREATE TABLE IF NOT EXISTS `members` (
                                         `member_id` CHAR(36) NOT NULL COMMENT 'ID from user',
                                         `first_name` VARCHAR(255) NOT NULL,
                                         `last_name` VARCHAR(255) NOT NULL,
                                         `middle_initial` VARCHAR(255) NULL DEFAULT NULL,
                                         `dob` DATE NULL DEFAULT NULL COMMENT 'Date of Birth',
                                         `nickname` VARCHAR(64) NULL DEFAULT NULL,
                                         `avatar` VARCHAR(255) NULL DEFAULT NULL,
                                         `status` VARCHAR(12) NOT NULL DEFAULT 'ACTIVE' COMMENT 'ACTIVE, INACTIVE, PENDING',
                                         `external_id` VARCHAR(255) NULL DEFAULT NULL COMMENT 'ID for external platforms',
                                         `main_electronic_address` VARCHAR(255) NOT NULL COMMENT 'EMAIL or other electronic contact',
                                         `main_mobile_number` VARCHAR(24) NULL DEFAULT NULL,
                                         `gender` VARCHAR(16) NULL DEFAULT NULL,
                                         `preferred_tz_name` VARCHAR(128) NULL DEFAULT NULL COMMENT 'Timezone for this member',
                                         `preferred_locale` VARCHAR(36) NULL DEFAULT NULL,
                                         `parent_id` CHAR(36) NULL DEFAULT NULL,
                                         `job_title` VARCHAR(150) NULL DEFAULT NULL,
                                         `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         `created_by` VARCHAR(255) NOT NULL DEFAULT 'system',
                                         `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                         `last_update_by` VARCHAR(255) NOT NULL DEFAULT 'system',
                                         PRIMARY KEY (`member_id`),
                                         INDEX `fk_members_members1_idx` (`parent_id` ASC),
                                         INDEX `IDX_MEMBERS_EXT_ID` (`external_id` ASC),
                                         INDEX `IDX_MEMBERS_FIRST_NAME` (`first_name` ASC),
                                         INDEX `IDX_MEMBERS_LAST_NAME` (`last_name` ASC),
                                         INDEX `IDX_MEMBERS_DOB` (`dob` ASC),
                                         INDEX `IDX_MEMBERS_MOBILE_PHONE_NUMBER` (`main_mobile_number` ASC),
                                         INDEX `IDX_MEMBERS_STATUS` (`status` ASC),
                                         INDEX `IDX_MEMBERS_CREATED_AT` (`created_at` DESC),
                                         CONSTRAINT `fk_members_members1`
                                             FOREIGN KEY (`parent_id`)
                                                 REFERENCES `members` (`member_id`)
                                                 ON DELETE SET NULL
                                                 ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- changeset francisco:3
CREATE TABLE IF NOT EXISTS `clients` (
                                         `client_id` CHAR(36) NOT NULL,
                                         `member_id` CHAR(36) NOT NULL,
                                         `status` VARCHAR(45) NOT NULL DEFAULT 'ACTIVE',
                                         `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         `created_by` VARCHAR(255) NOT NULL DEFAULT 'system',
                                         `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                         `last_update_by` VARCHAR(255) NOT NULL DEFAULT 'system',
                                         PRIMARY KEY (`client_id`),
                                         INDEX `fk_clients_members1_idx` (`member_id` ASC),
                                         INDEX `IDX_CLIENTS_STATUS` (`status` ASC),
                                         CONSTRAINT `fk_clients_members1`
                                             FOREIGN KEY (`member_id`)
                                                 REFERENCES `members` (`member_id`)
                                                 ON DELETE CASCADE
                                                 ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;