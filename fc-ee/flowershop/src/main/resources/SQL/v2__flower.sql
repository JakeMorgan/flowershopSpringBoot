CREATE TABLE Flower(
Id long not null IDENTITY(0,1) PRIMARY KEY,
name VARCHAR(50),
price DECIMAL,
quantity int
);

INSERT INTO FLOWER values(0, 'Props', 120, 6);
INSERT INTO FLOWER values(1, 'Fox', 160, 8);