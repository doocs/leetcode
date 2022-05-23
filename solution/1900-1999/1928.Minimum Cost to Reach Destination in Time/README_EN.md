# [1928. Minimum Cost to Reach Destination in Time](https://leetcode.com/problems/minimum-cost-to-reach-destination-in-time)

[中文文档](/solution/1900-1999/1928.Minimum%20Cost%20to%20Reach%20Destination%20in%20Time/README.md)

## Description

<p>There is a country of <code>n</code> cities numbered from <code>0</code> to <code>n - 1</code> where <strong>all the cities are connected</strong> by bi-directional roads. The roads are represented as a 2D integer array <code>edges</code> where <code>edges[i] = [x<sub>i</sub>, y<sub>i</sub>, time<sub>i</sub>]</code> denotes a road between cities <code>x<sub>i</sub></code> and <code>y<sub>i</sub></code> that takes <code>time<sub>i</sub></code> minutes to travel. There may be multiple roads of differing travel times connecting the same two cities, but no road connects a city to itself.</p>

<p>Each time you pass through a city, you must pay a passing fee. This is represented as a <strong>0-indexed</strong> integer array <code>passingFees</code> of length <code>n</code> where <code>passingFees[j]</code> is the amount of dollars you must pay when you pass through city <code>j</code>.</p>

<p>In the beginning, you are at city <code>0</code> and want to reach city <code>n - 1</code> in <code>maxTime</code><strong> minutes or less</strong>. The <strong>cost</strong> of your journey is the <strong>summation of passing fees</strong> for each city that you passed through at some moment of your journey (<strong>including</strong> the source and destination cities).</p>

<p>Given <code>maxTime</code>, <code>edges</code>, and <code>passingFees</code>, return <em>the <strong>minimum cost</strong> to complete your journey, or </em><code>-1</code><em> if you cannot complete it within </em><code>maxTime</code><em> minutes</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1928.Minimum%20Cost%20to%20Reach%20Destination%20in%20Time/images/leetgraph1-1.png" style="width: 371px; height: 171px;" /></p>

<pre>
<strong>Input:</strong> maxTime = 30, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
<strong>Output:</strong> 11
<strong>Explanation:</strong> The path to take is 0 -&gt; 1 -&gt; 2 -&gt; 5, which takes 30 minutes and has $11 worth of passing fees.
</pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1928.Minimum%20Cost%20to%20Reach%20Destination%20in%20Time/images/copy-of-leetgraph1-1.png" style="width: 371px; height: 171px;" /></strong></p>

<pre>
<strong>Input:</strong> maxTime = 29, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
<strong>Output:</strong> 48
<strong>Explanation:</strong> The path to take is 0 -&gt; 3 -&gt; 4 -&gt; 5, which takes 26 minutes and has $48 worth of passing fees.
You cannot take path 0 -&gt; 1 -&gt; 2 -&gt; 5 since it would take too long.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> maxTime = 25, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is no way to reach city 5 from city 0 within 25 minutes.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= maxTime &lt;= 1000</code></li>
	<li><code>n == passingFees.length</code></li>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>n - 1 &lt;= edges.length &lt;= 1000</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>1 &lt;= time<sub>i</sub> &lt;= 1000</code></li>
	<li><code>1 &lt;= passingFees[j] &lt;= 1000</code>&nbsp;</li>
	<li>The graph may contain multiple edges between two nodes.</li>
	<li>The graph does not contain self loops.</li>
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
