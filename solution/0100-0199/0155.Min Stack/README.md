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

<!-- 这里可写通用的实现逻辑 -->

**方法一：双栈**

我们用两个栈来实现，其中 `stk1` 用来存储数据，`stk2` 用来存储当前栈中的最小值。初始时，`stk2` 中存储一个极大值。

-   当我们向栈中压入一个元素 $x$ 时，我们将 $x$ 压入 `stk1`，并将 `min(x, stk2[-1])` 压入 `stk2`。
-   当我们从栈中弹出一个元素时，我们将 `stk1` 和 `stk2` 的栈顶元素都弹出。
-   当我们要获取当前栈中的栈顶元素时，我们只需要返回 `stk1` 的栈顶元素即可。
-   当我们要获取当前栈中的最小值时，我们只需要返回 `stk2` 的栈顶元素即可。

每个操作的时间复杂度为 $O(1)$。整体的空间复杂度为 $O(n)$，其中 $n$ 为栈中元素的个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class MinStack:

    def __init__(self):
        self.stk1 = []
        self.stk2 = [inf]

    def push(self, val: int) -> None:
        self.stk1.append(val)
        self.stk2.append(min(val, self.stk2[-1]))

    def pop(self) -> None:
        self.stk1.pop()
        self.stk2.pop()

    def top(self) -> int:
        return self.stk1[-1]

    def getMin(self) -> int:
        return self.stk2[-1]


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
    private Deque<Integer> stk1 = new ArrayDeque<>();
    private Deque<Integer> stk2 = new ArrayDeque<>();

    public MinStack() {
        stk2.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stk1.push(val);
        stk2.push(Math.min(val, stk2.peek()));
    }

    public void pop() {
        stk1.pop();
        stk2.pop();
    }

    public int top() {
        return stk1.peek();
    }

    public int getMin() {
        return stk2.peek();
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
public:
    MinStack() {
        stk2.push(INT_MAX);
    }

    void push(int val) {
        stk1.push(val);
        stk2.push(min(val, stk2.top()));
    }

    void pop() {
        stk1.pop();
        stk2.pop();
    }

    int top() {
        return stk1.top();
    }

    int getMin() {
        return stk2.top();
    }

private:
    stack<int> stk1;
    stack<int> stk2;
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

### **Go**

```go
type MinStack struct {
	stk1 []int
	stk2 []int
}

func Constructor() MinStack {
	return MinStack{[]int{}, []int{math.MaxInt32}}
}

func (this *MinStack) Push(val int) {
	this.stk1 = append(this.stk1, val)
	this.stk2 = append(this.stk2, min(val, this.stk2[len(this.stk2)-1]))
}

func (this *MinStack) Pop() {
	this.stk1 = this.stk1[:len(this.stk1)-1]
	this.stk2 = this.stk2[:len(this.stk2)-1]
}

func (this *MinStack) Top() int {
	return this.stk1[len(this.stk1)-1]
}

func (this *MinStack) GetMin() int {
	return this.stk2[len(this.stk2)-1]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

/**
 * Your MinStack object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Push(val);
 * obj.Pop();
 * param_3 := obj.Top();
 * param_4 := obj.GetMin();
 */
```

### **TypeScript**

```ts
class MinStack {
    stk1: number[];
    stk2: number[];

    constructor() {
        this.stk1 = [];
        this.stk2 = [Infinity];
    }

    push(val: number): void {
        this.stk1.push(val);
        this.stk2.push(Math.min(val, this.stk2[this.stk2.length - 1]));
    }

    pop(): void {
        this.stk1.pop();
        this.stk2.pop();
    }

    top(): number {
        return this.stk1[this.stk1.length - 1];
    }

    getMin(): number {
        return this.stk2[this.stk2.length - 1];
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = new MinStack()
 * obj.push(x)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */
```

### **JavaScript**

```js
var MinStack = function () {
    this.stk1 = [];
    this.stk2 = [Infinity];
};

/**
 * @param {number} val
 * @return {void}
 */
MinStack.prototype.push = function (val) {
    this.stk1.push(val);
    this.stk2.push(Math.min(this.stk2[this.stk2.length - 1], val));
};

/**
 * @return {void}
 */
MinStack.prototype.pop = function () {
    this.stk1.pop();
    this.stk2.pop();
};

/**
 * @return {number}
 */
MinStack.prototype.top = function () {
    return this.stk1[this.stk1.length - 1];
};

/**
 * @return {number}
 */
MinStack.prototype.getMin = function () {
    return this.stk2[this.stk2.length - 1];
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

### **Rust**

```rust
use std::collections::VecDeque;
struct MinStack {
    stk1: VecDeque<i32>,
    stk2: VecDeque<i32>,
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl MinStack {

    fn new() -> Self {
        Self { stk1: VecDeque::new(), stk2: VecDeque::new() }
    }

    fn push(&mut self, x: i32) {
        self.stk1.push_back(x);
        if self.stk2.is_empty() || *self.stk2.back().unwrap() >= x {
            self.stk2.push_back(x);
        }
    }

    fn pop(&mut self) {
        let val = self.stk1.pop_back().unwrap();
        if *self.stk2.back().unwrap() == val {
            self.stk2.pop_back();
        }
    }

    fn top(&self) -> i32 {
        *self.stk1.back().unwrap()
    }

    fn get_min(&self) -> i32 {
        *self.stk2.back().unwrap()
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * let obj = MinStack::new();
 * obj.push(x);
 * obj.pop();
 * let ret_3: i32 = obj.top();
 * let ret_4: i32 = obj.get_min();
 */
```

### **C#**

```cs
public class MinStack {
    private Stack<int> stk1 = new Stack<int>();
    private Stack<int> stk2 = new Stack<int>();
    
    public MinStack() {
        stk2.Push(int.MaxValue);
    }
    
    public void Push(int x) {
        stk1.Push(x);
        stk2.Push(Math.Min(x, GetMin()));
    }
    
    public void Pop() {
        stk1.Pop();
        stk2.Pop();
    }
    
    public int Top() {
        return stk1.Peek();
    }
    
    public int GetMin() {
        return stk2.Peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.Push(x);
 * obj.Pop();
 * int param_3 = obj.Top();
 * int param_4 = obj.GetMin();
 */
```

### **...**

```

```

<!-- tabs:end -->
