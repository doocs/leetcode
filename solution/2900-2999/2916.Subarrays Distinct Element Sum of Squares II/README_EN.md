---
comments: true
difficulty: Hard
rating: 2816
source: Biweekly Contest 116 Q4
tags:
    - Binary Indexed Tree
    - Segment Tree
    - Array
    - Hash Table
---

<!-- problem:start -->

# [2916. Subarrays Distinct Element Sum of Squares II](https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-ii)

[中文文档](/solution/2900-2999/2916.Subarrays%20Distinct%20Element%20Sum%20of%20Squares%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed </strong>integer array <code>nums</code>.</p>

<p>The <strong>distinct count</strong> of a subarray of <code>nums</code> is defined as:</p>

<ul>
	<li>Let <code>nums[i..j]</code> be a subarray of <code>nums</code> consisting of all the indices from <code>i</code> to <code>j</code> such that <code>0 &lt;= i &lt;= j &lt; nums.length</code>. Then the number of distinct values in <code>nums[i..j]</code> is called the distinct count of <code>nums[i..j]</code>.</li>
</ul>

<p>Return <em>the sum of the <strong>squares</strong> of <strong>distinct counts</strong> of all subarrays of </em><code>nums</code>.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>A subarray is a contiguous <strong>non-empty</strong> sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,1]
<strong>Output:</strong> 15
<strong>Explanation:</strong> Six possible subarrays are:
[1]: 1 distinct value
[2]: 1 distinct value
[1]: 1 distinct value
[1,2]: 2 distinct values
[2,1]: 2 distinct values
[1,2,1]: 2 distinct values
The sum of the squares of the distinct counts in all subarrays is equal to 1<sup>2</sup> + 1<sup>2</sup> + 1<sup>2</sup> + 2<sup>2</sup> + 2<sup>2</sup> + 2<sup>2</sup> = 15.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Three possible subarrays are:
[2]: 1 distinct value
[2]: 1 distinct value
[2,2]: 1 distinct value
The sum of the squares of the distinct counts in all subarrays is equal to 1<sup>2</sup> + 1<sup>2</sup> + 1<sup>2</sup> = 3.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Segment Tree + Hash Table

<!-- thinking:start -->

> **Thinking**
>
> Enumerating every subarray and squaring its distinct count is too slow once $n \le 10^5$. Fixing the right endpoint $r$ and scanning left still evaluates each subarray on its own, so the cost remains $O(n^2)$.
>
> Let $f(l, r)$ be the number of distinct values in $\textit{nums}[l..r]$. If $\textit{nums}[r]$ last appeared at $p$, or $p = -1$ on its first occurrence, then $f(l, r) = f(l, r - 1) + 1$ only when $l > p$. The identity $(x + 1)^2 = x^2 + 2x + 1$ turns that increment into an update of the sum of squares.
>
> Thus $f(\cdot, r)$ changes by one range addition, while the answer needs the sum of squares over every left endpoint. A segment tree stores both the range sum and the sum of squares, and a lazy tag defers the increment. Scanning $r$ from left to right, we add one on $[p + 1, r]$ and read the value at the root.

<!-- thinking:end -->

We scan the array by the right endpoint $r$ from left to right. A hash map $\textit{last}$ stores the latest index of each value, or $-1$ if it has not appeared. Let $f(l)$ be the number of distinct elements in $\textit{nums}[l..r]$. If $\textit{nums}[r]$ previously occurred at $p$, only the values $f(l)$ with $l \in [p + 1, r]$ increase by $1$.

Each segment-tree node keeps the range sum $\textit{sum}$, the sum of squares $\textit{sumSq}$, and a lazy tag for range addition. Adding $v$ to a range of length $\textit{len}$ updates them by

$$
\begin{aligned}
\textit{sumSq} &\leftarrow \textit{sumSq} + 2 \cdot v \cdot \textit{sum} + v^{2} \cdot \textit{len}, \\
\textit{sum} &\leftarrow \textit{sum} + v \cdot \textit{len}.
\end{aligned}
$$

Positions to the right of $r$ stay $0$, so the root's $\textit{sumSq}$ after each update is the sum of squares of distinct counts of every subarray ending at $r$. Summing these contributions modulo $10^9 + 7$ gives the answer.

The time complexity is $O(n \log n)$ and the space complexity is $O(n)$, where $n$ is the length of the array.

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
