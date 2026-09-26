---
comments: true
difficulty: 困难
rating: 2644
source: 第 446 场周赛 Q4
tags:
    - 线段树
    - 数组
    - 数学
---

<!-- problem:start -->

# [3525. 求出数组的 X 值 II](https://leetcode.cn/problems/find-x-value-of-array-ii)

[English Version](/solution/3500-3599/3525.Find%20X%20Value%20of%20Array%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由&nbsp;<strong>正整数&nbsp;</strong>组成的数组 <code>nums</code> 和一个&nbsp;<strong>正整数</strong> <code>k</code>。同时给你一个二维数组 <code>queries</code>，其中 <code>queries[i] = [index<sub>i</sub>, value<sub>i</sub>, start<sub>i</sub>, x<sub>i</sub>]</code>。</p>

<p>你可以对 <code>nums</code> 执行&nbsp;<strong>一次&nbsp;</strong>操作，移除 <code>nums</code> 的任意&nbsp;<strong>后缀&nbsp;</strong>，使得&nbsp;<code>nums</code> 仍然<strong>非空</strong>。</p>

<p>给定一个 <code>x</code>，<code>nums</code> 的&nbsp;<strong>x值&nbsp;</strong>定义为执行以上操作后剩余元素的&nbsp;<strong>乘积&nbsp;</strong>除以 <code>k</code> 的&nbsp;<strong>余数&nbsp;</strong>为 <code>x</code>&nbsp;的方案数。</p>

<p>对于 <code>queries</code> 中的每个查询，你需要执行以下操作，然后确定 <code>x<sub>i</sub></code> 对应的 <code>nums</code> 的&nbsp;<strong>x值</strong>：</p>

<ul>
	<li>将 <code>nums[index<sub>i</sub>]</code> 更新为 <code>value<sub>i</sub></code>。仅这个更改在接下来的所有查询中保留。</li>
	<li><strong>移除&nbsp;</strong>前缀 <code>nums[0..(start<sub>i</sub> - 1)]</code>（<code>nums[0..(-1)]</code> 表示&nbsp;<strong>空前缀&nbsp;</strong>）。</li>
</ul>

<p>返回一个长度为 <code>queries.length</code> 的数组 <code>result</code>，其中 <code>result[i]</code> 是第 <code>i</code> 个查询的答案。</p>

<p>数组的一个&nbsp;<strong>前缀&nbsp;</strong>是从数组开始位置到任意位置的子数组。</p>

<p>数组的一个&nbsp;<strong>后缀&nbsp;</strong>是从数组中任意位置开始直到结束的子数组。</p>

<p><strong>子数组&nbsp;</strong>是数组中一段连续的元素序列。</p>

<p><strong>注意</strong>：操作中所选的前缀或后缀可以是&nbsp;<strong>空的&nbsp;</strong>。</p>

<p><strong>注意</strong>：x值在本题中与问题 I 有不同的定义。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3,4,5], k = 3, queries = [[2,2,0,2],[3,3,3,0],[0,1,0,1]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[2,2,2]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>对于查询 0，<code>nums</code> 变为 <code>[1, 2, 2, 4, 5]</code>&nbsp;。移除空前缀后，可选操作包括：

    <ul>
    	<li>移除后缀 <code>[2, 4, 5]</code>&nbsp;，<code>nums</code> 变为 <code>[1, 2]</code>。</li>
    	<li>不移除任何后缀。<code>nums</code> 保持为 <code>[1, 2, 2, 4, 5]</code>，乘积为 80，对 3 取余为 2。</li>
    </ul>
    </li>
    <li>对于查询 1，<code>nums</code> 变为 <code>[1, 2, 2, 3, 5]</code>&nbsp;。移除前缀 <code>[1, 2, 2]</code>&nbsp;后，可选操作包括：
    <ul>
    	<li>不移除任何后缀，<code>nums</code> 为 <code>[3, 5]</code>。</li>
    	<li>移除后缀 <code>[5]</code>&nbsp;，<code>nums</code> 为 <code>[3]</code>。</li>
    </ul>
    </li>
    <li>对于查询 2，<code>nums</code> 保持为 <code>[1, 2, 2, 3, 5]</code>&nbsp;。移除空前缀后。可选操作包括：
    <ul>
    	<li>移除后缀 <code>[2, 2, 3, 5]</code>。<code>nums</code> 为 <code>[1]</code>。</li>
    	<li>移除后缀 <code>[3, 5]</code>。<code>nums</code> 为 <code>[1, 2, 2]</code>。</li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,4,8,16,32], k = 4, queries = [[0,2,0,2],[0,2,0,1]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,0]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>对于查询 0，<code>nums</code> 变为 <code>[2, 2, 4, 8, 16, 32]</code>。唯一可行的操作是：

    <ul>
    	<li>移除后缀 <code>[2, 4, 8, 16, 32]</code>。</li>
    </ul>
    </li>
    <li>对于查询 1，<code>nums</code> 仍为 <code>[2, 2, 4, 8, 16, 32]</code>。没有任何操作能使余数为 1。</li>

