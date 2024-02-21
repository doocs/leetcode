# [307. Range Sum Query - Mutable](https://leetcode.com/problems/range-sum-query-mutable)

[中文文档](/solution/0300-0399/0307.Range%20Sum%20Query%20-%20Mutable/README.md)

<!-- tags:Design,Binary Indexed Tree,Segment Tree,Array -->

## Description

<p>Given an integer array <code>nums</code>, handle multiple queries of the following types:</p>

<ol>
	<li><strong>Update</strong> the value of an element in <code>nums</code>.</li>
	<li>Calculate the <strong>sum</strong> of the elements of <code>nums</code> between indices <code>left</code> and <code>right</code> <strong>inclusive</strong> where <code>left &lt;= right</code>.</li>
</ol>

<p>Implement the <code>NumArray</code> class:</p>

<ul>
	<li><code>NumArray(int[] nums)</code> Initializes the object with the integer array <code>nums</code>.</li>
	<li><code>void update(int index, int val)</code> <strong>Updates</strong> the value of <code>nums[index]</code> to be <code>val</code>.</li>
	<li><code>int sumRange(int left, int right)</code> Returns the <strong>sum</strong> of the elements of <code>nums</code> between indices <code>left</code> and <code>right</code> <strong>inclusive</strong> (i.e. <code>nums[left] + nums[left + 1] + ... + nums[right]</code>).</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;NumArray&quot;, &quot;sumRange&quot;, &quot;update&quot;, &quot;sumRange&quot;]
[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
<strong>Output</strong>
[null, 9, null, 8]

<strong>Explanation</strong>
NumArray numArray = new NumArray([1, 3, 5]);
numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
numArray.update(1, 2);   // nums = [1, 2, 5]
numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>0 &lt;= index &lt; nums.length</code></li>
	<li><code>-100 &lt;= val &lt;= 100</code></li>
	<li><code>0 &lt;= left &lt;= right &lt; nums.length</code></li>
	<li>At most <code>3 * 10<sup>4</sup></code> calls will be made to <code>update</code> and <code>sumRange</code>.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class BinaryIndexedTree:
    __slots__ = ["n", "c"]

    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x: int, delta: int):
        while x <= self.n:
            self.c[x] += delta
            x += x & -x

    def query(self, x: int) -> int:
        s = 0
        while x > 0:
            s += self.c[x]
            x -= x & -x
        return s


class NumArray:
    __slots__ = ["tree"]

    def __init__(self, nums: List[int]):
        self.tree = BinaryIndexedTree(len(nums))
        for i, v in enumerate(nums, 1):
            self.tree.update(i, v)

    def update(self, index: int, val: int) -> None:
        prev = self.sumRange(index, index)
        self.tree.update(index + 1, val - prev)

    def sumRange(self, left: int, right: int) -> int:
        return self.tree.query(right + 1) - self.tree.query(left)


# Your NumArray object will be instantiated and called as such:
# obj = NumArray(nums)
# obj.update(index,val)
# param_2 = obj.sumRange(left,right)
```

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
}

class NumArray {
    private BinaryIndexedTree tree;

    public NumArray(int[] nums) {
        int n = nums.length;
        tree = new BinaryIndexedTree(n);
        for (int i = 0; i < n; ++i) {
            tree.update(i + 1, nums[i]);
        }
    }

    public void update(int index, int val) {
        int prev = sumRange(index, index);
        tree.update(index + 1, val - prev);
    }

    public int sumRange(int left, int right) {
        return tree.query(right + 1) - tree.query(left);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
```

```cpp
class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
};

class NumArray {
public:
    BinaryIndexedTree* tree;

    NumArray(vector<int>& nums) {
        int n = nums.size();
        tree = new BinaryIndexedTree(n);
        for (int i = 0; i < n; ++i) tree->update(i + 1, nums[i]);
    }

    void update(int index, int val) {
        int prev = sumRange(index, index);
        tree->update(index + 1, val - prev);
    }

    int sumRange(int left, int right) {
        return tree->query(right + 1) - tree->query(left);
    }
};

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray* obj = new NumArray(nums);
 * obj->update(index,val);
 * int param_2 = obj->sumRange(left,right);
 */
```

