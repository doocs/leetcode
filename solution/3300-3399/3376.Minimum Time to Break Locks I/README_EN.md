---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3376.Minimum%20Time%20to%20Break%20Locks%20I/README_EN.md
---

<!-- problem:start -->

# [3376. Minimum Time to Break Locks I](https://leetcode.com/problems/minimum-time-to-break-locks-i)

[中文文档](/solution/3300-3399/3376.Minimum%20Time%20to%20Break%20Locks%20I/README.md)

## Description

<!-- description:start -->

<p>Bob is stuck in a dungeon and must break <code>n</code> locks, each requiring some amount of <strong>energy</strong> to break. The required energy for each lock is stored in an array called <code>strength</code> where <code>strength[i]</code> indicates the energy needed to break the <code>i<sup>th</sup></code> lock.</p>

<p>To break a lock, Bob uses a sword with the following characteristics:</p>

<ul>
	<li>The initial energy of the sword is 0.</li>
	<li>The initial factor <code><font face="monospace">X</font></code> by which the energy of the sword increases is 1.</li>
	<li>Every minute, the energy of the sword increases by the current factor <code>X</code>.</li>
	<li>To break the <code>i<sup>th</sup></code> lock, the energy of the sword must reach <strong>at least</strong> <code>strength[i]</code>.</li>
	<li>After breaking a lock, the energy of the sword resets to 0, and the factor <code>X</code> increases by a given value <code>K</code>.</li>
</ul>

<p>Your task is to determine the <strong>minimum</strong> time in minutes required for Bob to break all <code>n</code> locks and escape the dungeon.</p>

<p>Return the <strong>minimum </strong>time required for Bob to break all <code>n</code> locks.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">strength = [3,4,1], K = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Time</th>
			<th style="border: 1px solid black;">Energy</th>
			<th style="border: 1px solid black;">X</th>
			<th style="border: 1px solid black;">Action</th>
			<th style="border: 1px solid black;">Updated X</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">Nothing</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">Break 3<sup>rd</sup> Lock</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">Nothing</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">Break 2<sup>nd</sup> Lock</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">Break 1<sup>st</sup> Lock</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
	</tbody>
</table>

<p>The locks cannot be broken in less than 4 minutes; thus, the answer is 4.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">strength = [2,5,4], K = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Time</th>
			<th style="border: 1px solid black;">Energy</th>
			<th style="border: 1px solid black;">X</th>
			<th style="border: 1px solid black;">Action</th>
			<th style="border: 1px solid black;">Updated X</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">Nothing</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">Nothing</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">Break 1<sup>st</sup> Lock</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">Nothing</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">Break 2<sup>n</sup><sup>d</sup> Lock</td>
			<td style="border: 1px solid black;">5</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">Break 3<sup>r</sup><sup>d</sup> Lock</td>
			<td style="border: 1px solid black;">7</td>
		</tr>
	</tbody>
</table>

<p>The locks cannot be broken in less than 5 minutes; thus, the answer is 5.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == strength.length</code></li>
	<li><code>1 &lt;= n &lt;= 8</code></li>
	<li><code>1 &lt;= K &lt;= 10</code></li>
	<li><code>1 &lt;= strength[i] &lt;= 10<sup>6</sup></code></li>
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
