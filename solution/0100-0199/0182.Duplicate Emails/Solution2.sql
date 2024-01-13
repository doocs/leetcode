SELECT DISTINCT p1.email
FROM
    person AS p1,
    person AS p2
WHERE p1.id != p2.id AND p1.email = p2.email;
