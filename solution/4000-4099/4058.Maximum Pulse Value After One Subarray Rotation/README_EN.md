---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4058. Maximum Pulse Value After One Subarray Rotation](https://leetcode.com/problems/maximum-pulse-value-after-one-subarray-rotation)

[中文文档](/solution/4000-4099/4058.Maximum%20Pulse%20Value%20After%20One%20Subarray%20Rotation/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>.</p>

<p>Define the <strong>pulse value</strong> of an integer array <code>arr</code> as the <strong>alternating sum</strong> starting at index 0: <code>pulse(arr) = arr[0] - arr[1] + arr[2] - arr[3] + ...</code>.</p>

<p>You may perform <strong>at most</strong> one operation on <code>nums</code>:</p>

<ul>
	<li>Choose two indices <code>l</code> and <code>r</code> such that <code>0 &lt;= l &lt; r &lt; n</code>.</li>
	<li><strong>Left-rotate</strong> the <span data-keyword="subarray-nonempty">subarray</span> <code>nums[l..r]</code> by <strong>exactly</strong> one position. For example, <code>[a, b, c, d]</code> becomes <code>[b, c, d, a]</code>.</li>
</ul>

<p>Return the <strong>maximum pulse value</strong> that can be obtained after performing <strong>at most</strong> one such operation.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,5,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The original pulse value is <code>1 - 5 + 2 = -2</code>.</li>
	<li>Rotate the subarray <code>nums[0..1]</code> from <code>[1, 5]</code> to <code>[5, 1]</code>.</li>
	<li>The resulting array is <code>[5, 1, 2]</code> and its pulse value is <code>5 - 1 + 2 = 6</code>, which is the maximum possible.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [6,4,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The original pulse value is <code>6 - 4 + 3 = 5</code>.</li>
	<li>Rotate the subarray <code>nums[1..2]</code> from <code>[4, 3]</code> to <code>[3, 4]</code>.</li>
	<li>The resulting array is <code>[6, 3, 4]</code> and its pulse value is <code>6 - 3 + 4 = 7</code>, which is the maximum possible.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [9,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The original pulse value is <code>9 - 7 = 2</code>, which is already maximum. Thus, no rotation is required.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
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
