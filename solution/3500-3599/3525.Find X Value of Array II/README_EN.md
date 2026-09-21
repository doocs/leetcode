---
comments: true
difficulty: Hard
rating: 2644
source: Weekly Contest 446 Q4
tags:
    - Segment Tree
    - Array
    - Math
---

<!-- problem:start -->

# [3525. Find X Value of Array II](https://leetcode.com/problems/find-x-value-of-array-ii)

[中文文档](/solution/3500-3599/3525.Find%20X%20Value%20of%20Array%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an array of <strong>positive</strong> integers <code>nums</code> and a <strong>positive</strong> integer <code>k</code>. You are also given a 2D array <code>queries</code>, where <code>queries[i] = [index<sub>i</sub>, value<sub>i</sub>, start<sub>i</sub>, x<sub>i</sub>]</code>.</p>

<p>You are allowed to perform an operation <strong>once</strong> on <code>nums</code>, where you can remove any <strong>suffix</strong> from <code>nums</code> such that <code>nums</code> remains <strong>non-empty</strong>.</p>

<p>The <strong>x-value</strong> of <code>nums</code> <strong>for a given</strong> <code>x</code> is defined as the number of ways to perform this operation so that the <strong>product</strong> of the remaining elements leaves a <em>remainder</em> of <code>x</code> <strong>modulo</strong> <code>k</code>.</p>

<p>For each query in <code>queries</code> you need to determine the <strong>x-value</strong> of <code>nums</code> for <code>x<sub>i</sub></code> after performing the following actions:</p>

<ul>
	<li>Update <code>nums[index<sub>i</sub>]</code> to <code>value<sub>i</sub></code>. Only this step persists for the rest of the queries.</li>
	<li><strong>Remove</strong> the prefix <code>nums[0..(start<sub>i</sub> - 1)]</code> (where <code>nums[0..(-1)]</code> will be used to represent the <strong>empty</strong> prefix).</li>
</ul>

<p>Return an array <code>result</code> of size <code>queries.length</code> where <code>result[i]</code> is the answer for the <code>i<sup>th</sup></code> query.</p>

<p>A <strong>prefix</strong> of an array is a <span data-keyword="subarray">subarray</span> that starts from the beginning of the array and extends to any point within it.</p>

<p>A <strong>suffix</strong> of an array is a <span data-keyword="subarray">subarray</span> that starts at any point within the array and extends to the end of the array.</p>

<p><strong>Note</strong> that the prefix and suffix to be chosen for the operation can be <strong>empty</strong>.</p>

<p><strong>Note</strong> that x-value has a <em>different</em> definition in this version.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,5], k = 3, queries = [[2,2,0,2],[3,3,3,0],[0,1,0,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,2,2]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For query 0, <code>nums</code> becomes <code>[1, 2, 2, 4, 5]</code>, and the empty prefix <strong>must</strong> be removed. The possible operations are:

    <ul>
    	<li>Remove the suffix <code>[2, 4, 5]</code>. <code>nums</code> becomes <code>[1, 2]</code>.</li>
    	<li>Remove the empty suffix. <code>nums</code> becomes <code>[1, 2, 2, 4, 5]</code> with a product 80, which gives remainder 2 when divided by 3.</li>
    </ul>
    </li>
    <li>For query 1, <code>nums</code> becomes <code>[1, 2, 2, 3, 5]</code>, and the prefix <code>[1, 2, 2]</code> <strong>must</strong> be removed. The possible operations are:
    <ul>
    	<li>Remove the empty suffix. <code>nums</code> becomes <code>[3, 5]</code>.</li>
    	<li>Remove the suffix <code>[5]</code>. <code>nums</code> becomes <code>[3]</code>.</li>
    </ul>
    </li>
    <li>For query 2, <code>nums</code> becomes <code>[1, 2, 2, 3, 5]</code>, and the empty prefix <strong>must</strong> be removed. The possible operations are:
    <ul>
    	<li>Remove the suffix <code>[2, 2, 3, 5]</code>. <code>nums</code> becomes <code>[1]</code>.</li>
    	<li>Remove the suffix <code>[3, 5]</code>. <code>nums</code> becomes <code>[1, 2, 2]</code>.</li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,4,8,16,32], k = 4, queries = [[0,2,0,2],[0,2,0,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,0]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For query 0, <code>nums</code> becomes <code>[2, 2, 4, 8, 16, 32]</code>. The only possible operation is:

    <ul>
    	<li>Remove the suffix <code>[2, 4, 8, 16, 32]</code>.</li>
    </ul>
    </li>
    <li>For query 1, <code>nums</code> becomes <code>[2, 2, 4, 8, 16, 32]</code>. There is no possible way to perform the operation.</li>

</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,2,1,1], k = 2, queries = [[2,1,0,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[5]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 5</code></li>
	<li><code>1 &lt;= queries.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>queries[i] == [index<sub>i</sub>, value<sub>i</sub>, start<sub>i</sub>, x<sub>i</sub>]</code></li>
	<li><code>0 &lt;= index<sub>i</sub> &lt;= nums.length - 1</code></li>
	<li><code>1 &lt;= value<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= nums.length - 1</code></li>
	<li><code>0 &lt;= x<sub>i</sub> &lt;= k - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Segment Tree

<!-- thinking:start -->

> **Thinking**
>
> The static count from the previous problem does not survive point updates and a forced prefix deletion. $k \le 5$, so a segment only needs its product modulo $k$ and the number of ways each remainder arises after dropping a suffix.
>
> Store that payload in a segment tree and define a merge. After each update, query the target remainder on $[start, n)$.

<!-- thinking:end -->

Each query first sets $nums[\textit{index}]$ to $\textit{value}$ (the update persists), then drops the prefix $nums[0..start-1]$. After that we may only drop a suffix, so the remainder is a non-empty prefix of $nums[start..n-1]$. The query therefore counts how many prefixes of $[start, n)$ have product congruent to $x$ modulo $k$.

Since $k \le 5$, each segment-tree node stores:

- $\textit{prod}$: the product of the whole segment modulo $k$
- $\textit{cnt}[r]$: how many prefixes of this segment have product $r$ modulo $k$

A leaf with $a = nums[i] \bmod k$ has $\textit{prod} = a$ and $\textit{cnt}[a] = 1$.

Merging left and right children $L$ and $R$:

$$
P.\textit{prod} = (L.\textit{prod} \times R.\textit{prod}) \bmod k
$$

Prefixes lying entirely in $L$ copy $L.\textit{cnt}$. Prefixes that take all of $L$ and then a prefix of $R$ contribute $R.\textit{cnt}[r]$ to remainder $(L.\textit{prod} \times r) \bmod k$.

After a point update, query $\textit{cnt}[x]$ on $[start+1, n]$ (1-indexed). Merges during a query must combine left then right.

The time complexity is $O((n + q) \times k \times \log n)$ and the space complexity is $O(n \times k)$, where $n$ is the array length and $q$ is the number of queries.

<!-- tabs:start -->

#### Python3

```python
class Node:
    __slots__ = "l", "r", "prod", "cnt"

    def __init__(self, l: int, r: int, k: int):
        self.l = l
        self.r = r
        self.prod = 1
        self.cnt = [0] * k


class SegmentTree:
    __slots__ = "k", "tr"

    def __init__(self, nums: list[int], k: int):
        self.k = k
        n = len(nums)
        self.tr = [None] * (n << 2)
        self.build(1, 1, n, nums)

    def merge(self, a: Node, b: Node) -> tuple[int, list[int]]:
        k = self.k
        prod = a.prod * b.prod % k
        cnt = a.cnt[:]
        for r, c in enumerate(b.cnt):
            cnt[a.prod * r % k] += c
        return prod, cnt

    def pushup(self, u: int):
        prod, cnt = self.merge(self.tr[u << 1], self.tr[u << 1 | 1])
        self.tr[u].prod = prod
        self.tr[u].cnt = cnt

    def build(self, u: int, l: int, r: int, nums: list[int]):
        self.tr[u] = Node(l, r, self.k)
        if l == r:
            v = nums[l - 1] % self.k
            self.tr[u].prod = v
            self.tr[u].cnt[v] = 1
            return
        mid = (l + r) >> 1
        self.build(u << 1, l, mid, nums)
        self.build(u << 1 | 1, mid + 1, r, nums)
        self.pushup(u)

    def modify(self, u: int, x: int, v: int):
        if self.tr[u].l == self.tr[u].r:
            v %= self.k
            self.tr[u].prod = v
            self.tr[u].cnt = [0] * self.k
            self.tr[u].cnt[v] = 1
            return
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        if x <= mid:
            self.modify(u << 1, x, v)
        else:
            self.modify(u << 1 | 1, x, v)
        self.pushup(u)

    def query(self, u: int, l: int, r: int) -> Node:
        if self.tr[u].l >= l and self.tr[u].r <= r:
            return self.tr[u]
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        if r <= mid:
            return self.query(u << 1, l, r)
        if l > mid:
            return self.query(u << 1 | 1, l, r)
        left = self.query(u << 1, l, r)
        right = self.query(u << 1 | 1, l, r)
        prod, cnt = self.merge(left, right)
        res = Node(0, 0, self.k)
        res.prod = prod
        res.cnt = cnt
        return res


class Solution:
    def resultArray(
        self, nums: list[int], k: int, queries: list[list[int]]
    ) -> list[int]:
        n = len(nums)
        tree = SegmentTree(nums, k)
        ans = []
        for idx, val, start, x in queries:
            tree.modify(1, idx + 1, val)
            ans.append(tree.query(1, start + 1, n).cnt[x])
        return ans
```

#### Java

```java
class Node {
    int l, r, prod;
    int[] cnt;

    Node(int l, int r, int k) {
        this.l = l;
        this.r = r;
        this.prod = 1;
        this.cnt = new int[k];
    }
}

class SegmentTree {
    private int k;
    private Node[] tr;

    SegmentTree(int[] nums, int k) {
        this.k = k;
        int n = nums.length;
        tr = new Node[n << 2];
        build(1, 1, n, nums);
    }

    private Node merge(Node a, Node b) {
        Node c = new Node(0, 0, k);
        c.prod = a.prod * b.prod % k;
        System.arraycopy(a.cnt, 0, c.cnt, 0, k);
        for (int r = 0; r < k; ++r) {
            c.cnt[a.prod * r % k] += b.cnt[r];
        }
        return c;
    }

    private void pushup(int u) {
        Node p = merge(tr[u << 1], tr[u << 1 | 1]);
        tr[u].prod = p.prod;
        tr[u].cnt = p.cnt;
    }

    private void build(int u, int l, int r, int[] nums) {
        tr[u] = new Node(l, r, k);
        if (l == r) {
            int v = nums[l - 1] % k;
            tr[u].prod = v;
            tr[u].cnt[v] = 1;
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid, nums);
        build(u << 1 | 1, mid + 1, r, nums);
        pushup(u);
    }

    void modify(int u, int x, int v) {
        if (tr[u].l == tr[u].r) {
            v %= k;
            tr[u].prod = v;
            Arrays.fill(tr[u].cnt, 0);
            tr[u].cnt[v] = 1;
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

    Node query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            return tr[u];
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (r <= mid) {
            return query(u << 1, l, r);
        }
        if (l > mid) {
            return query(u << 1 | 1, l, r);
        }
        return merge(query(u << 1, l, r), query(u << 1 | 1, l, r));
    }
}

class Solution {
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(nums, k);
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int idx = queries[i][0], val = queries[i][1], start = queries[i][2], x = queries[i][3];
            tree.modify(1, idx + 1, val);
            ans[i] = tree.query(1, start + 1, n).cnt[x];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Node {
public:
    int l = 0, r = 0;
    int prod = 1;
    vector<int> cnt;
};

class SegmentTree {
public:
    SegmentTree(vector<int>& nums, int k) {
        this->k = k;
        int n = nums.size();
        tr.resize(n << 2);
        build(1, 1, n, nums);
    }

    void modify(int u, int x, int v) {
        if (tr[u].l == tr[u].r) {
            v %= k;
            tr[u].prod = v;
            fill(tr[u].cnt.begin(), tr[u].cnt.end(), 0);
            tr[u].cnt[v] = 1;
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

    Node query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            return tr[u];
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (r <= mid) {
            return query(u << 1, l, r);
        }
        if (l > mid) {
            return query(u << 1 | 1, l, r);
        }
        return merge(query(u << 1, l, r), query(u << 1 | 1, l, r));
    }

private:
    int k;
    vector<Node> tr;

    Node merge(const Node& a, const Node& b) {
        Node c;
        c.prod = a.prod * b.prod % k;
        c.cnt = a.cnt;
        for (int r = 0; r < k; ++r) {
            c.cnt[a.prod * r % k] += b.cnt[r];
        }
        return c;
    }

    void pushup(int u) {
        Node p = merge(tr[u << 1], tr[u << 1 | 1]);
        tr[u].prod = p.prod;
        tr[u].cnt.swap(p.cnt);
    }

    void build(int u, int l, int r, vector<int>& nums) {
        tr[u].l = l;
        tr[u].r = r;
        tr[u].cnt.assign(k, 0);
        if (l == r) {
            int v = nums[l - 1] % k;
            tr[u].prod = v;
            tr[u].cnt[v] = 1;
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid, nums);
        build(u << 1 | 1, mid + 1, r, nums);
        pushup(u);
    }
};

class Solution {
public:
    vector<int> resultArray(vector<int>& nums, int k, vector<vector<int>>& queries) {
        int n = nums.size();
        SegmentTree tree(nums, k);
        vector<int> ans;
        ans.reserve(queries.size());
        for (auto& q : queries) {
            tree.modify(1, q[0] + 1, q[1]);
            ans.push_back(tree.query(1, q[2] + 1, n).cnt[q[3]]);
        }
        return ans;
    }
};
```

#### Go

```go
type node struct {
	l, r, prod int
	cnt        []int
}

type segmentTree struct {
	k  int
	tr []*node
}

func newSegmentTree(nums []int, k int) *segmentTree {
	n := len(nums)
	tr := make([]*node, n<<2)
	t := &segmentTree{k, tr}
	t.build(1, 1, n, nums)
	return t
}

func (t *segmentTree) merge(a, b *node) *node {
	c := &node{prod: a.prod * b.prod % t.k, cnt: make([]int, t.k)}
	copy(c.cnt, a.cnt)
	for r, v := range b.cnt {
		c.cnt[a.prod*r%t.k] += v
	}
	return c
}

func (t *segmentTree) pushup(u int) {
	p := t.merge(t.tr[u<<1], t.tr[u<<1|1])
	t.tr[u].prod = p.prod
	copy(t.tr[u].cnt, p.cnt)
}

func (t *segmentTree) build(u, l, r int, nums []int) {
	t.tr[u] = &node{l: l, r: r, prod: 1, cnt: make([]int, t.k)}
	if l == r {
		v := nums[l-1] % t.k
		t.tr[u].prod = v
		t.tr[u].cnt[v] = 1
		return
	}
	mid := (l + r) >> 1
	t.build(u<<1, l, mid, nums)
	t.build(u<<1|1, mid+1, r, nums)
	t.pushup(u)
}

func (t *segmentTree) modify(u, x, v int) {
	if t.tr[u].l == t.tr[u].r {
		v %= t.k
		t.tr[u].prod = v
		for i := range t.tr[u].cnt {
			t.tr[u].cnt[i] = 0
		}
		t.tr[u].cnt[v] = 1
		return
	}
	mid := (t.tr[u].l + t.tr[u].r) >> 1
	if x <= mid {
		t.modify(u<<1, x, v)
	} else {
		t.modify(u<<1|1, x, v)
	}
	t.pushup(u)
}

func (t *segmentTree) query(u, l, r int) *node {
	if t.tr[u].l >= l && t.tr[u].r <= r {
		return t.tr[u]
	}
	mid := (t.tr[u].l + t.tr[u].r) >> 1
	if r <= mid {
		return t.query(u<<1, l, r)
	}
	if l > mid {
		return t.query(u<<1|1, l, r)
	}
	return t.merge(t.query(u<<1, l, r), t.query(u<<1|1, l, r))
}

func resultArray(nums []int, k int, queries [][]int) []int {
	n := len(nums)
	tree := newSegmentTree(nums, k)
	ans := make([]int, len(queries))
	for i, q := range queries {
		tree.modify(1, q[0]+1, q[1])
		ans[i] = tree.query(1, q[2]+1, n).cnt[q[3]]
	}
	return ans
}
```

#### TypeScript

```ts
class Node {
    l: number;
    r: number;
    prod: number;
    cnt: number[];

    constructor(l: number, r: number, k: number) {
        this.l = l;
        this.r = r;
        this.prod = 1;
        this.cnt = Array(k).fill(0);
    }
}

class SegmentTree {
    private k: number;
    private tr: Node[];

    constructor(nums: number[], k: number) {
        this.k = k;
        this.tr = Array(nums.length << 2);
        this.build(1, 1, nums.length, nums);
    }

    private merge(a: Node, b: Node): Node {
        const c = new Node(0, 0, this.k);
        c.prod = (a.prod * b.prod) % this.k;
        c.cnt = a.cnt.slice();
        for (let r = 0; r < this.k; ++r) {
            c.cnt[(a.prod * r) % this.k] += b.cnt[r];
        }
        return c;
    }

    private pushup(u: number): void {
        const p = this.merge(this.tr[u << 1], this.tr[(u << 1) | 1]);
        this.tr[u].prod = p.prod;
        this.tr[u].cnt = p.cnt;
    }

    private build(u: number, l: number, r: number, nums: number[]): void {
        this.tr[u] = new Node(l, r, this.k);
        if (l === r) {
            const v = nums[l - 1] % this.k;
            this.tr[u].prod = v;
            this.tr[u].cnt[v] = 1;
            return;
        }
        const mid = (l + r) >> 1;
        this.build(u << 1, l, mid, nums);
        this.build((u << 1) | 1, mid + 1, r, nums);
        this.pushup(u);
    }

    modify(u: number, x: number, v: number): void {
        if (this.tr[u].l === this.tr[u].r) {
            v %= this.k;
            this.tr[u].prod = v;
            this.tr[u].cnt.fill(0);
            this.tr[u].cnt[v] = 1;
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

    query(u: number, l: number, r: number): Node {
        if (this.tr[u].l >= l && this.tr[u].r <= r) {
            return this.tr[u];
        }
        const mid = (this.tr[u].l + this.tr[u].r) >> 1;
        if (r <= mid) {
            return this.query(u << 1, l, r);
        }
        if (l > mid) {
            return this.query((u << 1) | 1, l, r);
        }
        return this.merge(this.query(u << 1, l, r), this.query((u << 1) | 1, l, r));
    }
}

function resultArray(nums: number[], k: number, queries: number[][]): number[] {
    const n = nums.length;
    const tree = new SegmentTree(nums, k);
    const ans: number[] = [];
    for (const [idx, val, start, x] of queries) {
        tree.modify(1, idx + 1, val);
        ans.push(tree.query(1, start + 1, n).cnt[x]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
