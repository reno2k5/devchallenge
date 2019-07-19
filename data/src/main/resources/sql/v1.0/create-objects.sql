CREATE TABLE product (
    id          integer PRIMARY KEY,
    name        varchar2(40),
    description varchar2(100),
    stock       integer,
    price       numeric(10,2)
);

CREATE SEQUENCE product_seq_pk START WITH 1 INCREMENT BY 1;