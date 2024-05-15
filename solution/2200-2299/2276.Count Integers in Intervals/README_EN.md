---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2276.Count%20Integers%20in%20Intervals/README_EN.md
rating: 2222
tags:
    - Design
    - Segment Tree
    - Ordered Set
---

# [2276. Count Integers in Intervals](https://leetcode.com/problems/count-integers-in-intervals)

[中文文档](/solution/2200-2299/2276.Count%20Integers%20in%20Intervals/README.md)

## Description

<p>Given an <strong>empty</strong> set of intervals, implement a data structure that can:</p>

<ul>
	<li><strong>Add</strong> an interval to the set of intervals.</li>
	<li><strong>Count</strong> the number of integers that are present in <strong>at least one</strong> interval.</li>
</ul>

<p>Implement the <code>CountIntervals</code> class:</p>

<ul>
	<li><code>CountIntervals()</code> Initializes the object with an empty set of intervals.</li>
	<li><code>void add(int left, int right)</code> Adds the interval <code>[left, right]</code> to the set of intervals.</li>
	<li><code>int count()</code> Returns the number of integers that are present in <strong>at least one</strong> interval.</li>
</ul>

<p><strong>Note</strong> that an interval <code>[left, right]</code> denotes all the integers <code>x</code> where <code>left &lt;= x &lt;= right</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;CountIntervals&quot;, &quot;add&quot;, &quot;add&quot;, &quot;count&quot;, &quot;add&quot;, &quot;count&quot;]
[[], [2, 3], [7, 10], [], [5, 8], []]
<strong>Output</strong>
[null, null, null, 6, null, 8]

<strong>Explanation</strong>
CountIntervals countIntervals = new CountIntervals(); // initialize the object with an empty set of intervals. 
countIntervals.add(2, 3);  // add [2, 3] to the set of intervals.
countIntervals.add(7, 10); // add [7, 10] to the set of intervals.
countIntervals.count();    // return 6
                           // the integers 2 and 3 are present in the interval [2, 3].
                           // the integers 7, 8, 9, and 10 are present in the interval [7, 10].
countIntervals.add(5, 8);  // add [5, 8] to the set of intervals.
countIntervals.count();    // return 8
                           // the integers 2 and 3 are present in the interval [2, 3].
                           // the integers 5 and 6 are present in the interval [5, 8].
                           // the integers 7 and 8 are present in the intervals [5, 8] and [7, 10].
                           // the integers 9 and 10 are present in the interval [7, 10].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= left &lt;= right &lt;= 10<sup>9</sup></code></li>
	<li>At most <code>10<sup>5</sup></code> calls <strong>in total</strong> will be made to <code>add</code> and <code>count</code>.</li>
	<li>At least <strong>one</strong> call will be made to <code>count</code>.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Node:
    def __init__(self):
        self.tag = 0
        self.tot = 0
        self.left = None
        self.right = None

    def update(self, l, r, a, b):
        if self.tag == 1:
            return
        mid = (a + b) >> 1
        if l == a and r == b:
            self.tag = 1
            self.tot = b - a + 1
            return
        if not self.left:
            self.left = Node()
        if not self.right:
            self.right = Node()
        if mid >= l:
            self.left.update(l, min(mid, r), a, mid)
        if mid + 1 <= r:
            self.right.update(max(mid + 1, l), r, mid + 1, b)
        self.tag = 0
        self.tot = self.left.tot + self.right.tot


class CountIntervals:
    def __init__(self):
        self.tree = Node()

    def add(self, left: int, right: int) -> None:
        self.tree.update(left, right, 0, 1000000010)

    def count(self) -> int:
        return self.tree.tot


