---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3629.Minimum%20Jumps%20to%20Reach%20End%20via%20Prime%20Teleportation/README_EN.md
rating: 2139
source: Weekly Contest 460 Q3
---

<!-- problem:start -->

# [3629. Minimum Jumps to Reach End via Prime Teleportation](https://leetcode.com/problems/minimum-jumps-to-reach-end-via-prime-teleportation)

[中文文档](/solution/3600-3699/3629.Minimum%20Jumps%20to%20Reach%20End%20via%20Prime%20Teleportation/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>.</p>

<p>You start at index 0, and your goal is to reach index <code>n - 1</code>.</p>

<p>From any index <code>i</code>, you may perform one of the following operations:</p>

<ul>
	<li><strong>Adjacent Step</strong>: Jump to index <code>i + 1</code> or <code>i - 1</code>, if the index is within bounds.</li>
	<li><strong>Prime Teleportation</strong>: If <code>nums[i]</code> is a <span data-keyword="prime-number">prime number</span> <code>p</code>, you may instantly jump to any index <code>j != i</code> such that <code>nums[j] % p == 0</code>.</li>
</ul>

<p>Return the <strong>minimum</strong> number of jumps required to reach index <code>n - 1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,4,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal sequence of jumps is:</p>

<ul>
	<li>Start at index <code>i = 0</code>. Take an adjacent step to index 1.</li>
	<li>At index <code>i = 1</code>, <code>nums[1] = 2</code> is a prime number. Therefore, we teleport to index <code>i = 3</code> as <code>nums[3] = 6</code> is divisible by 2.</li>
</ul>

<p>Thus, the answer is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,4,7,9]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal sequence of jumps is:</p>

<ul>
	<li>Start at index <code>i = 0</code>. Take an adjacent step to index <code>i = 1</code>.</li>
	<li>At index <code>i = 1</code>, <code>nums[1] = 3</code> is a prime number. Therefore, we teleport to index <code>i = 4</code> since <code>nums[4] = 9</code> is divisible by 3.</li>
</ul>

<p>Thus, the answer is 2.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,6,5,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Since no teleportation is possible, we move through <code>0 &rarr; 1 &rarr; 2 &rarr; 3</code>. Thus, the answer is 3.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
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
