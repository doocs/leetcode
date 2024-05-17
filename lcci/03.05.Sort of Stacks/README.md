---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/03.05.Sort%20of%20Stacks/README.md
---

<!-- problem:start -->

# [面试题 03.05. 栈排序](https://leetcode.cn/problems/sort-of-stacks-lcci)

[English Version](/lcci/03.05.Sort%20of%20Stacks/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：栈 + 辅助栈

我们定义一个栈 $stk$，用于存放元素。

在 `push` 操作中，我们定义一个辅助栈 $t$，用于存放 $stk$ 中比当前元素小的元素。我们将 $stk$ 中比当前元素小的元素全部弹出并存放到 $t$ 中，然后将当前元素压入 $stk$，最后将 $t$ 中的元素全部弹出并压入 $stk$。时间复杂度 $O(n)$。

在 `pop` 操作中，我们只需要判断 $stk$ 是否为空，如果不为空，则弹出栈顶元素。时间复杂度 $O(1)$。

在 `peek` 操作中，我们只需要判断 $stk$ 是否为空，如果为空则返回 -1，否则返回栈顶元素。时间复杂度 $O(1)$。

在 `isEmpty` 操作中，我们只需要判断 $stk$ 是否为空。时间复杂度 $O(1)$。

空间复杂度 $O(n)$，其中 $n$ 为栈中元素的个数。

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

<!-- solution:end -->

<!-- problem:end -->
