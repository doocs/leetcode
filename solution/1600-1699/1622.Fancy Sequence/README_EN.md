---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1622.Fancy%20Sequence/README_EN.md
rating: 2476
source: Biweekly Contest 37 Q4
tags:
    - Design
    - Segment Tree
    - Math
---

<!-- problem:start -->

# [1622. Fancy Sequence](https://leetcode.com/problems/fancy-sequence)

[中文文档](/solution/1600-1699/1622.Fancy%20Sequence/README.md)

## Description

<!-- description:start -->

<p>Write an API that generates fancy sequences using the <code>append</code>, <code>addAll</code>, and <code>multAll</code> operations.</p>

<p>Implement the <code>Fancy</code> class:</p>

<ul>
	<li><code>Fancy()</code> Initializes the object with an empty sequence.</li>
	<li><code>void append(val)</code> Appends an integer <code>val</code> to the end of the sequence.</li>
	<li><code>void addAll(inc)</code> Increments all existing values in the sequence by an integer <code>inc</code>.</li>
	<li><code>void multAll(m)</code> Multiplies all existing values in the sequence by an integer <code>m</code>.</li>
	<li><code>int getIndex(idx)</code> Gets the current value at index <code>idx</code> (0-indexed) of the sequence <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>. If the index is greater or equal than the length of the sequence, return <code>-1</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;Fancy&quot;, &quot;append&quot;, &quot;addAll&quot;, &quot;append&quot;, &quot;multAll&quot;, &quot;getIndex&quot;, &quot;addAll&quot;, &quot;append&quot;, &quot;multAll&quot;, &quot;getIndex&quot;, &quot;getIndex&quot;, &quot;getIndex&quot;]
[[], [2], [3], [7], [2], [0], [3], [10], [2], [0], [1], [2]]
<strong>Output</strong>
[null, null, null, null, null, 10, null, null, null, 26, 34, 20]

<strong>Explanation</strong>
Fancy fancy = new Fancy();
fancy.append(2);   // fancy sequence: [2]
fancy.addAll(3);   // fancy sequence: [2+3] -&gt; [5]
fancy.append(7);   // fancy sequence: [5, 7]
fancy.multAll(2);  // fancy sequence: [5*2, 7*2] -&gt; [10, 14]
fancy.getIndex(0); // return 10
fancy.addAll(3);   // fancy sequence: [10+3, 14+3] -&gt; [13, 17]
fancy.append(10);  // fancy sequence: [13, 17, 10]
fancy.multAll(2);  // fancy sequence: [13*2, 17*2, 10*2] -&gt; [26, 34, 20]
fancy.getIndex(0); // return 26
fancy.getIndex(1); // return 34
fancy.getIndex(2); // return 20
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= val, inc, m &lt;= 100</code></li>
	<li><code>0 &lt;= idx &lt;= 10<sup>5</sup></code></li>
	<li>At most <code>10<sup>5</sup></code> calls total will be made to <code>append</code>, <code>addAll</code>, <code>multAll</code>, and <code>getIndex</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
MOD = int(1e9 + 7)


class Node:
    def __init__(self, l, r):
        self.left = None
        self.right = None
        self.l = l
        self.r = r
        self.mid = (l + r) >> 1
        self.v = 0
        self.add = 0
        self.mul = 1


