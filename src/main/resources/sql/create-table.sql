CREATE SEQUENCE position_sequence
    START WITH 100000
    INCREMENT BY 1
    CACHE 50;

CREATE TABLE position
(
    position_id BIGINT NOT NULL DEFAULT NEXTVAL('position_sequence'),
    name        VARCHAR(15),
    -------------------------
    CONSTRAINT position_id_pk PRIMARY KEY (position_id),
    CONSTRAINT position_name_uq UNIQUE (name)
);


CREATE SEQUENCE employee_sequence
    START WITH 100000
    INCREMENT BY 1
    CACHE 50;

CREATE TABLE employee
(
    employee_id    BIGINT      NOT NULL DEFAULT NEXTVAL('employee_sequence'),
    first_name     VARCHAR(30) NOT NULL,
    last_name      VARCHAR(30) NOT NULL,
    patronymic     VARCHAR(30),
    effective_date DATE        NOT NULL,
    position_id    INTEGER     NOT NULL,
    salary         INTEGER     NOT NULL,
    -----------------------------------
    CONSTRAINT employee_id_pk PRIMARY KEY (employee_id),
    CONSTRAINT position_id_fk FOREIGN KEY (position_id) REFERENCES position (position_id) ON DELETE CASCADE
);

COMMENT ON COLUMN employee.effective_date IS 'Дата вступления в коллектив';


CREATE SEQUENCE finance_type_sequence
    START WITH 100000
    INCREMENT BY 1
    CACHE 50;

CREATE TABLE finance_type
(
    finance_type_id BIGINT      NOT NULL DEFAULT NEXTVAL('finance_type_sequence'),
    name            VARCHAR(30) NOT NULL,
    ------------------------------------
    CONSTRAINT finance_type_id_pk PRIMARY KEY (finance_type_id),
    CONSTRAINT finance_type_uq UNIQUE (name)
);


CREATE SEQUENCE profitability_sequence
    START WITH 100000
    INCREMENT BY 1
    CACHE 50;

CREATE TABLE finance
(
    finance_id      BIGINT         NOT NULL DEFAULT NEXTVAL('profitability_sequence'),
    finance_type_id BIGINT         NOT NULL,
    value           NUMERIC(12, 2) NOT NULL,
    date            DATE           NOT NULL,
    ---------------------------------------
    CONSTRAINT finance_id_pk PRIMARY KEY (finance_id),
    CONSTRAINT finance_type_id_fk FOREIGN KEY (finance_type_id) REFERENCES finance_type (finance_type_id)
);

COMMENT ON TABLE finance IS 'Таблица доходов и расходов';
COMMENT ON COLUMN finance.value IS 'Величина дохода или расхода';


CREATE SEQUENCE owner_sequence
    START WITH 100000
    INCREMENT BY 1
    CACHE 50;

CREATE TABLE owner
(
    owner_id          BIGINT       NOT NULL DEFAULT NEXTVAL('owner_sequence'),
    first_name        VARCHAR(30)  NOT NULL,
    last_name         VARCHAR(30),
    patronymic        VARCHAR(30),
    age               INTEGER,
    email             VARCHAR(255) NOT NULL,
    phone_number      VARCHAR(30),
    password          TEXT         NOT NULL,
    business_name     VARCHAR(30)  NOT NULL,
    profile_photo_url VARCHAR(255),
    ------------------------------
    CONSTRAINT owner_id_pk PRIMARY KEY (owner_id),
    CONSTRAINT owner_email_uq UNIQUE (email)
);


CREATE SEQUENCE invoice_sequence
    START WITH 100000
    INCREMENT BY 1
    CACHE 50;

CREATE TABLE invoice
(
    invoice_id BIGINT      NOT NULL DEFAULT NEXTVAL('invoice_sequence'),
    owner_id   BIGINT      NOT NULL,
    number     VARCHAR(30) NOT NULL,
    date       DATE        NOT NULL,
    -------------------------------
    CONSTRAINT invoice_id_pk PRIMARY KEY (invoice_id),
    CONSTRAINT owner_id_fk FOREIGN KEY (owner_id) REFERENCES owner (owner_id) ON DELETE CASCADE
);

COMMENT ON TABLE invoice IS 'Таблица накладных';


CREATE SEQUENCE product_sequence
    START WITH 100000
    INCREMENT BY 1
    CACHE 50;

CREATE TABLE product
(
    product_id       BIGINT         NOT NULL DEFAULT NEXTVAL('product_sequence'),
    invoice_id       BIGINT         NOT NULL,
    name             VARCHAR(100)   NOT NULL,
    measurement_unit VARCHAR(10)    NOT NULL,
    quantity         INT            NOT NULL,
    unit_price       NUMERIC(12, 2) NOT NULL,
    ----------------------------------------
    CONSTRAINT product_id_pk PRIMARY KEY (product_id),
    CONSTRAINT invoice_id_fk FOREIGN KEY (invoice_id) REFERENCES invoice (invoice_id) ON DELETE CASCADE
);

COMMENT ON TABLE invoice IS 'Таблица товаров из накладных';
COMMENT ON COLUMN product.name IS 'Наименование товара';
COMMENT ON COLUMN product.measurement_unit IS 'Единица измерения товара';
COMMENT ON COLUMN product.quantity IS 'Количество товара';
COMMENT ON COLUMN product.unit_price IS 'Цена за единицу товара';
