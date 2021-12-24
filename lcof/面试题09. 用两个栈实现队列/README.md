# [面试题 09. 用两个栈实现队列](https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)

## 题目描述

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 `appendTail` 和 `deleteHead` ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，`deleteHead`  操作返回 -1 )

**示例 1：**

```
输入：
["CQueue","appendTail","deleteHead","deleteHead"]
[[],[3],[],[]]
输出：[null,null,3,-1]
```

**示例 2：**

```
输入：
["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
[[],[],[5],[2],[],[]]
输出：[null,-1,null,null,5,2]
```

**提示：**

- `1 <= values <= 10000`
- `最多会对 appendTail、deleteHead 进行 10000 次调用`

## 解法

<!-- tabs:start -->

### **Python3**

```python
class CQueue:

    def __init__(self):
        self.s1 = []
        self.s2 = []

    def appendTail(self, value: int) -> None:
        self.s1.append(value)
        if not self.s2:
            self._move()

    def deleteHead(self) -> int:
        if not self.s2:
            self._move()
        return -1 if not self.s2 else self.s2.pop()

    def _move(self):
        while self.s1:
            self.s2.append(self.s1.pop())



# Your CQueue object will be instantiated and called as such:
# obj = CQueue()
# obj.appendTail(value)
# param_2 = obj.deleteHead()
```

### **Java**

```java
class CQueue {

    private Deque<Integer> s1;
    private Deque<Integer> s2;
    public CQueue() {
        s1 = new ArrayDeque<>();
        s2 = new ArrayDeque<>();
    }

    public void appendTail(int value) {
        s1.push(value);
        if (s2.isEmpty()) {
            move();
        }
    }

    public int deleteHead() {
        if (s2.isEmpty()) {
            move();
        }
        return s2.isEmpty() ? -1 : s2.pop();
    }

    private void move() {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
```

### **JavaScript**

```js
var CQueue = function () {
    this.data = [];
    this.helper = [];
};
/**
 * @param {number} value
 * @return {void}
 */
CQueue.prototype.appendTail = function (value) {
    this.data.push(value);
};
/**
 * @return {number}
 */
CQueue.prototype.deleteHead = function () {
    if (this.data.length) {
        while (this.data.length > 1) {
            this.helper.push(this.data.pop());
        }
        let res = this.data.pop();
        while (this.helper.length) {
            this.data.push(this.helper.pop());
        }
        return res;
    } else {
        return -1;
    }
};
```

### **Go**

```go
type CQueue struct {
	Stack1 []int
	Stack2 []int
}

// 入队都往S1压入，弹出时判定S2是否为空，S2非空则弹出S2顶，否则，S1的元素从栈顶依次入S2
//再从S2弹出

func Constructor() CQueue {
	return CQueue{Stack1: []int{}, Stack2: []int{}}
}

func (this *CQueue) AppendTail(value int) {
	this.Stack1 = append(this.Stack1, value)
}

func (this *CQueue) DeleteHead() int {
	if len(this.Stack1) == 0 && len(this.Stack2) == 0 {
		return -1
	}
	if len(this.Stack2) > 0 {
		res := this.Stack2[len(this.Stack2)-1]
		this.Stack2 = this.Stack2[0 : len(this.Stack2)-1]
		return res
	}
	for len(this.Stack1) > 0 {
		this.Stack2 = append(this.Stack2, this.Stack1[len(this.Stack1)-1])
		this.Stack1 = this.Stack1[0 : len(this.Stack1)-1]
	}
	res := this.Stack2[len(this.Stack2)-1]
	this.Stack2 = this.Stack2[0 : len(this.Stack2)-1]
	return res
}
```

### **C++**

```cpp
class CQueue {
private:
    stack<int> s1, s2;

public:
    CQueue() {
    }

    void appendTail(int value) {
        s1.push(value);
    }

    int deleteHead() {
        if (s2.empty()) {
            while (!s1.empty()) {
                s2.push(s1.top());
                s1.pop();
            }
        }
        if (s2.empty()) {
            return -1;
        }
        int head = s2.top();
        s2.pop();
        return head;
    }
};
```

### **TypeScript**

```ts
class CQueue {
    private stack1: number[];
    private stack2: number[];
    constructor() {
        this.stack1 = [];
        this.stack2 = [];
    }

    appendTail(value: number): void {
        this.stack1.push(value);
        if (this.stack2.length == 0) {
            this.move();
        }
    }

    move(): void {
        while (this.stack1.length != 0) {
            this.stack2.push(this.stack1.pop());
        }
    }

    deleteHead(): number {
        if (this.stack2.length == 0) {
            this.move();
        }
        return this.stack2.length == 0 ? -1 : this.stack2.pop();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * var obj = new CQueue()
 * obj.appendTail(value)
 * var param_2 = obj.deleteHead()
 */
```

### **...**

```

```

<!-- tabs:end -->
