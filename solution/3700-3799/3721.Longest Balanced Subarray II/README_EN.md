---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3721.Longest%20Balanced%20Subarray%20II/README_EN.md
rating: 2723
source: Weekly Contest 472 Q4
tags:
    - Segment Tree
    - Array
    - Hash Table
    - Divide and Conquer
    - Prefix Sum
---

<!-- problem:start -->

# [3721. Longest Balanced Subarray II](https://leetcode.com/problems/longest-balanced-subarray-ii)

[中文文档](/solution/3700-3799/3721.Longest%20Balanced%20Subarray%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>A <strong><span data-keyword="subarray-nonempty">subarray</span></strong> is called <strong>balanced</strong> if the number of <strong>distinct even</strong> numbers in the subarray is equal to the number of <strong>distinct odd</strong> numbers.</p>

<p>Return the length of the <strong>longest</strong> balanced subarray.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,5,4,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The longest balanced subarray is <code>[2, 5, 4, 3]</code>.</li>
	<li>It has 2 distinct even numbers <code>[2, 4]</code> and 2 distinct odd numbers <code>[5, 3]</code>. Thus, the answer is 4.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,2,2,5,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The longest balanced subarray is <code>[3, 2, 2, 5, 4]</code>.</li>
	<li>It has 2 distinct even numbers <code>[2, 4]</code> and 2 distinct odd numbers <code>[3, 5]</code>. Thus, the answer is 5.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The longest balanced subarray is <code>[2, 3, 2]</code>.</li>
	<li>It has 1 distinct even number <code>[2]</code> and 1 distinct odd number <code>[3]</code>. Thus, the answer is 3.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Segment Tree + Prefix Sum + Hash Table

We can transform the problem into a prefix sum problem. Define a prefix sum variable $\textit{now}$, representing the difference between odd and even numbers in the current subarray:

$$
\textit{now} = \text{distinct odd numbers} - \text{distinct even numbers}
$$

For odd elements, record as $+1$, and for even elements, record as $-1$. Use a hash table $\textit{last}$ to record the last occurrence position of each number. If a number appears repeatedly, we need to revoke its previous contribution.

To efficiently calculate the subarray length each time a right endpoint element is added, we use a segment tree to maintain the minimum and maximum values of the interval prefix sum, while supporting interval addition operations and binary search queries on the segment tree. When iterating to right endpoint $i$, first update the contribution of the current element, then use the segment tree to query the earliest position $pos$ where the current prefix sum $\textit{now}$ appears. The current subarray length is $i - pos$, and we update the answer:

$$
\textit{ans} = \max(\textit{ans}, i - pos)
$$

The time complexity is $O(n \log n)$, where $n$ is the length of the array. Each segment tree modification and query operation takes $O(\log n)$, and enumerating the right endpoint $n$ times gives a total time complexity of $O(n \log n)$. The space complexity is $O(n)$, where segment tree nodes and the hash table each occupy $O(n)$ space.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestBalanced(self, nums: List[int]) -> int:
        n = len(nums)

        class Node:
            __slots__ = ("l", "r", "mn", "mx", "lazy")
            def __init__(self):
                self.l = self.r = 0
                self.mn = self.mx = 0
                self.lazy = 0

        tr = [Node() for _ in range((n + 1) * 4)]

        def build(u: int, l: int, r: int):
            tr[u].l, tr[u].r = l, r
            tr[u].mn = tr[u].mx = tr[u].lazy = 0
            if l == r:
                return
            mid = (l + r) >> 1
            build(u << 1, l, mid)
            build(u << 1 | 1, mid + 1, r)

        def apply(u: int, v: int):
            tr[u].mn += v
            tr[u].mx += v
            tr[u].lazy += v

        def pushdown(u: int):
            if tr[u].lazy:
                apply(u << 1, tr[u].lazy)
                apply(u << 1 | 1, tr[u].lazy)
                tr[u].lazy = 0

        def pushup(u: int):
            tr[u].mn = min(tr[u << 1].mn, tr[u << 1 | 1].mn)
            tr[u].mx = max(tr[u << 1].mx, tr[u << 1 | 1].mx)

        def modify(u: int, l: int, r: int, v: int):
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

        def query(u: int, target: int) -> int:
            if tr[u].l == tr[u].r:
                return tr[u].l
            pushdown(u)
            if tr[u << 1].mn <= target <= tr[u << 1].mx:
                return query(u << 1, target)
            return query(u << 1 | 1, target)

        build(1, 0, n)

        last = {}
        now = ans = 0

        for i, x in enumerate(nums, start=1):
            det = 1 if (x & 1) else -1
            if x in last:
                modify(1, last[x], n, -det)
                now -= det
            last[x] = i
            modify(1, i, n, det)
            now += det
            pos = query(1, now)
            ans = max(ans, i - pos)

        return ans
```

#### Java

```java
/**
 *
 * Idea:
 * - Treat each distinct odd number as +1, and each distinct even number as -1
 * - Maintain prefix sums representing the balance
 * - When a number appears again, remove its previous contribution
 * - Use a segment tree to maintain min/max prefix sums with range add
 * - Binary search on the segment tree to find the earliest equal prefix sum
 */
class Solution {

    /**
     * Segment tree node
     */
    static class Node {
        int l, r; // segment range
        int mn, mx; // minimum / maximum prefix sum
        int lazy; // lazy propagation (range add)
    }

    /**
     * Segment tree supporting:
     * - range add
     * - find the smallest index with a given prefix sum
     */
    static class SegmentTree {
        Node[] tr;

        SegmentTree(int n) {
            tr = new Node[n << 2];
            for (int i = 0; i < tr.length; i++) {
                tr[i] = new Node();
            }
            build(1, 0, n);
        }

        // Build an empty tree with all prefix sums = 0
        void build(int u, int l, int r) {
            tr[u].l = l;
            tr[u].r = r;
            tr[u].mn = tr[u].mx = 0;
            tr[u].lazy = 0;
            if (l == r) return;
            int mid = (l + r) >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
        }

        // Add value v to all prefix sums in [l, r]
        void modify(int u, int l, int r, int v) {
            if (tr[u].l >= l && tr[u].r <= r) {
                apply(u, v);
                return;
            }
            pushdown(u);
            int mid = (tr[u].l + tr[u].r) >> 1;
            if (l <= mid) modify(u << 1, l, r, v);
            if (r > mid) modify(u << 1 | 1, l, r, v);
            pushup(u);
        }

        // Binary search on the segment tree
        // Find the smallest index where prefix sum == target
        int query(int u, int target) {
            if (tr[u].l == tr[u].r) {
                return tr[u].l;
            }
            pushdown(u);
            int left = u << 1;
            int right = u << 1 | 1;
            if (tr[left].mn <= target && target <= tr[left].mx) {
                return query(left, target);
            }
            return query(right, target);
        }

        // Apply range add
        void apply(int u, int v) {
            tr[u].mn += v;
            tr[u].mx += v;
            tr[u].lazy += v;
        }

        // Update from children
        void pushup(int u) {
            tr[u].mn = Math.min(tr[u << 1].mn, tr[u << 1 | 1].mn);
            tr[u].mx = Math.max(tr[u << 1].mx, tr[u << 1 | 1].mx);
        }

        // Push lazy tag down
        void pushdown(int u) {
            if (tr[u].lazy != 0) {
                apply(u << 1, tr[u].lazy);
                apply(u << 1 | 1, tr[u].lazy);
                tr[u].lazy = 0;
            }
        }
    }

    public int longestBalanced(int[] nums) {
        int n = nums.length;
        SegmentTree st = new SegmentTree(n);

        // last[x] = last position where value x appeared
        Map<Integer, Integer> last = new HashMap<>();

        int now = 0; // current prefix sum
        int ans = 0; // answer

        // Enumerate the right endpoint
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            int det = (x & 1) == 1 ? 1 : -1;

            // Remove previous contribution if x appeared before
            if (last.containsKey(x)) {
                st.modify(1, last.get(x), n, -det);
                now -= det;
            }

            // Add current contribution
            last.put(x, i);
            st.modify(1, i, n, det);
            now += det;

            // Find earliest position with the same prefix sum
            int pos = st.query(1, now);
            ans = Math.max(ans, i - pos);
        }

        return ans;
    }
}
```

#### C++

```cpp
/*
 * Segment tree node.
 * It maintains:
 *  - [l, r] : the covered index range
 *  - mn     : minimum prefix sum in this range
 *  - mx     : maximum prefix sum in this range
 *  - lazy   : lazy propagation value (range add)
 */
