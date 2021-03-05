# [232. Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks)

[中文文档](/solution/0200-0299/0232.Implement%20Queue%20using%20Stacks/README.md)

## Description

<p>Implement the following operations of a queue using stacks.</p>

<ul>
    <li>push(x) -- Push element x to the back of queue.</li>
    <li>pop() -- Removes the element from in front of queue.</li>
    <li>peek() -- Get the front element.</li>
    <li>empty() -- Return whether the queue is empty.</li>
</ul>

<p><b>Example:</b></p>

<pre>

MyQueue queue = new MyQueue();



queue.push(1);

queue.push(2);  

queue.peek();  // returns 1

queue.pop();   // returns 1

queue.empty(); // returns false</pre>

<p><b>Notes:</b></p>

<ul>
    <li>You must use <i>only</i> standard operations of a stack -- which means only <code>push to top</code>, <code>peek/pop from top</code>, <code>size</code>, and <code>is empty</code> operations are valid.</li>
    <li>Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.</li>
    <li>You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class MyQueue:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.s1 = []
        self.s2 = []


    def push(self, x: int) -> None:
        """
        Push element x to the back of queue.
        """
        self.s1.append(x)


    def pop(self) -> int:
        """
        Removes the element from in front of queue and returns that element.
        """
        self._move()
        return self.s2.pop()

    def peek(self) -> int:
        """
        Get the front element.
        """
        self._move()
        return self.s2[-1]


    def empty(self) -> bool:
        """
        Returns whether the queue is empty.
        """
        return len(self.s1) + len(self.s2) == 0


    def _move(self):
        """
        Move elements from s1 to s2.
        """
        if len(self.s2) == 0:
            while len(self.s1) > 0:
                self.s2.append(self.s1.pop())


# Your MyQueue object will be instantiated and called as such:
# obj = MyQueue()
# obj.push(x)
# param_2 = obj.pop()
# param_3 = obj.peek()
# param_4 = obj.empty()
```

### **Java**

```java
class MyQueue {

    private Deque<Integer> s1 = new ArrayDeque<>();
    private Deque<Integer> s2 = new ArrayDeque<>();

    /** Initialize your data structure here. */
    public MyQueue() {

    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        move();
        return s2.pop();
    }

    /** Get the front element. */
    public int peek() {
        move();
        return s2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    /** Move elements from s1 to s2. */
    private void move() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
```

### **...**

```

```

<!-- tabs:end -->