# Your CountIntervals object will be instantiated and called as such:
# obj = CountIntervals()
# obj.add(left,right)
# param_2 = obj.count()
```

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
            node.v = node.r - node.l + 1;
            node.add = v;
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
            v += query(l, r, node.left);
        }
        if (r > node.mid) {
            v += query(l, r, node.right);
        }
        return v;
    }

    public void pushup(Node node) {
        node.v = node.left.v + node.right.v;
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
            left.add = node.add;
            right.add = node.add;
            left.v = left.r - left.l + 1;
            right.v = right.r - right.l + 1;
            node.add = 0;
        }
    }
}

class CountIntervals {
    private SegmentTree tree = new SegmentTree();

    public CountIntervals() {
    }

    public void add(int left, int right) {
        tree.modify(left, right, 1);
    }

    public int count() {
        return tree.query(1, (int) 1e9);
    }
}

/**
 * Your CountIntervals object will be instantiated and called as such:
 * CountIntervals obj = new CountIntervals();
 * obj.add(left,right);
 * int param_2 = obj.count();
 */
```

```cpp
class Node {
public:
    Node(int l, int r)
        : l(l)
        , r(r)
        , mid((l + r) / 2)
        , v(0)
        , add(0)
        , left(nullptr)
        , right(nullptr) {}

    int l, r, mid, v, add;
    Node* left;
    Node* right;
};

class SegmentTree {
public:
    SegmentTree()
        : root(new Node(1, 1000000001)) {}

    void modify(int l, int r, int v, Node* node = nullptr) {
        if (node == nullptr) {
            node = root;
        }
        if (l > r) {
            return;
        }
        if (node->l >= l && node->r <= r) {
            node->v = node->r - node->l + 1;
            node->add = v;
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
        if (node == nullptr) {
            node = root;
        }
        if (l > r) {
            return 0;
        }
        if (node->l >= l && node->r <= r) {
            return node->v;
        }
        pushdown(node);
        int v = 0;
        if (l <= node->mid) {
            v += query(l, r, node->left);
        }
        if (r > node->mid) {
            v += query(l, r, node->right);
        }
        return v;
    }

private:
    Node* root;

    void pushup(Node* node) {
        node->v = node->left->v + node->right->v;
    }

    void pushdown(Node* node) {
        if (node->left == nullptr) {
            node->left = new Node(node->l, node->mid);
        }
        if (node->right == nullptr) {
            node->right = new Node(node->mid + 1, node->r);
        }
        if (node->add != 0) {
            Node* left = node->left;
            Node* right = node->right;
            left->add = node->add;
            right->add = node->add;
            left->v = left->r - left->l + 1;
            right->v = right->r - right->l + 1;
            node->add = 0;
        }
    }
};

class CountIntervals {
public:
    CountIntervals() {}

    void add(int left, int right) {
        tree.modify(left, right, 1);
    }

    int count() {
        return tree.query(1, 1000000000);
    }

private:
    SegmentTree tree;
};

/**
 * Your CountIntervals object will be instantiated and called as such:
 * CountIntervals* obj = new CountIntervals();
 * obj->add(left,right);
 * int param_2 = obj->count();
 */
```

```go
type Node struct {
	left  *Node
	right *Node
	l     int
	r     int
	mid   int
	v     int
	add   int
}

type SegmentTree struct {
	root *Node
}

func newNode(l, r int) *Node {
	return &Node{
		left:  nil,
		right: nil,
		l:     l,
		r:     r,
		mid:   (l + r) / 2,
		v:     0,
		add:   0,
	}
}

func newSegmentTree() *SegmentTree {
	return &SegmentTree{
		root: newNode(1, 1000000001),
	}
}

func (st *SegmentTree) modify(l, r, v int, node *Node) {
	if node == nil {
		node = st.root
	}
	if l > r {
		return
	}
	if node.l >= l && node.r <= r {
		node.v = node.r - node.l + 1
		node.add = v
		return
	}
	st.pushdown(node)
	if l <= node.mid {
		st.modify(l, r, v, node.left)
	}
	if r > node.mid {
		st.modify(l, r, v, node.right)
	}
	st.pushup(node)
}

func (st *SegmentTree) query(l, r int, node *Node) int {
	if node == nil {
		node = st.root
	}
	if l > r {
		return 0
	}
	if node.l >= l && node.r <= r {
		return node.v
	}
	st.pushdown(node)
	v := 0
	if l <= node.mid {
		v += st.query(l, r, node.left)
	}
	if r > node.mid {
		v += st.query(l, r, node.right)
	}
	return v
}

func (st *SegmentTree) pushup(node *Node) {
	node.v = node.left.v + node.right.v
}

func (st *SegmentTree) pushdown(node *Node) {
	if node.left == nil {
		node.left = newNode(node.l, node.mid)
	}
	if node.right == nil {
		node.right = newNode(node.mid+1, node.r)
	}
	if node.add != 0 {
		left := node.left
		right := node.right
		left.add = node.add
		right.add = node.add
		left.v = left.r - left.l + 1
		right.v = right.r - right.l + 1
		node.add = 0
	}
}

type CountIntervals struct {
	tree *SegmentTree
}

func Constructor() CountIntervals {
	return CountIntervals{
		tree: newSegmentTree(),
	}
}

func (ci *CountIntervals) Add(left, right int) {
	ci.tree.modify(left, right, 1, nil)
}

func (ci *CountIntervals) Count() int {
	return ci.tree.query(1, 1000000000, nil)
}

/**
 * Your CountIntervals object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Add(left,right);
 * param_2 := obj.Count();
 */
```

