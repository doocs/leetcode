# [2020. 无流量的帐户数](https://leetcode.cn/problems/number-of-accounts-that-did-not-stream)

[English Version](/solution/2000-2099/2020.Number%20of%20Accounts%20That%20Did%20Not%20Stream/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Subscriptions</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| account_id  | int  |
| start_date  | date |
| end_date    | date |
+-------------+------+
account_id 是此表的主键列。
此表的每一行都表示帐户订阅的开始和结束日期。
请注意，始终开始日期 &lt; 结束日期。</pre>

<p>&nbsp;</p>

<p>表: <code>Streams</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| session_id  | int  |
| account_id  | int  |
| stream_date | date |
+-------------+------+
session_id是该表的主键列。
account_id是订阅表中的外键。
此表的每一行都包含与会话相关联的帐户和日期的信息。</pre>

<p>&nbsp;</p>

<p>编写SQL查询以报告在 <code>2021</code> 购买订阅但没有任何会话的帐 户数。<br />
查询结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例1：</strong></p>

<pre>
<strong>输入:</strong> 
Subscriptions table:
+------------+------------+------------+
| account_id | start_date | end_date   |
+------------+------------+------------+
| 9          | 2020-02-18 | 2021-10-30 |
| 3          | 2021-09-21 | 2021-11-13 |
| 11         | 2020-02-28 | 2020-08-18 |
| 13         | 2021-04-20 | 2021-09-22 |
| 4          | 2020-10-26 | 2021-05-08 |
| 5          | 2020-09-11 | 2021-01-17 |
+------------+------------+------------+
Streams table:
+------------+------------+-------------+
| session_id | account_id | stream_date |
+------------+------------+-------------+
| 14         | 9          | 2020-05-16  |
| 16         | 3          | 2021-10-27  |
| 18         | 11         | 2020-04-29  |
| 17         | 13         | 2021-08-08  |
| 19         | 4          | 2020-12-31  |
| 13         | 5          | 2021-01-05  |
+------------+------------+-------------+
<strong>输出:</strong> 
+----------------+
| accounts_count |
+----------------+
| 2              |
+----------------+
<strong>解释：</strong>用户 4 和 9 在 2021 没有会话。
用户 11 在 2021 没有订阅。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
