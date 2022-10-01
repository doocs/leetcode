# [03.02. Min Stack](https://leetcode.cn/problems/min-stack-lcci)

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
        self.stk1 = []
        self.stk2 = [inf]

    def push(self, x: int) -> None:
        self.stk1.append(x)
        self.stk2.append(min(x, self.stk2[-1]))

    def pop(self) -> None:
        self.stk1.pop()
        self.stk2.pop()

    def top(self) -> int:
        return self.stk1[-1]

    def getMin(self) -> int:
        return self.stk2[-1]


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

### **TypeScript**

```ts
class MinStack {
    stk1: number[];
    stk2: number[];

    constructor() {
        this.stk1 = [];
        this.stk2 = [Infinity];
    }

    push(x: number): void {
        this.stk1.push(x);
        this.stk2.push(Math.min(x, this.stk2[this.stk2.length - 1]));
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
