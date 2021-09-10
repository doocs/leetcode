# [1631. Path With Minimum Effort](https://leetcode.com/problems/path-with-minimum-effort)

[中文文档](/solution/1600-1699/1631.Path%20With%20Minimum%20Effort/README.md)

## Description

<p>You are a hiker preparing for an upcoming hike. You are given <code>heights</code>, a 2D array of size <code>rows x columns</code>, where <code>heights[row][col]</code> represents the height of cell <code>(row, col)</code>. You are situated in the top-left cell, <code>(0, 0)</code>, and you hope to travel to the bottom-right cell, <code>(rows-1, columns-1)</code> (i.e.,&nbsp;<strong>0-indexed</strong>). You can move <strong>up</strong>, <strong>down</strong>, <strong>left</strong>, or <strong>right</strong>, and you wish to find a route that requires the minimum <strong>effort</strong>.</p>

<p>A route&#39;s <strong>effort</strong> is the <strong>maximum absolute difference</strong><strong> </strong>in heights between two consecutive cells of the route.</p>

<p>Return <em>the minimum <strong>effort</strong> required to travel from the top-left cell to the bottom-right cell.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1631.Path%20With%20Minimum%20Effort/images/ex1.png" style="width: 300px; height: 300px;" /></p>

<pre>
<strong>Input:</strong> heights = [[1,2,2],[3,8,2],[5,3,5]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
</pre>

<p><strong>Example 2:</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1631.Path%20With%20Minimum%20Effort/images/ex2.png" style="width: 300px; height: 300px;" /></p>

<pre>
<strong>Input:</strong> heights = [[1,2,3],[3,8,4],[5,3,5]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1631.Path%20With%20Minimum%20Effort/images/ex3.png" style="width: 300px; height: 300px;" />
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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumEffortPath(self, heights: List[List[int]]) -> int:
        m, n = len(heights), len(heights[0])
        p = list(range(m * n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        e = []
        for i in range(m):
            for j in range(n):
                if i < m - 1:
                    e.append([abs(heights[i][j] - heights[i + 1][j]), i * n + j, (i + 1) * n + j])
                if j < n - 1:
                    e.append([abs(heights[i][j] - heights[i][j + 1]), i * n + j, i * n + j + 1])
        e.sort()
        for h, i, j in e:
            p[find(i)] = find(j)
            if find(0) == find(m * n - 1):
                return h
        return 0
```

### **Java**

```java
class Solution {
    private int[] p;

    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
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

### **C++**

```cpp
class Solution {
public:
    vector<int> p;

    int minimumEffortPath(vector<vector<int>>& heights) {
        int m = heights.size(), n = heights[0].size();
        p.resize(m * n);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        vector<vector<int>> edges;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (i < m - 1) edges.push_back({abs(heights[i][j] - heights[i + 1][j]), i * n + j, (i + 1) * n + j});
                if (j < n - 1) edges.push_back({abs(heights[i][j] - heights[i][j + 1]), i * n + j, i * n + j + 1});
            }
        }
        sort(edges.begin(), edges.end());
        for (auto e : edges)
        {
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

### **Go**

```go
var p []int

func minimumEffortPath(heights [][]int) int {
	m, n := len(heights), len(heights[0])
	p = make([]int, m*n)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	var edges [][]int
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if i < m-1 {
				s := []int{abs(heights[i][j] - heights[i+1][j]), i*n + j, (i+1)*n + j}
				edges = append(edges, s)
			}
			if j < n-1 {
				s := []int{abs(heights[i][j] - heights[i][j+1]), i*n + j, i*n + j + 1}
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

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
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
