# [2867. Count Valid Paths in a Tree](https://leetcode.com/problems/count-valid-paths-in-a-tree)

[中文文档](/solution/2800-2899/2867.Count%20Valid%20Paths%20in%20a%20Tree/README.md)

## Description

<p>There is an undirected tree with <code>n</code> nodes labeled from <code>1</code> to <code>n</code>. You are given the integer <code>n</code> and a 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates that there is an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> in the tree.</p>

<p>Return <em>the <strong>number of valid paths</strong> in the tree</em>.</p>

<p>A path <code>(a, b)</code> is <strong>valid</strong> if there exists <strong>exactly one</strong> prime number among the node labels in the path from <code>a</code> to <code>b</code>.</p>

<p><strong>Note</strong> that:</p>

<ul>
	<li>The path <code>(a, b)</code> is a sequence of <strong>distinct</strong> nodes starting with node <code>a</code> and ending with node <code>b</code> such that every two adjacent nodes in the sequence share an edge in the tree.</li>
	<li>Path <code>(a, b)</code> and path <code>(b, a)</code> are considered the <strong>same</strong> and counted only <strong>once</strong>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2867.Count%20Valid%20Paths%20in%20a%20Tree/images/example1.png" style="width: 440px; height: 357px;" />
<pre>
<strong>Input:</strong> n = 5, edges = [[1,2],[1,3],[2,4],[2,5]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The pairs with exactly one prime number on the path between them are: 
- (1, 2) since the path from 1 to 2 contains prime number 2. 
- (1, 3) since the path from 1 to 3 contains prime number 3.
- (1, 4) since the path from 1 to 4 contains prime number 2.
- (2, 4) since the path from 2 to 4 contains prime number 2.
It can be shown that there are only 4 valid paths.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2867.Count%20Valid%20Paths%20in%20a%20Tree/images/example2.png" style="width: 488px; height: 384px;" />
<pre>
<strong>Input:</strong> n = 6, edges = [[1,2],[1,3],[2,4],[3,5],[3,6]]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The pairs with exactly one prime number on the path between them are: 
- (1, 2) since the path from 1 to 2 contains prime number 2.
- (1, 3) since the path from 1 to 3 contains prime number 3.
- (1, 4) since the path from 1 to 4 contains prime number 2.
- (1, 6) since the path from 1 to 6 contains prime number 3.
- (2, 4) since the path from 2 to 4 contains prime number 2.
- (3, 6) since the path from 3 to 6 contains prime number 3.
It can be shown that there are only 6 valid paths.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n</code></li>
	<li>The input is generated such that <code>edges</code> represent a valid tree.</li>
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
