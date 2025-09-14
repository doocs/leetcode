---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3685.Subsequence%20Sum%20After%20Capping%20Elements/README_EN.md
---

<!-- problem:start -->

# [3685. Subsequence Sum After Capping Elements](https://leetcode.com/problems/subsequence-sum-after-capping-elements)

[中文文档](/solution/3600-3699/3685.Subsequence%20Sum%20After%20Capping%20Elements/README.md)

## Description

<!-- description:start -->

<p data-end="320" data-start="259">You are given an integer array <code>nums</code> of size <code>n</code> and a positive integer <code>k</code>.</p>

<p data-end="294" data-start="163">An array <strong>capped</strong> by value <code>x</code> is obtained by replacing every element <code>nums[i]</code> with <code>min(nums[i], x)</code>.</p>

<p data-end="511" data-start="296">For each integer <code data-end="316" data-start="313">x</code> from 1 to <code data-end="332" data-start="329">n</code>, determine whether it is possible to choose a <strong><span data-keyword="subsequence-array-nonempty">subsequence</span></strong> from the array capped by <code>x</code> such that the sum of the chosen elements is <strong>exactly</strong> <code data-end="510" data-start="507">k</code>.</p>

<p data-end="788" data-start="649">Return a <strong>0-indexed</strong> boolean array <code data-end="680" data-start="672">answer</code> of size <code data-end="694" data-start="691">n</code>, where <code data-end="713" data-start="702">answer[i]</code> is <code data-end="723" data-start="717">true</code> if it is possible when using <code data-end="764" data-start="753">x = i + 1</code>, and <code data-end="777" data-start="770">false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,3,2,4], k = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">[false,false,true,true]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>x = 1</code>, the capped array is <code>[1, 1, 1, 1]</code>. Possible sums are <code>1, 2, 3, 4</code>, so it is impossible to form a sum of <code>5</code>.</li>
	<li>For <code>x = 2</code>, the capped array is <code>[2, 2, 2, 2]</code>. Possible sums are <code>2, 4, 6, 8</code>, so it is impossible to form a sum of <code>5</code>.</li>
	<li>For <code>x = 3</code>, the capped array is <code>[3, 3, 2, 3]</code>. A subsequence <code>[2, 3]</code> sums to <code>5</code>, so it is possible.</li>
	<li>For <code>x = 4</code>, the capped array is <code>[4, 3, 2, 4]</code>. A subsequence <code>[3, 2]</code> sums to <code>5</code>, so it is possible.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,5], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[true,true,true,true,true]</span></p>

<p><strong>Explanation:</strong></p>

<p>For every value of <code>x</code>, it is always possible to select a subsequence from the capped array that sums exactly to <code>3</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 4000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= n</code></li>
	<li><code>1 &lt;= k &lt;= 4000</code></li>
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
