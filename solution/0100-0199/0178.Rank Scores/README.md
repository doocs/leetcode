# [178. 分数排名](https://leetcode.cn/problems/rank-scores)

[English Version](/solution/0100-0199/0178.Rank%20Scores/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表:&nbsp;<code>Scores</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| score       | decimal |
+-------------+---------+
Id是该表的主键。
该表的每一行都包含了一场比赛的分数。Score是一个有两位小数点的浮点值。
</pre>

<p>&nbsp;</p>

<p>编写 SQL 查询对分数进行排序。排名按以下规则计算:</p>

<ul>
	<li>分数应按从高到低排列。</li>
	<li>如果两个分数相等，那么两个分数的排名应该相同。</li>
	<li>在排名相同的分数后，排名数应该是下一个连续的整数。换句话说，排名之间不应该有空缺的数字。</li>
</ul>

<p>按&nbsp;<code>score</code>&nbsp;降序返回结果表。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Scores 表:
+----+-------+
| id | score |
+----+-------+
| 1  | 3.50  |
| 2  | 3.65  |
| 3  | 4.00  |
| 4  | 3.85  |
| 5  | 4.00  |
| 6  | 3.65  |
+----+-------+
<strong>输出:</strong> 
+-------+------+
| score | rank |
+-------+------+
| 4.00  | 1    |
| 4.00  | 1    |
| 3.85  | 2    |
| 3.65  | 3    |
| 3.65  | 3    |
| 3.50  | 4    |
+-------+------+</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **MySQL8**

使用 `DENSE_RANK()` 函数，语法如下：

```sql
DENSE_RANK() OVER (
    PARTITION BY <expression>[{,<expression>...}]
    ORDER BY <expression> [ASC|DESC], [{,<expression>...}]
)
```

在这个语法中：

-   首先，`PARTITION BY` 子句将 `FROM` 子句生成的结果集划分为分区。`DENSE_RANK()`函数应用于每个分区。
-   其次，`ORDER BY` 子句指定 `DENSE_RANK()` 函数操作的每个分区中的行顺序。

与 `RANK()` 函数不同，`DENSE_RANK()` 函数始终返回连续的排名值。

题解如下：

```sql
# Write your MySQL query statement below
SELECT Score, DENSE_RANK() OVER (ORDER BY Score DESC) 'Rank'
FROM Scores;
```

### **MySQL5**

MySQL 8 开始才提供了 `ROW_NUMBER()`，`RANK()`，`DENSE_RANK()` 等[窗口函数](https://dev.mysql.com/doc/refman/8.0/en/window-function-descriptions.html)，在之前的版本，可以使用变量实现类似的功能：

```sql
SELECT Score,
       CONVERT(rk, SIGNED) `Rank`
FROM (SELECT Score,
             IF(@latest = Score, @rank, @rank := @rank + 1) rk,
             @latest := Score
      FROM Scores,
           (SELECT @rank := 0, @latest := NULL) tmp
      ORDER BY Score DESC) s;
```

<!-- tabs:end -->
