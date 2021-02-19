# [1609. Even Odd Tree](https://leetcode.com/problems/even-odd-tree)

[中文文档](/solution/1600-1699/1609.Even%20Odd%20Tree/README.md)

## Description

<p>A binary tree is named <strong>Even-Odd</strong> if it meets the following conditions:</p>

<ul>
	<li>The root of the binary tree is at level index <code>0</code>, its children are at level index <code>1</code>, their children are at level index <code>2</code>, etc.</li>
	<li>For every <strong>even-indexed</strong> level, all nodes at the level have <strong>odd</strong> integer values in <strong>strictly increasing</strong> order (from left to right).</li>
	<li>For every <b>odd-indexed</b> level, all nodes at the level have <b>even</b> integer values in <strong>strictly decreasing</strong> order (from left to right).</li>
</ul>

<p>Given the <code>root</code> of a binary tree, <em>return </em><code>true</code><em> if the binary tree is <strong>Even-Odd</strong>, otherwise return </em><code>false</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/sample_1_1966.png" style="width: 362px; height: 229px;" /></strong></p>

<pre>
<strong>Input:</strong> root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
<strong>Output:</strong> true
<strong>Explanation:</strong> The node values on each level are:
Level 0: [1]
Level 1: [10,4]
Level 2: [3,7,9]
Level 3: [12,8,6,2]
Since levels 0 and 2 are all odd and increasing, and levels 1 and 3 are all even and decreasing, the tree is Even-Odd.
</pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/sample_2_1966.png" style="width: 363px; height: 167px;" /></strong></p>

<pre>
<strong>Input:</strong> root = [5,4,2,3,3,7]
<strong>Output:</strong> false
<strong>Explanation:</strong> The node values on each level are:
Level 0: [5]
Level 1: [4,2]
Level 2: [3,3,7]
Node values in the level 2 must be in strictly increasing order, so the tree is not Even-Odd.
</pre>

<p><strong>Example 3:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2020/09/22/sample_1_333_1966.png" style="width: 363px; height: 167px;" /></p>

<pre>
<strong>Input:</strong> root = [5,9,1,3,5,7]
<strong>Output:</strong> false
<strong>Explanation:</strong> Node values in the level 1 should be even integers.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> root = [1]
<strong>Output:</strong> true
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> root = [11,8,6,1,3,9,11,30,20,18,16,12,10,4,2,17]
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>5</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>6</sup></code></li>
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
