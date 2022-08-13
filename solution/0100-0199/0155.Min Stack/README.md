# [155. 最小栈](https://leetcode.cn/problems/min-stack)

[English Version](/solution/0100-0199/0155.Min%20Stack/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个支持 <code>push</code> ，<code>pop</code> ，<code>top</code> 操作，并能在常数时间内检索到最小元素的栈。</p>

<p>实现 <code>MinStack</code> 类:</p>

<ul>
	<li><code>MinStack()</code> 初始化堆栈对象。</li>
	<li><code>void push(int val)</code> 将元素val推入堆栈。</li>
	<li><code>void pop()</code> 删除堆栈顶部的元素。</li>
	<li><code>int top()</code> 获取堆栈顶部的元素。</li>
	<li><code>int getMin()</code> 获取堆栈中的最小元素。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

<strong>输出：</strong>
[null,null,null,null,-3,null,0,-2]

<strong>解释：</strong>
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --&gt; 返回 -3.
minStack.pop();
minStack.top();      --&gt; 返回 0.
minStack.getMin();   --&gt; 返回 -2.
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-2<sup>31</sup>&nbsp;&lt;= val &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
	<li><code>pop</code>、<code>top</code> 和 <code>getMin</code> 操作总是在 <strong>非空栈</strong> 上调用</li>
	<li><code>push</code>,&nbsp;<code>pop</code>,&nbsp;<code>top</code>, and&nbsp;<code>getMin</code>最多被调用&nbsp;<code>3 * 10<sup>4</sup></code>&nbsp;次</li>
</ul>

## 解法

“辅助栈”实现。

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class MinStack:
    def __init__(self):
        """
        initialize your data structure here.
        """
        self.s = []
        self.mins = [inf]

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C#**

```cs
using System.Collections.Generic;

public class MinStack {
    private Stack<int> _stack = new Stack<int>();
    private Stack<int> _minStack = new Stack<int>();

    public void Push(int x) {
        _stack.Push(x);
        if (GetMin() >= x) _minStack.Push(x);
    }

    public void Pop() {
        var x = _stack.Pop();
        if (GetMin() == x) _minStack.Pop();
    }

    public int Top() {
        return _stack.Peek();
    }

    public int GetMin() {
        if (_minStack.Count == 0) return int.MaxValue;
        return _minStack.Peek();
    }
}
```

### **JavaScript**

```js
/**
 * initialize your data structure here.
 */
var MinStack = function () {
    this.s = [];
    this.mins = [Infinity];
};

/**
 * @param {number} val
 * @return {void}
 */
MinStack.prototype.push = function (val) {
    this.s.push(val);
    this.mins.push(Math.min(this.mins[this.mins.length - 1], val));
};

/**
 * @return {void}
 */
MinStack.prototype.pop = function () {
    this.s.pop();
    this.mins.pop();
};

/**
 * @return {number}
 */
MinStack.prototype.top = function () {
    return this.s[this.s.length - 1];
};

/**
 * @return {number}
 */
MinStack.prototype.getMin = function () {
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

### **TypeScript**

```ts
class MinStack {
    private stack: number[];
    private min: number[];

    constructor() {
        this.stack = [];
        this.min = [];
    }

    push(val: number): void {
        this.stack.push(val);
        if (val <= (this.min[this.min.length - 1] ?? Infinity)) {
            this.min.push(val);
        }
    }

    pop(): void {
        if (this.stack.pop() === this.min[this.min.length - 1]) {
            this.min.pop();
        }
    }

    top(): number {
        return this.stack[this.stack.length - 1];
    }

    getMin(): number {
        return this.min[this.min.length - 1];
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = new MinStack()
 * obj.push(val)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */
```

### **Rust**

```rust
struct MinStack {
    items: Vec<i32>,
    min: Vec<i32>,
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl MinStack {

    /** initialize your data structure here. */
    fn new() -> Self {
        MinStack {
            items: Vec::new(),
            min: Vec::new(),
        }
    }

    fn push(&mut self, x: i32) {
        self.items.push(x);
        match self.min.last() {
            Some(min) => {
                if min >= &x {
                    self.min.push(x);
                }
            },
            None => self.min.push(x),
        }
    }

    fn pop(&mut self) {
        if &self.items.pop().unwrap() == self.min.last().unwrap() {
            self.min.pop();
        }
    }

    fn top(&self) -> i32 {
        *self.items.last().unwrap()
    }

    fn get_min(&self) -> i32 {
        *self.min.last().unwrap()
    }
}


/**
 * Your MinStack object will be instantiated and called as such:
 * let obj = MinStack::new();
 * obj.push(val);
 * obj.pop();
 * let ret_3: i32 = obj.top();
 * let ret_4: i32 = obj.get_min();
 */
```

### **...**

```

```

<!-- tabs:end -->
