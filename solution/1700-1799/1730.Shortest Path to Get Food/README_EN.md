# [1730. Shortest Path to Get Food](https://leetcode.com/problems/shortest-path-to-get-food)

[中文文档](/solution/1700-1799/1730.Shortest%20Path%20to%20Get%20Food/README.md)

## Description

<p>You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.</p>

<p>You are given an <code>m x n</code> character matrix, <code>grid</code>, of these different types of cells:</p>

<ul>
	<li><code>&#39;*&#39;</code> is your location. There is <strong>exactly one </strong><code>&#39;*&#39;</code> cell.</li>
	<li><code>&#39;#&#39;</code> is a food cell. There may be <strong>multiple</strong> food cells.</li>
	<li><code>&#39;O&#39;</code> is free space, and you can travel through these cells.</li>
	<li><code>&#39;X&#39;</code> is an obstacle, and you cannot travel through these cells.</li>
</ul>

<p>You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.</p>

<p>Return <em>the <strong>length</strong> of the shortest path for you to reach <strong>any</strong> food cell</em>. If there is no path for you to reach food, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1730.Shortest%20Path%20to%20Get%20Food/images/img1.jpg" style="width: 300px; height: 201px;" />
<pre>
<strong>Input:</strong> grid = [[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;*&quot;,&quot;O&quot;,&quot;O&quot;,&quot;O&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;O&quot;,&quot;O&quot;,&quot;#&quot;,&quot;O&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> It takes 3 steps to reach the food.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1730.Shortest%20Path%20to%20Get%20Food/images/img2.jpg" style="width: 300px; height: 241px;" />
<pre>
<strong>Input:</strong> grid = [[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;*&quot;,&quot;X&quot;,&quot;O&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;O&quot;,&quot;X&quot;,&quot;#&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> It is not possible to reach the food.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1730.Shortest%20Path%20to%20Get%20Food/images/img3.jpg" style="width: 300px; height: 188px;" />
<pre>
<strong>Input:</strong> grid = [[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;*&quot;,&quot;O&quot;,&quot;X&quot;,&quot;O&quot;,&quot;#&quot;,&quot;O&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;O&quot;,&quot;O&quot;,&quot;X&quot;,&quot;O&quot;,&quot;O&quot;,&quot;X&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;O&quot;,&quot;O&quot;,&quot;O&quot;,&quot;O&quot;,&quot;#&quot;,&quot;O&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;]]
<strong>Output:</strong> 6
<strong>Explanation:</strong> There can be multiple food cells. It only takes 6 steps to reach the bottom food.</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> grid = [[&quot;O&quot;,&quot;*&quot;],[&quot;#&quot;,&quot;O&quot;]]
<strong>Output:</strong> 2
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> grid = [[&quot;X&quot;,&quot;*&quot;],[&quot;#&quot;,&quot;X&quot;]]
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>grid[row][col]</code> is <code>&#39;*&#39;</code>, <code>&#39;X&#39;</code>, <code>&#39;O&#39;</code>, or <code>&#39;#&#39;</code>.</li>
	<li>The <code>grid</code> contains <strong>exactly one</strong> <code>&#39;*&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
