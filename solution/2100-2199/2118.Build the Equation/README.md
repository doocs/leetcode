# [2118. 建立方程](https://leetcode.cn/problems/build-the-equation)

[English Version](/solution/2100-2199/2118.Build%20the%20Equation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Terms</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| power       | int  |
| factor      | int  |
+-------------+------+
power 是该表的主键。
该表的每一行包含关于方程的一项的信息。
power 为范围为 [0, 100] 的整数。
factor 为范围为 [-100,100] 的整数，不能为零。
</pre>

<p>&nbsp;</p>

<p>你有一个非常强大的程序，可以解决世界上任何一个变量的方程。传递给程序的方程必须格式化如下:</p>

<ul>
	<li>左边 (LHS) 应该包含所有的术语。</li>
	<li>右边 (RHS) 应该是零。</li>
	<li>LHS 的每一项应遵循 <code>"&lt;sign&gt;&lt;fact&gt;X^&lt;pow&gt;"</code>&nbsp;的格式，其中:
	<ul>
		<li><code>&lt;sign&gt;</code> 是 <code>"+"</code> 或者&nbsp;<code>"-"</code>。</li>
		<li><code>&lt;fact&gt;</code>&nbsp;是&nbsp;<code>factor</code>&nbsp;的绝对值。</li>
		<li><code>&lt;pow&gt;</code> 是 <code>power</code>&nbsp;的值。</li>
	</ul>
	</li>
	<li>如果幂是 <code>1</code>, 不要加上&nbsp;<code>"^&lt;pow&gt;"</code>.
	<ul>
		<li>例如, 如果&nbsp;<code>power = 1</code> 并且&nbsp;<code>factor = 3</code>, 将有 <code>"+3X"</code>。</li>
	</ul>
	</li>
	<li>如果幂是 <code>0</code>,&nbsp;不要加上&nbsp;<code>"X"</code> 和&nbsp;<code>"^&lt;pow&gt;"</code>.
	<ul>
		<li>例如, 如果&nbsp;<code>power = 0</code> 并且&nbsp;<code>factor = -3</code>, 将有 <code>"-3"</code>。</li>
	</ul>
	</li>
	<li>LHS中的幂应该按 <strong>降序排序</strong>。</li>
</ul>

<p data-group="1-1">编写一个 SQL 查询来构建方程。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Terms 表:
+-------+--------+
| power | factor |
+-------+--------+
| 2     | 1      |
| 1     | -4     |
| 0     | 2      |
+-------+--------+
<strong>输出:</strong> 
+--------------+
| equation     |
+--------------+
| +1X^2-4X+2=0 |
+--------------+
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>输入:</strong> 
Terms 表:
+-------+--------+
| power | factor |
+-------+--------+
| 4     | -4     |
| 2     | 1      |
| 1     | -1     |
+-------+--------+
<strong>输出:</strong> 
+-----------------+
| equation        |
+-----------------+
| -4X^4+1X^2-1X=0 |
+-----------------+
</pre>

<p>&nbsp;</p>

<p><strong>扩展:</strong> 如果幂函数不是主键，但每个幂函数在答案中都是唯一的，那么解决方案中将改变什么?</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
