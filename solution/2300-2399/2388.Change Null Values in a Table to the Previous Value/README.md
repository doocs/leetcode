# [2388. 将表中的空值更改为前一个值 🔒](https://leetcode.cn/problems/change-null-values-in-a-table-to-the-previous-value)

[English Version](/solution/2300-2399/2388.Change%20Null%20Values%20in%20a%20Table%20to%20the%20Previous%20Value/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>CoffeeShop</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| drink       | varchar |
+-------------+---------+
id 是该表的主键（具有唯一值的列）。
该表中的每一行都显示了订单 id 和所点饮料的名称。一些饮料行为 null。
</pre>

<p>&nbsp;</p>

<p>编写一个解决方案将 drink 的 <code>null</code>&nbsp;值替换为前面最近一行不为 <code>null</code>&nbsp;的 drink。保证表第一行的 drink 不为 <code>null</code>。</p>

<p>返回&nbsp;<strong>与输入顺序相同的&nbsp;</strong>结果表。</p>

<p>查询结果格式示例如下。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
CoffeeShop 表:
+----+-------------------+
| id | drink             |
+----+-------------------+
| 9  | Rum and Coke      |
| 6  | null              |
| 7  | null              |
| 3  | St Germain Spritz |
| 1  | Orange Margarita  |
| 2  | null              |
+----+-------------------+
<strong>输出:</strong> 
+----+-------------------+
| id | drink             |
+----+-------------------+
| 9  | Rum and Coke      |
| 6  | Rum and Coke      |
| 7  | Rum and Coke      |
| 3  | St Germain Spritz |
| 1  | Orange Margarita  |
| 2  | Orange Margarita  |
+----+-------------------+
<strong>解释:</strong> 
对于 ID 6，之前不为空的值来自 ID 9。我们将 null 替换为 "Rum and Coke"。
对于 ID 7，之前不为空的值来自 ID 9。我们将 null 替换为 "Rum and Coke"。
对于 ID 2，之前不为空的值来自 ID 1。我们将 null 替换为 "Orange Margarita"。
请注意，输出中的行与输入中的行相同。
</pre>

## 解法

### 方法一：临时变量

我们可以使用一个临时变量 $cur$ 来记录上一个不为 $null$ 的值，如果当前值为 $null$，则将 $cur$ 的值赋给当前值，否则我们更新 $cur$ 的值为当前值。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    id,
    CASE
        WHEN drink IS NOT NULL THEN @cur := drink
        ELSE @cur
    END AS drink
FROM CoffeeShop;
```

<!-- tabs:end -->

### 方法二：窗口函数

我们先用窗口函数 `row_number()` 为每一行生成一个序号，然后使用 `sum()` 窗口函数来生成一个分组序号，分组序号的生成规则为：如果当前行的值为 $null$，则分组序号与上一行相同，否则分组序号加一。最后我们使用 `max()` 窗口函数来获取每一组唯一一个不为 $null$ 的值。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    S AS (
        SELECT *, ROW_NUMBER() OVER () AS rk
        FROM CoffeeShop
    ),
    T AS (
        SELECT
            *,
            SUM(
                CASE
                    WHEN drink IS NULL THEN 0
                    ELSE 1
                END
            ) OVER (ORDER BY rk) AS gid
        FROM S
    )
SELECT
    id,
    MAX(drink) OVER (
        PARTITION BY gid
        ORDER BY rk
    ) AS drink
FROM T;
```

<!-- tabs:end -->

<!-- end -->
