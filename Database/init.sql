CREATE TABLE categories (
    id serial PRIMARY KEY,
    name VARCHAR(30) NOT NULL
);

CREATE TABLE goods (
    id serial PRIMARY KEY,
    name VARCHAR(30) UNIQUE NOT NULL,
    description TEXT,
    quantity integer DEFAULT 0,
    is_producable boolean DEFAULT false
);

CREATE TABLE recipe_goods (
    recipe_id integer NOT NULL REFERENCES goods (id) ON DELETE CASCADE,
    component_id integer NOT NULL REFERENCES goods (id) ON DELETE RESTRICT,
    PRIMARY KEY (recipe_id, component_id)
);

CREATE TABLE goods_categories (
    goods_id integer NOT NULL REFERENCES goods (id) ON DELETE RESTRICT,
    category_id integer NOT NULL REFERENCES categories (id) ON DELETE CASCADE,
    PRIMARY KEY (goods_id, category_id)
);

CREATE TABLE roles (
    id serial PRIMARY KEY,
    name VARCHAR(30) UNIQUE NOT NULL
);

CREATE TABLE users (
    id serial PRIMARY KEY,
    login VARCHAR(30) UNIQUE NOT NULL,
    name VARCHAR(30) UNIQUE NOT NULL,
    picture_url TEXT,
    role_id integer NOT NULL,
    password TEXT NOT NULL
);

CREATE TABLE orders (
    id serial PRIMARY KEY,
    client_id integer NOT NULL REFERENCES users (id),
    goods_id integer NOT NULL REFERENCES goods (id),
    quantity integer NOT NULL,
    phone VARCHAR(20),
    status integer NOT NULL
);
