# [707. Design Linked List](https://leetcode.com/problems/design-linked-list)

[中文文档](/solution/0700-0799/0707.Design%20Linked%20List/README.md)

## Description

<p>Design your&nbsp;implementation of the linked list. You can choose to use the singly linked list or the doubly linked list. A node in a singly&nbsp;linked list should have two attributes: <code>val</code>&nbsp;and <code>next</code>. <code>val</code> is the value of the current node, and <code>next</code>&nbsp;is&nbsp;a&nbsp;pointer/reference to the next node. If you want to use the doubly linked list,&nbsp;you will need&nbsp;one more attribute <code>prev</code> to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.</p>

<p>Implement these functions in your linked list class:</p>

<ul>
	<li><code>get(index)</code> : Get the value of&nbsp;the <code>index</code>-th&nbsp;node in the linked list. If the index is invalid, return <code>-1</code>.</li>
	<li><code>addAtHead(val)</code> : Add a node of value <code>val</code>&nbsp;before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.</li>
	<li><code>addAtTail(val)</code> : Append a node of value <code>val</code>&nbsp;to the last element of the linked list.</li>
	<li><code>addAtIndex(index, val)</code> : Add a node of value <code>val</code>&nbsp;before the <code>index</code>-th&nbsp;node in the linked list.&nbsp;If <code>index</code>&nbsp;equals&nbsp;to the length of&nbsp;linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.</li>
	<li><code>deleteAtIndex(index)</code> : Delete&nbsp;the <code>index</code>-th&nbsp;node in the linked list, if the index is valid.</li>
</ul>

<p>&nbsp;</p>

<p><strong>Example:</strong></p>

<pre>
<b>Input: </b>
[&quot;MyLinkedList&quot;,&quot;addAtHead&quot;,&quot;addAtTail&quot;,&quot;addAtIndex&quot;,&quot;get&quot;,&quot;deleteAtIndex&quot;,&quot;get&quot;]
[[],[1],[3],[1,2],[1],[1],[1]]
<b>Output: </b> 
[null,null,null,null,2,null,3]

<b>Explanation:</b>
MyLinkedList linkedList = new MyLinkedList(); // Initialize empty LinkedList
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1, 2);  // linked list becomes 1-&gt;2-&gt;3
linkedList.get(1);            // returns 2
linkedList.deleteAtIndex(1);  // now the linked list is 1-&gt;3
linkedList.get(1);&nbsp;&nbsp;&nbsp;         // returns 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= index,val &lt;= 1000</code></li>
	<li>Please do not use the built-in LinkedList library.</li>
	<li>At most <code>2000</code>&nbsp;calls will be made to&nbsp;<code>get</code>,&nbsp;<code>addAtHead</code>,&nbsp;<code>addAtTail</code>,&nbsp; <code>addAtIndex</code> and&nbsp;<code>deleteAtIndex</code>.</li>
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
