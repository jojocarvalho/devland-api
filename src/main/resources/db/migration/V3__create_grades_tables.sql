CREATE TABLE final_grade (
                             final_grade_id VARCHAR(50) PRIMARY KEY,
                             student_id VARCHAR(50),
                             course_id VARCHAR(50),
                             final_average NUMERIC(3),
                             status VARCHAR(20),
                             updated_at TIMESTAMP,

                             CONSTRAINT fk_final_grade_student FOREIGN KEY (student_id) REFERENCES  student(student_id),
                             CONSTRAINT fk_final_grade_course FOREIGN KEY (course_id) REFERENCES course(course_id)

);

CREATE TABLE partial_grades (
                                partial_grade_id VARCHAR(60) PRIMARY KEY,
                                course_module_id VARCHAR(50),
                                final_grade_id VARCHAR(50),
                                submission VARCHAR(255),
                                grade NUMERIC(2),
                                teacher_feedback TEXT,
                                submission_date TIMESTAMP,
                                correction_date TIMESTAMP,

                                CONSTRAINT fk_grade_course_module FOREIGN KEY (course_module_id) REFERENCES  course_module(module_id),
                                CONSTRAINT fk_grade_final_grade FOREIGN KEY (final_grade_id) REFERENCES  final_grade(final_grade_id)

);