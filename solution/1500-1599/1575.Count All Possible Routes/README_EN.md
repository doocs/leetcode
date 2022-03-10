# [1575. Count All Possible Routes](https://leetcode.com/problems/count-all-possible-routes)

[中文文档](/solution/1500-1599/1575.Count%20All%20Possible%20Routes/README.md)

## Description

<p>You are given an array of <strong>distinct</strong> positive integers locations where <code>locations[i]</code> represents the position of city <code>i</code>. You are also given integers <code>start</code>, <code>finish</code> and <code>fuel</code> representing the starting city, ending city, and the initial amount of fuel you have, respectively.</p>

<p>At each step, if you are at city <code>i</code>, you can pick any city <code>j</code> such that <code>j != i</code> and <code>0 &lt;= j &lt; locations.length</code> and move to city <code>j</code>. Moving from city <code>i</code> to city <code>j</code> reduces the amount of fuel you have by <code>|locations[i] - locations[j]|</code>. Please notice that <code>|x|</code> denotes the absolute value of <code>x</code>.</p>

<p>Notice that <code>fuel</code> <strong>cannot</strong> become negative at any point in time, and that you are <strong>allowed</strong> to visit any city more than once (including <code>start</code> and <code>finish</code>).</p>

<p>Return <em>the count of all possible routes from </em><code>start</code> <em>to</em> <code>finish</code>. Since the answer may be too large, return it modulo <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> locations = [2,3,6,8,4], start = 1, finish = 3, fuel = 5
<strong>Output:</strong> 4
<strong>Explanation:</strong> The following are all possible routes, each uses 5 units of fuel:
1 -&gt; 3
1 -&gt; 2 -&gt; 3
1 -&gt; 4 -&gt; 3
1 -&gt; 4 -&gt; 2 -&gt; 3
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> locations = [4,3,1], start = 1, finish = 0, fuel = 6
<strong>Output:</strong> 5
<strong>Explanation:</strong> The following are all possible routes:
1 -&gt; 0, used fuel = 1
1 -&gt; 2 -&gt; 0, used fuel = 5
1 -&gt; 2 -&gt; 1 -&gt; 0, used fuel = 5
1 -&gt; 0 -&gt; 1 -&gt; 0, used fuel = 3
1 -&gt; 0 -&gt; 1 -&gt; 0 -&gt; 1 -&gt; 0, used fuel = 5
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> locations = [5,2,1], start = 0, finish = 2, fuel = 3
<strong>Output:</strong> 0
<strong>Explanation:</strong> It is impossible to get from 0 to 2 using only 3 units of fuel since the shortest route needs 4 units of fuel.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= locations.length &lt;= 100</code></li>
	<li><code>1 &lt;= locations[i] &lt;= 10<sup>9</sup></code></li>
	<li>All integers in <code>locations</code> are <strong>distinct</strong>.</li>
	<li><code>0 &lt;= start, finish &lt; locations.length</code></li>
	<li><code>1 &lt;= fuel &lt;= 200</code></li>
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
