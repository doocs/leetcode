# [602. 好友申请 II ：谁有最多的好友](https://leetcode.cn/problems/friend-requests-ii-who-has-the-most-friends)

[English Version](/solution/0600-0699/0602.Friend%20Requests%20II%20Who%20Has%20the%20Most%20Friends/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在 Facebook 或者 Twitter 这样的社交应用中，人们经常会发好友申请也会收到其他人的好友申请。</p>

<div class="original__bRMd">
<div>
<p><code>RequestAccepted</code> 表：</p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| requester_id   | int     |
| accepter_id    | int     |
| accept_date    | date    |
+----------------+---------+
(requester_id, accepter_id) 是这张表的主键。
这张表包含发送好友请求的人的 ID ，接收好友请求的人的 ID ，以及好友请求通过的日期。
</pre>

<p>&nbsp;</p>

<p>写一个查询语句，找出拥有最多的好友的人和他拥有的好友数目。</p>

<p>生成的测试用例保证拥有最多好友数目的只有 1 个人。</p>

<p>查询结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
RequestAccepted 表：
+--------------+-------------+-------------+
| requester_id | accepter_id | accept_date |
+--------------+-------------+-------------+
| 1            | 2           | 2016/06/03  |
| 1            | 3           | 2016/06/08  |
| 2            | 3           | 2016/06/08  |
| 3            | 4           | 2016/06/09  |
+--------------+-------------+-------------+
<strong>输出：</strong>
+----+-----+
| id | num |
+----+-----+
| 3  | 3   |
+----+-----+
<strong>解释：</strong>
编号为 3 的人是编号为 1 ，2 和 4 的人的好友，所以他总共有 3 个好友，比其他人都多。</pre>

<p>&nbsp;</p>

<p><strong>进阶：</strong>在真实世界里，可能会有多个人拥有好友数相同且最多，你能找到所有这些人吗？</p>
</div>
</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT
    ids AS id, COUNT(*) num
FROM
    (SELECT
        requester_id AS ids
    FROM
        RequestAccepted UNION ALL SELECT
        accepter_id
    FROM
        RequestAccepted) t
GROUP BY ids
ORDER BY num DESC
LIMIT 1;
```

<!-- tabs:end -->
