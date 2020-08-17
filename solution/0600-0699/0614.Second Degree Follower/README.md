# [614. 二级关注者](https://leetcode-cn.com/problems/second-degree-follower)

[English Version](/solution/0600-0699/0614.Second%20Degree%20Follower/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
None


## 解法
<!-- 这里可写通用的实现逻辑 -->


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
