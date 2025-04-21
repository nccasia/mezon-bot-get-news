-- Insert data into news_page
INSERT INTO news_page (url, name)
VALUES ('https://baomoi.com/', 'Báo mới'),
       ('https://www.bbc.com/', 'BBC'),
       ('https://www.washingtonpost.com/', 'Washington post');

-- Insert data into category
INSERT INTO category (name)
VALUES ('football'),
       ('world'),
       ('society'),
       ('culture'),
       ('economy'),
       ('education'),
       ('sports'),
       ('entertainment'),
       ('law'),
       ('technology'),
       ('science'),
       ('life'),
       ('vehicles'),
       ('real estate'),
       ('hot'),
       ('new');

-- Insert data into news_page_category (linking news_page, category, and page_url)
INSERT INTO news_page_category (news_page_id, category_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (1, 8),
       (1, 9),
       (1, 10),
       (1, 11),
       (1, 12),
       (1, 13),
       (1, 14),
       (1, 15),
       (1, 16);

-- Insert data into page_url
INSERT INTO page_url (url, news_page_category_id)
VALUES ('https://baomoi.com/bong-da.epi', 1),
       ('https://baomoi.com/the-gioi.epi', 2),
       ('https://baomoi.com/xa-hoi.epi', 3),
       ('https://baomoi.com/van-hoa.epi', 4),
       ('https://baomoi.com/kinh-te.epi', 5),
       ('https://baomoi.com/giao-duc.epi', 6),
       ('https://baomoi.com/the-thao.epi', 7),
       ('https://baomoi.com/giai-tri.epi', 8),
       ('https://baomoi.com/phap-luat.epi', 9),
       ('https://baomoi.com/khoa-hoc-cong-nghe.epi', 10),
       ('https://baomoi.com/khoa-hoc.epi', 11),
       ('https://baomoi.com/doi-song.epi', 12),
       ('https://baomoi.com/xe-co.epi', 13),
       ('https://baomoi.com/nha-dat.epi', 14),
       ('https://baomoi.com/', 15),
       ('https://baomoi.com/tin-moi.epi', 16);

