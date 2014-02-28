# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table qcm (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255),
  description               varchar(255),
  number_of_questions       integer)
;

create table question (
  id                        integer primary key AUTOINCREMENT,
  question                  varchar(255),
  reponse                   varchar(255))
;

create table stagiaire (
  id                        integer primary key AUTOINCREMENT,
  nom                       varchar(255),
  prenom                    varchar(255),
  promotion                 varchar(255))
;


create table qcm_question (
  qcm_id                         integer not null,
  question_id                    integer not null,
  constraint pk_qcm_question primary key (qcm_id, question_id))
;

create table qcm_stagiaire (
  qcm_id                         integer not null,
  stagiaire_id                   integer not null,
  constraint pk_qcm_stagiaire primary key (qcm_id, stagiaire_id))
;



alter table qcm_question add constraint fk_qcm_question_qcm_01 foreign key (qcm_id) references qcm (id);

alter table qcm_question add constraint fk_qcm_question_question_02 foreign key (question_id) references question (id);

alter table qcm_stagiaire add constraint fk_qcm_stagiaire_qcm_01 foreign key (qcm_id) references qcm (id);

alter table qcm_stagiaire add constraint fk_qcm_stagiaire_stagiaire_02 foreign key (stagiaire_id) references stagiaire (id);

# --- !Downs

PRAGMA foreign_keys = OFF;

drop table qcm;

drop table qcm_question;

drop table qcm_stagiaire;

drop table question;

drop table stagiaire;

PRAGMA foreign_keys = ON;

