---
comments: true
difficulty: 困难
tags:
    - 数据库
---

<!-- problem:start -->

# [3401. 寻找环形礼物交换链 🔒](https://leetcode.cn/problems/find-circular-gift-exchange-chains)

[English Version](/solution/3400-3499/3401.Find%20Circular%20Gift%20Exchange%20Chains/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>SecretSanta</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| giver_id    | int  |
| receiver_id | int  |
| gift_value  | int  |
+-------------+------+
(giver_id, receiver_id) 是这张表的唯一主键。
每一行表示两个员工之间的一次礼物交换记录，giver_id 表示给予礼物的员工，receiver_id 表示收到礼物的员工，gift_value 表示所给予礼物的价值。
</pre>

<p>编写一个解决方案来找到 <strong>总礼物价值</strong>&nbsp;以及 <strong>环形礼物交换链的长度</strong>：</p>

<p><strong>环形链</strong> 被定义为一系列交换，其中：</p>

<ul>
	<li>每位员工都正好向另 <strong>一位</strong> 员工赠送一份礼物。</li>
	<li>每位员工都正好从另 <strong>一位</strong> 员工那里收到一份礼物。</li>
	<li>交换形成一个连续的循环（即 员工 A 给 B 一份礼物，B 给 C，C 再给 A）。</li>
</ul>

<p>返回结果以链的长度和总礼物价值 <strong>降序</strong>&nbsp;排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>SecretSanta 表：</p>

<pre class="example-io">
+----------+-------------+------------+
| giver_id | receiver_id | gift_value |
+----------+-------------+------------+
| 1        | 2           | 20         |
| 2        | 3           | 30         |
| 3        | 1           | 40         |
| 4        | 5           | 25         |
| 5        | 4           | 35         |
+----------+-------------+------------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+----------+--------------+------------------+
| chain_id | chain_length | total_gift_value |
+----------+--------------+------------------+
| 1        | 3            | 90               |
| 2        | 2            | 60               |
+----------+--------------+------------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>链 1</strong>&nbsp;包含员工 1，2 和 3：

    <ul>
    	<li>员工 1 给 2 一份礼物，员工&nbsp;2 给 3 一份礼物，员工 3 给 1 一份礼物。</li>
    	<li>这个链的总礼物价值 = 20 + 30 + 40 = 90。</li>
    </ul>
    </li>
    <li><strong>链 2</strong> 包含员工 4 和 5：
    <ul>
    	<li>员工 4 给 5 一份礼物，员工 5 给 4&nbsp;一份礼物。</li>
    	<li>这个链的总礼物价值 = 25 + 35 = 60。</li>
    </ul>
    </li>

</ul>

<p>结果表以链的长度和总礼物价值降序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 每位员工恰好送出并收到一份礼物，交换关系构成若干个互不相交的有向环。若在应用层逐条走边，需要额外的过程逻辑，而本题要求用一条 SQL 查出每条环的长度与礼物价值和。
>
> 图的规模由交换记录决定，关键是把同一环上的边归到同一组，并避免把一条环重复输出多次。
>
> 因此可用递归 CTE 沿 $\textit{giver\_id}\to\textit{receiver\_id}$ 前进，以环上最小员工编号作为 $\textit{chain\_id}$，再按链聚合长度与 $\textit{gift\_value}$ 之和，最后按长度与总价值降序排列。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
