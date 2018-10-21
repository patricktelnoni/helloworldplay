# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table asprak (
  nim_asprak                    bigint auto_increment not null,
  nama                          varchar(255),
  role                          varchar(255),
  active                        tinyint(1) default 0 not null,
  user_id                       bigint,
  due_date                      datetime(6),
  constraint uq_asprak_user_id unique (user_id),
  constraint pk_asprak primary key (nim_asprak)
);

create table authorised_user (
  id                            bigint auto_increment not null,
  user_name                     varchar(255),
  password                      varchar(255),
  constraint pk_authorised_user primary key (id)
);

create table authorised_user_security_role (
  authorised_user_id            bigint not null,
  security_role_id              bigint not null,
  constraint pk_authorised_user_security_role primary key (authorised_user_id,security_role_id)
);

create table authorised_user_user_permission (
  authorised_user_id            bigint not null,
  user_permission_id            bigint not null,
  constraint pk_authorised_user_user_permission primary key (authorised_user_id,user_permission_id)
);

create table kelas (
  id_kelas                      bigint auto_increment not null,
  nama_kelas                    varchar(255),
  wali                          varchar(255),
  constraint pk_kelas primary key (id_kelas)
);

create table modul (
  id_modul                      bigint auto_increment not null,
  nama_modul                    varchar(255),
  jurnal                        TEXT,
  tugas_akhir                   TEXT,
  deadline                      datetime(6),
  constraint pk_modul primary key (id_modul)
);

create table ploting_asprak (
  id_plotting                   bigint auto_increment not null,
  name                          varchar(255),
  asprak_nim_asprak             bigint,
  kelas_id_kelas                bigint,
  constraint pk_ploting_asprak primary key (id_plotting)
);

create table praktikan (
  nim_praktikan                 bigint auto_increment not null,
  name                          varchar(255),
  kelas_id_kelas                bigint,
  authorised_user_id            bigint,
  constraint uq_praktikan_authorised_user_id unique (authorised_user_id),
  constraint pk_praktikan primary key (nim_praktikan)
);

create table praktikum (
  idpraktikum                   bigint auto_increment not null,
  ploting_asprak_id_plotting    bigint not null,
  name                          varchar(255),
  done                          tinyint(1) default 0 not null,
  tanggal_praktikum             datetime(6),
  kelas_id_kelas                bigint,
  constraint pk_praktikum primary key (idpraktikum)
);

create table security_role (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  constraint pk_security_role primary key (id)
);

create table user_permission (
  id                            bigint auto_increment not null,
  permission_value              varchar(255),
  constraint pk_user_permission primary key (id)
);

create table web_dasar (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  done                          tinyint(1) default 0 not null,
  due_date                      datetime(6),
  constraint pk_web_dasar primary key (id)
);

alter table asprak add constraint fk_asprak_user_id foreign key (user_id) references authorised_user (id) on delete restrict on update restrict;

alter table authorised_user_security_role add constraint fk_authorised_user_security_role_authorised_user foreign key (authorised_user_id) references authorised_user (id) on delete restrict on update restrict;
create index ix_authorised_user_security_role_authorised_user on authorised_user_security_role (authorised_user_id);

alter table authorised_user_security_role add constraint fk_authorised_user_security_role_security_role foreign key (security_role_id) references security_role (id) on delete restrict on update restrict;
create index ix_authorised_user_security_role_security_role on authorised_user_security_role (security_role_id);

alter table authorised_user_user_permission add constraint fk_authorised_user_user_permission_authorised_user foreign key (authorised_user_id) references authorised_user (id) on delete restrict on update restrict;
create index ix_authorised_user_user_permission_authorised_user on authorised_user_user_permission (authorised_user_id);

alter table authorised_user_user_permission add constraint fk_authorised_user_user_permission_user_permission foreign key (user_permission_id) references user_permission (id) on delete restrict on update restrict;
create index ix_authorised_user_user_permission_user_permission on authorised_user_user_permission (user_permission_id);

alter table ploting_asprak add constraint fk_ploting_asprak_asprak_nim_asprak foreign key (asprak_nim_asprak) references asprak (nim_asprak) on delete restrict on update restrict;
create index ix_ploting_asprak_asprak_nim_asprak on ploting_asprak (asprak_nim_asprak);

alter table ploting_asprak add constraint fk_ploting_asprak_kelas_id_kelas foreign key (kelas_id_kelas) references kelas (id_kelas) on delete restrict on update restrict;
create index ix_ploting_asprak_kelas_id_kelas on ploting_asprak (kelas_id_kelas);

alter table praktikan add constraint fk_praktikan_kelas_id_kelas foreign key (kelas_id_kelas) references kelas (id_kelas) on delete restrict on update restrict;
create index ix_praktikan_kelas_id_kelas on praktikan (kelas_id_kelas);

alter table praktikan add constraint fk_praktikan_authorised_user_id foreign key (authorised_user_id) references authorised_user (id) on delete restrict on update restrict;

alter table praktikum add constraint fk_praktikum_ploting_asprak_id_plotting foreign key (ploting_asprak_id_plotting) references ploting_asprak (id_plotting) on delete restrict on update restrict;
create index ix_praktikum_ploting_asprak_id_plotting on praktikum (ploting_asprak_id_plotting);

alter table praktikum add constraint fk_praktikum_kelas_id_kelas foreign key (kelas_id_kelas) references kelas (id_kelas) on delete restrict on update restrict;
create index ix_praktikum_kelas_id_kelas on praktikum (kelas_id_kelas);


# --- !Downs

alter table asprak drop foreign key fk_asprak_user_id;

alter table authorised_user_security_role drop foreign key fk_authorised_user_security_role_authorised_user;
drop index ix_authorised_user_security_role_authorised_user on authorised_user_security_role;

alter table authorised_user_security_role drop foreign key fk_authorised_user_security_role_security_role;
drop index ix_authorised_user_security_role_security_role on authorised_user_security_role;

alter table authorised_user_user_permission drop foreign key fk_authorised_user_user_permission_authorised_user;
drop index ix_authorised_user_user_permission_authorised_user on authorised_user_user_permission;

alter table authorised_user_user_permission drop foreign key fk_authorised_user_user_permission_user_permission;
drop index ix_authorised_user_user_permission_user_permission on authorised_user_user_permission;

alter table ploting_asprak drop foreign key fk_ploting_asprak_asprak_nim_asprak;
drop index ix_ploting_asprak_asprak_nim_asprak on ploting_asprak;

alter table ploting_asprak drop foreign key fk_ploting_asprak_kelas_id_kelas;
drop index ix_ploting_asprak_kelas_id_kelas on ploting_asprak;

alter table praktikan drop foreign key fk_praktikan_kelas_id_kelas;
drop index ix_praktikan_kelas_id_kelas on praktikan;

alter table praktikan drop foreign key fk_praktikan_authorised_user_id;

alter table praktikum drop foreign key fk_praktikum_ploting_asprak_id_plotting;
drop index ix_praktikum_ploting_asprak_id_plotting on praktikum;

alter table praktikum drop foreign key fk_praktikum_kelas_id_kelas;
drop index ix_praktikum_kelas_id_kelas on praktikum;

drop table if exists asprak;

drop table if exists authorised_user;

drop table if exists authorised_user_security_role;

drop table if exists authorised_user_user_permission;

drop table if exists kelas;

drop table if exists modul;

drop table if exists ploting_asprak;

drop table if exists praktikan;

drop table if exists praktikum;

drop table if exists security_role;

drop table if exists user_permission;

drop table if exists web_dasar;

