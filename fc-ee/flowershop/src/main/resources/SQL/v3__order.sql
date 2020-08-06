Create table ORDERS(
Id long IDENTITY(1,1),
orderCreateDate TIMESTAMP,
orderCompleteDate TIMESTAMP,
total decimal,
status varchar(10));

INSERT into ORDERS values(0, '2020-08-05 10:00:00', null, '500', 'CREATED');
INSERT into ORDERS values(1, '2020-08-05 12:00:00', null, '2800', 'CREATED');
