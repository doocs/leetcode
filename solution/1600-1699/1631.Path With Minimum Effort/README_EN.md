# [1631. Path With Minimum Effort](https://leetcode.com/problems/path-with-minimum-effort)

[中文文档](/solution/1600-1699/1631.Path%20With%20Minimum%20Effort/README.md)

## Description

<p>You are a hiker preparing for an upcoming hike. You are given <code>heights</code>, a 2D array of size <code>rows x columns</code>, where <code>heights[row][col]</code> represents the height of cell <code>(row, col)</code>. You are situated in the top-left cell, <code>(0, 0)</code>, and you hope to travel to the bottom-right cell, <code>(rows-1, columns-1)</code> (i.e.,&nbsp;<strong>0-indexed</strong>). You can move <strong>up</strong>, <strong>down</strong>, <strong>left</strong>, or <strong>right</strong>, and you wish to find a route that requires the minimum <strong>effort</strong>.</p>

<p>A route&#39;s <strong>effort</strong> is the <strong>maximum absolute difference</strong><strong> </strong>in heights between two consecutive cells of the route.</p>

<p>Return <em>the minimum <strong>effort</strong> required to travel from the top-left cell to the bottom-right cell.</em></p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1631.Path%20With%20Minimum%20Effort/images/ex1.png" style="width: 300px; height: 300px;" /></p>

<pre>

<strong>Input:</strong> heights = [[1,2,2],[3,8,2],[5,3,5]]

<strong>Output:</strong> 2

<strong>Explanation:</strong> The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.

This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.

</pre>

<p><strong>Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1631.Path%20With%20Minimum%20Effort/images/ex2.png" style="width: 300px; height: 300px;" /></p>

<pre>

<strong>Input:</strong> heights = [[1,2,3],[3,8,4],[5,3,5]]

<strong>Output:</strong> 1

<strong>Explanation:</strong> The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].

</pre>

<p><strong>Example 3:</strong></p>

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1631.Path%20With%20Minimum%20Effort/images/ex3.png" style="width: 300px; height: 300px;" />

<pre>

<strong>Input:</strong> heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]

<strong>Output:</strong> 0

<strong>Explanation:</strong> This route does not require any effort.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>rows == heights.length</code></li>
    <li><code>columns == heights[i].length</code></li>
    <li><code>1 &lt;= rows, columns &lt;= 100</code></li>
    <li><code>1 &lt;= heights[i][j] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

`Union find` or `Binary search + BFS`.

<!-- tabs:start -->

### **Python3**

Union find:

```python
class Solution:
    def minimumEffortPath(self, heights: List[List[int]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        m, n = len(heights), len(heights[0])
        p = list(range(m * n))
        e = []
        for i in range(m):
            for j in range(n):
                if i < m - 1:
                    e.append(
                        (
                            abs(heights[i][j] - heights[i + 1][j]),
                            i * n + j,
                            (i + 1) * n + j,
                        )
                    )
                if j < n - 1:
                    e.append(
                        (
                            abs(heights[i][j] - heights[i][j + 1]),
                            i * n + j,
                            i * n + j + 1,
                        )
                    )
        e.sort()
        for h, i, j in e:
            p[find(i)] = find(j)
            if find(0) == find(m * n - 1):
                return h
        return 0
```

Binary search + BFS:

```python
class Solution:
    def minimumEffortPath(self, heights: List[List[int]]) -> int:
        m, n = len(heights), len(heights[0])
        left, right = 0, 999999
        while left < right:
            mid = (left + right) >> 1
            q = deque([(0, 0)])
            vis = {(0, 0)}
            while q:
                i, j = q.popleft()
                for a, b in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and (x, y) not in vis and abs(heights[i][j] - heights[x][y]) <= mid:
                        q.append((x, y))
                        vis.add((x, y))
            if (m - 1, n - 1) in vis:
                right = mid
            else:
                left = mid + 1
        return left
```

```python
class Solution:
    def minimumEffortPath(self, heights: List[List[int]]) -> int:
        INF = 0x3f3f3f3f
        m, n = len(heights), len(heights[0])
        dist = [[INF] * n for _ in range(m)]
        dist[0][0] = 0
        dirs = [-1, 0, 1, 0, -1]
        q = [(0, 0, 0)]
        while q:
            t, i, j = heappop(q)
            for k in range(4):
                x, y = i + dirs[k], j + dirs[k + 1]
                if 0 <= x < m and 0 <= y < n and max(t, abs(heights[x][y] - heights[i][j])) < dist[x][y]:
                    dist[x][y] = max(t, abs(heights[x][y] - heights[i][j]))
                    heappush(q, (dist[x][y], x, y))
        return dist[-1][-1]
```

### **Java**

Union find:

