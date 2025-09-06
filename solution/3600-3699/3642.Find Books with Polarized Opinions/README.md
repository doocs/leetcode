---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3642.Find%20Books%20with%20Polarized%20Opinions/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3642. 查找有两极分化观点的书籍](https://leetcode.cn/problems/find-books-with-polarized-opinions)

[English Version](/solution/3600-3699/3642.Find%20Books%20with%20Polarized%20Opinions/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>books</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| book_id     | int     |
| title       | varchar |
| author      | varchar |
| genre       | varchar |
| pages       | int     |
+-------------+---------+
book_id 是这张表的唯一主键。
每一行包含关于一本书的信息，包括其类型和页数。
</pre>

<p>表：<code>reading_sessions</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| session_id     | int     |
| book_id        | int     |
| reader_name    | varchar |
| pages_read     | int     |
| session_rating | int     |
+----------------+---------+
session_id 是这张表的唯一主键。
每一行代表一次阅读事件，有人阅读了书籍的一部分。session_rating 在 1-5 的范围内。
</pre>

<p>编写一个解决方案来找到具有 <strong>两极分化观点</strong> 的书 - 同时获得不同读者极高和极低评分的书籍。</p>

<ul>
	<li>如果一本书有至少一个大于等于&nbsp;<code>4</code>&nbsp;的评分和至少一个小于等于&nbsp;<code>2</code>&nbsp;的评分则是有两极分化观点的书</li>
	<li>只考虑有至少 <code>5</code> 次阅读事件的书籍</li>
	<li>按&nbsp;<code>highest_rating - lowest_rating</code>&nbsp;计算评分差幅&nbsp;<strong>rating spread</strong></li>
	<li>按极端评分（评分小于等于 <code>2</code> 或大于等于 <code>4</code>）的数量除以总阅读事件计算 <strong>极化得分&nbsp;polarization score</strong></li>
	<li><strong>只包含</strong>&nbsp;极化得分大于等于&nbsp;<code>0.6</code>&nbsp;的书（至少&nbsp;<code>60%</code>&nbsp;极端评分）</li>
</ul>

<p>返回结果表按极化得分 <strong>降序</strong> 排序，然后按标题 <strong>降序</strong> 排序。</p>

<p>返回格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>books 表：</p>

<pre class="example-io">
+---------+------------------------+---------------+----------+-------+
| book_id | title                  | author        | genre    | pages |
+---------+------------------------+---------------+----------+-------+
| 1       | The Great Gatsby       | F. Scott      | Fiction  | 180   |
| 2       | To Kill a Mockingbird  | Harper Lee    | Fiction  | 281   |
| 3       | 1984                   | George Orwell | Dystopian| 328   |
| 4       | Pride and Prejudice    | Jane Austen   | Romance  | 432   |
| 5       | The Catcher in the Rye | J.D. Salinger | Fiction  | 277   |
+---------+------------------------+---------------+----------+-------+
</pre>

<p>reading_sessions 表：</p>

<pre class="example-io">
+------------+---------+-------------+------------+----------------+
| session_id | book_id | reader_name | pages_read | session_rating |
+------------+---------+-------------+------------+----------------+
| 1          | 1       | Alice       | 50         | 5              |
| 2          | 1       | Bob         | 60         | 1              |
| 3          | 1       | Carol       | 40         | 4              |
| 4          | 1       | David       | 30         | 2              |
| 5          | 1       | Emma        | 45         | 5              |
| 6          | 2       | Frank       | 80         | 4              |
| 7          | 2       | Grace       | 70         | 4              |
| 8          | 2       | Henry       | 90         | 5              |
| 9          | 2       | Ivy         | 60         | 4              |
| 10         | 2       | Jack        | 75         | 4              |
| 11         | 3       | Kate        | 100        | 2              |
| 12         | 3       | Liam        | 120        | 1              |
| 13         | 3       | Mia         | 80         | 2              |
| 14         | 3       | Noah        | 90         | 1              |
| 15         | 3       | Olivia      | 110        | 4              |
| 16         | 3       | Paul        | 95         | 5              |
| 17         | 4       | Quinn       | 150        | 3              |
| 18         | 4       | Ruby        | 140        | 3              |
| 19         | 5       | Sam         | 80         | 1              |
| 20         | 5       | Tara        | 70         | 2              |
+------------+---------+-------------+------------+----------------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+---------+------------------+---------------+-----------+-------+---------------+--------------------+
| book_id | title            | author        | genre     | pages | rating_spread | polarization_score |
+---------+------------------+---------------+-----------+-------+---------------+--------------------+
| 1       | The Great Gatsby | F. Scott      | Fiction   | 180   | 4             | 1.00               |
| 3       | 1984             | George Orwell | Dystopian | 328   | 4             | 1.00               |
+---------+------------------+---------------+-----------+-------+---------------+--------------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>了不起的盖茨比（book_id = 1）：</strong>

    <ul>
    	<li>有 5 次阅读事件（满足最少要求）</li>
    	<li>评分：5, 1, 4, 2, 5</li>
    	<li>大于等于 4 的评分：5，4，5（3 次事件）</li>
    	<li>小于等于 2 的评分：1，2（2 次事件）</li>
    	<li>评分差：5 - 1 = 4</li>
    	<li>极端评分（≤2 或&nbsp;≥4）：所有 5 次事件（5，1，4，2，5）</li>
    	<li>极化得分：5/5 = 1.00（≥&nbsp;0.6，符合）</li>
    </ul>
    </li>
    <li><strong>1984 (book_id = 3):</strong>
    <ul>
    	<li>有 6&nbsp;次阅读事件（满足最少要求）</li>
    	<li>评分：2，1，2，1，4，5</li>
    	<li>大于等于 4 的评分：4，5（2 次事件）</li>
    	<li>小于等于 2 的评分：2，1，2，1（4&nbsp;次事件）</li>
    	<li>评分差：5 - 1 = 4</li>
    	<li>极端评分（≤2 或&nbsp;≥4）：所有 6&nbsp;次事件（2，1，2，1，4，5）</li>
    	<li>极化得分：6/6 = 1.00 (≥ 0.6，符合）</li>
    </ul>
    </li>
    <li><strong>未包含的书：</strong>
    <ul>
    	<li>杀死一只知更鸟（book_id = 2）：所有评分为 4-5，没有低分（≤2）</li>
    	<li>傲慢与偏见（book_id = 4）：只有&nbsp;2 次事件（&lt; 最少 5 次）</li>
    	<li>麦田里的守望者（book_id = 5）：只有&nbsp;2 次事件（&lt; 最少 5 次）</li>
    </ul>
    </li>

</ul>

<p>结果表按极化得分降序排序，然后按标题降序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：连接 + 分组聚合

我们可以通过连接 `books` 表和 `reading_sessions` 表，然后对结果进行分组和聚合来实现。

首先，我们需要计算每本书的评分范围、极端评分的数量和极端评分的比例。

然后，我们可以根据这些指标筛选出符合条件的书籍。

最后，按照极端评分比例和书名的降序排列结果。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    book_id,
    title,
    author,
    genre,
    pages,
    (MAX(session_rating) - MIN(session_rating)) AS rating_spread,
    ROUND((SUM(session_rating <= 2) + SUM(session_rating >= 4)) / COUNT(1), 2) polarization_score
FROM
    books
    JOIN reading_sessions USING (book_id)
GROUP BY book_id
HAVING
    COUNT(1) >= 5
    AND MAX(session_rating) >= 4
    AND MIN(session_rating) <= 2
    AND polarization_score >= 0.6
ORDER BY polarization_score DESC, title DESC;
```

#### Pandas

```python
import pandas as pd
from decimal import Decimal, ROUND_HALF_UP


def find_polarized_books(
    books: pd.DataFrame, reading_sessions: pd.DataFrame
) -> pd.DataFrame:
    df = books.merge(reading_sessions, on="book_id")
    agg_df = (
        df.groupby(["book_id", "title", "author", "genre", "pages"])
        .agg(
            max_rating=("session_rating", "max"),
            min_rating=("session_rating", "min"),
            rating_spread=("session_rating", lambda x: x.max() - x.min()),
            count_sessions=("session_rating", "count"),
            low_or_high_count=("session_rating", lambda x: ((x <= 2) | (x >= 4)).sum()),
        )
        .reset_index()
    )

    agg_df["polarization_score"] = agg_df.apply(
        lambda r: float(
            Decimal(r["low_or_high_count"] / r["count_sessions"]).quantize(
                Decimal("0.01"), rounding=ROUND_HALF_UP
            )
        ),
        axis=1,
    )

    result = agg_df[
        (agg_df["count_sessions"] >= 5)
        & (agg_df["max_rating"] >= 4)
        & (agg_df["min_rating"] <= 2)
        & (agg_df["polarization_score"] >= 0.6)
    ]

    return result.sort_values(
        by=["polarization_score", "title"], ascending=[False, False]
    )[
        [
            "book_id",
            "title",
            "author",
            "genre",
            "pages",
            "rating_spread",
            "polarization_score",
        ]
    ]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
