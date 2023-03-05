# [182. 查找重复的电子邮箱](https://leetcode.cn/problems/duplicate-emails)

[English Version](/solution/0100-0199/0182.Duplicate%20Emails/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><meta charset="UTF-8" /></p>

<p>表:&nbsp;<code>Person</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| email       | varchar |
+-------------+---------+
id 是该表的主键列。
此表的每一行都包含一封电子邮件。电子邮件不包含大写字母。
</pre>

<p>&nbsp;</p>

<p>编写一个 SQL 查询来报告所有重复的电子邮件。 请注意，可以保证电子邮件字段不为 NULL。</p>

<p>以&nbsp;<strong>任意顺序&nbsp;</strong>返回结果表。</p>

<p>查询结果格式如下例。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> 
Person 表:
+----+---------+
| id | email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+
<strong>输出:</strong> 
+---------+
| Email   |
+---------+
| a@b.com |
+---------+
<strong>解释:</strong> a@b.com 出现了两次。</pre>

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
