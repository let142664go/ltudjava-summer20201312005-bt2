--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-06-17 22:38:19

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 24799)
-- Name: LOP_MON_HOC; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."LOP_MON_HOC" (
    "MA_LOP" character varying(20) NOT NULL,
    "MA_MON_HOC" character varying(20) NOT NULL,
    "PHONG_HOC" character varying(20)
);


ALTER TABLE public."LOP_MON_HOC" OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 24804)
-- Name: MON_HOC; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."MON_HOC" (
    "MA" character varying(20) NOT NULL,
    "TEN" character varying(100) NOT NULL
);


ALTER TABLE public."MON_HOC" OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 24809)
-- Name: SINH_VIEN; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."SINH_VIEN" (
    "MA" character varying(20) NOT NULL,
    "TEN" character varying(100) NOT NULL,
    "GIOI_TINH" character varying(20),
    "CMND" character varying(20)
);


ALTER TABLE public."SINH_VIEN" OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 24820)
-- Name: SINH_VIEN_LOP; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."SINH_VIEN_LOP" (
    "MA_SINH_VIEN" character varying(20) NOT NULL,
    "MA_LOP" character varying(20) NOT NULL,
    "TRANG_THAI" integer,
    "DIEM_GK" numeric,
    "DIEM_CK" numeric,
    "DIEM_KHAC" numeric,
    "DIEM_TONG" numeric
);


ALTER TABLE public."SINH_VIEN_LOP" OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 24814)
-- Name: TAI_KHOAN; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."TAI_KHOAN" (
    "MA" character varying(20) NOT NULL,
    "MAT_KHAU" character varying(100),
    "LICENSE_ID" character varying(20)
);


ALTER TABLE public."TAI_KHOAN" OWNER TO postgres;

--
-- TOC entry 2703 (class 2606 OID 24803)
-- Name: LOP_MON_HOC LOP_MON_HOC_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."LOP_MON_HOC"
    ADD CONSTRAINT "LOP_MON_HOC_pkey" PRIMARY KEY ("MA_LOP", "MA_MON_HOC");


--
-- TOC entry 2705 (class 2606 OID 24808)
-- Name: MON_HOC MON_HOC_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."MON_HOC"
    ADD CONSTRAINT "MON_HOC_pkey" PRIMARY KEY ("MA");


--
-- TOC entry 2711 (class 2606 OID 24827)
-- Name: SINH_VIEN_LOP SINH_VIEN_LOP_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."SINH_VIEN_LOP"
    ADD CONSTRAINT "SINH_VIEN_LOP_pkey" PRIMARY KEY ("MA_LOP", "MA_SINH_VIEN");


--
-- TOC entry 2707 (class 2606 OID 24813)
-- Name: SINH_VIEN SINH_VIEN_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."SINH_VIEN"
    ADD CONSTRAINT "SINH_VIEN_pkey" PRIMARY KEY ("MA");


--
-- TOC entry 2709 (class 2606 OID 24818)
-- Name: TAI_KHOAN TAI_KHOAN_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."TAI_KHOAN"
    ADD CONSTRAINT "TAI_KHOAN_pkey" PRIMARY KEY ("MA");

INSERT INTO public."TAI_KHOAN"( "MA", "MAT_KHAU", "LICENSE_ID") VALUES ('giaovu', 'giaovu', 'GV');
-- Completed on 2020-06-17 22:38:21

--
-- PostgreSQL database dump complete
--

