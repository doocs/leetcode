# [511. 游戏玩法分析 I](https://leetcode.cn/problems/game-play-analysis-i)

[English Version](/solution/0500-0599/0511.Game%20Play%20Analysis%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>活动表&nbsp;<code>Activity</code>：</p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| player_id    | int     |
| device_id    | int     |
| event_date   | date    |
| games_played | int     |
+--------------+---------+
表的主键是 (player_id, event_date)。
这张表展示了一些游戏玩家在游戏平台上的行为活动。
每行数据记录了一名玩家在退出平台之前，当天使用同一台设备登录平台后打开的游戏的数目（可能是 0 个）。
</pre>

<p>&nbsp;</p>

<p>写一条 SQL&nbsp;查询语句获取每位玩家 <strong>第一次登陆平台的日期</strong>。</p>

<p>查询结果的格式如下所示：</p>

<pre>
Activity 表：
+-----------+-----------+------------+--------------+
| player_id | device_id | event_date | games_played |
+-----------+-----------+------------+--------------+
| 1         | 2         | 2016-03-01 | 5            |
| 1         | 2         | 2016-05-02 | 6            |
| 2         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-02 | 0            |
| 3         | 4         | 2018-07-03 | 5            |
+-----------+-----------+------------+--------------+

Result 表：
+-----------+-------------+
| player_id | first_login |
+-----------+-------------+
| 1         | 2016-03-01  |
| 2         | 2017-06-25  |
| 3         | 2016-03-02  |
+-----------+-------------+
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT
    player_id, MIN(event_date) first_login
FROM
    Activity
GROUP BY player_id;
```

<!-- tabs:end -->
