# [1097. Game Play Analysis V](https://leetcode.com/problems/game-play-analysis-v)

[中文文档](/solution/1000-1099/1097.Game%20Play%20Analysis%20V/README.md)

## Description

<p>Table: <code>Activity</code></p>

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
This table shows the activity of players of some games.
Each row is a record of a player who logged in and played a number of games (possibly 0) before logging out on someday using some device.
</pre>

<p>&nbsp;</p>

<p>The <strong>install date</strong> of a player is the first login day of that player.</p>

<p>We define <strong>day one retention</strong> of some date <code>x</code> to be the number of players whose <strong>install date</strong> is <code>x</code> and they logged back in on the day right after <code>x</code>, divided by the number of players whose install date is <code>x</code>, rounded to <code>2</code> decimal places.</p>

<p>Write an SQL query to report for each install date, the number of players that installed the game on that day, and the <strong>day one retention</strong>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
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
<strong>Output:</strong> 
+------------+----------+----------------+
| install_dt | installs | Day1_retention |
+------------+----------+----------------+
| 2016-03-01 | 2        | 0.50           |
| 2017-06-25 | 1        | 0.00           |
+------------+----------+----------------+
<strong>Explanation:</strong> 
Player 1 and 3 installed the game on 2016-03-01 but only player 1 logged back in on 2016-03-02 so the day 1 retention of 2016-03-01 is 1 / 2 = 0.50
Player 2 installed the game on 2017-06-25 but didn&#39;t log back in on 2017-06-26 so the day 1 retention of 2017-06-25 is 0 / 1 = 0.00
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
