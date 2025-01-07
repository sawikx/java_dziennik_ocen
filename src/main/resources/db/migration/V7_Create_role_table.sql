create table role (
    id integer not null auto_increment,
    name varchar(255),
    primary key (id)
) engine=InnoDB;

create table user (
    id integer not null auto_increment,
    email varchar(255),
    password varchar(255),
    primary key (id)
) engine=InnoDB;

create table user_role (
    user_id integer,
    role_id integer,
    primary key (user_id, role_id)
) engine=InnoDB;

alter table user_role add constraint user_role_fk0 foreign key (user_id) references user (id);
alter table user_role add constraint user_role_fk1 foreign key (role_id) references role (id);

