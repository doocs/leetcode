# [1127. 用户购买平台](https://leetcode.cn/problems/user-purchase-platform)

[English Version](/solution/1100-1199/1127.User%20Purchase%20Platform/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>支出表: <code>Spending</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| spend_date  | date    |
| platform    | enum    | 
| amount      | int     |
+-------------+---------+
这张表记录了用户在一个在线购物网站的支出历史，该在线购物平台同时拥有桌面端（&#39;desktop&#39;）和手机端（&#39;mobile&#39;）的应用程序。
这张表的主键是 (user_id, spend_date, platform)。
平台列 platform 是一种 ENUM ，类型为（&#39;desktop&#39;, &#39;mobile&#39;）。</pre>

<p>&nbsp;</p>

<p>写一段 SQL 来查找每天&nbsp;<strong>仅&nbsp;</strong>使用手机端用户、<strong>仅&nbsp;</strong>使用桌面端用户和&nbsp;<strong>同时&nbsp;</strong>使用桌面端和手机端的用户人数和总支出金额。</p>

<p>查询结果格式如下例所示：</p>

<pre>
<code>Spending</code> table:
+---------+------------+----------+--------+
| user_id | spend_date | platform | amount |
+---------+------------+----------+--------+
| 1       | 2019-07-01 | mobile   | 100    |
| 1       | 2019-07-01 | desktop  | 100    |
| 2       | 2019-07-01 | mobile   | 100    |
| 2       | 2019-07-02 | mobile   | 100    |
| 3       | 2019-07-01 | desktop  | 100    |
| 3       | 2019-07-02 | desktop  | 100    |
+---------+------------+----------+--------+

Result table:
+------------+----------+--------------+-------------+
| spend_date | platform | total_amount | total_users |
+------------+----------+--------------+-------------+
| 2019-07-01 | desktop  | 100          | 1           |
| 2019-07-01 | mobile   | 100          | 1           |
| 2019-07-01 | both     | 200          | 1           |
| 2019-07-02 | desktop  | 100          | 1           |
| 2019-07-02 | mobile   | 100          | 1           |
| 2019-07-02 | both     | 0            | 0           |
+------------+----------+--------------+-------------+ 
在 2019-07-01, 用户1 <strong>同时 </strong>使用桌面端和手机端购买, 用户2 <strong>仅 </strong>使用了手机端购买，而用户3 <strong>仅 </strong>使用了桌面端购买。
在 2019-07-02, 用户2 <strong>仅 </strong>使用了手机端购买, 用户3 <strong>仅 </strong>使用了桌面端购买，且没有用户 <strong>同时 </strong>使用桌面端和手机端购买。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
