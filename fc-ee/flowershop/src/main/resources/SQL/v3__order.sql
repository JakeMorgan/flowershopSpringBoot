Create table ORDERS(
Id long not null IDENTITY(0,1) PRIMARY KEY,
orderCreateDate DATE,
orderCompleteDate DATE,
userId long,
total decimal,
status nvarchar(10) check (status in ('CREATED', 'SENT', 'COMPLETED')),
FOREIGN KEY (userId) REFERENCES Users(id));

--INSERT into ORDERS values(0, '2020-08-05 10:00:00', null, 0, '500', 'CREATED');
--INSERT into ORDERS values(1, '2020-08-05 12:00:00', null, 0, '2800', 'CREATED');
