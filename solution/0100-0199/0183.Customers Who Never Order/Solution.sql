SELECT Name AS Customers
FROM Customers
WHERE
    id NOT IN (
        SELECT CustomerId
        FROM Orders
    );
