# [面试题 03.05. 栈排序](https://leetcode.cn/problems/sort-of-stacks-lcci)

[English Version](/lcci/03.05.Sort%20of%20Stacks/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。该栈支持如下操作：<code>push</code>、<code>pop</code>、<code>peek</code> 和 <code>isEmpty</code>。当栈为空时，<code>peek</code>&nbsp;返回 -1。</p>

<p><strong>示例1:</strong></p>

<pre><strong> 输入</strong>：
[&quot;SortedStack&quot;, &quot;push&quot;, &quot;push&quot;, &quot;peek&quot;, &quot;pop&quot;, &quot;peek&quot;]
[[], [1], [2], [], [], []]
<strong> 输出</strong>：
[null,null,null,1,null,2]
</pre>

<p><strong>示例2:</strong></p>

<pre><strong> 输入</strong>：
[&quot;SortedStack&quot;, &quot;pop&quot;, &quot;pop&quot;, &quot;push&quot;, &quot;pop&quot;, &quot;isEmpty&quot;]
[[], [], [], [1], [], []]
<strong> 输出</strong>：
[null,null,null,null,null,true]
</pre>

<p><strong>说明:</strong></p>

<ol>
	<li>栈中的元素数目在[0, 5000]范围内。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

利用辅助栈实现 `push` 操作，其余操作不变。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class SortedStack:
    def __init__(self):
        self.s = []

    def push(self, val: int) -> None:
        t = []
        while not self.isEmpty() and self.s[-1] < val:
            t.append(self.s.pop())
        self.s.append(val)
        while len(t) > 0:
            self.s.append(t.pop())

    def pop(self) -> None:
        if not self.isEmpty():
            self.s.pop()

    def peek(self) -> int:
        return -1 if self.isEmpty() else self.s[-1]

    def isEmpty(self) -> bool:
        return len(self.s) == 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class SortedStack {
    private Stack<Integer> s;
    public SortedStack() {
        s = new Stack<>();
    }

    public void push(int val) {
        Stack<Integer> t = new Stack<>();
        while (!isEmpty() && s.peek() < val) {
            t.push(s.pop());
        }
        s.push(val);
        while (!t.isEmpty()) {
            s.push(t.pop());
        }
    }

    public void pop() {
        if (!isEmpty()) {
            s.pop();
        }
    }

    public int peek() {
        return isEmpty() ? -1 : s.peek();
    }

    public boolean isEmpty() {
        return s.isEmpty();
    }
}

/**
 * Your SortedStack object will be instantiated and called as such:
 * SortedStack obj = new SortedStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.isEmpty();
 */
```

### **TypeScript**

```ts
class SortedStack {
    stack: number[];
    constructor() {
        this.stack = [];
    }

    push(val: number): void {
        let t = [];
        while (!this.isEmpty() && this.peek() < val) {
            t.push(this.stack.pop());
        }
        this.stack.push(val);
        while (t.length > 0) {
            this.stack.push(t.pop());
        }
    }

    pop(): void {
        this.stack.pop();
    }

    peek(): number {
        return this.isEmpty() ? -1 : this.stack[this.stack.length - 1];
    }

    isEmpty(): boolean {
        return this.stack.length == 0;
    }
}

/**
 * Your SortedStack object will be instantiated and called as such:
 * var obj = new SortedStack()
 * obj.push(val)
 * obj.pop()
 * var param_3 = obj.peek()
 * var param_4 = obj.isEmpty()
 */
```

```ts
class SortedStack {
    private stack: number[];

    constructor() {
        this.stack = [];
    }

    push(val: number): void {
        if (this.isEmpty() || this.peek() > val) {
            this.stack.push(val);
            return;
        }

        const tmp = this.stack.pop();
        this.push(val);
        this.stack.push(tmp);
    }

    pop(): void {
        this.stack.pop();
    }

    peek(): number {
        return this.stack[this.stack.length - 1] ?? -1;
    }

    isEmpty(): boolean {
        return this.stack.length === 0;
    }
}

/**
 * Your SortedStack object will be instantiated and called as such:
 * var obj = new SortedStack()
 * obj.push(val)
 * obj.pop()
 * var param_3 = obj.peek()
 * var param_4 = obj.isEmpty()
 */
```

### **Go**

```go
type SortedStack struct {
	data []int
}

func Constructor() SortedStack {
	return SortedStack{make([]int, 0)}
}

func (s *SortedStack) Push(val int) {
	temp := make([]int, 0)
	for !s.IsEmpty() && s.Peek() < val {
		temp = append(temp, s.Peek())
		s.Pop()
	}
	s.data = append(s.data, val)
	for len(temp) > 0 {
		s.data = append(s.data, temp[len(temp)-1])
		temp = temp[:len(temp)-1]
	}
}

func (s *SortedStack) Pop() {
	if !s.IsEmpty() {
		s.data = s.data[:len(s.data)-1]
	}
}

func (s *SortedStack) Peek() int {
	if !s.IsEmpty() {
		return s.data[len(s.data)-1]
	}
	return -1
}

func (s *SortedStack) IsEmpty() bool {
	return len(s.data) == 0
}
```

### **Rust**

```rust
use std::collections::VecDeque;
struct SortedStack {
    stack: VecDeque<i32>
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl SortedStack {

    fn new() -> Self {
        Self { stack: VecDeque::new() }
    }

    fn push(&mut self, val: i32) {
        if self.is_empty() || self.peek() > val {
            self.stack.push_back(val);
            return;
        }
        let t = self.stack.pop_back().unwrap();
        self.push(val);
        self.stack.push_back(t);
    }

    fn pop(&mut self) {
        self.stack.pop_back();
    }

    fn peek(&self) -> i32 {
        *self.stack.back().unwrap_or(&-1)
    }

    fn is_empty(&self) -> bool {
        self.stack.is_empty()
    }
}

/**
 * Your SortedStack object will be instantiated and called as such:
 * let obj = SortedStack::new();
 * obj.push(val);
 * obj.pop();
 * let ret_3: i32 = obj.peek();
 * let ret_4: bool = obj.is_empty();
 */
```

### **...**

```

```

<!-- tabs:end -->