```go
type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	c := make([]int, n+1)
	return &BinaryIndexedTree{n, c}
}

func (t *BinaryIndexedTree) update(x, delta int) {
	for ; x <= t.n; x += x & -x {
		t.c[x] += delta
	}
}

func (t *BinaryIndexedTree) query(x int) (s int) {
	for ; x > 0; x -= x & -x {
		s += t.c[x]
	}
	return s
}

type NumArray struct {
	tree *BinaryIndexedTree
}

func Constructor(nums []int) NumArray {
	tree := newBinaryIndexedTree(len(nums))
	for i, v := range nums {
		tree.update(i+1, v)
	}
	return NumArray{tree}
}

func (t *NumArray) Update(index int, val int) {
	prev := t.SumRange(index, index)
	t.tree.update(index+1, val-prev)
}

func (t *NumArray) SumRange(left int, right int) int {
	return t.tree.query(right+1) - t.tree.query(left)
}

/**
 * Your NumArray object will be instantiated and called as such:
 * obj := Constructor(nums);
 * obj.Update(index,val);
 * param_2 := obj.SumRange(left,right);
 */
```

```ts
class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
    }

    update(x: number, delta: number): void {
        while (x <= this.n) {
            this.c[x] += delta;
            x += x & -x;
        }
    }

    query(x: number): number {
        let s = 0;
        while (x > 0) {
            s += this.c[x];
            x -= x & -x;
        }
        return s;
    }
}

class NumArray {
    private tree: BinaryIndexedTree;

    constructor(nums: number[]) {
        const n = nums.length;
        this.tree = new BinaryIndexedTree(n);
        for (let i = 0; i < n; ++i) {
            this.tree.update(i + 1, nums[i]);
        }
    }

    update(index: number, val: number): void {
        const prev = this.sumRange(index, index);
        this.tree.update(index + 1, val - prev);
    }

    sumRange(left: number, right: number): number {
        return this.tree.query(right + 1) - this.tree.query(left);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * var obj = new NumArray(nums)
 * obj.update(index,val)
 * var param_2 = obj.sumRange(left,right)
 */
```

```cs
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void Update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    public int Query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
}

public class NumArray {
    private BinaryIndexedTree tree;

    public NumArray(int[] nums) {
        int n = nums.Length;
        tree = new BinaryIndexedTree(n);
        for (int i = 0; i < n; ++i) {
            tree.Update(i + 1, nums[i]);
        }
    }

    public void Update(int index, int val) {
        int prev = SumRange(index, index);
        tree.Update(index + 1, val - prev);
    }

    public int SumRange(int left, int right) {
        return tree.Query(right + 1) - tree.Query(left);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.Update(index,val);
 * int param_2 = obj.SumRange(left,right);
 */
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```python
class Node:
    __slots__ = ["l", "r", "v"]

    def __init__(self):
        self.l = self.r = self.v = 0


class SegmentTree:
    __slots__ = ["nums", "tr"]

    def __init__(self, nums):
        self.nums = nums
        n = len(nums)
        self.tr = [Node() for _ in range(n << 2)]
        self.build(1, 1, n)

    def build(self, u, l, r):
        self.tr[u].l, self.tr[u].r = l, r
        if l == r:
            self.tr[u].v = self.nums[l - 1]
            return
        mid = (l + r) >> 1
        self.build(u << 1, l, mid)
        self.build(u << 1 | 1, mid + 1, r)
        self.pushup(u)

    def modify(self, u, x, v):
        if self.tr[u].l == x and self.tr[u].r == x:
            self.tr[u].v = v
            return
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        if x <= mid:
            self.modify(u << 1, x, v)
        else:
            self.modify(u << 1 | 1, x, v)
        self.pushup(u)

    def query(self, u, l, r):
        if self.tr[u].l >= l and self.tr[u].r <= r:
            return self.tr[u].v
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        result = 0
        if l <= mid:
            result += self.query(u << 1, l, r)
        if r > mid:
            result += self.query(u << 1 | 1, l, r)
        return result

    def pushup(self, u):
        self.tr[u].v = self.tr[u << 1].v + self.tr[u << 1 | 1].v


class NumArray:
    __slots__ = ["tree"]

    def __init__(self, nums: List[int]):
        self.tree = SegmentTree(nums)

    def update(self, index: int, val: int) -> None:
        self.tree.modify(1, index + 1, val)

    def sumRange(self, left: int, right: int) -> int:
        return self.tree.query(1, left + 1, right + 1)


# Your NumArray object will be instantiated and called as such:
# obj = NumArray(nums)
# obj.update(index,val)
# param_2 = obj.sumRange(left,right)
```

```java
class Node {
    int l;
    int r;
    int v;
}

class SegmentTree {
    private Node[] tr;
    private int[] nums;

    public SegmentTree(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        tr = new Node[n << 2];
        for (int i = 0; i < tr.length; ++i) {
            tr[i] = new Node();
        }
        build(1, 1, n);
    }

