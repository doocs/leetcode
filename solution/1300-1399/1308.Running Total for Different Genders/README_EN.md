# [1308. Running Total for Different Genders](https://leetcode.com/problems/running-total-for-different-genders)

[中文文档](/solution/1300-1399/1308.Running%20Total%20for%20Different%20Genders/README.md)

## Description

<p>Table: <code>Scores</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| player_name   | varchar |
| gender        | varchar |
| day           | date    |
| score_points  | int     |
+---------------+---------+
(gender, day) is the primary key for this table.
A competition is held between females team and males team.
Each row of this table indicates that a player_name and with gender has scored score_point in someday.
Gender is &#39;F&#39; if the player is in females team and &#39;M&#39; if the player is in males team.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to find the total score for each gender at each day.</p>

<p>Order the result table by gender and day</p>

<p>The query result format is in the following example:</p>

<pre>
Scores table:
+-------------+--------+------------+--------------+
| player_name | gender | day        | score_points |
+-------------+--------+------------+--------------+
| Aron        | F      | 2020-01-01 | 17           |
| Alice       | F      | 2020-01-07 | 23           |
| Bajrang     | M      | 2020-01-07 | 7            |
| Khali       | M      | 2019-12-25 | 11           |
| Slaman      | M      | 2019-12-30 | 13           |
| Joe         | M      | 2019-12-31 | 3            |
| Jose        | M      | 2019-12-18 | 2            |
| Priya       | F      | 2019-12-31 | 23           |
| Priyanka    | F      | 2019-12-30 | 17           |
+-------------+--------+------------+--------------+
Result table:
+--------+------------+-------+
| gender | day        | total |
+--------+------------+-------+
| F      | 2019-12-30 | 17    |
| F      | 2019-12-31 | 40    |
| F      | 2020-01-01 | 57    |
| F      | 2020-01-07 | 80    |
| M      | 2019-12-18 | 2     |
| M      | 2019-12-25 | 13    |
| M      | 2019-12-30 | 26    |
| M      | 2019-12-31 | 29    |
| M      | 2020-01-07 | 36    |
+--------+------------+-------+
For females team:
First day is 2019-12-30, Priyanka scored 17 points and the total score for the team is 17.
Second day is 2019-12-31, Priya scored 23 points and the total score for the team is 40.
Third day is 2020-01-01, Aron scored 17 points and the total score for the team is 57.
Fourth day is 2020-01-07, Alice scored 23 points and the total score for the team is 80.
For males team:
First day is 2019-12-18, Jose scored 2 points and the total score for the team is 2.
Second day is 2019-12-25, Khali scored 11 points and the total score for the team is 13.
Third day is 2019-12-30, Slaman scored 13 points and the total score for the team is 26.
Fourth day is 2019-12-31, Joe scored 3 points and the total score for the team is 29.
Fifth day is 2020-01-07, Bajrang scored 7 points and the total score for the team is 36.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
