---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3592.Inverse%20Coin%20Change/README_EN.md
rating: 1700
source: Weekly Contest 455 Q2
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3592. Inverse Coin Change](https://leetcode.com/problems/inverse-coin-change)

[中文文档](/solution/3500-3599/3592.Inverse%20Coin%20Change/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>1-indexed</strong> integer array <code>numWays</code>, where <code>numWays[i]</code> represents the number of ways to select a total amount <code>i</code> using an <strong>infinite</strong> supply of some <em>fixed</em> coin denominations. Each denomination is a <strong>positive</strong> integer with value <strong>at most</strong> <code>numWays.length</code>.</p>

<p>However, the exact coin denominations have been <em>lost</em>. Your task is to recover the set of denominations that could have resulted in the given <code>numWays</code> array.</p>

<p>Return a <strong>sorted</strong> array containing <strong>unique</strong> integers which represents this set of denominations.</p>

<p>If no such set exists, return an <strong>empty</strong> array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">numWays = [0,1,0,2,0,3,0,4,0,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,4,6]</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Amount</th>
			<th style="border: 1px solid black;">Number of ways</th>
			<th style="border: 1px solid black;">Explanation</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">There is no way to select coins with total value 1.</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">The only way is <code>[2]</code>.</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">There is no way to select coins with total value 3.</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">The ways are <code>[2, 2]</code> and <code>[4]</code>.</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">There is no way to select coins with total value 5.</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">The ways are <code>[2, 2, 2]</code>, <code>[2, 4]</code>, and <code>[6]</code>.</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">There is no way to select coins with total value 7.</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">8</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">The ways are <code>[2, 2, 2, 2]</code>, <code>[2, 2, 4]</code>, <code>[2, 6]</code>, and <code>[4, 4]</code>.</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">9</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">There is no way to select coins with total value 9.</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">10</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">The ways are <code>[2, 2, 2, 2, 2]</code>, <code>[2, 2, 2, 4]</code>, <code>[2, 4, 4]</code>, <code>[2, 2, 6]</code>, and <code>[4, 6]</code>.</td>
		</tr>
	</tbody>
</table>
<strong class="example">Example 2:</strong>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">numWays = [1,2,2,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,5]</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Amount</th>
			<th style="border: 1px solid black;">Number of ways</th>
			<th style="border: 1px solid black;">Explanation</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">The only way is <code>[1]</code>.</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">The ways are <code>[1, 1]</code> and <code>[2]</code>.</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">The ways are <code>[1, 1, 1]</code> and <code>[1, 2]</code>.</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">The ways are <code>[1, 1, 1, 1]</code>, <code>[1, 1, 2]</code>, and <code>[2, 2]</code>.</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">The ways are <code>[1, 1, 1, 1, 1]</code>, <code>[1, 1, 1, 2]</code>, <code>[1, 2, 2]</code>, and <code>[5]</code>.</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">numWays = [1,2,3,4,15]</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<p>No set of denomination satisfies this array.</p>
</div>

<table style="border: 1px solid black;">
</table>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= numWays.length &lt;= 100</code></li>
	<li><code>0 &lt;= numWays[i] &lt;= 2 * 10<sup>8</sup></code></li>
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
