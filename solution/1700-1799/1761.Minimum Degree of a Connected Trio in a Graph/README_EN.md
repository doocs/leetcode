---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1761.Minimum%20Degree%20of%20a%20Connected%20Trio%20in%20a%20Graph/README_EN.md
rating: 2005
source: Weekly Contest 228 Q4
tags:
    - Graph
---

<!-- problem:start -->

# [1761. Minimum Degree of a Connected Trio in a Graph](https://leetcode.com/problems/minimum-degree-of-a-connected-trio-in-a-graph)

[中文文档](/solution/1700-1799/1761.Minimum%20Degree%20of%20a%20Connected%20Trio%20in%20a%20Graph/README.md)

## Description

<!-- description:start -->

<p>You are given an undirected graph. You are given an integer <code>n</code> which is the number of nodes in the graph and an array <code>edges</code>, where each <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates that there is an undirected edge between <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>.</p>

<p>A <strong>connected trio</strong> is a set of <strong>three</strong> nodes where there is an edge between <b>every</b> pair of them.</p>

<p>The <strong>degree of a connected trio</strong> is the number of edges where one endpoint is in the trio, and the other is not.</p>

<p>Return <em>the <strong>minimum</strong> degree of a connected trio in the graph, or</em> <code>-1</code> <em>if the graph has no connected trios.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1761.Minimum%20Degree%20of%20a%20Connected%20Trio%20in%20a%20Graph/images/trios1.png" style="width: 388px; height: 164px;" />
<pre>
<strong>Input:</strong> n = 6, edges = [[1,2],[1,3],[3,2],[4,1],[5,2],[3,6]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There is exactly one trio, which is [1,2,3]. The edges that form its degree are bolded in the figure above.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1761.Minimum%20Degree%20of%20a%20Connected%20Trio%20in%20a%20Graph/images/trios2.png" style="width: 388px; height: 164px;" />
<pre>
<strong>Input:</strong> n = 7, edges = [[1,3],[4,1],[4,3],[2,5],[5,6],[6,7],[7,5],[2,6]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are exactly three trios:
1) [1,4,3] with degree 0.
2) [2,5,6] with degree 2.
3) [5,6,7] with degree 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 400</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>1 &lt;= edges.length &lt;= n * (n-1) / 2</code></li>
	<li><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n</code></li>
	<li><code>u<sub>i </sub>!= v<sub>i</sub></code></li>
	<li>There are no repeated edges.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Brute Force Enumeration

We first store all edges in the adjacency matrix $\textit{g}$, and then store the degree of each node in the array $\textit{deg}$. Initialize the answer $\textit{ans} = +\infty$.

Then enumerate all triplets $(i, j, k)$, where $i \lt j \lt k$. If $\textit{g}[i][j] = \textit{g}[j][k] = \textit{g}[i][k] = 1$, it means these three nodes form a connected trio. In this case, update the answer to $\textit{ans} = \min(\textit{ans}, \textit{deg}[i] + \textit{deg}[j] + \textit{deg}[k] - 6)$.

After enumerating all triplets, if the answer is still $+\infty$, it means there is no connected trio in the graph, return $-1$. Otherwise, return the answer.

The time complexity is $O(n^3)$, and the space complexity is $O(n^2)$. Here, $n$ is the number of nodes.

<!-- tabs:start -->

#### Python3

```python
def min(a: int, b: int) -> int:
    return a if a < b else b


class Solution:
    def minTrioDegree(self, n: int, edges: List[List[int]]) -> int:
        g = [[False] * n for _ in range(n)]
        deg = [0] * n
        for u, v in edges:
            u, v = u - 1, v - 1
            g[u][v] = g[v][u] = True
            deg[u] += 1
            deg[v] += 1
        ans = inf
        for i in range(n):
            for j in range(i + 1, n):
                if g[i][j]:
                    for k in range(j + 1, n):
                        if g[i][k] and g[j][k]:
                            ans = min(ans, deg[i] + deg[j] + deg[k] - 6)
        return -1 if ans == inf else ans
```

#### Java

```java
class Solution {
    public int minTrioDegree(int n, int[][] edges) {
        boolean[][] g = new boolean[n][n];
        int[] deg = new int[n];
        for (var e : edges) {
            int u = e[0] - 1, v = e[1] - 1;
            g[u][v] = true;
            g[v][u] = true;
            ++deg[u];
            ++deg[v];
        }
        int ans = 1 << 30;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (g[i][j]) {
                    for (int k = j + 1; k < n; ++k) {
                        if (g[i][k] && g[j][k]) {
                            ans = Math.min(ans, deg[i] + deg[j] + deg[k] - 6);
                        }
                    }
                }
            }
        }
        return ans == 1 << 30 ? -1 : ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minTrioDegree(int n, vector<vector<int>>& edges) {
        bool g[n][n];
        memset(g, 0, sizeof g);
        int deg[n];
        memset(deg, 0, sizeof deg);
        for (auto& e : edges) {
            int u = e[0] - 1, v = e[1] - 1;
            g[u][v] = g[v][u] = true;
            deg[u]++, deg[v]++;
        }
        int ans = INT_MAX;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (g[i][j]) {
                    for (int k = j + 1; k < n; ++k) {
                        if (g[j][k] && g[i][k]) {
                            ans = min(ans, deg[i] + deg[j] + deg[k] - 6);
                        }
                    }
                }
            }
        }
        return ans == INT_MAX ? -1 : ans;
    }
};
```

#### Go

```go
func minTrioDegree(n int, edges [][]int) int {
	g := make([][]bool, n)
	deg := make([]int, n)
	for i := range g {
		g[i] = make([]bool, n)
	}
	for _, e := range edges {
		u, v := e[0]-1, e[1]-1
		g[u][v], g[v][u] = true, true
		deg[u]++
		deg[v]++
	}
	ans := 1 << 30
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			if g[i][j] {
				for k := j + 1; k < n; k++ {
					if g[i][k] && g[j][k] {
						ans = min(ans, deg[i]+deg[j]+deg[k]-6)
					}
				}
			}
		}
	}
	if ans == 1<<30 {
		return -1
	}
	return ans
}
```

#### TypeScript

```ts
function minTrioDegree(n: number, edges: number[][]): number {
    const g = Array.from({ length: n }, () => Array(n).fill(false));
    const deg: number[] = Array(n).fill(0);
    for (let [u, v] of edges) {
        u--;
        v--;
        g[u][v] = g[v][u] = true;
        ++deg[u];
        ++deg[v];
    }
    let ans = Infinity;
    for (let i = 0; i < n; ++i) {
        for (let j = i + 1; j < n; ++j) {
            if (g[i][j]) {
                for (let k = j + 1; k < n; ++k) {
                    if (g[i][k] && g[j][k]) {
                        ans = Math.min(ans, deg[i] + deg[j] + deg[k] - 6);
                    }
                }
            }
        }
    }
    return ans === Infinity ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
