---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3243.Shortest%20Distance%20After%20Road%20Addition%20Queries%20I/README.md
rating: 1567
source: 第 409 场周赛 Q2
tags:
    - 广度优先搜索
    - 图
    - 数组
---

<!-- problem:start -->

# [3243. 新增道路查询后的最短距离 I](https://leetcode.cn/problems/shortest-distance-after-road-addition-queries-i)

[English Version](/solution/3200-3299/3243.Shortest%20Distance%20After%20Road%20Addition%20Queries%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code> 和一个二维整数数组 <code>queries</code>。</p>

<p>有 <code>n</code> 个城市，编号从 <code>0</code> 到 <code>n - 1</code>。初始时，每个城市 <code>i</code> 都有一条<strong>单向</strong>道路通往城市 <code>i + 1</code>（ <code>0 &lt;= i &lt; n - 1</code>）。</p>

<p><code>queries[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> 表示新建一条从城市 <code>u<sub>i</sub></code> 到城市 <code>v<sub>i</sub></code> 的<strong>单向</strong>道路。每次查询后，你需要找到从城市 <code>0</code> 到城市 <code>n - 1</code> 的<strong>最短路径</strong>的<strong>长度</strong>。</p>

<p>返回一个数组 <code>answer</code>，对于范围 <code>[0, queries.length - 1]</code> 中的每个 <code>i</code>，<code>answer[i]</code> 是处理完<strong>前</strong> <code>i + 1</code> 个查询后，从城市 <code>0</code> 到城市 <code>n - 1</code> 的最短路径的<em>长度</em>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 5, queries = [[2, 4], [0, 2], [0, 4]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[3, 2, 1]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3243.Shortest%20Distance%20After%20Road%20Addition%20Queries%20I/images/image8.jpg" style="width: 350px; height: 60px;" /></p>

<p>新增一条从 2 到 4 的道路后，从 0 到 4 的最短路径长度为 3。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3243.Shortest%20Distance%20After%20Road%20Addition%20Queries%20I/images/image9.jpg" style="width: 350px; height: 60px;" /></p>

<p>新增一条从 0 到 2 的道路后，从 0 到 4 的最短路径长度为 2。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3243.Shortest%20Distance%20After%20Road%20Addition%20Queries%20I/images/image10.jpg" style="width: 350px; height: 96px;" /></p>

<p>新增一条从 0 到 4 的道路后，从 0 到 4 的最短路径长度为 1。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 4, queries = [[0, 3], [0, 2]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1, 1]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3243.Shortest%20Distance%20After%20Road%20Addition%20Queries%20I/images/image11.jpg" style="width: 300px; height: 70px;" /></p>

<p>新增一条从 0 到 3 的道路后，从 0 到 3 的最短路径长度为 1。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3243.Shortest%20Distance%20After%20Road%20Addition%20Queries%20I/images/image12.jpg" style="width: 300px; height: 70px;" /></p>

<p>新增一条从 0 到 2 的道路后，从 0 到 3 的最短路径长度仍为 1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 500</code></li>
	<li><code>1 &lt;= queries.length &lt;= 500</code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= queries[i][0] &lt; queries[i][1] &lt; n</code></li>
	<li><code>1 &lt; queries[i][1] - queries[i][0]</code></li>
	<li>查询中没有重复的道路。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS

我们先建立一个有向图 $\textit{g}$，其中 $\textit{g}[i]$ 表示从城市 $i$ 出发可以到达的城市列表，初始时，每个城市 $i$ 都有一条单向道路通往城市 $i + 1$。

然后，我们对每个查询 $[u, v]$，将 $u$ 添加到 $v$ 的出发城市列表中，然后使用 BFS 求出从城市 $0$ 到城市 $n - 1$ 的最短路径长度，将结果添加到答案数组中。

最后返回答案数组即可。

时间复杂度 $O(q \times (n + q))$，空间复杂度 $O(n + q)$。其中 $n$ 和 $q$ 分别为城市数量和查询数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def shortestDistanceAfterQueries(
        self, n: int, queries: List[List[int]]
    ) -> List[int]:
        def bfs(i: int) -> int:
            q = deque([i])
            vis = [False] * n
            vis[i] = True
            d = 0
            while 1:
                for _ in range(len(q)):
                    u = q.popleft()
                    if u == n - 1:
                        return d
                    for v in g[u]:
                        if not vis[v]:
                            vis[v] = True
                            q.append(v)
                d += 1

        g = [[i + 1] for i in range(n - 1)]
        ans = []
        for u, v in queries:
            g[u].append(v)
            ans.append(bfs(0))
        return ans
```

#### Java

```java
class Solution {
    private List<Integer>[] g;
    private int n;

    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        this.n = n;
        g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 0; i < n - 1; ++i) {
            g[i].add(i + 1);
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int u = queries[i][0], v = queries[i][1];
            g[u].add(v);
            ans[i] = bfs(0);
        }
        return ans;
    }

    private int bfs(int i) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(i);
        boolean[] vis = new boolean[n];
        vis[i] = true;
        for (int d = 0;; ++d) {
            for (int k = q.size(); k > 0; --k) {
                int u = q.poll();
                if (u == n - 1) {
                    return d;
                }
                for (int v : g[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        q.offer(v);
                    }
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
    vector<int> shortestDistanceAfterQueries(int n, vector<vector<int>>& queries) {
        vector<int> g[n];
        for (int i = 0; i < n - 1; ++i) {
            g[i].push_back(i + 1);
        }
        auto bfs = [&](int i) -> int {
            queue<int> q{{i}};
            vector<bool> vis(n);
            vis[i] = true;
            for (int d = 0;; ++d) {
                for (int k = q.size(); k; --k) {
                    int u = q.front();
                    q.pop();
                    if (u == n - 1) {
                        return d;
                    }
                    for (int v : g[u]) {
                        if (!vis[v]) {
                            vis[v] = true;
                            q.push(v);
                        }
                    }
                }
            }
        };
        vector<int> ans;
        for (const auto& q : queries) {
            g[q[0]].push_back(q[1]);
            ans.push_back(bfs(0));
        }
        return ans;
    }
};
```

#### Go

```go
func shortestDistanceAfterQueries(n int, queries [][]int) []int {
	g := make([][]int, n)
	for i := range g {
		g[i] = append(g[i], i+1)
	}
	bfs := func(i int) int {
		q := []int{i}
		vis := make([]bool, n)
		vis[i] = true
		for d := 0; ; d++ {
			for k := len(q); k > 0; k-- {
				u := q[0]
				if u == n-1 {
					return d
				}
				q = q[1:]
				for _, v := range g[u] {
					if !vis[v] {
						vis[v] = true
						q = append(q, v)
					}
				}
			}
		}
	}
	ans := make([]int, len(queries))
	for i, q := range queries {
		g[q[0]] = append(g[q[0]], q[1])
		ans[i] = bfs(0)
	}
	return ans
}
```

#### TypeScript

```ts
function shortestDistanceAfterQueries(n: number, queries: number[][]): number[] {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (let i = 0; i < n - 1; ++i) {
        g[i].push(i + 1);
    }
    const bfs = (i: number): number => {
        const q: number[] = [i];
        const vis: boolean[] = Array(n).fill(false);
        vis[i] = true;
        for (let d = 0; ; ++d) {
            const nq: number[] = [];
            for (const u of q) {
                if (u === n - 1) {
                    return d;
                }
                for (const v of g[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        nq.push(v);
                    }
                }
            }
            q.splice(0, q.length, ...nq);
        }
    };
    const ans: number[] = [];
    for (const [u, v] of queries) {
        g[u].push(v);
        ans.push(bfs(0));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
