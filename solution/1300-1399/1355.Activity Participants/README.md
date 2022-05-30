# [1355. 活动参与者](https://leetcode.cn/problems/activity-participants)

[English Version](/solution/1300-1399/1355.Activity%20Participants/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Friends</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| name          | varchar |
| activity      | varchar |
+---------------+---------+
id 是朋友的 id 和该表的主键
name 是朋友的名字
activity 是朋友参加的活动的名字
</pre>

<p>&nbsp;</p>

<p>表: <code>Activities</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| name          | varchar |
+---------------+---------+
id 是该表的主键
name 是活动的名字
</pre>

<p>&nbsp;</p>

<p>写一条 SQL 查询那些既没有最多，也没有最少参与者的活动的名字</p>

<p><code>Activities</code> 表中的任意活动都有在&nbsp;<code>Friends</code> 中参与过</p>

<p>可以以 <strong>任何顺序</strong> 返回结果。</p>

<p>下面是查询结果格式的例子。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Friends 表:
+------+--------------+---------------+
| id   | name         | activity      |
+------+--------------+---------------+
| 1    | Jonathan D.  | Eating        |
| 2    | Jade W.      | Singing       |
| 3    | Victor J.    | Singing       |
| 4    | Elvis Q.     | Eating        |
| 5    | Daniel A.    | Eating        |
| 6    | Bob B.       | Horse Riding  |
+------+--------------+---------------+
Activities 表:
+------+--------------+
| id   | name         |
+------+--------------+
| 1    | Eating       |
| 2    | Singing      |
| 3    | Horse Riding |
+------+--------------+
<strong>输出：</strong>
+--------------+
| activity     |
+--------------+
| Singing      |
+--------------+
<strong>解释：</strong>
Eating 活动有三个人参加, 是最多人参加的活动 (Jonathan D. , Elvis Q. and Daniel A.)
Horse Riding 活动有一个人参加, 是最少人参加的活动 (Bob B.)
Singing 活动有两个人参加 (Victor J. and Jade W.)</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
