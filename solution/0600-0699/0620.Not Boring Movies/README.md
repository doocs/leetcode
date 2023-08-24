# [620. 有趣的电影](https://leetcode.cn/problems/not-boring-movies)

[English Version](/solution/0600-0699/0620.Not%20Boring%20Movies/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>cinema</code></p>

<pre>
+----------------+----------+
| Column Name    | Type     |
+----------------+----------+
| id             | int      |
| movie          | varchar  |
| description    | varchar  |
| rating         | float    |
+----------------+----------+
id 是该表的主键(具有唯一值的列)。
每行包含有关电影名称、类型和评级的信息。
评级为 [0,10] 范围内的小数点后 2 位浮点数。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，找出所有影片描述为&nbsp;<strong>非</strong>&nbsp;<code>boring</code>&nbsp;(不无聊)&nbsp;的并且<strong> id 为奇数&nbsp;</strong>的影片。</p>

<p>返回结果按&nbsp;<code>rating</code>&nbsp;<strong>降序排列</strong>。</p>

<p>结果格式如下示例。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
+---------+-----------+--------------+-----------+
|   id    | movie     |  description |  rating   |
+---------+-----------+--------------+-----------+
|   1     | War       |   great 3D   |   8.9     |
|   2     | Science   |   fiction    |   8.5     |
|   3     | irish     |   boring     |   6.2     |
|   4     | Ice song  |   Fantacy    |   8.6     |
|   5     | House card|   Interesting|   9.1     |
<strong>+---------+-----------+--------------+-----------+
输出：</strong>
+---------+-----------+--------------+-----------+
|   id    | movie     |  description |  rating   |
+---------+-----------+--------------+-----------+
|   5     | House card|   Interesting|   9.1     |
|   1     | War       |   great 3D   |   8.9     |
+---------+-----------+--------------+-----------+
<strong>解释：</strong>
我们有三部电影，它们的 id 是奇数:1、3 和 5。id = 3 的电影是 boring 的，所以我们不把它包括在答案中。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT *
FROM Cinema
WHERE description != 'boring' AND id % 2 = 1
ORDER BY rating DESC;
```

<!-- tabs:end -->
