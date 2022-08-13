# [1670. Design Front Middle Back Queue](https://leetcode.com/problems/design-front-middle-back-queue)

[中文文档](/solution/1600-1699/1670.Design%20Front%20Middle%20Back%20Queue/README.md)

## Description

<p>Design a queue that supports <code>push</code> and <code>pop</code> operations in the front, middle, and back.</p>

<p>Implement the <code>FrontMiddleBack</code> class:</p>

<ul>
	<li><code>FrontMiddleBack()</code> Initializes the queue.</li>
	<li><code>void pushFront(int val)</code> Adds <code>val</code> to the <strong>front</strong> of the queue.</li>
	<li><code>void pushMiddle(int val)</code> Adds <code>val</code> to the <strong>middle</strong> of the queue.</li>
	<li><code>void pushBack(int val)</code> Adds <code>val</code> to the <strong>back</strong> of the queue.</li>
	<li><code>int popFront()</code> Removes the <strong>front</strong> element of the queue and returns it. If the queue is empty, return <code>-1</code>.</li>
	<li><code>int popMiddle()</code> Removes the <strong>middle</strong> element of the queue and returns it. If the queue is empty, return <code>-1</code>.</li>
	<li><code>int popBack()</code> Removes the <strong>back</strong> element of the queue and returns it. If the queue is empty, return <code>-1</code>.</li>
</ul>

<p><strong>Notice</strong> that when there are <b>two</b> middle position choices, the operation is performed on the <strong>frontmost</strong> middle position choice. For example:</p>

<ul>
	<li>Pushing <code>6</code> into the middle of <code>[1, 2, 3, 4, 5]</code> results in <code>[1, 2, <u>6</u>, 3, 4, 5]</code>.</li>
	<li>Popping the middle from <code>[1, 2, <u>3</u>, 4, 5, 6]</code> returns <code>3</code> and results in <code>[1, 2, 4, 5, 6]</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong>
[&quot;FrontMiddleBackQueue&quot;, &quot;pushFront&quot;, &quot;pushBack&quot;, &quot;pushMiddle&quot;, &quot;pushMiddle&quot;, &quot;popFront&quot;, &quot;popMiddle&quot;, &quot;popMiddle&quot;, &quot;popBack&quot;, &quot;popFront&quot;]
[[], [1], [2], [3], [4], [], [], [], [], []]
<strong>Output:</strong>
[null, null, null, null, null, 1, 3, 4, 2, -1]

<strong>Explanation:</strong>
FrontMiddleBackQueue q = new FrontMiddleBackQueue();
q.pushFront(1);   // [<u>1</u>]
q.pushBack(2);    // [1, <u>2</u>]
q.pushMiddle(3);  // [1, <u>3</u>, 2]
q.pushMiddle(4);  // [1, <u>4</u>, 3, 2]
q.popFront();     // return 1 -&gt; [4, 3, 2]
q.popMiddle();    // return 3 -&gt; [4, 2]
q.popMiddle();    // return 4 -&gt; [2]
q.popBack();      // return 2 -&gt; []
q.popFront();     // return -1 -&gt; [] (The queue is empty)
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= val &lt;= 10<sup>9</sup></code></li>
	<li>At most&nbsp;<code>1000</code>&nbsp;calls will be made to&nbsp;<code>pushFront</code>,&nbsp;<code>pushMiddle</code>,&nbsp;<code>pushBack</code>, <code>popFront</code>, <code>popMiddle</code>, and <code>popBack</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
