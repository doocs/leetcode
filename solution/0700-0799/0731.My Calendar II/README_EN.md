---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0731.My%20Calendar%20II/README_EN.md
tags:
    - Design
    - Segment Tree
    - Array
    - Binary Search
    - Ordered Set
    - Prefix Sum
---

<!-- problem:start -->

# [731. My Calendar II](https://leetcode.com/problems/my-calendar-ii)

[中文文档](/solution/0700-0799/0731.My%20Calendar%20II/README.md)

## Description

<!-- description:start -->

<p>You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a <strong>triple booking</strong>.</p>

<p>A <strong>triple booking</strong> happens when three events have some non-empty intersection (i.e., some moment is common to all the three events.).</p>

<p>The event can be represented as a pair of integers <code>startTime</code> and <code>endTime</code> that represents a booking on the half-open interval <code>[startTime, endTime)</code>, the range of real numbers <code>x</code> such that <code>startTime &lt;= x &lt; endTime</code>.</p>

<p>Implement the <code>MyCalendarTwo</code> class:</p>

<ul>
	<li><code>MyCalendarTwo()</code> Initializes the calendar object.</li>
	<li><code>boolean book(int startTime, int endTime)</code> Returns <code>true</code> if the event can be added to the calendar successfully without causing a <strong>triple booking</strong>. Otherwise, return <code>false</code> and do not add the event to the calendar.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;MyCalendarTwo&quot;, &quot;book&quot;, &quot;book&quot;, &quot;book&quot;, &quot;book&quot;, &quot;book&quot;, &quot;book&quot;]
