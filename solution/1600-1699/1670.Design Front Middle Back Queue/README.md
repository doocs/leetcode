# [1670. 设计前中后队列](https://leetcode.cn/problems/design-front-middle-back-queue)

[English Version](/solution/1600-1699/1670.Design%20Front%20Middle%20Back%20Queue/README_EN.md)

<!-- tags:设计,队列,数组,链表,数据流 -->

<!-- difficulty:中等 -->

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

### 方法一：两个双端队列

我们用两个双端队列，其中 $q_1$ 存储前半部分，而 $q_2$ 存储后半部分。每次由 `rebalance` 函数来维护两个队列的平衡性，即保持 $q_2$ 的长度大于等于 $q_1$ 的长度，且长度之差不超过 $1$。

在 `pushFront`、`pushMiddle` 和 `pushBack` 函数中，我们只需要将元素添加到 $q_1$ 或 $q_2$ 中，并调用 `rebalance` 函数即可。

对于 `popFront` 函数，我们需要判断 $q_1$ 和 $q_2$ 是否为空，如果都为空，则返回 $-1$，否则我们需要判断 $q_1$ 是否为空，如果不为空，则弹出 $q_1$ 的队首元素，否则弹出 $q_2$ 的队首元素，并调用 `rebalance` 函数。

对于 `popMiddle` 函数，我们需要判断 $q_1$ 和 $q_2$ 是否为空，如果都为空，则返回 $-1$，否则我们需要判断 $q_1$ 和 $q_2$ 的长度是否相等，如果相等，则弹出 $q_1$ 的队尾元素，否则弹出 $q_2$ 的队首元素，并调用 `rebalance` 函数。

对于 `popBack` 函数，我们只需要判断 $q_2$ 是否为空，如果为空，则返回 $-1$，否则弹出 $q_2$ 的队尾元素，并调用 `rebalance` 函数。

以上操作的时间复杂度均为 $O(1)$，空间复杂度为 $O(n)$，其中 $n$ 是队列中的元素数量。

<!-- tabs:start -->

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

```go
type FrontMiddleBackQueue struct {
	q1, q2 Deque
}

func Constructor() FrontMiddleBackQueue {
	return FrontMiddleBackQueue{}
}

func (this *FrontMiddleBackQueue) PushFront(val int) {
	this.q1.PushFront(val)
	this.rebalance()
}

func (this *FrontMiddleBackQueue) PushMiddle(val int) {
	this.q1.PushBack(val)
	this.rebalance()
}

func (this *FrontMiddleBackQueue) PushBack(val int) {
	this.q2.PushBack(val)
	this.rebalance()
}

func (this *FrontMiddleBackQueue) PopFront() int {
	if this.q1.Empty() && this.q2.Empty() {
		return -1
	}
	var val int
	if !this.q1.Empty() {
		val = this.q1.PopFront()
	} else {
		val = this.q2.PopFront()
	}
	this.rebalance()
	return val
}

func (this *FrontMiddleBackQueue) PopMiddle() int {
	if this.q1.Empty() && this.q2.Empty() {
		return -1
	}
	var val int
	if this.q1.Size() == this.q2.Size() {
		val = this.q1.PopBack()
	} else {
		val = this.q2.PopFront()
	}
	this.rebalance()
	return val
}

func (this *FrontMiddleBackQueue) PopBack() int {
	if this.q2.Empty() {
		return -1
	}
	val := this.q2.PopBack()
	this.rebalance()
	return val
}

func (this *FrontMiddleBackQueue) rebalance() {
	if this.q1.Size() > this.q2.Size() {
		this.q2.PushFront(this.q1.PopBack())
	}
	if this.q2.Size() > this.q1.Size()+1 {
		this.q1.PushBack(this.q2.PopFront())
	}
}

// template
type Deque struct{ l, r []int }

func (q Deque) Empty() bool {
	return len(q.l) == 0 && len(q.r) == 0
}

func (q Deque) Size() int {
	return len(q.l) + len(q.r)
}

func (q *Deque) PushFront(v int) {
	q.l = append(q.l, v)
}

func (q *Deque) PushBack(v int) {
	q.r = append(q.r, v)
}

func (q *Deque) PopFront() (v int) {
	if len(q.l) > 0 {
		q.l, v = q.l[:len(q.l)-1], q.l[len(q.l)-1]
	} else {
		v, q.r = q.r[0], q.r[1:]
	}
	return
}

func (q *Deque) PopBack() (v int) {
	if len(q.r) > 0 {
		q.r, v = q.r[:len(q.r)-1], q.r[len(q.r)-1]
	} else {
		v, q.l = q.l[0], q.l[1:]
	}
	return
}

func (q Deque) Front() int {
	if len(q.l) > 0 {
		return q.l[len(q.l)-1]
	}
	return q.r[0]
}

func (q Deque) Back() int {
	if len(q.r) > 0 {
		return q.r[len(q.r)-1]
	}
	return q.l[0]
}

func (q Deque) Get(i int) int {
	if i < len(q.l) {
		return q.l[len(q.l)-1-i]
	}
	return q.r[i-len(q.l)]
}

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * obj := Constructor();
 * obj.PushFront(val);
 * obj.PushMiddle(val);
 * obj.PushBack(val);
 * param_4 := obj.PopFront();
 * param_5 := obj.PopMiddle();
 * param_6 := obj.PopBack();
 */
```

