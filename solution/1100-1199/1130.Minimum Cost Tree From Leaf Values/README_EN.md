# [1130. Minimum Cost Tree From Leaf Values](https://leetcode.com/problems/minimum-cost-tree-from-leaf-values)

[中文文档](/solution/1100-1199/1130.Minimum%20Cost%20Tree%20From%20Leaf%20Values/README.md)

## Description

<p>Given an array <code>arr</code> of positive integers, consider all binary trees such that:</p>

<ul>
	<li>Each node has either 0 or 2 children;</li>
	<li>The values of <code>arr</code> correspond to the values of each&nbsp;<strong>leaf</strong> in an in-order traversal of the tree.&nbsp; <em>(Recall that a node is a leaf if and only if it has 0 children.)</em></li>
	<li>The value&nbsp;of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.</li>
</ul>

<p>Among all possible binary trees considered,&nbsp;return the smallest possible sum of the values of each non-leaf node.&nbsp; It is guaranteed this sum fits into a 32-bit integer.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> arr = [6,2,4]

<strong>Output:</strong> 32

<strong>Explanation:</strong>

There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.



    24            24

   /  \          /  \

  12   4        6    8

 /  \               / \

6    2             2   4

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 40</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 15</code></li>
	<li>It is guaranteed that the answer fits into a 32-bit signed integer (ie.&nbsp;it is less than <code>2^31</code>).</li>
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
