---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3832.Find%20Users%20with%20Persistent%20Behavior%20Patterns/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3832. Find Users with Persistent Behavior Patterns](https://leetcode.com/problems/find-users-with-persistent-behavior-patterns)

[中文文档](/solution/3800-3899/3832.Find%20Users%20with%20Persistent%20Behavior%20Patterns/README.md)

## Description

<!-- description:start -->

<p>Table: <code>activity</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| user_id      | int     |
| action_date  | date    |
| action       | varchar |
+--------------+---------+
(user_id, action_date, action) is the primary key (unique value) for this table.
Each row represents a user performing a specific action on a given date.
</pre>

<p>Write a solution to identify <strong>behaviorally stable users</strong> based on the following definition:</p>

<ul>
	<li>A user is considered <strong>behaviorally stable</strong> if there exists a sequence of <strong>at least </strong><code>5</code><strong> consecutive days</strong> such that:

    <ul>
    	<li>The user performed <strong>exactly one action per day</strong> during that period.</li>
    	<li>The <strong>action is the same</strong> on all those consecutive days.</li>
    </ul>
    </li>
    <li>If a user has multiple qualifying sequences, only consider the sequence with the <strong>maximum length</strong>.</li>

</ul>

<p>Return <em>the result table ordered by</em> <code>streak_length</code> <em>in <strong>descending</strong> order</em>,<em> then by </em><code>user_id</code> <em>in <strong>ascending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>activity table:</p>

<pre class="example-io">
+---------+-------------+--------+
| user_id | action_date | action |
+---------+-------------+--------+
| 1       | 2024-01-01  | login  |
| 1       | 2024-01-02  | login  |
| 1       | 2024-01-03  | login  |
| 1       | 2024-01-04  | login  |
| 1       | 2024-01-05  | login  |
| 1       | 2024-01-06  | logout |
| 2       | 2024-01-01  | click  |
| 2       | 2024-01-02  | click  |
| 2       | 2024-01-03  | click  |
| 2       | 2024-01-04  | click  |
| 3       | 2024-01-01  | view   |
| 3       | 2024-01-02  | view   |
| 3       | 2024-01-03  | view   |
| 3       | 2024-01-04  | view   |
| 3       | 2024-01-05  | view   |
| 3       | 2024-01-06  | view   |
| 3       | 2024-01-07  | view   |
+---------+-------------+--------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+---------+--------+---------------+------------+------------+
| user_id | action | streak_length | start_date | end_date   |
+---------+--------+---------------+------------+------------+
| 3       | view   | 7             | 2024-01-01 | 2024-01-07 |
| 1       | login  | 5             | 2024-01-01 | 2024-01-05 |
+---------+--------+---------------+------------+------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>User 1</strong>:

    <ul>
    	<li>Performed <code>login</code> from 2024-01-01 to 2024-01-05 on consecutive days</li>
    	<li>Each day has exactly one action, and the action is the same</li>
    	<li>Streak length = 5 (meets minimum requirement)</li>
    	<li>The action changes on 2024-01-06, ending the streak</li>
    </ul>
    </li>
    <li><strong>User 2</strong>:
    <ul>
    	<li>Performed <code>click</code> for only 4 consecutive days</li>
    	<li>Does not meet the minimum streak length of 5</li>
    	<li>Excluded from the result</li>
    </ul>
    </li>
    <li><strong>User 3</strong>:
    <ul>
    	<li>Performed <code>view</code> for 7 consecutive days</li>
    	<li>This is the longest valid sequence for this user</li>
    	<li>Included in the result</li>
    </ul>
    </li>

</ul>

<p>The Results table is ordered by streak_length in descending order, then by user_id in ascending order</p>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### MySQL

```sql

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
