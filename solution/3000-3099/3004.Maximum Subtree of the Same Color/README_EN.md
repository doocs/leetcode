---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3004.Maximum%20Subtree%20of%20the%20Same%20Color/README_EN.md
tags:
    - Tree
    - Depth-First Search
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3004. Maximum Subtree of the Same Color 🔒](https://leetcode.com/problems/maximum-subtree-of-the-same-color)

[中文文档](/solution/3000-3099/3004.Maximum%20Subtree%20of%20the%20Same%20Color/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>edges</code> representing a tree with <code>n</code> nodes, numbered from <code>0</code> to <code>n - 1</code>, rooted at node <code>0</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> means there is an edge between the nodes <code>v<sub>i</sub></code> and <code>u<sub>i</sub></code>.</p>

<p>You are also given a <strong>0-indexed</strong> integer array <code>colors</code> of size <code>n</code>, where <code>colors[i]</code> is the color assigned to node <code>i</code>.</p>

<p>We want to find a node <code>v</code> such that every node in the <span data-keyword="subtree-of-node">subtree</span> of <code>v</code> has the <strong>same</strong> color.</p>

<p>Return <em>the size of such subtree with the <strong>maximum</strong> number of nodes possible.</em></p>

<p>&nbsp;</p>
<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3004.Maximum%20Subtree%20of%20the%20Same%20Color/images/20231216-134026.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 221px; height: 132px;" /></strong></p>

<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> edges = [[0,1],[0,2],[0,3]], colors = [1,1,2,3]
<strong>Output:</strong> 1
<strong>Explanation:</strong> Each color is represented as: 1 -&gt; Red, 2 -&gt; Green, 3 -&gt; Blue. We can see that the subtree rooted at node 0 has children with different colors. Any other subtree is of the same color and has a size of 1. Hence, we return 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> edges = [[0,1],[0,2],[0,3]], colors = [1,1,1,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The whole tree has the same color, and the subtree rooted at node 0 has the most number of nodes which is 4. Hence, we return 4.
</pre>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3004.Maximum%20Subtree%20of%20the%20Same%20Color/images/20231216-134017.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 221px; height: 221px;" /></strong></p>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> edges = [[0,1],[0,2],[2,3],[2,4]], colors = [1,2,3,3,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Each color is represented as: 1 -&gt; Red, 2 -&gt; Green, 3 -&gt; Blue. We can see that the subtree rooted at node 0 has children with different colors. Any other subtree is of the same color, but the subtree rooted at node 2 has a size of 3 which is the maximum. Hence, we return 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == edges.length + 1</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>edges[i] == [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>colors.length == n</code></li>
	<li><code>1 &lt;= colors[i] &lt;= 10<sup>5</sup></code></li>
	<li>The input is generated such that the graph represented by <code>edges</code> is a tree.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

First, according to the edge information given in the problem, we construct an adjacency list $g$, where $g[a]$ represents all adjacent nodes of node $a$. Then we create an array $size$ of length $n$, where $size[a]$ represents the number of nodes in the subtree with node $a$ as the root.

Next, we design a function $dfs(a, fa)$, which will return whether the subtree with node $a$ as the root meets the requirements of the problem. The execution process of the function $dfs(a, fa)$ is as follows:

-   First, we use a variable $ok$ to record whether the subtree with node $a$ as the root meets the requirements of the problem, and initially $ok$ is $true$.
-   Then, we traverse all adjacent nodes $b$ of node $a$. If $b$ is not the parent node $fa$ of $a$, then we recursively call $dfs(b, a)$, save the return value into the variable $t$, and update $ok$ to the value of $ok$ and $colors[a] = colors[b] \land t$, where $\land$ represents logical AND operation. Then, we update $size[a] = size[a] + size[b]$.
-   After that, we judge the value of $ok$. If $ok$ is $true$, then we update the answer $ans = \max(ans, size[a])$.
-   Finally, we return the value of $ok$.

We call $dfs(0, -1)$, where $0$ represents the root node number, and $-1$ represents that the root node has no parent node. The final answer is $ans$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the number of nodes.

<!-- tabs:start -->

```python
class Solution:
    def maximumSubtreeSize(self, edges: List[List[int]], colors: List[int]) -> int:
        def dfs(a: int, fa: int) -> bool:
            ok = True
            for b in g[a]:
                if b != fa:
                    t = dfs(b, a)
                    ok = ok and colors[a] == colors[b] and t
                    size[a] += size[b]
            if ok:
                nonlocal ans
                ans = max(ans, size[a])
            return ok

        n = len(edges) + 1
        g = [[] for _ in range(n)]
        size = [1] * n
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        ans = 0
        dfs(0, -1)
        return ans
```

```java
class Solution {
    private List<Integer>[] g;
    private int[] colors;
    private int[] size;
    private int ans;

    public int maximumSubtreeSize(int[][] edges, int[] colors) {
        int n = edges.length + 1;
        g = new List[n];
        size = new int[n];
        this.colors = colors;
        Arrays.fill(size, 1);
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        dfs(0, -1);
        return ans;
    }

    private boolean dfs(int a, int fa) {
        boolean ok = true;
        for (int b : g[a]) {
            if (b != fa) {
                boolean t = dfs(b, a);
                ok = ok && colors[a] == colors[b] && t;
                size[a] += size[b];
            }
        }
        if (ok) {
            ans = Math.max(ans, size[a]);
        }
        return ok;
    }
}
```

```cpp
class Solution {
public:
    int maximumSubtreeSize(vector<vector<int>>& edges, vector<int>& colors) {
        int n = edges.size() + 1;
        vector<int> g[n];
        vector<int> size(n, 1);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        int ans = 0;
        function<bool(int, int)> dfs = [&](int a, int fa) {
            bool ok = true;
            for (int b : g[a]) {
                if (b != fa) {
                    bool t = dfs(b, a);
                    ok = ok && colors[a] == colors[b] && t;
                    size[a] += size[b];
                }
            }
            if (ok) {
                ans = max(ans, size[a]);
            }
            return ok;
        };
        dfs(0, -1);
        return ans;
    }
};
```

```go
func maximumSubtreeSize(edges [][]int, colors []int) (ans int) {
	n := len(edges) + 1
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	size := make([]int, n)
	var dfs func(int, int) bool
	dfs = func(a, fa int) bool {
		size[a] = 1
		ok := true
		for _, b := range g[a] {
			if b != fa {
				t := dfs(b, a)
				ok = ok && t && colors[a] == colors[b]
				size[a] += size[b]
			}
		}
		if ok {
			ans = max(ans, size[a])
		}
		return ok
	}
	dfs(0, -1)
	return
}
```

```ts
function maximumSubtreeSize(edges: number[][], colors: number[]): number {
    const n = edges.length + 1;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const size: number[] = Array(n).fill(1);
    let ans = 0;
    const dfs = (a: number, fa: number): boolean => {
        let ok = true;
        for (const b of g[a]) {
            if (b !== fa) {
                const t = dfs(b, a);
                ok = ok && t && colors[a] === colors[b];
                size[a] += size[b];
            }
        }
        if (ok) {
            ans = Math.max(ans, size[a]);
        }
        return ok;
    };
    dfs(0, -1);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
