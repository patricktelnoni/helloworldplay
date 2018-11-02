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

create table user (
  id                            bigint auto_increment not null,
  user_name                     varchar(255),
  password                      varchar(255),
  constraint pk_user primary key (id)
);

create table user_security_role (
  user_id                       bigint not null,
  security_role_id              bigint not null,
  constraint pk_user_security_role primary key (user_id,security_role_id)
);

create table user_aksesuser (
  user_id                       bigint not null,
  aksesuser_id                  bigint not null,
  constraint pk_user_aksesuser primary key (user_id,aksesuser_id)
);

create table dosen (
  nim_dosen                     bigint auto_increment not null,
  name                          varchar(255),
  user_id                       bigint,
  constraint uq_dosen_user_id unique (user_id),
  constraint pk_dosen primary key (nim_dosen)
);

create table kelas (
  id_kelas                      bigint auto_increment not null,
  nama_kelas                    varchar(255),
  constraint pk_kelas primary key (id_kelas)
);

create table laboran (
  id_laboran                    bigint auto_increment not null,
  name                          varchar(255),
  user_id                       bigint,
  constraint uq_laboran_user_id unique (user_id),
  constraint pk_laboran primary key (id_laboran)
);

create table matakuliah (
  id_matakuliah                 bigint auto_increment not null,
  kode_matakuliah               TEXT,
  nama_matakuliah               varchar(255),
  constraint pk_matakuliah primary key (id_matakuliah)
);

create table matakuliah_praktikum (
  matakuliah_id_matakuliah      bigint not null,
  praktikum_idpraktikum         bigint not null,
  constraint pk_matakuliah_praktikum primary key (matakuliah_id_matakuliah,praktikum_idpraktikum)
);

create table modul (
  id_modul                      bigint auto_increment not null,
  nama_modul                    varchar(255),
  tp                            TEXT,
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
  matakuliah_id_matakuliah      bigint,
  constraint pk_ploting_asprak primary key (id_plotting)
);

