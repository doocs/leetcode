---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3067.Count%20Pairs%20of%20Connectable%20Servers%20in%20a%20Weighted%20Tree%20Network/README_EN.md
rating: 1908
source: Biweekly Contest 125 Q3
tags:
    - Tree
    - Depth-First Search
    - Array
---

<!-- problem:start -->

# [3067. Count Pairs of Connectable Servers in a Weighted Tree Network](https://leetcode.com/problems/count-pairs-of-connectable-servers-in-a-weighted-tree-network)

[中文文档](/solution/3000-3099/3067.Count%20Pairs%20of%20Connectable%20Servers%20in%20a%20Weighted%20Tree%20Network/README.md)

## Description

<!-- description:start -->

<p>You are given an unrooted weighted tree with <code>n</code> vertices representing servers numbered from <code>0</code> to <code>n - 1</code>, an array <code>edges</code> where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>, weight<sub>i</sub>]</code> represents a bidirectional edge between vertices <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> of weight <code>weight<sub>i</sub></code>. You are also given an integer <code>signalSpeed</code>.</p>

<p>Two servers <code>a</code> and <code>b</code> are <strong>connectable</strong> through a server <code>c</code> if:</p>

<ul>
	<li><code>a &lt; b</code>, <code>a != c</code> and <code>b != c</code>.</li>
	<li>The distance from <code>c</code> to <code>a</code> is divisible by <code>signalSpeed</code>.</li>
	<li>The distance from <code>c</code> to <code>b</code> is divisible by <code>signalSpeed</code>.</li>
	<li>The path from <code>c</code> to <code>b</code> and the path from <code>c</code> to <code>a</code> do not share any edges.</li>
</ul>

<p>Return <em>an integer array</em> <code>count</code> <em>of length</em> <code>n</code> <em>where</em> <code>count[i]</code> <em>is the <strong>number</strong> of server pairs that are <strong>connectable</strong> through</em> <em>the server</em> <code>i</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3067.Count%20Pairs%20of%20Connectable%20Servers%20in%20a%20Weighted%20Tree%20Network/images/example22.png" style="width: 438px; height: 243px; padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>Input:</strong> edges = [[0,1,1],[1,2,5],[2,3,13],[3,4,9],[4,5,2]], signalSpeed = 1
<strong>Output:</strong> [0,4,6,6,4,0]
<strong>Explanation:</strong> Since signalSpeed is 1, count[c] is equal to the number of pairs of paths that start at c and do not share any edges.
In the case of the given path graph, count[c] is equal to the number of servers to the left of c multiplied by the servers to the right of c.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3067.Count%20Pairs%20of%20Connectable%20Servers%20in%20a%20Weighted%20Tree%20Network/images/example11.png" style="width: 495px; height: 484px; padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>Input:</strong> edges = [[0,6,3],[6,5,3],[0,3,1],[3,2,7],[3,1,6],[3,4,2]], signalSpeed = 3
<strong>Output:</strong> [2,0,0,0,0,0,2]
<strong>Explanation:</strong> Through server 0, there are 2 pairs of connectable servers: (4, 5) and (4, 6).
Through server 6, there are 2 pairs of connectable servers: (4, 5) and (0, 5).
It can be shown that no two servers are connectable through servers other than 0 and 6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>, weight<sub>i</sub>]</code><!-- notionvc: a2623897-1bb1-4c07-84b6-917ffdcd83ec --></li>
	<li><code>1 &lt;= weight<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= signalSpeed &lt;= 10<sup>6</sup></code></li>
	<li>The input is generated such that <code>edges</code> represents a valid tree.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration + DFS

First, we construct an adjacency list `g` based on the edges given in the problem, where `g[a]` represents all the neighbor nodes of node `a` and their corresponding edge weights.

Then, we can enumerate each node `a` as the connecting intermediate node, and calculate the number of nodes `t` that start from the neighbor node `b` of `a` and whose distance to node `a` can be divided by `signalSpeed` through depth-first search. Then, the number of connectable node pairs of node `a` increases by `s * t`, where `s` represents the cumulative number of nodes that start from the neighbor node `b` of `a` and whose distance to node `a` cannot be divided by `signalSpeed`. Then we update `s` to `s + t`.

