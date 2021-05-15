# [155. Min Stack](https://leetcode.com/problems/min-stack)

[中文文档](/solution/0100-0199/0155.Min%20Stack/README.md)

## Description

<p>Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.</p>

<p>Implement the <code>MinStack</code> class:</p>

<ul>
	<li><code>MinStack()</code> initializes the stack object.</li>
	<li><code>void push(val)</code> pushes the element <code>val</code> onto the stack.</li>
	<li><code>void pop()</code> removes the element on the top of the stack.</li>
	<li><code>int top()</code> gets the top element of the stack.</li>
	<li><code>int getMin()</code> retrieves the minimum element in the stack.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;MinStack&quot;,&quot;push&quot;,&quot;push&quot;,&quot;push&quot;,&quot;getMin&quot;,&quot;pop&quot;,&quot;top&quot;,&quot;getMin&quot;]
[[],[-2],[0],[-3],[],[],[],[]]

<strong>Output</strong>
[null,null,null,null,-3,null,0,-2]

<strong>Explanation</strong>
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;= val &lt;= 2<sup>31</sup> - 1</code></li>
	<li>Methods <code>pop</code>, <code>top</code> and <code>getMin</code> operations will always be called on <strong>non-empty</strong> stacks.</li>
	<li>At most <code>3 * 10<sup>4</sup></code> calls will be made to <code>push</code>, <code>pop</code>, <code>top</code>, and <code>getMin</code>.</li>
</ul>

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

### **C++**

```cpp
class MinStack {
private:
    stack<int> s;
    stack<int> mins;

public:
    /** initialize your data structure here. */
    MinStack() {
        mins.push(INT_MAX);
    }

    void push(int val) {
        s.push(val);
        mins.push(min(mins.top(), val));
    }

    void pop() {
        s.pop();
        mins.pop();
    }

    int top() {
        return s.top();
    }

    int getMin() {
        return mins.top();
    }
};

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack* obj = new MinStack();
 * obj->push(val);
 * obj->pop();
 * int param_3 = obj->top();
 * int param_4 = obj->getMin();
 */
```

### **JavaScript**

```js
/**
 * initialize your data structure here.
 */
var MinStack = function() {
    this.s = [];
    this.mins = [Infinity];
};

/** 
 * @param {number} val
 * @return {void}
 */
MinStack.prototype.push = function(val) {
    this.s.push(val);
    this.mins.push(Math.min(this.mins[this.mins.length - 1], val));
};

/**
 * @return {void}
 */
MinStack.prototype.pop = function() {
    this.s.pop();
    this.mins.pop();
};

/**
 * @return {number}
 */
MinStack.prototype.top = function() {
    return this.s[this.s.length - 1];
};

/**
 * @return {number}
 */
MinStack.prototype.getMin = function() {
    return this.mins[this.mins.length - 1];
};

/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = new MinStack()
 * obj.push(val)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */
```

### **...**

```

```

<!-- tabs:end -->
