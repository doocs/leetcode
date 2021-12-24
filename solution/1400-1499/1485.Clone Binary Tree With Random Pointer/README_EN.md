# [1485. Clone Binary Tree With Random Pointer](https://leetcode.com/problems/clone-binary-tree-with-random-pointer)

[中文文档](/solution/1400-1499/1485.Clone%20Binary%20Tree%20With%20Random%20Pointer/README.md)

## Description

<p>A binary tree is given such that each node contains an additional random pointer which could point to any node in the tree or null.</p>

<p>Return a&nbsp;<a href="https://en.wikipedia.org/wiki/Object_copying#Deep_copy" target="_blank"><strong>deep copy</strong></a>&nbsp;of the tree.</p>

<p>The tree is represented in the same input/output way as normal binary trees where each node is represented as a pair of&nbsp;<code>[val, random_index]</code>&nbsp;where:</p>

<ul>
	<li><code>val</code>: an integer representing&nbsp;<code>Node.val</code></li>
	<li><code>random_index</code>: the index of the node (in the input) where the random pointer points to, or&nbsp;<code>null</code>&nbsp;if it does not point to any node.</li>
</ul>

<p>You will be given the tree in class <code>Node</code> and you should return the cloned tree in class <code>NodeCopy</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1485.Clone%20Binary%20Tree%20With%20Random%20Pointer/images/e1.png" style="width: 750px; height: 410px;" />
<pre>
<strong>Input:</strong> root = [[1,null],null,[4,3],[7,0]]
<strong>Output:</strong> [[1,null],null,[4,3],[7,0]]
<strong>Explanation:</strong> The original binary tree is [1,null,4,7].
The random pointer of node one is null, so it is represented as [1, null].
The random pointer of node 4 is node 7, so it is represented as [4, 3] where 3 is the index of node 7 in the tree array.
The random pointer of node 7 is node 1, so it is represented as [7, 0] where 0 is the index of node 1 in the tree array
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1485.Clone%20Binary%20Tree%20With%20Random%20Pointer/images/e3.png" style="width: 750px; height: 400px;" />
<pre>
<strong>Input:</strong> root = [[1,4],null,[1,0],null,[1,5],[1,5]]
<strong>Output:</strong> [[1,4],null,[1,0],null,[1,5],[1,5]]
<strong>Explanation:</strong> The random pointer of a node can be the node itself.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1485.Clone%20Binary%20Tree%20With%20Random%20Pointer/images/e2.png" style="height: 640px; width: 750px;" />
<pre>
<strong>Input:</strong> root = [[1,6],[2,5],[3,4],[4,3],[5,2],[6,1],[7,0]]
<strong>Output:</strong> [[1,6],[2,5],[3,4],[4,3],[5,2],[6,1],[7,0]]
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> root = []
<strong>Output:</strong> []
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> root = [[1,null],null,[2,null],null,[1,null]]
<strong>Output:</strong> [[1,null],null,[2,null],null,[1,null]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the&nbsp;<code>tree</code>&nbsp;is in the range&nbsp;<code>[0, 1000].</code></li>
	<li>Each node&#39;s value is between&nbsp;<code>[1, 10^6]</code>.</li>
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
