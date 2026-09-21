---
comments: true
difficulty: Hard
---

<!-- problem:start -->

# [4059. Lexicographically Largest Power Array](https://leetcode.com/problems/lexicographically-largest-power-array)

[中文文档](/solution/4000-4099/4059.Lexicographically%20Largest%20Power%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>. You may rearrange its elements to form any <span data-keyword="permutation-array">permutation</span> <code>perm</code>.</p>

<p>Define an array <code>power</code> of length 15. For each <code>0 &lt;= i &lt; 15</code>, <code>power[i]</code> is the largest integer <code>j</code>, where <code>0 &lt;= j &lt;= n</code>, such that the first <code>j</code> elements of <code>perm</code> all have the <code>(14 - i)<sup>th</sup></code> bit <span data-keyword="set-bit">set</span>.</p>

<p>Bit positions are indexed from right to left, starting with the <code>0<sup>th</sup></code> bit.</p>

<p>Return the <span data-keyword="lexicographically-larger-array">lexicographically largest</span> possible <code>power</code> array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,0,0,0,0,0,0,0,0,0,0,0,2,1,2]</span></p>

<p><strong>Explanation:</strong></p>

<p>Choose <code>perm = [7, 5]</code>.</p>

<ul>
	<li>Both elements have bit 2 set, so <code>power[12] = 2</code>.</li>
	<li>The first element has bit 1 set, but the second does not, so <code>power[13] = 1</code>.</li>
	<li>Both elements have bit 0 set, so <code>power[14] = 2</code>.</li>
</ul>

<p>All higher bits are unset in the first element, so the remaining entries are 0.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,1,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,0,0,0,0,0,0,0,0,0,0,0,1,2,3]</span></p>

<p><strong>Explanation:</strong></p>

<p>Choose <code>perm = [7, 3, 1]</code>.</p>

<ul>
	<li>The first element has bit 2 set, but the second does not, so <code>power[12] = 1</code>.</li>
	<li>The first two elements have bit 1 set, but the third does not, so <code>power[13] = 2</code>.</li>
	<li>All three elements have bit 0 set, so <code>power[14] = 3</code>.</li>
</ul>

<p>All higher bits are unset in the first element, so the remaining entries are 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt; 2<sup>15</sup></code></li>
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
