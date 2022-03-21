SELECT DISTINCT
    title
FROM
    Content
        INNER JOIN
    TVProgram ON Content.content_id = TVProgram.content_id
WHERE
    content_type = 'Movies'
        AND kids_content = 'Y'
        AND program_date BETWEEN '2020-06-01' AND '2020-06-30';



SELECT DISTINCT
    title
FROM
    Content
        INNER JOIN
    TVProgram ON Content.content_id = TVProgram.content_id
WHERE
    kids_content = 'Y'
        AND (MONTH(program_date) , YEAR(program_date)) = (6 , 2020);
 
