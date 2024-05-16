---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2276.Count%20Integers%20in%20Intervals/README.md
rating: 2222
source: 第 293 场周赛 Q4
tags:
    - 设计
    - 线段树
    - 有序集合
---

<!-- problem:start -->

# [2276. 统计区间中的整数数目](https://leetcode.cn/problems/count-integers-in-intervals)

[English Version](/solution/2200-2299/2276.Count%20Integers%20in%20Intervals/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你区间的 <strong>空</strong> 集，请你设计并实现满足要求的数据结构：</p>

<ul>
	<li><strong>新增：</strong>添加一个区间到这个区间集合中。</li>
	<li><strong>统计：</strong>计算出现在 <strong>至少一个</strong> 区间中的整数个数。</li>
</ul>

<p>实现 <code>CountIntervals</code> 类：</p>

<ul>
	<li><code>CountIntervals()</code> 使用区间的空集初始化对象</li>
	<li><code>void add(int left, int right)</code> 添加区间 <code>[left, right]</code> 到区间集合之中。</li>
	<li><code>int count()</code> 返回出现在 <strong>至少一个</strong> 区间中的整数个数。</li>
</ul>

<p><strong>注意：</strong>区间 <code>[left, right]</code> 表示满足 <code>left &lt;= x &lt;= right</code> 的所有整数 <code>x</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入</strong>
["CountIntervals", "add", "add", "count", "add", "count"]
[[], [2, 3], [7, 10], [], [5, 8], []]
<strong>输出</strong>
[null, null, null, 6, null, 8]

<strong>解释</strong>
CountIntervals countIntervals = new CountIntervals(); // 用一个区间空集初始化对象
countIntervals.add(2, 3);  // 将 [2, 3] 添加到区间集合中
countIntervals.add(7, 10); // 将 [7, 10] 添加到区间集合中
countIntervals.count();    // 返回 6
                           // 整数 2 和 3 出现在区间 [2, 3] 中
                           // 整数 7、8、9、10 出现在区间 [7, 10] 中
countIntervals.add(5, 8);  // 将 [5, 8] 添加到区间集合中
countIntervals.count();    // 返回 8
                           // 整数 2 和 3 出现在区间 [2, 3] 中
                           // 整数 5 和 6 出现在区间 [5, 8] 中
                           // 整数 7 和 8 出现在区间 [5, 8] 和区间 [7, 10] 中
                           // 整数 9 和 10 出现在区间 [7, 10] 中</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= left &lt;= right &lt;= 10<sup>9</sup></code></li>
	<li>最多调用&nbsp; <code>add</code> 和 <code>count</code> 方法 <strong>总计</strong> <code>10<sup>5</sup></code> 次</li>
	<li>调用 <code>count</code> 方法至少一次</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：线段树（动态开点）

根据题目描述，我们需要维护一个区间集合，支持区间的添加和查询操作。对于区间的添加，我们可以使用线段树来维护区间集合。

线段树将整个区间分割为多个不连续的子区间，子区间的数量不超过 $\log(width)$。更新某个元素的值，只需要更新 $\log(width)$ 个区间，并且这些区间都包含在一个包含该元素的大区间内。区间修改时，需要使用**懒标记**保证效率。

-   线段树的每个节点代表一个区间；
-   线段树具有唯一的根节点，代表的区间是整个统计范围，如 $[1,N]$；
-   线段树的每个叶子节点代表一个长度为 $1$ 的元区间 $[x,x]$；
-   对于每个内部节点 $[l,r]$，它的左儿子是 $[l,mid]$，右儿子是 $[mid+1,r]$, 其中 $mid=⌊(l+r)/2⌋$ (即向下取整)。

由于题目数据范围较大，我们可以使用动态开点的线段树来实现。动态开点的线段树是指，我们只在需要的时候才开点，而不是一开始就开好所有的点，这样可以节省空间。

时间复杂度方面，每次操作的时间复杂度为 $O(\log n)$。空间复杂度为 $O(m \times \log n)$。其中 $m$ 为操作次数，而 $n$ 为数据范围。

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

<!-- solution:end -->

<!-- solution:start -->

### 方法二

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

<!-- solution:end -->

<!-- problem:end -->
