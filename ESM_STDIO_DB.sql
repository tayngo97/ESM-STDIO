drop schema if exists esm_stdio;
create schema esm_stdio;
use esm_stdio;

drop table if exists skill_types;
create table skill_types (
	id bigint primary key auto_increment,
    name varchar(255) unique not null,
    description varchar(1000),
    create_at date,
    modify_at date
    
);

drop table if exists level_of_skill;
create table level_of_skill (
	id bigint primary key auto_increment,
    name varchar(255) unique not null,
    description varchar(1000),
    create_at date,
    modify_at date
);

drop table if exists skill;
create table skill (
	id bigint primary key auto_increment,
	name varchar(255) unique not null,
    skill_type_id bigint,
    create_at date,
    modify_at date,
    delete_flag boolean
);
alter table skill add constraint Pk_skill_skill_type foreign key (skill_type_id) references skill_types(id);

drop table if exists project_status;
create table project_status (
	id bigint primary key auto_increment,
    name varchar(255) unique not null,
    create_at date,
    modify_at date
);

drop table if exists project;
create table project (
	id bigint primary key auto_increment,
    name varchar(255),
    start_time date,
	end_time date,
    description varchar(255),
    status_id bigint,
    delete_flag boolean
);
alter table project add constraint Pk_project_project_status foreign key (status_id) references project_status(id);

drop table if exists project_skills;
create table project_skills (
	project_id bigint,
    skills_id bigint
);
alter table project_skills add primary key(project_id,skills_id);
alter table project_skills add constraint Pk_project_project_1 foreign key (project_id) references project(id);
alter table project_skills add constraint Pk_project_project_skills_1 foreign key (skills_id) references skill(id);

drop table if exists employee;
create table employee (
	id bigint primary key auto_increment,
    name varchar(255),
    age int,
	address varchar(255),
    avatar_img varchar(255),
    start_date date,
    create_at date,
    modify_at date,
    delete_flag boolean
);

drop table if exists employee_skills;
create table employee_skills (
	employee_id bigint,
    skills_id bigint,
    level_of_skill_id bigint
);
alter table employee_skills add  primary key (employee_id,skills_id);
alter table employee_skills add constraint Pk_employee_skills_employee foreign key (employee_id) references employee(id);
alter table employee_skills add constraint Pk_employee_skills_employee_skills foreign key (skills_id) references skill(id);
alter table employee_skills add constraint Pk_employee_skills_level_of_skill foreign key (level_of_skill_id) references level_of_skill(id);

drop table if exists account;
create table account(
	id bigint primary key auto_increment,
	username varchar(255) unique not null,
	password varchar(255) not null,
    create_at date,
    modify_at date,
    employee_id bigint,
    delete_flag boolean
);
alter table account add constraint Pk_account_employee foreign key (employee_id) references employee(id);

drop table if exists role;
create table role (
	id bigint primary key auto_increment,
    name varchar(255) unique not null,
    create_at date,
    modify_at date
);

drop table if exists accounts_roles;
create table accounts_roles (
	account_id bigint,
    role_id bigint
);
alter table accounts_roles add  primary key (account_id,role_id);
alter table accounts_roles add constraint Pk_account_roles_account foreign key (account_id) references account(id);
alter table accounts_roles add constraint Pk_account_roles_role foreign key (role_id) references role(id);

drop table if exists employees_projects;
create table employees_projects (
	employee_id bigint,
    project_id bigint
);
alter table employees_projects add  primary key (employee_id,project_id);
alter table employees_projects add constraint Pk_employees_projects_employee  foreign key (employee_id) references employee(id);
alter table employees_projects add constraint Pk_employees_projects_project  foreign key (project_id) references project(id);

















