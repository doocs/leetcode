# [1517. 查找拥有有效邮箱的用户](https://leetcode.cn/problems/find-users-with-valid-e-mails)

[English Version](/solution/1500-1599/1517.Find%20Users%20With%20Valid%20E-Mails/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>用户表：&nbsp;Users</p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user_id       | int     |
| name          | varchar |
| mail          | varchar | 
+---------------+---------+
user_id （用户 ID）是该表的主键。
这个表包含用户在某网站上注册的信息。有些邮箱是无效的。</pre>

<p>&nbsp;</p>

<p>写一条&nbsp;SQL 语句，查询拥有<strong>有效邮箱</strong>的用户。</p>

<p>有效的邮箱包含符合下列条件的前缀名和域名：</p>

<ul>
	<li><strong>前缀名</strong>是包含字母（大写或小写）、数字、下划线&nbsp;<code>&#39;_&#39;</code>、句点&nbsp;<code>&#39;.&#39;</code>&nbsp;和/或横杠&nbsp;<code>&#39;-&#39;</code>&nbsp;的字符串。前缀名<strong>必须</strong>以字母开头。</li>
	<li><strong>域名</strong>是&nbsp;<code>&#39;@leetcode.com&#39;</code>&nbsp;。</li>
</ul>

<p>按任意顺序返回结果表。</p>

<p>&nbsp;</p>

<p>查询格式如下所示：</p>

<pre>
<code>Users</code>
+---------+-----------+-------------------------+
| user_id | name      | mail                    |
+---------+-----------+-------------------------+
| 1       | Winston   | winston@leetcode.com    |
| 2       | Jonathan  | jonathanisgreat         |
| 3       | Annabelle | bella-@leetcode.com     |
| 4       | Sally     | sally.come@leetcode.com |
| 5       | Marwan    | quarz#2020@leetcode.com |
| 6       | David     | david69@gmail.com       |
| 7       | Shapiro   | .shapo@leetcode.com     |
+---------+-----------+-------------------------+

结果表：
+---------+-----------+-------------------------+
| user_id | name      | mail                    |
+---------+-----------+-------------------------+
| 1       | Winston   | winston@leetcode.com    |
| 3       | Annabelle | bella-@leetcode.com     |
| 4       | Sally     | sally.come@leetcode.com |
+---------+-----------+-------------------------+
2 号用户的邮箱没有域名。
5 号用户的邮箱包含非法字符 #。
6 号用户的邮箱的域名不是 leetcode。
7 号用户的邮箱以句点（.）开头。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
