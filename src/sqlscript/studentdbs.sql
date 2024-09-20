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


-- Insert additional students (10 records)
INSERT INTO students (first_name, last_name, email, birthdate, address)
VALUES
    ('さくら', '藤田', 'sakura.fujita@example.com', '2000-03-14', '123 名古屋市, 愛知'),
    ('まさと', '森', 'masato.mori@example.com', '1998-08-07', '456 福岡市, 福岡'),
    ('ひな', '加藤', 'hina.kato@example.com', '2001-12-02', '789 川崎市, 神奈川'),
    ('かずみ', '中村', 'kazumi.nakamura@example.com', '2000-10-10', '987 横浜市, 神奈川'),
    ('けんじ', '山口', 'kenji.yamaguchi@example.com', '1999-09-01', '654 札幌市, 北海道'),
    ('えりか', '井上', 'erika.inoue@example.com', '2001-01-19', '321 京都市, 京都'),
    ('ゆうた', '岡田', 'yuta.okada@example.com', '2002-04-22', '654 仙台市, 宮城'),
    ('まゆ', '高橋', 'mayu.takahashi@example.com', '2000-06-13', '345 神戸市, 兵庫'),
    ('さとる', '松田', 'satoru.matsuda@example.com', '1998-02-05', '432 静岡市, 静岡'),
    ('かなえ', '田村', 'kanae.tamura@example.com', '2002-11-28', '765 大阪市, 大阪');

-- Insert additional courses (10 records)
INSERT INTO courses (course_name, description, credit_hours)
VALUES
    ('生物学', '基礎的な生物学コース', 4),
    ('日本史', '日本の歴史の概要', 3),
    ('統計学', 'データ解析と統計', 5),
    ('化学', '有機化学と無機化学', 4),
    ('哲学', '西洋哲学の歴史', 3),
    ('機械工学', '機械設計の基本', 5),
    ('経済学', 'マクロ経済とミクロ経済', 4),
    ('言語学', '言語の構造と意味論', 3),
    ('心理学', '心理学の基本理論', 4),
    ('情報技術', 'コンピュータ技術の基礎', 5);

-- Insert additional instructors (10 records)
INSERT INTO instructors (instructor_id, first_name, last_name, email, hire_date, password, active)
VALUES
    ('tomoya', 'ともや', '藤井', 'tomoya.fujii@example.com', '2017-03-15', '{bcrypt}$2a$12$ctoibXEWawP.Kb9XkK5Ilelfm8A7KQHGw1dn2LgaAZtlDE7wrkvre', 1),
    ('yoko', 'ようこ', '安田', 'yoko.yasuda@example.com', '2019-09-20', '{bcrypt}$2a$12$ctoibXEWawP.Kb9XkK5Ilelfm8A7KQHGw1dn2LgaAZtlDE7wrkvre', 1),
    ('daisuke', 'だいすけ', '今井', 'daisuke.imai@example.com', '2016-05-10', '{bcrypt}$2a$12$ctoibXEWawP.Kb9XkK5Ilelfm8A7KQHGw1dn2LgaAZtlDE7wrkvre', 1),
    ('jun', 'じゅん', '石田', 'jun.ishida@example.com', '2020-07-05', '{bcrypt}$2a$12$ctoibXEWawP.Kb9XkK5Ilelfm8A7KQHGw1dn2LgaAZtlDE7wrkvre', 1),
    ('ayaka', 'あやか', '中田', 'ayaka.nakata@example.com', '2021-10-12', '{bcrypt}$2a$12$ctoibXEWawP.Kb9XkK5Ilelfm8A7KQHGw1dn2LgaAZtlDE7wrkvre', 1),
    ('kaito', 'かいと', '近藤', 'kaito.kondo@example.com', '2018-01-30', '{bcrypt}$2a$12$ctoibXEWawP.Kb9XkK5Ilelfm8A7KQHGw1dn2LgaAZtlDE7wrkvre', 1),
    ('saki', 'さき', '石原', 'saki.ishihara@example.com', '2019-11-22', '{bcrypt}$2a$12$ctoibXEWawP.Kb9XkK5Ilelfm8A7KQHGw1dn2LgaAZtlDE7wrkvre', 1),
    ('yukiha', 'ゆきは', '高野', 'yukiha.takano@example.com', '2022-03-14', '{bcrypt}$2a$12$ctoibXEWawP.Kb9XkK5Ilelfm8A7KQHGw1dn2LgaAZtlDE7wrkvre', 1),
    ('shun', 'しゅん', '田島', 'shun.tajima@example.com', '2021-08-28', '{bcrypt}$2a$12$ctoibXEWawP.Kb9XkK5Ilelfm8A7KQHGw1dn2LgaAZtlDE7wrkvre', 1),
    ('taku', 'たく', '大西', 'taku.onishi@example.com', '2022-05-06', '{bcrypt}$2a$12$ctoibXEWawP.Kb9XkK5Ilelfm8A7KQHGw1dn2LgaAZtlDE7wrkvre', 1);

-- Insert roles for new instructors
INSERT INTO roles (instructor_id, role_name)
VALUES
    ('tomoya', '講師'),
    ('yoko', '講師'),
    ('daisuke', '講師'),
    ('jun', '講師'),
    ('ayaka', '講師'),
    ('kaito', '講師'),
    ('saki', '講師'),
    ('yukiha', '講師'),
    ('shun', '講師'),
    ('taku', '管理者');

-- Link new courses and departments (ensuring 3 departments)
INSERT INTO course_departments (course_id, department_id)
VALUES
    (4, 1),  -- 生物学 to 科学部
    (5, 3),  -- 日本史 to 人文学部
    (6, 2),  -- 統計学 to 工学部
    (7, 1),  -- 化学 to 科学部
    (8, 3),  -- 哲学 to 人文学部
    (9, 2),  -- 機械工学 to 工学部
    (10, 1), -- 経済学 to 科学部
    (11, 3), -- 言語学 to 人文学部
    (12, 2), -- 心理学 to 工学部
    (13, 2); -- 情報技術 to 工学部

-- Assign new instructors to courses
INSERT INTO course_instructors (course_id, instructor_id)
VALUES
    (4, 'tomoya'),  -- Tomoya teaching 生物学
    (5, 'yoko'),    -- Yoko teaching 日本史
    (6, 'daisuke'), -- Daisuke teaching 統計学
    (7, 'jun'),     -- Jun teaching 化学
    (8, 'ayaka'),   -- Ayaka teaching 哲学
    (9, 'kaito'),   -- Kaito teaching 機械工学
    (10, 'saki'),   -- Saki teaching 経済学
    (11, 'yukiha'), -- Yukiha teaching 言語学
    (12, 'shun'),   -- Shun teaching 心理学
    (13, 'taku');   -- Taku teaching 情報技術

-- Enroll additional students in courses (no NULL values)
INSERT INTO enrollments (student_id, course_id)
VALUES
    (4, 4),  -- Kazumi enrolled in 生物学
    (5, 5),  -- Kenji enrolled in 日本史
    (6, 6),  -- Erika enrolled in 統計学
    (7, 7),  -- Yuta enrolled in 化学
    (8, 8),  -- Mayu enrolled in 哲学
    (9, 9),  -- Satoru enrolled in 機械工学
    (10, 10),-- Kanae enrolled in 経済学
    (4, 11), -- Kazumi enrolled in 言語学
    (5, 12), -- Kenji enrolled in 心理学
    (6, 13);-- Erika enrolled in 情報技術
