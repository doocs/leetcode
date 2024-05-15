---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2699.Modify%20Graph%20Edge%20Weights/README.md
rating: 2873
tags:
    - 图
    - 最短路
    - 堆（优先队列）
---

# [2699. 修改图中的边权](https://leetcode.cn/problems/modify-graph-edge-weights)

[English Version](/solution/2600-2699/2699.Modify%20Graph%20Edge%20Weights/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>n</code>&nbsp;个节点的 <strong>无向带权连通</strong>&nbsp;图，节点编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;，再给你一个整数数组&nbsp;<code>edges</code>&nbsp;，其中&nbsp;<code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>, w<sub>i</sub>]</code>&nbsp;表示节点&nbsp;<code>a<sub>i</sub></code> 和&nbsp;<code>b<sub>i</sub></code>&nbsp;之间有一条边权为&nbsp;<code>w<sub>i</sub></code>&nbsp;的边。</p>

<p>部分边的边权为&nbsp;<code>-1</code>（<code>w<sub>i</sub> = -1</code>），其他边的边权都为 <strong>正</strong>&nbsp;数（<code>w<sub>i</sub> &gt; 0</code>）。</p>

<p>你需要将所有边权为 <code>-1</code>&nbsp;的边都修改为范围&nbsp;<code>[1, 2 * 10<sup>9</sup>]</code>&nbsp;中的 <strong>正整数</strong>&nbsp;，使得从节点&nbsp;<code>source</code>&nbsp;到节点&nbsp;<code>destination</code>&nbsp;的 <strong>最短距离</strong>&nbsp;为整数&nbsp;<code>target</code>&nbsp;。如果有 <strong>多种</strong>&nbsp;修改方案可以使&nbsp;<code>source</code> 和&nbsp;<code>destination</code>&nbsp;之间的最短距离等于&nbsp;<code>target</code>&nbsp;，你可以返回任意一种方案。</p>

<p>如果存在使 <code>source</code>&nbsp;到 <code>destination</code>&nbsp;最短距离为 <code>target</code>&nbsp;的方案，请你按任意顺序返回包含所有边的数组（包括未修改边权的边）。如果不存在这样的方案，请你返回一个 <strong>空数组</strong>&nbsp;。</p>

<p><strong>注意：</strong>你不能修改一开始边权为正数的边。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2699.Modify%20Graph%20Edge%20Weights/images/graph.png" style="width: 300px; height: 300px;" /></strong></p>

<pre>
<b>输入：</b>n = 5, edges = [[4,1,-1],[2,0,-1],[0,3,-1],[4,3,-1]], source = 0, destination = 1, target = 5
<b>输出：</b>[[4,1,1],[2,0,1],[0,3,3],[4,3,1]]
<b>解释：</b>上图展示了一个满足题意的修改方案，从 0 到 1 的最短距离为 5 。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2699.Modify%20Graph%20Edge%20Weights/images/graph-2.png" style="width: 300px; height: 300px;" /></strong></p>

<pre>
<b>输入：</b>n = 3, edges = [[0,1,-1],[0,2,5]], source = 0, destination = 2, target = 6
<b>输出：</b>[]
<b>解释：</b>上图是一开始的图。没有办法通过修改边权为 -1 的边，使得 0 到 2 的最短距离等于 6 ，所以返回一个空数组。
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2699.Modify%20Graph%20Edge%20Weights/images/graph-3.png" style="width: 300px; height: 300px;" /></strong></p>

<pre>
<b>输入：</b>n = 4, edges = [[1,0,4],[1,2,3],[2,3,5],[0,3,-1]], source = 0, destination = 2, target = 6
<b>输出：</b>[[1,0,4],[1,2,3],[2,3,5],[0,3,1]]
<b>解释：</b>上图展示了一个满足题意的修改方案，从 0 到 2 的最短距离为 6 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= edges.length &lt;= n * (n - 1) / 2</code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i&nbsp;</sub>&lt;&nbsp;n</code></li>
	<li><code>w<sub>i</sub>&nbsp;= -1</code> 或者 <code>1 &lt;= w<sub>i&nbsp;</sub>&lt;= 10<sup><span style="">7</span></sup></code></li>
	<li><code>a<sub>i&nbsp;</sub>!=&nbsp;b<sub>i</sub></code></li>
	<li><code>0 &lt;= source, destination &lt; n</code></li>
	<li><code>source != destination</code></li>
	<li><code>1 &lt;= target &lt;= 10<sup>9</sup></code></li>
	<li>输入的图是连通图，且没有自环和重边。</li>
