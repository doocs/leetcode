---
comments: true
difficulty: 困难
rating: 2816
source: 第 116 场双周赛 Q4
tags:
    - 树状数组
    - 线段树
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [2916. 子数组不同元素数目的平方和 II](https://leetcode.cn/problems/subarrays-distinct-element-sum-of-squares-ii)

[English Version](/solution/2900-2999/2916.Subarrays%20Distinct%20Element%20Sum%20of%20Squares%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>定义 <code>nums</code>&nbsp;一个子数组的 <strong>不同计数</strong>&nbsp;值如下：</p>

<ul>
	<li>令&nbsp;<code>nums[i..j]</code>&nbsp;表示 <code>nums</code> 中所有下标在 <code>i</code> 到 <code>j</code> 范围内的元素构成的子数组（满足 <code>0 &lt;= i &lt;= j &lt; nums.length</code> ），那么我们称子数组&nbsp;<code>nums[i..j]</code>&nbsp;中不同值的数目为&nbsp;<code>nums[i..j]</code>&nbsp;的不同计数。</li>
</ul>

<p>请你返回 <code>nums</code>&nbsp;中所有子数组的 <strong>不同计数</strong>&nbsp;的 <strong>平方</strong>&nbsp;和。</p>

<p>由于答案可能会很大，请你将它对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>子数组指的是一个数组里面一段连续 <strong>非空</strong>&nbsp;的元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,1]
<b>输出：</b>15
<b>解释：</b>六个子数组分别为：
[1]: 1 个互不相同的元素。
[2]: 1 个互不相同的元素。
[1]: 1 个互不相同的元素。
[1,2]: 2 个互不相同的元素。
[2,1]: 2 个互不相同的元素。
[1,2,1]: 2 个互不相同的元素。
所有不同计数的平方和为 1<sup>2</sup> + 1<sup>2</sup> + 1<sup>2</sup> + 2<sup>2</sup> + 2<sup>2</sup> + 2<sup>2</sup> = 15 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,2]
<b>输出：3</b>
<strong>解释：</strong>三个子数组分别为：
[2]: 1 个互不相同的元素。
[2]: 1 个互不相同的元素。
[2,2]: 1 个互不相同的元素。
所有不同计数的平方和为 1<sup>2</sup> + 1<sup>2</sup> + 1<sup>2</sup> = 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：线段树 + 哈希表

<!-- thinking:start -->

> **思考**
>
> 直接枚举每个子数组，统计不同元素后再平方累加，在 $n \le 10^5$ 时会超时。即使固定右端点 $r$ 再向左扩展，每个子数组仍被单独计算，时间仍是 $O(n^2)$。
>
> 令 $f(l, r)$ 表示 $\textit{nums}[l..r]$ 的不同元素个数。若 $\textit{nums}[r]$ 上一次出现在 $p$（首次出现则 $p = -1$），则只有 $l > p$ 时 $f(l, r) = f(l, r - 1) + 1$。由 $(x + 1)^2 = x^2 + 2x + 1$，对这段左端点做一次区间加一，平方和可以同步更新。
>
> $f(\cdot, r)$ 的变化因此只是一段区间加，而答案需要全体左端点的平方和。线段树在每个节点保存区间和与平方和，并用懒标记推迟加一。从左到右处理 $r$，更新 $[p + 1, r]$ 后读取根节点，即得到以 $r$ 结尾的贡献。

<!-- thinking:end -->

我们按右端点 $r$ 从左到右扫描数组。用哈希表 $\textit{last}$ 记录每个值最近一次出现的下标，没有出现过则为 $-1$。记 $f(l)$ 为子数组 $\textit{nums}[l..r]$ 的不同元素个数。若 $\textit{nums}[r]$ 上一次出现在 $p$，则只有 $l \in [p + 1, r]$ 的 $f(l)$ 增加 $1$。

线段树的每个节点维护区间和 $\textit{sum}$、平方和 $\textit{sumSq}$，以及区间加的懒标记。区间长度为 $\textit{len}$、整体加上 $v$ 时：

