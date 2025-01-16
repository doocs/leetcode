---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0225.Implement%20Stack%20using%20Queues/README_EN.md
tags:
    - Stack
    - Design
    - Queue
---

<!-- problem:start -->

# [225. Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues)

[中文文档](/solution/0200-0299/0225.Implement%20Stack%20using%20Queues/README.md)

## Description

<!-- description:start -->

<p>Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (<code>push</code>, <code>top</code>, <code>pop</code>, and <code>empty</code>).</p>

<p>Implement the <code>MyStack</code> class:</p>

<ul>
	<li><code>void push(int x)</code> Pushes element x to the top of the stack.</li>
	<li><code>int pop()</code> Removes the element on the top of the stack and returns it.</li>
	<li><code>int top()</code> Returns the element on the top of the stack.</li>
	<li><code>boolean empty()</code> Returns <code>true</code> if the stack is empty, <code>false</code> otherwise.</li>
</ul>

<p><b>Notes:</b></p>

<ul>
	<li>You must use <strong>only</strong> standard operations of a queue, which means that only <code>push to back</code>, <code>peek/pop from front</code>, <code>size</code> and <code>is empty</code> operations are valid.</li>
	<li>Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue&#39;s standard operations.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;MyStack&quot;, &quot;push&quot;, &quot;push&quot;, &quot;top&quot;, &quot;pop&quot;, &quot;empty&quot;]
[[], [1], [2], [], [], []]
<strong>Output</strong>
[null, null, null, 2, 2, false]

<strong>Explanation</strong>
MyStack myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.top(); // return 2
myStack.pop(); // return 2
myStack.empty(); // return False
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= x &lt;= 9</code></li>
	<li>At most <code>100</code> calls will be made to <code>push</code>, <code>pop</code>, <code>top</code>, and <code>empty</code>.</li>
	<li>All the calls to <code>pop</code> and <code>top</code> are valid.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow-up:</strong> Can you implement the stack using only one queue?</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Queues

We use two queues $q_1$ and $q_2$, where $q_1$ is used to store the elements in the stack, and $q_2$ is used to assist in implementing the stack operations.

-   `push` operation: Push the element into $q_2$, then pop the elements in $q_1$ one by one and push them into $q_2$, finally swap the references of $q_1$ and $q_2$. The time complexity is $O(n)$.
-   `pop` operation: Directly pop the front element of $q_1$. The time complexity is $O(1)$.
-   `top` operation: Directly return the front element of $q_1$. The time complexity is $O(1)$.
-   `empty` operation: Check whether $q_1$ is empty. The time complexity is $O(1)$.

The space complexity is $O(n)$, where $n$ is the number of elements in the stack.

<!-- tabs:start -->

#### Python3

```python
class MyStack:
    def __init__(self):
        self.q1 = deque()
        self.q2 = deque()

    def push(self, x: int) -> None:
        self.q2.append(x)
        while self.q1:
            self.q2.append(self.q1.popleft())
        self.q1, self.q2 = self.q2, self.q1

    def pop(self) -> int:
        return self.q1.popleft()

    def top(self) -> int:
        return self.q1[0]

    def empty(self) -> bool:
        return len(self.q1) == 0


# Your MyStack object will be instantiated and called as such:
# obj = MyStack()
# obj.push(x)
# param_2 = obj.pop()
# param_3 = obj.top()
# param_4 = obj.empty()
```

#### Java

```java
class MyStack {
    private Deque<Integer> q1 = new ArrayDeque<>();
    private Deque<Integer> q2 = new ArrayDeque<>();

    public MyStack() {
    }

    public void push(int x) {
        q2.offer(x);
        while (!q1.isEmpty()) {
            q2.offer(q1.poll());
        }
        Deque<Integer> q = q1;
        q1 = q2;
        q2 = q;
    }

    public int pop() {
        return q1.poll();
    }

    public int top() {
        return q1.peek();
    }

    public boolean empty() {
        return q1.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
```

#### C++

```cpp
class MyStack {
public:
    MyStack() {
    }

    void push(int x) {
        q2.push(x);
        while (!q1.empty()) {
            q2.push(q1.front());
            q1.pop();
        }
        swap(q1, q2);
    }

    int pop() {
        int x = q1.front();
        q1.pop();
        return x;
    }

    int top() {
        return q1.front();
    }

    bool empty() {
        return q1.empty();
    }

private:
    queue<int> q1;
    queue<int> q2;
};

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack* obj = new MyStack();
 * obj->push(x);
 * int param_2 = obj->pop();
 * int param_3 = obj->top();
 * bool param_4 = obj->empty();
 */
```

#### Go

```go
type MyStack struct {
	q1 []int
	q2 []int
}

func Constructor() MyStack {
	return MyStack{}
}

func (this *MyStack) Push(x int) {
	this.q2 = append(this.q2, x)
	for len(this.q1) > 0 {
		this.q2 = append(this.q2, this.q1[0])
		this.q1 = this.q1[1:]
	}
	this.q1, this.q2 = this.q2, this.q1
}

func (this *MyStack) Pop() int {
	x := this.q1[0]
	this.q1 = this.q1[1:]
	return x
}

func (this *MyStack) Top() int {
	return this.q1[0]
}

func (this *MyStack) Empty() bool {
	return len(this.q1) == 0
}

/**
 * Your MyStack object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Push(x);
 * param_2 := obj.Pop();
 * param_3 := obj.Top();
 * param_4 := obj.Empty();
 */
```

#### TypeScript

```ts
class MyStack {
    q1: number[] = [];
    q2: number[] = [];

    constructor() {}

    push(x: number): void {
        this.q2.push(x);
        while (this.q1.length) {
            this.q2.push(this.q1.shift()!);
        }
        [this.q1, this.q2] = [this.q2, this.q1];
    }

    pop(): number {
        return this.q1.shift()!;
    }

    top(): number {
        return this.q1[0];
    }

    empty(): boolean {
        return this.q1.length === 0;
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * var obj = new MyStack()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.empty()
 */
```

#### Rust

```rust
use std::collections::VecDeque;

struct MyStack {
    /// There could only be two status at all time
    /// 1. One contains N elements, the other is empty
    /// 2. One contains N - 1 elements, the other contains exactly 1 element
    q_1: VecDeque<i32>,
    q_2: VecDeque<i32>,
    // Either 1 or 2, originally begins from 1
    index: i32,
}

impl MyStack {
    fn new() -> Self {
        Self {
            q_1: VecDeque::new(),
            q_2: VecDeque::new(),
            index: 1,
        }
    }

    fn move_data(&mut self) {
        // Always move from q1 to q2
        assert!(self.q_2.len() == 1);
        while !self.q_1.is_empty() {
            self.q_2.push_back(self.q_1.pop_front().unwrap());
        }
        let tmp = self.q_1.clone();
        self.q_1 = self.q_2.clone();
        self.q_2 = tmp;
    }

    fn push(&mut self, x: i32) {
        self.q_2.push_back(x);
        self.move_data();
    }

    fn pop(&mut self) -> i32 {
        self.q_1.pop_front().unwrap()
    }

    fn top(&mut self) -> i32 {
        *self.q_1.front().unwrap()
    }

    fn empty(&self) -> bool {
        self.q_1.is_empty()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
