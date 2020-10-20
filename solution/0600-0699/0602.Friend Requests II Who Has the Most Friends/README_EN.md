# [602. Friend Requests II: Who Has the Most Friends](https://leetcode.com/problems/friend-requests-ii-who-has-the-most-friends)

[中文文档](/solution/0600-0699/0602.Friend%20Requests%20II%20Who%20Has%20the%20Most%20Friends/README.md)

## Description

None

## Solutions

<!-- tabs:start -->

### **SQL**

```
SELECT id,
         count(*) num
FROM (
    (SELECT requester_id AS id
    FROM request_accepted )
    UNION
    all
        (SELECT accepter_id AS id
        FROM request_accepted) ) t
    GROUP BY  id
ORDER BY  num DESC limit 1
```

<!-- tabs:end -->
