# [225. 用队列实现栈](https://leetcode.cn/problems/implement-stack-using-queues)

[English Version](/solution/0200-0299/0225.Implement%20Stack%20using%20Queues/README_EN.md)

<!-- tags:栈,设计,队列 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（<code>push</code>、<code>top</code>、<code>pop</code> 和 <code>empty</code>）。</p>

<p>实现 <code>MyStack</code> 类：</p>

<ul>
	<li><code>void push(int x)</code> 将元素 x 压入栈顶。</li>
	<li><code>int pop()</code> 移除并返回栈顶元素。</li>
	<li><code>int top()</code> 返回栈顶元素。</li>
	<li><code>boolean empty()</code> 如果栈是空的，返回 <code>true</code> ；否则，返回 <code>false</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>注意：</strong></p>

<ul>
	<li>你只能使用队列的标准操作 —— 也就是&nbsp;<code>push to back</code>、<code>peek/pop from front</code>、<code>size</code> 和&nbsp;<code>is empty</code>&nbsp;这些操作。</li>
	<li>你所使用的语言也许不支持队列。&nbsp;你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列&nbsp;, 只要是标准的队列操作即可。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["MyStack", "push", "push", "top", "pop", "empty"]
[[], [1], [2], [], [], []]
<strong>输出：</strong>
[null, null, null, 2, 2, false]

<strong>解释：</strong>
MyStack myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.top(); // 返回 2
myStack.pop(); // 返回 2
myStack.empty(); // 返回 False
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= x &lt;= 9</code></li>
	<li>最多调用<code>100</code> 次 <code>push</code>、<code>pop</code>、<code>top</code> 和 <code>empty</code></li>
	<li>每次调用 <code>pop</code> 和 <code>top</code> 都保证栈不为空</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能否仅用一个队列来实现栈。</p>

## 解法

### 方法一：两个队列

我们使用两个队列 $q_1$ 和 $q_2$，其中 $q_1$ 用于存储栈中的元素，而 $q_2$ 用于辅助实现栈的操作。

-   `push` 操作：将元素压入 $q_2$，然后将 $q_1$ 中的元素依次弹出并压入 $q_2$，最后交换 $q_1$ 和 $q_2$ 的引用。时间复杂度 $O(n)$。
-   `pop` 操作：直接弹出 $q_1$ 的队首元素。时间复杂度 $O(1)$。
-   `top` 操作：直接返回 $q_1$ 的队首元素。时间复杂度 $O(1)$。
-   `empty` 操作：判断 $q_1$ 是否为空。时间复杂度 $O(1)$。

空间复杂度 $O(n)$，其中 $n$ 是栈中元素的个数。

<!-- tabs:start -->

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

```java
import java.util.Deque;

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

<!-- end -->
