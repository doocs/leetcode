---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3212.Count%20Submatrices%20With%20Equal%20Frequency%20of%20X%20and%20Y/README.md
---

<!-- problem:start -->

# [3212. 统计 X 和 Y 频数相等的子矩阵数量](https://leetcode.cn/problems/count-submatrices-with-equal-frequency-of-x-and-y)

[English Version](/solution/3200-3299/3212.Count%20Submatrices%20With%20Equal%20Frequency%20of%20X%20and%20Y/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二维字符矩阵 <code>grid</code>，其中 <code>grid[i][j]</code> 可能是 <code>'X'</code>、<code>'Y'</code> 或 <code>'.'</code>，返回满足以下条件的<span data-keyword="submatrix">子矩阵</span>数量：</p>

<ul>
	<li>包含 <code>grid[0][0]</code></li>
	<li><code>'X'</code> 和 <code>'Y'</code> 的频数相等。</li>
	<li>至少包含一个 <code>'X'</code>。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [["X","Y","."],["Y",".","."]]</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3212.Count%20Submatrices%20With%20Equal%20Frequency%20of%20X%20and%20Y/images/examplems.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 175px; height: 350px;" /></strong></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [["X","X"],["X","Y"]]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>不存在满足 <code>'X'</code> 和 <code>'Y'</code> 频数相等的子矩阵。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [[".","."],[".","."]]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>不存在满足至少包含一个 <code>'X'</code> 的子矩阵。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= grid.length, grid[i].length &lt;= 1000</code></li>
	<li><code>grid[i][j]</code> 可能是 <code>'X'</code>、<code>'Y'</code> 或 <code>'.'</code>.</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二维前缀和

根据题目描述，我们只需要统计每个位置 $(i, j)$ 的前缀和 $s[i][j][0]$ 和 $s[i][j][1]$，分别表示从 $(0, 0)$ 到 $(i, j)$ 的子矩阵中字符 `X` 和 `Y` 的数量，如果 $s[i][j][0] > 0$ 且 $s[i][j][0] = s[i][j][1]$，则说明满足题目条件，答案加一。

遍历完所有位置后，返回答案即可。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别表示矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfSubmatrices(self, grid: List[List[str]]) -> int:
        m, n = len(grid), len(grid[0])
        s = [[[0] * 2 for _ in range(n + 1)] for _ in range(m + 1)]
        ans = 0
        for i, row in enumerate(grid, 1):
            for j, x in enumerate(row, 1):
                s[i][j][0] = s[i - 1][j][0] + s[i][j - 1][0] - s[i - 1][j - 1][0]
                s[i][j][1] = s[i - 1][j][1] + s[i][j - 1][1] - s[i - 1][j - 1][1]
                if x != ".":
                    s[i][j][ord(x) & 1] += 1
                if s[i][j][0] > 0 and s[i][j][0] == s[i][j][1]:
                    ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][][] s = new int[m + 1][n + 1][2];
        int ans = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                s[i][j][0] = s[i - 1][j][0] + s[i][j - 1][0] - s[i - 1][j - 1][0]
                    + (grid[i - 1][j - 1] == 'X' ? 1 : 0);
                s[i][j][1] = s[i - 1][j][1] + s[i][j - 1][1] - s[i - 1][j - 1][1]
                    + (grid[i - 1][j - 1] == 'Y' ? 1 : 0);
                if (s[i][j][0] > 0 && s[i][j][0] == s[i][j][1]) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfSubmatrices(vector<vector<char>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<vector<int>>> s(m + 1, vector<vector<int>>(n + 1, vector<int>(2)));
        int ans = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                s[i][j][0] = s[i - 1][j][0] + s[i][j - 1][0] - s[i - 1][j - 1][0]
                    + (grid[i - 1][j - 1] == 'X' ? 1 : 0);
                s[i][j][1] = s[i - 1][j][1] + s[i][j - 1][1] - s[i - 1][j - 1][1]
                    + (grid[i - 1][j - 1] == 'Y' ? 1 : 0);
                if (s[i][j][0] > 0 && s[i][j][0] == s[i][j][1]) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfSubmatrices(grid [][]byte) (ans int) {
	m, n := len(grid), len(grid[0])
	s := make([][][]int, m+1)
	for i := range s {
		s[i] = make([][]int, n+1)
		for j := range s[i] {
			s[i][j] = make([]int, 2)
		}
	}

	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			s[i][j][0] = s[i-1][j][0] + s[i][j-1][0] - s[i-1][j-1][0]
			if grid[i-1][j-1] == 'X' {
				s[i][j][0]++
			}
			s[i][j][1] = s[i-1][j][1] + s[i][j-1][1] - s[i-1][j-1][1]
			if grid[i-1][j-1] == 'Y' {
				s[i][j][1]++
			}
			if s[i][j][0] > 0 && s[i][j][0] == s[i][j][1] {
				ans++
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function numberOfSubmatrices(grid: string[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    const s = Array.from({ length: m + 1 }, () => Array.from({ length: n + 1 }, () => [0, 0]));
    let ans = 0;

    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            s[i][j][0] =
                s[i - 1][j][0] +
                s[i][j - 1][0] -
                s[i - 1][j - 1][0] +
                (grid[i - 1][j - 1] === 'X' ? 1 : 0);
            s[i][j][1] =
                s[i - 1][j][1] +
                s[i][j - 1][1] -
                s[i - 1][j - 1][1] +
                (grid[i - 1][j - 1] === 'Y' ? 1 : 0);
            if (s[i][j][0] > 0 && s[i][j][0] === s[i][j][1]) {
                ++ans;
            }
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
