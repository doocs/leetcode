# Write your MySQL query statement below
SELECT
    country_name,
    CASE
        WHEN avg(weather_state) <= 15 THEN 'Cold'
        WHEN avg(weather_state) >= 25 THEN 'Hot'
        ELSE 'Warm'
    END AS weather_type
FROM
    Weather AS w
    JOIN Countries AS c ON w.country_id = c.country_id
WHERE date_format(day, '%Y-%m') = '2019-11'
GROUP BY w.country_id;
