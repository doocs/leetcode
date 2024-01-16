# [1990. Count the Number of Experiments](https://leetcode.com/problems/count-the-number-of-experiments)

[中文文档](/solution/1900-1999/1990.Count%20the%20Number%20of%20Experiments/README.md)

## Description

<p>Table: <code>Experiments</code></p>

<pre>
+-----------------+------+
| Column Name     | Type |
+-----------------+------+
| experiment_id   | int  |
| platform        | enum |
| experiment_name | enum |
+-----------------+------+
experiment_id is the column with unique values for this table.
platform is an enum (category) type of values (&#39;Android&#39;, &#39;IOS&#39;, &#39;Web&#39;).
experiment_name is an enum (category) type of values (&#39;Reading&#39;, &#39;Sports&#39;, &#39;Programming&#39;).
This table contains information about the ID of an experiment done with a random person, the platform used to do the experiment, and the name of the experiment.
</pre>

<p>&nbsp;</p>

<p>Write a solution to report the <strong>number of experiments</strong> done on each of the three platforms for each of the three given experiments. Notice that all the pairs of (platform, experiment) should be included in the output <strong>including</strong> the pairs with <strong>zero experiments</strong>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong>
Experiments table:
+---------------+----------+-----------------+
| experiment_id | platform | experiment_name |
+---------------+----------+-----------------+
| 4             | IOS      | Programming     |
| 13            | IOS      | Sports          |
| 14            | Android  | Reading         |
| 8             | Web      | Reading         |
| 12            | Web      | Reading         |
| 18            | Web      | Programming     |
+---------------+----------+-----------------+
<strong>Output:</strong> 
+----------+-----------------+-----------------+
| platform | experiment_name | num_experiments |
+----------+-----------------+-----------------+
| Android  | Reading         | 1               |
| Android  | Sports          | 0               |
| Android  | Programming     | 0               |
| IOS      | Reading         | 0               |
| IOS      | Sports          | 1               |
| IOS      | Programming     | 1               |
| Web      | Reading         | 2               |
| Web      | Sports          | 0               |
| Web      | Programming     | 1               |
+----------+-----------------+-----------------+
<strong>Explanation:</strong> 
On the platform &quot;Android&quot;, we had only one &quot;Reading&quot; experiment.
On the platform &quot;IOS&quot;, we had one &quot;Sports&quot; experiment and one &quot;Programming&quot; experiment.
On the platform &quot;Web&quot;, we had two &quot;Reading&quot; experiments and one &quot;Programming&quot; experiment.
</pre>

## Solutions

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    P AS (
        SELECT 'Android' AS platform
        UNION
        SELECT 'IOS'
        UNION
        SELECT 'Web'
    ),
    Exp AS (
        SELECT 'Reading' AS experiment_name
        UNION
        SELECT 'Sports'
        UNION
        SELECT 'Programming'
    ),
    T AS (
        SELECT *
        FROM
            P,
            Exp
    )
SELECT platform, experiment_name, COUNT(experiment_id) AS num_experiments
FROM
    T AS t
    LEFT JOIN Experiments USING (platform, experiment_name)
GROUP BY 1, 2;
```

<!-- tabs:end -->

<!-- end -->
