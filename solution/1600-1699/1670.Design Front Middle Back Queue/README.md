# [1670. 设计前中后队列](https://leetcode.cn/problems/design-front-middle-back-queue)

[English Version](/solution/1600-1699/1670.Design%20Front%20Middle%20Back%20Queue/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你设计一个队列，支持在前，中，后三个位置的 <code>push</code> 和 <code>pop</code> 操作。</p>

<p>请你完成 <code>FrontMiddleBack</code> 类：</p>

<ul>
	<li><code>FrontMiddleBack()</code> 初始化队列。</li>
	<li><code>void pushFront(int val)</code> 将 <code>val</code> 添加到队列的 <strong>最前面</strong> 。</li>
	<li><code>void pushMiddle(int val)</code> 将 <code>val</code> 添加到队列的 <strong>正中间</strong> 。</li>
	<li><code>void pushBack(int val)</code> 将 <code>val</code> 添加到队里的 <strong>最后面</strong> 。</li>
	<li><code>int popFront()</code> 将 <strong>最前面</strong> 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 <code>-1</code> 。</li>
	<li><code>int popMiddle()</code> 将 <b>正中间</b> 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 <code>-1</code> 。</li>
	<li><code>int popBack()</code> 将 <strong>最后面</strong> 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 <code>-1</code> 。</li>
</ul>

<p>请注意当有 <strong>两个</strong> 中间位置的时候，选择靠前面的位置进行操作。比方说：</p>

<ul>
	<li>将 <code>6</code> 添加到 <code>[1, 2, 3, 4, 5]</code> 的中间位置，结果数组为 <code>[1, 2, <strong>6</strong>, 3, 4, 5]</code> 。</li>
	<li>从 <code>[1, 2, <strong>3</strong>, 4, 5, 6]</code> 的中间位置弹出元素，返回 <code>3</code> ，数组变为 <code>[1, 2, 4, 5, 6]</code> 。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
[[], [1], [2], [3], [4], [], [], [], [], []]
<strong>输出：</strong>
[null, null, null, null, null, 1, 3, 4, 2, -1]

<strong>解释：</strong>
FrontMiddleBackQueue q = new FrontMiddleBackQueue();
q.pushFront(1);   // [<strong>1</strong>]
q.pushBack(2);    // [1, <strong>2</strong>]
q.pushMiddle(3);  // [1, <strong>3</strong>, 2]
q.pushMiddle(4);  // [1, <strong>4</strong>, 3, 2]
q.popFront();     // 返回 1 -> [4, 3, 2]
q.popMiddle();    // 返回 3 -> [4, 2]
q.popMiddle();    // 返回 4 -> [2]
q.popBack();      // 返回 2 -> []
q.popFront();     // 返回 -1 -> [] （队列为空）
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= val <= 10<sup>9</sup></code></li>
	<li>最多调用 <code>1000</code> 次 <code>pushFront</code>， <code>pushMiddle</code>， <code>pushBack</code>， <code>popFront</code>， <code>popMiddle</code> 和 <code>popBack</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双“双端队列”**

我们用两个双端队列，其中 $q_1$ 存储前半部分，而 $q_2$ 存储后半部分。每次由 `rebalance` 函数来维护两个队列的平衡性，即保持 $q_1$ 和 $q_2$ 的长度差不超过 $1$ 且 $q_2$ 的长度不小于 $q_1$ 的长度。

时间复杂度方面，每次操作的时间复杂度为 $O(1)$，总的空间复杂度为 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class FrontMiddleBackQueue:
    def __init__(self):
        self.q1 = deque()
        self.q2 = deque()

    def pushFront(self, val: int) -> None:
        self.q1.appendleft(val)
        self.rebalance()

    def pushMiddle(self, val: int) -> None:
        self.q1.append(val)
        self.rebalance()

    def pushBack(self, val: int) -> None:
        self.q2.append(val)
        self.rebalance()

    def popFront(self) -> int:
        if not self.q1 and not self.q2:
            return -1
        if self.q1:
            val = self.q1.popleft()
        else:
            val = self.q2.popleft()
        self.rebalance()
        return val

    def popMiddle(self) -> int:
        if not self.q1 and not self.q2:
            return -1
        if len(self.q1) == len(self.q2):
            val = self.q1.pop()
        else:
            val = self.q2.popleft()
        self.rebalance()
        return val

    def popBack(self) -> int:
        if not self.q2:
            return -1
        val = self.q2.pop()
        self.rebalance()
        return val

    def rebalance(self):
        if len(self.q1) > len(self.q2):
            self.q2.appendleft(self.q1.pop())
        if len(self.q2) > len(self.q1) + 1:
            self.q1.append(self.q2.popleft())


# Your FrontMiddleBackQueue object will be instantiated and called as such:
# obj = FrontMiddleBackQueue()
# obj.pushFront(val)
# obj.pushMiddle(val)
# obj.pushBack(val)
# param_4 = obj.popFront()
# param_5 = obj.popMiddle()
# param_6 = obj.popBack()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class FrontMiddleBackQueue {
    private Deque<Integer> q1 = new ArrayDeque<>();
    private Deque<Integer> q2 = new ArrayDeque<>();

    public FrontMiddleBackQueue() {
    }

    public void pushFront(int val) {
        q1.offerFirst(val);
        rebalance();
    }

    public void pushMiddle(int val) {
        q1.offerLast(val);
        rebalance();
    }

    public void pushBack(int val) {
        q2.offerLast(val);
        rebalance();
    }

    public int popFront() {
        if (q1.isEmpty() && q2.isEmpty()) {
            return -1;
        }
        int val = q1.isEmpty() ? q2.pollFirst() : q1.pollFirst();
        rebalance();
        return val;
    }

    public int popMiddle() {
        if (q1.isEmpty() && q2.isEmpty()) {
            return -1;
        }
        int val = q1.size() == q2.size() ? q1.pollLast() : q2.pollFirst();
        rebalance();
        return val;
    }

    public int popBack() {
        if (q2.isEmpty()) {
            return -1;
        }
        int val = q2.pollLast();
        rebalance();
        return val;
    }

    private void rebalance() {
        if (q1.size() > q2.size()) {
            q2.offerFirst(q1.pollLast());
        }
        if (q2.size() > q1.size() + 1) {
            q1.offerLast(q2.pollFirst());
        }
    }
}

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
 * obj.pushFront(val);
 * obj.pushMiddle(val);
 * obj.pushBack(val);
 * int param_4 = obj.popFront();
 * int param_5 = obj.popMiddle();
 * int param_6 = obj.popBack();
 */
