# [1263. Minimum Moves to Move a Box to Their Target Location](https://leetcode.com/problems/minimum-moves-to-move-a-box-to-their-target-location)

[中文文档](/solution/1200-1299/1263.Minimum%20Moves%20to%20Move%20a%20Box%20to%20Their%20Target%20Location/README.md)

## Description

<p>Storekeeper is a&nbsp;game&nbsp;in which the player pushes boxes around in a warehouse&nbsp;trying to get them to target locations.</p>

<p>The game is represented by a <code>grid</code> of size&nbsp;<code>m x n</code>, where each element is a wall, floor, or a box.</p>

<p>Your task is move the box <code>&#39;B&#39;</code> to the target position <code>&#39;T&#39;</code> under the following rules:</p>

<ul>
	<li>Player is represented by character <code>&#39;S&#39;</code>&nbsp;and&nbsp;can move up, down, left, right in the <code>grid</code> if it is a floor (empy cell).</li>
	<li>Floor is represented by character <code>&#39;.&#39;</code> that means free cell to walk.</li>
	<li>Wall is represented by character <code>&#39;#&#39;</code> that means obstacle&nbsp;&nbsp;(impossible to walk there).&nbsp;</li>
	<li>There is only one box <code>&#39;B&#39;</code> and one&nbsp;target cell <code>&#39;T&#39;</code> in the <code>grid</code>.</li>
	<li>The box can be moved to an adjacent free cell by standing next to the box and then moving in the direction of the box. This is a <strong>push</strong>.</li>
	<li>The player cannot walk through the box.</li>
</ul>

<p>Return the minimum number of <strong>pushes</strong> to move the box to the target. If there is no way to reach the target, return&nbsp;<code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1263.Minimum%20Moves%20to%20Move%20a%20Box%20to%20Their%20Target%20Location/images/sample_1_1620.png" style="width: 520px; height: 386px;" /></strong></p>

<pre>
<strong>Input:</strong> grid = [[&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;T&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;],
&nbsp;              [&quot;#&quot;,&quot;.&quot;,&quot;.&quot;,&quot;B&quot;,&quot;.&quot;,&quot;#&quot;],
&nbsp;              [&quot;#&quot;,&quot;.&quot;,&quot;#&quot;,&quot;#&quot;,&quot;.&quot;,&quot;#&quot;],
&nbsp;              [&quot;#&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;S&quot;,&quot;#&quot;],
&nbsp;              [&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;]]
<strong>Output:</strong> 3
<strong>Explanation: </strong>We return only the number of times the box is pushed.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;T&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;],
&nbsp;              [&quot;#&quot;,&quot;.&quot;,&quot;.&quot;,&quot;B&quot;,&quot;.&quot;,&quot;#&quot;],
&nbsp;              [&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;.&quot;,&quot;#&quot;],
&nbsp;              [&quot;#&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;S&quot;,&quot;#&quot;],
&nbsp;              [&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;]]
<strong>Output:</strong> -1
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [[&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;],
&nbsp;              [&quot;#&quot;,&quot;T&quot;,&quot;.&quot;,&quot;.&quot;,&quot;#&quot;,&quot;#&quot;],
&nbsp;              [&quot;#&quot;,&quot;.&quot;,&quot;#&quot;,&quot;B&quot;,&quot;.&quot;,&quot;#&quot;],
&nbsp;              [&quot;#&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;#&quot;],
&nbsp;              [&quot;#&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;S&quot;,&quot;#&quot;],
&nbsp;              [&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;]]
<strong>Output:</strong> 5
<strong>Explanation:</strong>  push the box down, left, left, up and up.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> grid = [[&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;],
&nbsp;              [&quot;#&quot;,&quot;S&quot;,&quot;#&quot;,&quot;.&quot;,&quot;B&quot;,&quot;T&quot;,&quot;#&quot;],
&nbsp;              [&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;]]
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m ==&nbsp;grid.length</code></li>
	<li><code>n ==&nbsp;grid[i].length</code></li>
	<li><code>1 &lt;= m &lt;= 20</code></li>
	<li><code>1 &lt;= n &lt;= 20</code></li>
	<li><code>grid</code> contains only characters&nbsp;<code>&#39;.&#39;</code>, <code>&#39;#&#39;</code>,&nbsp; <code>&#39;S&#39;</code> , <code>&#39;T&#39;</code>,&nbsp;or <code>&#39;B&#39;</code>.</li>
	<li>There is only one character&nbsp;<code>&#39;S&#39;</code>, <code>&#39;B&#39;</code>&nbsp;<font face="sans-serif, Arial, Verdana, Trebuchet MS">and&nbsp;</font><code>&#39;T&#39;</code>&nbsp;in the <code>grid</code>.</li>
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
