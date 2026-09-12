---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2308.Arrange%20Table%20by%20Gender/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2308. 按性别排列表格 🔒](https://leetcode.cn/problems/arrange-table-by-gender)

[English Version](/solution/2300-2399/2308.Arrange%20Table%20by%20Gender/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Genders</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| gender      | varchar |
+-------------+---------+
user_id 是该表的主键(具有唯一值的列)。
gender 的值是 'female', 'male','other' 之一。
该表中的每一行都包含用户的 ID 及其性别。
表格中 'female', 'male','other' 数量相等。
</pre>

<p>&nbsp;</p>

<p>编写一个解决方案以重新排列 <code>Genders</code> 表，使行按顺序在&nbsp;<code>'female'</code>,&nbsp;<code>'other'</code>&nbsp;和&nbsp;<code>'male'</code>&nbsp;之间交替。同时每种性别按照&nbsp;user_id 升序进行排序。<br />
按 <strong>上述顺序</strong> 返回结果表。<br />
返回结果格式如以下示例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Genders 表:
+---------+--------+
| user_id | gender |
+---------+--------+
| 4       | male   |
| 7       | female |
| 2       | other  |
| 5       | male   |
| 3       | female |
| 8       | male   |
| 6       | other  |
| 1       | other  |
| 9       | female |
+---------+--------+
<strong>输出:</strong> 
+---------+--------+
| user_id | gender |
+---------+--------+
| 3       | female |
| 1       | other  |
| 4       | male   |
| 7       | female |
| 2       | other  |
| 5       | male   |
| 9       | female |
| 6       | other  |
| 8       | male   |
+---------+--------+
<strong>解释:</strong> 
女性：ID 3、7、9。
其他性别：ID 1、2、6。
男性：ID 4、5、8。
我们在 'female', 'other','male' 之间交替排列表。
注意，每种性别都是按 user_id 升序排序的。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 需要按性别轮流输出，且同一性别内部按 $user\_id$ 升序。直接按性别排序会把三类人分成三段，无法交错。
>
> 在每种性别内用窗口函数按 $user\_id$ 编号，再把 female、other、male 映成 $0,1,2$。先按组内名次、再按该映射排序，同一名次下三人恰好按题目要求的性别顺序出现。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY gender
                ORDER BY user_id
            ) AS rk1,
            CASE
                WHEN gender = 'female' THEN 0
                WHEN gender = 'other' THEN 1
                ELSE 2
            END AS rk2
        FROM Genders
    )
SELECT user_id, gender
FROM t
ORDER BY rk1, rk2;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- thinking:start -->

> **思考**
>
> 方法一借助中间表存放两个排序键。窗口函数可直接写在 $ORDER\ BY$ 中：仍按性别分区、按 $user\_id$ 取 $RANK$，再以性别字典序（female、male、other）作为第二键，省去显式 $CASE$ 与额外一层 $WITH$。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
SELECT
    user_id,
    gender
FROM Genders
ORDER BY
    (
        RANK() OVER (
            PARTITION BY gender
            ORDER BY user_id
        )
    ),
    2;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
