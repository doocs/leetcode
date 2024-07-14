---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2377.Sort%20the%20Olympic%20Table/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2377. 整理奥运表 🔒](https://leetcode.cn/problems/sort-the-olympic-table)

[English Version](/solution/2300-2399/2377.Sort%20the%20Olympic%20Table/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Olympic</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| country       | varchar |
| gold_medals   | int     |
| silver_medals | int     |
| bronze_medals | int     |
+---------------+---------+
在 SQL 中，country 是该表的主键。
该表中的每一行都显示了一个国家的名称以及它在奥运会上获得的金、银、铜牌的数量。
</pre>

<p>&nbsp;</p>

<p>奥运名次表的排序规则如下:</p>

<ul>
	<li>金牌越多的国家排名第一。</li>
	<li>如果金牌数持平，银牌多的国家排名第一。</li>
	<li>如果银牌数量持平，铜牌数量最多的国家排名第一。</li>
	<li>如果铜牌中出现并列，那么并列的国家将按照字典的升序进行排序。</li>
</ul>

<p>写一个解决方案对奥运表进行排序</p>

<p>返回结果格式示例如下。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Olympic 表:
+-------------+-------------+---------------+---------------+
| country     | gold_medals | silver_medals | bronze_medals |
+-------------+-------------+---------------+---------------+
| China       | 10          | 10            | 20            |
| South Sudan | 0           | 0             | 1             |
| USA         | 10          | 10            | 20            |
| Israel      | 2           | 2             | 3             |
| Egypt       | 2           | 2             | 2             |
+-------------+-------------+---------------+---------------+
<strong>输出:</strong> 
+-------------+-------------+---------------+---------------+
| country     | gold_medals | silver_medals | bronze_medals |
+-------------+-------------+---------------+---------------+
| China       | 10          | 10            | 20            |
| USA         | 10          | 10            | 20            |
| Israel      | 2           | 2             | 3             |
| Egypt       | 2           | 2             | 2             |
| South Sudan | 0           | 0             | 1             |
+-------------+-------------+---------------+---------------+
<strong>解释:</strong> 
中国和美国之间的联系被它们的字典名称打破了。因为 "China" 在字典上比 "USA" 小，所以它排在第一位。
以色列排在埃及之前，因为它的铜牌更多。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT *
FROM Olympic
ORDER BY 2 DESC, 3 DESC, 4 DESC, 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