```java
class Solution {
    private int[] p;

    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        p = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i < m - 1) {
                    edges.add(new int[]{Math.abs(heights[i][j] - heights[i + 1][j]), i * n + j, (i + 1) * n + j});
                }
                if (j < n - 1) {
                    edges.add(new int[]{Math.abs(heights[i][j] - heights[i][j + 1]), i * n + j, i * n + j + 1});
                }
            }
        }
        Collections.sort(edges, Comparator.comparingInt(a -> a[0]));
        for (int[] e : edges) {
            int i = e[1], j = e[2];
            p[find(i)] = find(j);
            if (find(0) == find(m * n - 1)) {
                return e[0];
            }
        }
        return 0;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

Binary search + BFS:

```java
class Solution {
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int left = 0;
        int right = 999999;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (left < right) {
            int mid = (left + right) >> 1;
            boolean[][] vis = new boolean[m][n];
            vis[0][0] = true;
            Deque<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{0, 0});
            while (!q.isEmpty()) {
                int[] p = q.poll();
                int i = p[0], j = p[1];
                for (int k = 0; k < 4; ++k) {
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] && Math.abs(heights[i][j] - heights[x][y]) <= mid) {
                        q.offer(new int[]{x, y});
                        vis[x][y] = true;
                    }
                }
            }
            if (vis[m - 1][n - 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

```java
class Solution {
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dist[i], 0x3f3f3f3f);
        }
        dist[0][0] = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        q.offer(new int[]{0, 0, 0});
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int t = p[0], i = p[1], j = p[2];
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    int nd = Math.max(t, Math.abs(heights[x][y] - heights[i][j]));
                    if (nd < dist[x][y]) {
                        dist[x][y] = nd;
                        q.offer(new int[]{nd, x, y});
                    }
                }
            }
        }
        return dist[m - 1][n - 1];
    }
}
```

### **C++**

Union find:

```cpp
class Solution {
public:
    vector<int> p;

    int minimumEffortPath(vector<vector<int>>& heights) {
        int m = heights.size(), n = heights[0].size();
        p.resize(m * n);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        vector<vector<int>> edges;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i < m - 1) edges.push_back({abs(heights[i][j] - heights[i + 1][j]), i * n + j, (i + 1) * n + j});
                if (j < n - 1) edges.push_back({abs(heights[i][j] - heights[i][j + 1]), i * n + j, i * n + j + 1});
            }
        }
        sort(edges.begin(), edges.end());
        for (auto& e : edges) {
            int i = e[1], j = e[2];
            p[find(i)] = find(j);
            if (find(0) == find(m * n - 1)) return e[0];
        }
        return 0;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

Binary search + BFS:

```cpp
class Solution {
public:
    int minimumEffortPath(vector<vector<int>>& heights) {
        int m = heights.size(), n = heights[0].size();
        int left = 0, right = 999999;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (left < right)
        {
            int mid = (left + right) >> 1;
            vector<vector<bool>> vis(m, vector<bool>(n));
            vis[0][0] = true;
            queue<pair<int, int>> q;
            q.push({0, 0});
            while (!q.empty())
            {
                auto [i, j] = q.front();
                q.pop();
                for (int k = 0; k < 4; ++k)
                {
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] && abs(heights[i][j] - heights[x][y]) <= mid)
                    {
                        q.push({x, y});
                        vis[x][y] = true;
                    }
                }
            }
            if (vis[m - 1][n - 1]) right = mid;
            else left = mid + 1;
        }
        return left;
    }
};
```

```cpp
class Solution {
public:
    int minimumEffortPath(vector<vector<int>>& heights) {
        int m = heights.size(), n = heights[0].size();
        vector<vector<int>> dist(m, vector<int>(n, 0x3f3f3f3f));
        dist[0][0] = 0;
        priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<tuple<int, int, int>>> q;
        q.push({0, 0, 0});
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty())
        {
            auto [t, i, j] = q.top();
            q.pop();
            for (int k = 0; k < 4; ++k)
            {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n)
                {
                    int nd = max(t, abs(heights[x][y] - heights[i][j]));
                    if (nd < dist[x][y])
                    {
                        dist[x][y] = nd;
                        q.push({nd, x, y});
                    }
                }
            }
        }
        return dist[m - 1][n - 1];
    }
};
```

### **Go**

Union find:

```go
func minimumEffortPath(heights [][]int) int {
	m, n := len(heights), len(heights[0])
	p := make([]int, m*n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	edges := [][]int{}
	for i, row := range heights {
		for j, h := range row {
			if i < m-1 {
				s := []int{abs(h - heights[i+1][j]), i*n + j, (i+1)*n + j}
				edges = append(edges, s)
			}
			if j < n-1 {
				s := []int{abs(h - row[j+1]), i*n + j, i*n + j + 1}
				edges = append(edges, s)
			}
		}
	}
	sort.Slice(edges, func(i, j int) bool {
		return edges[i][0] < edges[j][0]
	})
	for _, e := range edges {
		i, j := e[1], e[2]
		p[find(i)] = find(j)
		if find(0) == find(m*n-1) {
			return e[0]
		}
	}
	return 0
}

func abs(x int) int {
	if x > 0 {
		return x
	}
	return -x
}
```

Binary search + BFS:

```go
func minimumEffortPath(heights [][]int) int {
	m, n := len(heights), len(heights[0])
	left, right := 0, 999999
	dirs := []int{-1, 0, 1, 0, -1}
	for left < right {
		mid := (left + right) >> 1
		vis := make([][]bool, m)
		for i := range vis {
			vis[i] = make([]bool, n)
		}
		vis[0][0] = true
		q := [][]int{{0, 0}}
		for len(q) > 0 {
			p := q[0]
			q = q[1:]
			i, j := p[0], p[1]
			for k := 0; k < 4; k++ {
				x, y := i+dirs[k], j+dirs[k+1]
				if x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] && abs(heights[i][j]-heights[x][y]) <= mid {
					q = append(q, []int{x, y})
					vis[x][y] = true
				}
			}
		}
		if vis[m-1][n-1] {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}

func abs(x int) int {
	if x > 0 {
		return x
	}
	return -x
}
```

### **...**

```

```

<!-- tabs:end -->
