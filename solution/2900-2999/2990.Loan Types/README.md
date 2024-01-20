# [2990. 贷款类型](https://leetcode.cn/problems/loan-types)

[English Version](/solution/2900-2999/2990.Loan%20Types/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：&nbsp;<code>Loans</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| loan_id     | int     |
| user_id     | int     |
| loan_type   | varchar |
+-------------+---------+
loan_id 是这张表具有唯一值的列。
该表包含 loan_id, user_id,和 loan_type。
</pre>

<p>编写一个解决方案，找出所有具有至少一种 <strong>再融资</strong> 贷款类型和至少一种 <strong>抵押</strong> 贷款类型的&nbsp;<strong>不同的</strong>&nbsp;<code>user_id</code>。</p>

<p>按 <em><strong>升序</strong> 返回结果表中的 </em><code>user_id</code>。</p>

<p>返回结果表格式如下例所示。</p>

<p>&nbsp;</p>

<p><b>示例 1:</b></p>

<pre>
<b>输入：</b>
Sessions table:
+---------+---------+-----------+
| loan_id | user_id | loan_type |
+---------+---------+-----------+
| 683     | 101     | Mortgage  |
| 218     | 101     | AutoLoan  |
| 802     | 101     | Inschool  |
| 593     | 102     | Mortgage  |
| 138     | 102     | Refinance |
| 294     | 102     | Inschool  |
| 308     | 103     | Refinance |
| 389     | 104     | Mortgage  |
+---------+---------+-----------+
<b>输出</b>
+---------+
| user_id | 
+---------+
| 102     | 
+---------+
<b>解释</b>
- User_id 101 有三种贷款类型，其中之一是 Mortgage。但是，此用户没有任何类别为 Refinance 的贷款类型，因此用户 101 不会被考虑。
- User_id 102 拥有三种贷款类型：一种是 Mortgage，一种是 Refinance。因此，用户 102 将包括在结果中。
- User_id 103 有一种 Refinance 贷款类型，但没有 Mortgage 贷款类型，因此用户 103 不会被考虑。
- User_id 104 有一种 Mortgage 贷款类型，但没有 Refinance 贷款类型，因此用户 104 不会被考虑。
输出表以升序按 user_id 排序。
</pre>

## 解法

### 方法一：分组求和

我们可以对 `Loans` 表按照 `user_id` 进行分组，找出既包含 `Refinance` 又包含 `Mortgage` 的用户，然后按照 `user_id` 进行排序。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT user_id
FROM Loans
GROUP BY 1
HAVING SUM(loan_type = 'Refinance') > 0 AND SUM(loan_type = 'Mortgage') > 0
ORDER BY 1;
```

<!-- tabs:end -->

<!-- end -->
