
-- 1. INSERTS PARA GUARDIAN
INSERT INTO guardian (guardian_id, name, phone, email, cpf, birth_date, address) VALUES
                                                                                     ('GUARDIAN_3F31FA8C-9982-4DF0-A355-000D2EB2E416', 'Adelaide da Silva', '11 91234-5678', 'adelaide@gmail.com', '123.456.789-00', TO_DATE('25/02/1980', 'DD/MM/YYYY'), 'Rua Albuquerque, 512'),
                                                                                     ('GUARDIAN_A1B2C3D4-E5F6-4788-9900-112233445566', 'Roberto Mendes', '11 98888-7777', 'roberto.m@email.com', '234.567.890-11', TO_DATE('15/05/1978', 'DD/MM/YYYY'), 'Av. Paulista, 1000, Apt 40'),
                                                                                     ('GUARDIAN_B2C3D4E5-F6A7-4899-0011-223344556677', 'Carla Souza', '21 99999-1111', 'carla.souza@email.com', '345.678.901-22', TO_DATE('10/09/1985', 'DD/MM/YYYY'), 'Rua das Flores, 20'),
                                                                                     ('GUARDIAN_C3D4E5F6-A7B8-4900-1122-334455667788', 'Marcos Oliveira', '31 97777-6666', 'marcos.oli@email.com', '456.789.012-33', TO_DATE('01/12/1982', 'DD/MM/YYYY'), 'Alameda Santos, 50'),
                                                                                     ('GUARDIAN_D4E5F6A7-B8C9-4A11-2233-445566778899', 'Julia Pereira', '41 96666-5555', 'ju.pereira@email.com', '567.890.123-44', TO_DATE('20/03/1990', 'DD/MM/YYYY'), 'Rua XV de Novembro, 300');

-- 2. INSERTS PARA TEACHER
INSERT INTO teacher (teacher_id, regmat, password, name, phone, email) VALUES
                                                                           ('TEACHER_D82E9103-5C7A-4F19-B26D-8E1A9C4F3B20', 'PROF250001', 'hash_senha_123', 'Andrey Alegre', '11 91111-2222', 'andrey@gmail.com'),
                                                                           ('TEACHER_E93F0214-6D8B-5G20-C37E-9F2B0D5E4C31', 'PROF250002', 'hash_senha_456', 'Mariana Rocha', '11 92222-3333', 'mariana.dev@email.com'),
                                                                           ('TEACHER_F04G1325-7E9C-6H31-D48F-0G3C1E6F5D42', 'PROF250003', 'hash_senha_789', 'Ricardo Santos', '21 93333-4444', 'ricardo.java@email.com'),
                                                                           ('TEACHER_G15H2436-8F0D-7I42-E59G-1H4D2F7G6E53', 'PROF250004', 'hash_senha_abc', 'Fernanda Lima', '31 94444-5555', 'fernanda.ux@email.com'),
                                                                           ('TEACHER_H26I3547-9G1E-8J53-F60H-2I5E3G8H7F64', 'PROF250005', 'hash_senha_def', 'Lucas Almeida', '41 95555-6666', 'lucas.db@email.com');

-- 3. INSERTS PARA COURSE
INSERT INTO course (course_id, name, description, number_of_classes, duration_hours) VALUES
                                                                                         ('COURSE_E1F53135-4E2B-42D3-A92B-3F4E5D6C7B8A', 'Scratch Maker', 'Lógica de programação para crianças', 20, 80),
                                                                                         ('COURSE_F2G64246-5F3C-53E4-B03C-4G5F6E7D8C9B', 'Python Game Dev', 'Desenvolvimento de jogos com a biblioteca PyGames', 25, 100),
                                                                                         ('COURSE_G3H75357-6G4D-64F5-C14D-5H6G7F8E9D0C', 'Roblox World', 'Criando mundos dentro do Roblox', 30, 120),
                                                                                         ('COURSE_H4I86468-7H5E-75G6-D25E-6I7H8G9F0E1D', 'AI Explorer', 'Introdução a criação de Agentes de IA', 40, 160),
                                                                                         ('COURSE_I5J97579-8I6F-86H7-E36F-7J8I9H0G1F2E', 'Digital Artist', 'Criação de artes digitais', 20, 80);

