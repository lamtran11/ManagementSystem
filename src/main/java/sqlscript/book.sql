-- Create the Shop_Detail table
CREATE TABLE Shop_Detail (
    id SERIAL PRIMARY KEY NOT NULL,
    address VARCHAR(256) NOT NULL,
    branch_address_1 VARCHAR(256),
    branch_address_2 VARCHAR(256)  -- Fixed typo from varcahr to VARCHAR
);

-- Create the Shop table
CREATE TABLE Shop (
    id SERIAL PRIMARY KEY NOT NULL,  -- Added PRIMARY KEY constraint
    name VARCHAR(100) DEFAULT NULL,
    email VARCHAR(45) DEFAULT NULL,
    shop_detail_id INTEGER,
    FOREIGN KEY (shop_detail_id) REFERENCES Shop_Detail(id) 
        ON DELETE CASCADE
        ON UPDATE NO ACTION 
);

-- Create the Book table
CREATE TABLE Book (
    id SERIAL PRIMARY KEY NOT NULL,
    title VARCHAR(128) UNIQUE NOT NULL,
    shop_id INTEGER,
    FOREIGN KEY (shop_id) REFERENCES Shop(id) 
        ON DELETE SET NULL
        ON UPDATE NO ACTION 
);

-- Create the Customer table
CREATE TABLE Customer (
    id SERIAL PRIMARY KEY NOT NULL,
    first_name VARCHAR(45) DEFAULT NULL,
    last_name VARCHAR(45) DEFAULT NULL,
    email VARCHAR(45) DEFAULT NULL
);

-- Create the Customer_Book table (many-to-many relationship)
CREATE TABLE Customer_Book (
    book_id INTEGER NOT NULL,
    customer_id INTEGER NOT NULL,
    PRIMARY KEY (book_id, customer_id),  -- Added composite primary key
    FOREIGN KEY (book_id) REFERENCES Book(id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    FOREIGN KEY (customer_id) REFERENCES Customer(id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION 
);

-- Create the Review table
CREATE TABLE Review (
    id SERIAL PRIMARY KEY NOT NULL,  -- Added PRIMARY KEY constraint
    comment VARCHAR(256) DEFAULT NULL,
    book_id INTEGER,
    FOREIGN KEY (book_id) REFERENCES Book(id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION 
);


ALTER TABLE Book
ADD COLUMN author varchar(45) DEFAULT NULL;



-- Shop_Detail data
INSERT INTO Shop_Detail (address, branch_address_1, branch_address_2) VALUES
('東京都新宿区西新宿2-8-1', '渋谷区神南1-19-11', '品川区東品川2-2-24'),
('大阪府大阪市北区梅田3-1-1', '中央区難波5-1-60', NULL),
('愛知県名古屋市中村区名駅3-16-1', NULL, NULL),
('福岡県福岡市博多区博多駅前2-1-1', NULL, NULL),
('北海道札幌市中央区北5条西2丁目5', NULL, NULL);

-- Shop data
INSERT INTO Shop (name, email, shop_detail_id) VALUES
('東京ブックストア', 'tokyo@bookstore.jp', 1),
('大阪ブックストア', 'osaka@bookstore.jp', 2),
('名古屋ブックストア', 'nagoya@bookstore.jp', 3),
('福岡ブックストア', 'fukuoka@bookstore.jp', 4),
('札幌ブックストア', 'sapporo@bookstore.jp', 5);

-- Book data with author
INSERT INTO Book (title, author, shop_id) VALUES
('日本の歴史', '山田 太郎', 1),
('プログラミング入門', '田中 花子', 2),
('データベース設計', '佐藤 一郎', 3),
('料理の基本', '鈴木 美咲', 4),
('ビジネス成功法', '高橋 健一', 5),
('旅行ガイド東京', '山田 太郎', 1),
('英語学習法', '田中 花子', 2),
('趣味の園芸', '佐藤 一郎', 3),
('科学の不思議', '鈴木 美咲', 4),
('世界の文学', '高橋 健一', 5);

-- Customer data
INSERT INTO Customer (first_name, last_name, email) VALUES
('太郎', '山田', 'yamada_taro@example.jp'),
('花子', '田中', 'tanaka_hanako@example.jp'),
('一郎', '佐藤', 'sato_ichiro@example.jp'),
('美咲', '鈴木', 'suzuki_misaki@example.jp'),
('健一', '高橋', 'takahashi_kenichi@example.jp'),
('絵里', '伊藤', 'ito_eri@example.jp'),
('慎吾', '渡辺', 'watanabe_shingo@example.jp'),
('桃子', '中村', 'nakamura_momoko@example.jp'),
('直樹', '小林', 'kobayashi_naoki@example.jp'),
('結衣', '加藤', 'kato_yui@example.jp');

-- Customer_Book data
INSERT INTO Customer_Book (book_id, customer_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

-- Review data
INSERT INTO Review (comment, book_id) VALUES
('非常に面白い本でした！', 1),
('役に立つ情報が多く、勉強になりました。', 2),
('もう少し具体的な例が欲しかったです。', 3),
('初心者向けでわかりやすい内容です。', 4),
('非常にインスピレーションを受けました！', 5),
('旅行の計画に大いに役立ちました。', 6),
('実践的な英語学習法が学べました。', 7),
('植物を育てるのが楽しくなりました！', 8),
('子供と一緒に楽しめる内容でした。', 9),
('文学ファンにはたまらない一冊です。', 10);





