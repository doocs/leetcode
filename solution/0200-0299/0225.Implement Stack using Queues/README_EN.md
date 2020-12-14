# [225. Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues)

[中文文档](/solution/0200-0299/0225.Implement%20Stack%20using%20Queues/README.md)

## Description

<p>Implement the following operations of a stack using queues.</p>

<ul>
    <li>push(x) -- Push element x onto stack.</li>
    <li>pop() -- Removes the element on top of the stack.</li>
    <li>top() -- Get the top element.</li>
    <li>empty() -- Return whether the stack is empty.</li>
</ul>

<p><b>Example:</b></p>

<pre>

MyStack stack = new MyStack();



stack.push(1);

stack.push(2);  

stack.top();   // returns 2

stack.pop();   // returns 2

stack.empty(); // returns false</pre>

<p><b>Notes:</b></p>

<ul>
    <li>You must use <i>only</i> standard operations of a queue -- which means only <code>push to back</code>, <code>peek/pop from front</code>, <code>size</code>, and <code>is empty</code> operations are valid.</li>
    <li>Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.</li>
    <li>You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class MyStack:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.q = []


    def push(self, x: int) -> None:
        """
        Push element x onto stack.
        """
        self.q.append(x)
        n = len(self.q)
        for i in range(1, n):
            self.q.append(self.q.pop(0))


    def pop(self) -> int:
        """
        Removes the element on top of the stack and returns that element.
        """
        return self.q.pop(0)


    def top(self) -> int:
        """
        Get the top element.
        """
        return self.q[0]


    def empty(self) -> bool:
        """
        Returns whether the stack is empty.
        """
        return len(self.q) == 0



# Your MyStack object will be instantiated and called as such:
# obj = MyStack()
# obj.push(x)
# param_2 = obj.pop()
# param_3 = obj.top()
# param_4 = obj.empty()
```

### **Java**

```java
class MyStack {

    private Deque<Integer> q;

    /** Initialize your data structure here. */
    public MyStack() {
        q = new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        q.offerLast(x);
        int n = q.size();
        while (n-- > 1) {
            q.offerLast(q.pollFirst());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return q.pollFirst();
    }

    /** Get the top element. */
    public int top() {
        return q.peekFirst();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
```

### **...**

```

```

<!-- tabs:end -->
