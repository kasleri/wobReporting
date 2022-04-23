-- Database: wobreportingdb

-- DROP DATABASE IF EXISTS wobreportingdb;

CREATE DATABASE wobreportingdb
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United Kingdom.1250'
    LC_CTYPE = 'English_United Kingdom.1250'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE IF NOT EXISTS public.locations
(
    id uuid NOT NULL,
    manager_name text COLLATE pg_catalog."default",
    phone text COLLATE pg_catalog."default",
    address_primary text COLLATE pg_catalog."default",
    address_secondary text COLLATE pg_catalog."default",
    country text COLLATE pg_catalog."default",
    town text COLLATE pg_catalog."default",
    postal_code text COLLATE pg_catalog."default",
    CONSTRAINT locations_pkey PRIMARY KEY (id),
    CONSTRAINT locations_id_key UNIQUE (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.locations
    OWNER to postgres;

-----------------------------------------------------
-- Table: public.listing_status

-- DROP TABLE IF EXISTS public.listing_status;

CREATE TABLE IF NOT EXISTS public.listing_status
(
    id integer NOT NULL ,
    status_name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT listing_status_pkey PRIMARY KEY (id),
    CONSTRAINT listing_status_id_key UNIQUE (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.listing_status
    OWNER to postgres;

-----------------------------------------------------

-- Table: public.marketplaces

-- DROP TABLE IF EXISTS public.marketplaces;

CREATE TABLE IF NOT EXISTS public.marketplaces
(
    id integer NOT NULL ,
    marketplace_name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT marketplaces_pkey PRIMARY KEY (id),
    CONSTRAINT marketplaces_id_key UNIQUE (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.marketplaces
    OWNER to postgres;
-----------------------------------------------------
-- Table: public.listings

-- DROP TABLE IF EXISTS public.listings;

CREATE TABLE IF NOT EXISTS public.listings
(
    id UUID NOT NULL ,
    title text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default" NOT NULL,
    location_id uuid NOT NULL,
    listing_price NUMERIC(6, 2) NOT NULL,
    currency character varying(3) COLLATE pg_catalog."default" NOT NULL,
    quantity integer NOT NULL,
    listing_status integer NOT NULL,
    marketplace integer NOT NULL,
    upload_time date NOT NULL,
    owner_email_address text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT listing_pkey PRIMARY KEY (id),
    CONSTRAINT id UNIQUE (id),
    CONSTRAINT listing_listing_status_fkey FOREIGN KEY (listing_status)
        REFERENCES public.listing_status (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT listing_location_id_fkey FOREIGN KEY (location_id)
        REFERENCES public.locations (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT listing_marketplace_fkey FOREIGN KEY (marketplace)
        REFERENCES public.marketplaces (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.listings
    OWNER to postgres;