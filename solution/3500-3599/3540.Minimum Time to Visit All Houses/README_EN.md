---
comments: true
difficulty: Medium
tags:
    - Array
    - Prefix Sum
---

<!-- problem:start -->

# [3540. Minimum Time to Visit All Houses 🔒](https://leetcode.com/problems/minimum-time-to-visit-all-houses)

[中文文档](/solution/3500-3599/3540.Minimum%20Time%20to%20Visit%20All%20Houses/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>forward</code> and <code>backward</code>, both of size <code>n</code>. You are also given another integer array <code>queries</code>.</p>

<p>There are <code>n</code> houses <em>arranged in a circle</em>. The houses are connected via roads in a special arrangement:</p>

<ul>
	<li>For all <code>0 &lt;= i &lt;= n - 2</code>, house <code>i</code> is connected to house <code>i + 1</code> via a road with length <code>forward[i]</code> meters. Additionally, house <code>n - 1</code> is connected back to house 0 via a road with length <code>forward[n - 1]</code> meters, completing the circle.</li>
	<li>For all <code>1 &lt;= i &lt;= n - 1</code>, house <code>i</code> is connected to house <code>i - 1</code> via a road with length <code>backward[i]</code> meters. Additionally, house 0 is connected back to house <code>n - 1</code> via a road with length <code>backward[0]</code> meters, completing the circle.</li>
</ul>

<p>You can walk at a pace of <strong>one</strong> meter per second. Starting from house 0, find the <strong>minimum</strong> time taken to visit each house in the order specified by <code>queries</code>.</p>

<p>Return the <strong>minimum</strong> total time taken to visit the houses.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">forward = [1,4,4], backward = [4,1,2], queries = [1,2,0,2]</span></p>

<p><strong>Output:</strong> 12</p>

<p><strong>Explanation:</strong></p>

<p>The path followed is <code><u>0</u><sup>(0)</sup> &rarr; <u>1</u><sup>(1)</sup> &rarr;​​​​​​​ <u>2</u><sup>(5)</sup> <u>&rarr;</u> 1<sup>(7)</sup> <u>&rarr;</u>​​​​​​​ <u>0</u><sup>(8)</sup> <u>&rarr;</u> <u>2</u><sup>(12)</sup></code>.</p>

<p><strong>Note:</strong> The notation used is <code>node<sup>(total time)</sup></code>, <code>&rarr;</code> represents forward road, and <code><u>&rarr;</u></code> represents backward road.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">forward = [1,1,1,1], backward = [2,2,2,2], queries = [1,2,3,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The path travelled is <code><u>0</u> &rarr;​​​​​​​ <u>1</u> &rarr;​​​​​​​ <u>2</u> &rarr;​​​​​​​ <u>3</u> &rarr; <u>0</u></code>. Each step is in the forward direction and requires 1 second.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n == forward.length == backward.length</code></li>
	<li><code>1 &lt;= forward[i], backward[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= queries[i] &lt; n</code></li>
	<li><code>queries[i] != queries[i + 1]</code></li>
	<li><code>queries[0]</code> is not 0.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> Houses sit on a cycle with different forward and backward weights, and $\textit{queries}$ must be visited in order. $n,q \le 10^5$ forbid searching the cycle each time.
>
> Prefix sums of the two directions give the shorter arc between any pair. Sum those arcs along the query sequence.

<!-- thinking:end -->

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
