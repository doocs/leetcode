---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0741.Cherry%20Pickup/README.md
tags:
    - 数组
    - 动态规划
    - 矩阵
---

<!-- problem:start -->

# [741. 摘樱桃](https://leetcode.cn/problems/cherry-pickup)

[English Version](/solution/0700-0799/0741.Cherry%20Pickup/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <code>n x n</code> 的网格 <code>grid</code> ，代表一块樱桃地，每个格子由以下三种数字的一种来表示：</p>

<ul>
	<li><code>0</code> 表示这个格子是空的，所以你可以穿过它。</li>
	<li><code>1</code> 表示这个格子里装着一个樱桃，你可以摘到樱桃然后穿过它。</li>
	<li><code>-1</code> 表示这个格子里有荆棘，挡着你的路。</li>
</ul>

<p>请你统计并返回：在遵守下列规则的情况下，能摘到的最多樱桃数：</p>

<ul>
	<li>从位置&nbsp;<code>(0, 0)</code> 出发，最后到达 <code>(n - 1, n - 1)</code> ，只能向下或向右走，并且只能穿越有效的格子（即只可以穿过值为 <code>0</code> 或者 <code>1</code> 的格子）；</li>
	<li>当到达 <code>(n - 1, n&nbsp;- 1)</code> 后，你要继续走，直到返回到 <code>(0, 0) </code>，只能向上或向左走，并且只能穿越有效的格子；</li>
	<li>当你经过一个格子且这个格子包含一个樱桃时，你将摘到樱桃并且这个格子会变成空的（值变为 <code>0</code> ）；</li>
	<li>如果在 <code>(0, 0)</code> 和 <code>(n - 1, n - 1)</code> 之间不存在一条可经过的路径，则无法摘到任何一个樱桃。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0741.Cherry%20Pickup/images/grid.jpg" />
<pre>
<b>输入：</b>grid = [[0,1,-1],[1,0,-1],[1,1,1]]
<b>输出：</b>5
<b>解释：</b>玩家从 (0, 0) 出发：向下、向下、向右、向右移动至 (2, 2) 。
在这一次行程中捡到 4 个樱桃，矩阵变成 [[0,1,-1],[0,0,-1],[0,0,0]] 。
然后，玩家向左、向上、向上、向左返回起点，再捡到 1 个樱桃。
总共捡到 5 个樱桃，这是最大可能值。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>grid = [[1,1,-1],[1,-1,1],[-1,1,1]]
<b>输出：</b>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>grid[i][j]</code>&nbsp;为&nbsp;<code>-1</code>、<code>0</code>&nbsp;或&nbsp;<code>1</code></li>
	<li><code>grid[0][0] != -1</code></li>
	<li><code>grid[n - 1][n - 1] != -1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

根据题目描述，玩家从 $(0, 0)$ 出发，到达 $(n-1, n-1)$ 后，又重新返回到起始点 $(0, 0)$，我们可以视为玩家两次从 $(0, 0)$ 出发，到达 $(n-1, n-1)$。

因此，我们定义 $f[k][i_1][i_2]$ 表示两次都走了 $k$ 步，分别到达 $(i_1, k-i_1)$ 和 $(i_2, k-i_2)$ 时，能够摘到的最多樱桃数。初始时 $f[0][0][0] = grid[0][0]$。其余 $f[k][i_1][i_2]$ 的初始值为负无穷。答案为 $\max(0, f[2n-2][n-1][n-1])$。

我们可以根据题目描述，得到状态转移方程：

$$
f[k][i_1][i_2] = \max(f[k-1][x_1][x_2] + t, f[k][i_1][i_2])
$$

其中 $t$ 表示 $(i_1, k-i_1)$ 和 $(i_2, k-i_2)$ 位置上的樱桃数，而 $x_1, x_2$ 分别表示 $(i_1, k-i_1)$ 和 $(i_2, k-i_2)$ 的前一步位置。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^3)$。其中 $n$ 表示网格的边长。

<!-- tabs:start -->

```python
class Solution:
    def cherryPickup(self, grid: List[List[int]]) -> int:
        n = len(grid)
        f = [[[-inf] * n for _ in range(n)] for _ in range((n << 1) - 1)]
        f[0][0][0] = grid[0][0]
        for k in range(1, (n << 1) - 1):
            for i1 in range(n):
                for i2 in range(n):
                    j1, j2 = k - i1, k - i2
                    if (
                        not 0 <= j1 < n
                        or not 0 <= j2 < n
                        or grid[i1][j1] == -1
                        or grid[i2][j2] == -1
                    ):
                        continue
                    t = grid[i1][j1]
                    if i1 != i2:
                        t += grid[i2][j2]
                    for x1 in range(i1 - 1, i1 + 1):
                        for x2 in range(i2 - 1, i2 + 1):
                            if x1 >= 0 and x2 >= 0:
                                f[k][i1][i2] = max(f[k][i1][i2], f[k - 1][x1][x2] + t)
        return max(0, f[-1][-1][-1])
```

```java
class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] f = new int[n * 2][n][n];
        f[0][0][0] = grid[0][0];
        for (int k = 1; k < n * 2 - 1; ++k) {
            for (int i1 = 0; i1 < n; ++i1) {
                for (int i2 = 0; i2 < n; ++i2) {
                    int j1 = k - i1, j2 = k - i2;
                    f[k][i1][i2] = Integer.MIN_VALUE;
                    if (j1 < 0 || j1 >= n || j2 < 0 || j2 >= n || grid[i1][j1] == -1
                        || grid[i2][j2] == -1) {
                        continue;
                    }
                    int t = grid[i1][j1];
                    if (i1 != i2) {
                        t += grid[i2][j2];
                    }
                    for (int x1 = i1 - 1; x1 <= i1; ++x1) {
                        for (int x2 = i2 - 1; x2 <= i2; ++x2) {
                            if (x1 >= 0 && x2 >= 0) {
                                f[k][i1][i2] = Math.max(f[k][i1][i2], f[k - 1][x1][x2] + t);
                            }
                        }
                    }
                }
            }
        }
        return Math.max(0, f[n * 2 - 2][n - 1][n - 1]);
    }
}
```

