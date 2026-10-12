---
comments: true
difficulty: Easy
---

<!-- problem:start -->

# [4074. Maximum Product Pair With Target Sum](https://leetcode.com/problems/maximum-product-pair-with-target-sum)

[中文文档](/solution/4000-4099/4074.Maximum%20Product%20Pair%20With%20Target%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>target</code>.</p>

<p>A pair of <strong>distinct</strong> indices <code>(i, j)</code> is <strong>valid</strong> if:</p>

<ul>
	<li><code>nums[i] + nums[j] == target</code></li>
	<li><code>nums[i] &gt; nums[j]</code></li>
</ul>

<p>Return a <strong>valid</strong> pair <code>[i, j]</code> whose <strong>product</strong> <code>nums[i] * nums[j]</code> is <strong>maximum</strong> among all valid pairs. If no valid pair exists, return <code>[-1, -1]</code>.</p>

<p>If multiple valid pairs achieve the <strong>maximum</strong> product, you may return <strong>any</strong> of them.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4], target = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 2 valid pairs:</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th scope="col" style="text-align:center;">No.</th>
			<th scope="col" style="text-align:center;"><code>(i, j)</code></th>
			<th scope="col" style="text-align:center;"><code>nums[i]</code></th>
			<th scope="col" style="text-align:center;"><code>nums[j]</code></th>
			<th scope="col" style="text-align:center;">Sum</th>
			<th scope="col" style="text-align:center;">Product</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">(3, 0)</td>
			<td style="text-align:center;">4</td>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">5</td>
			<td style="text-align:center;">4</td>
		</tr>
		<tr>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">(2, 1)</td>
			<td style="text-align:center;">3</td>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">5</td>
			<td style="text-align:center;">6</td>
		</tr>
	</tbody>
</table>

<p>Both pairs sum to 5 and satisfy <code>nums[i] &gt; nums[j]</code>. The second pair has the larger product, <code>3 * 2 = 6</code>, so the answer is <code>[2, 1]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-3,-1,4,2], target = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 2 valid pairs:</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th scope="col" style="text-align:center;">No.</th>
			<th scope="col" style="text-align:center;"><code>(i, j)</code></th>
			<th scope="col" style="text-align:center;"><code>nums[i]</code></th>
			<th scope="col" style="text-align:center;"><code>nums[j]</code></th>
			<th scope="col" style="text-align:center;">Sum</th>
			<th scope="col" style="text-align:center;">Product</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">(2, 0)</td>
			<td style="text-align:center;">4</td>
			<td style="text-align:center;">-3</td>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">-12</td>
		</tr>
		<tr>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">(3, 1)</td>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">-1</td>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">-2</td>
		</tr>
	</tbody>
</table>

<p>Since <code>-2 &gt; -12</code>, the pair at indices <code>(3, 1)</code> is chosen and the answer is <code>[3, 1]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,3,5], target = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1,-1]</span></p>

<p><strong>Explanation:</strong></p>

<p><strong>​​​​​​​</strong>No valid pair exists, since <code>nums[0]</code> and <code>nums[1]</code> sum to 6 but are equal.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-100 &lt;= nums[i], target &lt;= 100</code>​​​​​​​</li>
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
