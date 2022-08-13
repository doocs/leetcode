# [361. Bomb Enemy](https://leetcode.com/problems/bomb-enemy)

[中文文档](/solution/0300-0399/0361.Bomb%20Enemy/README.md)

## Description

<p>Given an <code>m x n</code> matrix <code>grid</code> where each cell is either a wall <code>&#39;W&#39;</code>, an enemy <code>&#39;E&#39;</code> or empty <code>&#39;0&#39;</code>, return <em>the maximum enemies you can kill using one bomb</em>. You can only place the bomb in an empty cell.</p>

<p>The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since it is too strong to be destroyed.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0361.Bomb%20Enemy/images/bomb1-grid.jpg" style="width: 600px; height: 187px;" />
<pre>
<strong>Input:</strong> grid = [[&quot;0&quot;,&quot;E&quot;,&quot;0&quot;,&quot;0&quot;],[&quot;E&quot;,&quot;0&quot;,&quot;W&quot;,&quot;E&quot;],[&quot;0&quot;,&quot;E&quot;,&quot;0&quot;,&quot;0&quot;]]
<strong>Output:</strong> 3
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0361.Bomb%20Enemy/images/bomb2-grid.jpg" style="width: 500px; height: 194px;" />
<pre>
<strong>Input:</strong> grid = [[&quot;W&quot;,&quot;W&quot;,&quot;W&quot;],[&quot;0&quot;,&quot;0&quot;,&quot;0&quot;],[&quot;E&quot;,&quot;E&quot;,&quot;E&quot;]]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>grid[i][j]</code> is either <code>&#39;W&#39;</code>, <code>&#39;E&#39;</code>, or <code>&#39;0&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxKilledEnemies(self, grid: List[List[str]]) -> int:
        m, n = len(grid), len(grid[0])
        g = [[0] * n for _ in range(m)]
        for i in range(m):
            t = 0
            for j in range(n):
                if grid[i][j] == 'W':
                    t = 0
                elif grid[i][j] == 'E':
                    t += 1
                g[i][j] += t
            t = 0
            for j in range(n - 1, -1, -1):
                if grid[i][j] == 'W':
                    t = 0
                elif grid[i][j] == 'E':
                    t += 1
                g[i][j] += t
        for j in range(n):
            t = 0
            for i in range(m):
                if grid[i][j] == 'W':
                    t = 0
                elif grid[i][j] == 'E':
                    t += 1
                g[i][j] += t
            t = 0
            for i in range(m - 1, -1, -1):
                if grid[i][j] == 'W':
                    t = 0
                elif grid[i][j] == 'E':
                    t += 1
                g[i][j] += t
        return max(
            [g[i][j] for i in range(m) for j in range(n) if grid[i][j] == '0'],
            default=0,
        )
```

### **Java**

```java
class Solution {
    public int maxKilledEnemies(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] g = new int[m][n];
        for (int i = 0; i < m; ++i) {
            int t = 0;
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 'W') {
                    t = 0;
                } else if (grid[i][j] == 'E') {
                    ++t;
                }
                g[i][j] += t;
            }
            t = 0;
            for (int j = n - 1; j >= 0; --j) {
                if (grid[i][j] == 'W') {
                    t = 0;
                } else if (grid[i][j] == 'E') {
                    ++t;
                }
                g[i][j] += t;
            }
        }
        for (int j = 0; j < n; ++j) {
            int t = 0;
            for (int i = 0; i < m; ++i) {
                if (grid[i][j] == 'W') {
                    t = 0;
                } else if (grid[i][j] == 'E') {
                    ++t;
                }
                g[i][j] += t;
            }
            t = 0;
            for (int i = m - 1; i >= 0; --i) {
                if (grid[i][j] == 'W') {
                    t = 0;
                } else if (grid[i][j] == 'E') {
                    ++t;
                }
                g[i][j] += t;
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '0') {
                    ans = Math.max(ans, g[i][j]);
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxKilledEnemies(vector<vector<char>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> g(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            int t = 0;
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 'W')
                    t = 0;
                else if (grid[i][j] == 'E')
                    ++t;
                g[i][j] += t;
            }
            t = 0;
            for (int j = n - 1; j >= 0; --j) {
                if (grid[i][j] == 'W')
                    t = 0;
                else if (grid[i][j] == 'E')
                    ++t;
                g[i][j] += t;
            }
        }
        for (int j = 0; j < n; ++j) {
            int t = 0;
            for (int i = 0; i < m; ++i) {
                if (grid[i][j] == 'W')
                    t = 0;
                else if (grid[i][j] == 'E')
                    ++t;
                g[i][j] += t;
            }
            t = 0;
            for (int i = m - 1; i >= 0; --i) {
                if (grid[i][j] == 'W')
                    t = 0;
                else if (grid[i][j] == 'E')
                    ++t;
                g[i][j] += t;
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '0') ans = max(ans, g[i][j]);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxKilledEnemies(grid [][]byte) int {
	m, n := len(grid), len(grid[0])
	g := make([][]int, m)
	for i := range g {
		g[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		t := 0
		for j := 0; j < n; j++ {
			if grid[i][j] == 'W' {
				t = 0
			} else if grid[i][j] == 'E' {
				t++
			}
			g[i][j] += t
		}
		t = 0
		for j := n - 1; j >= 0; j-- {
			if grid[i][j] == 'W' {
				t = 0
			} else if grid[i][j] == 'E' {
				t++
			}
			g[i][j] += t
		}
	}
	for j := 0; j < n; j++ {
		t := 0
		for i := 0; i < m; i++ {
			if grid[i][j] == 'W' {
				t = 0
			} else if grid[i][j] == 'E' {
				t++
			}
			g[i][j] += t
		}
		t = 0
		for i := m - 1; i >= 0; i-- {
			if grid[i][j] == 'W' {
				t = 0
			} else if grid[i][j] == 'E' {
				t++
			}
			g[i][j] += t
		}
	}
	ans := 0
	for i, row := range grid {
		for j, v := range row {
			if v == '0' && ans < g[i][j] {
				ans = g[i][j]
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
