---
comments: true
difficulty: Hard
rating: 2538
source: Weekly Contest 443 Q4
tags:
    - Array
    - Hash Table
    - Math
    - Dynamic Programming
    - Sliding Window
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3505. Minimum Operations to Make Elements Within K Subarrays Equal](https://leetcode.com/problems/minimum-operations-to-make-elements-within-k-subarrays-equal)

[中文文档](/solution/3500-3599/3505.Minimum%20Operations%20to%20Make%20Elements%20Within%20K%20Subarrays%20Equal/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and two integers, <code>x</code> and <code>k</code>. You can perform the following operation any number of times (<strong>including zero</strong>):</p>

<ul>
	<li>Increase or decrease any element of <code>nums</code> by 1.</li>
</ul>

<p>Return the <strong>minimum</strong> number of operations needed to have <strong>at least</strong> <code>k</code> <em>non-overlapping <span data-keyword="subarray-nonempty">subarrays</span></em> of size <strong>exactly</strong> <code>x</code> in <code>nums</code>, where all elements within each subarray are equal.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,-2,1,3,7,3,6,4,-1], x = 3, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Use 3 operations to add 3 to <code>nums[1]</code> and use 2 operations to subtract 2 from <code>nums[3]</code>. The resulting array is <code>[5, 1, 1, 1, 7, 3, 6, 4, -1]</code>.</li>
	<li>Use 1 operation to add 1 to <code>nums[5]</code> and use 2 operations to subtract 2 from <code>nums[6]</code>. The resulting array is <code>[5, 1, 1, 1, 7, 4, 4, 4, -1]</code>.</li>
	<li>Now, all elements within each subarray <code>[1, 1, 1]</code> (from indices 1 to 3) and <code>[4, 4, 4]</code> (from indices 5 to 7) are equal. Since 8 total operations were used, 8 is the output.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [9,-2,-2,-2,1,5], x = 2, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Use 3 operations to subtract 3 from <code>nums[4]</code>. The resulting array is <code>[9, -2, -2, -2, -2, 5]</code>.</li>
	<li>Now, all elements within each subarray <code>[-2, -2]</code> (from indices 1 to 2) and <code>[-2, -2]</code> (from indices 3 to 4) are equal. Since 3 operations were used, 3 is the output.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>6</sup> &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>2 &lt;= x &lt;= nums.length</code></li>
	<li><code>1 &lt;= k &lt;= 15</code></li>
	<li><code>2 &lt;= k * x &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Ordered Set + Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> $n \le 10^5$ and $k \le 15$, so we cannot brute-force sort every window of length $x$ and then search combinations. The minimum operations to make a subarray all equal is the sum of distances to its median. By maintaining two ordered sets $l$ and $r$ for the lower and upper halves of the sliding window along with their sums, we can update the window in $O(\log x)$ time and query its minimum cost in $O(1)$ time.
>
> Choosing $k$ non-overlapping windows can be solved with dynamic programming: let $f[i][j]$ be the minimum cost to choose $j$ subarrays of length $x$ from the first $i$ elements. At step $i$, we either skip ending a subarray at $i$ (cost $f[i - 1][j]$) or place the $j$-th subarray at $[i - x, i - 1]$ (cost $f[i - x][j - 1] + w$). Since $k$ is small, the total time complexity $O(n(\log x + k))$ easily passes.

<!-- thinking:end -->

To make all elements in a subarray of length $x$ equal, the minimum number of operations is achieved by changing every element to the median of the subarray. To efficiently compute this minimum cost for every sliding window of length $x$, we design a helper structure $\textit{MedianCost}$ that maintains two ordered multisets $l$ and $r$ along with their element sums $\textit{s1}$ and $\textit{s2}$:

- Multiset $l$ stores the smaller half of the elements in the window and $r$ stores the larger half. The $\textit{adjust}$ method keeps $0 \le |l| - |r| \le 1$, so the largest element in $l$ is always the median $\textit{mid}$ of the window.
- $\textit{add}(v)$: inserts $v$ into $r$ and adds $v$ to $\textit{s2}$, then moves the smallest element of $r$ into $l$ (updating $\textit{s2}$ and $\textit{s1}$), and calls $\textit{adjust}$ to rebalance the sizes.
- $\textit{remove}(v)$: if $v \in l$, removes $v$ from $l$ and subtracts $v$ from $\textit{s1}$; otherwise removes $v$ from $r$ and subtracts $v$ from $\textit{s2}$, and then calls $\textit{adjust}$ to rebalance.
- $\textit{query}()$: letting $\textit{mid}$ be the largest element in $l$, the number of operations to make all elements in the window equal to $\textit{mid}$ is $|l| \times \textit{mid} - \textit{s1} + \textit{s2} - |r| \times \textit{mid}$.

We then use dynamic programming. Define $f[i][j]$ as the minimum number of operations to choose $j$ non-overlapping subarrays of length $x$ from the first $i$ elements of $\textit{nums}$ such that all elements within each subarray are equal. Initially, $f[0][0] = 0$ and all other states are set to $+\infty$.

We iterate $i$ from $1$ to $n$, adding $\textit{nums}[i - 1]$ to $\textit{mc}$ and querying the current window cost $w = \textit{mc}.\textit{query}()$. For each $j \in [0, k]$:

- If we do not end a subarray at the $i$-th element, $f[i][j] = f[i - 1][j]$;
- If $j > 0$ and $i \ge x$, we can choose the subarray $\textit{nums}[i - x .. i - 1]$ as the $j$-th subarray, giving the transition:

$$
f[i][j] = \min(f[i - 1][j], f[i - x][j - 1] + w)
$$

When $i \ge x$, we remove the outgoing element $\textit{nums}[i - x]$ from $\textit{mc}$ before moving to the next index. After the loop, $f[n][k]$ is the answer.

The time complexity is $O(n(\log x + k))$, and the space complexity is $O(nk)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class MedianCost:
    def __init__(self):
        self.l = SortedList()
        self.r = SortedList()
        self.s1 = 0
        self.s2 = 0

    def add(self, x: int):
        self.r.add(x)
        self.s2 += x

        y = self.r.pop(0)
        self.s2 -= y
        self.l.add(y)
        self.s1 += y
        self.adjust()

    def adjust(self):
        while len(self.l) - len(self.r) > 1:
            x = self.l.pop()
            self.s1 -= x
            self.r.add(x)
            self.s2 += x
        while len(self.r) > len(self.l):
            x = self.r.pop(0)
            self.s2 -= x
            self.l.add(x)
            self.s1 += x

    def remove(self, x: int):
        if x in self.l:
            self.l.remove(x)
            self.s1 -= x
        else:
            self.r.remove(x)
            self.s2 -= x
        self.adjust()

    def query(self) -> int:
        mid = self.l[-1]
        return len(self.l) * mid - self.s1 + self.s2 - len(self.r) * mid


class Solution:
    def minOperations(self, nums: List[int], x: int, k: int) -> int:
        n = len(nums)
        f = [[inf] * (k + 1) for _ in range(n + 1)]
        f[0][0] = 0
        mc = MedianCost()
        for i, v in enumerate(nums, 1):
            mc.add(v)
            w = mc.query()
            for j in range(k + 1):
                f[i][j] = min(f[i][j], f[i - 1][j])
                if j > 0 and i >= x:
                    f[i][j] = min(f[i][j], f[i - x][j - 1] + w)
            if i >= x:
                mc.remove(nums[i - x])
        return f[n][k]
```

#### Java

```java
class MedianCost {
    TreeMap<Integer, Integer> l = new TreeMap<>();
    TreeMap<Integer, Integer> r = new TreeMap<>();
    int n1 = 0, n2 = 0;
    long s1 = 0, s2 = 0;

    void addTo(TreeMap<Integer, Integer> tree, int x) {
        tree.merge(x, 1, Integer::sum);
    }

    void removeFrom(TreeMap<Integer, Integer> tree, int x) {
        int cnt = tree.get(x);
        if (cnt == 1) {
            tree.remove(x);
        } else {
            tree.put(x, cnt - 1);
        }
    }

    void adjust() {
        while (n1 - n2 > 1) {
            int x = l.lastKey();
            removeFrom(l, x);
            n1--;
            s1 -= x;
            addTo(r, x);
            n2++;
            s2 += x;
        }
        while (n2 > n1) {
            int x = r.firstKey();
            removeFrom(r, x);
            n2--;
            s2 -= x;
            addTo(l, x);
            n1++;
            s1 += x;
        }
    }

    void add(int x) {
        addTo(r, x);
        n2++;
        s2 += x;

        int y = r.firstKey();
        removeFrom(r, y);
        n2--;
        s2 -= y;
        addTo(l, y);
        n1++;
        s1 += y;

        adjust();
    }

    void remove(int x) {
        if (l.containsKey(x)) {
            removeFrom(l, x);
            n1--;
            s1 -= x;
        } else {
            removeFrom(r, x);
            n2--;
            s2 -= x;
        }
        adjust();
    }

    long query() {
        long mid = l.lastKey();
        return n1 * mid - s1 + s2 - n2 * mid;
    }
}

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long INF = Long.MAX_VALUE / 4;
        long[][] f = new long[n + 1][k + 1];
        for (long[] row : f) {
            Arrays.fill(row, INF);
        }
        f[0][0] = 0;

        MedianCost mc = new MedianCost();
        for (int i = 1; i <= n; i++) {
            mc.add(nums[i - 1]);
            long w = mc.query();

            for (int j = 0; j <= k; j++) {
                f[i][j] = f[i - 1][j];
                if (j > 0 && i >= x) {
                    f[i][j] = Math.min(f[i][j], f[i - x][j - 1] + w);
                }
            }

            if (i >= x) {
                mc.remove(nums[i - x]);
            }
        }
        return f[n][k];
    }
}
```

#### C++

```cpp
class MedianCost {
    multiset<int> l, r;
    long long s1 = 0, s2 = 0;

public:
    void adjust() {
        while ((long long) l.size() - (long long) r.size() > 1) {
            int v = *l.rbegin();
            l.erase(prev(l.end()));
            s1 -= v;
            r.insert(v);
            s2 += v;
        }
        while (r.size() > l.size()) {
            int v = *r.begin();
            r.erase(r.begin());
            s2 -= v;
            l.insert(v);
            s1 += v;
        }
    }

