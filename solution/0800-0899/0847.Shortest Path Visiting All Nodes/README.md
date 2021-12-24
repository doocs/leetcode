# [847. 访问所有节点的最短路径](https://leetcode-cn.com/problems/shortest-path-visiting-all-nodes)

[English Version](/solution/0800-0899/0847.Shortest%20Path%20Visiting%20All%20Nodes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出&nbsp;<code>graph</code>&nbsp;为有 N 个节点（编号为&nbsp;<code>0, 1, 2, ..., N-1</code>）的无向连通图。&nbsp;</p>

<p><code>graph.length = N</code>，且只有节点 <code>i</code>&nbsp;和 <code>j</code>&nbsp;连通时，<code>j != i</code>&nbsp;在列表&nbsp;<code>graph[i]</code>&nbsp;中恰好出现一次。</p>

<p>返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[[1,2,3],[0],[0],[0]]
<strong>输出：</strong>4
<strong>解释：</strong>一个可能的路径为 [1,0,2,0,3]</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[[1],[0,2,4],[1,3,4],[2],[1,2]]
<strong>输出：</strong>4
<strong>解释：</strong>一个可能的路径为 [0,1,4,2,3]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= graph.length &lt;= 12</code></li>
	<li><code>0 &lt;= graph[i].length &lt;&nbsp;graph.length</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

因为每条边权值一样，所以用 BFS 就能得出最短路径，过程中可以用状态压缩记录节点的访问情况

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

### **...**

```

```

<!-- tabs:end -->
