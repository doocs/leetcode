# Write your MySQL query statement below
SELECT actor_id, director_id
FROM ActorDirector
GROUP BY 1, 2
HAVING count(1) >= 3;
