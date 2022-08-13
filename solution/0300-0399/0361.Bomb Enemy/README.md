# [361. 轰炸敌人](https://leetcode.cn/problems/bomb-enemy)

[English Version](/solution/0300-0399/0361.Bomb%20Enemy/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>m x n</code> 的矩阵 <code>grid</code> ，其中每个单元格都放置有一个字符：</p>

<ul>
	<li><code>'W'</code>&nbsp;表示一堵墙</li>
	<li><code>'E'</code>&nbsp;表示一个敌人</li>
	<li><code>'0'</code>（数字 0）表示一个空位</li>
</ul>

<p>返回你使用 <strong>一颗炸弹</strong> 可以击杀的最大敌人数目。你只能把炸弹放在一个空位里。</p>

<p>由于炸弹的威力不足以穿透墙体，炸弹只能击杀同一行和同一列没被墙体挡住的敌人。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0361.Bomb%20Enemy/images/bomb1-grid.jpg" style="width: 600px; height: 187px;" />
<pre>
<strong>输入：</strong>grid = [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
<strong>输出：</strong>3
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0361.Bomb%20Enemy/images/bomb2-grid.jpg" style="width: 500px; height: 194px;" />
<pre>
<strong>输入：</strong>grid = [["W","W","W"],["0","0","0"],["E","E","E"]]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>grid[i][j]</code> 可以是 <code>'W'</code>、<code>'E'</code> 或 <code>'0'</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
