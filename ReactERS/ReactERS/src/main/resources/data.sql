create table if not exists users (
    userId int primary key auto_increment,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    email varchar(255) not null unique,
    password varchar(255) not null,
    role varchar(255) not null
);



create table if not exists reimbursements (
    reimbId int primary key auto_increment,
    description varchar(255) not null,
    amount int not null,
    status varchar(255) not null,
    userId int not null,
    foreign key (userId) references users(userId) on delete cascade
);