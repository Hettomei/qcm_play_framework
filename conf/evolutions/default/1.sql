# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table qcm (
  id                        bigint not null,
  question                  varchar(255),
  reponse                   varchar(255),
  constraint pk_qcm primary key (id))
;

create table question (
  id                        bigint not null,
  question                  varchar(255),
  reponse                   varchar(255),
  constraint pk_question primary key (id))
;

create sequence qcm_seq;

create sequence question_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists qcm;

drop table if exists question;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists qcm_seq;

drop sequence if exists question_seq;

