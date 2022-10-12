# [2173. Longest Winning Streak](https://leetcode.com/problems/longest-winning-streak)

[中文文档](/solution/2100-2199/2173.Longest%20Winning%20Streak/README.md)

## Description

<p>Table: <code>Matches</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| player_id   | int  |
| match_day   | date |
| result      | enum |
+-------------+------+
(player_id, match_day) is the primary key for this table.
Each row of this table contains the ID of a player, the day of the match they played, and the result of that match.
The result column is an ENUM type of (&#39;Win&#39;, &#39;Draw&#39;, &#39;Lose&#39;).
</pre>

<p>&nbsp;</p>

<p>The <strong>winning streak</strong> of a player is the number of consecutive wins uninterrupted by draws or losses.</p>

<p>Write an SQL query to count the longest winning streak for each player.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Matches table:
+-----------+------------+--------+
| player_id | match_day  | result |
+-----------+------------+--------+
| 1         | 2022-01-17 | Win    |
| 1         | 2022-01-18 | Win    |
| 1         | 2022-01-25 | Win    |
| 1         | 2022-01-31 | Draw   |
| 1         | 2022-02-08 | Win    |
| 2         | 2022-02-06 | Lose   |
| 2         | 2022-02-08 | Lose   |
| 3         | 2022-03-30 | Win    |
+-----------+------------+--------+
<strong>Output:</strong> 
+-----------+----------------+
| player_id | longest_streak |
+-----------+----------------+
| 1         | 3              |
| 2         | 0              |
| 3         | 1              |
+-----------+----------------+
<strong>Explanation:</strong> 
Player 1:
From 2022-01-17 to 2022-01-25, player 1 won 3 consecutive matches.
On 2022-01-31, player 1 had a draw.
On 2022-02-08, player 1 won a match.
The longest winning streak was 3 matches.

Player 2:
From 2022-02-06 to 2022-02-08, player 2 lost 2 consecutive matches.
The longest winning streak was 0 matches.

Player 3:
On 2022-03-30, player 3 won a match.
The longest winning streak was 1 match.
</pre>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> If we are interested in calculating the longest streak without losing (i.e., win or draw), how will your solution change?</p>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