class Node {
public:
    int l = 0, r = 0;
    int mn = 0, mx = 0;
    int lazy = 0;
};

/*
 * Segment Tree that supports:
 *  1. Range add
 *  2. Query the smallest index whose prefix sum equals a given value
 */
class SegmentTree {
public:
    SegmentTree(int n) {
        tr.resize(n << 2);
        for (int i = 0; i < (int) tr.size(); ++i) {
            tr[i] = new Node();
        }
        // Build the tree on range [0, n]
        build(1, 0, n);
    }

    /*
     * Add value v to all prefix sums in range [l, r]
     */
    void modify(int u, int l, int r, int v) {
        if (tr[u]->l >= l && tr[u]->r <= r) {
            apply(u, v);
            return;
        }
        pushdown(u);
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        if (l <= mid) modify(u << 1, l, r, v);
        if (r > mid) modify(u << 1 | 1, l, r, v);
        pushup(u);
    }

    /*
     * Binary search on the segment tree.
     * Find the smallest index pos such that prefix_sum[pos] == target.
     *
     * The key observation:
     * If target is within [mn, mx] of a segment, then such a position
     * must exist inside this segment.
     */
    int query(int u, int target) {
        if (tr[u]->l == tr[u]->r) {
            return tr[u]->l;
        }
        pushdown(u);
        int lc = u << 1, rc = u << 1 | 1;
        if (tr[lc]->mn <= target && target <= tr[lc]->mx) {
            return query(lc, target);
        }
        return query(rc, target);
    }

private:
    vector<Node*> tr;

