---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1245.Tree%20Diameter/README_EN.md
rating: 1792
source: Biweekly Contest 12 Q3
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Graph
    - Topological Sort
---

<!-- problem:start -->

# [1245. Tree Diameter 🔒](https://leetcode.com/problems/tree-diameter)

[中文文档](/solution/1200-1299/1245.Tree%20Diameter/README.md)

## Description

<!-- description:start -->

<p>The <strong>diameter</strong> of a tree is <strong>the number of edges</strong> in the longest path in that tree.</p>

<p>There is an undirected tree of <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>. You are given a 2D array <code>edges</code> where <code>edges.length == n - 1</code> and <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an undirected edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the tree.</p>

<p>Return <em>the <strong>diameter</strong> of the tree</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1245.Tree%20Diameter/images/tree1.jpg" style="width: 224px; height: 145px;" />
<pre>
<strong>Input:</strong> edges = [[0,1],[0,2]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The longest path of the tree is the path 1 - 0 - 2.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1245.Tree%20Diameter/images/tree2.jpg" style="width: 224px; height: 225px;" />
<pre>
<strong>Input:</strong> edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest path of the tree is the path 3 - 2 - 1 - 4 - 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == edges.length + 1</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two DFS Passes

First, we arbitrarily select a node and start a depth-first search (DFS) from this node to find the farthest node from it, denoted as node $a$. Then, we start another DFS from node $a$ to find the farthest node from node $a$, denoted as node $b$. It can be proven that the path between node $a$ and node $b$ is the diameter of the tree.

Time complexity is $O(n)$, and space complexity is $O(n)$, where $n$ is the number of nodes.

Similar problems:

- [1522. Diameter of N-Ary Tree 🔒](https://github.com/doocs/leetcode/blob/main/solution/1500-1599/1522.Diameter%20of%20N-Ary%20Tree/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def treeDiameter(self, edges: List[List[int]]) -> int:
        def dfs(i: int, fa: int, t: int):
            for j in g[i]:
                if j != fa:
                    dfs(j, i, t + 1)
            nonlocal ans, a
            if ans < t:
                ans = t
                a = i

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        ans = a = 0
        dfs(0, -1, 0)
        dfs(a, -1, 0)
        return ans
```

#### Java

```java
class Solution {
    private List<Integer>[] g;
    private int ans;
    private int a;

    public int treeDiameter(int[][] edges) {
        int n = edges.length + 1;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        dfs(0, -1, 0);
        dfs(a, -1, 0);
        return ans;
    }

    private void dfs(int i, int fa, int t) {
        for (int j : g[i]) {
            if (j != fa) {
                dfs(j, i, t + 1);
            }
        }
        if (ans < t) {
            ans = t;
            a = i;
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int treeDiameter(vector<vector<int>>& edges) {
        int n = edges.size() + 1;
        vector<int> g[n];
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        int ans = 0, a = 0;
        auto dfs = [&](this auto&& dfs, int i, int fa, int t) -> void {
            for (int j : g[i]) {
                if (j != fa) {
                    dfs(j, i, t + 1);
                }
            }
            if (ans < t) {
                ans = t;
                a = i;
            }
        };
        dfs(0, -1, 0);
        dfs(a, -1, 0);
        return ans;
    }
};
```

#### Go

```go
func treeDiameter(edges [][]int) (ans int) {
	n := len(edges) + 1
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	a := 0
	var dfs func(i, fa, t int)
	dfs = func(i, fa, t int) {
		for _, j := range g[i] {
			if j != fa {
				dfs(j, i, t+1)
			}
		}
		if ans < t {
			ans = t
			a = i
		}
	}
	dfs(0, -1, 0)
	dfs(a, -1, 0)
	return
}
```

#### TypeScript

```ts
function treeDiameter(edges: number[][]): number {
    const n = edges.length + 1;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    let [ans, a] = [0, 0];
    const dfs = (i: number, fa: number, t: number): void => {
        for (const j of g[i]) {
            if (j !== fa) {
                dfs(j, i, t + 1);
            }
        }
        if (ans < t) {
            ans = t;
            a = i;
        }
    };
    dfs(0, -1, 0);
    dfs(a, -1, 0);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
