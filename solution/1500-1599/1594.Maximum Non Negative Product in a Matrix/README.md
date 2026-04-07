---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1594.Maximum%20Non%20Negative%20Product%20in%20a%20Matrix/README.md
rating: 1807
source: 第 207 场周赛 Q3
tags:
    - 数组
    - 动态规划
    - 矩阵
---

<!-- problem:start -->

# [1594. 矩阵的最大非负积](https://leetcode.cn/problems/maximum-non-negative-product-in-a-matrix)

[English Version](/solution/1500-1599/1594.Maximum%20Non%20Negative%20Product%20in%20a%20Matrix/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个大小为 <code>m x n</code> 的矩阵 <code>grid</code> 。最初，你位于左上角 <code>(0, 0)</code> ，每一步，你可以在矩阵中 <strong>向右</strong> 或 <strong>向下</strong> 移动。</p>

<p>在从左上角 <code>(0, 0)</code> 开始到右下角 <code>(m - 1, n - 1)</code> 结束的所有路径中，找出具有 <strong>最大非负积</strong> 的路径。路径的积是沿路径访问的单元格中所有整数的乘积。</p>

<p>返回 <strong>最大非负积 </strong>对<strong><em> </em><code>10<sup>9</sup>&nbsp;+ 7</code></strong> <strong>取余</strong> 的结果。如果最大积为 <strong>负数</strong> ，则返回<em> </em><code>-1</code> 。</p>

<p><strong>注意，</strong>取余是在得到最大积之后执行的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1594.Maximum%20Non%20Negative%20Product%20in%20a%20Matrix/images/product1.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>输入：</strong>grid = [[-1,-2,-3],[-2,-3,-3],[-3,-3,-2]]
<strong>输出：</strong>-1
<strong>解释：</strong>从 (0, 0) 到 (2, 2) 的路径中无法得到非负积，所以返回 -1 。</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1594.Maximum%20Non%20Negative%20Product%20in%20a%20Matrix/images/product2.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>输入：</strong>grid = [[1,-2,1],[1,-2,1],[3,-4,1]]
<strong>输出：</strong>8
<strong>解释：</strong>最大非负积对应的路径如图所示 (1 * 1 * -2 * -4 * 1 = 8)
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1594.Maximum%20Non%20Negative%20Product%20in%20a%20Matrix/images/product3.jpg" style="width: 164px; height: 165px;" />
<pre>
<strong>输入：</strong>grid = [[1,3],[0,-4]]
<strong>输出：</strong>0
<strong>解释：</strong>最大非负积对应的路径如图所示 (1 * 0 * -4 = 0)
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 15</code></li>
	<li><code>-4 &lt;= grid[i][j] &lt;= 4</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义一个三维数组 $f$，其中 $f[i][j][0]$ 和 $f[i][j][1]$ 分别表示从左上角 $(0, 0)$ 出发到达位置 $(i, j)$ 的路径中积的最小值和最大值。对于每个位置 $(i, j)$，我们可以从上方 $(i - 1, j)$ 或左方 $(i, j - 1)$ 转移过来，因此我们需要考虑这两条路径的积的最小值和最大值与当前单元格的值相乘后的结果。

最后，我们需要返回 $f[m - 1][n - 1][1]$ 对 $10^9 + 7$ 取余的结果，如果 $f[m - 1][n - 1][1]$ 小于 $0$，则返回 $-1$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProductPath(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        f = [[[0, 0] for _ in range(n)] for _ in range(m)]

        for i in range(m):
            for j in range(n):
                x = grid[i][j]
                if i == 0 and j == 0:
                    f[i][j][0] = x
                    f[i][j][1] = x
                    continue

                mn, mx = inf, -inf

                if i > 0:
                    a, b = f[i - 1][j]
                    mn = min(mn, a * x, b * x)
                    mx = max(mx, a * x, b * x)

                if j > 0:
                    a, b = f[i][j - 1]
                    mn = min(mn, a * x, b * x)
                    mx = max(mx, a * x, b * x)

                f[i][j][0], f[i][j][1] = mn, mx

        ans = f[m - 1][n - 1][1]
        mod = 10**9 + 7
        return -1 if ans < 0 else ans % mod
```

#### Java

```java
class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][][] f = new long[m][n][2];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                long x = grid[i][j];

                if (i == 0 && j == 0) {
                    f[i][j][0] = x;
                    f[i][j][1] = x;
                    continue;
                }

                long mn = Long.MAX_VALUE, mx = Long.MIN_VALUE;

                if (i > 0) {
                    long a = f[i - 1][j][0], b = f[i - 1][j][1];
                    mn = Math.min(mn, Math.min(a * x, b * x));
                    mx = Math.max(mx, Math.max(a * x, b * x));
                }

                if (j > 0) {
                    long a = f[i][j - 1][0], b = f[i][j - 1][1];
                    mn = Math.min(mn, Math.min(a * x, b * x));
                    mx = Math.max(mx, Math.max(a * x, b * x));
                }

                f[i][j][0] = mn;
                f[i][j][1] = mx;
            }
        }

        long ans = f[m - 1][n - 1][1];
        int mod = (int) 1e9 + 7;
        return ans < 0 ? -1 : (int) (ans % mod);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxProductPath(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<array<long long, 2>>> f(m, vector<array<long long, 2>>(n));

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                long long x = grid[i][j];
                if (i == 0 && j == 0) {
                    f[i][j] = {x, x};
                    continue;
                }

                long long mn = LLONG_MAX, mx = LLONG_MIN;

                if (i > 0) {
                    auto [a, b] = f[i - 1][j];
                    mn = min(mn, min(a * x, b * x));
                    mx = max(mx, max(a * x, b * x));
                }

                if (j > 0) {
                    auto [a, b] = f[i][j - 1];
                    mn = min(mn, min(a * x, b * x));
                    mx = max(mx, max(a * x, b * x));
                }

                f[i][j] = {mn, mx};
            }
        }

        long long ans = f[m - 1][n - 1][1];
        const int mod = 1e9 + 7;
        return ans < 0 ? -1 : ans % mod;
    }
};
```

#### Go

```go
func maxProductPath(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	f := make([][][2]int64, m)
	for i := range f {
		f[i] = make([][2]int64, n)
	}

	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			x := int64(grid[i][j])
			if i == 0 && j == 0 {
				f[i][j] = [2]int64{x, x}
				continue
			}

			mn, mx := int64(1<<63-1), int64(-1<<63)

			if i > 0 {
				a, b := f[i-1][j][0], f[i-1][j][1]
				mn = min(mn, min(a*x, b*x))
				mx = max(mx, max(a*x, b*x))
			}

			if j > 0 {
				a, b := f[i][j-1][0], f[i][j-1][1]
				mn = min(mn, min(a*x, b*x))
				mx = max(mx, max(a*x, b*x))
			}

			f[i][j] = [2]int64{mn, mx}
		}
	}

	ans := f[m-1][n-1][1]
	mod := int64(1e9 + 7)
	if ans < 0 {
		return -1
	}
	return int(ans % mod)
}
```

#### TypeScript

```ts
function maxProductPath(grid: number[][]): number {
    const m = grid.length,
        n = grid[0].length;
    const f = Array.from({ length: m }, () => Array.from({ length: n }, () => [0, 0]));

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const x = grid[i][j];

            if (i === 0 && j === 0) {
                f[i][j] = [x, x];
                continue;
            }

            let mn = Infinity,
                mx = -Infinity;

            if (i > 0) {
                const [a, b] = f[i - 1][j];
                mn = Math.min(mn, a * x, b * x);
                mx = Math.max(mx, a * x, b * x);
            }

            if (j > 0) {
                const [a, b] = f[i][j - 1];
                mn = Math.min(mn, a * x, b * x);
                mx = Math.max(mx, a * x, b * x);
            }

            f[i][j] = [mn, mx];
        }
    }

    const ans = f[m - 1][n - 1][1];
    const mod = 1e9 + 7;
    return ans < 0 ? -1 : ans % mod;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_product_path(grid: Vec<Vec<i32>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let mut f = vec![vec![[0i64; 2]; n]; m];

        for i in 0..m {
            for j in 0..n {
                let x = grid[i][j] as i64;

                if i == 0 && j == 0 {
                    f[i][j] = [x, x];
                    continue;
                }

                let mut mn = i64::MAX;
                let mut mx = i64::MIN;

                if i > 0 {
                    let [a, b] = f[i - 1][j];
                    mn = mn.min(a * x).min(b * x);
                    mx = mx.max(a * x).max(b * x);
                }

                if j > 0 {
                    let [a, b] = f[i][j - 1];
                    mn = mn.min(a * x).min(b * x);
                    mx = mx.max(a * x).max(b * x);
                }

                f[i][j] = [mn, mx];
            }
        }

        let ans = f[m - 1][n - 1][1];
        let mod_val = 1_000_000_007i64;
        if ans < 0 {
            -1
        } else {
            (ans % mod_val) as i32
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
