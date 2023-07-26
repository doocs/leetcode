# Write your MySQL query statement below
(
    SELECT name AS results
    FROM
        Users
        JOIN MovieRating USING (user_id)
    GROUP BY user_id
    ORDER BY count(1) DESC, name
    LIMIT 1
)
UNION ALL
(
    SELECT title
    FROM
        MovieRating
        JOIN Movies USING (movie_id)
    WHERE date_format(created_at, '%Y-%m') = '2020-02'
    GROUP BY movie_id
    ORDER BY avg(rating) DESC, title
    LIMIT 1
);
