## 创建flow database
CREATE DATABASE IF NOT EXISTS flowDB CHARACTER SET utf8mb4;

## 创建custom tab
CREATE TABLE IF NOT EXISTS custom(
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
cus_name NVARCHAR(20) NOT NULL,
cus_age INT NOT NULL,
cus_sex CHAR(3) NOT NULL,
cus_phone NVARCHAR(20) NOT NULL,
cus_level INT NOT NULL 
);

## 截断表
TRUNCATE TABLE custom;

## 添加 E-mail 字段
ALTER TABLE custom ADD COLUMN cus_e_mail NVARCHAR(52) NOT NULL;

## 添加 cus_pwd 字段
ALTER TABLE custom ADD COLUMN cus_pwd NVARCHAR(20) NOT NULL;

## 创建商品表
SHOW TABLES LIKE 'custom'; 
SELECT t.table_name FROM information_schema.TABLES t WHERE t.TABLE_SCHEMA ='flowDB' AND t.TABLE_NAME ='custom';

CREATE TABLE a(cus_phone_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL, goodsName NVARCHAR(20) NOT NULL, buyTime DATETIME NOT NULL, price FLOAT NOT NULL)

## 删除表
DROP TABLE s17803218828;

ALTER TABLE s17803218828 CHANGE cus_phone_id cus_phone_id INT(10); 
ALTER TABLE s17803218828 DROP PRIMARY KEY; 
ALTER TABLE s17803218828 MODIFY COLUMN cus_phone_id NVARCHAR(20) NOT NULL;