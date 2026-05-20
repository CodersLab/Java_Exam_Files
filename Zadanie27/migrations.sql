-- W aplikacji istnieje już tabela customers z danymi produkcyjnymi.
-- Chcemy dodać obsługę zamówień oraz status klienta.
--
-- Który zestaw migracji jest poprawny?

------------------------------------------------------------
-- A
------------------------------------------------------------

-- V2__add_status_to_customers.sql
ALTER TABLE customers
    ADD COLUMN status VARCHAR(20);

UPDATE customers
SET status = 'ACTIVE'
WHERE status IS NULL;

ALTER TABLE customers
    ALTER COLUMN status SET NOT NULL;

-- V3__create_orders_table.sql
CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        customer_id BIGINT NOT NULL,
                        total_amount DECIMAL(10, 2) NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        CONSTRAINT fk_orders_customer
                            FOREIGN KEY (customer_id)
                                REFERENCES customers(id)
);

------------------------------------------------------------
-- B
------------------------------------------------------------

-- V2__add_status_to_customers.sql
ALTER TABLE customers
    ADD COLUMN status VARCHAR(20) NOT NULL;

UPDATE customers
SET status = 'ACTIVE';

-- V3__create_orders_table.sql
CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        customer_id BIGINT NOT NULL,
                        total_amount DECIMAL(10, 2) NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        CONSTRAINT fk_orders_customer
                            FOREIGN KEY (customer_id)
                                REFERENCES customers(id)
);

------------------------------------------------------------
-- C
------------------------------------------------------------

-- V2__create_orders_table.sql
CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        customer_id BIGINT NOT NULL,
                        total_amount DECIMAL(10, 2) NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        CONSTRAINT fk_orders_customer
                            FOREIGN KEY (customer_id)
                                REFERENCES clients(id)
);

-- V3__add_status_to_customers.sql
ALTER TABLE customers
    ADD COLUMN status VARCHAR(20) DEFAULT 'ACTIVE' NOT NULL;

------------------------------------------------------------
-- D
------------------------------------------------------------

-- V2__add_status_to_customers.sql
ALTER TABLE customers
    ADD COLUMN status VARCHAR(20);

-- V2__create_orders_table.sql
CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        customer_id BIGINT NOT NULL,
                        total_amount DECIMAL(10, 2) NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        CONSTRAINT fk_orders_customer
                            FOREIGN KEY (customer_id)
                                REFERENCES customers(id)
);