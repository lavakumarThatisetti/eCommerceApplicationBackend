CREATE TABLE IF NOT EXISTS persons
(
   person_id bigserial NOT NULL ,
   user_name character varying(40) NOT NULL,
   email character varying(40) NOT NULL UNIQUE,
   phone_number character varying(20) NOT NULL UNIQUE,
   PRIMARY KEY (person_id)
);

CREATE TABLE IF NOT EXISTS orders
(
   order_id bigserial NOT NULL ,
   person_id bigserial NOT NULL,
   book_ids TEXT NOT NULL,
   total_items INT NOT NULL,
   total_money double precision,
   status character varying(50),
   PRIMARY KEY (order_id),
   CONSTRAINT fk_customer
   FOREIGN KEY(person_id)
	 REFERENCES persons(person_id)
);

CREATE TABLE IF NOT EXISTS payment_info
(
   id bigserial NOT NULL ,
   order_id bigserial NOT NULL ,
   name character varying(40) NOT NULL,
   email character varying(40) NOT NULL,
   phone_number character varying(20) NOT NULL,
   currency character varying(20) NULL,
   amount double precision,
   description TEXT NULL,
   transaction_id character varying(40) NOT NULL,
   created_at character varying(40) Null,
   resource_uri TEXT NULL,
   status character varying(40) NULL,
   PRIMARY KEY (id),
   CONSTRAINT fk_order
   FOREIGN KEY(order_id)
   REFERENCES orders(order_id)
);