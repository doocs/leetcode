---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1192.Critical%20Connections%20in%20a%20Network/README.md
rating: 2084
tags:
    - 深度优先搜索
    - 图
    - 双连通分量
---

# [1192. 查找集群内的关键连接](https://leetcode.cn/problems/critical-connections-in-a-network)

[English Version](/solution/1100-1199/1192.Critical%20Connections%20in%20a%20Network/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>力扣数据中心有&nbsp;<code>n</code>&nbsp;台服务器，分别按从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n-1</code>&nbsp;的方式进行了编号。它们之间以 <strong>服务器到服务器</strong> 的形式相互连接组成了一个内部集群，连接是无向的。用 &nbsp;<code>connections</code> 表示集群网络，<code>connections[i] = [a, b]</code>&nbsp;表示服务器 <code>a</code>&nbsp;和 <code>b</code>&nbsp;之间形成连接。任何服务器都可以直接或者间接地通过网络到达任何其他服务器。</p>

<p><strong>关键连接</strong><em> </em>是在该集群中的重要连接，假如我们将它移除，便会导致某些服务器无法访问其他服务器。</p>

<p>请你以任意顺序返回该集群内的所有 <strong>关键连接</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1192.Critical%20Connections%20in%20a%20Network/images/critical-connections-in-a-network.png" style="height: 205px; width: 200px;" /></strong></p>

<pre>
<strong>输入：</strong>n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
<strong>输出：</strong>[[1,3]]
<strong>解释：</strong>[[3,1]] 也是正确的。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<b>输入：</b>n = 2, connections = [[0,1]]
<b>输出：</b>[[0,1]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n - 1 &lt;= connections.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>不存在重复的连接</li>
</ul>

## 解法

### 方法一：Tarjan 算法

此题中的「关键连接」即为「桥」。

「桥」：在一连通的无向图中，若去除某一边后会使得图不再连通，则这条边可以视作「桥」。

与之相应的概念还有「割点」。

「割点」：在一连通的无向图中，若去除某一点及所有与其相连的边后会使得图不再连通，则这个点可以视作「割点」。

用于求图中的「桥」与「割点」有一算法：tarjan 算法，这个算法使用先递归的访问相邻节点后访问节点自身的 dfs 方法，通过记录「访问的顺序：DFN」以及在递归结束后访问节点自身时探索其可以回溯到的最早被访问的节点来更新「最早可回溯的节点：low」，可以实现在 $O(n)$ 时间内找到图的「桥」与「割点」。同时，此种算法可以用于查找有向图中的强连通分量。

<!-- tabs:start -->

```python
class Solution:
    def criticalConnections(
        self, n: int, connections: List[List[int]]
    ) -> List[List[int]]:
        def tarjan(a: int, fa: int):
            nonlocal now
            now += 1
            dfn[a] = low[a] = now
            for b in g[a]:
                if b == fa:
                    continue
                if not dfn[b]:
                    tarjan(b, a)
                    low[a] = min(low[a], low[b])
                    if low[b] > dfn[a]:
                        ans.append([a, b])
                else:
                    low[a] = min(low[a], dfn[b])

        g = [[] for _ in range(n)]
        for a, b in connections:
            g[a].append(b)
            g[b].append(a)

        dfn = [0] * n
        low = [0] * n
        now = 0
        ans = []
        tarjan(0, -1)
        return ans
```

```java
class Solution {
    private int now;
    private List<Integer>[] g;
    private List<List<Integer>> ans = new ArrayList<>();
    private int[] dfn;
    private int[] low;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        dfn = new int[n];
        low = new int[n];
        for (var e : connections) {
            int a = e.get(0), b = e.get(1);
            g[a].add(b);
            g[b].add(a);
        }
        tarjan(0, -1);
        return ans;
    }

    private void tarjan(int a, int fa) {
        dfn[a] = low[a] = ++now;
        for (int b : g[a]) {
            if (b == fa) {
                continue;
            }
            if (dfn[b] == 0) {
                tarjan(b, a);
                low[a] = Math.min(low[a], low[b]);
                if (low[b] > dfn[a]) {
                    ans.add(List.of(a, b));
                }
            } else {
                low[a] = Math.min(low[a], dfn[b]);
            }
        }
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> criticalConnections(int n, vector<vector<int>>& connections) {
        int now = 0;
        vector<int> dfn(n);
        vector<int> low(n);
        vector<int> g[n];
        for (auto& e : connections) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<vector<int>> ans;
        function<void(int, int)> tarjan = [&](int a, int fa) -> void {
            dfn[a] = low[a] = ++now;
            for (int b : g[a]) {
                if (b == fa) {
                    continue;
                }
                if (!dfn[b]) {
                    tarjan(b, a);
                    low[a] = min(low[a], low[b]);
                    if (low[b] > dfn[a]) {
                        ans.push_back({a, b});
                    }
                } else {
                    low[a] = min(low[a], dfn[b]);
                }
            }
        };
        tarjan(0, -1);
        return ans;
    }
};
```

```go
func criticalConnections(n int, connections [][]int) (ans [][]int) {
	now := 0
	g := make([][]int, n)
	dfn := make([]int, n)
	low := make([]int, n)
	for _, e := range connections {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	var tarjan func(int, int)
	tarjan = func(a, fa int) {
		now++
		dfn[a], low[a] = now, now
		for _, b := range g[a] {
			if b == fa {
				continue
			}
			if dfn[b] == 0 {
				tarjan(b, a)
				low[a] = min(low[a], low[b])
				if low[b] > dfn[a] {
					ans = append(ans, []int{a, b})
				}
			} else {
				low[a] = min(low[a], dfn[b])
			}
		}
	}
	tarjan(0, -1)
	return
}
```

```ts
function criticalConnections(n: number, connections: number[][]): number[][] {
    let now: number = 0;
    const g: number[][] = Array(n)
        .fill(0)
        .map(() => []);
    const dfn: number[] = Array(n).fill(0);
    const low: number[] = Array(n).fill(0);
    for (const [a, b] of connections) {
        g[a].push(b);
        g[b].push(a);
    }
    const ans: number[][] = [];
    const tarjan = (a: number, fa: number) => {
        dfn[a] = low[a] = ++now;
        for (const b of g[a]) {
            if (b === fa) {
                continue;
            }
            if (!dfn[b]) {
                tarjan(b, a);
                low[a] = Math.min(low[a], low[b]);
                if (low[b] > dfn[a]) {
                    ans.push([a, b]);
                }
            } else {
                low[a] = Math.min(low[a], dfn[b]);
            }
        }
    };
    tarjan(0, -1);
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
