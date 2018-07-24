-- Table: public."SALES_TRANSACTION"

-- DROP TABLE public."SALES_TRANSACTION";

CREATE TABLE public.sales_transaction
(
    ordernumber numeric(10,0),
    quantityordered numeric(10,0),
    priceeach numeric(12,2),
    orderlinenumber character varying(250) COLLATE pg_catalog."default",
    sales numeric(12,2),
    orderdate timestamp without time zone,
    status character varying(20) COLLATE pg_catalog."default",
    qtr_id numeric(6,0),
    month_id numeric(6,0),
    year_id numeric(6,0),
    productline character varying(2500) COLLATE pg_catalog."default",
    msrp numeric(6,0),
    productcode character varying(250) COLLATE pg_catalog."default",
    customername character varying(2500) COLLATE pg_catalog."default",
    phone character varying(200) COLLATE pg_catalog."default",
    addressline1 character varying(2500) COLLATE pg_catalog."default",
    addressline2 character varying(2500) COLLATE pg_catalog."default",
    city character varying(250) COLLATE pg_catalog."default",
    state character varying(250) COLLATE pg_catalog."default",
    postalcode character varying(100) COLLATE pg_catalog."default",
    country character varying(200) COLLATE pg_catalog."default",
    territory character varying(200) COLLATE pg_catalog."default",
    contactlastname character varying(2500) COLLATE pg_catalog."default",
    contactfirstname character varying(2500) COLLATE pg_catalog."default",
    dealsize character varying(20) COLLATE pg_catalog."default"
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.sales_transaction
    OWNER to postgres;