# [614. Second Degree Follower](https://leetcode.com/problems/second-degree-follower)

[中文文档](/solution/0600-0699/0614.Second%20Degree%20Follower/README.md)

## Description

None

## Solutions

<!-- tabs:start -->

### **SQL**

```
SELECT followee AS follower,
        count(distinct(follower)) AS num
FROM follow
WHERE followee IN
    (SELECT follower
    FROM follow)
GROUP BY  followee
ORDER BY  followee
```

<!-- tabs:end -->
