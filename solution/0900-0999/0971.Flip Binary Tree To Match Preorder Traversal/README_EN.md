# [971. Flip Binary Tree To Match Preorder Traversal](https://leetcode.com/problems/flip-binary-tree-to-match-preorder-traversal)

[中文文档](/solution/0900-0999/0971.Flip%20Binary%20Tree%20To%20Match%20Preorder%20Traversal/README.md)

## Description
<p>Given a binary tree with <code>N</code> nodes, each node has a different value from&nbsp;<code>{1, ..., N}</code>.</p>



<p>A node in this binary tree can be <em>flipped</em>&nbsp;by swapping the left child and the right child of that node.</p>



<p>Consider the sequence of&nbsp;<code>N</code> values reported by a preorder traversal starting from the root.&nbsp; Call such a sequence of <code>N</code> values the&nbsp;<em>voyage</em>&nbsp;of the tree.</p>



<p>(Recall that a <em>preorder traversal</em>&nbsp;of a node means we report the current node&#39;s value, then preorder-traverse the left child, then preorder-traverse the right child.)</p>



<p>Our goal is to flip the <strong>least number</strong> of nodes in the tree so that the voyage of the tree matches the <code>voyage</code> we are given.</p>



<p>If we can do so, then return a&nbsp;list&nbsp;of the values of all nodes flipped.&nbsp; You may return the answer in any order.</p>



<p>If we cannot do so, then return the list <code>[-1]</code>.</p>



<p>&nbsp;</p>



<div>

<p><strong>Example 1:</strong></p>



<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2019/01/02/1219-01.png" style="width: 88px; height: 120px;" /></strong></p>



<pre>

<strong>Input: </strong>root = <span id="example-input-1-1">[1,2]</span>, voyage = <span id="example-input-1-2">[2,1]</span>

<strong>Output: </strong><span id="example-output-1">[-1]</span>

</pre>



<div>

<p><strong>Example 2:</strong></p>



<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2019/01/02/1219-02.png" style="width: 127px; height: 120px;" /></strong></p>



<pre>

<strong>Input: </strong>root = <span id="example-input-2-1">[1,2,3]</span>, voyage = <span id="example-input-2-2">[1,3,2]</span>

<strong>Output: </strong><span id="example-output-2">[1]</span>

</pre>



<div>

<p><strong>Example 3:</strong></p>



<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2019/01/02/1219-02.png" style="width: 127px; height: 120px;" /></strong></p>



<pre>

<strong>Input: </strong>root = <span id="example-input-3-1">[1,2,3]</span>, voyage = <span id="example-input-3-2">[1,2,3]</span>

<strong>Output: </strong><span id="example-output-3">[]</span>

</pre>



<p>&nbsp;</p>



<p><strong><span>Note:</span></strong></p>



<ol>

	<li><code>1 &lt;= N &lt;= 100</code></li>

</ol>

</div>

</div>

</div>




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