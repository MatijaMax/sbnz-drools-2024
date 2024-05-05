--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

-- Started on 2024-05-04 17:04:46

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4923 (class 0 OID 415778)
-- Dependencies: 221
-- Data for Name: arrangements; Type: TABLE DATA; Schema: isa; Owner: postgres
--

INSERT INTO isa.arrangements (id, date_added, name, price) VALUES (1, '2024-05-03', 'Salatiranje', 124);
INSERT INTO isa.arrangements (id, date_added, name, price) VALUES (2, '2024-05-04', 'Jako aranzmanisanje', 520);
INSERT INTO isa.arrangements (id, date_added, name, price) VALUES (3, '2024-05-04', 'Bali Mali', 28000);


--
-- TOC entry 4929 (class 0 OID 415801)
-- Dependencies: 227
-- Data for Name: users; Type: TABLE DATA; Schema: isa; Owner: postgres
--

INSERT INTO isa.users (id, city, company_info, country, email, first_name, last_name, password, phone, profession) VALUES (1, 'a', '.', 'a', 'author1@gmail.com', 'a', 'a', '$2a$10$XoUsms6Ek2d1ODlyFMlRMe2foG8btfzP98zC8OkIWXi3XyCWeRdZ6', 123, 'a');
INSERT INTO isa.users (id, city, company_info, country, email, first_name, last_name, password, phone, profession) VALUES (2, 'a', '.', 'a', 'admin@gmail.com', 'a', 'a', '$2a$10$35e095853WVKwBn2ZAgq5O7RclCqT5K8Xvc9krpGg3UONzHBDKTsW', 123, 'a');


--
-- TOC entry 4919 (class 0 OID 415764)
-- Dependencies: 217
-- Data for Name: arrangement_grades; Type: TABLE DATA; Schema: isa; Owner: postgres
--



--
-- TOC entry 4921 (class 0 OID 415771)
-- Dependencies: 219
-- Data for Name: arrangement_reservation; Type: TABLE DATA; Schema: isa; Owner: postgres
--



--
-- TOC entry 4931 (class 0 OID 415812)
-- Dependencies: 229
-- Data for Name: role; Type: TABLE DATA; Schema: isa; Owner: postgres
--

INSERT INTO isa.role (id, name) VALUES (1, 'ROLE_Regular');
INSERT INTO isa.role (id, name) VALUES (2, 'ROLE_Admin');


--
-- TOC entry 4933 (class 0 OID 415819)
-- Dependencies: 231
-- Data for Name: secure_tokens; Type: TABLE DATA; Schema: isa; Owner: postgres
--



--
-- TOC entry 4935 (class 0 OID 415826)
-- Dependencies: 233
-- Data for Name: student; Type: TABLE DATA; Schema: isa; Owner: postgres
--



--
-- TOC entry 4927 (class 0 OID 415792)
-- Dependencies: 225
-- Data for Name: trips; Type: TABLE DATA; Schema: isa; Owner: postgres
--

INSERT INTO isa.trips (id, description, name, price, type, arrangement_id) VALUES (1, 'jako lososiranje', 'Lososiranje', 123, 3, 1);
INSERT INTO isa.trips (id, description, name, price, type, arrangement_id) VALUES (2, 'Spargliranje u fulu', 'Spargliranje', 625, 1, 1);
INSERT INTO isa.trips (id, description, name, price, type, arrangement_id) VALUES (3, 'Kremasti zabagljone sa Bejlis krem', 'Zabagljone', 420, 2, 1);
INSERT INTO isa.trips (id, description, name, price, type, arrangement_id) VALUES (4, 'Jako terasiranje', 'Sedenje na terasi', 690, 1, 2);
INSERT INTO isa.trips (id, description, name, price, type, arrangement_id) VALUES (5, 'Rskavo pecenie', 'Pecenje', 1600, 2, 2);
INSERT INTO isa.trips (id, description, name, price, type, arrangement_id) VALUES (6, 'Jako balavljenje, obala u fulu', 'Obala Bala', 234, 1, 3);
INSERT INTO isa.trips (id, description, name, price, type, arrangement_id) VALUES (7, 'Sir u fulu, kremasti', 'Vulkan sira', 4200, 3, 3);
INSERT INTO isa.trips (id, description, name, price, type, arrangement_id) VALUES (8, 'Gimnastisanje u fulu, nepreporuceno za iskrivljene', 'Takmicanje u gimnastisanju', 2500, 3, 3);


