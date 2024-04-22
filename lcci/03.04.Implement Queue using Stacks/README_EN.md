# [03.04. Implement Queue using Stacks](https://leetcode.cn/problems/implement-queue-using-stacks-lcci)

[中文文档](/lcci/03.04.Implement%20Queue%20using%20Stacks/README.md)

## Description

<p>Implement a MyQueue class which implements a queue using two stacks.</p>

&nbsp;

<p><strong>Example: </strong></p>

<pre>

MyQueue queue = new MyQueue();



queue.push(1);

queue.push(2);

queue.peek();  // return 1

queue.pop();   // return 1

queue.empty(); // return false</pre>

<p>&nbsp;</p>

<p><b>Notes:</b></p>

<ul>
	<li>You must use&nbsp;<i>only</i>&nbsp;standard operations of a stack -- which means only&nbsp;<code>push to top</code>,&nbsp;<code>peek/pop from top</code>,&nbsp;<code>size</code>, and&nbsp;<code>is empty</code>&nbsp;operations are valid.</li>
	<li>Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.</li>
	<li>You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).</li>
</ul>

<p>&nbsp;</p>

## Solutions

### Solution 1: Double Stack

We use two stacks, where `stk1` is used for enqueue, and another stack `stk2` is used for dequeue.

When enqueueing, we directly push the element into `stk1`. The time complexity is $O(1)$.

When dequeueing, we first check whether `stk2` is empty. If it is empty, we pop all elements from `stk1` and push them into `stk2`, and then pop an element from `stk2`. If `stk2` is not empty, we directly pop an element from `stk2`. The amortized time complexity is $O(1)$.

When getting the front element, we first check whether `stk2` is empty. If it is empty, we pop all elements from `stk1` and push them into `stk2`, and then get the top element from `stk2`. If `stk2` is not empty, we directly get the top element from `stk2`. The amortized time complexity is $O(1)$.

When checking whether the queue is empty, we only need to check whether both stacks are empty. The time complexity is $O(1)$.

<!-- tabs:start -->

```python
class MyQueue:
    def __init__(self):
        self.stk1 = []
        self.stk2 = []

    def push(self, x: int) -> None:
        self.stk1.append(x)

    def pop(self) -> int:
        self.move()
        return self.stk2.pop()

    def peek(self) -> int:
        self.move()
        return self.stk2[-1]

    def empty(self) -> bool:
        return not self.stk1 and not self.stk2

    def move(self):
        if not self.stk2:
            while self.stk1:
                self.stk2.append(self.stk1.pop())


# Your MyQueue object will be instantiated and called as such:
# obj = MyQueue()
# obj.push(x)
# param_2 = obj.pop()
# param_3 = obj.peek()
# param_4 = obj.empty()
```

```java
class MyQueue {
    private Deque<Integer> stk1 = new ArrayDeque<>();
    private Deque<Integer> stk2 = new ArrayDeque<>();

    public MyQueue() {
    }

    public void push(int x) {
        stk1.push(x);
    }

    public int pop() {
        move();
        return stk2.pop();
    }

    public int peek() {
        move();
        return stk2.peek();
    }

    public boolean empty() {
        return stk1.isEmpty() && stk2.isEmpty();
    }

    private void move() {
        while (stk2.isEmpty()) {
            while (!stk1.isEmpty()) {
                stk2.push(stk1.pop());
            }
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
```

```cpp
class MyQueue {
public:
    MyQueue() {
    }

    void push(int x) {
        stk1.push(x);
    }

    int pop() {
        move();
        int ans = stk2.top();
        stk2.pop();
        return ans;
    }

    int peek() {
        move();
        return stk2.top();
    }

    bool empty() {
        return stk1.empty() && stk2.empty();
    }

private:
    stack<int> stk1;
    stack<int> stk2;

    void move() {
        if (stk2.empty()) {
            while (!stk1.empty()) {
                stk2.push(stk1.top());
                stk1.pop();
            }
        }
    }
};

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue* obj = new MyQueue();
 * obj->push(x);
 * int param_2 = obj->pop();
 * int param_3 = obj->peek();
 * bool param_4 = obj->empty();
 */
```

