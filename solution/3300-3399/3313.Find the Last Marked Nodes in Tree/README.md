---
comments: true
difficulty: 困难
tags:
    - 树
    - 深度优先搜索
---

<!-- problem:start -->

# [3313. 查找树中最后标记的节点 🔒](https://leetcode.cn/problems/find-the-last-marked-nodes-in-tree)

[English Version](/solution/3300-3399/3313.Find%20the%20Last%20Marked%20Nodes%20in%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有一棵有&nbsp;<code>n</code>&nbsp;个节点，编号从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code> 的&nbsp;<strong>无向</strong> 树。给定一个长度为&nbsp;<code>n - 1</code>&nbsp;的整数数组&nbsp;<code>edges</code>，其中&nbsp;<code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code>&nbsp;表示树中的&nbsp;<code>u<sub>i</sub></code> 和&nbsp;<code>v<sub>i</sub></code>&nbsp;之间有一条边。</p>

<p>一开始，<strong>所有</strong>&nbsp;节点都 <b>未标记</b>。之后的每一秒，你需要标记所有 <strong>至少</strong>&nbsp;有一个已标记节点相邻的未标记节点。</p>

<p>返回一个数组 <code>nodes</code>，表示在时刻 <code>t = 0</code> 标记了节点 <code>i</code>，那么树中最后标记的节点是 <code>nodes[i]</code>。如果对于任意节点&nbsp;<code>i</code>&nbsp;有多个&nbsp;<code>nodes[i]</code>，你可以选择 <strong>任意</strong>&nbsp;一个作为答案。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>edges = [[0,1],[0,2]]</span></p>

<p><b>输出：</b>[2,2,1]</p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3313.Find%20the%20Last%20Marked%20Nodes%20in%20Tree/images/screenshot-2024-06-02-122236.png" style="width: 450px; height: 217px;" /></p>

<ul>
	<li>对于&nbsp;<code>i = 0</code>，节点以如下序列标记：<code>[0] -&gt; [0,1,2]</code>。1 和 2 都可以是答案。</li>
	<li>对于 <code>i = 1</code>，节点以如下序列标记：<code>[1] -&gt; [0,1] -&gt; [0,1,2]</code>。节点 2 最后被标记。</li>
	<li>对于 <code>i = 2</code>，节点以如下序列标记：<code>[2] -&gt; [0,2] -&gt; [0,1,2]</code>。节点 1 最后被标记。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>edges = [[0,1]]</span></p>

<p><b>输出：</b>[1,0]</p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3313.Find%20the%20Last%20Marked%20Nodes%20in%20Tree/images/screenshot-2024-06-02-122249.png" style="width: 350px; height: 180px;" /></p>

<ul>
	<li>对于&nbsp;<code>i = 0</code>，节点以如下序列被标记：<code>[0] -&gt; [0,1]</code>。</li>
	<li>对于&nbsp;<code>i = 1</code>，节点以如下序列被标记：<code>[1] -&gt; [0,1]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>edges = [[0,1],[0,2],[2,3],[2,4]]</span></p>

<p><b>输出：</b>[3,3,1,1,1]</p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3313.Find%20the%20Last%20Marked%20Nodes%20in%20Tree/images/screenshot-2024-06-03-210550.png" style="height: 240px; width: 450px;" /></p>

<ul>
	<li>对于&nbsp;<code>i = 0</code>，节点以如下序列被标记：<code>[0] -&gt; [0,1,2] -&gt; [0,1,2,3,4]</code>。</li>
	<li>对于 <code>i = 1</code>，节点以如下序列被标记：<code>[1] -&gt; [0,1] -&gt; [0,1,2] -&gt; [0,1,2,3,4]</code>。</li>
	<li>对于 <code>i = 2</code>，节点以如下序列被标记：<code>[2] -&gt; [0,2,3,4] -&gt; [0,1,2,3,4]</code>。</li>
	<li>对于 <code>i = 3</code>，节点以如下序列被标记：<code>[3] -&gt; [2,3] -&gt; [0,2,3,4] -&gt; [0,1,2,3,4]</code>。</li>
	<li>对于 <code>i = 4</code>，节点以如下序列被标记：<code>[4] -&gt; [2,4] -&gt; [0,2,3,4] -&gt; [0,1,2,3,4]</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= edges[i][0], edges[i][1] &lt;= n - 1</code></li>
	<li>输入保证&nbsp;<code>edges</code>&nbsp;形成一棵合法的树。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：求树的直径 + DFS

<!-- thinking:start -->

> **思考**
>
> 从 $i$ 同时向外标记，最晚被标记的点是距离 $i$ 最远的点。对每个起点求最远点，暴力为 $O(n^2)$，与 $n \le 10^5$ 不符。
>
> 树上任意点的最远点必是直径的某个端点。因此只需确定直径两端 $a,b$。
>
> 三次 DFS 分别找到 $a$、$b$ 以及到它们的距离；对每个 $i$ 比较 $\textit{dist}(i,a)$ 与 $\textit{dist}(i,b)$，较大者对应的端点即为答案。

<!-- thinking:end -->

根据题目描述，最后一个被标记的节点一定是树的直径的一个端点，因为树的直径上的节点到直径上的任意一个节点的距离最大。

我们可以从任意一个节点开始进行深度优先搜索，找到距离最远的节点 $a$，这个节点就是树的直径的一个端点。

然后从节点 $a$ 开始进行深度优先搜索，找到距离最远的节点 $b$，这个节点就是树的直径的另一个端点，在这个过程中，我们计算出了每个节点到节点 $a$ 的距离，记为 $\textit{dist2}$。

接着从节点 $b$ 开始进行深度优先搜索，计算出每个节点到节点 $b$ 的距离，记为 $\textit{dist3}$。

那么，对于每一个节点 $i$，如果 $\textit{dist2}[i] > \textit{dist3}[i]$，那么节点 $a$ 到节点 $i$ 的距离更远，所以节点 $a$ 是最后一个被标记的节点；否则，节点 $b$ 是最后一个被标记的节点。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是节点的数量。

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
