---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3594.Minimum%20Time%20to%20Transport%20All%20Individuals/README_EN.md
tags:
    - Bit Manipulation
    - Graph
    - Array
    - Dynamic Programming
    - Bitmask
    - Shortest Path
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3594. Minimum Time to Transport All Individuals](https://leetcode.com/problems/minimum-time-to-transport-all-individuals)

[中文文档](/solution/3500-3599/3594.Minimum%20Time%20to%20Transport%20All%20Individuals/README.md)

## Description

<!-- description:start -->

<p>You are given <code>n</code> individuals at a base camp who need to cross a river to reach a destination using a single boat. The boat can carry at most <code>k</code> people at a time. The trip is affected by environmental conditions that vary <strong>cyclically</strong> over <code>m</code> stages.</p>

<p>Each stage <code>j</code> has a speed multiplier <code>mul[j]</code>:</p>

<ul>
	<li>If <code>mul[j] &gt; 1</code>, the trip slows down.</li>
	<li>If <code>mul[j] &lt; 1</code>, the trip speeds up.</li>
</ul>

<p>Each individual <code>i</code> has a rowing strength represented by <code>time[i]</code>, the time (in minutes) it takes them to cross alone in neutral conditions.</p>

<p><strong>Rules:</strong></p>

<ul>
	<li>A group <code>g</code> departing at stage <code>j</code> takes time equal to the <strong>maximum</strong> <code>time[i]</code> among its members, multiplied by <code>mul[j]</code> minutes to reach the destination.</li>
	<li>After the group crosses the river in time <code>d</code>, the stage advances by <code>floor(d) % m</code> steps.</li>
	<li>If individuals are left behind, one person must return with the boat. Let <code>r</code> be the index of the returning person, the return takes <code>time[r] &times; mul[current_stage]</code>, defined as <code>return_time</code>, and the stage advances by <code>floor(return_time) % m</code>.</li>
</ul>

<p>Return the <strong>minimum</strong> total time required to transport all individuals. If it is not possible to transport all individuals to the destination, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1, k = 1, m = 2, time = [5], mul = [1.0,1.3]</span></p>

<p><strong>Output:</strong> <span class="example-io">5.00000</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Individual 0 departs from stage 0, so crossing time = <code>5 &times; 1.00 = 5.00</code> minutes.</li>
	<li>All team members are now at the destination. Thus, the total time taken is <code>5.00</code> minutes.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, k = 2, m = 3, time = [2,5,8], mul = [1.0,1.5,0.75]</span></p>

<p><strong>Output:</strong> <span class="example-io">14.50000</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal strategy is:</p>

<ul>
	<li>Send individuals 0 and 2 from the base camp to the destination from stage 0. The crossing time is <code>max(2, 8) &times; mul[0] = 8 &times; 1.00 = 8.00</code> minutes. The stage advances by <code>floor(8.00) % 3 = 2</code>, so the next stage is <code>(0 + 2) % 3 = 2</code>.</li>
	<li>Individual 0 returns alone from the destination to the base camp from stage 2. The return time is <code>2 &times; mul[2] = 2 &times; 0.75 = 1.50</code> minutes. The stage advances by <code>floor(1.50) % 3 = 1</code>, so the next stage is <code>(2 + 1) % 3 = 0</code>.</li>
	<li>Send individuals 0 and 1 from the base camp to the destination from stage 0. The crossing time is <code>max(2, 5) &times; mul[0] = 5 &times; 1.00 = 5.00</code> minutes. The stage advances by <code>floor(5.00) % 3 = 2</code>, so the final stage is <code>(0 + 2) % 3 = 2</code>.</li>
	<li>All team members are now at the destination. The total time taken is <code>8.00 + 1.50 + 5.00 = 14.50</code> minutes.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, k = 1, m = 2, time = [10,10], mul = [2.0,2.0]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1.00000</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Since the boat can only carry one person at a time, it is impossible to transport both individuals as one must always return. Thus, the answer is <code>-1.00</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == time.length &lt;= 12</code></li>
	<li><code>1 &lt;= k &lt;= 5</code></li>
	<li><code>1 &lt;= m &lt;= 5</code></li>
	<li><code>1 &lt;= time[i] &lt;= 100</code></li>
	<li><code>m == mul.length</code></li>
	<li><code>0.5 &lt;= mul[i] &lt;= 2.0</code></li>
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
