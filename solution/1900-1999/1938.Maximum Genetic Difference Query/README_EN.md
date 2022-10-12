# [1938. Maximum Genetic Difference Query](https://leetcode.com/problems/maximum-genetic-difference-query)

[中文文档](/solution/1900-1999/1938.Maximum%20Genetic%20Difference%20Query/README.md)

## Description

<p>There is a rooted tree consisting of <code>n</code> nodes numbered <code>0</code> to <code>n - 1</code>. Each node&#39;s number denotes its <strong>unique genetic value</strong> (i.e. the genetic value of node <code>x</code> is <code>x</code>). The <strong>genetic difference</strong> between two genetic values is defined as the <strong>bitwise-</strong><strong>XOR</strong> of their values. You are given the integer array <code>parents</code>, where <code>parents[i]</code> is the parent for node <code>i</code>. If node <code>x</code> is the <strong>root</strong> of the tree, then <code>parents[x] == -1</code>.</p>

<p>You are also given the array <code>queries</code> where <code>queries[i] = [node<sub>i</sub>, val<sub>i</sub>]</code>. For each query <code>i</code>, find the <strong>maximum genetic difference</strong> between <code>val<sub>i</sub></code> and <code>p<sub>i</sub></code>, where <code>p<sub>i</sub></code> is the genetic value of any node that is on the path between <code>node<sub>i</sub></code> and the root (including <code>node<sub>i</sub></code> and the root). More formally, you want to maximize <code>val<sub>i</sub> XOR p<sub>i</sub></code>.</p>

<p>Return <em>an array </em><code>ans</code><em> where </em><code>ans[i]</code><em> is the answer to the </em><code>i<sup>th</sup></code><em> query</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1938.Maximum%20Genetic%20Difference%20Query/images/c1.png" style="width: 118px; height: 163px;" />
<pre>
<strong>Input:</strong> parents = [-1,0,1,1], queries = [[0,2],[3,2],[2,5]]
<strong>Output:</strong> [2,3,7]
<strong>Explanation: </strong>The queries are processed as follows:
- [0,2]: The node with the maximum genetic difference is 0, with a difference of 2 XOR 0 = 2.
- [3,2]: The node with the maximum genetic difference is 1, with a difference of 2 XOR 1 = 3.
- [2,5]: The node with the maximum genetic difference is 2, with a difference of 5 XOR 2 = 7.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1938.Maximum%20Genetic%20Difference%20Query/images/c2.png" style="width: 256px; height: 221px;" />
<pre>
<strong>Input:</strong> parents = [3,7,-1,2,0,7,0,2], queries = [[4,6],[1,15],[0,5]]
<strong>Output:</strong> [6,14,7]
<strong>Explanation: </strong>The queries are processed as follows:
- [4,6]: The node with the maximum genetic difference is 0, with a difference of 6 XOR 0 = 6.
- [1,15]: The node with the maximum genetic difference is 1, with a difference of 15 XOR 1 = 14.
- [0,5]: The node with the maximum genetic difference is 2, with a difference of 5 XOR 2 = 7.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= parents.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= parents[i] &lt;= parents.length - 1</code> for every node <code>i</code> that is <strong>not</strong> the root.</li>
	<li><code>parents[root] == -1</code></li>
	<li><code>1 &lt;= queries.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= node<sub>i</sub> &lt;= parents.length - 1</code></li>
	<li><code>0 &lt;= val<sub>i</sub> &lt;= 2 * 10<sup>5</sup></code></li>
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
