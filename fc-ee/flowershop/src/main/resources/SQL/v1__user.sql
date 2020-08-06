Create Table Users(
Id long IDENTITY(1,1),
UserName nvarchar(30),
Password nvarchar(30),
Address nvarchar(30),
Phone nvarchar(20),
Balance Decimal,
Discount int);

INSERT into Users values(0, 'Admin', 'Admin', 'Plot', '89205152432', 5000, 0);