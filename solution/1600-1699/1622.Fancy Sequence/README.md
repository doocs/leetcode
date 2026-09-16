---
comments: true
difficulty: 困难
rating: 2476
source: 第 37 场双周赛 Q4
tags:
    - 设计
    - 线段树
    - 数学
    - 数论
---

<!-- problem:start -->

# [1622. 奇妙序列](https://leetcode.cn/problems/fancy-sequence)

[English Version](/solution/1600-1699/1622.Fancy%20Sequence/README_EN.md)

## 题目描述

<!-- description:start -->

<p>请你实现三个 API <code>append</code>，<code>addAll</code> 和 <code>multAll</code> 来实现奇妙序列。</p>

<p>请实现 <code>Fancy</code> 类 ：</p>

<ul>
	<li><code>Fancy()</code> 初始化一个空序列对象。</li>
	<li><code>void append(val)</code> 将整数 <code>val</code> 添加在序列末尾。</li>
	<li><code>void addAll(inc)</code> 将所有序列中的现有数值都增加 <code>inc</code> 。</li>
	<li><code>void multAll(m)</code> 将序列中的所有现有数值都乘以整数 <code>m</code> 。</li>
	<li><code>int getIndex(idx)</code> 得到下标为 <code>idx</code> 处的数值（下标从 0 开始），并将结果对 <code>10<sup>9</sup> + 7</code> 取余。如果下标大于等于序列的长度，请返回 <code>-1</code> 。</li>
</ul>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["Fancy", "append", "addAll", "append", "multAll", "getIndex", "addAll", "append", "multAll", "getIndex", "getIndex", "getIndex"]
[[], [2], [3], [7], [2], [0], [3], [10], [2], [0], [1], [2]]
<strong>输出：</strong>
[null, null, null, null, null, 10, null, null, null, 26, 34, 20]

<strong>解释：</strong>
Fancy fancy = new Fancy();
fancy.append(2);   // 奇妙序列：[2]
fancy.addAll(3);   // 奇妙序列：[2+3] -> [5]
fancy.append(7);   // 奇妙序列：[5, 7]
fancy.multAll(2);  // 奇妙序列：[5*2, 7*2] -> [10, 14]
fancy.getIndex(0); // 返回 10
fancy.addAll(3);   // 奇妙序列：[10+3, 14+3] -> [13, 17]
fancy.append(10);  // 奇妙序列：[13, 17, 10]
fancy.multAll(2);  // 奇妙序列：[13*2, 17*2, 10*2] -> [26, 34, 20]
fancy.getIndex(0); // 返回 26
fancy.getIndex(1); // 返回 34
fancy.getIndex(2); // 返回 20
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= val, inc, m <= 100</code></li>
	<li><code>0 <= idx <= 10<sup>5</sup></code></li>
	<li>总共最多会有 <code>10<sup>5</sup></code> 次对 <code>append</code>，<code>addAll</code>，<code>multAll</code> 和 <code>getIndex</code> 的调用。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：线段树

<!-- thinking:start -->

> **思考**
>
> 题目要在序列末尾不断加入新数，还要对当前所有数一起做加法或乘法，再查某一个位置。操作最多 $10^5$ 次，每次把整个序列扫一遍会超时。
>
> 加法和乘法都作用在「已经加入的那一段」上，查询又只问一个点，可以用线段树来做区间修改。每个结点记下这段的和，以及还没传给子结点的乘法和加法；真正往下走时再传下去。
>
> 序列最长 $10^5$，按 $[1,10^5]$ 动态开点即可。`append` 改一个点，`addAll` 和 `multAll` 改当前前缀，`getIndex` 查一个点，答案对 $10^9+7$ 取模。

<!-- thinking:end -->

根据题目描述，`append` 在序列末尾加入一个数，`addAll` 给当前所有数加上同一个值，`multAll` 给当前所有数乘上同一个值，`getIndex` 查询某个位置的数。这就是区间加、区间乘和单点查询，可以用线段树维护。

我们给每个结点记录以下信息：