[[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
<strong>Output</strong>
[null, true, true, true, false, true, true]

<strong>Explanation</strong>
MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
myCalendarTwo.book(10, 20); // return True, The event can be booked. 
myCalendarTwo.book(50, 60); // return True, The event can be booked. 
myCalendarTwo.book(10, 40); // return True, The event can be double booked. 
myCalendarTwo.book(5, 15);  // return False, The event cannot be booked, because it would result in a triple booking.
myCalendarTwo.book(5, 10); // return True, The event can be booked, as it does not use time 10 which is already double booked.
myCalendarTwo.book(25, 55); // return True, The event can be booked, as the time in [25, 40) will be double booked with the third event, the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= start &lt; end &lt;= 10<sup>9</sup></code></li>
	<li>At most <code>1000</code> calls will be made to <code>book</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Difference Array

We can use the concept of a difference array to record the booking status at each time point. Then, we traverse all the time points and count the booking status at the current time point. If the number of bookings exceeds $2$, we return $\textit{false}$. Otherwise, we return $\textit{true}$.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$, where $n$ is the number of bookings.

<!-- tabs:start -->

#### Python3

```python
from sortedcontainers import SortedDict


class MyCalendarTwo:

    def __init__(self):
        self.sd = SortedDict()

    def book(self, startTime: int, endTime: int) -> bool:
        self.sd[startTime] = self.sd.get(startTime, 0) + 1
        self.sd[endTime] = self.sd.get(endTime, 0) - 1
        s = 0
        for v in self.sd.values():
            s += v
            if s > 2:
                self.sd[startTime] -= 1
                self.sd[endTime] += 1
                return False
        return True


# Your MyCalendarTwo object will be instantiated and called as such:
# obj = MyCalendarTwo()
# param_1 = obj.book(startTime,endTime)
```

#### Java

```java
class MyCalendarTwo {
    private final Map<Integer, Integer> tm = new TreeMap<>();

    public MyCalendarTwo() {
    }

    public boolean book(int startTime, int endTime) {
        tm.merge(startTime, 1, Integer::sum);
        tm.merge(endTime, -1, Integer::sum);
        int s = 0;
        for (int v : tm.values()) {
            s += v;
            if (s > 2) {
                tm.merge(startTime, -1, Integer::sum);
                tm.merge(endTime, 1, Integer::sum);
                return false;
            }
        }
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(startTime,endTime);
 */
```

#### C++

```cpp
class MyCalendarTwo {
public:
    MyCalendarTwo() {
    }

    bool book(int startTime, int endTime) {
        ++m[startTime];
        --m[endTime];
        int s = 0;
        for (auto& [_, v] : m) {
            s += v;
            if (s > 2) {
                --m[startTime];
                ++m[endTime];
                return false;
            }
        }
        return true;
    }

private:
    map<int, int> m;
};

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo* obj = new MyCalendarTwo();
 * bool param_1 = obj->book(startTime,endTime);
 */
```

#### Go

```go
type MyCalendarTwo struct {
	rbt *redblacktree.Tree[int, int]
}

func Constructor() MyCalendarTwo {
	return MyCalendarTwo{rbt: redblacktree.New[int, int]()}
}

func (this *MyCalendarTwo) Book(startTime int, endTime int) bool {
	merge := func(x, v int) {
		c, _ := this.rbt.Get(x)
		if c+v == 0 {
			this.rbt.Remove(x)
		} else {
			this.rbt.Put(x, c+v)
		}
	}

	merge(startTime, 1)
	merge(endTime, -1)

	s := 0
	for _, v := range this.rbt.Values() {
		s += v
		if s > 2 {
			merge(startTime, -1)
			merge(endTime, 1)
			return false
		}
	}
	return true
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Book(startTime,endTime);
 */
```

#### TypeScript

```ts
class MyCalendarTwo {
    private tm: Record<number, number> = {};

    constructor() {}

    book(startTime: number, endTime: number): boolean {
        this.tm[startTime] = (this.tm[startTime] ?? 0) + 1;
        this.tm[endTime] = (this.tm[endTime] ?? 0) - 1;
        let s = 0;
        for (const v of Object.values(this.tm)) {
            s += v;
            if (s > 2) {
                if (--this.tm[startTime] === 0) {
                    delete this.tm[startTime];
                }
                if (++this.tm[endTime] === 0) {
                    delete this.tm[endTime];
                }
                return false;
            }
        }
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * var obj = new MyCalendarTwo()
 * var param_1 = obj.book(startTime,endTime)
 */
```

#### JavaScript

```js
var MyCalendarTwo = function () {
    this.tm = {};
};

/**
 * @param {number} startTime
 * @param {number} endTime
 * @return {boolean}
 */
MyCalendarTwo.prototype.book = function (startTime, endTime) {
    this.tm[startTime] = (this.tm[startTime] || 0) + 1;
    this.tm[endTime] = (this.tm[endTime] || 0) - 1;
    let s = 0;

    for (const v of Object.values(this.tm)) {
        s += v;
        if (s > 2) {
            if (--this.tm[startTime] === 0) {
                delete this.tm[startTime];
            }
            if (++this.tm[endTime] === 0) {
                delete this.tm[endTime];
            }
            return false;
        }
    }
    return true;
};

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * var obj = new MyCalendarTwo()
 * var param_1 = obj.book(startTime,endTime)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Segment Tree

A segment tree divides the entire interval into multiple non-contiguous subintervals, with the number of subintervals not exceeding $\log(\textit{width})$. To update the value of an element, only $\log(\textit{width})$ intervals need to be updated, and these intervals are all contained within a larger interval that includes the element. When modifying intervals, a **lazy mark** is used to ensure efficiency.

-   Each node of the segment tree represents an interval;
-   The segment tree has a unique root node representing the entire statistical range, such as $[1, N]$;
-   Each leaf node of the segment tree represents a unit interval of length 1, $[x, x]$;
-   For each internal node $[l, r]$, its left child is $[l, \textit{mid}]$ and its right child is $[\textit{mid} + 1, r]$, where $\textit{mid} = \lfloor(l + r) / 2\rfloor$ (i.e., floor division).

For this problem, the segment tree nodes maintain the following information:

1. The maximum number of bookings within the interval $v$
2. Lazy mark $\textit{add}$

Since the time range is $10^9$, which is very large, we use dynamic node creation.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the number of bookings.

<!-- tabs:start -->

#### Python3

```python
class Node:
    def __init__(self, l: int, r: int):
        self.left = None
        self.right = None
        self.l = l
        self.r = r
        self.mid = (l + r) >> 1
        self.v = 0
        self.add = 0


class SegmentTree:
    def __init__(self):
        self.root = Node(1, 10**9 + 1)

    def modify(self, l: int, r: int, v: int, node: Node = None):
        if l > r:
            return
        if node is None:
            node = self.root
        if node.l >= l and node.r <= r:
            node.v += v
            node.add += v
            return
        self.pushdown(node)
        if l <= node.mid:
            self.modify(l, r, v, node.left)
        if r > node.mid:
            self.modify(l, r, v, node.right)
        self.pushup(node)

    def query(self, l: int, r: int, node: Node = None) -> int:
        if l > r:
            return 0
        if node is None:
            node = self.root
        if node.l >= l and node.r <= r:
            return node.v
        self.pushdown(node)
        v = 0
        if l <= node.mid:
            v = max(v, self.query(l, r, node.left))
        if r > node.mid:
            v = max(v, self.query(l, r, node.right))
        return v

    def pushup(self, node: Node):
        node.v = max(node.left.v, node.right.v)

    def pushdown(self, node: Node):
        if node.left is None:
            node.left = Node(node.l, node.mid)
        if node.right is None:
            node.right = Node(node.mid + 1, node.r)
        if node.add:
            node.left.v += node.add
            node.right.v += node.add
            node.left.add += node.add
            node.right.add += node.add
            node.add = 0


class MyCalendarTwo:
    def __init__(self):
        self.tree = SegmentTree()

    def book(self, startTime: int, endTime: int) -> bool:
        if self.tree.query(startTime + 1, endTime) >= 2:
            return False
        self.tree.modify(startTime + 1, endTime, 1)
        return True


# Your MyCalendarTwo object will be instantiated and called as such:
# obj = MyCalendarTwo()
# param_1 = obj.book(startTime,endTime)
```

#### Java

```java
class Node {
    Node left;
    Node right;
    int l;
    int r;
    int mid;
    int v;
    int add;
    public Node(int l, int r) {
        this.l = l;
        this.r = r;
        this.mid = (l + r) >> 1;
    }
}

class SegmentTree {
    private Node root = new Node(1, (int) 1e9 + 1);

    public SegmentTree() {
    }

    public void modify(int l, int r, int v) {
        modify(l, r, v, root);
    }

    public void modify(int l, int r, int v, Node node) {
        if (l > r) {
            return;
        }
        if (node.l >= l && node.r <= r) {
            node.v += v;
            node.add += v;
            return;
        }
        pushdown(node);
        if (l <= node.mid) {
            modify(l, r, v, node.left);
        }
        if (r > node.mid) {
            modify(l, r, v, node.right);
        }
        pushup(node);
    }

    public int query(int l, int r) {
        return query(l, r, root);
    }

    public int query(int l, int r, Node node) {
        if (l > r) {
            return 0;
        }
        if (node.l >= l && node.r <= r) {
            return node.v;
        }
        pushdown(node);
        int v = 0;
        if (l <= node.mid) {
            v = Math.max(v, query(l, r, node.left));
        }
        if (r > node.mid) {
            v = Math.max(v, query(l, r, node.right));
        }
        return v;
    }

    public void pushup(Node node) {
        node.v = Math.max(node.left.v, node.right.v);
    }

    public void pushdown(Node node) {
        if (node.left == null) {
            node.left = new Node(node.l, node.mid);
        }
        if (node.right == null) {
            node.right = new Node(node.mid + 1, node.r);
        }
        if (node.add != 0) {
            Node left = node.left, right = node.right;
            left.add += node.add;
            right.add += node.add;
            left.v += node.add;
            right.v += node.add;
            node.add = 0;
        }
    }
}

class MyCalendarTwo {
    private SegmentTree tree = new SegmentTree();

    public MyCalendarTwo() {
    }

    public boolean book(int startTime, int endTime) {
        if (tree.query(startTime + 1, endTime) >= 2) {
            return false;
        }
        tree.modify(startTime + 1, endTime, 1);
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(startTime,endTime);
 */
```

#### C++

```cpp
class Node {
public:
    int l, r, mid, v, add;
    Node* left;
    Node* right;

    Node(int l, int r)
        : l(l)
        , r(r)
        , mid((l + r) >> 1)
        , v(0)
        , add(0)
        , left(nullptr)
        , right(nullptr) {}
};

class SegmentTree {
public:
    Node* root;

    SegmentTree() {
        root = new Node(1, 1e9 + 1);
    }

    void modify(int l, int r, int v, Node* node = nullptr) {
        if (l > r) {
            return;
        }
        if (node == nullptr) {
            node = root;
        }

        if (node->l >= l && node->r <= r) {
            node->v += v;
            node->add += v;
            return;
        }
        pushdown(node);
        if (l <= node->mid) {
            modify(l, r, v, node->left);
        }
        if (r > node->mid) {
            modify(l, r, v, node->right);
        }
        pushup(node);
    }

    int query(int l, int r, Node* node = nullptr) {
        if (l > r) {
            return 0;
        }
        if (node == nullptr) {
            node = root;
        }

        if (node->l >= l && node->r <= r) {
            return node->v;
        }
        pushdown(node);
        int v = 0;
        if (l <= node->mid) {
            v = max(v, query(l, r, node->left));
        }
        if (r > node->mid) {
            v = max(v, query(l, r, node->right));
        }
        return v;
    }

private:
    void pushup(Node* node) {
        node->v = max(node->left->v, node->right->v);
    }

    void pushdown(Node* node) {
        if (node->left == nullptr) {
            node->left = new Node(node->l, node->mid);
        }
        if (node->right == nullptr) {
            node->right = new Node(node->mid + 1, node->r);
        }
        if (node->add) {
            node->left->v += node->add;
            node->right->v += node->add;
            node->left->add += node->add;
            node->right->add += node->add;
            node->add = 0;
        }
    }
};

class MyCalendarTwo {
public:
    SegmentTree tree;

    MyCalendarTwo() {}

    bool book(int startTime, int endTime) {
        if (tree.query(startTime + 1, endTime) >= 2) {
            return false;
        }
        tree.modify(startTime + 1, endTime, 1);
        return true;
    }
};

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo* obj = new MyCalendarTwo();
 * bool param_1 = obj->book(startTime,endTime);
 */
```

#### Go

```go
type node struct {
	left      *node
	right     *node
	l, mid, r int
	v, add    int
}

func newNode(l, r int) *node {
	return &node{
		l:   l,
		r:   r,
		mid: int(uint(l+r) >> 1),
	}
}

type segmentTree struct {
	root *node
}

func newSegmentTree() *segmentTree {
	return &segmentTree{
		root: newNode(1, 1e9+1),
	}
}

func (t *segmentTree) modify(l, r, v int, n *node) {
	if l > r {
		return
	}
	if n.l >= l && n.r <= r {
		n.v += v
		n.add += v
		return
	}
	t.pushdown(n)
	if l <= n.mid {
		t.modify(l, r, v, n.left)
	}
	if r > n.mid {
		t.modify(l, r, v, n.right)
	}
	t.pushup(n)
}

func (t *segmentTree) query(l, r int, n *node) int {
	if l > r {
		return 0
	}
	if n.l >= l && n.r <= r {
		return n.v
	}
	t.pushdown(n)
	v := 0
	if l <= n.mid {
		v = max(v, t.query(l, r, n.left))
	}
	if r > n.mid {
		v = max(v, t.query(l, r, n.right))
	}
	return v
}

func (t *segmentTree) pushup(n *node) {
	n.v = max(n.left.v, n.right.v)
}

func (t *segmentTree) pushdown(n *node) {
	if n.left == nil {
		n.left = newNode(n.l, n.mid)
	}
	if n.right == nil {
		n.right = newNode(n.mid+1, n.r)
	}
	if n.add != 0 {
		n.left.add += n.add
		n.right.add += n.add
		n.left.v += n.add
		n.right.v += n.add
		n.add = 0
	}
}

type MyCalendarTwo struct {
	tree *segmentTree
}

func Constructor() MyCalendarTwo {
	return MyCalendarTwo{newSegmentTree()}
}

func (this *MyCalendarTwo) Book(startTime int, endTime int) bool {
	if this.tree.query(startTime+1, endTime, this.tree.root) >= 2 {
		return false
	}
	this.tree.modify(startTime+1, endTime, 1, this.tree.root)
	return true
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Book(startTime,endTime);
 */
```

#### TypeScript

```ts
class Node {
    left: Node | null = null;
    right: Node | null = null;
    l: number;
    r: number;
    mid: number;
    v: number = 0;
    add: number = 0;

    constructor(l: number, r: number) {
        this.l = l;
        this.r = r;
        this.mid = (l + r) >> 1;
    }
}

class SegmentTree {
    private root: Node = new Node(1, 1e9 + 1);

    modify(l: number, r: number, v: number, node: Node | null = this.root): void {
        if (l > r || !node) {
            return;
        }
        if (node.l >= l && node.r <= r) {
            node.v += v;
            node.add += v;
            return;
        }
        this.pushdown(node);
        if (l <= node.mid) {
            this.modify(l, r, v, node.left);
        }
        if (r > node.mid) {
            this.modify(l, r, v, node.right);
        }
        this.pushup(node);
    }

    query(l: number, r: number, node: Node | null = this.root): number {
        if (l > r || !node) {
            return 0;
        }
        if (node.l >= l && node.r <= r) {
            return node.v;
        }
        this.pushdown(node);
        let v = 0;
        if (l <= node.mid) {
            v = Math.max(v, this.query(l, r, node.left));
        }
        if (r > node.mid) {
            v = Math.max(v, this.query(l, r, node.right));
        }
        return v;
    }

    private pushup(node: Node): void {
        if (node.left && node.right) {
            node.v = Math.max(node.left.v, node.right.v);
        }
    }

    private pushdown(node: Node): void {
        if (!node.left) {
            node.left = new Node(node.l, node.mid);
        }
        if (!node.right) {
            node.right = new Node(node.mid + 1, node.r);
        }
        if (node.add) {
            let left = node.left;
            let right = node.right;
            left.add += node.add;
            right.add += node.add;
            left.v += node.add;
            right.v += node.add;
            node.add = 0;
        }
    }
}

class MyCalendarTwo {
    private tree: SegmentTree = new SegmentTree();

    constructor() {}

    book(startTime: number, endTime: number): boolean {
        if (this.tree.query(startTime + 1, endTime) >= 2) {
            return false;
        }
        this.tree.modify(startTime + 1, endTime, 1);
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * var obj = new MyCalendarTwo()
 * var param_1 = obj.book(startTime,endTime)
 */
```

#### JavaScript

```js
class Node {
    constructor(l, r) {
        this.left = null;
        this.right = null;
        this.l = l;
        this.r = r;
        this.mid = (l + r) >> 1;
        this.v = 0;
        this.add = 0;
    }
}

class SegmentTree {
    constructor() {
        this.root = new Node(1, 1e9 + 1);
    }

    modify(l, r, v, node = this.root) {
        if (l > r || !node) {
            return;
        }
        if (node.l >= l && node.r <= r) {
            node.v += v;
            node.add += v;
            return;
        }
        this.pushdown(node);
        if (l <= node.mid) {
            this.modify(l, r, v, node.left);
        }
        if (r > node.mid) {
            this.modify(l, r, v, node.right);
        }
        this.pushup(node);
    }

    query(l, r, node = this.root) {
        if (l > r || !node) {
            return 0;
        }
        if (node.l >= l && node.r <= r) {
            return node.v;
        }
        this.pushdown(node);
        let v = 0;
        if (l <= node.mid) {
            v = Math.max(v, this.query(l, r, node.left));
        }
        if (r > node.mid) {
            v = Math.max(v, this.query(l, r, node.right));
        }
        return v;
    }

    pushup(node) {
        if (node.left && node.right) {
            node.v = Math.max(node.left.v, node.right.v);
        }
    }

    pushdown(node) {
        if (!node.left) {
            node.left = new Node(node.l, node.mid);
        }
        if (!node.right) {
            node.right = new Node(node.mid + 1, node.r);
        }
        if (node.add) {
            const left = node.left;
            const right = node.right;
            left.add += node.add;
            right.add += node.add;
            left.v += node.add;
            right.v += node.add;
            node.add = 0;
        }
    }
}

var MyCalendarTwo = function () {
    this.tree = new SegmentTree();
};

/**
 * @param {number} startTime
 * @param {number} endTime
 * @return {boolean}
 */
MyCalendarTwo.prototype.book = function (startTime, endTime) {
    if (this.tree.query(startTime + 1, endTime) >= 2) {
        return false;
    }
    this.tree.modify(startTime + 1, endTime, 1);
    return true;
};

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * var obj = new MyCalendarTwo()
 * var param_1 = obj.book(startTime,endTime)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
