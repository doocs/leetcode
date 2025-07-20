---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3546.Equal%20Sum%20Grid%20Partition%20I/README.md
rating: 1411
source: 第 449 场周赛 Q2
tags:
    - 数组
    - 枚举
    - 矩阵
    - 前缀和
---

<!-- problem:start -->

# [3546. 等和矩阵分割 I](https://leetcode.cn/problems/equal-sum-grid-partition-i)

[English Version](/solution/3500-3599/3546.Equal%20Sum%20Grid%20Partition%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由正整数组成的 <code>m x n</code> 矩阵 <code>grid</code>。你的任务是判断是否可以通过&nbsp;<strong>一条水平或一条垂直分割线&nbsp;</strong>将矩阵分割成两部分，使得：</p>

<ul>
	<li>分割后形成的每个部分都是&nbsp;<strong>非空&nbsp;</strong>的。</li>
	<li>两个部分中所有元素的和&nbsp;<strong>相等&nbsp;</strong>。</li>
</ul>

<p>如果存在这样的分割，返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> grid = [[1,4],[2,3]]</p>

<p><strong>输出：</strong> true</p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3546.Equal%20Sum%20Grid%20Partition%20I/images/1746839596-kWigaF-lc.jpeg" style="height: 200px; width: 200px;" /></p>

<p>在第 0 行和第 1 行之间进行水平分割，得到两个非空部分，每部分的元素之和为 5。因此，答案是 <code>true</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> grid = [[1,3],[2,4]]</p>

<p><strong>输出：</strong> false</p>

<p><strong>解释：</strong></p>

<p>无论是水平分割还是垂直分割，都无法使两个非空部分的元素之和相等。因此，答案是 <code>false</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m == grid.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= n == grid[i].length &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举 + 前缀和

我们先计算矩阵中所有元素的和，记为 $s$。如果 $s$ 是奇数，则不可能将矩阵分割成两个和相等的部分，直接返回 `false`。

如果 $s$ 是偶数，我们可以枚举所有可能的分割线，判断是否存在一条分割线将矩阵分割成两个和相等的部分。

我们从上到下遍历每一行，计算当前行之前所有行的元素之和 $\textit{pre}$，如果 $\textit{pre} \times 2 = s$，且当前行不是最后一行，则说明可以在当前行和下一行之间进行水平分割，返回 `true`。

如果没有找到这样的分割线，我们再从左到右遍历每一列，计算当前列之前所有列的元素之和 $\textit{pre}$，如果 $\textit{pre} \times 2 = s$，且当前列不是最后一列，则说明可以在当前列和下一列之间进行垂直分割，返回 `true`。

如果没有找到这样的分割线，则返回 `false`。

时间复杂度 $O(m \times n)$，其中 $m$ 和 $n$ 分别是矩阵的行数和列数。空间复杂度 $O(1)$，只使用了常数级别的额外空间。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canPartitionGrid(self, grid: List[List[int]]) -> bool:
        s = sum(sum(row) for row in grid)
        if s % 2:
            return False
        pre = 0
        for i, row in enumerate(grid):
            pre += sum(row)
            if pre * 2 == s and i != len(grid) - 1:
                return True
        pre = 0
        for j, col in enumerate(zip(*grid)):
            pre += sum(col)
            if pre * 2 == s and j != len(grid[0]) - 1:
                return True
        return False
```

#### Java

```java
class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        long s = 0;
        for (var row : grid) {
            for (int x : row) {
                s += x;
            }
        }
        if (s % 2 != 0) {
            return false;
        }
        int m = grid.length, n = grid[0].length;
        long pre = 0;
        for (int i = 0; i < m; ++i) {
            for (int x : grid[i]) {
                pre += x;
            }
            if (pre * 2 == s && i < m - 1) {
                return true;
            }
        }
        pre = 0;
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                pre += grid[i][j];
            }
            if (pre * 2 == s && j < n - 1) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canPartitionGrid(vector<vector<int>>& grid) {
        long long s = 0;
        for (const auto& row : grid) {
            for (int x : row) {
                s += x;
            }
        }
        if (s % 2 != 0) {
            return false;
        }
        int m = grid.size(), n = grid[0].size();
        long long pre = 0;
        for (int i = 0; i < m; ++i) {
            for (int x : grid[i]) {
                pre += x;
            }
            if (pre * 2 == s && i + 1 < m) {
                return true;
            }
        }
        pre = 0;
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                pre += grid[i][j];
            }
            if (pre * 2 == s && j + 1 < n) {
                return true;
            }
        }
        return false;
    }
};
```

#### Go

```go
func canPartitionGrid(grid [][]int) bool {
	s := 0
	for _, row := range grid {
		for _, x := range row {
			s += x
		}
	}
	if s%2 != 0 {
		return false
	}
	m, n := len(grid), len(grid[0])
	pre := 0
	for i, row := range grid {
		for _, x := range row {
			pre += x
		}
		if pre*2 == s && i+1 < m {
			return true
		}
	}
	pre = 0
	for j := 0; j < n; j++ {
		for i := 0; i < m; i++ {
			pre += grid[i][j]
		}
		if pre*2 == s && j+1 < n {
			return true
		}
	}
	return false
}
```

#### TypeScript

```ts
function canPartitionGrid(grid: number[][]): boolean {
    let s = 0;
    for (const row of grid) {
        s += row.reduce((a, b) => a + b, 0);
    }
    if (s % 2 !== 0) {
        return false;
    }
    const [m, n] = [grid.length, grid[0].length];
    let pre = 0;
    for (let i = 0; i < m; ++i) {
        pre += grid[i].reduce((a, b) => a + b, 0);
        if (pre * 2 === s && i + 1 < m) {
            return true;
        }
    }
    pre = 0;
    for (let j = 0; j < n; ++j) {
        for (let i = 0; i < m; ++i) {
            pre += grid[i][j];
        }
        if (pre * 2 === s && j + 1 < n) {
            return true;
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