</ul>

## 解法

### 方法一：最短路（Dijkstra 算法）

我们先不考虑边权为 $-1$ 的边，使用 Dijkstra 算法求出从 $source$ 到 $destination$ 的最短距离 $d$。

如果 $d \lt target$，说明存在一条完全由正权边组成的最短路径，此时无论我们如何修改边权为 $-1$ 的边，都无法使得 $source$ 到 $destination$ 的最短距离等于 $target$，因此不存在满足题意的修改方案，返回一个空数组即可。

如果 $d = target$，说明存在一条完全由正权边组成的、长度为 $target$ 的最短路径，此时我们可以将所有边权为 $-1$ 的边修改为最大值 $2 \times 10^9$ 即可。

如果 $d \gt target$，我们可以尝试往图中加入一条边权为 $-1$ 的边，将边权设置为 $1$，然后再次使用 Dijkstra 算法求出从 $source$ 到 $destination$ 的最短距离 $d$。

-   如果最短距离 $d \leq target$，说明加入这条边后，可以使得最短路变短，而且最短路也一定经过这条边，那么我们只需要将这条边的边权改为 $target-d+1$，就可以使得最短路等于 $target$。然后我们将其余的边权为 $-1$ 的边修改为最大值 $2 \times 10^9$ 即可。
-   如果最短距离 $d \gt target$，说明加入这条边后，最短路不会变短，那么我们贪心地将这条边的边权保持为 $-1$，然后继续尝试加入其余的边权为 $-1$ 的边。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 是图中的点数。

<!-- tabs:start -->

```python
class Solution:
    def modifiedGraphEdges(
        self, n: int, edges: List[List[int]], source: int, destination: int, target: int
    ) -> List[List[int]]:
        def dijkstra(edges: List[List[int]]) -> int:
            g = [[inf] * n for _ in range(n)]
            for a, b, w in edges:
                if w == -1:
                    continue
                g[a][b] = g[b][a] = w
            dist = [inf] * n
            dist[source] = 0
            vis = [False] * n
            for _ in range(n):
                k = -1
                for j in range(n):
                    if not vis[j] and (k == -1 or dist[k] > dist[j]):
                        k = j
                vis[k] = True
                for j in range(n):
                    dist[j] = min(dist[j], dist[k] + g[k][j])
            return dist[destination]

        inf = 2 * 10**9
        d = dijkstra(edges)
        if d < target:
            return []
        ok = d == target
        for e in edges:
            if e[2] > 0:
                continue
            if ok:
                e[2] = inf
                continue
            e[2] = 1
            d = dijkstra(edges)
            if d <= target:
                ok = True
                e[2] += target - d
        return edges if ok else []
```

```java
class Solution {
    private final int inf = 2000000000;

    public int[][] modifiedGraphEdges(
        int n, int[][] edges, int source, int destination, int target) {
        long d = dijkstra(edges, n, source, destination);
        if (d < target) {
            return new int[0][];
        }
        boolean ok = d == target;
        for (var e : edges) {
            if (e[2] > 0) {
                continue;
            }
            if (ok) {
                e[2] = inf;
                continue;
            }
            e[2] = 1;
            d = dijkstra(edges, n, source, destination);
            if (d <= target) {
                ok = true;
                e[2] += target - d;
            }
        }
        return ok ? edges : new int[0][];
    }

    private long dijkstra(int[][] edges, int n, int src, int dest) {
        int[][] g = new int[n][n];
        long[] dist = new long[n];
        Arrays.fill(dist, inf);
        dist[src] = 0;
        for (var f : g) {
            Arrays.fill(f, inf);
        }
        for (var e : edges) {
            int a = e[0], b = e[1], w = e[2];
            if (w == -1) {
                continue;
            }
            g[a][b] = w;
            g[b][a] = w;
        }
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            int k = -1;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (k == -1 || dist[k] > dist[j])) {
                    k = j;
                }
            }
            vis[k] = true;
            for (int j = 0; j < n; ++j) {
                dist[j] = Math.min(dist[j], dist[k] + g[k][j]);
            }
        }
        return dist[dest];
    }
}
```

