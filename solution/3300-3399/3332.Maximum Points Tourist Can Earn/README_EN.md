---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3332.Maximum%20Points%20Tourist%20Can%20Earn/README_EN.md
---

<!-- problem:start -->

# [3332. Maximum Points Tourist Can Earn](https://leetcode.com/problems/maximum-points-tourist-can-earn)

[中文文档](/solution/3300-3399/3332.Maximum%20Points%20Tourist%20Can%20Earn/README.md)

## Description

<!-- description:start -->

<p>You are given two integers, <code>n</code> and <code>k</code>, along with two 2D integer arrays, <code>stayScore</code> and <code>travelScore</code>.</p>

<p>A tourist is visiting a country with <code>n</code> cities, where each city is <strong>directly</strong> connected to every other city. The tourist&#39;s journey consists of <strong>exactly</strong> <code>k</code> <strong>0-indexed</strong> days, and they can choose <strong>any</strong> city as their starting point.</p>

<p>Each day, the tourist has two choices:</p>

<ul>
	<li><strong>Stay in the current city</strong>: If the tourist stays in their current city <code>curr</code> during day <code>i</code>, they will earn <code>stayScore[i][curr]</code> points.</li>
	<li><strong>Move to another city</strong>: If the tourist moves from their current city <code>curr</code> to city <code>dest</code>, they will earn <code>travelScore[curr][dest]</code> points.</li>
</ul>

<p>Return the <strong>maximum</strong> possible points the tourist can earn.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, k = 1, stayScore = [[2,3]], travelScore = [[0,2],[1,0]]</span></p>

<p><strong>Output:</strong> 3</p>

<p><strong>Explanation:</strong></p>

<p>The tourist earns the maximum number of points by starting in city 1 and staying in that city.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, k = 2, stayScore = [[3,4,2],[2,1,2]], travelScore = [[0,2,1],[2,0,4],[3,2,0]]</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<p>The tourist earns the maximum number of points by starting in city 1, staying in that city on day 0, and traveling to city 2 on day 1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>1 &lt;= k &lt;= 200</code></li>
	<li><code>n == travelScore.length == travelScore[i].length == stayScore[i].length</code></li>
	<li><code>k == stayScore.length</code></li>
	<li><code>1 &lt;= stayScore[i][j] &lt;= 100</code></li>
	<li><code>0 &lt;= travelScore[i][j] &lt;= 100</code></li>
	<li><code>travelScore[i][i] == 0</code></li>
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
