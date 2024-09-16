-- mybatis_plus        utf8mb4_general_ci
create table user
(
    id          bigint      not null comment '主键ID'
        primary key,
    name        varchar(30) null comment '姓名',
    age         int         null comment '年龄',
    email       varchar(50) null comment '邮箱',
    create_time datetime    null comment '创建时间',
    update_time datetime    null comment '更新时间'
);
alter table user
    alter column is_deleted set default 0;