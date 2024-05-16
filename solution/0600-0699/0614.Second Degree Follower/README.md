---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0614.Second%20Degree%20Follower/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [614. 二级关注者 🔒](https://leetcode.cn/problems/second-degree-follower)

[English Version](/solution/0600-0699/0614.Second%20Degree%20Follower/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Follow</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| followee    | varchar |
| follower    | varchar |
+-------------+---------+
(followee, follower) 是该表的主键(具有唯一值的列的组合)。
该表的每一行表示关注者关注了社交网络上的关注者。
不会有用户关注他们自己。
</pre>

<p>&nbsp;</p>

<p><strong>二级关注者</strong> 是指满足以下条件的用户:</p>

<ul>
	<li>关注至少一个用户，</li>
	<li>被至少一个用户关注。</li>
</ul>

<p>编写一个解决方案来报告 <strong>二级用户</strong> 及其关注者的数量。</p>

<p>返回按 <code>follower</code> <strong>字典序排序&nbsp;</strong>的结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>
Follow table:
+----------+----------+
| followee | follower |
+----------+----------+
| Alice    | Bob      |
| Bob      | Cena     |
| Bob      | Donald   |
| Donald   | Edward   |
+----------+----------+
<b>输出：</b>
+----------+-----+
| follower | num |
+----------+-----+
| Bob      | 2   |
| Donald   | 1   |
+----------+-----+
<b>解释：</b>
用户 Bob 有 2 个关注者。Bob 是二级关注者，因为他关注了 Alice，所以我们把他包括在结果表中。
用户 Donald 有 1 个关注者。Donald 是二级关注者，因为他关注了 Bob，所以我们把他包括在结果表中。
用户 Alice 有 1 个关注者。Alice 不是二级关注者，但是她不关注任何人，所以我们不把她包括在结果表中。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT f1.follower AS follower, f2.follower AS followee
        FROM
            Follow AS f1
            JOIN Follow AS f2 ON f1.follower = f2.followee
    )
SELECT follower, COUNT(DISTINCT followee) AS num
FROM T
GROUP BY 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
