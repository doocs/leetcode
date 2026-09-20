---
comments: true
difficulty: Hard
rating: 2630
source: Weekly Contest 519 Q4
---

<!-- problem:start -->

# [4055. Count Shadow Pairs II](https://leetcode.com/problems/count-shadow-pairs-ii)

[中文文档](/solution/4000-4099/4055.Count%20Shadow%20Pairs%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>.</p>

<p>A pair of indices <code>(i, j)</code> is called a <strong>shadow pair</strong> if all of the following conditions are satisfied:</p>

<ul>
	<li><code>0 &lt;= i &lt; j &lt; n</code></li>
	<li><code>nums[i] &lt; nums[j]</code></li>
	<li>There <strong>does not exist</strong> an index <code>k</code> such that <code>i &lt; k &lt; j</code> and <code>nums[i] &lt; nums[k] &lt; nums[j]</code>.</li>
</ul>

<p>Return the total number of <strong>shadow pairs</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,1,4,2,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<table style="border-collapse: collapse; text-align: center; width: 70%;">
	<thead>
		<tr>
			<th style="padding: 8px;"><code>(i, j)</code></th>
			<th style="padding: 8px;"><code>nums[i]</code></th>
			<th style="padding: 8px;"><code>nums[j]</code></th>
			<th style="padding: 8px;">Shadow Pair</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>(0, 2)</td>
			<td>3</td>
			<td>4</td>
			<td><code>nums[1] = 1</code> is not strictly between 3 and 4</td>
		</tr>
		<tr>
			<td>(1, 2)</td>
			<td>1</td>
			<td>4</td>
			<td>No index <code>k</code> exists such that <code>1 &lt; k &lt; 2</code></td>
		</tr>
		<tr>
			<td>(1, 3)</td>
			<td>1</td>
			<td>2</td>
			<td><code>nums[2] = 4</code> is not strictly between 1 and 2</td>
		</tr>
		<tr>
			<td>(2, 4)</td>
			<td>4</td>
			<td>5</td>
			<td><code>nums[3] = 2</code> is not strictly between 4 and 5</td>
		</tr>
		<tr>
			<td>(3, 4)</td>
			<td>2</td>
			<td>5</td>
			<td>No index <code>k</code> exists such that <code>3 &lt; k &lt; 4</code></td>
		</tr>
	</tbody>
</table>
</div>

<p>Thus, the answer is 5.</p>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [6,7,8,9]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<table style="border-collapse: collapse; text-align: center; width: 70%;">
	<thead>
		<tr>
			<th style="padding: 8px;"><code>(i, j)</code></th>
			<th style="padding: 8px;"><code>nums[i]</code></th>
			<th style="padding: 8px;"><code>nums[j]</code></th>
			<th style="padding: 8px;">Shadow Pair</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>(0, 1)</td>
			<td>6</td>
			<td>7</td>
			<td>No index <code>k</code> exists such that <code>0 &lt; k &lt; 1</code></td>
		</tr>
		<tr>
			<td>(1, 2)</td>
			<td>7</td>
			<td>8</td>
			<td>No index <code>k</code> exists such that <code>1 &lt; k &lt; 2</code></td>
		</tr>
		<tr>
			<td>(2, 3)</td>
			<td>8</td>
			<td>9</td>
			<td>No index <code>k</code> exists such that <code>2 &lt; k &lt; 3</code></td>
		</tr>
	</tbody>
</table>

<p>Thus, the answer is 3.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n == nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
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
