create table if not exists student(
    id int not null auto_increment primary key,
    name varchar(125) not null,
    email varchar(125) not null
) Engine=InnoDB;