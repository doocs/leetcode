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

利用一个辅助队列按单调顺序存储当前队列的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class MaxQueue:
    def __init__(self):
        self.p = deque()
        self.q = deque()

    def max_value(self) -> int:
        return -1 if not self.q else self.q[0]

    def push_back(self, value: int) -> None:
        while self.q and self.q[-1] < value:
            self.q.pop()
        self.p.append(value)
        self.q.append(value)

    def pop_front(self) -> int:
        if not self.p:
            return -1
        res = self.p.popleft()
        if self.q[0] == res:
            self.q.popleft()
        return res


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
    private Deque<Integer> p;
    private Deque<Integer> q;

    public MaxQueue() {
        p = new ArrayDeque<>();
        q = new ArrayDeque<>();
    }

    public int max_value() {
        return q.isEmpty() ? -1 : q.peekFirst();
    }

    public void push_back(int value) {
        while (!q.isEmpty() && q.peekLast() < value) {
            q.pollLast();
        }
        p.offerLast(value);
        q.offerLast(value);
    }

    public int pop_front() {
        if (p.isEmpty()) return -1;
        int res = p.pollFirst();
        if (q.peek() == res) q.pollFirst();
        return res;
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

### **JavaScript**

```js
var MaxQueue = function () {
    this.queue = [];
    this.maxValue = -Infinity;
    this.maxIdx = -1;
};

/**
 * @return {number}
 */
MaxQueue.prototype.max_value = function () {
    if (!this.queue.length) return -1;
    return this.maxValue;
};

/**
 * @param {number} value
 * @return {void}
 */
MaxQueue.prototype.push_back = function (value) {
    this.queue.push(value);
    if (value >= this.maxValue) {
        this.maxIdx = this.queue.length - 1;
        this.maxValue = value;
    }
};

/**
 * @return {number}
 */
MaxQueue.prototype.pop_front = function () {
    if (!this.queue.length) return -1;
    let a = this.queue.shift();
    this.maxIdx--;
    if (this.maxIdx < 0) {
        let tmp = -Infinity;
        let id = -1;
        for (let i = 0; i < this.queue.length; i++) {
            if (this.queue[i] > tmp) {
                tmp = this.queue[i];
                id = i;
            }
        }
        this.maxIdx = id;
        this.maxValue = tmp;
    }
    return a;
};
```

### **C++**

```cpp
class MaxQueue {
private:
    queue<int> q;
    deque<int> d;

public:
    MaxQueue() { }

    int max_value() {
        if (d.empty()) return -1;
        return d.front();
    }

    void push_back(int value) {
        while (!d.empty() && d.back() < value) d.pop_back();
        d.push_back(value);
        q.push(value);
    }

    int pop_front() {
        if (d.empty()) return -1;
        int retVal = q.front();
        q.pop();
        if (d.front() == retVal) d.pop_front();
        return retVal;
    }
};
```

### **Go**

```go
type MaxQueue struct {
	queue []int
	deque []int
}

func Constructor() MaxQueue {
	return MaxQueue{
		queue: make([]int, 0),
		deque: make([]int, 0),
	}
}

func (this *MaxQueue) Max_value() int {
	if len(this.deque) == 0 {
		return -1
	}
	return this.deque[0]
}

func (this *MaxQueue) Push_back(value int) {
	for len(this.deque) != 0 && this.deque[len(this.deque)-1] < value {
		this.deque = this.deque[:len(this.deque)-1]
	}
	this.deque = append(this.deque, value)
	this.queue = append(this.queue, value)
}

func (this *MaxQueue) Pop_front() int {
	if len(this.deque) == 0 {
		return -1
	}
	retVal := this.queue[0]
	this.queue = this.queue[1:]
	if this.deque[0] == retVal {
		this.deque = this.deque[1:]
	}
	return retVal
}
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