$$
\begin{aligned}
\textit{sumSq} &\leftarrow \textit{sumSq} + 2 \cdot v \cdot \textit{sum} + v^{2} \cdot \textit{len}, \\
\textit{sum} &\leftarrow \textit{sum} + v \cdot \textit{len}.
\end{aligned}
$$

$r$ 右侧的位置保持 $0$，因此更新后根节点的 $\textit{sumSq}$ 就是所有以 $r$ 结尾的子数组的平方和。将这些贡献累加，并对 $10^9 + 7$ 取模，得到答案。

时间复杂度 $O(n \log n)$，空间复杂度 $O(n)$，其中 $n$ 是数组长度。

<!-- tabs:start -->

#### Python3

```python
MOD = 10**9 + 7


class Solution:
    def sumCounts(self, nums: List[int]) -> int:
        n = len(nums)

        class Node:
            __slots__ = ("l", "r", "sum", "sum_sq", "lazy")

            def __init__(self):
                self.l = self.r = 0
                self.sum = self.sum_sq = self.lazy = 0

        tr = [Node() for _ in range(n << 2)]

        def build(u: int, l: int, r: int) -> None:
            tr[u].l, tr[u].r = l, r
            if l == r:
                return
            mid = (l + r) >> 1
            build(u << 1, l, mid)
            build(u << 1 | 1, mid + 1, r)

        def apply(u: int, v: int) -> None:
            length = tr[u].r - tr[u].l + 1
            tr[u].sum_sq += 2 * v * tr[u].sum + v * v * length
            tr[u].sum += v * length
            tr[u].lazy += v

        def pushdown(u: int) -> None:
            if tr[u].lazy:
                apply(u << 1, tr[u].lazy)
                apply(u << 1 | 1, tr[u].lazy)
                tr[u].lazy = 0

        def pushup(u: int) -> None:
            tr[u].sum = tr[u << 1].sum + tr[u << 1 | 1].sum
            tr[u].sum_sq = tr[u << 1].sum_sq + tr[u << 1 | 1].sum_sq

        def modify(u: int, l: int, r: int, v: int) -> None:
            if tr[u].l >= l and tr[u].r <= r:
                apply(u, v)
                return
            pushdown(u)
            mid = (tr[u].l + tr[u].r) >> 1
            if l <= mid:
                modify(u << 1, l, r, v)
            if r > mid:
                modify(u << 1 | 1, l, r, v)
            pushup(u)

        build(1, 0, n - 1)
        last = {}
        ans = 0
        for i, x in enumerate(nums):
            p = last.get(x, -1)
            modify(1, p + 1, i, 1)
            last[x] = i
            ans = (ans + tr[1].sum_sq) % MOD
        return ans
```

#### Java

