# [1102. Path With Maximum Minimum Value](https://leetcode.com/problems/path-with-maximum-minimum-value)

[中文文档](/solution/1100-1199/1102.Path%20With%20Maximum%20Minimum%20Value/README.md)

## Description

<p>Given an <code>m x n</code> integer matrix <code>grid</code>, return <em>the maximum <strong>score</strong> of a path starting at </em><code>(0, 0)</code><em> and ending at </em><code>(m - 1, n - 1)</code> moving in the 4 cardinal directions.</p>

<p>The <strong>score</strong> of a path is the minimum value in that path.</p>

<ul>
	<li>For example, the score of the path <code>8 &rarr; 4 &rarr; 5 &rarr; 9</code> is <code>4</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1102.Path%20With%20Maximum%20Minimum%20Value/images/maxgrid1.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>Input:</strong> grid = [[5,4,5],[1,2,6],[7,4,6]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The path with the maximum score is highlighted in yellow. 
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1102.Path%20With%20Maximum%20Minimum%20Value/images/maxgrid2.jpg" style="width: 484px; height: 165px;" />
<pre>
<strong>Input:</strong> grid = [[2,2,1,2,2,2],[1,2,2,2,1,2]]
<strong>Output:</strong> 2
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1102.Path%20With%20Maximum%20Minimum%20Value/images/maxgrid3.jpg" style="width: 404px; height: 485px;" />
<pre>
<strong>Input:</strong> grid = [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumMinimumPath(self, grid: List[List[int]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        m, n = len(grid), len(grid[0])
        p = list(range(m * n))
        ans = min(grid[0][0], grid[-1][-1])
        vis = {(0, 0), (m - 1, n - 1)}
        scores = [[grid[i][j], i, j] for i in range(m) for j in range(n)]
        scores.sort()
        while find(0) != find(m * n - 1):
            score, i, j = scores.pop()
            ans = min(ans, score)
            vis.add((i, j))
            for a, b in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and (x, y) in vis:
                    p[find(x * n + y)] = find(i * n + j)
        return ans
```

### **Java**

```java
class Solution {
    private int[] p;

    public int maximumMinimumPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        p = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        int ans = Math.min(grid[0][0], grid[m - 1][n - 1]);
        Set<Integer> vis = new HashSet<>(Arrays.asList(0, m * n - 1));
        List<int[]> scores = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                scores.add(new int[]{grid[i][j], i, j});
            }
        }
        scores.sort(Comparator.comparingInt(a -> a[0]));
        int[] dirs = {-1, 0, 1, 0, -1};
        while (find(0) != find(m * n - 1)) {
            int[] t = scores.remove(scores.size() - 1);
            int score = t[0], i = t[1], j = t[2];
            ans = Math.min(ans, score);
            vis.add(i * n + j);
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && vis.contains(x * n + y)) {
                    p[find(x * n + y)] = find(i * n + j);
                }
            }
        }
        return ans;
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

    int maximumMinimumPath(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        p.resize(m * n);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        int ans = min(grid[0][0], grid[m - 1][n - 1]);
        vector<vector<bool>> vis(m, vector<bool>(n));
        vis[0][0] = true;
        vis[m - 1][n - 1] = true;
        vector<tuple<int, int, int>> scores;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                scores.emplace_back(grid[i][j], i, j);
        sort(scores.begin(), scores.end());
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (find(0) != find(m * n - 1)) {
            auto [score, i, j] = scores.back();
            scores.pop_back();
            ans = min(ans, score);
            vis[i][j] = true;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && vis[x][y])
                    p[find(x * n + y)] = find(i * n + j);
            }
        }
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
func maximumMinimumPath(grid [][]int) int {
	m, n := len(grid), len(grid[0])
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
	vis := make([][]bool, m)
	var scores [][]int
	for i := range vis {
		vis[i] = make([]bool, n)
		for j := range grid[i] {
			scores = append(scores, []int{grid[i][j], i, j})
		}
	}
	sort.Slice(scores, func(i, j int) bool {
		return scores[i][0] > scores[j][0]
	})
	vis[0][0] = true
	vis[m-1][n-1] = true
	dirs := []int{-1, 0, 1, 0, -1}
	ans := min(grid[0][0], grid[m-1][n-1])
	for find(0) != find(m*n-1) {
		t := scores[0]
		scores = scores[1:]
		score, i, j := t[0], t[1], t[2]
		vis[i][j] = true
		ans = min(ans, score)
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && vis[x][y] {
				p[find(x*n+y)] = find(i*n + j)
			}
		}
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

### **...**

```

```

<!-- tabs:end -->
