---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3649.Number%20of%20Perfect%20Pairs/README_EN.md
rating: 1715
source: Biweekly Contest 163 Q2
---

<!-- problem:start -->

# [3649. Number of Perfect Pairs](https://leetcode.com/problems/number-of-perfect-pairs)

[中文文档](/solution/3600-3699/3649.Number%20of%20Perfect%20Pairs/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>A pair of indices <code>(i, j)</code> is called <strong>perfect</strong> if the following conditions are satisfied:</p>

<ul>
	<li><code>i &lt; j</code></li>
	<li>Let <code>a = nums[i]</code>, <code>b = nums[j]</code>. Then:
	<ul>
		<li><code>min(|a - b|, |a + b|) &lt;= min(|a|, |b|)</code></li>
		<li><code>max(|a - b|, |a + b|) &gt;= max(|a|, |b|)</code></li>
	</ul>
	</li>
</ul>

<p>Return the number of <strong>distinct</strong> perfect pairs.</p>

<p><strong>Note:</strong> The absolute value <code>|x|</code> refers to the <strong>non-negative</strong> value of <code>x</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 2 perfect pairs:</p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>(i, j)</code></th>
			<th style="border: 1px solid black;"><code>(a, b)</code></th>
			<th style="border: 1px solid black;"><code>min(|a &minus; b|, |a + b|)</code></th>
			<th style="border: 1px solid black;"><code>min(|a|, |b|)</code></th>
			<th style="border: 1px solid black;"><code>max(|a &minus; b|, |a + b|)</code></th>
			<th style="border: 1px solid black;"><code>max(|a|, |b|)</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">(1, 2)</td>
			<td style="border: 1px solid black;">(1, 2)</td>
			<td style="border: 1px solid black;"><code>min(|1 &minus; 2|, |1 + 2|) = 1</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>max(|1 &minus; 2|, |1 + 2|) = 3</code></td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">(2, 3)</td>
			<td style="border: 1px solid black;">(2, 3)</td>
			<td style="border: 1px solid black;"><code>min(|2 &minus; 3|, |2 + 3|) = 1</code></td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>max(|2 &minus; 3|, |2 + 3|) = 5</code></td>
			<td style="border: 1px solid black;">3</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-3,2,-1,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 4 perfect pairs:</p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>(i, j)</code></th>
			<th style="border: 1px solid black;"><code>(a, b)</code></th>
			<th style="border: 1px solid black;"><code>min(|a &minus; b|, |a + b|)</code></th>
			<th style="border: 1px solid black;"><code>min(|a|, |b|)</code></th>
			<th style="border: 1px solid black;"><code>max(|a &minus; b|, |a + b|)</code></th>
			<th style="border: 1px solid black;"><code>max(|a|, |b|)</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">(0, 1)</td>
			<td style="border: 1px solid black;">(-3, 2)</td>
			<td style="border: 1px solid black;"><code>min(|-3 - 2|, |-3 + 2|) = 1</code></td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>max(|-3 - 2|, |-3 + 2|) = 5</code></td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">(0, 3)</td>
			<td style="border: 1px solid black;">(-3, 4)</td>
			<td style="border: 1px solid black;"><code>min(|-3 - 4|, |-3 + 4|) = 1</code></td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;"><code>max(|-3 - 4|, |-3 + 4|) = 7</code></td>
			<td style="border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">(1, 2)</td>
			<td style="border: 1px solid black;">(2, -1)</td>
			<td style="border: 1px solid black;"><code>min(|2 - (-1)|, |2 + (-1)|) = 1</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>max(|2 - (-1)|, |2 + (-1)|) = 3</code></td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">(1, 3)</td>
			<td style="border: 1px solid black;">(2, 4)</td>
			<td style="border: 1px solid black;"><code>min(|2 - 4|, |2 + 4|) = 2</code></td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>max(|2 - 4|, |2 + 4|) = 6</code></td>
			<td style="border: 1px solid black;">4</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,10,100,1000]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no perfect pairs. Thus, the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
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
