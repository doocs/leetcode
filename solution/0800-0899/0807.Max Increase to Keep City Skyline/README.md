---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0807.Max%20Increase%20to%20Keep%20City%20Skyline/README.md
tags:
    - 贪心
    - 数组
    - 矩阵
---

<!-- problem:start -->

# [807. 保持城市天际线](https://leetcode.cn/problems/max-increase-to-keep-city-skyline)

[English Version](/solution/0800-0899/0807.Max%20Increase%20to%20Keep%20City%20Skyline/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一座由 <code>n x n</code> 个街区组成的城市，每个街区都包含一座立方体建筑。给你一个下标从 <strong>0</strong> 开始的 <code>n x n</code> 整数矩阵 <code>grid</code> ，其中 <code>grid[r][c]</code> 表示坐落于 <code>r</code> 行 <code>c</code> 列的建筑物的 <strong>高度</strong> 。</p>

<p>城市的 <strong>天际线</strong> 是从远处观察城市时，所有建筑物形成的外部轮廓。从东、南、西、北四个主要方向观测到的 <strong>天际线</strong> 可能不同。</p>

<p>我们被允许为 <strong>任意数量的建筑物 </strong>的高度增加<strong> 任意增量（不同建筑物的增量可能不同）</strong> 。 高度为 <code>0</code> 的建筑物的高度也可以增加。然而，增加的建筑物高度 <strong>不能影响</strong> 从任何主要方向观察城市得到的 <strong>天际线</strong> 。</p>

<p>在 <strong>不改变</strong> 从任何主要方向观测到的城市 <strong>天际线</strong> 的前提下，返回建筑物可以增加的 <strong>最大高度增量总和</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0807.Max%20Increase%20to%20Keep%20City%20Skyline/images/807-ex1.png" style="width: 700px; height: 603px;" />
<pre>
<strong>输入：</strong>grid = [[3,0,8,4],[2,4,5,7],[9,2,6,3],[0,3,1,0]]
<strong>输出：</strong>35
<strong>解释：</strong>建筑物的高度如上图中心所示。
用红色绘制从不同方向观看得到的天际线。
在不影响天际线的情况下，增加建筑物的高度：
gridNew = [ [8, 4, 8, 7],
            [7, 4, 7, 7],
            [9, 4, 8, 7],
            [3, 3, 3, 3] ]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [[0,0,0],[0,0,0],[0,0,0]]
<strong>输出：</strong>0
<strong>解释：</strong>增加任何建筑物的高度都会导致天际线的变化。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[r].length</code></li>
	<li><code>2 &lt;= n &lt;= 50</code></li>
	<li><code>0 &lt;= grid[r][c] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

根据题目描述，我们可以将每个单元格 $(i, j)$ 的值增加至第 $i$ 行的最大值和第 $j$ 列的最大值中的较小值，这样可以保证不影响天际线，即每个单元格增加的高度为 $\min(\text{rowMax}[i], \text{colMax}[j]) - \text{grid}[i][j]$。

因此，我们可以先遍历一次矩阵，分别计算出每行和每列的最大值，记录在数组 $\text{rowMax}$ 和 $\text{colMax}$ 中，然后再遍历一次矩阵，计算出答案即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为矩阵 $\text{grid}$ 的边长。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxIncreaseKeepingSkyline(self, grid: List[List[int]]) -> int:
        row_max = [max(row) for row in grid]
        col_max = [max(col) for col in zip(*grid)]
        return sum(
            min(row_max[i], col_max[j]) - x
            for i, row in enumerate(grid)
            for j, x in enumerate(row)
        )
```

#### Java

```java
class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] rowMax = new int[m];
        int[] colMax = new int[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                rowMax[i] = Math.max(rowMax[i], grid[i][j]);
                colMax[j] = Math.max(colMax[j], grid[i][j]);
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans += Math.min(rowMax[i], colMax[j]) - grid[i][j];
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxIncreaseKeepingSkyline(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        vector<int> rowMax(m);
        vector<int> colMax(n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                rowMax[i] = max(rowMax[i], grid[i][j]);
                colMax[j] = max(colMax[j], grid[i][j]);
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans += min(rowMax[i], colMax[j]) - grid[i][j];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxIncreaseKeepingSkyline(grid [][]int) (ans int) {
	rowMax := make([]int, len(grid))
	colMax := make([]int, len(grid[0]))
	for i, row := range grid {
		for j, x := range row {
			rowMax[i] = max(rowMax[i], x)
			colMax[j] = max(colMax[j], x)
		}
	}
	for i, row := range grid {
		for j, x := range row {
			ans += min(rowMax[i], colMax[j]) - x
		}
	}
	return
}
```

#### TypeScript

```ts
function maxIncreaseKeepingSkyline(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const rowMax = Array(m).fill(0);
    const colMax = Array(n).fill(0);
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            rowMax[i] = Math.max(rowMax[i], grid[i][j]);
            colMax[j] = Math.max(colMax[j], grid[i][j]);
        }
    }
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            ans += Math.min(rowMax[i], colMax[j]) - grid[i][j];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
