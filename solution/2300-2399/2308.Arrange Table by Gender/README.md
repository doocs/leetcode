# [2308. 按性别排列表格](https://leetcode.cn/problems/arrange-table-by-gender)

[English Version](/solution/2300-2399/2308.Arrange%20Table%20by%20Gender/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Genders</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| gender      | varchar |
+-------------+---------+
user_id 是该表的主键。
gender 的值是 'female', 'male','other' 之一。
该表中的每一行都包含用户的 ID 及其性别。
表格中 'female', 'male','other' 数量相等。
</pre>

<p>&nbsp;</p>

<p>编写一个SQL查询以重新排列 <code>Genders</code> 表，使行按顺序在&nbsp;<code>'female'</code>,&nbsp;<code>'other'</code>&nbsp;和&nbsp;<code>'male'</code>&nbsp;之间交替。同时每种性别按照&nbsp;user_id 升序进行排序。<br />
按 <strong>上述顺序</strong> 返回结果表。<br />
查询结果格式如以下示例所示。</p>

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
