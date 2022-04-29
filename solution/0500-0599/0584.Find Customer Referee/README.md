# [584. 寻找用户推荐人](https://leetcode.cn/problems/find-customer-referee)

[English Version](/solution/0500-0599/0584.Find%20Customer%20Referee/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定表 <code>customer</code> ，里面保存了所有客户信息和他们的推荐人。</p>

<pre>
+------+------+-----------+
| id   | name | referee_id|
+------+------+-----------+
|    1 | Will |      NULL |
|    2 | Jane |      NULL |
|    3 | Alex |         2 |
|    4 | Bill |      NULL |
|    5 | Zack |         1 |
|    6 | Mark |         2 |
+------+------+-----------+
</pre>

<p>写一个查询语句，返回一个客户列表，列表中客户的推荐人的编号都 <strong>不是 </strong>2。</p>

<p>对于上面的示例数据，结果为：</p>

<pre>
+------+
| name |
+------+
| Will |
| Jane |
| Bill |
| Zack |
+------+
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT
    name
FROM
    Customer
WHERE
    referee_id != 2 OR referee_id IS NULL;
```

MySQL 可使用 `IFNULL()`：

```sql
SELECT
    name
FROM
    customer
WHERE
    IFNULL(referee_id, 0) != 2;
```

<!-- tabs:end -->
