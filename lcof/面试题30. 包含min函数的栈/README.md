# [面试题 30. 包含 min 函数的栈](https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/)

## 题目描述

定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。

**示例:**

```
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.min();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.min();   --> 返回 -2.
```

**提示：**

- 各函数的调用总次数不超过 20000 次

## 解法

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

    def min(self) -> int:
        return self.mins[-1]


# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(val)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.min()
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

    public int min() {
        return mins.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */
```

### **JavaScript**

```js
/**
 * initialize your data structure here.
 */
var MinStack = function () {
  this.stack = [];
  this.minStack = [];
};

/**
 * @param {number} x
 * @return {void}
 */
MinStack.prototype.push = function (x) {
  this.stack.unshift(x);
  if (!this.minStack.length || this.minStack[0] >= x) {
    this.minStack.unshift(x);
  }
};

/**
 * @return {void}
 */
MinStack.prototype.pop = function () {
  if (this.stack.shift() === this.minStack[0]) {
    this.minStack.shift();
  }
};

/**
 * @return {number}
 */
MinStack.prototype.top = function () {
  return this.stack[0];
};

/**
 * @return {number}
 */
MinStack.prototype.min = function () {
  return this.minStack[0];
};

/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = new MinStack()
 * obj.push(x)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.min()
 */
```

### **C++**

```cpp
class MinStack {
private:
    stack<int> a, b;

public:
    /** initialize your data structure here. */
    MinStack() {
    }

    void push(int x) {
        a.push(x);
        if (b.empty() || x <= b.top()) {
            b.push(x);
        }
    }

    void pop() {
        if (a.top() == b.top()) {
            b.pop();
        }
        a.pop();
    }

    int top() {
        return a.top();
    }

    int min() {
        return b.top();
    }
};
```

### **...**

```

```

<!-- tabs:end -->
