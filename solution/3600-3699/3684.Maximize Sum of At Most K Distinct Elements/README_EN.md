---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3684.Maximize%20Sum%20of%20At%20Most%20K%20Distinct%20Elements/README_EN.md
---

<!-- problem:start -->

# [3684. Maximize Sum of At Most K Distinct Elements](https://leetcode.com/problems/maximize-sum-of-at-most-k-distinct-elements)

[中文文档](/solution/3600-3699/3684.Maximize%20Sum%20of%20At%20Most%20K%20Distinct%20Elements/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>positive</strong> integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>Choose at most <code>k</code> elements from <code>nums</code> so that their sum is maximized. However, the chosen numbers must be <strong>distinct</strong>.</p>

<p>Return an array containing the chosen numbers in <strong>strictly descending</strong> order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [84,93,100,77,90], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[100,93,90]</span></p>

<p><strong>Explanation:</strong></p>

<p>The maximum sum is 283, which is attained by choosing 93, 100 and 90. We rearrange them in strictly descending order as <code>[100, 93, 90]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [84,93,100,77,93], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[100,93,84]</span></p>

<p><strong>Explanation:</strong></p>

<p>The maximum sum is 277, which is attained by choosing 84, 93 and 100. We rearrange them in strictly descending order as <code>[100, 93, <span class="example-io">84</span>]</code>. We cannot choose 93, 100 and 93 because the chosen numbers must be distinct.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1,2,2,2], k = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>The maximum sum is 3, which is attained by choosing 1 and 2. We rearrange them in strictly descending order as <code>[2, 1]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
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
