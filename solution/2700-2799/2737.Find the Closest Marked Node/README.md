# [2737. 找到最近的标记节点](https://leetcode.cn/problems/find-the-closest-marked-node)

[English Version](/solution/2700-2799/2737.Find%20the%20Closest%20Marked%20Node/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个正整数 <code>n</code> ，表示一个 <strong>索引从 0 开始的有向加权</strong> 图的节点数量，以及一个 <strong>索引从 0 开始的二维数组</strong> <code>edges</code> ，其中 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> 表示从节点 <code>u<sub>i</sub></code> 到节点 <code>v<sub>i</sub></code> 的一条权重为 <code>w<sub>i</sub></code> 的边。</p>

<p>并给定一个节点 <code>s</code> 和一个节点数组 <code>marked</code> ；你的任务是找到从 <code>s</code> 到 <code>marked</code> 中 <strong>任何</strong> 节点的 <strong>最短</strong> 距离。</p>

<p>返回一个整数，表示从 <code>s</code> 到 <code>marked</code> 中任何节点的最短距离，如果从 s 到任何标记节点没有路径，则返回 <code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<b>输入：</b>n = 4, edges = [[0,1,1],[1,2,3],[2,3,2],[0,3,4]], s = 0, marked = [2,3]
<b>输出：</b>4
<b>解释：</b>从节点 0（绿色节点）到节点 2（红色节点）有一条路径，即 0-&gt;1-&gt;2，距离为 1 + 3 = 4。 
从节点 0 到节点 3（红色节点）有两条路径，即 0-&gt;1-&gt;2-&gt;3 和 0-&gt;3，分别距离为 1 + 3 + 2 = 6 和 4。 
它们中的最小值是 4。
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2737.Find%20the%20Closest%20Marked%20Node/images/image_2023-06-13_16-34-38.png" style="width: 185px; height: 180px;" /></p>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>n = 5, edges = [[0,1,2],[0,2,4],[1,3,1],[2,3,3],[3,4,2]], s = 1, marked = [0,4]
<b>输出：</b>3
<b>解释：</b>从节点 1（绿色节点）到节点 0（红色节点）没有路径。 
从节点 1 到节点 4（红色节点）有一条路径，即 1-&gt;3-&gt;4，距离为 1 + 2 = 3。 
因此答案是 3。
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2737.Find%20the%20Closest%20Marked%20Node/images/image_2023-06-13_16-35-13.png" style="width: 300px; height: 285px;" /></p>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>n = 4, edges = [[0,1,1],[1,2,3],[2,3,2]], s = 3, marked = [0,1]
<b>输出：</b>-1
<b>解释：</b>从节点 3（绿色节点）到任何一个标记节点（红色节点）都没有路径，因此答案是 -1。
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2737.Find%20the%20Closest%20Marked%20Node/images/image_2023-06-13_16-35-47.png" style="width: 420px; height: 80px;" /></p>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>2 &lt;= n &lt;= 500</code></li>
	<li><code>1 &lt;= edges.length &lt;= 10<sup>4</sup></code></li>
	<li><code>edges[i].length = 3</code></li>
	<li><code>0 &lt;= edges[i][0], edges[i][1] &lt;= n - 1</code></li>
	<li><code>1 &lt;= edges[i][2] &lt;=&nbsp;10<sup>6</sup></code></li>
	<li><code>1 &lt;= marked.length&nbsp;&lt;= n - 1</code></li>
	<li><code>0 &lt;= s, marked[i]&nbsp;&lt;= n - 1</code></li>
	<li><code>s != marked[i]</code></li>
	<li>如果&nbsp;<code>i != j</code>&nbsp;则&nbsp;<code>marked[i] != marked[j]</code></li>
	<li>图中可能有 <strong>重复的边 。</strong></li>
	<li>图的生成不会出现 <strong>自环</strong> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：Dijkstra 算法**

我们先根据题目中提供的边的信息，建立一个邻接矩阵 $g$，其中 $g[i][j]$ 表示节点 $i$ 到节点 $j$ 的距离，如果不存在这样的边，则 $g[i][j]$ 为正无穷。

然后我们就可以使用 Dijkstra 算法求出从起点 $s$ 到所有节点的最短距离，记为 $dist$。

最后我们遍历所有的标记节点，找到距离最小的标记节点，如果距离为正无穷，则返回 $-1$。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为节点数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumDistance(
        self, n: int, edges: List[List[int]], s: int, marked: List[int]
    ) -> int:
        g = [[inf] * n for _ in range(n)]
        for u, v, w in edges:
            g[u][v] = min(g[u][v], w)
        dist = [inf] * n
        vis = [False] * n
        dist[s] = 0
        for _ in range(n):
            t = -1
            for j in range(n):
                if not vis[j] and (t == -1 or dist[t] > dist[j]):
                    t = j
            vis[t] = True
            for j in range(n):
                dist[j] = min(dist[j], dist[t] + g[t][j])
        ans = min(dist[i] for i in marked)
        return -1 if ans >= inf else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumDistance(int n, List<List<Integer>> edges, int s, int[] marked) {
        final int inf = 1 << 29;
        int[][] g = new int[n][n];
        for (var e : g) {
            Arrays.fill(e, inf);
        }
        for (var e : edges) {
            int u = e.get(0), v = e.get(1), w = e.get(2);
            g[u][v] = Math.min(g[u][v], w);
        }
        int[] dist = new int[n];
        Arrays.fill(dist, inf);
        dist[s] = 0;
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            int t = -1;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (t == -1 || dist[t] > dist[j])) {
                    t = j;
                }
            }
            vis[t] = true;
            for (int j = 0; j < n; ++j) {
                dist[j] = Math.min(dist[j], dist[t] + g[t][j]);
            }
        }
        int ans = inf;
        for (int i : marked) {
            ans = Math.min(ans, dist[i]);
        }
        return ans >= inf ? -1 : ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumDistance(int n, vector<vector<int>>& edges, int s, vector<int>& marked) {
        const int inf = 1 << 29;
        vector<vector<int>> g(n, vector<int>(n, inf));
        vector<int> dist(n, inf);
        dist[s] = 0;
        vector<bool> vis(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u][v] = min(g[u][v], w);
        }
        for (int i = 0; i < n; ++i) {
            int t = -1;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (t == -1 || dist[t] > dist[j])) {
                    t = j;
                }
            }
            vis[t] = true;
            for (int j = 0; j < n; ++j) {
                dist[j] = min(dist[j], dist[t] + g[t][j]);
            }
        }
        int ans = inf;
        for (int i : marked) {
            ans = min(ans, dist[i]);
        }
        return ans >= inf ? -1 : ans;
    }
};
```

### **Go**

```go
func minimumDistance(n int, edges [][]int, s int, marked []int) int {
	const inf = 1 << 29
	g := make([][]int, n)
	dist := make([]int, n)
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = inf
		}
		dist[i] = inf
	}
	dist[s] = 0
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		g[u][v] = min(g[u][v], w)
	}
	vis := make([]bool, n)
	for _ = range g {
		t := -1
		for j := 0; j < n; j++ {
			if !vis[j] && (t == -1 || dist[j] < dist[t]) {
				t = j
			}
		}
		vis[t] = true
		for j := 0; j < n; j++ {
			dist[j] = min(dist[j], dist[t]+g[t][j])
		}
	}
	ans := inf
	for _, i := range marked {
		ans = min(ans, dist[i])
	}
	if ans >= inf {
		return -1
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function minimumDistance(n: number, edges: number[][], s: number, marked: number[]): number {
    const inf = 1 << 29;
    const g: number[][] = Array(n)
        .fill(0)
        .map(() => Array(n).fill(inf));
    const dist: number[] = Array(n).fill(inf);
    const vis: boolean[] = Array(n).fill(false);
    for (const [u, v, w] of edges) {
        g[u][v] = Math.min(g[u][v], w);
    }
    dist[s] = 0;
    for (let i = 0; i < n; ++i) {
        let t = -1;
        for (let j = 0; j < n; ++j) {
            if (!vis[j] && (t == -1 || dist[t] > dist[j])) {
                t = j;
            }
        }
        vis[t] = true;
        for (let j = 0; j < n; ++j) {
            dist[j] = Math.min(dist[j], dist[t] + g[t][j]);
        }
    }
    let ans = inf;
    for (const i of marked) {
        ans = Math.min(ans, dist[i]);
    }
    return ans >= inf ? -1 : ans;
}
```

### **...**

```

```

<!-- tabs:end -->
