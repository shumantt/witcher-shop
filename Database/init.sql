CREATE TABLE categories (
    id serial PRIMARY KEY,
    name VARCHAR(30) NOT NULL
);

CREATE TABLE goods (
    id serial PRIMARY KEY,
    name VARCHAR(30) UNIQUE NOT NULL,
    description TEXT,
    quantity integer DEFAULT 0,
    is_producable boolean DEFAULT false,
    instruction TEXT
);

CREATE TABLE recipe_goods (
    recipe_id integer NOT NULL REFERENCES goods (id) ON DELETE CASCADE,
    component_id integer NOT NULL REFERENCES goods (id) ON DELETE RESTRICT,
    required_quantity integer DEFAULT 1,
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
    login VARCHAR(30) PRIMARY KEY,
    name VARCHAR(30) UNIQUE NOT NULL,
    picture_url TEXT,
    role_id integer NOT NULL,
    password TEXT NOT NULL
);

CREATE TABLE orders (
    id serial PRIMARY KEY,
    client_id VARCHAR(30) NOT NULL REFERENCES users (login),
    goods_id integer NOT NULL REFERENCES goods (id),
    quantity integer NOT NULL,
    phone VARCHAR(20),
    status integer NOT NULL
);

CREATE TABLE history (
    id serial PRIMARY KEY,
    user_id VARCHAR(30) NOT NULL REFERENCES users (login),
    product_id integer NOT NULL REFERENCES goods (id),
    change integer NOT NULL,
    date DATE NOT NULL
);