create table praktikan (
  nim_praktikan                 bigint auto_increment not null,
  nama_praktikan                varchar(255),
  ttl                           varchar(255),
  id_kelas                      bigint,
  user_id                       bigint,
  constraint uq_praktikan_user_id unique (user_id),
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

create table security_role_user (
  security_role_id              bigint not null,
  user_id                       bigint not null,
  constraint pk_security_role_user primary key (security_role_id,user_id)
);

create table aksesuser (
  id                            bigint auto_increment not null,
  permission_value              varchar(255),
  constraint pk_aksesuser primary key (id)
);

create table web_dasar (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  done                          tinyint(1) default 0 not null,
  due_date                      datetime(6),
  constraint pk_web_dasar primary key (id)
);

alter table asprak add constraint fk_asprak_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;

alter table user_security_role add constraint fk_user_security_role_user foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_user_security_role_user on user_security_role (user_id);

alter table user_security_role add constraint fk_user_security_role_security_role foreign key (security_role_id) references security_role (id) on delete restrict on update restrict;
create index ix_user_security_role_security_role on user_security_role (security_role_id);

alter table user_aksesuser add constraint fk_user_aksesuser_user foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_user_aksesuser_user on user_aksesuser (user_id);

alter table user_aksesuser add constraint fk_user_aksesuser_aksesuser foreign key (aksesuser_id) references aksesuser (id) on delete restrict on update restrict;
create index ix_user_aksesuser_aksesuser on user_aksesuser (aksesuser_id);

alter table dosen add constraint fk_dosen_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;

alter table laboran add constraint fk_laboran_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;

alter table matakuliah_praktikum add constraint fk_matakuliah_praktikum_matakuliah foreign key (matakuliah_id_matakuliah) references matakuliah (id_matakuliah) on delete restrict on update restrict;
create index ix_matakuliah_praktikum_matakuliah on matakuliah_praktikum (matakuliah_id_matakuliah);

alter table matakuliah_praktikum add constraint fk_matakuliah_praktikum_praktikum foreign key (praktikum_idpraktikum) references praktikum (idpraktikum) on delete restrict on update restrict;
create index ix_matakuliah_praktikum_praktikum on matakuliah_praktikum (praktikum_idpraktikum);

alter table ploting_asprak add constraint fk_ploting_asprak_asprak_nim_asprak foreign key (asprak_nim_asprak) references asprak (nim_asprak) on delete restrict on update restrict;
create index ix_ploting_asprak_asprak_nim_asprak on ploting_asprak (asprak_nim_asprak);

alter table ploting_asprak add constraint fk_ploting_asprak_kelas_id_kelas foreign key (kelas_id_kelas) references kelas (id_kelas) on delete restrict on update restrict;
create index ix_ploting_asprak_kelas_id_kelas on ploting_asprak (kelas_id_kelas);

alter table ploting_asprak add constraint fk_ploting_asprak_matakuliah_id_matakuliah foreign key (matakuliah_id_matakuliah) references matakuliah (id_matakuliah) on delete restrict on update restrict;
create index ix_ploting_asprak_matakuliah_id_matakuliah on ploting_asprak (matakuliah_id_matakuliah);

alter table praktikan add constraint fk_praktikan_id_kelas foreign key (id_kelas) references kelas (id_kelas) on delete restrict on update restrict;
create index ix_praktikan_id_kelas on praktikan (id_kelas);

alter table praktikan add constraint fk_praktikan_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;

alter table praktikum add constraint fk_praktikum_ploting_asprak_id_plotting foreign key (ploting_asprak_id_plotting) references ploting_asprak (id_plotting) on delete restrict on update restrict;
create index ix_praktikum_ploting_asprak_id_plotting on praktikum (ploting_asprak_id_plotting);

alter table praktikum add constraint fk_praktikum_kelas_id_kelas foreign key (kelas_id_kelas) references kelas (id_kelas) on delete restrict on update restrict;
create index ix_praktikum_kelas_id_kelas on praktikum (kelas_id_kelas);

alter table security_role_user add constraint fk_security_role_user_security_role foreign key (security_role_id) references security_role (id) on delete restrict on update restrict;
create index ix_security_role_user_security_role on security_role_user (security_role_id);

alter table security_role_user add constraint fk_security_role_user_user foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_security_role_user_user on security_role_user (user_id);


# --- !Downs

alter table asprak drop foreign key fk_asprak_user_id;

alter table user_security_role drop foreign key fk_user_security_role_user;
drop index ix_user_security_role_user on user_security_role;

alter table user_security_role drop foreign key fk_user_security_role_security_role;
drop index ix_user_security_role_security_role on user_security_role;

alter table user_aksesuser drop foreign key fk_user_aksesuser_user;
drop index ix_user_aksesuser_user on user_aksesuser;

alter table user_aksesuser drop foreign key fk_user_aksesuser_aksesuser;
drop index ix_user_aksesuser_aksesuser on user_aksesuser;

alter table dosen drop foreign key fk_dosen_user_id;

alter table laboran drop foreign key fk_laboran_user_id;

alter table matakuliah_praktikum drop foreign key fk_matakuliah_praktikum_matakuliah;
drop index ix_matakuliah_praktikum_matakuliah on matakuliah_praktikum;

alter table matakuliah_praktikum drop foreign key fk_matakuliah_praktikum_praktikum;
drop index ix_matakuliah_praktikum_praktikum on matakuliah_praktikum;

alter table ploting_asprak drop foreign key fk_ploting_asprak_asprak_nim_asprak;
drop index ix_ploting_asprak_asprak_nim_asprak on ploting_asprak;

alter table ploting_asprak drop foreign key fk_ploting_asprak_kelas_id_kelas;
drop index ix_ploting_asprak_kelas_id_kelas on ploting_asprak;

alter table ploting_asprak drop foreign key fk_ploting_asprak_matakuliah_id_matakuliah;
drop index ix_ploting_asprak_matakuliah_id_matakuliah on ploting_asprak;

alter table praktikan drop foreign key fk_praktikan_id_kelas;
drop index ix_praktikan_id_kelas on praktikan;

alter table praktikan drop foreign key fk_praktikan_user_id;

alter table praktikum drop foreign key fk_praktikum_ploting_asprak_id_plotting;
drop index ix_praktikum_ploting_asprak_id_plotting on praktikum;

alter table praktikum drop foreign key fk_praktikum_kelas_id_kelas;
drop index ix_praktikum_kelas_id_kelas on praktikum;

alter table security_role_user drop foreign key fk_security_role_user_security_role;
drop index ix_security_role_user_security_role on security_role_user;

alter table security_role_user drop foreign key fk_security_role_user_user;
drop index ix_security_role_user_user on security_role_user;

drop table if exists asprak;

drop table if exists user;

drop table if exists user_security_role;

drop table if exists user_aksesuser;

drop table if exists dosen;

drop table if exists kelas;

drop table if exists laboran;

drop table if exists matakuliah;

drop table if exists matakuliah_praktikum;

drop table if exists modul;

drop table if exists ploting_asprak;

drop table if exists praktikan;

drop table if exists praktikum;

drop table if exists security_role;

drop table if exists security_role_user;

drop table if exists aksesuser;

drop table if exists web_dasar;

