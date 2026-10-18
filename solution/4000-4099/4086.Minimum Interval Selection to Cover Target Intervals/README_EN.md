---
comments: true
difficulty: Hard
---

<!-- problem:start -->

# [4086. Minimum Interval Selection to Cover Target Intervals](https://leetcode.com/problems/minimum-interval-selection-to-cover-target-intervals)

[中文文档](/solution/4000-4099/4086.Minimum%20Interval%20Selection%20to%20Cover%20Target%20Intervals/README.md)

## Description

<!-- description:start -->

<p>You are given two 2D integer arrays, <code>availableIntervals</code> and <code>targetIntervals</code>, each representing a list of <strong>closed intervals</strong>.</p>

<p>Select a <strong><span data-keyword="subset">subset</span></strong> of intervals from <code>availableIntervals</code> so that <strong>every real point</strong> contained in any interval in <code>targetIntervals</code> belongs to at least one selected interval.</p>

<p>Points outside the target intervals do not need to be covered. Selected intervals may overlap or extend beyond the target intervals.</p>

<p>Return the <strong>minimum</strong> number of intervals you need to select. If it is impossible to cover all the target intervals, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">availableIntervals = [[1,4],[1,7],[3,9],[5,10],[10,15]], targetIntervals = [[1,3],[2,5],[7,8],[10,13],[11,15],[12,13]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The target intervals cover the ranges <code>[1, 5]</code>, <code>[7, 8]</code>, and <code>[10, 15]</code>. By selecting <code>[1, 4]</code>, <code>[3, 9]</code>, and <code>[10, 15]</code> from <code>availableIntervals</code>, all target intervals can be fully covered with three intervals.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">availableIntervals = [[4,6],[1,4],[2,5]], targetIntervals = [[3,4],[2,5],[6,7]]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>It is impossible to select intervals from <code>availableIntervals</code> to cover all the intervals in <code>targetIntervals</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">availableIntervals = [[1,2],[3,4]], targetIntervals = [[1,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>Selecting both available intervals leaves every real point strictly between 2 and 3 uncovered. For example, 2.5 belongs to the target interval but to neither available interval.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= availableIntervals.length, targetIntervals.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>availableIntervals[i].length == targetIntervals[j].length == 2</code></li>
	<li><code>1 &lt;= availableIntervals[i][0] &lt;= availableIntervals[i][1] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= targetIntervals[j][0] &lt;= targetIntervals[j][1] &lt;= 10<sup>5</sup></code></li>
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
