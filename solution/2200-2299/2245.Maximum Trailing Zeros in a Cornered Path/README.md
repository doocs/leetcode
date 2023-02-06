# [2245. 转角路径的乘积中最多能有几个尾随零](https://leetcode.cn/problems/maximum-trailing-zeros-in-a-cornered-path)

[English Version](/solution/2200-2299/2245.Maximum%20Trailing%20Zeros%20in%20a%20Cornered%20Path/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维整数数组 <code>grid</code> ，大小为 <code>m x n</code>，其中每个单元格都含一个正整数。</p>

<p><strong>转角路径</strong> 定义为：包含至多一个弯的一组相邻单元。具体而言，路径应该完全 <strong>向水平方向</strong> 或者 <strong>向竖直方向</strong> 移动过弯（如果存在弯），而不能访问之前访问过的单元格。在过弯之后，路径应当完全朝 <strong>另一个</strong> 方向行进：如果之前是向水平方向，那么就应该变为向竖直方向；反之亦然。当然，同样不能访问之前已经访问过的单元格。</p>

<p>一条路径的 <strong>乘积</strong> 定义为：路径上所有值的乘积。</p>

<p>请你从 <code>grid</code> 中找出一条乘积中尾随零数目最多的转角路径，并返回该路径中尾随零的数目。</p>

<p>注意：</p>

<ul>
	<li><strong>水平</strong> 移动是指向左或右移动。</li>
	<li><strong>竖直 </strong>移动是指向上或下移动。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2245.Maximum%20Trailing%20Zeros%20in%20a%20Cornered%20Path/images/ex1new2.jpg" style="width: 577px; height: 190px;" /></p>

<pre>
<strong>输入：</strong>grid = [[23,17,15,3,20],[8,1,20,27,11],[9,4,6,2,21],[40,9,1,10,6],[22,7,4,5,3]]
<strong>输出：</strong>3
<strong>解释：</strong>左侧的图展示了一条有效的转角路径。
其乘积为 15 * 20 * 6 * 1 * 10 = 18000 ，共计 3 个尾随零。
可以证明在这条转角路径的乘积中尾随零数目最多。

中间的图不是一条有效的转角路径，因为它有不止一个弯。
右侧的图也不是一条有效的转角路径，因为它需要重复访问已经访问过的单元格。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2245.Maximum%20Trailing%20Zeros%20in%20a%20Cornered%20Path/images/ex2.jpg" style="width: 150px; height: 157px;" /></p>

<pre>
<strong>输入：</strong>grid = [[4,3,2],[7,6,1],[8,8,8]]
<strong>输出：</strong>0
<strong>解释：</strong>网格如上图所示。
不存在乘积含尾随零的转角路径。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 枚举拐点**

首先我们要明确，对于一个乘积，尾随零的个数取决于因子中 $2$ 和 $5$ 的个数的较小值。另外，每一条转角路径应该覆盖尽可能多的数，因此，它一定是从某个边界出发，到达某个拐点，再到达另一个边界。

因此，我们可以创建四个二维数组 $r2$, $c2$, $r5$, $c5$ 来记录每一行和每一列中 $2$ 和 $5$ 的个数。其中：

-   `r2[i][j]` 表示第 $i$ 行中从第 $1$ 列到第 $j$ 列的 $2$ 的个数；
-   `c2[i][j]` 表示第 $j$ 列中从第 $1$ 行到第 $i$ 行的 $2$ 的个数；
-   `r5[i][j]` 表示第 $i$ 行中从第 $1$ 列到第 $j$ 列的 $5$ 的个数；
-   `c5[i][j]` 表示第 $j$ 列中从第 $1$ 行到第 $i$ 行的 $5$ 的个数。

接下来，我们遍历二维数组 `grid`，对于每个数，我们计算它的 $2$ 和 $5$ 的个数，然后更新四个二维数组。

然后，我们枚举拐点 $(i, j)$，对于每个拐点，我们计算四个值，其中：

-   `a` 表示从 $(i, 1)$ 右移到 $(i, j)$，再从 $(i, j)$ 拐头向上移动到 $(1, j)$ 的路径中 $2$ 的个数和 $5$ 的个数的较小值；
-   `b` 表示从 $(i, 1)$ 右移到 $(i, j)$，再从 $(i, j)$ 拐头向下移动到 $(m, j)$ 的路径中 $2$ 的个数和 $5$ 的个数的较小值；
-   `c` 表示从 $(i, n)$ 左移到 $(i, j)$，再从 $(i, j)$ 拐头向上移动到 $(1, j)$ 的路径中 $2$ 的个数和 $5$ 的个数的较小值；
-   `d` 表示从 $(i, n)$ 左移到 $(i, j)$，再从 $(i, j)$ 拐头向下移动到 $(m, j)$ 的路径中 $2$ 的个数和 $5$ 的个数的较小值。

每一次枚举，我们取这四个值的最大值，然后更新答案。

最后，我们返回答案即可。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是二维数组 `grid` 的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxTrailingZeros(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        r2 = [[0] * (n + 1) for _ in range(m + 1)]
        c2 = [[0] * (n + 1) for _ in range(m + 1)]
        r5 = [[0] * (n + 1) for _ in range(m + 1)]
        c5 = [[0] * (n + 1) for _ in range(m + 1)]
        for i, row in enumerate(grid, 1):
            for j, x in enumerate(row, 1):
                s2 = s5 = 0
                while x % 2 == 0:
                    x //= 2
                    s2 += 1
                while x % 5 == 0:
                    x //= 5
                    s5 += 1
                r2[i][j] = r2[i][j - 1] + s2
                c2[i][j] = c2[i - 1][j] + s2
                r5[i][j] = r5[i][j - 1] + s5
                c5[i][j] = c5[i - 1][j] + s5
        ans = 0
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                a = min(r2[i][j] + c2[i - 1][j], r5[i][j] + c5[i - 1][j])
                b = min(r2[i][j] + c2[m][j] - c2[i][j], r5[i][j] + c5[m][j] - c5[i][j])
                c = min(r2[i][n] - r2[i][j] + c2[i][j], r5[i][n] - r5[i][j] + c5[i][j])
                d = min(
                    r2[i][n] - r2[i][j - 1] + c2[m][j] - c2[i][j],
                    r5[i][n] - r5[i][j - 1] + c5[m][j] - c5[i][j],
                )
                ans = max(ans, a, b, c, d)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxTrailingZeros(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] r2 = new int[m + 1][n + 1];
        int[][] c2 = new int[m + 1][n + 1];
        int[][] r5 = new int[m + 1][n + 1];
        int[][] c5 = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                int x = grid[i - 1][j - 1];
                int s2 = 0, s5 = 0;
                for (; x % 2 == 0; x /= 2) {
                    ++s2;
                }
                for (; x % 5 == 0; x /= 5) {
                    ++s5;
                }
                r2[i][j] = r2[i][j - 1] + s2;
                c2[i][j] = c2[i - 1][j] + s2;
                r5[i][j] = r5[i][j - 1] + s5;
                c5[i][j] = c5[i - 1][j] + s5;
            }
        }
        int ans = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                int a = Math.min(r2[i][j] + c2[i - 1][j], r5[i][j] + c5[i - 1][j]);
                int b = Math.min(r2[i][j] + c2[m][j] - c2[i][j], r5[i][j] + c5[m][j] - c5[i][j]);
                int c = Math.min(r2[i][n] - r2[i][j] + c2[i][j], r5[i][n] - r5[i][j] + c5[i][j]);
                int d = Math.min(r2[i][n] - r2[i][j - 1] + c2[m][j] - c2[i][j],
                    r5[i][n] - r5[i][j - 1] + c5[m][j] - c5[i][j]);
                ans = Math.max(ans, Math.max(a, Math.max(b, Math.max(c, d))));
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxTrailingZeros(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> r2(m + 1, vector<int>(n + 1));
        vector<vector<int>> c2(m + 1, vector<int>(n + 1));
        vector<vector<int>> r5(m + 1, vector<int>(n + 1));
        vector<vector<int>> c5(m + 1, vector<int>(n + 1));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                int x = grid[i - 1][j - 1];
                int s2 = 0, s5 = 0;
                for (; x % 2 == 0; x /= 2) {
                    ++s2;
                }
                for (; x % 5 == 0; x /= 5) {
                    ++s5;
                }
                r2[i][j] = r2[i][j - 1] + s2;
                c2[i][j] = c2[i - 1][j] + s2;
                r5[i][j] = r5[i][j - 1] + s5;
                c5[i][j] = c5[i - 1][j] + s5;
            }
        }
        int ans = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                int a = min(r2[i][j] + c2[i - 1][j], r5[i][j] + c5[i - 1][j]);
                int b = min(r2[i][j] + c2[m][j] - c2[i][j], r5[i][j] + c5[m][j] - c5[i][j]);
                int c = min(r2[i][n] - r2[i][j] + c2[i][j], r5[i][n] - r5[i][j] + c5[i][j]);
                int d = min(r2[i][n] - r2[i][j - 1] + c2[m][j] - c2[i][j], r5[i][n] - r5[i][j - 1] + c5[m][j] - c5[i][j]);
                ans = max({ans, a, b, c, d});
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxTrailingZeros(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	r2 := get(m+1, n+1)
	c2 := get(m+1, n+1)
	r5 := get(m+1, n+1)
	c5 := get(m+1, n+1)
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			x := grid[i-1][j-1]
			s2, s5 := 0, 0
			for ; x%2 == 0; x /= 2 {
				s2++
			}
			for ; x%5 == 0; x /= 5 {
				s5++
			}
			r2[i][j] = r2[i][j-1] + s2
			c2[i][j] = c2[i-1][j] + s2
			r5[i][j] = r5[i][j-1] + s5
			c5[i][j] = c5[i-1][j] + s5
		}
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			a := min(r2[i][j]+c2[i-1][j], r5[i][j]+c5[i-1][j])
			b := min(r2[i][j]+c2[m][j]-c2[i][j], r5[i][j]+c5[m][j]-c5[i][j])
			c := min(r2[i][n]-r2[i][j]+c2[i][j], r5[i][n]-r5[i][j]+c5[i][j])
			d := min(r2[i][n]-r2[i][j-1]+c2[m][j]-c2[i][j], r5[i][n]-r5[i][j-1]+c5[m][j]-c5[i][j])
			ans = max(ans, max(a, max(b, max(c, d))))
		}
	}
	return
}