- `v`：这个区间里所有数的和；
- `mul`：还没下传给子结点的乘法，初始为 $1$；
- `add`：还没下传给子结点的加法，初始为 $0$。

这两个标记的含义是：区间里每个数都要先乘上 `mul`，再加上 `add`。如果同一段上先后做了两次修改，不必真的改到叶子，可以把标记合并成一次。设第一次是「乘 $m_1$ 再加 $a_1$」，第二次是「乘 $m_2$ 再加 $a_2$」，那么：

$$
(x \cdot m_1 + a_1)\cdot m_2 + a_2 = x\cdot (m_1 m_2) + (a_1 m_2 + a_2)
$$

也就是新的乘法是 $m_1 m_2$，新的加法是 $a_1 m_2 + a_2$。

因此，给一段乘上 $m$ 时，结点的 `v`、`mul`、`add` 都乘上 $m$；给一段加上 $inc$ 时，`v` 加上「区间长度 $\times inc$」，`add` 加上 $inc$。下传给左右儿子时，按同样的规则先乘后加。代码里把加和乘写成同一次操作：只加相当于「乘 $1$ 再加 $inc$」，只乘相当于「乘 $m$ 再加 $0$」。

下标从 $1$ 开始，序列长度不超过 $10^5$，我们按 $[1,10^5]$ 动态开点（用到某个结点时再创建）。`append` 时先让 $n$ 加一，再在位置 $n$ 上加上 $val$；`addAll` 和 `multAll` 修改区间 $[1,n]$；`getIndex` 查询位置 $idx+1$。所有运算对 $10^9+7$ 取模。

时间复杂度 $O(m \log n)$，空间复杂度 $O(m \log n)$。其中 $m$ 为操作次数，$n$ 为序列长度的上界 $10^5$。

<!-- tabs:start -->

#### Python3

```python
MOD = 10**9 + 7


class Node:
    __slots__ = "left", "right", "l", "r", "mid", "v", "add", "mul"

    def __init__(self, l, r):
        self.left = self.right = None
        self.l, self.r = l, r
        self.mid = (l + r) >> 1
        self.v = self.add = 0
        self.mul = 1


class SegmentTree:
    def __init__(self):
        self.root = Node(1, 10**5 + 1)

    def modify(self, l, r, mul, add, node=None):
        if l > r:
            return
        if node is None:
            node = self.root
        if node.l >= l and node.r <= r:
            self.apply(node, mul, add)
            return
        self.pushdown(node)
        if l <= node.mid:
            self.modify(l, r, mul, add, node.left)
        if r > node.mid:
            self.modify(l, r, mul, add, node.right)
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

    def apply(self, node, mul, add):
        node.v = (node.v * mul + (node.r - node.l + 1) * add) % MOD
        node.add = (node.add * mul + add) % MOD
        node.mul = node.mul * mul % MOD

    def pushup(self, node):
        node.v = (node.left.v + node.right.v) % MOD

    def pushdown(self, node):
        if node.left is None:
            node.left = Node(node.l, node.mid)
        if node.right is None:
            node.right = Node(node.mid + 1, node.r)
        if node.add or node.mul != 1:
            self.apply(node.left, node.mul, node.add)
            self.apply(node.right, node.mul, node.add)
            node.add = 0
            node.mul = 1


class Fancy:
    def __init__(self):
        self.n = 0
        self.tree = SegmentTree()

    def append(self, val: int) -> None:
        self.n += 1
        self.tree.modify(self.n, self.n, 1, val)

    def addAll(self, inc: int) -> None:
        self.tree.modify(1, self.n, 1, inc)

    def multAll(self, m: int) -> None:
        self.tree.modify(1, self.n, m, 0)

    def getIndex(self, idx: int) -> int:
        return -1 if idx >= self.n else self.tree.query(idx + 1, idx + 1)
```

#### Java

