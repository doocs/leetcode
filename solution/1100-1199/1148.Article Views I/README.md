# [1148. 文章浏览 I](https://leetcode.cn/problems/article-views-i)

[English Version](/solution/1100-1199/1148.Article%20Views%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>Views</code>&nbsp;表：</p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| article_id    | int     |
| author_id     | int     |
| viewer_id     | int     |
| view_date     | date    |
+---------------+---------+
此表无主键，因此可能会存在重复行。
此表的每一行都表示某人在某天浏览了某位作者的某篇文章。
请注意，同一人的 author_id 和 viewer_id 是相同的。
</pre>

<p>&nbsp;</p>

<p>请编写一条 SQL 查询以找出所有浏览过自己文章的作者，结果按照 id 升序排列。</p>

<p>查询结果的格式如下所示：</p>

<pre>
Views 表：
+------------+-----------+-----------+------------+
| article_id | author_id | viewer_id | view_date  |
+------------+-----------+-----------+------------+
| 1          | 3         | 5         | 2019-08-01 |
| 1          | 3         | 6         | 2019-08-02 |
| 2          | 7         | 7         | 2019-08-01 |
| 2          | 7         | 6         | 2019-08-02 |
| 4          | 7         | 1         | 2019-07-22 |
| 3          | 4         | 4         | 2019-07-21 |
| 3          | 4         | 4         | 2019-07-21 |
+------------+-----------+-----------+------------+

结果表：
+------+
| id   |
+------+
| 4    |
| 7    |
+------+
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“`DISTINCT` + `ORDER BY`” 实现。

<!-- tabs:start -->

### **SQL**

```sql
SELECT DISTINCT(author_id) as id
FROM Views
WHERE author_id = viewer_id
ORDER BY id;
```

<!-- tabs:end -->
