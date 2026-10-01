---
comments: true
difficulty: Medium
tags:
    - Stack
    - Design
---

<!-- problem:start -->

# [155. Min Stack](https://leetcode.com/problems/min-stack)

[中文文档](/solution/0100-0199/0155.Min%20Stack/README.md)

## Mô tả

<!-- description:start -->

<p>Thiết kế một ngăn xếp hỗ trợ các thao tác push, pop, top và lấy phần tử nhỏ nhất trong thời gian hằng số.</p>

<p>Hãy triển khai lớp <code>MinStack</code>:</p>

<ul>
	<li><code>MinStack()</code> khởi tạo đối tượng ngăn xếp.</li>
	<li><code>void push(int value)</code> đưa phần tử <code>value</code> vào ngăn xếp.</li>
	<li><code>void pop()</code> xóa phần tử ở đỉnh ngăn xếp.</li>
	<li><code>int top()</code> lấy phần tử ở đỉnh ngăn xếp.</li>
	<li><code>int getMin()</code> lấy phần tử nhỏ nhất trong ngăn xếp.</li>
</ul>

<p>Bạn phải triển khai lời giải có độ phức tạp thời gian <code>O(1)</code> cho mỗi hàm.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào</strong>
[&quot;MinStack&quot;,&quot;push&quot;,&quot;push&quot;,&quot;push&quot;,&quot;getMin&quot;,&quot;pop&quot;,&quot;top&quot;,&quot;getMin&quot;]
[[],[-2],[0],[-3],[],[],[],[]]

<strong>Đầu ra</strong>
[null,null,null,null,-3,null,0,-2]

<strong>Giải thích</strong>
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // trả về -3
minStack.pop();
minStack.top();    // trả về 0
minStack.getMin(); // trả về -2
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;= val &lt;= 2<sup>31</sup> - 1</code></li>
	<li>Các thao tác <code>pop</code>, <code>top</code> và <code>getMin</code> sẽ luôn được gọi trên các ngăn xếp <strong>không rỗng</strong>.</li>
	<li>Có nhiều nhất <code>3 * 10<sup>4</sup></code> lần gọi đến <code>push</code>, <code>pop</code>, <code>top</code> và <code>getMin</code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> $\textit{getMin}$ phải có độ phức tạp $O(1)$ giống như $\textit{push}/\textit{pop}$. Việc duyệt qua ngăn xếp có độ phức tạp tuyến tính, trong khi có thể có tới $3\times 10^4$ lần gọi. Giá trị nhỏ nhất chỉ thay đổi khi một giá trị nhỏ hơn được push vào hoặc giá trị nhỏ nhất hiện tại bị pop. Một ngăn xếp min song song lưu $\min(x,$ giá trị nhỏ nhất hiện tại $)$ sau mỗi lần push và pop đồng thời.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class MinStack:
    def __init__(self):
        self.stk1 = []
        self.stk2 = [inf]

    def push(self, val: int) -> None:
        self.stk1.append(val)
        self.stk2.append(min(val, self.stk2[-1]))

    def pop(self) -> None:
        self.stk1.pop()
        self.stk2.pop()

    def top(self) -> int:
        return self.stk1[-1]

    def getMin(self) -> int:
        return self.stk2[-1]


# Đối tượng MinStack của bạn sẽ được khởi tạo và gọi như sau:
# obj = MinStack()
# obj.push(val)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.getMin()
```

#### Java

```java
class MinStack {
    private Deque<Integer> stk1 = new ArrayDeque<>();
    private Deque<Integer> stk2 = new ArrayDeque<>();

