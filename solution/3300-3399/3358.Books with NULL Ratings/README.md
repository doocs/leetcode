---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3358.Books%20with%20NULL%20Ratings/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3358. 评分为 NULL 的图书 🔒](https://leetcode.cn/problems/books-with-null-ratings)

[English Version](/solution/3300-3399/3358.Books%20with%20NULL%20Ratings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>books</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| book_id        | int     |
| title          | varchar |
| author         | varchar |
| published_year | int     |
| rating         | decimal |
+----------------+---------+
book_id 是这张表的唯一主键。
这张表的每一行包含关于一本书的唯一 ID，题目，作者，出版年份以及评分的信息。
评分可能为 NULL，表示这本书还没有被评分。
</pre>

<p>编写一个解决方案来找到所有还没有被评分的图书。（即评分为 <strong>NULL</strong>）</p>

<p>返回结果表以&nbsp;<code>book_id</code>&nbsp;<strong>升序&nbsp;</strong>排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>books 表：</p>

<pre class="example-io">
+---------+------------------------+------------------+----------------+--------+
| book_id | title                  | author           | published_year | rating |
+---------+------------------------+------------------+----------------+--------+
| 1       | The Great Gatsby       | F. Scott         | 1925           | 4.5    |
| 2       | To Kill a Mockingbird  | Harper Lee       | 1960           | NULL   |
| 3       | Pride and Prejudice    | Jane Austen      | 1813           | 4.8    |
| 4       | The Catcher in the Rye | J.D. Salinger    | 1951           | NULL   |
| 5       | Animal Farm            | George Orwell    | 1945           | 4.2    |
| 6       | Lord of the Flies      | William Golding  | 1954           | NULL   |
+---------+------------------------+------------------+----------------+--------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+---------+------------------------+------------------+----------------+
| book_id | title                  | author           | published_year |
+---------+------------------------+------------------+----------------+
| 2       | To Kill a Mockingbird  | Harper Lee       | 1960           |
| 4       | The Catcher in the Rye | J.D. Salinger    | 1951           |
| 6       | Lord of the Flies      | William Golding  | 1954           |
+---------+------------------------+------------------+----------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>book_id 为 2，4，6 的书评分为 NULL。</li>
	<li>这些书被包含在结果表中。</li>
	<li>其它书（book_id 为 1，3，5）有评分并且没有被包含。</li>
</ul>
结果以 book_id 升序排序。</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：条件筛选

<!-- thinking:start -->

> **思考**
>
> 只需列出评分缺失的书，并按 $\textit{book\_id}$ 升序输出指定列。
>
> 用 $\textit{isnull}$ 过滤 $\textit{rating}$，再选取四列排序即可，无需连接或聚合。

<!-- thinking:end -->

我们直接筛选出 `rating` 为 `NULL` 的书籍，然后按照 `book_id` 升序排序即可。

注意，结果集中只包含 `book_id`、`title`、`author` 和 `published_year` 四个字段。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT book_id, title, author, published_year
FROM books
WHERE rating IS NULL
ORDER BY 1;
```

#### Pandas

```python
import pandas as pd


def find_unrated_books(books: pd.DataFrame) -> pd.DataFrame:
    unrated_books = books[books["rating"].isnull()]
    return unrated_books[["book_id", "title", "author", "published_year"]].sort_values(
        by="book_id"
    )
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
