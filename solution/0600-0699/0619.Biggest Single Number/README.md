# [619. 只出现一次的最大数字](https://leetcode.cn/problems/biggest-single-number)

[English Version](/solution/0600-0699/0619.Biggest%20Single%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>MyNumbers</code> 表：</p>

<div class="original__bRMd">
<div>
<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| num         | int  |
+-------------+------+
这张表没有主键。可能包含重复数字。
这张表的每一行都含有一个整数。
</pre>

<p>&nbsp;</p>

<p><strong>单一数字</strong> 是在 <code>MyNumbers</code> 表中只出现一次的数字。</p>

<p>请你编写一个 SQL 查询来报告最大的 <strong>单一数字</strong> 。如果不存在 <strong>单一数字</strong> ，查询需报告 <code>null</code> 。</p>

<p>查询结果如下例所示。</p>
<ptable> </ptable>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
MyNumbers 表：
+-----+
| num |
+-----+
| 8   |
| 8   |
| 3   |
| 3   |
| 1   |
| 4   |
| 5   |
| 6   |
+-----+
<strong>输出：</strong>
+-----+
| num |
+-----+
| 6   |
+-----+
<strong>解释：</strong>单一数字有 1、4、5 和 6 。
6 是最大的单一数字，返回 6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>
MyNumbers table:
+-----+
| num |
+-----+
| 8   |
| 8   |
| 7   |
| 7   |
| 3   |
| 3   |
| 3   |
+-----+
<strong>输出：</strong>
+------+
| num  |
+------+
| null |
+------+
<strong>解释：</strong>输入的表中不存在单一数字，所以返回 null 。
</pre>
</div>
</div>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT MAX(a.num) AS num
FROM (
	SELECT num
	FROM MyNumbers
	GROUP BY num
	HAVING count(*) = 1
) a;
```

<!-- tabs:end -->
