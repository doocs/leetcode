---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3514.Number%20of%20Unique%20XOR%20Triplets%20II/README_EN.md
rating: 1883
source: Biweekly Contest 154 Q3
tags:
    - Bit Manipulation
    - Array
    - Math
    - Enumeration
---

<!-- problem:start -->

# [3514. Number of Unique XOR Triplets II](https://leetcode.com/problems/number-of-unique-xor-triplets-ii)

[中文文档](/solution/3500-3599/3514.Number%20of%20Unique%20XOR%20Triplets%20II/README.md)

## Description

<!-- description:start -->

<p data-end="261" data-start="147">You are given an integer array <code>nums</code>.</p>

<p>A <strong>XOR triplet</strong> is defined as the XOR of three elements <code>nums[i] XOR nums[j] XOR nums[k]</code> where <code>i &lt;= j &lt;= k</code>.</p>

<p>Return the number of <strong>unique</strong> XOR triplet values from all possible triplets <code>(i, j, k)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p data-end="158" data-start="101">The possible XOR triplet values are:</p>

<ul data-end="280" data-start="159">
	<li data-end="188" data-start="159"><code>(0, 0, 0) &rarr; 1 XOR 1 XOR 1 = 1</code></li>
	<li data-end="218" data-start="189"><code>(0, 0, 1) &rarr; 1 XOR 1 XOR 3 = 3</code></li>
	<li data-end="248" data-start="219"><code>(0, 1, 1) &rarr; 1 XOR 3 XOR 3 = 1</code></li>
	<li data-end="280" data-start="249"><code>(1, 1, 1) &rarr; 3 XOR 3 XOR 3 = 3</code></li>
</ul>

<p data-end="343" data-start="282">The unique XOR values are <code data-end="316" data-start="308">{1, 3}</code>. Thus, the output is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [6,7,8,9]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The possible XOR triplet values are <code data-end="275" data-start="267">{6, 7, 8, 9}</code>. Thus, the output is 4.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1500</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1500</code></li>
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
