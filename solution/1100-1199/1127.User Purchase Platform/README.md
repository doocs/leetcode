# [1127. 用户购买平台 🔒](https://leetcode.cn/problems/user-purchase-platform)

[English Version](/solution/1100-1199/1127.User%20Purchase%20Platform/README_EN.md)

<!-- tags:数据库 -->

<!-- difficulty:困难 -->

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
这张表记录了用户在一个在线购物网站的支出历史，该在线购物平台同时拥有桌面端（'desktop'）和手机端（'mobile'）的应用程序。
(user_id, spend_date, platform) 是这张表的主键(具有唯一值的列的组合)。
平台列 platform 是一种 ENUM ，类型为（'desktop', 'mobile'）。</pre>

<p>&nbsp;</p>

<p>编写解决方案找出每天&nbsp;<strong>仅&nbsp;</strong>使用手机端用户、<strong>仅&nbsp;</strong>使用桌面端用户和&nbsp;<strong>同时&nbsp;</strong>使用桌面端和手机端的用户人数和总支出金额。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>返回结果格式如下例所示：</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<code><strong>输入：</strong>
Spending</code> table:
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
<strong>输出：</strong>
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
<strong>解释：</strong>
在 2019-07-01, 用户1 <strong>同时 </strong>使用桌面端和手机端购买, 用户2 <strong>仅 </strong>使用了手机端购买，而用户3 <strong>仅 </strong>使用了桌面端购买。
在 2019-07-02, 用户2 <strong>仅 </strong>使用了手机端购买, 用户3 <strong>仅 </strong>使用了桌面端购买，且没有用户 <strong>同时 </strong>使用桌面端和手机端购买。</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    P AS (
        SELECT DISTINCT spend_date, 'desktop' AS platform FROM Spending
        UNION
        SELECT DISTINCT spend_date, 'mobile' FROM Spending
        UNION
        SELECT DISTINCT spend_date, 'both' FROM Spending
    ),
    T AS (
        SELECT
            user_id,
            spend_date,
            SUM(amount) AS amount,
            IF(COUNT(platform) = 1, platform, 'both') AS platform
        FROM Spending
        GROUP BY 1, 2
    )
SELECT
    p.*,
    IFNULL(SUM(amount), 0) AS total_amount,
    COUNT(t.user_id) AS total_users
FROM
    P AS p
    LEFT JOIN T AS t USING (spend_date, platform)
GROUP BY 1, 2;
```

<!-- tabs:end -->

<!-- end -->
