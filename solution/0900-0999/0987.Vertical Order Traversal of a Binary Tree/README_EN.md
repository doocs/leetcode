# [987. Vertical Order Traversal of a Binary Tree](https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree)

## Description
<p>Given a binary tree, return the <em>vertical order</em> traversal of its nodes&nbsp;values.</p>



<p>For each node at position <code>(X, Y)</code>, its left and right children respectively&nbsp;will be at positions <code>(X-1, Y-1)</code> and <code>(X+1, Y-1)</code>.</p>



<p>Running a vertical line from <code>X = -infinity</code> to <code>X = +infinity</code>, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing <code>Y</code> coordinates).</p>



<p>If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.</p>



<p>Return an list&nbsp;of non-empty reports in order of <code>X</code> coordinate.&nbsp; Every report will have a list of values of nodes.</p>



<p>&nbsp;</p>



<p><strong>Example 1:</strong></p>



<p><img alt="" src="https://assets.leetcode.com/uploads/2019/01/31/1236_example_1.PNG" style="width: 201px; height: 186px;" /></p>



<div>

<pre>

<strong>Input: </strong><span id="example-input-1-1">[3,9,20,null,null,15,7]</span>

<strong>Output: </strong><span id="example-output-1">[[9],[3,15],[20],[7]]</span>

<strong>Explanation: </strong>

Without loss of generality, we can assume the root node is at position (0, 0):

Then, the node with value 9 occurs at position (-1, -1);

The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);

The node with value 20 occurs at position (1, -1);

The node with value 7 occurs at position (2, -2).

</pre>



<div>

<p><strong>Example 2:</strong></p>



<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2019/01/31/tree2.png" style="width: 236px; height: 150px;" /></strong></p>



<pre>

<strong>Input: </strong><span id="example-input-2-1">[1,2,3,4,5,6,7]</span>

<strong>Output: </strong><span id="example-output-2">[[4],[2],[1,5,6],[3],[7]]</span>

<strong>Explanation: </strong>

The node with value 5 and the node with value 6 have the same position according to the given scheme.

However, in the report &quot;[1,5,6]&quot;, the node value of 5 comes first since 5 is smaller than 6.

</pre>



<p>&nbsp;</p>

</div>



<p><strong>Note:</strong></p>



<ol>

	<li>The tree will have between <font face="monospace">1</font>&nbsp;and <code>1000</code> nodes.</li>

	<li>Each node&#39;s value will be between <code>0</code> and <code>1000</code>.</li>

</ol>

</div>



<div>

<div>&nbsp;</div>

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