---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2737.Find%20the%20Closest%20Marked%20Node/README_EN.md
tags:
    - Graph
    - Array
    - Shortest Path
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2737. Find the Closest Marked Node 🔒](https://leetcode.com/problems/find-the-closest-marked-node)

[中文文档](/solution/2700-2799/2737.Find%20the%20Closest%20Marked%20Node/README.md)

## Description

<!-- description:start -->

<p>You are given a positive integer <code>n</code> which is the number of nodes of a <strong>0-indexed directed weighted</strong> graph and a <strong>0-indexed</strong> <strong>2D array</strong> <code>edges</code> where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> indicates that there is an edge from node <code>u<sub>i</sub></code> to node <code>v<sub>i</sub></code> with weight <code>w<sub>i</sub></code>.</p>

<p>You are also given a node <code>s</code> and a node array <code>marked</code>; your task is to find the <strong>minimum</strong> distance from <code>s</code> to <strong>any</strong> of the nodes in <code>marked</code>.</p>

<p>Return <em>an integer denoting the minimum distance from </em><code>s</code><em> to any node in </em><code>marked</code><em> or </em><code>-1</code><em> if there are no paths from s to any of the marked nodes</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 4, edges = [[0,1,1],[1,2,3],[2,3,2],[0,3,4]], s = 0, marked = [2,3]
<strong>Output:</strong> 4
<strong>Explanation:</strong> There is one path from node 0 (the green node) to node 2 (a red node), which is 0-&gt;1-&gt;2, and has a distance of 1 + 3 = 4.
There are two paths from node 0 to node 3 (a red node), which are 0-&gt;1-&gt;2-&gt;3 and 0-&gt;3, the first one has a distance of 1 + 3 + 2 = 6 and the second one has a distance of 4.
The minimum of them is 4.
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2737.Find%20the%20Closest%20Marked%20Node/images/image_2023-06-13_16-34-38.png" style="width: 185px; height: 180px;" /></p>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 5, edges = [[0,1,2],[0,2,4],[1,3,1],[2,3,3],[3,4,2]], s = 1, marked = [0,4]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are no paths from node 1 (the green node) to node 0 (a red node).
There is one path from node 1 to node 4 (a red node), which is 1-&gt;3-&gt;4, and has a distance of 1 + 2 = 3.
So the answer is 3.
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2737.Find%20the%20Closest%20Marked%20Node/images/image_2023-06-13_16-35-13.png" style="width: 300px; height: 285px;" /></p>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 4, edges = [[0,1,1],[1,2,3],[2,3,2]], s = 3, marked = [0,1]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There are no paths from node 3 (the green node) to any of the marked nodes (the red nodes), so the answer is -1.
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2737.Find%20the%20Closest%20Marked%20Node/images/image_2023-06-13_16-35-47.png" style="width: 420px; height: 80px;" /></p>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 500</code></li>
	<li><code>1 &lt;= edges.length &lt;= 10<sup>4</sup></code></li>
	<li><code>edges[i].length = 3</code></li>
	<li><code>0 &lt;= edges[i][0], edges[i][1] &lt;= n - 1</code></li>
	<li><code>1 &lt;= edges[i][2] &lt;=&nbsp;10<sup>6</sup></code></li>
	<li><code>1 &lt;= marked.length&nbsp;&lt;= n - 1</code></li>
	<li><code>0 &lt;= s, marked[i]&nbsp;&lt;= n - 1</code></li>
	<li><code>s != marked[i]</code></li>
	<li><code>marked[i] != marked[j]</code> for every <code>i != j</code></li>
	<li>The&nbsp;graph might have&nbsp;<strong>repeated edges</strong>.</li>
	<li>The graph is generated such that it has no&nbsp;<strong>self-loops</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dijkstra's Algorithm

First, we construct an adjacency matrix $g$ based on the edge information provided in the problem, where $g[i][j]$ represents the distance from node $i$ to node $j$. If such an edge does not exist, then $g[i][j]$ is positive infinity.

Then, we can use Dijkstra's algorithm to find the shortest distance from the starting point $s$ to all nodes, denoted as $dist$.

Finally, we traverse all the marked nodes and find the marked node with the smallest distance. If the distance is positive infinity, we return $-1$.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$. Here, $n$ is the number of nodes.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumDistance(
        self, n: int, edges: List[List[int]], s: int, marked: List[int]
    ) -> int:
        g = [[inf] * n for _ in range(n)]
        for u, v, w in edges:
            g[u][v] = min(g[u][v], w)
        dist = [inf] * n
        vis = [False] * n
        dist[s] = 0
        for _ in range(n):
            t = -1
            for j in range(n):
                if not vis[j] and (t == -1 or dist[t] > dist[j]):
                    t = j
            vis[t] = True
            for j in range(n):
                dist[j] = min(dist[j], dist[t] + g[t][j])
        ans = min(dist[i] for i in marked)
        return -1 if ans >= inf else ans
```

#### Java

```java
class Solution {
    public int minimumDistance(int n, List<List<Integer>> edges, int s, int[] marked) {
        final int inf = 1 << 29;
        int[][] g = new int[n][n];
        for (var e : g) {
            Arrays.fill(e, inf);
        }
        for (var e : edges) {
            int u = e.get(0), v = e.get(1), w = e.get(2);
            g[u][v] = Math.min(g[u][v], w);
        }
        int[] dist = new int[n];
        Arrays.fill(dist, inf);
        dist[s] = 0;
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            int t = -1;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (t == -1 || dist[t] > dist[j])) {
                    t = j;
                }
            }
            vis[t] = true;
            for (int j = 0; j < n; ++j) {
                dist[j] = Math.min(dist[j], dist[t] + g[t][j]);
            }
        }
        int ans = inf;
        for (int i : marked) {
            ans = Math.min(ans, dist[i]);
        }
        return ans >= inf ? -1 : ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumDistance(int n, vector<vector<int>>& edges, int s, vector<int>& marked) {
        const int inf = 1 << 29;
        vector<vector<int>> g(n, vector<int>(n, inf));
        vector<int> dist(n, inf);
        dist[s] = 0;
        vector<bool> vis(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u][v] = min(g[u][v], w);
        }
        for (int i = 0; i < n; ++i) {
            int t = -1;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (t == -1 || dist[t] > dist[j])) {
                    t = j;
                }
            }
            vis[t] = true;
            for (int j = 0; j < n; ++j) {
                dist[j] = min(dist[j], dist[t] + g[t][j]);
            }
        }
        int ans = inf;
        for (int i : marked) {
            ans = min(ans, dist[i]);
        }
        return ans >= inf ? -1 : ans;
    }
};
```

#### Go

```go
func minimumDistance(n int, edges [][]int, s int, marked []int) int {
	const inf = 1 << 29
	g := make([][]int, n)
	dist := make([]int, n)
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = inf
		}
		dist[i] = inf
	}
	dist[s] = 0
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		g[u][v] = min(g[u][v], w)
	}
	vis := make([]bool, n)
	for _ = range g {
		t := -1
		for j := 0; j < n; j++ {
			if !vis[j] && (t == -1 || dist[j] < dist[t]) {
				t = j
			}
		}
		vis[t] = true
		for j := 0; j < n; j++ {
			dist[j] = min(dist[j], dist[t]+g[t][j])
		}
	}
	ans := inf
	for _, i := range marked {
		ans = min(ans, dist[i])
	}
	if ans >= inf {
		return -1
	}
	return ans
}
```

#### TypeScript

```ts
function minimumDistance(n: number, edges: number[][], s: number, marked: number[]): number {
    const inf = 1 << 29;
    const g: number[][] = Array(n)
        .fill(0)
        .map(() => Array(n).fill(inf));
    const dist: number[] = Array(n).fill(inf);
    const vis: boolean[] = Array(n).fill(false);
    for (const [u, v, w] of edges) {
        g[u][v] = Math.min(g[u][v], w);
    }
    dist[s] = 0;
    for (let i = 0; i < n; ++i) {
        let t = -1;
        for (let j = 0; j < n; ++j) {
            if (!vis[j] && (t == -1 || dist[t] > dist[j])) {
                t = j;
            }
        }
        vis[t] = true;
        for (let j = 0; j < n; ++j) {
            dist[j] = Math.min(dist[j], dist[t] + g[t][j]);
        }
    }
    let ans = inf;
    for (const i of marked) {
        ans = Math.min(ans, dist[i]);
    }
    return ans >= inf ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