-- 4. INSERTS PARA STUDENT
INSERT INTO student (student_id, regmat, password, name, email, birth_date, guardian_id) VALUES
                                                                                             ('STUDENT_72A4F8B1-9C3D-4E5A-8F12-6D0E1B2C3A4F', 'REGMAT250001', 'senha_enzo', 'Enzo da Silva', 'enzinho@gmail.com', TO_DATE('01/01/2012', 'DD/MM/YYYY'), 'GUARDIAN_3F31FA8C-9982-4DF0-A355-000D2EB2E416'),
                                                                                             ('STUDENT_83B5G9C2-0D4E-5F6B-9G23-7E1F2C3D4B5G', 'REGMAT250002', 'senha_sofia', 'Sofia Mendes', 'sofia.m@email.com', TO_DATE('05/12/2013', 'DD/MM/YYYY'), 'GUARDIAN_A1B2C3D4-E5F6-4788-9900-112233445566'),
                                                                                             ('STUDENT_94C6H0D3-1E5F-6G7C-0H34-8F2G3D4E5C6H', 'REGMAT250003', 'senha_pedro', 'Pedro Souza', 'pedro.game@email.com', TO_DATE('20/08/2011', 'DD/MM/YYYY'), 'GUARDIAN_B2C3D4E5-F6A7-4899-0011-223344556677'),
                                                                                             ('STUDENT_05D7I1E4-2F6G-7H8D-1I45-9G3H4E5F6D7I', 'REGMAT250004', 'senha_alice', 'Alice Oliveira', 'alice.art@email.com', TO_DATE('11/05/2012', 'DD/MM/YYYY'), 'GUARDIAN_C3D4E5F6-A7B8-4900-1122-334455667788'),
                                                                                             ('STUDENT_16E8J2F5-3G7H-8I9E-2J56-0H4I5F6G7E8J', 'REGMAT250005', 'senha_miguel', 'Miguel Pereira', 'miguel.tech@email.com', TO_DATE('15/02/2014', 'DD/MM/YYYY'), 'GUARDIAN_D4E5F6A7-B8C9-4A11-2233-445566778899');

-- 5. INSERTS PARA SCHOOL_CLASS
INSERT INTO school_class (school_class_id, name, start_date, end_date, status, course_id, teacher_id) VALUES
                                                                                                          ('CLASS_7A8B9C0D-1E2F-43A4-B5C6-7D8E9F0A1B2C', 'Scratch Maker - Terça e Quinta', TO_DATE('02/01/2026', 'DD/MM/YYYY'), TO_DATE('30/06/2026', 'DD/MM/YYYY'), '0', 'COURSE_E1F53135-4E2B-42D3-A92B-3F4E5D6C7B8A', 'TEACHER_D82E9103-5C7A-4F19-B26D-8E1A9C4F3B20'),
                                                                                                          ('CLASS_8B9C0D1E-2F3G-54B5-C6D7-8E9F0A1B2C3D', 'Python Game Dev - Segunda e Quarta', TO_DATE('01/02/2026', 'DD/MM/YYYY'), TO_DATE('30/06/2026', 'DD/MM/YYYY'), '0', 'COURSE_F2G64246-5F3C-53E4-B03C-4G5F6E7D8C9B', 'TEACHER_E93F0214-6D8B-5G20-C37E-9F2B0D5E4C31'),
                                                                                                          ('CLASS_9C0D1E2F-3G4H-65C6-D7E8-9F0A1B2C3D4E', 'Roblox World - Segunda, Quarta e Sexta', TO_DATE('05/02/2026', 'DD/MM/YYYY'), TO_DATE('15/07/2026', 'DD/MM/YYYY'), '1', 'COURSE_G3H75357-6G4D-64F5-C14D-5H6G7F8E9D0C', 'TEACHER_F04G1325-7E9C-6H31-D48F-0G3C1E6F5D42'),
                                                                                                          ('CLASS_0D1E2F3G-4H5I-76D7-E8F9-0A1B2C3D4E5F', 'AI Explorer - Sábado', TO_DATE('08/01/2026', 'DD/MM/YYYY'), TO_DATE('15/02/2026', 'DD/MM/YYYY'), '0', 'COURSE_H4I86468-7H5E-75G6-D25E-6I7H8G9F0E1D', 'TEACHER_G15H2436-8F0D-7I42-E59G-1H4D2F7G6E53'),
                                                                                                          ('CLASS_1E2F3G4H-5I6J-87E8-F9G0-1B2C3D4E5F6G', 'Digital Artist -  Segunda e Quinta', TO_DATE('15/01/2026', 'DD/MM/YYYY'), TO_DATE('15/02/2026', 'DD/MM/YYYY'), '2', 'COURSE_I5J97579-8I6F-86H7-E36F-7J8I9H0G1F2E', 'TEACHER_H26I3547-9G1E-8J53-F60H-2I5E3G8H7F64');