--
-- TOC entry 4925 (class 0 OID 415785)
-- Dependencies: 223
-- Data for Name: trip_reservation; Type: TABLE DATA; Schema: isa; Owner: postgres
--



--
-- TOC entry 4938 (class 0 OID 415893)
-- Dependencies: 236
-- Data for Name: user_preferences; Type: TABLE DATA; Schema: isa; Owner: postgres
--

INSERT INTO isa.user_preferences (id, destinations, trips, user_id) VALUES (1, 'destinacija 1|destinacija 2', 'avantura|istorija', 1);


--
-- TOC entry 4936 (class 0 OID 415834)
-- Dependencies: 234
-- Data for Name: user_role; Type: TABLE DATA; Schema: isa; Owner: postgres
--

INSERT INTO isa.user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO isa.user_role (user_id, role_id) VALUES (2, 2);


--
-- TOC entry 4944 (class 0 OID 0)
-- Dependencies: 216
-- Name: arrangement_grades_id_seq; Type: SEQUENCE SET; Schema: isa; Owner: postgres
--

SELECT pg_catalog.setval('isa.arrangement_grades_id_seq', 1, false);


--
-- TOC entry 4945 (class 0 OID 0)
-- Dependencies: 218
-- Name: arrangement_reservation_id_seq; Type: SEQUENCE SET; Schema: isa; Owner: postgres
--

SELECT pg_catalog.setval('isa.arrangement_reservation_id_seq', 1, false);


--
-- TOC entry 4946 (class 0 OID 0)
-- Dependencies: 220
-- Name: arrangements_id_seq; Type: SEQUENCE SET; Schema: isa; Owner: postgres
--

SELECT pg_catalog.setval('isa.arrangements_id_seq', 3, true);


--
-- TOC entry 4947 (class 0 OID 0)
-- Dependencies: 228
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: isa; Owner: postgres
--

SELECT pg_catalog.setval('isa.role_id_seq', 1, false);


--
-- TOC entry 4948 (class 0 OID 0)
-- Dependencies: 230
-- Name: secure_tokens_id_seq; Type: SEQUENCE SET; Schema: isa; Owner: postgres
--

SELECT pg_catalog.setval('isa.secure_tokens_id_seq', 1, false);


--
-- TOC entry 4949 (class 0 OID 0)
-- Dependencies: 232
-- Name: student_id_seq; Type: SEQUENCE SET; Schema: isa; Owner: postgres
--

SELECT pg_catalog.setval('isa.student_id_seq', 1, false);


--
-- TOC entry 4950 (class 0 OID 0)
-- Dependencies: 222
-- Name: trip_reservation_id_seq; Type: SEQUENCE SET; Schema: isa; Owner: postgres
--

SELECT pg_catalog.setval('isa.trip_reservation_id_seq', 1, false);


--
-- TOC entry 4951 (class 0 OID 0)
-- Dependencies: 224
-- Name: trips_id_seq; Type: SEQUENCE SET; Schema: isa; Owner: postgres
--

SELECT pg_catalog.setval('isa.trips_id_seq', 8, true);


--
-- TOC entry 4952 (class 0 OID 0)
-- Dependencies: 235
-- Name: user_preferences_id_seq; Type: SEQUENCE SET; Schema: isa; Owner: postgres
--

SELECT pg_catalog.setval('isa.user_preferences_id_seq', 1, true);


--
-- TOC entry 4953 (class 0 OID 0)
-- Dependencies: 226
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: isa; Owner: postgres
--

SELECT pg_catalog.setval('isa.users_id_seq', 2, true);


-- Completed on 2024-05-04 17:04:46

--
-- PostgreSQL database dump complete
--