```java
class Solution {
    private static final int MOD = 1000000007;

    static class Node {
        int l, r;
        long sum, sumSq;
        int lazy;
    }

    static class SegmentTree {
        Node[] tr;

        SegmentTree(int n) {
            tr = new Node[n << 2];
            for (int i = 0; i < tr.length; i++) {
                tr[i] = new Node();
            }
            build(1, 0, n - 1);
        }

        void build(int u, int l, int r) {
            tr[u].l = l;
            tr[u].r = r;
            if (l == r) {
                return;
            }
            int mid = (l + r) >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
        }

        void apply(int u, int v) {
            long len = tr[u].r - tr[u].l + 1L;
            long add = v;
            tr[u].sumSq += 2 * add * tr[u].sum + add * add * len;
            tr[u].sum += add * len;
            tr[u].lazy += v;
        }

        void pushdown(int u) {
            if (tr[u].lazy != 0) {
                apply(u << 1, tr[u].lazy);
                apply(u << 1 | 1, tr[u].lazy);
                tr[u].lazy = 0;
            }
        }

        void pushup(int u) {
            tr[u].sum = tr[u << 1].sum + tr[u << 1 | 1].sum;
            tr[u].sumSq = tr[u << 1].sumSq + tr[u << 1 | 1].sumSq;
        }

        void modify(int u, int l, int r, int v) {
            if (tr[u].l >= l && tr[u].r <= r) {
                apply(u, v);
                return;
            }
            pushdown(u);
            int mid = (tr[u].l + tr[u].r) >> 1;
            if (l <= mid) {
                modify(u << 1, l, r, v);
            }
            if (r > mid) {
                modify(u << 1 | 1, l, r, v);
            }
            pushup(u);
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree st = new SegmentTree(n);
        Map<Integer, Integer> last = new HashMap<>();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int p = last.getOrDefault(nums[i], -1);
            st.modify(1, p + 1, i, 1);
            last.put(nums[i], i);
            ans = (ans + st.tr[1].sumSq) % MOD;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Node {
public:
    int l = 0, r = 0;
    long long sum = 0, sumSq = 0;
    int lazy = 0;
};

class SegmentTree {
public:
    SegmentTree(int n) {
        tr.resize(n << 2);
        build(1, 0, n - 1);
    }

    void modify(int u, int l, int r, int v) {
        if (tr[u].l >= l && tr[u].r <= r) {
            apply(u, v);
            return;
        }
        pushdown(u);
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (l <= mid) {
            modify(u << 1, l, r, v);
        }
        if (r > mid) {
            modify(u << 1 | 1, l, r, v);
        }
        pushup(u);
    }

    long long sumSq() const {
        return tr[1].sumSq;
    }

private:
    vector<Node> tr;

    void build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if (l == r) {
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
    }

    void apply(int u, int v) {
        long long len = tr[u].r - tr[u].l + 1LL;
        long long add = v;
        tr[u].sumSq += 2 * add * tr[u].sum + add * add * len;
        tr[u].sum += add * len;
        tr[u].lazy += v;
    }

    void pushdown(int u) {
        if (tr[u].lazy) {
            apply(u << 1, tr[u].lazy);
            apply(u << 1 | 1, tr[u].lazy);
            tr[u].lazy = 0;
        }
    }

    void pushup(int u) {
        tr[u].sum = tr[u << 1].sum + tr[u << 1 | 1].sum;
        tr[u].sumSq = tr[u << 1].sumSq + tr[u << 1 | 1].sumSq;
    }
};

class Solution {
public:
    int sumCounts(vector<int>& nums) {
        int n = nums.size();
        SegmentTree st(n);
        unordered_map<int, int> last;
        long long ans = 0;
        for (int i = 0; i < n; ++i) {
            int p = last.count(nums[i]) ? last[nums[i]] : -1;
            st.modify(1, p + 1, i, 1);
            last[nums[i]] = i;
            ans = (ans + st.sumSq()) % 1000000007;
        }
        return (int) ans;
    }
};
```

#### Go

```go
const mod = 1_000_000_007

type node struct {
	l, r       int
	sum, sumSq int
	lazy       int
}

type segmentTree struct {
	tr []node
}

func newSegmentTree(n int) *segmentTree {
	st := &segmentTree{tr: make([]node, n<<2)}
	st.build(1, 0, n-1)
	return st
}

func (st *segmentTree) build(u, l, r int) {
	st.tr[u].l, st.tr[u].r = l, r
	if l == r {
		return
	}
	mid := (l + r) >> 1
	st.build(u<<1, l, mid)
	st.build(u<<1|1, mid+1, r)
}

func (st *segmentTree) apply(u, v int) {
	length := st.tr[u].r - st.tr[u].l + 1
	st.tr[u].sumSq += 2*v*st.tr[u].sum + v*v*length
	st.tr[u].sum += v * length
	st.tr[u].lazy += v
}

func (st *segmentTree) pushdown(u int) {
	if st.tr[u].lazy != 0 {
		st.apply(u<<1, st.tr[u].lazy)
		st.apply(u<<1|1, st.tr[u].lazy)
		st.tr[u].lazy = 0
	}
}

func (st *segmentTree) pushup(u int) {
	st.tr[u].sum = st.tr[u<<1].sum + st.tr[u<<1|1].sum
	st.tr[u].sumSq = st.tr[u<<1].sumSq + st.tr[u<<1|1].sumSq
}

func (st *segmentTree) modify(u, l, r, v int) {
	if st.tr[u].l >= l && st.tr[u].r <= r {
		st.apply(u, v)
		return
	}
	st.pushdown(u)
	mid := (st.tr[u].l + st.tr[u].r) >> 1
	if l <= mid {
		st.modify(u<<1, l, r, v)
	}
	if r > mid {
		st.modify(u<<1|1, l, r, v)
	}
	st.pushup(u)
}

func sumCounts(nums []int) int {
	n := len(nums)
	st := newSegmentTree(n)
	last := map[int]int{}
	ans := 0
	for i, x := range nums {
		p, ok := last[x]
		if !ok {
			p = -1
		}
		st.modify(1, p+1, i, 1)
		last[x] = i
		ans = (ans + st.tr[1].sumSq) % mod
	}
	return ans
}
```

