INSERT INTO roles (name) VALUES ('client'), ('employee'), ('manager');

INSERT INTO users (login, name, role_id, password)
VALUES
    ('witcher', 'Геральт из Ривии', 1, '{bcrypt}$2a$10$CsW6AeiIWd6VlM35GPFaRuxmy1v539zu.7VBoSspDD.9FO5CICSZ.'),
    ('alchemist', 'Николас Фламель', 2, '{bcrypt}$2a$10$CsW6AeiIWd6VlM35GPFaRuxmy1v539zu.7VBoSspDD.9FO5CICSZ.'),
    ('manager', 'Лютик', 3, '{bcrypt}$2a$10$CsW6AeiIWd6VlM35GPFaRuxmy1v539zu.7VBoSspDD.9FO5CICSZ.');

INSERT INTO categories (name) VALUES
    ('runes'), ('grass'), ('animals'), ('Зелье'),
    ('Смертельное'), ('Усиливающее'), ('Магическое');

INSERT INTO goods (name, description, is_producable) VALUES
    ('Волкодав', 'Секретный рецепт школы Волка.', true),
    ('Драконья гадость', 'Драконы вас не тронут.', true),
    ('Поцелуй вампира', 'Станьте бледным как Смерть!', true);

INSERT INTO goods (name, description) VALUES
    ('Зверобой', 'Успокаивает нервы.'),
    ('Подсолнух', 'Вкусные семечки.'),
    ('Семилистник', 'У него аж семь листьев.'),
    ('Руна древних', 'Наследие викингов.'),
    ('Слово силы', 'Импортировано из провинций Скайрима'),
    ('Нога звер', 'Бедный звер');

INSERT INTO recipe_goods VALUES
    (1, 4), (1, 6), (1, 7),
    (2, 5), (2, 7),
    (3, 4), (3, 7), (3, 8);

INSERT INTO goods_categories VALUES
    (1, 6), (1, 4), (2, 4), (2, 7), (3, 7), (3, 4), (3, 5),
    (4, 2), (5, 2), (6, 2), (7, 1), (8, 1), (9, 3);
