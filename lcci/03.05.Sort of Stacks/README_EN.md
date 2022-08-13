# [03.05. Sort of Stacks](https://leetcode.cn/problems/sort-of-stacks-lcci)

[中文文档](/lcci/03.05.Sort%20of%20Stacks/README.md)

## Description

<p>Write a program to sort a stack such that the smallest items are on the top. You can use an additional temporary stack, but you may not copy the elements into any other data structure (such as an array). The stack supports the following operations: <code>push</code>, <code>pop</code>, <code>peek</code>, and <code>isEmpty</code>. When the stack is empty, <code>peek</code> should return -1.</p>

<p><strong>Example1:</strong></p>

<pre>

<strong> Input</strong>:

[&quot;SortedStack&quot;, &quot;push&quot;, &quot;push&quot;, &quot;peek&quot;, &quot;pop&quot;, &quot;peek&quot;]

[[], [1], [2], [], [], []]

<strong> Output</strong>:

[null,null,null,1,null,2]

</pre>

<p><strong>Example2:</strong></p>

<pre>

<strong> Input</strong>:

[&quot;SortedStack&quot;, &quot;pop&quot;, &quot;pop&quot;, &quot;push&quot;, &quot;pop&quot;, &quot;isEmpty&quot;]

[[], [], [], [1], [], []]

<strong> Output</strong>:

[null,null,null,null,null,true]

</pre>

<p><strong>Note:</strong></p>

<ol>
	<li>The total number of elements in the stack is within the range [0, 5000].</li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

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