#### TypeScript

```ts
const MOD = 1_000_000_007;

function sumCounts(nums: number[]): number {
    const n = nums.length;

    interface Node {
        l: number;
        r: number;
        sum: number;
        sumSq: number;
        lazy: number;
    }

    const tr: Node[] = Array.from({ length: n << 2 }, () => ({
        l: 0,
        r: 0,
        sum: 0,
        sumSq: 0,
        lazy: 0,
    }));

    const build = (u: number, l: number, r: number) => {
        tr[u].l = l;
        tr[u].r = r;
        if (l === r) {
            return;
        }
        const mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build((u << 1) | 1, mid + 1, r);
    };

    const apply = (u: number, v: number) => {
        const len = tr[u].r - tr[u].l + 1;
        tr[u].sumSq += 2 * v * tr[u].sum + v * v * len;
        tr[u].sum += v * len;
        tr[u].lazy += v;
    };

    const pushdown = (u: number) => {
        if (tr[u].lazy !== 0) {
            apply(u << 1, tr[u].lazy);
            apply((u << 1) | 1, tr[u].lazy);
            tr[u].lazy = 0;
        }
    };

    const pushup = (u: number) => {
        tr[u].sum = tr[u << 1].sum + tr[(u << 1) | 1].sum;
        tr[u].sumSq = tr[u << 1].sumSq + tr[(u << 1) | 1].sumSq;
    };

    const modify = (u: number, l: number, r: number, v: number) => {
        if (tr[u].l >= l && tr[u].r <= r) {
            apply(u, v);
            return;
        }
        pushdown(u);
        const mid = (tr[u].l + tr[u].r) >> 1;
        if (l <= mid) {
            modify(u << 1, l, r, v);
        }
        if (r > mid) {
            modify((u << 1) | 1, l, r, v);
        }
        pushup(u);
    };

    build(1, 0, n - 1);
    const last = new Map<number, number>();
    let ans = 0;
    for (let i = 0; i < n; i++) {
        const p = last.get(nums[i]) ?? -1;
        modify(1, p + 1, i, 1);
        last.set(nums[i], i);
        ans = (ans + tr[1].sumSq) % MOD;
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

const MOD: i64 = 1_000_000_007;

#[derive(Clone, Copy)]
struct Node {
    l: i32,
    r: i32,
    sum: i64,
    sum_sq: i64,
    lazy: i32,
}

struct SegmentTree {
    tr: Vec<Node>,
}

impl SegmentTree {
    fn new(n: i32) -> Self {
        let mut st = Self {
            tr: vec![
                Node {
                    l: 0,
                    r: 0,
                    sum: 0,
                    sum_sq: 0,
                    lazy: 0,
                };
                (n as usize) << 2
            ],
        };
        st.build(1, 0, n - 1);
        st
    }

    fn build(&mut self, u: usize, l: i32, r: i32) {
        self.tr[u].l = l;
        self.tr[u].r = r;
        if l == r {
            return;
        }
        let mid = (l + r) >> 1;
        self.build(u << 1, l, mid);
        self.build(u << 1 | 1, mid + 1, r);
    }

    fn apply(&mut self, u: usize, v: i32) {
        let len = (self.tr[u].r - self.tr[u].l + 1) as i64;
        let add = v as i64;
        self.tr[u].sum_sq += 2 * add * self.tr[u].sum + add * add * len;
        self.tr[u].sum += add * len;
        self.tr[u].lazy += v;
    }

    fn pushdown(&mut self, u: usize) {
        if self.tr[u].lazy != 0 {
            let lazy = self.tr[u].lazy;
            self.apply(u << 1, lazy);
            self.apply(u << 1 | 1, lazy);
            self.tr[u].lazy = 0;
        }
    }

    fn pushup(&mut self, u: usize) {
        self.tr[u].sum = self.tr[u << 1].sum + self.tr[u << 1 | 1].sum;
        self.tr[u].sum_sq = self.tr[u << 1].sum_sq + self.tr[u << 1 | 1].sum_sq;
    }

    fn modify(&mut self, u: usize, l: i32, r: i32, v: i32) {
        if self.tr[u].l >= l && self.tr[u].r <= r {
            self.apply(u, v);
            return;
        }
        self.pushdown(u);
        let mid = (self.tr[u].l + self.tr[u].r) >> 1;
        if l <= mid {
            self.modify(u << 1, l, r, v);
        }
        if r > mid {
            self.modify(u << 1 | 1, l, r, v);
        }
        self.pushup(u);
    }
}

impl Solution {
    pub fn sum_counts(nums: Vec<i32>) -> i32 {
        let n = nums.len() as i32;
        let mut st = SegmentTree::new(n);
        let mut last = HashMap::new();
        let mut ans = 0_i64;
        for (i, &x) in nums.iter().enumerate() {
            let p = *last.get(&x).unwrap_or(&-1);
            st.modify(1, p + 1, i as i32, 1);
            last.insert(x, i as i32);
            ans = (ans + st.tr[1].sum_sq) % MOD;
        }
        ans as i32
    }
}
```

