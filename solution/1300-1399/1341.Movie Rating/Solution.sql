# Write your MySQL query statement below
(
    SELECT
        name AS results
    FROM
        MovieRating AS r
        LEFT JOIN Users AS u ON u.user_id = r.user_id
    GROUP BY r.user_id
    ORDER BY count(1) DESC, name
    LIMIT 1
)
UNION ALL
(
    SELECT
        title AS results
    FROM
        MovieRating AS r
        LEFT JOIN Movies AS m ON m.movie_id = r.movie_id
    WHERE date_format(created_at, '%Y-%m') = '2020-02'
    GROUP BY m.movie_id
    ORDER BY avg(rating) DESC, title
    LIMIT 1
);
