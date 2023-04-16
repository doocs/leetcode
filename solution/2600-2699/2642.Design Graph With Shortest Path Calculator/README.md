# [2642. 设计可以求最短路径的图类](https://leetcode.cn/problems/design-graph-with-shortest-path-calculator)

[English Version](/solution/2600-2699/2642.Design%20Graph%20With%20Shortest%20Path%20Calculator/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个有&nbsp;<code>n</code>&nbsp;个节点的&nbsp;<strong>有向带权</strong>&nbsp;图，节点编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;。图中的初始边用数组&nbsp;<code>edges</code>&nbsp;表示，其中&nbsp;<code>edges[i] = [from<sub>i</sub>, to<sub>i</sub>, edgeCost<sub>i</sub>]</code>&nbsp;表示从&nbsp;<code>from<sub>i</sub></code>&nbsp;到&nbsp;<code>to<sub>i</sub></code>&nbsp;有一条代价为&nbsp;<code>edgeCost<sub>i</sub></code>&nbsp;的边。</p>

<p>请你实现一个&nbsp;<code>Graph</code>&nbsp;类：</p>

<ul>
	<li><code>Graph(int n, int[][] edges)</code>&nbsp;初始化图有&nbsp;<code>n</code>&nbsp;个节点，并输入初始边。</li>
	<li><code>addEdge(int[] edge)</code>&nbsp;向边集中添加一条边，其中<strong>&nbsp;</strong><code>edge = [from, to, edgeCost]</code>&nbsp;。数据保证添加这条边之前对应的两个节点之间没有有向边。</li>
	<li><code>int shortestPath(int node1, int node2)</code>&nbsp;返回从节点&nbsp;<code>node1</code>&nbsp;到&nbsp;<code>node2</code>&nbsp;的路径<strong>&nbsp;最小</strong>&nbsp;代价。如果路径不存在，返回&nbsp;<code>-1</code>&nbsp;。一条路径的代价是路径中所有边代价之和。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2642.Design%20Graph%20With%20Shortest%20Path%20Calculator/images/graph3drawio-2.png" style="width: 621px; height: 191px;"></p>

<pre><strong>输入：</strong>
["Graph", "shortestPath", "shortestPath", "addEdge", "shortestPath"]
[[4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]], [3, 2], [0, 3], [[1, 3, 4]], [0, 3]]
<b>输出：</b>
[null, 6, -1, null, 6]

<strong>解释：</strong>
Graph g = new Graph(4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]);
g.shortestPath(3, 2); // 返回 6 。从 3 到 2 的最短路径如第一幅图所示：3 -&gt; 0 -&gt; 1 -&gt; 2 ，总代价为 3 + 2 + 1 = 6 。
g.shortestPath(0, 3); // 返回 -1 。没有从 0 到 3 的路径。
g.addEdge([1, 3, 4]); // 添加一条节点 1 到节点 3 的边，得到第二幅图。
g.shortestPath(0, 3); // 返回 6 。从 0 到 3 的最短路径为 0 -&gt; 1 -&gt; 3 ，总代价为 2 + 4 = 6 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= edges.length &lt;= n * (n - 1)</code></li>
	<li><code>edges[i].length == edge.length == 3</code></li>
	<li><code>0 &lt;= from<sub>i</sub>, to<sub>i</sub>, from, to, node1, node2 &lt;= n - 1</code></li>
	<li><code>1 &lt;= edgeCost<sub>i</sub>, edgeCost &lt;= 10<sup>6</sup></code></li>
	<li>图中任何时候都不会有重边和自环。</li>
	<li>调用 <code>addEdge</code>&nbsp;至多&nbsp;<code>100</code>&nbsp;次。</li>
	<li>调用 <code>shortestPath</code>&nbsp;至多&nbsp;<code>100</code>&nbsp;次。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：Dijsktra 算法**

在初始化函数中，我们先用邻接矩阵 $g$ 存储图的边权，其中 $g_{ij}$ 表示从节点 $i$ 到节点 $j$ 的边权，如果 $i$ 和 $j$ 之间没有边，则 $g_{ij}$ 的值为 $\infty$。

在 `addEdge` 函数中，我们更新 $g_{ij}$ 的值为 $edge[2]$。

在 `shortestPath` 函数中，我们使用 Dijsktra 算法求从节点 $node1$ 到节点 $node2$ 的最短路径，其中 $dist[i]$ 表示从节点 $node1$ 到节点 $i$ 的最短路径，$vis[i]$ 表示节点 $i$ 是否已经被访问过。我们初始化 $dist[node1]$ 为 $0$，其余的 $dist[i]$ 均为 $\infty$。然后我们遍历 $n$ 次，每次找到当前未被访问过的节点 $t$，使得 $dist[t]$ 最小。然后我们将节点 $t$ 标记为已访问，然后更新 $dist[i]$ 的值为 $min(dist[i], dist[t] + g_{ti})$。最后我们返回 $dist[node2]$，如果 $dist[node2]$ 为 $\infty$，则说明从节点 $node1$ 到节点 $node2$ 不存在路径，返回 $-1$。

