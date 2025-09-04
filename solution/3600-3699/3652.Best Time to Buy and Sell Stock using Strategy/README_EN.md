---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3652.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20using%20Strategy/README_EN.md
rating: 1556
source: Weekly Contest 463 Q1
tags:
    - Array
    - Prefix Sum
    - Sliding Window
---

<!-- problem:start -->

# [3652. Best Time to Buy and Sell Stock using Strategy](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-using-strategy)

[中文文档](/solution/3600-3699/3652.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20using%20Strategy/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>prices</code> and <code>strategy</code>, where:</p>

<ul>
	<li><code>prices[i]</code> is the price of a given stock on the <code>i<sup>th</sup></code> day.</li>
	<li><code>strategy[i]</code> represents a trading action on the <code>i<sup>th</sup></code> day, where:
	<ul>
		<li><code>-1</code> indicates buying one unit of the stock.</li>
		<li><code>0</code> indicates holding the stock.</li>
		<li><code>1</code> indicates selling one unit of the stock.</li>
	</ul>
	</li>
</ul>

<p>You are also given an <strong>even</strong> integer <code>k</code>, and may perform <strong>at most one</strong> modification to <code>strategy</code>. A modification consists of:</p>

<ul>
	<li>Selecting exactly <code>k</code> <strong>consecutive</strong> elements in <code>strategy</code>.</li>
	<li>Set the <strong>first</strong> <code>k / 2</code> elements to <code>0</code> (hold).</li>
	<li>Set the <strong>last</strong> <code>k / 2</code> elements to <code>1</code> (sell).</li>
</ul>

<p>The <strong>profit</strong> is defined as the <strong>sum</strong> of <code>strategy[i] * prices[i]</code> across all days.</p>

<p>Return the <strong>maximum</strong> possible profit you can achieve.</p>

<p><strong>Note:</strong> There are no constraints on budget or stock ownership, so all buy and sell operations are feasible regardless of past actions.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">prices = [4,2,8], strategy = [-1,0,1], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Modification</th>
			<th style="border: 1px solid black;">Strategy</th>
			<th style="border: 1px solid black;">Profit Calculation</th>
			<th style="border: 1px solid black;">Profit</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">Original</td>
			<td style="border: 1px solid black;">[-1, 0, 1]</td>
			<td style="border: 1px solid black;">(-1 &times; 4) + (0 &times; 2) + (1 &times; 8) = -4 + 0 + 8</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">Modify [0, 1]</td>
			<td style="border: 1px solid black;">[0, 1, 1]</td>
			<td style="border: 1px solid black;">(0 &times; 4) + (1 &times; 2) + (1 &times; 8) = 0 + 2 + 8</td>
			<td style="border: 1px solid black;">10</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">Modify [1, 2]</td>
			<td style="border: 1px solid black;">[-1, 0, 1]</td>
			<td style="border: 1px solid black;">(-1 &times; 4) + (0 &times; 2) + (1 &times; 8) = -4 + 0 + 8</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
	</tbody>
</table>

<p>Thus, the maximum possible profit is 10, which is achieved by modifying the subarray <code>[0, 1]</code>​​​​​​​.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">prices = [5,4,3], strategy = [1,1,0], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<div class="example-block">
<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Modification</th>
			<th style="border: 1px solid black;">Strategy</th>
			<th style="border: 1px solid black;">Profit Calculation</th>
			<th style="border: 1px solid black;">Profit</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">Original</td>
			<td style="border: 1px solid black;">[1, 1, 0]</td>
			<td style="border: 1px solid black;">(1 &times; 5) + (1 &times; 4) + (0 &times; 3) = 5 + 4 + 0</td>
			<td style="border: 1px solid black;">9</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">Modify [0, 1]</td>
			<td style="border: 1px solid black;">[0, 1, 0]</td>
			<td style="border: 1px solid black;">(0 &times; 5) + (1 &times; 4) + (0 &times; 3) = 0 + 4 + 0</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">Modify [1, 2]</td>
			<td style="border: 1px solid black;">[1, 0, 1]</td>
			<td style="border: 1px solid black;">(1 &times; 5) + (0 &times; 4) + (1 &times; 3) = 5 + 0 + 3</td>
			<td style="border: 1px solid black;">8</td>
		</tr>
	</tbody>
</table>

<p>Thus, the maximum possible profit is 9, which is achieved without any modification.</p>
</div>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= prices.length == strategy.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>-1 &lt;= strategy[i] &lt;= 1</code></li>
	<li><code>2 &lt;= k &lt;= prices.length</code></li>
	<li><code>k</code> is even</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

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
