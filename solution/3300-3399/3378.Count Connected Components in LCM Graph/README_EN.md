---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3378.Count%20Connected%20Components%20in%20LCM%20Graph/README_EN.md
tags:
    - Union Find
    - Array
    - Hash Table
    - Math
    - Number Theory
---

<!-- problem:start -->

# [3378. Count Connected Components in LCM Graph](https://leetcode.com/problems/count-connected-components-in-lcm-graph)

[中文文档](/solution/3300-3399/3378.Count%20Connected%20Components%20in%20LCM%20Graph/README.md)

## Description

<!-- description:start -->

<p>You are given an array of integers <code>nums</code> of size <code>n</code> and a <strong>positive</strong> integer <code>threshold</code>.</p>

<p>There is a graph consisting of <code>n</code> nodes with the&nbsp;<code>i<sup>th</sup></code>&nbsp;node having a value of <code>nums[i]</code>. Two nodes <code>i</code> and <code>j</code> in the graph are connected via an <strong>undirected</strong> edge if <code>lcm(nums[i], nums[j]) &lt;= threshold</code>.</p>

<p>Return the number of <strong>connected components</strong> in this graph.</p>

<p>A <strong>connected component</strong> is a subgraph of a graph in which there exists a path between any two vertices, and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.</p>

<p>The term <code>lcm(a, b)</code> denotes the <strong>least common multiple</strong> of <code>a</code> and <code>b</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4,8,3,9], threshold = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong>&nbsp;</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3378.Count%20Connected%20Components%20in%20LCM%20Graph/images/example0.png" style="width: 250px; height: 251px;" /></p>

<p>&nbsp;</p>

<p>The four connected components are <code>(2, 4)</code>, <code>(3)</code>, <code>(8)</code>, <code>(9)</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4,8,3,9,12], threshold = 10</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong>&nbsp;</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3378.Count%20Connected%20Components%20in%20LCM%20Graph/images/example1.png" style="width: 250px; height: 252px;" /></p>

<p>The two connected components are <code>(2, 3, 4, 8, 9)</code>, and <code>(12)</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>All elements of <code>nums</code> are unique.</li>
	<li><code>1 &lt;= threshold &lt;= 2 * 10<sup>5</sup></code></li>
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
