SELECT project_id
FROM Project
GROUP BY  project_id
HAVING count(*) >= all
    (SELECT count(*)
    FROM Project
    GROUP BY  project_id )
