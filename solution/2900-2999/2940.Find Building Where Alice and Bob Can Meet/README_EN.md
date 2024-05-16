---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2940.Find%20Building%20Where%20Alice%20and%20Bob%20Can%20Meet/README_EN.md
rating: 2327
source: Weekly Contest 372 Q4
tags:
    - Stack
    - Binary Indexed Tree
    - Segment Tree
    - Array
    - Binary Search
    - Monotonic Stack
    - Heap (Priority Queue)
---

# [2940. Find Building Where Alice and Bob Can Meet](https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet)

[中文文档](/solution/2900-2999/2940.Find%20Building%20Where%20Alice%20and%20Bob%20Can%20Meet/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array <code>heights</code> of positive integers, where <code>heights[i]</code> represents the height of the <code>i<sup>th</sup></code> building.</p>

<p>If a person is in building <code>i</code>, they can move to any other building <code>j</code> if and only if <code>i &lt; j</code> and <code>heights[i] &lt; heights[j]</code>.</p>

<p>You are also given another array <code>queries</code> where <code>queries[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>. On the <code>i<sup>th</sup></code> query, Alice is in building <code>a<sub>i</sub></code> while Bob is in building <code>b<sub>i</sub></code>.</p>

<p>Return <em>an array</em> <code>ans</code> <em>where</em> <code>ans[i]</code> <em>is <strong>the index of the leftmost building</strong> where Alice and Bob can meet on the</em> <code>i<sup>th</sup></code> <em>query</em>. <em>If Alice and Bob cannot move to a common building on query</em> <code>i</code>, <em>set</em> <code>ans[i]</code> <em>to</em> <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> heights = [6,4,8,5,2,7], queries = [[0,1],[0,3],[2,4],[3,4],[2,2]]
<strong>Output:</strong> [2,5,-1,5,2]
<strong>Explanation:</strong> In the first query, Alice and Bob can move to building 2 since heights[0] &lt; heights[2] and heights[1] &lt; heights[2]. 
In the second query, Alice and Bob can move to building 5 since heights[0] &lt; heights[5] and heights[3] &lt; heights[5]. 
In the third query, Alice cannot meet Bob since Alice cannot move to any other building.
In the fourth query, Alice and Bob can move to building 5 since heights[3] &lt; heights[5] and heights[4] &lt; heights[5].
In the fifth query, Alice and Bob are already in the same building.  
For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> heights = [5,3,8,2,6,1,4,6], queries = [[0,7],[3,5],[5,2],[3,0],[1,6]]
<strong>Output:</strong> [7,6,-1,4,6]
<strong>Explanation:</strong> In the first query, Alice can directly move to Bob&#39;s building since heights[0] &lt; heights[7].
In the second query, Alice and Bob can move to building 6 since heights[3] &lt; heights[6] and heights[5] &lt; heights[6].
In the third query, Alice cannot meet Bob since Bob cannot move to any other building.
In the fourth query, Alice and Bob can move to building 4 since heights[3] &lt; heights[4] and heights[0] &lt; heights[4].
In the fifth query, Alice can directly move to Bob&#39;s building since heights[1] &lt; heights[6].
For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= heights.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= heights[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>queries[i] = [a<sub>i</sub>, b<sub>i</sub>]</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= heights.length - 1</code></li>
</ul>

## Solutions

### Solution 1: Binary Indexed Tree

Let's denote $queries[i] = [l_i, r_i]$, where $l_i \le r_i$. If $l_i = r_i$ or $heights[l_i] < heights[r_i]$, then the answer is $r_i$. Otherwise, we need to find the smallest $j$ among all $j > r_i$ and $heights[j] > heights[l_i]$.

We can sort $queries$ in descending order of $r_i$, and use a pointer $j$ to point to the current index of $heights$ being traversed.

Next, we traverse each query $queries[i] = (l, r)$. For the current query, if $j > r$, then we loop to insert $heights[j]$ into the binary indexed tree. The binary indexed tree maintains the minimum index of the suffix height (after discretization). Then, we judge whether $l = r$ or $heights[l] < heights[r]$. If it is, then the answer to the current query is $r$. Otherwise, we query the minimum index of $heights[l]$ in the binary indexed tree, which is the answer to the current query.

The time complexity is $O((n + m) \times \log n + m \times \log m)$, and the space complexity is $O(n + m)$. Where $n$ and $m$ are the lengths of $heights$ and $queries$ respectively.

Similar problems:

-   [2736. Maximum Sum Queries](https://github.com/doocs/leetcode/blob/main/solution/2700-2799/2736.Maximum%20Sum%20Queries/README_EN.md)

<!-- tabs:start -->

```python
class BinaryIndexedTree:
    __slots__ = ["n", "c"]

    def __init__(self, n: int):
        self.n = n
        self.c = [inf] * (n + 1)

    def update(self, x: int, v: int):
        while x <= self.n:
            self.c[x] = min(self.c[x], v)
            x += x & -x

    def query(self, x: int) -> int:
        mi = inf
        while x:
            mi = min(mi, self.c[x])
            x -= x & -x
        return -1 if mi == inf else mi


class Solution:
    def leftmostBuildingQueries(
        self, heights: List[int], queries: List[List[int]]
    ) -> List[int]:
        n, m = len(heights), len(queries)
        for i in range(m):
            queries[i] = [min(queries[i]), max(queries[i])]
        j = n - 1
        s = sorted(set(heights))
        ans = [-1] * m
        tree = BinaryIndexedTree(n)
        for i in sorted(range(m), key=lambda i: -queries[i][1]):
            l, r = queries[i]
            while j > r:
                k = n - bisect_left(s, heights[j]) + 1
                tree.update(k, j)
                j -= 1
            if l == r or heights[l] < heights[r]:
                ans[i] = r
            else:
                k = n - bisect_left(s, heights[l])
                ans[i] = tree.query(k)
        return ans
```

```java
class BinaryIndexedTree {
    private final int inf = 1 << 30;
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
        Arrays.fill(c, inf);
    }

    public void update(int x, int v) {
        while (x <= n) {
            c[x] = Math.min(c[x], v);
            x += x & -x;
        }
    }

    public int query(int x) {
        int mi = inf;
        while (x > 0) {
            mi = Math.min(mi, c[x]);
            x -= x & -x;
        }
        return mi == inf ? -1 : mi;
    }
}

class Solution {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = heights.length;
        int m = queries.length;
        for (int i = 0; i < m; ++i) {
            if (queries[i][0] > queries[i][1]) {
                queries[i] = new int[] {queries[i][1], queries[i][0]};
            }
        }
        Integer[] idx = new Integer[m];
        for (int i = 0; i < m; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> queries[j][1] - queries[i][1]);
        int[] s = heights.clone();
        Arrays.sort(s);
        int[] ans = new int[m];
        int j = n - 1;
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        for (int i : idx) {
            int l = queries[i][0], r = queries[i][1];
            while (j > r) {
                int k = n - Arrays.binarySearch(s, heights[j]) + 1;
                tree.update(k, j);
                --j;
            }
            if (l == r || heights[l] < heights[r]) {
                ans[i] = r;
            } else {
                int k = n - Arrays.binarySearch(s, heights[l]);
                ans[i] = tree.query(k);
            }
        }
        return ans;
    }
}
```

```cpp
class BinaryIndexedTree {
private:
    int inf = 1 << 30;
    int n;
    vector<int> c;

public:
    BinaryIndexedTree(int n) {
        this->n = n;
        c.resize(n + 1, inf);
    }

    void update(int x, int v) {
        while (x <= n) {
            c[x] = min(c[x], v);
            x += x & -x;
        }
    }

    int query(int x) {
        int mi = inf;
        while (x > 0) {
            mi = min(mi, c[x]);
            x -= x & -x;
        }
        return mi == inf ? -1 : mi;
    }
};

class Solution {
public:
    vector<int> leftmostBuildingQueries(vector<int>& heights, vector<vector<int>>& queries) {
        int n = heights.size(), m = queries.size();
        for (auto& q : queries) {
            if (q[0] > q[1]) {
                swap(q[0], q[1]);
            }
        }
        vector<int> idx(m);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) {
            return queries[j][1] < queries[i][1];
        });
        vector<int> s = heights;
        sort(s.begin(), s.end());
        s.erase(unique(s.begin(), s.end()), s.end());
        vector<int> ans(m);
        int j = n - 1;
        BinaryIndexedTree tree(n);
        for (int i : idx) {
            int l = queries[i][0], r = queries[i][1];
            while (j > r) {
                int k = s.end() - lower_bound(s.begin(), s.end(), heights[j]) + 1;
                tree.update(k, j);
                --j;
            }
            if (l == r || heights[l] < heights[r]) {
                ans[i] = r;
            } else {
                int k = s.end() - lower_bound(s.begin(), s.end(), heights[l]);
                ans[i] = tree.query(k);
            }
        }
        return ans;
    }
};
```

```go
const inf int = 1 << 30

type BinaryIndexedTree struct {
	n int
	c []int
}

func NewBinaryIndexedTree(n int) BinaryIndexedTree {
	c := make([]int, n+1)
	for i := range c {
		c[i] = inf
	}
	return BinaryIndexedTree{n: n, c: c}
}

func (bit *BinaryIndexedTree) update(x, v int) {
	for x <= bit.n {
		bit.c[x] = min(bit.c[x], v)
		x += x & -x
	}
}

func (bit *BinaryIndexedTree) query(x int) int {
	mi := inf
	for x > 0 {
		mi = min(mi, bit.c[x])
		x -= x & -x
	}
	if mi == inf {
		return -1
	}
	return mi
}

func leftmostBuildingQueries(heights []int, queries [][]int) []int {
	n, m := len(heights), len(queries)
	for _, q := range queries {
		if q[0] > q[1] {
			q[0], q[1] = q[1], q[0]
		}
	}
	idx := make([]int, m)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return queries[idx[j]][1] < queries[idx[i]][1] })
	s := make([]int, n)
	copy(s, heights)
	sort.Ints(s)
	ans := make([]int, m)
	tree := NewBinaryIndexedTree(n)
	j := n - 1
	for _, i := range idx {
		l, r := queries[i][0], queries[i][1]
		for ; j > r; j-- {
			k := n - sort.SearchInts(s, heights[j]) + 1
			tree.update(k, j)
		}
		if l == r || heights[l] < heights[r] {
			ans[i] = r
		} else {
			k := n - sort.SearchInts(s, heights[l])
			ans[i] = tree.query(k)
		}
	}
	return ans
}
```

```ts
class BinaryIndexedTree {
    private n: number;
    private c: number[];
    private inf: number = 1 << 30;

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(this.inf);
    }

    update(x: number, v: number): void {
        while (x <= this.n) {
            this.c[x] = Math.min(this.c[x], v);
            x += x & -x;
        }
    }

    query(x: number): number {
        let mi = this.inf;
        while (x > 0) {
            mi = Math.min(mi, this.c[x]);
            x -= x & -x;
        }
        return mi === this.inf ? -1 : mi;
    }
}

function leftmostBuildingQueries(heights: number[], queries: number[][]): number[] {
    const n = heights.length;
    const m = queries.length;
    for (const q of queries) {
        if (q[0] > q[1]) {
            [q[0], q[1]] = [q[1], q[0]];
        }
    }
    const idx: number[] = Array(m)
        .fill(0)
        .map((_, i) => i);
    idx.sort((i, j) => queries[j][1] - queries[i][1]);
    const tree = new BinaryIndexedTree(n);
    const ans: number[] = Array(m).fill(-1);
    const s = [...heights];
    s.sort((a, b) => a - b);
    const search = (x: number) => {
        let [l, r] = [0, n];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (s[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    let j = n - 1;
    for (const i of idx) {
        const [l, r] = queries[i];
        while (j > r) {
            const k = n - search(heights[j]) + 1;
            tree.update(k, j);
            --j;
        }
        if (l === r || heights[l] < heights[r]) {
            ans[i] = r;
        } else {
            const k = n - search(heights[l]);
            ans[i] = tree.query(k);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
