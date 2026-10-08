---
comments: true
difficulty: 中等
rating: 1682
source: 第 188 场周赛 Q3
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 哈希表
    - 树形 DP
---

<!-- problem:start -->

# [1443. 收集树上所有苹果的最少时间](https://leetcode.cn/problems/minimum-time-to-collect-all-apples-in-a-tree)

[English Version](/solution/1400-1499/1443.Minimum%20Time%20to%20Collect%20All%20Apples%20in%20a%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一棵有&nbsp;<code>n</code>&nbsp;个节点的无向树，节点编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n-1</code>&nbsp;，它们中有一些节点有苹果。通过树上的一条边，需要花费 1 秒钟。你从&nbsp;<strong>节点 0&nbsp;</strong>出发，请你返回最少需要多少秒，可以收集到所有苹果，并回到节点 0 。</p>

<p>无向树的边由&nbsp;<code>edges</code>&nbsp;给出，其中&nbsp;<code>edges[i] = [from<sub>i</sub>, to<sub>i</sub>]</code>&nbsp;，表示有一条边连接&nbsp;<code>from</code>&nbsp;和&nbsp;<code>to<sub>i</sub></code> 。除此以外，还有一个布尔数组&nbsp;<code>hasApple</code> ，其中&nbsp;<code>hasApple[i] = true</code>&nbsp;代表节点&nbsp;<code>i</code>&nbsp;有一个苹果，否则，节点&nbsp;<code>i</code>&nbsp;没有苹果。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1443.Minimum%20Time%20to%20Collect%20All%20Apples%20in%20a%20Tree/images/min_time_collect_apple_1.png" style="height: 212px; width: 300px;" /></strong></p>

<pre>
<strong>输入：</strong>n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
<strong>输出：</strong>8 
<strong>解释：</strong>上图展示了给定的树，其中红色节点表示有苹果。一个能收集到所有苹果的最优方案由绿色箭头表示。
</pre>

<p><strong class="example">示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1443.Minimum%20Time%20to%20Collect%20All%20Apples%20in%20a%20Tree/images/min_time_collect_apple_2.png" style="height: 212px; width: 300px;" /></strong></p>

<pre>
<strong>输入：</strong>n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
<strong>输出：</strong>6
<strong>解释：</strong>上图展示了给定的树，其中红色节点表示有苹果。一个能收集到所有苹果的最优方案由绿色箭头表示。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>&nbsp;&lt; b<sub>i</sub>&nbsp;&lt;= n - 1</code></li>
	<li><code>hasApple.length == n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

<!-- thinking:start -->

> **思考**
>
> 必须从 $0$ 出发收集全部苹果并返回，每条边走两次（去和回）。 $n\le 10^5$，只能一遍 DFS。
>
> 子树内若既无苹果、子孙也不需继续走，则不必进入。对孩子递归得到的边权（进入该孩子记 $2$）求和，仅当本节点有苹果或子树代价非零时，才把进入边的代价加上返回。根的进入代价为 $0$。

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minTime(self, n: int, edges: List[List[int]], hasApple: List[bool]) -> int:
        def dfs(u, cost):
            if vis[u]:
                return 0
            vis[u] = True
            nxt_cost = 0
            for v in g[u]:
                nxt_cost += dfs(v, 2)
            if not hasApple[u] and nxt_cost == 0:
                return 0
            return cost + nxt_cost

        g = defaultdict(list)
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        vis = [False] * n
        return dfs(0, 0)
```

#### Java

```java
class Solution {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        boolean[] vis = new boolean[n];
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        return dfs(0, 0, g, hasApple, vis);
    }

    private int dfs(int u, int cost, List<Integer>[] g, List<Boolean> hasApple, boolean[] vis) {
        if (vis[u]) {
            return 0;
        }
        vis[u] = true;
        int nxtCost = 0;
        for (int v : g[u]) {
            nxtCost += dfs(v, 2, g, hasApple, vis);
        }
        if (!hasApple.get(u) && nxtCost == 0) {
            return 0;
        }
        return cost + nxtCost;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minTime(int n, vector<vector<int>>& edges, vector<bool>& hasApple) {
        vector<bool> vis(n);
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        return dfs(0, 0, g, hasApple, vis);
    }

    int dfs(int u, int cost, vector<vector<int>>& g, vector<bool>& hasApple, vector<bool>& vis) {
        if (vis[u]) return 0;
        vis[u] = true;
        int nxt = 0;
        for (int& v : g[u]) nxt += dfs(v, 2, g, hasApple, vis);
        if (!hasApple[u] && !nxt) return 0;
        return cost + nxt;
    }
};
```

#### Go

```go
func minTime(n int, edges [][]int, hasApple []bool) int {
	vis := make([]bool, n)
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	var dfs func(int, int) int
	dfs = func(u, cost int) int {
		if vis[u] {
			return 0
		}
		vis[u] = true
		nxt := 0
		for _, v := range g[u] {
			nxt += dfs(v, 2)
		}
		if !hasApple[u] && nxt == 0 {
			return 0
		}
		return cost + nxt
	}
	return dfs(0, 0)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：显式栈

<!-- thinking:start -->

> **思考**
>
> 必须从 $0$ 出发收集全部苹果并返回，每条用到的边走两次。$n \le 10^5$，从根递归统计每棵子树的往返代价，链上的调用深度就是 $n$，会超出递归栈。
>
> 一棵子树如果自己没有苹果、子孙也不需要再走，就不必进入。孩子都处理完以后，把各孩子的往返代价相加；当前点有苹果或这个和不为 $0$ 时，再加上进入当前点的代价，根是 $0$，其余点是 $2$。
>
> 显式栈按 $(节点, 父节点, 状态)$ 做后序。进入时先压出栈标记再压孩子，离开时按上面的规则写入往返代价。根的代价就是答案。

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minTime(self, n: int, edges: List[List[int]], hasApple: List[bool]) -> int:
        g = [[] for _ in range(n)]
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        cost = [0] * n
        stk = [(0, -1, 0)]
        while stk:
            u, fa, state = stk.pop()
            if state == 0:
                stk.append((u, fa, 1))
                for v in g[u]:
                    if v != fa:
                        stk.append((v, u, 0))
            else:
                nxt = 0
                for v in g[u]:
                    if v != fa:
                        nxt += cost[v]
                if hasApple[u] or nxt:
                    cost[u] = nxt if u == 0 else nxt + 2
        return cost[0]
```

#### Java

```java
class Solution {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        int[] cost = new int[n];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, -1, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int u = cur[0], fa = cur[1], state = cur[2];
            if (state == 0) {
                stk.push(new int[] {u, fa, 1});
                for (int v : g[u]) {
                    if (v != fa) {
                        stk.push(new int[] {v, u, 0});
                    }
                }
            } else {
                int nxt = 0;
                for (int v : g[u]) {
                    if (v != fa) {
                        nxt += cost[v];
                    }
                }
                if (hasApple.get(u) || nxt > 0) {
                    cost[u] = u == 0 ? nxt : nxt + 2;
                }
            }
        }
        return cost[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minTime(int n, vector<vector<int>>& edges, vector<bool>& hasApple) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        vector<int> cost(n);
        vector<array<int, 3>> stk{{0, -1, 0}};
        while (!stk.empty()) {
            auto [u, fa, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.push_back({u, fa, 1});
                for (int v : g[u]) {
                    if (v != fa) {
                        stk.push_back({v, u, 0});
                    }
                }
            } else {
                int nxt = 0;
                for (int v : g[u]) {
                    if (v != fa) {
                        nxt += cost[v];
                    }
                }
                if (hasApple[u] || nxt) {
                    cost[u] = u == 0 ? nxt : nxt + 2;
                }
            }
        }
        return cost[0];
    }
};
```

#### Go

```go
func minTime(n int, edges [][]int, hasApple []bool) int {
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	cost := make([]int, n)
	stk := [][3]int{{0, -1, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		u, fa, state := cur[0], cur[1], cur[2]
		if state == 0 {
			stk = append(stk, [3]int{u, fa, 1})
			for _, v := range g[u] {
				if v != fa {
					stk = append(stk, [3]int{v, u, 0})
				}
			}
		} else {
			nxt := 0
			for _, v := range g[u] {
				if v != fa {
					nxt += cost[v]
				}
			}
			if hasApple[u] || nxt > 0 {
				if u == 0 {
					cost[u] = nxt
				} else {
					cost[u] = nxt + 2
				}
			}
		}
	}
	return cost[0]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
