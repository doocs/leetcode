---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3965.Finish%20Time%20of%20Tasks%20I/README_EN.md
---

<!-- problem:start -->

# [3965. Finish Time of Tasks I](https://leetcode.com/problems/finish-time-of-tasks-i)

[中文文档](/solution/3900-3999/3965.Finish%20Time%20of%20Tasks%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> representing the number of tasks in a project, numbered from 0 to <code>n - 1</code>. These tasks are connected as a <strong>tree</strong> rooted at task 0. This is represented by a 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates that task <code>u<sub>i</sub></code> is the parent of task <code>v<sub>i</sub></code>.</p>

<p>You are also given an array <code>baseTime</code> of length <code>n</code>, where <code>baseTime[i]</code> represents the time to complete task <code>i</code>.</p>

<p>The <strong>finish time</strong> of each task is calculated as follows:</p>

<ul>
	<li>Leaf task: The finish time is <code>baseTime[i]</code>.</li>
	<li>Non-leaf task:
	<ul>
		<li>Let <code>earliest</code> be the <strong>minimum</strong> finish time among its children, and <code>latest</code> be the <strong>maximum</strong> finish time among its children.</li>
		<li>Let <code>ownDuration</code> be <code>(latest - earliest) + baseTime[i]</code>.</li>
		<li>The finish time of task <code>i</code> is <code>latest + ownDuration</code>.</li>
	</ul>
	</li>
</ul>

<p>Return the finish time of the root task 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1],[1,2]], baseTime = [9,5,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">17</span></p>

<p><strong>Explanation:</strong></p>
<svg height="100" viewbox="0 0 420 140" width="300" xmlns="http://www.w3.org/2000/svg"> <rect fill="white" height="100%" width="100%"></rect> <line stroke="black" stroke-width="2" x1="80" x2="210" y1="60" y2="60"></line> <line stroke="black" stroke-width="2" x1="210" x2="340" y1="60" y2="60"></line> <circle cx="80" cy="60" fill="white" r="24" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="16" text-anchor="middle" x="80" y="65">0</text> <text fill="black" font-size="14" text-anchor="middle" x="80" y="100">9</text> <circle cx="210" cy="60" fill="white" r="24" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="16" text-anchor="middle" x="210" y="65">1</text> <text fill="black" font-size="14" text-anchor="middle" x="210" y="100">5</text> <circle cx="340" cy="60" fill="white" r="24" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="16" text-anchor="middle" x="340" y="65">2</text> <text fill="black" font-size="14" text-anchor="middle" x="340" y="100">3</text> </svg>

<ul>
	<li>Task 2 is a leaf, so its finish time is <code>baseTime[2] = 3</code>.</li>
	<li>Task 1 has one child task 2:
	<ul>
		<li><code>earliest = latest = 3</code></li>
		<li><code>ownDuration = (latest - earliest) + baseTime[1] = 5</code></li>
		<li>Finish time of task 1 is <code>3 + 5 = 8</code></li>
	</ul>
	</li>
	<li>Task 0 has one child with finish time 8:
	<ul>
		<li><code>earliest = latest = 8</code></li>
		<li><code>ownDuration = (latest - earliest) + baseTime[0] = 9</code></li>
		<li>Finish time of task 0 is <code>8 + 9 = 17</code></li>
	</ul>
	</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1],[0,2]], baseTime = [4,7,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>
<svg height="130" viewbox="0 0 420 180" width="300" xmlns="http://www.w3.org/2000/svg"> <rect fill="white" height="100%" width="100%"></rect> <line stroke="black" stroke-width="2" x1="210" x2="110" y1="60" y2="130"></line> <line stroke="black" stroke-width="2" x1="210" x2="310" y1="60" y2="130"></line> <circle cx="210" cy="60" fill="white" r="24" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="16" text-anchor="middle" x="210" y="65">0</text> <text fill="black" font-size="14" text-anchor="middle" x="210" y="100">4</text> <circle cx="110" cy="130" fill="white" r="24" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="16" text-anchor="middle" x="110" y="135">1</text> <text fill="black" font-size="14" text-anchor="middle" x="110" y="170">7</text> <circle cx="310" cy="130" fill="white" r="24" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="16" text-anchor="middle" x="310" y="135">2</text> <text fill="black" font-size="14" text-anchor="middle" x="310" y="170">6</text> </svg>

<ul>
	<li>Task 1 is a leaf, so its finish time is <code>baseTime[1] = 7</code>.</li>
	<li>Task 2 is a leaf, so its finish time is <code>baseTime[2] = 6</code>.</li>
	<li>Task 0 has two children with finish times 7 and 6:
	<ul>
		<li><code>earliest = 6</code>, <code>latest = 7</code></li>
		<li><code>ownDuration = (latest - earliest) + baseTime[0] = (7 - 6) + 4 = 5</code></li>
		<li>Finish time of task 0 is <code>latest + ownDuration = 7 + 5 = 12</code></li>
	</ul>
	</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, edges = [[0,1],[0,2],[2,3]], baseTime = [5,8,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">18</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Task 1 is a leaf, so its finish time is <code>baseTime[1] = 8</code>.</li>
	<li>Task 3 is a leaf, so its finish time is <code>baseTime[3] = 1</code>.</li>
	<li>Task 2 has one child task 3:
	<ul>
		<li><code>earliest = latest = 1</code></li>
		<li><code>ownDuration = (latest - earliest) + baseTime[2] = 0 + 2 = 2</code></li>
		<li>Finish time of task 2 is <code>latest + ownDuration = 1 + 2 = 3</code></li>
	</ul>
	</li>
	<li>Task 0 has two children with finish times 8 and 3:
	<ul>
		<li><code>earliest = 3</code>, <code>latest = 8</code></li>
		<li><code>ownDuration = (latest - earliest) + baseTime[0] = (8 - 3) + 5 = 10</code></li>
		<li>Finish time of task 0 is <code>latest + ownDuration = 8 + 10 = 18</code></li>
	</ul>
	</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length = n - 1</code></li>
	<li><code>edges[i] == [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>i </sub>!= v<sub>i</sub></code></li>
	<li>The input is generated such that <code>edges</code> represents a valid tree.</li>
	<li><code>baseTime.length == n</code></li>
	<li><code>1 &lt;= baseTime[i] &lt;= 10<sup>5</sup></code>​​​​​​​</li>
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
