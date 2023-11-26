# [2943. Maximize Area of Square Hole in Grid](https://leetcode.com/problems/maximize-area-of-square-hole-in-grid)

[中文文档](/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/README.md)

## Description

<p>There is a grid with <code>n + 2</code> <strong>horizontal</strong> bars and <code>m + 2</code> <strong>vertical</strong> bars, and initially containing <code>1 x 1</code> unit cells.</p>

<p>The bars are <strong>1-indexed</strong>.</p>

<p>You are given the two integers, <code>n</code> and <code>m</code>.</p>

<p>You are also given two integer arrays: <code>hBars</code> and <code>vBars</code>.</p>

<ul>
	<li><code>hBars</code> contains <strong>distinct</strong> horizontal bars in the range <code>[2, n + 1]</code>.</li>
	<li><code>vBars</code> contains <strong>distinct</strong> vertical bars in the range <code>[2, m + 1]</code>.</li>
</ul>

<p>You are allowed to <strong>remove</strong> bars that satisfy any of the following conditions:</p>

<ul>
	<li>If it is a horizontal bar, it must correspond to a value in <code>hBars</code>.</li>
	<li>If it is a vertical bar, it must correspond to a value in <code>vBars</code>.</li>
</ul>

<p>Return <em>an integer denoting the <strong>maximum</strong> area of a <strong>square-shaped</strong> hole in the grid after removing some bars (<strong>possibly none</strong>).</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/images/screenshot-from-2023-11-05-22-40-25.png" style="width: 411px; height: 220px;" /></p>

<pre>
<strong>Input:</strong> n = 2, m = 1, hBars = [2,3], vBars = [2]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The left image shows the initial grid formed by the bars.
The horizontal bars are in the range [1,4], and the vertical bars are in the range [1,3].
It is allowed to remove horizontal bars [2,3] and the vertical bar [2].
One way to get the maximum square-shaped hole is by removing horizontal bar 2 and vertical bar 2.
The resulting grid is shown on the right.
The hole has an area of 4.
It can be shown that it is not possible to get a square hole with an area more than 4.
Hence, the answer is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/images/screenshot-from-2023-11-04-17-01-02.png" style="width: 368px; height: 145px;" /></p>

<pre>
<strong>Input:</strong> n = 1, m = 1, hBars = [2], vBars = [2]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The left image shows the initial grid formed by the bars.
The horizontal bars are in the range [1,3], and the vertical bars are in the range [1,3].
It is allowed to remove the horizontal bar [2] and the vertical bar [2].
To get the maximum square-shaped hole, we remove horizontal bar 2 and vertical bar 2.
The resulting grid is shown on the right.
The hole has an area of 4.
Hence, the answer is 4, and it is the maximum possible.
</pre>

<p><strong class="example">Example 3:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/images/screenshot-from-2023-11-05-22-33-35.png" style="width: 648px; height: 218px;" /></p>

<pre>
<strong>Input:</strong> n = 2, m = 3, hBars = [2,3], vBars = [2,3,4]
<strong>Output:</strong> 9
<strong>Explanation:</strong> The left image shows the initial grid formed by the bars.
The horizontal bars are in the range [1,4], and the vertical bars are in the range [1,5].
It is allowed to remove horizontal bars [2,3] and vertical bars [2,3,4].
One way to get the maximum square-shaped hole is by removing horizontal bars 2 and 3, and vertical bars 3 and 4.
The resulting grid is shown on the right.
The hole has an area of 9.
It can be shown that it is not possible to get a square hole with an area more than 9.
Hence, the answer is 9.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= hBars.length &lt;= 100</code></li>
	<li><code>2 &lt;= hBars[i] &lt;= n + 1</code></li>
	<li><code>1 &lt;= vBars.length &lt;= 100</code></li>
	<li><code>2 &lt;= vBars[i] &lt;= m + 1</code></li>
	<li>All values in <code>hBars</code> are distinct.</li>
	<li>All values in <code>vBars</code> are distinct.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
