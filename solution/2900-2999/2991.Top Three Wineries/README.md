# [2991. 最好的三家酒庄](https://leetcode.cn/problems/top-three-wineries)

[English Version](/solution/2900-2999/2991.Top%20Three%20Wineries/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表：&nbsp;<code>Wineries</code></p>

<pre>
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| id          | int      |
| country     | varchar  |
| points      | int      |
| winery      | varchar  |
+-------------+----------+
id 是这张表具有唯一值的列。
这张表包含 id, country, points,和 winery。
</pre>

<p>编写一个解决方案，根据每家酒庄的 <strong>总分</strong> 找出 <strong>每个国家</strong> 的 <strong>前三名酒庄</strong>。如果有 <strong>多个酒庄</strong> 的总分 <strong>相同</strong>，则按 <code>winery</code> 名称升序排列。如果没有 <strong>分数排在第二的酒庄</strong>，则输出 'No Second Winery'，如果没有 <strong>分数排在第三的酒庄</strong>，则输出 'No Third Winery'。</p>

<p>返回结果表按<meta charset="UTF-8" /><em>&nbsp;</em><code>country</code><em>&nbsp;</em><strong>升序</strong> 排列。</p>

<p>结果表格格式如下例所示。</p>

<p>&nbsp;</p>

<p><b>示例 1:</b></p>

<pre>
<b>输入：</b>
Wineries table:
+-----+-----------+--------+-----------------+
| id  | country   | points | winery          | 
+-----+-----------+--------+-----------------+
| 103 | Australia | 84     | WhisperingPines | 
| 737 | Australia | 85     | GrapesGalore    |    
| 848 | Australia | 100    | HarmonyHill     | 
| 222 | Hungary   | 60     | MoonlitCellars  | 
| 116 | USA       | 47     | RoyalVines      | 
| 124 | USA       | 45     | Eagle'sNest     | 
| 648 | India     | 69     | SunsetVines     | 
| 894 | USA       | 39     | RoyalVines      |  
| 677 | USA       | 9      | PacificCrest    |  
+-----+-----------+--------+-----------------+
<b>输出：</b>
+-----------+---------------------+-------------------+----------------------+
| country   | top_winery          | second_winery     | third_winery         |
+-----------+---------------------+-------------------+----------------------+
| Australia | HarmonyHill (100)   | GrapesGalore (85) | WhisperingPines (84) |
| Hungary   | MoonlitCellars (60) | No second winery  | No third winery      | 
| India     | SunsetVines (69)    | No second winery  | No third winery      |  
| USA       | RoyalVines (86)     | Eagle'sNest (45)  | PacificCrest (9)     | 
+-----------+---------------------+-------------------+----------------------+
<b>解释：</b>
对于 Australia
&nbsp;- HarmonyHill 酒庄获得了 Australia 的最高分数，为 100 分。
&nbsp;- GrapesGalore 酒庄总共获得 85 分，位列 Australia 的第二位。
&nbsp;- WhisperingPines 酒庄总共获得 80 分，位列 Australia 的第三位。
对于 Hungary
&nbsp;- MoonlitCellars 是唯一的酒庄，获得 60 分，自动成为最高分数的酒庄。没有第二或第三家酒庄。
对于 India
&nbsp;- SunsetVines 是唯一的酒庄，获得 69 分，成为最高的酒庄。没有第二或第三家酒庄。
对于 USA
&nbsp;- RoyalVines Wines 累计了总分 47 + 39 = 86 分，占据了 USA 的最高位置。
&nbsp;- Eagle'sNest 总共获得 45 分，位列 USA 的第二高位置。
&nbsp;- PacificCrest 累计了 9 分，位列 USA 的第三高酒庄。
输出表按国家首字母升序排列。
</pre>

## 解法

### 方法一：分组 + 窗口函数 + 左连接

我们可以先对 `Wineries` 表按照 `country` 和 `winery` 进行分组，计算每个分组的总得分 `points`，然后再利用窗口函数 `RANK()` 将数据再按照 `country` 进行分组，按照 `points` 降序、`winery` 升序进行排序，并且用 `CONCAT()` 函数将 `winery` 和 `points` 进行拼接，得到如下形式的数据，记为 `T` 表：

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

接下来，我们只需要筛选出 `rk = 1` 的数据，然后再将 `T` 表自连接两次，分别连接 `rk = 2` 和 `rk = 3` 的数据，即可得到最终结果。

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
