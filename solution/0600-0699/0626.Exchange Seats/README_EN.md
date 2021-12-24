# [626. Exchange Seats](https://leetcode.com/problems/exchange-seats)

[中文文档](/solution/0600-0699/0626.Exchange%20Seats/README.md)

## Description

<p>Mary is a teacher in a middle school and she has a table <code>seat</code> storing students&#39; names and their corresponding seat ids.</p>

<p>The column <b>id</b> is continuous increment.</p>

<p>Mary wants to change seats for the adjacent students.</p>

<p>Can you write a SQL query to output the result for Mary?</p>

<p>&nbsp;</p>

<pre>
+---------+---------+
|    id   | student |
+---------+---------+
|    1    | Abbot   |
|    2    | Doris   |
|    3    | Emerson |
|    4    | Green   |
|    5    | Jeames  |
+---------+---------+
</pre>

<p>For the sample input, the output is:</p>

<pre>
+---------+---------+
|    id   | student |
+---------+---------+
|    1    | Doris   |
|    2    | Abbot   |
|    3    | Green   |
|    4    | Emerson |
|    5    | Jeames  |
+---------+---------+
</pre>

<p><b>Note:</b></p>

<p>If the number of students is odd, there is no need to change the last one&#39;s seat.</p>

## Solutions

<!-- tabs:start -->

### **SQL**

```
SELECT
    s1.id, COALESCE(s2.student, s1.student) AS student
FROM
    seat s1
        LEFT JOIN
    seat s2 ON (s1.id+1)^1-1 = s2.id
ORDER BY s1.id;
```

<!-- tabs:end -->
