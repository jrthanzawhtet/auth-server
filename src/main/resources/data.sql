

create table if not exists `security`.`user`
(
    `username` varchar(45) not null,
    `password` TEXT null,
    primary key (`username`)
);
create table if not exists `security`.`otp`(
    `username` varchar(45) not null,
    `code` varchar(45) null,
    primary key(`username`)
);
