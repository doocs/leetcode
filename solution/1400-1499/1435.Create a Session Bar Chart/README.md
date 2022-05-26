# [1435. 制作会话柱状图](https://leetcode.cn/problems/create-a-session-bar-chart)

[English Version](/solution/1400-1499/1435.Create%20a%20Session%20Bar%20Chart/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Sessions</code></p>

<pre>+---------------------+---------+
| Column Name         | Type    |
+---------------------+---------+
| session_id          | int     |
| duration            | int     |
+---------------------+---------+
session_id 是该表主键
duration 是用户访问应用的时间, 以秒为单位
</pre>

<p>&nbsp;</p>

<p>你想知道用户在你的 app 上的访问时长情况。因此决定统计访问时长区间分别为 &quot;[0-5&gt;&quot;, &quot;[5-10&gt;&quot;, &quot;[10-15&gt;&quot;&nbsp;和&nbsp;&quot;15 or more&quot; （单位：分钟）的会话数量，并以此绘制柱状图。</p>

<p>写一个SQL查询来报告（访问时长区间，会话总数）。结果可用任何顺序呈现。</p>

<p>&nbsp;</p>

<p><strong>下方为查询的输出格式：</strong></p>

<pre>Sessions 表：
+-------------+---------------+
| session_id  | duration      |
+-------------+---------------+
| 1           | 30            |
| 2           | 199           |
| 3           | 299           |
| 4           | 580           |
| 5           | 1000          |
+-------------+---------------+

Result 表：
+--------------+--------------+
| bin          | total        |
+--------------+--------------+
| [0-5&gt;        | 3            |
| [5-10&gt;       | 1            |
| [10-15&gt;      | 0            |
| 15 or more   | 1            |
+--------------+--------------+

对于 session_id 1，2 和 3 ，它们的访问时间大于等于 0 分钟且小于 5 分钟。
对于 session_id 4，它的访问时间大于等于 5 分钟且小于 10 分钟。
没有会话的访问时间大于等于 10 分钟且小于 15 分钟。
对于 session_id 5, 它的访问时间大于等于 15 分钟。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
(SELECT
    '[0-5>' bin,
    SUM(CASE
        WHEN duration / 60 < 5 THEN 1
        ELSE 0
    END) total
FROM
    Sessions) UNION (SELECT
    '[5-10>' bin,
    SUM(CASE
        WHEN
            (duration / 60 >= 5
                AND duration / 60 < 10)
        THEN
            1
        ELSE 0
    END) total
FROM
    Sessions) UNION (SELECT
    '[10-15>' bin,
    SUM(CASE
        WHEN
            (duration / 60 >= 10
                AND duration / 60 < 15)
        THEN
            1
        ELSE 0
    END) total
FROM
    Sessions) UNION (SELECT
    '15 or more' bin,
    SUM(CASE
        WHEN duration / 60 >= 15 THEN 1
        ELSE 0
  END) total
FROM
    Sessions);
```

<!-- tabs:end -->
