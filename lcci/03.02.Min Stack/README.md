# [面试题 03.02. 栈的最小值](https://leetcode.cn/problems/min-stack-lcci)

[English Version](/lcci/03.02.Min%20Stack/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。执行push、pop和min操作的时间复杂度必须为O(1)。</p><br><p><strong>示例：</strong><pre>MinStack minStack = new MinStack();<br>minStack.push(-2);<br>minStack.push(0);<br>minStack.push(-3);<br>minStack.getMin();   --> 返回 -3.<br>minStack.pop();<br>minStack.top();      --> 返回 0.<br>minStack.getMin();   --> 返回 -2.</pre></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双栈**

我们用两个栈来实现，其中`stk1` 用来存储数据，`stk2` 用来存储当前栈中的最小值。初始时，`stk2` 中存储一个极大值。

-   当我们向栈中压入一个元素 `x` 时，我们将 `x` 压入 `stk1`，并将 `min(x, stk2[-1])` 压入 `stk2`。
-   当我们从栈中弹出一个元素时，我们将 `stk1` 和 `stk2` 的栈顶元素都弹出。
-   当我们要获取当前栈中的栈顶元素时，我们只需要返回 `stk1` 的栈顶元素即可。
-   当我们要获取当前栈中的最小值时，我们只需要返回 `stk2` 的栈顶元素即可。

时间复杂度：对于每个操作，时间复杂度均为 $O(1)$，空间复杂度 $O(n)$。

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
    private Deque<Integer> stk1 = new ArrayDeque<>();
    private Deque<Integer> stk2 = new ArrayDeque<>();

    /** initialize your data structure here. */
    public MinStack() {
        stk2.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        stk1.push(x);
        stk2.push(Math.min(x, stk2.peek()));
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
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```

### **C++**

```cpp
class MinStack {
public:
    /** initialize your data structure here. */
    MinStack() {
        stk2.push(INT_MAX);
    }

    void push(int x) {
        stk1.push(x);
        stk2.push(min(x, stk2.top()));
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
 * obj->push(x);
 * obj->pop();
 * int param_3 = obj->top();
 * int param_4 = obj->getMin();
 */
```

### **TypeScript**

```ts
class MinStack {
    stack: number[];
    mins: number[];
    constructor() {
        this.stack = [];
        this.mins = [];
    }

    push(x: number): void {
        this.stack.push(x);
        this.mins.push(Math.min(this.getMin(), x));
    }

    pop(): void {
        this.stack.pop();
        this.mins.pop();
    }

    top(): number {
        return this.stack[this.stack.length - 1];
    }

    getMin(): number {
        return this.mins.length == 0
            ? Infinity
            : this.mins[this.mins.length - 1];
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

### **Go**

```go
type MinStack struct {
	stk1 []int
	stk2 []int
}

/** initialize your data structure here. */
func Constructor() MinStack {
	return MinStack{[]int{}, []int{math.MaxInt32}}
}

func (this *MinStack) Push(x int) {
	this.stk1 = append(this.stk1, x)
	this.stk2 = append(this.stk2, min(x, this.stk2[len(this.stk2)-1]))
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
 * obj.Push(x);
 * obj.Pop();
 * param_3 := obj.Top();
 * param_4 := obj.GetMin();
 */
```

### **Rust**

```rust
use std::collections::VecDeque;
struct MinStack {
    stack: VecDeque<i32>,
    min_stack: VecDeque<i32>,
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl MinStack {

    /** initialize your data structure here. */
    fn new() -> Self {
        Self { stack: VecDeque::new(), min_stack: VecDeque::new() }
    }

    fn push(&mut self, x: i32) {
        self.stack.push_back(x);
        if self.min_stack.is_empty() || *self.min_stack.back().unwrap() >= x {
            self.min_stack.push_back(x);
        }
    }

    fn pop(&mut self) {
        let val = self.stack.pop_back().unwrap();
        if *self.min_stack.back().unwrap() == val {
            self.min_stack.pop_back();
        }
    }

    fn top(&self) -> i32 {
        *self.stack.back().unwrap()
    }

    fn get_min(&self) -> i32 {
        *self.min_stack.back().unwrap()
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

    /** initialize your data structure here. */
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