```java
class Node {
    Node left;
    Node right;
    int l, r, mid;
    long v, add, mul = 1;

    Node(int l, int r) {
        this.l = l;
        this.r = r;
        this.mid = (l + r) >> 1;
    }
}

class SegmentTree {
    private static final int MOD = (int) 1e9 + 7;
    private Node root = new Node(1, (int) 1e5 + 1);

    void modify(int l, int r, int mul, int add) {
        modify(l, r, mul, add, root);
    }

    void modify(int l, int r, int mul, int add, Node node) {
        if (l > r) {
            return;
        }
        if (node.l >= l && node.r <= r) {
            apply(node, mul, add);
            return;
        }
        pushdown(node);
        if (l <= node.mid) {
            modify(l, r, mul, add, node.left);
        }
        if (r > node.mid) {
            modify(l, r, mul, add, node.right);
        }
        pushup(node);
    }

    int query(int l, int r) {
        return query(l, r, root);
    }

    int query(int l, int r, Node node) {
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

    void apply(Node node, long mul, long add) {
        node.v = (node.v * mul + (node.r - node.l + 1) * add) % MOD;
        node.add = (node.add * mul + add) % MOD;
        node.mul = node.mul * mul % MOD;
    }

    void pushup(Node node) {
        node.v = (node.left.v + node.right.v) % MOD;
    }

    void pushdown(Node node) {
        if (node.left == null) {
            node.left = new Node(node.l, node.mid);
        }
        if (node.right == null) {
            node.right = new Node(node.mid + 1, node.r);
        }
        if (node.add != 0 || node.mul != 1) {
            apply(node.left, node.mul, node.add);
            apply(node.right, node.mul, node.add);
            node.add = 0;
            node.mul = 1;
        }
    }
}

class Fancy {
    private int n;
    private SegmentTree tree = new SegmentTree();

    public void append(int val) {
        ++n;
        tree.modify(n, n, 1, val);
    }

    public void addAll(int inc) {
        tree.modify(1, n, 1, inc);
    }

    public void multAll(int m) {
        tree.modify(1, n, m, 0);
    }

    public int getIndex(int idx) {
        return idx >= n ? -1 : tree.query(idx + 1, idx + 1);
    }
}
```

#### C++

```cpp
const int MOD = 1e9 + 7;

class Node {
public:
    Node* left = nullptr;
    Node* right = nullptr;
    int l, r, mid;
    long long v = 0, add = 0, mul = 1;

    Node(int l, int r)
        : l(l)
        , r(r)
        , mid((l + r) >> 1) {}
};

class SegmentTree {
public:
    SegmentTree()
        : root(new Node(1, 1e5 + 1)) {}

    void modify(int l, int r, int mul, int add) {
        modify(l, r, mul, add, root);
    }

    int query(int l, int r) {
        return query(l, r, root);
    }

private:
    Node* root;

    void modify(int l, int r, int mul, int add, Node* node) {
        if (l > r) {
            return;
        }
        if (node->l >= l && node->r <= r) {
            apply(node, mul, add);
            return;
        }
        pushdown(node);
        if (l <= node->mid) {
            modify(l, r, mul, add, node->left);
        }
        if (r > node->mid) {
            modify(l, r, mul, add, node->right);
        }
        pushup(node);
    }

    int query(int l, int r, Node* node) {
        if (l > r) {
            return 0;
        }
        if (node->l >= l && node->r <= r) {
            return node->v;
        }
        pushdown(node);
        int v = 0;
        if (l <= node->mid) {
            v = (v + query(l, r, node->left)) % MOD;
        }
        if (r > node->mid) {
            v = (v + query(l, r, node->right)) % MOD;
        }
        return v;
    }

    void apply(Node* node, long long mul, long long add) {
        node->v = (node->v * mul + (node->r - node->l + 1) * add) % MOD;
        node->add = (node->add * mul + add) % MOD;
        node->mul = node->mul * mul % MOD;
    }

    void pushup(Node* node) {
        node->v = (node->left->v + node->right->v) % MOD;
    }

    void pushdown(Node* node) {
        if (!node->left) {
            node->left = new Node(node->l, node->mid);
        }
        if (!node->right) {
            node->right = new Node(node->mid + 1, node->r);
        }
        if (node->add || node->mul != 1) {
            apply(node->left, node->mul, node->add);
            apply(node->right, node->mul, node->add);
            node->add = 0;
            node->mul = 1;
        }
    }
};

class Fancy {
public:
    void append(int val) {
        ++n;
        tree.modify(n, n, 1, val);
    }

    void addAll(int inc) {
        tree.modify(1, n, 1, inc);
    }

    void multAll(int m) {
        tree.modify(1, n, m, 0);
    }

    int getIndex(int idx) {
        return idx >= n ? -1 : tree.query(idx + 1, idx + 1);
    }

private:
    int n = 0;
    SegmentTree tree;
};
```

