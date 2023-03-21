# [1516. Move Sub-Tree of N-Ary Tree](https://leetcode.com/problems/move-sub-tree-of-n-ary-tree)

[中文文档](/solution/1500-1599/1516.Move%20Sub-Tree%20of%20N-Ary%20Tree/README.md)

## Description

<p>Given the <code>root</code> of an <span data-keyword="n-ary-tree">N-ary tree</span> of unique values, and two nodes of the tree <code>p</code> and <code>q</code>.</p>

<p>You should move the subtree of the node <code>p</code> to become a direct child of node <code>q</code>. If <code>p</code> is already a direct child of <code>q</code>, do not change anything. Node <code>p</code> <strong>must be</strong> the last child in the children list of node <code>q</code>.</p>

<p>Return <em>the root of the tree</em> after adjusting it.</p>

<p>&nbsp;</p>

<p>There are 3 cases for nodes <code>p</code> and <code>q</code>:</p>

<ol>
	<li>Node <code>q</code> is in the sub-tree of node <code>p</code>.</li>
	<li>Node <code>p</code> is in the sub-tree of node <code>q</code>.</li>
	<li>Neither node <code>p</code> is in the sub-tree of node <code>q</code> nor node <code>q</code> is in the sub-tree of node <code>p</code>.</li>
</ol>

<p>In cases 2 and 3, you just need to move <code><span>p</span></code> (with its sub-tree) to be a child of <code>q</code>, but in case 1 the tree may be disconnected, thus you need to reconnect the tree again. <strong>Please read the examples carefully before solving this problem.</strong></p>

<p>&nbsp;</p>

<p><em>Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).</em></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1516.Move%20Sub-Tree%20of%20N-Ary%20Tree/images/sample_4_964.png" style="width: 296px; height: 241px;" /></p>

<p>For example, the above tree is serialized as <code>[1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1516.Move%20Sub-Tree%20of%20N-Ary%20Tree/images/move_e1.jpg" style="width: 450px; height: 188px;" />
<pre>
<strong>Input:</strong> root = [1,null,2,3,null,4,5,null,6,null,7,8], p = 4, q = 1
<strong>Output:</strong> [1,null,2,3,4,null,5,null,6,null,7,8]
<strong>Explanation:</strong> This example follows the second case as node p is in the sub-tree of node q. We move node p with its sub-tree to be a direct child of node q.
Notice that node 4 is the last child of node 1.</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1516.Move%20Sub-Tree%20of%20N-Ary%20Tree/images/move_e2.jpg" style="width: 281px; height: 281px;" />
<pre>
<strong>Input:</strong> root = [1,null,2,3,null,4,5,null,6,null,7,8], p = 7, q = 4
<strong>Output:</strong> [1,null,2,3,null,4,5,null,6,null,7,8]
<strong>Explanation:</strong> Node 7 is already a direct child of node 4. We don&#39;t change anything.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1516.Move%20Sub-Tree%20of%20N-Ary%20Tree/images/move_e3.jpg" style="width: 450px; height: 331px;" />
<pre>
<strong>Input:</strong> root = [1,null,2,3,null,4,5,null,6,null,7,8], p = 3, q = 8
<strong>Output:</strong> [1,null,2,null,4,5,null,7,8,null,null,null,3,null,6]
<strong>Explanation:</strong> This example follows case 3 because node p is not in the sub-tree of node q and vice-versa. We can move node 3 with its sub-tree and make it as node 8&#39;s child.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The total number of nodes is between <code>[2, 1000]</code>.</li>
	<li>Each node has a <strong>unique</strong> value.</li>
	<li><code>p != null</code></li>
	<li><code>q != null</code></li>
	<li><code>p</code> and <code>q</code> are two different nodes (i.e. <code>p != q</code>).</li>
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
