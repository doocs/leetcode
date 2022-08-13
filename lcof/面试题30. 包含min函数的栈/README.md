# [面试题 30. 包含 min 函数的栈](https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof/)

## 题目描述

<p>定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。</p>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre>MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.min();   --&gt; 返回 -3.
minStack.pop();
minStack.top();      --&gt; 返回 0.
minStack.min();   --&gt; 返回 -2.
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>各函数的调用总次数不超过 20000 次</li>
</ol>

<p>&nbsp;</p>

<p>注意：本题与主站 155 题相同：<a href="https://leetcode.cn/problems/min-stack/">https://leetcode.cn/problems/min-stack/</a></p>

## 解法

-   若是无视时间复杂度需求，使用暴力解法：执行 `min()` 时，遍历整个栈，找到最小数返回，时间复杂度是 O(n)；
-   若是使用一个变量记录最小数，可以将时间复杂度拉到 O(1)。可是当最小数出栈之后就麻烦了，因为不知道第二小的数是谁；
-   因此需要的是一个结构，当最小值离开，暴漏第二小的值，以此类推；
-   此时使用一个新的栈，存储最小值，保证该栈顶元素为栈中最小数（称此栈为最小栈）；
-   当 `push()` 时，该值只有比最小栈栈顶元素更小，才放入最小栈当中；
-   当最小值出栈时，最小栈进行出栈操作（`pop()`）；
-   不会有出栈问题，最小栈栈底存放了正常栈中栈底的元素。

<!-- tabs:start -->

### **Python3**

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
                if *min >= x {
                    self.min.push(x)
                }
            },
            None => self.min.push(x)
        }
    }

    fn pop(&mut self) {
        if self.items.pop().unwrap() == *self.min.last().unwrap() {
            self.min.pop();
        }
    }

    fn top(&self) -> i32 {
        *self.items.last().unwrap()
    }

    fn min(&self) -> i32 {
        *self.min.last().unwrap()
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * let obj = MinStack::new();
 * obj.push(x);
 * obj.pop();
 * let ret_3: i32 = obj.top();
 * let ret_4: i32 = obj.min();
 */
```

### **C#**

```cs
public class MinStack {
    Stack<int> stack;
    Stack<int> minStack;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<int>();
        minStack = new Stack<int>();
        minStack.Push(int.MaxValue);
    }

    public void Push(int x) {
        stack.Push(x);
        minStack.Push(Math.Min(minStack.Peek(), x));
    }

    public void Pop() {
        stack.Pop();
        minStack.Pop();
    }

    public int Top() {
        return stack.Peek();
    }

    public int Min() {
        return minStack.Peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.Push(x);
 * obj.Pop();
 * int param_3 = obj.Top();
 * int param_4 = obj.Min();
 */
```

### **...**

```

```

<!-- tabs:end -->
