# [883. 三维形体投影面积](https://leetcode.cn/problems/projection-area-of-3d-shapes)

[English Version](/solution/0800-0899/0883.Projection%20Area%20of%203D%20Shapes/README_EN.md)

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

<!-- 这里可写通用的实现逻辑 -->

根据题意：

-   xy 表示 grid 中大于 0 的元素个数
-   yz 表示 grid 每一行的最大值之和
-   zx 表示 grid 每一列的最大值之和

遍历 grid，更新 xy, yz, zx。遍历结束返回 `xy + yz + zx`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def projectionArea(self, grid: List[List[int]]) -> int:
        xy = sum(v > 0 for row in grid for v in row)
        yz = sum(max(row) for row in grid)
        zx = sum(max(col) for col in zip(*grid))
        return xy + yz + zx
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **TypeScript**

```ts
function projectionArea(grid: number[][]): number {
    const n = grid.length;
    let res = grid.reduce(
        (r, v) => r + v.reduce((r, v) => r + (v === 0 ? 0 : 1), 0),
        0,
    );
    for (let i = 0; i < n; i++) {
        let xMax = 0;
        let yMax = 0;
        for (let j = 0; j < n; j++) {
            xMax = Math.max(xMax, grid[i][j]);
            yMax = Math.max(yMax, grid[j][i]);
        }
        res += xMax + yMax;
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn projection_area(grid: Vec<Vec<i32>>) -> i32 {
        let n = grid.len();
        let mut res = 0;
        let mut x_max = vec![0; n];
        let mut y_max = vec![0; n];
        for i in 0..n {
            for j in 0..n {
                let val = grid[i][j];
                if val == 0 {
                    continue;
                }
                res += 1;
                x_max[i] = x_max[i].max(val);
                y_max[j] = y_max[j].max(val);
            }
        }
        res + y_max.iter().sum::<i32>() + x_max.iter().sum::<i32>()
    }
}
```

### **C++**

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

### **Go**

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

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
