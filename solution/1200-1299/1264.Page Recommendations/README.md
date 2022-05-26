# [1264. 页面推荐](https://leetcode.cn/problems/page-recommendations)

[English Version](/solution/1200-1299/1264.Page%20Recommendations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>朋友关系列表：&nbsp;<code>Friendship</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user1_id      | int     |
| user2_id      | int     |
+---------------+---------+
这张表的主键是 (user1_id, user2_id)。
这张表的每一行代表着 user1_id 和 user2_id 之间存在着朋友关系。
</pre>

<p>&nbsp;</p>

<p>喜欢列表：&nbsp;<code>Likes</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| page_id     | int     |
+-------------+---------+
这张表的主键是 (user_id, page_id)。
这张表的每一行代表着 user_id 喜欢 page_id。
</pre>

<p>&nbsp;</p>

<p>写一段 SQL &nbsp;向<code>user_id</code> = 1 的用户，推荐其朋友们喜欢的页面。不要推荐该用户已经喜欢的页面。</p>

<p>你返回的结果中不应当包含重复项。</p>

<p>返回结果的格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Friendship table:
+----------+----------+
| user1_id | user2_id |
+----------+----------+
| 1        | 2        |
| 1        | 3        |
| 1        | 4        |
| 2        | 3        |
| 2        | 4        |
| 2        | 5        |
| 6        | 1        |
+----------+----------+
 
Likes table:
+---------+---------+
| user_id | page_id |
+---------+---------+
| 1       | 88      |
| 2       | 23      |
| 3       | 24      |
| 4       | 56      |
| 5       | 11      |
| 6       | 33      |
| 2       | 77      |
| 3       | 77      |
| 6       | 88      |
+---------+---------+

<strong>输出：</strong>
+------------------+
| recommended_page |
+------------------+
| 23               |
| 24               |
| 56               |
| 33               |
| 77               |
+------------------+
<strong>解释：</strong>
用户1 同 用户2, 3, 4, 6 是朋友关系。
推荐页面为： 页面23 来自于 用户2, 页面24 来自于 用户3, 页面56 来自于 用户3 以及 页面33 来自于 用户6。
页面77 同时被 用户2 和 用户3 推荐。
页面88 没有被推荐，因为 用户1 已经喜欢了它。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