```ts
class FrontMiddleBackQueue {
    private q1: Deque<number>;
    private q2: Deque<number>;

    constructor() {
        this.q1 = new Deque<number>();
        this.q2 = new Deque<number>();
    }

    pushFront(val: number): void {
        this.q1.pushFront(val);
        this.rebalance();
    }

    pushMiddle(val: number): void {
        this.q1.pushBack(val);
        this.rebalance();
    }

    pushBack(val: number): void {
        this.q2.pushBack(val);
        this.rebalance();
    }

    popFront(): number {
        if (this.q1.isEmpty() && this.q2.isEmpty()) {
            return -1;
        }
        const val = this.q1.isEmpty() ? this.q2.popFront() : this.q1.popFront();
        this.rebalance();
        return val!;
    }

    popMiddle(): number {
        if (this.q1.isEmpty() && this.q2.isEmpty()) {
            return -1;
        }
        const val =
            this.q1.getSize() === this.q2.getSize() ? this.q1.popBack() : this.q2.popFront();
        this.rebalance();
        return val!;
    }

    popBack(): number {
        if (this.q2.isEmpty()) {
            return -1;
        }
        const val = this.q2.popBack();
        this.rebalance();
        return val!;
    }

    private rebalance(): void {
        if (this.q1.getSize() > this.q2.getSize()) {
            this.q2.pushFront(this.q1.popBack()!);
        }
        if (this.q2.getSize() > this.q1.getSize() + 1) {
            this.q1.pushBack(this.q2.popFront()!);
        }
    }
}

class Node<T> {
    value: T;
    next: Node<T> | null;
    prev: Node<T> | null;

    constructor(value: T) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}

class Deque<T> {
    private front: Node<T> | null;
    private back: Node<T> | null;
    private size: number;

    constructor() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }

    pushFront(val: T): void {
        const newNode = new Node(val);
        if (this.isEmpty()) {
            this.front = newNode;
            this.back = newNode;
        } else {
            newNode.next = this.front;
            this.front!.prev = newNode;
            this.front = newNode;
        }
        this.size++;
    }

    pushBack(val: T): void {
        const newNode = new Node(val);
        if (this.isEmpty()) {
            this.front = newNode;
            this.back = newNode;
        } else {
            newNode.prev = this.back;
            this.back!.next = newNode;
            this.back = newNode;
        }
        this.size++;
    }

    popFront(): T | undefined {
        if (this.isEmpty()) {
            return undefined;
        }
        const value = this.front!.value;
        this.front = this.front!.next;
        if (this.front !== null) {
            this.front.prev = null;
        } else {
            this.back = null;
        }
        this.size--;
        return value;
    }

    popBack(): T | undefined {
        if (this.isEmpty()) {
            return undefined;
        }
        const value = this.back!.value;
        this.back = this.back!.prev;
        if (this.back !== null) {
            this.back.next = null;
        } else {
            this.front = null;
        }
        this.size--;
        return value;
    }

    frontValue(): T | undefined {
        return this.front?.value;
    }

    backValue(): T | undefined {
        return this.back?.value;
    }

    getSize(): number {
        return this.size;
    }

    isEmpty(): boolean {
        return this.size === 0;
    }
}

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

```js
class FrontMiddleBackQueue {
    constructor() {
        this.q1 = new Deque();
        this.q2 = new Deque();
    }

    pushFront(val) {
        this.q1.pushFront(val);
        this.rebalance();
    }

    pushMiddle(val) {
        this.q1.pushBack(val);
        this.rebalance();
    }

    pushBack(val) {
        this.q2.pushBack(val);
        this.rebalance();
    }

    popFront() {
        if (this.q1.isEmpty() && this.q2.isEmpty()) {
            return -1;
        }
        const val = this.q1.isEmpty() ? this.q2.popFront() : this.q1.popFront();
        this.rebalance();
        return val !== undefined ? val : -1;
    }

    popMiddle() {
        if (this.q1.isEmpty() && this.q2.isEmpty()) {
            return -1;
        }
        const val =
            this.q1.getSize() === this.q2.getSize() ? this.q1.popBack() : this.q2.popFront();
        this.rebalance();
        return val !== undefined ? val : -1;
    }

    popBack() {
        if (this.q2.isEmpty()) {
            return -1;
        }
        const val = this.q2.popBack();
        this.rebalance();
        return val !== undefined ? val : -1;
    }

    rebalance() {
        if (this.q1.getSize() > this.q2.getSize()) {
            this.q2.pushFront(this.q1.popBack());
        }
        if (this.q2.getSize() > this.q1.getSize() + 1) {
            this.q1.pushBack(this.q2.popFront());
        }
    }
}

class Node {
    constructor(value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}

class Deque {
    constructor() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }

    pushFront(val) {
        const newNode = new Node(val);
        if (this.isEmpty()) {
            this.front = newNode;
            this.back = newNode;
        } else {
            newNode.next = this.front;
            this.front.prev = newNode;
            this.front = newNode;
        }
        this.size++;
    }

    pushBack(val) {
        const newNode = new Node(val);
        if (this.isEmpty()) {
            this.front = newNode;
            this.back = newNode;
        } else {
            newNode.prev = this.back;
            this.back.next = newNode;
            this.back = newNode;
        }
        this.size++;
    }

    popFront() {
        if (this.isEmpty()) {
            return undefined;
        }
        const value = this.front.value;
        this.front = this.front.next;
        if (this.front !== null) {
            this.front.prev = null;
        } else {
            this.back = null;
        }
        this.size--;
        return value;
    }

    popBack() {
        if (this.isEmpty()) {
            return undefined;
        }
        const value = this.back.value;
        this.back = this.back.prev;
        if (this.back !== null) {
            this.back.next = null;
        } else {
            this.front = null;
        }
        this.size--;
        return value;
    }

    frontValue() {
        return this.front?.value;
    }

    backValue() {
        return this.back?.value;
    }

    getSize() {
        return this.size;
    }

    isEmpty() {
        return this.size === 0;
    }
}

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

<!-- tabs:end -->

<!-- end -->
