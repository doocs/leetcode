# [面试题 09. 用两个栈实现队列](https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)

## 题目描述

<p>用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 <code>appendTail</code> 和 <code>deleteHead</code> ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，<code>deleteHead</code>&nbsp;操作返回 -1 )</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>
[&quot;CQueue&quot;,&quot;appendTail&quot;,&quot;deleteHead&quot;,&quot;deleteHead&quot;]
[[],[3],[],[]]
<strong>输出：</strong>[null,null,3,-1]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>
[&quot;CQueue&quot;,&quot;deleteHead&quot;,&quot;appendTail&quot;,&quot;appendTail&quot;,&quot;deleteHead&quot;,&quot;deleteHead&quot;]
[[],[],[5],[2],[],[]]
<strong>输出：</strong>[null,-1,null,null,5,2]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= values &lt;= 10000</code></li>
	<li><code>最多会对&nbsp;appendTail、deleteHead 进行&nbsp;10000&nbsp;次调用</code></li>
</ul>

## 解法

**方法一：双栈**

我们可以使用两个栈来实现队列，其中一个栈 `stk1` 用来存储输入的元素，另一个栈 `stk2` 用来输出元素。

当调用 `appendTail()` 方法时，我们将元素压入 `stk1` 中。

当调用 `deleteHead()` 方法时，如果此时栈 `stk2` 为空，我们将栈 `stk1` 中的元素逐个弹出并压入栈 `stk2` 中，然后弹出栈 `stk2` 的栈顶元素即可。如果此时栈 `stk2` 不为空，我们直接弹出栈 `stk2` 的栈顶元素即可。如果两个栈都为空，说明队列中没有元素，返回 `-1`。

时间复杂度上，对于 `appendTail()` 方法，时间复杂度为 $O(1)$；对于 `deleteHead()` 方法，时间复杂度为 $O(n)$；空间复杂度为 $O(n)$。其中 $n$ 为队列中的元素个数。

<!-- tabs:start -->

### **Python3**

```python
class CQueue:
    def __init__(self):
        self.stk1 = []
        self.stk2 = []

    def appendTail(self, value: int) -> None:
        self.stk1.append(value)

    def deleteHead(self) -> int:
        if not self.stk2:
            while self.stk1:
                self.stk2.append(self.stk1.pop())
        return -1 if not self.stk2 else self.stk2.pop()


# Your CQueue object will be instantiated and called as such:
# obj = CQueue()
# obj.appendTail(value)
# param_2 = obj.deleteHead()
```

### **Java**

```java
class CQueue {
    private Deque<Integer> stk1 = new ArrayDeque<>();
    private Deque<Integer> stk2 = new ArrayDeque<>();

    public CQueue() {
    }

    public void appendTail(int value) {
        stk1.push(value);
    }

    public int deleteHead() {
        if (stk2.isEmpty()) {
            while (!stk1.isEmpty()) {
                stk2.push(stk1.pop());
            }
        }
        return stk2.isEmpty() ? -1 : stk2.pop();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
```

### **C++**

```cpp
class CQueue {
public:
    CQueue() {

    }

    void appendTail(int value) {
        stk1.push(value);
    }

    int deleteHead() {
        if (stk2.empty()) {
            while (!stk1.empty()) {
                stk2.push(stk1.top());
                stk1.pop();
            }
        }
        if (stk2.empty()) {
            return -1;
        }
        int ans = stk2.top();
        stk2.pop();
        return ans;
    }

private:
    stack<int> stk1, stk2;
};

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue* obj = new CQueue();
 * obj->appendTail(value);
 * int param_2 = obj->deleteHead();
 */
```

### **Go**

```go
type CQueue struct {
	stk1, stk2 []int
}

func Constructor() CQueue {
	return CQueue{[]int{}, []int{}}
}

func (this *CQueue) AppendTail(value int) {
	this.stk1 = append(this.stk1, value)
}

func (this *CQueue) DeleteHead() int {
	if len(this.stk2) == 0 {
		for len(this.stk1) > 0 {
			this.stk2 = append(this.stk2, this.stk1[len(this.stk1)-1])
			this.stk1 = this.stk1[:len(this.stk1)-1]
		}
	}
	if len(this.stk2) == 0 {
		return -1
	}
	ans := this.stk2[len(this.stk2)-1]
	this.stk2 = this.stk2[:len(this.stk2)-1]
	return ans
}

/**
 * Your CQueue object will be instantiated and called as such:
 * obj := Constructor();
 * obj.AppendTail(value);
 * param_2 := obj.DeleteHead();
 */
```

### **JavaScript**

```js
var CQueue = function () {
    this.stk1 = [];
    this.stk2 = [];
};

/**
 * @param {number} value
 * @return {void}
 */
CQueue.prototype.appendTail = function (value) {
    this.stk1.push(value);
};

/**
 * @return {number}
 */
CQueue.prototype.deleteHead = function () {
    if (!this.stk2.length) {
        while (this.stk1.length) {
            this.stk2.push(this.stk1.pop());
        }
    }
    return this.stk2.length ? this.stk2.pop() : -1;
};

/**
 * Your CQueue object will be instantiated and called as such:
 * var obj = new CQueue()
 * obj.appendTail(value)
 * var param_2 = obj.deleteHead()
 */
```

### **TypeScript**

```ts
class CQueue {
    private stk1: number[];
    private stk2: number[];

    constructor() {
        this.stk1 = [];
        this.stk2 = [];
    }

    appendTail(value: number): void {
        this.stk1.push(value);
    }

    deleteHead(): number {
        if (this.stk2.length == 0) {
            while (this.stk1.length) {
                this.stk2.push(this.stk1.pop());
            }
        }
        return this.stk2.length == 0 ? -1 : this.stk2.pop();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * var obj = new CQueue()
 * obj.appendTail(value)
 * var param_2 = obj.deleteHead()
 */
```

### **Rust**

```rust
struct CQueue {
    s1: Vec<i32>,
    s2: Vec<i32>
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl CQueue {

    fn new() -> Self {
        CQueue {
            s1: Vec::new(),
            s2: Vec::new(),
        }
    }

    fn append_tail(&mut self, value: i32) {
        self.s1.push(value);
    }

    fn delete_head(&mut self) -> i32 {
        match self.s2.pop() {
            Some(value) => value,
            None => {
                while !self.s1.is_empty() {
                    self.s2.push(self.s1.pop().unwrap());
                }
                self.s2.pop().unwrap_or(-1)
            }
        }
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * let obj = CQueue::new();
 * obj.append_tail(value);
 * let ret_2: i32 = obj.delete_head();
 */
```

### **C#**

```cs
public class CQueue {
    private Stack<int> stk1 = new Stack<int>();
    private Stack<int> stk2 = new Stack<int>();

    public CQueue() {

    }

    public void AppendTail(int value) {
        stk1.Push(value);
    }

    public int DeleteHead() {
        if (stk2.Count == 0) {
            while (stk1.Count != 0) {
                stk2.Push(stk1.Pop());
            }
        }
        return stk2.Count == 0 ? -1 : stk2.Pop();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.AppendTail(value);
 * int param_2 = obj.DeleteHead();
 */
```

### **...**

```

```

<!-- tabs:end -->
