---
comments: true
difficulty: Hard
---

<!-- problem:start -->

# [4119. Minimum Set Bits in a Valid Integer](https://leetcode.com/problems/minimum-set-bits-in-a-valid-integer)

[中文文档](/solution/4100-4199/4119.Minimum%20Set%20Bits%20in%20a%20Valid%20Integer/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and an integer <code>k</code>.</p>

<p>Let <code>x<sub>1</sub>, x<sub>2</sub>, ..., x<sub>n-k+1</sub></code> denote the <strong>bitwise AND</strong> of each <strong><span data-keyword="subarray-nonempty">subarray</span></strong> of length <code>k</code> in <code>nums</code>, in order from left to right.</p>

<p>A positive integer <code>y</code> is called <strong>valid</strong> if <code>(y &amp; x<sub>i</sub>) != 0</code> for every <code>1 &lt;= i &lt;= n - k + 1</code>.</p>

<p>Return the <strong>minimum</strong> number of <strong><span data-keyword="set-bit">set bits</span></strong> in any <strong>valid</strong> integer <code>y</code>. If no valid integer exists, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [6,7,3], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The two subarrays of length <code>k = 2</code> are <code>[6, 7]</code> and <code>[7, 3]</code>.</li>
	<li>Their bitwise ANDs are <code>x<sub>1</sub> = 6 &amp; 7 = 6</code> and <code>x<sub>2</sub> = 7 &amp; 3 = 3</code>.</li>
	<li><code>y = 2</code> is valid, since <code>2 &amp; 6 = 2</code> and <code>2 &amp; 3 = 2</code>, both non-zero.</li>
	<li><code>y = 2</code> has 1 set bit (<code>&quot;010&quot;</code>), so the answer is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,5,12], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Since <code>k = 1</code>, each subarray has one element, so <code>x<sub>1</sub> = 3</code>, <code>x<sub>2</sub> = 5</code>, and <code>x<sub>3</sub> = 12</code>.</li>
	<li><code>y = 9</code> is valid, since <code>9 &amp; 3 = 1</code>, <code>9 &amp; 5 = 1</code>, and <code>9 &amp; 12 = 8</code>, all non-zero.</li>
	<li><code>y = 9</code> has 2 set bits (<code>&quot;1001&quot;</code>), so the answer is 2.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The only subarray of length <code>k = 2</code> is <code>[2, 1]</code>, with bitwise AND <code>x<sub>1</sub> = 2 &amp; 1 = 0</code>.</li>
	<li>Since <code>x<sub>1</sub> = 0</code>, no <code>y</code> is valid. Thus, the answer is -1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
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
