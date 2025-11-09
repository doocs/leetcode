---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3743.Maximize%20Cyclic%20Partition%20Score/README_EN.md
---

<!-- problem:start -->

# [3743. Maximize Cyclic Partition Score](https://leetcode.com/problems/maximize-cyclic-partition-score)

[中文文档](/solution/3700-3799/3743.Maximize%20Cyclic%20Partition%20Score/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>cyclic</strong> array <code>nums</code> and an integer <code>k</code>.</p>

<p><strong>Partition</strong> <code>nums</code> into <strong>at most</strong> <code>k</code><strong> </strong><span data-keyword="subarray-nonempty">subarrays</span>. As <code>nums</code> is cyclic, these subarrays may wrap around from the end of the array back to the beginning.</p>

<p>The <strong>range</strong> of a subarray is the difference between its <strong>maximum</strong> and <strong>minimum</strong> values. The <strong>score</strong> of a partition is the sum of subarray <strong>ranges</strong>.</p>

<p>Return the <strong>maximum</strong> possible <strong>score</strong> among all cyclic partitions.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,3], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Partition <code>nums</code> into <code>[2, 3]</code> and <code>[3, 1]</code> (wrapped around).</li>
	<li>The range of <code>[2, 3]</code> is <code>max(2, 3) - min(2, 3) = 3 - 2 = 1</code>.</li>
	<li>The range of <code>[3, 1]</code> is <code>max(3, 1) - min(3, 1) = 3 - 1 = 2</code>.</li>
	<li>The score is <code>1 + 2 = 3</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,3], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Partition <code>nums</code> into <code>[1, 2, 3, 3]</code>.</li>
	<li>The range of <code>[1, 2, 3, 3]</code> is <code>max(1, 2, 3, 3) - min(1, 2, 3, 3) = 3 - 1 = 2</code>.</li>
	<li>The score is 2.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,3], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>Identical to Example 1, we partition <code>nums</code> into <code>[2, 3]</code> and <code>[3, 1]</code>. Note that <code>nums</code> may be partitioned into fewer than <code>k</code> subarrays.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
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