class SegmentTree:
    def __init__(self):
        self.root = Node(1, int(1e5 + 1))

    def modifyAdd(self, l, r, inc, node=None):
        if l > r:
            return
        if node is None:
            node = self.root
        if node.l >= l and node.r <= r:
            node.v = (node.v + (node.r - node.l + 1) * inc) % MOD
            node.add += inc
            return
        self.pushdown(node)
        if l <= node.mid:
            self.modifyAdd(l, r, inc, node.left)
        if r > node.mid:
            self.modifyAdd(l, r, inc, node.right)
        self.pushup(node)

    def modifyMul(self, l, r, m, node=None):
        if l > r:
            return
        if node is None:
            node = self.root
        if node.l >= l and node.r <= r:
            node.v = (node.v * m) % MOD
            node.add = (node.add * m) % MOD
            node.mul = (node.mul * m) % MOD
            return
        self.pushdown(node)
        if l <= node.mid:
            self.modifyMul(l, r, m, node.left)
        if r > node.mid:
            self.modifyMul(l, r, m, node.right)
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
            v = (v + self.query(l, r, node.left)) % MOD
        if r > node.mid:
            v = (v + self.query(l, r, node.right)) % MOD
        return v

    def pushup(self, node):
        node.v = (node.left.v + node.right.v) % MOD

    def pushdown(self, node):
        if node.left is None:
            node.left = Node(node.l, node.mid)
        if node.right is None:
            node.right = Node(node.mid + 1, node.r)
        left, right = node.left, node.right
        if node.add != 0 or node.mul != 1:
            left.v = (left.v * node.mul + (left.r - left.l + 1) * node.add) % MOD
            right.v = (right.v * node.mul + (right.r - right.l + 1) * node.add) % MOD
            left.add = (left.add * node.mul + node.add) % MOD
            right.add = (right.add * node.mul + node.add) % MOD
            left.mul = (left.mul * node.mul) % MOD
            right.mul = (right.mul * node.mul) % MOD
            node.add = 0
            node.mul = 1


class Fancy:
    def __init__(self):
        self.n = 0
        self.tree = SegmentTree()

    def append(self, val: int) -> None:
        self.n += 1
        self.tree.modifyAdd(self.n, self.n, val)

    def addAll(self, inc: int) -> None:
        self.tree.modifyAdd(1, self.n, inc)

    def multAll(self, m: int) -> None:
        self.tree.modifyMul(1, self.n, m)

    def getIndex(self, idx: int) -> int:
        return -1 if idx >= self.n else self.tree.query(idx + 1, idx + 1)


# Your Fancy object will be instantiated and called as such:
# obj = Fancy()
# obj.append(val)
# obj.addAll(inc)
# obj.multAll(m)
# param_4 = obj.getIndex(idx)
```

#### Java

```java
class Node {
    Node left;
    Node right;
    int l;
    int r;
    int mid;
    long v;
    long add;
    long mul = 1;

    public Node(int l, int r) {
        this.l = l;
        this.r = r;
        this.mid = (l + r) >> 1;
    }
}

class SegmentTree {
    private Node root = new Node(1, (int) 1e5 + 1);
    private static final int MOD = (int) 1e9 + 7;

    public SegmentTree() {
    }

    public void modifyAdd(int l, int r, int inc) {
        modifyAdd(l, r, inc, root);
    }

    public void modifyAdd(int l, int r, int inc, Node node) {
        if (l > r) {
            return;
        }
        if (node.l >= l && node.r <= r) {
            node.v = (node.v + (node.r - node.l + 1) * inc) % MOD;
            node.add = (node.add + inc) % MOD;
            return;
        }
        pushdown(node);
        if (l <= node.mid) {
            modifyAdd(l, r, inc, node.left);
        }
        if (r > node.mid) {
            modifyAdd(l, r, inc, node.right);
        }
        pushup(node);
    }

    public void modifyMul(int l, int r, int m) {
        modifyMul(l, r, m, root);
    }

    public void modifyMul(int l, int r, int m, Node node) {
        if (l > r) {
            return;
        }
        if (node.l >= l && node.r <= r) {
            node.v = (node.v * m) % MOD;
            node.add = (node.add * m) % MOD;
            node.mul = (node.mul * m) % MOD;
            return;
        }
        pushdown(node);
        if (l <= node.mid) {
            modifyMul(l, r, m, node.left);
        }
        if (r > node.mid) {
            modifyMul(l, r, m, node.right);
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
            return (int) node.v;
        }
        pushdown(node);
        int v = 0;
        if (l <= node.mid) {
            v = (v + query(l, r, node.left)) % MOD;
        }
        if (r > node.mid) {
            v = (v + query(l, r, node.right)) % MOD;
        }
        return v;
    }

    public void pushup(Node node) {
        node.v = (node.left.v + node.right.v) % MOD;
    }

