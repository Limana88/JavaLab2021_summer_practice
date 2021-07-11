create table course(
    id serial primary key ,
    name varchar(30) not null ,
    date_start varchar(30) not null ,
    date_end varchar(30) not null
);
create table student(
    id serial primary key ,
    first_name varchar(20) not null ,
    last_name varchar(20) not null ,
    group_number integer check ( group_number> 0 ) not null ,
    course_id integer,
    foreign key (course_id) references course(id)
);

create table teacher(
    id serial primary key ,
    first_name varchar(20) not null ,
    last_name varchar(20) not null ,
    experience integer check ( experience >= 0 ) not null ,
    course_id integer,
    foreign key (course_id) references course(id)
);

 create table lesson(
     id serial primary key ,
     name varchar(30) not null,
     day_of_week varchar(10) not null ,
     time varchar(8) not null ,
     course_id integer,
     foreign key (course_id) references course(id)

 );

create table teacher_course (
    teacher_id integer,
    course_id integer,
    foreign key (teacher_id) references teacher(id),
    foreign key (course_id) references course(id)
);

create table student_course(
    student_id integer,
    course_id integer,
    foreign key (student_id) references student(id),
    foreign key (course_id) references course(id)
);