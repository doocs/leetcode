# [1139. 最大的以 1 为边界的正方形](https://leetcode.cn/problems/largest-1-bordered-square)

[English Version](/solution/1100-1199/1139.Largest%201-Bordered%20Square/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由若干 <code>0</code> 和 <code>1</code> 组成的二维网格&nbsp;<code>grid</code>，请你找出边界全部由 <code>1</code> 组成的最大 <strong>正方形</strong> 子网格，并返回该子网格中的元素数量。如果不存在，则返回 <code>0</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>grid = [[1,1,1],[1,0,1],[1,1,1]]
<strong>输出：</strong>9
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>grid = [[1,1,0,0]]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= grid.length &lt;= 100</code></li>
	<li><code>1 &lt;= grid[0].length &lt;= 100</code></li>
	<li><code>grid[i][j]</code> 为&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 枚举**

我们可以使用前缀和的方法预处理出每个位置向下和向右的连续 $1$ 的个数，记为 `down[i][j]` 和 `right[i][j]`。

然后我们枚举正方形的边长 $k$，从最大的边长开始枚举，然后枚举正方形的左上角位置 $(i, j)$，如果满足条件，即可返回 $k^2$。

时间复杂度 $O(m \times n \times \min(m, n))$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是网格的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
