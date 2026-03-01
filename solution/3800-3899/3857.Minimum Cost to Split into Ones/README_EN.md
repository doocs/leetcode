---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3857.Minimum%20Cost%20to%20Split%20into%20Ones/README_EN.md
---

<!-- problem:start -->

# [3857. Minimum Cost to Split into Ones](https://leetcode.com/problems/minimum-cost-to-split-into-ones)

[中文文档](/solution/3800-3899/3857.Minimum%20Cost%20to%20Split%20into%20Ones/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>.</p>

<p>In one operation, you may split an integer <code>x</code> into two positive integers <code>a</code> and <code>b</code> such that <code>a + b = x</code>.</p>

<p>The cost of this operation is <code>a * b</code>.</p>

<p>Return an integer denoting the <strong>minimum</strong> total cost required to split the integer <code>n</code> into <code>n</code> ones.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal set of operations is:</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;"><code>x</code></th>
			<th style="border: 1px solid black;"><code>a</code></th>
			<th style="border: 1px solid black;"><code>b</code></th>
			<th style="border: 1px solid black;"><code>a + b</code></th>
			<th style="border: 1px solid black;"><code>a * b</code></th>
			<th style="border: 1px solid black;">Cost</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
	</tbody>
</table>

<p>Thus, the minimum total cost is <code>2 + 1 = 3</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<div class="example-block">
<p>One optimal set of operations is:</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;"><code>x</code></th>
			<th style="border: 1px solid black;"><code>a</code></th>
			<th style="border: 1px solid black;"><code>b</code></th>
			<th style="border: 1px solid black;"><code>a + b</code></th>
			<th style="border: 1px solid black;"><code>a * b</code></th>
			<th style="border: 1px solid black;">Cost</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
	</tbody>
</table>

<p>Thus, the minimum total cost is <code>4 + 1 + 1 = 6</code>.</p>
</div>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 500</code></li>
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
