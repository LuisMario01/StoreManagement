--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.3

-- Started on 2018-06-22 20:39:11

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE product_store;
--
-- TOC entry 2156 (class 1262 OID 45365)
-- Name: product_store; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE product_store WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_El Salvador.1252' LC_CTYPE = 'Spanish_El Salvador.1252';


ALTER DATABASE product_store OWNER TO postgres;

\connect product_store

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2157 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 8 (class 2615 OID 45366)
-- Name: store; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA store;


ALTER SCHEMA store OWNER TO postgres;

--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2159 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = store, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 187 (class 1259 OID 45393)
-- Name: likes; Type: TABLE; Schema: store; Owner: postgres
--

CREATE TABLE likes (
    id_like integer NOT NULL,
    id_user integer NOT NULL,
    id_product integer NOT NULL
);


ALTER TABLE likes OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 45391)
-- Name: likes_id_like_seq; Type: SEQUENCE; Schema: store; Owner: postgres
--

CREATE SEQUENCE likes_id_like_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE likes_id_like_seq OWNER TO postgres;

--
-- TOC entry 2160 (class 0 OID 0)
-- Dependencies: 186
-- Name: likes_id_like_seq; Type: SEQUENCE OWNED BY; Schema: store; Owner: postgres
--

ALTER SEQUENCE likes_id_like_seq OWNED BY likes.id_like;


--
-- TOC entry 185 (class 1259 OID 45377)
-- Name: product; Type: TABLE; Schema: store; Owner: postgres
--

CREATE TABLE product (
    id_product integer NOT NULL,
    product character varying(20) NOT NULL,
    price double precision NOT NULL,
    stock integer NOT NULL
);


ALTER TABLE product OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 45375)
-- Name: product_id_product_seq; Type: SEQUENCE; Schema: store; Owner: postgres
--

CREATE SEQUENCE product_id_product_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_id_product_seq OWNER TO postgres;

--
-- TOC entry 2161 (class 0 OID 0)
-- Dependencies: 184
-- Name: product_id_product_seq; Type: SEQUENCE OWNED BY; Schema: store; Owner: postgres
--

ALTER SEQUENCE product_id_product_seq OWNED BY product.id_product;


--
-- TOC entry 189 (class 1259 OID 45411)
-- Name: product_log; Type: TABLE; Schema: store; Owner: postgres
--

CREATE TABLE product_log (
    id_product_log integer NOT NULL,
    previous_price double precision NOT NULL,
    current_price double precision NOT NULL,
    id_product integer NOT NULL
);


ALTER TABLE product_log OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 45409)
-- Name: product_log_id_product_log_seq; Type: SEQUENCE; Schema: store; Owner: postgres
--

CREATE SEQUENCE product_log_id_product_log_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_log_id_product_log_seq OWNER TO postgres;

--
-- TOC entry 2162 (class 0 OID 0)
-- Dependencies: 188
-- Name: product_log_id_product_log_seq; Type: SEQUENCE OWNED BY; Schema: store; Owner: postgres
--

ALTER SEQUENCE product_log_id_product_log_seq OWNED BY product_log.id_product_log;


--
-- TOC entry 191 (class 1259 OID 45442)
-- Name: purchase; Type: TABLE; Schema: store; Owner: postgres
--

CREATE TABLE purchase (
    id_purchase integer NOT NULL,
    id_user integer NOT NULL,
    id_product integer NOT NULL,
    amount integer NOT NULL,
    purchasedate date NOT NULL
);


ALTER TABLE purchase OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 45440)
-- Name: purchase_id_purchase_seq; Type: SEQUENCE; Schema: store; Owner: postgres
--

CREATE SEQUENCE purchase_id_purchase_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE purchase_id_purchase_seq OWNER TO postgres;

--
-- TOC entry 2163 (class 0 OID 0)
-- Dependencies: 190
-- Name: purchase_id_purchase_seq; Type: SEQUENCE OWNED BY; Schema: store; Owner: postgres
--

ALTER SEQUENCE purchase_id_purchase_seq OWNED BY purchase.id_purchase;


--
-- TOC entry 183 (class 1259 OID 45369)
-- Name: store_user; Type: TABLE; Schema: store; Owner: postgres
--

CREATE TABLE store_user (
    id_user integer NOT NULL,
    username character varying(10) NOT NULL,
    password character varying(64) NOT NULL,
    role integer NOT NULL
);


ALTER TABLE store_user OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 45367)
-- Name: store_user_id_user_seq; Type: SEQUENCE; Schema: store; Owner: postgres
--

CREATE SEQUENCE store_user_id_user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE store_user_id_user_seq OWNER TO postgres;

--
-- TOC entry 2164 (class 0 OID 0)
-- Dependencies: 182
-- Name: store_user_id_user_seq; Type: SEQUENCE OWNED BY; Schema: store; Owner: postgres
--

ALTER SEQUENCE store_user_id_user_seq OWNED BY store_user.id_user;


--
-- TOC entry 2008 (class 2604 OID 45396)
-- Name: id_like; Type: DEFAULT; Schema: store; Owner: postgres
--

