# [1325. Delete Leaves With a Given Value](https://leetcode.com/problems/delete-leaves-with-a-given-value)

[中文文档](/solution/1300-1399/1325.Delete%20Leaves%20With%20a%20Given%20Value/README.md)

## Description

<p>Given a binary tree&nbsp;<code>root</code>&nbsp;and an integer&nbsp;<code>target</code>, delete all the&nbsp;<strong>leaf nodes</strong>&nbsp;with value <code>target</code>.</p>

<p>Note&nbsp;that once you delete a leaf node with value <code>target</code><strong>,&nbsp;</strong>if it&#39;s parent node becomes a leaf node and has the value <code><font face="monospace">target</font></code>, it should also be deleted (you need to continue doing that until you can&#39;t).</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1325.Delete%20Leaves%20With%20a%20Given%20Value/images/sample_1_1684.png" style="width: 550px; height: 120px;" /></strong></p>

<pre>
<strong>Input:</strong> root = [1,2,3,2,null,2,4], target = 2
<strong>Output:</strong> [1,null,3,null,4]
<strong>Explanation:</strong> Leaf nodes in green with value (target = 2) are removed (Picture in left). 
After removing, new nodes become leaf nodes with value (target = 2) (Picture in center).
</pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1325.Delete%20Leaves%20With%20a%20Given%20Value/images/sample_2_1684.png" style="width: 300px; height: 120px;" /></strong></p>

<pre>
<strong>Input:</strong> root = [1,3,3,3,2], target = 3
<strong>Output:</strong> [1,3,null,null,2]
</pre>

<p><strong>Example 3:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1325.Delete%20Leaves%20With%20a%20Given%20Value/images/sample_3_1684.png" style="width: 420px; height: 150px;" /></strong></p>

<pre>
<strong>Input:</strong> root = [1,2,null,2,null,2], target = 2
<strong>Output:</strong> [1]
<strong>Explanation:</strong> Leaf nodes in green with value (target = 2) are removed at each step.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> root = [1,1,1], target = 1
<strong>Output:</strong> []
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> root = [1,2,3], target = 1
<strong>Output:</strong> [1,2,3]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= target&nbsp;&lt;= 1000</code></li>
	<li>The&nbsp;given binary tree will have between&nbsp;<code>1</code>&nbsp;and&nbsp;<code>3000</code>&nbsp;nodes.</li>
	<li>Each node&#39;s value is between <code>[1, 1000]</code>.</li>
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
