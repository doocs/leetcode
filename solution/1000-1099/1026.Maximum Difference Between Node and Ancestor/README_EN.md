# [1026. Maximum Difference Between Node and Ancestor](https://leetcode.com/problems/maximum-difference-between-node-and-ancestor)

[中文文档](/solution/1000-1099/1026.Maximum%20Difference%20Between%20Node%20and%20Ancestor/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, find the maximum value <code>V</code> for which there exist <strong>different</strong> nodes <code>A</code> and <code>B</code> where <code>V = |A.val - B.val|</code>&nbsp;and <code>A</code> is an ancestor of <code>B</code>.</p>

<p>A node <code>A</code> is an ancestor of <code>B</code> if either: any child of <code>A</code> is equal to <code>B</code>, or any child of <code>A</code> is an ancestor of <code>B</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1026.Maximum%20Difference%20Between%20Node%20and%20Ancestor/images/tmp-tree.jpg" style="width: 400px; height: 390px;" />
<pre>
<strong>Input:</strong> root = [8,3,10,1,6,null,14,null,null,4,7,13]
<strong>Output:</strong> 7
<strong>Explanation: </strong>We have various ancestor-node differences, some of which are given below :
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1026.Maximum%20Difference%20Between%20Node%20and%20Ancestor/images/tmp-tree-1.jpg" style="width: 250px; height: 349px;" />
<pre>
<strong>Input:</strong> root = [1,null,2,null,0,3]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree&nbsp;is in the range <code>[2, 5000]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
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
