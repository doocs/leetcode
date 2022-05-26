# [608. Tree Node](https://leetcode.com/problems/tree-node)

[中文文档](/solution/0600-0699/0608.Tree%20Node/README.md)

## Description

None

## Solutions

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
