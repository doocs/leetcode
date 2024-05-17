---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2132.Stamping%20the%20Grid/README.md
rating: 2364
source: 第 69 场双周赛 Q4
tags:
    - 贪心
    - 数组
    - 矩阵
    - 前缀和
---

<!-- problem:start -->

# [2132. 用邮票贴满网格图](https://leetcode.cn/problems/stamping-the-grid)

[English Version](/solution/2100-2199/2132.Stamping%20the%20Grid/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个&nbsp;<code>m x n</code>&nbsp;的二进制矩阵&nbsp;<code>grid</code>&nbsp;，每个格子要么为&nbsp;<code>0</code>&nbsp;（空）要么为&nbsp;<code>1</code>&nbsp;（被占据）。</p>

<p>给你邮票的尺寸为&nbsp;<code>stampHeight x stampWidth</code>&nbsp;。我们想将邮票贴进二进制矩阵中，且满足以下&nbsp;<strong>限制</strong>&nbsp;和&nbsp;<strong>要求</strong>&nbsp;：</p>

<ol>
	<li>覆盖所有 <strong>空</strong>&nbsp;格子。</li>
	<li>不覆盖任何 <strong>被占据&nbsp;</strong>的格子。</li>
	<li>我们可以放入任意数目的邮票。</li>
	<li>邮票可以相互有 <strong>重叠</strong>&nbsp;部分。</li>
	<li>邮票不允许 <strong>旋转</strong>&nbsp;。</li>
	<li>邮票必须完全在矩阵 <strong>内</strong>&nbsp;。</li>
</ol>

<p>如果在满足上述要求的前提下，可以放入邮票，请返回&nbsp;<code>true</code>&nbsp;，否则返回<i>&nbsp;</i><code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2132.Stamping%20the%20Grid/images/ex1.png" style="width: 180px; height: 237px;"></p>

<pre><b>输入：</b>grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 4, stampWidth = 3
<b>输出：</b>true
<b>解释：</b>我们放入两个有重叠部分的邮票（图中标号为 1 和 2），它们能覆盖所有与空格子。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2132.Stamping%20the%20Grid/images/ex2.png" style="width: 170px; height: 179px;"></p>

<pre><b>输入：</b>grid = [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]], stampHeight = 2, stampWidth = 2 
<b>输出：</b>false 
<b>解释：</b>没办法放入邮票覆盖所有的空格子，且邮票不超出网格图以外。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[r].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>grid[r][c]</code> 要么是&nbsp;<code>0</code>&nbsp;，要么是&nbsp;<code>1</code> 。</li>
	<li><code>1 &lt;= stampHeight, stampWidth &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二维前缀和 + 二维差分

根据题目描述，每一个空格子都必须被邮票覆盖，而且不能覆盖任何被占据的格子。因此，我们可以遍历二维矩阵，对于每个格子，如果以该格子为左上角的 $stampHeight \times stampWidth$ 的区域内的所有格子都是空格子（即没有被占据），那么我们就可以在该格子处放置一个邮票。

为了快速判断一个区域内的所有格子是否都是空格子，我们可以使用二维前缀和。我们用 $s_{i,j}$ 表示二维矩阵中从 $(1,1)$ 到 $(i,j)$ 的子矩阵中被占据的格子的数量。即 $s_{i, j} = s_{i - 1, j} + s_{i, j - 1} - s_{i - 1, j - 1} + grid_{i-1, j-1}$。

那么以 $(i, j)$ 为左上角，且高度和宽度分别为 $stampHeight$ 和 $stampWidth$ 的子矩阵的右下角坐标为 $(x, y) = (i + stampHeight - 1, j + stampWidth - 1)$，我们可以通过 $s_{x, y} - s_{x, j - 1} - s_{i - 1, y} + s_{i - 1, j - 1}$ 来计算出该子矩阵中被占据的格子的数量。如果该子矩阵中被占据的格子的数量为 $0$，那么我们就可以在 $(i, j)$ 处放置一个邮票，放置邮票后，这 $stampHeight \times stampWidth$ 的区域内的所有格子都会变成被占据的格子，我们可以用二维差分数组 $d$ 来记录这一变化。即：

$$
\begin{aligned}
d_{i, j} &\leftarrow d_{i, j} + 1 \\
d_{i, y + 1} &\leftarrow d_{i, y + 1} - 1 \\
d_{x + 1, j} &\leftarrow d_{x + 1, j} - 1 \\
d_{x + 1, y + 1} &\leftarrow d_{x + 1, y + 1} + 1
\end{aligned}
$$