#### Go

```go
const mod int64 = 1e9 + 7

type node struct {
	left, right *node
	l, r, mid   int
	v, add, mul int64
}

func newNode(l, r int) *node {
	return &node{l: l, r: r, mid: (l + r) >> 1, mul: 1}
}

type segmentTree struct{ root *node }

func newSegmentTree() *segmentTree {
	return &segmentTree{root: newNode(1, 100001)}
}

func (t *segmentTree) modify(l, r int, mul, add int64, o *node) {
	if l > r {
		return
	}
	if o.l >= l && o.r <= r {
		t.apply(o, mul, add)
		return
	}
	t.pushdown(o)
	if l <= o.mid {
		t.modify(l, r, mul, add, o.left)
	}
	if r > o.mid {
		t.modify(l, r, mul, add, o.right)
	}
	t.pushup(o)
}

func (t *segmentTree) query(l, r int, o *node) int64 {
	if l > r {
		return 0
	}
	if o.l >= l && o.r <= r {
		return o.v
	}
	t.pushdown(o)
	var v int64
	if l <= o.mid {
		v = (v + t.query(l, r, o.left)) % mod
	}
	if r > o.mid {
		v = (v + t.query(l, r, o.right)) % mod
	}
	return v
}

func (t *segmentTree) apply(o *node, mul, add int64) {
	o.v = (o.v*mul + int64(o.r-o.l+1)*add) % mod
	o.add = (o.add*mul + add) % mod
	o.mul = o.mul * mul % mod
}

func (t *segmentTree) pushup(o *node) {
	o.v = (o.left.v + o.right.v) % mod
}

func (t *segmentTree) pushdown(o *node) {
	if o.left == nil {
		o.left = newNode(o.l, o.mid)
	}
	if o.right == nil {
		o.right = newNode(o.mid+1, o.r)
	}
	if o.add != 0 || o.mul != 1 {
		t.apply(o.left, o.mul, o.add)
		t.apply(o.right, o.mul, o.add)
		o.add, o.mul = 0, 1
	}
}

type Fancy struct {
	n    int
	tree *segmentTree
}

func Constructor() Fancy {
	return Fancy{tree: newSegmentTree()}
}

func (f *Fancy) Append(val int) {
	f.n++
	f.tree.modify(f.n, f.n, 1, int64(val), f.tree.root)
}

func (f *Fancy) AddAll(inc int) {
	f.tree.modify(1, f.n, 1, int64(inc), f.tree.root)
}

func (f *Fancy) MultAll(m int) {
	f.tree.modify(1, f.n, int64(m), 0, f.tree.root)
}

func (f *Fancy) GetIndex(idx int) int {
	if idx >= f.n {
		return -1
	}
	return int(f.tree.query(idx+1, idx+1, f.tree.root))
}
```

#### TypeScript

