# [196. 删除重复的电子邮箱](https://leetcode-cn.com/problems/delete-duplicate-emails)

[English Version](/solution/0100-0199/0196.Delete%20Duplicate%20Emails/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>编写一个 SQL 查询，来删除&nbsp;<code>Person</code>&nbsp;表中所有重复的电子邮箱，重复的邮箱里只保留&nbsp;<strong>Id&nbsp;</strong><em>最小&nbsp;</em>的那个。</p>

<pre>+----+------------------+
| Id | Email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
| 3  | john@example.com |
+----+------------------+
Id 是这个表的主键。
</pre>

<p>例如，在运行你的查询语句之后，上面的 <code>Person</code> 表应返回以下几行:</p>

<pre>+----+------------------+
| Id | Email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
+----+------------------+
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>执行 SQL 之后，输出是整个 <code>Person</code>&nbsp;表。</li>
	<li>使用 <code>delete</code> 语句。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```
delete from Person where Id not in (select min(Id) from (select * from Person) as p group by p.Email)
```

<!-- tabs:end -->
