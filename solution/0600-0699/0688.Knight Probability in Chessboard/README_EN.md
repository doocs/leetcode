# [688. Knight Probability in Chessboard](https://leetcode.com/problems/knight-probability-in-chessboard)

[中文文档](/solution/0600-0699/0688.Knight%20Probability%20in%20Chessboard/README.md)

## Description

<p>On an <code>n x n</code> chessboard, a knight starts at the cell <code>(row, column)</code> and attempts to make exactly <code>k</code> moves. The rows and columns are <strong>0-indexed</strong>, so the top-left cell is <code>(0, 0)</code>, and the bottom-right cell is <code>(n - 1, n - 1)</code>.</p>

<p>A chess knight has eight possible moves it can make, as illustrated below. Each move is two cells in a cardinal direction, then one cell in an orthogonal direction.</p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0688.Knight%20Probability%20in%20Chessboard/images/knight.png" style="width: 300px; height: 300px;" />
<p>Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.</p>

<p>The knight continues moving until it has made exactly <code>k</code> moves or has moved off the chessboard.</p>

<p>Return <em>the probability that the knight remains on the board after it has stopped moving</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 2, row = 0, column = 0
<strong>Output:</strong> 0.06250
<strong>Explanation:</strong> There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1, k = 0, row = 0, column = 0
<strong>Output:</strong> 1.00000
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 25</code></li>
	<li><code>0 &lt;= k &lt;= 100</code></li>
	<li><code>0 &lt;= row, column &lt;= n - 1</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