    void add(int x) {
        r.insert(x);
        s2 += x;
        int y = *r.begin();
        r.erase(r.begin());
        s2 -= y;
        l.insert(y);
        s1 += y;
        adjust();
    }

    void remove(int x) {
        auto it = l.find(x);
        if (it != l.end()) {
            l.erase(it);
            s1 -= x;
        } else {
            r.erase(r.find(x));
            s2 -= x;
        }
        adjust();
    }

    long long query() {
        long long mid = *l.rbegin();
        return (long long) l.size() * mid - s1 + s2 - (long long) r.size() * mid;
    }
};

class Solution {
public:
    long long minOperations(vector<int>& nums, int x, int k) {
        int n = nums.size();
        const long long INF = LLONG_MAX / 4;
        vector<vector<long long>> f(n + 1, vector<long long>(k + 1, INF));
        f[0][0] = 0;

        MedianCost mc;
        for (int i = 1; i <= n; ++i) {
            mc.add(nums[i - 1]);
            long long w = mc.query();

            for (int j = 0; j <= k; ++j) {
                f[i][j] = f[i - 1][j];
                if (j > 0 && i >= x) {
                    f[i][j] = min(f[i][j], f[i - x][j - 1] + w);
                }
            }

            if (i >= x) {
                mc.remove(nums[i - x]);
            }
        }
        return f[n][k];
    }
};
```

#### Go

```go
type MedianCost struct {
	l, r   *redblacktree.Tree
	n1, n2 int
	s1, s2 int64
}

