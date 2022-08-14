# [1724. Checking Existence of Edge Length Limited Paths II](https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths-ii)

[中文文档](/solution/1700-1799/1724.Checking%20Existence%20of%20Edge%20Length%20Limited%20Paths%20II/README.md)

## Description

<p>An undirected graph of <code>n</code> nodes is defined by <code>edgeList</code>, where <code>edgeList[i] = [u<sub>i</sub>, v<sub>i</sub>, dis<sub>i</sub>]</code> denotes an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> with distance <code>dis<sub>i</sub></code>. Note that there may be <strong>multiple</strong> edges between two nodes, and the graph may not be connected.</p>

<p>Implement the <code>DistanceLimitedPathsExist</code> class:</p>

<ul>
    <li><code>DistanceLimitedPathsExist(int n, int[][] edgeList)</code> Initializes the class with an undirected graph.</li>
    <li><code>boolean query(int p, int q, int limit)</code> Returns <code>true</code> if there exists a path from <code>p</code> to <code>q</code> such that each edge on the path has a distance <strong>strictly less than</strong> <code>limit</code>, and otherwise <code>false</code>.</li>
</ul>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1724.Checking%20Existence%20of%20Edge%20Length%20Limited%20Paths%20II/images/messed.png" style="width: 300px; height: 298px;" /></strong></p>

<pre>


<strong>Input</strong>


[&quot;DistanceLimitedPathsExist&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;]


[[6, [[0, 2, 4], [0, 3, 2], [1, 2, 3], [2, 3, 1], [4, 5, 5]]], [2, 3, 2], [1, 3, 3], [2, 0, 3], [0, 5, 6]]


<strong>Output</strong>


[null, true, false, true, false]





<strong>Explanation</strong>


DistanceLimitedPathsExist distanceLimitedPathsExist = new DistanceLimitedPathsExist(6, [[0, 2, 4], [0, 3, 2], [1, 2, 3], [2, 3, 1], [4, 5, 5]]);


distanceLimitedPathsExist.query(2, 3, 2); // return true. There is an edge from 2 to 3 of distance 1, which is less than 2.


distanceLimitedPathsExist.query(1, 3, 3); // return false. There is no way to go from 1 to 3 with distances <strong>strictly</strong> less than 3.


distanceLimitedPathsExist.query(2, 0, 3); // return true. There is a way to go from 2 to 0 with distance &lt; 3: travel from 2 to 3 to 0.


distanceLimitedPathsExist.query(0, 5, 6); // return false. There are no paths from 0 to 5.


</pre>

<p>&nbsp;</p>

<p><code><strong>Constraints:</strong></code></p>

<ul>

    <li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li>


    <li><code>0 &lt;= edgeList.length &lt;= 10<sup>4</sup></code></li>


    <li><code>edgeList[i].length == 3</code></li>


    <li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub>, p, q &lt;= n-1</code></li>


    <li><code>u<sub>i</sub> != v<sub>i</sub></code></li>


    <li><code>p != q</code></li>


    <li><code>1 &lt;= dis<sub>i</sub>, limit &lt;= 10<sup>9</sup></code></li>


    <li>At most&nbsp;<code>10<sup>4</sup></code> calls will be made to <code>query</code>.</li>

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
