---
comments: true
difficulty: 简单
tags:
    - 数据库
---

<!-- problem:start -->

# [1809. 没有广告的剧集 🔒](https://leetcode.cn/problems/ad-free-sessions)

[English Version](/solution/1800-1899/1809.Ad-Free%20Sessions/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Playback</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| session_id  | int  |
| customer_id | int  |
| start_time  | int  |
| end_time    | int  |
+-------------+------+
session_id 是该表中具有唯一值的列。
customer_id 是观看该剧集的客户的 id。
剧集播放时间包含start_time（开始时间） 及 end_time（结束时间）
可以保证的是，start_time（开始时间）&lt;= end_time（结束时间），一个观众观看的两个剧集的时间不会出现重叠。</pre>

<p>&nbsp;</p>

<p>表：<code>Ads</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| ad_id       | int  |
| customer_id | int  |
| timestamp   | int  |
+-------------+------+
ad_id 是该表中具有唯一值的列。
customer_id 为 观看广告的用户 id
timestamp 表示广告出现的时间点
</pre>

<p>&nbsp;</p>

<p>编写解决方案找出所有没有广告出现过的剧集。</p>

<p>返回结果 <strong>无顺序要求</strong> 。</p>

<p>返回结果格式如下例所示：</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Playback table:
+------------+-------------+------------+----------+
| session_id | customer_id | start_time | end_time |
+------------+-------------+------------+----------+
| 1          | 1           | 1          | 5        |
| 2          | 1           | 15         | 23       |
| 3          | 2           | 10         | 12       |
| 4          | 2           | 17         | 28       |
| 5          | 2           | 2          | 8        |
+------------+-------------+------------+----------+
Ads table:
+-------+-------------+-----------+
| ad_id | customer_id | timestamp |
+-------+-------------+-----------+
| 1     | 1           | 5         |
| 2     | 2           | 17        |
| 3     | 2           | 20        |
+-------+-------------+-----------+
<strong>输出：</strong>
+------------+
| session_id |
+------------+
| 2          |
| 3          |
| 5          |
+------------+
<strong>解释：</strong>
广告1出现在了剧集1的时间段，被观众1看到了。
广告2出现在了剧集4的时间段，被观众2看到了。
广告3出现在了剧集4的时间段，被观众2看到了。
我们可以得出结论，剧集1 、4 内，起码有1处广告。 剧集2 、3 、5 没有广告。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 需要找出播放过程中从未被同一用户广告命中的会话。若在应用层对每个会话扫描全部广告，两表规模较大时代价过高。
>
> 用子查询把「同一 $customer\_id$ 且广告时间落在 $[start\_time,end\_time]$」的会话求出来，外层再选出不在该集合中的 $session\_id$。连接条件同时约束用户与时间，即可一次排除所有被插播的会话。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT session_id
FROM Playback
WHERE
    session_id NOT IN (
        SELECT session_id
        FROM
            Playback AS p
            JOIN Ads AS a
                ON p.customer_id = a.customer_id AND a.timestamp BETWEEN p.start_time AND p.end_time
    );
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