    public void pushdown(Node node) {
        if (node.left == null) {
            node.left = new Node(node.l, node.mid);
        }
        if (node.right == null) {
            node.right = new Node(node.mid + 1, node.r);
        }
        if (node.add != 0 || node.mul != 1) {
            Node left = node.left, right = node.right;
            left.v = (left.v * node.mul + (left.r - left.l + 1) * node.add) % MOD;
            right.v = (right.v * node.mul + (right.r - right.l + 1) * node.add) % MOD;
            left.add = (left.add * node.mul + node.add) % MOD;
            right.add = (right.add * node.mul + node.add) % MOD;
            left.mul = (left.mul * node.mul) % MOD;
            right.mul = (right.mul * node.mul) % MOD;
            node.add = 0;
            node.mul = 1;
        }
    }
}

class Fancy {
    private int n;
    private SegmentTree tree = new SegmentTree();

    public Fancy() {
    }

    public void append(int val) {
        ++n;
        tree.modifyAdd(n, n, val);
    }

    public void addAll(int inc) {
        tree.modifyAdd(1, n, inc);
    }

    public void multAll(int m) {
        tree.modifyMul(1, n, m);
    }

    public int getIndex(int idx) {
        return idx >= n ? -1 : tree.query(idx + 1, idx + 1);
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */
```

#### C++

```cpp
const int MOD = 1e9 + 7;

class Node {
public:
    Node* left;
    Node* right;
    int l;
    int r;
    int mid;
    long long v;
    long long add;
    long long mul;

    Node(int l, int r) {
        this->l = l;
        this->r = r;
        this->mid = (l + r) >> 1;
        this->left = this->right = nullptr;
        v = add = 0;
        mul = 1;
    }
};

class SegmentTree {
private:
    Node* root;

public:
    SegmentTree() {
        root = new Node(1, 1e5 + 1);
    }

    void modifyAdd(int l, int r, int inc) {
        modifyAdd(l, r, inc, root);
    }

    void modifyAdd(int l, int r, int inc, Node* node) {
        if (l > r) return;
        if (node->l >= l && node->r <= r) {
            node->v = (node->v + (node->r - node->l + 1) * inc) % MOD;
            node->add = (node->add + inc) % MOD;
            return;
        }
        pushdown(node);
        if (l <= node->mid) modifyAdd(l, r, inc, node->left);
        if (r > node->mid) modifyAdd(l, r, inc, node->right);
        pushup(node);
    }

    void modifyMul(int l, int r, int m) {
        modifyMul(l, r, m, root);
    }

    void modifyMul(int l, int r, int m, Node* node) {
        if (l > r) return;
        if (node->l >= l && node->r <= r) {
            node->v = (node->v * m) % MOD;
            node->add = (node->add * m) % MOD;
            node->mul = (node->mul * m) % MOD;
            return;
        }
        pushdown(node);
        if (l <= node->mid) modifyMul(l, r, m, node->left);
        if (r > node->mid) modifyMul(l, r, m, node->right);
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
        if (l <= node->mid) v = (v + query(l, r, node->left)) % MOD;
        if (r > node->mid) v = (v + query(l, r, node->right)) % MOD;
        return v;
    }

    void pushup(Node* node) {
        node->v = (node->left->v + node->right->v) % MOD;
    }

    void pushdown(Node* node) {
        if (!node->left) node->left = new Node(node->l, node->mid);
        if (!node->right) node->right = new Node(node->mid + 1, node->r);
        if (node->add || node->mul != 1) {
            long add = node->add, mul = node->mul;
            Node* left = node->left;
            Node* right = node->right;
            left->v = (left->v * mul + (left->r - left->l + 1) * add) % MOD;
            right->v = (right->v * mul + (right->r - right->l + 1) * add) % MOD;
            left->add = (left->add * mul + add) % MOD;
            right->add = (right->add * mul + add) % MOD;
            left->mul = (left->mul * mul) % MOD;
            right->mul = (right->mul * mul) % MOD;
            node->add = 0;
            node->mul = 1;
        }
    }
};

class Fancy {
public:
    int n;
    SegmentTree* tree;

    Fancy() {
        n = 0;
        tree = new SegmentTree();
    }

    void append(int val) {
        ++n;
        tree->modifyAdd(n, n, val);
    }

    void addAll(int inc) {
        tree->modifyAdd(1, n, inc);
    }

    void multAll(int m) {
        tree->modifyMul(1, n, m);
    }

    int getIndex(int idx) {
        return idx >= n ? -1 : tree->query(idx + 1, idx + 1);
    }
};

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy* obj = new Fancy();
 * obj->append(val);
 * obj->addAll(inc);
 * obj->multAll(m);
 * int param_4 = obj->getIndex(idx);
 */
```

#### Go

```go
const MOD int64 = 1e9 + 7

type Node struct {
	left  *Node
	right *Node
	l     int
	r     int
	mid   int
	v     int64
	add   int64
	mul   int64
}

func newNode(l, r int) *Node {
	return &Node{
		l:   l,
		r:   r,
		mid: (l + r) >> 1,
		mul: 1,
	}
}

type SegmentTree struct {
	root *Node
}

func newSegmentTree() *SegmentTree {
	return &SegmentTree{
		root: newNode(1, 100001),
	}
}

func (t *SegmentTree) modifyAdd(l, r int, inc int64) {
	t.modifyAddNode(l, r, inc, t.root)
}

func (t *SegmentTree) modifyAddNode(l, r int, inc int64, node *Node) {
	if l > r {
		return
	}

	if node.l >= l && node.r <= r {
		node.v = (node.v + int64(node.r-node.l+1)*inc) % MOD
		node.add = (node.add + inc) % MOD
		return
	}

	t.pushdown(node)

	if l <= node.mid {
		t.modifyAddNode(l, r, inc, node.left)
	}

	if r > node.mid {
		t.modifyAddNode(l, r, inc, node.right)
	}

	t.pushup(node)
}

func (t *SegmentTree) modifyMul(l, r int, m int64) {
	t.modifyMulNode(l, r, m, t.root)
}

func (t *SegmentTree) modifyMulNode(l, r int, m int64, node *Node) {
	if l > r {
		return
	}

	if node.l >= l && node.r <= r {
		node.v = node.v * m % MOD
		node.add = node.add * m % MOD
		node.mul = node.mul * m % MOD
		return
	}

	t.pushdown(node)

	if l <= node.mid {
		t.modifyMulNode(l, r, m, node.left)
	}

	if r > node.mid {
		t.modifyMulNode(l, r, m, node.right)
	}

	t.pushup(node)
}

func (t *SegmentTree) query(l, r int) int {
	return int(t.queryNode(l, r, t.root))
}

func (t *SegmentTree) queryNode(l, r int, node *Node) int64 {
	if l > r {
		return 0
	}

	if node.l >= l && node.r <= r {
		return node.v
	}

	t.pushdown(node)

	var v int64

	if l <= node.mid {
		v = (v + t.queryNode(l, r, node.left)) % MOD
	}

	if r > node.mid {
		v = (v + t.queryNode(l, r, node.right)) % MOD
	}

	return v
}

func (t *SegmentTree) pushup(node *Node) {
	node.v = (node.left.v + node.right.v) % MOD
}

func (t *SegmentTree) pushdown(node *Node) {

	if node.left == nil {
		node.left = newNode(node.l, node.mid)
	}

	if node.right == nil {
		node.right = newNode(node.mid+1, node.r)
	}

	if node.add != 0 || node.mul != 1 {

		add := node.add
		mul := node.mul

		left := node.left
		right := node.right

		left.v = (left.v*mul + int64(left.r-left.l+1)*add) % MOD
		right.v = (right.v*mul + int64(right.r-right.l+1)*add) % MOD

		left.add = (left.add*mul + add) % MOD
		right.add = (right.add*mul + add) % MOD

		left.mul = left.mul * mul % MOD
		right.mul = right.mul * mul % MOD

		node.add = 0
		node.mul = 1
	}
}

type Fancy struct {
	n    int
	tree *SegmentTree
}

func Constructor() Fancy {
	return Fancy{
		tree: newSegmentTree(),
	}
}

func (f *Fancy) Append(val int) {
	f.n++
	f.tree.modifyAdd(f.n, f.n, int64(val))
}

func (f *Fancy) AddAll(inc int) {
	f.tree.modifyAdd(1, f.n, int64(inc))
}

func (f *Fancy) MultAll(m int) {
	f.tree.modifyMul(1, f.n, int64(m))
}

func (f *Fancy) GetIndex(idx int) int {
	if idx >= f.n {
		return -1
	}
	return f.tree.query(idx+1, idx+1)
}
```

#### TypeScript

```ts
const MOD = 1000000007n;

class Node {
    left: Node | null = null;
    right: Node | null = null;

    l: number;
    r: number;
    mid: number;

    v = 0n;
    add = 0n;
    mul = 1n;

    constructor(l: number, r: number) {
        this.l = l;
        this.r = r;
        this.mid = (l + r) >> 1;
    }
}

class SegmentTree {
    root: Node;

    constructor() {
        this.root = new Node(1, 100001);
    }

    modifyAdd(l: number, r: number, inc: bigint, node: Node = this.root): void {
        if (l > r) return;

        if (node.l >= l && node.r <= r) {
            node.v = (node.v + BigInt(node.r - node.l + 1) * inc) % MOD;
            node.add = (node.add + inc) % MOD;
            return;
        }

        this.pushdown(node);

        if (l <= node.mid) this.modifyAdd(l, r, inc, node.left!);
        if (r > node.mid) this.modifyAdd(l, r, inc, node.right!);

        this.pushup(node);
    }

    modifyMul(l: number, r: number, m: bigint, node: Node = this.root): void {
        if (l > r) return;

        if (node.l >= l && node.r <= r) {
            node.v = (node.v * m) % MOD;
            node.add = (node.add * m) % MOD;
            node.mul = (node.mul * m) % MOD;
            return;
        }

        this.pushdown(node);

        if (l <= node.mid) this.modifyMul(l, r, m, node.left!);
        if (r > node.mid) this.modifyMul(l, r, m, node.right!);

        this.pushup(node);
    }

    query(l: number, r: number, node: Node = this.root): bigint {
        if (l > r) return 0n;

        if (node.l >= l && node.r <= r) return node.v;

        this.pushdown(node);

        let v = 0n;

        if (l <= node.mid) v = (v + this.query(l, r, node.left!)) % MOD;
        if (r > node.mid) v = (v + this.query(l, r, node.right!)) % MOD;

        return v;
    }

    pushup(node: Node): void {
        node.v = (node.left!.v + node.right!.v) % MOD;
    }

    pushdown(node: Node): void {
        if (!node.left) node.left = new Node(node.l, node.mid);
        if (!node.right) node.right = new Node(node.mid + 1, node.r);

        if (node.add !== 0n || node.mul !== 1n) {
            const add = node.add;
            const mul = node.mul;

            const left = node.left!;
            const right = node.right!;

            left.v = (left.v * mul + BigInt(left.r - left.l + 1) * add) % MOD;
            right.v = (right.v * mul + BigInt(right.r - right.l + 1) * add) % MOD;

            left.add = (left.add * mul + add) % MOD;
            right.add = (right.add * mul + add) % MOD;

            left.mul = (left.mul * mul) % MOD;
            right.mul = (right.mul * mul) % MOD;

            node.add = 0n;
            node.mul = 1n;
        }
    }
}

class Fancy {
    n = 0;
    tree = new SegmentTree();

    append(val: number): void {
        this.n++;
        this.tree.modifyAdd(this.n, this.n, BigInt(val));
    }

    addAll(inc: number): void {
        this.tree.modifyAdd(1, this.n, BigInt(inc));
    }

    multAll(m: number): void {
        this.tree.modifyMul(1, this.n, BigInt(m));
    }

    getIndex(idx: number): number {
        if (idx >= this.n) return -1;
        return Number(this.tree.query(idx + 1, idx + 1));
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * var obj = new Fancy()
 * obj.append(val)
 * obj.addAll(inc)
 * obj.multAll(m)
 * var param_4 = obj.getIndex(idx)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