    /*
     * Build an empty segment tree.
     * Initial prefix sums are all zero.
     */
    void build(int u, int l, int r) {
        tr[u]->l = l;
        tr[u]->r = r;
        tr[u]->mn = tr[u]->mx = 0;
        tr[u]->lazy = 0;
        if (l == r) return;
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
    }

    /*
     * Apply a range add to node u.
     */
    void apply(int u, int v) {
        tr[u]->mn += v;
        tr[u]->mx += v;
        tr[u]->lazy += v;
    }

    /*
     * Push information from children to parent.
     */
    void pushup(int u) {
        tr[u]->mn = min(tr[u << 1]->mn, tr[u << 1 | 1]->mn);
        tr[u]->mx = max(tr[u << 1]->mx, tr[u << 1 | 1]->mx);
    }

    /*
     * Push lazy tag down to children.
     */
    void pushdown(int u) {
        if (tr[u]->lazy != 0) {
            apply(u << 1, tr[u]->lazy);
            apply(u << 1 | 1, tr[u]->lazy);
            tr[u]->lazy = 0;
        }
    }
};

class Solution {
public:
    int longestBalanced(vector<int>& nums) {
        int n = nums.size();
        SegmentTree st(n);

        /*
         * last[x] = the most recent position where value x appeared
         */
        unordered_map<int, int> last;

        int now = 0; // current prefix sum
        int ans = 0; // answer

        /*
         * Enumerate the right endpoint of the subarray
         */
        for (int i = 1; i <= n; ++i) {
            int x = nums[i - 1];

            /*
             * Contribution of x:
             *  +1 if x is odd
             *  -1 if x is even
             */
            int det = (x & 1) ? 1 : -1;

            /*
             * If x appeared before, remove its previous contribution
             */
            if (last.count(x)) {
                st.modify(1, last[x], n, -det);
                now -= det;
            }

            /*
             * Add current contribution of x
             */
            last[x] = i;
            st.modify(1, i, n, det);
            now += det;

            /*
             * Find the smallest position pos such that
             * prefix_sum[pos] == now
             */
            int pos = st.query(1, now);

            /*
             * Update the maximum balanced subarray length
             */
            ans = max(ans, i - pos);
        }

        return ans;
    }
};
```

#### Go

```go
// Segment tree node
type Node struct {
	l, r   int // segment range
	mn, mx int // minimum / maximum prefix sum
	lazy   int // lazy propagation (range add)
}

// Segment tree
type SegmentTree struct {
	tr []Node
}

// Build a segment tree for range [0, n]
func NewSegmentTree(n int) *SegmentTree {
	st := &SegmentTree{
		tr: make([]Node, n<<2),
	}
	st.build(1, 0, n)
	return st
}

// Initialize all prefix sums to 0
func (st *SegmentTree) build(u, l, r int) {
	st.tr[u] = Node{l: l, r: r, mn: 0, mx: 0, lazy: 0}
	if l == r {
		return
	}
	mid := (l + r) >> 1
	st.build(u<<1, l, mid)
	st.build(u<<1|1, mid+1, r)
}

