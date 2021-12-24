# [1430. Check If a String Is a Valid Sequence from Root to Leaves Path in a Binary Tree](https://leetcode.com/problems/check-if-a-string-is-a-valid-sequence-from-root-to-leaves-path-in-a-binary-tree)

[中文文档](/solution/1400-1499/1430.Check%20If%20a%20String%20Is%20a%20Valid%20Sequence%20from%20Root%20to%20Leaves%20Path%20in%20a%20Binary%20Tree/README.md)

## Description

<p>Given a binary tree where each path going from the root to any leaf form a <strong>valid sequence</strong>, check if a given string&nbsp;is a <strong>valid sequence</strong> in such binary tree.&nbsp;</p>

<p>We get the given string from the concatenation of an array of integers <code>arr</code> and the concatenation of all&nbsp;values of the nodes along a path results in a <strong>sequence</strong> in the given binary tree.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1430.Check%20If%20a%20String%20Is%20a%20Valid%20Sequence%20from%20Root%20to%20Leaves%20Path%20in%20a%20Binary%20Tree/images/leetcode_testcase_1.png" style="width: 333px; height: 250px;" /></strong></p>

<pre>
<strong>Input:</strong> root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,0,1]
<strong>Output:</strong> true
<strong>Explanation: 
</strong>The path 0 -&gt; 1 -&gt; 0 -&gt; 1 is a valid sequence (green color in the figure). 
Other valid sequences are: 
0 -&gt; 1 -&gt; 1 -&gt; 0 
0 -&gt; 0 -&gt; 0
</pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1430.Check%20If%20a%20String%20Is%20a%20Valid%20Sequence%20from%20Root%20to%20Leaves%20Path%20in%20a%20Binary%20Tree/images/leetcode_testcase_2.png" style="width: 333px; height: 250px;" /></strong></p>

<pre>
<strong>Input:</strong> root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,0,1]
<strong>Output:</strong> false 
<strong>Explanation:</strong> The path 0 -&gt; 0 -&gt; 1 does not exist, therefore it is not even a sequence.
</pre>

<p><strong>Example 3:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1430.Check%20If%20a%20String%20Is%20a%20Valid%20Sequence%20from%20Root%20to%20Leaves%20Path%20in%20a%20Binary%20Tree/images/leetcode_testcase_3.png" style="width: 333px; height: 250px;" /></strong></p>

<pre>
<strong>Input:</strong> root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,1]
<strong>Output:</strong> false
<strong>Explanation: </strong>The path 0 -&gt; 1 -&gt; 1 is a sequence, but it is not a valid sequence.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 5000</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 9</code></li>
	<li>Each node&#39;s value is between [0 - 9].</li>
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
