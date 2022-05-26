# [155. Min Stack](https://leetcode.com/problems/min-stack)

[中文文档](/solution/0100-0199/0155.Min%20Stack/README.md)

## Description

<p>Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.</p>

<ul>
    <li>push(x) -- Push element x onto stack.</li>
    <li>pop() -- Removes the element on top of the stack.</li>
    <li>top() -- Get the top element.</li>
    <li>getMin() -- Retrieve the minimum element in the stack.</li>
</ul>

<p>&nbsp;</p>

<p><b>Example:</b></p>

<pre>

MinStack minStack = new MinStack();

minStack.push(-2);

minStack.push(0);

minStack.push(-3);

minStack.getMin();   --&gt; Returns -3.

minStack.pop();

minStack.top();      --&gt; Returns 0.

minStack.getMin();   --&gt; Returns -2.

</pre>

<p>&nbsp;</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class MinStack:

    def __init__(self):
        """
        initialize your data structure here.
        """
        self.s = []
        self.helper = []


    def push(self, x: int) -> None:
        self.s.append(x)
        element = x if not self.helper or x < self.helper[-1] else self.helper[-1]
        self.helper.append(element)

    def pop(self) -> None:
        self.s.pop()
        self.helper.pop()

    def top(self) -> int:
        return self.s[-1]

    def getMin(self) -> int:
        return self.helper[-1]


# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(x)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.getMin()
```

### **Java**

```java
class MinStack {

    private Deque<Integer> s;
    private Deque<Integer> helper;

    /** initialize your data structure here. */
    public MinStack() {
        s = new ArrayDeque<>();
        helper = new ArrayDeque<>();
    }

    public void push(int x) {
        s.push(x);
        int element = helper.isEmpty() || x < helper.peek() ? x : helper.peek();
        helper.push(element);
    }

    public void pop() {
        s.pop();
        helper.pop();
    }

    public int top() {
        return s.peek();
    }

    public int getMin() {
        return helper.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```

### **...**

```

```

<!-- tabs:end -->
