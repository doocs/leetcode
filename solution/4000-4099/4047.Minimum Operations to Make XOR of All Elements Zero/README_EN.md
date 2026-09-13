---
comments: true
difficulty: Hard
---

<!-- problem:start -->

# [4047. Minimum Operations to Make XOR of All Elements Zero 🔒](https://leetcode.com/problems/minimum-operations-to-make-xor-of-all-elements-zero)

[中文文档](/solution/4000-4099/4047.Minimum%20Operations%20to%20Make%20XOR%20of%20All%20Elements%20Zero/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> consisting of <strong>positive</strong> integers.</p>

<p>You may perform the following <strong>operation</strong> any number of times:</p>

<ul>
	<li>Choose two <strong>distinct</strong> indices <code>i</code> and <code>j</code> such that <code>nums[i] != nums[j]</code>, and replace either <code>nums[i]</code> or <code>nums[j]</code> with <code>nums[i] ^ nums[j]</code>, where <code>^</code> denotes the <strong>bitwise XOR</strong>.</li>
</ul>

<p>Return the <strong>minimum</strong> number of operations required to make the <strong>bitwise XOR</strong> of all elements in <code>nums</code> equal to 0. If it is impossible, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [8,1,4,8,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal sequence of operations is:</p>

<ul>
	<li>Choose indices 0 and 1, and replace <code>nums[0]</code> with <code>8 ^ 1 = 9</code>. The array becomes <code>[9, 1, 4, 8, 2]</code>.</li>
	<li>Choose indices 2 and 3, and replace <code>nums[3]</code> with <code>4 ^ 8 = 12</code>. The array becomes <code>[9, 1, 4, 12, 2]</code>.</li>
	<li>Choose indices 0 and 4, and replace <code>nums[0]</code> with <code>9 ^ 2 = 11</code>. The array becomes <code>[11, 1, 4, 12, 2]</code>.</li>
</ul>

<p>The XOR of all elements of <code>nums</code> is <code>11 ^ 1 ^ 4 ^ 12 ^ 2 = 0</code>, so the answer is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The XOR of all elements of <code>nums</code> is <code>1 ^ 2 ^ 3 = 0</code>, so no operations are required.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>It is impossible to make the XOR of all elements of <code>nums</code> equal to 0, so the answer is -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 2000</code></li>
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
