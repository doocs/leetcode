# [3004. Maximum Subtree of the Same Color](https://leetcode.cn/problems/maximum-subtree-of-the-same-color)

[English Version](/solution/3000-3099/3004.Maximum%20Subtree%20of%20the%20Same%20Color/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

### 方法一：DFS

我们先根据题目给定的边的信息，构建一个邻接表 $g$，其中 $g[a]$ 表示节点 $a$ 的所有相邻节点。然后我们创建一个长度为 $n$ 的数组 $size$，其中 $size[a]$ 表示以节点 $a$ 为根的子树的节点数。

接下来，我们设计一个函数 $dfs(a, fa)$，它将返回以节点 $a$ 为根的子树是否满足题目要求。函数 $dfs(a, fa)$ 的执行过程如下：

-   首先，我们用一个变量 $ok$ 记录以节点 $a$ 为根的子树是否满足题目要求，初始时 $ok$ 为 $true$。
-   接着，我们遍历节点 $a$ 的所有相邻节点 $b$，如果 $b$ 不是 $a$ 的父节点 $fa$，那么我们递归调用 $dfs(b, a)$，并将返回值保存到变量 $t$ 中，并且更新 $ok$ 为 $ok$ 与 $colors[a] = colors[b] \land t$ 的值，其中 $\land$ 表示逻辑与运算。然后，我们更新 $size[a] = size[a] + size[b]$。
-   然后，我们判断 $ok$ 的值，如果 $ok$ 为 $true$，那么我们更新答案 $ans = \max(ans, size[a])$。
-   最后，我们返回 $ok$ 的值。

我们调用 $dfs(0, -1)$，其中 $0$ 表示根节点的编号，$-1$ 表示根节点没有父节点。最终的答案即为 $ans$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是节点的数量。

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

<!-- end -->
