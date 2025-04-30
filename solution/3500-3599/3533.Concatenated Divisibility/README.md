---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3533.Concatenated%20Divisibility/README.md
---

<!-- problem:start -->

# [3533. 判断连接可整除性](https://leetcode.cn/problems/concatenated-divisibility)

[English Version](/solution/3500-3599/3533.Concatenated%20Divisibility/README_EN.md)

## 题目描述

<!-- description:start -->

<p data-end="378" data-start="31">给你一个正整数数组 <code data-end="85" data-start="79">nums</code> 和一个正整数 <code data-end="112" data-start="109">k</code>。</p>

<p data-end="378" data-start="31">当&nbsp;<code data-end="137" data-start="131">nums</code>&nbsp;的一个 <span data-keyword="permutation-array">排列</span> 中的所有数字，按照排列顺序&nbsp;<strong data-end="183" data-start="156">连接其十进制表示&nbsp;</strong>后形成的数可以&nbsp;<strong>被</strong> <code data-end="359" data-start="356">k</code>&nbsp; 整除时，我们称该排列形成了一个&nbsp;<strong>可整除连接&nbsp;</strong>。</p>

<p data-end="561" data-start="380">返回能够形成&nbsp;<strong>可整除连接 </strong>且&nbsp;<strong><span data-keyword="lexicographically-smaller-string">字典序</span> 最小 </strong>的排列（按整数列表的形式表示）。如果不存在这样的排列，返回一个空列表。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [3,12,45], k = 5</span></p>

<p><strong>输出:</strong> <span class="example-io">[3,12,45]</span></p>

<p><strong>解释:</strong></p>

<table data-end="896" data-start="441" node="[object Object]" style="border: 1px solid black;">
	<thead data-end="497" data-start="441">
		<tr data-end="497" data-start="441">
			<th data-end="458" data-start="441" style="border: 1px solid black;">排列</th>
			<th data-end="479" data-start="458" style="border: 1px solid black;">连接后的值</th>
			<th data-end="497" data-start="479" style="border: 1px solid black;">是否能被 5 整除</th>
		</tr>
	</thead>
	<tbody data-end="896" data-start="555">
		<tr data-end="611" data-start="555">
			<td style="border: 1px solid black;">[3, 12, 45]</td>
			<td style="border: 1px solid black;">31245</td>
			<td style="border: 1px solid black;">是</td>
		</tr>
		<tr data-end="668" data-start="612">
			<td style="border: 1px solid black;">[3, 45, 12]</td>
			<td style="border: 1px solid black;">34512</td>
			<td style="border: 1px solid black;">否</td>
		</tr>
		<tr data-end="725" data-start="669">
			<td style="border: 1px solid black;">[12, 3, 45]</td>
			<td style="border: 1px solid black;">12345</td>
			<td style="border: 1px solid black;">是</td>
		</tr>
		<tr data-end="782" data-start="726">
			<td style="border: 1px solid black;">[12, 45, 3]</td>
			<td style="border: 1px solid black;">12453</td>
			<td style="border: 1px solid black;">否</td>
		</tr>
		<tr data-end="839" data-start="783">
			<td style="border: 1px solid black;">[45, 3, 12]</td>
			<td style="border: 1px solid black;">45312</td>
			<td style="border: 1px solid black;">否</td>
		</tr>
		<tr data-end="896" data-start="840">
			<td style="border: 1px solid black;">[45, 12, 3]</td>
			<td style="border: 1px solid black;">45123</td>
			<td style="border: 1px solid black;">否</td>
		</tr>
	</tbody>
</table>

<p data-end="1618" data-start="1525">可以形成可整除连接且字典序最小的排列是 <code>[3,12,45]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [10,5], k = 10</span></p>

<p><strong>输出:</strong> <span class="example-io">[5,10]</span></p>

<p><strong>解释:</strong></p>

<table data-end="1421" data-start="1200" node="[object Object]" style="border: 1px solid black;">
	<thead data-end="1255" data-start="1200">
		<tr data-end="1255" data-start="1200">
			<th data-end="1216" data-start="1200" style="border: 1px solid black;">排列</th>
			<th data-end="1237" data-start="1216" style="border: 1px solid black;">连接后的值</th>
			<th data-end="1255" data-start="1237" style="border: 1px solid black;">是否能被 10 整除</th>
		</tr>
	</thead>
	<tbody data-end="1421" data-start="1312">
		<tr data-end="1366" data-start="1312">
			<td style="border: 1px solid black;">[5, 10]</td>
			<td style="border: 1px solid black;">510</td>
			<td style="border: 1px solid black;">是</td>
		</tr>
		<tr data-end="1421" data-start="1367">
			<td style="border: 1px solid black;">[10, 5]</td>
			<td style="border: 1px solid black;">105</td>
			<td style="border: 1px solid black;">否</td>
		</tr>
	</tbody>
</table>

<p data-end="2011" data-start="1921">可以形成可整除连接且字典序最小的排列是 <code>[5,10]</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [1,2,3], k = 5</span></p>

<p><strong>输出:</strong> <span class="example-io">[]</span></p>

<p><strong>解释:</strong></p>

<p>由于不存在任何可以形成有效可整除连接的排列，因此返回空列表。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 13</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
