SELECT
    country_name,
    CASE
        WHEN AVG(weather_state) <= 15 THEN 'Cold'
        WHEN AVG(weather_state) >= 25 THEN 'Hot'
        ELSE 'Warm'
    END AS weather_type
FROM
    Weather AS w
    JOIN Countries USING (country_id)
WHERE DATE_FORMAT(day, '%Y-%m') = '2019-11'
GROUP BY 1;