```go
type MyQueue struct {
	stk1 []int
	stk2 []int
}

func Constructor() MyQueue {
	return MyQueue{[]int{}, []int{}}
}

func (this *MyQueue) Push(x int) {
	this.stk1 = append(this.stk1, x)
}

func (this *MyQueue) Pop() int {
	this.move()
	ans := this.stk2[len(this.stk2)-1]
	this.stk2 = this.stk2[:len(this.stk2)-1]
	return ans
}

func (this *MyQueue) Peek() int {
	this.move()
	return this.stk2[len(this.stk2)-1]
}

func (this *MyQueue) Empty() bool {
	return len(this.stk1) == 0 && len(this.stk2) == 0
}

func (this *MyQueue) move() {
	if len(this.stk2) == 0 {
		for len(this.stk1) > 0 {
			this.stk2 = append(this.stk2, this.stk1[len(this.stk1)-1])
			this.stk1 = this.stk1[:len(this.stk1)-1]
		}
	}
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Push(x);
 * param_2 := obj.Pop();
 * param_3 := obj.Peek();
 * param_4 := obj.Empty();
 */
```

```ts
class MyQueue {
    stk1: number[];
    stk2: number[];

    constructor() {
        this.stk1 = [];
        this.stk2 = [];
    }

    push(x: number): void {
        this.stk1.push(x);
    }

    pop(): number {
        this.move();
        return this.stk2.pop();
    }

    peek(): number {
        this.move();
        return this.stk2.at(-1);
    }

    empty(): boolean {
        return !this.stk1.length && !this.stk2.length;
    }

    move(): void {
        if (!this.stk2.length) {
            while (this.stk1.length) {
                this.stk2.push(this.stk1.pop()!);
            }
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * var obj = new MyQueue()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.peek()
 * var param_4 = obj.empty()
 */
```

```rust
use std::collections::VecDeque;

struct MyQueue {
    stk1: Vec<i32>,
    stk2: Vec<i32>,
}

impl MyQueue {
    fn new() -> Self {
        MyQueue {
            stk1: Vec::new(),
            stk2: Vec::new(),
        }
    }

    fn push(&mut self, x: i32) {
        self.stk1.push(x);
    }

    fn pop(&mut self) -> i32 {
        self.move_elements();
        self.stk2.pop().unwrap()
    }

    fn peek(&mut self) -> i32 {
        self.move_elements();
        *self.stk2.last().unwrap()
    }

    fn empty(&self) -> bool {
        self.stk1.is_empty() && self.stk2.is_empty()
    }

    fn move_elements(&mut self) {
        if self.stk2.is_empty() {
            while let Some(element) = self.stk1.pop() {
                self.stk2.push(element);
            }
        }
    }
}/**
 * Your MyQueue object will be instantiated and called as such:
 * let obj = MyQueue::new();
 * obj.push(x);
 * let ret_2: i32 = obj.pop();
 * let ret_3: i32 = obj.peek();
 * let ret_4: bool = obj.empty();
 */
```

```swift
class MyQueue {
    private var stk1: [Int] = []
    private var stk2: [Int] = []

    init() {}

    func push(_ x: Int) {
        stk1.append(x)
    }

    @discardableResult
    func pop() -> Int {
        move()
        return stk2.removeLast()
    }

    func peek() -> Int {
        move()
        return stk2.last!
    }

    func empty() -> Bool {
        return stk1.isEmpty && stk2.isEmpty
    }

    private func move() {
        if stk2.isEmpty {
            while !stk1.isEmpty {
                stk2.append(stk1.removeLast())
            }
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * let obj = new MyQueue();
 * obj.push(x);
 * let param_2 = obj.pop();
 * let param_3 = obj.peek();
 * var myValue : Bool 
 * myValue = obj.empty();
 */
```

<!-- tabs:end -->

<!-- end -->
