create table if not exists course_registration(
    id int not null auto_increment,
    course_id int not null,
    student_id int not null,
    registered_at timestamp default current_timestamp,
    primary key(id),
    constraint fk_course_rel foreign key(course_id) references course(id),
    constraint fk_student_rel foreign key(student_id) references student(id)
) Engine=InnoDB;