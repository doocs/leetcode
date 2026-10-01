---
comments: true
difficulty: Easy
tags:
    - Stack
    - Design
    - Queue
---

<!-- problem:start -->

# [232. Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks)

[中文文档](/solution/0200-0299/0232.Implement%20Queue%20using%20Stacks/README.md)

## Mô tả

<!-- description:start -->

<p>Triển khai một hàng đợi vào trước ra trước (FIFO) chỉ bằng hai ngăn xếp. Hàng đợi được triển khai phải hỗ trợ tất cả các hàm của một hàng đợi thông thường (<code>push</code>, <code>peek</code>, <code>pop</code> và <code>empty</code>).</p>

<p>Triển khai lớp <code>MyQueue</code>:</p>

<ul>
	<li><code>void push(int x)</code> Đưa phần tử x vào cuối hàng đợi.</li>
	<li><code>int pop()</code> Xóa phần tử ở đầu hàng đợi và trả về phần tử đó.</li>
	<li><code>int peek()</code> Trả về phần tử ở đầu hàng đợi.</li>
	<li><code>boolean empty()</code> Trả về <code>true</code> nếu hàng đợi rỗng, <code>false</code> nếu ngược lại.</li>
</ul>

<p><strong>Lưu ý:</strong></p>

<ul>
	<li>Bạn phải <strong>chỉ</strong> sử dụng các thao tác tiêu chuẩn của một ngăn xếp, nghĩa là chỉ các thao tác <code>push to top</code>, <code>peek/pop from top</code>, <code>size</code> và <code>is empty</code> là hợp lệ.</li>
	<li>Tùy thuộc vào ngôn ngữ, ngăn xếp có thể không được hỗ trợ nguyên bản. Bạn có thể mô phỏng một ngăn xếp bằng list hoặc deque (hàng đợi hai đầu), miễn là bạn chỉ sử dụng các thao tác tiêu chuẩn của ngăn xếp.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào</strong>
[&quot;MyQueue&quot;, &quot;push&quot;, &quot;push&quot;, &quot;peek&quot;, &quot;pop&quot;, &quot;empty&quot;]
[[], [1], [2], [], [], []]
<strong>Đầu ra</strong>
[null, null, null, 1, 1, false]

<strong>Giải thích</strong>
MyQueue myQueue = new MyQueue();
myQueue.push(1); // hàng đợi là: [1]
myQueue.push(2); // hàng đợi là: [1, 2] (phần tử ngoài cùng bên trái là đầu hàng đợi)
myQueue.peek(); // trả về 1
myQueue.pop(); // trả về 1, hàng đợi là [2]
myQueue.empty(); // trả về false
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= x &lt;= 9</code></li>
	<li>Sẽ có nhiều nhất <code>100</code>&nbsp;lời gọi đến <code>push</code>, <code>pop</code>, <code>peek</code> và <code>empty</code>.</li>
	<li>Tất cả các lời gọi đến <code>pop</code> và <code>peek</code> đều hợp lệ.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng:</strong> Bạn có thể triển khai hàng đợi sao cho mỗi thao tác có độ phức tạp thời gian <strong><a href="https://en.wikipedia.org/wiki/Amortized_analysis" target="_blank">khấu hao</a></strong> <code>O(1)</code> không? Nói cách khác, thực hiện <code>n</code> thao tác sẽ mất tổng cộng thời gian <code>O(n)</code>, ngay cả khi một trong các thao tác đó có thể mất nhiều thời gian hơn.</p>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Hai ngăn xếp

<!-- thinking:start -->

> **Tư duy**
>
> Ngăn xếp là LIFO còn hàng đợi là FIFO, vì vậy một ngăn xếp không thể phục vụ cả hai đầu. Đẩy phần tử vào $stk1$ và lấy phần tử ra từ $stk2$.
>
> Khi $stk2$ rỗng, chuyển toàn bộ $stk1$ vào đó để phần tử cũ nhất nằm trên cùng. Mỗi phần tử được di chuyển nhiều nhất một lần, và thao tác lấy phần tử có độ phức tạp khấu hao hằng số.

<!-- thinking:end -->

Chúng ta sử dụng hai ngăn xếp, trong đó `stk1` được dùng để thêm phần tử vào hàng đợi, còn ngăn xếp `stk2` được dùng để lấy phần tử ra khỏi hàng đợi.

Khi thêm phần tử, chúng ta đẩy trực tiếp phần tử đó vào `stk1`. Độ phức tạp thời gian là $O(1)$.

Khi lấy phần tử ra, trước tiên chúng ta kiểm tra xem `stk2` có rỗng hay không. Nếu rỗng, chúng ta lấy toàn bộ phần tử ra khỏi `stk1` rồi đẩy chúng vào `stk2`, sau đó lấy một phần tử ra khỏi `stk2`. Nếu `stk2` không rỗng, chúng ta lấy trực tiếp một phần tử ra khỏi `stk2`. Độ phức tạp thời gian khấu hao là $O(1)$.

Khi lấy phần tử ở đầu hàng đợi, trước tiên chúng ta kiểm tra xem `stk2` có rỗng hay không. Nếu rỗng, chúng ta lấy toàn bộ phần tử ra khỏi `stk1` rồi đẩy chúng vào `stk2`, sau đó lấy phần tử trên cùng của `stk2`. Nếu `stk2` không rỗng, chúng ta lấy trực tiếp phần tử trên cùng của `stk2`. Độ phức tạp thời gian khấu hao là $O(1)$.

Khi kiểm tra xem hàng đợi có rỗng hay không, chúng ta chỉ cần kiểm tra xem cả hai ngăn xếp có rỗng hay không. Độ phức tạp thời gian là $O(1)$.

<!-- tabs:start -->

#### Python3

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


# Đối tượng MyQueue của bạn sẽ được khởi tạo và gọi như sau:
# obj = MyQueue()
# obj.push(x)
# param_2 = obj.pop()
# param_3 = obj.peek()
# param_4 = obj.empty()
```

#### Java

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
 * Đối tượng MyQueue của bạn sẽ được khởi tạo và gọi như sau:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
```

#### C++

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
 * Đối tượng MyQueue của bạn sẽ được khởi tạo và gọi như sau:
 * MyQueue* obj = new MyQueue();
 * obj->push(x);
 * int param_2 = obj->pop();
 * int param_3 = obj->peek();
 * bool param_4 = obj->empty();
 */
```

#### Go

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
 * Đối tượng MyQueue của bạn sẽ được khởi tạo và gọi như sau:
 * obj := Constructor();
 * obj.Push(x);
 * param_2 := obj.Pop();
 * param_3 := obj.Peek();
 * param_4 := obj.Empty();
 */
```

#### TypeScript

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
 * Đối tượng MyQueue của bạn sẽ được khởi tạo và gọi như sau:
 * var obj = new MyQueue()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.peek()
 * var param_4 = obj.empty()
 */
```

#### Rust

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
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
