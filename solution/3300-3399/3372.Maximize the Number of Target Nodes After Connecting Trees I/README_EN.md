---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3372.Maximize%20the%20Number%20of%20Target%20Nodes%20After%20Connecting%20Trees%20I/README_EN.md
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
---

<!-- problem:start -->

# [3372. Maximize the Number of Target Nodes After Connecting Trees I](https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i)

[中文文档](/solution/3300-3399/3372.Maximize%20the%20Number%20of%20Target%20Nodes%20After%20Connecting%20Trees%20I/README.md)

## Description

<!-- description:start -->

<p>There exist two <strong>undirected </strong>trees with <code>n</code> and <code>m</code> nodes, with <strong>distinct</strong> labels in ranges <code>[0, n - 1]</code> and <code>[0, m - 1]</code>, respectively.</p>

<p>You are given two 2D integer arrays <code>edges1</code> and <code>edges2</code> of lengths <code>n - 1</code> and <code>m - 1</code>, respectively, where <code>edges1[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the first tree and <code>edges2[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates that there is an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> in the second tree. You are also given an integer <code>k</code>.</p>

<p>Node <code>u</code> is <strong>target</strong> to node <code>v</code> if the number of edges on the path from <code>u</code> to <code>v</code> is less than or equal to <code>k</code>. <strong>Note</strong> that a node is <em>always</em> <strong>target</strong> to itself.</p>

<p>Return an array of <code>n</code> integers <code>answer</code>, where <code>answer[i]</code> is the <strong>maximum</strong> possible number of nodes <strong>target</strong> to node <code>i</code> of the first tree if you have to connect one node from the first tree to another node in the second tree.</p>

<p><strong>Note</strong> that queries are independent from each other. That is, for every query you will remove the added edge before proceeding to the next query.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges1 = [[0,1],[0,2],[2,3],[2,4]], edges2 = [[0,1],[0,2],[0,3],[2,7],[1,4],[4,5],[4,6]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[9,7,9,8,8]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>i = 0</code>, connect node 0 from the first tree to node 0 from the second tree.</li>
	<li>For <code>i = 1</code>, connect node 1 from the first tree to node 0 from the second tree.</li>
	<li>For <code>i = 2</code>, connect node 2 from the first tree to node 4 from the second tree.</li>
	<li>For <code>i = 3</code>, connect node 3 from the first tree to node 4 from the second tree.</li>
	<li>For <code>i = 4</code>, connect node 4 from the first tree to node 4 from the second tree.</li>
</ul>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3372.Maximize%20the%20Number%20of%20Target%20Nodes%20After%20Connecting%20Trees%20I/images/3982-1.png" style="width: 600px; height: 169px;" /></div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges1 = [[0,1],[0,2],[0,3],[0,4]], edges2 = [[0,1],[1,2],[2,3]], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[6,3,3,3,3]</span></p>

<p><strong>Explanation:</strong></p>

<p>For every <code>i</code>, connect node <code>i</code> of the first tree with any node of the second tree.</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3372.Maximize%20the%20Number%20of%20Target%20Nodes%20After%20Connecting%20Trees%20I/images/3928-2.png" style="height: 281px; width: 500px;" /></div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n, m &lt;= 1000</code></li>
	<li><code>edges1.length == n - 1</code></li>
	<li><code>edges2.length == m - 1</code></li>
	<li><code>edges1[i].length == edges2[i].length == 2</code></li>
	<li><code>edges1[i] = [a<sub>i</sub>, b<sub>i</sub>]</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>edges2[i] = [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; m</code></li>
	<li>The input is generated such that <code>edges1</code> and <code>edges2</code> represent valid trees.</li>
	<li><code>0 &lt;= k &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration + DFS

According to the problem description, to maximize the number of target nodes for node $i$, we must connect node $i$ to one of the nodes $j$ in the second tree. Therefore, the number of target nodes for node $i$ can be divided into two parts:

-   In the first tree, the number of nodes reachable from node $i$ within a depth of $k$.
-   In the second tree, the maximum number of nodes reachable from any node $j$ within a depth of $k - 1$.

Thus, we can first calculate the number of nodes reachable within a depth of $k - 1$ for each node in the second tree. Then, we enumerate each node $i$ in the first tree, calculate the sum of the two parts mentioned above, and take the maximum value.

The time complexity is $O(n^2 + m^2)$, and the space complexity is $O(n + m)$. Here, $n$ and $m$ are the number of nodes in the two trees, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxTargetNodes(
        self, edges1: List[List[int]], edges2: List[List[int]], k: int
    ) -> List[int]:
        def build(edges: List[List[int]]) -> List[List[int]]:
            n = len(edges) + 1
            g = [[] for _ in range(n)]
            for a, b in edges:
                g[a].append(b)
                g[b].append(a)
            return g

        def dfs(g: List[List[int]], a: int, fa: int, d: int) -> int:
            if d < 0:
                return 0
            cnt = 1
            for b in g[a]:
                if b != fa:
                    cnt += dfs(g, b, a, d - 1)
            return cnt

        g2 = build(edges2)
        m = len(edges2) + 1
        t = max(dfs(g2, i, -1, k - 1) for i in range(m))
        g1 = build(edges1)
        n = len(edges1) + 1
        return [dfs(g1, i, -1, k) + t for i in range(n)]
```

#### Java

```java
class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        var g2 = build(edges2);
        int m = edges2.length + 1;
        int t = 0;
        for (int i = 0; i < m; ++i) {
            t = Math.max(t, dfs(g2, i, -1, k - 1));
        }
        var g1 = build(edges1);
        int n = edges1.length + 1;
        int[] ans = new int[n];
        Arrays.fill(ans, t);
        for (int i = 0; i < n; ++i) {
            ans[i] += dfs(g1, i, -1, k);
        }
        return ans;
    }

    private List<Integer>[] build(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        return g;
    }

    private int dfs(List<Integer>[] g, int a, int fa, int d) {
        if (d < 0) {
            return 0;
        }
        int cnt = 1;
        for (int b : g[a]) {
            if (b != fa) {
                cnt += dfs(g, b, a, d - 1);
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
    vector<int> maxTargetNodes(vector<vector<int>>& edges1, vector<vector<int>>& edges2, int k) {
        auto g2 = build(edges2);
        int m = edges2.size() + 1;
        int t = 0;
        for (int i = 0; i < m; ++i) {
            t = max(t, dfs(g2, i, -1, k - 1));
        }

        auto g1 = build(edges1);
        int n = edges1.size() + 1;

        vector<int> ans(n, t);
        for (int i = 0; i < n; ++i) {
            ans[i] += dfs(g1, i, -1, k);
        }

        return ans;
    }

private:
    vector<vector<int>> build(const vector<vector<int>>& edges) {
        int n = edges.size() + 1;
        vector<vector<int>> g(n);
        for (const auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        return g;
    }

    int dfs(const vector<vector<int>>& g, int a, int fa, int d) {
        if (d < 0) {
            return 0;
        }
        int cnt = 1;
        for (int b : g[a]) {
            if (b != fa) {
                cnt += dfs(g, b, a, d - 1);
            }
        }
        return cnt;
    }
};
```

#### Go

```go
func maxTargetNodes(edges1 [][]int, edges2 [][]int, k int) []int {
	g2 := build(edges2)
	m := len(edges2) + 1
	t := 0
	for i := 0; i < m; i++ {
		t = max(t, dfs(g2, i, -1, k-1))
	}

	g1 := build(edges1)
	n := len(edges1) + 1
	ans := make([]int, n)
	for i := 0; i < n; i++ {
		ans[i] = t + dfs(g1, i, -1, k)
	}
	return ans
}

func build(edges [][]int) [][]int {
	n := len(edges) + 1
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	return g
}

func dfs(g [][]int, a, fa, d int) int {
	if d < 0 {
		return 0
	}
	cnt := 1
	for _, b := range g[a] {
		if b != fa {
			cnt += dfs(g, b, a, d-1)
		}
	}
	return cnt
}
```

#### TypeScript

```ts
function maxTargetNodes(edges1: number[][], edges2: number[][], k: number): number[] {
    const g2 = build(edges2);
    const m = edges2.length + 1;
    let t = 0;
    for (let i = 0; i < m; i++) {
        t = Math.max(t, dfs(g2, i, -1, k - 1));
    }

    const g1 = build(edges1);
    const n = edges1.length + 1;
    const ans = Array(n).fill(t);

    for (let i = 0; i < n; i++) {
        ans[i] += dfs(g1, i, -1, k);
    }

    return ans;
}

function build(edges: number[][]): number[][] {
    const n = edges.length + 1;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    return g;
}

function dfs(g: number[][], a: number, fa: number, d: number): number {
    if (d < 0) {
        return 0;
    }
    let cnt = 1;
    for (const b of g[a]) {
        if (b !== fa) {
            cnt += dfs(g, b, a, d - 1);
        }
    }
    return cnt;
}
```

#### C#

```cs
public class Solution {
    public int[] MaxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        var g2 = Build(edges2);
        int m = edges2.Length + 1;
        int t = 0;

        for (int i = 0; i < m; i++) {
            t = Math.Max(t, Dfs(g2, i, -1, k - 1));
        }

        var g1 = Build(edges1);
        int n = edges1.Length + 1;
        var ans = new int[n];
        Array.Fill(ans, t);

        for (int i = 0; i < n; i++) {
            ans[i] += Dfs(g1, i, -1, k);
        }

        return ans;
    }

    private List<int>[] Build(int[][] edges) {
        int n = edges.Length + 1;
        var g = new List<int>[n];
        for (int i = 0; i < n; i++) {
            g[i] = new List<int>();
        }
        foreach (var e in edges) {
            int a = e[0], b = e[1];
            g[a].Add(b);
            g[b].Add(a);
        }
        return g;
    }

    private int Dfs(List<int>[] g, int a, int fa, int d) {
        if (d < 0) {
            return 0;
        }
        int cnt = 1;
        foreach (var b in g[a]) {
            if (b != fa) {
                cnt += Dfs(g, b, a, d - 1);
            }
        }
        return cnt;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
