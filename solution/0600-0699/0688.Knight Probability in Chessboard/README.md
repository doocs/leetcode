---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0688.Knight%20Probability%20in%20Chessboard/README.md
tags:
    - 动态规划
---

<!-- problem:start -->

# [688. 骑士在棋盘上的概率](https://leetcode.cn/problems/knight-probability-in-chessboard)

[English Version](/solution/0600-0699/0688.Knight%20Probability%20in%20Chessboard/README_EN.md)

## 题目描述

<!-- description:start -->

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
	<li><code>0 &lt;= row, column &lt;= n - 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[h][i][j]$ 表示骑士从 $(i, j)$ 位置出发，走了 $h$ 步以后还留在棋盘上的概率。那么最终答案就是 $f[k][\textit{row}][\textit{column}]$。

当 $h=0$ 时，骑士一定在棋盘上，概率为 $1$，即 $f[0][i][j]=1$。

当 $h \gt 0$ 时，骑士在 $(i, j)$ 位置上的概率可以由其上一步的 $8$ 个位置上的概率转移得到，即：

$$
f[h][i][j] = \sum_{x, y} f[h - 1][x][y] \times \frac{1}{8}
$$

其中 $(x, y)$ 是从 $(i, j)$ 位置可以走到的 $8$ 个位置中的一个。

最终答案即为 $f[k][\textit{row}][\textit{column}]$。

时间复杂度 $O(k \times n^2)$，空间复杂度 $O(k \times n^2)$。其中 $k$ 和 $n$ 分别是给定的步数和棋盘大小。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def knightProbability(self, n: int, k: int, row: int, column: int) -> float:
        f = [[[0] * n for _ in range(n)] for _ in range(k + 1)]
        for i in range(n):
            for j in range(n):
                f[0][i][j] = 1
        for h in range(1, k + 1):
            for i in range(n):
                for j in range(n):
                    for a, b in pairwise((-2, -1, 2, 1, -2, 1, 2, -1, -2)):
                        x, y = i + a, j + b
                        if 0 <= x < n and 0 <= y < n:
                            f[h][i][j] += f[h - 1][x][y] / 8
        return f[k][row][column]
```

#### Java

```java
class Solution {
    public double knightProbability(int n, int k, int row, int column) {
        double[][][] f = new double[k + 1][n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                f[0][i][j] = 1;
            }
        }
        int[] dirs = {-2, -1, 2, 1, -2, 1, 2, -1, -2};
        for (int h = 1; h <= k; ++h) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    for (int p = 0; p < 8; ++p) {
                        int x = i + dirs[p], y = j + dirs[p + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            f[h][i][j] += f[h - 1][x][y] / 8;
                        }
                    }
                }
            }
        }
        return f[k][row][column];
    }
}
```

#### C++

```cpp
class Solution {
public:
    double knightProbability(int n, int k, int row, int column) {
        double f[k + 1][n][n];
        memset(f, 0, sizeof(f));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                f[0][i][j] = 1;
            }
        }
        int dirs[9] = {-2, -1, 2, 1, -2, 1, 2, -1, -2};
        for (int h = 1; h <= k; ++h) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    for (int p = 0; p < 8; ++p) {
                        int x = i + dirs[p], y = j + dirs[p + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            f[h][i][j] += f[h - 1][x][y] / 8;
                        }
                    }
                }
            }
        }
        return f[k][row][column];
    }
};
```

#### Go

```go
func knightProbability(n int, k int, row int, column int) float64 {
	f := make([][][]float64, k+1)
	for h := range f {
		f[h] = make([][]float64, n)
		for i := range f[h] {
			f[h][i] = make([]float64, n)
			for j := range f[h][i] {
				f[0][i][j] = 1
			}
		}
	}
	dirs := [9]int{-2, -1, 2, 1, -2, 1, 2, -1, -2}
	for h := 1; h <= k; h++ {
		for i := 0; i < n; i++ {
			for j := 0; j < n; j++ {
				for p := 0; p < 8; p++ {
					x, y := i+dirs[p], j+dirs[p+1]
					if x >= 0 && x < n && y >= 0 && y < n {
						f[h][i][j] += f[h-1][x][y] / 8
					}
				}
			}
		}
	}
	return f[k][row][column]
}
```

#### TypeScript

```ts
function knightProbability(n: number, k: number, row: number, column: number): number {
    const f = Array.from({ length: k + 1 }, () =>
        Array.from({ length: n }, () => Array(n).fill(0)),
    );
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            f[0][i][j] = 1;
        }
    }
    const dirs = [-2, -1, 2, 1, -2, 1, 2, -1, -2];
    for (let h = 1; h <= k; ++h) {
        for (let i = 0; i < n; ++i) {
            for (let j = 0; j < n; ++j) {
                for (let p = 0; p < 8; ++p) {
                    const x = i + dirs[p];
                    const y = j + dirs[p + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n) {
                        f[h][i][j] += f[h - 1][x][y] / 8;
                    }
                }
            }
        }
    }
    return f[k][row][column];
}
```

#### Rust

```rust
impl Solution {
    pub fn knight_probability(n: i32, k: i32, row: i32, column: i32) -> f64 {
        let n = n as usize;
        let k = k as usize;

        let mut f = vec![vec![vec![0.0; n]; n]; k + 1];

        for i in 0..n {
            for j in 0..n {
                f[0][i][j] = 1.0;
            }
        }

        let dirs = [-2, -1, 2, 1, -2, 1, 2, -1, -2];

        for h in 1..=k {
            for i in 0..n {
                for j in 0..n {
                    for p in 0..8 {
                        let x = i as isize + dirs[p];
                        let y = j as isize + dirs[p + 1];

                        if x >= 0 && x < n as isize && y >= 0 && y < n as isize {
                            let x = x as usize;
                            let y = y as usize;
                            f[h][i][j] += f[h - 1][x][y] / 8.0;
                        }
                    }
                }
            }
        }

        f[k][row as usize][column as usize]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
