# [641. Design Circular Deque](https://leetcode.com/problems/design-circular-deque)

[中文文档](/solution/0600-0699/0641.Design%20Circular%20Deque/README.md)

## Description

<p>Design your implementation of the circular double-ended queue (deque).</p>

<p>Your implementation should support following operations:</p>

<ul>
    <li><code>MyCircularDeque(k)</code>: Constructor, set the size of the deque to be k.</li>
    <li><code>insertFront()</code>: Adds an item at the front of Deque. Return true if the operation is successful.</li>
    <li><code>insertLast()</code>: Adds an item at the rear of Deque. Return true if the operation is successful.</li>
    <li><code>deleteFront()</code>: Deletes an item from the front of Deque. Return true if the operation is successful.</li>
    <li><code>deleteLast()</code>: Deletes an item from the rear of Deque. Return true if the operation is successful.</li>
    <li><code>getFront()</code>: Gets the front item from the Deque. If the deque is empty, return -1.</li>
    <li><code>getRear()</code>: Gets the last item from Deque. If the deque is empty, return -1.</li>
    <li><code>isEmpty()</code>: Checks whether Deque is empty or not.&nbsp;</li>
    <li><code>isFull()</code>: Checks whether Deque is full or not.</li>
</ul>

<p>&nbsp;</p>

<p><strong>Example:</strong></p>

<pre>

MyCircularDeque circularDeque = new MycircularDeque(3); // set the size to be 3

circularDeque.insertLast(1);			// return true

circularDeque.insertLast(2);			// return true

circularDeque.insertFront(3);			// return true

circularDeque.insertFront(4);			// return false, the queue is full

circularDeque.getRear();  			// return 2

circularDeque.isFull();				// return true

circularDeque.deleteLast();			// return true

circularDeque.insertFront(4);			// return true

circularDeque.getFront();			// return 4

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ul>

    <li>All values will be in the range of [0, 1000].</li>

    <li>The number of operations will be in the range of&nbsp;[1, 1000].</li>

    <li>Please do not use the built-in Deque library.</li>

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
