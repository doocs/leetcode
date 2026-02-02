---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3812.Minimum%20Edge%20Toggles%20on%20a%20Tree/README_EN.md
rating: 2178
source: Biweekly Contest 174 Q4
---

<!-- problem:start -->

# [3812. Minimum Edge Toggles on a Tree](https://leetcode.com/problems/minimum-edge-toggles-on-a-tree)

[中文文档](/solution/3800-3899/3812.Minimum%20Edge%20Toggles%20on%20a%20Tree/README.md)

## Description

<!-- description:start -->

<p>You are given an <strong>undirected tree</strong> with <code>n</code> nodes, numbered from 0 to <code>n - 1</code>. It is represented by a 2D integer array <code>edges</code>​​​​​​​ of length <code>n - 1</code>, where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the tree.</p>

<p>You are also given two <strong>binary</strong> strings <code>start</code> and <code>target</code> of length <code>n</code>. For each node <code>x</code>, <code>start[x]</code> is its initial color and <code>target[x]</code> is its desired color.</p>

<p>In one operation, you may pick an edge with index <code>i</code> and <strong>toggle </strong>both of its endpoints. That is, if the edge is <code>[u, v]</code>, then the colors of nodes <code>u</code> and <code>v</code> <strong>each</strong> flip from <code>&#39;0&#39;</code> to <code>&#39;1&#39;</code> or from <code>&#39;1&#39;</code> to <code>&#39;0&#39;</code>.</p>

<p>Return an array of edge indices whose operations transform <code>start</code> into <code>target</code>. Among all valid sequences with <strong>minimum possible length</strong>, return the edge indices in <strong>increasing</strong>​​​​​​​ order.</p>

<p>If it is impossible to transform <code>start</code> into <code>target</code>, return an array containing a single element equal to -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3800-3899/3812.Minimum%20Edge%20Toggles%20on%20a%20Tree/images/example1.png" style="width: 271px; height: 51px;" />​​​​​​​</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1],[1,2]], start = &quot;010&quot;, target = &quot;100&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[0]</span></p>

<p><strong>Explanation:</strong></p>

<p>Toggle edge with index 0, which flips nodes 0 and 1.<br />
​​​​​​​The string changes from <code>&quot;010&quot;</code> to <code>&quot;100&quot;</code>, matching the target.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<p><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3800-3899/3812.Minimum%20Edge%20Toggles%20on%20a%20Tree/images/example2.png" style="width: 411px; height: 208px;" /></strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 7, edges = [[0,1],[1,2],[2,3],[3,4],[3,5],[1,6]], start = &quot;0011000&quot;, target = &quot;0010001&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,5]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Toggle edge with index 1, which flips nodes 1 and 2.</li>
	<li>Toggle edge with index 2, which flips nodes 2 and 3.</li>
	<li>Toggle edge with index 5, which flips nodes 1 and 6.</li>
</ul>

