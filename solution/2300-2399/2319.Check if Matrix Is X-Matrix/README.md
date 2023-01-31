# [2319. 判断矩阵是否是一个 X 矩阵](https://leetcode.cn/problems/check-if-matrix-is-x-matrix)

[English Version](/solution/2300-2399/2319.Check%20if%20Matrix%20Is%20X-Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果一个正方形矩阵满足下述 <strong>全部</strong> 条件，则称之为一个 <strong>X 矩阵</strong> ：</p>

<ol>
	<li>矩阵对角线上的所有元素都 <strong>不是 0</strong></li>
	<li>矩阵中所有其他元素都是 <strong>0</strong></li>
</ol>

<p>给你一个大小为 <code>n x n</code> 的二维整数数组 <code>grid</code> ，表示一个正方形矩阵。如果<em> </em><code>grid</code><em> </em>是一个 <strong>X 矩阵 </strong>，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2319.Check%20if%20Matrix%20Is%20X-Matrix/images/ex1.jpg" style="width: 311px; height: 320px;">
<pre><strong>输入：</strong>grid = [[2,0,0,1],[0,3,1,0],[0,5,2,0],[4,0,0,2]]
<strong>输出：</strong>true
<strong>解释：</strong>矩阵如上图所示。
X 矩阵应该满足：绿色元素（对角线上）都不是 0 ，红色元素都是 0 。
因此，grid 是一个 X 矩阵。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2319.Check%20if%20Matrix%20Is%20X-Matrix/images/ex2.jpg" style="width: 238px; height: 246px;">
<pre><strong>输入：</strong>grid = [[5,7,0],[0,3,1],[0,5,0]]
<strong>输出：</strong>false
<strong>解释：</strong>矩阵如上图所示。
X 矩阵应该满足：绿色元素（对角线上）都不是 0 ，红色元素都是 0 。
因此，grid 不是一个 X 矩阵。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>3 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

遍历矩阵，对于每个元素，判断其是否满足 $X$ 矩阵的条件。若不满足，直接返回 `false`；若遍历完所有元素都满足，返回 `true`。

时间复杂度 $O(n^2)$，空间复杂度 $O(1)$。其中 $n$ 为矩阵的行数或列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkXMatrix(self, grid: List[List[int]]) -> bool:
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                if i == j or i + j == len(grid) - 1:
                    if v == 0:
                        return False
                elif v:
                    return False
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkXMatrix(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == j || i + j == n - 1) {
                    if (grid[i][j] == 0) {
                        return false;
                    }
                } else if (grid[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkXMatrix(vector<vector<int>>& grid) {
        int n = grid.size();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == j || i + j == n - 1) {
                    if (!grid[i][j]) {
                        return false;
                    }
                } else if (grid[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
};
```

### **Go**

```go
func checkXMatrix(grid [][]int) bool {
	for i, row := range grid {
		for j, v := range row {
			if i == j || i+j == len(row)-1 {
				if v == 0 {
					return false
				}
			} else if v != 0 {
				return false
			}
		}
	}
	return true
}
```

### **TypeScript**

```ts
function checkXMatrix(grid: number[][]): boolean {
    const n = grid.length;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            if (i == j || i + j == n - 1) {
                if (!grid[i][j]) {
                    return false;
                }
            } else if (grid[i][j]) {
                return false;
            }
        }
    }
    return true;
}
```

### **C#**

```cs
public class Solution {
    public bool CheckXMatrix(int[][] grid) {
        int n = grid.Length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == j || i + j == n - 1) {
                    if (grid[i][j] == 0) {
                        return false;
                    }
                } else if (grid[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

### **Rust**

```rust
impl Solution {
    pub fn check_x_matrix(grid: Vec<Vec<i32>>) -> bool {
        let n = grid.len();
        for i in 0..n {
            for j in 0..n {
                if i == j || i + j == n - 1 {
                    if grid[i][j] == 0 {
                        return false;
                    }
                } else if grid[i][j] != 0 {
                    return false;
                }
            }
        }
        true
    }
}
```

### **C**

```c
bool checkXMatrix(int **grid, int gridSize, int *gridColSize) {
    for (int i = 0; i < gridSize; i++) {
        for (int j = 0; j < gridSize; j++) {
            if (i == j || i + j == gridSize - 1) {
                if (grid[i][j] == 0) {
                    return false;
                }
            } else if (grid[i][j] != 0) {
                return false;
            }
        }
    }
    return true;
}
```

### **...**

```

```

<!-- tabs:end -->
