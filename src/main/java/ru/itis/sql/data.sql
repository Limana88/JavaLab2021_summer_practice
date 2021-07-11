insert into course(name, date_start, date_end) values ('jabaLab', '01-09-2021', '24-12-2021');
insert into course(name, date_start, date_end) values ('DMLLab', '01-09-2021', '24-12-2021');
insert into course(name, date_start, date_end) values ('Python Django', '01-09-2021', '24-12-2021');
insert into course(name, date_start, date_end) values ('Mobile Development', '01-09-2021', '24-12-2021');
insert into course(name, date_start, date_end) values ('NcN Lab', '01-09-2021', '24-12-2021');

insert into teacher(first_name, last_name, experience, course_id) values ('Marsel', 'Sidikov', 3, 1);
insert into teacher(first_name, last_name, experience, course_id) values ('Vlada', 'Kugurakova', 32, 2);
insert into teacher(first_name, last_name, experience, course_id) values ('Michael', 'Abramski', 10, 3);
insert into teacher(first_name, last_name, experience, course_id) values ('Irina', 'Shakhova', 6, 4);
insert into teacher(first_name, last_name, experience, course_id) values ('Maxim', 'Talanov', 7, 5);

insert into student(first_name, last_name, group_number, course_id) values ('Milana', 'Makhsotova', 1, 1);
insert into student(first_name, last_name, group_number, course_id) values ('Elina', 'Zagidullina', 1, 1);
insert into student(first_name, last_name, group_number, course_id) values ('Roman', 'Snitsaryuk', 1, 4);
insert into student(first_name, last_name, group_number, course_id) values ('Regina', 'Tyapkina', 1, 4);
insert into student(first_name, last_name, group_number, course_id) values ('Kamil', 'Galiev', 1, 5);
insert into student(first_name, last_name, group_number, course_id) values ('Murat', 'Kamaletdinov', 5, 3);
insert into student(first_name, last_name, group_number, course_id) values ('Dilyara', 'Kashapova', 1, 2);


insert into student_courses(student_id, course_id)  values (1, 1);
insert into student_courses(student_id, course_id)  values (2, 1);
insert into student_courses(student_id, course_id)  values (3, 4);
insert into student_courses(student_id, course_id)  values (4, 4);
insert into student_courses(student_id, course_id)  values (5, 5);
insert into student_courses(student_id, course_id)  values (6, 3);
insert into student_courses(student_id, course_id)  values (7, 2);

insert into lesson(name, day_of_week, time, course_id) values('lesson1 IOS lab', 'Tuesday', '8:30', 4);
insert into lesson(name, day_of_week, time, course_id) values('lesson1 javalab', 'Tuesday', '10:10', 1);
insert into lesson(name, day_of_week, time, course_id) values('lesson1 Django', 'Wednesday', '14:00', 3);
insert into lesson(name, day_of_week, time, course_id) values('lesson1 NcN', 'Thursday', '8:30', 5);

insert into teacher_course(teacher_id, course_id) values (1, 1);
insert into teacher_course(teacher_id, course_id) values (2, 2);
insert into teacher_course(teacher_id, course_id) values (3, 3);
insert into teacher_course(teacher_id, course_id) values (4, 4);
insert into teacher_course(teacher_id, course_id) values (5, 5);