func NewMedianCost() *MedianCost {
	return &MedianCost{
		l: redblacktree.NewWithIntComparator(),
		r: redblacktree.NewWithIntComparator(),
	}
}

func addTo(tree *redblacktree.Tree, x int) {
	v, ok := tree.Get(x)
	if ok {
		tree.Put(x, v.(int)+1)
	} else {
		tree.Put(x, 1)
	}
}

func removeFrom(tree *redblacktree.Tree, x int) {
	v, _ := tree.Get(x)
	if v.(int) == 1 {
		tree.Remove(x)
	} else {
		tree.Put(x, v.(int)-1)
	}
}

func (mc *MedianCost) adjust() {
	for mc.n1-mc.n2 > 1 {
		x := mc.l.Right().Key.(int)
		removeFrom(mc.l, x)
		mc.n1--
		mc.s1 -= int64(x)

		addTo(mc.r, x)
		mc.n2++
		mc.s2 += int64(x)
	}

	for mc.n2 > mc.n1 {
		x := mc.r.Left().Key.(int)
		removeFrom(mc.r, x)
		mc.n2--
		mc.s2 -= int64(x)

		addTo(mc.l, x)
		mc.n1++
		mc.s1 += int64(x)
	}
}

func (mc *MedianCost) add(x int) {
	addTo(mc.r, x)
	mc.n2++
	mc.s2 += int64(x)

	y := mc.r.Left().Key.(int)
	removeFrom(mc.r, y)
	mc.n2--
	mc.s2 -= int64(y)

	addTo(mc.l, y)
	mc.n1++
	mc.s1 += int64(y)

	mc.adjust()
}

func (mc *MedianCost) remove(x int) {
	if _, ok := mc.l.Get(x); ok {
		removeFrom(mc.l, x)
		mc.n1--
		mc.s1 -= int64(x)
	} else {
		removeFrom(mc.r, x)
		mc.n2--
		mc.s2 -= int64(x)
	}
	mc.adjust()
}

func (mc *MedianCost) query() int64 {
	mid := int64(mc.l.Right().Key.(int))
	return int64(mc.n1)*mid - mc.s1 + mc.s2 - int64(mc.n2)*mid
}

