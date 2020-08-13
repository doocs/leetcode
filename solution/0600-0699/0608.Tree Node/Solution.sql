SELECT id AS Id,

    CASE
    WHEN p_id is NULL THEN
    'Root'
    WHEN id IN
    (SELECT p_id
    FROM tree
    WHERE p_id is NOT null) THEN
    'Inner'
    ELSE 'Leaf'
    END AS Type
FROM tree
