# Write your MySQL query statement below
SELECT user_id, email
FROM Users
WHERE email REGEXP '^[A-Za-z0-9_]+@[A-Za-z][A-Za-z0-9]*\\.com$'
ORDER BY 1;
