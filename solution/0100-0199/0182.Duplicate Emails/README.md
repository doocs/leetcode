# [182. 查找重复的电子邮箱](https://leetcode.cn/problems/duplicate-emails)

[English Version](/solution/0100-0199/0182.Duplicate%20Emails/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>编写一个 SQL 查询，查找&nbsp;<code>Person</code> 表中所有重复的电子邮箱。</p>

<p><strong>示例：</strong></p>

<pre>+----+---------+
| Id | Email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+
</pre>

<p>根据以上输入，你的查询应返回以下结果：</p>

<pre>+---------+
| Email   |
+---------+
| a@b.com |
+---------+
</pre>

<p><strong>说明：</strong>所有电子邮箱都是小写字母。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT Email
FROM Person
GROUP BY Email
HAVING count(Email) > 1;
```

```sql
SELECT DISTINCT p1.email
FROM person AS p1,
    person AS p2
WHERE p1.id != p2.id
    AND p1.email = p2.email;
```

<!-- tabs:end -->
