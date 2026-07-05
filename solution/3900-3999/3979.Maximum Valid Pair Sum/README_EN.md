---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3979.Maximum%20Valid%20Pair%20Sum/README_EN.md
---

<!-- problem:start -->

# [3979. Maximum Valid Pair Sum](https://leetcode.com/problems/maximum-valid-pair-sum)

[中文文档](/solution/3900-3999/3979.Maximum%20Valid%20Pair%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and an integer <code>k</code>.</p>

<p>A pair of indices <code>(i, j)</code> is called <strong>valid</strong> if:</p>

<ul>
	<li><code>0 &lt;= i &lt; j &lt; n</code></li>
	<li><code>j - i &gt;= k</code></li>
</ul>

<p>Return the <strong>maximum</strong> value of <code>nums[i] + nums[j]</code> among all valid pairs.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,5,2,8], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">13</span></p>

<p><strong>Explanation:</strong></p>

<p>The valid pairs are:</p>

<ul>
	<li><code>(0, 2)</code>: <code>nums[0] + nums[2] = 6</code></li>
	<li><code>(0, 3)</code>: <code>nums[0] + nums[3] = 3</code></li>
	<li><code>(0, 4)</code>: <code>nums[0] + nums[4] = 9</code></li>
	<li><code>(1, 3)</code>: <code>nums[1] + nums[3] = 5</code></li>
	<li><code>(1, 4)</code>: <code>nums[1] + nums[4] = 11</code></li>
	<li><code>(2, 4)</code>: <code>nums[2] + nums[4] = 13</code></li>
</ul>

<p>Thus, the answer is 13.​​​​​​​</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,1,9], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">14</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Since <code>k = 1</code>, every pair is valid.</li>
	<li>The maximum value is obtained from a pair <code>(0, 2)</code>​​​​​​​, which is <code>nums[0] + nums[2] = 5 + 9 = 14</code>.</li>
	<li>Thus, the answer is 14.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= n - 1</code></li>
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
