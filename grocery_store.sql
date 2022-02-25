CREATE USER store_manager identified by store_manager;

CREATE TABLE Employees(
	employee_id NUMBER(3), name VARCHAR2(20), Contact_No NUMBER(14), date_of_birth DATE, Gender VARCHAR2(7), 
	Blood_group VARCHAR2(5), address VARCHAR2(50), salary NUMBER(6), hire_date DATE,
	Constraint employees_pk Primary key (employee_id) 
);

CREATE TABLE PRODUCTS (
	product_id NUMBER(5), product_type VARCHAR2(20), product_name VARCHAR2(20), 
	cost_price NUMBER(7), selling_price NUMBER(7), amount NUMBER(4), 
	CONSTRAINT products_pk PRIMARY KEY (product_id)
);
create table wholesale 
(
	product_type VARCHAR2(20), product_name VARCHAR2(20), cost_price NUMBER(7), 
	constraint wholesale_pk PRIMARY KEY (product_type, product_name)
);
alter table products add foreign key(product_type, product_name) references WHOLESALE(product_type, product_name);

INSERT INTO Wholesale values('chips','Sweet chips',13);
INSERT INTO Wholesale values('chips','Pran chips',7);
INSERT INTO Wholesale values('chips','spicy',7);
INSERT INTO Wholesale values('chips','potato chips',13);
INSERT INTO Wholesale values('Chocolate','kitkat',35);
INSERT INTO Wholesale values('Chocolate','kinderjoy',75);
INSERT INTO Wholesale values('Chocolate','dairy milk',40);
INSERT INTO Wholesale values('Dairy','tasty Yoghurt',20);
INSERT INTO Wholesale values('Dairy','tasty Cheese',27);
INSERT INTO Wholesale values('Bag','sports bag',200);
INSERT INTO Wholesale values('Bag','hiking bag',170);
INSERT INTO Wholesale values('Juice','Pran juice',10);
INSERT INTO Wholesale values('Juice','PQR juice',10);
INSERT INTO Wholesale values('Juice','Abc juice',12);
INSERT INTO Wholesale values('Toffee','ABC co.',14);
INSERT INTO Wholesale values('Toffee','XYZ',15);

INSERT INTO PRODUCTS VALUES (1, 'Juice', 'Pran juice', 10, 15, 20);
INSERT INTO PRODUCTS VALUES (2, 'Juice', 'Abc juice', 12, 17, 30);
INSERT INTO PRODUCTS VALUES (3, 'chips', 'Pran chips', 7, 14, 75);
INSERT INTO PRODUCTS VALUES (4, 'chips', 'potato chips', 13, 15, 35);
INSERT INTO PRODUCTS VALUES (5, 'Chocolate', 'kitkat', 35, 55, 25);
INSERT INTO PRODUCTS VALUES (6, 'Chocolate', 'dairy milk', 40, 60, 30);
INSERT INTO PRODUCTS VALUES (7, 'Toffee', 'ABC co.', 14, 20, 25);
INSERT INTO PRODUCTS VALUES (8, 'Toffee', 'XYZ', 15, 18, 30);
INSERT INTO PRODUCTS VALUES (9, 'Bag', 'sports bag', 200, 400, 15);
INSERT INTO PRODUCTS VALUES (10, 'Bag', 'hiking bag', 170, 350, 15);
INSERT INTO PRODUCTS VALUES (11, 'Dairy', 'tasty Yoghurt', 20, 35, 70);
INSERT INTO PRODUCTS VALUES (12, 'Dairy', 'tasty Cheese', 27, 40, 70);
INSERT INTO PRODUCTS VALUES (13, 'Juice', 'PQR juice', 10, 13, 34);
INSERT INTO PRODUCTS VALUES (14, 'chips', 'spicy', 7, 14, 75);
INSERT INTO PRODUCTS VALUES (15, 'chips', 'Sweet chips', 13, 15, 35);
INSERT INTO PRODUCTS VALUES (16, 'Chocolate', 'kinderjoy', 75, 88, 23);

create table ACCOUNT (
	account_id NUMBER(3), current_balance NUMBER(25), net_expenditure NUMBER(25), net_profit NUMBER(25), managerID NUMBER(3),
	constraint account_pk PRIMARY KEY (account_id)
);

create table MANAGER (
	managerID NUMBER(3), name VARCHAR2(20), contact_no NUMBER(15), address VARCHAR2(40), date_of_birth DATE,
	constraint  manager_pk PRIMARY KEY (managerID)
);
insert into manager VALUES(100, 'ARIF FAISAL', 01712345678, 'Dhaka, Bangladesh', '06-AUG-1999');
alter TABLE account add foreign key(managerID) references MANAGER (managerID);
insert into account VALUES(1, 100000, 0, 0, 100);

create table STALL (
	stall_no NUMBER(3), product_category VARCHAR2(20),  category_id NUMBER(3),
	constraint stall_pk primary key (stall_no),
	foreign key(category_id) references products (product_id)
);

create table CUSTOMERS (
	phone_no NUMBER(15), name VARCHAR2(20), address VARCHAR2(20), selling_price NUMBER(7), bought_product_id NUMBER(3),
	constraint customers_pk primary key (phone_no, bought_product_id),
	foreign key (bought_product_id) references products(product_id)
);

INSERT INTO STALL VALUES (1, 'chips', 3);
INSERT INTO STALL VALUES (2, 'chips', 4);
INSERT INTO STALL VALUES (3, 'chips', 14);
INSERT INTO STALL VALUES (4, 'chips', 15);
INSERT INTO STALL VALUES (5, 'Juice', 13);
INSERT INTO STALL VALUES (6, 'Juice', 2);
INSERT INTO STALL VALUES (7, 'Juice', 1);
INSERT INTO STALL VALUES (8, 'Chocolate', 5);
INSERT INTO STALL VALUES (9, 'Chocolate', 6);
INSERT INTO STALL VALUES (10, 'Chocolate', 16);
INSERT INTO STALL VALUES (11, 'Toffee', 7);
INSERT INTO STALL VALUES (12, 'Toffee', 8);
INSERT INTO STALL VALUES (13, 'Bag', 10);
INSERT INTO STALL VALUES (14, 'Bag', 9);
INSERT INTO STALL VALUES (15, 'Dairy', 11);
INSERT INTO STALL VALUES (16, 'Dairy', 12);

