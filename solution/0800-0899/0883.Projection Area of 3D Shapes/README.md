# [883. 三维形体投影面积](https://leetcode.cn/problems/projection-area-of-3d-shapes)

[English Version](/solution/0800-0899/0883.Projection%20Area%20of%203D%20Shapes/README_EN.md)

<!-- tags:几何,数组,数学,矩阵 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>在<meta charset="UTF-8" />&nbsp;<code>n x n</code>&nbsp;的网格<meta charset="UTF-8" />&nbsp;<code>grid</code>&nbsp;中，我们放置了一些与 x，y，z 三轴对齐的<meta charset="UTF-8" />&nbsp;<code>1 x 1 x 1</code>&nbsp;立方体。</p>

<p>每个值&nbsp;<code>v = grid[i][j]</code>&nbsp;表示 <code>v</code>&nbsp;个正方体叠放在单元格&nbsp;<code>(i, j)</code>&nbsp;上。</p>

<p>现在，我们查看这些立方体在 <code>xy</code>&nbsp;、<code>yz</code>&nbsp;和 <code>zx</code>&nbsp;平面上的<em>投影</em>。</p>

<p><strong>投影</strong>&nbsp;就像影子，将 <strong>三维</strong> 形体映射到一个 <strong>二维</strong> 平面上。从顶部、前面和侧面看立方体时，我们会看到“影子”。</p>

<p>返回 <em>所有三个投影的总面积</em> 。</p>

<p>&nbsp;</p>

<ul>
</ul>

<ul>
</ul>

<ul>
</ul>

<ul>
</ul>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0883.Projection%20Area%20of%203D%20Shapes/images/shadow.png" style="height: 214px; width: 800px;" /></p>

<pre>
<strong>输入：</strong>[[1,2],[3,4]]
<strong>输出：</strong>17
<strong>解释：</strong>这里有该形体在三个轴对齐平面上的三个投影(“阴影部分”)。
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入：</strong>grid = [[2]]
<strong>输出：</strong>5
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>[[1,0],[0,2]]
<strong>输出：</strong>8
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 50</code></li>
</ul>

## 解法

### 方法一：数学

我们可以分别计算三个投影的面积。

-   xy 平面的投影面积：每个非零值都会投影到 xy 平面，所以 xy 的投影面积为非零值的个数。
-   yz 平面的投影面积：每一行的最大值。
-   zx 平面的投影面积：每一列的最大值。

最后将三个面积相加即可。

时间复杂度 $O(n^2)$，其中 $n$ 为网格 `grid` 的边长。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def projectionArea(self, grid: List[List[int]]) -> int:
        xy = sum(v > 0 for row in grid for v in row)
        yz = sum(max(row) for row in grid)
        zx = sum(max(col) for col in zip(*grid))
        return xy + yz + zx
```

```java
class Solution {
    public int projectionArea(int[][] grid) {
        int xy = 0, yz = 0, zx = 0;
        for (int i = 0, n = grid.length; i < n; ++i) {
            int maxYz = 0;
            int maxZx = 0;
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] > 0) {
                    ++xy;
                }
                maxYz = Math.max(maxYz, grid[i][j]);
                maxZx = Math.max(maxZx, grid[j][i]);
            }
            yz += maxYz;
            zx += maxZx;
        }
        return xy + yz + zx;
    }
}
```

```cpp
class Solution {
public:
    int projectionArea(vector<vector<int>>& grid) {
        int xy = 0, yz = 0, zx = 0;
        for (int i = 0, n = grid.size(); i < n; ++i) {
            int maxYz = 0, maxZx = 0;
            for (int j = 0; j < n; ++j) {
                xy += grid[i][j] > 0;
                maxYz = max(maxYz, grid[i][j]);
                maxZx = max(maxZx, grid[j][i]);
            }
            yz += maxYz;
            zx += maxZx;
        }
        return xy + yz + zx;
    }
};
```

```go
func projectionArea(grid [][]int) int {
	xy, yz, zx := 0, 0, 0
	for i, row := range grid {
		maxYz, maxZx := 0, 0
		for j, v := range row {
			if v > 0 {
				xy++
			}
			maxYz = max(maxYz, v)
			maxZx = max(maxZx, grid[j][i])
		}
		yz += maxYz
		zx += maxZx
	}
	return xy + yz + zx
}
```

```ts
function projectionArea(grid: number[][]): number {
    const xy: number = grid.flat().filter(v => v > 0).length;
    const yz: number = grid.reduce((acc, row) => acc + Math.max(...row), 0);
    const zx: number = grid[0]
        .map((_, i) => Math.max(...grid.map(row => row[i])))
        .reduce((acc, val) => acc + val, 0);
    return xy + yz + zx;
}
```

```rust
impl Solution {
    pub fn projection_area(grid: Vec<Vec<i32>>) -> i32 {
        let xy: i32 = grid
            .iter()
            .map(
                |row|
                    row
                        .iter()
                        .filter(|&&v| v > 0)
                        .count() as i32
            )
            .sum();
        let yz: i32 = grid
            .iter()
            .map(|row| *row.iter().max().unwrap_or(&0))
            .sum();
        let zx: i32 = (0..grid[0].len())
            .map(|i|
                grid
                    .iter()
                    .map(|row| row[i])
                    .max()
                    .unwrap_or(0)
            )
            .sum();
        xy + yz + zx
    }
}
```

<!-- tabs:end -->

<!-- end -->
