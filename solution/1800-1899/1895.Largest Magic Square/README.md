---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1895.Largest%20Magic%20Square/README.md
rating: 1781
source: 第 54 场双周赛 Q3
tags:
    - 数组
    - 矩阵
    - 前缀和
---

<!-- problem:start -->

# [1895. 最大的幻方](https://leetcode.cn/problems/largest-magic-square)

[English Version](/solution/1800-1899/1895.Largest%20Magic%20Square/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一个 <code>k x k</code> 的<strong> 幻方</strong> 指的是一个 <code>k x k</code> 填满整数的方格阵，且每一行、每一列以及两条对角线的和 <strong>全部</strong><strong>相等</strong> 。幻方中的整数 <strong>不需要互不相同</strong> 。显然，每个 <code>1 x 1</code> 的方格都是一个幻方。</p>

<p>给你一个 <code>m x n</code> 的整数矩阵 <code>grid</code> ，请你返回矩阵中 <strong>最大幻方</strong> 的 <strong>尺寸</strong> （即边长 <code>k</code>）。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1895.Largest%20Magic%20Square/images/magicsquare-grid.jpg" style="width: 413px; height: 335px;">
<pre><b>输入：</b>grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
<b>输出：</b>3
<b>解释：</b>最大幻方尺寸为 3 。
每一行，每一列以及两条对角线的和都等于 12 。
- 每一行的和：5+1+6 = 5+4+3 = 2+7+3 = 12
- 每一列的和：5+5+2 = 1+4+7 = 6+3+3 = 12
- 对角线的和：5+4+3 = 6+4+2 = 12
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1895.Largest%20Magic%20Square/images/magicsquare2-grid.jpg" style="width: 333px; height: 255px;">
<pre><b>输入：</b>grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
<b>输出：</b>2
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和 + 枚举

我们定义 $\text{rowsum}[i][j]$ 表示矩阵第 $i$ 行前 $j$ 列的元素和，定义 $\text{colsum}[i][j]$ 表示矩阵第 $j$ 列前 $i$ 行的元素和。则对于任意子矩阵 $(x_1, y_1)$ 到 $(x_2, y_2)$，其第 $i$ 行的和可以表示为 $\text{rowsum}[i+1][y_2+1] - \text{rowsum}[i+1][y_1]$，其第 $j$ 列的和可以表示为 $\text{colsum}[x_2+1][j+1] - \text{colsum}[x_1][j+1]$。

我们枚举所有可能的子矩阵，并检查其是否为幻方。对于每个子矩阵，我们计算其每一行、每一列以及两条对角线的和，判断它们是否相等即可。

时间复杂度 $O(m \times n \times \min(m, n)^2)$，空间复杂度 $O(m \times n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestMagicSquare(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        rowsum = [[0] * (n + 1) for _ in range(m + 1)]
        colsum = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                rowsum[i][j] = rowsum[i][j - 1] + grid[i - 1][j - 1]
                colsum[i][j] = colsum[i - 1][j] + grid[i - 1][j - 1]

        def check(x1, y1, x2, y2):
            val = rowsum[x1 + 1][y2 + 1] - rowsum[x1 + 1][y1]
            for i in range(x1 + 1, x2 + 1):
                if rowsum[i + 1][y2 + 1] - rowsum[i + 1][y1] != val:
                    return False
            for j in range(y1, y2 + 1):
                if colsum[x2 + 1][j + 1] - colsum[x1][j + 1] != val:
                    return False
            s, i, j = 0, x1, y1
            while i <= x2:
                s += grid[i][j]
                i += 1
                j += 1
            if s != val:
                return False
            s, i, j = 0, x1, y2
            while i <= x2:
                s += grid[i][j]
                i += 1
                j -= 1
            if s != val:
                return False
            return True

        for k in range(min(m, n), 1, -1):
            i = 0
            while i + k - 1 < m:
                j = 0
                while j + k - 1 < n:
                    i2, j2 = i + k - 1, j + k - 1
                    if check(i, j, i2, j2):
                        return k
                    j += 1
                i += 1
        return 1
```

#### Java

```java
class Solution {
    private int[][] rowsum;
    private int[][] colsum;

    public int largestMagicSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        rowsum = new int[m + 1][n + 1];
        colsum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                rowsum[i][j] = rowsum[i][j - 1] + grid[i - 1][j - 1];
                colsum[i][j] = colsum[i - 1][j] + grid[i - 1][j - 1];
            }
        }
        for (int k = Math.min(m, n); k > 1; --k) {
            for (int i = 0; i + k - 1 < m; ++i) {
                for (int j = 0; j + k - 1 < n; ++j) {
                    int i2 = i + k - 1, j2 = j + k - 1;
                    if (check(grid, i, j, i2, j2)) {
                        return k;
                    }
                }
            }
        }
        return 1;
    }

    private boolean check(int[][] grid, int x1, int y1, int x2, int y2) {
        int val = rowsum[x1 + 1][y2 + 1] - rowsum[x1 + 1][y1];
        for (int i = x1 + 1; i <= x2; ++i) {
            if (rowsum[i + 1][y2 + 1] - rowsum[i + 1][y1] != val) {
                return false;
            }
        }
        for (int j = y1; j <= y2; ++j) {
            if (colsum[x2 + 1][j + 1] - colsum[x1][j + 1] != val) {
                return false;
            }
        }
        int s = 0;
        for (int i = x1, j = y1; i <= x2; ++i, ++j) {
            s += grid[i][j];
        }
        if (s != val) {
            return false;
        }
        s = 0;
        for (int i = x1, j = y2; i <= x2; ++i, --j) {
            s += grid[i][j];
        }
        if (s != val) {
            return false;
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> rowsum;
    vector<vector<int>> colsum;

    int largestMagicSquare(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        rowsum.assign(m + 1, vector<int>(n + 1, 0));
        colsum.assign(m + 1, vector<int>(n + 1, 0));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                rowsum[i][j] = rowsum[i][j - 1] + grid[i - 1][j - 1];
                colsum[i][j] = colsum[i - 1][j] + grid[i - 1][j - 1];
            }
        }
        for (int k = min(m, n); k > 1; --k) {
            for (int i = 0; i + k - 1 < m; ++i) {
                for (int j = 0; j + k - 1 < n; ++j) {
                    int i2 = i + k - 1, j2 = j + k - 1;
                    if (check(grid, i, j, i2, j2)) {
                        return k;
                    }
                }
            }
        }
        return 1;
    }

    bool check(vector<vector<int>>& grid, int x1, int y1, int x2, int y2) {
        int val = rowsum[x1 + 1][y2 + 1] - rowsum[x1 + 1][y1];
        for (int i = x1 + 1; i <= x2; ++i) {
            if (rowsum[i + 1][y2 + 1] - rowsum[i + 1][y1] != val) {
                return false;
            }
        }
        for (int j = y1; j <= y2; ++j) {
            if (colsum[x2 + 1][j + 1] - colsum[x1][j + 1] != val) {
                return false;
            }
        }
        int s = 0;
        for (int i = x1, j = y1; i <= x2; ++i, ++j) {
            s += grid[i][j];
        }
        if (s != val) {
            return false;
        }
        s = 0;
        for (int i = x1, j = y2; i <= x2; ++i, --j) {
            s += grid[i][j];
        }
        if (s != val) {
            return false;
        }
        return true;
    }
};
```

#### Go

```go
func largestMagicSquare(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	rowsum := make([][]int, m+1)
	colsum := make([][]int, m+1)
	for i := 0; i <= m; i++ {
		rowsum[i] = make([]int, n+1)
		colsum[i] = make([]int, n+1)
	}
	for i := 1; i < m+1; i++ {
		for j := 1; j < n+1; j++ {
			rowsum[i][j] = rowsum[i][j-1] + grid[i-1][j-1]
			colsum[i][j] = colsum[i-1][j] + grid[i-1][j-1]
		}
	}
	for k := min(m, n); k > 1; k-- {
		for i := 0; i+k-1 < m; i++ {
			for j := 0; j+k-1 < n; j++ {
				i2, j2 := i+k-1, j+k-1
				if check(grid, rowsum, colsum, i, j, i2, j2) {
					return k
				}
			}
		}
	}
	return 1
}

