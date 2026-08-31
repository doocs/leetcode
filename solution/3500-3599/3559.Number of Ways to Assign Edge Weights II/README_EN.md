---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3559.Number%20of%20Ways%20to%20Assign%20Edge%20Weights%20II/README_EN.md
rating: 2146
source: Biweekly Contest 157 Q4
tags:
    - Bit Manipulation
    - Tree
    - Depth-First Search
    - Array
    - Math
    - Dynamic Programming
---

<!-- problem:start -->

# [3559. Number of Ways to Assign Edge Weights II](https://leetcode.com/problems/number-of-ways-to-assign-edge-weights-ii)

[中文文档](/solution/3500-3599/3559.Number%20of%20Ways%20to%20Assign%20Edge%20Weights%20II/README.md)

## Description

<!-- description:start -->

<p>There is an undirected tree with <code>n</code> nodes labeled from 1 to <code>n</code>, rooted at node 1. The tree is represented by a 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates that there is an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>.</p>

<p>Initially, all edges have a weight of 0. You must assign each edge a weight of either <strong>1</strong> or <strong>2</strong>.</p>

<p>The <strong>cost</strong> of a path between any two nodes <code>u</code> and <code>v</code> is the total weight of all edges in the path connecting them.</p>

<p>You are given a 2D integer array <code>queries</code>. For each <code>queries[i] = [u<sub>i</sub>, v<sub>i</sub>]</code>, determine the number of ways to assign weights to edges <strong>in the path</strong> such that the cost of the path between <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> is <strong>odd</strong>.</p>

<p>Return an array <code>answer</code>, where <code>answer[i]</code> is the number of valid assignments for <code>queries[i]</code>.</p>

<p>Since the answer may be large, apply <strong>modulo</strong> <code>10<sup>9</sup> + 7</code> to each <code>answer[i]</code>.</p>

<p><strong>Note:</strong> For each query, disregard all edges <strong>not</strong> in the path between node <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3559.Number%20of%20Ways%20to%20Assign%20Edge%20Weights%20II/images/screenshot-2025-03-24-at-060006.png" style="height: 72px; width: 200px;" /></p>

