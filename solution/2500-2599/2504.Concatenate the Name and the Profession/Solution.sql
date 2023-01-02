# Write your MySQL query statement below
SELECT person_id, CONCAT(name, "(", substring(profession, 1, 1), ")") AS name
FROM Person
ORDER BY person_id DESC;