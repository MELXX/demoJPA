CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE Author (
	Id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
	FirstName VARCHAR ( 50 ) NOT NULL,
	LastName VARCHAR ( 50 ) NOT NULL,
	DateOfBirth TIMESTAMP NOT NULL
);
CREATE TABLE Publisher (
	Id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
	Name VARCHAR ( 50 ) NOT NULL
);
CREATE TABLE Book (
	Id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
	Title VARCHAR ( 50 ) NOT NULL,
	ISBN VARCHAR ( 50 ) NOT NULL,
	Genre VARCHAR ( 15 ) NOT NULL,
	YearPublished TIMESTAMP NOT NULL,
	Author_Id uuid,
	Publisher_Id uuid,
	CONSTRAINT fk_author FOREIGN KEY(Author_Id) REFERENCES Author(Id),
    CONSTRAINT fk_publisher FOREIGN KEY(Publisher_Id) REFERENCES Publisher(Id)
);

