CREATE TABLE address
(
    id           BINARY(16)   NOT NULL,
    created_at   datetime     NULL,
    updated_at   datetime     NULL,
    house_number VARCHAR(255) NULL,
    street       VARCHAR(255) NULL,
    city         VARCHAR(255) NULL,
    pin_code     VARCHAR(255) NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id                  BINARY(16) NOT NULL,
    created_at          datetime   NULL,
    updated_at          datetime   NULL,
    user_id             BINARY(16) NOT NULL,
    delivery_address_id BINARY(16) NULL,
    order_price         DOUBLE     NOT NULL,
    mode_of_payment     TINYINT   NULL,
    payment_status      TINYINT   NULL,
    order_status        TINYINT   NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE orders_products
(
    order_id    BINARY(16) NOT NULL,
    products_id BINARY(16) NOT NULL
);

CREATE TABLE product
(
    id                BINARY(16) NOT NULL,
    created_at        datetime   NULL,
    updated_at        datetime   NULL,
    product_id        BINARY(16) NOT NULL,
    quantity          INT        NOT NULL,
    price_per_product DOUBLE     NOT NULL,
    total_price       DOUBLE     NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE orders_products
    ADD CONSTRAINT uc_orders_products_products UNIQUE (products_id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_DELIVERY_ADDRESS FOREIGN KEY (delivery_address_id) REFERENCES address (id);

ALTER TABLE orders_products
    ADD CONSTRAINT fk_ordpro_on_order FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE orders_products
    ADD CONSTRAINT fk_ordpro_on_product FOREIGN KEY (products_id) REFERENCES product (id);