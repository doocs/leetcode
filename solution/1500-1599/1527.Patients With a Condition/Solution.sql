SELECT
    patient_id,
    patient_name,
    conditions
FROM
    patients
WHERE
    conditions LIKE 'DIAB1%'
    OR conditions LIKE '% DIAB1%';