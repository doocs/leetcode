# Write your MySQL query statement below
SELECT
    post_id,
    IFNULL(GROUP_CONCAT(DISTINCT topic_id), 'Ambiguous!') AS topic
FROM
    Posts
    LEFT JOIN Keywords ON INSTR(CONCAT(' ', content, ' '), CONCAT(' ', word, ' ')) > 0
GROUP BY post_id;
