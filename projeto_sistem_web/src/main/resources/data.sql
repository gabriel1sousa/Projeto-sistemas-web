
INSERT INTO teacher(id, name, email) VALUES (1, 'Prof. Ana Silva', 'ana@uni.edu');
INSERT INTO classroom(id, name, location) VALUES (1, 'Sala 101', 'Bloco A');
INSERT INTO student(id, name, email) VALUES (1, 'João Pereira', 'joao@uni.edu');
INSERT INTO course(id, code, title, teacher_id, classroom_id) VALUES (1, 'CS101', 'Introdução à Computação', 1, 1);
INSERT INTO course_detail(course_id, syllabus, credits) VALUES (1, 'Syllabus exemplo', 4);
INSERT INTO assignment(id, course_id, title) VALUES (1, 1, 'Tarefa 1');
