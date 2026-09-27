---
comments: true
difficulty: Easy
---

<!-- problem:start -->

# [4065. Rearrange Array by Removing Distinct Values](https://leetcode.com/problems/rearrange-array-by-removing-distinct-values)

[中文文档](/solution/4000-4099/4065.Rearrange%20Array%20by%20Removing%20Distinct%20Values/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>You start with an <strong>empty</strong> array <code>ans</code>. Repeat the following operation until <code>nums</code> is <strong>empty</strong>:</p>

<ul>
	<li>Identify <strong>all</strong> <strong>distinct</strong> values currently present in <code>nums</code>.</li>
	<li>Remove <strong>one</strong> occurrence of every <strong>distinct</strong> value currently in <code>nums</code>, and append those values to <code>ans</code> in <strong>ascending</strong> order.</li>
</ul>

<p>Return the array <code>ans</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,1,3,2,1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,3,1,3,3]</span></p>

<p><strong>Explanation:</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th scope="col" style="text-align:center;">Operation</th>
			<th scope="col" style="text-align:center;">Appended to <code>ans</code></th>
			<th scope="col" style="text-align:center;"><code>nums</code> after</th>
			<th scope="col" style="text-align:center;"><code>ans</code> after</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">1, 2, 3</td>
			<td style="text-align:center;"><code>[3, 1, 3]</code></td>
			<td style="text-align:center;"><code>[1, 2, 3]</code></td>
		</tr>
		<tr>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">1, 3</td>
			<td style="text-align:center;"><code>[3]</code></td>
			<td style="text-align:center;"><code>[1, 2, 3, 1, 3]</code></td>
		</tr>
		<tr>
			<td style="text-align:center;">3</td>
			<td style="text-align:center;">3</td>
			<td style="text-align:center;"><code>[]</code></td>
			<td style="text-align:center;"><code>[1, 2, 3, 1, 3, 3]</code></td>
		</tr>
	</tbody>
</table>

<p><code>nums</code> is now empty, so the answer is <code>[1, 2, 3, 1, 3, 3]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7,7,4,4,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">[4,7,4,7,4]</span></p>

<p><strong>Explanation:</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th scope="col" style="text-align:center;">Operation</th>
			<th scope="col" style="text-align:center;">Appended to <code>ans</code></th>
			<th scope="col" style="text-align:center;"><code>nums</code> after</th>
			<th scope="col" style="text-align:center;"><code>ans</code> after</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">4, 7</td>
			<td style="text-align:center;"><code>[7, 4, 4]</code></td>
			<td style="text-align:center;"><code>[4, 7]</code></td>
		</tr>
		<tr>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">4, 7</td>
			<td style="text-align:center;"><code>[4]</code></td>
			<td style="text-align:center;"><code>[4, 7, 4, 7]</code></td>
		</tr>
		<tr>
			<td style="text-align:center;">3</td>
			<td style="text-align:center;">4</td>
			<td style="text-align:center;"><code>[]</code></td>
			<td style="text-align:center;"><code>[4, 7, 4, 7, 4]</code></td>
		</tr>
	</tbody>
</table>

<p><code>nums</code> is now empty, so the answer is <code>[4, 7, 4, 7, 4]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
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
