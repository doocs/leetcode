# [1627. Graph Connectivity With Threshold](https://leetcode.com/problems/graph-connectivity-with-threshold)

[中文文档](/solution/1600-1699/1627.Graph%20Connectivity%20With%20Threshold/README.md)

## Description

<p>We have <code>n</code> cities labeled from <code>1</code> to <code>n</code>. Two different cities with labels <code>x</code> and <code>y</code> are directly connected by a bidirectional road if and only if <code>x</code> and <code>y</code> share a common divisor <strong>strictly greater</strong> than some <code>threshold</code>. More formally, cities with labels <code>x</code> and <code>y</code> have a road between them if there exists an integer <code>z</code> such that all of the following are true:</p>

<ul>
	<li><code>x % z == 0</code>,</li>
	<li><code>y % z == 0</code>, and</li>
	<li><code>z &gt; threshold</code>.</li>
</ul>

<p>Given the two integers, <code>n</code> and <code>threshold</code>, and an array of <code>queries</code>, you must determine for each <code>queries[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> if cities <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> are connected directly or indirectly.&nbsp;(i.e. there is some path between them).</p>

<p>Return <em>an array </em><code>answer</code><em>, where </em><code>answer.length == queries.length</code><em> and </em><code>answer[i]</code><em> is </em><code>true</code><em> if for the </em><code>i<sup>th</sup></code><em> query, there is a path between </em><code>a<sub>i</sub></code><em> and </em><code>b<sub>i</sub></code><em>, or </em><code>answer[i]</code><em> is </em><code>false</code><em> if there is no path.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1627.Graph%20Connectivity%20With%20Threshold/images/ex1.jpg" style="width: 382px; height: 181px;" />
<pre>
<strong>Input:</strong> n = 6, threshold = 2, queries = [[1,4],[2,5],[3,6]]
<strong>Output:</strong> [false,false,true]
<strong>Explanation:</strong> The divisors for each number:
1:   1
2:   1, 2
3:   1, <u>3</u>
4:   1, 2, <u>4</u>
5:   1, <u>5</u>
6:   1, 2, <u>3</u>, <u>6</u>
Using the underlined divisors above the threshold, only cities 3 and 6 share a common divisor, so they are the
only ones directly connected. The result of each query:
[1,4]   1 is not connected to 4
[2,5]   2 is not connected to 5
[3,6]   3 is connected to 6 through path 3--6
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1627.Graph%20Connectivity%20With%20Threshold/images/tmp.jpg" style="width: 532px; height: 302px;" />
<pre>
<strong>Input:</strong> n = 6, threshold = 0, queries = [[4,5],[3,4],[3,2],[2,6],[1,3]]
<strong>Output:</strong> [true,true,true,true,true]
<strong>Explanation:</strong> The divisors for each number are the same as the previous example. However, since the threshold is 0,
all divisors can be used. Since all numbers share 1 as a divisor, all cities are connected.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1627.Graph%20Connectivity%20With%20Threshold/images/ex3.jpg" style="width: 282px; height: 282px;" />
<pre>
<strong>Input:</strong> n = 5, threshold = 1, queries = [[4,5],[4,5],[3,2],[2,3],[3,4]]
<strong>Output:</strong> [false,false,false,false,false]
<strong>Explanation:</strong> Only cities 2 and 4 share a common divisor 2 which is strictly greater than the threshold 1, so they are the only ones directly connected.
Please notice that there can be multiple queries for the same pair of nodes [x, y], and that the query [x, y] is equivalent to the query [y, x].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= threshold &lt;= n</code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>1 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= cities</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
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
