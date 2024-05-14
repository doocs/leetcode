---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2852.Sum%20of%20Remoteness%20of%20All%20Cells/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 数组
    - 矩阵
---

# [2852. 所有单元格的远离程度之和 🔒](https://leetcode.cn/problems/sum-of-remoteness-of-all-cells)

[English Version](/solution/2800-2899/2852.Sum%20of%20Remoteness%20of%20All%20Cells/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个下标从 <strong>0</strong> 开始的大小为 <code>n * n</code> 的矩阵 <code>grid</code>，其中每个单元格的值 <code>grid[i][j]</code> 要么是 <strong>正整数</strong>，要么是表示阻塞单元格的值 <code>-1</code> 。</p>

<p>你可以从一个非阻塞单元格移动到与其共享边的任何非阻塞单元格。</p>

<p>对于任何单元格 <code>(i, j)</code>，我们定义其 <strong>远离程度</strong> <code>R[i][j]</code> 如下：</p>

<ul>
	<li>如果单元格 <code>(i, j)</code> 是 <strong>非阻塞</strong> 单元格，则 <code>R[i][j]</code> 是值 <code>grid[x][y]</code> 的总和，其中 <strong>没有</strong> 从 <strong>非阻塞</strong> 单元格 <code>(x, y)</code> 到单元格 <code>(i, j)</code> 的路径。</li>
	<li>对于阻塞单元格，<code>R[i][j] == 0</code>。</li>
</ul>

<p>返回所有单元格的 <code>R[i][j]</code> 之和。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2852.Sum%20of%20Remoteness%20of%20All%20Cells/images/1-new.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 400px; height: 304px;" /></p>

<pre>
<b>输入：</b>grid = [[-1,1,-1],[5,-1,4],[-1,3,-1]]
<b>输出：</b>39
<b>解释：</b>在上面的图片中，有四个矩阵。左上角的矩阵是题目给定矩阵的初始值。被阻塞的单元格是黑色的，其他单元格的值与输入相同。在右上方的网格中，可以看到所有单元格的值也就是 R[i][j] 的值。答案是它们的和。即:0 + 12 + 0 + 8 + 0 + 9 + 0 + 10 + 0 = 39。
在上图左下角的矩阵，计算 R[0][1] (目标单元格为绿色)。我们应该将单元格 (0,1) 无法到达的单元格的值相加。这些单元格在这个矩阵中是黄色的。所以 R[0][1] = 5 + 4 + 3 = 12。
在上图右下角的矩阵，计算 R[1][2] (目标单元格为绿色)。我们应该把单元格 (1,2) 无法到达的单元格的值相加。这些单元格在这个矩阵中是黄色的。所以 R[1][2] = 1 + 5 + 3 = 9。
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2852.Sum%20of%20Remoteness%20of%20All%20Cells/images/2.png" style="width: 400px; height: 302px; background: #fff; border-radius: .5rem;" /></p>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>grid = [[-1,3,4],[-1,-1,-1],[3,-1,-1]]
<b>输出：</b>13
<b>解释：</b>在上面的图片中，有四个矩阵。左上角的矩阵是给定矩阵的初始值。被阻塞的单元格是黑色的，其他单元格的值与输入相同。在右上方的网格中，可以看到所有单元格的值也就是 R[i][j] 的值。答案是它们的和。即:3 + 3 + 0 + 0 + 0 + 0 + 7 + 0 + 0 = 13。
在上图左下角的矩阵上，计算 R[0][2] (目标单元格为绿色)。将单元格 (0,2) 无法到达的单元格的值相加。这个单元格在这个矩阵中是黄色的。所以 R[0][2] = 3。
在上图右下角的矩阵上，计算 R[2][0] (目标单元格为绿色)。将单元格 (2,0) 无法到达的单元格的值相加，这些单元格在这个矩阵中是黄色的。所以 R[2][0] = 3 + 4 = 7。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>grid = [[1]]
<b>输出：</b>0
<b>解释：</b>因为除了 (0,0) 没有其他单元格，所以 R[0][0] 等于 0。所以所有单元格的和是 0。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 300</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>6</sup></code> 或&nbsp;<code>grid[i][j] == -1</code></li>
</ul>

## 解法

### 方法一：DFS

我们先统计矩阵中非阻塞的格子的个数，记为 $cnt$，然后从每个非阻塞的格子出发，使用 DFS 计算出每个连通块中格子之和 $s$ 以及格子个数 $t$，那么其它连通块的所有 $(cnt - t)$ 个格子都可以累加上 $s$。我们累加所有连通块的结果即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是矩阵的边长。

<!-- tabs:start -->

```python
class Solution:
    def sumRemoteness(self, grid: List[List[int]]) -> int:
        def dfs(i: int, j: int) -> (int, int):
            s, t = grid[i][j], 1
            grid[i][j] = 0
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < n and 0 <= y < n and grid[x][y] > 0:
                    s1, t1 = dfs(x, y)
                    s, t = s + s1, t + t1
            return s, t

        n = len(grid)
        dirs = (-1, 0, 1, 0, -1)
        cnt = sum(x > 0 for row in grid for x in row)
        ans = 0
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                if x > 0:
                    s, t = dfs(i, j)
                    ans += (cnt - t) * s
        return ans
```

```java
class Solution {
    private int n;
    private int[][] grid;
    private final int[] dirs = {-1, 0, 1, 0, -1};

    public long sumRemoteness(int[][] grid) {
        n = grid.length;
        this.grid = grid;
        int cnt = 0;
        for (int[] row : grid) {
            for (int x : row) {
                if (x > 0) {
                    ++cnt;
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] > 0) {
                    long[] res = dfs(i, j);
                    ans += (cnt - res[1]) * res[0];
                }
            }
        }
        return ans;
    }

    private long[] dfs(int i, int j) {
        long[] res = new long[2];
        res[0] = grid[i][j];
        res[1] = 1;
        grid[i][j] = 0;
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] > 0) {
                long[] tmp = dfs(x, y);
                res[0] += tmp[0];
                res[1] += tmp[1];
            }
        }
        return res;
    }
}
```

```cpp
class Solution {
public:
    long long sumRemoteness(vector<vector<int>>& grid) {
        using pli = pair<long long, int>;
        int n = grid.size();
        int cnt = 0;
        for (auto& row : grid) {
            for (int x : row) {
                cnt += x > 0;
            }
        }
        int dirs[5] = {-1, 0, 1, 0, -1};
        function<pli(int, int)> dfs = [&](int i, int j) {
            long long s = grid[i][j];
            int t = 1;
            grid[i][j] = 0;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] > 0) {
                    auto [ss, tt] = dfs(x, y);
                    s += ss;
                    t += tt;
                }
            }
            return pli(s, t);
        };
        long long ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] > 0) {
                    auto [s, t] = dfs(i, j);
                    ans += (cnt - t) * s;
                }
            }
        }
        return ans;
    }
};
```

```go
func sumRemoteness(grid [][]int) (ans int64) {
	n := len(grid)
	cnt := 0
	for _, row := range grid {
		for _, x := range row {
			if x > 0 {
				cnt++
			}
		}
	}
	var dfs func(i, j int) (int, int)
	dfs = func(i, j int) (int, int) {
		s, t := grid[i][j], 1
		grid[i][j] = 0
		dirs := [5]int{-1, 0, 1, 0, -1}
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] > 0 {
				ss, tt := dfs(x, y)
				s += ss
				t += tt
			}
		}
		return s, t
	}
	for i := range grid {
		for j := range grid[i] {
			if grid[i][j] > 0 {
				s, t := dfs(i, j)
				ans += int64(cnt-t) * int64(s)
			}
		}
	}
	return
}
```

```ts
function sumRemoteness(grid: number[][]): number {
    const n = grid.length;
    let cnt = 0;
    for (const row of grid) {
        for (const x of row) {
            if (x > 0) {
                cnt++;
            }
        }
    }
    const dirs = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number): [number, number] => {
        let s = grid[i][j];
        let t = 1;
        grid[i][j] = 0;
        for (let k = 0; k < 4; ++k) {
            const [x, y] = [i + dirs[k], j + dirs[k + 1]];
            if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] > 0) {
                const [ss, tt] = dfs(x, y);
                s += ss;
                t += tt;
            }
        }
        return [s, t];
    };
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] > 0) {
                const [s, t] = dfs(i, j);
                ans += (cnt - t) * s;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
