---
comments: true
difficulty: Easy
---

<!-- problem:start -->

# [4083. Minimum Deletions to Make Array Sharp](https://leetcode.com/problems/minimum-deletions-to-make-array-sharp)

[中文文档](/solution/4000-4099/4083.Minimum%20Deletions%20to%20Make%20Array%20Sharp/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>You may delete any number of elements from <code>nums</code>. The remaining elements keep their original order.</p>

<p>The resulting array <code>a</code> is <strong>sharp</strong> if the <strong>bitwise XOR</strong> of every pair of <strong>adjacent</strong> elements is <strong>non-zero</strong>. That is, <code>a[i] XOR a[i + 1] != 0</code> for every index <code>i</code> such that <code>0 &lt;= i &lt; a.length - 1</code>. An array with <strong>at most</strong> one element is sharp.</p>

<p>Return the <strong>minimum</strong> number of deletions needed to make <code>nums</code> sharp.</p>

<p><strong>Bitwise XOR</strong> compares the corresponding bits of two integers. A bit in the result is 1 if the corresponding bits differ, and 0 otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,2,2,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The pairs at indices <code>(0, 1)</code>, <code>(2, 3)</code>, and <code>(3, 4)</code> each have an XOR of 0.</li>
	<li>Deleting one 1 and two 2s leaves <code>[1, 2, 3]</code>, where <code>1 XOR 2 == 3</code> and <code>2 XOR 3 == 1</code> are both non-zero. Therefore, the answer is 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,4,9,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The pair at indices <code>(0, 1)</code> has an XOR of 0.</li>
	<li>Deleting either of these two 4s leaves <code>[4, 9, 4]</code>, where <code>4 XOR 9 == 13</code> and <code>9 XOR 4 == 13</code> are both non-zero.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
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
