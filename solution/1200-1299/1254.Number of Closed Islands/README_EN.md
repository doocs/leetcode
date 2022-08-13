# [1254. Number of Closed Islands](https://leetcode.com/problems/number-of-closed-islands)

[中文文档](/solution/1200-1299/1254.Number%20of%20Closed%20Islands/README.md)

## Description

<p>Given a 2D&nbsp;<code>grid</code> consists of <code>0s</code> (land)&nbsp;and <code>1s</code> (water).&nbsp; An <em>island</em> is a maximal 4-directionally connected group of <code><font face="monospace">0</font>s</code> and a <em>closed island</em>&nbsp;is an island <strong>totally</strong>&nbsp;(all left, top, right, bottom) surrounded by <code>1s.</code></p>

<p>Return the number of <em>closed islands</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1254.Number%20of%20Closed%20Islands/images/sample_3_1610.png" style="width: 240px; height: 120px;" /></p>

<pre>
<strong>Input:</strong> grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
Islands in gray are closed because they are completely surrounded by water (group of 1s).</pre>

<p><strong>Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1254.Number%20of%20Closed%20Islands/images/sample_4_1610.png" style="width: 160px; height: 80px;" /></p>

<pre>
<strong>Input:</strong> grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
<strong>Output:</strong> 1
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,1,1,1,1,1,1],
&nbsp;              [1,0,0,0,0,0,1],
&nbsp;              [1,0,1,1,1,0,1],
&nbsp;              [1,0,1,0,1,0,1],
&nbsp;              [1,0,1,1,1,0,1],
&nbsp;              [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= grid.length, grid[0].length &lt;= 100</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;=1</code></li>
</ul>

## Solutions

Union find.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def closedIsland(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        p = list(range(m * n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    continue
                idx = i * n + j
                if i < m - 1 and grid[i + 1][j] == 0:
                    p[find(idx)] = find((i + 1) * n + j)
                if j < n - 1 and grid[i][j + 1] == 0:
                    p[find(idx)] = find(i * n + j + 1)

        s = [0] * (m * n)
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 0:
                    s[find(i * n + j)] = 1
        for i in range(m):
            for j in range(n):
                root = find(i * n + j)
                if not s[root]:
                    continue
                if i == 0 or i == m - 1 or j == 0 or j == n - 1:
                    s[root] = 0
        return sum(s)
```

### **Java**

```java
class Solution {
    private int[] p;

    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        p = new int[m * n];
        for (int i = 0; i < m * n; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    continue;
                }
                int idx = i * n + j;
                if (i < m - 1 && grid[i + 1][j] == 0) {
                    p[find(idx)] = find((i + 1) * n + j);
                }
                if (j < n - 1 && grid[i][j + 1] == 0) {
                    p[find(idx)] = find(i * n + j + 1);
                }
            }
        }
        boolean[] s = new boolean[m * n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    s[find(i * n + j)] = true;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int root = find(i * n + j);
                if (!s[root]) {
                    continue;
                }
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    s[root] = false;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m * n; ++i) {
            if (s[i]) {
                ++res;
            }
        }
        return res;
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

    int closedIsland(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        p.resize(m * n);
        for (int i = 0; i < m * n; ++i) p[i] = i;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) continue;
                int idx = i * n + j;
                if (i < m - 1 && grid[i + 1][j] == 0) p[find(idx)] = find((i + 1) * n + j);
                if (j < n - 1 && grid[i][j + 1] == 0) p[find(idx)] = find(i * n + j + 1);
            }
        }
        vector<bool> s(m * n, false);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) s[find(i * n + j)] = true;
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int root = find(i * n + j);
                if (!s[root]) continue;
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) s[root] = false;
            }
        }
        int res = 0;
        for (auto e : s) {
            if (e) ++res;
        }
        return res;
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

func closedIsland(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	p = make([]int, m*n)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				continue
			}
			idx := i*n + j
			if i < m-1 && grid[i+1][j] == 0 {
				p[find(idx)] = find((i+1)*n + j)
			}
			if j < n-1 && grid[i][j+1] == 0 {
				p[find(idx)] = find(i*n + j + 1)
			}
		}
	}
	s := make([]bool, m*n)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 0 {
				s[find(i*n+j)] = true
			}
		}
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			root := find(i*n + j)
			if !s[root] {
				continue
			}
			if i == 0 || i == m-1 || j == 0 || j == n-1 {
				s[root] = false
			}
		}
	}
	res := 0
	for _, e := range s {
		if e {
			res++
		}
	}
	return res
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