-- 6. INSERTS PARA ENROLLMENT
INSERT INTO enrollment (enrollment_id, enrollment_date, status, final_grade, school_class_id, student_id) VALUES
                                                                                                              ('ENROLLMENT_6A7B8C9D-0E1F-42A3-B4C5-D6E7F8A9B0C1', TO_DATE('02/01/2026', 'DD/MM/YYYY'), '0', NULL, 'CLASS_7A8B9C0D-1E2F-43A4-B5C6-7D8E9F0A1B2C', 'STUDENT_72A4F8B1-9C3D-4E5A-8F12-6D0E1B2C3A4F'), -- Enzo na turma de Scratch
                                                                                                              ('ENROLLMENT_7B8C9D0E-1F2G-53B4-C5D6-E7F8A9B0C1D2', TO_DATE('02/01/2026', 'DD/MM/YYYY'), '0', NULL, 'CLASS_8B9C0D1E-2F3G-54B5-C6D7-8E9F0A1B2C3D', 'STUDENT_83B5G9C2-0D4E-5F6B-9G23-7E1F2C3D4B5G'), -- Sofia na turma de Python
                                                                                                              ('ENROLLMENT_8C9D0E1F-2G3H-64C5-D6E7-F8A9B0C1D2E3', TO_DATE('02/04/2026', 'DD/MM/YYYY'), '0', 85, 'CLASS_9C0D1E2F-3G4H-65C6-D7E8-9F0A1B2C3D4E', 'STUDENT_94C6H0D3-1E5F-6G7C-0H34-8F2G3D4E5C6H'), -- Pedro na Robótica
                                                                                                              ('ENROLLMENT_9D0E1F2G-3H4I-75D6-E7F8-0A9B0C1D2E3F', TO_DATE('03/01/2026', 'DD/MM/YYYY'), '0', NULL, 'CLASS_0D1E2F3G-4H5I-76D7-E8F9-0A1B2C3D4E5F', 'STUDENT_05D7I1E4-2F6G-7H8D-1I45-9G3H4E5F6D7I'), -- Alice na Games
                                                                                                              ('ENROLLMENT_0E1F2G3H-4I5J-86E7-F8G9-1A9B0C1D2E3G', TO_DATE('15/01/2026', 'DD/MM/YYYY'), '2', 95, 'CLASS_1E2F3G4H-5I6J-87E8-F9G0-1B2C3D4E5F6G', 'STUDENT_16E8J2F5-3G7H-8I9E-2J56-0H4I5F6G7E8J'); -- Miguel completou Web Design

-- 7. INSERTS PARA COURSE_MODULE
INSERT INTO course_module (module_id, title, order_index, course_id) VALUES
                                                                         ('MODULE_A1B2C3D4-E5F6-4078-9012-345678901234', 'Módulo 1 - Configuração do Ambiente', 1, 'COURSE_E1F53135-4E2B-42D3-A92B-3F4E5D6C7B8A'),
                                                                         ('MODULE_B2C3D4E5-F6A7-4189-0123-456789012345', 'Módulo 2 - Movimentos e Loops', 2, 'COURSE_E1F53135-4E2B-42D3-A92B-3F4E5D6C7B8A'),
                                                                         ('MODULE_C3D4E5F6-A7B8-4290-1234-567890123456', 'Módulo 1 - Variáveis e Tipos de Dados', 1, 'COURSE_F2G64246-5F3C-53E4-B03C-4G5F6E7D8C9B'),
                                                                         ('MODULE_D4E5F6A7-B8C9-4301-2345-678901234567', 'Módulo 1 - Introdução à mundos do Roblox', 1, 'COURSE_G3H75357-6G4D-64F5-C14D-5H6G7F8E9D0C'),
                                                                         ('MODULE_E5F6A7B8-C9D0-4412-3456-789012345678', 'Módulo 1 - Principios de IA', 1, 'COURSE_H4I86468-7H5E-75G6-D25E-6I7H8G9F0E1D');

-- 8. INSERTS PARA MATERIAL
INSERT INTO material (material_id, title, file_type, file_url, is_public, module_id) VALUES
                                                                                         ('MATERIAL_F6A7B8C9-D0E1-4523-4567-890123456789', 'Unidade I', '0', 's3://bucket/scratch/install.pdf', true, 'MODULE_A1B2C3D4-E5F6-4078-9012-345678901234'),
                                                                                         ('MATERIAL_G7B8C9D0-E1F2-4634-5678-901234567890', 'Unidade I', '1', 's3://bucket/scratch/video01.mp4', false, 'MODULE_A1B2C3D4-E5F6-4078-9012-345678901234'),
                                                                                         ('MATERIAL_H8C9D0E1-F2G3-4745-6789-012345678901', 'Unidade II', '2', 'https://scratch.mit.edu/projects/123', false, 'MODULE_B2C3D4E5-F6A7-4189-0123-456789012345'),
                                                                                         ('MATERIAL_I9D0E1F2-G3H4-4856-7890-123456789012', 'Unidade I', '0', 's3://bucket/python/vars.pdf', true, 'MODULE_C3D4E5F6-A7B8-4290-1234-567890123456'),
                                                                                         ('MATERIAL_J0E1F2G3-H4I5-4967-8901-234567890123', 'Unidade I', '2', 's3://bucket/robotica/led.png', false, 'MODULE_D4E5F6A7-B8C9-4301-2345-678901234567');

