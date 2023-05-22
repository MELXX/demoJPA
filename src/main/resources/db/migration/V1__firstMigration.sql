CREATE TABLE Author (
	Id uuid PRIMARY KEY,
	FirstName VARCHAR ( 50 ) NOT NULL,
	LastName VARCHAR ( 50 ) NOT NULL,
	DateOfBirth TIMESTAMP NOT NULL
);

CREATE TABLE Book (
	Id uuid PRIMARY KEY,
	Title VARCHAR ( 50 ) NOT NULL,
	ISBN VARCHAR ( 50 ) NOT NULL,
	Genre VARCHAR ( 15 ) NOT NULL,
	YearPublished TIMESTAMP NOT NULL,
	CONSTRAINT fk_author FOREIGN KEY(author_id) REFERENCES Author(Id),
    CONSTRAINT fk_publisher FOREIGN KEY(publisher_id) REFERENCES Publisher(Id),
);

CREATE TABLE Publisher (
	Id uuid PRIMARY KEY,
	Name VARCHAR ( 50 ) NOT NULL
);