```cpp
class Solution {
public:
    int cherryPickup(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<vector<vector<int>>> f(n << 1, vector<vector<int>>(n, vector<int>(n, -1e9)));
        f[0][0][0] = grid[0][0];
        for (int k = 1; k < n * 2 - 1; ++k) {
            for (int i1 = 0; i1 < n; ++i1) {
                for (int i2 = 0; i2 < n; ++i2) {
                    int j1 = k - i1, j2 = k - i2;
                    if (j1 < 0 || j1 >= n || j2 < 0 || j2 >= n || grid[i1][j1] == -1 || grid[i2][j2] == -1) {
                        continue;
                    }
                    int t = grid[i1][j1];
                    if (i1 != i2) {
                        t += grid[i2][j2];
                    }
                    for (int x1 = i1 - 1; x1 <= i1; ++x1) {
                        for (int x2 = i2 - 1; x2 <= i2; ++x2) {
                            if (x1 >= 0 && x2 >= 0) {
                                f[k][i1][i2] = max(f[k][i1][i2], f[k - 1][x1][x2] + t);
                            }
                        }
                    }
                }
            }
        }
        return max(0, f[n * 2 - 2][n - 1][n - 1]);
    }
};
```

```go
func cherryPickup(grid [][]int) int {
	n := len(grid)
	f := make([][][]int, (n<<1)-1)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, n)
		}
	}
	f[0][0][0] = grid[0][0]
	for k := 1; k < (n<<1)-1; k++ {
		for i1 := 0; i1 < n; i1++ {
			for i2 := 0; i2 < n; i2++ {
				f[k][i1][i2] = int(-1e9)
				j1, j2 := k-i1, k-i2
				if j1 < 0 || j1 >= n || j2 < 0 || j2 >= n || grid[i1][j1] == -1 || grid[i2][j2] == -1 {
					continue
				}
				t := grid[i1][j1]
				if i1 != i2 {
					t += grid[i2][j2]
				}
				for x1 := i1 - 1; x1 <= i1; x1++ {
					for x2 := i2 - 1; x2 <= i2; x2++ {
						if x1 >= 0 && x2 >= 0 {
							f[k][i1][i2] = max(f[k][i1][i2], f[k-1][x1][x2]+t)
						}
					}
				}
			}
		}
	}
	return max(0, f[n*2-2][n-1][n-1])
}
```

```ts
function cherryPickup(grid: number[][]): number {
    const n: number = grid.length;
    const f: number[][][] = Array.from({ length: n * 2 - 1 }, () =>
        Array.from({ length: n }, () => Array.from({ length: n }, () => -Infinity)),
    );
    f[0][0][0] = grid[0][0];
    for (let k = 1; k < n * 2 - 1; ++k) {
        for (let i1 = 0; i1 < n; ++i1) {
            for (let i2 = 0; i2 < n; ++i2) {
                const [j1, j2]: [number, number] = [k - i1, k - i2];
                if (
                    j1 < 0 ||
                    j1 >= n ||
                    j2 < 0 ||
                    j2 >= n ||
                    grid[i1][j1] == -1 ||
                    grid[i2][j2] == -1
                ) {
                    continue;
                }
                const t: number = grid[i1][j1] + (i1 != i2 ? grid[i2][j2] : 0);
                for (let x1 = i1 - 1; x1 <= i1; ++x1) {
                    for (let x2 = i2 - 1; x2 <= i2; ++x2) {
                        if (x1 >= 0 && x2 >= 0) {
                            f[k][i1][i2] = Math.max(f[k][i1][i2], f[k - 1][x1][x2] + t);
                        }
                    }
                }
            }
        }
    }
    return Math.max(0, f[n * 2 - 2][n - 1][n - 1]);
}
```

```js
/**
 * @param {number[][]} grid
 * @return {number}
 */
var cherryPickup = function (grid) {
    const n = grid.length;
    const f = Array.from({ length: n * 2 - 1 }, () =>
        Array.from({ length: n }, () => Array.from({ length: n }, () => -Infinity)),
    );
    f[0][0][0] = grid[0][0];
    for (let k = 1; k < n * 2 - 1; ++k) {
        for (let i1 = 0; i1 < n; ++i1) {
            for (let i2 = 0; i2 < n; ++i2) {
                const [j1, j2] = [k - i1, k - i2];
                if (
                    j1 < 0 ||
                    j1 >= n ||
                    j2 < 0 ||
                    j2 >= n ||
                    grid[i1][j1] == -1 ||
                    grid[i2][j2] == -1
                ) {
                    continue;
                }
                const t = grid[i1][j1] + (i1 != i2 ? grid[i2][j2] : 0);
                for (let x1 = i1 - 1; x1 <= i1; ++x1) {
                    for (let x2 = i2 - 1; x2 <= i2; ++x2) {
                        if (x1 >= 0 && x2 >= 0) {
                            f[k][i1][i2] = Math.max(f[k][i1][i2], f[k - 1][x1][x2] + t);
                        }
                    }
                }
            }
        }
    }
    return Math.max(0, f[n * 2 - 2][n - 1][n - 1]);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
