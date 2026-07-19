---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3994.Minimum%20Adjacent%20Swaps%20to%20Partition%20Array/README_EN.md
---

<!-- problem:start -->

# [3994. Minimum Adjacent Swaps to Partition Array](https://leetcode.com/problems/minimum-adjacent-swaps-to-partition-array)

[中文文档](/solution/3900-3999/3994.Minimum%20Adjacent%20Swaps%20to%20Partition%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and two integers <code>a</code> and <code>b</code> such that <code>a &lt; b</code>.</p>

<p>An array is called <strong>good</strong> if it can be split into three <strong>contiguous</strong> parts, in this order, such that:</p>

<ul>
	<li>Every element in the first part is <strong>less than</strong> <code>a</code>.</li>
	<li>Every element in the second part is <strong>in</strong> the range <code>[a, b]</code> inclusive.</li>
	<li>Every element in the third part is <strong>greater than</strong> <code>b</code>.</li>
</ul>

<p>Any of the three parts <strong>may be</strong> empty.</p>

<p>In one <strong>adjacent swap</strong>, you may swap two <strong>neighboring</strong> elements of <code>nums</code>.</p>

<p>Return the <strong>minimum number</strong> of adjacent swaps required to make <code>nums</code> good. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,2,4,5,6], a = 3, b = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Swap <code>nums[1]</code> and <code>nums[2]</code>. The array becomes <code>[1, 2, 3, 4, 5, 6]</code>.</li>
	<li>This array is good because it can be split into <code>[1, 2]</code>, <code>[3, 4]</code>, and <code>[5, 6]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [9,7,5,3], a = 4, b = 8</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>One sequence of optimal swaps is as follows:</p>

<ul>
	<li>Swap <code>nums[2]</code> and <code>nums[3]</code>. The array becomes <code>[9, 7, 3, 5]</code>.</li>
	<li>Swap <code>nums[1]</code> and <code>nums[2]</code>. The array becomes <code>[9, 3, 7, 5]</code>.</li>
	<li>Swap <code>nums[0]</code> and <code>nums[1]</code>. The array becomes <code>[3, 9, 7, 5]</code>.</li>
	<li>Swap <code>nums[1]</code> and <code>nums[2]</code>. The array becomes <code>[3, 7, 9, 5]</code>.</li>
	<li>Swap <code>nums[2]</code> and <code>nums[3]</code>. The array becomes <code>[3, 7, 5, 9]</code>.</li>
	<li>This array is good because it can be split into <code>[3]</code>, <code>[7, 5]</code>, and <code>[9]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,7,5,9], a = 4, b = 8</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The array is already good. No swaps are needed.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>​​​​​​​1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= a &lt; b &lt;= 10<sup>9</sup>​​​​​​​</code></li>
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
