# [847. 访问所有节点的最短路径](https://leetcode.cn/problems/shortest-path-visiting-all-nodes)

[English Version](/solution/0800-0899/0847.Shortest%20Path%20Visiting%20All%20Nodes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>存在一个由 <code>n</code> 个节点组成的无向连通图，图中的节点按从 <code>0</code> 到 <code>n - 1</code> 编号。</p>

<p>给你一个数组 <code>graph</code> 表示这个图。其中，<code>graph[i]</code> 是一个列表，由所有与节点 <code>i</code> 直接相连的节点组成。</p>

<p>返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0847.Shortest%20Path%20Visiting%20All%20Nodes/images/shortest1-graph.jpg" style="width: 222px; height: 183px;" />
<pre>
<strong>输入：</strong>graph = [[1,2,3],[0],[0],[0]]
<strong>输出：</strong>4
<strong>解释：</strong>一种可能的路径为 [1,0,2,0,3]</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0847.Shortest%20Path%20Visiting%20All%20Nodes/images/shortest2-graph.jpg" style="width: 382px; height: 222px;" /></p>

<pre>
<strong>输入：</strong>graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
<strong>输出：</strong>4
<strong>解释：</strong>一种可能的路径为 [0,1,4,2,3]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == graph.length</code></li>
	<li><code>1 &lt;= n &lt;= 12</code></li>
	<li><code>0 &lt;= graph[i].length &lt;&nbsp;n</code></li>
	<li><code>graph[i]</code> 不包含 <code>i</code></li>
	<li>如果 <code>graph[a]</code> 包含 <code>b</code> ，那么 <code>graph[b]</code> 也包含 <code>a</code></li>
	<li>输入的图总是连通图</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS(A\* 算法)**

因为每条边权值一样，所以用 BFS 就能得出最短路径，过程中可以用**状态压缩**记录节点的访问情况。另外，同一个节点 u 以及对应的节点访问情况需要保证只被搜索过一次，因此可以用 `vis(u, state)` 表示是否已经被搜索过，防止无效的重复搜索。

本题也属于 BFS 最小步数模型，可以使用 A\* 算法优化搜索。

A\* 算法主要思想如下：

1. 将 BFS 队列转换为优先队列（小根堆）；
1. 队列中的每个元素为 `(dist[state] + f(state), state)`，`dist[state]` 表示从起点到当前 state 的距离，`f(state)` 表示从当前 state 到终点的估计距离，这两个距离之和作为堆排序的依据；
1. 当终点第一次出队时，说明找到了从起点到终点的最短路径，直接返回对应的 step；
1. `f(state)` 是估价函数，并且估价函数要满足 `f(state) <= g(state)`，其中 `g(state)` 表示 state 到终点的真实距离；
1. A\* 算法只能保证终点第一次出队时，即找到了一条从起点到终点的最小路径，不能保证其他点出队时也是从起点到当前点的最短路径。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shortestPathLength(self, graph: List[List[int]]) -> int:
        n = len(graph)
        dst = -1 ^ (-1 << n)

        q = deque()
        vis = [[False] * (1 << n) for _ in range(n)]
        for i in range(n):
            q.append((i, 1 << i, 0))
            vis[i][1 << i] = True

        while q:
            u, state, dis = q.popleft()
            for v in graph[u]:
                nxt = state | (1 << v)
                if nxt == dst:
                    return dis + 1
                if not vis[v][nxt]:
                    q.append((v, nxt, dis + 1))
                    vis[v][nxt] = True
        return 0
```

A\* 算法：

```python
class Solution:
    def shortestPathLength(self, graph: List[List[int]]) -> int:
        n = len(graph)

        def f(state):
            return sum(((state >> i) & 1) == 0 for i in range(n))

        q = []
        dist = [[inf] * (1 << n) for _ in range(n)]
        for i in range(n):
            heappush(q, (f(1 << i), i, 1 << i))
            dist[i][1 << i] = 0
        while q:
            _, u, state = heappop(q)
            if state == (1 << n) - 1:
                return dist[u][state]
            for v in graph[u]:
                nxt = state | (1 << v)
                if dist[v][nxt] > dist[u][state] + 1:
                    dist[v][nxt] = dist[u][state] + 1
                    heappush(q, (dist[v][nxt] + f(nxt), v, nxt))
        return 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int dst = -1 ^ (-1 << n);

        Queue<Tuple> queue = new ArrayDeque<>();
        boolean[][] vis = new boolean[n][1 << n];
        for (int i = 0; i < n; i++) {
            queue.offer(new Tuple(i, 1 << i, 0));
            vis[i][1 << i] = true;
        }

        while (!queue.isEmpty()) {
            Tuple t = queue.poll();
            int u = t.u, state = t.state, dis = t.dis;
            for (int v : graph[u]) {
                int next = state | (1 << v);
                if (next == dst) {
                    return dis + 1;
                }
                if (!vis[v][next]) {
                    queue.offer(new Tuple(v, next, dis + 1));
                    vis[v][next] = true;
                }
            }
        }
        return 0;
    }

    private static class Tuple {
        int u;
        int state;
        int dis;

        public Tuple(int u, int state, int dis) {
            this.u = u;
            this.state = state;
            this.dis = dis;
        }
    }
}
```

A\* 算法：

```java
class Solution {
    private int n;

