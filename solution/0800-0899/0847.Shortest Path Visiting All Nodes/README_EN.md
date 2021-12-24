# [847. Shortest Path Visiting All Nodes](https://leetcode.com/problems/shortest-path-visiting-all-nodes)

[中文文档](/solution/0800-0899/0847.Shortest%20Path%20Visiting%20All%20Nodes/README.md)

## Description

<p>An undirected, connected graph of N nodes (labeled&nbsp;<code>0, 1, 2, ..., N-1</code>) is given as <code>graph</code>.</p>

<p><code>graph.length = N</code>, and <code>j != i</code>&nbsp;is in the list&nbsp;<code>graph[i]</code>&nbsp;exactly once, if and only if nodes <code>i</code> and <code>j</code> are connected.</p>

<p>Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.</p>

<p>&nbsp;</p>

<ol>

</ol>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>[[1,2,3],[0],[0],[0]]

<strong>Output: </strong>4

<strong>Explanation</strong>: One possible path is [1,0,2,0,3]</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>[[1],[0,2,4],[1,3,4],[2],[1,2]]

<strong>Output: </strong>4

<strong>Explanation</strong>: One possible path is [0,1,4,2,3]

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= graph.length &lt;= 12</code></li>
	<li><code>0 &lt;= graph[i].length &lt;&nbsp;graph.length</code></li>
</ol>

## Solutions

Because each edge has the same weight, the shortest path can be solution by using BFS, and the access of the point can be recorded by state compression.

<!-- tabs:start -->

### **Python3**

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
