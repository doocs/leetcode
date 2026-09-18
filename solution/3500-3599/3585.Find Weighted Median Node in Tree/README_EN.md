---
comments: true
difficulty: Hard
rating: 2428
source: Weekly Contest 454 Q4
tags:
    - Bit Manipulation
    - Tree
    - Depth-First Search
    - Array
    - Binary Search
    - Dynamic Programming
---

<!-- problem:start -->

# [3585. Find Weighted Median Node in Tree](https://leetcode.com/problems/find-weighted-median-node-in-tree)

[中文文档](/solution/3500-3599/3585.Find%20Weighted%20Median%20Node%20in%20Tree/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> and an <strong>undirected, weighted</strong> tree rooted at node 0 with <code>n</code> nodes numbered from 0 to <code>n - 1</code>. This is represented by a 2D array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> indicates an edge from node <code>u<sub>i</sub></code> to <code>v<sub>i</sub></code> with weight <code>w<sub>i</sub></code>.</p>

<p>The <strong>weighted median node</strong> is defined as the <strong>first</strong> node <code>x</code> on the path from <code>u<sub>i</sub></code> to <code>v<sub>i</sub></code> such that the sum of edge weights from <code>u<sub>i</sub></code> to <code>x</code> is <strong>greater than or equal to half</strong> of the total path weight.</p>

<p>You are given a 2D integer array <code>queries</code>. For each <code>queries[j] = [u<sub>j</sub>, v<sub>j</sub>]</code>, determine the weighted median node along the path from <code>u<sub>j</sub></code> to <code>v<sub>j</sub></code>.</p>

<p>Return an array <code>ans</code>, where <code>ans[j]</code> is the node index of the weighted median for <code>queries[j]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, edges = [[0,1,7]], queries = [[1,0],[0,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,1]</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3585.Find%20Weighted%20Median%20Node%20in%20Tree/images/screenshot-2025-05-26-at-193447.png" style="width: 200px; height: 64px;" /></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Query</th>
			<th style="border: 1px solid black;">Path</th>
			<th style="border: 1px solid black;">Edge<br />
			Weights</th>
			<th style="border: 1px solid black;">Total<br />
			Path<br />
			Weight</th>
			<th style="border: 1px solid black;">Half</th>
			<th style="border: 1px solid black;">Explanation</th>
			<th style="border: 1px solid black;">Answer</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;"><code>[1, 0]</code></td>
			<td style="border: 1px solid black;"><code>1 &rarr; 0</code></td>
			<td style="border: 1px solid black;"><code>[7]</code></td>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">3.5</td>
			<td style="border: 1px solid black;">Sum from <code>1 &rarr; 0 = 7 &gt;= 3.5</code>, median is node 0.</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[0, 1]</code></td>
			<td style="border: 1px solid black;"><code>0 &rarr; 1</code></td>
			<td style="border: 1px solid black;"><code>[7]</code></td>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">3.5</td>
			<td style="border: 1px solid black;">Sum from <code>0 &rarr; 1 = 7 &gt;= 3.5</code>, median is node 1.</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1,2],[2,0,4]], queries = [[0,1],[2,0],[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,0,2]</span></p>

<p><strong>E</strong><strong>xplanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3585.Find%20Weighted%20Median%20Node%20in%20Tree/images/screenshot-2025-05-26-at-193610.png" style="width: 180px; height: 149px;" /></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Query</th>
			<th style="border: 1px solid black;">Path</th>
			<th style="border: 1px solid black;">Edge<br />
			Weights</th>
			<th style="border: 1px solid black;">Total<br />
			Path<br />
			Weight</th>
			<th style="border: 1px solid black;">Half</th>
			<th style="border: 1px solid black;">Explanation</th>
			<th style="border: 1px solid black;">Answer</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;"><code>[0, 1]</code></td>
			<td style="border: 1px solid black;"><code>0 &rarr; 1</code></td>
			<td style="border: 1px solid black;"><code>[2]</code></td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">Sum from <code>0 &rarr; 1 = 2 &gt;= 1</code>, median is node 1.</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[2, 0]</code></td>
			<td style="border: 1px solid black;"><code>2 &rarr; 0</code></td>
			<td style="border: 1px solid black;"><code>[4]</code></td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">Sum from <code>2 &rarr; 0 = 4 &gt;= 2</code>, median is node 0.</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1, 2]</code></td>
			<td style="border: 1px solid black;"><code>1 &rarr; 0 &rarr; 2</code></td>
			<td style="border: 1px solid black;"><code>[2, 4]</code></td>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">Sum from <code>1 &rarr; 0 = 2 &lt; 3</code>.<br />
			Sum from <code>1 &rarr; 2 = 2 + 4 = 6 &gt;= 3</code>, median is node 2.</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, edges = [[0,1,2],[0,2,5],[1,3,1],[2,4,3]], queries = [[3,4],[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,2]</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3585.Find%20Weighted%20Median%20Node%20in%20Tree/images/screenshot-2025-05-26-at-193857.png" style="width: 150px; height: 229px;" /></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Query</th>
			<th style="border: 1px solid black;">Path</th>
			<th style="border: 1px solid black;">Edge<br />
			Weights</th>
			<th style="border: 1px solid black;">Total<br />
			Path<br />
			Weight</th>
			<th style="border: 1px solid black;">Half</th>
			<th style="border: 1px solid black;">Explanation</th>
			<th style="border: 1px solid black;">Answer</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;"><code>[3, 4]</code></td>
			<td style="border: 1px solid black;"><code>3 &rarr; 1 &rarr; 0 &rarr; 2 &rarr; 4</code></td>
			<td style="border: 1px solid black;"><code>[1, 2, 5, 3]</code></td>
			<td style="border: 1px solid black;">11</td>
			<td style="border: 1px solid black;">5.5</td>
			<td style="border: 1px solid black;">Sum from <code>3 &rarr; 1 = 1 &lt; 5.5</code>.<br />
			Sum from <code>3 &rarr; 0 = 1 + 2 = 3 &lt; 5.5</code>.<br />
			Sum from <code>3 &rarr; 2 = 1 + 2 + 5 = 8 &gt;= 5.5</code>, median is node 2.</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1, 2]</code></td>
			<td style="border: 1px solid black;"><code>1 &rarr; 0 &rarr; 2</code></td>
			<td style="border: 1px solid black;"><code>[2, 5]</code></td>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">3.5</td>
			<td style="border: 1px solid black;">
			<p>Sum from <code>1 &rarr; 0 = 2 &lt; 3.5</code>.<br />
			Sum from <code>1 &rarr; 2 = 2 + 5 = 7 &gt;= 3.5</code>, median is node 2.</p>
			</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
	</tbody>
</table>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i] == [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[j] == [u<sub>j</sub>, v<sub>j</sub>]</code></li>
	<li><code>0 &lt;= u<sub>j</sub>, v<sub>j</sub> &lt; n</code></li>
	<li>The input is generated such that <code>edges</code> represents a valid tree.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: LCA + Binary Lifting

<!-- thinking:start -->

> **Thinking**
>
> The weighted median is the first vertex on $u \to v$ whose prefix weight from $u$ is at least half the path. $n,q \le 10^5$ call for LCA and weighted prefixes.
>
> After $lca$ and the total $W$, binary-lift on $u \to lca$ or $lca \to v$ to the farthest node whose prefix is still $< W/2$, then take one more step.

<!-- thinking:end -->

Root the tree at node $0$. Use BFS to compute each node's depth $\textit{depth}$, parent $p$, and weighted distance $\textit{dist}$ from the root, and build the binary lifting table $f[i][j]$ as the $2^j$-th ancestor of $i$.

For a query $(u, v)$: if $u = v$, the answer is $u$. Otherwise let $x = \textit{lca}(u, v)$ and $W = \textit{dist}[u] + \textit{dist}[v] - 2 \cdot \textit{dist}[x]$. The weighted median is the first node on the path starting from $u$ whose prefix weight is at least $W / 2$. We compare $2 \cdot \textit{pref} \ge W$ to avoid floating-point arithmetic.

- If $2 \cdot (\textit{dist}[u] - \textit{dist}[x]) \ge W$, the median lies on $u \to x$ (including $x$). Lift from $u$ to the farthest ancestor $k$ that still satisfies $2 \cdot (\textit{dist}[u] - \textit{dist}[k]) < W$, then take one parent step to $p[k]$.
- Otherwise the median lies on $x \to v$ (excluding $x$). Lift from $v$ to the highest node whose depth is greater than $x$ and $2 \cdot (\textit{dist}[u] + \textit{dist}[k] - 2 \cdot \textit{dist}[x]) \ge W$.

The time complexity is $O((n + q) \times \log n)$, and the space complexity is $O(n \times \log n)$, where $n$ is the number of nodes and $q$ is the number of queries.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMedian(
        self, n: int, edges: List[List[int]], queries: List[List[int]]
    ) -> List[int]:
        m = n.bit_length()
        g = [[] for _ in range(n)]
        for u, v, w in edges:
            g[u].append((v, w))
            g[v].append((u, w))
        f = [[0] * m for _ in range(n)]
        p = [0] * n
        depth = [0] * n
        dist = [0] * n
        q = deque([0])
        while q:
            i = q.popleft()
            f[i][0] = p[i]
            for j in range(1, m):
                f[i][j] = f[f[i][j - 1]][j - 1]
            for j, w in g[i]:
                if j != p[i]:
                    p[j] = i
                    depth[j] = depth[i] + 1
                    dist[j] = dist[i] + w
                    q.append(j)
        ans = []
        for u, v in queries:
            if u == v:
                ans.append(u)
                continue
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
            w = dist[u] + dist[v] - 2 * dist[x]
            if 2 * (dist[u] - dist[x]) >= w:
                cur = u
                for j in range(m - 1, -1, -1):
                    k = f[cur][j]
                    if depth[k] >= depth[x] and 2 * (dist[u] - dist[k]) < w:
                        cur = k
                ans.append(p[cur])
            else:
                cur = v
                for j in range(m - 1, -1, -1):
                    k = f[cur][j]
                    if (
                        depth[k] > depth[x]
                        and 2 * (dist[u] + dist[k] - 2 * dist[x]) >= w
                    ):
                        cur = k
                ans.append(cur)
        return ans
```

#### Java

```java
class Solution {
    public int[] findMedian(int n, int[][] edges, int[][] queries) {
        int m = 32 - Integer.numberOfLeadingZeros(n);
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].add(new int[] {v, w});
            g[v].add(new int[] {u, w});
        }
        int[][] f = new int[n][m];
        int[] p = new int[n];
        int[] depth = new int[n];
        long[] dist = new long[n];
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        while (!q.isEmpty()) {
            int i = q.poll();
            f[i][0] = p[i];
            for (int j = 1; j < m; ++j) {
                f[i][j] = f[f[i][j - 1]][j - 1];
            }
            for (var nxt : g[i]) {
                int j = nxt[0], w = nxt[1];
                if (j != p[i]) {
                    p[j] = i;
                    depth[j] = depth[i] + 1;
                    dist[j] = dist[i] + w;
                    q.offer(j);
                }
            }
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int u = queries[i][0], v = queries[i][1];
            if (u == v) {
                ans[i] = u;
                continue;
            }
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
            long w = dist[u] + dist[v] - 2 * dist[x];
            if (2 * (dist[u] - dist[x]) >= w) {
                int cur = u;
                for (int j = m - 1; j >= 0; --j) {
                    int k = f[cur][j];
                    if (depth[k] >= depth[x] && 2 * (dist[u] - dist[k]) < w) {
                        cur = k;
                    }
                }
                ans[i] = p[cur];
            } else {
                int cur = v;
                for (int j = m - 1; j >= 0; --j) {
                    int k = f[cur][j];
                    if (depth[k] > depth[x] && 2 * (dist[u] + dist[k] - 2 * dist[x]) >= w) {
                        cur = k;
                    }
                }
                ans[i] = cur;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> findMedian(int n, vector<vector<int>>& edges, vector<vector<int>>& queries) {
        int m = 32 - __builtin_clz(n);
        vector<vector<pair<int, int>>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].emplace_back(v, w);
            g[v].emplace_back(u, w);
        }
        vector<vector<int>> f(n, vector<int>(m));
        vector<int> p(n), depth(n);
        vector<long long> dist(n);
        queue<int> q;
        q.push(0);
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            f[i][0] = p[i];
            for (int j = 1; j < m; ++j) {
                f[i][j] = f[f[i][j - 1]][j - 1];
            }
            for (auto [j, w] : g[i]) {
                if (j != p[i]) {
                    p[j] = i;
                    depth[j] = depth[i] + 1;
                    dist[j] = dist[i] + w;
                    q.push(j);
                }
            }
        }
        vector<int> ans;
        for (auto& qq : queries) {
            int u = qq[0], v = qq[1];
            if (u == v) {
                ans.push_back(u);
                continue;
            }
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
            long long w = dist[u] + dist[v] - 2 * dist[x];
            if (2 * (dist[u] - dist[x]) >= w) {
                int cur = u;
                for (int j = m - 1; ~j; --j) {
                    int k = f[cur][j];
                    if (depth[k] >= depth[x] && 2 * (dist[u] - dist[k]) < w) {
                        cur = k;
                    }
                }
                ans.push_back(p[cur]);
            } else {
                int cur = v;
                for (int j = m - 1; ~j; --j) {
                    int k = f[cur][j];
                    if (depth[k] > depth[x] && 2 * (dist[u] + dist[k] - 2 * dist[x]) >= w) {
                        cur = k;
                    }
                }
                ans.push_back(cur);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findMedian(n int, edges [][]int, queries [][]int) []int {
	m := bits.Len(uint(n))
	g := make([][][2]int, n)
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		g[u] = append(g[u], [2]int{v, w})
		g[v] = append(g[v], [2]int{u, w})
	}
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, m)
	}
	p := make([]int, n)
	depth := make([]int, n)
	dist := make([]int, n)
	q := []int{0}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		f[i][0] = p[i]
		for j := 1; j < m; j++ {
			f[i][j] = f[f[i][j-1]][j-1]
		}
		for _, nxt := range g[i] {
			j, w := nxt[0], nxt[1]
			if j != p[i] {
				p[j] = i
				depth[j] = depth[i] + 1
				dist[j] = dist[i] + w
				q = append(q, j)
			}
		}
	}
	ans := make([]int, len(queries))
	for i, qq := range queries {
		u, v := qq[0], qq[1]
		if u == v {
			ans[i] = u
			continue
		}
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
		w := dist[u] + dist[v] - 2*dist[x]
		if 2*(dist[u]-dist[x]) >= w {
			cur := u
			for j := m - 1; j >= 0; j-- {
				k := f[cur][j]
				if depth[k] >= depth[x] && 2*(dist[u]-dist[k]) < w {
					cur = k
				}
			}
			ans[i] = p[cur]
		} else {
			cur := v
			for j := m - 1; j >= 0; j-- {
				k := f[cur][j]
				if depth[k] > depth[x] && 2*(dist[u]+dist[k]-2*dist[x]) >= w {
					cur = k
				}
			}
			ans[i] = cur
		}
	}
	return ans
}
```

#### TypeScript

```ts
function findMedian(n: number, edges: number[][], queries: number[][]): number[] {
    const m = 32 - Math.clz32(n);
    const g: number[][][] = Array.from({ length: n }, () => []);
    for (const [u, v, w] of edges) {
        g[u].push([v, w]);
        g[v].push([u, w]);
    }
    const f: number[][] = Array.from({ length: n }, () => Array(m).fill(0));
    const p: number[] = Array(n).fill(0);
    const depth: number[] = Array(n).fill(0);
    const dist: number[] = Array(n).fill(0);
    const q: number[] = [0];
    for (let qq = 0; qq < q.length; ++qq) {
        const i = q[qq];
        f[i][0] = p[i];
        for (let j = 1; j < m; ++j) {
            f[i][j] = f[f[i][j - 1]][j - 1];
        }
        for (const [j, w] of g[i]) {
            if (j !== p[i]) {
                p[j] = i;
                depth[j] = depth[i] + 1;
                dist[j] = dist[i] + w;
                q.push(j);
            }
        }
    }
    const ans: number[] = [];
    for (const [u, v] of queries) {
        if (u === v) {
            ans.push(u);
            continue;
        }
        let x = u,
            y = v;
        if (depth[x] < depth[y]) {
            [x, y] = [y, x];
        }
        for (let j = m - 1; j >= 0; --j) {
            if (depth[x] - depth[y] >= 1 << j) {
                x = f[x][j];
            }
        }
        for (let j = m - 1; j >= 0; --j) {
            if (f[x][j] !== f[y][j]) {
                x = f[x][j];
                y = f[y][j];
            }
        }
        if (x !== y) {
            x = p[x];
        }
        const w = dist[u] + dist[v] - 2 * dist[x];
        if (2 * (dist[u] - dist[x]) >= w) {
            let cur = u;
            for (let j = m - 1; j >= 0; --j) {
                const k = f[cur][j];
                if (depth[k] >= depth[x] && 2 * (dist[u] - dist[k]) < w) {
                    cur = k;
                }
            }
            ans.push(p[cur]);
        } else {
            let cur = v;
            for (let j = m - 1; j >= 0; --j) {
                const k = f[cur][j];
                if (depth[k] > depth[x] && 2 * (dist[u] + dist[k] - 2 * dist[x]) >= w) {
                    cur = k;
                }
            }
            ans.push(cur);
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::VecDeque;

impl Solution {
    pub fn find_median(n: i32, edges: Vec<Vec<i32>>, queries: Vec<Vec<i32>>) -> Vec<i32> {
        let n = n as usize;
        let m = 32 - (n as u32).leading_zeros() as usize;
        let mut g = vec![vec![]; n];
        for e in &edges {
            let u = e[0] as usize;
            let v = e[1] as usize;
            let w = e[2] as i64;
            g[u].push((v, w));
            g[v].push((u, w));
        }
        let mut f = vec![vec![0; m]; n];
        let mut p = vec![0; n];
        let mut depth = vec![0; n];
        let mut dist = vec![0i64; n];
        let mut q = VecDeque::new();
        q.push_back(0);
        while let Some(i) = q.pop_front() {
            f[i][0] = p[i];
            for j in 1..m {
                f[i][j] = f[f[i][j - 1]][j - 1];
            }
            for &(j, w) in &g[i] {
                if j != p[i] {
                    p[j] = i;
                    depth[j] = depth[i] + 1;
                    dist[j] = dist[i] + w;
                    q.push_back(j);
                }
            }
        }
        let mut ans = Vec::with_capacity(queries.len());
        for qq in &queries {
            let u = qq[0] as usize;
            let v = qq[1] as usize;
            if u == v {
                ans.push(u as i32);
                continue;
            }
            let (mut x, mut y) = (u, v);
            if depth[x] < depth[y] {
                std::mem::swap(&mut x, &mut y);
            }
            for j in (0..m).rev() {
                if depth[x] - depth[y] >= (1 << j) {
                    x = f[x][j];
                }
            }
            for j in (0..m).rev() {
                if f[x][j] != f[y][j] {
                    x = f[x][j];
                    y = f[y][j];
                }
            }
            if x != y {
                x = p[x];
            }
            let w = dist[u] + dist[v] - 2 * dist[x];
            if 2 * (dist[u] - dist[x]) >= w {
                let mut cur = u;
                for j in (0..m).rev() {
                    let k = f[cur][j];
                    if depth[k] >= depth[x] && 2 * (dist[u] - dist[k]) < w {
                        cur = k;
                    }
                }
                ans.push(p[cur] as i32);
            } else {
                let mut cur = v;
                for j in (0..m).rev() {
                    let k = f[cur][j];
                    if depth[k] > depth[x] && 2 * (dist[u] + dist[k] - 2 * dist[x]) >= w {
                        cur = k;
                    }
                }
                ans.push(cur as i32);
            }
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int[] FindMedian(int n, int[][] edges, int[][] queries) {
        int m = 32 - BitOperations.LeadingZeroCount((uint)n);
        List<int[]>[] g = new List<int[]>[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new List<int[]>();
        }
        foreach (var e in edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].Add(new int[] { v, w });
            g[v].Add(new int[] { u, w });
        }
        int[][] f = new int[n][];
        for (int i = 0; i < n; ++i) {
            f[i] = new int[m];
        }
        int[] p = new int[n];
        int[] depth = new int[n];
        long[] dist = new long[n];
        Queue<int> q = new Queue<int>();
        q.Enqueue(0);
        while (q.Count > 0) {
            int i = q.Dequeue();
            f[i][0] = p[i];
            for (int j = 1; j < m; ++j) {
                f[i][j] = f[f[i][j - 1]][j - 1];
            }
            foreach (var nxt in g[i]) {
                int j = nxt[0], w = nxt[1];
                if (j != p[i]) {
                    p[j] = i;
                    depth[j] = depth[i] + 1;
                    dist[j] = dist[i] + w;
                    q.Enqueue(j);
                }
            }
        }
        int[] ans = new int[queries.Length];
        for (int i = 0; i < queries.Length; ++i) {
            int u = queries[i][0], v = queries[i][1];
            if (u == v) {
                ans[i] = u;
                continue;
            }
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
            long w = dist[u] + dist[v] - 2 * dist[x];
            if (2 * (dist[u] - dist[x]) >= w) {
                int cur = u;
                for (int j = m - 1; j >= 0; --j) {
                    int k = f[cur][j];
                    if (depth[k] >= depth[x] && 2 * (dist[u] - dist[k]) < w) {
                        cur = k;
                    }
                }
                ans[i] = p[cur];
            } else {
                int cur = v;
                for (int j = m - 1; j >= 0; --j) {
                    int k = f[cur][j];
                    if (depth[k] > depth[x] && 2 * (dist[u] + dist[k] - 2 * dist[x]) >= w) {
                        cur = k;
                    }
                }
                ans[i] = cur;
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
