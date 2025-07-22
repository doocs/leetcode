---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0855.Exam%20Room/README.md
tags:
    - 设计
    - 有序集合
    - 堆（优先队列）
---

<!-- problem:start -->

# [855. 考场就座](https://leetcode.cn/problems/exam-room)

[English Version](/solution/0800-0899/0855.Exam%20Room/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在考场里，有&nbsp;<code>n</code>&nbsp;个座位排成一行，编号为 <code>0</code> 到 <code>n - 1</code>。</p>

<p>当学生进入考场后，他必须坐在离最近的人最远的座位上。如果有多个这样的座位，他会坐在编号最小的座位上。(另外，如果考场里没有人，那么学生就坐在 <code>0</code> 号座位上。)</p>

<p>设计一个模拟所述考场的类。</p>

<p>实现&nbsp;<code>ExamRoom</code>&nbsp;类：</p>

<ul>
	<li><code>ExamRoom(int n)</code> 用座位的数量&nbsp;<code>n</code> 初始化考场对象。</li>
	<li><code>int seat()</code> 返回下一个学生将会入座的座位编号。</li>
	<li><code>void leave(int p)</code> 指定坐在座位 <code>p</code> 的学生将离开教室。保证座位 <code>p</code> 上会有一位学生。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["ExamRoom", "seat", "seat", "seat", "seat", "leave", "seat"]
[[10], [], [], [], [], [4], []]
<strong>输出：</strong>
[null, 0, 9, 4, 2, null, 5]
<strong>解释：</strong>
ExamRoom examRoom = new ExamRoom(10);
examRoom.seat(); // 返回 0，房间里没有人，学生坐在 0 号座位。
examRoom.seat(); // 返回 9，学生最后坐在 9 号座位。
examRoom.seat(); // 返回 4，学生最后坐在 4 号座位。
examRoom.seat(); // 返回 2，学生最后坐在 2 号座位。
examRoom.leave(4);
examRoom.seat(); // 返回 5，学生最后坐在 5 号座位。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li>保证有学生正坐在座位 <code>p</code> 上。</li>
	<li><code>seat</code>&nbsp;和&nbsp;<code>leave</code>&nbsp;最多被调用&nbsp;<code>10<sup>4</sup></code>&nbsp;次。</li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：有序集合 + 哈希表

考虑到每次 $\text{seat}()$ 时都需要找到最大距离的座位，我们可以使用有序集合来保存座位区间。有序集合的每个元素为一个二元组 $(l, r)$，表示 $l$ 和 $r$ 之间（不包括 $l$ 和 $r$）的座位可以坐学生。初始时有序集合中只有一个元素 $(-1, n)$，表示 $(-1, n)$ 之间的座位可以坐学生。

另外，我们使用两个哈希表 $\textit{left}$ 和 $\textit{right}$ 来维护每个有学生的座位的左右邻居学生，方便我们在 $\text{leave}(p)$ 时合并两个座位区间。

时间复杂度 $O(\log n)$，空间复杂度 $O(n)$。其中 $n$ 为考场的座位数。

<!-- tabs:start -->

#### Python3

```python
class ExamRoom:
    def __init__(self, n: int):
        def dist(x):
            l, r = x
            return r - l - 1 if l == -1 or r == n else (r - l) >> 1

        self.n = n
        self.ts = SortedList(key=lambda x: (-dist(x), x[0]))
        self.left = {}
        self.right = {}
        self.add((-1, n))

    def seat(self) -> int:
        s = self.ts[0]
        p = (s[0] + s[1]) >> 1
        if s[0] == -1:
            p = 0
        elif s[1] == self.n:
            p = self.n - 1
        self.delete(s)
        self.add((s[0], p))
        self.add((p, s[1]))
        return p

    def leave(self, p: int) -> None:
        l, r = self.left[p], self.right[p]
        self.delete((l, p))
        self.delete((p, r))
        self.add((l, r))

    def add(self, s):
        self.ts.add(s)
        self.left[s[1]] = s[0]
        self.right[s[0]] = s[1]

    def delete(self, s):
        self.ts.remove(s)
        self.left.pop(s[1])
        self.right.pop(s[0])


# Your ExamRoom object will be instantiated and called as such:
# obj = ExamRoom(n)
# param_1 = obj.seat()
# obj.leave(p)
```

#### Java

```java
class ExamRoom {
    private TreeSet<int[]> ts = new TreeSet<>((a, b) -> {
        int d1 = dist(a), d2 = dist(b);
        return d1 == d2 ? a[0] - b[0] : d2 - d1;
    });
    private Map<Integer, Integer> left = new HashMap<>();
    private Map<Integer, Integer> right = new HashMap<>();
    private int n;

    public ExamRoom(int n) {
        this.n = n;
        add(new int[] {-1, n});
    }

    public int seat() {
        int[] s = ts.first();
        int p = (s[0] + s[1]) >> 1;
        if (s[0] == -1) {
            p = 0;
        } else if (s[1] == n) {
            p = n - 1;
        }
        del(s);
        add(new int[] {s[0], p});
        add(new int[] {p, s[1]});
        return p;
    }

    public void leave(int p) {
        int l = left.get(p), r = right.get(p);
        del(new int[] {l, p});
        del(new int[] {p, r});
        add(new int[] {l, r});
    }

    private int dist(int[] s) {
        int l = s[0], r = s[1];
        return l == -1 || r == n ? r - l - 1 : (r - l) >> 1;
    }

    private void add(int[] s) {
        ts.add(s);
        left.put(s[1], s[0]);
        right.put(s[0], s[1]);
    }

    private void del(int[] s) {
        ts.remove(s);
        left.remove(s[1]);
        right.remove(s[0]);
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(n);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */
```

#### C++

```cpp
int N;

int dist(const pair<int, int>& p) {
    auto [l, r] = p;
    if (l == -1 || r == N) return r - l - 1;
    return (r - l) >> 1;
}

struct cmp {
    bool operator()(const pair<int, int>& a, const pair<int, int>& b) const {
        int d1 = dist(a), d2 = dist(b);
        return d1 == d2 ? a.first < b.first : d1 > d2;
    };
};

class ExamRoom {
public:
    ExamRoom(int n) {
        N = n;
        this->n = n;
        add({-1, n});
    }

    int seat() {
        auto s = *ts.begin();
        int p = (s.first + s.second) >> 1;
        if (s.first == -1) {
            p = 0;
        } else if (s.second == n) {
            p = n - 1;
        }
        del(s);
        add({s.first, p});
        add({p, s.second});
        return p;
    }

    void leave(int p) {
        int l = left[p], r = right[p];
        del({l, p});
        del({p, r});
        add({l, r});
    }

private:
    set<pair<int, int>, cmp> ts;
    unordered_map<int, int> left;
    unordered_map<int, int> right;
    int n;

    void add(pair<int, int> s) {
        ts.insert(s);
        left[s.second] = s.first;
        right[s.first] = s.second;
    }

    void del(pair<int, int> s) {
        ts.erase(s);
        left.erase(s.second);
        right.erase(s.first);
    }
};

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom* obj = new ExamRoom(n);
 * int param_1 = obj->seat();
 * obj->leave(p);
 */
```

#### Go

```go
type ExamRoom struct {
	rbt   *redblacktree.Tree
	left  map[int]int
	right map[int]int
	n     int
}

func Constructor(n int) ExamRoom {
	dist := func(s []int) int {
		if s[0] == -1 || s[1] == n {
			return s[1] - s[0] - 1
		}
		return (s[1] - s[0]) >> 1
	}
	cmp := func(a, b any) int {
		x, y := a.([]int), b.([]int)
		d1, d2 := dist(x), dist(y)
		if d1 == d2 {
			return x[0] - y[0]
		}
		return d2 - d1
	}
	this := ExamRoom{redblacktree.NewWith(cmp), map[int]int{}, map[int]int{}, n}
	this.add([]int{-1, n})
	return this
}

func (this *ExamRoom) Seat() int {
	s := this.rbt.Left().Key.([]int)
	p := (s[0] + s[1]) >> 1
	if s[0] == -1 {
		p = 0
	} else if s[1] == this.n {
		p = this.n - 1
	}
	this.del(s)
	this.add([]int{s[0], p})
	this.add([]int{p, s[1]})
	return p
}

func (this *ExamRoom) Leave(p int) {
	l, _ := this.left[p]
	r, _ := this.right[p]
	this.del([]int{l, p})
	this.del([]int{p, r})
	this.add([]int{l, r})
}

func (this *ExamRoom) add(s []int) {
	this.rbt.Put(s, struct{}{})
	this.left[s[1]] = s[0]
	this.right[s[0]] = s[1]
}

func (this *ExamRoom) del(s []int) {
	this.rbt.Remove(s)
	delete(this.left, s[1])
	delete(this.right, s[0])
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * obj := Constructor(n);
 * param_1 := obj.Seat();
 * obj.Leave(p);
 */
```

#### TypeScript

```ts
class ExamRoom {
    private ts: TreeSet<number[]> = new TreeSet<number[]>((a, b) => {
        const d1 = this.dist(a),
            d2 = this.dist(b);
        return d1 === d2 ? a[0] - b[0] : d2 - d1;
    });
    private left: Map<number, number> = new Map();
    private right: Map<number, number> = new Map();
    private n: number;

    constructor(n: number) {
        this.n = n;
        this.add([-1, n]);
    }

    seat(): number {
        const s = this.ts.first();
        let p = Math.floor((s[0] + s[1]) / 2);
        if (s[0] === -1) {
            p = 0;
        } else if (s[1] === this.n) {
            p = this.n - 1;
        }
        this.del(s);
        this.add([s[0], p]);
        this.add([p, s[1]]);
        return p;
    }

    leave(p: number): void {
        const l = this.left.get(p)!;
        const r = this.right.get(p)!;
        this.del([l, p]);
        this.del([p, r]);
        this.add([l, r]);
    }

    private dist(s: number[]): number {
        const [l, r] = s;
        return l === -1 || r === this.n ? r - l - 1 : Math.floor((r - l) / 2);
    }

    private add(s: number[]): void {
        this.ts.add(s);
        this.left.set(s[1], s[0]);
        this.right.set(s[0], s[1]);
    }

    private del(s: number[]): void {
        this.ts.delete(s);
        this.left.delete(s[1]);
        this.right.delete(s[0]);
    }
}

type Compare<T> = (lhs: T, rhs: T) => number;

class RBTreeNode<T = number> {
    data: T;
    count: number;
    left: RBTreeNode<T> | null;
    right: RBTreeNode<T> | null;
    parent: RBTreeNode<T> | null;
    color: number;
    constructor(data: T) {
        this.data = data;
        this.left = this.right = this.parent = null;
        this.color = 0;
        this.count = 1;
    }

    sibling(): RBTreeNode<T> | null {
        if (!this.parent) return null; // sibling null if no parent
        return this.isOnLeft() ? this.parent.right : this.parent.left;
    }

    isOnLeft(): boolean {
        return this === this.parent!.left;
    }

    hasRedChild(): boolean {
        return (
            Boolean(this.left && this.left.color === 0) ||
            Boolean(this.right && this.right.color === 0)
        );
    }
}

class RBTree<T> {
    root: RBTreeNode<T> | null;
    lt: (l: T, r: T) => boolean;
    constructor(compare: Compare<T> = (l: T, r: T) => (l < r ? -1 : l > r ? 1 : 0)) {
        this.root = null;
        this.lt = (l: T, r: T) => compare(l, r) < 0;
    }

    rotateLeft(pt: RBTreeNode<T>): void {
        const right = pt.right!;
        pt.right = right.left;

        if (pt.right) pt.right.parent = pt;
        right.parent = pt.parent;

        if (!pt.parent) this.root = right;
        else if (pt === pt.parent.left) pt.parent.left = right;
        else pt.parent.right = right;

        right.left = pt;
        pt.parent = right;
    }

    rotateRight(pt: RBTreeNode<T>): void {
        const left = pt.left!;
        pt.left = left.right;

        if (pt.left) pt.left.parent = pt;
        left.parent = pt.parent;

        if (!pt.parent) this.root = left;
        else if (pt === pt.parent.left) pt.parent.left = left;
        else pt.parent.right = left;

        left.right = pt;
        pt.parent = left;
    }

    swapColor(p1: RBTreeNode<T>, p2: RBTreeNode<T>): void {
        const tmp = p1.color;
        p1.color = p2.color;
        p2.color = tmp;
    }

    swapData(p1: RBTreeNode<T>, p2: RBTreeNode<T>): void {
        const tmp = p1.data;
        p1.data = p2.data;
        p2.data = tmp;
    }

    fixAfterInsert(pt: RBTreeNode<T>): void {
        let parent = null;
        let grandParent = null;

        while (pt !== this.root && pt.color !== 1 && pt.parent?.color === 0) {
            parent = pt.parent;
            grandParent = pt.parent.parent;

            /*  Case : A
                Parent of pt is left child of Grand-parent of pt */
            if (parent === grandParent?.left) {
                const uncle = grandParent.right;

                /* Case : 1
                   The uncle of pt is also red
                   Only Recoloring required */
                if (uncle && uncle.color === 0) {
                    grandParent.color = 0;
                    parent.color = 1;
                    uncle.color = 1;
                    pt = grandParent;
                } else {
                    /* Case : 2
                       pt is right child of its parent
                       Left-rotation required */
                    if (pt === parent.right) {
                        this.rotateLeft(parent);
                        pt = parent;
                        parent = pt.parent;
                    }

                    /* Case : 3
                       pt is left child of its parent
                       Right-rotation required */
                    this.rotateRight(grandParent);
                    this.swapColor(parent!, grandParent);
                    pt = parent!;
                }
            } else {
                /* Case : B
               Parent of pt is right child of Grand-parent of pt */
                const uncle = grandParent!.left;

                /*  Case : 1
                    The uncle of pt is also red
                    Only Recoloring required */
                if (uncle != null && uncle.color === 0) {
                    grandParent!.color = 0;
                    parent.color = 1;
                    uncle.color = 1;
                    pt = grandParent!;
                } else {
                    /* Case : 2
                       pt is left child of its parent
                       Right-rotation required */
                    if (pt === parent.left) {
                        this.rotateRight(parent);
                        pt = parent;
                        parent = pt.parent;
                    }

                    /* Case : 3
                       pt is right child of its parent
                       Left-rotation required */
                    this.rotateLeft(grandParent!);
                    this.swapColor(parent!, grandParent!);
                    pt = parent!;
                }
            }
        }
        this.root!.color = 1;
    }

    delete(val: T): boolean {
        const node = this.find(val);
        if (!node) return false;
        node.count--;
        if (!node.count) this.deleteNode(node);
        return true;
    }

    deleteAll(val: T): boolean {
        const node = this.find(val);
        if (!node) return false;
        this.deleteNode(node);
        return true;
    }

    deleteNode(v: RBTreeNode<T>): void {
        const u = BSTreplace(v);

        // True when u and v are both black
        const uvBlack = (u === null || u.color === 1) && v.color === 1;
        const parent = v.parent!;

        if (!u) {
            // u is null therefore v is leaf
            if (v === this.root) this.root = null;
            // v is root, making root null
            else {
                if (uvBlack) {
                    // u and v both black
                    // v is leaf, fix double black at v
                    this.fixDoubleBlack(v);
                } else {
                    // u or v is red
                    if (v.sibling()) {
                        // sibling is not null, make it red"
                        v.sibling()!.color = 0;
                    }
                }
                // delete v from the tree
                if (v.isOnLeft()) parent.left = null;
                else parent.right = null;
            }
            return;
        }

        if (!v.left || !v.right) {
            // v has 1 child
            if (v === this.root) {
                // v is root, assign the value of u to v, and delete u
                v.data = u.data;
                v.left = v.right = null;
            } else {
                // Detach v from tree and move u up
                if (v.isOnLeft()) parent.left = u;
                else parent.right = u;
                u.parent = parent;
                if (uvBlack) this.fixDoubleBlack(u);
                // u and v both black, fix double black at u
                else u.color = 1; // u or v red, color u black
            }
            return;
        }

        // v has 2 children, swap data with successor and recurse
        this.swapData(u, v);
        this.deleteNode(u);

        // find node that replaces a deleted node in BST
        function BSTreplace(x: RBTreeNode<T>): RBTreeNode<T> | null {
            // when node have 2 children
            if (x.left && x.right) return successor(x.right);
            // when leaf
            if (!x.left && !x.right) return null;
            // when single child
            return x.left ?? x.right;
        }
        // find node that do not have a left child
        // in the subtree of the given node
        function successor(x: RBTreeNode<T>): RBTreeNode<T> {
            let temp = x;
            while (temp.left) temp = temp.left;
            return temp;
        }
    }

    fixDoubleBlack(x: RBTreeNode<T>): void {
        if (x === this.root) return; // Reached root

        const sibling = x.sibling();
        const parent = x.parent!;
        if (!sibling) {
            // No sibiling, double black pushed up
            this.fixDoubleBlack(parent);
        } else {
            if (sibling.color === 0) {
                // Sibling red
                parent.color = 0;
                sibling.color = 1;
                if (sibling.isOnLeft()) this.rotateRight(parent);
                // left case
                else this.rotateLeft(parent); // right case
                this.fixDoubleBlack(x);
            } else {
                // Sibling black
                if (sibling.hasRedChild()) {
                    // at least 1 red children
                    if (sibling.left && sibling.left.color === 0) {
                        if (sibling.isOnLeft()) {
                            // left left
                            sibling.left.color = sibling.color;
                            sibling.color = parent.color;
                            this.rotateRight(parent);
                        } else {
                            // right left
                            sibling.left.color = parent.color;
                            this.rotateRight(sibling);
                            this.rotateLeft(parent);
                        }
                    } else {
                        if (sibling.isOnLeft()) {
                            // left right
                            sibling.right!.color = parent.color;
                            this.rotateLeft(sibling);
                            this.rotateRight(parent);
                        } else {
                            // right right
                            sibling.right!.color = sibling.color;
                            sibling.color = parent.color;
                            this.rotateLeft(parent);
                        }
                    }
                    parent.color = 1;
                } else {
                    // 2 black children
                    sibling.color = 0;
                    if (parent.color === 1) this.fixDoubleBlack(parent);
                    else parent.color = 1;
                }
            }
        }
    }

    insert(data: T): boolean {
        // search for a position to insert
        let parent = this.root;
        while (parent) {
            if (this.lt(data, parent.data)) {
                if (!parent.left) break;
                else parent = parent.left;
            } else if (this.lt(parent.data, data)) {
                if (!parent.right) break;
                else parent = parent.right;
            } else break;
        }

        // insert node into parent
        const node = new RBTreeNode(data);
        if (!parent) this.root = node;
        else if (this.lt(node.data, parent.data)) parent.left = node;
        else if (this.lt(parent.data, node.data)) parent.right = node;
        else {
            parent.count++;
            return false;
        }
        node.parent = parent;
        this.fixAfterInsert(node);
        return true;
    }

    find(data: T): RBTreeNode<T> | null {
        let p = this.root;
        while (p) {
            if (this.lt(data, p.data)) {
                p = p.left;
            } else if (this.lt(p.data, data)) {
                p = p.right;
            } else break;
        }
        return p ?? null;
    }

    *inOrder(root: RBTreeNode<T> = this.root!): Generator<T, undefined, void> {
        if (!root) return;
        for (const v of this.inOrder(root.left!)) yield v;
        yield root.data;
        for (const v of this.inOrder(root.right!)) yield v;
    }

    *reverseInOrder(root: RBTreeNode<T> = this.root!): Generator<T, undefined, void> {
        if (!root) return;
        for (const v of this.reverseInOrder(root.right!)) yield v;
        yield root.data;
        for (const v of this.reverseInOrder(root.left!)) yield v;
    }
}

class TreeSet<T = number> {
    _size: number;
    tree: RBTree<T>;
    compare: Compare<T>;
    constructor(
        collection: T[] | Compare<T> = [],
        compare: Compare<T> = (l: T, r: T) => (l < r ? -1 : l > r ? 1 : 0),
    ) {
        if (typeof collection === 'function') {
            compare = collection;
            collection = [];
        }
        this._size = 0;
        this.compare = compare;
        this.tree = new RBTree(compare);
        for (const val of collection) this.add(val);
    }

    size(): number {
        return this._size;
    }

    has(val: T): boolean {
        return !!this.tree.find(val);
    }

    add(val: T): boolean {
        const successful = this.tree.insert(val);
        this._size += successful ? 1 : 0;
        return successful;
    }

    delete(val: T): boolean {
        const deleted = this.tree.deleteAll(val);
        this._size -= deleted ? 1 : 0;
        return deleted;
    }

    ceil(val: T): T | undefined {
        let p = this.tree.root;
        let higher = null;
        while (p) {
            if (this.compare(p.data, val) >= 0) {
                higher = p;
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return higher?.data;
    }

    floor(val: T): T | undefined {
        let p = this.tree.root;
        let lower = null;
        while (p) {
            if (this.compare(val, p.data) >= 0) {
                lower = p;
                p = p.right;
            } else {
                p = p.left;
            }
        }
        return lower?.data;
    }

    higher(val: T): T | undefined {
        let p = this.tree.root;
        let higher = null;
        while (p) {
            if (this.compare(val, p.data) < 0) {
                higher = p;
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return higher?.data;
    }

    lower(val: T): T | undefined {
        let p = this.tree.root;
        let lower = null;
        while (p) {
            if (this.compare(p.data, val) < 0) {
                lower = p;
                p = p.right;
            } else {
                p = p.left;
            }
        }
        return lower?.data;
    }

    first(): T | undefined {
        return this.tree.inOrder().next().value;
    }

    last(): T | undefined {
        return this.tree.reverseInOrder().next().value;
    }

    shift(): T | undefined {
        const first = this.first();
        if (first === undefined) return undefined;
        this.delete(first);
        return first;
    }

    pop(): T | undefined {
        const last = this.last();
        if (last === undefined) return undefined;
        this.delete(last);
        return last;
    }

    *[Symbol.iterator](): Generator<T, void, void> {
        for (const val of this.values()) yield val;
    }

    *keys(): Generator<T, void, void> {
        for (const val of this.values()) yield val;
    }

    *values(): Generator<T, undefined, void> {
        for (const val of this.tree.inOrder()) yield val;
        return undefined;
    }

    /**
     * Return a generator for reverse order traversing the set
     */
    *rvalues(): Generator<T, undefined, void> {
        for (const val of this.tree.reverseInOrder()) yield val;
        return undefined;
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * var obj = new ExamRoom(n)
 * var param_1 = obj.seat()
 * obj.leave(p)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