最后，我们对二维差分数组 $d$ 进行前缀和运算，可以得出每个格子被邮票覆盖的次数。如果某个格子没有被占据，且被邮票覆盖的次数为 $0$，那么我们就无法将邮票放置在该格子处，因此我们需要返回 $\texttt{false}$。如果所有“没被占据的格子”都成功被邮票覆盖，返回 $\texttt{true}$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是二维矩阵的高度和宽度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def possibleToStamp(
        self, grid: List[List[int]], stampHeight: int, stampWidth: int
    ) -> bool:
        m, n = len(grid), len(grid[0])
        s = [[0] * (n + 1) for _ in range(m + 1)]
        for i, row in enumerate(grid, 1):
            for j, v in enumerate(row, 1):
                s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + v
        d = [[0] * (n + 2) for _ in range(m + 2)]
        for i in range(1, m - stampHeight + 2):
            for j in range(1, n - stampWidth + 2):
                x, y = i + stampHeight - 1, j + stampWidth - 1
                if s[x][y] - s[x][j - 1] - s[i - 1][y] + s[i - 1][j - 1] == 0:
                    d[i][j] += 1
                    d[i][y + 1] -= 1
                    d[x + 1][j] -= 1
                    d[x + 1][y + 1] += 1
        for i, row in enumerate(grid, 1):
            for j, v in enumerate(row, 1):
                d[i][j] += d[i - 1][j] + d[i][j - 1] - d[i - 1][j - 1]
                if v == 0 and d[i][j] == 0:
                    return False
        return True
