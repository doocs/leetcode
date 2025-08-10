---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3642.Find%20Books%20with%20Polarized%20Opinions/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3642. Find Books with Polarized Opinions](https://leetcode.com/problems/find-books-with-polarized-opinions)

[中文文档](/solution/3600-3699/3642.Find%20Books%20with%20Polarized%20Opinions/README.md)

## Description

<!-- description:start -->

<p>Table: <code>books</code></p>

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
book_id is the unique ID for this table.
Each row contains information about a book including its genre and page count.
</pre>

<p>Table: <code>reading_sessions</code></p>

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
session_id is the unique ID for this table.
Each row represents a reading session where someone read a portion of a book. session_rating is on a scale of 1-5.
</pre>

<p>Write a solution to find books that have <strong>polarized opinions</strong> - books that receive both very high ratings and very low ratings from different readers.</p>

<ul>
	<li>A book has polarized opinions if it has <code>at least one rating &ge; 4</code> and <code>at least one rating &le; 2</code></li>
	<li>Only consider books that have <strong>at least </strong><code>5</code><strong> reading sessions</strong></li>
	<li>Calculate the <strong>rating spread</strong> as (<code>highest_rating - lowest_rating</code>)</li>
	<li>Calculate the <strong>polarization score</strong> as the number of extreme ratings (<code>ratings &le; 2 or &ge; 4</code>) divided by total sessions</li>
	<li><strong>Only include</strong> books where <code>polarization score &ge; 0.6</code> (at least <code>60%</code> extreme ratings)</li>
</ul>

<p>Return <em>the result table ordered by polarization score in <strong>descending</strong> order, then by title in <strong>descending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>books table:</p>

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

<p>reading_sessions table:</p>

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

<p><strong>Output:</strong></p>

<pre class="example-io">
+---------+------------------+---------------+-----------+-------+---------------+--------------------+
| book_id | title            | author        | genre     | pages | rating_spread | polarization_score |
+---------+------------------+---------------+-----------+-------+---------------+--------------------+
| 1       | The Great Gatsby | F. Scott      | Fiction   | 180   | 4             | 1.00               |
| 3       | 1984             | George Orwell | Dystopian | 328   | 4             | 1.00               |
+---------+------------------+---------------+-----------+-------+---------------+--------------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>The Great Gatsby (book_id = 1):</strong>

    <ul>
    	<li>Has 5 reading sessions (meets minimum requirement)</li>
    	<li>Ratings: 5, 1, 4, 2, 5</li>
    	<li>Has ratings &ge; 4: 5, 4, 5 (3 sessions)</li>
    	<li>Has ratings &le; 2: 1, 2 (2 sessions)</li>
    	<li>Rating spread: 5 - 1 = 4</li>
    	<li>Extreme ratings (&le;2 or &ge;4): All 5 sessions (5, 1, 4, 2, 5)</li>
    	<li>Polarization score: 5/5 = 1.00 (&ge; 0.6, qualifies)</li>
    </ul>
    </li>
    <li><strong>1984 (book_id = 3):</strong>
    <ul>
    	<li>Has 6 reading sessions (meets minimum requirement)</li>
    	<li>Ratings: 2, 1, 2, 1, 4, 5</li>
    	<li>Has ratings &ge; 4: 4, 5 (2 sessions)</li>
    	<li>Has ratings &le; 2: 2, 1, 2, 1 (4 sessions)</li>
    	<li>Rating spread: 5 - 1 = 4</li>
    	<li>Extreme ratings (&le;2 or &ge;4): All 6 sessions (2, 1, 2, 1, 4, 5)</li>
    	<li>Polarization score: 6/6 = 1.00 (&ge; 0.6, qualifies)</li>
    </ul>
    </li>
    <li><strong>Books not included:</strong>
    <ul>
    	<li>To Kill a Mockingbird (book_id = 2): All ratings are 4-5, no low ratings (&le;2)</li>
    	<li>Pride and Prejudice (book_id = 4): Only 2 sessions (&lt; 5 minimum)</li>
    	<li>The Catcher in the Rye (book_id = 5): Only 2 sessions (&lt; 5 minimum)</li>
    </ul>
    </li>

</ul>

<p>The result table is ordered by polarization score in descending order, then by book title in descending order.</p>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Join + Group Aggregation

We can implement this by joining the `books` table with the `reading_sessions` table, then grouping and aggregating the results.

First, we need to calculate the rating range, the number of extreme ratings, and the proportion of extreme ratings for each book.

Then, we can filter out books that meet the criteria based on these metrics.

Finally, sort the results by extreme rating proportion and book title in descending order.

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