    public MinStack() {
        stk2.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stk1.push(val);
        stk2.push(Math.min(val, stk2.peek()));
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
 * Đối tượng MinStack của bạn sẽ được khởi tạo và gọi như sau:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```

#### C++

```cpp
class MinStack {
public:
    MinStack() {
        stk2.push(INT_MAX);
    }

    void push(int val) {
        stk1.push(val);
        stk2.push(min(val, stk2.top()));
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
 * Đối tượng MinStack của bạn sẽ được khởi tạo và gọi như sau:
 * MinStack* obj = new MinStack();
 * obj->push(val);
 * obj->pop();
 * int param_3 = obj->top();
 * int param_4 = obj->getMin();
 */
```

#### Go

```go
type MinStack struct {
	stk1 []int
	stk2 []int
}

func Constructor() MinStack {
	return MinStack{[]int{}, []int{math.MaxInt32}}
}

func (this *MinStack) Push(val int) {
	this.stk1 = append(this.stk1, val)
	this.stk2 = append(this.stk2, min(val, this.stk2[len(this.stk2)-1]))
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

/**
 * Đối tượng MinStack của bạn sẽ được khởi tạo và gọi như sau:
 * obj := Constructor();
 * obj.Push(val);
 * obj.Pop();
 * param_3 := obj.Top();
 * param_4 := obj.GetMin();
 */
```

#### TypeScript

```ts
class MinStack {
    stk1: number[];
    stk2: number[];

    constructor() {
        this.stk1 = [];
        this.stk2 = [Infinity];
    }

    push(val: number): void {
        this.stk1.push(val);
        this.stk2.push(Math.min(val, this.stk2[this.stk2.length - 1]));
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
 * Đối tượng MinStack của bạn sẽ được khởi tạo và gọi như sau:
 * var obj = new MinStack()
 * obj.push(x)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */
```

#### Rust

```rust
use std::collections::VecDeque;
struct MinStack {
    stk1: VecDeque<i32>,
    stk2: VecDeque<i32>,
}

/**
 * `&self` nghĩa là phương thức nhận một tham chiếu bất biến.
 * Nếu cần một tham chiếu khả biến, hãy đổi nó thành `&mut self`.
 */
impl MinStack {
    fn new() -> Self {
        Self {
            stk1: VecDeque::new(),
            stk2: VecDeque::new(),
        }
    }

    fn push(&mut self, x: i32) {
        self.stk1.push_back(x);
        if self.stk2.is_empty() || *self.stk2.back().unwrap() >= x {
            self.stk2.push_back(x);
        }
    }

    fn pop(&mut self) {
        let val = self.stk1.pop_back().unwrap();
        if *self.stk2.back().unwrap() == val {
            self.stk2.pop_back();
        }
    }

    fn top(&self) -> i32 {
        *self.stk1.back().unwrap()
    }

    fn get_min(&self) -> i32 {
        *self.stk2.back().unwrap()
    }
}
```

#### JavaScript

```js
var MinStack = function () {
    this.stk1 = [];
    this.stk2 = [Infinity];
};

/**
 * @param {number} val
 * @return {void}
 */
MinStack.prototype.push = function (val) {
    this.stk1.push(val);
    this.stk2.push(Math.min(this.stk2[this.stk2.length - 1], val));
};

/**
 * @return {void}
 */
MinStack.prototype.pop = function () {
    this.stk1.pop();
    this.stk2.pop();
};

/**
 * @return {number}
 */
MinStack.prototype.top = function () {
    return this.stk1[this.stk1.length - 1];
};

/**
 * @return {number}
 */
MinStack.prototype.getMin = function () {
    return this.stk2[this.stk2.length - 1];
};

/**
 * Đối tượng MinStack của bạn sẽ được khởi tạo và gọi như sau:
 * var obj = new MinStack()
 * obj.push(val)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */
```

#### C#

```cs
public class MinStack {
    private Stack<int> stk1 = new Stack<int>();
    private Stack<int> stk2 = new Stack<int>();

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
 * Đối tượng MinStack của bạn sẽ được khởi tạo và gọi như sau:
 * MinStack obj = new MinStack();
 * obj.Push(x);
 * obj.Pop();
 * int param_3 = obj.Top();
 * int param_4 = obj.GetMin();
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
