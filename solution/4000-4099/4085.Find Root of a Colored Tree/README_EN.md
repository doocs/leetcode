---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4085. Find Root of a Colored Tree](https://leetcode.com/problems/find-root-of-a-colored-tree)

[中文文档](/solution/4000-4099/4085.Find%20Root%20of%20a%20Colored%20Tree/README.md)

## Description

<!-- description:start -->

<p>You are given an undirected <strong>tree</strong> of <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>, represented by a 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates that there is an undirected edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>.</p>

<p>You are also given a string <code>colors</code> of length <code>n</code>, where <code>colors[i]</code> is one of <code>&#39;R&#39;</code>, <code>&#39;B&#39;</code>, or <code>&#39;W&#39;</code>, and denotes the color of node <code>i</code>.</p>

<p>A node chosen as <strong>root</strong> must satisfy both of the following <strong>conditions</strong> once the tree is oriented away from it:</p>

<ol>
	<li><strong>Binary-tree property:</strong> Every node has at most two children.</li>
	<li><strong>Layered color pattern:</strong> All nodes at the same depth have the same color. Starting at depth 0, these colors follow one of the three repeating sequences:
	<ul>
		<li><code>R &rarr; B &rarr; W &rarr; R &rarr; B &rarr; W &rarr; ...</code></li>
		<li><code>B &rarr; W &rarr; R &rarr; B &rarr; W &rarr; R &rarr; ...</code></li>
		<li><code>W &rarr; R &rarr; B &rarr; W &rarr; R &rarr; B &rarr; ...</code></li>
	</ul>
	</li>
</ol>

<p>Return the <strong>index</strong> of any root that satisfies both conditions, or -1 if no such root exists.</p>

<p>A <strong>tree</strong> is a connected undirected graph with no cycles.</p>

<p>The <strong>depth</strong> of a node is the number of edges on the path from the root to that node. The root has depth 0. A <strong>child</strong> of a node is an adjacent node whose depth is one greater.</p>

<p>The <strong>degree</strong> of a node is the number of edges incident to it.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[3,1],[3,2],[1,4],[2,0]], colors = &quot;BRRWB&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4085.Find%20Root%20of%20a%20Colored%20Tree/images/treee.jpg" style="width: 351px; height: 224px;" /></p>

<p>Rooting at node 3 gives each node at most two children and every edge advances to the next color in the cycle (<code>W &rarr; R &rarr; B</code>), so return 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1],[1,2],[2,3]], colors = &quot;RBRB&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4085.Find%20Root%20of%20a%20Colored%20Tree/images/treee2.jpg" style="width: 282px; height: 41px;" /></p>

<p>No root satisfies the required color step on every edge, so return -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>colors.length == n</code></li>
	<li><code>colors[i]</code> is one of <code>&#39;R&#39;</code>, <code>&#39;B&#39;</code>, <code>&#39;W&#39;</code>.</li>
	<li>The input graph is a tree and every node has degree at most three.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Orient Edges by Color

<!-- thinking:start -->

> **Thinking**
>
> Trying every node as the root, then checking child counts and the color of each depth, walks the whole tree once per trial. With up to $10^5$ nodes, repeating that walk $n$ times takes $O(n^2)$ time.
>
> The repeated work is the parent-child direction of each edge. The three sequences are the same cycle $W \to R \to B \to W$, started at different colors. Number $W,R,B$ as $0,1,2$. Each extra level of depth adds $1$ to the color index, modulo $3$. The two colors decide whether the edge is legal and which endpoint is the parent.
>
> An adjacency list and a count of neighbor colors separate the parent side from the child side. A neighbor of color $(c + 2) \bmod 3$ is the only possible parent, and there can be at most one. A neighbor of the same color is off the cycle. The node with no parent neighbor and degree less than $3$ has only children, at most two of them, and that node is the root.

<!-- thinking:end -->

Number $W$, $R$, and $B$ as $0$, $1$, and $2$ so that the cycle $W \to R \to B \to W$ is adding $1$ modulo $3$, and build an undirected adjacency list from $\textit{edges}$. For a node $u$ with color index $c$, count the neighbors of each color.