<p>After these operations, the resulting string becomes <code>&quot;0010001&quot;</code>, which matches the target.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<p><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3800-3899/3812.Minimum%20Edge%20Toggles%20on%20a%20Tree/images/example3.png" style="width: 161px; height: 51px;" />​​​​​​​</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, edges = [[0,1]], start = &quot;00&quot;, target = &quot;01&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1]</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no sequence of edge toggles that transforms <code>&quot;00&quot;</code> into <code>&quot;01&quot;</code>. Therefore, we return <code>[-1]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == start.length == target.length &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>start[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
	<li><code>target[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
	<li>The input is generated such that <code>edges</code> represents a valid tree.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

We define an adjacency list $g$ to represent the tree, where $g[a]$ stores all adjacent nodes of node $a$ and the indices of the corresponding edges.

We design a function $\text{dfs}(a, \text{fa})$, which indicates whether the edge between node $a$ and $\text{fa}$ needs to be toggled in the subtree rooted at node $a$ with parent $\text{fa}$. The logic of the function $\text{dfs}(a, \text{fa})$ is as follows:

1. Initialize a boolean variable $\text{rev}$, indicating whether node $a$ needs to be toggled. The initial value is $\text{start}[a] \ne \text{target}[a]$.
2. Iterate through all adjacent nodes $b$ of node $a$ and the corresponding edge index $i$:
    - If $b \ne \text{fa}$, recursively call $\text{dfs}(b, a)$.
    - If the recursive call returns true, it means the edge $[a, b]$ in the subtree needs to be toggled. We add the edge index $i$ to the answer list and toggle $\text{rev}$.
3. Return $\text{rev}$.

Finally, we call $\text{dfs}(0, -1)$. If the return value is true, it means it is impossible to convert $\text{start}$ to $\text{target}$, so we return $[-1]$. Otherwise, we sort the answer list and return it.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes in the tree.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumFlips(
        self, n: int, edges: List[List[int]], start: str, target: str
    ) -> List[int]:
        g = [[] for _ in range(n)]
        for i, (a, b) in enumerate(edges):
            g[a].append((b, i))
            g[b].append((a, i))

        ans = []

        def dfs(a: int, fa: int) -> bool:
            rev = start[a] != target[a]
            for b, i in g[a]:
                if b != fa and dfs(b, a):
                    ans.append(i)
                    rev = not rev
            return rev

        if dfs(0, -1):
            return [-1]
        ans.sort()
        return ans
```

#### Java

```java
class Solution {
    private final List<Integer> ans = new ArrayList<>();
    private List<int[]>[] g;
    private char[] start;
    private char[] target;

    public List<Integer> minimumFlips(int n, int[][] edges, String start, String target) {
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < n - 1; ++i) {
            int a = edges[i][0], b = edges[i][1];
            g[a].add(new int[] {b, i});
            g[b].add(new int[] {a, i});
        }
        this.start = start.toCharArray();
        this.target = target.toCharArray();
        if (dfs(0, -1)) {
            return List.of(-1);
        }
        Collections.sort(ans);
        return ans;
    }

    private boolean dfs(int a, int fa) {
        boolean rev = start[a] != target[a];
        for (var e : g[a]) {
            int b = e[0], i = e[1];
            if (b != fa && dfs(b, a)) {
                ans.add(i);
                rev = !rev;
            }
        }
        return rev;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> minimumFlips(int n, vector<vector<int>>& edges, string start, string target) {
        vector<pair<int, int>> g[n];
        for (int i = 0; i < n - 1; ++i) {
            int a = edges[i][0], b = edges[i][1];
            g[a].push_back({b, i});
            g[b].push_back({a, i});
        }
        vector<int> ans;
        auto dfs = [&](this auto&& dfs, int a, int fa) -> bool {
            bool rev = start[a] != target[a];
            for (auto [b, i] : g[a]) {
                if (b != fa && dfs(b, a)) {
                    ans.push_back(i);
                    rev = !rev;
                }
            }
            return rev;
        };
        if (dfs(0, -1)) {
            return {-1};
        }
        ranges::sort(ans);
        return ans;
    }
};
```

#### Go

```go
func minimumFlips(n int, edges [][]int, start string, target string) []int {
	g := make([][]struct{ to, idx int }, n)
	for i := 0; i < n-1; i++ {
		a, b := edges[i][0], edges[i][1]
		g[a] = append(g[a], struct{ to, idx int }{b, i})
		g[b] = append(g[b], struct{ to, idx int }{a, i})
	}
	ans := []int{}
	var dfs func(a, fa int) bool
	dfs = func(a, fa int) bool {
		rev := start[a] != target[a]
		for _, p := range g[a] {
			b, i := p.to, p.idx
			if b != fa && dfs(b, a) {
				ans = append(ans, i)
				rev = !rev
			}
		}
		return rev
	}
	if dfs(0, -1) {
		return []int{-1}
	}
	sort.Ints(ans)
	return ans
}
```

#### TypeScript

```ts
function minimumFlips(n: number, edges: number[][], start: string, target: string): number[] {
    const g: number[][][] = Array.from({ length: n }, () => []);
    for (let i = 0; i < n - 1; i++) {
        const [a, b] = edges[i];
        g[a].push([b, i]);
        g[b].push([a, i]);
    }
    const ans: number[] = [];
    const dfs = (a: number, fa: number): boolean => {
        let rev = start[a] !== target[a];
        for (const [b, i] of g[a]) {
            if (b !== fa && dfs(b, a)) {
                ans.push(i);
                rev = !rev;
            }
        }
        return rev;
    };
    if (dfs(0, -1)) {
        return [-1];
    }
    ans.sort((x, y) => x - y);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
