---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3367.Maximize%20Sum%20of%20Weights%20after%20Edge%20Removals/README_EN.md
tags:
    - Tree
    - Depth-First Search
    - Dynamic Programming
---

<!-- problem:start -->

# [3367. Maximize Sum of Weights after Edge Removals](https://leetcode.com/problems/maximize-sum-of-weights-after-edge-removals)

[中文文档](/solution/3300-3399/3367.Maximize%20Sum%20of%20Weights%20after%20Edge%20Removals/README.md)

## Description

<!-- description:start -->

<p>There exists an <strong>undirected</strong> tree with <code>n</code> nodes numbered <code>0</code> to <code>n - 1</code>. You are given a 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> indicates that there is an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> with weight <code>w<sub>i</sub></code> in the tree.</p>

<p>Your task is to remove <em>zero or more</em> edges such that:</p>

<ul>
	<li>Each node has an edge with <strong>at most</strong> <code>k</code> other nodes, where <code>k</code> is given.</li>
	<li>The sum of the weights of the remaining edges is <strong>maximized</strong>.</li>
</ul>

<p>Return the <strong>maximum </strong>possible sum of weights for the remaining edges after making the necessary removals.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1,4],[0,2,2],[2,3,12],[2,4,6]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">22</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3367.Maximize%20Sum%20of%20Weights%20after%20Edge%20Removals/images/test1drawio.png" style="width: 250px; height: 250px;" /></p>

<ul>
	<li>Node 2 has edges with 3 other nodes. We remove the edge <code>[0, 2, 2]</code>, ensuring that no node has edges with more than <code>k = 2</code> nodes.</li>
	<li>The sum of weights is 22, and we can&#39;t achieve a greater sum. Thus, the answer is 22.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1,5],[1,2,10],[0,3,15],[3,4,20],[3,5,5],[0,6,10]], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">65</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Since no node has edges connecting it to more than <code>k = 3</code> nodes, we don&#39;t remove any edges.</li>
	<li>The sum of weights is 65. Thus, the answer is 65.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= n - 1</code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code><font face="monospace">0 &lt;= edges[i][0] &lt;= n - 1</font></code></li>
	<li><code><font face="monospace">0 &lt;= edges[i][1] &lt;= n - 1</font></code></li>
	<li><code><font face="monospace">1 &lt;= edges[i][2] &lt;= 10<sup>6</sup></font></code></li>
	<li>The input is generated such that <code>edges</code> form a valid tree.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximizeSumOfWeights(self, edges: List[List[int]], k: int) -> int:
        def dfs(u: int, fa: int) -> Tuple[int, int]:
            s = 0
            t = []
            for v, w in g[u]:
                if v == fa:
                    continue
                a, b = dfs(v, u)
                s += a
                if (d := (w + b - a)) > 0:
                    t.append(d)
            t.sort(reverse=True)
            return s + sum(t[:k]), s + sum(t[: k - 1])

        n = len(edges) + 1
        g: List[List[Tuple[int, int]]] = [[] for _ in range(n)]
        for u, v, w in edges:
            g[u].append((v, w))
            g[v].append((u, w))
        x, y = dfs(0, -1)
        return max(x, y)
```

#### Java

```java
class Solution {
    private List<int[]>[] g;
    private int k;

    public long maximizeSumOfWeights(int[][] edges, int k) {
        this.k = k;
        int n = edges.length + 1;
        g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].add(new int[] {v, w});
            g[v].add(new int[] {u, w});
        }
        var ans = dfs(0, -1);
        return Math.max(ans[0], ans[1]);
    }

    private long[] dfs(int u, int fa) {
        long s = 0;
        List<Long> t = new ArrayList<>();
        for (var e : g[u]) {
            int v = e[0], w = e[1];
            if (v == fa) {
                continue;
            }
            var res = dfs(v, u);
            s += res[0];
            long d = w + res[1] - res[0];
            if (d > 0) {
                t.add(d);
            }
        }
        t.sort(Comparator.reverseOrder());
        for (int i = 0; i < Math.min(t.size(), k - 1); ++i) {
            s += t.get(i);
        }
        return new long[] {s + (t.size() >= k ? t.get(k - 1) : 0), s};
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximizeSumOfWeights(vector<vector<int>>& edges, int k) {
        int n = edges.size() + 1;
        vector<vector<pair<int, int>>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].emplace_back(v, w);
            g[v].emplace_back(u, w);
        }
        using ll = long long;
        auto dfs = [&](auto&& dfs, int u, int fa) -> pair<ll, ll> {
            ll s = 0;
            vector<ll> t;
            for (auto& [v, w] : g[u]) {
                if (v == fa) {
                    continue;
                }
                auto [a, b] = dfs(dfs, v, u);
                s += a;
                ll d = w + b - a;
                if (d > 0) {
                    t.push_back(d);
                }
            }
            ranges::sort(t, greater<>());
            for (int i = 0; i < min((int) t.size(), k - 1); ++i) {
                s += t[i];
            }
            return {s + (t.size() >= k ? t[k - 1] : 0), s};
        };

        auto [x, y] = dfs(dfs, 0, -1);
        return max(x, y);
    }
};
```

#### Go

```go
func maximizeSumOfWeights(edges [][]int, k int) int64 {
	n := len(edges) + 1
	g := make([][][]int, n)
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		g[u] = append(g[u], []int{v, w})
		g[v] = append(g[v], []int{u, w})
	}

	var dfs func(u, fa int) (int64, int64)
	dfs = func(u, fa int) (int64, int64) {
		var s int64
		var t []int64
		for _, e := range g[u] {
			v, w := e[0], e[1]
			if v == fa {
				continue
			}
			a, b := dfs(v, u)
			s += a
			d := int64(w) + b - a
			if d > 0 {
				t = append(t, d)
			}
		}
		sort.Slice(t, func(i, j int) bool {
			return t[i] > t[j]
		})
		for i := 0; i < min(len(t), k-1); i++ {
			s += t[i]
		}
		s2 := s
		if len(t) >= k {
			s += t[k-1]
		}
		return s, s2
	}

	x, y := dfs(0, -1)
	return max(x, y)
}
```

#### TypeScript

```ts
function maximizeSumOfWeights(edges: number[][], k: number): number {
    const n = edges.length + 1;
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [u, v, w] of edges) {
        g[u].push([v, w]);
        g[v].push([u, w]);
    }
    const dfs = (u: number, fa: number): [number, number] => {
        let s = 0;
        const t: number[] = [];

        for (const [v, w] of g[u]) {
            if (v === fa) continue;

            const [a, b] = dfs(v, u);
            s += a;
            const d = w + b - a;
            if (d > 0) t.push(d);
        }

        t.sort((a, b) => b - a);
        for (let i = 0; i < Math.min(t.length, k - 1); i++) {
            s += t[i];
        }
        const s2 = s;
        if (t.length >= k) {
            s += t[k - 1];
        }

        return [s, s2];
    };

    const [x, y] = dfs(0, -1);
    return Math.max(x, y);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