```

#### Java

```java
class Solution {
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length, n = grid[0].length;
        int[][] s = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }
        int[][] d = new int[m + 2][n + 2];
        for (int i = 1; i + stampHeight - 1 <= m; ++i) {
            for (int j = 1; j + stampWidth - 1 <= n; ++j) {
                int x = i + stampHeight - 1, y = j + stampWidth - 1;
                if (s[x][y] - s[x][j - 1] - s[i - 1][y] + s[i - 1][j - 1] == 0) {
                    d[i][j]++;
                    d[i][y + 1]--;
                    d[x + 1][j]--;
                    d[x + 1][y + 1]++;
                }
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                d[i][j] += d[i - 1][j] + d[i][j - 1] - d[i - 1][j - 1];
                if (grid[i - 1][j - 1] == 0 && d[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool possibleToStamp(vector<vector<int>>& grid, int stampHeight, int stampWidth) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> s(m + 1, vector<int>(n + 1));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }

        vector<vector<int>> d(m + 2, vector<int>(n + 2));
        for (int i = 1; i + stampHeight - 1 <= m; ++i) {
            for (int j = 1; j + stampWidth - 1 <= n; ++j) {
                int x = i + stampHeight - 1, y = j + stampWidth - 1;
                if (s[x][y] - s[x][j - 1] - s[i - 1][y] + s[i - 1][j - 1] == 0) {
                    d[i][j]++;
                    d[i][y + 1]--;
                    d[x + 1][j]--;
                    d[x + 1][y + 1]++;
                }
            }
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                d[i][j] += d[i - 1][j] + d[i][j - 1] - d[i - 1][j - 1];
                if (grid[i - 1][j - 1] == 0 && d[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
};
```

#### Go

```go
func possibleToStamp(grid [][]int, stampHeight int, stampWidth int) bool {
	m, n := len(grid), len(grid[0])
	s := make([][]int, m+1)
	for i := range s {
		s[i] = make([]int, n+1)
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			s[i][j] = s[i-1][j] + s[i][j-1] - s[i-1][j-1] + grid[i-1][j-1]
		}
	}

	d := make([][]int, m+2)
	for i := range d {
		d[i] = make([]int, n+2)
	}

	for i := 1; i+stampHeight-1 <= m; i++ {
		for j := 1; j+stampWidth-1 <= n; j++ {
			x, y := i+stampHeight-1, j+stampWidth-1
			if s[x][y]-s[x][j-1]-s[i-1][y]+s[i-1][j-1] == 0 {
				d[i][j]++
				d[i][y+1]--
				d[x+1][j]--
				d[x+1][y+1]++
			}
		}
	}

	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			d[i][j] += d[i-1][j] + d[i][j-1] - d[i-1][j-1]
			if grid[i-1][j-1] == 0 && d[i][j] == 0 {
				return false
			}
		}
	}
	return true
}
```

#### TypeScript

```ts
function possibleToStamp(grid: number[][], stampHeight: number, stampWidth: number): boolean {
    const m = grid.length;
    const n = grid[0].length;
    const s: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + grid[i - 1][j - 1];
        }
    }

    const d: number[][] = Array.from({ length: m + 2 }, () => Array(n + 2).fill(0));
    for (let i = 1; i + stampHeight - 1 <= m; ++i) {
        for (let j = 1; j + stampWidth - 1 <= n; ++j) {
            const [x, y] = [i + stampHeight - 1, j + stampWidth - 1];
            if (s[x][y] - s[x][j - 1] - s[i - 1][y] + s[i - 1][j - 1] === 0) {
                d[i][j]++;
                d[i][y + 1]--;
                d[x + 1][j]--;
                d[x + 1][y + 1]++;
            }
        }
    }

    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            d[i][j] += d[i - 1][j] + d[i][j - 1] - d[i - 1][j - 1];
            if (grid[i - 1][j - 1] === 0 && d[i][j] === 0) {
                return false;
            }
        }
    }
    return true;
}
```

#### Rust

```rust
impl Solution {
    pub fn possible_to_stamp(grid: Vec<Vec<i32>>, stamp_height: i32, stamp_width: i32) -> bool {
        let n: usize = grid.len();
        let m: usize = grid[0].len();

        let mut prefix_vec: Vec<Vec<i32>> = vec![vec![0; m + 1]; n + 1];

        // Initialize the prefix vector
        for i in 0..n {
            for j in 0..m {
                prefix_vec[i + 1][j + 1] =
                    prefix_vec[i][j + 1] + prefix_vec[i + 1][j] - prefix_vec[i][j] + grid[i][j];
            }
        }

        let mut diff_vec: Vec<Vec<i32>> = vec![vec![0; m + 1]; n + 1];

        // Construct the difference vector based on prefix sum vector
        for i in 0..n {
            for j in 0..m {
                // If the value of the cell is one, just bypass this
                if grid[i][j] == 1 {
                    continue;
                }
                // Otherwise, try stick the stamp
                let x: usize = i + (stamp_height as usize);
                let y: usize = j + (stamp_width as usize);
                // Check the bound
                if x <= n && y <= m {
                    // If the region can be sticked (All cells are empty, which means the sum will be zero)
                    if
                        prefix_vec[x][y] - prefix_vec[x][j] - prefix_vec[i][y] + prefix_vec[i][j] ==
                        0
                    {
                        // Update the difference vector
                        diff_vec[i][j] += 1;
                        diff_vec[x][y] += 1;

                        diff_vec[x][j] -= 1;
                        diff_vec[i][y] -= 1;
                    }
                }
            }
        }

        let mut check_vec: Vec<Vec<i32>> = vec![vec![0; m + 1]; n + 1];

        // Check the prefix sum of difference vector, to determine if there is any empty cell left
        for i in 0..n {
            for j in 0..m {
                // If the value of the cell is one, just bypass this
                if grid[i][j] == 1 {
                    continue;
                }
                // Otherwise, check if the region is empty, by calculating the prefix sum of difference vector
                check_vec[i + 1][j + 1] =
                    check_vec[i][j + 1] + check_vec[i + 1][j] - check_vec[i][j] + diff_vec[i][j];
                if check_vec[i + 1][j + 1] == 0 {
                    return false;
                }
            }
        }

        true
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} grid
 * @param {number} stampHeight
 * @param {number} stampWidth
 * @return {boolean}
 */
var possibleToStamp = function (grid, stampHeight, stampWidth) {
    const m = grid.length;
    const n = grid[0].length;
    const s = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + grid[i - 1][j - 1];
        }
    }

    const d = Array.from({ length: m + 2 }, () => Array(n + 2).fill(0));
    for (let i = 1; i + stampHeight - 1 <= m; ++i) {
        for (let j = 1; j + stampWidth - 1 <= n; ++j) {
            const [x, y] = [i + stampHeight - 1, j + stampWidth - 1];
            if (s[x][y] - s[x][j - 1] - s[i - 1][y] + s[i - 1][j - 1] === 0) {
                d[i][j]++;
                d[i][y + 1]--;
                d[x + 1][j]--;
                d[x + 1][y + 1]++;
            }
        }
    }

    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            d[i][j] += d[i - 1][j] + d[i][j - 1] - d[i - 1][j - 1];
            if (grid[i - 1][j - 1] === 0 && d[i][j] === 0) {
                return false;
            }
        }
    }
    return true;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
