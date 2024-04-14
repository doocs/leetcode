# [3111. Minimum Rectangles to Cover Points](https://leetcode.com/problems/minimum-rectangles-to-cover-points)

[中文文档](/solution/3100-3199/3111.Minimum%20Rectangles%20to%20Cover%20Points/README.md)

<!-- tags: -->

## Description

<p>You are given a 2D integer array <code>points</code>, where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>. You are also given an integer <code>w</code>. Your task is to <strong>cover</strong> <strong>all</strong> the given points with rectangles.</p>

<p>Each rectangle has its lower end at some point <code>(x<sub>1</sub>, 0)</code> and its upper end at some point <code>(x<sub>2</sub>, y<sub>2</sub>)</code>, where <code>x<sub>1</sub> &lt;= x<sub>2</sub></code>, <code>y<sub>2</sub> &gt;= 0</code>, and the condition <code>x<sub>2</sub> - x<sub>1</sub> &lt;= w</code> <strong>must</strong> be satisfied for each rectangle.</p>

<p>A point is considered covered by a rectangle if it lies within or on the boundary of the rectangle.</p>

<p>Return an integer denoting the <strong>minimum</strong> number of rectangles needed so that each point is covered by <strong>at least one</strong> rectangle<em>.</em></p>

<p><strong>Note:</strong> A point may be covered by more than one rectangle.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3111.Minimum%20Rectangles%20to%20Cover%20Points/images/screenshot-from-2024-03-04-20-33-05.png" style="width: 205px; height: 300px;" /></p>

<div class="example-block" style="
    border-color: var(--border-tertiary);
    border-left-width: 2px;
    color: var(--text-secondary);
    font-size: .875rem;
    margin-bottom: 1rem;
    margin-top: 1rem;
    overflow: visible;
    padding-left: 1rem;
">
<p><strong>Input:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">points = [[2,1],[1,0],[1,4],[1,8],[3,5],[4,6]], w = 1</span></p>

<p><strong>Output:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">2</span></p>

<p><strong>Explanation: </strong></p>

<p>The image above shows one possible placement of rectangles to cover the points:</p>

<ul>
	<li>A rectangle with a lower end at <code>(1, 0)</code> and its upper end at <code>(2, 8)</code></li>
	<li>A rectangle with a lower end at <code>(3, 0)</code> and its upper end at <code>(4, 8)</code></li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3111.Minimum%20Rectangles%20to%20Cover%20Points/images/screenshot-from-2024-03-04-18-59-12.png" style="width: 260px; height: 250px;" /></p>

<div class="example-block" style="
    border-color: var(--border-tertiary);
    border-left-width: 2px;
    color: var(--text-secondary);
    font-size: .875rem;
    margin-bottom: 1rem;
    margin-top: 1rem;
    overflow: visible;
    padding-left: 1rem;
">
<p><strong>Input:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">points = [[0,0],[1,1],[2,2],[3,3],[4,4],[5,5],[6,6]], w = 2</span></p>

<p><strong>Output:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">3</span></p>

<p><strong>Explanation: </strong></p>

<p>The image above shows one possible placement of rectangles to cover the points:</p>

<ul>
	<li>A rectangle with a lower end at <code>(0, 0)</code> and its upper end at <code>(2, 2)</code></li>
	<li>A rectangle with a lower end at <code>(3, 0)</code> and its upper end at <code>(5, 5)</code></li>
	<li>A rectangle with a lower end at <code>(6, 0)</code> and its upper end at <code>(6, 6)</code></li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3111.Minimum%20Rectangles%20to%20Cover%20Points/images/screenshot-from-2024-03-04-20-24-03.png" style="height: 150px; width: 127px;" /></p>

<div class="example-block" style="
    border-color: var(--border-tertiary);
    border-left-width: 2px;
    color: var(--text-secondary);
    font-size: .875rem;
    margin-bottom: 1rem;
    margin-top: 1rem;
    overflow: visible;
    padding-left: 1rem;
">
<p><strong>Input:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">points = [[2,3],[1,2]], w = 0</span></p>

<p><strong>Output:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">2</span></p>

<p><strong>Explanation: </strong></p>

<p>The image above shows one possible placement of rectangles to cover the points:</p>

<ul>
	<li>A rectangle with a lower end at <code>(1, 0)</code> and its upper end at <code>(1, 2)</code></li>
	<li>A rectangle with a lower end at <code>(2, 0)</code> and its upper end at <code>(2, 3)</code></li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= points.length &lt;= 10<sup>5</sup></code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i</sub> == points[i][0] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= y<sub>i</sub> == points[i][1] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= w &lt;= 10<sup>9</sup></code></li>
	<li>All pairs <code>(x<sub>i</sub>, y<sub>i</sub>)</code> are distinct.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