    public void build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if (l == r) {
            tr[u].v = nums[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    public void modify(int u, int x, int v) {
        if (tr[u].l == x && tr[u].r == x) {
            tr[u].v = v;
            return;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (x <= mid) {
            modify(u << 1, x, v);
        } else {
            modify(u << 1 | 1, x, v);
        }
        pushup(u);
    }

    public int query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            return tr[u].v;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        int v = 0;
        if (l <= mid) {
            v += query(u << 1, l, r);
        }
        if (r > mid) {
            v += query(u << 1 | 1, l, r);
        }
        return v;
    }

    public void pushup(int u) {
        tr[u].v = tr[u << 1].v + tr[u << 1 | 1].v;
    }
}

class NumArray {
    private SegmentTree tree;

    public NumArray(int[] nums) {
        tree = new SegmentTree(nums);
    }

    public void update(int index, int val) {
        tree.modify(1, index + 1, val);
    }

    public int sumRange(int left, int right) {
        return tree.query(1, left + 1, right + 1);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
```

```cpp
class Node {
public:
    int l;
    int r;
    int v;
};

class SegmentTree {
public:
    vector<Node*> tr;
    vector<int> nums;

    SegmentTree(vector<int>& nums) {
        this->nums = nums;
        int n = nums.size();
        tr.resize(n << 2);
        for (int i = 0; i < tr.size(); ++i) tr[i] = new Node();
        build(1, 1, n);
    }

    void build(int u, int l, int r) {
        tr[u]->l = l;
        tr[u]->r = r;
        if (l == r) {
            tr[u]->v = nums[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    void modify(int u, int x, int v) {
        if (tr[u]->l == x && tr[u]->r == x) {
            tr[u]->v = v;
            return;
        }
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        if (x <= mid)
            modify(u << 1, x, v);
        else
            modify(u << 1 | 1, x, v);
        pushup(u);
    }

    int query(int u, int l, int r) {
        if (tr[u]->l >= l && tr[u]->r <= r) return tr[u]->v;
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        int v = 0;
        if (l <= mid) v += query(u << 1, l, r);
        if (r > mid) v += query(u << 1 | 1, l, r);
        return v;
    }

    void pushup(int u) {
        tr[u]->v = tr[u << 1]->v + tr[u << 1 | 1]->v;
    }
};

class NumArray {
public:
    SegmentTree* tree;

    NumArray(vector<int>& nums) {
        tree = new SegmentTree(nums);
    }

    void update(int index, int val) {
        return tree->modify(1, index + 1, val);
    }

    int sumRange(int left, int right) {
        return tree->query(1, left + 1, right + 1);
    }
};

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray* obj = new NumArray(nums);
 * obj->update(index,val);
 * int param_2 = obj->sumRange(left,right);
 */
```

```go
type Node struct {
	l, r, v int
}

type SegmentTree struct {
	tr   []Node
	nums []int
}

func newSegmentTree(nums []int) *SegmentTree {
	n := len(nums)
	tr := make([]Node, n<<2)
	for i := range tr {
		tr[i] = Node{}
	}
	tree := &SegmentTree{
		tr:   tr,
		nums: nums,
	}
	tree.build(1, 1, n)
	return tree
}

func (tree *SegmentTree) build(u, l, r int) {
	tree.tr[u].l, tree.tr[u].r = l, r
	if l == r {
		tree.tr[u].v = tree.nums[l-1]
		return
	}
	mid := (l + r) >> 1
	tree.build(u<<1, l, mid)
	tree.build(u<<1|1, mid+1, r)
	tree.pushup(u)
}

func (tree *SegmentTree) modify(u, x, v int) {
	if tree.tr[u].l == x && tree.tr[u].r == x {
		tree.tr[u].v = v
		return
	}
	mid := (tree.tr[u].l + tree.tr[u].r) >> 1
	if x <= mid {
		tree.modify(u<<1, x, v)
	} else {
		tree.modify(u<<1|1, x, v)
	}
	tree.pushup(u)
}

func (tree *SegmentTree) query(u, l, r int) (v int) {
	if tree.tr[u].l >= l && tree.tr[u].r <= r {
		return tree.tr[u].v
	}
	mid := (tree.tr[u].l + tree.tr[u].r) >> 1
	if l <= mid {
		v += tree.query(u<<1, l, r)
	}
	if r > mid {
		v += tree.query(u<<1|1, l, r)
	}
	return v
}

func (tree *SegmentTree) pushup(u int) {
	tree.tr[u].v = tree.tr[u<<1].v + tree.tr[u<<1|1].v
}

type NumArray struct {
	tree *SegmentTree
}

func Constructor(nums []int) NumArray {
	return NumArray{
		tree: newSegmentTree(nums),
	}
}

func (this *NumArray) Update(index int, val int) {
	this.tree.modify(1, index+1, val)
}

func (this *NumArray) SumRange(left int, right int) int {
	return this.tree.query(1, left+1, right+1)
}

/**
 * Your NumArray object will be instantiated and called as such:
 * obj := Constructor(nums);
 * obj.Update(index,val);
 * param_2 := obj.SumRange(left,right);
 */
```

```ts
class Node {
    l: number;
    r: number;
    v: number;
}

class SegmentTree {
    private tr: Node[];
    private nums: number[];

    constructor(nums: number[]) {
        this.nums = nums;
        const n = nums.length;
        this.tr = new Array<Node>(n << 2);
        for (let i = 0; i < this.tr.length; ++i) {
            this.tr[i] = { l: 0, r: 0, v: 0 };
        }
        this.build(1, 1, n);
    }

    build(u: number, l: number, r: number): void {
        this.tr[u].l = l;
        this.tr[u].r = r;
        if (l == r) {
            this.tr[u].v = this.nums[l - 1];
            return;
        }
        const mid = (l + r) >> 1;
        this.build(u << 1, l, mid);
        this.build((u << 1) | 1, mid + 1, r);
        this.pushup(u);
    }

    modify(u: number, x: number, v: number): void {
        if (this.tr[u].l == x && this.tr[u].r == x) {
            this.tr[u].v = v;
            return;
        }
        const mid = (this.tr[u].l + this.tr[u].r) >> 1;
        if (x <= mid) {
            this.modify(u << 1, x, v);
        } else {
            this.modify((u << 1) | 1, x, v);
        }
        this.pushup(u);
    }

    query(u: number, l: number, r: number): number {
        if (this.tr[u].l >= l && this.tr[u].r <= r) {
            return this.tr[u].v;
        }
        const mid = (this.tr[u].l + this.tr[u].r) >> 1;
        let v = 0;
        if (l <= mid) {
            v += this.query(u << 1, l, r);
        }
        if (r > mid) {
            v += this.query((u << 1) | 1, l, r);
        }
        return v;
    }

    pushup(u: number): void {
        this.tr[u].v = this.tr[u << 1].v + this.tr[(u << 1) | 1].v;
    }
}

class NumArray {
    private tree: SegmentTree;

    constructor(nums: number[]) {
        this.tree = new SegmentTree(nums);
    }

    update(index: number, val: number): void {
        this.tree.modify(1, index + 1, val);
    }

    sumRange(left: number, right: number): number {
        return this.tree.query(1, left + 1, right + 1);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * var obj = new NumArray(nums)
 * obj.update(index,val)
 * var param_2 = obj.sumRange(left,right)
 */
```

```cs
public class Node {
    public int l;
    public int r;
    public int v;
}

public class SegmentTree {
    private Node[] tr;
    private int[] nums;

    public SegmentTree(int[] nums) {
        this.nums = nums;
        int n = nums.Length;
        tr = new Node[n << 2];
        for (int i = 0; i < tr.Length; ++i) {
            tr[i] = new Node();
        }
        Build(1, 1, n);
    }

    public void Build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if (l == r) {
            tr[u].v = nums[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        Build(u << 1, l, mid);
        Build(u << 1 | 1, mid + 1, r);
        Pushup(u);
    }

    public void Modify(int u, int x, int v) {
        if (tr[u].l == x && tr[u].r == x) {
            tr[u].v = v;
            return;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (x <= mid) {
            Modify(u << 1, x, v);
        } else {
            Modify(u << 1 | 1, x, v);
        }
        Pushup(u);
    }

    public int Query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            return tr[u].v;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        int v = 0;
        if (l <= mid) {
            v += Query(u << 1, l, r);
        }
        if (r > mid) {
            v += Query(u << 1 | 1, l, r);
        }
        return v;
    }

    public void Pushup(int u) {
        tr[u].v = tr[u << 1].v + tr[u << 1 | 1].v;
    }
}

public class NumArray {
    private SegmentTree tree;

    public NumArray(int[] nums) {
        tree = new SegmentTree(nums);
    }

    public void Update(int index, int val) {
        tree.Modify(1, index + 1, val);
    }

    public int SumRange(int left, int right) {
        return tree.Query(1, left + 1, right + 1);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.Update(index,val);
 * int param_2 = obj.SumRange(left,right);
 */
```

<!-- tabs:end -->

<!-- end -->
