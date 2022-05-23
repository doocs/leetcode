# [1548. The Most Similar Path in a Graph](https://leetcode.com/problems/the-most-similar-path-in-a-graph)

[中文文档](/solution/1500-1599/1548.The%20Most%20Similar%20Path%20in%20a%20Graph/README.md)

## Description

<p>We have <code>n</code> cities and <code>m</code> bi-directional <code>roads</code> where <code>roads[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> connects city <code>a<sub>i</sub></code> with city <code>b<sub>i</sub></code>. Each city has a name consisting of exactly three upper-case English letters given in the string array <code>names</code>. Starting at any city <code>x</code>, you can reach any city <code>y</code> where <code>y != x</code> (i.e., the cities and the roads are forming an undirected connected graph).</p>

<p>You will be given a string array <code>targetPath</code>. You should find a path in the graph of the <strong>same length</strong> and with the <strong>minimum edit distance</strong> to <code>targetPath</code>.</p>

<p>You need to return <em>the order of the nodes in the path with the minimum edit distance</em>. The&nbsp;path should be of the same length of <code>targetPath</code> and should be valid (i.e., there should be a direct road between <code>ans[i]</code> and <code>ans[i + 1]</code>). If there are multiple answers return any one of them.</p>

<p>The <strong>edit distance</strong> is defined as follows:</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1548.The%20Most%20Similar%20Path%20in%20a%20Graph/images/edit.jpg" style="width: 403px; height: 273px;" />
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1548.The%20Most%20Similar%20Path%20in%20a%20Graph/images/e1.jpg" style="width: 213px; height: 300px;" />
<pre>
<strong>Input:</strong> n = 5, roads = [[0,2],[0,3],[1,2],[1,3],[1,4],[2,4]], names = [&quot;ATL&quot;,&quot;PEK&quot;,&quot;LAX&quot;,&quot;DXB&quot;,&quot;HND&quot;], targetPath = [&quot;ATL&quot;,&quot;DXB&quot;,&quot;HND&quot;,&quot;LAX&quot;]
<strong>Output:</strong> [0,2,4,2]
<strong>Explanation:</strong> [0,2,4,2], [0,3,0,2] and [0,3,1,2] are accepted answers.
[0,2,4,2] is equivalent to [&quot;ATL&quot;,&quot;LAX&quot;,&quot;HND&quot;,&quot;LAX&quot;] which has edit distance = 1 with targetPath.
[0,3,0,2] is equivalent to [&quot;ATL&quot;,&quot;DXB&quot;,&quot;ATL&quot;,&quot;LAX&quot;] which has edit distance = 1 with targetPath.
[0,3,1,2] is equivalent to [&quot;ATL&quot;,&quot;DXB&quot;,&quot;PEK&quot;,&quot;LAX&quot;] which has edit distance = 1 with targetPath.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1548.The%20Most%20Similar%20Path%20in%20a%20Graph/images/e2.jpg" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> n = 4, roads = [[1,0],[2,0],[3,0],[2,1],[3,1],[3,2]], names = [&quot;ATL&quot;,&quot;PEK&quot;,&quot;LAX&quot;,&quot;DXB&quot;], targetPath = [&quot;ABC&quot;,&quot;DEF&quot;,&quot;GHI&quot;,&quot;JKL&quot;,&quot;MNO&quot;,&quot;PQR&quot;,&quot;STU&quot;,&quot;VWX&quot;]
<strong>Output:</strong> [0,1,0,1,0,1,0,1]
<strong>Explanation:</strong> Any path in this graph has edit distance = 8 with targetPath.
</pre>

<p><strong>Example 3:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1548.The%20Most%20Similar%20Path%20in%20a%20Graph/images/e3.jpg" style="width: 600px; height: 106px;" /></strong></p>

<pre>
<strong>Input:</strong> n = 6, roads = [[0,1],[1,2],[2,3],[3,4],[4,5]], names = [&quot;ATL&quot;,&quot;PEK&quot;,&quot;LAX&quot;,&quot;ATL&quot;,&quot;DXB&quot;,&quot;HND&quot;], targetPath = [&quot;ATL&quot;,&quot;DXB&quot;,&quot;HND&quot;,&quot;DXB&quot;,&quot;ATL&quot;,&quot;LAX&quot;,&quot;PEK&quot;]
<strong>Output:</strong> [3,4,5,4,3,2,1]
<strong>Explanation:</strong> [3,4,5,4,3,2,1] is the only path with edit distance = 0 with targetPath.
It&#39;s equivalent to [&quot;ATL&quot;,&quot;DXB&quot;,&quot;HND&quot;,&quot;DXB&quot;,&quot;ATL&quot;,&quot;LAX&quot;,&quot;PEK&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>m == roads.length</code></li>
	<li><code>n - 1 &lt;= m &lt;= (n * (n - 1) / 2)</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>The graph is guaranteed to be <strong>connected</strong> and each pair of nodes may have <strong>at most one</strong> direct road.</li>
	<li><code>names.length == n</code></li>
	<li><code>names[i].length == 3</code></li>
	<li><code>names[i]</code> consists of upper-case English letters.</li>
	<li>There can be two cities with <strong>the same</strong> name.</li>
	<li><code>1 &lt;= targetPath.length &lt;= 100</code></li>
	<li><code>targetPath[i].length == 3</code></li>
	<li><code>targetPath[i]</code> consists of upper-case English letters.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> If each node can be visited only once in the path, What should you change in your solution?</p>

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
