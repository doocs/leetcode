SELECT *
FROM cinema
WHERE description NOT LIKE '%boring%'
        AND mod(id, 2) = 1
ORDER BY rating desc;