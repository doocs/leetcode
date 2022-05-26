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
        self._s1 = []
        self._s2 = []


    def push(self, x: int) -> None:
        self._s1.append(x)
        self._s2.append(x if len(self._s2) == 0 or self._s2[-1] >= x else self._s2[-1])

    def pop(self) -> None:
        self._s1.pop()
        self._s2.pop()

    def top(self) -> int:
        return self._s1[-1]

    def getMin(self) -> int:
        return self._s2[-1]


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
    private Stack<Integer> s1;
    private Stack<Integer> s2;

    /** initialize your data structure here. */
    public MinStack() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void push(int x) {
        s1.push(x);
        s2.push(s2.empty() || s2.peek() >= x ? x : s2.peek());
    }

    public void pop() {
        s1.pop();
        s2.pop();
    }

    public int top() {
        return s1.peek();
    }

    public int getMin() {
        return s2.empty() ? -1 : s2.peek();
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
