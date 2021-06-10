# Java课设

## 说点什么

这是我的第一个前后端分离的项目，大概的功能都在xmind中。以此记录，以资鼓励。(●'◡'●)
接下来是要找暑假实习，加油(●'◡'●)

## 前端地址以及相关技术栈
前端地址：https://github.com/Hedgehog-03/java-course-design

前端(2人)
- React + React Hooks + React Router + Ant Design + axios
后端(1人)
- Springboot + Mybatis + MySQL

## 数据库

### 人事管理

```mysql
create table staff
(
    id                       int auto_increment
        primary key,
    name                     varchar(30) not null,
    id_card                  char(18)    not null,
    position_id              int         not null,
    age                      int         not null,
    sex                      varchar(10) not null,
    qualification_id         int         not null,
    education                varchar(30) not null,
    entry_time               bigint      not null,
    contract_expiration_time bigint      not null,
    is_resigned              tinyint(1)  not null comment '0：未离职，1：已离职',
    constraint staff_position_id_fk
        foreign key (position_id) references position (id),
    constraint staff_qualification_id_fk
        foreign key (qualification_id) references qualification (id)
);

create table qualification
(
    id           int auto_increment
        primary key,
    name         varchar(30)                 not null,
    extra_salary decimal(12, 2) default 0.00 not null,
    count        int            default 0    not null
);

create table employee_interview
(
    id                  int auto_increment,
    name                varchar(30)  not null,
    telephone           char(11)     not null,
    interview_status    varchar(20)  not null comment '待面试,已面试,面试通过,面试不通过',
    interview_situation varchar(255) null,
    constraint employee_interview_pk
        primary key (id)
);

create table performance
(
    id       int auto_increment
        primary key,
    staff_id int            not null,
    date     bigint         not null,
    bonus    decimal(12, 2) not null,
    constraint performance_staff_id_fk
        foreign key (staff_id) references staff (id)
);


create table leave_record
(
    id               int auto_increment
        primary key,
    leave_start_time bigint       not null,
    leave_end_time   bigint       not null,
    leave_reason     varchar(100) not null,
    staff_id         int          not null,
    pass             varchar(10)  null comment '未审批，通过，不通过',
    approcver_id     int          null comment '审批人id',
    last_time        varchar(20)  not null,
    constraint leave_record_staff_id_fk
        foreign key (staff_id) references staff (id),
    constraint leave_record_staff_id_fk_2
        foreign key (approcver_id) references staff (id)
);

create table position
(
    id         int auto_increment
        primary key,
    name       varchar(30)    not null,
    basic_wage decimal(12, 2) not null,
    count      int default 0  not null
);

create table attendance
(
    id          int auto_increment
        primary key,
    staff_id    int         not null,
    arrive_time bigint      not null,
    leave_time  bigint      null,
    last_time   varchar(20) not null,
    constraint attendance_staff_id_fk
        foreign key (staff_id) references staff (id)
);

create table training
(
    id              int auto_increment
        primary key,
    training_method varchar(30)    not null,
    score           decimal(10, 2) not null,
    staff_id        int            not null,
    start_time      bigint         not null,
    end_time        bigint         not null,
    last_time       varchar(30)    not null,
    constraint training_staff_id_fk
        foreign key (staff_id) references staff (id)
);

create table payroll
(
    id         int auto_increment
        primary key,
    staff_id   int            not null,
    basic_wage decimal(12, 2) not null,
    bonus      decimal(12, 2) null,
    date       bigint         not null comment '发工资的日期',
    total      decimal(12, 2) not null,
    constraint payroll_staff_id_fk
        foreign key (staff_id) references staff (id)
)
    comment '工资单';
```

### 系统用户

```mysql
create table user
(
    id       int auto_increment
        primary key,
    username varchar(30) not null,
    password varchar(30) not null,
    staff_id int         not null,
    constraint user_staff_id_fk
        foreign key (staff_id) references staff (id)
);

create table token
(
    id          int auto_increment
        primary key,
    user_id     int          not null,
    expire_time bigint       not null,
    token       varchar(255) not null,
    constraint token_user_id_fk
        foreign key (user_id) references user (id)
);
```