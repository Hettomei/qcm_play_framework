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
  qcm_id                    integer not null,
  question                  varchar(255),
  reponse                   varchar(255))
;

create table stagiaire (
  id                        integer primary key AUTOINCREMENT,
  nom                       varchar(255),
  prenom                    varchar(255),
  promotion                 varchar(255))
;


create table qcm_stagiaire (
  qcm_id                         integer not null,
  stagiaire_id                   integer not null,
  constraint pk_qcm_stagiaire primary key (qcm_id, stagiaire_id))
;

create table question_qcm (
  question_id                    integer not null,
  qcm_id                         integer not null,
  constraint pk_question_qcm primary key (question_id, qcm_id))
;

create table stagiaire_qcm (
  stagiaire_id                   integer not null,
  qcm_id                         integer not null,
  constraint pk_stagiaire_qcm primary key (stagiaire_id, qcm_id))
;
alter table question add constraint fk_question_qcm_1 foreign key (qcm_id) references qcm (id);
create index ix_question_qcm_1 on question (qcm_id);



alter table qcm_stagiaire add constraint fk_qcm_stagiaire_qcm_01 foreign key (qcm_id) references qcm (id);

alter table qcm_stagiaire add constraint fk_qcm_stagiaire_stagiaire_02 foreign key (stagiaire_id) references stagiaire (id);

alter table question_qcm add constraint fk_question_qcm_question_01 foreign key (question_id) references question (id);

alter table question_qcm add constraint fk_question_qcm_qcm_02 foreign key (qcm_id) references qcm (id);

alter table stagiaire_qcm add constraint fk_stagiaire_qcm_stagiaire_01 foreign key (stagiaire_id) references stagiaire (id);

alter table stagiaire_qcm add constraint fk_stagiaire_qcm_qcm_02 foreign key (qcm_id) references qcm (id);

# --- !Downs

PRAGMA foreign_keys = OFF;

drop table qcm;

drop table qcm_stagiaire;

drop table question;

drop table question_qcm;

drop table stagiaire;

drop table stagiaire_qcm;

PRAGMA foreign_keys = ON;

