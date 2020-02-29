/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

##
## SEQUENCE
##

DROP TABLE IF EXISTS `bitstwinkle_jelly_sequence`;
CREATE TABLE `bitstwinkle_jelly_sequence`
(
    `auto_id`      bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `gmt_create`   datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime                     DEFAULT NULL COMMENT '最后更新时间',
    `biz_code`     varchar(32)         NOT NULL DEFAULT '' COMMENT '业务唯一码',
    `min`          bigint(20) unsigned NOT NULL DEFAULT 1 COMMENT '最小值',
    `max`          bigint(20) unsigned NOT NULL DEFAULT 1000 COMMENT '最大值',
    `current`      bigint(20) unsigned NOT NULL DEFAULT 1 COMMENT '当前值',
    `step`         bigint(20) unsigned NOT NULL DEFAULT 1 COMMENT '截段步长',
    `version`      bigint(20) unsigned NOT NULL DEFAULT 1 COMMENT '乐观锁',
    PRIMARY KEY (`biz_code`) COMMENT '业务码主键',
    UNIQUE KEY `uk_auto_id` (`auto_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='SEQUENCE';