```ts
const mod = BigInt(1e9 + 7);

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
    root = new Node(1, 1e5 + 1);

    modify(l: number, r: number, mul: bigint, add: bigint, node = this.root): void {
        if (l > r) {
            return;
        }
        if (node.l >= l && node.r <= r) {
            this.apply(node, mul, add);
            return;
        }
        this.pushdown(node);
        if (l <= node.mid) {
            this.modify(l, r, mul, add, node.left!);
        }
        if (r > node.mid) {
            this.modify(l, r, mul, add, node.right!);
        }
        this.pushup(node);
    }

    query(l: number, r: number, node = this.root): bigint {
        if (l > r) {
            return 0n;
        }
        if (node.l >= l && node.r <= r) {
            return node.v;
        }
        this.pushdown(node);
        let v = 0n;
        if (l <= node.mid) {
            v = (v + this.query(l, r, node.left!)) % mod;
        }
        if (r > node.mid) {
            v = (v + this.query(l, r, node.right!)) % mod;
        }
        return v;
    }

    apply(node: Node, mul: bigint, add: bigint): void {
        node.v = (node.v * mul + BigInt(node.r - node.l + 1) * add) % mod;
        node.add = (node.add * mul + add) % mod;
        node.mul = (node.mul * mul) % mod;
    }

    pushup(node: Node): void {
        node.v = (node.left!.v + node.right!.v) % mod;
    }

    pushdown(node: Node): void {
        node.left ??= new Node(node.l, node.mid);
        node.right ??= new Node(node.mid + 1, node.r);
        if (node.add !== 0n || node.mul !== 1n) {
            this.apply(node.left, node.mul, node.add);
            this.apply(node.right, node.mul, node.add);
            node.add = 0n;
            node.mul = 1n;
        }
    }
}

class Fancy {
    private n = 0;
    private tree = new SegmentTree();

    append(val: number): void {
        this.n++;
        this.tree.modify(this.n, this.n, 1n, BigInt(val));
    }

    addAll(inc: number): void {
        this.tree.modify(1, this.n, 1n, BigInt(inc));
    }

    multAll(m: number): void {
        this.tree.modify(1, this.n, BigInt(m), 0n);
    }

    getIndex(idx: number): number {
        return idx >= this.n ? -1 : Number(this.tree.query(idx + 1, idx + 1));
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：数学 + 乘法逆元

<!-- thinking:start -->

> **思考**
>
> 方法一每次修改还要在线段树上走一层，复杂度是 $O(\log n)$。再看操作会发现：`addAll` 和 `multAll` 永远作用在「当前已经有的全部数」上，新 `append` 进来的数并不会被之前的加、乘改到。
>
> 也就是说，已经在序列里的数，之后经历的加和乘完全一样。我们不必给每个下标单独记一棵树上的标记，只要用两个全局变量记下「这些数接下来都要乘多少、再加多少」。数组里存的是还没做这些运算之前的值，查询时再乘上、加上即可。模数是质数，除法用费马小定理换成乘逆元。

<!-- thinking:end -->

观察题目操作可以发现：每一次 `addAll` 和 `multAll` 都会作用到当时已经存在的所有数上，而之后新加入的数不受之前操作的影响。因此，对当前序列里的每个数，尚未作用到它身上的加、乘可以用两个全局变量来描述：先乘上 $a$，再加上 $b$。初始时 $a=1$，$b=0$。

我们在数组 `nums` 里存放的不是当前真实值，而是「还没乘 $a$、加上 $b$ 之前」的值，使得任意时刻都有：

$$
\text{真实值} = (a \times \textit{nums}[i] + b) \bmod (10^9+7)
$$

各操作对应如下：

- `append(val)`：这个新数还没有经历当前的 $a$ 和 $b$，所以要存一个 $x$，使得 $a \times x + b = \textit{val}$，即 $x = (\textit{val} - b) \times a^{-1}$；
- `addAll(inc)`：所有真实值都加上 $inc$，只需把 $b$ 加上 $inc$；
- `multAll(m)`：所有真实值都乘上 $m$，于是 $a$ 和 $b$ 都乘上 $m$；
- `getIndex(idx)`：下标越界返回 $-1$，否则返回 $a \times \textit{nums}[idx] + b$。

其中 $a^{-1}$ 表示 $a$ 在模 $10^9+7$ 下的乘法逆元。模数是质数，由费马小定理有 $a^{-1} \equiv a^{MOD-2} \pmod{MOD}$。

除 `append` 中求逆元需要 $O(\log MOD)$ 外，其余操作的时间复杂度均为 $O(1)$。空间复杂度 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
class Fancy:
    def __init__(self):
        self.mod = 10**9 + 7
        self.nums = []
        self.a = 1
        self.b = 0

    def append(self, val: int) -> None:
        x = (val - self.b) * pow(self.a, self.mod - 2, self.mod) % self.mod
        self.nums.append(x)

    def addAll(self, inc: int) -> None:
        self.b = (self.b + inc) % self.mod

    def multAll(self, m: int) -> None:
        self.a = self.a * m % self.mod
        self.b = self.b * m % self.mod

    def getIndex(self, idx: int) -> int:
        if idx >= len(self.nums):
            return -1
        return (self.a * self.nums[idx] + self.b) % self.mod
```

