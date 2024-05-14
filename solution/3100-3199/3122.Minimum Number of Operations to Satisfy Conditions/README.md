# [3122. 使矩阵满足条件的最少操作次数](https://leetcode.cn/problems/minimum-number-of-operations-to-satisfy-conditions)

[English Version](/solution/3100-3199/3122.Minimum%20Number%20of%20Operations%20to%20Satisfy%20Conditions/README_EN.md)

<!-- tags:数组,动态规划,矩阵 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>m x n</code>&nbsp;的二维矩形&nbsp;<code>grid</code>&nbsp;。每次 <strong>操作</strong>&nbsp;中，你可以将 <strong>任一</strong> 格子的值修改为 <strong>任意</strong>&nbsp;非负整数。完成所有操作后，你需要确保每个格子&nbsp;<code>grid[i][j]</code>&nbsp;的值满足：</p>

<ul>
	<li>如果下面相邻格子存在的话，它们的值相等，也就是&nbsp;<code>grid[i][j] == grid[i + 1][j]</code>（如果存在）。</li>
	<li>如果右边相邻格子存在的话，它们的值不相等，也就是&nbsp;<code>grid[i][j] != grid[i][j + 1]</code>（如果存在）。</li>
</ul>

<p>请你返回需要的 <strong>最少</strong>&nbsp;操作数目。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[1,0,2],[1,0,2]]</span></p>

<p><b>输出：</b>0</p>

<p><b>解释：</b></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3122.Minimum%20Number%20of%20Operations%20to%20Satisfy%20Conditions/images/examplechanged.png" style="width: 254px; height: 186px;padding: 10px; background: #fff; border-radius: .5rem;" /></strong></p>

<p>矩阵中所有格子已经满足要求。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[1,1,1],[0,0,0]]</span></p>

<p><b>输出：</b>3</p>

<p><strong>解释：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3122.Minimum%20Number%20of%20Operations%20to%20Satisfy%20Conditions/images/example21.png" style="width: 254px; height: 186px;padding: 10px; background: #fff; border-radius: .5rem;" /></strong></p>

<p>将矩阵变成&nbsp;<code>[[1,0,1],[1,0,1]]</code>&nbsp;，它满足所有要求，需要 3 次操作：</p>

<ul>
	<li>将&nbsp;<code>grid[1][0]</code>&nbsp;变为 1 。</li>
	<li>将&nbsp;<code>grid[0][1]</code> 变为 0 。</li>
	<li>将&nbsp;<code>grid[1][2]</code>&nbsp;变为 1 。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[1],[2],[3]]</span></p>

<p><b>输出：</b>2</p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3122.Minimum%20Number%20of%20Operations%20to%20Satisfy%20Conditions/images/changed.png" style="width: 86px; height: 277px;padding: 10px; background: #fff; border-radius: .5rem;" /></p>

<p>这个矩阵只有一列，我们可以通过 2 次操作将所有格子里的值变为 1 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n, m &lt;= 1000</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 9</code></li>
</ul>

## 解法

### 方法一：动态规划

我们注意到，矩阵中格子的值只有 $10$ 种可能，题目需要我们求出每一列数字相同，且相邻列数字不同的最小操作次数。那么我们只需要考虑将数字修改为 $0$ 到 $9$ 的情况即可。

我们定义状态 $f[i][j]$ 表示前 $[0,..i]$ 列数字，且第 $i$ 列数字为 $j$ 的最小操作次数。那么我们可以得到状态转移方程：

$$
f[i][j] = \min_{k \neq j} f[i-1][k] + m - \text{cnt}[j]
$$

其中 $\text{cnt}[j]$ 表示第 $i$ 列数字为 $j$ 的个数。

最后我们只需要求出 $f[n-1][j]$ 的最小值即可。

时间复杂度 $O(n \times (m + C^2))$，空间复杂度 $O(n \times C)$。其中 $m$ 和 $n$ 分别表示矩阵的行数和列数；而 $C$ 表示数字的种类数，这里 $C = 10$。

<!-- tabs:start -->

```python
class Solution:
    def minimumOperations(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        f = [[inf] * 10 for _ in range(n)]
        for i in range(n):
            cnt = [0] * 10
            for j in range(m):
                cnt[grid[j][i]] += 1
            if i == 0:
                for j in range(10):
                    f[i][j] = m - cnt[j]
            else:
                for j in range(10):
                    for k in range(10):
                        if k != j:
                            f[i][j] = min(f[i][j], f[i - 1][k] + m - cnt[j])
        return min(f[-1])
```

```java
class Solution {
    public int minimumOperations(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[n][10];
        final int inf = 1 << 29;
        for (var g : f) {
            Arrays.fill(g, inf);
        }
        for (int i = 0; i < n; ++i) {
            int[] cnt = new int[10];
            for (int j = 0; j < m; ++j) {
                ++cnt[grid[j][i]];
            }
            if (i == 0) {
                for (int j = 0; j < 10; ++j) {
                    f[i][j] = m - cnt[j];
                }
            } else {
                for (int j = 0; j < 10; ++j) {
                    for (int k = 0; k < 10; ++k) {
                        if (k != j) {
                            f[i][j] = Math.min(f[i][j], f[i - 1][k] + m - cnt[j]);
                        }
                    }
                }
            }
        }
        int ans = inf;
        for (int j = 0; j < 10; ++j) {
            ans = Math.min(ans, f[n - 1][j]);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumOperations(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int f[n][10];
        memset(f, 0x3f, sizeof(f));
        for (int i = 0; i < n; ++i) {
            int cnt[10]{};
            for (int j = 0; j < m; ++j) {
                ++cnt[grid[j][i]];
            }
            if (i == 0) {
                for (int j = 0; j < 10; ++j) {
                    f[i][j] = m - cnt[j];
                }
            } else {
                for (int j = 0; j < 10; ++j) {
                    for (int k = 0; k < 10; ++k) {
                        if (k != j) {
                            f[i][j] = min(f[i][j], f[i - 1][k] + m - cnt[j]);
                        }
                    }
                }
            }
        }
        return *min_element(f[n - 1], f[n - 1] + 10);
    }
};
```

```go
func minimumOperations(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, 10)
		for j := range f[i] {
			f[i][j] = 1 << 29
		}
	}
	for i := 0; i < n; i++ {
		cnt := [10]int{}
		for j := 0; j < m; j++ {
			cnt[grid[j][i]]++
		}
		if i == 0 {
			for j := 0; j < 10; j++ {
				f[i][j] = m - cnt[j]
			}
		} else {
			for j := 0; j < 10; j++ {
				for k := 0; k < 10; k++ {
					if j != k {
						f[i][j] = min(f[i][j], f[i-1][k]+m-cnt[j])
					}
				}
			}
		}
	}
	return slices.Min(f[n-1])
}
```

```ts
function minimumOperations(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const f: number[][] = Array.from({ length: n }, () =>
        Array.from({ length: 10 }, () => Infinity),
    );
    for (let i = 0; i < n; ++i) {
        const cnt: number[] = Array(10).fill(0);
        for (let j = 0; j < m; ++j) {
            cnt[grid[j][i]]++;
        }
        if (i === 0) {
            for (let j = 0; j < 10; ++j) {
                f[i][j] = m - cnt[j];
            }
        } else {
            for (let j = 0; j < 10; ++j) {
                for (let k = 0; k < 10; ++k) {
                    if (j !== k) {
                        f[i][j] = Math.min(f[i][j], f[i - 1][k] + m - cnt[j]);
                    }
                }
            }
        }
    }
    return Math.min(...f[n - 1]);
}
```

<!-- tabs:end -->

<!-- end -->
