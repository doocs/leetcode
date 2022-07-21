# [1949. 坚定的友谊](https://leetcode.cn/problems/strong-friendship)

[English Version](/solution/1900-1999/1949.Strong%20Friendship/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表名: <code>Friendship</code></p>

<pre>
+-------------+------+
| 列名         | 类型 |
+-------------+------+
| user1_id    | int  |
| user2_id    | int  |
+-------------+------+
(user1_id, user2_id) 是这个表的主键。
这张表的每一行都表示用户 user1_id 和 user2_id 是朋友。
请注意，user1_id &lt; user2_id。
</pre>

<p>&nbsp;</p>

<p>如果 <code>x</code>&nbsp; 和&nbsp;<code>y</code>&nbsp;为<strong>朋友</strong>且他们<strong>至少</strong>有三个共同的朋友 ，那么&nbsp;<code>x</code> 和&nbsp;<code>y</code> 之间的友谊就是<strong>坚定的</strong>。</p>

<p>写一个 SQL 查询来找到所有的<strong>坚定的友谊</strong>。</p>

<p>注意，结果表不应该包含重复，并且 <code>user1_id &lt; user2_id</code>。</p>

<p>以<strong>任何顺序</strong>返回结果表。</p>

<p>查询结果的格式在下面的例子中。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> 
表 Friendship:
+----------+----------+
| user1_id | user2_id |
+----------+----------+
| 1        | 2        |
| 1        | 3        |
| 2        | 3        |
| 1        | 4        |
| 2        | 4        |
| 1        | 5        |
| 2        | 5        |
| 1        | 7        |
| 3        | 7        |
| 1        | 6        |
| 3        | 6        |
| 2        | 6        |
+----------+----------+
<strong>输出:</strong> 
+----------+----------+---------------+
| user1_id | user2_id | common_friend |
+----------+----------+---------------+
| 1        | 2        | 4             |
| 1        | 3        | 3             |
+----------+----------+---------------+
<strong>解释:</strong> 
用户 1 和 2 有 4 个共同的朋友（3，4，5，和 6）。
用户 1 和 3 有 3 个共同的朋友（2，6，和 7）。
我们没有包括用户 2 和 3 的友谊，因为他们只有两个共同的朋友（1 和 6）。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
