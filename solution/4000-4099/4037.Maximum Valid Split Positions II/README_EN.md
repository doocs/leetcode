---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4037.Maximum%20Valid%20Split%20Positions%20II/README_EN.md
---

<!-- problem:start -->

# [4037. Maximum Valid Split Positions II](https://leetcode.com/problems/maximum-valid-split-positions-ii)

[中文文档](/solution/4000-4099/4037.Maximum%20Valid%20Split%20Positions%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>You may remove <strong>at most one</strong> element from <code>nums</code>. Let <code>arr</code> be the array of remaining elements in their original order, and let <code>m</code> be its length.</p>

<p>A <strong>split position</strong> <code>i</code> of <code>arr</code> is <strong>valid</strong> if:</p>

<ul>
	<li><code>0 &lt;= i &lt; m - 1</code>, and</li>
	<li><code>gcd(arr[0..i]) == gcd(arr[i + 1..m - 1])</code>.</li>
</ul>

<p>An array of length 1 has no valid split positions.</p>

<p>The <strong>score</strong> of <code>arr</code> is the number of valid split positions in it.</p>

<p>Return the <strong>maximum possible score</strong> of <code>arr</code>.</p>

<p>Here, <code>gcd(a)</code> denotes the <strong>greatest common divisor</strong> of all elements in the array <code>a</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [10,30,15,10]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal solution is to remove <code>nums[2] = 15</code>. Then <code>arr = [10, 30, 10]</code>.</p>

<p>The split positions are:</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse; text-align:center;">
	<tbody>
		<tr>
			<th>Split Position <code>i</code></th>
			<th><code>gcd(arr[0..i])</code></th>
			<th><code>gcd(arr[i + 1..m - 1])</code></th>
		</tr>
		<tr>
			<td>0</td>
			<td>10</td>
			<td>10</td>
		</tr>
		<tr>
			<td>1</td>
			<td>10</td>
			<td>10</td>
		</tr>
	</tbody>
</table>

<p>All split positions are valid. Thus, the answer is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,10,14]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal solution is to not remove any element. Then <code>arr = [2, 10, 14]</code>.</p>

<p>The split positions are:</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse; text-align:center;">
	<tbody>
		<tr>
			<th>Split Position <code>i</code></th>
			<th><code>gcd(arr[0..i])</code></th>
			<th><code>gcd(arr[i + 1..m - 1])</code></th>
		</tr>
		<tr>
			<td>0</td>
			<td>2</td>
			<td>2</td>
		</tr>
		<tr>
			<td>1</td>
			<td>2</td>
			<td>14</td>
		</tr>
	</tbody>
</table>

<p>Only the split position at index 0 is valid. Thus, the answer is 1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The only remaining array that has a split position is <code>arr = [2, 4]</code>.</p>

<p>The split positions are:</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse; text-align:center;">
	<tbody>
		<tr>
			<th>Split Position <code>i</code></th>
			<th><code>gcd(arr[0..i])</code></th>
			<th><code>gcd(arr[i + 1..m - 1])</code></th>
		</tr>
		<tr>
			<td>0</td>
			<td>2</td>
			<td>4</td>
		</tr>
	</tbody>
</table>

<p>There are no valid split positions. Thus, the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code>​​​​​​​</li>
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
