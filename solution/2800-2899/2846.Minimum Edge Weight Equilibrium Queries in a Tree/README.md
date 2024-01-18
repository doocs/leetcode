# [2846. 边权重均等查询](https://leetcode.cn/problems/minimum-edge-weight-equilibrium-queries-in-a-tree)

[English Version](/solution/2800-2899/2846.Minimum%20Edge%20Weight%20Equilibrium%20Queries%20in%20a%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现有一棵由 <code>n</code> 个节点组成的无向树，节点按从 <code>0</code> 到 <code>n - 1</code> 编号。给你一个整数 <code>n</code> 和一个长度为 <code>n - 1</code> 的二维整数数组 <code>edges</code> ，其中 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> 表示树中存在一条位于节点 <code>u<sub>i</sub></code> 和节点 <code>v<sub>i</sub></code> 之间、权重为 <code>w<sub>i</sub></code> 的边。</p>

<p>另给你一个长度为 <code>m</code> 的二维整数数组 <code>queries</code> ，其中 <code>queries[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 。对于每条查询，请你找出使从 <code>a<sub>i</sub></code> 到 <code>b<sub>i</sub></code> 路径上每条边的权重相等所需的 <strong>最小操作次数</strong> 。在一次操作中，你可以选择树上的任意一条边，并将其权重更改为任意值。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>查询之间 <strong>相互独立</strong> 的，这意味着每条新的查询时，树都会回到 <strong>初始状态</strong> 。</li>
	<li>从 <code>a<sub>i</sub></code> 到 <code>b<sub>i</sub></code>的路径是一个由 <strong>不同</strong> 节点组成的序列，从节点 <code>a<sub>i</sub></code> 开始，到节点 <code>b<sub>i</sub></code> 结束，且序列中相邻的两个节点在树中共享一条边。</li>
</ul>

<p>返回一个长度为 <code>m</code> 的数组 <code>answer</code> ，其中 <code>answer[i]</code> 是第 <code>i</code> 条查询的答案。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2846.Minimum%20Edge%20Weight%20Equilibrium%20Queries%20in%20a%20Tree/images/graph-6-1.png" style="width: 339px; height: 344px;" />
<pre>
<strong>输入：</strong>n = 7, edges = [[0,1,1],[1,2,1],[2,3,1],[3,4,2],[4,5,2],[5,6,2]], queries = [[0,3],[3,6],[2,6],[0,6]]
<strong>输出：</strong>[0,0,1,3]
<strong>解释：</strong>第 1 条查询，从节点 0 到节点 3 的路径中的所有边的权重都是 1 。因此，答案为 0 。
第 2 条查询，从节点 3 到节点 6 的路径中的所有边的权重都是 2 。因此，答案为 0 。
第 3 条查询，将边 [2,3] 的权重变更为 2 。在这次操作之后，从节点 2 到节点 6 的路径中的所有边的权重都是 2 。因此，答案为 1 。
第 4 条查询，将边 [0,1]、[1,2]、[2,3] 的权重变更为 2 。在这次操作之后，从节点 0 到节点 6 的路径中的所有边的权重都是 2 。因此，答案为 3 。
对于每条查询 queries[i] ，可以证明 answer[i] 是使从 a<sub>i</sub> 到 b<sub>i</sub> 的路径中的所有边的权重相等的最小操作次数。
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2846.Minimum%20Edge%20Weight%20Equilibrium%20Queries%20in%20a%20Tree/images/graph-9-1.png" style="width: 472px; height: 370px;" />
<pre>
<strong>输入：</strong>n = 8, edges = [[1,2,6],[1,3,4],[2,4,6],[2,5,3],[3,6,6],[3,0,8],[7,0,2]], queries = [[4,6],[0,4],[6,5],[7,4]]
<strong>输出：</strong>[1,2,2,3]
<strong>解释：</strong>第 1 条查询，将边 [1,3] 的权重变更为 6 。在这次操作之后，从节点 4 到节点 6 的路径中的所有边的权重都是 6 。因此，答案为 1 。
第 2 条查询，将边 [0,3]、[3,1] 的权重变更为 6 。在这次操作之后，从节点 0 到节点 4 的路径中的所有边的权重都是 6 。因此，答案为 2 。
第 3 条查询，将边 [1,3]、[5,2] 的权重变更为 6 。在这次操作之后，从节点 6 到节点 5 的路径中的所有边的权重都是 6 。因此，答案为 2 。
第 4 条查询，将边 [0,7]、[0,3]、[1,3] 的权重变更为 6 。在这次操作之后，从节点 7 到节点 4 的路径中的所有边的权重都是 6 。因此，答案为 3 。
对于每条查询 queries[i] ，可以证明 answer[i] 是使从 a<sub>i</sub> 到 b<sub>i</sub> 的路径中的所有边的权重相等的最小操作次数。 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= 26</code></li>
	<li>生成的输入满足 <code>edges</code> 表示一棵有效的树</li>
	<li><code>1 &lt;= queries.length == m &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
</ul>

## 解法

### 方法一：倍增法求 LCA

题目求的是任意两点的路径上，将其所有边的权重变成相同值的最小操作次数。实际上就是求这两点之间的路径长度，减去路径上出现次数最多的边的次数。

而求两点间的路径长度，可以通过倍增法求 LCA 来实现。我们记两点分别为 $u$ 和 $v$，最近公共祖先为 $x$，那么 $u$ 到 $v$ 的路径长度就是 $depth(u) + depth(v) - 2 \times depth(x)$。

另外，我们可以用一个数组 $cnt[n][26]$ 记录根节点到每个节点上，每个边权重出现的次数。那么 $u$ 到 $v$ 的路径上，出现次数最多的边的次数就是 $\max_{0 \leq j < 26} cnt[u][j] + cnt[v][j] - 2 \times cnt[x][j]$。其中 $x$ 为 $u$ 和 $v$ 的最近公共祖先。

倍增法求 LCA 的过程如下：

我们记每个节点的深度为 $depth$，父节点为 $p$，而 $f[i][j]$ 表示节点 $i$ 的第 $2^j$ 个祖先。那么，对于任意两点 $x$ 和 $y$，我们可以通过以下方式求出它们的最近公共祖先：

1. 如果 $depth(x) < depth(y)$，那么交换 $x$ 和 $y$，即保证 $x$ 的深度不小于 $y$ 的深度；
2. 接下来，我们将 $x$ 的深度不断向上提升，直到 $x$ 和 $y$ 的深度相同，此时 $x$ 和 $y$ 的深度都为 $depth(x)$；
3. 然后，我们将 $x$ 和 $y$ 的深度同时向上提升，直到 $x$ 和 $y$ 的父节点相同，此时 $x$ 和 $y$ 的父节点都为 $f[x][0]$，即为 $x$ 和 $y$ 的最近公共祖先。

最后，节点 $u$ 到节点 $v$ 的最小操作次数就是 $depth(u) + depth(v) - 2 \times depth(x) - \max_{0 \leq j < 26} cnt[u][j] + cnt[v][j] - 2 \times cnt[x][j]$。

时间复杂度 $O((n + q) \times C \times \log n)$，空间复杂度 $O(n \times C \times \log n)$，其中 $C$ 为边权重的最大值。

<!-- tabs:start -->

```python
class Solution:
    def minOperationsQueries(
        self, n: int, edges: List[List[int]], queries: List[List[int]]
    ) -> List[int]:
        m = n.bit_length()
        g = [[] for _ in range(n)]
        f = [[0] * m for _ in range(n)]
        p = [0] * n
        cnt = [None] * n
        depth = [0] * n
        for u, v, w in edges:
            g[u].append((v, w - 1))
            g[v].append((u, w - 1))
        cnt[0] = [0] * 26
        q = deque([0])
        while q:
            i = q.popleft()
            f[i][0] = p[i]
            for j in range(1, m):
                f[i][j] = f[f[i][j - 1]][j - 1]
            for j, w in g[i]:
                if j != p[i]:
                    p[j] = i
                    cnt[j] = cnt[i][:]
                    cnt[j][w] += 1
                    depth[j] = depth[i] + 1
                    q.append(j)
        ans = []
        for u, v in queries:
            x, y = u, v
            if depth[x] < depth[y]:
                x, y = y, x
            for j in reversed(range(m)):
                if depth[x] - depth[y] >= (1 << j):
                    x = f[x][j]
            for j in reversed(range(m)):
                if f[x][j] != f[y][j]:
                    x, y = f[x][j], f[y][j]
            if x != y:
                x = p[x]
            mx = max(cnt[u][j] + cnt[v][j] - 2 * cnt[x][j] for j in range(26))
            ans.append(depth[u] + depth[v] - 2 * depth[x] - mx)
        return ans
```

```java
class Solution {
    public int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {
        int m = 32 - Integer.numberOfLeadingZeros(n);
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        int[][] f = new int[n][m];
        int[] p = new int[n];
        int[][] cnt = new int[n][0];
        int[] depth = new int[n];
        for (var e : edges) {
            int u = e[0], v = e[1], w = e[2] - 1;
            g[u].add(new int[] {v, w});
            g[v].add(new int[] {u, w});
        }
        cnt[0] = new int[26];
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
                    cnt[j] = cnt[i].clone();
                    cnt[j][w]++;
                    depth[j] = depth[i] + 1;
                    q.offer(j);
                }
            }
        }
        int k = queries.length;
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
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
            int mx = 0;
            for (int j = 0; j < 26; ++j) {
                mx = Math.max(mx, cnt[u][j] + cnt[v][j] - 2 * cnt[x][j]);
            }
            ans[i] = depth[u] + depth[v] - 2 * depth[x] - mx;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> minOperationsQueries(int n, vector<vector<int>>& edges, vector<vector<int>>& queries) {
        int m = 32 - __builtin_clz(n);
        vector<pair<int, int>> g[n];
        int f[n][m];
        int p[n];
        int cnt[n][26];
        int depth[n];
        memset(f, 0, sizeof(f));
        memset(cnt, 0, sizeof(cnt));
        memset(depth, 0, sizeof(depth));
        memset(p, 0, sizeof(p));
        for (auto& e : edges) {
            int u = e[0], v = e[1], w = e[2] - 1;
            g[u].emplace_back(v, w);
            g[v].emplace_back(u, w);
        }
        queue<int> q;
        q.push(0);
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            f[i][0] = p[i];
            for (int j = 1; j < m; ++j) {
                f[i][j] = f[f[i][j - 1]][j - 1];
            }
            for (auto& [j, w] : g[i]) {
                if (j != p[i]) {
                    p[j] = i;
                    memcpy(cnt[j], cnt[i], sizeof(cnt[i]));
                    cnt[j][w]++;
                    depth[j] = depth[i] + 1;
                    q.push(j);
                }
            }
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
            int mx = 0;
            for (int j = 0; j < 26; ++j) {
                mx = max(mx, cnt[u][j] + cnt[v][j] - 2 * cnt[x][j]);
            }
            ans.push_back(depth[u] + depth[v] - 2 * depth[x] - mx);
        }
        return ans;
    }
};
```

```go
func minOperationsQueries(n int, edges [][]int, queries [][]int) []int {
	m := bits.Len(uint(n))
	g := make([][][2]int, n)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, m)
	}
	p := make([]int, n)
	cnt := make([][26]int, n)
	cnt[0] = [26]int{}
	depth := make([]int, n)
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]-1
		g[u] = append(g[u], [2]int{v, w})
		g[v] = append(g[v], [2]int{u, w})
	}
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
				cnt[j] = [26]int{}
				for k := 0; k < 26; k++ {
					cnt[j][k] = cnt[i][k]
				}
				cnt[j][w]++
				depth[j] = depth[i] + 1
				q = append(q, j)
			}
		}
	}
	ans := make([]int, len(queries))
	for i, qq := range queries {
		u, v := qq[0], qq[1]
		x, y := u, v
		if depth[x] < depth[y] {
			x, y = y, x
		}
		for j := m - 1; j >= 0; j-- {
			if depth[x]-depth[y] >= (1 << j) {
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
		mx := 0
		for j := 0; j < 26; j++ {
			mx = max(mx, cnt[u][j]+cnt[v][j]-2*cnt[x][j])
		}
		ans[i] = depth[u] + depth[v] - 2*depth[x] - mx
	}
	return ans
}
```

<!-- tabs:end -->

<!-- end -->
