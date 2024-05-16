---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2699.Modify%20Graph%20Edge%20Weights/README_EN.md
rating: 2873
source: Weekly Contest 346 Q4
tags:
    - Graph
    - Shortest Path
    - Heap (Priority Queue)
---

# [2699. Modify Graph Edge Weights](https://leetcode.com/problems/modify-graph-edge-weights)

[中文文档](/solution/2600-2699/2699.Modify%20Graph%20Edge%20Weights/README.md)

## Description

<p>You are given an <strong>undirected weighted</strong> <strong>connected</strong> graph containing <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>, and an integer array <code>edges</code> where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>, w<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> with weight <code>w<sub>i</sub></code>.</p>

<p>Some edges have a weight of <code>-1</code> (<code>w<sub>i</sub> = -1</code>), while others have a <strong>positive</strong> weight (<code>w<sub>i</sub> &gt; 0</code>).</p>

<p>Your task is to modify <strong>all edges</strong> with a weight of <code>-1</code> by assigning them <strong>positive integer values </strong>in the range <code>[1, 2 * 10<sup>9</sup>]</code> so that the <strong>shortest distance</strong> between the nodes <code>source</code> and <code>destination</code> becomes equal to an integer <code>target</code>. If there are <strong>multiple</strong> <strong>modifications</strong> that make the shortest distance between <code>source</code> and <code>destination</code> equal to <code>target</code>, any of them will be considered correct.</p>

<p>Return <em>an array containing all edges (even unmodified ones) in any order if it is possible to make the shortest distance from </em><code>source</code><em> to </em><code>destination</code><em> equal to </em><code>target</code><em>, or an <strong>empty array</strong> if it&#39;s impossible.</em></p>

<p><strong>Note:</strong> You are not allowed to modify the weights of edges with initial positive weights.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2699.Modify%20Graph%20Edge%20Weights/images/graph.png" style="width: 300px; height: 300px;" /></strong></p>

<pre>
<strong>Input:</strong> n = 5, edges = [[4,1,-1],[2,0,-1],[0,3,-1],[4,3,-1]], source = 0, destination = 1, target = 5
<strong>Output:</strong> [[4,1,1],[2,0,1],[0,3,3],[4,3,1]]
<strong>Explanation:</strong> The graph above shows a possible modification to the edges, making the distance from 0 to 1 equal to 5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2699.Modify%20Graph%20Edge%20Weights/images/graph-2.png" style="width: 300px; height: 300px;" /></strong></p>

<pre>
<strong>Input:</strong> n = 3, edges = [[0,1,-1],[0,2,5]], source = 0, destination = 2, target = 6
<strong>Output:</strong> []
<strong>Explanation:</strong> The graph above contains the initial edges. It is not possible to make the distance from 0 to 2 equal to 6 by modifying the edge with weight -1. So, an empty array is returned.
</pre>

<p><strong class="example">Example 3:</strong></p>

<p><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2699.Modify%20Graph%20Edge%20Weights/images/graph-3.png" style="width: 300px; height: 300px;" /></strong></p>

<pre>
<strong>Input:</strong> n = 4, edges = [[1,0,4],[1,2,3],[2,3,5],[0,3,-1]], source = 0, destination = 2, target = 6
<strong>Output:</strong> [[1,0,4],[1,2,3],[2,3,5],[0,3,1]]
<strong>Explanation:</strong> The graph above shows a modified graph having the shortest distance from 0 to 2 as 6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code><font face="monospace">1 &lt;= edges.length &lt;= n * (n - 1) / 2</font></code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i&nbsp;</sub>&lt;&nbsp;n</code></li>
	<li><code><font face="monospace">w<sub>i</sub>&nbsp;= -1&nbsp;</font></code>or <code><font face="monospace">1 &lt;= w<sub>i&nbsp;</sub>&lt;= 10<sup><span style="font-size: 10.8333px;">7</span></sup></font></code></li>
	<li><code>a<sub>i&nbsp;</sub>!=&nbsp;b<sub>i</sub></code></li>
	<li><code>0 &lt;= source, destination &lt; n</code></li>
	<li><code>source != destination</code></li>
	<li><code><font face="monospace">1 &lt;= target &lt;= 10<sup>9</sup></font></code></li>
	<li>The graph is connected, and there are no self-loops or repeated edges</li>
</ul>

## Solutions

### Solution 1: Shortest Path (Dijkstra's Algorithm)

First, we ignore the edges with a weight of $-1$ and use Dijkstra's algorithm to find the shortest distance $d$ from $source$ to $destination$.

-   If $d < target$, it means there is a shortest path composed entirely of positive weight edges. No matter how we modify the edges with a weight of $-1$, we cannot make the shortest distance from $source$ to $destination$ equal to $target$. Therefore, there is no modification scheme that satisfies the problem, and we can return an empty array.

-   If $d = target$, it means there is a shortest path composed entirely of positive weight edges, and its length is $target$. In this case, we can modify all edges with a weight of $-1$ to the maximum value $2 \times 10^9$.

-   If $d > target$, we can try to add an edge with a weight of $-1$ to the graph, set the weight of the edge to $1$, and then use Dijkstra's algorithm again to find the shortest distance $d$ from $source$ to $destination$.

    -   If the shortest distance $d \leq target$, it means that after adding this edge, the shortest path can be shortened, and the shortest path must pass through this edge. Then we only need to change the weight of this edge to $target-d+1$ to make the shortest path equal to $target$. Then we can modify the remaining edges with a weight of $-1$ to the maximum value $2 \times 10^9$.
    -   If the shortest distance $d > target$, it means that after adding this edge, the shortest path will not be shortened. Then we greedily keep the weight of this edge as $-1$ and continue to try to add the remaining edges with a weight of $-1$.

The time complexity is $O(n^3)$, and the space complexity is $O(n^2)$, where $n$ is the number of points in the graph.

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
