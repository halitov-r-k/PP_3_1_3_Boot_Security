-- Table: users
create table users(
    id int not null auto_increment primary key ,
    username varchar(250) not null ,
    password varchar(250) not null
) engine = InnoDB;

-- Table: roles
create table roles(
    id int not null auto_increment primary key ,
    name varchar(100) not null
) engine = InnoDB;

-- Table for mapping user and roles: user_roles
create table user_roles(
    user_id int not null ,
    role_id int not null ,
    foreign key (user_id) references users(id),
    foreign key (role_id) references roles(id),
    unique (user_id, role_id)
) engine = InnoDB;

-- insert data
insert into users values (1, 'user1', 'user1');
insert into roles values (1, 'ROLE_USER');
insert into roles values (2, 'ROLE_ADMIN');
insert into user_roles values (1,2);

