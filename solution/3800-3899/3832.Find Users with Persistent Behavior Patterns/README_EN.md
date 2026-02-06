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

### Solution 1: Filtering + Grouping + Aggregation

We first need to filter user dates with only a single action per day, then identify consecutive intervals among these dates, and finally aggregate these intervals to calculate the streak length and filter records that meet the criteria.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    daily_counts AS (
        -- Step 1: Filter user dates with exactly one record per day (meeting the requirement of "exactly one action per day")
        SELECT
            user_id,
            action_date,
            action,
            COUNT(*) OVER (PARTITION BY user_id, action_date) AS cnt
        FROM activity
    ),
    filtered_activity AS (
        -- Step 2: Filter out data with multiple actions on the same day
        SELECT user_id, action_date, action
        FROM daily_counts
        WHERE cnt = 1
    ),
    streak_groups AS (
        -- Step 3: Group consecutive dates using the method of subtracting row number from date
        SELECT
            user_id,
            action,
            action_date,
            DATE_SUB(
                action_date,
                INTERVAL ROW_NUMBER() OVER (
                    PARTITION BY user_id, action
                    ORDER BY action_date
                ) DAY
            ) AS grp
        FROM filtered_activity
    ),
    streak_summary AS (
        -- Step 4: Calculate the length of each consecutive segment and only keep records with length >= 5
        SELECT
            user_id,
            action,
            COUNT(*) AS streak_length,
            MIN(action_date) AS start_date,
            MAX(action_date) AS end_date,
            -- Sort different streaks for each user to facilitate getting the maximum value later
            ROW_NUMBER() OVER (
                PARTITION BY user_id
                ORDER BY COUNT(*) DESC
            ) AS rnk
        FROM streak_groups
        GROUP BY user_id, action, grp
        HAVING streak_length >= 5
    )
-- Step 5: Extract the longest record for each qualified user and sort
SELECT user_id, action, streak_length, start_date, end_date
FROM streak_summary
WHERE rnk = 1
ORDER BY streak_length DESC, user_id ASC;
```

#### Pandas

```python
import pandas as pd

def find_behaviorally_stable_users(activity: pd.DataFrame) -> pd.DataFrame:
    activity['action_date'] = pd.to_datetime(activity['action_date'])

    # Filter users with only a single action per day
    df = activity.assign(cnt=activity.groupby(['user_id', 'action_date'])['action'].transform('count'))
    df = df[df['cnt'] == 1].sort_values(['user_id', 'action', 'action_date'])

    # Identify consecutive intervals
    df['rn'] = df.groupby(['user_id', 'action'])['action_date'].rank(method='first')
    df['grp'] = df['action_date'] - pd.to_timedelta(df['rn'], unit='D')

    # Aggregate streaks
    streaks = df.groupby(['user_id', 'action', 'grp']).agg(
        streak_length=('action_date', 'count'),
        start_date=('action_date', 'min'),
        end_date=('action_date', 'max')
    ).reset_index()

    # Filter and get the longest streak for each user
    res = streaks[streaks['streak_length'] >= 5].sort_values(
        ['streak_length', 'user_id'], ascending=[False, True]
    )

    return res.groupby('user_id').head(1)[['user_id', 'action', 'streak_length', 'start_date', 'end_date']]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
