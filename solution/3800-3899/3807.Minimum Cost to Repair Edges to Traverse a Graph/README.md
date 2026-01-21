---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3807.Minimum%20Cost%20to%20Repair%20Edges%20to%20Traverse%20a%20Graph/README.md
tags:
    - 广度优先搜索
    - 图
    - 二分查找
---

<!-- problem:start -->

# [3807. 修复边以遍历图的最小成本 🔒](https://leetcode.cn/problems/minimum-cost-to-repair-edges-to-traverse-a-graph)

[English Version](/solution/3800-3899/3807.Minimum%20Cost%20to%20Repair%20Edges%20to%20Traverse%20a%20Graph/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个下标从 0 到&nbsp;<code>n - 1</code>&nbsp;的&nbsp;<code>n</code> 个节点的&nbsp;<strong>无向图</strong>。该图由 <code>m</code> 条边组成，用一个二维整数数组 <code>edges</code> 表示，其中&nbsp;<code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code>&nbsp;表示节点&nbsp;<code>u<sub>i</sub></code> 和&nbsp;<code>v<sub>i</sub></code>&nbsp;之间有一条修复成本为&nbsp;<code>w<sub>i</sub></code>&nbsp;的边。</p>

<p>同时给定一个整数&nbsp;<code>k</code>。一开始，<strong>所有</strong>&nbsp;边都是被损坏的。</p>

<p>你可以选择一个非负整数&nbsp;<code>money</code>并修复所有修复成本 <strong>小于或等于</strong>&nbsp;<code>money</code> 的边。其他所有边保持损坏状态，无法使用。</p>

<p>你想要从节点 <code>0</code> 出发，使用最多 <code>k</code> 条边到达节点 <code>n - 1</code>。</p>

<p>返回一个整数，表示实现此目标所需的 <strong>最小</strong>&nbsp;成本，如果不可能则返回 -1。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3800-3899/3807.Minimum%20Cost%20to%20Repair%20Edges%20to%20Traverse%20a%20Graph/images/ex1drawio.png" style="width: 211px; height: 171px;" /></strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 3, edges = [[0,1,10],[1,2,10],[0,2,100]], k = 1</span></p>

<p><span class="example-io"><b>输出：</b>100</span></p>

<p><strong>解释：</strong></p>

<p>唯一使用最多 <code>k = 1</code>&nbsp;条边的合法路径是&nbsp;<code>0 -&gt; 2</code>，这需要花费 100 来修复边。因此，所需的最低成本是 100。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<p><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3800-3899/3807.Minimum%20Cost%20to%20Repair%20Edges%20to%20Traverse%20a%20Graph/images/ex2drawio.png" style="width: 361px; height: 251px;" /></strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 6, edges = [[0,2,5],[2,3,6],[3,4,7],[4,5,5],[0,1,10],[1,5,12],[0,3,9],[1,2,8],[2,4,11]], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>12</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>由于&nbsp;<code>money = 12</code>，所有修复成本不超过 12 的边都变得可用。</li>
	<li>这使得存在路径&nbsp;<code>0 -&gt; 1 -&gt; 5</code>，使用恰好 2 条边到达节点 5。</li>
	<li>如果&nbsp;<code>money &lt; 12</code>，不存在从节点 0 到节点 5 长度最多为 <code>k = 2</code> 的合法路径。</li>
	<li>因此，所需的最少成本是 12。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<p><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3800-3899/3807.Minimum%20Cost%20to%20Repair%20Edges%20to%20Traverse%20a%20Graph/images/ex3drawio.png" style="width: 312px; height: 52px;" /></strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 3, edges = [[0,1,1]], k = 1</span></p>

<p><span class="example-io"><b>输出：</b>-1</span></p>

<p><strong>解释：</strong></p>

<p>从节点 0 无法使用任何金额到达节点 2。因此，答案是 -1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= edges.length == m &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li>图中没有自环或重复边。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找 + BFS

我们注意到，修复边的成本越高，可用的边就越多，越容易满足从节点 $0$ 出发，使用最多 $k$ 条边到达节点 $n - 1$ 的要求。并且，最小的修复成本一定在 $\textit{edges}$ 中，因此，我们先对 $\textit{edges}$ 按照修复成本进行排序，然后使用二分查找来寻找满足要求的最小修复成本。

我们二分枚举修复成本的下标，定义左边界 $l = 0$，右边界 $r = |\textit{edges}| - 1$。对于中间位置 $mid = \lfloor (l + r) / 2 \rfloor$，我们将修复成本小于等于 $\textit{edges}[mid][2]$ 的边加入图中，然后使用 BFS 判断从节点 $0$ 出发，是否可以使用最多 $k$ 条边到达节点 $n - 1$。如果可以，则将右边界更新为 $r = mid$；否则，将左边界更新为 $l = mid + 1$。当二分查找结束后，我们需要再进行一次 BFS 判断 $\textit{edges}[l][2]$ 是否满足要求，如果满足则返回 $\textit{edges}[l][2]$，否则返回 $-1$。

时间复杂度 $O((m + n) \times \log m)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别是节点数和边数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(self, n: int, edges: List[List[int]], k: int) -> int:
        def check(idx: int) -> bool:
            g = [[] for _ in range(n)]
            for u, v, _ in edges[: idx + 1]:
                g[u].append(v)
                g[v].append(u)
            q = [0]
            dist = 0
            vis = [False] * n
            vis[0] = True
            while q:
                nq = []
                for u in q:
                    if u == n - 1:
                        return dist <= k
                    for v in g[u]:
                        if not vis[v]:
                            vis[v] = True
                            nq.append(v)
                q = nq
                dist += 1
            return False

        m = len(edges)
        edges.sort(key=lambda x: x[2])
        l, r = 0, m - 1
        while l < r:
            mid = (l + r) >> 1
            if check(mid):
                r = mid
            else:
                l = mid + 1
        return edges[l][2] if check(l) else -1
```

#### Java

```java
class Solution {
    private int n;
    private int[][] edges;
    private int k;

    public int minCost(int n, int[][] edges, int k) {
        this.n = n;
        this.edges = edges;
        this.k = k;
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);
        int l = 0, r = edges.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return check(l) ? edges[l][2] : -1;
    }

    private boolean check(int idx) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 0; i <= idx; ++i) {
            int u = edges[i][0], v = edges[i][1];
            g[u].add(v);
            g[v].add(u);
        }
        List<Integer> q = new ArrayList<>();
        q.add(0);
        int dist = 0;
        boolean[] vis = new boolean[n];
        vis[0] = true;
        while (!q.isEmpty()) {
            List<Integer> nq = new ArrayList<>();
            for (int u : q) {
                if (u == n - 1) {
                    return dist <= k;
                }
                for (int v : g[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        nq.add(v);
                    }
                }
            }
            q = nq;
            ++dist;
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minCost(int n, vector<vector<int>>& edges, int k) {
        sort(edges.begin(), edges.end(),
            [](const vector<int>& a, const vector<int>& b) {
                return a[2] < b[2];
            });

        auto check = [&](int idx) -> bool {
            vector<vector<int>> g(n);
            for (int i = 0; i <= idx; ++i) {
                int u = edges[i][0], v = edges[i][1];
                g[u].push_back(v);
                g[v].push_back(u);
            }

            vector<int> q;
            q.push_back(0);
            vector<char> vis(n, 0);
            vis[0] = 1;

            int dist = 0;
            while (!q.empty()) {
                vector<int> nq;
                for (int u : q) {
                    if (u == n - 1) {
                        return dist <= k;
                    }
                    for (int v : g[u]) {
                        if (!vis[v]) {
                            vis[v] = 1;
                            nq.push_back(v);
                        }
                    }
                }
                q.swap(nq);
                ++dist;
            }
            return false;
        };

        int m = edges.size();
        int l = 0, r = m - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return check(l) ? edges[l][2] : -1;
    }
};
```

#### Go

```go
func minCost(n int, edges [][]int, k int) int {
	sort.Slice(edges, func(i, j int) bool {
		return edges[i][2] < edges[j][2]
	})

	check := func(idx int) bool {
		g := make([][]int, n)
		for i := 0; i <= idx; i++ {
			u, v := edges[i][0], edges[i][1]
			g[u] = append(g[u], v)
			g[v] = append(g[v], u)
		}

		q := make([]int, 0, n)
		q = append(q, 0)
		vis := make([]bool, n)
		vis[0] = true

		dist := 0
		for len(q) > 0 {
			nq := make([]int, 0)
			for _, u := range q {
				if u == n-1 {
					return dist <= k
				}
				for _, v := range g[u] {
					if !vis[v] {
						vis[v] = true
						nq = append(nq, v)
					}
				}
			}
			q = nq
			dist++
		}
		return false
	}

	m := len(edges)
	l, r := 0, m-1
	for l < r {
		mid := (l + r) >> 1
		if check(mid) {
			r = mid
		} else {
			l = mid + 1
		}
	}
	if check(l) {
		return edges[l][2]
	}
	return -1
}
```

#### TypeScript

```ts
function minCost(n: number, edges: number[][], k: number): number {
    edges.sort((a, b) => a[2] - b[2]);

    const check = (idx: number): boolean => {
        const g: number[][] = Array.from({ length: n }, () => []);
        for (let i = 0; i <= idx; i++) {
            const [u, v] = edges[i];
            g[u].push(v);
            g[v].push(u);
        }

        let q: number[] = [0];
        const vis: boolean[] = Array(n).fill(false);
        vis[0] = true;

        let dist = 0;
        while (q.length > 0) {
            const nq: number[] = [];
            for (const u of q) {
                if (u === n - 1) {
                    return dist <= k;
                }
                for (const v of g[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        nq.push(v);
                    }
                }
            }
            q = nq;
            dist++;
        }
        return false;
    };

    let [l, r] = [0, edges.length - 1];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return check(l) ? edges[l][2] : -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