```

### **C++**

```cpp
class FrontMiddleBackQueue {
public:
    FrontMiddleBackQueue() {

    }

    void pushFront(int val) {
        q1.push_front(val);
        rebalance();
    }

    void pushMiddle(int val) {
        q1.push_back(val);
        rebalance();
    }

    void pushBack(int val) {
        q2.push_back(val);
        rebalance();
    }

    int popFront() {
        if (q1.empty() && q2.empty()) return -1;
        int val = 0;
        if (q1.size()) {
            val = q1.front();
            q1.pop_front();
        } else {
            val = q2.front();
            q2.pop_front();
        }
        rebalance();
        return val;
    }

    int popMiddle() {
        if (q1.empty() && q2.empty()) return -1;
        int val = 0;
        if (q1.size() == q2.size()) {
            val = q1.back();
            q1.pop_back();
        } else {
            val = q2.front();
            q2.pop_front();
        }
        rebalance();
        return val;
    }

    int popBack() {
        if (q2.empty()) return -1;
        int val = q2.back();
        q2.pop_back();
        rebalance();
        return val;
    }

private:
    deque<int> q1;
    deque<int> q2;

    void rebalance() {
        if (q1.size() > q2.size()) {
            q2.push_front(q1.back());
            q1.pop_back();
        }
        if (q2.size() > q1.size() + 1) {
            q1.push_back(q2.front());
            q2.pop_front();
        }
    }
};

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * FrontMiddleBackQueue* obj = new FrontMiddleBackQueue();
 * obj->pushFront(val);
 * obj->pushMiddle(val);
 * obj->pushBack(val);
 * int param_4 = obj->popFront();
 * int param_5 = obj->popMiddle();
 * int param_6 = obj->popBack();
 */
