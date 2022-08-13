# [面试题 03.04. 化栈为队](https://leetcode.cn/problems/implement-queue-using-stacks-lcci)

[English Version](/lcci/03.04.Implement%20Queue%20using%20Stacks/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>实现一个MyQueue类，该类用两个栈来实现一个队列。</p><br><p><strong>示例：</strong><pre>MyQueue queue = new MyQueue();<br><br>queue.push(1);<br>queue.push(2);<br>queue.peek();  // 返回 1<br>queue.pop();   // 返回 1<br>queue.empty(); // 返回 false</pre></p><br><p><strong>说明：</strong><br><ul><li>你只能使用标准的栈操作 -- 也就是只有 <code>push to top</code>, <code>peek/pop from top</code>, <code>size</code> 和 <code>is empty</code> 操作是合法的。</li><li>你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。</li><li>假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。</li></ul></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

-   每次压入元素时，放入第 1 个栈中；
-   第 2 个栈不为空时，不能倒入元素；
-   第 2 个栈为空时，必须将第 1 个栈的所有元素按顺序倒入第 2 个栈中。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class MyQueue:
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self._s1, self._s2 = [], []

    def push(self, x: int) -> None:
        """
        Push element x to the back of queue.
        """
        self._s1.append(x)

    def pop(self) -> int:
        """
        Removes the element from in front of queue and returns that element.
        """
        if len(self._s2) == 0:
            while self._s1:
                self._s2.append(self._s1.pop())
        return self._s2.pop()

    def peek(self) -> int:
        """
        Get the front element.
        """
        if len(self._s2) == 0:
            while self._s1:
                self._s2.append(self._s1.pop())
        return self._s2[-1]

    def empty(self) -> bool:
        """
        Returns whether the queue is empty.
        """
        return len(self._s1) + len(self._s2) == 0


# Your MyQueue object will be instantiated and called as such:
# obj = MyQueue()
# obj.push(x)
# param_2 = obj.pop()
# param_3 = obj.peek()
# param_4 = obj.empty()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class MyQueue {
    private Stack<Integer> s1;
    private Stack<Integer> s2;

    /** Initialize your data structure here. */
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (s2.empty()) {
            while (!s1.empty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (s2.empty()) {
            while (!s1.empty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.empty() && s2.empty();
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

### **Go**

```go
type MyQueue struct {
	s1, s2 []int
}

/** Initialize your data structure here. */
func Constructor() MyQueue {
	return MyQueue{
		s1: make([]int, 0),
		s2: make([]int, 0),
	}
}

/** Push element x to the back of queue. */
func (this *MyQueue) Push(x int) {
	this.s1 = append(this.s1, x)
}

/** Removes the element from in front of queue and returns that element. */
func (this *MyQueue) Pop() int {
	if len(this.s2) == 0 {
		this.transfer()
	}
	v := this.s2[len(this.s2)-1]
	this.s2 = this.s2[:len(this.s2)-1]
	return v
}

/** Get the front element. */
func (this *MyQueue) Peek() int {
	if len(this.s2) == 0 {
		this.transfer()
	}
	return this.s2[len(this.s2)-1]
}

/** Returns whether the queue is empty. */
func (this *MyQueue) Empty() bool {
	return len(this.s1) == 0 && len(this.s2) == 0
}

func (this *MyQueue) transfer() {
	for len(this.s1) > 0 {
		this.s2 = append(this.s2, this.s1[len(this.s1)-1])
		this.s1 = this.s1[:len(this.s1)-1]
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

### **TypeScript**

```ts
class MyQueue {
    private inStack: number[];
    private outStack: number[];

    constructor() {
        this.inStack = [];
        this.outStack = [];
    }

    push(x: number): void {
        this.inStack.push(x);
    }

    pop(): number {
        if (this.outStack.length === 0) {
            this.inToOut();
        }
        return this.outStack.pop() ?? -1;
    }

    peek(): number {
        if (this.outStack.length === 0) {
            this.inToOut();
        }
        return this.outStack[this.outStack.length - 1] ?? -1;
    }

    empty(): boolean {
        return this.inStack.length === 0 && this.outStack.length === 0;
    }

    inToOut() {
        while (this.inStack.length !== 0) {
            this.outStack.push(this.inStack.pop());
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

### **...**

```

```

<!-- tabs:end -->