```ts
class CountIntervals {
    left: null | CountIntervals;
    right: null | CountIntervals;
    start: number;
    end: number;
    sum: number;
    constructor(start: number = 0, end: number = 10 ** 9) {
        this.left = null;
        this.right = null;
        this.start = start;
        this.end = end;
        this.sum = 0;
    }

    add(left: number, right: number): void {
        if (this.sum == this.end - this.start + 1) return;
        if (left <= this.start && right >= this.end) {
            this.sum = this.end - this.start + 1;
            return;
        }
        let mid = (this.start + this.end) >> 1;
        if (!this.left) this.left = new CountIntervals(this.start, mid);
        if (!this.right) this.right = new CountIntervals(mid + 1, this.end);
        if (left <= mid) this.left.add(left, right);
        if (right > mid) this.right.add(left, right);
        this.sum = this.left.sum + this.right.sum;
    }

    count(): number {
        return this.sum;
    }
}

/**
 * Your CountIntervals object will be instantiated and called as such:
 * var obj = new CountIntervals()
 * obj.add(left,right)
 * var param_2 = obj.count()
 */
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```python
class Node:
    __slots__ = ("left", "right", "l", "r", "mid", "v", "add")

    def __init__(self, l, r):
        self.left = None
        self.right = None
        self.l = l
        self.r = r
        self.mid = (l + r) // 2
        self.v = 0
        self.add = 0


class SegmentTree:
    def __init__(self):
        self.root = Node(1, int(1e9) + 1)

    def modify(self, l, r, v, node=None):
        if node is None:
            node = self.root
        if l > r:
            return
        if node.l >= l and node.r <= r:
            node.v = node.r - node.l + 1
            node.add = v
            return
        self.pushdown(node)
        if l <= node.mid:
            self.modify(l, r, v, node.left)
        if r > node.mid:
            self.modify(l, r, v, node.right)
        self.pushup(node)

    def query(self, l, r, node=None):
        if node is None:
            node = self.root
        if l > r:
            return 0
        if node.l >= l and node.r <= r:
            return node.v
        self.pushdown(node)
        v = 0
        if l <= node.mid:
            v += self.query(l, r, node.left)
        if r > node.mid:
            v += self.query(l, r, node.right)
        return v

    def pushup(self, node):
        node.v = node.left.v + node.right.v

    def pushdown(self, node):
        if node.left is None:
            node.left = Node(node.l, node.mid)
        if node.right is None:
            node.right = Node(node.mid + 1, node.r)
        if node.add != 0:
            left, right = node.left, node.right
            left.add = node.add
            right.add = node.add
            left.v = left.r - left.l + 1
            right.v = right.r - right.l + 1
            node.add = 0


class CountIntervals:
    def __init__(self):
        self.tree = SegmentTree()

    def add(self, left, right):
        self.tree.modify(left, right, 1)

    def count(self):
        return self.tree.query(1, int(1e9))


# Your CountIntervals object will be instantiated and called as such:
# obj = CountIntervals()
# obj.add(left, right)
# param_2 = obj.count()
```

<!-- tabs:end -->

<!-- end -->