func check(grid, rowsum, colsum [][]int, x1, y1, x2, y2 int) bool {
	val := rowsum[x1+1][y2+1] - rowsum[x1+1][y1]
	for i := x1 + 1; i < x2+1; i++ {
		if rowsum[i+1][y2+1]-rowsum[i+1][y1] != val {
			return false
		}
	}
	for j := y1; j < y2+1; j++ {
		if colsum[x2+1][j+1]-colsum[x1][j+1] != val {
			return false
		}
	}
	s := 0
	for i, j := x1, y1; i <= x2; i, j = i+1, j+1 {
		s += grid[i][j]
	}
	if s != val {
		return false
	}
	s = 0
	for i, j := x1, y2; i <= x2; i, j = i+1, j-1 {
		s += grid[i][j]
	}
	if s != val {
		return false
	}
	return true
}
```

#### TypeScript

```ts
function largestMagicSquare(grid: number[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    const rowsum: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    const colsum: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));

    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            rowsum[i][j] = rowsum[i][j - 1] + grid[i - 1][j - 1];
            colsum[i][j] = colsum[i - 1][j] + grid[i - 1][j - 1];
        }
    }

    const check = (x1: number, y1: number, x2: number, y2: number): boolean => {
        const val = rowsum[x1 + 1][y2 + 1] - rowsum[x1 + 1][y1];
        for (let i = x1 + 1; i <= x2; ++i) {
            if (rowsum[i + 1][y2 + 1] - rowsum[i + 1][y1] !== val) {
                return false;
            }
        }
        for (let j = y1; j <= y2; ++j) {
            if (colsum[x2 + 1][j + 1] - colsum[x1][j + 1] !== val) {
                return false;
            }
        }
        let s = 0;
        for (let i = x1, j = y1; i <= x2; ++i, ++j) {
            s += grid[i][j];
        }
        if (s !== val) {
            return false;
        }
        s = 0;
        for (let i = x1, j = y2; i <= x2; ++i, --j) {
            s += grid[i][j];
        }
        if (s !== val) {
            return false;
        }
        return true;
    };

    for (let k = Math.min(m, n); k > 1; --k) {
        for (let i = 0; i + k - 1 < m; ++i) {
            for (let j = 0; j + k - 1 < n; ++j) {
                const i2 = i + k - 1,
                    j2 = j + k - 1;
                if (check(i, j, i2, j2)) {
                    return k;
                }
            }
        }
    }
    return 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
