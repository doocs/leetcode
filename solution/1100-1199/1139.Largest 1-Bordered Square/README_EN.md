# [1139. Largest 1-Bordered Square](https://leetcode.com/problems/largest-1-bordered-square)

[中文文档](/solution/1100-1199/1139.Largest%201-Bordered%20Square/README.md)

## Description

<p>Given a 2D <code>grid</code> of <code>0</code>s and <code>1</code>s, return the number of elements in&nbsp;the largest <strong>square</strong>&nbsp;subgrid that has all <code>1</code>s on its <strong>border</strong>, or <code>0</code> if such a subgrid&nbsp;doesn&#39;t exist in the <code>grid</code>.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> grid = [[1,1,1],[1,0,1],[1,1,1]]

<strong>Output:</strong> 9

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> grid = [[1,1,0,0]]

<strong>Output:</strong> 1

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>1 &lt;= grid.length &lt;= 100</code></li>
    <li><code>1 &lt;= grid[0].length &lt;= 100</code></li>
    <li><code>grid[i][j]</code> is <code>0</code> or <code>1</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largest1BorderedSquare(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        down = [[0] * n for _ in range(m)]
        right = [[0] * n for _ in range(m)]
        for i in range(m - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                if grid[i][j]:
                    down[i][j] = down[i + 1][j] + 1 if i + 1 < m else 1
                    right[i][j] = right[i][j + 1] + 1 if j + 1 < n else 1
        for k in range(min(m, n), 0, -1):
            for i in range(m - k + 1):
                for j in range(n - k + 1):
                    if down[i][j] >= k and right[i][j] >= k and right[i + k - 1][j] >= k and down[i][j + k - 1] >= k:
                        return k * k
        return 0
```

### **Java**

```java
class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] down = new int[m][n];
        int[][] right = new int[m][n];
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (grid[i][j] == 1) {
                    down[i][j] = i + 1 < m ? down[i + 1][j] + 1 : 1;
                    right[i][j] = j + 1 < n ? right[i][j + 1] + 1 : 1;
                }
            }
        }
        for (int k = Math.min(m, n); k > 0; --k) {
            for (int i = 0; i <= m - k; ++i) {
                for (int j = 0; j <= n - k; ++j) {
                    if (down[i][j] >= k && right[i][j] >= k && right[i + k - 1][j] >= k
                        && down[i][j + k - 1] >= k) {
                        return k * k;
                    }
                }
            }
        }
        return 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int largest1BorderedSquare(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int down[m][n];
        int right[m][n];
        memset(down, 0, sizeof down);
        memset(right, 0, sizeof right);
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (grid[i][j] == 1) {
                    down[i][j] = i + 1 < m ? down[i + 1][j] + 1 : 1;
                    right[i][j] = j + 1 < n ? right[i][j + 1] + 1 : 1;
                }
            }
        }
        for (int k = min(m, n); k > 0; --k) {
            for (int i = 0; i <= m - k; ++i) {
                for (int j = 0; j <= n - k; ++j) {
                    if (down[i][j] >= k && right[i][j] >= k && right[i + k - 1][j] >= k
                        && down[i][j + k - 1] >= k) {
                        return k * k;
                    }
                }
            }
        }
        return 0;
    }
};
```

### **Go**

```go
func largest1BorderedSquare(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	down := make([][]int, m)
	right := make([][]int, m)
	for i := range down {
		down[i] = make([]int, n)
		right[i] = make([]int, n)
	}
	for i := m - 1; i >= 0; i-- {
		for j := n - 1; j >= 0; j-- {
			if grid[i][j] == 1 {
				down[i][j], right[i][j] = 1, 1
				if i+1 < m {
					down[i][j] += down[i+1][j]
				}
				if j+1 < n {
					right[i][j] += right[i][j+1]
				}
			}
		}
	}
	for k := min(m, n); k > 0; k-- {
		for i := 0; i <= m-k; i++ {
			for j := 0; j <= n-k; j++ {
				if down[i][j] >= k && right[i][j] >= k && right[i+k-1][j] >= k && down[i][j+k-1] >= k {
					return k * k
				}
			}
		}
	}
	return 0
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
