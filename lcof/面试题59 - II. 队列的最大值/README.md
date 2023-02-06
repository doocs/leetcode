# [面试题 59 - II. 队列的最大值](https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>请定义一个队列并实现函数 <code>max_value</code> 得到队列里的最大值，要求函数<code>max_value</code>、<code>push_back</code> 和 <code>pop_front</code> 的<strong>均摊</strong>时间复杂度都是O(1)。</p>

<p>若队列为空，<code>pop_front</code> 和 <code>max_value</code>&nbsp;需要返回 -1</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入:</strong> 
[&quot;MaxQueue&quot;,&quot;push_back&quot;,&quot;push_back&quot;,&quot;max_value&quot;,&quot;pop_front&quot;,&quot;max_value&quot;]
[[],[1],[2],[],[],[]]
<strong>输出:&nbsp;</strong>[null,null,null,2,1,2]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> 
[&quot;MaxQueue&quot;,&quot;pop_front&quot;,&quot;max_value&quot;]
[[],[],[]]
<strong>输出:&nbsp;</strong>[null,-1,-1]
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>1 &lt;= push_back,pop_front,max_value的总操作数&nbsp;&lt;= 10000</code></li>
	<li><code>1 &lt;= value &lt;= 10^5</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双队列**

我们维护两个队列 $q_1$ 和 $q_2$，其中 $q_1$ 用于存储所有元素，而 $q_2$ 用于存储当前队列中的最大值。

当获取队列中的最大值时，如果队列 $q_2$ 不为空，则队列中的最大值即为 $q_2$ 的队首元素；否则队列为空，返回 $-1$。

当向队列中添加元素时，我们需要将 $q_2$ 弹出所有队尾元素小于当前元素的元素，然后将当前元素添加到 $q_2$ 的队尾，最后将当前元素添加到 $q_1$ 的队尾。

当从队列中弹出元素时，如果 $q_1$ 为空，则返回 $-1$；否则，如果 $q_1$ 的队首元素等于 $q_2$ 的队首元素，则将 $q_2$ 的队首元素弹出，然后将 $q_1$ 的队首元素弹出；否则，只将 $q_1$ 的队首元素弹出。

以上操作的时间复杂度均为 $O(1)$，空间复杂度为 $O(n)$。其中 $n$ 为队列中的元素个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class MaxQueue:
    def __init__(self):
        self.q1 = deque()
        self.q2 = deque()

    def max_value(self) -> int:
        return -1 if not self.q2 else self.q2[0]

    def push_back(self, value: int) -> None:
        while self.q2 and self.q2[-1] < value:
            self.q2.pop()
        self.q1.append(value)
        self.q2.append(value)

    def pop_front(self) -> int:
        if not self.q1:
            return -1
        ans = self.q1.popleft()
        if self.q2[0] == ans:
            self.q2.popleft()
        return ans


