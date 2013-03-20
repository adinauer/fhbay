DROP TABLE PUBLIC.SIMPLECUSTOMER;

CREATE TABLE PUBLIC.SIMPLECUSTOMER (
		ID BIGINT IDENTITY PRIMARY KEY,
		FIRSTNAME VARCHAR(255) NOT NULL,
		LASTNAME VARCHAR(255) NOT NULL,
		USERNAME VARCHAR(255),
		PASSWORD VARCHAR(255),
		EMAIL VARCHAR(255));
	
INSERT INTO PUBLIC.SIMPLECUSTOMER (FIRSTNAME, LASTNAME, USERNAME, PASSWORD, EMAIL) VALUES ('Jaquira', 'himmelpfortsleitner', 'jaqui', 'jahi', 'jaqui@gmx.at');
delete from PUBLIC.SIMPLECUSTOMER;