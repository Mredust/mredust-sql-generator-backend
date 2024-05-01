-- 版权 @author <a href="https://github.com/Mredust">Mredust</a>

-- 创建库
create database if not exists mredust_sql_generator;

-- 切换库
use mredust_sql_generator;

-- 用户表
create table if not exists user
(
    id          bigint auto_increment comment 'id' comment '主键' primary key,
    account     varchar(256)                           not null comment '用户账号',
    password    varchar(512)                           not null comment '用户密码',
    username    varchar(256)                           null comment '用户昵称',
    avatar_url  varchar(1024)                          null comment '用户头像',
    sex         tinyint      default 2                 not null comment '性别（0-女 1-男 2-保密）',
    role        varchar(256) default 0                 not null comment '用户角色（0-用户 1-管理员）',
    create_time datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete   tinyint      default 0                 not null comment '是否删除',
    constraint uni_account
        unique (account)
)
    comment '用户表';


-- 词库表
create table if not exists dict
(
    id          bigint auto_increment comment 'id' comment '主键' primary key,
    name        varchar(512)                       null comment '词库名称',
    content     text                               null comment '词库内容（json 数组）',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete   tinyint  default 0                 not null comment '是否删除'
) comment '词库表';


-- 表信息表
create table if not exists table_info
(
    id          bigint auto_increment comment 'id' comment '主键' primary key,
    name        varchar(512)                       null comment '表名称',
    content     text                               null comment '表信息（json）',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete   tinyint  default 0                 not null comment '是否删除'
) comment '表信息表';

