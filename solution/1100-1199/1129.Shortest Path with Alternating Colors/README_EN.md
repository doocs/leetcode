---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1129.Shortest%20Path%20with%20Alternating%20Colors/README_EN.md
rating: 1779
source: Weekly Contest 146 Q2
tags:
    - Breadth-First Search
    - Graph
---

<!-- problem:start -->

# [1129. Shortest Path with Alternating Colors](https://leetcode.com/problems/shortest-path-with-alternating-colors)

[中文文档](/solution/1100-1199/1129.Shortest%20Path%20with%20Alternating%20Colors/README.md)

## Description

<p>You are given an integer <code>n</code>, the number of nodes in a directed graph where the nodes are labeled from <code>0</code> to <code>n - 1</code>. Each edge is red or blue in this graph, and there could be self-edges and parallel edges.</p>

<p>You are given two arrays <code>redEdges</code> and <code>blueEdges</code> where:</p>

<ul>
	<li><code>redEdges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is a directed red edge from node <code>a<sub>i</sub></code> to node <code>b<sub>i</sub></code> in the graph, and</li>
	<li><code>blueEdges[j] = [u<sub>j</sub>, v<sub>j</sub>]</code> indicates that there is a directed blue edge from node <code>u<sub>j</sub></code> to node <code>v<sub>j</sub></code> in the graph.</li>
</ul>

<p>Return an array <code>answer</code> of length <code>n</code>, where each <code>answer[x]</code> is the length of the shortest path from node <code>0</code> to node <code>x</code> such that the edge colors alternate along the path, or <code>-1</code> if such a path does not exist.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
<strong>Output:</strong> [0,1,-1]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
<strong>Output:</strong> [0,1,-1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= redEdges.length,&nbsp;blueEdges.length &lt;= 400</code></li>
	<li><code>redEdges[i].length == blueEdges[j].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub>, u<sub>j</sub>, v<sub>j</sub> &lt; n</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: BFS

The problem is essentially a shortest path problem, which we can consider solving using BFS.

First, we preprocess all the edges, categorizing all the edges by color and storing them in a multi-dimensional array $g$. Where $g[0]$ stores all red edges, and $g[1]$ stores all blue edges.

Next, we define the following data structures or variables:

-   Queue $q$: used to store the currently searched node and the color of the current edge;
-   Set $vis$: used to store the nodes that have been searched and the color of the current edge;
-   Variable $d$: used to represent the current search level, i.e., the distance from the currently searched node to the starting point;
-   Array $ans$: used to store the shortest distance from each node to the starting point. Initially, we initialize all elements in the $ans$ array to $-1$, indicating that the distance from all nodes to the starting point is unknown.

We first enqueue the starting point $0$ and the color of the starting edge $0$ or $1$, indicating that we start from the starting point and the current edge is red or blue.

Next, we start the BFS search. Each time we take out a node $(i, c)$ from the queue, if the answer of the current node has not been updated, then we update the answer of the current node to the current level $d$, i.e., $ans[i] = d$. Then, we flip the color of the current edge $c$, i.e., if the current edge is red, we change it to blue, and vice versa. We take out all edges corresponding to the color, if the other end node $j$ of the edge has not been searched, then we enqueue it.

After the search is over, return the answer array.

The time complexity is $O(n + m)$, and the space complexity is $O(n + m)$. Here, $n$ and $m$ are the number of nodes and edges, respectively.

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
