# [688. “马”在棋盘上的概率](https://leetcode-cn.com/problems/knight-probability-in-chessboard)

[English Version](/solution/0600-0699/0688.Knight%20Probability%20in%20Chessboard/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>已知一个&nbsp;<code>N</code>x<code>N</code>&nbsp;的国际象棋棋盘，棋盘的行号和列号都是从 0 开始。即最左上角的格子记为&nbsp;<code>(0, 0)</code>，最右下角的记为&nbsp;<code>(N-1, N-1)</code>。&nbsp;</p>

<p>现有一个 &ldquo;马&rdquo;（也译作 &ldquo;骑士&rdquo;）位于&nbsp;<code>(r, c)</code>&nbsp;，并打算进行&nbsp;<code>K</code> 次移动。&nbsp;</p>

<p>如下图所示，国际象棋的 &ldquo;马&rdquo; 每一步先沿水平或垂直方向移动 2 个格子，然后向与之相垂直的方向再移动 1 个格子，共有 8 个可选的位置。</p>

<p>&nbsp;</p>

<p><img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0688.Knight%20Probability%20in%20Chessboard/images/knight.png" style="height: 200px; width: 200px;"></p>

<p>&nbsp;</p>

<p>现在 &ldquo;马&rdquo; 每一步都从可选的位置（包括棋盘外部的）中独立随机地选择一个进行移动，直到移动了&nbsp;<code>K</code>&nbsp;次或跳到了棋盘外面。</p>

<p>求移动结束后，&ldquo;马&rdquo; 仍留在棋盘上的概率。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入:</strong> 3, 2, 0, 0
<strong>输出:</strong> 0.0625
<strong>解释:</strong> 
输入的数据依次为 N, K, r, c
第 1 步时，有且只有 2 种走法令 &ldquo;马&rdquo; 可以留在棋盘上（跳到（1,2）或（2,1））。对于以上的两种情况，各自在第2步均有且只有2种走法令 &ldquo;马&rdquo; 仍然留在棋盘上。
所以 &ldquo;马&rdquo; 在结束后仍在棋盘上的概率为 0.0625。
</pre>

<p>&nbsp;</p>

<p><strong>注意：</strong></p>

<ul>
	<li><code>N</code> 的取值范围为 [1, 25]</li>
	<li><code>K</code>&nbsp;的取值范围为 [0, 100]</li>
	<li>开始时，&ldquo;马&rdquo; 总是位于棋盘上</li>
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
                        for a, b in ((-2, -1), (-2, 1), (2, -1), (2, 1), (-1, -2), (-1, 2), (1, -2), (1, 2)):
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
function knightProbability(n: number, k: number, row: number, column: number): number {
    let dp = Array.from({ length: k + 1 }, v => Array.from({ length: n }, w => new Array(n).fill(0)));
    const directions = [[-2, -1], [-2, 1], [-1, -2], [-1, 2], [1, -2], [1, 2], [2, -1], [2, 1]];
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
};
```

### **C++**

```cpp
class Solution {
public:
    double knightProbability(int n, int k, int row, int column) {
        vector<vector<vector<double>>> dp(k + 1, vector<vector<double>>(n, vector<double>(n)));
        vector<int> dirs = {-2, -1, 2, 1, -2, 1, 2, -1, -2};
        for (int l = 0; l <= k; ++l)
        {
            for (int i = 0; i < n; ++i)
            {
                for (int j = 0; j < n; ++j)
                {
                    if (l == 0) dp[l][i][j] = 1;
                    else
                    {
                        for (int d = 0; d < 8; ++d)
                        {
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
