/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : spot_check_voucher

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 20/09/2022 16:30:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

SET FOREIGN_KEY_CHECKS = 1;


drop table if exists Check_vou_info;
drop table if exists Auditor_manage;
drop table if exists Intern;
drop table if exists Vouchers;
drop table if exists Auditor;


/*==============================================================*/
/* Table: Auditor                                               */
/*==============================================================*/
create table Auditor
(
   auditor              varchar(5) comment '审计师姓名',
   auditor_id           varchar(10) not null comment '审计师工号',
   aud_pro              varchar(100) comment '负责的审计程序',
   primary key (auditor_id)
);

alter table Auditor comment '审计师（管理者）表';


/*==============================================================*/
/* Table: Vouchers                                              */
/*==============================================================*/
create table Vouchers
(
   Comp                 varchar(20) not null comment '单位名称',
   vou_id               char(4) not null comment '凭证编号',
   vou_date             date not null comment '记账日期',
   vou_acc              varchar(20) comment '记账科目',
   con_acc              varchar(20) comment '对方科目',
   amount               float comment '凭证金额',
   primary key (Comp, vou_id, vou_date)
);

alter table Vouchers comment '凭证信息表';


/*==============================================================*/
/* Table: Intern                                                */
/*==============================================================*/
create table Intern
(
   intern_name          varchar(5) comment '实习生姓名',
   intern_id            varchar(10) not null comment '实习生工号',
   formal               boolean comment '是否签合同',
   auditor_id           varchar(10) comment '带教审计师编号',
   auditor              varchar(5) comment '带教审计师姓名',
   primary key (intern_id)
);

alter table Intern comment '凭证抽查者（实习生）表';

alter table Intern add constraint FK_Reference_4 foreign key (auditor_id)
      references Auditor (auditor_id) on delete cascade on update cascade;


/*==============================================================*/
/* Table: Auditor_manage                                        */
/*==============================================================*/
create table Auditor_manage
(
   Comp                 varchar(20) not null comment '单位名称',
   vou_id               char(4) not null comment '凭证编号',
   vou_date             date not null comment '记账日期',
   vou_acc              varchar(20) comment '记账科目',
   con_acc              varchar(20) comment '对方科目',
   amount               float comment '凭证金额',
   aud_pro              varchar(10) comment '审计程序',
   spot_choose          boolean comment '是否抽查',
   aud_id               varchar(10) comment '抽凭索引号',
   intern_name          varchar(5) comment '抽凭负责人',
   auditor_id           varchar(10) comment '审计师工号',
   auditor              varchar(5) comment '审计师姓名',
   intern_id            varchar(10) comment '实习生工号',
   primary key (Comp, vou_id, vou_date)
);

alter table Auditor_manage comment '审计师用表';

alter table Auditor_manage add constraint FK_Reference_3 foreign key (auditor_id)
      references Auditor (auditor_id) on delete cascade on update cascade;

alter table Auditor_manage add constraint FK_Reference_5 foreign key (Comp, vou_id, vou_date)
      references Vouchers (Comp, vou_id, vou_date) on delete cascade on update cascade;

alter table Auditor_manage add constraint FK_Reference_6 foreign key (intern_id)
      references Intern (intern_id) on delete cascade on update cascade;


/*==============================================================*/
/* Table: Check_vou_info                                        */
/*==============================================================*/
create table Check_vou_info
(
   Comp                 varchar(20) not null comment '单位名称',
   vou_id               char(4) comment '凭证编号',
   vou_date             date comment '记账日期',
   vou_acc              varchar(20) comment '记账科目',
   con_acc              varchar(20) comment '对方科目',
   amount               float comment '凭证金额',
   aud_pro              varchar(10) comment '抽凭程序',
   aud_id               varchar(10) AUTO_INCREMENT comment '抽凭索引号',
   intern_id            varchar(10) comment '实习生工号',
   intern_name          varchar(5) comment '抽凭负责人',
   attach               varchar(100) comment '凭证附件',
   inv_typ              varchar(20) comment '所涉发票类型',
   inv_id               char(4) comment '所涉发票编号（如有）',
   procedu              boolean comment '手续是否齐全',
   verify               boolean comment '核对内容是否相符',
   att_file             varchar(200) comment '附件文件',
   create_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '创建时间',
   update_time          timestamp comment '更新时间',
   primary key (Comp, aud_id)
);

alter table Check_vou_info comment '实习生抽查凭证信息表';

alter table Check_vou_info add constraint FK_Reference_1 foreign key (Comp, vou_id, vou_date)
      references Auditor_manage (Comp, vou_id, vou_date) on delete cascade on update cascade;

alter table Check_vou_info add constraint FK_Reference_2 foreign key (intern_id)
      references Intern (intern_id) on delete cascade on update cascade;
