# [2051. 商店中每个成员的级别](https://leetcode.cn/problems/the-category-of-each-member-in-the-store)

[English Version](/solution/2000-2099/2051.The%20Category%20of%20Each%20Member%20in%20the%20Store/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Members</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| member_id   | int     |
| name        | varchar |
+-------------+---------+
member_id 是该表的主键。
该表的每一行都表示成员的名称和 ID。
</pre>

<p>&nbsp;</p>

<p>表: <code>Visits</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| visit_id    | int  |
| member_id   | int  |
| visit_date  | date |
+-------------+------+
visit_id 是该表的主键。
member_id 是 Members 表中 member_id 的外键。
该表的每一行都包含关于访问商店的日期和访问该商店的成员的信息。
</pre>

<p>&nbsp;</p>

<p>表: <code>Purchases</code></p>

<pre>
+----------------+------+
| Column Name    | Type |
+----------------+------+
| visit_id       | int  |
| charged_amount | int  |
+----------------+------+
visit_id 是该表的主键。
visit_id 是访问表 visit_id 的外键。
该表的每一行都包含了关于在商店中消费的信息。
</pre>

<p>&nbsp;</p>

<p>一个商店想对其成员进行分类。有三个层次:</p>

<ul>
	<li><strong>"钻石"</strong>: 如果转换率&nbsp;<strong>大于或等于</strong> <code>80</code>.</li>
	<li><strong>"黄金"</strong>: 如果转换率&nbsp;<strong>大于或等于</strong> <code>50</code> 且小于 <code>80</code>.</li>
	<li><strong>"白银"</strong>: 如果转化率 <strong>小于</strong> <code>50</code>.</li>
	<li><strong>"青铜"</strong>: 如果该成员从未访问过该商店。</li>
</ul>

<p>成员的&nbsp;<strong>转化率&nbsp;</strong>为 <code>(100 * 该会员的购买总数) / 该成员的总访问次数</code>.</p>

<p>编写一个 SQL 来查询每个成员的 id、名称和类别。</p>

<p data-group="1-1">以&nbsp;<strong>任意顺序&nbsp;</strong>返回结果表。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Members 表:
+-----------+---------+
| member_id | name    |
+-----------+---------+
| 9         | Alice   |
| 11        | Bob     |
| 3         | Winston |
| 8         | Hercy   |
| 1         | Narihan |
+-----------+---------+
Visits 表:
+----------+-----------+------------+
| visit_id | member_id | visit_date |
+----------+-----------+------------+
| 22       | 11        | 2021-10-28 |
| 16       | 11        | 2021-01-12 |
| 18       | 9         | 2021-12-10 |
| 19       | 3         | 2021-10-19 |
| 12       | 11        | 2021-03-01 |
| 17       | 8         | 2021-05-07 |
| 21       | 9         | 2021-05-12 |
+----------+-----------+------------+
Purchases 表:
+----------+----------------+
| visit_id | charged_amount |
+----------+----------------+
| 12       | 2000           |
| 18       | 9000           |
| 17       | 7000           |
+----------+----------------+
<strong>输出:</strong> 
+-----------+---------+----------+
| member_id | name    | category |
+-----------+---------+----------+
| 1         | Narihan | Bronze   |
| 3         | Winston | Silver   |
| 8         | Hercy   | Diamond  |
| 9         | Alice   | Gold     |
| 11        | Bob     | Silver   |
+-----------+---------+----------+
<strong>解释:</strong> 
- id = 1 的成员 Narihan 没有访问过该商店。她获得了铜奖。
- id = 3 的成员 Winston 访问了商店一次，但没有购买任何东西。转化率=(100 * 0)/ 1 = 0。他获得了银奖。
- id = 8 的成员 Hercy 访问商店一次，购买一次。转化率=(100 * 1)/ 1 = 1。他获得了钻石奖。
- id = 9 的成员 Alice 访问了商店两次，购买了一次。转化率=(100 * 1)/ 2 = 50。她获得了金奖。
- id = 11 的用户 Bob 访问了商店三次，购买了一次。转化率=(100 * 1)/ 3 = 33.33。他获得了银奖。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
