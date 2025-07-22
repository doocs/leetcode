---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2065.Maximum%20Path%20Quality%20of%20a%20Graph/README.md
rating: 2178
source: 第 266 场周赛 Q4
tags:
    - 图
    - 数组
    - 回溯
---

<!-- problem:start -->

# [2065. 最大化一张图中的路径价值](https://leetcode.cn/problems/maximum-path-quality-of-a-graph)

[English Version](/solution/2000-2099/2065.Maximum%20Path%20Quality%20of%20a%20Graph/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一张 <strong>无向</strong>&nbsp;图，图中有 <code>n</code>&nbsp;个节点，节点编号从 <code>0</code>&nbsp;到 <code>n - 1</code>&nbsp;（<strong>都包括</strong>）。同时给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>values</code>&nbsp;，其中&nbsp;<code>values[i]</code>&nbsp;是第 <code>i</code>&nbsp;个节点的 <strong>价值</strong>&nbsp;。同时给你一个下标从 <strong>0</strong>&nbsp;开始的二维整数数组&nbsp;<code>edges</code>&nbsp;，其中&nbsp;<code>edges[j] = [u<sub>j</sub>, v<sub>j</sub>, time<sub>j</sub>]</code>&nbsp;表示节点&nbsp;<code>u<sub>j</sub></code> 和&nbsp;<code>v<sub>j</sub></code>&nbsp;之间有一条需要&nbsp;<code>time<sub>j</sub></code>&nbsp;秒才能通过的无向边。最后，给你一个整数&nbsp;<code>maxTime</code>&nbsp;。</p>

<p><strong>合法路径</strong>&nbsp;指的是图中任意一条从节点&nbsp;<code>0</code>&nbsp;开始，最终回到节点 <code>0</code>&nbsp;，且花费的总时间 <strong>不超过</strong>&nbsp;<code>maxTime</code> 秒的一条路径。你可以访问一个节点任意次。一条合法路径的 <b>价值</b>&nbsp;定义为路径中 <strong>不同节点</strong>&nbsp;的价值 <strong>之和</strong>&nbsp;（每个节点的价值 <strong>至多</strong>&nbsp;算入价值总和中一次）。</p>

<p>请你返回一条合法路径的 <strong>最大</strong>&nbsp;价值。</p>

<p><strong>注意：</strong>每个节点 <strong>至多</strong>&nbsp;有 <strong>四条</strong>&nbsp;边与之相连。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2065.Maximum%20Path%20Quality%20of%20a%20Graph/images/ex1drawio.png" style="width: 269px; height: 170px;" /></p>

<pre>
<b>输入：</b>values = [0,32,10,43], edges = [[0,1,10],[1,2,15],[0,3,10]], maxTime = 49
<b>输出：</b>75
<strong>解释：</strong>
一条可能的路径为：0 -&gt; 1 -&gt; 0 -&gt; 3 -&gt; 0 。总花费时间为 10 + 10 + 10 + 10 = 40 &lt;= 49 。
访问过的节点为 0 ，1 和 3 ，最大路径价值为 0 + 32 + 43 = 75 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2065.Maximum%20Path%20Quality%20of%20a%20Graph/images/ex2drawio.png" style="width: 269px; height: 170px;" /></p>

<pre>
<b>输入：</b>values = [5,10,15,20], edges = [[0,1,10],[1,2,10],[0,3,10]], maxTime = 30
<b>输出：</b>25
<strong>解释：</strong>
一条可能的路径为：0 -&gt; 3 -&gt; 0 。总花费时间为 10 + 10 = 20 &lt;= 30 。
访问过的节点为 0 和 3 ，最大路径价值为 5 + 20 = 25 。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2065.Maximum%20Path%20Quality%20of%20a%20Graph/images/ex31drawio.png" style="width: 236px; height: 170px;" /></p>

<pre>
<b>输入：</b>values = [1,2,3,4], edges = [[0,1,10],[1,2,11],[2,3,12],[1,3,13]], maxTime = 50
<b>输出：</b>7
<strong>解释：</strong>
一条可能的路径为：0 -&gt; 1 -&gt; 3 -&gt; 1 -&gt; 0 。总花费时间为 10 + 13 + 13 + 10 = 46 &lt;= 50 。
访问过的节点为 0 ，1 和 3 ，最大路径价值为 1 + 2 + 4 = 7 。</pre>

<p><strong>示例 4：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2065.Maximum%20Path%20Quality%20of%20a%20Graph/images/ex4drawio.png" style="width: 270px; height: 71px;" /></strong></p>

<pre>
<b>输入：</b>values = [0,1,2], edges = [[1,2,10]], maxTime = 10
<b>输出：</b>0
<b>解释：</b>
唯一一条路径为 0 。总花费时间为 0 。
唯一访问过的节点为 0 ，最大路径价值为 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == values.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= values[i] &lt;= 10<sup>8</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 2000</code></li>
	<li><code>edges[j].length == 3 </code></li>
	<li><code>0 &lt;= u<sub>j </sub>&lt; v<sub>j</sub> &lt;= n - 1</code></li>
	<li><code>10 &lt;= time<sub>j</sub>, maxTime &lt;= 100</code></li>
	<li><code>[u<sub>j</sub>, v<sub>j</sub>]</code>&nbsp;所有节点对 <strong>互不相同</strong>&nbsp;。</li>
	<li>每个节点 <strong>至多有四条&nbsp;</strong>边。</li>
	<li>图可能不连通。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们观察题目的数据范围，可以发现从 $0$ 开始的每条合法路径的边数不超过 $\frac{\textit{maxTime}}{\min(time_j)} = \frac{100}{10} = 10$ 条，并且每个节点至多有四条边，所以我们可以直接使用朴素的 DFS 暴力搜索所有合法路径。

我们先将图的边存储在邻接表表 $g$ 中，然后我们设计一个函数 $\textit{dfs}(u, \textit{cost}, \textit{value})$，其中 $u$ 表示当前节点编号，而 $\textit{cost}$ 和 $\textit{value}$ 分别表示当前路径的花费时间和价值。另外，使用一个长度为 $n$ 的数组 $\textit{vis}$ 记录每个节点是否被访问过。初始时，我们将节点 $0$ 标记为已访问。

函数 $\textit{dfs}(u, \textit{cost}, \textit{value})$ 的逻辑如下：

-   如果当前节点编号 $u$ 等于 $0$，表示我们已经回到了起点，那么我们更新答案为 $\max(\textit{ans}, \textit{value})$；
-   遍历当前节点 $u$ 的所有邻居节点 $v$，如果当前路径的花费时间加上边 $(u, v)$ 的时间 $t$ 不超过 $\textit{maxTime}$，那么我们可以选择继续访问节点 $v$；
    -   如果节点 $v$ 已经被访问过，那么我们直接递归调用 $\textit{dfs}(v, \textit{cost} + t, \textit{value})$；
    -   如果节点 $v$ 没有被访问过，我们标记节点 $v$ 为已访问，然后递归调用 $\textit{dfs}(v, \textit{cost} + t, \textit{value} + \textit{values}[v])$，最后恢复节点 $v$ 的访问状态。

在主函数中，我们调用 $\textit{dfs}(0, 0, \textit{values}[0])$，并返回答案 $\textit{ans}$ 即可。

时间复杂度 $O(n + m + 4^{\frac{\textit{maxTime}}{\min(time_j)}})$，空间复杂度 $O(n + m + \frac{\textit{maxTime}}{\min(time_j)})$。其中 $n$ 和 $m$ 分别表示节点数和边数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximalPathQuality(
        self, values: List[int], edges: List[List[int]], maxTime: int
    ) -> int:
        def dfs(u: int, cost: int, value: int):
            if u == 0:
                nonlocal ans
                ans = max(ans, value)
            for v, t in g[u]:
                if cost + t <= maxTime:
                    if vis[v]:
                        dfs(v, cost + t, value)
                    else:
                        vis[v] = True
                        dfs(v, cost + t, value + values[v])
                        vis[v] = False

        n = len(values)
        g = [[] for _ in range(n)]
        for u, v, t in edges:
            g[u].append((v, t))
            g[v].append((u, t))
        vis = [False] * n
        vis[0] = True
        ans = 0
        dfs(0, 0, values[0])
        return ans
```

#### Java

```java
class Solution {
    private List<int[]>[] g;
    private boolean[] vis;
    private int[] values;
    private int maxTime;
    private int ans;

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1], t = e[2];
            g[u].add(new int[] {v, t});
            g[v].add(new int[] {u, t});
        }
        vis = new boolean[n];
        vis[0] = true;
        this.values = values;
        this.maxTime = maxTime;
        dfs(0, 0, values[0]);
        return ans;
    }

    private void dfs(int u, int cost, int value) {
        if (u == 0) {
            ans = Math.max(ans, value);
        }
        for (var e : g[u]) {
            int v = e[0], t = e[1];
            if (cost + t <= maxTime) {
                if (vis[v]) {
                    dfs(v, cost + t, value);
                } else {
                    vis[v] = true;
                    dfs(v, cost + t, value + values[v]);
                    vis[v] = false;
                }
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximalPathQuality(vector<int>& values, vector<vector<int>>& edges, int maxTime) {
        int n = values.size();
        vector<pair<int, int>> g[n];
        for (auto& e : edges) {
            int u = e[0], v = e[1], t = e[2];
            g[u].emplace_back(v, t);
            g[v].emplace_back(u, t);
        }
        bool vis[n];
        memset(vis, false, sizeof(vis));
        vis[0] = true;
        int ans = 0;
        auto dfs = [&](this auto&& dfs, int u, int cost, int value) -> void {
            if (u == 0) {
                ans = max(ans, value);
            }
            for (auto& [v, t] : g[u]) {
                if (cost + t <= maxTime) {
                    if (vis[v]) {
                        dfs(v, cost + t, value);
                    } else {
                        vis[v] = true;
                        dfs(v, cost + t, value + values[v]);
                        vis[v] = false;
                    }
                }
            }
        };
        dfs(0, 0, values[0]);
        return ans;
    }
};
```

#### Go

```go
func maximalPathQuality(values []int, edges [][]int, maxTime int) (ans int) {
	n := len(values)
	g := make([][][2]int, n)
	for _, e := range edges {
		u, v, t := e[0], e[1], e[2]
		g[u] = append(g[u], [2]int{v, t})
		g[v] = append(g[v], [2]int{u, t})
	}
	vis := make([]bool, n)
	vis[0] = true
	var dfs func(u, cost, value int)
	dfs = func(u, cost, value int) {
		if u == 0 {
			ans = max(ans, value)
		}
		for _, e := range g[u] {
			v, t := e[0], e[1]
			if cost+t <= maxTime {
				if vis[v] {
					dfs(v, cost+t, value)
				} else {
					vis[v] = true
					dfs(v, cost+t, value+values[v])
					vis[v] = false
				}
			}
		}
	}
	dfs(0, 0, values[0])
	return
}
```

#### TypeScript

```ts
function maximalPathQuality(values: number[], edges: number[][], maxTime: number): number {
    const n = values.length;
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [u, v, t] of edges) {
        g[u].push([v, t]);
        g[v].push([u, t]);
    }
    const vis: boolean[] = Array(n).fill(false);
    vis[0] = true;
    let ans = 0;
    const dfs = (u: number, cost: number, value: number) => {
        if (u === 0) {
            ans = Math.max(ans, value);
        }
        for (const [v, t] of g[u]) {
            if (cost + t <= maxTime) {
                if (vis[v]) {
                    dfs(v, cost + t, value);
                } else {
                    vis[v] = true;
                    dfs(v, cost + t, value + values[v]);
                    vis[v] = false;
                }
            }
        }
    };
    dfs(0, 0, values[0]);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