```cpp
using ll = long long;
const int inf = 2e9;

class Solution {
public:
    vector<vector<int>> modifiedGraphEdges(int n, vector<vector<int>>& edges, int source, int destination, int target) {
        ll d = dijkstra(edges, n, source, destination);
        if (d < target) {
            return {};
        }
        bool ok = d == target;
        for (auto& e : edges) {
            if (e[2] > 0) {
                continue;
            }
            if (ok) {
                e[2] = inf;
                continue;
            }
            e[2] = 1;
            d = dijkstra(edges, n, source, destination);
            if (d <= target) {
                ok = true;
                e[2] += target - d;
            }
        }
        return ok ? edges : vector<vector<int>>{};
    }

    ll dijkstra(vector<vector<int>>& edges, int n, int src, int dest) {
        ll g[n][n];
        ll dist[n];
        bool vis[n];
        for (int i = 0; i < n; ++i) {
            fill(g[i], g[i] + n, inf);
            dist[i] = inf;
            vis[i] = false;
        }
        dist[src] = 0;
        for (auto& e : edges) {
            int a = e[0], b = e[1], w = e[2];
            if (w == -1) {
                continue;
            }
            g[a][b] = w;
            g[b][a] = w;
        }
        for (int i = 0; i < n; ++i) {
            int k = -1;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (k == -1 || dist[j] < dist[k])) {
                    k = j;
                }
            }
            vis[k] = true;
            for (int j = 0; j < n; ++j) {
                dist[j] = min(dist[j], dist[k] + g[k][j]);
            }
        }
        return dist[dest];
    }
};
```

```go
func modifiedGraphEdges(n int, edges [][]int, source int, destination int, target int) [][]int {
	const inf int = 2e9
	dijkstra := func(edges [][]int) int {
		g := make([][]int, n)
		dist := make([]int, n)
		vis := make([]bool, n)
		for i := range g {
			g[i] = make([]int, n)
			for j := range g[i] {
				g[i][j] = inf
			}
			dist[i] = inf
		}
		dist[source] = 0
		for _, e := range edges {
			a, b, w := e[0], e[1], e[2]
			if w == -1 {
				continue
			}
			g[a][b], g[b][a] = w, w
		}
		for i := 0; i < n; i++ {
			k := -1
			for j := 0; j < n; j++ {
				if !vis[j] && (k == -1 || dist[j] < dist[k]) {
					k = j
				}
			}
			vis[k] = true
			for j := 0; j < n; j++ {
				dist[j] = min(dist[j], dist[k]+g[k][j])
			}
		}
		return dist[destination]
	}
	d := dijkstra(edges)
	if d < target {
		return [][]int{}
	}
	ok := d == target
	for _, e := range edges {
		if e[2] > 0 {
			continue
		}
		if ok {
			e[2] = inf
			continue
		}
		e[2] = 1
		d := dijkstra(edges)
		if d <= target {
			ok = true
			e[2] += target - d
		}
	}
	if ok {
		return edges
	}
	return [][]int{}
}
```

```ts
function modifiedGraphEdges(
    n: number,
    edges: number[][],
    source: number,
    destination: number,
    target: number,
): number[][] {
    const inf = 2e9;
    const dijkstra = (edges: number[][]): number => {
        const g: number[][] = Array(n)
            .fill(0)
            .map(() => Array(n).fill(inf));
        const dist: number[] = Array(n).fill(inf);
        const vis: boolean[] = Array(n).fill(false);
        for (const [a, b, w] of edges) {
            if (w === -1) {
                continue;
            }
            g[a][b] = w;
            g[b][a] = w;
        }
        dist[source] = 0;
        for (let i = 0; i < n; ++i) {
            let k = -1;
            for (let j = 0; j < n; ++j) {
                if (!vis[j] && (k === -1 || dist[j] < dist[k])) {
                    k = j;
                }
            }
            vis[k] = true;
            for (let j = 0; j < n; ++j) {
                dist[j] = Math.min(dist[j], dist[k] + g[k][j]);
            }
        }
        return dist[destination];
    };
    let d = dijkstra(edges);
    if (d < target) {
        return [];
    }
    let ok = d === target;
    for (const e of edges) {
        if (e[2] > 0) {
            continue;
        }
        if (ok) {
            e[2] = inf;
            continue;
        }
        e[2] = 1;
        d = dijkstra(edges);
        if (d <= target) {
            ok = true;
            e[2] += target - d;
        }
    }
    return ok ? edges : [];
}
```

<!-- tabs:end -->

<!-- end -->
