# [1372. Longest ZigZag Path in a Binary Tree](https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree)

[中文文档](/solution/1300-1399/1372.Longest%20ZigZag%20Path%20in%20a%20Binary%20Tree/README.md)

## Description
<p>Given a binary tree <code>root</code>, a&nbsp;ZigZag path for a binary tree is defined as follow:</p>



<ul>

	<li>Choose <strong>any </strong>node in the binary tree and a direction (right or left).</li>

	<li>If the current direction is right then move to the right child of the current node otherwise move to the left child.</li>

	<li>Change the direction from right to left or right to left.</li>

	<li>Repeat the second and third step until you can&#39;t move in the tree.</li>

</ul>



<p>Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).</p>



<p>Return&nbsp;the longest <strong>ZigZag</strong> path contained in that tree.</p>



<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>



<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2020/01/22/sample_1_1702.png" style="width: 151px; height: 283px;" /></strong></p>



<pre>

<strong>Input:</strong> root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]

<strong>Output:</strong> 3

<strong>Explanation:</strong> Longest ZigZag path in blue nodes (right -&gt; left -&gt; right).

</pre>



<p><strong>Example 2:</strong></p>



<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2020/01/22/sample_2_1702.png" style="width: 120px; height: 253px;" /></strong></p>



<pre>

<strong>Input:</strong> root = [1,1,1,null,1,null,null,1,1,null,1]

<strong>Output:</strong> 4

<strong>Explanation:</strong> Longest ZigZag path in blue nodes (left -&gt; right -&gt; left -&gt; right).

</pre>



<p><strong>Example 3:</strong></p>



<pre>

<strong>Input:</strong> root = [1]

<strong>Output:</strong> 0

</pre>



<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>



<ul>

	<li>Each tree has at most <code>50000</code> nodes..</li>

	<li>Each node&#39;s value is between <code>[1, 100]</code>.</li>

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