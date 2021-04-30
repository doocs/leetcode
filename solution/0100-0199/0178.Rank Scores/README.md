# [178. 分数排名](https://leetcode-cn.com/problems/rank-scores)

[English Version](/solution/0100-0199/0178.Rank%20Scores/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>编写一个 SQL 查询来实现分数排名。</p>

<p>如果两个分数相同，则两个分数排名（Rank）相同。请注意，平分后的下一个名次应该是下一个连续的整数值。换句话说，名次之间不应该有&ldquo;间隔&rdquo;。</p>

<pre>+----+-------+
| Id | Score |
+----+-------+
| 1  | 3.50  |
| 2  | 3.65  |
| 3  | 4.00  |
| 4  | 3.85  |
| 5  | 4.00  |
| 6  | 3.65  |
+----+-------+
</pre>

<p>例如，根据上述给定的&nbsp;<code>Scores</code> 表，你的查询应该返回（按分数从高到低排列）：</p>

<pre>+-------+------+
| Score | Rank |
+-------+------+
| 4.00  | 1    |
| 4.00  | 1    |
| 3.85  | 2    |
|&nbsp;3.65  | 3    |
| 3.65  | 3    |
| 3.50  | 4    |
+-------+------+
</pre>

<p><strong>重要提示：</strong>对于 MySQL 解决方案，如果要转义用作列名的保留字，可以在关键字之前和之后使用撇号。例如 <strong>`Rank`</strong></p>


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

- 首先，`PARTITION BY` 子句将 `FROM` 子句生成的结果集划分为分区。`DENSE_RANK()`函数应用于每个分区。
- 其次，`ORDER BY` 子句指定 `DENSE_RANK()` 函数操作的每个分区中的行顺序。

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
