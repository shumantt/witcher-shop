INSERT INTO roles (name) VALUES ('client'), ('employee'), ('manager');

INSERT INTO users (login, name, role_id, password)
VALUES
    ('witcher', 'Геральт из Ривии', 0, '123'),
    ('alchemist', 'Николас Фламель', 1, '123'),
    ('manager', 'Лютик', 2, '123');

INSERT INTO categories (name) VALUES
    ('Руна'), ('Растение'), ('Зелье'),
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
    ('Слово силы', 'Импортировано из провинций Скайрима');

INSERT INTO recipe_goods VALUES
    (1, 4), (1, 6), (1, 7),
    (2, 5), (2, 7),
    (3, 4), (3, 7), (3, 8);

INSERT INTO goods_categories VALUES
    (1, 5), (1, 3), (2, 3), (2, 6), (3, 6), (3, 3), (3, 4),
    (4, 2), (5, 2), (6, 2), (7, 1), (8, 1);
