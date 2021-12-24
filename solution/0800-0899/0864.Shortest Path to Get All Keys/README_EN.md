# [864. Shortest Path to Get All Keys](https://leetcode.com/problems/shortest-path-to-get-all-keys)

[中文文档](/solution/0800-0899/0864.Shortest%20Path%20to%20Get%20All%20Keys/README.md)

## Description

<p>We are given a 2-dimensional&nbsp;<code>grid</code>.&nbsp;<code>&quot;.&quot;</code> is an empty cell, <code>&quot;#&quot;</code> is&nbsp;a wall, <code>&quot;@&quot;</code> is the starting point, (<code>&quot;a&quot;</code>, <code>&quot;b&quot;</code>, ...) are keys, and (<code>&quot;A&quot;</code>,&nbsp;<code>&quot;B&quot;</code>, ...) are locks.</p>

<p>We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions.&nbsp; We cannot walk outside the grid, or walk into a wall.&nbsp; If we walk over a key, we pick it up.&nbsp; We can&#39;t walk over a lock unless we have the corresponding key.</p>

<p>For some <font face="monospace">1 &lt;= K &lt;= 6</font>, there is exactly one lowercase and one uppercase letter of the first <code>K</code> letters of the English alphabet in the grid.&nbsp; This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were&nbsp;chosen in the same order as the English alphabet.</p>

<p>Return the lowest number of moves to acquire all keys.&nbsp; If&nbsp;it&#39;s impossible, return <code>-1</code>.</p>

<p>&nbsp;</p>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">[&quot;@.a.#&quot;,&quot;###.#&quot;,&quot;b.A.B&quot;]</span>

<strong>Output: </strong><span id="example-output-1">8</span>

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-2-1">[&quot;@..aA&quot;,&quot;..B#.&quot;,&quot;....b&quot;]</span>

<strong>Output: </strong><span id="example-output-2">6</span>

</pre>

</div>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= grid.length&nbsp;&lt;= 30</code></li>
	<li><code>1 &lt;= grid[0].length&nbsp;&lt;= 30</code></li>
	<li><code>grid[i][j]</code> contains only<code> &#39;.&#39;</code>, <code>&#39;#&#39;</code>, <code>&#39;@&#39;</code>,&nbsp;<code>&#39;a&#39;-</code><code>&#39;f</code><code>&#39;</code> and <code>&#39;A&#39;-&#39;F&#39;</code></li>
	<li>The number of keys is in <code>[1, 6]</code>.&nbsp; Each key has a different letter and opens exactly one lock.</li>
</ol>

</div>

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
