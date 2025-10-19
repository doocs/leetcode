---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3716.Find%20Churn%20Risk%20Customers/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3716. 寻找流失风险客户](https://leetcode.cn/problems/find-churn-risk-customers)

[English Version](/solution/3700-3799/3716.Find%20Churn%20Risk%20Customers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>subscription_events</code></p>

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
event_id 是这张表的唯一主键。
event_type 可以是 start，upgrade，downgrade 或 cancel。
plan_name 可以是 basic，standard，premium 或 NULL（当 event_type 是 cancel）。
monthly_amount 表示此次事件后的月度订阅费用。
对于 cancel 的事件，monthly_amount 为 0。
</pre>

<p>编写一个解决方案来 <strong>寻找流失风险用户</strong>&nbsp;- 出现预流失信号的用户。如果用户符合以下所有条件，则被视为 <strong>有流失风险</strong> 的客户：</p>

<ul>
	<li>目前有 <strong>有效的订阅</strong>（他们的最后事件不是 cancel）。</li>
	<li>已在其订阅历史中 <strong>至少进行过一次</strong> 降级。</li>
	<li>他们 <strong>目前的订阅费用</strong> 低于历史最高订阅费用的 <code>50%</code>。</li>
	<li>已订阅 <strong>至少</strong> <code>60</code> 天。</li>
</ul>

<p>返回结果表按&nbsp;<code>days_as_subscriber</code> <strong>降序</strong>&nbsp;排序，然后按&nbsp;<code>user_id</code> <strong>升序&nbsp;</strong>排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>subscription_events 表：</p>

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

<p><strong>输出：</strong></p>

<pre class="example-io">
+----------+--------------+------------------------+-----------------------+--------------------+
| user_id  | current_plan | current_monthly_amount | max_historical_amount | days_as_subscriber |
+----------+--------------+------------------------+-----------------------+--------------------+
| 501      | basic        | 9.99                   | 29.99                 | 79                 |
| 502      | basic        | 9.99                   | 29.99                 | 69                 |
+----------+--------------+------------------------+-----------------------+--------------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>用户 501：</strong>

    <ul>
    	<li>当前订阅有效：最近一次事件是降级到基础（未取消）</li>
    	<li>有降级记录：是，历史上有 2 次降级</li>
    	<li>当前订阅（9.99）vs 最大订阅（29.99）：9.99/29.99 = 33.3%（少于 50%）</li>
    	<li>订阅天数：1 月 1 日到 5 月 20 日 = 79 天（至少 60 天）</li>
    	<li>结果：<strong>流失风险客户</strong></li>
    </ul>
    </li>
    <li><strong>用户 502：</strong>
    <ul>
    	<li>当前订阅有效：最近一次事件是降级到基础（未取消）</li>
    	<li>有降级记录：是，历史上有 1 次降级</li>
    	<li>当前订阅（9.99）vs 最大订阅（29.99）：9.99/29.99 = 33.3%（少于 50%）</li>
    	<li>订阅天数：1 月&nbsp;5 日到 5 月 15 日 = 70 天（至少 60 天）</li>
    	<li>结果：<strong>流失风险客户</strong></li>
    </ul>
    </li>
    <li><strong>用户 503：</strong>
    <ul>
    	<li>当前订阅有效：最近一次事件是升级到高级（未取消）</li>
    	<li>有降级记录：历史上没有降级</li>
    	<li>结果：<strong>无风险客户</strong>（没有降级历史）</li>
    </ul>
    </li>
    <li><strong>用户 504：</strong>
    <ul>
    	<li>当前订阅有效：最近一次事件是取消</li>
    	<li>结果：<strong>无风险客户</strong>（已取消订阅）</li>
    </ul>
    </li>
    <li><strong>用户 505：</strong>
    <ul>
    	<li>当前订阅有效：最近一次事件是升级到标准（未取消）</li>
    	<li>有降级记录：历史上没有降级</li>
    	<li>结果：<strong>无风险客户</strong>（没有降级历史）</li>
    </ul>
    </li>
    <li><strong>用户 506：</strong>
    <ul>
    	<li>当前订阅有效：最近一次事件是降级到标准（未取消）</li>
    	<li>有降级记录：是，历史上有 1 次降级</li>
    	<li>当前订阅（9.99）vs 最大订阅（29.99）：9.99/29.99 = 33.3%（少于 50%）</li>
    	<li>订阅天数：1 月&nbsp;20 日到 5 月 10 日 = 50 天（少于 60 天）</li>
    	<li>结果：<strong>无风险客户</strong>（订阅时长不足）</li>
    </ul>
    </li>

</ul>

<p>结果表按 days_as_subscriber 降序排序，然后按&nbsp;user_id 升序排序。</p>

<p><strong>注意：</strong>days_as_subscriber 按照每个用户的第一个事件日期到最后一个事件日期进行计算。</p>
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
