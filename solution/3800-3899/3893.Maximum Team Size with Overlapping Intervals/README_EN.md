---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3893.Maximum%20Team%20Size%20with%20Overlapping%20Intervals/README_EN.md
---

<!-- problem:start -->

# [3893. Maximum Team Size with Overlapping Intervals 🔒](https://leetcode.com/problems/maximum-team-size-with-overlapping-intervals)

[中文文档](/solution/3800-3899/3893.Maximum%20Team%20Size%20with%20Overlapping%20Intervals/README.md)

## Description

<!-- description:start -->

<p data-end="767" data-start="694">You are given two integer arrays <code>startTime</code> and <code>endTime</code> of length <code>n</code>.</p>

<ul>
	<li><code>startTime[i]</code> represents the start time of the <code>i<sup>th</sup></code> employee.</li>
	<li><code>endTime[i]</code> represents the end time of the <code>i<sup>th</sup></code> employee.</li>
</ul>

<p>Two employees <code>i</code> and <code>j</code> can interact if their time intervals <strong>overlap</strong>. Two intervals are considered overlapping if they share <strong>at least one</strong> common time point.</p>

<p>A team is <strong>valid</strong> if there exists <strong>at least one</strong> employee in the team who can interact with every other member of the team.</p>

<p>Return an integer denoting the <strong>maximum</strong> possible size of such a team.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">startTime = [1,2,3], endTime = [4,5,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>i = 0</code> with interval <code>[1, 4]</code>.</li>
	<li>It overlaps with <code>i = 1</code> having interval <code>[2, 5]</code> and <code>i = 2</code> having interval <code>[3, 6]</code>.</li>
	<li>Thus, index 0 can interact with all other indices, so the team size is 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">startTime = [2,5,8], endTime = [3,7,9]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>i = 0</code>, interval <code>[2, 3]</code> does not overlap with <code>[5, 7]</code> or <code>[8, 9]</code>.</li>
	<li>For <code>i = 1</code>, interval <code>[5, 7]</code> does not overlap with <code>[2, 3]</code> or <code>[8, 9]</code>.</li>
	<li>For <code>i = 2</code>, interval <code>[8, 9]</code> does not overlap with <code>[2, 3]</code> or <code>[5, 7]</code>.</li>
	<li>Thus, no index can interact with others, so the maximum team size is 1.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">startTime = [3,4,6], endTime = [8,5,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>i = 0</code> with interval <code>[3, 8]</code>.</li>
	<li>It overlaps with <code>i = 1</code> having interval <code>[4, 5]</code> and <code>i = 2</code> having interval <code>[6, 7]</code>.</li>
	<li>Thus, index 0 can interact with all other indices, so the team size is 3.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == startTime.length == endTime.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= startTime[i] &lt;= endTime[i] &lt;= 10<sup>9</sup></code></li>
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
