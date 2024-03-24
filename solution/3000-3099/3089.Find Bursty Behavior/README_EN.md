# [3089. Find Bursty Behavior](https://leetcode.com/problems/find-bursty-behavior)

[中文文档](/solution/3000-3099/3089.Find%20Bursty%20Behavior/README.md)

<!-- tags: -->

## Description

<p>Table: <code>Posts</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| post_id     | int     |
| user_id     | int     |
| post_date   | date    |
+-------------+---------+
post_id is the primary key (column with unique values) for this table.
Each row of this table contains post_id, user_id, and post_date.
</pre>

<p>Write a solution to find users who demonstrate <strong>bursty behavior</strong> in their posting patterns during February <code>2024</code>. <strong>Bursty behavior</strong> is defined as <strong>any</strong> period of <strong>7</strong> <strong>consecutive</strong> days where a user&#39;s posting frequency is <strong>at least twice</strong> to their <strong>average</strong> weekly posting frequency for February <code>2024</code>.</p>

<p><strong>Note:</strong> Only include the dates from February <code>1</code> to February <code>28</code> in your analysis, which means you should count February as having exactly <code>4</code> weeks.</p>

<p>Return <em>the result table orderd by </em><code>user_id</code><em> in </em><strong>ascending</strong><em> order.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>Posts table:</p>

<pre class="example-io">
+---------+---------+------------+
| post_id | user_id | post_date  |
+---------+---------+------------+
| 1       | 1       | 2024-02-27 |
| 2       | 5       | 2024-02-06 |
| 3       | 3       | 2024-02-25 |
| 4       | 3       | 2024-02-14 |
| 5       | 3       | 2024-02-06 |
| 6       | 2       | 2024-02-25 |
+---------+---------+------------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+---------+----------------+------------------+
| user_id | max_7day_posts | avg_weekly_posts |
+---------+----------------+------------------+
| 1       | 1              | 0.2500           |
| 2       | 1              | 0.2500           |
| 5       | 1              | 0.2500           |
+---------+----------------+------------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>User 1:</strong> Made only 1 post in February, resulting in an average of 0.25 posts per week and a max of 1 post in any 7-day period.</li>
	<li><strong>User 2:</strong> Also made just 1 post, with the same average and max 7-day posting frequency as User 1.</li>
	<li><strong>User 5:</strong> Like Users 1 and 2, User 5 made only 1 post throughout February, leading to the same average and max 7-day posting metrics.</li>
	<li><strong>User 3:</strong> Although User 3 made more posts than the others (3 posts), they did not reach twice the average weekly posts in their consecutive 7-day window, so they are not listed in the output.</li>
</ul>

<p><b>Note:</b> Output table is ordered by user_id in ascending order.</p>
</div>

## Solutions

### Solution 1

<!-- tabs:start -->

```sql

```

<!-- tabs:end -->

<!-- end -->
