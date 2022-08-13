# [688. 骑士在棋盘上的概率](https://leetcode.cn/problems/knight-probability-in-chessboard)

[English Version](/solution/0600-0699/0688.Knight%20Probability%20in%20Chessboard/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个&nbsp;<code>n x n</code>&nbsp;的国际象棋棋盘上，一个骑士从单元格 <code>(row, column)</code>&nbsp;开始，并尝试进行 <code>k</code> 次移动。行和列是 <strong>从 0 开始</strong> 的，所以左上单元格是 <code>(0,0)</code> ，右下单元格是 <code>(n - 1, n - 1)</code> 。</p>

<p>象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0688.Knight%20Probability%20in%20Chessboard/images/knight.png" style="height: 300px; width: 300px;" /></p>

<p>每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。</p>

<p>骑士继续移动，直到它走了 <code>k</code> 步或离开了棋盘。</p>

<p>返回 <em>骑士在棋盘停止移动后仍留在棋盘上的概率</em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> n = 3, k = 2, row = 0, column = 0
<strong>输出:</strong> 0.0625
<strong>解释:</strong> 有两步(到(1,2)，(2,1))可以让骑士留在棋盘上。
在每一个位置上，也有两种移动可以让骑士留在棋盘上。
骑士留在棋盘上的总概率是0.0625。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入:</strong> n = 1, k = 0, row = 0, column = 0
<strong>输出:</strong> 1.00000
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 25</code></li>
	<li><code>0 &lt;= k &lt;= 100</code></li>
	<li><code>0 &lt;= row, column &lt;= n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划。`dp[l][i][j]` 表示骑士从 `(i, j)` 出发，走了 l 步后，仍留在棋盘上的概率。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def knightProbability(self, n: int, k: int, row: int, column: int) -> float:
        dp = [[[0] * n for _ in range(n)] for _ in range(k + 1)]
        for l in range(k + 1):
            for i in range(n):
                for j in range(n):
                    if l == 0:
                        dp[l][i][j] = 1
                    else:
                        for a, b in (
                            (-2, -1),
                            (-2, 1),
                            (2, -1),
                            (2, 1),
                            (-1, -2),
                            (-1, 2),
                            (1, -2),
                            (1, 2),
                        ):
                            x, y = i + a, j + b
                            if 0 <= x < n and 0 <= y < n:
                                dp[l][i][j] += dp[l - 1][x][y] / 8
        return dp[k][row][column]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public double knightProbability(int n, int k, int row, int column) {
        double[][][] dp = new double[k + 1][n][n];
        int[] dirs = {-2, -1, 2, 1, -2, 1, 2, -1, -2};
        for (int l = 0; l <= k; ++l) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (l == 0) {
                        dp[l][i][j] = 1;
                    } else {
                        for (int d = 0; d < 8; ++d) {
                            int x = i + dirs[d], y = j + dirs[d + 1];
                            if (x >= 0 && x < n && y >= 0 && y < n) {
                                dp[l][i][j] += dp[l - 1][x][y] / 8;
                            }
                        }
                    }
                }
            }
        }
        return dp[k][row][column];
    }
}
```

### **TypeScript**

```ts
function knightProbability(
    n: number,
    k: number,
    row: number,
    column: number,
): number {
    let dp = Array.from({ length: k + 1 }, v =>
        Array.from({ length: n }, w => new Array(n).fill(0)),
    );
    const directions = [
        [-2, -1],
        [-2, 1],
        [-1, -2],
        [-1, 2],
        [1, -2],
        [1, 2],
        [2, -1],
        [2, 1],
    ];
    for (let depth = 0; depth <= k; depth++) {
        for (let i = 0; i < n; i++) {
            for (let j = 0; j < n; j++) {
                if (!depth) {
                    dp[depth][i][j] = 1;
                } else {
                    for (let [dx, dy] of directions) {
                        let [x, y] = [i + dx, j + dy];
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            dp[depth][i][j] += dp[depth - 1][x][y] / 8;
                        }
                    }
                }
            }
        }
    }
    return dp[k][row][column];
}
```

### **C++**

```cpp
class Solution {
public:
    double knightProbability(int n, int k, int row, int column) {
        vector<vector<vector<double>>> dp(k + 1, vector<vector<double>>(n, vector<double>(n)));
        vector<int> dirs = {-2, -1, 2, 1, -2, 1, 2, -1, -2};
        for (int l = 0; l <= k; ++l) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (l == 0)
                        dp[l][i][j] = 1;
                    else {
                        for (int d = 0; d < 8; ++d) {
                            int x = i + dirs[d], y = j + dirs[d + 1];
                            if (x >= 0 && x < n && y >= 0 && y < n)
                                dp[l][i][j] += dp[l - 1][x][y] / 8;
                        }
                    }
                }
            }
        }
        return dp[k][row][column];
    }
};
```

### **Go**

```go
func knightProbability(n int, k int, row int, column int) float64 {
	dp := make([][][]float64, k+1)
	dirs := []int{-2, -1, 2, 1, -2, 1, 2, -1, -2}
	for l := range dp {
		dp[l] = make([][]float64, n)
		for i := 0; i < n; i++ {
			dp[l][i] = make([]float64, n)
			for j := 0; j < n; j++ {
				if l == 0 {
					dp[l][i][j] = 1
				} else {
					for d := 0; d < 8; d++ {
						x, y := i+dirs[d], j+dirs[d+1]
						if 0 <= x && x < n && 0 <= y && y < n {
							dp[l][i][j] += dp[l-1][x][y] / 8
						}
					}
				}
			}
		}
	}
	return dp[k][row][column]
}
```

### **...**

```

```

<!-- tabs:end -->