</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,1,2,1,1], k = 2, queries = [[2,1,0,1]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[5]</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

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

## 解法

<!-- solution:start -->

### 方法一：线段树

<!-- thinking:start -->

> **思考**
>
> 静态统计在单点修改与强制删除前缀后不再适用，$n$ 与询问次数都很大。$k \le 5$，一段的有用信息是乘积模 $k$ 以及「从左端切掉后缀后各余数的方案数」。
>
> 将这些信息放进线段树结点并定义合并，即可在修改后查询 $[start, n)$ 上目标余数的方案数。

<!-- thinking:end -->

每次询问先把 $nums[\textit{index}]$ 改成 $\textit{value}$（该修改对后续询问保留），再强制去掉前缀 $nums[0..start-1]$。之后只能再删后缀，剩余部分是 $nums[start..n-1]$ 的某个非空前缀。因此询问等价于：统计区间 $[start, n)$ 上有多少前缀的乘积模 $k$ 等于 $x$。

$k \le 5$。线段树每个结点维护：

- $\textit{prod}$：该区间全部元素之积模 $k$
- $\textit{cnt}[r]$：该区间自身有多少前缀的乘积模 $k$ 为 $r$

叶子：$a = nums[i] \bmod k$，$\textit{prod} = a$，$\textit{cnt}[a] = 1$。

合并左右儿子 $L$、$R$：

$$
P.\textit{prod} = (L.\textit{prod} \times R.\textit{prod}) \bmod k
$$

完全落在左段的前缀直接累加 $L.\textit{cnt}$；跨过左段再取右段前缀的，余数为 $(L.\textit{prod} \times r) \bmod k$，个数为 $R.\textit{cnt}[r]$。

单点修改后查询 $[start+1, n]$（下标从 $1$ 开始）的 $\textit{cnt}[x]$。查询合并必须先左后右。

时间复杂度 $O((n + q) \times k \times \log n)$，空间复杂度 $O(n \times k)$。其中 $n$ 是数组长度，$q$ 是询问次数。

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
    int cnt[5]{};
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
            memset(tr[u].cnt, 0, sizeof(tr[u].cnt));
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
        memcpy(c.cnt, a.cnt, sizeof(c.cnt));
        for (int r = 0; r < k; ++r) {
            c.cnt[a.prod * r % k] += b.cnt[r];
        }
        return c;
    }

    void pushup(int u) {
        Node p = merge(tr[u << 1], tr[u << 1 | 1]);
        tr[u].prod = p.prod;
        memcpy(tr[u].cnt, p.cnt, sizeof(tr[u].cnt));
    }

    void build(int u, int l, int r, vector<int>& nums) {
        tr[u].l = l;
        tr[u].r = r;
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
