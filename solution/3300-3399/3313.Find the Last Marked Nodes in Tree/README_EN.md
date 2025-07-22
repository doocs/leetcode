---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3313.Find%20the%20Last%20Marked%20Nodes%20in%20Tree/README_EN.md
tags:
    - Tree
    - Depth-First Search
---

<!-- problem:start -->

# [3313. Find the Last Marked Nodes in Tree 🔒](https://leetcode.com/problems/find-the-last-marked-nodes-in-tree)

[中文文档](/solution/3300-3399/3313.Find%20the%20Last%20Marked%20Nodes%20in%20Tree/README.md)

## Description

<!-- description:start -->

<p>There exists an <strong>undirected</strong> tree with <code>n</code> nodes numbered <code>0</code> to <code>n - 1</code>. You are given a 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates that there is an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> in the tree.</p>

<p>Initially, <strong>all</strong> nodes are <strong>unmarked</strong>. After every second, you mark all unmarked nodes which have <strong>at least</strong> one marked node <em>adjacent</em> to them.</p>

<p>Return an array <code>nodes</code> where <code>nodes[i]</code> is the last node to get marked in the tree, if you mark node <code>i</code> at time <code>t = 0</code>. If <code>nodes[i]</code> has <em>multiple</em> answers for any node <code>i</code>, you can choose<strong> any</strong> one answer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1],[0,2]]</span></p>

<p><strong>Output:</strong> [2,2,1]</p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3313.Find%20the%20Last%20Marked%20Nodes%20in%20Tree/images/screenshot-2024-06-02-122236.png" style="width: 450px; height: 217px;" /></p>

<ul>
	<li>For <code>i = 0</code>, the nodes are marked in the sequence: <code>[0] -&gt; [0,1,2]</code>. Either 1 or 2 can be the answer.</li>
	<li>For <code>i = 1</code>, the nodes are marked in the sequence: <code>[1] -&gt; [0,1] -&gt; [0,1,2]</code>. Node 2 is marked last.</li>
	<li>For <code>i = 2</code>, the nodes are marked in the sequence: <code>[2] -&gt; [0,2] -&gt; [0,1,2]</code>. Node 1 is marked last.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1]]</span></p>

<p><strong>Output:</strong> [1,0]</p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3313.Find%20the%20Last%20Marked%20Nodes%20in%20Tree/images/screenshot-2024-06-02-122249.png" style="width: 350px; height: 180px;" /></p>

<ul>
	<li>For <code>i = 0</code>, the nodes are marked in the sequence: <code>[0] -&gt; [0,1]</code>.</li>
	<li>For <code>i = 1</code>, the nodes are marked in the sequence: <code>[1] -&gt; [0,1]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1],[0,2],[2,3],[2,4]]</span></p>

<p><strong>Output:</strong> [3,3,1,1,1]</p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3313.Find%20the%20Last%20Marked%20Nodes%20in%20Tree/images/screenshot-2024-06-03-210550.png" style="height: 240px; width: 450px;" /></p>

<ul>
	<li>For <code>i = 0</code>, the nodes are marked in the sequence: <code>[0] -&gt; [0,1,2] -&gt; [0,1,2,3,4]</code>.</li>
	<li>For <code>i = 1</code>, the nodes are marked in the sequence: <code>[1] -&gt; [0,1] -&gt; [0,1,2] -&gt; [0,1,2,3,4]</code>.</li>
	<li>For <code>i = 2</code>, the nodes are marked in the sequence: <code>[2] -&gt; [0,2,3,4] -&gt; [0,1,2,3,4]</code>.</li>
	<li>For <code>i = 3</code>, the nodes are marked in the sequence: <code>[3] -&gt; [2,3] -&gt; [0,2,3,4] -&gt; [0,1,2,3,4]</code>.</li>
	<li>For <code>i = 4</code>, the nodes are marked in the sequence: <code>[4] -&gt; [2,4] -&gt; [0,2,3,4] -&gt; [0,1,2,3,4]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= edges[i][0], edges[i][1] &lt;= n - 1</code></li>
	<li>The input is generated such that <code>edges</code> represents a valid tree.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Find the Diameter of the Tree + DFS

According to the problem description, the last marked node must be one endpoint of the tree's diameter, because the distance from any node on the diameter to any other node on the diameter is the greatest.

We can start a depth-first search (DFS) from any node to find the farthest node $a$, which is one endpoint of the tree's diameter.

Then, starting from node $a$, we perform another depth-first search to find the farthest node $b$, which is the other endpoint of the tree's diameter. During this process, we calculate the distance from each node to node $a$, denoted as $\textit{dist2}$.

Next, we perform a depth-first search starting from node $b$ to calculate the distance from each node to node $b$, denoted as $\textit{dist3}$.

