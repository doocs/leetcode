# [545. Boundary of Binary Tree](https://leetcode.com/problems/boundary-of-binary-tree)

[中文文档](/solution/0500-0599/0545.Boundary%20of%20Binary%20Tree/README.md)

## Description

<p>The <strong>boundary</strong> of a binary tree is the concatenation of the <strong>root</strong>, the <strong>left boundary</strong>, the <strong>leaves</strong> ordered from left-to-right, and the <strong>reverse order</strong> of the <strong>right boundary</strong>.</p>

<p>The <strong>left boundary</strong> is the set of nodes defined by the following:</p>

<ul>
	<li>The root node&#39;s left child is in the left boundary. If the root does not have a left child, then the left boundary is <strong>empty</strong>.</li>
	<li>If a node in the left boundary and has a left child, then the left child is in the left boundary.</li>
	<li>If a node is in the left boundary, has <strong>no</strong> left child, but has a right child, then the right child is in the left boundary.</li>
	<li>The leftmost leaf is <strong>not</strong> in the left boundary.</li>
</ul>

<p>The <strong>right boundary</strong> is similar to the <strong>left boundary</strong>, except it is the right side of the root&#39;s right subtree. Again, the leaf is <strong>not</strong> part of the <strong>right boundary</strong>, and the <strong>right boundary</strong> is empty if the root does not have a right child.</p>

<p>The <strong>leaves</strong> are nodes that do not have any children. For this problem, the root is <strong>not</strong> a leaf.</p>

<p>Given the <code>root</code> of a binary tree, return <em>the values of its <strong>boundary</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/11/boundary1.jpg" style="width: 299px; height: 290px;" />
<pre>
<strong>Input:</strong> root = [1,null,2,3,4]
<strong>Output:</strong> [1,3,4,2]
<b>Explanation:</b>
- The left boundary is empty because the root does not have a left child.
- The right boundary follows the path starting from the root&#39;s right child 2 -&gt; 4.
  4 is a leaf, so the right boundary is [2].
- The leaves from left to right are [3,4].
Concatenating everything results in [1] + [] + [3,4] + [2] = [1,3,4,2].
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/11/boundary2.jpg" style="width: 599px; height: 411px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,4,5,6,null,null,null,7,8,9,10]
<strong>Output:</strong> [1,2,4,7,8,9,10,6,3]
<b>Explanation:</b>
- The left boundary follows the path starting from the root&#39;s left child 2 -&gt; 4.
  4 is a leaf, so the left boundary is [2].
- The right boundary follows the path starting from the root&#39;s right child 3 -&gt; 6 -&gt; 10.
  10 is a leaf, so the right boundary is [3,6], and in reverse order is [6,3].
- The leaves from left to right are [4,7,8,9,10].
Concatenating everything results in [1] + [2] + [4,7,8,9,10] + [6,3] = [1,2,4,7,8,9,10,6,3].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
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
