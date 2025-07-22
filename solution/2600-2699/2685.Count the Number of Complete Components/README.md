---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2685.Count%20the%20Number%20of%20Complete%20Components/README.md
rating: 1769
source: 第 345 场周赛 Q4
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 图
---

<!-- problem:start -->

# [2685. 统计完全连通分量的数量](https://leetcode.cn/problems/count-the-number-of-complete-components)

[English Version](/solution/2600-2699/2685.Count%20the%20Number%20of%20Complete%20Components/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code> 。现有一个包含 <code>n</code> 个顶点的 <strong>无向</strong> 图，顶点按从 <code>0</code> 到 <code>n - 1</code> 编号。给你一个二维整数数组 <code>edges</code> 其中 <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 表示顶点 <code>a<sub>i</sub></code> 和 <code>b<sub>i</sub></code> 之间存在一条 <strong>无向</strong> 边。</p>

<p>返回图中 <strong>完全连通分量</strong> 的数量。</p>

<p>如果在子图中任意两个顶点之间都存在路径，并且子图中没有任何一个顶点与子图外部的顶点共享边，则称其为 <strong>连通分量</strong> 。</p>

<p>如果连通分量中每对节点之间都存在一条边，则称其为 <strong>完全连通分量</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2685.Count%20the%20Number%20of%20Complete%20Components/images/screenshot-from-2023-04-11-23-31-23.png" style="width: 671px; height: 270px;" /></strong></p>

<pre>
<strong>输入：</strong>n = 6, edges = [[0,1],[0,2],[1,2],[3,4]]
<strong>输出：</strong>3
<strong>解释：</strong>如上图所示，可以看到此图所有分量都是完全连通分量。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2685.Count%20the%20Number%20of%20Complete%20Components/images/screenshot-from-2023-04-11-23-32-00.png" style="width: 671px; height: 270px;" /></strong></p>

<pre>
<strong>输入：</strong>n = 6, edges = [[0,1],[0,2],[1,2],[3,4],[3,5]]
<strong>输出：</strong>1
<strong>解释：</strong>包含节点 0、1 和 2 的分量是完全连通分量，因为每对节点之间都存在一条边。
包含节点 3 、4 和 5 的分量不是完全连通分量，因为节点 4 和 5 之间不存在边。
因此，在图中完全连接分量的数量是 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>0 &lt;= edges.length &lt;= n * (n - 1) / 2</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>不存在重复的边</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们先根据题目给定的边建立一个邻接表 $g$，其中 $g[i]$ 表示顶点 $i$ 的邻接点集合。

然后我们从 $0$ 开始遍历所有顶点，如果当前顶点没有被访问过，我们就从当前顶点开始进行深度优先搜索，统计当前连通分量的顶点数 $x$ 和边数 $y$。如果 $\frac{x(x-1)}{2} = y$，那么当前连通分量就是完全连通分量，我们将答案加一。

最后我们返回答案即可。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是顶点数和边数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countCompleteComponents(self, n: int, edges: List[List[int]]) -> int:
        def dfs(i: int) -> (int, int):
            vis[i] = True
            x, y = 1, len(g[i])
            for j in g[i]:
                if not vis[j]:
                    a, b = dfs(j)
                    x += a
                    y += b
            return x, y

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = [False] * n
        ans = 0
        for i in range(n):
            if not vis[i]:
                a, b = dfs(i)
                ans += a * (a - 1) == b
        return ans
```

#### Java

```java
class Solution {
    private List<Integer>[] g;
    private boolean[] vis;

    public int countCompleteComponents(int n, int[][] edges) {
        g = new List[n];
        vis = new boolean[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                int[] t = dfs(i);
                if (t[0] * (t[0] - 1) == t[1]) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private int[] dfs(int i) {
        vis[i] = true;
        int x = 1, y = g[i].size();
        for (int j : g[i]) {
            if (!vis[j]) {
                int[] t = dfs(j);
                x += t[0];
                y += t[1];
            }
        }
        return new int[] {x, y};
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countCompleteComponents(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n);
        bool vis[n];
        memset(vis, false, sizeof(vis));
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        function<pair<int, int>(int)> dfs = [&](int i) -> pair<int, int> {
            vis[i] = true;
            int x = 1, y = g[i].size();
            for (int j : g[i]) {
                if (!vis[j]) {
                    auto [a, b] = dfs(j);
                    x += a;
                    y += b;
                }
            }
            return make_pair(x, y);
        };
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                auto [a, b] = dfs(i);
                if (a * (a - 1) == b) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countCompleteComponents(n int, edges [][]int) (ans int) {
	g := make([][]int, n)
	vis := make([]bool, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	var dfs func(int) (int, int)
	dfs = func(i int) (int, int) {
		vis[i] = true
		x, y := 1, len(g[i])
		for _, j := range g[i] {
			if !vis[j] {
				a, b := dfs(j)
				x += a
				y += b
			}
		}
		return x, y
	}
	for i := range vis {
		if !vis[i] {
			a, b := dfs(i)
			if a*(a-1) == b {
				ans++
			}
		}
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：取巧做法

要解决的问题：

1. 如何保存每一个节点与其它点联通状态
2. 如何判断多个点是否是一个联通图

对于第一点：实际上就是保存了当前到每个点的联通点集合（包括自己），方便后续判等。
第二点：有了第一点之后，如果是连通图中的点就有：

1. 此点包含此联通图中所有的点（包括自己）
2. 并且只包含此联通图中的点

拿示例一举例：

-   5 包含的联通点有且只有自己，所以是连通图
-   0 包含 0、1、2，同理 1、2 点也是
-   3 和 4 也是包含自己和彼此
-   基于以上就有以下代码实现：

<!-- tabs:start -->

#### C++

```cpp
class Solution {
public:
    int countCompleteComponents(int n, vector<vector<int>>& edges) {
        int ans = 0;
        vector<set<int>> m(n + 1, set<int>());
        for (int i = 0; i < n; i++) {
            m[i].insert(i);
        }
        for (auto x : edges) {
            m[x[0]].insert(x[1]);
            m[x[1]].insert(x[0]);
        }
        map<set<int>, int> s;
        for (int i = 0; i < n; i++) {
            s[m[i]]++;
        }
        for (auto& [x, y] : s) {
            if (y == x.size()) {
                ans++;
            }
        }
        return ans;
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