// Add v to all prefix sums in [l, r]
func (st *SegmentTree) modify(u, l, r, v int) {
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

// Binary search on the segment tree
// Find the smallest index where prefix sum == target
func (st *SegmentTree) query(u, target int) int {
	if st.tr[u].l == st.tr[u].r {
		return st.tr[u].l
	}
	st.pushdown(u)
	left, right := u<<1, u<<1|1
	if st.tr[left].mn <= target && target <= st.tr[left].mx {
		return st.query(left, target)
	}
	return st.query(right, target)
}

// Apply range addition
func (st *SegmentTree) apply(u, v int) {
	st.tr[u].mn += v
	st.tr[u].mx += v
	st.tr[u].lazy += v
}

// Update from children
func (st *SegmentTree) pushup(u int) {
	st.tr[u].mn = min(st.tr[u<<1].mn, st.tr[u<<1|1].mn)
	st.tr[u].mx = max(st.tr[u<<1].mx, st.tr[u<<1|1].mx)
}

// Push lazy value down
func (st *SegmentTree) pushdown(u int) {
	if st.tr[u].lazy != 0 {
		v := st.tr[u].lazy
		st.apply(u<<1, v)
		st.apply(u<<1|1, v)
		st.tr[u].lazy = 0
	}
}

// Main function
func longestBalanced(nums []int) int {
	n := len(nums)
	st := NewSegmentTree(n)

	// last[x] = last position where value x appeared
	last := make(map[int]int)

	now := 0 // current prefix sum
	ans := 0 // answer

	// Enumerate right endpoint
	for i := 1; i <= n; i++ {
		x := nums[i-1]
		det := -1
		if x&1 == 1 {
			det = 1
		}

		// Remove previous contribution if x appeared before
		if pos, ok := last[x]; ok {
			st.modify(1, pos, n, -det)
			now -= det
		}

		// Add current contribution
		last[x] = i
		st.modify(1, i, n, det)
		now += det

		// Find earliest position with same prefix sum
		pos := st.query(1, now)
		ans = max(ans, i-pos)
	}

	return ans
}
```

#### TypeScript

```ts
function longestBalanced(nums: number[]): number {
    const n = nums.length;

    interface Node {
        l: number;
        r: number;
        mn: number;
        mx: number;
        lazy: number;
    }

    const tr: Node[] = Array.from({ length: (n + 1) * 4 }, () => ({
        l: 0,
        r: 0,
        mn: 0,
        mx: 0,
        lazy: 0,
    }));

    function build(u: number, l: number, r: number) {
        tr[u].l = l;
        tr[u].r = r;
        if (l === r) return;
        const mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build((u << 1) | 1, mid + 1, r);
    }

    function apply(u: number, v: number) {
        tr[u].mn += v;
        tr[u].mx += v;
        tr[u].lazy += v;
    }

    function pushdown(u: number) {
        if (tr[u].lazy !== 0) {
            apply(u << 1, tr[u].lazy);
            apply((u << 1) | 1, tr[u].lazy);
            tr[u].lazy = 0;
        }
    }

    function pushup(u: number) {
        tr[u].mn = Math.min(tr[u << 1].mn, tr[(u << 1) | 1].mn);
        tr[u].mx = Math.max(tr[u << 1].mx, tr[(u << 1) | 1].mx);
    }

    function modify(u: number, l: number, r: number, v: number) {
        if (tr[u].l >= l && tr[u].r <= r) {
            apply(u, v);
            return;
        }
        pushdown(u);
        const mid = (tr[u].l + tr[u].r) >> 1;
        if (l <= mid) modify(u << 1, l, r, v);
        if (r > mid) modify((u << 1) | 1, l, r, v);
        pushup(u);
    }

    function query(u: number, target: number): number {
        if (tr[u].l === tr[u].r) return tr[u].l;
        pushdown(u);
        if (tr[u << 1].mn <= target && target <= tr[u << 1].mx) {
            return query(u << 1, target);
        }
        return query((u << 1) | 1, target);
    }

    build(1, 0, n);

    const last = new Map<number, number>();
    let now = 0,
        ans = 0;

    nums.forEach((x, idx) => {
        const i = idx + 1;
        const det = x & 1 ? 1 : -1;
        if (last.has(x)) {
            modify(1, last.get(x)!, n, -det);
            now -= det;
        }
        last.set(x, i);
        modify(1, i, n, det);
        now += det;
        const pos = query(1, now);
        ans = Math.max(ans, i - pos);
    });

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
