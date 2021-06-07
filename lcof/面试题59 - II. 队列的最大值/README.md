# [面试题 59 - II. 队列的最大值](https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

请定义一个队列并实现函数 `max_value` 得到队列里的最大值，要求函数`max_value`、`push_back` 和 `pop_front` 的**均摊**时间复杂度都是 O(1)。

若队列为空，`pop_front` 和 `max_value`  需要返回 -1

**示例 1:**

```
输入:
["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
[[],[1],[2],[],[],[]]
输出: [null,null,null,2,1,2]
```

**示例 2:**

```
输入:
["MaxQueue","pop_front","max_value"]
[[],[],[]]
输出: [null,-1,-1]
```

**限制：**

- `1 <= push_back,pop_front,max_value的总操作数 <= 10000`
- `1 <= value <= 10^5`

## 解法

<!-- 这里可写通用的实现逻辑 -->

利用一个辅助队列按单调顺序存储当前队列的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
from collections import deque


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
    MaxQueue() {}

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

### **...**

```

```

<!-- tabs:end -->
