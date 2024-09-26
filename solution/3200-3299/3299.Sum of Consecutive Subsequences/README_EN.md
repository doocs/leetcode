---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3299.Sum%20of%20Consecutive%20Subsequences/README_EN.md
---

<!-- problem:start -->

# [3299. Sum of Consecutive Subsequences 🔒](https://leetcode.com/problems/sum-of-consecutive-subsequences)

[中文文档](/solution/3200-3299/3299.Sum%20of%20Consecutive%20Subsequences/README.md)

## Description

<!-- description:start -->

<p>We call an array <code>arr</code> of length <code>n</code> <strong>consecutive</strong> if one of the following holds:</p>

<ul>
	<li><code>arr[i] - arr[i - 1] == 1</code> for <em>all</em> <code>1 &lt;= i &lt; n</code>.</li>
	<li><code>arr[i] - arr[i - 1] == -1</code> for <em>all</em> <code>1 &lt;= i &lt; n</code>.</li>
</ul>

<p>The <strong>value</strong> of an array is the sum of its elements.</p>

<p>For example, <code>[3, 4, 5]</code> is a consecutive array of value 12 and <code>[9, 8]</code> is another of value 17. While <code>[3, 4, 3]</code> and <code>[8, 6]</code> are not consecutive.</p>

<p>Given an array of integers <code>nums</code>, return the <em>sum</em> of the <strong>values</strong> of all <strong>consecutive </strong><em>non-empty</em> <span data-keyword="subsequence-array">subsequences</span>.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9 </sup>+ 7.</code></p>

<p><strong>Note</strong> that an array of length 1 is also considered consecutive.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>The consecutive subsequences are: <code>[1]</code>, <code>[2]</code>, <code>[1, 2]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,4,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">31</span></p>

<p><strong>Explanation:</strong></p>

<p>The consecutive subsequences are: <code>[1]</code>, <code>[4]</code>, <code>[2]</code>, <code>[3]</code>, <code>[1, 2]</code>, <code>[2, 3]</code>, <code>[4, 3]</code>, <code>[1, 2, 3]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
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
