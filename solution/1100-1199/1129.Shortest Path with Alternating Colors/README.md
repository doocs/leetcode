# [1129. 颜色交替的最短路径](https://leetcode.cn/problems/shortest-path-with-alternating-colors)

[English Version](/solution/1100-1199/1129.Shortest%20Path%20with%20Alternating%20Colors/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数 <code>n</code>，即有向图中的节点数，其中节点标记为 <code>0</code> 到 <code>n - 1</code>。图中的每条边为红色或者蓝色，并且可能存在自环或平行边。</p>

<p>给定两个数组&nbsp;<code>redEdges</code>&nbsp;和&nbsp;<code>blueEdges</code>，其中：</p>

<ul>
	<li><code>redEdges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;表示图中存在一条从节点&nbsp;<code>a<sub>i</sub></code>&nbsp;到节点&nbsp;<code>b<sub>i</sub></code>&nbsp;的红色有向边，</li>
	<li><code>blueEdges[j] = [u<sub>j</sub>, v<sub>j</sub>]</code>&nbsp;表示图中存在一条从节点&nbsp;<code>u<sub>j</sub></code>&nbsp;到节点&nbsp;<code>v<sub>j</sub></code>&nbsp;的蓝色有向边。</li>
</ul>

<p>返回长度为 <code>n</code> 的数组&nbsp;<code>answer</code>，其中&nbsp;<code>answer[X]</code>&nbsp;是从节点&nbsp;<code>0</code>&nbsp;到节点&nbsp;<code>X</code>&nbsp;的红色边和蓝色边交替出现的最短路径的长度。如果不存在这样的路径，那么 <code>answer[x] = -1</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
<strong>输出：</strong>[0,1,-1]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
<strong>输出：</strong>[0,1,-1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= redEdges.length,&nbsp;blueEdges.length &lt;= 400</code></li>
	<li><code>redEdges[i].length == blueEdges[j].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub>, u<sub>j</sub>, v<sub>j</sub>&nbsp;&lt; n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

题目实际上是最短路问题，我们可以考虑使用 BFS 来解决。

首先，我们对所有的边进行预处理，将所有的边按照颜色分类，存储到多维数组 $g$ 中。其中 $g[0]$ 存储所有红色边，而 $g[1]$ 存储所有蓝色边。

接着，我们定义以下数据结构或变量：

-   队列 $q$：用来存储当前搜索到的节点，以及当前边的颜色；
-   集合 $vis$：用来存储已经搜索过的节点，以及当前边的颜色；
-   变量 $d$：用来表示当前搜索的层数，即当前搜索到的节点到起点的距离；
-   数组 $ans$：用来存储每个节点到起点的最短距离。初始时，我们将 $ans$ 数组中的所有元素初始化为 $-1$，表示所有节点到起点的距离都未知。

我们首先将起点 $0$ 和起点边的颜色 $0$ 或 $1$ 入队，表示从起点出发，且当前是红色或蓝色边。

接下来，我们开始进行 BFS 搜索。我们每次从队列中取出一个节点 $(i, c)$，如果当前节点的答案还未更新，则将当前节点的答案更新为当前层数 $d$，即 $ans[i] = d$。然后，我们将当前边的颜色 $c$ 取反，即如果当前边为红色，则将其变为蓝色，反之亦然。我们取出颜色对应的所有边，如果边的另一端节点 $j$ 未被搜索过，则将其入队。

搜索结束后，返回答案数组即可。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别为节点数和边数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shortestAlternatingPaths(
        self, n: int, redEdges: List[List[int]], blueEdges: List[List[int]]
    ) -> List[int]:
        g = [defaultdict(list), defaultdict(list)]
        for i, j in redEdges:
            g[0][i].append(j)
        for i, j in blueEdges:
            g[1][i].append(j)
        ans = [-1] * n
        vis = set()
        q = deque([(0, 0), (0, 1)])
        d = 0
        while q:
            for _ in range(len(q)):
                i, c = q.popleft()
                if ans[i] == -1:
                    ans[i] = d
                vis.add((i, c))
                c ^= 1
                for j in g[c][i]:
                    if (j, c) not in vis:
                        q.append((j, c))
            d += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[][] g = new List[2][n];
        for (var f : g) {
            Arrays.setAll(f, k -> new ArrayList<>());
        }
        for (var e : redEdges) {
            g[0][e[0]].add(e[1]);
        }
        for (var e : blueEdges) {
            g[1][e[0]].add(e[1]);
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        q.offer(new int[] {0, 1});
        boolean[][] vis = new boolean[n][2];
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        int d = 0;
        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; --k) {
                var p = q.poll();
                int i = p[0], c = p[1];
                if (ans[i] == -1) {
                    ans[i] = d;
                }
                vis[i][c] = true;
                c ^= 1;
                for (int j : g[c][i]) {
                    if (!vis[j][c]) {
                        q.offer(new int[] {j, c});
                    }
                }
            }
            ++d;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> shortestAlternatingPaths(int n, vector<vector<int>>& redEdges, vector<vector<int>>& blueEdges) {
        vector<vector<vector<int>>> g(2, vector<vector<int>>(n));
        for (auto& e : redEdges) {
            g[0][e[0]].push_back(e[1]);
        }
        for (auto& e : blueEdges) {
            g[1][e[0]].push_back(e[1]);
        }
        queue<pair<int, int>> q;
        q.emplace(0, 0);
        q.emplace(0, 1);
        bool vis[n][2];
        memset(vis, false, sizeof vis);
        vector<int> ans(n, -1);
        int d = 0;
        while (!q.empty()) {
            for (int k = q.size(); k; --k) {
                auto [i, c] = q.front();
                q.pop();
                if (ans[i] == -1) {
                    ans[i] = d;
                }
                vis[i][c] = true;
                c ^= 1;
                for (int& j : g[c][i]) {
                    if (!vis[j][c]) {
                        q.emplace(j, c);
                    }
                }
            }
            ++d;
        }
        return ans;
    }
};
```

### **Go**

```go
func shortestAlternatingPaths(n int, redEdges [][]int, blueEdges [][]int) []int {
	g := [2][][]int{}
	for i := range g {
		g[i] = make([][]int, n)
	}
	for _, e := range redEdges {
		g[0][e[0]] = append(g[0][e[0]], e[1])
	}
	for _, e := range blueEdges {
		g[1][e[0]] = append(g[1][e[0]], e[1])
	}
	type pair struct{ i, c int }
	q := []pair{pair{0, 0}, pair{0, 1}}
	ans := make([]int, n)
	vis := make([][2]bool, n)
	for i := range ans {
		ans[i] = -1
	}
	d := 0
	for len(q) > 0 {
		for k := len(q); k > 0; k-- {
			p := q[0]
			q = q[1:]
			i, c := p.i, p.c
			if ans[i] == -1 {
				ans[i] = d
			}
			vis[i][c] = true
			c ^= 1
			for _, j := range g[c][i] {
				if !vis[j][c] {
					q = append(q, pair{j, c})
				}
			}
		}
		d++
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
