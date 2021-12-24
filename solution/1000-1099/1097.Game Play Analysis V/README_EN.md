# [1097. Game Play Analysis V](https://leetcode.com/problems/game-play-analysis-v)

[中文文档](/solution/1000-1099/1097.Game%20Play%20Analysis%20V/README.md)

## Description

<p>Table:&nbsp;<code>Activity</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| player_id    | int     |
| device_id    | int     |
| event_date   | date    |
| games_played | int     |
+--------------+---------+
(player_id, event_date) is the primary key of this table.
This table shows the activity of players of some game.
Each row is a record of a player who logged in and played a number of games (possibly 0) before logging out on some day using some device.
</pre>

<p>&nbsp;</p>

<p>We define the <em>install date</em> of a player to be the first login day of that player.</p>

<p>We also define <em>day 1 retention</em>&nbsp;of some date <code>X</code>&nbsp;to be the number&nbsp;of players whose install date is&nbsp;<code>X</code>&nbsp;and they logged back in on the day right after <code>X</code>, divided by the number of players whose install date is&nbsp;<code>X</code>, <strong>rounded to 2 decimal places</strong>.</p>

<p>Write an SQL query that reports for each <strong>install date</strong>, the <strong>number&nbsp;of players</strong> that installed the game on that day and the <strong>day 1 retention</strong>.</p>

<p>The query result format is in the following example:</p>

<pre>
Activity table:
+-----------+-----------+------------+--------------+
| player_id | device_id | event_date | games_played |
+-----------+-----------+------------+--------------+
| 1         | 2         | 2016-03-01 | 5            |
| 1         | 2         | 2016-03-02 | 6            |
| 2         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-01 | 0            |
| 3         | 4         | 2016-07-03 | 5            |
+-----------+-----------+------------+--------------+

Result table:
+------------+----------+----------------+
| install_dt | installs | Day1_retention |
+------------+----------+----------------+
| 2016-03-01 | 2        | 0.50           |
| 2017-06-25 | 1        | 0.00           |
+------------+----------+----------------+
Player 1 and 3 installed the game on 2016-03-01 but only player 1 logged back in on 2016-03-02 so the day 1 retention of 2016-03-01 is 1 / 2 = 0.50
Player 2 installed the game on 2017-06-25 but didn&#39;t log back in on 2017-06-26 so the day 1 retention of 2017-06-25 is 0 / 1 = 0.00
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
