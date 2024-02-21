# [1149. 文章浏览 II](https://leetcode.cn/problems/article-views-ii)

[English Version](/solution/1100-1199/1149.Article%20Views%20II/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Views</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| article_id    | int     |
| author_id     | int     |
| viewer_id     | int     |
| view_date     | date    |
+---------------+---------+
此表可能会存在重复行。
此表的每一行都表示某人在某天浏览了某位作者的某篇文章。 
请注意，同一人的 author_id 和 viewer_id 是相同的。
</pre>

<p>&nbsp;</p>

<p>编写解决方案来找出在同一天阅读至少两篇文章的人。</p>

<p>结果按照 <code>id</code> 升序排序。</p>

<p>结果的格式如下。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Views 表:
+------------+-----------+-----------+------------+
| article_id | author_id | viewer_id | view_date  |
+------------+-----------+-----------+------------+
| 1          | 3         | 5         | 2019-08-01 |
| 3          | 4         | 5         | 2019-08-01 |
| 1          | 3         | 6         | 2019-08-02 |
| 2          | 7         | 7         | 2019-08-01 |
| 2          | 7         | 6         | 2019-08-02 |
| 4          | 7         | 1         | 2019-07-22 |
| 3          | 4         | 4         | 2019-07-21 |
| 3          | 4         | 4         | 2019-07-21 |
+------------+-----------+-----------+------------+
<strong>输出：</strong>
+------+
| id   |
+------+
| 5    |
| 6    |
+------+</pre>

## 解法

### 方法一：DISTINCT + GROUP BY + HAVING

我们将数据按照 `viewer_id` 和 `view_date` 分组，然后利用 `HAVING` 子句来筛选出浏览文章数大于 $1$ 的记录，最后按照 `id` 去重排序即可。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT DISTINCT viewer_id AS id
FROM Views
GROUP BY viewer_id, view_date
HAVING COUNT(DISTINCT article_id) > 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- end -->
