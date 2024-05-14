---
comment: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/03.05.Sort%20of%20Stacks/README_EN.md
---

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

### Solution 1: Stack + Auxiliary Stack

We define a stack $stk$ for storing elements.

In the `push` operation, we define an auxiliary stack $t$ for storing elements in $stk$ that are smaller than the current element. We pop all elements smaller than the current element from $stk$ and store them in $t$, then push the current element into $stk$, and finally pop all elements from $t$ and push them back into $stk$. The time complexity is $O(n)$.

In the `pop` operation, we just need to check if $stk$ is empty. If it's not, we pop the top element. The time complexity is $O(1)$.

In the `peek` operation, we just need to check if $stk$ is empty. If it is, we return -1, otherwise, we return the top element. The time complexity is $O(1)$.

In the `isEmpty` operation, we just need to check if $stk$ is empty. The time complexity is $O(1)$.

The space complexity is $O(n)$, where $n$ is the number of elements in the stack.

<!-- tabs:start -->

```python
class SortedStack:

    def __init__(self):
        self.stk = []

    def push(self, val: int) -> None:
        t = []
        while self.stk and self.stk[-1] < val:
            t.append(self.stk.pop())
        self.stk.append(val)
        while t:
            self.stk.append(t.pop())

    def pop(self) -> None:
        if not self.isEmpty():
            self.stk.pop()

    def peek(self) -> int:
        return -1 if self.isEmpty() else self.stk[-1]

    def isEmpty(self) -> bool:
        return not self.stk


# Your SortedStack object will be instantiated and called as such:
# obj = SortedStack()
# obj.push(val)
# obj.pop()
# param_3 = obj.peek()
# param_4 = obj.isEmpty()
```

```java
class SortedStack {
    private Deque<Integer> stk = new ArrayDeque<>();

    public SortedStack() {
    }

    public void push(int val) {
        Deque<Integer> t = new ArrayDeque<>();
        while (!stk.isEmpty() && stk.peek() < val) {
            t.push(stk.pop());
        }
        stk.push(val);
        while (!t.isEmpty()) {
            stk.push(t.pop());
        }
    }

    public void pop() {
        if (!isEmpty()) {
            stk.pop();
        }
    }

    public int peek() {
        return isEmpty() ? -1 : stk.peek();
    }

    public boolean isEmpty() {
        return stk.isEmpty();
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

```cpp
class SortedStack {
public:
    SortedStack() {
    }

    void push(int val) {
        stack<int> t;
        while (!stk.empty() && stk.top() < val) {
            t.push(stk.top());
            stk.pop();
        }
        stk.push(val);
        while (!t.empty()) {
            stk.push(t.top());
            t.pop();
        }
    }

    void pop() {
        if (!isEmpty()) {
            stk.pop();
        }
    }

    int peek() {
        return isEmpty() ? -1 : stk.top();
    }

    bool isEmpty() {
        return stk.empty();
    }

private:
    stack<int> stk;
};

/**
 * Your SortedStack object will be instantiated and called as such:
 * SortedStack* obj = new SortedStack();
 * obj->push(val);
 * obj->pop();
 * int param_3 = obj->peek();
 * bool param_4 = obj->isEmpty();
 */
```

```go
type SortedStack struct {
	stk []int
}

func Constructor() SortedStack {
	return SortedStack{}
}

func (this *SortedStack) Push(val int) {
	t := make([]int, 0)
	for len(this.stk) > 0 && this.stk[len(this.stk)-1] < val {
		t = append(t, this.stk[len(this.stk)-1])
		this.stk = this.stk[:len(this.stk)-1]
	}
	this.stk = append(this.stk, val)
	for i := len(t) - 1; i >= 0; i-- {
		this.stk = append(this.stk, t[i])
	}
}

func (this *SortedStack) Pop() {
	if !this.IsEmpty() {
		this.stk = this.stk[:len(this.stk)-1]
	}
}

func (this *SortedStack) Peek() int {
	if this.IsEmpty() {
		return -1
	}
	return this.stk[len(this.stk)-1]
}

func (this *SortedStack) IsEmpty() bool {
	return len(this.stk) == 0
}

/**
 * Your SortedStack object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Push(val);
 * obj.Pop();
 * param_3 := obj.Peek();
 * param_4 := obj.IsEmpty();
 */
```

```ts
class SortedStack {
    private stk: number[] = [];
    constructor() {}

    push(val: number): void {
        const t: number[] = [];
        while (this.stk.length > 0 && this.stk.at(-1)! < val) {
            t.push(this.stk.pop()!);
        }
        this.stk.push(val);
        while (t.length > 0) {
            this.stk.push(t.pop()!);
        }
    }

    pop(): void {
        if (!this.isEmpty()) {
            this.stk.pop();
        }
    }

    peek(): number {
        return this.isEmpty() ? -1 : this.stk.at(-1)!;
    }

    isEmpty(): boolean {
        return this.stk.length === 0;
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

```rust
use std::collections::VecDeque;

struct SortedStack {
    stk: VecDeque<i32>,
}

impl SortedStack {
    fn new() -> Self {
        SortedStack {
            stk: VecDeque::new(),
        }
    }

    fn push(&mut self, val: i32) {
        let mut t = VecDeque::new();
        while let Some(top) = self.stk.pop_back() {
            if top < val {
                t.push_back(top);
            } else {
                self.stk.push_back(top);
                break;
            }
        }
        self.stk.push_back(val);
        while let Some(top) = t.pop_back() {
            self.stk.push_back(top);
        }
    }

    fn pop(&mut self) {
        if !self.is_empty() {
            self.stk.pop_back();
        }
    }

    fn peek(&self) -> i32 {
        if self.is_empty() { -1 } else { *self.stk.back().unwrap() }
    }

    fn is_empty(&self) -> bool {
        self.stk.is_empty()
    }
}/**
 * Your SortedStack object will be instantiated and called as such:
 * let obj = SortedStack::new();
 * obj.push(val);
 * obj.pop();
 * let ret_3: i32 = obj.peek();
 * let ret_4: bool = obj.is_empty();
 */
```

```swift
class SortedStack {
    private var stk: [Int] = []

    init() {}

    func push(_ val: Int) {
        var temp: [Int] = []
        while let top = stk.last, top < val {
            temp.append(stk.removeLast())
        }
        stk.append(val)
        while let last = temp.popLast() {
            stk.append(last)
        }
    }

    func pop() {
        if !isEmpty() {
            stk.removeLast()
        }
    }

    func peek() -> Int {
        return isEmpty() ? -1 : stk.last!
    }

    func isEmpty() -> Bool {
        return stk.isEmpty
    }
}

/**
 * Your SortedStack object will be instantiated and called as such:
 * let obj = new SortedStack();
 * obj.push(val);
 * obj.pop();
 * let param_3 = obj.peek();
 * var myVar: Bool;
 * myVar = obj.isEmpty();
 */
```

<!-- tabs:end -->

<!-- end -->
