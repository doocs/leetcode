---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3832.Find%20Users%20with%20Persistent%20Behavior%20Patterns/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3832. 查找具有持续行为模式的用户](https://leetcode.cn/problems/find-users-with-persistent-behavior-patterns)

[English Version](/solution/3800-3899/3832.Find%20Users%20with%20Persistent%20Behavior%20Patterns/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>activity</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| user_id      | int     |
| action_date  | date    |
| action       | varchar |
+--------------+---------+
(user_id, action_date, action) 是这张表的主键（值互不相同）。
每一行代表一个用户在特定日期执行的具体操作。
</pre>

<p>根据以下定义，编写一个解决方案来识别 <strong>行为稳定的用户</strong>：</p>

<ul>
	<li>一个用户如果存在一个 <strong>连续至少&nbsp;</strong><code>5</code>&nbsp;天的行为序列满足以下条件，则认为他是 <strong>行为稳定</strong>&nbsp;的：

    <ul>
    	<li>该用户在该期间 <strong>每天只执行了一个操作</strong>。</li>
    	<li>这些连续的日子里，<strong>操作都是相同的</strong>。</li>
    </ul>
    </li>
    <li>如果一个用户有多个符合条件的序列，只考虑 <strong>最长</strong> 的那条序列。</li>

</ul>

<p>返回结果表按&nbsp;<code>streak_length</code> <strong>降序&nbsp;</strong>排序，然后按<em>&nbsp;</em><code>user_id</code> <strong>升序</strong>&nbsp;排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>activity 表：</p>

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

<p><strong>输出：</strong></p>

<pre class="example-io">
+---------+--------+---------------+------------+------------+
| user_id | action | streak_length | start_date | end_date   |
+---------+--------+---------------+------------+------------+
| 3       | view   | 7             | 2024-01-01 | 2024-01-07 |
| 1       | login  | 5             | 2024-01-01 | 2024-01-05 |
+---------+--------+---------------+------------+------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>用户 1：</strong>

    <ul>
    	<li>从 2024 年 1 月 1 日至 2024 年 1 月 5 日连续五天执行&nbsp;<code>login</code>&nbsp;操作</li>
    	<li>每一天都恰好有一个操作，且操作相同</li>
    	<li>连续长度 = 5（满足最小要求）</li>
    	<li>行动在 2024-01-06 发生变化，结束连续计数</li>
    </ul>
    </li>
    <li><strong>用户 2：</strong>
    <ul>
    	<li>只连续执行了 4 天&nbsp;<code>click</code>&nbsp;操作</li>
    	<li>不满足最小连续计数 5 天的要求</li>
    	<li>从结果排除</li>
    </ul>
    </li>
    <li><strong>用户 3：</strong>
    <ul>
    	<li>连续 7 天执行了&nbsp;<code>view</code>&nbsp;操作</li>
    	<li>这是此用户的最长有效序列</li>
    	<li>包含在结果中</li>
    </ul>
    </li>

</ul>

<p>结果表按 streak_length 降序排序，然后按 user_id 升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：筛选 + 分组 + 聚合

我们首先需要筛选出每天仅有单一操作的用户日期，然后识别这些日期中的连续区间，最后聚合这些区间以计算连续长度并筛选出符合条件的记录。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    daily_counts AS (
        -- 步骤 1: 筛选每天只有一条记录的用户日期（符合题目“exactly one action per day”的要求）
        SELECT
            user_id,
            action_date,
            action,
            COUNT(*) OVER (PARTITION BY user_id, action_date) AS cnt
        FROM activity
    ),
    filtered_activity AS (
        -- 步骤 2: 过滤掉同一天有多个操作的数据
        SELECT user_id, action_date, action
        FROM daily_counts
        WHERE cnt = 1
    ),
    streak_groups AS (
        -- 步骤 3: 使用日期减去连续行号的方法来分组连续日期
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
        -- 步骤 4: 计算每个连续片段的长度，并只保留长度 >= 5 的记录
        SELECT
            user_id,
            action,
            COUNT(*) AS streak_length,
            MIN(action_date) AS start_date,
            MAX(action_date) AS end_date,
            -- 为每个用户的不同 streak 排序，以便后续取最大值
            ROW_NUMBER() OVER (
                PARTITION BY user_id
                ORDER BY COUNT(*) DESC
            ) AS rnk
        FROM streak_groups
        GROUP BY user_id, action, grp
        HAVING streak_length >= 5
    )
-- 步骤 5: 提取每个符合条件用户最长的那条记录并排序
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

    # 筛选每日仅有单一操作的用户
    df = activity.assign(cnt=activity.groupby(['user_id', 'action_date'])['action'].transform('count'))
    df = df[df['cnt'] == 1].sort_values(['user_id', 'action', 'action_date'])

    # 识别连续区间
    df['rn'] = df.groupby(['user_id', 'action'])['action_date'].rank(method='first')
    df['grp'] = df['action_date'] - pd.to_timedelta(df['rn'], unit='D')

    # 聚合 streak
    streaks = df.groupby(['user_id', 'action', 'grp']).agg(
        streak_length=('action_date', 'count'),
        start_date=('action_date', 'min'),
        end_date=('action_date', 'max')
    ).reset_index()

    # 筛选并取每位用户最长 streak
    res = streaks[streaks['streak_length'] >= 5].sort_values(
        ['streak_length', 'user_id'], ascending=[False, True]
    )

    return res.groupby('user_id').head(1)[['user_id', 'action', 'streak_length', 'start_date', 'end_date']]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
