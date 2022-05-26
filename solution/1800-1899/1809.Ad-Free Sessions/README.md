# [1809. 没有广告的剧集](https://leetcode.cn/problems/ad-free-sessions)

[English Version](/solution/1800-1899/1809.Ad-Free%20Sessions/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code>Playback</code></p>

<pre>+-------------+------+
| Column Name | Type |
+-------------+------+
| session_id  | int  |
| customer_id | int  |
| start_time  | int  |
| end_time    | int  |
+-------------+------+
该表主键为：session_id （剧集id）
customer_id 是观看该剧集的观众id
剧集播放时间包含start_time（开始时间） 及 end_time（结束时间）
可以保证的是，start_time（开始时间）&lt;= end_time（结束时间），一个观众观看的两个剧集的时间不会出现重叠。</pre>

<p> </p>

<p>Table: <code>Ads</code></p>

<pre>+-------------+------+
| Column Name | Type |
+-------------+------+
| ad_id       | int  |
| customer_id | int  |
| timestamp   | int  |
+-------------+------+
该表的主键为：ad_id（广告id）
customer_id 为 观看广告的用户id
timestamp 表示广告出现的时间点
</pre>

<p> </p>

<p>请查出，所有没有广告出现过的剧集。</p>

<p>如果观众观看了剧集，并且剧集里出现了广告，就一定会有观众观看广告的记录。</p>

<p>返回结果没有顺序要求。</p>

<p> </p>

<p>示例：</p>

<pre>Playback table:
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

Result table:
+------------+
| session_id |
+------------+
| 2          |
| 3          |
| 5          |
+------------+
广告1出现在了剧集1的时间段，被观众1看到了。
广告2出现在了剧集4的时间段，被观众2看到了。
广告3出现在了剧集4的时间段，被观众2看到了。
我们可以得出结论，剧集1 、4 内，起码有1处广告。 剧集2 、3 、5 没有广告。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
