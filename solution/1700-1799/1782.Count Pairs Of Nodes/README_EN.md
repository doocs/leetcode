# [1782. Count Pairs Of Nodes](https://leetcode.com/problems/count-pairs-of-nodes)

[中文文档](/solution/1700-1799/1782.Count%20Pairs%20Of%20Nodes/README.md)

## Description

<p>You are given an undirected graph represented by an integer <code>n</code>, which is the number of nodes, and <code>edges</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> which indicates that there is an undirected edge between <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>. You are also given an integer array <code>queries</code>.</p>

<p>The answer to the <code>j<sup>th</sup></code> query is the number of pairs of nodes <code>(a, b)</code> that satisfy the following conditions:</p>

<ul>
	<li><code>a &lt; b</code></li>
	<li><code>cnt</code> is <strong>strictly greater</strong> than <code>queries[j]</code>, where <code>cnt</code> is the number of edges incident to <code>a</code> <strong>or</strong> <code>b</code>.</li>
</ul>

<p>Return an array <code>answers</code> such that <code>answers.length == queries.length</code> and <code>answers[j]</code> is the answer of the <code>j<sup>th</sup></code> query.</p>

<p>Note that there can be <strong>repeated edges</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1782.Count%20Pairs%20Of%20Nodes/images/screenshot-from-2021-02-11-23-07-35.png" style="width: 310px; height: 278px;" />
<pre>
<strong>Input:</strong> n = 4, edges = [[1,2],[2,4],[1,3],[2,3],[2,1]], queries = [2,3]
<strong>Output:</strong> [6,5]
<strong>Explanation:</strong> The number of edges incident to at least one of each pair is shown above.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 5, edges = [[1,5],[1,5],[3,4],[2,5],[1,3],[5,1],[2,3],[2,5]], queries = [1,2,3,4,5]
<strong>Output:</strong> [10,10,9,8,6]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= edges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n</code></li>
	<li><code>u<sub>i </sub>!= v<sub>i</sub></code></li>
	<li><code>1 &lt;= queries.length &lt;= 20</code></li>
	<li><code>0 &lt;= queries[j] &lt; edges.length</code></li>
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
