---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3976.Maximum%20Subarray%20Sum%20After%20Multiplier/README_EN.md
---

<!-- problem:start -->

# [3976. Maximum Subarray Sum After Multiplier](https://leetcode.com/problems/maximum-subarray-sum-after-multiplier)

[中文文档](/solution/3900-3999/3976.Maximum%20Subarray%20Sum%20After%20Multiplier/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and a positive integer <code>k</code>.</p>

<p>You must choose <strong>exactly</strong> one <span data-keyword="subarray-nonempty">subarray</span> of <code>nums</code> and perform <strong>exactly</strong> one of the following operations:</p>

<ol>
	<li>Multiply each number in the chosen subarray by <code>k</code>.</li>
	<li>Divide each number in the chosen subarray by <code>k</code>.
	<ul>
		<li>When dividing a positive number by <code>k</code>, use the <strong>floor</strong> value of the division result.</li>
		<li>When dividing a negative number by <code>k</code>, use the <strong>ceiling</strong> value of the division result.</li>
	</ul>
	</li>
</ol>

<p>Return the <strong>maximum</strong> possible sum of a <strong>non-empty</strong> subarray in the resulting array.</p>

<p>Note that the subarray chosen for the operation and the subarray chosen for the sum may be <strong>different</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,-2,3,4,-5], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">14</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Multiply each number in the subarray <code>[3, 4]</code> by 2.</li>
	<li>This results in <code>nums = [1, -2, 6, 8, -5]</code>.</li>
	<li>The subarray with the largest sum is <code>[6, 8]</code>, so the output is <code>6 + 8 = 14</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-5,-4,-3], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Divide each number in the subarray <code>[-3]</code> by 2.</li>
	<li>This results in <code>nums = [-5, -4, -1]</code>.</li>
	<li>The subarray with the largest sum is <code>[-1]</code>, so the output is -1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
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
