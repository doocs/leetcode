# [2737. Find the Closest Marked Node](https://leetcode.cn/problems/find-the-closest-marked-node)

[English Version](/solution/2700-2799/2737.Find%20the%20Closest%20Marked%20Node/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given a positive integer <code>n</code> which is the number of nodes of a <strong>0-indexed directed weighted</strong> graph and a <strong>0-indexed</strong> <strong>2D array</strong> <code>edges</code> where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> indicates that there is an edge from node <code>u<sub>i</sub></code> to node <code>v<sub>i</sub></code> with weight <code>w<sub>i</sub></code>.</p>

<p>You are also given a node <code>s</code> and a node array <code>marked</code>; your task is to find the <strong>minimum</strong> distance from <code>s</code> to <strong>any</strong> of the nodes in <code>marked</code>.</p>

<p>Return <em>an integer denoting the minimum distance from </em><code>s</code><em> to any node in </em><code>marked</code><em> or </em><code>-1</code><em> if there are no paths from s to any of the marked nodes</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 4, edges = [[0,1,1],[1,2,3],[2,3,2],[0,3,4]], s = 0, marked = [2,3]
<strong>Output:</strong> 4
<strong>Explanation:</strong> There is one path from node 0 (the green node) to node 2 (a red node), which is 0-&gt;1-&gt;2, and has a distance of 1 + 3 = 4.
There are two paths from node 0 to node 3 (a red node), which are 0-&gt;1-&gt;2-&gt;3 and 0-&gt;3, the first one has a distance of 1 + 3 + 2 = 6 and the second one has a distance of 4.
The minimum of them is 4.
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2737.Find%20the%20Closest%20Marked%20Node/images/image_2023-06-13_16-34-38.png" style="width: 185px; height: 180px;" /></p>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 5, edges = [[0,1,2],[0,2,4],[1,3,1],[2,3,3],[3,4,2]], s = 1, marked = [0,4]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are no paths from node 1 (the green node) to node 0 (a red node).
There is one path from node 1 to node 4 (a red node), which is 1-&gt;3-&gt;4, and has a distance of 1 + 2 = 3.
So the answer is 3.
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2737.Find%20the%20Closest%20Marked%20Node/images/image_2023-06-13_16-35-13.png" style="width: 300px; height: 285px;" /></p>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 4, edges = [[0,1,1],[1,2,3],[2,3,2]], s = 3, marked = [0,1]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There are no paths from node 3 (the green node) to any of the marked nodes (the red nodes), so the answer is -1.
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2737.Find%20the%20Closest%20Marked%20Node/images/image_2023-06-13_16-35-47.png" style="width: 420px; height: 80px;" /></p>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 500</code></li>
	<li><code>1 &lt;= edges.length &lt;= 10<sup>4</sup></code></li>
	<li><code>edges[i].length = 3</code></li>
	<li><code>0 &lt;= edges[i][0], edges[i][1] &lt;= n - 1</code></li>
	<li><code>1 &lt;= edges[i][2] &lt;=&nbsp;10<sup>6</sup></code></li>
	<li><code>1 &lt;= marked.length&nbsp;&lt;= n - 1</code></li>
	<li><code>0 &lt;= s, marked[i]&nbsp;&lt;= n - 1</code></li>
	<li><code>s != marked[i]</code></li>
	<li><code>marked[i] != marked[j]</code> for every <code>i != j</code></li>
	<li>The&nbsp;graph might have&nbsp;<strong>repeated edges</strong>.</li>
	<li>The graph is generated such that it has no&nbsp;<strong>self-loops</strong>.</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
