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

两个“双端队列”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class FrontMiddleBackQueue:
    def __init__(self):
        self.left = deque()
        self.right = deque()

    def pushFront(self, val: int) -> None:
        self.left.appendleft(val)
        self.rebalance()

    def pushMiddle(self, val: int) -> None:
        self.left.append(val)
        self.rebalance()

    def pushBack(self, val: int) -> None:
        self.right.append(val)
        self.rebalance()

    def popFront(self) -> int:
        if self.empty():
            return -1
        if self.left:
            val = self.left.popleft()
        else:
            val = self.right.popleft()
        self.rebalance()
        return val

    def popMiddle(self) -> int:
        if self.empty():
            return -1
        if len(self.left) >= len(self.right):
            val = self.left.pop()
        else:
            val = self.right.popleft()
        self.rebalance()
        return val

    def popBack(self) -> int:
        if self.empty():
            return -1
        val = self.right.pop()
        self.rebalance()
        return val

    def empty(self) -> bool:
        return not self.left and not self.right

    def rebalance(self) -> None:
        while len(self.left) > len(self.right):
            self.right.appendleft(self.left.pop())
        while len(self.right) - len(self.left) > 1:
            self.left.append(self.right.popleft())


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
    private Deque<Integer> left;
    private Deque<Integer> right;

    public FrontMiddleBackQueue() {
        left = new LinkedList<>();
        right = new LinkedList<>();
    }

    public void pushFront(int val) {
        left.offerFirst(val);
        rebalance();
    }

    public void pushMiddle(int val) {
        left.offerLast(val);
        rebalance();
    }

    public void pushBack(int val) {
        right.offerLast(val);
        rebalance();
    }

    public int popFront() {
        if (empty()) {
            return -1;
        }
        int val = left.isEmpty() ? right.pollFirst() : left.pollFirst();
        rebalance();
        return val;
    }

    public int popMiddle() {
        if (empty()) {
            return -1;
        }
        int val = left.size() >= right.size() ? left.pollLast() : right.pollFirst();
        rebalance();
        return val;
    }

    public int popBack() {
        if (empty()) {
            return -1;
        }
        int val = right.pollLast();
        rebalance();
        return val;
    }

    private boolean empty() {
        return left.isEmpty() && right.isEmpty();
    }

    private void rebalance() {
        while (left.size() > right.size()) {
            right.offerFirst(left.pollLast());
        }
        while (right.size() - left.size() > 1) {
            left.offerLast(right.pollFirst());
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
