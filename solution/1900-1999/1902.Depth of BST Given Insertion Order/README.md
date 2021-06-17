# [1902. Depth of BST Given Insertion Order](https://leetcode-cn.com/problems/depth-of-bst-given-insertion-order)

[English Version](/solution/1900-1999/1902.Depth%20of%20BST%20Given%20Insertion%20Order/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given a <strong>0-indexed</strong> integer array <code>order</code> of length <code>n</code>, a <strong>permutation</strong> of integers from <code>1</code> to <code>n</code> representing the <strong>order</strong> of insertion into a <strong>binary search tree</strong>.</p>



<p>A binary search tree is defined as follows:</p>



<ul>
	<li>The left subtree of a node contains only nodes with keys <strong>less than</strong> the node&#39;s key.</li>
	<li>The right subtree of a node contains only nodes with keys <strong>greater than</strong> the node&#39;s key.</li>
	<li>Both the left and right subtrees must also be binary search trees.</li>
</ul>



<p>The binary search tree is constructed as follows:</p>



<ul>
	<li><code>order[0]</code> will be the <strong>root</strong> of the binary search tree.</li>
	<li>All subsequent elements are inserted as the <strong>child</strong> of <strong>any</strong> existing node such that the binary search tree properties hold.</li>
</ul>



<p>Return <em>the <strong>depth</strong> of the binary search tree</em>.</p>



<p>A binary tree&#39;s <strong>depth</strong> is the number of <strong>nodes</strong> along the <strong>longest path</strong> from the root node down to the farthest leaf node.</p>



<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1902.Depth%20of%20BST%20Given%20Insertion%20Order/images/1.png" style="width: 624px; height: 154px;" />

<pre>

<strong>Input:</strong> order = [2,1,4,3]

<strong>Output:</strong> 3

<strong>Explanation: </strong>The binary search tree has a depth of 3 with path 2-&gt;3-&gt;4.

</pre>



<p><strong>Example 2:</strong></p>

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1902.Depth%20of%20BST%20Given%20Insertion%20Order/images/2.png" style="width: 624px; height: 146px;" />

<pre>

<strong>Input:</strong> order = [2,1,3,4]

<strong>Output:</strong> 3

<strong>Explanation: </strong>The binary search tree has a depth of 3 with path 2-&gt;3-&gt;4.

</pre>



<p><strong>Example 3:</strong></p>

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1902.Depth%20of%20BST%20Given%20Insertion%20Order/images/3.png" style="width: 624px; height: 225px;" />

<pre>

<strong>Input:</strong> order = [1,2,3,4]

<strong>Output:</strong> 4

<strong>Explanation: </strong>The binary search tree has a depth of 4 with path 1-&gt;2-&gt;3-&gt;4.

</pre>



<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>



<ul>
	<li><code>n == order.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>order</code> is a permutation of integers between <code>1</code> and <code>n</code>.</li>
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

### **...**

```

```

<!-- tabs:end -->
