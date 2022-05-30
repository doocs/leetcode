# [1892. 页面推荐 Ⅱ](https://leetcode.cn/problems/page-recommendations-ii)

[English Version](/solution/1800-1899/1892.Page%20Recommendations%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：&nbsp;<code>Friendship</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user1_id      | int     |
| user2_id      | int     |
+---------------+---------+
(user1_id,user2_id)是Friendship表的主键。
该表的每一行表示用户user1_id和user2_id是好友。
</pre>

<p>&nbsp;</p>

<p>表：&nbsp;<code>Likes</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| page_id     | int     |
+-------------+---------+
(user_id,page_id)是Likes表的主键。
(user_id, page_id) is the primary key for this table.
该表的每一行表示user_id喜欢page_id。
</pre>

<p>&nbsp;</p>

<p>您正在为一个社交媒体网站实施一个页面推荐系统。如果页面被<code>user_id</code>的&nbsp;<strong>至少一个朋友喜欢&nbsp;</strong>，而&nbsp;<strong>不被</strong><code>user_id</code><strong>喜欢&nbsp;</strong>，你的系统将&nbsp;<strong>推荐&nbsp;</strong>一个页面到<code>user_id</code>。</p>

<p>编写一个SQL查询来查找针对每个用户的所有可能的&nbsp;<strong>页面建议&nbsp;</strong>。每个建议应该在结果表中显示为一行，包含以下列:</p>

<ul>
	<li><code>user_id</code>: 系统向其提出建议的用户的ID。</li>
	<li><code>page_id</code>: 推荐为&nbsp;<code>user_id</code>&nbsp;的页面ID。.</li>
	<li><code>friends_likes</code>:&nbsp;&nbsp;<code>user_id</code>&nbsp;对应&nbsp;<code>page_id</code>&nbsp;的好友数。</li>
</ul>

<p>以&nbsp;<strong>任意顺序&nbsp;</strong>返回结果表。</p>

<p>查询结果格式示例如下。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Friendship 表:
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
Likes 表:
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
+---------+---------+---------------+
| user_id | page_id | friends_likes |
+---------+---------+---------------+
| 1       | 77      | 2             |
| 1       | 23      | 1             |
| 1       | 24      | 1             |
| 1       | 56      | 1             |
| 1       | 33      | 1             |
| 2       | 24      | 1             |
| 2       | 56      | 1             |
| 2       | 11      | 1             |
| 2       | 88      | 1             |
| 3       | 88      | 1             |
| 3       | 23      | 1             |
| 4       | 88      | 1             |
| 4       | 77      | 1             |
| 4       | 23      | 1             |
| 5       | 77      | 1             |
| 5       | 23      | 1             |
+---------+---------+---------------+
<strong>解释：</strong>
以用户1为例:
—用户1是用户2、3、4、6的好友。
-推荐页面有23(用户2喜欢)，24(用户3喜欢)，56(用户3喜欢)，33(用户6喜欢)，77(用户2和用户3喜欢)。
-请注意，第88页不推荐，因为用户1已经喜欢它。

另一个例子是用户6:
—用户6是用户1的好友。
-用户1只喜欢了88页，但用户6已经喜欢了。因此，用户6没有推荐。

您可以使用类似的过程为用户2、3、4和5推荐页面。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
