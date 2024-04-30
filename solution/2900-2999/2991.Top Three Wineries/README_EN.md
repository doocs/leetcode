# [2991. Top Three Wineries 🔒](https://leetcode.com/problems/top-three-wineries)

[中文文档](/solution/2900-2999/2991.Top%20Three%20Wineries/README.md)

<!-- tags:Database -->

## Description

<p>Table: <code>Wineries</code></p>

<pre>
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| id          | int      |
| country     | varchar  |
| points      | int      |
| winery      | varchar  |
+-------------+----------+
id is column of unique values for this table.
This table contains id, country, points, and winery.
</pre>

<p>Write a solution to find the <strong>top three wineries</strong> in <strong>each</strong> <strong>country</strong> based on their <strong>total points</strong>. If <strong>multiple wineries</strong> have the <strong>same</strong> total points, order them by <code>winery</code> name in <strong>ascending</strong> order. If there&#39;s <strong>no second winery</strong>, output &#39;No Second Winery,&#39; and if there&#39;s <strong>no third winery</strong>, output &#39;No Third Winery.&#39;</p>

<p>Return <em>the result table ordered by </em><code>country</code><em> in <strong>ascending</strong> order</em><em>.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Wineries table:
+-----+-----------+--------+-----------------+
| id  | country   | points | winery          | 
+-----+-----------+--------+-----------------+
| 103 | Australia | 84     | WhisperingPines | 
| 737 | Australia | 85     | GrapesGalore    |    
| 848 | Australia | 100    | HarmonyHill     | 
| 222 | Hungary   | 60     | MoonlitCellars  | 
| 116 | USA       | 47     | RoyalVines      | 
| 124 | USA       | 45     | Eagle&#39;sNest     | 
| 648 | India     | 69     | SunsetVines     | 
| 894 | USA       | 39     | RoyalVines      |  
| 677 | USA       | 9      | PacificCrest    |  
+-----+-----------+--------+-----------------+
<strong>Output:</strong> 
+-----------+---------------------+-------------------+----------------------+
| country   | top_winery          | second_winery     | third_winery         |
+-----------+---------------------+-------------------+----------------------+
| Australia | HarmonyHill (100)   | GrapesGalore (85) | WhisperingPines (84) |
| Hungary   | MoonlitCellars (60) | No second winery  | No third winery      | 
| India     | SunsetVines (69)    | No second winery  | No third winery      |  
| USA       | RoyalVines (86)     | Eagle&#39;sNest (45)  | PacificCrest (9)     | 
+-----------+---------------------+-------------------+----------------------+
<strong>Explanation</strong>
For Australia
 - HarmonyHill Winery accumulates the highest score of 100 points in Australia.
 - GrapesGalore Winery has a total of 85 points, securing the second-highest position in Australia.
 - WhisperingPines Winery has a total of 80 points, ranking as the third-highest.
For Hungary
 - MoonlitCellars is the sole winery, accruing 60 points, automatically making it the highest. There is no second or third winery.
For India
 - SunsetVines is the sole winery, earning 69 points, making it the top winery. There is no second or third winery.
For the USA
 - RoyalVines Wines accumulates a total of 47 + 39 = 86 points, claiming the highest position in the USA.
 - Eagle&#39;sNest has a total of 45 points, securing the second-highest position in the USA.
 - PacificCrest accumulates 9 points, ranking as the third-highest winery in the USA
Output table is ordered by country in ascending order.
</pre>

## Solutions

### Solution 1: Grouping + Window Function + Left Join

We can first group the `Wineries` table by `country` and `winery`, calculate the total score `points` for each group, then use the window function `RANK()` to group the data by `country` again, sort by `points` in descending order and `winery` in ascending order, and use the `CONCAT()` function to concatenate `winery` and `points`, resulting in the following data, denoted as table `T`:

| country   | winery               | rk  |
| --------- | -------------------- | --- |
| Australia | HarmonyHill (100)    | 1   |
| Australia | GrapesGalore (85)    | 2   |
| Australia | WhisperingPines (84) | 3   |
| Hungary   | MoonlitCellars (60)  | 1   |
| India     | SunsetVines (69)     | 1   |
| USA       | RoyalVines (86)      | 1   |
| USA       | Eagle'sNest (45)     | 2   |
| USA       | PacificCrest (9)     | 3   |

Next, we just need to filter out the data where `rk = 1`, then join table `T` to itself twice, connecting the data where `rk = 2` and `rk = 3` respectively, to get the final result.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            country,
            CONCAT(winery, ' (', points, ')') AS winery,
            RANK() OVER (
                PARTITION BY country
                ORDER BY points DESC, winery
            ) AS rk
        FROM (SELECT country, SUM(points) AS points, winery FROM Wineries GROUP BY 1, 3) AS t
    )
SELECT
    t1.country,
    t1.winery AS top_winery,
    IFNULL(t2.winery, 'No second winery') AS second_winery,
    IFNULL(t3.winery, 'No third winery') AS third_winery
FROM
    T AS t1
    LEFT JOIN T AS t2 ON t1.country = t2.country AND t1.rk = t2.rk - 1
    LEFT JOIN T AS t3 ON t2.country = t3.country AND t2.rk = t3.rk - 1
WHERE t1.rk = 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- end -->
