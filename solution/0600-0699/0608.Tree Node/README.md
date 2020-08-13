# [608. 树节点](https://leetcode-cn.com/problems/tree-node)

[English Version](/solution/0600-0699/0608.Tree%20Node/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
None


## 解法
<!-- 这里可写通用的实现逻辑 -->


<!-- tabs:start -->

### **SQL**

```
SELECT id AS Id,
        
    CASE
    WHEN p_id is NULL THEN
    'Root'
    WHEN id IN 
    (SELECT p_id
    FROM tree
    WHERE p_id is NOT null) THEN
    'Inner'
    ELSE 'Leaf'
    END AS Type
FROM tree
```

<!-- tabs:end -->
