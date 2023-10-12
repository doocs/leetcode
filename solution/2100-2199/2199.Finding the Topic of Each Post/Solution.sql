# Write your MySQL query statement below
SELECT
    post_id,
    ifnull(group_concat(DISTINCT topic_id), 'Ambiguous!') AS topic
FROM
    Posts
    LEFT JOIN Keywords ON instr(concat(' ', content, ' '), concat(' ', word, ' ')) > 0
GROUP BY post_id;
