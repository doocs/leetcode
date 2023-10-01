# [2876. Count Visited Nodes in a Directed Graph](https://leetcode.com/problems/count-visited-nodes-in-a-directed-graph)

[中文文档](/solution/2800-2899/2876.Count%20Visited%20Nodes%20in%20a%20Directed%20Graph/README.md)

## Description

<p>There is a <strong>directed</strong> graph consisting of <code>n</code> nodes numbered from <code>0</code> to <code>n - 1</code> and <code>n</code> directed edges.</p>

<p>You are given a <strong>0-indexed</strong> array <code>edges</code> where <code>edges[i]</code> indicates that there is an edge from node <code>i</code> to node <code>edges[i]</code>.</p>

<p>Consider the following process on the graph:</p>

<ul>
	<li>You start from a node <code>x</code> and keep visiting other nodes through edges until you reach a node that you have already visited before on this <strong>same</strong> process.</li>
</ul>

<p>Return <em>an array </em><code>answer</code><em> where </em><code>answer[i]</code><em> is the number of <strong>different</strong> nodes that you will visit if you perform the process starting from node </em><code>i</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2876.Count%20Visited%20Nodes%20in%20a%20Directed%20Graph/images/graaphdrawio-1.png" />
<pre>
<strong>Input:</strong> edges = [1,2,0,0]
<strong>Output:</strong> [3,3,3,4]
<strong>Explanation:</strong> We perform the process starting from each node in the following way:
- Starting from node 0, we visit the nodes 0 -&gt; 1 -&gt; 2 -&gt; 0. The number of different nodes we visit is 3.
- Starting from node 1, we visit the nodes 1 -&gt; 2 -&gt; 0 -&gt; 1. The number of different nodes we visit is 3.
- Starting from node 2, we visit the nodes 2 -&gt; 0 -&gt; 1 -&gt; 2. The number of different nodes we visit is 3.
- Starting from node 3, we visit the nodes 3 -&gt; 0 -&gt; 1 -&gt; 2 -&gt; 0. The number of different nodes we visit is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2876.Count%20Visited%20Nodes%20in%20a%20Directed%20Graph/images/graaph2drawio.png" style="width: 191px; height: 251px;" />
<pre>
<strong>Input:</strong> edges = [1,2,3,4,0]
<strong>Output:</strong> [5,5,5,5,5]
<strong>Explanation:</strong> Starting from any node we can visit every node in the graph in the process.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == edges.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges[i] &lt;= n - 1</code></li>
	<li><code>edges[i] != i</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