After enumerating all nodes `a`, we can get the number of connectable node pairs for all nodes.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$, where $n$ is the number of nodes.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPairsOfConnectableServers(
        self, edges: List[List[int]], signalSpeed: int
    ) -> List[int]:
        def dfs(a: int, fa: int, ws: int) -> int:
            cnt = 0 if ws % signalSpeed else 1
            for b, w in g[a]:
                if b != fa:
                    cnt += dfs(b, a, ws + w)
            return cnt

        n = len(edges) + 1
        g = [[] for _ in range(n)]
        for a, b, w in edges:
            g[a].append((b, w))
            g[b].append((a, w))
        ans = [0] * n
        for a in range(n):
            s = 0
            for b, w in g[a]:
                t = dfs(b, a, w)
                ans[a] += s * t
                s += t
        return ans
```

#### Java

```java
class Solution {
    private int signalSpeed;
    private List<int[]>[] g;

    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length + 1;
        g = new List[n];
        this.signalSpeed = signalSpeed;
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1], w = e[2];
            g[a].add(new int[] {b, w});
            g[b].add(new int[] {a, w});
        }
        int[] ans = new int[n];
        for (int a = 0; a < n; ++a) {
            int s = 0;
            for (var e : g[a]) {
                int b = e[0], w = e[1];
                int t = dfs(b, a, w);
                ans[a] += s * t;
                s += t;
            }
        }
        return ans;
    }

    private int dfs(int a, int fa, int ws) {
        int cnt = ws % signalSpeed == 0 ? 1 : 0;
        for (var e : g[a]) {
            int b = e[0], w = e[1];
            if (b != fa) {
                cnt += dfs(b, a, ws + w);
            }
        }
        return cnt;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> countPairsOfConnectableServers(vector<vector<int>>& edges, int signalSpeed) {
        int n = edges.size() + 1;
        vector<pair<int, int>> g[n];
        for (auto& e : edges) {
            int a = e[0], b = e[1], w = e[2];
            g[a].emplace_back(b, w);
            g[b].emplace_back(a, w);
        }
        function<int(int, int, int)> dfs = [&](int a, int fa, int ws) {
            int cnt = ws % signalSpeed == 0;
            for (auto& [b, w] : g[a]) {
                if (b != fa) {
                    cnt += dfs(b, a, ws + w);
                }
            }
            return cnt;
        };
        vector<int> ans(n);
        for (int a = 0; a < n; ++a) {
            int s = 0;
            for (auto& [b, w] : g[a]) {
                int t = dfs(b, a, w);
                ans[a] += s * t;
                s += t;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countPairsOfConnectableServers(edges [][]int, signalSpeed int) []int {
	n := len(edges) + 1
	type pair struct{ x, w int }
	g := make([][]pair, n)
	for _, e := range edges {
		a, b, w := e[0], e[1], e[2]
		g[a] = append(g[a], pair{b, w})
		g[b] = append(g[b], pair{a, w})
	}
	var dfs func(a, fa, ws int) int
	dfs = func(a, fa, ws int) int {
		cnt := 0
		if ws%signalSpeed == 0 {
			cnt++
		}
		for _, e := range g[a] {
			b, w := e.x, e.w
			if b != fa {
				cnt += dfs(b, a, ws+w)
			}
		}
		return cnt
	}
	ans := make([]int, n)
	for a := 0; a < n; a++ {
		s := 0
		for _, e := range g[a] {
			b, w := e.x, e.w
			t := dfs(b, a, w)
			ans[a] += s * t
			s += t
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countPairsOfConnectableServers(edges: number[][], signalSpeed: number): number[] {
    const n = edges.length + 1;
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [a, b, w] of edges) {
        g[a].push([b, w]);
        g[b].push([a, w]);
    }
    const dfs = (a: number, fa: number, ws: number): number => {
        let cnt = ws % signalSpeed === 0 ? 1 : 0;
        for (const [b, w] of g[a]) {
            if (b != fa) {
                cnt += dfs(b, a, ws + w);
            }
        }
        return cnt;
    };
    const ans: number[] = Array(n).fill(0);
    for (let a = 0; a < n; ++a) {
        let s = 0;
        for (const [b, w] of g[a]) {
            const t = dfs(b, a, w);
            ans[a] += s * t;
            s += t;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
