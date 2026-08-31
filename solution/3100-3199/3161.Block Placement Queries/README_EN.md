---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3161.Block%20Placement%20Queries/README_EN.md
rating: 2513
source: Biweekly Contest 131 Q4
tags:
    - Binary Indexed Tree
    - Segment Tree
    - Array
    - Binary Search
    - Ordered Set
---

<!-- problem:start -->

# [3161. Block Placement Queries](https://leetcode.com/problems/block-placement-queries)

[中文文档](/solution/3100-3199/3161.Block%20Placement%20Queries/README.md)

## Description

<!-- description:start -->

<p>There exists an infinite number line, with its origin at 0 and extending towards the <strong>positive</strong> x-axis.</p>

<p>You are given a 2D array <code>queries</code>, which contains two types of queries:</p>

<ol>
	<li>For a query of type 1, <code>queries[i] = [1, x]</code>. Build an obstacle at distance <code>x</code> from the origin. It is guaranteed that there is <strong>no</strong> obstacle at distance <code>x</code> when the query is asked.</li>
	<li>For a query of type 2, <code>queries[i] = [2, x, sz]</code>. Check if it is possible to place a block of size <code>sz</code> <em>anywhere</em> in the range <code>[0, x]</code> on the line, such that the block <strong>entirely</strong> lies in the range <code>[0, x]</code>. A block <strong>cannot </strong>be placed if it intersects with any obstacle, but it may touch it. Note that you do<strong> not</strong> actually place the block. Queries are separate.</li>
</ol>

<p>Return a boolean array <code>results</code>, where <code>results[i]</code> is <code>true</code> if you can place the block specified in the <code>i<sup>th</sup></code> query of type 2, and <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">queries = [[1,2],[2,3,3],[2,3,1],[2,2,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[false,true,true]</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3161.Block%20Placement%20Queries/images/example0block.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 309px; height: 129px;" /></strong></p>

<p>For query 0, place an obstacle at <code>x = 2</code>. A block of size at most 2 can be placed before <code>x = 3</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">queries = </span>[[1,7],[2,7,6],[1,2],[2,7,5],[2,7,6]]<!-- notionvc: 4a471445-5af1-4d72-b11b-94d351a2c8e9 --></p>

<p><strong>Output:</strong> [true,true,false]</p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3161.Block%20Placement%20Queries/images/example1block.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 310px; height: 130px;" /></strong></p>

<ul>
	<li>Place an obstacle at <code>x = 7</code> for query 0. A block of size at most 7 can be placed before <code>x = 7</code>.</li>
	<li>Place an obstacle at <code>x = 2</code> for query 2. Now, a block of size at most 5 can be placed before <code>x = 7</code>, and a block of size at most 2 before <code>x = 2</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= queries.length &lt;= 15 * 10<sup>4</sup></code></li>
	<li><code>2 &lt;= queries[i].length &lt;= 3</code></li>
	<li><code>1 &lt;= queries[i][0] &lt;= 2</code></li>
	<li><code>1 &lt;= x, sz &lt;= min(5 * 10<sup>4</sup>, 3 * queries.length)</code></li>
	<li>The input is generated such that for queries of type 1, no obstacle exists at distance <code>x</code> when the query is asked.</li>
	<li>The input is generated such that there is at least one query of type 2.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Indexed Tree + Ordered Set

Obstacles are only inserted, so we can process the queries offline in reverse and turn "insert an obstacle" into "delete an obstacle". After a deletion the adjacent gap only grows, and a Fenwick tree can maintain prefix maxima.

Put all obstacles into an ordered set, and add sentinels $0$ and $m+1$, where $m$ is the maximum coordinate. For every pair of neighboring obstacles $x_1, x_2$, update index $x_2$ in the Fenwick tree with the gap $x_2 - x_1$.

Then scan the queries from back to front:

- Type $2$: find the last obstacle $pre \le x$. The block can be placed if the maximum gap in $[0, pre]$ or the tail gap $(pre, x]$ is at least $sz$.
- Type $1$: delete obstacle $x$, and update the gap at its successor $nxt$ to $nxt - pre$.

The time complexity is $O(q \times \log m)$, and the space complexity is $O(m)$, where $q$ is the number of queries and $m$ is the maximum coordinate.

<!-- tabs:start -->

#### Python3

```python
class BinaryIndexedTree:
    def __init__(self, n: int):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x: int, v: int):
        while x <= self.n:
            self.c[x] = max(self.c[x], v)
            x += x & -x

    def query(self, x: int) -> int:
        mx = 0
        while x:
            mx = max(mx, self.c[x])
            x -= x & -x
        return mx


class Solution:
    def getResults(self, queries: List[List[int]]) -> List[bool]:
        m = max(q[1] for q in queries)
        sl = SortedList([0, m + 1])
        for q in queries:
            if q[0] == 1:
                sl.add(q[1])
        tree = BinaryIndexedTree(m + 1)
        for x1, x2 in pairwise(sl):
            tree.update(x2, x2 - x1)
        ans = []
        for q in reversed(queries):
            x = q[1]
            if q[0] == 1:
                i = sl.index(x)
                tree.update(sl[i + 1], sl[i + 1] - sl[i - 1])
                sl.remove(x)
            else:
                i = sl.bisect_right(x)
                pre = sl[i - 1]
                ans.append(tree.query(pre) >= q[2] or x - pre >= q[2])
        return ans[::-1]
```

