# [1036. Escape a Large Maze](https://leetcode.com/problems/escape-a-large-maze)

[中文文档](/solution/1000-1099/1036.Escape%20a%20Large%20Maze/README.md)

## Description

<p>In a 1 million by 1 million grid, the coordinates of each grid square are <code>(x, y)</code> with <code>0 &lt;= x, y &lt; 10^6</code>.</p>

<p>We start at the <code>source</code> square and want to reach the <code>target</code> square.&nbsp; Each move, we can walk to a 4-directionally adjacent square in the grid that isn&#39;t in the given list of <code>blocked</code> squares.</p>

<p>Return <code>true</code> if and only if it is possible to reach the target square through a sequence of moves.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>blocked = <span id="example-input-1-1">[[0,1],[1,0]]</span>, source = <span id="example-input-1-2">[0,0]</span>, target = <span id="example-input-1-3">[0,2]</span>

<strong>Output: </strong><span id="example-output-1">false</span>

<strong>Explanation: </strong>

The target square is inaccessible starting from the source square, because we can&#39;t walk outside the grid.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>blocked = <span id="example-input-2-1">[]</span>, source = <span id="example-input-2-2">[0,0]</span>, target = <span id="example-input-2-3">[999999,999999]</span>

<strong>Output: </strong><span id="example-output-2">true</span>

<strong>Explanation: </strong>

Because there are no blocked cells, it&#39;s possible to reach the target square.

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>

    <li><code>0 &lt;= blocked.length &lt;= 200</code></li>

    <li><code>blocked[i].length == 2</code></li>

    <li><code>0 &lt;= blocked[i][j] &lt; 10^6</code></li>

    <li><code>source.length == target.length == 2</code></li>

    <li><code>0 &lt;= source[i][j], target[i][j] &lt; 10^6</code></li>

    <li><code>source != target</code></li>

</ol>

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
