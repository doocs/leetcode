---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3967.Finish%20Time%20of%20Tasks%20II/README_EN.md
---

<!-- problem:start -->

# [3967. Finish Time of Tasks II 🔒](https://leetcode.com/problems/finish-time-of-tasks-ii)

[中文文档](/solution/3900-3999/3967.Finish%20Time%20of%20Tasks%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> representing the number of tasks in a project, numbered from 0 to <code>n - 1</code>. These tasks are connected as an undirected<strong> tree</strong>. This is represented by a 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates an undirected connection between task <code>u<sub>i</sub></code> and task <code>v<sub>i</sub></code>.</p>

<p>You are also given an array <code>baseTime</code> of length <code>n</code>, where <code>baseTime[i]</code> represents the time to complete task <code>i</code>.</p>

<p>For any chosen task as the root, the <strong>finish time</strong> of each task is calculated as follows:</p>

<ul>
	<li>Leaf task: The finish time is <code>baseTime[i]</code>.</li>
	<li>Non-leaf task:
	<ul>
		<li>Let <code>earliest</code> be the <strong>minimum</strong> finish time among its children, and <code>latest</code> be the <strong>maximum</strong> finish time among its children.</li>
		<li>Let <code>ownDuration</code> be <code>(latest - earliest) + baseTime[i]</code>.</li>
		<li>Finish time of task <code>i</code> is <code>latest + ownDuration</code>.</li>
	</ul>
	</li>
</ul>

<p>Choose <strong>any</strong> task as the root and compute the finish time of that root based on the rules above.</p>

<p>Return the <strong>minimum</strong> possible finish time among all choices of root.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1],[1,2]], baseTime = [9,1,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">14</span></p>

<p><strong>Explanation:</strong></p>
<svg height="110" viewbox="50 30 400 124" width="350" xmlns="http://www.w3.org/2000/svg"> <rect fill="white" height="124" width="400" x="50" y="30"></rect> <line stroke="black" stroke-width="2" x1="100" x2="250" y1="80" y2="80"></line> <line stroke="black" stroke-width="2" x1="250" x2="400" y1="80" y2="80"></line> <circle cx="100" cy="80" fill="white" r="30" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="18" text-anchor="middle" x="100" y="87">0</text> <text fill="black" font-size="16" text-anchor="middle" x="100" y="131">9</text> <circle cx="250" cy="80" fill="white" r="30" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="18" text-anchor="middle" x="250" y="87">1</text> <text fill="black" font-size="16" text-anchor="middle" x="250" y="131">1</text> <circle cx="400" cy="80" fill="white" r="30" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="18" text-anchor="middle" x="400" y="87">2</text> <text fill="black" font-size="16" text-anchor="middle" x="400" y="131">5</text> </svg>

<p>The optimal choice is to treat task 1 as the root.</p>

<ul>
	<li>Task 0 is a leaf, so its finish time is <code>baseTime[0] = 9</code>.</li>
	<li>Task 2 is a leaf, so its finish time is <code>baseTime[2] = 5</code>.</li>
	<li>Task 1 has two children with finish times 9 and 5:
	<ul>
		<li><code>earliest = 5</code>, <code>latest = 9</code></li>
		<li><code>ownDuration = (latest - earliest) + baseTime[1] = (9 - 5) + 1 = 5</code></li>
		<li>Finish time of task 1 is <code>latest + ownDuration = 9 + 5 = 14</code></li>
	</ul>
	</li>
</ul>

<p>Thus, the minimum possible finish time among all choices of root is 14.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1],[0,2]], baseTime = [4,7,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>
<svg height="215" viewbox="48 14 324 232" width="300" xmlns="http://www.w3.org/2000/svg"> <rect fill="white" height="232" width="324" x="48" y="14"></rect> <line stroke="black" stroke-width="2" x1="210" x2="110" y1="60" y2="180"></line> <line stroke="black" stroke-width="2" x1="210" x2="310" y1="60" y2="180"></line> <circle cx="210" cy="60" fill="white" r="32" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="18" text-anchor="middle" x="210" y="66">0</text> <text fill="black" font-size="16" text-anchor="middle" x="210" y="110">4</text> <circle cx="110" cy="180" fill="white" r="32" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="18" text-anchor="middle" x="110" y="186">1</text> <text fill="black" font-size="16" text-anchor="middle" x="110" y="230">7</text> <circle cx="310" cy="180" fill="white" r="32" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="18" text-anchor="middle" x="310" y="186">2</text> <text fill="black" font-size="16" text-anchor="middle" x="310" y="230">6</text> </svg>

<p>The optimal choice is to treat task 0 as the root.</p>

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

<p>Thus, the minimum possible finish time among all choices of root is 12.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, edges = [[0,1],[0,2],[2,3]], baseTime = [5,8,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">16</span></p>

<p><strong>Explanation:</strong></p>
<svg height="368" viewbox="46 26 380 466" width="300" xmlns="http://www.w3.org/2000/svg"> <rect fill="white" height="466" width="380" x="46" y="26"></rect> <line stroke="black" stroke-width="2" x1="230" x2="110" y1="80" y2="260"></line> <line stroke="black" stroke-width="2" x1="230" x2="350" y1="80" y2="260"></line> <line stroke="black" stroke-width="2" x1="350" x2="350" y1="260" y2="420"></line> <circle cx="230" cy="80" fill="white" r="34" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="18" text-anchor="middle" x="230" y="88">0</text> <text fill="black" font-size="16" text-anchor="middle" x="230" y="132">5</text> <circle cx="110" cy="260" fill="white" r="34" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="18" text-anchor="middle" x="110" y="268">1</text> <text fill="black" font-size="16" text-anchor="middle" x="110" y="312">8</text> <circle cx="350" cy="260" fill="white" r="34" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="18" text-anchor="middle" x="350" y="268">2</text> <text fill="black" font-size="16" text-anchor="middle" x="398" y="266">2</text> <circle cx="350" cy="420" fill="white" r="34" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="18" text-anchor="middle" x="350" y="428">3</text> <text fill="black" font-size="16" text-anchor="middle" x="350" y="472">1</text> </svg>

<p>The optimal choice is to treat task 1 as the root.</p>

<ul>
	<li>Task 3 is a leaf, so its finish time is <code>baseTime[3] = 1</code>.</li>
	<li>Task 2 has one child task 3:
	<ul>
		<li><code>earliest = latest = 1</code></li>
		<li><code>ownDuration = (latest - earliest) + baseTime[2] = 0 + 2 = 2</code></li>
		<li>Finish time of task 2 is <code>latest + ownDuration = 1 + 2 = 3</code></li>
	</ul>
	</li>
	<li>Task 0 has one child task 2:
	<ul>
		<li><code>earliest = latest = 3</code></li>
		<li><code>ownDuration = (latest - earliest) + baseTime[0] = 0 + 5 = 5</code></li>
		<li>Finish time of task 0 is <code>latest + ownDuration = 3 + 5 = 8</code></li>
	</ul>
	</li>
	<li>Task 1 has one child task 0:
	<ul>
		<li><code>earliest = latest = 8</code></li>
		<li><code>ownDuration = (latest - earliest) + baseTime[1] = 0 + 8 = 8</code></li>
		<li>Finish time of task 1 is <code>latest + ownDuration = 8 + 8 = 16</code></li>
	</ul>
	</li>
</ul>

<p>Thus, the minimum possible finish time among all choices of root is 16.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length = n - 1</code></li>
	<li><code>edges[i] == [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li>The input is generated such that <code>edges</code> represents a valid undirected tree.</li>
	<li><code>baseTime.length == n</code></li>
	<li><code>1 &lt;= baseTime[i] &lt;= 10<sup>5</sup></code></li>
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
