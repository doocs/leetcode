# [1194. 锦标赛优胜者](https://leetcode.cn/problems/tournament-winners)

[English Version](/solution/1100-1199/1194.Tournament%20Winners/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>Players</code>&nbsp;玩家表</p>

<pre>
+-------------+-------+
| Column Name | Type  |
+-------------+-------+
| player_id   | int   |
| group_id    | int   |
+-------------+-------+
player_id 是此表的主键。
此表的每一行表示每个玩家的组。
</pre>

<p><code>Matches</code>&nbsp;赛事表</p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| match_id      | int     |
| first_player  | int     |
| second_player | int     | 
| first_score   | int     |
| second_score  | int     |
+---------------+---------+
match_id 是此表的主键。
每一行是一场比赛的记录，first_player 和 second_player 表示该场比赛的球员 ID。
first_score 和 second_score 分别表示 first_player 和 second_player 的得分。
你可以假设，在每一场比赛中，球员都属于同一组。
</pre>

<p>&nbsp;</p>

<p>每组的获胜者是在组内累积得分最高的选手。如果平局，<code>player_id</code> <strong>最小&nbsp;</strong>的选手获胜。</p>

<p>编写一个 SQL 查询来查找每组中的获胜者。</p>

<p>返回的结果表单 <strong>没有顺序要求</strong>&nbsp;。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<code><strong>输入：</strong>
Players 表</code>:
+-----------+------------+
| player_id | group_id   |
+-----------+------------+
| 15        | 1          |
| 25        | 1          |
| 30        | 1          |
| 45        | 1          |
| 10        | 2          |
| 35        | 2          |
| 50        | 2          |
| 20        | 3          |
| 40        | 3          |
+-----------+------------+
<code>Matches 表</code>:
+------------+--------------+---------------+-------------+--------------+
| match_id   | first_player | second_player | first_score | second_score |
+------------+--------------+---------------+-------------+--------------+
| 1          | 15           | 45            | 3           | 0            |
| 2          | 30           | 25            | 1           | 2            |
| 3          | 30           | 15            | 2           | 0            |
| 4          | 40           | 20            | 5           | 2            |
| 5          | 35           | 50            | 1           | 1            |
+------------+--------------+---------------+-------------+--------------+
<strong>输出：</strong>
+-----------+------------+
| group_id  | player_id  |
+-----------+------------+ 
| 1         | 15         |
| 2         | 35         |
| 3         | 40         |
+-----------+------------+</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