ALTER TABLE ONLY likes ALTER COLUMN id_like SET DEFAULT nextval('likes_id_like_seq'::regclass);


--
-- TOC entry 2007 (class 2604 OID 45380)
-- Name: id_product; Type: DEFAULT; Schema: store; Owner: postgres
--

ALTER TABLE ONLY product ALTER COLUMN id_product SET DEFAULT nextval('product_id_product_seq'::regclass);


--
-- TOC entry 2009 (class 2604 OID 45414)
-- Name: id_product_log; Type: DEFAULT; Schema: store; Owner: postgres
--

ALTER TABLE ONLY product_log ALTER COLUMN id_product_log SET DEFAULT nextval('product_log_id_product_log_seq'::regclass);


--
-- TOC entry 2010 (class 2604 OID 45445)
-- Name: id_purchase; Type: DEFAULT; Schema: store; Owner: postgres
--

ALTER TABLE ONLY purchase ALTER COLUMN id_purchase SET DEFAULT nextval('purchase_id_purchase_seq'::regclass);


--
-- TOC entry 2006 (class 2604 OID 45372)
-- Name: id_user; Type: DEFAULT; Schema: store; Owner: postgres
--

ALTER TABLE ONLY store_user ALTER COLUMN id_user SET DEFAULT nextval('store_user_id_user_seq'::regclass);


--
-- TOC entry 2147 (class 0 OID 45393)
-- Dependencies: 187
-- Data for Name: likes; Type: TABLE DATA; Schema: store; Owner: postgres
--

INSERT INTO likes (id_like, id_user, id_product) VALUES (1, 4, 2);
INSERT INTO likes (id_like, id_user, id_product) VALUES (2, 3, 2);
INSERT INTO likes (id_like, id_user, id_product) VALUES (3, 2, 4);
INSERT INTO likes (id_like, id_user, id_product) VALUES (4, 2, 4);


--
-- TOC entry 2165 (class 0 OID 0)
-- Dependencies: 186
-- Name: likes_id_like_seq; Type: SEQUENCE SET; Schema: store; Owner: postgres
--

SELECT pg_catalog.setval('likes_id_like_seq', 4, true);


--
-- TOC entry 2145 (class 0 OID 45377)
-- Dependencies: 185
-- Data for Name: product; Type: TABLE DATA; Schema: store; Owner: postgres
--

INSERT INTO product (id_product, product, price, stock) VALUES (3, 'Pecans', 2, 40);
INSERT INTO product (id_product, product, price, stock) VALUES (5, 'Coca-Cola', 0.59999999999999998, 30);
INSERT INTO product (id_product, product, price, stock) VALUES (6, 'Pepsi', 0.59999999999999998, 90);
INSERT INTO product (id_product, product, price, stock) VALUES (-39, 'Milk', 1.2, 10);
INSERT INTO product (id_product, product, price, stock) VALUES (-38, 'Takis', 0.5, 1000);
INSERT INTO product (id_product, product, price, stock) VALUES (12, 'Almonds', 3, 76);
INSERT INTO product (id_product, product, price, stock) VALUES (1, 'Goldfish', 1.5, 30);
INSERT INTO product (id_product, product, price, stock) VALUES (4, 'Cheetos', 0.94999999999999996, 140);
INSERT INTO product (id_product, product, price, stock) VALUES (13, 'Chips&Fish', 3, 76);
INSERT INTO product (id_product, product, price, stock) VALUES (14, 'Potato chips', 0.29999999999999999, 76);
INSERT INTO product (id_product, product, price, stock) VALUES (15, 'Chocolate', 1, 180);
INSERT INTO product (id_product, product, price, stock) VALUES (2, 'Cashews', 4, 76);


--
-- TOC entry 2166 (class 0 OID 0)
-- Dependencies: 184
-- Name: product_id_product_seq; Type: SEQUENCE SET; Schema: store; Owner: postgres
--

SELECT pg_catalog.setval('product_id_product_seq', 15, true);


--
-- TOC entry 2149 (class 0 OID 45411)
-- Dependencies: 189
-- Data for Name: product_log; Type: TABLE DATA; Schema: store; Owner: postgres
--

INSERT INTO product_log (id_product_log, previous_price, current_price, id_product) VALUES (1, 0.55000000000000004, 0.94999999999999996, 4);
INSERT INTO product_log (id_product_log, previous_price, current_price, id_product) VALUES (2, 1, 4, 2);


--
-- TOC entry 2167 (class 0 OID 0)
-- Dependencies: 188
-- Name: product_log_id_product_log_seq; Type: SEQUENCE SET; Schema: store; Owner: postgres
--

SELECT pg_catalog.setval('product_log_id_product_log_seq', 2, true);


--
-- TOC entry 2151 (class 0 OID 45442)
-- Dependencies: 191
-- Data for Name: purchase; Type: TABLE DATA; Schema: store; Owner: postgres
--

