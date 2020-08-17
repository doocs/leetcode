# [1050. Actors and Directors Who Cooperated At Least Three Times](https://leetcode.com/problems/actors-and-directors-who-cooperated-at-least-three-times)

[中文文档](/solution/1000-1099/1050.Actors%20and%20Directors%20Who%20Cooperated%20At%20Least%20Three%20Times/README.md)

## Description
None


## Solutions


<!-- tabs:start -->

### **SQL**

```
SELECT actor_id, director_id
FROM ActorDirector
GROUP BY actor_id, director_id
HAVING COUNT(*) > 2
```

<!-- tabs:end -->