Return $-1$ if the degree is greater than $3$, if any neighbor has the same color, or if more than one neighbor has color $(c + 2) \bmod 3$. The root has no parent and at most two children, so its degree is at most $2$. Every other node has one more parent, so its degree is at most $3$. Equal colors cannot occupy adjacent depths. Color $(c + 2) \bmod 3$ is one step earlier in the cycle, so it can only be the parent, and a node has only one parent.

When the degree is less than $3$ and no neighbor has color $(c + 2) \bmod 3$, every neighbor of $u$ is a child and there are at most two of them, so record $u$. Each edge whose colors are one step apart increments the parent count of exactly one endpoint, and a tree has $n - 1$ edges. If every parent count is at most $1$, exactly one node has count $0$. If that node has degree $3$, all three neighbors are children, so it cannot be the root and the answer stays $-1$.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the number of nodes.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findColoredRoot(self, edges: list[list[int]], colors: str) -> int:
        d = {"W": 0, "R": 1, "B": 2}
        n = len(colors)
        g = [[] for _ in range(n)]
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        ans = -1
        for u, vs in enumerate(g):
            if len(vs) > 3:
                return -1
            cnt = [0] * 3
            for v in vs:
                cnt[d[colors[v]]] += 1
            c = d[colors[u]]
            if cnt[c] > 0 or cnt[(c + 2) % 3] > 1:
                return -1
            if len(vs) < 3 and cnt[(c + 2) % 3] == 0:
                ans = u
        return ans
```

#### Java

```java
class Solution {
    public int findColoredRoot(int[][] edges, String colors) {
        int n = colors.length();
        int[] d = new int[128];
        d['W'] = 0;
        d['R'] = 1;
        d['B'] = 2;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        int ans = -1;
        for (int u = 0; u < n; ++u) {
            List<Integer> vs = g[u];
            if (vs.size() > 3) {
                return -1;
            }
            int[] cnt = new int[3];
            for (int v : vs) {
                ++cnt[d[colors.charAt(v)]];
            }
            int c = d[colors.charAt(u)];
            if (cnt[c] > 0 || cnt[(c + 2) % 3] > 1) {
                return -1;
            }
            if (vs.size() < 3 && cnt[(c + 2) % 3] == 0) {
                ans = u;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findColoredRoot(vector<vector<int>>& edges, string colors) {
        int d[128]{};
        d['W'] = 0;
        d['R'] = 1;
        d['B'] = 2;
        int n = colors.size();
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        int ans = -1;
        for (int u = 0; u < n; ++u) {
            auto& vs = g[u];
            if (vs.size() > 3) {
                return -1;
            }
            int cnt[3]{};
            for (int v : vs) {
                ++cnt[d[colors[v]]];
            }
            int c = d[colors[u]];
            if (cnt[c] > 0 || cnt[(c + 2) % 3] > 1) {
                return -1;
            }
            if (vs.size() < 3 && cnt[(c + 2) % 3] == 0) {
                ans = u;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findColoredRoot(edges [][]int, colors string) int {
	d := [128]int{'W': 0, 'R': 1, 'B': 2}
	n := len(colors)
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	ans := -1
	for u, vs := range g {
		if len(vs) > 3 {
			return -1
		}
		cnt := [3]int{}
		for _, v := range vs {
			cnt[d[colors[v]]]++
		}
		c := d[colors[u]]
		if cnt[c] > 0 || cnt[(c+2)%3] > 1 {
			return -1
		}
		if len(vs) < 3 && cnt[(c+2)%3] == 0 {
			ans = u
		}
	}
	return ans
}
```

#### TypeScript

```ts
function findColoredRoot(edges: number[][], colors: string): number {
    const d: Record<string, number> = { W: 0, R: 1, B: 2 };
    const n = colors.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }
    let ans = -1;
    for (let u = 0; u < n; ++u) {
        const vs = g[u];
        if (vs.length > 3) {
            return -1;
        }
        const cnt = [0, 0, 0];
        for (const v of vs) {
            ++cnt[d[colors[v]]];
        }
        const c = d[colors[u]];
        if (cnt[c] > 0 || cnt[(c + 2) % 3] > 1) {
            return -1;
        }
        if (vs.length < 3 && cnt[(c + 2) % 3] === 0) {
            ans = u;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
