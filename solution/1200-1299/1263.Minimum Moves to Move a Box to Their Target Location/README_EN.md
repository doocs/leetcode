# [1263. Minimum Moves to Move a Box to Their Target Location](https://leetcode.com/problems/minimum-moves-to-move-a-box-to-their-target-location)

[中文文档](/solution/1200-1299/1263.Minimum%20Moves%20to%20Move%20a%20Box%20to%20Their%20Target%20Location/README.md)

## Description

<p>A storekeeper is a game in which the player pushes boxes around in a warehouse trying to get them to target locations.</p>

<p>The game is represented by an <code>m x n</code> grid of characters <code>grid</code> where each element is a wall, floor, or box.</p>

<p>Your task is to move the box <code>&#39;B&#39;</code> to the target position <code>&#39;T&#39;</code> under the following rules:</p>

<ul>
	<li>The character <code>&#39;S&#39;</code> represents the player. The player can move up, down, left, right in <code>grid</code> if it is a floor (empty cell).</li>
	<li>The character <code>&#39;.&#39;</code> represents the floor which means a free cell to walk.</li>
	<li>The character <code>&#39;#&#39; </code>represents the wall which means an obstacle (impossible to walk there).</li>
	<li>There is only one box <code>&#39;B&#39;</code> and one target cell <code>&#39;T&#39;</code> in the <code>grid</code>.</li>
	<li>The box can be moved to an adjacent free cell by standing next to the box and then moving in the direction of the box. This is a <strong>push</strong>.</li>
	<li>The player cannot walk through the box.</li>
</ul>

<p>Return <em>the minimum number of <strong>pushes</strong> to move the box to the target</em>. If there is no way to reach the target, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1263.Minimum%20Moves%20to%20Move%20a%20Box%20to%20Their%20Target%20Location/images/sample_1_1620.png" style="width: 500px; height: 335px;" />
<pre>
<strong>Input:</strong> grid = [[&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;T&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;.&quot;,&quot;.&quot;,&quot;B&quot;,&quot;.&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;.&quot;,&quot;#&quot;,&quot;#&quot;,&quot;.&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;S&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We return only the number of times the box is pushed.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;T&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;.&quot;,&quot;.&quot;,&quot;B&quot;,&quot;.&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;.&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;S&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;]]
<strong>Output:</strong> -1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [[&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;T&quot;,&quot;.&quot;,&quot;.&quot;,&quot;#&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;.&quot;,&quot;#&quot;,&quot;B&quot;,&quot;.&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;S&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;]]
<strong>Output:</strong> 5
<strong>Explanation:</strong> push the box down, left, left, up and up.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 20</code></li>
	<li><code>grid</code> contains only characters <code>&#39;.&#39;</code>, <code>&#39;#&#39;</code>, <code>&#39;S&#39;</code>, <code>&#39;T&#39;</code>, or <code>&#39;B&#39;</code>.</li>
	<li>There is only one character <code>&#39;S&#39;</code>, <code>&#39;B&#39;</code>, and <code>&#39;T&#39;</code> in the <code>grid</code>.</li>
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