func get(m, n int) [][]int {
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
	}
	return f
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function maxTrailingZeros(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const r2 = Array.from({ length: m + 1 }, () => new Array(n + 1).fill(0));
    const c2 = Array.from({ length: m + 1 }, () => new Array(n + 1).fill(0));
    const r5 = Array.from({ length: m + 1 }, () => new Array(n + 1).fill(0));
    const c5 = Array.from({ length: m + 1 }, () => new Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            let x = grid[i - 1][j - 1];
            let s2 = 0;
            let s5 = 0;
            for (; x % 2 == 0; x = Math.floor(x / 2)) {
                ++s2;
            }
            for (; x % 5 == 0; x = Math.floor(x / 5)) {
                ++s5;
            }
            r2[i][j] = r2[i][j - 1] + s2;
            c2[i][j] = c2[i - 1][j] + s2;
            r5[i][j] = r5[i][j - 1] + s5;
            c5[i][j] = c5[i - 1][j] + s5;
        }
    }
    let ans = 0;
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            const a = Math.min(
                r2[i][j] + c2[i - 1][j],
                r5[i][j] + c5[i - 1][j],
            );
            const b = Math.min(
                r2[i][j] + c2[m][j] - c2[i][j],
                r5[i][j] + c5[m][j] - c5[i][j],
            );
            const c = Math.min(
                r2[i][n] - r2[i][j] + c2[i][j],
                r5[i][n] - r5[i][j] + c5[i][j],
            );
            const d = Math.min(
                r2[i][n] - r2[i][j - 1] + c2[m][j] - c2[i][j],
                r5[i][n] - r5[i][j - 1] + c5[m][j] - c5[i][j],
            );
            ans = Math.max(ans, a, b, c, d);
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
