---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3154.Find%20Number%20of%20Ways%20to%20Reach%20the%20K-th%20Stair/README_EN.md
---

<!-- problem:start -->

# [3154. Find Number of Ways to Reach the K-th Stair](https://leetcode.com/problems/find-number-of-ways-to-reach-the-k-th-stair)

[中文文档](/solution/3100-3199/3154.Find%20Number%20of%20Ways%20to%20Reach%20the%20K-th%20Stair/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>non-negative</strong> integer <code>k</code>. There exists a staircase with an infinite number of stairs, with the <strong>lowest</strong> stair numbered 0.</p>

<p>Alice has an integer <code>jump</code>, with an initial value of 0. She starts on stair 1 and wants to reach stair <code>k</code> using <strong>any</strong> number of <strong>operations</strong>. If she is on stair <code>i</code>, in one <strong>operation</strong> she can:</p>

<ul>
	<li>Go down to stair <code>i - 1</code>. This operation <strong>cannot</strong> be used consecutively or on stair 0.</li>
	<li>Go up to stair <code>i + 2<sup>jump</sup></code>. And then, <code>jump</code> becomes <code>jump + 1</code>.</li>
</ul>

<p>Return the <em>total</em> number of ways Alice can reach stair <code>k</code>.</p>

<p><strong>Note</strong> that it is possible that Alice reaches the stair <code>k</code>, and performs some operations to reach the stair <code>k</code> again.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The 2 possible ways of reaching stair 0 are:</p>

<ul>
	<li>Alice starts at stair 1.
	<ul>
		<li>Using an operation of the first type, she goes down 1 stair to reach stair 0.</li>
	</ul>
	</li>
	<li>Alice starts at stair 1.
	<ul>
		<li>Using an operation of the first type, she goes down 1 stair to reach stair 0.</li>
		<li>Using an operation of the second type, she goes up 2<sup>0</sup> stairs to reach stair 1.</li>
		<li>Using an operation of the first type, she goes down 1 stair to reach stair 0.</li>
	</ul>
	</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The 4 possible ways of reaching stair 1 are:</p>

<ul>
	<li>Alice starts at stair 1. Alice is at stair 1.</li>
	<li>Alice starts at stair 1.
	<ul>
		<li>Using an operation of the first type, she goes down 1 stair to reach stair 0.</li>
		<li>Using an operation of the second type, she goes up 2<sup>0</sup> stairs to reach stair 1.</li>
	</ul>
	</li>
	<li>Alice starts at stair 1.
	<ul>
		<li>Using an operation of the second type, she goes up 2<sup>0</sup> stairs to reach stair 2.</li>
		<li>Using an operation of the first type, she goes down 1 stair to reach stair 1.</li>
	</ul>
	</li>
	<li>Alice starts at stair 1.
	<ul>
		<li>Using an operation of the first type, she goes down 1 stair to reach stair 0.</li>
		<li>Using an operation of the second type, she goes up 2<sup>0</sup> stairs to reach stair 1.</li>
		<li>Using an operation of the first type, she goes down 1 stair to reach stair 0.</li>
		<li>Using an operation of the second type, she goes up 2<sup>1</sup> stairs to reach stair 2.</li>
		<li>Using an operation of the first type, she goes down 1 stair to reach stair 1.</li>
	</ul>
	</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup></code></li>
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
