# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table qcm (
  id                        bigint not null,
  question                  varchar(255),
  reponse                   varchar(255),
  constraint pk_qcm primary key (id))
;

create sequence qcm_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists qcm;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists qcm_seq;

