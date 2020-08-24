# [708. Insert into a Sorted Circular Linked List](https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list)

[中文文档](/solution/0700-0799/0708.Insert%20into%20a%20Sorted%20Circular%20Linked%20List/README.md)

## Description
<p>Given a node from a <strong>Circular Linked List</strong> which is sorted in ascending order, write a function to insert a value <code>insertVal</code> into the list such that it remains a sorted circular list. The given node can be a reference to <em>any</em> single node in the list, and may not be necessarily the smallest value in the circular list.</p>

<p>If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the circular list should remain sorted.</p>

<p>If the list is empty (i.e., given node is <code>null</code>), you should create a new single circular list and return the reference to that single node. Otherwise, you should return the original given node.</p>

<p> </p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2019/01/19/example_1_before_65p.jpg" style="width: 250px; height: 149px;" /><br />
 
<pre>
<strong>Input:</strong> head = [3,4,1], insertVal = 2
<strong>Output:</strong> [3,4,1,2]
<strong>Explanation:</strong> In the figure above, there is a sorted circular list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list. The new node should be inserted between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.

<img alt="" src="https://assets.leetcode.com/uploads/2019/01/19/example_1_after_65p.jpg" style="width: 250px; height: 149px;" />

</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> head = [], insertVal = 1
<strong>Output:</strong> [1]
<strong>Explanation:</strong> The list is empty (given head is <code>null</code>). We create a new single circular list and return the reference to that single node.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> head = [1], insertVal = 0
<strong>Output:</strong> [1,0]
</pre>

<p> </p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 <= Number of Nodes <= 5 * 10^4</code></li>
	<li><code><font face="monospace">-10^6 <= Node.val <= 10^6</font></code></li>
	<li><code>-10^6 <= insertVal <= 10^6</code></li>
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