For each node $i$, if $\textit{dist2}[i] > $\textit{dist3}[i]$, then the distance from node $a$ to node $i$ is greater, so node $a$ is the last marked node; otherwise, node $b$ is the last marked node.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lastMarkedNodes(self, edges: List[List[int]]) -> List[int]:
        def dfs(i: int, fa: int, dist: List[int]):
            for j in g[i]:
                if j != fa:
                    dist[j] = dist[i] + 1
                    dfs(j, i, dist)

        n = len(edges) + 1
        g = [[] for _ in range(n)]
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)

        dist1 = [-1] * n
        dist1[0] = 0
        dfs(0, -1, dist1)
        a = dist1.index(max(dist1))

        dist2 = [-1] * n
        dist2[a] = 0
        dfs(a, -1, dist2)
        b = dist2.index(max(dist2))

        dist3 = [-1] * n
        dist3[b] = 0
        dfs(b, -1, dist3)

        return [a if x > y else b for x, y in zip(dist2, dist3)]
```

#### Java

```java
class Solution {
    private List<Integer>[] g;

    public int[] lastMarkedNodes(int[][] edges) {
        int n = edges.length + 1;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        int[] dist1 = new int[n];
        dist1[0] = 0;
        dfs(0, -1, dist1);
        int a = maxNode(dist1);

        int[] dist2 = new int[n];
        dist2[a] = 0;
        dfs(a, -1, dist2);
        int b = maxNode(dist2);

        int[] dist3 = new int[n];
        dist3[b] = 0;
        dfs(b, -1, dist3);

        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = dist2[i] > dist3[i] ? a : b;
        }
        return ans;
    }

    private void dfs(int i, int fa, int[] dist) {
        for (int j : g[i]) {
            if (j != fa) {
                dist[j] = dist[i] + 1;
                dfs(j, i, dist);
            }
        }
    }

    private int maxNode(int[] dist) {
        int mx = 0;
        for (int i = 0; i < dist.length; ++i) {
            if (dist[mx] < dist[i]) {
                mx = i;
            }
        }
        return mx;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> lastMarkedNodes(vector<vector<int>>& edges) {
        int n = edges.size() + 1;
        g.resize(n);
        for (const auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        vector<int> dist1(n);
        dfs(0, -1, dist1);
        int a = max_element(dist1.begin(), dist1.end()) - dist1.begin();

        vector<int> dist2(n);
        dfs(a, -1, dist2);
        int b = max_element(dist2.begin(), dist2.end()) - dist2.begin();

        vector<int> dist3(n);
        dfs(b, -1, dist3);

        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            ans.push_back(dist2[i] > dist3[i] ? a : b);
        }
        return ans;
    }

private:
    vector<vector<int>> g;

    void dfs(int i, int fa, vector<int>& dist) {
        for (int j : g[i]) {
            if (j != fa) {
                dist[j] = dist[i] + 1;
                dfs(j, i, dist);
            }
        }
    }
};
```

#### Go

```go
func lastMarkedNodes(edges [][]int) (ans []int) {
	n := len(edges) + 1
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	var dfs func(int, int, []int)
	dfs = func(i, fa int, dist []int) {
		for _, j := range g[i] {
			if j != fa {
				dist[j] = dist[i] + 1
				dfs(j, i, dist)
			}
		}
	}
	maxNode := func(dist []int) int {
		mx := 0
		for i, d := range dist {
			if dist[mx] < d {
				mx = i
			}
		}
		return mx
	}

	dist1 := make([]int, n)
	dfs(0, -1, dist1)
	a := maxNode(dist1)

	dist2 := make([]int, n)
	dfs(a, -1, dist2)
	b := maxNode(dist2)

	dist3 := make([]int, n)
	dfs(b, -1, dist3)

	for i, x := range dist2 {
		if x > dist3[i] {
			ans = append(ans, a)
		} else {
			ans = append(ans, b)
		}
	}
	return
}
```

#### TypeScript

```ts
function lastMarkedNodes(edges: number[][]): number[] {
    const n = edges.length + 1;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }
    const dfs = (i: number, fa: number, dist: number[]) => {
        for (const j of g[i]) {
            if (j !== fa) {
                dist[j] = dist[i] + 1;
                dfs(j, i, dist);
            }
        }
    };

    const dist1: number[] = Array(n).fill(0);
    dfs(0, -1, dist1);
    const a = dist1.indexOf(Math.max(...dist1));

    const dist2: number[] = Array(n).fill(0);
    dfs(a, -1, dist2);
    const b = dist2.indexOf(Math.max(...dist2));

    const dist3: number[] = Array(n).fill(0);
    dfs(b, -1, dist3);

    const ans: number[] = [];
    for (let i = 0; i < n; ++i) {
        ans.push(dist2[i] > dist3[i] ? a : b);
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[][]} edges
 * @return {number[]}
 */
var lastMarkedNodes = function (edges) {
    const n = edges.length + 1;
    const g = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }
    const dfs = (i, fa, dist) => {
        for (const j of g[i]) {
            if (j !== fa) {
                dist[j] = dist[i] + 1;
                dfs(j, i, dist);
            }
        }
    };

    const dist1 = Array(n).fill(0);
    dfs(0, -1, dist1);
    const a = dist1.indexOf(Math.max(...dist1));

    const dist2 = Array(n).fill(0);
    dfs(a, -1, dist2);
    const b = dist2.indexOf(Math.max(...dist2));

    const dist3 = Array(n).fill(0);
    dfs(b, -1, dist3);

    const ans = [];
    for (let i = 0; i < n; ++i) {
        ans.push(dist2[i] > dist3[i] ? a : b);
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
