---
comments: true
difficulty: Easy
tags:
    - Stack
    - Design
    - Queue
---

<!-- problem:start -->

# [225. Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues)

[中文文档](/solution/0200-0299/0225.Implement%20Stack%20using%20Queues/README.md)

## Mô tả

<!-- description:start -->

<p>Hãy triển khai một ngăn xếp vào sau ra trước (LIFO) chỉ bằng hai hàng đợi. Ngăn xếp được triển khai phải hỗ trợ tất cả các hàm của một ngăn xếp thông thường (<code>push</code>, <code>top</code>, <code>pop</code> và <code>empty</code>).</p>

<p>Triển khai lớp <code>MyStack</code>:</p>

<ul>
	<li><code>void push(int x)</code> Đẩy phần tử x lên đỉnh ngăn xếp.</li>
	<li><code>int pop()</code> Xóa phần tử trên đỉnh ngăn xếp và trả về phần tử đó.</li>
	<li><code>int top()</code> Trả về phần tử trên đỉnh ngăn xếp.</li>
	<li><code>boolean empty()</code> Trả về <code>true</code> nếu ngăn xếp rỗng, <code>false</code> nếu ngược lại.</li>
</ul>

<p><b>Lưu ý:</b></p>

<ul>
	<li>Bạn được sử dụng <strong>chỉ</strong> các thao tác tiêu chuẩn của hàng đợi, nghĩa là chỉ các thao tác <code>push to back</code>, <code>peek/pop from front</code>, <code>size</code> và <code>is empty</code> là hợp lệ.</li>
	<li>Tùy thuộc vào ngôn ngữ của bạn, hàng đợi có thể không được hỗ trợ nguyên bản. Bạn có thể mô phỏng một hàng đợi bằng list hoặc deque (hàng đợi hai đầu), miễn là chỉ sử dụng các thao tác tiêu chuẩn của hàng đợi.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào</strong>
[&quot;MyStack&quot;, &quot;push&quot;, &quot;push&quot;, &quot;top&quot;, &quot;pop&quot;, &quot;empty&quot;]
[[], [1], [2], [], [], []]
<strong>Đầu ra</strong>
[null, null, null, 2, 2, false]

<strong>Giải thích</strong>
MyStack myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.top(); // trả về 2
myStack.pop(); // trả về 2
myStack.empty(); // trả về False
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= x &lt;= 9</code></li>
	<li>Sẽ có nhiều nhất <code>100</code> lần gọi đến <code>push</code>, <code>pop</code>, <code>top</code> và <code>empty</code>.</li>
	<li>Mọi lời gọi đến <code>pop</code> và <code>top</code> đều hợp lệ.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng:</strong> Bạn có thể triển khai ngăn xếp chỉ bằng một hàng đợi không?</p>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Hai hàng đợi

<!-- thinking:start -->

> **Tư duy**
>
> Một hàng đợi thêm phần tử vào cuối và xóa phần tử ở đầu, trong khi ngăn xếp hoạt động theo LIFO. Nếu mỗi lần push đều đưa giá trị mới lên đầu, pop và top trở thành thao tác lấy phần tử khỏi đầu hàng đợi.
>
> Chúng ta đưa $x$ vào $q_2$, chuyển toàn bộ phần tử từ $q_1$ ra phía sau nó rồi hoán đổi hai hàng đợi, để phần tử đầu của $q_1$ luôn là đỉnh ngăn xếp.

<!-- thinking:end -->

Chúng ta sử dụng hai hàng đợi $q_1$ và $q_2$, trong đó $q_1$ được dùng để lưu trữ các phần tử trong ngăn xếp, còn $q_2$ được dùng để hỗ trợ triển khai các thao tác của ngăn xếp.

- Thao tác `push`: Đẩy phần tử vào $q_2$, sau đó lần lượt lấy các phần tử trong $q_1$ ra và đẩy chúng vào $q_2$, cuối cùng hoán đổi các tham chiếu của $q_1$ và $q_2$. Độ phức tạp thời gian là $O(n)$.
- Thao tác `pop`: Trực tiếp lấy phần tử đầu của $q_1$ ra. Độ phức tạp thời gian là $O(1)$.
- Thao tác `top`: Trực tiếp trả về phần tử đầu của $q_1$. Độ phức tạp thời gian là $O(1)$.
- Thao tác `empty`: Kiểm tra xem $q_1$ có rỗng hay không. Độ phức tạp thời gian là $O(1)$.

Độ phức tạp không gian là $O(n)$, trong đó $n$ là số phần tử trong ngăn xếp.

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
    /// Chỉ có thể có hai trạng thái tại mọi thời điểm
    /// 1. Một hàng đợi chứa N phần tử, hàng đợi còn lại rỗng
    /// 2. Một hàng đợi chứa N - 1 phần tử, hàng đợi còn lại chứa đúng 1 phần tử
    q_1: VecDeque<i32>,
    q_2: VecDeque<i32>,
    // Hoặc 1 hoặc 2, ban đầu bắt đầu từ 1
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
        // Luôn chuyển từ q1 sang q2
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
