# Write your MySQL query statement below
WITH
    first_positive AS (
        SELECT
            patient_id,
            MIN(test_date) AS first_positive_date
        FROM covid_tests
        WHERE result = 'Positive'
        GROUP BY patient_id
    ),
    first_negative_after_positive AS (
        SELECT
            t.patient_id,
            MIN(t.test_date) AS first_negative_date
        FROM
            covid_tests t
            JOIN first_positive p
                ON t.patient_id = p.patient_id AND t.test_date > p.first_positive_date
        WHERE t.result = 'Negative'
        GROUP BY t.patient_id
    )
SELECT
    p.patient_id,
    p.patient_name,
    p.age,
    DATEDIFF(n.first_negative_date, f.first_positive_date) AS recovery_time
FROM
    first_positive f
    JOIN first_negative_after_positive n ON f.patient_id = n.patient_id
    JOIN patients p ON p.patient_id = f.patient_id
ORDER BY recovery_time ASC, patient_name ASC;
