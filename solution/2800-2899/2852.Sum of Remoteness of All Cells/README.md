# [2852. Sum of Remoteness of All Cells](https://leetcode.cn/problems/sum-of-remoteness-of-all-cells)

[English Version](/solution/2800-2899/2852.Sum%20of%20Remoteness%20of%20All%20Cells/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given a <strong>0-indexed</strong> matrix <code>grid</code> of order <code>n * n</code>. Each cell in this matrix has a value <code>grid[i][j]</code>, which is either a <strong>positive</strong> integer or <code>-1</code> representing a blocked cell.</p>

<p>You can move from a non-blocked cell to any non-blocked cell that shares an edge.</p>

<p>For any cell <code>(i, j)</code>, we represent its <strong>remoteness</strong> as <code>R[i][j]</code> which is defined as the following:</p>

<ul>
	<li>If the cell <code>(i, j)</code> is a <strong>non-blocked</strong> cell, <code>R[i][j]</code> is the sum of the values <code>grid[x][y]</code> such that there is <strong>no path</strong> from the <strong>non-blocked</strong> cell <code>(x, y)</code> to the cell <code>(i, j)</code>.</li>
	<li>For blocked cells, <code>R[i][j] == 0</code>.</li>
</ul>

<p>Return<em> the sum of </em><code>R[i][j]</code><em> over all cells.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2852.Sum%20of%20Remoteness%20of%20All%20Cells/images/1-new.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 400px; height: 304px;" /></p>

<pre>
<strong>Input:</strong> grid = [[-1,1,-1],[5,-1,4],[-1,3,-1]]
<strong>Output:</strong> 39
<strong>Explanation:</strong> In the picture above, there are four grids. The top-left grid contains the initial values in the grid. Blocked cells are colored black, and other cells get their values as it is in the input. In the top-right grid, you can see the value of R[i][j] for all cells. So the answer would be the sum of them. That is: 0 + 12 + 0 + 8 + 0 + 9 + 0 + 10 + 0 = 39.
Let&#39;s jump on the bottom-left grid in the above picture and calculate R[0][1] (the target cell is colored green). We should sum up the value of cells that can&#39;t be reached by the cell (0, 1). These cells are colored yellow in this grid. So R[0][1] = 5 + 4 + 3 = 12.
Now let&#39;s jump on the bottom-right grid in the above picture and calculate R[1][2] (the target cell is colored green). We should sum up the value of cells that can&#39;t be reached by the cell (1, 2). These cells are colored yellow in this grid. So R[1][2] = 1 + 5 + 3 = 9.
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2852.Sum%20of%20Remoteness%20of%20All%20Cells/images/2.png" style="width: 400px; height: 302px; background: #fff; border-radius: .5rem;" /></p>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[-1,3,4],[-1,-1,-1],[3,-1,-1]]
<strong>Output:</strong> 13
<strong>Explanation:</strong> In the picture above, there are four grids. The top-left grid contains the initial values in the grid. Blocked cells are colored black, and other cells get their values as it is in the input. In the top-right grid, you can see the value of R[i][j] for all cells. So the answer would be the sum of them. That is: 3 + 3 + 0 + 0 + 0 + 0 + 7 + 0 + 0 = 13.
Let&#39;s jump on the bottom-left grid in the above picture and calculate R[0][2] (the target cell is colored green). We should sum up the value of cells that can&#39;t be reached by the cell (0, 2). This cell is colored yellow in this grid. So R[0][2] = 3.
Now let&#39;s jump on the bottom-right grid in the above picture and calculate R[2][0] (the target cell is colored green). We should sum up the value of cells that can&#39;t be reached by the cell (2, 0). These cells are colored yellow in this grid. So R[2][0] = 3 + 4 = 7.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Since there are no other cells than (0, 0), R[0][0] is equal to 0. So the sum of R[i][j] over all cells would be 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 300</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>6</sup></code> or <code>grid[i][j] == -1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS**

我们先统计矩阵中非阻塞的格子的个数，记为 $cnt$，然后从每个非阻塞的格子出发，使用 DFS 计算出每个连通块中格子之和 $s$ 以及格子个数 $t$，那么其它连通块的所有 $(cnt - t)$ 个格子都可以累加上 $s$。我们累加所有连通块的结果即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是矩阵的边长。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

### **TypeScript**

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

### **...**

```

```

<!-- tabs:end -->