```

### **Go**

```go
type FrontMiddleBackQueue struct{}

var a []int

func Constructor() (_ FrontMiddleBackQueue) {
	a = nil
	return
}

func (FrontMiddleBackQueue) PushFront(v int) {
	a = append([]int{v}, a...)
}

func (FrontMiddleBackQueue) PushMiddle(v int) {
	p := len(a) / 2
	a = append(a[:p], append([]int{v}, a[p:]...)...)
}

func (FrontMiddleBackQueue) PushBack(v int) {
	a = append(a, v)
}

func (FrontMiddleBackQueue) PopFront() (ans int) {
	if len(a) == 0 {
		return -1
	}
	ans = a[0]
	a = a[1:]
	return
}

func (FrontMiddleBackQueue) PopMiddle() (ans int) {
	if len(a) == 0 {
		return -1
	}
	p := (len(a) - 1) / 2
	ans = a[p]
	a = append(a[:p], a[p+1:]...)
	return
}

func (FrontMiddleBackQueue) PopBack() (ans int) {
	if len(a) == 0 {
		return -1
	}
	ans = a[len(a)-1]
	a = a[:len(a)-1]
	return
}
```

### **JavaScript**

```js
var FrontMiddleBackQueue = function () {
    this.left = [];
    this.right = [];
};

/**
 * @param {number} val
 * @return {void}
 */
FrontMiddleBackQueue.prototype.pushFront = function (val) {
    this.left.unshift(val);
    this.rebalance();
};

/**
 * @param {number} val
 * @return {void}
 */
FrontMiddleBackQueue.prototype.pushMiddle = function (val) {
    this.left.push(val);
    this.rebalance();
};

/**
 * @param {number} val
 * @return {void}
 */
FrontMiddleBackQueue.prototype.pushBack = function (val) {
    this.right.push(val);
    this.rebalance();
};

/**
 * @return {number}
 */
FrontMiddleBackQueue.prototype.popFront = function () {
    if (this.isEmpty()) return -1;
    let num = this.left.length == 0 ? this.right.shift() : this.left.shift();
    this.rebalance();
    return num;
};

/**
 * @return {number}
 */
FrontMiddleBackQueue.prototype.popMiddle = function () {
    if (this.isEmpty()) return -1;
    let num =
        this.left.length == this.right.length
            ? this.left.pop()
            : this.right.shift();
    this.rebalance();
    return num;
};

/**
 * @return {number}
 */
FrontMiddleBackQueue.prototype.popBack = function () {
    if (this.isEmpty()) return -1;
    let num = this.right.pop();
    this.rebalance();
    return num;
};

FrontMiddleBackQueue.prototype.rebalance = function () {
    while (this.left.length > this.right.length) {
        this.right.unshift(this.left.pop());
    }
    while (this.right.length > this.left.length + 1) {
        this.left.push(this.right.shift());
    }
};

FrontMiddleBackQueue.prototype.isEmpty = function () {
    return this.left.length == 0 && this.right.length == 0;
};

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * var obj = new FrontMiddleBackQueue()
 * obj.pushFront(val)
 * obj.pushMiddle(val)
 * obj.pushBack(val)
 * var param_4 = obj.popFront()
 * var param_5 = obj.popMiddle()
 * var param_6 = obj.popBack()
 */
```

### **...**

```

```

<!-- tabs:end -->
