---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3195.Find%20the%20Minimum%20Area%20to%20Cover%20All%20Ones%20I/README.md
rating: 1348
source: 第 403 场周赛 Q2
tags:
    - 数组
    - 矩阵
---

<!-- problem:start -->

# [3195. 包含所有 1 的最小矩形面积 I](https://leetcode.cn/problems/find-the-minimum-area-to-cover-all-ones-i)

[English Version](/solution/3100-3199/3195.Find%20the%20Minimum%20Area%20to%20Cover%20All%20Ones%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二维 <strong>二进制 </strong>数组 <code>grid</code>。请你找出一个边在水平方向和竖直方向上、面积 <strong>最小</strong> 的矩形，并且满足 <code>grid</code> 中所有的 1 都在矩形的内部。</p>

<p>返回这个矩形可能的 <strong>最小 </strong>面积。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [[0,1,0],[1,0,1]]</span></p>

<p><strong>输出：</strong> <span class="example-io">6</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3195.Find%20the%20Minimum%20Area%20to%20Cover%20All%20Ones%20I/images/examplerect0.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 279px; height: 198px;" /></p>

<p>这个最小矩形的高度为 2，宽度为 3，因此面积为 <code>2 * 3 = 6</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [[0,0],[1,0]]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3195.Find%20the%20Minimum%20Area%20to%20Cover%20All%20Ones%20I/images/examplerect1.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 204px; height: 201px;" /></p>

<p>这个最小矩形的高度和宽度都是 1，因此面积为 <code>1 * 1 = 1</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= grid.length, grid[i].length &lt;= 1000</code></li>
	<li><code>grid[i][j]</code> 是 0 或 1。</li>
	<li>输入保证 <code>grid</code> 中至少有一个 1 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：求最小边界和最大边界

我们可以遍历 `grid`，找到所有 `1` 的最小边界，记为 $(x_1, y_1)$，最大边界，记为 $(x_2, y_2)$，那么最小矩形的面积就是 $(x_2 - x_1 + 1) \times (y_2 - y_1 + 1)$。

时间复杂度 $O(m \times n)$，其中 $m$ 和 $n$ 分别是 `grid` 的行数和列数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumArea(self, grid: List[List[int]]) -> int:
        x1 = y1 = inf
        x2 = y2 = -inf
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                if x == 1:
                    x1 = min(x1, i)
                    y1 = min(y1, j)
                    x2 = max(x2, i)
                    y2 = max(y2, j)
        return (x2 - x1 + 1) * (y2 - y1 + 1)
```

#### Java

```java
class Solution {
    public int minimumArea(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int x1 = m, y1 = n;
        int x2 = 0, y2 = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    x1 = Math.min(x1, i);
                    y1 = Math.min(y1, j);
                    x2 = Math.max(x2, i);
                    y2 = Math.max(y2, j);
                }
            }
        }
        return (x2 - x1 + 1) * (y2 - y1 + 1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumArea(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int x1 = m, y1 = n;
        int x2 = 0, y2 = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    x1 = min(x1, i);
                    y1 = min(y1, j);
                    x2 = max(x2, i);
                    y2 = max(y2, j);
                }
            }
        }
        return (x2 - x1 + 1) * (y2 - y1 + 1);
    }
};
```

#### Go

```go
func minimumArea(grid [][]int) int {
	x1, y1 := len(grid), len(grid[0])
	x2, y2 := 0, 0
	for i, row := range grid {
		for j, x := range row {
			if x == 1 {
				x1, y1 = min(x1, i), min(y1, j)
				x2, y2 = max(x2, i), max(y2, j)
			}
		}
	}
	return (x2 - x1 + 1) * (y2 - y1 + 1)
}
```

#### TypeScript

```ts
function minimumArea(grid: number[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    let [x1, y1] = [m, n];
    let [x2, y2] = [0, 0];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] === 1) {
                x1 = Math.min(x1, i);
                y1 = Math.min(y1, j);
                x2 = Math.max(x2, i);
                y2 = Math.max(y2, j);
            }
        }
    }
    return (x2 - x1 + 1) * (y2 - y1 + 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
