# [03.02. Min Stack](https://leetcode-cn.com/problems/min-stack-lcci)

[中文文档](/lcci/03.02.Min%20Stack/README.md)

## Description

<p>How would you design a stack which, in addition to push and pop, has a function min which returns the minimum element? Push, pop and min should all operate in 0(1) time.</p>

<p><strong>Example: </strong></p>

<pre>

MinStack minStack = new MinStack();

minStack.push(-2);

minStack.push(0);

minStack.push(-3);

minStack.getMin();   --&gt; return -3.

minStack.pop();

minStack.top();      --&gt; return 0.

minStack.getMin();   --&gt; return -2.</pre>

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
        self.mins = [float('inf')]

    def push(self, val: int) -> None:
        self.s.append(val)
        self.mins.append(min(self.mins[-1], val))

    def pop(self) -> None:
        self.s.pop()
        self.mins.pop()

    def top(self) -> int:
        return self.s[-1]

    def getMin(self) -> int:
        return self.mins[-1]


# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(val)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.getMin()
```

### **Java**

```java
class MinStack {
    private Deque<Integer> s;
    private Deque<Integer> mins;

    /** initialize your data structure here. */
    public MinStack() {
        s = new ArrayDeque<>();
        mins = new ArrayDeque<>();
        mins.push(Integer.MAX_VALUE);
    }
    
    public void push(int val) {
        s.push(val);
        mins.push(Math.min(mins.peek(), val));
    }
    
    public void pop() {
        s.pop();
        mins.pop();
    }
    
    public int top() {
        return s.peek();
    }
    
    public int getMin() {
        return mins.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```

### **...**

```

```

<!-- tabs:end -->
