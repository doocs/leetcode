# [1102. Path With Maximum Minimum Value](https://leetcode.com/problems/path-with-maximum-minimum-value)

[中文文档](/solution/1100-1199/1102.Path%20With%20Maximum%20Minimum%20Value/README.md)

## Description

<p>Given a&nbsp;matrix of integers <code>A</code>&nbsp;with&nbsp;<font face="monospace">R</font>&nbsp;rows and <font face="monospace">C</font>&nbsp;columns, find&nbsp;the <strong>maximum</strong>&nbsp;score&nbsp;of a path starting at&nbsp;<code>[0,0]</code>&nbsp;and ending at <code>[R-1,C-1]</code>.</p>

<p>The <em>score</em> of a path is the <strong>minimum</strong> value in that path.&nbsp; For example, the value of the path 8 &rarr;&nbsp; 4 &rarr;&nbsp; 5 &rarr;&nbsp; 9 is 4.</p>

<p>A <em>path</em> moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions (north, east, west, south).</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1102.Path%20With%20Maximum%20Minimum%20Value/images/1313_ex1.jpeg" style="width: 70px; height: 59px;" /></strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">[[5,4,5],[1,2,6],[7,4,6]]</span>

<strong>Output: </strong><span id="example-output-1">4</span>

<strong>Explanation: </strong>

The path with the maximum score is highlighted in yellow. 

</pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1102.Path%20With%20Maximum%20Minimum%20Value/images/1313_ex2.jpeg" style="width: 134px; height: 39px;" /></strong></p>

<pre>

<strong>Input: </strong><span>[[2,2,1,2,2,2],[1,2,2,2,1,2]]</span>

<strong>Output: 2</strong></pre>

<p><strong>Example 3:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1102.Path%20With%20Maximum%20Minimum%20Value/images/1313_ex3.jpeg" /></strong></p>

<pre>

<strong>Input: </strong><span>[[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]</span>

<strong>Output: 3</strong></pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= R, C&nbsp;&lt;= 100</code></li>
	<li><code>0 &lt;= A[i][j] &lt;= 10^9</code></li>
</ol>

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
        while (find(0) != find(m * n - 1))
        {
            auto [score, i, j] = scores.back();
            scores.pop_back();
            ans = min(ans, score);
            vis[i][j] = true;
            for (int k = 0; k < 4; ++k)
            {
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
