---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3449.Maximize%20the%20Minimum%20Game%20Score/README_EN.md
tags:
    - Greedy
    - Array
    - Binary Search
---

<!-- problem:start -->

# [3449. Maximize the Minimum Game Score](https://leetcode.com/problems/maximize-the-minimum-game-score)

[中文文档](/solution/3400-3499/3449.Maximize%20the%20Minimum%20Game%20Score/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>points</code> of size <code>n</code> and an integer <code>m</code>. There is another array <code>gameScore</code> of size <code>n</code>, where <code>gameScore[i]</code> represents the score achieved at the <code>i<sup>th</sup></code> game. Initially, <code>gameScore[i] == 0</code> for all <code>i</code>.</p>

<p>You start at index -1, which is outside the array (before the first position at index 0). You can make <strong>at most</strong> <code>m</code> moves. In each move, you can either:</p>

<ul>
	<li>Increase the index by 1 and add <code>points[i]</code> to <code>gameScore[i]</code>.</li>
	<li>Decrease the index by 1 and add <code>points[i]</code> to <code>gameScore[i]</code>.</li>
</ul>

<p><strong>Note</strong> that the index must always remain within the bounds of the array after the first move.</p>

<p>Return the <strong>maximum possible minimum</strong> value in <code>gameScore</code> after <strong>at most</strong> <code>m</code> moves.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">points = [2,4], m = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Initially, index <code>i = -1</code> and <code>gameScore = [0, 0]</code>.</p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Move</th>
			<th style="border: 1px solid black;">Index</th>
			<th style="border: 1px solid black;">gameScore</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">Increase <code>i</code></td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;"><code>[2, 0]</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">Increase <code>i</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>[2, 4]</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">Decrease <code>i</code></td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;"><code>[4, 4]</code></td>
		</tr>
	</tbody>
</table>

<p>The minimum value in <code>gameScore</code> is 4, and this is the maximum possible minimum among all configurations. Hence, 4 is the output.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">points = [1,2,3], m = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Initially, index <code>i = -1</code> and <code>gameScore = [0, 0, 0]</code>.</p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Move</th>
			<th style="border: 1px solid black;">Index</th>
			<th style="border: 1px solid black;">gameScore</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">Increase <code>i</code></td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;"><code>[1, 0, 0]</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">Increase <code>i</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>[1, 2, 0]</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">Decrease <code>i</code></td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;"><code>[2, 2, 0]</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">Increase <code>i</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>[2, 4, 0]</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">Increase <code>i</code></td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>[2, 4, 3]</code></td>
		</tr>
	</tbody>
</table>

<p>The minimum value in <code>gameScore</code> is 2, and this is the maximum possible minimum among all configurations. Hence, 2 is the output.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == points.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= points[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= m &lt;= 10<sup>9</sup></code></li>
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
