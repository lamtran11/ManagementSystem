-- DROP TABLES if they exist (to avoid conflicts when re-running the script)
DROP TABLE IF EXISTS course_departments CASCADE;
DROP TABLE IF EXISTS course_instructors CASCADE;
DROP TABLE IF EXISTS enrollments CASCADE;
DROP TABLE IF EXISTS instructors CASCADE;
DROP TABLE IF EXISTS courses CASCADE;
DROP TABLE IF EXISTS students CASCADE;
DROP TABLE IF EXISTS departments CASCADE;
DROP TABLE IF EXISTS roles CASCADE;

-- Table: students
CREATE TABLE students (
                          student_id SERIAL PRIMARY KEY,
                          first_name VARCHAR(50) NOT NULL,
                          last_name VARCHAR(50) NOT NULL,
                          email VARCHAR(100) UNIQUE NOT NULL,
                          birthdate DATE,
                          address TEXT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table: courses
CREATE TABLE courses (
                         course_id SERIAL PRIMARY KEY,
                         course_name VARCHAR(100) NOT NULL,
                         description TEXT,
                         credit_hours INT NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table: enrollments (links students to courses)
CREATE TABLE enrollments (
                             enrollment_id SERIAL PRIMARY KEY,
                             student_id INT REFERENCES students(student_id) ON DELETE CASCADE,
                             course_id INT REFERENCES courses(course_id) ON DELETE CASCADE,
                             enrollment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table: instructors
CREATE TABLE instructors (
                             instructor_id VARCHAR(50) PRIMARY KEY,
                             first_name VARCHAR(50) NOT NULL,
                             last_name VARCHAR(50) NOT NULL,
                             email VARCHAR(100) UNIQUE NOT NULL,
                             hire_date DATE,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             password CHAR(68) NOT NULL,
                             active SMALLINT NOT NULL
);

-- Table: course_instructors (assign instructors to courses)
CREATE TABLE course_instructors (
                                    course_instructor_id SERIAL PRIMARY KEY,
                                    course_id INT REFERENCES courses(course_id) ON DELETE CASCADE,
                                    instructor_id VARCHAR(50) REFERENCES instructors(instructor_id) ON DELETE CASCADE,
                                    assigned_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table: departments (organizes courses into departments)
CREATE TABLE departments (
                             department_id SERIAL PRIMARY KEY,
                             department_name VARCHAR(100) NOT NULL,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table: course_departments (assigns courses to departments)
CREATE TABLE course_departments (
                                    course_department_id SERIAL PRIMARY KEY,
                                    course_id INT REFERENCES courses(course_id) ON DELETE CASCADE,
                                    department_id INT REFERENCES departments(department_id) ON DELETE CASCADE,
                                    assigned_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table: roles (defines roles linked to instructors)
CREATE TABLE roles (
                       instructor_id VARCHAR(50) PRIMARY KEY REFERENCES instructors(instructor_id) ON DELETE CASCADE,
                       role_name VARCHAR(50) NOT NULL
);

-- Insert students with Japanese names
INSERT INTO students (first_name, last_name, email, birthdate, address)
VALUES
    ('太郎', '山田', 'taro.yamada@example.com', '2000-04-12', '123 東京市, 東京'),
    ('花子', '佐藤', 'hanako.sato@example.com', '1999-07-21', '456 大阪市, 大阪'),
    ('健太', '田中', 'kenta.tanaka@example.com', '2001-11-30', '789 京都市, 京都');

-- Insert courses with Japanese names
INSERT INTO courses (course_name, description, credit_hours)
VALUES
    ('数学', '高度な数学コース', 4),
    ('物理学', '物理学の基礎', 3),
    ('コンピュータサイエンス', 'プログラミング入門', 5);

-- Insert instructors with Japanese names
INSERT INTO instructors (instructor_id, first_name, last_name, email, hire_date, password, active)
VALUES
    ('yuki', 'ゆき', '鈴木', 'yuki.suzuki@example.com', '2015-05-11', '{bcrypt}$2a$12$ctoibXEWawP.Kb9XkK5Ilelfm8A7KQHGw1dn2LgaAZtlDE7wrkvre', 1),
    ('ken', 'けん', '伊藤', 'ken.ito@example.com', '2018-09-02', '{bcrypt}$2a$12$ctoibXEWawP.Kb9XkK5Ilelfm8A7KQHGw1dn2LgaAZtlDE7wrkvre', 1),
    ('hiro', 'ひろ', '佐々木', 'hiro.sasaki@example.com', '2020-01-15', '{bcrypt}$2a$12$ctoibXEWawP.Kb9XkK5Ilelfm8A7KQHGw1dn2LgaAZtlDE7wrkvre', 1),
    ('nao', 'なお', '高橋', 'nao.takahashi@example.com', '2021-04-22', '{bcrypt}$2a$12$ctoibXEWawP.Kb9XkK5Ilelfm8A7KQHGw1dn2LgaAZtlDE7wrkvre', 1),
    ('riku', 'りく', '松本', 'riku.matsumoto@example.com', '2022-07-08', '{bcrypt}$2a$12$ctoibXEWawP.Kb9XkK5Ilelfm8A7KQHGw1dn2LgaAZtlDE7wrkvre', 1);

-- Insert departments with Japanese names
INSERT INTO departments (department_id, department_name)
VALUES
    (1, '科学部'),
    (2, '工学部'),
    (3, '人文学部');

-- Insert roles with Japanese names and link to instructors
INSERT INTO roles (instructor_id, role_name)
VALUES
    ('yuki', '管理者'),  -- ゆき as 管理者
    ('ken', '講師'),     -- けん as 講師
    ('hiro', '講師'),    -- ひろ as 講師
    ('nao', '講師'),     -- なお as 講師
    ('riku', '講師');    -- りく as 講師

-- Link courses and departments
INSERT INTO course_departments (course_id, department_id)
VALUES
    (1, 1),  -- 数学 to 科学部
    (2, 1),  -- 物理学 to 科学部
    (3, 2);  -- コンピュータサイエンス to 工学部

-- Assign instructors to courses
INSERT INTO course_instructors (course_id, instructor_id)
VALUES
    (1, 'yuki'),  -- ゆき teaching 数学
    (2, 'ken'),   -- けん teaching 物理学
    (3, 'ken'),   -- けん teaching コンピュータサイエンス
    (1, 'hiro'),  -- ひろ teaching 数学
    (2, 'nao'),   -- なお teaching 物理学
    (3, 'riku');  -- りく teaching コンピュータサイエンス

-- Enroll students in courses
INSERT INTO enrollments (student_id, course_id)
VALUES
    (1, 1), -- 太郎 enrolled in 数学
    (2, 2), -- 花子 enrolled in 物理学
    (3, 3), -- 健太 enrolled in コンピュータサイエンス
    (1, 2); -- 太郎 enrolled in 物理学


-- Update roles to use ROLE_ADMIN and ROLE_TEACHER
UPDATE roles
SET role_name = CASE
                    WHEN role_name = '管理者' THEN 'ROLE_ADMIN'
                    WHEN role_name = '講師' THEN 'ROLE_TEACHER'
                    ELSE role_name
END;


