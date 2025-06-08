---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3570.Find%20Books%20with%20No%20Available%20Copies/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3570. 查找无可用副本的书籍](https://leetcode.cn/problems/find-books-with-no-available-copies)

[English Version](/solution/3500-3599/3570.Find%20Books%20with%20No%20Available%20Copies/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>library_books</code></p>

<pre>
+------------------+---------+
| Column Name      | Type    |
+------------------+---------+
| book_id          | int     |
| title            | varchar |
| author           | varchar |
| genre            | varchar |
| publication_year | int     |
| total_copies     | int     |
+------------------+---------+
book_id 是这张表的唯一主键。
每一行包含图书馆中一本书的信息，包括图书馆拥有的副本总数。
</pre>

<p>表：<code>borrowing_records</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| record_id     | int     |
| book_id       | int     |
| borrower_name | varchar |
| borrow_date   | date    |
| return_date   | date    |
+---------------+---------+
record_id 是这张表的唯一主键。
每一行代表一笔借阅交易并且如果这本书目前被借出并且还没有被归还，return_date 为 NULL。
</pre>

<p>编写一个解决方案以找到 <strong>所有</strong> <strong>当前被借出（未归还）&nbsp;</strong>且图书馆中 <strong>无可用副本</strong> 的书籍。</p>

<ul>
	<li>如果存在一条借阅记录，其&nbsp;<code>return_date</code>&nbsp;为 <strong>NULL</strong>，那么这本书被认为 <strong>当前是借出的</strong>。</li>
</ul>

<p>返回结果表按当前借阅者数量 <strong>降序</strong> 排列，然后按书名 <strong>升序</strong> 排列。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>library_books 表：</p>

<pre class="example-io">
+---------+------------------------+------------------+----------+------------------+--------------+
| book_id | title                  | author           | genre    | publication_year | total_copies |
+---------+------------------------+------------------+----------+------------------+--------------+
| 1       | The Great Gatsby       | F. Scott         | Fiction  | 1925             | 3            |
| 2       | To Kill a Mockingbird  | Harper Lee       | Fiction  | 1960             | 3            |
| 3       | 1984                   | George Orwell    | Dystopian| 1949             | 1            |
| 4       | Pride and Prejudice    | Jane Austen      | Romance  | 1813             | 2            |
| 5       | The Catcher in the Rye | J.D. Salinger    | Fiction  | 1951             | 1            |
| 6       | Brave New World        | Aldous Huxley    | Dystopian| 1932             | 4            |
+---------+------------------------+------------------+----------+------------------+--------------+
</pre>

<p>borrowing_records 表：</p>

<pre class="example-io">
+-----------+---------+---------------+-------------+-------------+
| record_id | book_id | borrower_name | borrow_date | return_date |
+-----------+---------+---------------+-------------+-------------+
| 1         | 1       | Alice Smith   | 2024-01-15  | NULL        |
| 2         | 1       | Bob Johnson   | 2024-01-20  | NULL        |
| 3         | 2       | Carol White   | 2024-01-10  | 2024-01-25  |
| 4         | 3       | David Brown   | 2024-02-01  | NULL        |
| 5         | 4       | Emma Wilson   | 2024-01-05  | NULL        |
| 6         | 5       | Frank Davis   | 2024-01-18  | 2024-02-10  |
| 7         | 1       | Grace Miller  | 2024-02-05  | NULL        |
| 8         | 6       | Henry Taylor  | 2024-01-12  | NULL        |
| 9         | 2       | Ivan Clark    | 2024-02-12  | NULL        |
| 10        | 2       | Jane Adams    | 2024-02-15  | NULL        |
+-----------+---------+---------------+-------------+-------------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+---------+------------------+---------------+-----------+------------------+-------------------+
| book_id | title            | author        | genre     | publication_year | current_borrowers |
+---------+------------------+---------------+-----------+------------------+-------------------+
| 1       | The Great Gatsby | F. Scott      | Fiction   | 1925             | 3                 | 
| 3       | 1984             | George Orwell | Dystopian | 1949             | 1                 |
+---------+------------------+---------------+-----------+------------------+-------------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>The Great Gatsby (book_id = 1)：</strong>

    <ul>
    	<li>总副本数：3</li>
    	<li>当前被 Alice Smith，Bob Johnson 和 Grace Miller 借阅（3 名借阅者）</li>
    	<li>可用副本数：3 - 3 = 0</li>
    	<li>因为 available_copies = 0，所以被包含</li>
    </ul>
    </li>
    <li><strong>1984 (book_id = 3):</strong>
    <ul>
    	<li>总副本数：1</li>
    	<li>当前被 David Brown 借阅（1 名借阅者）</li>
    	<li>可用副本数：1 - 1 = 0</li>
    	<li>因为 available_copies = 0，所以被包含</li>
    </ul>
    </li>
    <li><strong>未被包含的书：</strong>
    <ul>
    	<li>To Kill a Mockingbird (book_id = 2)：总副本数 = 3，当前借阅者&nbsp;= 2，可用副本 = 1</li>
    	<li>Pride and Prejudice (book_id = 4)：总副本数 = 2，当前借阅者 = 1，可用副本 = 1</li>
    	<li>The Catcher in the Rye (book_id = 5)：总副本数 = 1，当前借阅者 = 0，可用副本 = 1</li>
    	<li>Brave New World (book_id = 6)：总副本数 = 4，当前借阅者 = 1，可用副本 = 3</li>
    </ul>
    </li>
    <li><strong>结果顺序：</strong>
    <ul>
    	<li>The Great Gatsby 有 3 名当前借阅者，排序第一</li>
    	<li>1984 有 1 名当前借阅者，排序第二</li>
    </ul>
    </li>

</ul>

<p>输出表以 current_borrowers 降序排序，然后以 book_title 升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组统计 + 连接查询

我们先统计每本书当前的借阅者数量，然后将其与图书信息表连接，筛选出当前借阅者数量等于总副本数的图书。最后按照当前借阅者数量降序排列，如果相同则按照书名升序排列。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT book_id, COUNT(1) current_borrowers
        FROM borrowing_records
        WHERE return_date IS NULL
        GROUP BY 1
    )
SELECT book_id, title, author, genre, publication_year, current_borrowers
FROM
    library_books
    JOIN T USING (book_id)
WHERE current_borrowers = total_copies
ORDER BY 6 DESC, 2;
```

#### Pandas

```python
import pandas as pd


def find_books_with_no_available_copies(
    library_books: pd.DataFrame, borrowing_records: pd.DataFrame
) -> pd.DataFrame:
    current_borrowers = (
        borrowing_records[borrowing_records["return_date"].isna()]
        .groupby("book_id")
        .size()
        .rename("current_borrowers")
        .reset_index()
    )

    merged = library_books.merge(current_borrowers, on="book_id", how="inner")
    fully_borrowed = merged[merged["current_borrowers"] == merged["total_copies"]]
    fully_borrowed = fully_borrowed.sort_values(
        by=["current_borrowers", "title"], ascending=[False, True]
    )

    cols = [
        "book_id",
        "title",
        "author",
        "genre",
        "publication_year",
        "current_borrowers",
    ]
    return fully_borrowed[cols].reset_index(drop=True)
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
