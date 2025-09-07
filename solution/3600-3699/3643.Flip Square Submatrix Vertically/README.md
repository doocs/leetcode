---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3643.Flip%20Square%20Submatrix%20Vertically/README.md
rating: 1234
source: 第 462 场周赛 Q1
tags:
    - 数组
    - 双指针
    - 矩阵
---

<!-- problem:start -->

# [3643. 垂直翻转子矩阵](https://leetcode.cn/problems/flip-square-submatrix-vertically)

[English Version](/solution/3600-3699/3643.Flip%20Square%20Submatrix%20Vertically/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <code>m x n</code> 的整数矩阵 <code>grid</code>，以及三个整数 <code>x</code>、<code>y</code> 和 <code>k</code>。</p>

<p>整数 <code>x</code> 和 <code>y</code> 表示一个&nbsp;<strong>正方形子矩阵&nbsp;</strong>的左上角下标，整数 <code>k</code> 表示该正方形子矩阵的边长。</p>

<p>你的任务是垂直翻转子矩阵的行顺序。</p>

<p>返回更新后的矩阵。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3643.Flip%20Square%20Submatrix%20Vertically/images/gridexmdrawio.png" style="width: 300px; height: 116px;" />
<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = </span>[[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]]<span class="example-io">, x = 1, y = 0, k = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">[[1,2,3,4],[13,14,15,8],[9,10,11,12],[5,6,7,16]]</span></p>

<p><strong>解释：</strong></p>

<p>上图展示了矩阵在变换前后的样子。</p>
</div>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3643.Flip%20Square%20Submatrix%20Vertically/images/gridexm2drawio.png" style="width: 350px; height: 68px;" />
<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [[3,4,2,3],[2,3,4,2]], x = 0, y = 2, k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">[[3,4,4,2],[2,3,2,3]]</span></p>

<p><strong>解释：</strong></p>

<p>上图展示了矩阵在变换前后的样子。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 100</code></li>
	<li><code>0 &lt;= x &lt; m</code></li>
	<li><code>0 &lt;= y &lt; n</code></li>
	<li><code>1 &lt;= k &lt;= min(m - x, n - y)</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们从第 $x$ 行开始，一共翻转 $\lfloor \frac{k}{2} \rfloor$ 行。

对于每一行 $i$，我们需要将其与对应的行 $i_2$ 进行交换，其中 $i_2 = x + k - 1 - (i - x)$。

在交换时，我们需要遍历 $j \in [y, y + k)$，将 $\text{grid}[i][j]$ 和 $\text{grid}[i_2][j]$ 进行交换。

最后，返回更新后的矩阵。

时间复杂度 $O(k^2)$，其中 $k$ 是子矩阵的边长。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverseSubmatrix(
        self, grid: List[List[int]], x: int, y: int, k: int
    ) -> List[List[int]]:
        for i in range(x, x + k // 2):
            i2 = x + k - 1 - (i - x)
            for j in range(y, y + k):
                grid[i][j], grid[i2][j] = grid[i2][j], grid[i][j]
        return grid
```

#### Java

```java
class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        for (int i = x; i < x + k / 2; i++) {
            int i2 = x + k - 1 - (i - x);
            for (int j = y; j < y + k; j++) {
                int t = grid[i][j];
                grid[i][j] = grid[i2][j];
                grid[i2][j] = t;
            }
        }
        return grid;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> reverseSubmatrix(vector<vector<int>>& grid, int x, int y, int k) {
        for (int i = x; i < x + k / 2; i++) {
            int i2 = x + k - 1 - (i - x);
            for (int j = y; j < y + k; j++) {
                swap(grid[i][j], grid[i2][j]);
            }
        }
        return grid;
    }
};
```

#### Go

```go
func reverseSubmatrix(grid [][]int, x int, y int, k int) [][]int {
	for i := x; i < x+k/2; i++ {
		i2 := x + k - 1 - (i - x)
		for j := y; j < y+k; j++ {
			grid[i][j], grid[i2][j] = grid[i2][j], grid[i][j]
		}
	}
	return grid
}
```

#### TypeScript

```ts
function reverseSubmatrix(grid: number[][], x: number, y: number, k: number): number[][] {
    for (let i = x; i < x + Math.floor(k / 2); i++) {
        const i2 = x + k - 1 - (i - x);
        for (let j = y; j < y + k; j++) {
            [grid[i][j], grid[i2][j]] = [grid[i2][j], grid[i][j]];
        }
    }
    return grid;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
