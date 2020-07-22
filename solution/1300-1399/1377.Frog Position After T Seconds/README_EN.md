# [1377. Frog Position After T Seconds](https://leetcode.com/problems/frog-position-after-t-seconds)

[中文文档](/solution/1300-1399/1377.Frog%20Position%20After%20T%20Seconds/README.md)

## Description
<p>Given an undirected tree&nbsp;consisting of <code>n</code> vertices numbered from 1 to <code>n</code>. A frog starts jumping&nbsp;from the <strong>vertex 1</strong>. In one second, the frog&nbsp;jumps from its&nbsp;current&nbsp;vertex to another <strong>unvisited</strong> vertex if they are directly connected. The frog can not jump back to a visited vertex.&nbsp;In case the frog can jump to several vertices it jumps randomly to one of them with the same probability, otherwise, when the frog can not jump to any unvisited vertex it jumps forever on the same vertex.&nbsp;</p>



<p>The edges of the undirected tree&nbsp;are given in the array <code>edges</code>, where <code>edges[i] = [from<sub>i</sub>, to<sub>i</sub>]</code> means that exists an edge connecting directly the vertices <code>from<sub>i</sub></code> and <code>to<sub>i</sub></code>.</p>



<p><em>Return the probability that after <code>t</code> seconds the frog is on the vertex <code><font face="monospace">target</font></code>.</em></p>



<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>



<p><img alt="" src="https://assets.leetcode.com/uploads/2020/02/20/frog_2.png" style="width: 350px; height: 236px;" /></p>



<pre>

<strong>Input:</strong> n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4

<strong>Output:</strong> 0.16666666666666666 

<strong>Explanation: </strong>The figure above shows the given graph. The frog starts at vertex 1, jumping with 1/3 probability to the vertex 2 after <strong>second 1</strong> and then jumping with 1/2 probability to vertex 4 after <strong>second 2</strong>. Thus the probability for the frog is on the vertex 4 after 2 seconds is 1/3 * 1/2 = 1/6 = 0.16666666666666666. 

</pre>



<p><strong>Example 2:</strong></p>



<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2020/02/20/frog_3.png" style="width: 350px; height: 236px;" /></strong></p>



<pre>

<strong>Input:</strong> n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7

<strong>Output:</strong> 0.3333333333333333

<strong>Explanation: </strong>The figure above shows the given graph. The frog starts at vertex 1, jumping with 1/3 = 0.3333333333333333 probability to the vertex 7 after <strong>second 1</strong>. 

</pre>



<p><strong>Example 3:</strong></p>



<pre>

<strong>Input:</strong> n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 20, target = 6

<strong>Output:</strong> 0.16666666666666666

</pre>



<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>



<ul>

	<li><code>1 &lt;= n &lt;= 100</code></li>

	<li><code>edges.length == n-1</code></li>

	<li><code>edges[i].length == 2</code></li>

	<li><code>1 &lt;= edges[i][0], edges[i][1] &lt;= n</code></li>

	<li><code>1 &lt;= t&nbsp;&lt;= 50</code></li>

	<li><code>1 &lt;= target&nbsp;&lt;= n</code></li>

	<li>Answers within <code>10^-5</code> of the actual value will be accepted as correct.</li>

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