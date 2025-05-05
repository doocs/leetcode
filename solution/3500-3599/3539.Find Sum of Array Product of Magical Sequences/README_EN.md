---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3539.Find%20Sum%20of%20Array%20Product%20of%20Magical%20Sequences/README_EN.md
---

<!-- problem:start -->

# [3539. Find Sum of Array Product of Magical Sequences](https://leetcode.com/problems/find-sum-of-array-product-of-magical-sequences)

[中文文档](/solution/3500-3599/3539.Find%20Sum%20of%20Array%20Product%20of%20Magical%20Sequences/README.md)

## Description

<!-- description:start -->

<p>You are given two integers, <code>m</code> and <code>k</code>, and an integer array <code>nums</code>.</p>
A sequence of integers <code>seq</code> is called <strong>magical</strong> if:

<ul>
	<li><code>seq</code> has a size of <code>m</code>.</li>
	<li><code>0 &lt;= seq[i] &lt; nums.length</code></li>
	<li>The <strong>binary representation</strong> of <code>2<sup>seq[0]</sup> + 2<sup>seq[1]</sup> + ... + 2<sup>seq[m - 1]</sup></code> has <code>k</code> <strong>set bits</strong>.</li>
</ul>

<p>The <strong>array product</strong> of this sequence is defined as <code>prod(seq) = (nums[seq[0]] * nums[seq[1]] * ... * nums[seq[m - 1]])</code>.</p>

<p>Return the <strong>sum</strong> of the <strong>array products</strong> for all valid <strong>magical</strong> sequences.</p>

<p>Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>A <strong>set bit</strong> refers to a bit in the binary representation of a number that has a value of 1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 5, k = 5, nums = [1,10,100,10000,1000000]</span></p>

<p><strong>Output:</strong> <span class="example-io">991600007</span></p>

<p><strong>Explanation:</strong></p>

<p>All permutations of <code>[0, 1, 2, 3, 4]</code> are magical sequences, each with an array product of 10<sup>13</sup>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 2, k = 2, nums = [5,4,3,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">170</span></p>

<p><strong>Explanation:</strong></p>

<p>The magical sequences are <code>[0, 1]</code>, <code>[0, 2]</code>, <code>[0, 3]</code>, <code>[0, 4]</code>, <code>[1, 0]</code>, <code>[1, 2]</code>, <code>[1, 3]</code>, <code>[1, 4]</code>, <code>[2, 0]</code>, <code>[2, 1]</code>, <code>[2, 3]</code>, <code>[2, 4]</code>, <code>[3, 0]</code>, <code>[3, 1]</code>, <code>[3, 2]</code>, <code>[3, 4]</code>, <code>[4, 0]</code>, <code>[4, 1]</code>, <code>[4, 2]</code>, and <code>[4, 3]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 1, k = 1, nums = [28]</span></p>

<p><strong>Output:</strong> <span class="example-io">28</span></p>

<p><strong>Explanation:</strong></p>

<p>The only magical sequence is <code>[0]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= m &lt;= 30</code></li>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>8</sup></code></li>
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
