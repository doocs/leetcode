---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3716.Find%20Churn%20Risk%20Customers/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3716. Find Churn Risk Customers](https://leetcode.com/problems/find-churn-risk-customers)

[中文文档](/solution/3700-3799/3716.Find%20Churn%20Risk%20Customers/README.md)

## Description

<!-- description:start -->

<p>Table: <code>subscription_events</code></p>

<pre>
+------------------+---------+
| Column Name      | Type    | 
+------------------+---------+
| event_id         | int     |
| user_id          | int     |
| event_date       | date    |
| event_type       | varchar |
| plan_name        | varchar |
| monthly_amount   | decimal |
+------------------+---------+
event_id is the unique identifier for this table.
event_type can be start, upgrade, downgrade, or cancel.
plan_name can be basic, standard, premium, or NULL (when event_type is cancel).
monthly_amount represents the monthly subscription cost after this event.
For cancel events, monthly_amount is 0.
</pre>

<p>Write a solution to <strong>Find Churn Risk Customers</strong> - users who show warning signs before churning. A user is considered <b>churn risk customer</b>&nbsp;if they meet ALL the following criteria:</p>

<ul>
	<li>Currently have an <strong>active subscription</strong> (their last event is not cancel).</li>
	<li>Have performed <strong>at least one</strong> downgrade in their subscription history.</li>
	<li>Their <strong>current plan revenue</strong> is less than <code>50%</code> of their historical maximum plan revenue.</li>
	<li>Have been a subscriber for <strong>at least</strong> <code>60</code> days.</li>
</ul>

<p>Return <em>the result table&nbsp;ordered by</em> <code>days_as_subscriber</code> <em>in <strong>descending</strong> order, then by</em> <code>user_id</code> <em>in <strong>ascending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>subscription_events table:</p>

<pre class="example-io">
+----------+---------+------------+------------+-----------+----------------+
| event_id | user_id | event_date | event_type | plan_name | monthly_amount |
+----------+---------+------------+------------+-----------+----------------+
| 1        | 501     | 2024-01-01 | start      | premium   | 29.99          |
| 2        | 501     | 2024-02-15 | downgrade  | standard  | 19.99          |
| 3        | 501     | 2024-03-20 | downgrade  | basic     | 9.99           |
| 4        | 502     | 2024-01-05 | start      | standard  | 19.99          |
| 5        | 502     | 2024-02-10 | upgrade    | premium   | 29.99          |
| 6        | 502     | 2024-03-15 | downgrade  | basic     | 9.99           |
| 7        | 503     | 2024-01-10 | start      | basic     | 9.99           |
| 8        | 503     | 2024-02-20 | upgrade    | standard  | 19.99          |
| 9        | 503     | 2024-03-25 | upgrade    | premium   | 29.99          |
| 10       | 504     | 2024-01-15 | start      | premium   | 29.99          |
| 11       | 504     | 2024-03-01 | downgrade  | standard  | 19.99          |
| 12       | 504     | 2024-03-30 | cancel     | NULL      | 0.00           |
| 13       | 505     | 2024-02-01 | start      | basic     | 9.99           |
| 14       | 505     | 2024-02-28 | upgrade    | standard  | 19.99          |
| 15       | 506     | 2024-01-20 | start      | premium   | 29.99          |
| 16       | 506     | 2024-03-10 | downgrade  | basic     | 9.99           |
+----------+---------+------------+------------+-----------+----------------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+----------+--------------+------------------------+-----------------------+--------------------+
| user_id  | current_plan | current_monthly_amount | max_historical_amount | days_as_subscriber |
+----------+--------------+------------------------+-----------------------+--------------------+
| 501      | basic        | 9.99                   | 29.99                 | 79                 |
| 502      | basic        | 9.99                   | 29.99                 | 69                 |
+----------+--------------+------------------------+-----------------------+--------------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>User 501</strong>:

    <ul>
    	<li>Currently active: Last event is downgrade&nbsp;to basic (not cancelled)&nbsp;</li>
    	<li>Has downgrades: Yes, 2 downgrades in history&nbsp;</li>
    	<li>Current revenue (9.99) vs max (29.99): 9.99/29.99 = 33.3% (less than 50%)&nbsp;</li>
    	<li>Days as subscriber: Jan 1 to Mar 20 = 79 days (at least 60)&nbsp;</li>
    	<li>Result: <strong>Churn Risk Customer</strong></li>
    </ul>
    </li>
    <li><strong>User 502</strong>:
    <ul>
    	<li>Currently active: Last event is downgrade&nbsp;to basic (not cancelled)&nbsp;</li>
    	<li>Has downgrades: Yes, 1 downgrade in history&nbsp;</li>
    	<li>Current revenue (9.99) vs max (29.99): 9.99/29.99 = 33.3% (less than 50%)&nbsp;</li>
    	<li>Days as subscriber: Jan 5 to Mar 15 = 70 days (at least 60)&nbsp;</li>
    	<li>Result: <strong>Churn Risk Customer</strong></li>
    </ul>
    </li>
    <li><strong>User 503</strong>:
    <ul>
    	<li>Currently active: Last event is upgrade&nbsp;to premium (not cancelled)&nbsp;</li>
    	<li>Has downgrades: No downgrades in history&nbsp;</li>
    	<li>Result: <strong>Not at-risk</strong> (no downgrade history)</li>
    </ul>
    </li>
    <li><strong>User 504</strong>:
    <ul>
    	<li>Currently active: Last event is cancel</li>
    	<li>Result: <strong>Not at-risk</strong> (subscription cancelled)</li>
    </ul>
    </li>
    <li><strong>User 505</strong>:
    <ul>
    	<li>Currently active: Last event is &#39;upgrade&#39; to standard (not cancelled)&nbsp;</li>
    	<li>Has downgrades: No downgrades in history&nbsp;</li>
    	<li>Result: <strong>Not at-risk</strong> (no downgrade history)</li>
    </ul>
    </li>
    <li><strong>User 506</strong>:
    <ul>
    	<li>Currently active: Last event is downgrade&nbsp;to basic (not cancelled)&nbsp;</li>
    	<li>Has downgrades: Yes, 1 downgrade in history&nbsp;</li>
    	<li>Current revenue (9.99) vs max (29.99): 9.99/29.99 = 33.3% (less than 50%)&nbsp;</li>
    	<li>Days as subscriber: Jan 20 to Mar 10 = 50 days (less than 60)&nbsp;</li>
    	<li>Result: <strong>Not at-risk</strong> (insufficient subscription duration)</li>
    </ul>
    </li>

</ul>

<p>Result table is ordered by days_as_subscriber DESC, then user_id ASC.</p>

<p><strong>Note:</strong> days_as_subscriber is calculated from the first event date to the last event date for each user.</p>
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
