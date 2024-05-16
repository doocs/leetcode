---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1466.Reorder%20Routes%20to%20Make%20All%20Paths%20Lead%20to%20the%20City%20Zero/README_EN.md
rating: 1633
source: Weekly Contest 191 Q3
tags:
    - Depth-First Search
    - Breadth-First Search
    - Graph
---

<!-- problem:start -->

# [1466. Reorder Routes to Make All Paths Lead to the City Zero](https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero)

[中文文档](/solution/1400-1499/1466.Reorder%20Routes%20to%20Make%20All%20Paths%20Lead%20to%20the%20City%20Zero/README.md)

## Description

<p>There are <code>n</code> cities numbered from <code>0</code> to <code>n - 1</code> and <code>n - 1</code> roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.</p>

<p>Roads are represented by <code>connections</code> where <code>connections[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> represents a road from city <code>a<sub>i</sub></code> to city <code>b<sub>i</sub></code>.</p>

<p>This year, there will be a big event in the capital (city <code>0</code>), and many people want to travel to this city.</p>

<p>Your task consists of reorienting some roads such that each city can visit the city <code>0</code>. Return the <strong>minimum</strong> number of edges changed.</p>

<p>It&#39;s <strong>guaranteed</strong> that each city can reach city <code>0</code> after reorder.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1466.Reorder%20Routes%20to%20Make%20All%20Paths%20Lead%20to%20the%20City%20Zero/images/sample_1_1819.png" style="width: 311px; height: 189px;" />
<pre>
<strong>Input:</strong> n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
<strong>Output:</strong> 3
<strong>Explanation: </strong>Change the direction of edges show in red such that each node can reach the node 0 (capital).
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1466.Reorder%20Routes%20to%20Make%20All%20Paths%20Lead%20to%20the%20City%20Zero/images/sample_2_1819.png" style="width: 509px; height: 79px;" />
<pre>
<strong>Input:</strong> n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
<strong>Output:</strong> 2
<strong>Explanation: </strong>Change the direction of edges show in red such that each node can reach the node 0 (capital).
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 3, connections = [[1,0],[2,0]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>connections.length == n - 1</code></li>
	<li><code>connections[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: DFS

The route map given in the problem has $n$ nodes and $n-1$ edges. If we ignore the direction of the edges, then these $n$ nodes form a tree. The problem requires us to change the direction of some edges so that each node can reach node $0$.

We might as well consider starting from node $0$ and reaching all other nodes. The direction is opposite to the problem description, which means that when we build the graph, for the directed edge $[a, b]$, we should regard it as the directed edge $[b, a]$. That is to say, if it is from $a$ to $b$, we need to change the direction once; if it is from $b$ to $a$, no direction change is needed.

Next, we only need to start from node $0$, search all other nodes, and during the process, if we encounter an edge that needs to change direction, we accumulate the number of direction changes once.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes in the problem.

<!-- tabs:start -->

```python
class Solution:
    def minReorder(self, n: int, connections: List[List[int]]) -> int:
        def dfs(a: int, fa: int) -> int:
            return sum(c + dfs(b, a) for b, c in g[a] if b != fa)

        g = [[] for _ in range(n)]
        for a, b in connections:
            g[a].append((b, 1))
            g[b].append((a, 0))
        return dfs(0, -1)
```

```java
class Solution {
    private List<int[]>[] g;

    public int minReorder(int n, int[][] connections) {
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : connections) {
            int a = e[0], b = e[1];
            g[a].add(new int[] {b, 1});
            g[b].add(new int[] {a, 0});
        }
        return dfs(0, -1);
    }

    private int dfs(int a, int fa) {
        int ans = 0;
        for (var e : g[a]) {
            int b = e[0], c = e[1];
            if (b != fa) {
                ans += c + dfs(b, a);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minReorder(int n, vector<vector<int>>& connections) {
        vector<pair<int, int>> g[n];
        for (auto& e : connections) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b, 1);
            g[b].emplace_back(a, 0);
        }
        function<int(int, int)> dfs = [&](int a, int fa) {
            int ans = 0;
            for (auto& [b, c] : g[a]) {
                if (b != fa) {
                    ans += c + dfs(b, a);
                }
            }
            return ans;
        };
        return dfs(0, -1);
    }
};
```

```go
func minReorder(n int, connections [][]int) int {
	g := make([][][2]int, n)
	for _, e := range connections {
		a, b := e[0], e[1]
		g[a] = append(g[a], [2]int{b, 1})
		g[b] = append(g[b], [2]int{a, 0})
	}
	var dfs func(int, int) int
	dfs = func(a, fa int) (ans int) {
		for _, e := range g[a] {
			if b, c := e[0], e[1]; b != fa {
				ans += c + dfs(b, a)
			}
		}
		return
	}
	return dfs(0, -1)
}
```

```ts
function minReorder(n: number, connections: number[][]): number {
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [a, b] of connections) {
        g[a].push([b, 1]);
        g[b].push([a, 0]);
    }
    const dfs = (a: number, fa: number): number => {
        let ans = 0;
        for (const [b, c] of g[a]) {
            if (b !== fa) {
                ans += c + dfs(b, a);
            }
        }
        return ans;
    };
    return dfs(0, -1);
}
```

```rust
impl Solution {
    pub fn min_reorder(n: i32, connections: Vec<Vec<i32>>) -> i32 {
        let mut g: Vec<Vec<(i32, i32)>> = vec![vec![]; n as usize];
        for e in connections.iter() {
            let a = e[0] as usize;
            let b = e[1] as usize;
            g[a].push((b as i32, 1));
            g[b].push((a as i32, 0));
        }
        fn dfs(a: usize, fa: i32, g: &Vec<Vec<(i32, i32)>>) -> i32 {
            let mut ans = 0;
            for &(b, c) in g[a].iter() {
                if b != fa {
                    ans += c + dfs(b as usize, a as i32, g);
                }
            }
            ans
        }
        dfs(0, -1, &g)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
