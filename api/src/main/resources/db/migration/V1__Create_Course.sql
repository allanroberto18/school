create table if not exists course(
    id int not null auto_increment primary key,
    name varchar(125) not null,
    enabled boolean default false
) Engine=InnoDB;