# Your MaxQueue object will be instantiated and called as such:
# obj = MaxQueue()
# param_1 = obj.max_value()
# obj.push_back(value)
# param_3 = obj.pop_front()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class MaxQueue {
    private Deque<Integer> q1 = new ArrayDeque<>();
    private Deque<Integer> q2 = new ArrayDeque<>();

    public MaxQueue() {
    }

    public int max_value() {
        return q2.isEmpty() ? -1 : q2.peek();
    }

    public void push_back(int value) {
        while (!q2.isEmpty() && q2.peekLast() < value) {
            q2.pollLast();
        }
        q1.offer(value);
        q2.offer(value);
    }

    public int pop_front() {
        if (q1.isEmpty()) {
            return -1;
        }
        int ans = q1.poll();
        if (q2.peek() == ans) {
            q2.poll();
        }
        return ans;
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
```

### **C++**

```cpp
class MaxQueue {
public:
    MaxQueue() {

    }

    int max_value() {
        return q2.empty() ? -1 : q2.front();
    }

    void push_back(int value) {
        while (!q2.empty() && q2.back() < value) {
            q2.pop_back();
        }
        q1.push(value);
        q2.push_back(value);
    }

    int pop_front() {
        if (q1.empty()) {
            return -1;
        }
        int ans = q1.front();
        q1.pop();
        if (q2.front() == ans) {
            q2.pop_front();
        }
        return ans;
    }

private:
    queue<int> q1;
    deque<int> q2;
};

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue* obj = new MaxQueue();
 * int param_1 = obj->max_value();
 * obj->push_back(value);
 * int param_3 = obj->pop_front();
 */
```

### **Go**

```go
type MaxQueue struct {
	q1, q2 []int
}

func Constructor() MaxQueue {
	return MaxQueue{[]int{}, []int{}}
}

func (this *MaxQueue) Max_value() int {
	if len(this.q2) == 0 {
		return -1
	}
	return this.q2[0]
}

func (this *MaxQueue) Push_back(value int) {
	for len(this.q2) > 0 && this.q2[len(this.q2)-1] < value {
		this.q2 = this.q2[:len(this.q2)-1]
	}
	this.q1 = append(this.q1, value)
	this.q2 = append(this.q2, value)
}

func (this *MaxQueue) Pop_front() int {
	if len(this.q1) == 0 {
		return -1
	}
	ans := this.q1[0]
	this.q1 = this.q1[1:]
	if this.q2[0] == ans {
		this.q2 = this.q2[1:]
	}
	return ans
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Max_value();
 * obj.Push_back(value);
 * param_3 := obj.Pop_front();
 */
```

### **JavaScript**

```js
var MaxQueue = function () {
    this.q1 = [];
    this.q2 = [];
};

/**
 * @return {number}
 */
MaxQueue.prototype.max_value = function () {
    return this.q2.length ? this.q2[0] : -1;
};

/**
 * @param {number} value
 * @return {void}
 */
MaxQueue.prototype.push_back = function (value) {
    while (this.q2.length && this.q2[this.q2.length - 1] < value) {
        this.q2.pop();
    }
    this.q1.push(value);
    this.q2.push(value);
};

/**
 * @return {number}
 */
MaxQueue.prototype.pop_front = function () {
    if (!this.q1.length) {
        return -1;
    }
    const ans = this.q1.shift();
    if (this.q2[0] == ans) {
        this.q2.shift();
    }
    return ans;
};

/**
 * Your MaxQueue object will be instantiated and called as such:
 * var obj = new MaxQueue()
 * var param_1 = obj.max_value()
 * obj.push_back(value)
 * var param_3 = obj.pop_front()
 */
```

### **TypeScript**

```ts
class MaxQueue {
    private queue: number[];
    private deque: number[];

    constructor() {
        this.queue = [];
        this.deque = [];
    }

    max_value(): number {
        return this.deque[0] ?? -1;
    }

    push_back(value: number): void {
        this.queue.push(value);
        while (
            this.deque.length !== 0 &&
            this.deque[this.deque.length - 1] < value
        ) {
            this.deque.pop();
        }
        this.deque.push(value);
    }

    pop_front(): number {
        const res = this.queue.shift();
        if (res === this.deque[0]) {
            this.deque.shift();
        }
        return res ?? -1;
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * var obj = new MaxQueue()
 * var param_1 = obj.max_value()
 * obj.push_back(value)
 * var param_3 = obj.pop_front()
 */
```

### **Rust**

```rust
use std::collections::VecDeque;
struct MaxQueue {
    queue: VecDeque<i32>,
    deque: VecDeque<i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl MaxQueue {
    fn new() -> Self {
        Self {
            queue: VecDeque::new(),
            deque: VecDeque::new(),
        }
    }

    fn max_value(&self) -> i32 {
        *self.deque.front().unwrap_or(&-1)
    }

    fn push_back(&mut self, value: i32) {
        self.queue.push_back(value);
        while !self.deque.is_empty() && *self.deque.back().unwrap() < value {
            self.deque.pop_back();
        }
        self.deque.push_back(value);
    }

    fn pop_front(&mut self) -> i32 {
        if self.queue.is_empty() {
            return -1;
        }
        let res = self.queue.pop_front().unwrap();
        if res == self.deque[0] {
            self.deque.pop_front();
        }
        res
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * let obj = MaxQueue::new();
 * let ret_1: i32 = obj.max_value();
 * obj.push_back(value);
 * let ret_3: i32 = obj.pop_front();
 */
```

### **C#**

```cs
public class MaxQueue {
    LinkedList<int> mvq;
    Queue<int> q;

    public MaxQueue() {
        mvq = new LinkedList<int>();
        q = new Queue<int>();
    }

    public int Max_value() {
        if (mvq.Count == 0) {
            return -1;
        }
        return mvq.First.Value;
    }

    public void Push_back(int value) {
        q.Enqueue(value);
        while (mvq.Count > 0 && mvq.Last.Value < value) {
            mvq.RemoveLast();
        }
        mvq.AddLast(value);
    }

    public int Pop_front() {
        if (q.Count == 0) {
            return -1;
        }
        int v = q.Dequeue();
        if (mvq.First.Value == v) {
            mvq.RemoveFirst();
        }
        return v;
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.Max_value();
 * obj.Push_back(value);
 * int param_3 = obj.Pop_front();
 */
```

### **...**

```

```

<!-- tabs:end -->
