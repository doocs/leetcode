# Write your MySQL query statement below
SELECT school_id, MIN(IFNULL(score, -1)) AS score
FROM
    Schools AS s
    LEFT JOIN Exam AS e ON s.capacity >= e.student_count
GROUP BY school_id;
