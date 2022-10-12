# [1322. Ads Performance](https://leetcode.com/problems/ads-performance)

[中文文档](/solution/1300-1399/1322.Ads%20Performance/README.md)

## Description

<p>Table: <code>Ads</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| ad_id         | int     |
| user_id       | int     |
| action        | enum    |
+---------------+---------+
(ad_id, user_id) is the primary key for this table.
Each row of this table contains the ID of an Ad, the ID of a user, and the action taken by this user regarding this Ad.
The action column is an ENUM type of (&#39;Clicked&#39;, &#39;Viewed&#39;, &#39;Ignored&#39;).
</pre>

<p>&nbsp;</p>

<p>A company is running Ads and wants to calculate the performance of each Ad.</p>

<p>Performance of the Ad is measured using Click-Through Rate (CTR) where:</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1322.Ads%20Performance/images/sql1.png" style="width: 600px; height: 54px;" />
<p>Write an SQL query to find the <code>ctr</code> of each Ad. <strong>Round</strong> <code>ctr</code> to <strong>two decimal points</strong>.</p>

<p>Return the result table ordered by <code>ctr</code> in <strong>descending order</strong> and by <code>ad_id</code> in <strong>ascending order</strong> in case of a tie.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Ads table:
+-------+---------+---------+
| ad_id | user_id | action  |
+-------+---------+---------+
| 1     | 1       | Clicked |
| 2     | 2       | Clicked |
| 3     | 3       | Viewed  |
| 5     | 5       | Ignored |
| 1     | 7       | Ignored |
| 2     | 7       | Viewed  |
| 3     | 5       | Clicked |
| 1     | 4       | Viewed  |
| 2     | 11      | Viewed  |
| 1     | 2       | Clicked |
+-------+---------+---------+
<strong>Output:</strong> 
+-------+-------+
| ad_id | ctr   |
+-------+-------+
| 1     | 66.67 |
| 3     | 50.00 |
| 2     | 33.33 |
| 5     | 0.00  |
+-------+-------+
<strong>Explanation:</strong> 
for ad_id = 1, ctr = (2/(2+1)) * 100 = 66.67
for ad_id = 2, ctr = (1/(1+2)) * 100 = 33.33
for ad_id = 3, ctr = (1/(1+1)) * 100 = 50.00
for ad_id = 5, ctr = 0.00, Note that ad_id = 5 has no clicks or views.
Note that we do not care about Ignored Ads.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
SELECT
  ad_id,
  Ifnull(ROUND(AVG(CASE
    WHEN action = 'Clicked' THEN 1
    WHEN action = 'Viewed' THEN 0
    ELSE NULL
  END) * 100, 2), 0) AS ctr
FROM ads
GROUP BY ad_id
ORDER BY ctr DESC,
ad_id ASC;
```

<!-- tabs:end -->
