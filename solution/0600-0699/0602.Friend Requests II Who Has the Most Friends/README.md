# [602. 好友申请 II ：谁有最多的好友](https://leetcode-cn.com/problems/friend-requests-ii-who-has-the-most-friends)

[English Version](/solution/0600-0699/0602.Friend%20Requests%20II%20Who%20Has%20the%20Most%20Friends/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
None


## 解法
<!-- 这里可写通用的实现逻辑 -->


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