INSERT INTO purchase (id_purchase, id_user, id_product, amount, purchasedate) VALUES (2, 2, 1, 10, '2018-06-21');
INSERT INTO purchase (id_purchase, id_user, id_product, amount, purchasedate) VALUES (3, 3, 1, 10, '2018-06-21');
INSERT INTO purchase (id_purchase, id_user, id_product, amount, purchasedate) VALUES (4, 3, 2, 4, '2018-06-22');


--
-- TOC entry 2168 (class 0 OID 0)
-- Dependencies: 190
-- Name: purchase_id_purchase_seq; Type: SEQUENCE SET; Schema: store; Owner: postgres
--

SELECT pg_catalog.setval('purchase_id_purchase_seq', 4, true);


--
-- TOC entry 2143 (class 0 OID 45369)
-- Dependencies: 183
-- Data for Name: store_user; Type: TABLE DATA; Schema: store; Owner: postgres
--

INSERT INTO store_user (id_user, username, password, role) VALUES (1, 'admin', 'password', 1);
INSERT INTO store_user (id_user, username, password, role) VALUES (2, 'user', 'password', 2);
INSERT INTO store_user (id_user, username, password, role) VALUES (3, 'user2', 'password', 2);
INSERT INTO store_user (id_user, username, password, role) VALUES (4, 'user3', 'password', 2);


--
-- TOC entry 2169 (class 0 OID 0)
-- Dependencies: 182
-- Name: store_user_id_user_seq; Type: SEQUENCE SET; Schema: store; Owner: postgres
--

SELECT pg_catalog.setval('store_user_id_user_seq', 4, true);


--
-- TOC entry 2018 (class 2606 OID 45398)
-- Name: likes_pkey; Type: CONSTRAINT; Schema: store; Owner: postgres
--

ALTER TABLE ONLY likes
    ADD CONSTRAINT likes_pkey PRIMARY KEY (id_like);


--
-- TOC entry 2020 (class 2606 OID 45416)
-- Name: product_log_pkey; Type: CONSTRAINT; Schema: store; Owner: postgres
--

ALTER TABLE ONLY product_log
    ADD CONSTRAINT product_log_pkey PRIMARY KEY (id_product_log);


--
-- TOC entry 2016 (class 2606 OID 45382)
-- Name: product_pkey; Type: CONSTRAINT; Schema: store; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id_product);


--
-- TOC entry 2022 (class 2606 OID 45447)
-- Name: purchase_pkey; Type: CONSTRAINT; Schema: store; Owner: postgres
--

ALTER TABLE ONLY purchase
    ADD CONSTRAINT purchase_pkey PRIMARY KEY (id_purchase);


--
-- TOC entry 2012 (class 2606 OID 45374)
-- Name: store_user_pkey; Type: CONSTRAINT; Schema: store; Owner: postgres
--

ALTER TABLE ONLY store_user
    ADD CONSTRAINT store_user_pkey PRIMARY KEY (id_user);


--
-- TOC entry 2014 (class 2606 OID 45463)
-- Name: uniqueusername; Type: CONSTRAINT; Schema: store; Owner: postgres
--

ALTER TABLE ONLY store_user
    ADD CONSTRAINT uniqueusername UNIQUE (username);


--
-- TOC entry 2024 (class 2606 OID 45404)
-- Name: likes_id_product_fkey; Type: FK CONSTRAINT; Schema: store; Owner: postgres
--

ALTER TABLE ONLY likes
    ADD CONSTRAINT likes_id_product_fkey FOREIGN KEY (id_product) REFERENCES product(id_product) ON DELETE CASCADE;


--
-- TOC entry 2023 (class 2606 OID 45399)
-- Name: likes_id_user_fkey; Type: FK CONSTRAINT; Schema: store; Owner: postgres
--

ALTER TABLE ONLY likes
    ADD CONSTRAINT likes_id_user_fkey FOREIGN KEY (id_user) REFERENCES store_user(id_user) ON DELETE CASCADE;


--
-- TOC entry 2025 (class 2606 OID 45417)
-- Name: product_log_id_product_fkey; Type: FK CONSTRAINT; Schema: store; Owner: postgres
--

ALTER TABLE ONLY product_log
    ADD CONSTRAINT product_log_id_product_fkey FOREIGN KEY (id_product) REFERENCES product(id_product);


--
-- TOC entry 2027 (class 2606 OID 45453)
-- Name: purchase_id_product_fkey; Type: FK CONSTRAINT; Schema: store; Owner: postgres
--

ALTER TABLE ONLY purchase
    ADD CONSTRAINT purchase_id_product_fkey FOREIGN KEY (id_product) REFERENCES product(id_product);


--
-- TOC entry 2026 (class 2606 OID 45448)
-- Name: purchase_id_user_fkey; Type: FK CONSTRAINT; Schema: store; Owner: postgres
--

ALTER TABLE ONLY purchase
    ADD CONSTRAINT purchase_id_user_fkey FOREIGN KEY (id_user) REFERENCES store_user(id_user);


--
-- TOC entry 2158 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2018-06-22 20:39:11

--
-- PostgreSQL database dump complete
--

