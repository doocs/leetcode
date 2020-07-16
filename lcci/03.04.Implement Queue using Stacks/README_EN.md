# [03.04. Implement Queue using Stacks](https://leetcode-cn.com/problems/implement-queue-using-stacks-lcci)

[中文文档](/lcci/03.04.Implement%20Queue%20using%20Stacks/README.md)

## Description
<p>Implement a MyQueue class which implements a queue using two stacks.</p>

&nbsp;



<p><strong>Example: </strong></p>



<pre>

MyQueue queue = new MyQueue();



queue.push(1);

queue.push(2);

queue.peek();  // return 1

queue.pop();   // return 1

queue.empty(); // return false</pre>



<p>&nbsp;</p>



<p><b>Notes:</b></p>



<ul>
	<li>You must use&nbsp;<i>only</i>&nbsp;standard operations of a stack -- which means only&nbsp;<code>push to top</code>,&nbsp;<code>peek/pop from top</code>,&nbsp;<code>size</code>, and&nbsp;<code>is empty</code>&nbsp;operations are valid.</li>
	<li>Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.</li>
	<li>You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).</li>
</ul>



<p>&nbsp;</p>




## Solutions


### Python3

```python
class MyQueue:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self._s1, self._s2 = [], []


    def push(self, x: int) -> None:
        """
        Push element x to the back of queue.
        """
        self._s1.append(x)


    def pop(self) -> int:
        """
        Removes the element from in front of queue and returns that element.
        """
        if len(self._s2) == 0:
            while self._s1:
                self._s2.append(self._s1.pop())
        return self._s2.pop()


    def peek(self) -> int:
        """
        Get the front element.
        """
        if len(self._s2) == 0:
            while self._s1:
                self._s2.append(self._s1.pop())
        return self._s2[-1]


    def empty(self) -> bool:
        """
        Returns whether the queue is empty.
        """
        return len(self._s1) + len(self._s2) == 0



# Your MyQueue object will be instantiated and called as such:
# obj = MyQueue()
# obj.push(x)
# param_2 = obj.pop()
# param_3 = obj.peek()
# param_4 = obj.empty()
```

### Java

```java
class MyQueue {
    private Stack<Integer> s1;
    private Stack<Integer> s2;

    /** Initialize your data structure here. */
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (s2.empty()) {
            while (!s1.empty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if (s2.empty()) {
            while (!s1.empty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.empty() && s2.empty();
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

### ...
```

```
