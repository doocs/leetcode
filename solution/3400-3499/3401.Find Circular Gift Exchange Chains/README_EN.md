---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3401.Find%20Circular%20Gift%20Exchange%20Chains/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3401. Find Circular Gift Exchange Chains 🔒](https://leetcode.com/problems/find-circular-gift-exchange-chains)

[中文文档](/solution/3400-3499/3401.Find%20Circular%20Gift%20Exchange%20Chains/README.md)

## Description

<!-- description:start -->

<p>Table: <code>SecretSanta</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| giver_id    | int  |
| receiver_id | int  |
| gift_value  | int  |
+-------------+------+
(giver_id, receiver_id) is the unique key for this table.   
Each row represents a record of a gift exchange between two employees, giver_id represents the employee who gives a gift, receiver_id represents the employee who receives the gift and gift_value represents the value of the gift given.  
</pre>

<p>Write a solution to find the <strong>total gift value</strong> and <strong>length</strong> of<strong> circular chains</strong> of Secret Santa gift exchanges:</p>

<p>A <strong>circular chain</strong> is defined as a series of exchanges where:</p>

<ul>
	<li>Each employee gives a gift to <strong>exactly one</strong> other employee.</li>
	<li>Each employee receives a gift <strong>from exactly</strong> one other employee.</li>
	<li>The exchanges form a continuous <strong>loop</strong> (e.g., employee A gives a gift to B, B gives to C, and C gives back to A).</li>
</ul>

<p>Return <em>the result ordered by the chain length and total gift value of the chain in&nbsp;<strong>descending</strong> order</em>.&nbsp;</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>SecretSanta table:</p>

<pre class="example-io">
+----------+-------------+------------+
| giver_id | receiver_id | gift_value |
+----------+-------------+------------+
| 1        | 2           | 20         |
| 2        | 3           | 30         |
| 3        | 1           | 40         |
| 4        | 5           | 25         |
| 5        | 4           | 35         |
+----------+-------------+------------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+----------+--------------+------------------+
| chain_id | chain_length | total_gift_value |
+----------+--------------+------------------+
| 1        | 3            | 90               |
| 2        | 2            | 60               |
+----------+--------------+------------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>Chain 1</strong> involves employees 1, 2, and 3:

    <ul>
    	<li>Employee 1 gives a gift to 2, employee 2 gives a gift to 3, and employee 3 gives a gift to 1.</li>
    	<li>Total gift value for this chain = 20 + 30 + 40 = 90.</li>
    </ul>
    </li>
    <li><strong>Chain 2</strong> involves employees 4 and 5:
    <ul>
    	<li>Employee 4 gives a gift to 5, and employee 5 gives a gift to 4.</li>
    	<li>Total gift value for this chain = 25 + 35 = 60.</li>
    </ul>
    </li>

</ul>

<p>The result table is ordered by the chain length and total gift value of the chain in descending order.</p>
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