    public int shortestPathLength(int[][] graph) {
        n = graph.length;
        int[][] dist = new int[n][1 << n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < n; ++i) {
            q.offer(new int[] {f(1 << i), i, 1 << i});
            dist[i][1 << i] = 0;
        }
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int u = p[1], state = p[2];
            if (state == (1 << n) - 1) {
                return dist[u][state];
            }
            for (int v : graph[u]) {
                int nxt = state | (1 << v);
                if (dist[v][nxt] > dist[u][state] + 1) {
                    dist[v][nxt] = dist[u][state] + 1;
                    q.offer(new int[] {dist[v][nxt] + f(nxt), v, nxt});
                }
            }
        }
        return 0;
    }

    private int f(int state) {
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (((state >> i) & 1) == 0) {
                ++ans;
            }
        }
        return ans;
    }
}
```

### **Go**

```go
type tuple struct {
	u     int
	state int
	dis   int
}

func shortestPathLength(graph [][]int) int {
	n := len(graph)
	dst := -1 ^ (-1 << n)

	q := make([]tuple, 0)
	vis := make([][]bool, n)
	for i := 0; i < n; i++ {
		vis[i] = make([]bool, 1<<n)
		q = append(q, tuple{i, 1 << i, 0})
		vis[i][1<<i] = true
	}

	for len(q) > 0 {
		t := q[0]
		q = q[1:]
		cur, state, dis := t.u, t.state, t.dis
		for _, v := range graph[cur] {
			next := state | (1 << v)
			if next == dst {
				return dis + 1
			}
			if !vis[v][next] {
				q = append(q, tuple{v, next, dis + 1})
				vis[v][next] = true
			}
		}
	}
	return 0
}
```

### **C++**

```cpp
class Solution {
public:
    int shortestPathLength(vector<vector<int>>& graph) {
        int n = graph.size();
        queue<tuple<int, int, int>> q;
        vector<vector<bool>> vis(n, vector<bool>(1 << n));
        for (int i = 0; i < n; ++i) {
            q.emplace(i, 1 << i, 0);
            vis[i][1 << i] = true;
        }
        while (!q.empty()) {
            auto [u, state, dist] = q.front();
            q.pop();
            if (state == (1 << n) - 1) return dist;
            for (int& v : graph[u]) {
                int nxt = state | (1 << v);
                if (!vis[v][nxt]) {
                    q.emplace(v, nxt, dist + 1);
                    vis[v][nxt] = true;
                }
            }
        }
        return 0;
    }
};
```

A\* 算法：

```cpp
class Solution {
public:
    int n;

    int shortestPathLength(vector<vector<int>>& graph) {
        n = graph.size();
        priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<tuple<int, int, int>>> q;
        vector<vector<int>> dist(n, vector<int>(1 << n, INT_MAX));
        for (int i = 0; i < n; ++i)
        {
            q.push({f(1 << i), i, 1 << i});
            dist[i][1 << i] = 0;
        }
        while (!q.empty())
        {
            auto [_, u, state] = q.top();
            q.pop();
            if (state == (1 << n) - 1) return dist[u][state];
            for (int v : graph[u])
            {
                int nxt = state | (1 << v);
                if (dist[v][nxt] > dist[u][state] + 1)
                {
                    dist[v][nxt] = dist[u][state] + 1;
                    q.push({dist[v][nxt] + f(nxt), v, nxt});
                }
            }
        }
        return 0;
    }

    int f(int state) {
        int ans = 0;
        for (int i = 0; i < n; ++i)
            if (((state >> i) & 1) == 0)
                ++ans;
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