func minOperations(nums []int, x int, k int) int64 {
	n := len(nums)
	const INF int64 = math.MaxInt64 / 4

	f := make([][]int64, n+1)
	for i := range f {
		f[i] = make([]int64, k+1)
		for j := range f[i] {
			f[i][j] = INF
		}
	}
	f[0][0] = 0

	mc := NewMedianCost()
	for i := 1; i <= n; i++ {
		mc.add(nums[i-1])
		w := mc.query()

		for j := 0; j <= k; j++ {
			f[i][j] = f[i-1][j]
			if j > 0 && i >= x {
				f[i][j] = min(f[i][j], f[i-x][j-1]+w)
			}
		}

		if i >= x {
			mc.remove(nums[i-x])
		}
	}

	return f[n][k]
}
```

#### TypeScript

```ts
class Node {
    key: number;
    pri: number;
    cnt: number;
    left: Node | null = null;
    right: Node | null = null;

    constructor(key: number) {
        this.key = key;
        this.pri = Math.random();
        this.cnt = 1;
    }
}

class Treap {
    root: Node | null = null;

    rotateRight(o: Node): Node {
        const p = o.left!;
        o.left = p.right;
        p.right = o;
        return p;
    }

    rotateLeft(o: Node): Node {
        const p = o.right!;
        o.right = p.left;
        p.left = o;
        return p;
    }

    merge(a: Node | null, b: Node | null): Node | null {
        if (!a) return b;
        if (!b) return a;
        if (a.pri > b.pri) {
            a.right = this.merge(a.right, b);
            return a;
        }
        b.left = this.merge(a, b.left);
        return b;
    }

    insert(o: Node | null, x: number): Node {
        if (!o) return new Node(x);

        if (x === o.key) {
            o.cnt++;
        } else if (x < o.key) {
            o.left = this.insert(o.left, x);
            if (o.left.pri > o.pri) {
                o = this.rotateRight(o);
            }
        } else {
            o.right = this.insert(o.right, x);
            if (o.right.pri > o.pri) {
                o = this.rotateLeft(o);
            }
        }
        return o;
    }

    erase(o: Node | null, x: number): Node | null {
        if (!o) return null;

        if (x < o.key) {
            o.left = this.erase(o.left, x);
        } else if (x > o.key) {
            o.right = this.erase(o.right, x);
        } else {
            if (o.cnt > 1) {
                o.cnt--;
            } else {
                return this.merge(o.left, o.right);
            }
        }
        return o;
    }

    add(x: number): void {
        this.root = this.insert(this.root, x);
    }

    remove(x: number): void {
        this.root = this.erase(this.root, x);
    }

    contains(x: number): boolean {
        let o = this.root;
        while (o) {
            if (x === o.key) return true;
            o = x < o.key ? o.left : o.right;
        }
        return false;
    }

    first(): number {
        let o = this.root!;
        while (o.left) o = o.left;
        return o.key;
    }

    last(): number {
        let o = this.root!;
        while (o.right) o = o.right;
        return o.key;
    }
}

class MedianCost {
    l = new Treap();
    r = new Treap();
    n1 = 0;
    n2 = 0;
    s1 = 0;
    s2 = 0;

    adjust(): void {
        while (this.n1 - this.n2 > 1) {
            const x = this.l.last();
            this.l.remove(x);
            this.n1--;
            this.s1 -= x;

            this.r.add(x);
            this.n2++;
            this.s2 += x;
        }

        while (this.n2 > this.n1) {
            const x = this.r.first();
            this.r.remove(x);
            this.n2--;
            this.s2 -= x;

            this.l.add(x);
            this.n1++;
            this.s1 += x;
        }
    }

    add(x: number): void {
        this.r.add(x);
        this.n2++;
        this.s2 += x;

        const y = this.r.first();
        this.r.remove(y);
        this.n2--;
        this.s2 -= y;

        this.l.add(y);
        this.n1++;
        this.s1 += y;

        this.adjust();
    }

    remove(x: number): void {
        if (this.l.contains(x)) {
            this.l.remove(x);
            this.n1--;
            this.s1 -= x;
        } else {
            this.r.remove(x);
            this.n2--;
            this.s2 -= x;
        }
        this.adjust();
    }

    query(): number {
        const mid = this.l.last();
        return this.n1 * mid - this.s1 + this.s2 - this.n2 * mid;
    }
}

function minOperations(nums: number[], x: number, k: number): number {
    const n = nums.length;
    const INF = Number.MAX_SAFE_INTEGER;

    const f: number[][] = Array.from({ length: n + 1 }, () => new Array(k + 1).fill(INF));
    f[0][0] = 0;

    const mc = new MedianCost();

    for (let i = 1; i <= n; i++) {
        mc.add(nums[i - 1]);
        const w = mc.query();

        for (let j = 0; j <= k; j++) {
            f[i][j] = f[i - 1][j];
            if (j > 0 && i >= x) {
                f[i][j] = Math.min(f[i][j], f[i - x][j - 1] + w);
            }
        }

        if (i >= x) {
            mc.remove(nums[i - x]);
        }
    }

    return f[n][k];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
