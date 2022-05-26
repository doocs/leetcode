# [1575. Count All Possible Routes](https://leetcode.com/problems/count-all-possible-routes)

[中文文档](/solution/1500-1599/1575.Count%20All%20Possible%20Routes/README.md)

## Description

<p>You are given an array of <strong>distinct</strong> positive integers locations&nbsp;where <code>locations[i]</code> represents the position of city <code>i</code>. You are also given&nbsp;integers&nbsp;<code>start</code>,&nbsp;<code>finish</code>&nbsp;and&nbsp;<code>fuel</code>&nbsp;representing the starting city, ending city, and the initial amount of fuel you have, respectively.</p>

<p>At each step, if you are at city&nbsp;<code>i</code>, you can pick any city&nbsp;<code>j</code>&nbsp;such that <code>j != i</code>&nbsp;and&nbsp;<code>0 &lt;= j &lt; locations.length</code>&nbsp;and move to city <code>j</code>.&nbsp;Moving from city <code>i</code> to city <code>j</code> reduces the amount of fuel you have by&nbsp;<code>|locations[i] - locations[j]|</code>.&nbsp;Please notice that <code>|x|</code>&nbsp;denotes the absolute value of <code>x</code>.</p>

<p>Notice that&nbsp;<code>fuel</code>&nbsp;<strong>cannot</strong> become negative at any point in time, and that you are <strong>allowed</strong> to visit any city more than once (including <code>start</code>&nbsp;and&nbsp;<code>finish</code>).</p>

<p>Return <em>the count of all possible routes from&nbsp;</em><code>start</code>&nbsp;<em>to</em>&nbsp;<code>finish</code>.</p>

<p>Since the answer&nbsp;may be too large,&nbsp;return it modulo&nbsp;<code>10^9 + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> locations = [2,3,6,8,4], start = 1, finish = 3, fuel = 5
<strong>Output:</strong> 4
<strong>Explanation:</strong>&nbsp;The following are all possible routes, each uses 5 units of fuel:
1 -&gt; 3
1 -&gt; 2 -&gt; 3
1 -&gt; 4 -&gt; 3
1 -&gt; 4 -&gt; 2 -&gt; 3
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> locations = [4,3,1], start = 1, finish = 0, fuel = 6
<strong>Output:</strong> 5
<strong>Explanation: </strong>The following are all possible routes:
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
<b>Explanation: </b>It&#39;s impossible to get from 0 to 2 using only 3 units of fuel since the shortest route needs 4 units of fuel.</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> locations = [2,1,5], start = 0, finish = 0, fuel = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong>&nbsp;There are two possible routes, 0 and 0 -&gt; 1 -&gt; 0.</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> locations = [1,2,3], start = 0, finish = 2, fuel = 40
<strong>Output:</strong> 615088286
<strong>Explanation: </strong>The total number of possible routes is 2615088300. Taking this number modulo 10^9 + 7 gives us 615088286.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= locations.length &lt;= 100</code></li>
	<li><code>1 &lt;= locations[i] &lt;= 10^9</code></li>
	<li>All integers in&nbsp;<code>locations</code>&nbsp;are&nbsp;<strong>distinct</strong>.</li>
	<li><code>0 &lt;= start, finish &lt;&nbsp;locations.length</code></li>
	<li><code><font face="monospace">1 &lt;= fuel &lt;= 200</font></code></li>
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