时间复杂度 $O(n^2 \times q)$，空间复杂度 $O(n^2)$。其中 $n$ 为节点数，而 $q$ 为 `shortestPath` 函数的调用次数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Graph:

    def __init__(self, n: int, edges: List[List[int]]):
        self.n = n
        self.g = [[inf] * n for _ in range(n)]
        for f, t, c in edges:
            self.g[f][t] = c

    def addEdge(self, edge: List[int]) -> None:
        f, t, c = edge
        self.g[f][t] = c

    def shortestPath(self, node1: int, node2: int) -> int:
        dist = [inf] * self.n
        dist[node1] = 0
        vis = [False] * self.n
        for _ in range(self.n):
            t = -1
            for j in range(self.n):
                if not vis[j] and (t == -1 or dist[t] > dist[j]):
                    t = j
            vis[t] = True
            for j in range(self.n):
                dist[j] = min(dist[j], dist[t] + self.g[t][j])
        return -1 if dist[node2] == inf else dist[node2]


# Your Graph object will be instantiated and called as such:
# obj = Graph(n, edges)
# obj.addEdge(edge)
# param_2 = obj.shortestPath(node1,node2)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Graph {
    private int n;
    private int[][] g;
    private final int inf = 1 << 29;

    public Graph(int n, int[][] edges) {
        this.n = n;
        g = new int[n][n];
        for (var f : g) {
            Arrays.fill(f, inf);
        }
        for (int[] e : edges) {
            int f = e[0], t = e[1], c = e[2];
            g[f][t] = c;
        }
    }

    public void addEdge(int[] edge) {
        int f = edge[0], t = edge[1], c = edge[2];
        g[f][t] = c;
    }

    public int shortestPath(int node1, int node2) {
        int[] dist = new int[n];
        boolean[] vis = new boolean[n];
        Arrays.fill(dist, inf);
        dist[node1] = 0;
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
        return dist[node2] >= inf ? -1 : dist[node2];
    }
}

/**
 * Your Graph object will be instantiated and called as such:
 * Graph obj = new Graph(n, edges);
 * obj.addEdge(edge);
 * int param_2 = obj.shortestPath(node1,node2);
 */
```

### **C++**

```cpp
class Graph {
public:
    Graph(int n, vector<vector<int>>& edges) {
        this->n = n;
        g = vector<vector<int>>(n, vector<int>(n, inf));
        for (auto& e : edges) {
            int f = e[0], t = e[1], c = e[2];
            g[f][t] = c;
        }
    }

    void addEdge(vector<int> edge) {
        int f = edge[0], t = edge[1], c = edge[2];
        g[f][t] = c;
    }

    int shortestPath(int node1, int node2) {
        vector<bool> vis(n);
        vector<int> dist(n, inf);
        dist[node1] = 0;
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
        return dist[node2] >= inf ? -1 : dist[node2];
    }

private:
    vector<vector<int>> g;
    int n;
    const int inf = 1 << 29;
};

/**
 * Your Graph object will be instantiated and called as such:
 * Graph* obj = new Graph(n, edges);
 * obj->addEdge(edge);
 * int param_2 = obj->shortestPath(node1,node2);
 */
```

### **Go**

```go
const inf = 1 << 29

type Graph struct {
	g [][]int
}

func Constructor(n int, edges [][]int) Graph {
	g := make([][]int, n)
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = inf
		}
	}
	for _, e := range edges {
		f, t, c := e[0], e[1], e[2]
		g[f][t] = c
	}
	return Graph{g}
}

func (this *Graph) AddEdge(edge []int) {
	f, t, c := edge[0], edge[1], edge[2]
	this.g[f][t] = c
}

func (this *Graph) ShortestPath(node1 int, node2 int) int {
	n := len(this.g)
	dist := make([]int, n)
	for i := range dist {
		dist[i] = inf
	}
	vis := make([]bool, n)
	dist[node1] = 0
	for i := 0; i < n; i++ {
		t := -1
		for j := 0; j < n; j++ {
			if !vis[j] && (t == -1 || dist[t] > dist[j]) {
				t = j
			}
		}
		vis[t] = true
		for j := 0; j < n; j++ {
			dist[j] = min(dist[j], dist[t]+this.g[t][j])
		}
	}
	if dist[node2] >= inf {
		return -1
	}
	return dist[node2]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

/**
 * Your Graph object will be instantiated and called as such:
 * obj := Constructor(n, edges);
 * obj.AddEdge(edge);
 * param_2 := obj.ShortestPath(node1,node2);
 */
```

### **TypeScript**

```ts
class Graph {
    private g: number[][] = [];
    private inf: number = 1 << 29;

    constructor(n: number, edges: number[][]) {
        this.g = Array.from({ length: n }, () => Array(n).fill(this.inf));
        for (const [f, t, c] of edges) {
            this.g[f][t] = c;
        }
    }

    addEdge(edge: number[]): void {
        const [f, t, c] = edge;
        this.g[f][t] = c;
    }

    shortestPath(node1: number, node2: number): number {
        const n = this.g.length;
        const dist: number[] = new Array(n).fill(this.inf);
        dist[node1] = 0;
        const vis: boolean[] = new Array(n).fill(false);
        for (let i = 0; i < n; ++i) {
            let t = -1;
            for (let j = 0; j < n; ++j) {
                if (!vis[j] && (t === -1 || dist[j] < dist[t])) {
                    t = j;
                }
            }
            vis[t] = true;
            for (let j = 0; j < n; ++j) {
                dist[j] = Math.min(dist[j], dist[t] + this.g[t][j]);
            }
        }
        return dist[node2] >= this.inf ? -1 : dist[node2];
    }
}

/**
 * Your Graph object will be instantiated and called as such:
 * var obj = new Graph(n, edges)
 * obj.addEdge(edge)
 * var param_2 = obj.shortestPath(node1,node2)
 */
```

### **...**

```

```

<!-- tabs:end -->
