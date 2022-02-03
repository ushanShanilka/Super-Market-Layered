drop database supermarkettest;
create database supermarkettest;

use supermarkettest;

create table User(
	id VARCHAR (50) primary key,
	name varchar (20) not null,
	password varchar (20) not null,
    active_state BOOLEAN,
	userType  varchar (45),
	constraint id unique(id)
);

create table Customer(
	id Varchar (50) primary key,
	customerType VARCHAR (45),
	name varchar (20) not null,
	address VARCHAR (45),
	city VARCHAR (45),
	province VARCHAR (45),
	contact int (11)
);

CREATE TABLE Ref(
    refId VARCHAR (50) primary key,
    name VARCHAR (100) NOT NULL ,
    idNumber VARCHAR (100) NOT NULL ,
    tel int (11) NOT NULL ,
    address VARCHAR (200) NOT NULL
);

create table Product(
	id Varchar (50) primary key,
	name varchar (20) not null,
	description VARCHAR (45),
	specification VARCHAR (45),
	displayName VARCHAR (45),
	available BOOLEAN,
	activeState BOOLEAN,
	availableBrands VARCHAR (45),
    quantity int (100),
    price DECIMAL (10,2)
);

CREATE TABLE Orders(
	id VARCHAR(60) NOT NULL,
	date VARCHAR(200),
	totalCost DECIMAL (10,2),
	customerId VARCHAR(60) ,
	userId VARCHAR (60) NOT NULL,
	CONSTRAINT PRIMARY KEY (id),
	CONSTRAINT FOREIGN KEY(customerId) REFERENCES Customer(id) on Delete Cascade on Update Cascade,
	CONSTRAINT FOREIGN KEY(userId) REFERENCES User(id) on Delete Cascade on Update Cascade
);

Create TABLE orderDetail(
    qty int not null,
    unitPrice DECIMAL (10,2) not null,
    disCount DECIMAL (10,2) not null,
    orderId VARCHAR (50) not null,
    propId VARCHAR (50) not null,
    refId VARCHAR (50) not null,
    CONSTRAINT FOREIGN KEY(orderId) REFERENCES Orders(id) on Delete Cascade on Update Cascade,
    CONSTRAINT FOREIGN KEY(propId) REFERENCES Product(id) on Delete Cascade on Update Cascade,
    CONSTRAINT FOREIGN KEY(refId) REFERENCES Ref(refId) on Delete Cascade on Update Cascade
);
