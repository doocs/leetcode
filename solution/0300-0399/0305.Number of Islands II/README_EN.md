# [305. Number of Islands II](https://leetcode.com/problems/number-of-islands-ii)

[中文文档](/solution/0300-0399/0305.Number%20of%20Islands%20II/README.md)

## Description

<p>You are given an empty 2D binary grid <code>grid</code> of size <code>m x n</code>. The grid represents a map where <code>0</code>&#39;s represent water and <code>1</code>&#39;s represent land. Initially, all the cells of <code>grid</code> are water cells (i.e., all the cells are <code>0</code>&#39;s).</p>

<p>We may perform an add land operation which turns the water at position into a land. You are given an array <code>positions</code> where <code>positions[i] = [r<sub>i</sub>, c<sub>i</sub>]</code> is the position <code>(r<sub>i</sub>, c<sub>i</sub>)</code> at which we should operate the <code>i<sup>th</sup></code> operation.</p>

<p>Return <em>an array of integers</em> <code>answer</code> <em>where</em> <code>answer[i]</code> <em>is the number of islands after turning the cell</em> <code>(r<sub>i</sub>, c<sub>i</sub>)</code> <em>into a land</em>.</p>

<p>An <strong>island</strong> is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0305.Number%20of%20Islands%20II/images/tmp-grid.jpg" style="width: 500px; height: 294px;" />
<pre>
<strong>Input:</strong> m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
<strong>Output:</strong> [1,1,2,3]
<strong>Explanation:</strong>
Initially, the 2d grid is filled with water.
- Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land. We have 1 island.
- Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land. We still have 1 island.
- Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land. We have 2 islands.
- Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land. We have 3 islands.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> m = 1, n = 1, positions = [[0,0]]
<strong>Output:</strong> [1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n, positions.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>4</sup></code></li>
	<li><code>positions[i].length == 2</code></li>
	<li><code>0 &lt;= r<sub>i</sub> &lt; m</code></li>
	<li><code>0 &lt;= c<sub>i</sub> &lt; n</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you solve it in time complexity <code>O(k log(mn))</code>, where <code>k == positions.length</code>?</p>

## Solutions

Union find.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numIslands2(self, m: int, n: int, positions: List[List[int]]) -> List[int]:
        p = list(range(m * n))
        grid = [[0] * n for _ in range(m)]

        def check(i, j):
            return 0 <= i < m and 0 <= j < n and grid[i][j] == 1

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        res = []
        cur = 0
        for i, j in positions:
            if grid[i][j] == 1:
                res.append(cur)
                continue
            grid[i][j] = 1
            cur += 1
            for x, y in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                if check(i + x, j + y) and find(i * n + j) != find((i + x) * n + j + y):
                    p[find(i * n + j)] = find((i + x) * n + j + y)
                    cur -= 1
            res.append(cur)
        return res
```

### **Java**

```java
class Solution {
    private int[] p;
    private int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    private int[][] grid;
    private int m;
    private int n;

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        p = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        grid = new int[m][n];
        this.m = m;
        this.n = n;
        List<Integer> res = new ArrayList<>();
        int cur = 0;
        for (int[] position : positions) {
            int i = position[0], j = position[1];
            if (grid[i][j] == 1) {
                res.add(cur);
                continue;
            }
            grid[i][j] = 1;
            ++cur;
            for (int[] e : dirs) {
                if (check(i + e[0], j + e[1]) && find(i * n + j) != find((i + e[0]) * n + j + e[1])) {
                    p[find(i * n + j)] = find((i + e[0]) * n + j + e[1]);
                    --cur;
                }
            }
            res.add(cur);
        }
        return res;
    }

    private boolean check(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == 1;
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
    int dirs[4][2] = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    vector<int> numIslands2(int m, int n, vector<vector<int>>& positions) {
        p.resize(m * n);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        vector<vector<int>> grid(m, vector<int>(n, 0));
        vector<int> res;
        int cur = 0;
        for (auto position : positions)
        {
            int i = position[0], j = position[1];
            if (grid[i][j] == 1)
            {
                res.push_back(cur);
                continue;
            }
            grid[i][j] = 1;
            ++cur;
            for (auto e : dirs) {
                if (check(i + e[0], j + e[1], grid) && find(i * n + j) != find((i + e[0]) * n + j + e[1]))
                {
                    p[find(i * n + j)] = find((i + e[0]) * n + j + e[1]);
                    --cur;
                }
            }
            res.push_back(cur);
        }
        return res;
    }

    bool check(int i, int j, vector<vector<int>>& grid) {
        return i >= 0 && i < grid.size() && j >= 0 && j < grid[0].size() && grid[i][j] == 1;
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

func numIslands2(m int, n int, positions [][]int) []int {
	p = make([]int, m*n)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	grid := make([][]int, m)
	for i := 0; i < m; i++ {
		grid[i] = make([]int, n)
	}
	var res []int
	cur := 0
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for _, position := range positions {
		i, j := position[0], position[1]
		if grid[i][j] == 1 {
			res = append(res, cur)
			continue
		}
		grid[i][j] = 1
		cur++
		for _, e := range dirs {
			if check(i+e[0], j+e[1], grid) && find(i*n+j) != find((i+e[0])*n+j+e[1]) {
				p[find(i*n+j)] = find((i+e[0])*n + j + e[1])
				cur--
			}
		}
		res = append(res, cur)
	}
	return res
}

func check(i, j int, grid [][]int) bool {
	return i >= 0 && i < len(grid) && j >= 0 && j < len(grid[0]) && grid[i][j] == 1
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}
```

### **...**

```

```

<!-- tabs:end -->
