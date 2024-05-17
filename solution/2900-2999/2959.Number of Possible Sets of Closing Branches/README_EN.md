---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2959.Number%20of%20Possible%20Sets%20of%20Closing%20Branches/README_EN.md
rating: 2077
source: Biweekly Contest 119 Q4
tags:
    - Bit Manipulation
    - Graph
    - Enumeration
    - Shortest Path
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2959. Number of Possible Sets of Closing Branches](https://leetcode.com/problems/number-of-possible-sets-of-closing-branches)

[中文文档](/solution/2900-2999/2959.Number%20of%20Possible%20Sets%20of%20Closing%20Branches/README.md)

## Description

<!-- description:start -->

<p>There is a company with <code>n</code> branches across the country, some of which are connected by roads. Initially, all branches are reachable from each other by traveling some roads.</p>

<p>The company has realized that they are spending an excessive amount of time traveling between their branches. As a result, they have decided to close down some of these branches (<strong>possibly none</strong>). However, they want to ensure that the remaining branches have a distance of at most <code>maxDistance</code> from each other.</p>

<p>The <strong>distance</strong> between two branches is the <strong>minimum</strong> total traveled length needed to reach one branch from another.</p>

<p>You are given integers <code>n</code>, <code>maxDistance</code>, and a <strong>0-indexed</strong> 2D array <code>roads</code>, where <code>roads[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> represents the <strong>undirected</strong> road between branches <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> with length <code>w<sub>i</sub></code>.</p>

<p>Return <em>the number of possible sets of closing branches, so that any branch has a distance of at most </em><code>maxDistance</code><em> from any other</em>.</p>

<p><strong>Note</strong> that, after closing a branch, the company will no longer have access to any roads connected to it.</p>

<p><strong>Note</strong> that, multiple roads are allowed.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2959.Number%20of%20Possible%20Sets%20of%20Closing%20Branches/images/example11.png" style="width: 221px; height: 191px;" />
<pre>
<strong>Input:</strong> n = 3, maxDistance = 5, roads = [[0,1,2],[1,2,10],[0,2,10]]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The possible sets of closing branches are:
- The set [2], after closing, active branches are [0,1] and they are reachable to each other within distance 2.
- The set [0,1], after closing, the active branch is [2].
- The set [1,2], after closing, the active branch is [0].
- The set [0,2], after closing, the active branch is [1].
- The set [0,1,2], after closing, there are no active branches.
It can be proven, that there are only 5 possible sets of closing branches.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2959.Number%20of%20Possible%20Sets%20of%20Closing%20Branches/images/example22.png" style="width: 221px; height: 241px;" />
<pre>
<strong>Input:</strong> n = 3, maxDistance = 5, roads = [[0,1,20],[0,1,10],[1,2,2],[0,2,2]]
<strong>Output:</strong> 7
<strong>Explanation:</strong> The possible sets of closing branches are:
- The set [], after closing, active branches are [0,1,2] and they are reachable to each other within distance 4.
- The set [0], after closing, active branches are [1,2] and they are reachable to each other within distance 2.
- The set [1], after closing, active branches are [0,2] and they are reachable to each other within distance 2.
- The set [0,1], after closing, the active branch is [2].
- The set [1,2], after closing, the active branch is [0].
- The set [0,2], after closing, the active branch is [1].
- The set [0,1,2], after closing, there are no active branches.
It can be proven, that there are only 7 possible sets of closing branches.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 1, maxDistance = 10, roads = []
<strong>Output:</strong> 2
<strong>Explanation:</strong> The possible sets of closing branches are:
- The set [], after closing, the active branch is [0].
- The set [0], after closing, there are no active branches.
It can be proven, that there are only 2 possible sets of closing branches.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10</code></li>
	<li><code>1 &lt;= maxDistance &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= roads.length &lt;= 1000</code></li>
	<li><code>roads[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= 1000</code></li>
	<li>All branches are reachable from each other by traveling some roads.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Enumeration + Floyd Algorithm

We notice that $n \leq 10$, so we might as well consider using the method of binary enumeration to enumerate all subsets of departments.

For each subset of departments, we can use the Floyd algorithm to calculate the shortest distance between the remaining departments, and then judge whether it meets the requirements of the problem. Specifically, we first enumerate the middle point $k$, then enumerate the starting point $i$ and the ending point $j$. If $g[i][k] + g[k][j] < g[i][j]$, then we update $g[i][j]$ with the shorter distance $g[i][k] + g[k][j]$.

The time complexity is $O(2^n \times (n^3 + m))$, and the space complexity is $O(n^2)$. Here, $n$ and $m$ are the number of departments and the number of roads, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfSets(self, n: int, maxDistance: int, roads: List[List[int]]) -> int:
        ans = 0
        for mask in range(1 << n):
            g = [[inf] * n for _ in range(n)]
            for u, v, w in roads:
                if mask >> u & 1 and mask > v & 1:
                    g[u][v] = min(g[u][v], w)
                    g[v][u] = min(g[v][u], w)
            for k in range(n):
                if mask >> k & 1:
                    g[k][k] = 0
                    for i in range(n):
                        for j in range(n):
                            # g[i][j] = min(g[i][j], g[i][k] + g[k][j])
                            if g[i][k] + g[k][j] < g[i][j]:
                                g[i][j] = g[i][k] + g[k][j]
            if all(
                g[i][j] <= maxDistance
                for i in range(n)
                for j in range(n)
                if mask >> i & 1 and mask >> j & 1
            ):
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        int ans = 0;
        for (int mask = 0; mask < 1 << n; ++mask) {
            int[][] g = new int[n][n];
            for (var e : g) {
                Arrays.fill(e, 1 << 29);
            }
            for (var e : roads) {
                int u = e[0], v = e[1], w = e[2];
                if ((mask >> u & 1) == 1 && (mask >> v & 1) == 1) {
                    g[u][v] = Math.min(g[u][v], w);
                    g[v][u] = Math.min(g[v][u], w);
                }
            }
            for (int k = 0; k < n; ++k) {
                if ((mask >> k & 1) == 1) {
                    g[k][k] = 0;
                    for (int i = 0; i < n; ++i) {
                        for (int j = 0; j < n; ++j) {
                            g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
                        }
                    }
                }
            }
            int ok = 1;
            for (int i = 0; i < n && ok == 1; ++i) {
                for (int j = 0; j < n && ok == 1; ++j) {
                    if ((mask >> i & 1) == 1 && (mask >> j & 1) == 1) {
                        if (g[i][j] > maxDistance) {
                            ok = 0;
                        }
                    }
                }
            }
            ans += ok;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfSets(int n, int maxDistance, vector<vector<int>>& roads) {
        int ans = 0;
        for (int mask = 0; mask < 1 << n; ++mask) {
            int g[n][n];
            memset(g, 0x3f, sizeof(g));
            for (auto& e : roads) {
                int u = e[0], v = e[1], w = e[2];
                if ((mask >> u & 1) & (mask >> v & 1)) {
                    g[u][v] = min(g[u][v], w);
                    g[v][u] = min(g[v][u], w);
                }
            }
            for (int k = 0; k < n; ++k) {
                if (mask >> k & 1) {
                    g[k][k] = 0;
                    for (int i = 0; i < n; ++i) {
                        for (int j = 0; j < n; ++j) {
                            g[i][j] = min(g[i][j], g[i][k] + g[k][j]);
                        }
                    }
                }
            }
            int ok = 1;
            for (int i = 0; i < n && ok == 1; ++i) {
                for (int j = 0; j < n && ok == 1; ++j) {
                    if ((mask >> i & 1) & (mask >> j & 1) && g[i][j] > maxDistance) {
                        ok = 0;
                    }
                }
            }
            ans += ok;
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfSets(n int, maxDistance int, roads [][]int) (ans int) {
	for mask := 0; mask < 1<<n; mask++ {
		g := make([][]int, n)
		for i := range g {
			g[i] = make([]int, n)
			for j := range g[i] {
				g[i][j] = 1 << 29
			}
		}
		for _, e := range roads {
			u, v, w := e[0], e[1], e[2]
			if mask>>u&1 == 1 && mask>>v&1 == 1 {
				g[u][v] = min(g[u][v], w)
				g[v][u] = min(g[v][u], w)
			}
		}
		for k := 0; k < n; k++ {
			if mask>>k&1 == 1 {
				g[k][k] = 0
				for i := 0; i < n; i++ {
					for j := 0; j < n; j++ {
						g[i][j] = min(g[i][j], g[i][k]+g[k][j])
					}
				}
			}
		}
		ok := 1
		for i := 0; i < n && ok == 1; i++ {
			for j := 0; j < n && ok == 1; j++ {
				if mask>>i&1 == 1 && mask>>j&1 == 1 && g[i][j] > maxDistance {
					ok = 0
				}
			}
		}
		ans += ok
	}
	return
}
```

#### TypeScript

```ts
function numberOfSets(n: number, maxDistance: number, roads: number[][]): number {
    let ans = 0;
    for (let mask = 0; mask < 1 << n; ++mask) {
        const g: number[][] = Array.from({ length: n }, () => Array(n).fill(Infinity));
        for (const [u, v, w] of roads) {
            if ((mask >> u) & 1 && (mask >> v) & 1) {
                g[u][v] = Math.min(g[u][v], w);
                g[v][u] = Math.min(g[v][u], w);
            }
        }
        for (let k = 0; k < n; ++k) {
            if ((mask >> k) & 1) {
                g[k][k] = 0;
                for (let i = 0; i < n; ++i) {
                    for (let j = 0; j < n; ++j) {
                        g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
                    }
                }
            }
        }
        let ok = 1;
        for (let i = 0; i < n && ok; ++i) {
            for (let j = 0; j < n && ok; ++j) {
                if ((mask >> i) & 1 && (mask >> j) & 1 && g[i][j] > maxDistance) {
                    ok = 0;
                }
            }
        }
        ans += ok;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