#### Java

```java
class Fancy {
    private static final int MOD = (int) 1e9 + 7;
    private List<Integer> nums = new ArrayList<>();
    private long a = 1, b;

    public void append(int val) {
        long x = (val - b + MOD) % MOD * qpow(a, MOD - 2) % MOD;
        nums.add((int) x);
    }

    public void addAll(int inc) {
        b = (b + inc) % MOD;
    }

    public void multAll(int m) {
        a = a * m % MOD;
        b = b * m % MOD;
    }

    public int getIndex(int idx) {
        if (idx >= nums.size()) {
            return -1;
        }
        return (int) ((a * nums.get(idx) + b) % MOD);
    }

    private long qpow(long x, int n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
            n >>= 1;
        }
        return res;
    }
}
```

#### C++

```cpp
class Fancy {
public:
    void append(int val) {
        long long x = (val - b + mod) % mod * qpow(a, mod - 2) % mod;
        nums.push_back(x);
    }

    void addAll(int inc) {
        b = (b + inc) % mod;
    }

    void multAll(int m) {
        a = a * m % mod;
        b = b * m % mod;
    }

    int getIndex(int idx) {
        if (idx >= nums.size()) {
            return -1;
        }
        return (a * nums[idx] + b) % mod;
    }

private:
    const int mod = 1e9 + 7;
    vector<long long> nums;
    long long a = 1, b = 0;

    long long qpow(long long x, int n) {
        long long res = 1;
        while (n) {
            if (n & 1) {
                res = res * x % mod;
            }
            x = x * x % mod;
            n >>= 1;
        }
        return res;
    }
};
```

#### Go

```go
const mod int = 1e9 + 7

func qpow(x, n int) int {
	res := 1
	for n > 0 {
		if n&1 == 1 {
			res = res * x % mod
		}
		x = x * x % mod
		n >>= 1
	}
	return res
}

type Fancy struct {
	nums []int
	a, b int
}

func Constructor() Fancy {
	return Fancy{a: 1}
}

func (f *Fancy) Append(val int) {
	x := (val - f.b + mod) % mod * qpow(f.a, mod-2) % mod
	f.nums = append(f.nums, x)
}

func (f *Fancy) AddAll(inc int) {
	f.b = (f.b + inc) % mod
}

func (f *Fancy) MultAll(m int) {
	f.a = f.a * m % mod
	f.b = f.b * m % mod
}

func (f *Fancy) GetIndex(idx int) int {
	if idx >= len(f.nums) {
		return -1
	}
	return (f.a*f.nums[idx] + f.b) % mod
}
```

#### TypeScript

```ts
class Fancy {
    private mod = BigInt(1e9 + 7);
    private nums: bigint[] = [];
    private a = 1n;
    private b = 0n;

    append(val: number): void {
        const x = (((BigInt(val) - this.b) % this.mod) + this.mod) % this.mod;
        this.nums.push((x * this.qpow(this.a, 1e9 + 5)) % this.mod);
    }

    addAll(inc: number): void {
        this.b = (this.b + BigInt(inc)) % this.mod;
    }

    multAll(m: number): void {
        this.a = (this.a * BigInt(m)) % this.mod;
        this.b = (this.b * BigInt(m)) % this.mod;
    }

    getIndex(idx: number): number {
        if (idx >= this.nums.length) {
            return -1;
        }
        return Number((this.a * this.nums[idx] + this.b) % this.mod);
    }

    private qpow(x: bigint, n: number): bigint {
        let res = 1n;
        while (n) {
            if (n & 1) {
                res = (res * x) % this.mod;
            }
            x = (x * x) % this.mod;
            n >>= 1;
        }
        return res;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
