# [2858. Minimum Edge Reversals So Every Node Is Reachable](https://leetcode.com/problems/minimum-edge-reversals-so-every-node-is-reachable)

[中文文档](/solution/2800-2899/2858.Minimum%20Edge%20Reversals%20So%20Every%20Node%20Is%20Reachable/README.md)

## Description

<p>There is a <strong>simple directed graph</strong> with <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>. The graph would form a <strong>tree</strong> if its edges were bi-directional.</p>

<p>You are given an integer <code>n</code> and a <strong>2D</strong> integer array <code>edges</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> represents a <strong>directed edge</strong> going from node <code>u<sub>i</sub></code> to node <code>v<sub>i</sub></code>.</p>

<p>An <strong>edge reversal</strong> changes the direction of an edge, i.e., a directed edge going from node <code>u<sub>i</sub></code> to node <code>v<sub>i</sub></code> becomes a directed edge going from node <code>v<sub>i</sub></code> to node <code>u<sub>i</sub></code>.</p>

<p>For every node <code>i</code> in the range <code>[0, n - 1]</code>, your task is to <strong>independently</strong> calculate the <strong>minimum</strong> number of <strong>edge reversals</strong> required so it is possible to reach any other node starting from node <code>i</code> through a <strong>sequence</strong> of <strong>directed edges</strong>.</p>

<p>Return <em>an integer array </em><code>answer</code><em>, where </em><code>answer[i]</code><em> is the</em><em> </em> <em><strong>minimum</strong> number of <strong>edge reversals</strong> required so it is possible to reach any other node starting from node </em><code>i</code><em> through a <strong>sequence</strong> of <strong>directed edges</strong>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img height="246" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2858.Minimum%20Edge%20Reversals%20So%20Every%20Node%20Is%20Reachable/images/image-20230826221104-3.png" width="312" /></p>

<pre>
<strong>Input:</strong> n = 4, edges = [[2,0],[2,1],[1,3]]
<strong>Output:</strong> [1,1,0,2]
<strong>Explanation:</strong> The image above shows the graph formed by the edges.
For node 0: after reversing the edge [2,0], it is possible to reach any other node starting from node 0.
So, answer[0] = 1.
For node 1: after reversing the edge [2,1], it is possible to reach any other node starting from node 1.
So, answer[1] = 1.
For node 2: it is already possible to reach any other node starting from node 2.
So, answer[2] = 0.
For node 3: after reversing the edges [1,3] and [2,1], it is possible to reach any other node starting from node 3.
So, answer[3] = 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><img height="217" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2858.Minimum%20Edge%20Reversals%20So%20Every%20Node%20Is%20Reachable/images/image-20230826225541-2.png" width="322" /></p>

<pre>
<strong>Input:</strong> n = 3, edges = [[1,2],[2,0]]
<strong>Output:</strong> [2,0,1]
<strong>Explanation:</strong> The image above shows the graph formed by the edges.
For node 0: after reversing the edges [2,0] and [1,2], it is possible to reach any other node starting from node 0.
So, answer[0] = 2.
For node 1: it is already possible to reach any other node starting from node 1.
So, answer[1] = 0.
For node 2: after reversing the edge [1, 2], it is possible to reach any other node starting from node 2.
So, answer[2] = 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= u<sub>i</sub> == edges[i][0] &lt; n</code></li>
	<li><code>0 &lt;= v<sub>i</sub> == edges[i][1] &lt; n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li>The input is generated such&nbsp;that if the edges were bi-directional, the graph would be a tree.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def minEdgeReversals(self, n: int, edges: List[List[int]]) -> List[int]:
        ans = [0] * n
        g = [[] for _ in range(n)]
        for x, y in edges:
            g[x].append((y, 1))
            g[y].append((x, -1))

        def dfs(i: int, fa: int):
            for j, k in g[i]:
                if j != fa:
                    ans[0] += int(k < 0)
                    dfs(j, i)

        dfs(0, -1)

        def dfs2(i: int, fa: int):
            for j, k in g[i]:
                if j != fa:
                    ans[j] = ans[i] + k
                    dfs2(j, i)

        dfs2(0, -1)
        return ans
```

```java
class Solution {
    private List<int[]>[] g;
    private int[] ans;

    public int[] minEdgeReversals(int n, int[][] edges) {
        ans = new int[n];
        g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int x = e[0], y = e[1];
            g[x].add(new int[] {y, 1});
            g[y].add(new int[] {x, -1});
        }
        dfs(0, -1);
        dfs2(0, -1);
        return ans;
    }

    private void dfs(int i, int fa) {
        for (var ne : g[i]) {
            int j = ne[0], k = ne[1];
            if (j != fa) {
                ans[0] += k < 0 ? 1 : 0;
                dfs(j, i);
            }
        }
    }

    private void dfs2(int i, int fa) {
        for (var ne : g[i]) {
            int j = ne[0], k = ne[1];
            if (j != fa) {
                ans[j] = ans[i] + k;
                dfs2(j, i);
            }
        }
    }
}
```

```cpp
class Solution {
public:
    vector<int> minEdgeReversals(int n, vector<vector<int>>& edges) {
        vector<pair<int, int>> g[n];
        vector<int> ans(n);
        for (auto& e : edges) {
            int x = e[0], y = e[1];
            g[x].emplace_back(y, 1);
            g[y].emplace_back(x, -1);
        }
        function<void(int, int)> dfs = [&](int i, int fa) {
            for (auto& [j, k] : g[i]) {
                if (j != fa) {
                    ans[0] += k < 0;
                    dfs(j, i);
                }
            }
        };
        function<void(int, int)> dfs2 = [&](int i, int fa) {
            for (auto& [j, k] : g[i]) {
                if (j != fa) {
                    ans[j] = ans[i] + k;
                    dfs2(j, i);
                }
            }
        };
        dfs(0, -1);
        dfs2(0, -1);
        return ans;
    }
};
```

```go
func minEdgeReversals(n int, edges [][]int) []int {
	g := make([][][2]int, n)
	for _, e := range edges {
		x, y := e[0], e[1]
		g[x] = append(g[x], [2]int{y, 1})
		g[y] = append(g[y], [2]int{x, -1})
	}
	ans := make([]int, n)
	var dfs func(int, int)
	var dfs2 func(int, int)
	dfs = func(i, fa int) {
		for _, ne := range g[i] {
			j, k := ne[0], ne[1]
			if j != fa {
				if k < 0 {
					ans[0]++
				}
				dfs(j, i)
			}
		}
	}
	dfs2 = func(i, fa int) {
		for _, ne := range g[i] {
			j, k := ne[0], ne[1]
			if j != fa {
				ans[j] = ans[i] + k
				dfs2(j, i)
			}
		}
	}
	dfs(0, -1)
	dfs2(0, -1)
	return ans
}
```

```ts
function minEdgeReversals(n: number, edges: number[][]): number[] {
    const g: number[][][] = Array.from({ length: n }, () => []);
    for (const [x, y] of edges) {
        g[x].push([y, 1]);
        g[y].push([x, -1]);
    }
    const ans: number[] = Array(n).fill(0);
    const dfs = (i: number, fa: number) => {
        for (const [j, k] of g[i]) {
            if (j !== fa) {
                ans[0] += k < 0 ? 1 : 0;
                dfs(j, i);
            }
        }
    };
    const dfs2 = (i: number, fa: number) => {
        for (const [j, k] of g[i]) {
            if (j !== fa) {
                ans[j] = ans[i] + k;
                dfs2(j, i);
            }
        }
    };
    dfs(0, -1);
    dfs2(0, -1);
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
