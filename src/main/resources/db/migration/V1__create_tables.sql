CREATE TABLE guardian (
                          guardian_id VARCHAR(50) PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          phone VARCHAR(15) NOT NULL,
                          email VARCHAR(100) NOT NULL,
                          cpf VARCHAR(14) NOT NULL UNIQUE,
                          birth_date DATE NOT NULL,
                          address VARCHAR(255) NOT NULL
);

CREATE TABLE student (
                         student_id VARCHAR(50) PRIMARY KEY,
                         regmat VARCHAR(15) NOT NULL UNIQUE,
                         password VARCHAR(255) NOT NULL,
                         name VARCHAR(100) NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         birth_date DATE NOT NULL,
                         guardian_id VARCHAR(50) NOT NULL,

                         CONSTRAINT fk_guardian_student FOREIGN KEY(guardian_id) REFERENCES guardian(guardian_id)
);

CREATE TABLE teacher (
                         teacher_id VARCHAR(50) PRIMARY KEY,
                         regmat VARCHAR(15) NOT NULL UNIQUE,
                         password VARCHAR(255) NOT NULL,
                         name VARCHAR(100) NOT NULL,
                         phone VARCHAR(15) NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE course (
                        course_id VARCHAR(50) PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        description VARCHAR(255),
                        number_of_classes INT NOT NULL,
                        duration_hours INT NOT NULL
);

CREATE TABLE school_class (
                              school_class_id VARCHAR(50) PRIMARY KEY,
                              name VARCHAR(100) NOT NULL,
                              start_date DATE NOT NULL,
                              end_date DATE NOT NULL,
                              status VARCHAR(50) NOT NULL,
                              course_id VARCHAR(50) NOT NULL,
                              teacher_id VARCHAR(50) NOT NULL,

                              CONSTRAINT fk_course_class FOREIGN KEY(course_id) REFERENCES course(course_id),
                              CONSTRAINT fk_teacher_class FOREIGN KEY(teacher_id) REFERENCES teacher(teacher_id)
);

CREATE TABLE enrollment (
                            enrollment_id VARCHAR(50) PRIMARY KEY,
                            enrollment_date DATE NOT NULL,
                            status VARCHAR(50) NOT NULL,
                            final_grade INT,
                            school_class_id VARCHAR(50) NOT NULL,
                            student_id VARCHAR(50) NOT NULL,

                            CONSTRAINT fk_class_enrollment FOREIGN KEY(school_class_id) REFERENCES school_class(school_class_id),
                            CONSTRAINT fk_student_enrollment FOREIGN KEY(student_id) REFERENCES student(student_id) -- Adicionado FK que faltava
);

CREATE TABLE course_module (
                               module_id VARCHAR(50) PRIMARY KEY,
                               title VARCHAR(100) NOT NULL,
                               order_index INT NOT NULL,
                               course_id VARCHAR(50) NOT NULL,

                               CONSTRAINT fk_course_module FOREIGN KEY(course_id) REFERENCES course(course_id)
);

CREATE TABLE material (
                          material_id VARCHAR(50) PRIMARY KEY,
                          title VARCHAR(100) NOT NULL,
                          file_type VARCHAR(50) NOT NULL,
                          file_url VARCHAR(255) NOT NULL,
                          is_public BOOLEAN NOT NULL,
                          module_id VARCHAR(50) NOT NULL,

                          CONSTRAINT fk_module_material FOREIGN KEY(module_id) REFERENCES course_module(module_id)
);

CREATE TABLE admin (
                       admin_id VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       CONSTRAINT admin_pkey PRIMARY KEY (admin_id)
);