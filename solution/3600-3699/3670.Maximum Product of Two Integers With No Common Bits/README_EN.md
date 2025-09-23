---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3670.Maximum%20Product%20of%20Two%20Integers%20With%20No%20Common%20Bits/README_EN.md
rating: 2233
source: Weekly Contest 465 Q3
---

<!-- problem:start -->

# [3670. Maximum Product of Two Integers With No Common Bits](https://leetcode.com/problems/maximum-product-of-two-integers-with-no-common-bits)

[中文文档](/solution/3600-3699/3670.Maximum%20Product%20of%20Two%20Integers%20With%20No%20Common%20Bits/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Your task is to find two <strong>distinct</strong> indices <code>i</code> and <code>j</code> such that the product <code>nums[i] * nums[j]</code> is <strong>maximized,</strong> and the binary representations of <code>nums[i]</code> and <code>nums[j]</code> do not share any common set bits.</p>

<p>Return the <strong>maximum</strong> possible product of such a pair. If no such pair exists, return 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,5,6,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p>The best pair is 3 (011) and 4 (100). They share no set bits and <code>3 * 4 = 12</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,6,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>Every pair of numbers has at least one common set bit. Hence, the answer is 0.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [64,8,32]</span></p>

<p><strong>Output:</strong> <span class="example-io">2048</span></p>

<p><strong>Explanation:</strong></p>

<p>No pair of numbers share a common bit, so the answer is the product of the two maximum elements, 64 and 32 (<code>64 * 32 = 2048</code>).</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
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
