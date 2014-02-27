# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table qcm (
  id                        bigint not null,
  name                      varchar(255),
  description               varchar(255),
  number_of_questions       bigint,
  constraint pk_qcm primary key (id))
;

create table question (
  id                        bigint not null,
  qcm_id                    bigint not null,
  question                  varchar(255),
  reponse                   varchar(255),
  constraint pk_question primary key (id))
;

create table stagiaire (
  id                        bigint not null,
  nom                       varchar(255),
  prenom                    varchar(255),
  promotion                 varchar(255),
  constraint pk_stagiaire primary key (id))
;

create sequence qcm_seq;

create sequence question_seq;

create sequence stagiaire_seq;

alter table question add constraint fk_question_qcm_1 foreign key (qcm_id) references qcm (id) on delete restrict on update restrict;
create index ix_question_qcm_1 on question (qcm_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists qcm;

drop table if exists question;

drop table if exists stagiaire;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists qcm_seq;

drop sequence if exists question_seq;

drop sequence if exists stagiaire_seq;

