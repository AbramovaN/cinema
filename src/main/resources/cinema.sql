-- MySQL Script generated by MySQL Workbench
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema cinema
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cinema` ;

-- -----------------------------------------------------
-- Schema cinema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cinema` DEFAULT CHARACTER SET utf8 ;
USE `cinema` ;

-- -----------------------------------------------------
-- Table `cinema`.`user_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinema`.`user_type` ;

CREATE TABLE IF NOT EXISTS `cinema`.`user_type` (
  `id_user_type` INT NOT NULL AUTO_INCREMENT,
  `user_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_user_type`),
  UNIQUE INDEX `id_user_type_UNIQUE` (`id_user_type` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinema`.`user` ;

CREATE TABLE IF NOT EXISTS `cinema`.`user` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `id_user_type` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `operator_code` INT NOT NULL,
  `phone_number` INT NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE INDEX `id_user_UNIQUE` (`id_user` ASC),
  INDEX `id_user_type_idx` (`id_user_type` ASC),
  CONSTRAINT `id_user_type`
    FOREIGN KEY (`id_user_type`)
    REFERENCES `cinema`.`user_type` (`id_user_type`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema`.`cinema_hall`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinema`.`cinema_hall` ;

CREATE TABLE IF NOT EXISTS `cinema`.`cinema_hall` (
  `id_cinema_hall` INT NOT NULL AUTO_INCREMENT,
  `row` INT NOT NULL,
  `place` INT NOT NULL,
  PRIMARY KEY (`id_cinema_hall`),
  UNIQUE INDEX `id_cinema_hall_UNIQUE` (`id_cinema_hall` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema`.`film`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinema`.`film` ;

CREATE TABLE IF NOT EXISTS `cinema`.`film` (
  `id_film` INT NOT NULL AUTO_INCREMENT,
  `film_name` VARCHAR(45) NOT NULL,
  `genre` VARCHAR(45) NOT NULL,
  `description` VARCHAR(800) NOT NULL,
  `price` FLOAT NOT NULL,
  `time` TIME NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`id_film`),
  UNIQUE INDEX `id_film_UNIQUE` (`id_film` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema`.`ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinema`.`ticket` ;

CREATE TABLE IF NOT EXISTS `cinema`.`ticket` (
  `id_ticket` INT NOT NULL AUTO_INCREMENT,
  `id_cinema_hall` INT NOT NULL,
  `id_film` INT NOT NULL,
  `id_user` INT NULL,
  `booked` TINYINT NOT NULL,
  PRIMARY KEY (`id_ticket`),
  UNIQUE INDEX `id_ticket_UNIQUE` (`id_ticket` ASC),
  INDEX `id_cinema_hall_idx` (`id_cinema_hall` ASC),
  INDEX `id_film_idx` (`id_film` ASC),
  INDEX `id_user_idx` (`id_user` ASC),
  CONSTRAINT `id_cinema_hall`
    FOREIGN KEY (`id_cinema_hall`)
    REFERENCES `cinema`.`cinema_hall` (`id_cinema_hall`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `id_film`
    FOREIGN KEY (`id_film`)
    REFERENCES `cinema`.`film` (`id_film`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `id_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `cinema`.`user` (`id_user`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;