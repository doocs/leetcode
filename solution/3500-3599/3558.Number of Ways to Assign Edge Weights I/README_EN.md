---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3558.Number%20of%20Ways%20to%20Assign%20Edge%20Weights%20I/README_EN.md
rating: 1845
source: Biweekly Contest 157 Q3
tags:
    - Tree
    - Depth-First Search
    - Math
---

<!-- problem:start -->

# [3558. Number of Ways to Assign Edge Weights I](https://leetcode.com/problems/number-of-ways-to-assign-edge-weights-i)

[中文文档](/solution/3500-3599/3558.Number%20of%20Ways%20to%20Assign%20Edge%20Weights%20I/README.md)

## Description

<!-- description:start -->

<p>There is an undirected tree with <code>n</code> nodes labeled from 1 to <code>n</code>, rooted at node 1. The tree is represented by a 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates that there is an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>.</p>

<p>Initially, all edges have a weight of 0. You must assign each edge a weight of either <strong>1</strong> or <strong>2</strong>.</p>

<p>The <strong>cost</strong> of a path between any two nodes <code>u</code> and <code>v</code> is the total weight of all edges in the path connecting them.</p>

<p>Select any one node <code>x</code> at the <strong>maximum</strong> depth. Return the number of ways to assign edge weights in the path from node 1 to <code>x</code> such that its total cost is <strong>odd</strong>.</p>

<p>Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p><strong>Note:</strong> Ignore all edges <strong>not</strong> in the path from node 1 to <code>x</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3558.Number%20of%20Ways%20to%20Assign%20Edge%20Weights%20I/images/screenshot-2025-03-24-at-060006.png" style="width: 200px; height: 72px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The path from Node 1 to Node 2 consists of one edge (<code>1 &rarr; 2</code>).</li>
	<li>Assigning weight 1 makes the cost odd, while 2 makes it even. Thus, the number of valid assignments is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3558.Number%20of%20Ways%20to%20Assign%20Edge%20Weights%20I/images/screenshot-2025-03-24-at-055820.png" style="width: 220px; height: 207px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[1,2],[1,3],[3,4],[3,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The maximum depth is 2, with nodes 4 and 5 at the same depth. Either node can be selected for processing.</li>
	<li>For example, the path from Node 1 to Node 4 consists of two edges (<code>1 &rarr; 3</code> and <code>3 &rarr; 4</code>).</li>
	<li>Assigning weights (1,2) or (2,1) results in an odd cost. Thus, the number of valid assignments is 2.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i] == [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n</code></li>
	<li><code>edges</code> represents a valid tree.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS + Mathematics

First, we build an adjacency list $g$ from the edges, where $g[u]$ contains all neighbors of node $u$.

Next, we use a function $\textit{dfs}$ to compute the depth $d$ of the tree. The answer is the number of ways to choose an odd number of elements from $d$. According to a well-known combinatorial identity, the number of ways to choose an odd number of elements from a set of size $d$ is $2^{d-1}$. Therefore, we can compute the answer using fast exponentiation.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of nodes in the tree.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def assignEdgeWeights(self, edges: List[List[int]]) -> int:
        def dfs(i: int, fa: int = 0) -> int:
            res = 0
            for j in g[i]:
                if j != fa:
                    res = max(res, dfs(j, i) + 1)
            return res

        n = len(edges) + 1
        g = [[] for _ in range(n + 1)]
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        d = dfs(1)
        return pow(2, d - 1, 10**9 + 7)
```

#### Java

```java
class Solution {
    private List<Integer>[] g;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        g = new List[n + 1];
        Arrays.setAll(g, k -> new ArrayList<>());

        for (var e : edges) {
            int u = e[0];
            int v = e[1];
            g[u].add(v);
            g[v].add(u);
        }

        return (int) pow(2, dfs(1, 0) - 1, 1_000_000_007);
    }

    private int dfs(int i, int fa) {
        int res = 0;
        for (int j : g[i]) {
            if (j != fa) {
                res = Math.max(res, dfs(j, i) + 1);
            }
        }
        return res;
    }

    private long pow(long a, int n, int mod) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) != 0) {
                res = res * a % mod;
            }
            a = a * a % mod;
            n >>= 1;
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int assignEdgeWeights(vector<vector<int>>& edges) {
        int n = edges.size() + 1;
        vector<vector<int>> g(n + 1);

        for (auto& e : edges) {
            int u = e[0];
            int v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }

        auto dfs = [&](this auto&& dfs, int i, int fa) -> int {
            int res = 0;
            for (int j : g[i]) {
                if (j != fa) {
                    res = max(res, dfs(j, i) + 1);
                }
            }
            return res;
        };

        return pow(2, dfs(1, 0) - 1, 1000000007);
    }

private:
    long long pow(long long a, int n, int mod) {
        long long res = 1;
        while (n > 0) {
            if (n & 1) {
                res = res * a % mod;
            }
            a = a * a % mod;
            n >>= 1;
        }
        return res;
    }
};
```

#### Go

```go
func assignEdgeWeights(edges [][]int) int {
	const mod = 1_000_000_007

	n := len(edges) + 1
	g := make([][]int, n+1)

	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}

	var dfs func(int, int) int
	dfs = func(i, fa int) int {
		res := 0
		for _, j := range g[i] {
			if j != fa {
				res = max(res, dfs(j, i)+1)
			}
		}
		return res
	}

	return pow(2, dfs(1, 0)-1, mod)
}

func pow(a, n, mod int) int {
	res := 1
	for n > 0 {
		if n&1 > 0 {
			res = res * a % mod
		}
		a = a * a % mod
		n >>= 1
	}
	return res
}
```

#### TypeScript

```ts
function assignEdgeWeights(edges: number[][]): number {
    const mod = 1_000_000_007;
    const n = edges.length + 1;
    const g: number[][] = Array.from({ length: n + 1 }, () => []);

    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }

    const dfs = (i: number, fa: number): number => {
        let res = 0;
        for (const j of g[i]) {
            if (j !== fa) {
                res = Math.max(res, dfs(j, i) + 1);
            }
        }
        return res;
    };

    const pow = (a: number, n: number, mod: number): number => {
        let res = 1n;
        let x = BigInt(a);
        const m = BigInt(mod);

        while (n > 0) {
            if (n & 1) {
                res = (res * x) % m;
            }
            x = (x * x) % m;
            n >>= 1;
        }

        return Number(res);
    };

    return pow(2, dfs(1, 0) - 1, mod);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
