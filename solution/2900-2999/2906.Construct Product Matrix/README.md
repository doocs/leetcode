# [2906. 构造乘积矩阵](https://leetcode.cn/problems/construct-product-matrix)

[English Version](/solution/2900-2999/2906.Construct%20Product%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始、大小为 <code>n * m</code> 的二维整数矩阵 <code><font face="monospace">grid</font></code><font face="monospace"> ，定义一个下标从 <strong>0</strong> 开始、大小为 <code>n * m</code> 的的二维矩阵</font> <code>p</code>。如果满足以下条件，则称 <code>p</code> 为 <code>grid</code> 的 <strong>乘积矩阵</strong> ：</p>

<ul>
	<li>对于每个元素 <code>p[i][j]</code> ，它的值等于除了 <code>grid[i][j]</code> 外所有元素的乘积。乘积对 <code>12345</code> 取余数。</li>
</ul>

<p>返回 <code>grid</code> 的乘积矩阵。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>grid = [[1,2],[3,4]]
<strong>输出：</strong>[[24,12],[8,6]]
<strong>解释：</strong>p[0][0] = grid[0][1] * grid[1][0] * grid[1][1] = 2 * 3 * 4 = 24
p[0][1] = grid[0][0] * grid[1][0] * grid[1][1] = 1 * 3 * 4 = 12
p[1][0] = grid[0][0] * grid[0][1] * grid[1][1] = 1 * 2 * 4 = 8
p[1][1] = grid[0][0] * grid[0][1] * grid[1][0] = 1 * 2 * 3 = 6
所以答案是 [[24,12],[8,6]] 。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [[12345],[2],[1]]
<strong>输出：</strong>[[2],[0],[0]]
<strong>解释：</strong>p[0][0] = grid[0][1] * grid[0][2] = 2 * 1 = 2
p[0][1] = grid[0][0] * grid[0][2] = 12345 * 1 = 12345. 12345 % 12345 = 0 ，所以 p[0][1] = 0
p[0][2] = grid[0][0] * grid[0][1] = 12345 * 2 = 24690. 24690 % 12345 = 0 ，所以 p[0][2] = 0
所以答案是 [[2],[0],[0]] 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == grid.length&nbsp;&lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m == grid[i].length&nbsp;&lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= n * m &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：前后缀分解

我们可以预处理出每个元素的后缀乘积（不包含自身），然后再遍历矩阵，计算得到每个元素的前缀乘积（不包含自身），将两者相乘即可得到每个位置的结果。

具体地，我们用 $p[i][j]$ 表示矩阵中第 $i$ 行第 $j$ 列元素的结果，定义一个变量 $suf$ 表示当前位置右下方的所有元素的乘积，初始时 $suf = 1$。我们从矩阵右下角开始遍历，对于每个位置 $(i, j)$，我们将 $suf$ 赋值给 $p[i][j]$，然后更新 $suf$ 为 $suf \times grid[i][j] \bmod 12345$，这样就可以得到每个位置的后缀乘积。

接下来我们从矩阵左上角开始遍历，对于每个位置 $(i, j)$，我们将 $p[i][j]$ 乘上 $pre$，再对 $12345$ 取模，然后更新 $pre$ 为 $pre \times grid[i][j] \bmod 12345$，这样就可以得到每个位置的前缀乘积。

遍历结束，返回结果矩阵 $p$ 即可。

时间复杂度 $O(n \times m)$，其中 $n$ 和 $m$ 分别是矩阵的行数和列数。忽略结果矩阵的空间占用，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def constructProductMatrix(self, grid: List[List[int]]) -> List[List[int]]:
        n, m = len(grid), len(grid[0])
        p = [[0] * m for _ in range(n)]
        mod = 12345
        suf = 1
        for i in range(n - 1, -1, -1):
            for j in range(m - 1, -1, -1):
                p[i][j] = suf
                suf = suf * grid[i][j] % mod
        pre = 1
        for i in range(n):
            for j in range(m):
                p[i][j] = p[i][j] * pre % mod
                pre = pre * grid[i][j] % mod
        return p
```

```java
class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        final int mod = 12345;
        int n = grid.length, m = grid[0].length;
        int[][] p = new int[n][m];
        long suf = 1;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = m - 1; j >= 0; --j) {
                p[i][j] = (int) suf;
                suf = suf * grid[i][j] % mod;
            }
        }
        long pre = 1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                p[i][j] = (int) (p[i][j] * pre % mod);
                pre = pre * grid[i][j] % mod;
            }
        }
        return p;
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> constructProductMatrix(vector<vector<int>>& grid) {
        const int mod = 12345;
        int n = grid.size(), m = grid[0].size();
        vector<vector<int>> p(n, vector<int>(m));
        long long suf = 1;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = m - 1; j >= 0; --j) {
                p[i][j] = suf;
                suf = suf * grid[i][j] % mod;
            }
        }
        long long pre = 1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                p[i][j] = p[i][j] * pre % mod;
                pre = pre * grid[i][j] % mod;
            }
        }
        return p;
    }
};
```

```go
func constructProductMatrix(grid [][]int) [][]int {
	const mod int = 12345
	n, m := len(grid), len(grid[0])
	p := make([][]int, n)
	for i := range p {
		p[i] = make([]int, m)
	}
	suf := 1
	for i := n - 1; i >= 0; i-- {
		for j := m - 1; j >= 0; j-- {
			p[i][j] = suf
			suf = suf * grid[i][j] % mod
		}
	}
	pre := 1
	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			p[i][j] = p[i][j] * pre % mod
			pre = pre * grid[i][j] % mod
		}
	}
	return p
}
```

```ts
function constructProductMatrix(grid: number[][]): number[][] {
    const mod = 12345;
    const [n, m] = [grid.length, grid[0].length];
    const p: number[][] = Array.from({ length: n }, () => Array.from({ length: m }, () => 0));
    let suf = 1;
    for (let i = n - 1; ~i; --i) {
        for (let j = m - 1; ~j; --j) {
            p[i][j] = suf;
            suf = (suf * grid[i][j]) % mod;
        }
    }
    let pre = 1;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < m; ++j) {
            p[i][j] = (p[i][j] * pre) % mod;
            pre = (pre * grid[i][j]) % mod;
        }
    }
    return p;
}
```

```rust
impl Solution {
    pub fn construct_product_matrix(grid: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let modulo: i32 = 12345;
        let n = grid.len();
        let m = grid[0].len();
        let mut p: Vec<Vec<i32>> = vec![vec![0; m]; n];
        let mut suf = 1;

        for i in (0..n).rev() {
            for j in (0..m).rev() {
                p[i][j] = suf;
                suf = (((suf as i64) * (grid[i][j] as i64)) % (modulo as i64)) as i32;
            }
        }

        let mut pre = 1;

        for i in 0..n {
            for j in 0..m {
                p[i][j] = (((p[i][j] as i64) * (pre as i64)) % (modulo as i64)) as i32;
                pre = (((pre as i64) * (grid[i][j] as i64)) % (modulo as i64)) as i32;
            }
        }

        p
    }
}
```

<!-- tabs:end -->

<!-- end -->
