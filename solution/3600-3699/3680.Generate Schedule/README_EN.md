---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3680.Generate%20Schedule/README_EN.md
rating: 2377
source: Biweekly Contest 165 Q3
tags:
    - Greedy
    - Array
    - Math
---

<!-- problem:start -->

# [3680. Generate Schedule](https://leetcode.com/problems/generate-schedule)

[中文文档](/solution/3600-3699/3680.Generate%20Schedule/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> representing <code>n</code> teams. You are asked to generate a schedule such that:</p>

<ul>
	<li>Each team plays every other team <strong>exactly twice</strong>: once at home and once away.</li>
	<li>There is <strong>exactly one</strong> match per day; the schedule is a list of <strong>consecutive</strong> days and <code>schedule[i]</code> is the match on day <code>i</code>.</li>
	<li>No team plays on <strong>consecutive</strong> days.</li>
</ul>

<p>Return a 2D integer array <code>schedule</code>, where <code>schedule[i][0]</code> represents the home team and <code>schedule[i][1]</code> represents the away team. If multiple schedules meet the conditions, return <strong>any</strong> one of them.</p>

<p>If no schedule exists that meets the conditions, return an empty array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<p>​​​​​​​Since each team plays every other team exactly twice, a total of 6 matches need to be played: <code>[0,1],[0,2],[1,2],[1,0],[2,0],[2,1]</code>.</p>

<p>It&#39;s not possible to create a schedule without at least one team playing consecutive days.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">[[0,1],[2,3],[0,4],[1,2],[3,4],[0,2],[1,3],[2,4],[0,3],[1,4],[2,0],[3,1],[4,0],[2,1],[4,3],[1,0],[3,2],[4,1],[3,0],[4,2]]</span></p>

<p><strong>Explanation:</strong></p>

<p>Since each team plays every other team exactly twice, a total of 20 matches need to be played.</p>

<p>The output shows one of the schedules that meet the conditions. No team plays on consecutive days.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 50</code>​​​​​​​</li>
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