#### Java

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int v) {
        while (x <= n) {
            c[x] = Math.max(c[x], v);
            x += x & -x;
        }
    }

    public int query(int x) {
        int mx = 0;
        while (x > 0) {
            mx = Math.max(mx, c[x]);
            x -= x & -x;
        }
        return mx;
    }
}

class Solution {
    public List<Boolean> getResults(int[][] queries) {
        int m = 0;
        for (int[] q : queries) {
            m = Math.max(m, q[1]);
        }
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(0);
        ts.add(m + 1);
        for (int[] q : queries) {
            if (q[0] == 1) {
                ts.add(q[1]);
            }
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(m + 1);
        int pre = 0;
        for (int x : ts) {
            if (x > 0) {
                tree.update(x, x - pre);
            }
            pre = x;
        }
        List<Boolean> ans = new ArrayList<>();
        for (int i = queries.length - 1; i >= 0; --i) {
            int[] q = queries[i];
            int x = q[1];
            if (q[0] == 1) {
                int nxt = ts.higher(x);
                tree.update(nxt, nxt - ts.lower(x));
                ts.remove(x);
            } else {
                int p = ts.floor(x);
                ans.add(tree.query(p) >= q[2] || x - p >= q[2]);
            }
        }
        Collections.reverse(ans);
        return ans;
    }
}
```

#### C++

```cpp
class BinaryIndexedTree {
private:
    int n;
    vector<int> c;

public:
    BinaryIndexedTree(int n) {
        this->n = n;
        c.resize(n + 1);
    }

    void update(int x, int v) {
        while (x <= n) {
            c[x] = max(c[x], v);
            x += x & -x;
        }
    }

    int query(int x) {
        int mx = 0;
        while (x > 0) {
            mx = max(mx, c[x]);
            x -= x & -x;
        }
        return mx;
    }
};

class Solution {
public:
    vector<bool> getResults(vector<vector<int>>& queries) {
        int m = 0;
        for (auto& q : queries) {
            m = max(m, q[1]);
        }
        set<int> ts{0, m + 1};
        for (auto& q : queries) {
            if (q[0] == 1) {
                ts.insert(q[1]);
            }
        }
        BinaryIndexedTree tree(m + 1);
        int pre = 0;
        for (int x : ts) {
            if (x) {
                tree.update(x, x - pre);
            }
            pre = x;
        }
        vector<bool> ans;
        for (int i = queries.size() - 1; i >= 0; --i) {
            int x = queries[i][1];
            if (queries[i][0] == 1) {
                auto it = ts.find(x);
                tree.update(*next(it), *next(it) - *prev(it));
                ts.erase(it);
            } else {
                auto it = prev(ts.upper_bound(x));
                ans.push_back(tree.query(*it) >= queries[i][2] || x - *it >= queries[i][2]);
            }
        }
        ranges::reverse(ans);
        return ans;
    }
};
```

#### Go

```go
func getResults(queries [][]int) []bool {
	m := 0
	for _, q := range queries {
		m = max(m, q[1])
	}
	st := redblacktree.New[int, struct{}]()
	st.Put(0, struct{}{})
	st.Put(m+1, struct{}{})
	for _, q := range queries {
		if q[0] == 1 {
			st.Put(q[1], struct{}{})
		}
	}
	tree := newBinaryIndexedTree(m + 1)
	it := st.Iterator()
	it.Next()
	pre := it.Key()
	for it.Next() {
		x := it.Key()
		tree.update(x, x-pre)
		pre = x
	}
	ans := []bool{}
	for i := len(queries) - 1; i >= 0; i-- {
		q := queries[i]
		x := q[1]
		if q[0] == 1 {
			nxt, _ := st.Ceiling(x + 1)
			p, _ := st.Floor(x - 1)
			st.Remove(x)
			tree.update(nxt.Key, nxt.Key-p.Key)
		} else {
			node, _ := st.Floor(x)
			p := node.Key
			ans = append(ans, tree.query(p) >= q[2] || x-p >= q[2])
		}
	}
	slices.Reverse(ans)
	return ans
}

type binaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *binaryIndexedTree {
	return &binaryIndexedTree{n: n, c: make([]int, n+1)}
}

func (t *binaryIndexedTree) update(x, v int) {
	for x <= t.n {
		t.c[x] = max(t.c[x], v)
		x += x & -x
	}
}

func (t *binaryIndexedTree) query(x int) int {
	mx := 0
	for x > 0 {
		mx = max(mx, t.c[x])
		x -= x & -x
	}
	return mx
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
