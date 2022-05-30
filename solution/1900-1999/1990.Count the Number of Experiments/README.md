# [1990. 统计实验的数量](https://leetcode.cn/problems/count-the-number-of-experiments)

[English Version](/solution/1900-1999/1990.Count%20the%20Number%20of%20Experiments/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Experiments</code></p>

<pre>
+-----------------+------+
| Column Name     | Type |
+-----------------+------+
| experiment_id   | int  |
| platform        | enum |
| experiment_name | enum |
+-----------------+------+

experiment_id 是这个表的主键.
platform 是枚举类型的，取值是这三种 ('Android', 'IOS', 'Web') 之一.
experiment_name 也是枚举类型的，取值是这三种 ('Reading', 'Sports', 'Programming') 之一.
这个表包含有关随机实验人员进行的实验的 ID、用于做实验的平台以及实验名称的信息。
</pre>

<p>&nbsp;</p>

<p>写一个 SQL 查询语句，以报告在给定三个实验平台中每种实验完成的次数。请注意，每一对（实验平台、实验名称）都应包含在输出中，包括平台上实验次数是零的。</p>

<p>结果可以以任意顺序给出。</p>

<p>查询的结果如下所示：</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
Experiments table:
+---------------+----------+-----------------+
| experiment_id | platform | experiment_name |
+---------------+----------+-----------------+
| 4             | IOS      | Programming     |
| 13            | IOS      | Sports          |
| 14            | Android  | Reading         |
| 8             | Web      | Reading         |
| 12            | Web      | Reading         |
| 18            | Web      | Programming     |
+---------------+----------+-----------------+
<strong>输出：</strong>
+----------+-----------------+-----------------+
| platform | experiment_name | num_experiments |
+----------+-----------------+-----------------+
| Android  | Reading         | 1               |
| Android  | Sports          | 0               |
| Android  | Programming     | 0               |
| IOS      | Reading         | 0               |
| IOS      | Sports          | 1               |
| IOS      | Programming     | 1               |
| Web      | Reading         | 2               |
| Web      | Sports          | 0               |
| Web      | Programming     | 1               |
+----------+-----------------+-----------------+
<strong>解释：</strong>
在安卓平台上, 我们只做了一个"Reading" 实验.
在 "IOS" 平台上，我们做了一个"Sports" 实验和一个"Programming" 实验.
在 "Web" 平台上，我们做了两个"Reading" 实验和一个"Programming" 实验.
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
