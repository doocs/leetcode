---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3586.Find%20COVID%20Recovery%20Patients/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3586. Find COVID Recovery Patients](https://leetcode.cn/problems/find-covid-recovery-patients)

[English Version](/solution/3500-3599/3586.Find%20COVID%20Recovery%20Patients/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Table: <code>patients</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| patient_id  | int     |
| patient_name| varchar |
| age         | int     |
+-------------+---------+
patient_id is the unique identifier for this table.
Each row contains information about a patient.
</pre>

<p>Table: <code>covid_tests</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| test_id     | int     |
| patient_id  | int     |
| test_date   | date    |
| result      | varchar |
+-------------+---------+
test_id is the unique identifier for this table.
Each row represents a COVID test result. The result can be Positive, Negative, or Inconclusive.
</pre>

<p>Write a solution to find patients who have <strong>recovered from COVID</strong> - patients who tested positive but later tested negative.</p>

<ul>
	<li>A patient is considered recovered if they have <strong>at least one</strong> <strong>Positive</strong> test followed by at least one <strong>Negative</strong> test on a <strong>later date</strong></li>
	<li>Calculate the <strong>recovery time</strong> in days as the <strong>difference</strong> between the <strong>first positive test</strong> and the <strong>first negative test</strong> after that <strong>positive test</strong></li>
	<li><strong>Only include</strong> patients who have both positive and negative test results</li>
</ul>

<p>Return <em>the result table ordered by </em><code>recovery_time</code><em> in <strong>ascending</strong> order, then by </em><code>patient_name</code><em> in <strong>ascending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>patients table:</p>

<pre class="example-io">
+------------+--------------+-----+
| patient_id | patient_name | age |
+------------+--------------+-----+
| 1          | Alice Smith  | 28  |
| 2          | Bob Johnson  | 35  |
| 3          | Carol Davis  | 42  |
| 4          | David Wilson | 31  |
| 5          | Emma Brown   | 29  |
+------------+--------------+-----+
</pre>

<p>covid_tests table:</p>

<pre class="example-io">
+---------+------------+------------+--------------+
| test_id | patient_id | test_date  | result       |
+---------+------------+------------+--------------+
| 1       | 1          | 2023-01-15 | Positive     |
| 2       | 1          | 2023-01-25 | Negative     |
| 3       | 2          | 2023-02-01 | Positive     |
| 4       | 2          | 2023-02-05 | Inconclusive |
| 5       | 2          | 2023-02-12 | Negative     |
| 6       | 3          | 2023-01-20 | Negative     |
| 7       | 3          | 2023-02-10 | Positive     |
| 8       | 3          | 2023-02-20 | Negative     |
| 9       | 4          | 2023-01-10 | Positive     |
| 10      | 4          | 2023-01-18 | Positive     |
| 11      | 5          | 2023-02-15 | Negative     |
| 12      | 5          | 2023-02-20 | Negative     |
+---------+------------+------------+--------------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+------------+--------------+-----+---------------+
| patient_id | patient_name | age | recovery_time |
+------------+--------------+-----+---------------+
| 1          | Alice Smith  | 28  | 10            |
| 3          | Carol Davis  | 42  | 10            |
| 2          | Bob Johnson  | 35  | 11            |
+------------+--------------+-----+---------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>Alice Smith (patient_id = 1):</strong>

    <ul>
    	<li>First positive test: 2023-01-15</li>
    	<li>First negative test after positive: 2023-01-25</li>
    	<li>Recovery time: 25 - 15 = 10 days</li>
    </ul>
    </li>
    <li><strong>Bob Johnson (patient_id = 2):</strong>
    <ul>
    	<li>First positive test: 2023-02-01</li>
    	<li>Inconclusive test on 2023-02-05 (ignored for recovery calculation)</li>
    	<li>First negative test after positive: 2023-02-12</li>
    	<li>Recovery time: 12 - 1 = 11 days</li>
    </ul>
    </li>
    <li><strong>Carol Davis (patient_id = 3):</strong>
    <ul>
    	<li>Had negative test on 2023-01-20 (before positive test)</li>
    	<li>First positive test: 2023-02-10</li>
    	<li>First negative test after positive: 2023-02-20</li>
    	<li>Recovery time: 20 - 10 = 10 days</li>
    </ul>
    </li>
    <li><strong>Patients not included:</strong>
    <ul>
    	<li>David Wilson (patient_id = 4): Only has positive tests, no negative test after positive</li>
    	<li>Emma Brown (patient_id = 5): Only has negative tests, never tested positive</li>
    </ul>
    </li>

</ul>

<p>Output table is ordered by recovery_time in ascending order, and then by patient_name in ascending order.</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### MySQL

```sql

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
