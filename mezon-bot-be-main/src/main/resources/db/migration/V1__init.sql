-- Tạo bảng Category
CREATE TABLE category
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Tạo bảng NewsPage
CREATE TABLE news_page
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    url  VARCHAR(255) NOT NULL
);

-- Tạo bảng NewsPageCategory với các quan hệ tới Category và NewsPage
CREATE TABLE news_page_category
(
    id           BIGSERIAL PRIMARY KEY,
    news_page_id BIGINT REFERENCES news_page (id) ON DELETE CASCADE,
    category_id  BIGINT REFERENCES category (id) ON DELETE CASCADE
);

-- Tạo bảng PageUrl với quan hệ one-to-one tới NewsPageCategory
CREATE TABLE page_url
(
    id                    BIGSERIAL PRIMARY KEY,
    url                   VARCHAR(255) NOT NULL,
    news_page_category_id BIGINT REFERENCES news_page_category (id) ON DELETE CASCADE
);

-- Tạo bảng News với quan hệ tới PageUrl
CREATE TABLE news
(
    id           BIGSERIAL PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    content      TEXT,
    source       VARCHAR(255),
    url          VARCHAR(255),
    publish_date TIMESTAMP,
    page_url_id  BIGINT       REFERENCES page_url (id) ON DELETE SET NULL
);
