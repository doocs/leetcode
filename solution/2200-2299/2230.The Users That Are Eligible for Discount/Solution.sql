CREATE PROCEDURE getUserIDs(startDate DATE, endDate DATE, minAmount INT)
BEGIN
    # Write your MySQL query statement below.
    SELECT DISTINCT user_id
    FROM Purchases
    WHERE amount >= minAmount AND time_stamp BETWEEN startDate AND endDate
    ORDER BY user_id;
END;