#### C#

```cs
public class Solution {
    private const int Mod = 1000000007;

    private class Node {
        public int L, R;
        public long Sum, SumSq;
        public int Lazy;
    }

    private class SegmentTree {
        private readonly Node[] tr;

        public SegmentTree(int n) {
            tr = new Node[n << 2];
            for (int i = 0; i < tr.Length; i++) {
                tr[i] = new Node();
            }
            Build(1, 0, n - 1);
        }

        public long SumSq => tr[1].SumSq;

        private void Build(int u, int l, int r) {
            tr[u].L = l;
            tr[u].R = r;
            if (l == r) {
                return;
            }
            int mid = (l + r) >> 1;
            Build(u << 1, l, mid);
            Build(u << 1 | 1, mid + 1, r);
        }

        private void Apply(int u, int v) {
            long len = tr[u].R - tr[u].L + 1L;
            long add = v;
            tr[u].SumSq += 2 * add * tr[u].Sum + add * add * len;
            tr[u].Sum += add * len;
            tr[u].Lazy += v;
        }

        private void PushDown(int u) {
            if (tr[u].Lazy != 0) {
                Apply(u << 1, tr[u].Lazy);
                Apply(u << 1 | 1, tr[u].Lazy);
                tr[u].Lazy = 0;
            }
        }

        private void PushUp(int u) {
            tr[u].Sum = tr[u << 1].Sum + tr[u << 1 | 1].Sum;
            tr[u].SumSq = tr[u << 1].SumSq + tr[u << 1 | 1].SumSq;
        }

        public void Modify(int u, int l, int r, int v) {
            if (tr[u].L >= l && tr[u].R <= r) {
                Apply(u, v);
                return;
            }
            PushDown(u);
            int mid = (tr[u].L + tr[u].R) >> 1;
            if (l <= mid) {
                Modify(u << 1, l, r, v);
            }
            if (r > mid) {
                Modify(u << 1 | 1, l, r, v);
            }
            PushUp(u);
        }
    }

    public int SumCounts(int[] nums) {
        int n = nums.Length;
        SegmentTree st = new SegmentTree(n);
        Dictionary<int, int> last = new Dictionary<int, int>();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int p = last.TryGetValue(nums[i], out int idx) ? idx : -1;
            st.Modify(1, p + 1, i, 1);
            last[nums[i]] = i;
            ans = (ans + st.SumSq) % Mod;
        }
        return (int) ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
