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
class MyCircularDeque:

    def __init__(self, k: int):
        """
        Initialize your data structure here. Set the size of the deque to be k.
        """
        self.q = [0] * k
        self.front = 0
        self.size = 0
        self.capacity = k

    def insertFront(self, value: int) -> bool:
        """
        Adds an item at the front of Deque. Return true if the operation is successful.
        """
        if self.isFull():
            return False
        if not self.isEmpty():
            self.front = (self.front - 1 + self.capacity) % self.capacity
        self.q[self.front] = value
        self.size += 1
        return True

    def insertLast(self, value: int) -> bool:
        """
        Adds an item at the rear of Deque. Return true if the operation is successful.
        """
        if self.isFull():
            return False
        idx = (self.front + self.size) % self.capacity
        self.q[idx] = value
        self.size += 1
        return True

    def deleteFront(self) -> bool:
        """
        Deletes an item from the front of Deque. Return true if the operation is successful.
        """
        if self.isEmpty():
            return False
        self.front = (self.front + 1) % self.capacity
        self.size -= 1
        return True

    def deleteLast(self) -> bool:
        """
        Deletes an item from the rear of Deque. Return true if the operation is successful.
        """
        if self.isEmpty():
            return False
        self.size -= 1
        return True

    def getFront(self) -> int:
        """
        Get the front item from the deque.
        """
        if self.isEmpty():
            return -1
        return self.q[self.front]

    def getRear(self) -> int:
        """
        Get the last item from the deque.
        """
        if self.isEmpty():
            return -1
        idx = (self.front + self.size - 1) % self.capacity
        return self.q[idx]

    def isEmpty(self) -> bool:
        """
        Checks whether the circular deque is empty or not.
        """
        return self.size == 0

    def isFull(self) -> bool:
        """
        Checks whether the circular deque is full or not.
        """
        return self.size == self.capacity


# Your MyCircularDeque object will be instantiated and called as such:
# obj = MyCircularDeque(k)
# param_1 = obj.insertFront(value)
# param_2 = obj.insertLast(value)
# param_3 = obj.deleteFront()
# param_4 = obj.deleteLast()
# param_5 = obj.getFront()
# param_6 = obj.getRear()
# param_7 = obj.isEmpty()
# param_8 = obj.isFull()
```

### **Java**

```java
class MyCircularDeque {
    private int[] q;
    private int front;
    private int size;
    private int capacity;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        q = new int[k];
        capacity = k;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        if (!isEmpty()) {
            front = (front - 1 + capacity) % capacity;
        }
        q[front] = value;
        ++size;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        int idx = (front + size) % capacity;
        q[idx] = value;
        ++size;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % capacity;
        --size;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        --size;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return q[front];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        int idx = (front + size - 1) % capacity;
        return q[idx];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size == capacity;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
```

### **...**

```

```

<!-- tabs:end -->
