# Write your MySQL query statement below
SELECT
    SUM(IFNULL(b.apple_count, 0) + IFNULL(c.apple_count, 0)) AS apple_count,
    SUM(IFNULL(b.orange_count, 0) + IFNULL(c.orange_count, 0)) AS orange_count
FROM
    Boxes AS b
    LEFT JOIN Chests AS c USING (chest_id);
