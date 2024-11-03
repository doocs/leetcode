---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0731.My%20Calendar%20II/README.md
tags:
    - 设计
    - 线段树
    - 数组
    - 二分查找
    - 有序集合
    - 前缀和
---

<!-- problem:start -->

# [731. 我的日程安排表 II](https://leetcode.cn/problems/my-calendar-ii)

[English Version](/solution/0700-0799/0731.My%20Calendar%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>实现一个程序来存放你的日程安排。如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。</p>

<p>当三个日程安排有一些时间上的交叉时（例如三个日程安排都在同一时间内），就会产生 <strong>三重预订</strong>。</p>

<p>事件能够用一对整数&nbsp;<code>startTime</code>&nbsp;和&nbsp;<code>endTime</code>&nbsp;表示，在一个半开区间的时间&nbsp;<code>[startTime, endTime)</code>&nbsp;上预定。实数&nbsp;<code>x</code> 的范围为&nbsp;&nbsp;<code>startTime &lt;= x &lt; endTime</code>。</p>

<p>实现&nbsp;<code>MyCalendarTwo</code> 类：</p>

<ul>
	<li><code>MyCalendarTwo()</code>&nbsp;初始化日历对象。</li>
	<li><code>boolean book(int startTime, int endTime)</code>&nbsp;如果可以将日程安排成功添加到日历中而不会导致三重预订，返回 <code>true</code>。否则，返回 <code>false</code> 并且不要将该日程安排添加到日历中。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
[[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
<strong>输出：</strong>
[null, true, true, true, false, true, true]

<strong>解释：</strong>
MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
myCalendarTwo.book(10, 20); // 返回 True，能够预定该日程。
myCalendarTwo.book(50, 60); // 返回 True，能够预定该日程。
myCalendarTwo.book(10, 40); // 返回 True，该日程能够被重复预定。
myCalendarTwo.book(5, 15);  // 返回 False，该日程导致了三重预定，所以不能预定。
myCalendarTwo.book(5, 10); // 返回 True，能够预定该日程，因为它不使用已经双重预订的时间 10。
myCalendarTwo.book(25, 55); // 返回 True，能够预定该日程，因为时间段 [25, 40) 将被第三个日程重复预定，时间段 [40, 50) 将被单独预定，而时间段 [50, 55) 将被第二个日程重复预定。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= start &lt; end &lt;= 10<sup>9</sup></code></li>
	<li>最多调用&nbsp;<code>book</code>&nbsp;1000 次。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：差分

利用有序哈希表实现。

时间复杂度 $O(n^2)$，其中 $n$ 表示日程安排的数量。

<!-- tabs:start -->

#### Python3

```python
from sortedcontainers import SortedDict


class MyCalendarTwo:
    def __init__(self):
        self.sd = SortedDict()

    def book(self, start: int, end: int) -> bool:
        self.sd[start] = self.sd.get(start, 0) + 1
        self.sd[end] = self.sd.get(end, 0) - 1
        s = 0
        for v in self.sd.values():
            s += v
            if s > 2:
                self.sd[start] -= 1
                self.sd[end] += 1
                return False
        return True


# Your MyCalendarTwo object will be instantiated and called as such:
# obj = MyCalendarTwo()
# param_1 = obj.book(start,end)
```

#### Java

```java
class MyCalendarTwo {
    private Map<Integer, Integer> tm = new TreeMap<>();

    public MyCalendarTwo() {
    }

    public boolean book(int start, int end) {
        tm.put(start, tm.getOrDefault(start, 0) + 1);
        tm.put(end, tm.getOrDefault(end, 0) - 1);
        int s = 0;
        for (int v : tm.values()) {
            s += v;
            if (s > 2) {
                tm.put(start, tm.get(start) - 1);
                tm.put(end, tm.get(end) + 1);
                return false;
            }
        }
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */
```

#### C++

```cpp
class MyCalendarTwo {
public:
    map<int, int> m;

    MyCalendarTwo() {
    }

    bool book(int start, int end) {
        ++m[start];
        --m[end];
        int s = 0;
        for (auto& [_, v] : m) {
            s += v;
            if (s > 2) {
                --m[start];
                ++m[end];
                return false;
            }
        }
        return true;
    }
};

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo* obj = new MyCalendarTwo();
 * bool param_1 = obj->book(start,end);
 */
```

#### Go

```go
type MyCalendarTwo struct {
	*redblacktree.Tree
}

func Constructor() MyCalendarTwo {
	return MyCalendarTwo{redblacktree.NewWithIntComparator()}
}

func (this *MyCalendarTwo) Book(start int, end int) bool {
	add := func(key, val int) {
		if v, ok := this.Get(key); ok {
			this.Put(key, v.(int)+val)
		} else {
			this.Put(key, val)
		}
	}
	add(start, 1)
	add(end, -1)
	s := 0
	it := this.Iterator()
	for it.Next() {
		s += it.Value().(int)
		if s > 2 {
			add(start, -1)
			add(end, 1)
			return false
		}
	}
	return true
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Book(start,end);
 */
```

#### TypeScript

```ts
class MyCalendarTwo {
    private events: [number, number][];
    private overlaps: [number, number][];

    constructor() {
        this.events = [];
        this.overlaps = [];
    }

    book(start: number, end: number): boolean {
        for (const [s, e] of this.overlaps) {
            if (Math.max(start, s) < Math.min(end, e)) {
                return false;
            }
        }

        for (const [s, e] of this.events) {
            if (Math.max(start, s) < Math.min(end, e)) {
                this.overlaps.push([Math.max(start, s), Math.min(end, e)]);
            }
        }

        this.events.push([start, end]);
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * var obj = new MyCalendarTwo()
 * var param_1 = obj.book(start,end)
 */
```

#### JavaScript

```js
var MyCalendarTwo = function () {
    this.events = [];
    this.overlaps = [];
};

/**
 * @param {number} start
 * @param {number} end
 * @return {boolean}
 */
MyCalendarTwo.prototype.book = function (start, end) {
    for (let [s, e] of this.overlaps) {
        if (Math.max(start, s) < Math.min(end, e)) {
            return false;
        }
    }

    for (let [s, e] of this.events) {
        if (Math.max(start, s) < Math.min(end, e)) {
            this.overlaps.push([Math.max(start, s), Math.min(end, e)]);
        }
    }

    this.events.push([start, end]);
    return true;
};

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * var obj = new MyCalendarTwo()
 * var param_1 = obj.book(start,end)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：线段树

线段树将整个区间分割为多个不连续的子区间，子区间的数量不超过 $log(width)$。更新某个元素的值，只需要更新 $log(width)$ 个区间，并且这些区间都包含在一个包含该元素的大区间内。区间修改时，需要使用**懒标记**保证效率。

-   线段树的每个节点代表一个区间；
-   线段树具有唯一的根节点，代表的区间是整个统计范围，如 $[1,N]$；
-   线段树的每个叶子节点代表一个长度为 $1$ 的元区间 $[x, x]$；
-   对于每个内部节点 $[l,r]$，它的左儿子是 $[l,mid]$，右儿子是 $[mid+1,r]$, 其中 $mid = ⌊(l+r)/2⌋$ (即向下取整)。

对于本题，线段树节点维护的信息有：

1. 区间范围内被预定的次数的最大值 $v$
1. 懒标记 $add$

由于时间范围为 $10^9$，非常大，因此我们采用动态开点。

时间复杂度 $O(nlogn)$，其中 $n$ 表示日程安排的数量。

<!-- tabs:start -->

#### Python3

```python
class Node:
    def __init__(self, l, r):
        self.left = None
        self.right = None
        self.l = l
        self.r = r
        self.mid = (l + r) >> 1
        self.v = 0
        self.add = 0


class SegmentTree:
    def __init__(self):
        self.root = Node(1, int(1e9 + 1))

    def modify(self, l, r, v, node=None):
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

    def query(self, l, r, node=None):
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

    def pushup(self, node):
        node.v = max(node.left.v, node.right.v)

    def pushdown(self, node):
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

    def book(self, start: int, end: int) -> bool:
        if self.tree.query(start + 1, end) >= 2:
            return False
        self.tree.modify(start + 1, end, 1)
        return True


# Your MyCalendarTwo object will be instantiated and called as such:
# obj = MyCalendarTwo()
# param_1 = obj.book(start,end)
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

    public boolean book(int start, int end) {
        if (tree.query(start + 1, end) >= 2) {
            return false;
        }
        tree.modify(start + 1, end, 1);
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */
```

#### C++

```cpp
class Node {
public:
    Node* left;
    Node* right;
    int l;
    int r;
    int mid;
    int v;
    int add;

    Node(int l, int r) {
        this->l = l;
        this->r = r;
        this->mid = (l + r) >> 1;
        this->left = this->right = nullptr;
        v = add = 0;
    }
};

class SegmentTree {
private:
    Node* root;

public:
    SegmentTree() {
        root = new Node(1, 1e9 + 1);
    }

    void modify(int l, int r, int v) {
        modify(l, r, v, root);
    }

    void modify(int l, int r, int v, Node* node) {
        if (l > r) return;
        if (node->l >= l && node->r <= r) {
            node->v += v;
            node->add += v;
            return;
        }
        pushdown(node);
        if (l <= node->mid) modify(l, r, v, node->left);
        if (r > node->mid) modify(l, r, v, node->right);
        pushup(node);
    }

    int query(int l, int r) {
        return query(l, r, root);
    }

    int query(int l, int r, Node* node) {
        if (l > r) return 0;
        if (node->l >= l && node->r <= r) return node->v;
        pushdown(node);
        int v = 0;
        if (l <= node->mid) v = max(v, query(l, r, node->left));
        if (r > node->mid) v = max(v, query(l, r, node->right));
        return v;
    }

    void pushup(Node* node) {
        node->v = max(node->left->v, node->right->v);
    }

    void pushdown(Node* node) {
        if (!node->left) node->left = new Node(node->l, node->mid);
        if (!node->right) node->right = new Node(node->mid + 1, node->r);
        if (node->add) {
            Node* left = node->left;
            Node* right = node->right;
            left->v += node->add;
            right->v += node->add;
            left->add += node->add;
            right->add += node->add;
            node->add = 0;
        }
    }
};

class MyCalendarTwo {
public:
    SegmentTree* tree = new SegmentTree();

    MyCalendarTwo() {
    }

    bool book(int start, int end) {
        if (tree->query(start + 1, end) >= 2) return false;
        tree->modify(start + 1, end, 1);
        return true;
    }
};

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo* obj = new MyCalendarTwo();
 * bool param_1 = obj->book(start,end);
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

func (this *MyCalendarTwo) Book(start int, end int) bool {
	if this.tree.query(start+1, end, this.tree.root) >= 2 {
		return false
	}
	this.tree.modify(start+1, end, 1, this.tree.root)
	return true
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Book(start,end);
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3: Line Sweep

<!-- tabs:start -->

#### TypeScript

```ts
class MyCalendarTwo {
    #OVERLAPS = 2;
    #cnt: Record<number, number> = {};

    book(start: number, end: number): boolean {
        this.#cnt[start] = (this.#cnt[start] ?? 0) + 1;
        this.#cnt[end] = (this.#cnt[end] ?? 0) - 1;

        let sum = 0;
        for (const v of Object.values(this.#cnt)) {
            sum += v;
            if (sum > this.#OVERLAPS) {
                this.#cnt[start]--;
                this.#cnt[end]++;

                if (!this.#cnt[start]) delete this.#cnt[start];
                if (!this.#cnt[end]) delete this.#cnt[end];

                return false;
            }
        }

        return true;
    }
}
```

#### JavaScript

```js
class MyCalendarTwo {
    #OVERLAPS = 2;
    #cnt = {};

    book(start, end) {
        this.#cnt[start] = (this.#cnt[start] ?? 0) + 1;
        this.#cnt[end] = (this.#cnt[end] ?? 0) - 1;

        let sum = 0;
        for (const v of Object.values(this.#cnt)) {
            sum += v;
            if (sum > this.#OVERLAPS) {
                this.#cnt[start]--;
                this.#cnt[end]++;

                if (!this.#cnt[start]) delete this.#cnt[start];
                if (!this.#cnt[end]) delete this.#cnt[end];

                return false;
            }
        }

        return true;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