<p><strong>Input:</strong> <span class="example-io">edges = [[1,2]], queries = [[1,1],[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,1]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Query <code>[1,1]</code>: The path from Node 1 to itself consists of no edges, so the cost is 0. Thus, the number of valid assignments is 0.</li>
	<li>Query <code>[1,2]</code>: The path from Node 1 to Node 2 consists of one edge (<code>1 &rarr; 2</code>). Assigning weight 1 makes the cost odd, while 2 makes it even. Thus, the number of valid assignments is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3559.Number%20of%20Ways%20to%20Assign%20Edge%20Weights%20II/images/screenshot-2025-03-24-at-055820.png" style="height: 207px; width: 220px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[1,2],[1,3],[3,4],[3,5]], queries = [[1,4],[3,4],[2,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,1,4]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Query <code>[1,4]</code>: The path from Node 1 to Node 4 consists of two edges (<code>1 &rarr; 3</code> and <code>3 &rarr; 4</code>). Assigning weights (1,2) or (2,1) results in an odd cost. Thus, the number of valid assignments is 2.</li>
	<li>Query <code>[3,4]</code>: The path from Node 3 to Node 4 consists of one edge (<code>3 &rarr; 4</code>). Assigning weight 1 makes the cost odd, while 2 makes it even. Thus, the number of valid assignments is 1.</li>
	<li>Query <code>[2,5]</code>: The path from Node 2 to Node 5 consists of three edges (<code>2 &rarr; 1, 1 &rarr; 3</code>, and <code>3 &rarr; 5</code>). Assigning (1,2,2), (2,1,2), (2,2,1), or (1,1,1) makes the cost odd. Thus, the number of valid assignments is 4.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i] == [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i] == [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n</code></li>
	<li><code>edges</code> represents a valid tree.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: LCA + Math

The path $u \to v$ has $d = \textit{depth}[u] + \textit{depth}[v] - 2 \cdot \textit{depth}[\textit{lca}]$ edges, and each edge can be assigned weight $1$ or $2$. The cost is odd if and only if an odd number of edges have weight $1$. The number of ways to choose an odd number of edges out of $d$ is $2^{d-1}$ (or $0$ if $d = 0$).

Use BFS to compute the depth and parent of each node, then binary lifting to preprocess LCAs. Precompute powers of $2$ so each query can be answered in $O(\log n)$.

The time complexity is $O((n + q) \times \log n)$, and the space complexity is $O(n \times \log n)$, where $n$ is the number of nodes and $q$ is the number of queries.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def assignEdgeWeights(
        self, edges: List[List[int]], queries: List[List[int]]
    ) -> List[int]:
        n = len(edges) + 1
        m = n.bit_length()
        g = [[] for _ in range(n + 1)]
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        f = [[0] * m for _ in range(n + 1)]
        p = [0] * (n + 1)
        depth = [0] * (n + 1)
        q = deque([1])
        while q:
            i = q.popleft()
            f[i][0] = p[i]
            for j in range(1, m):
                f[i][j] = f[f[i][j - 1]][j - 1]
            for j in g[i]:
                if j != p[i]:
                    p[j] = i
                    depth[j] = depth[i] + 1
                    q.append(j)
        mod = 10**9 + 7
        pow2 = [1] * n
        for i in range(1, n):
            pow2[i] = pow2[i - 1] * 2 % mod
        ans = []
        for u, v in queries:
            x, y = u, v
            if depth[x] < depth[y]:
                x, y = y, x
            for j in range(m - 1, -1, -1):
                if depth[x] - depth[y] >= (1 << j):
                    x = f[x][j]
            for j in range(m - 1, -1, -1):
                if f[x][j] != f[y][j]:
                    x, y = f[x][j], f[y][j]
            if x != y:
                x = p[x]
            d = depth[u] + depth[v] - 2 * depth[x]
            ans.append(0 if d == 0 else pow2[d - 1])
        return ans
```

#### Java

```java
class Solution {
    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        int m = 32 - Integer.numberOfLeadingZeros(n);
        List<Integer>[] g = new List[n + 1];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        int[][] f = new int[n + 1][m];
        int[] p = new int[n + 1];
        int[] depth = new int[n + 1];
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(1);
        while (!q.isEmpty()) {
            int i = q.poll();
            f[i][0] = p[i];
            for (int j = 1; j < m; ++j) {
                f[i][j] = f[f[i][j - 1]][j - 1];
            }
            for (int j : g[i]) {
                if (j != p[i]) {
                    p[j] = i;
                    depth[j] = depth[i] + 1;
                    q.offer(j);
                }
            }
        }
        final int mod = (int) 1e9 + 7;
        int[] pow2 = new int[n];
        pow2[0] = 1;
        for (int i = 1; i < n; ++i) {
            pow2[i] = (int) (pow2[i - 1] * 2L % mod);
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int u = queries[i][0], v = queries[i][1];
            int x = u, y = v;
            if (depth[x] < depth[y]) {
                int t = x;
                x = y;
                y = t;
            }
            for (int j = m - 1; j >= 0; --j) {
                if (depth[x] - depth[y] >= (1 << j)) {
                    x = f[x][j];
                }
            }
            for (int j = m - 1; j >= 0; --j) {
                if (f[x][j] != f[y][j]) {
                    x = f[x][j];
                    y = f[y][j];
                }
            }
            if (x != y) {
                x = p[x];
            }
            int d = depth[u] + depth[v] - 2 * depth[x];
            ans[i] = d == 0 ? 0 : pow2[d - 1];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> assignEdgeWeights(vector<vector<int>>& edges, vector<vector<int>>& queries) {
        int n = edges.size() + 1;
        int m = 32 - __builtin_clz(n);
        vector<vector<int>> g(n + 1);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        vector<vector<int>> f(n + 1, vector<int>(m));
        vector<int> p(n + 1), depth(n + 1);
        queue<int> q;
        q.push(1);
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            f[i][0] = p[i];
            for (int j = 1; j < m; ++j) {
                f[i][j] = f[f[i][j - 1]][j - 1];
            }
            for (int j : g[i]) {
                if (j != p[i]) {
                    p[j] = i;
                    depth[j] = depth[i] + 1;
                    q.push(j);
                }
            }
        }
        const int mod = 1e9 + 7;
        vector<int> pow2(n, 1);
        for (int i = 1; i < n; ++i) {
            pow2[i] = pow2[i - 1] * 2 % mod;
        }
        vector<int> ans;
        for (auto& qq : queries) {
            int u = qq[0], v = qq[1];
            int x = u, y = v;
            if (depth[x] < depth[y]) {
                swap(x, y);
            }
            for (int j = m - 1; ~j; --j) {
                if (depth[x] - depth[y] >= (1 << j)) {
                    x = f[x][j];
                }
            }
            for (int j = m - 1; ~j; --j) {
                if (f[x][j] != f[y][j]) {
                    x = f[x][j];
                    y = f[y][j];
                }
            }
            if (x != y) {
                x = p[x];
            }
            int d = depth[u] + depth[v] - 2 * depth[x];
            ans.push_back(d == 0 ? 0 : pow2[d - 1]);
        }
        return ans;
    }
};
```

#### Go

```go
func assignEdgeWeights(edges [][]int, queries [][]int) []int {
	n := len(edges) + 1
	m := bits.Len(uint(n))
	g := make([][]int, n+1)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, m)
	}
	p := make([]int, n+1)
	depth := make([]int, n+1)
	q := []int{1}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		f[i][0] = p[i]
		for j := 1; j < m; j++ {
			f[i][j] = f[f[i][j-1]][j-1]
		}
		for _, j := range g[i] {
			if j != p[i] {
				p[j] = i
				depth[j] = depth[i] + 1
				q = append(q, j)
			}
		}
	}
	const mod = int(1e9 + 7)
	pow2 := make([]int, n)
	pow2[0] = 1
	for i := 1; i < n; i++ {
		pow2[i] = pow2[i-1] * 2 % mod
	}
	ans := make([]int, len(queries))
	for i, qq := range queries {
		u, v := qq[0], qq[1]
		x, y := u, v
		if depth[x] < depth[y] {
			x, y = y, x
		}
		for j := m - 1; j >= 0; j-- {
			if depth[x]-depth[y] >= 1<<j {
				x = f[x][j]
			}
		}
		for j := m - 1; j >= 0; j-- {
			if f[x][j] != f[y][j] {
				x, y = f[x][j], f[y][j]
			}
		}
		if x != y {
			x = p[x]
		}
		d := depth[u] + depth[v] - 2*depth[x]
		if d > 0 {
			ans[i] = pow2[d-1]
		}
	}
	return ans
}
```

#### C

```c
int* assignEdgeWeights(int** edges, int edgesSize, int* edgesColSize, int** queries,
    int queriesSize, int* queriesColSize, int* returnSize) {
    int n = edgesSize + 1;
    int m = 32 - __builtin_clz(n);
    int* cnt = calloc(n + 1, sizeof(int));
    for (int i = 0; i < edgesSize; ++i) {
        ++cnt[edges[i][0]];
        ++cnt[edges[i][1]];
    }
    int** g = malloc((n + 1) * sizeof(int*));
    for (int i = 1; i <= n; ++i) {
        g[i] = malloc(cnt[i] * sizeof(int));
        cnt[i] = 0;
    }
    for (int i = 0; i < edgesSize; ++i) {
        int u = edges[i][0], v = edges[i][1];
        g[u][cnt[u]++] = v;
        g[v][cnt[v]++] = u;
    }
    int* f = calloc((n + 1) * m, sizeof(int));
    int* p = calloc(n + 1, sizeof(int));
    int* depth = calloc(n + 1, sizeof(int));
    int* que = malloc(n * sizeof(int));
    int head = 0, tail = 0;
    que[tail++] = 1;
    while (head < tail) {
        int i = que[head++];
        f[i * m] = p[i];
        for (int j = 1; j < m; ++j) {
            f[i * m + j] = f[f[i * m + j - 1] * m + j - 1];
        }
        for (int k = 0; k < cnt[i]; ++k) {
            int j = g[i][k];
            if (j != p[i]) {
                p[j] = i;
                depth[j] = depth[i] + 1;
                que[tail++] = j;
            }
        }
    }
    const int mod = 1e9 + 7;
    int* pow2 = malloc(n * sizeof(int));
    pow2[0] = 1;
    for (int i = 1; i < n; ++i) {
        pow2[i] = pow2[i - 1] * 2 % mod;
    }
    int* ans = malloc(queriesSize * sizeof(int));
    for (int t = 0; t < queriesSize; ++t) {
        int u = queries[t][0], v = queries[t][1];
        int x = u, y = v;
        if (depth[x] < depth[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }
        for (int j = m - 1; j >= 0; --j) {
            if (depth[x] - depth[y] >= (1 << j)) {
                x = f[x * m + j];
            }
        }
        for (int j = m - 1; j >= 0; --j) {
            if (f[x * m + j] != f[y * m + j]) {
                x = f[x * m + j];
                y = f[y * m + j];
            }
        }
        if (x != y) {
            x = p[x];
        }
        int d = depth[u] + depth[v] - 2 * depth[x];
        ans[t] = d == 0 ? 0 : pow2[d - 1];
    }
    for (int i = 1; i <= n; ++i) {
        free(g[i]);
    }
    free(g);
    free(cnt);
    free(f);
    free(p);
    free(depth);
    free(que);
    free(pow2);
    *returnSize = queriesSize;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
