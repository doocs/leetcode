---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3693.Climbing%20Stairs%20II/README_EN.md
rating: 1560
source: Biweekly Contest 166 Q2
---

<!-- problem:start -->

# [3693. Climbing Stairs II](https://leetcode.com/problems/climbing-stairs-ii)

[中文文档](/solution/3600-3699/3693.Climbing%20Stairs%20II/README.md)

## Description

<!-- description:start -->

<p>You are climbing a staircase with <code>n + 1</code> steps, numbered from 0 to <code>n</code>.</p>

<p>You are also given a <strong>1-indexed</strong> integer array <code>costs</code> of length <code>n</code>, where <code>costs[i]</code> is the cost of step <code>i</code>.</p>

<p>From step <code>i</code>, you can jump <strong>only</strong> to step <code>i + 1</code>, <code>i + 2</code>, or <code>i + 3</code>. The cost of jumping from step <code>i</code> to step <code>j</code> is defined as: <code>costs[j] + (j - i)<sup>2</sup></code></p>

<p>You start from step 0 with <code>cost = 0</code>.</p>

<p>Return the <strong>minimum</strong> total cost to reach step <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, costs = [1,2,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">13</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal path is <code>0 &rarr; 1 &rarr; 2 &rarr; 4</code></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Jump</th>
			<th style="border: 1px solid black;">Cost Calculation</th>
			<th style="border: 1px solid black;">Cost</th>
		</tr>
	</tbody>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0 &rarr; 1</td>
			<td style="border: 1px solid black;"><code>costs[1] + (1 - 0)<sup>2</sup> = 1 + 1</code></td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1 &rarr; 2</td>
			<td style="border: 1px solid black;"><code>costs[2] + (2 - 1)<sup>2</sup> = 2 + 1</code></td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2 &rarr; 4</td>
			<td style="border: 1px solid black;"><code>costs[4] + (4 - 2)<sup>2</sup> = 4 + 4</code></td>
			<td style="border: 1px solid black;">8</td>
		</tr>
	</tbody>
</table>

<p>Thus, the minimum total cost is <code>2 + 3 + 8 = 13</code></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, costs = [5,1,6,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">11</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal path is <code>0 &rarr; 2 &rarr; 4</code></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Jump</th>
			<th style="border: 1px solid black;">Cost Calculation</th>
			<th style="border: 1px solid black;">Cost</th>
		</tr>
	</tbody>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0 &rarr; 2</td>
			<td style="border: 1px solid black;"><code>costs[2] + (2 - 0)<sup>2</sup> = 1 + 4</code></td>
			<td style="border: 1px solid black;">5</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2 &rarr; 4</td>
			<td style="border: 1px solid black;"><code>costs[4] + (4 - 2)<sup>2</sup> = 2 + 4</code></td>
			<td style="border: 1px solid black;">6</td>
		</tr>
	</tbody>
</table>

<p>Thus, the minimum total cost is <code>5 + 6 = 11</code></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, costs = [9,8,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal path is <code>0 &rarr; 3</code> with total cost = <code>costs[3] + (3 - 0)<sup>2</sup> = 3 + 9 = 12</code></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == costs.length &lt;= 10<sup>5</sup>​​​​​​​</code></li>
	<li><code>1 &lt;= costs[i] &lt;= 10<sup>4</sup></code></li>
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
