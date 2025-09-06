---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3459.Length%20of%20Longest%20V-Shaped%20Diagonal%20Segment/README.md
rating: 2530
source: 第 437 场周赛 Q4
tags:
    - 记忆化搜索
    - 数组
    - 动态规划
    - 矩阵
---

<!-- problem:start -->

# [3459. 最长 V 形对角线段的长度](https://leetcode.cn/problems/length-of-longest-v-shaped-diagonal-segment)

[English Version](/solution/3400-3499/3459.Length%20of%20Longest%20V-Shaped%20Diagonal%20Segment/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个大小为 <code>n x m</code> 的二维整数矩阵 <code>grid</code>，其中每个元素的值为 <code>0</code>、<code>1</code> 或 <code>2</code>。</p>

<p><strong>V 形对角线段</strong> 定义如下：</p>

<ul>
	<li>线段从&nbsp;<code>1</code> 开始。</li>
	<li>后续元素按照以下无限序列的模式排列：<code>2, 0, 2, 0, ...</code>。</li>
	<li>该线段：
	<ul>
		<li>起始于某个对角方向（左上到右下、右下到左上、右上到左下或左下到右上）。</li>
		<li>沿着相同的对角方向继续，保持&nbsp;<strong>序列模式&nbsp;</strong>。</li>
		<li>在保持&nbsp;<strong>序列模式&nbsp;</strong>的前提下，最多允许&nbsp;<strong>一次顺时针 90 度转向&nbsp;</strong>另一个对角方向。</li>
	</ul>
	</li>
</ul>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3459.Length%20of%20Longest%20V-Shaped%20Diagonal%20Segment/images/1739609732-jHpPma-length_of_longest3.jpg" style="width: 481px; height: 202px;" /></p>

<p>返回最长的&nbsp;<strong>V 形对角线段&nbsp;</strong>的&nbsp;<strong>长度&nbsp;</strong>。如果不存在有效的线段，则返回 0。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [[2,2,1,2,2],[2,0,2,2,0],[2,0,1,1,0],[1,0,2,2,2],[2,0,0,2,2]]</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3459.Length%20of%20Longest%20V-Shaped%20Diagonal%20Segment/images/1739609768-rhePxN-matrix_1-2.jpg" style="width: 201px; height: 192px;" /></p>

<p>最长的 V 形对角线段长度为 5，路径如下：<code>(0,2) → (1,3) → (2,4)</code>，在 <code>(2,4)</code> 处进行&nbsp;<strong>顺时针 90 度转向&nbsp;</strong>，继续路径为 <code>(3,3) → (4,2)</code>。</p>
</div>

<p><strong>示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [[2,2,2,2,2],[2,0,2,2,0],[2,0,1,1,0],[1,0,2,2,2],[2,0,0,2,2]]</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3459.Length%20of%20Longest%20V-Shaped%20Diagonal%20Segment/images/1739609774-nYJElV-matrix_2.jpg" style="width: 201px; height: 201px;" /></p>

<p>最长的 V 形对角线段长度为 4，路径如下：<code>(2,3) → (3,2)</code>，在 <code>(3,2)</code> 处进行&nbsp;<strong>顺时针 90 度转向&nbsp;</strong>，继续路径为 <code>(2,1) → (1,0)</code>。</p>
</div>

<p><strong>示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [[1,2,2,2,2],[2,2,2,2,0],[2,0,0,0,0],[0,0,2,2,2],[2,0,0,2,0]]</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3459.Length%20of%20Longest%20V-Shaped%20Diagonal%20Segment/images/1739609780-tlkdUW-matrix_3.jpg" style="width: 201px; height: 201px;" /></p>

<p>最长的 V 形对角线段长度为 5，路径如下：<code>(0,0) → (1,1) → (2,2) → (3,3) → (4,4)</code>。</p>
</div>

<p><strong>示例 4：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [[1]]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>最长的 V 形对角线段长度为 1，路径如下：<code>(0,0)</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>m == grid[i].length</code></li>
	<li><code>1 &lt;= n, m &lt;= 500</code></li>
	<li><code>grid[i][j]</code> 的值为 <code>0</code>、<code>1</code> 或 <code>2</code>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们设计一个函数 $\text{dfs}(i, j, k, \textit{cnt})$，表示上一个位置为 $(i, j)$，当前方向为 $k$，剩余可转向次数为 $\textit{cnt}$ 时，返回最长的 V 形对角线段长度。

函数 $\text{dfs}$ 的执行逻辑如下：

我们首先基于上一个位置以及当前的方向，计算当前得到当前位置 $(x, y)$，然后计算当前目标值 $\textit{target}$。如果 $x$ 或 $y$ 不在矩阵范围内，或者 $\textit{grid}[x][y] \neq \textit{target}$，返回 $0$。

否则，我们有两种选择：

1. 继续沿着当前方向前进。
2. 在当前位置进行顺时针 90 度转向，然后继续前进。

我们可以通过改变方向来实现顺时针 90 度转向。具体来说，如果当前方向为 $k$，则顺时针 90 度转向后的新方向为 $(k + 1) \bmod 4$。最后，我们选择这两种选择中的最大值作为当前状态的结果。

在主函数中，我们遍历整个矩阵，对于每个值为 1 的位置，尝试四个方向的搜索，并更新答案。

遍历结束后，返回答案即可。

为了避免重复计算，我们使用记忆化搜索来缓存中间结果。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lenOfVDiagonal(self, grid: List[List[int]]) -> int:
        @cache
        def dfs(i: int, j: int, k: int, cnt: int) -> int:
            x, y = i + dirs[k], j + dirs[k + 1]
            target = 2 if grid[i][j] == 1 else (2 - grid[i][j])
            if not 0 <= x < m or not 0 <= y < n or grid[x][y] != target:
                return 0
            res = dfs(x, y, k, cnt)
            if cnt > 0:
                res = max(res, dfs(x, y, (k + 1) % 4, 0))
            return 1 + res

        m, n = len(grid), len(grid[0])
        dirs = (1, 1, -1, -1, 1)
        ans = 0
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                if x == 1:
                    for k in range(4):
                        ans = max(ans, dfs(i, j, k, 1) + 1)
        return ans
```

#### Java

```java
class Solution {
    private int m, n;
    private final int[] dirs = {1, 1, -1, -1, 1};
    private Integer[][][][] f;

    public int lenOfVDiagonal(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        f = new Integer[m][n][4][2];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        ans = Math.max(ans, dfs(grid, i, j, k, 1) + 1);
                    }
                }
            }
        }
        return ans;
    }

    private int dfs(int[][] grid, int i, int j, int k, int cnt) {
        if (f[i][j][k][cnt] != null) {
            return f[i][j][k][cnt];
        }
        int x = i + dirs[k];
        int y = j + dirs[k + 1];
        int target = grid[i][j] == 1 ? 2 : (2 - grid[i][j]);
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != target) {
            f[i][j][k][cnt] = 0;
            return 0;
        }
        int res = dfs(grid, x, y, k, cnt);
        if (cnt > 0) {
            res = Math.max(res, dfs(grid, x, y, (k + 1) % 4, 0));
        }
        f[i][j][k][cnt] = 1 + res;
        return 1 + res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    static constexpr int MAXN = 501;
    int f[MAXN][MAXN][4][2];

    int lenOfVDiagonal(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int dirs[5] = {1, 1, -1, -1, 1};
        memset(f, -1, sizeof(f));

        auto dfs = [&](this auto&& dfs, int i, int j, int k, int cnt) -> int {
            if (f[i][j][k][cnt] != -1) {
                return f[i][j][k][cnt];
            }
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            int target = grid[i][j] == 1 ? 2 : (2 - grid[i][j]);
            if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != target) {
                f[i][j][k][cnt] = 0;
                return 0;
            }
            int res = dfs(x, y, k, cnt);
            if (cnt > 0) {
                res = max(res, dfs(x, y, (k + 1) % 4, 0));
            }
            f[i][j][k][cnt] = 1 + res;
            return 1 + res;
        };

        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; ++k) {
                        ans = max(ans, dfs(i, j, k, 1) + 1);
                    }
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func lenOfVDiagonal(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dirs := []int{1, 1, -1, -1, 1}
	f := make([][][4][2]int, m)
	for i := range f {
		f[i] = make([][4][2]int, n)
	}

	var dfs func(i, j, k, cnt int) int
	dfs = func(i, j, k, cnt int) int {
		if f[i][j][k][cnt] != 0 {
			return f[i][j][k][cnt]
		}

		x := i + dirs[k]
		y := j + dirs[k+1]

		var target int
		if grid[i][j] == 1 {
			target = 2
		} else {
			target = 2 - grid[i][j]
		}

		if x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != target {
			f[i][j][k][cnt] = 0
			return 0
		}

		res := dfs(x, y, k, cnt)
		if cnt > 0 {
			res = max(res, dfs(x, y, (k+1)%4, 0))
		}
		f[i][j][k][cnt] = res + 1
		return res + 1
	}

	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				for k := 0; k < 4; k++ {
					ans = max(ans, dfs(i, j, k, 1)+1)
				}
			}
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
