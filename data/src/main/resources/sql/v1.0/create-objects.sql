CREATE TABLE product (
    id          number(38,0) PRIMARY KEY,
    name        varchar2(40),
    description varchar2(100),
    stock       integer,
    price       number(10,2)
);
CREATE SEQUENCE product_seq_pk START WITH 1 INCREMENT BY 1;

CREATE TABLE app_user(
    id integer PRIMARY KEY,
    username varchar2(100),
    password varchar2(100),
    user_role varchar2(40)
);
CREATE SEQUENCE user_seq_pk START WITH 1 INCREMENT BY 1;

CREATE TABLE APP_ROLE (	
    ID integer primary key, 
    APP_ROLE VARCHAR2(40)
;
CREATE SEQUENCE  ROLE_SEQ_PK START WITH 1 INCREMENT BY 1;
 
CREATE TABLE user_roles(
    user_id integer,
    user_role varchar2(40),
    constraint user_roles_pk primary key(user_id, user_role)
);

CREATE TABLE product_liked(
    user_id integer,
    prod_id integer,
    constraint liked_pk primary key(user_id,prod_id),
    constraint liked_on_user_fk foreign key (user_id) references app_user (id),
    constraint liked_on_product_fk foreign key (prod_id) references product (id)
);

CREATE TABLE purchase_history(
    id integer PRIMARY KEY,
    product_id number(38,0),
    purchase_time TIMESTAMP,
    price number(10,2),
    amount integer,
    user_id integer,
    constraint purchase_user_fk foreign key (user_id) references app_user(id)
);
CREATE SEQUENCE purchase_history_seq_pk START WITH 1 INCREMENT BY 1;


