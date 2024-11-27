---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0935.Knight%20Dialer/README.md
tags:
    - 动态规划
---

<!-- problem:start -->

# [935. 骑士拨号器](https://leetcode.cn/problems/knight-dialer)

[English Version](/solution/0900-0999/0935.Knight%20Dialer/README_EN.md)

## 题目描述

<!-- description:start -->

<p>象棋骑士有一个<strong>独特的移动方式</strong>，它可以垂直移动两个方格，水平移动一个方格，或者水平移动两个方格，垂直移动一个方格(两者都形成一个&nbsp;<strong>L&nbsp;</strong>的形状)。</p>

<p>象棋骑士可能的移动方式如下图所示:</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0935.Knight%20Dialer/images/chess.jpg" style="height: 200px; width: 200px;" /></p>

<p>我们有一个象棋骑士和一个电话垫，如下所示，骑士<strong>只能站在一个数字单元格上</strong>(即蓝色单元格)。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0935.Knight%20Dialer/images/phone.jpg" style="height: 200px; width: 150px;" /></p>

<p>给定一个整数 n，返回我们可以拨多少个长度为 n 的不同电话号码。</p>

<p>你可以将骑士放置在<strong>任何数字单元格</strong>上，然后你应该执行 n - 1 次移动来获得长度为 n 的号码。所有的跳跃应该是<strong>有效</strong>的骑士跳跃。</p>

<p>因为答案可能很大，<strong>所以输出答案模&nbsp;</strong><code>10<sup>9</sup>&nbsp;+ 7</code>.</p>

<p>&nbsp;</p>

<ul>
</ul>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>10
<strong>解释：</strong>我们需要拨一个长度为1的数字，所以把骑士放在10个单元格中的任何一个数字单元格上都能满足条件。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>20
<strong>解释：</strong>我们可以拨打的所有有效号码为[04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 3131
<strong>输出：</strong>136006598
<strong>解释：</strong>注意取模
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递推

根据题目描述，我们需要计算出长度为 $n$ 的不同电话号码的数量。其中，每个数字的上一个数字只有固定的几个，我们可以列出每个数字的上一个数字：

| 当前数字 | 上一个数字 |
| -------- | ---------- |
| 0        | 4, 6       |
| 1        | 6, 8       |
| 2        | 7, 9       |
| 3        | 4, 8       |
| 4        | 0, 3, 9    |
| 5        |            |
| 6        | 0, 1, 7    |
| 7        | 2, 6       |
| 8        | 1, 3       |
| 9        | 2, 4       |

我们可以通过递推的方式，计算出长度为 $n$ 的不同电话号码的数量。设 $f[i]$ 表示长度为 $i$ 的不同电话号码的数量，初始时 $f[1] = 1$。对于长度为 $i$ 的电话号码，我们可以通过长度为 $i - 1$ 的电话号码计算出来，因此我们可以得到递推关系：

$$
\begin{aligned}
g[0] & = f[4] + f[6] \\
g[1] & = f[6] + f[8] \\
g[2] & = f[7] + f[9] \\
g[3] & = f[4] + f[8] \\
g[4] & = f[0] + f[3] + f[9] \\
g[6] & = f[0] + f[1] + f[7] \\
g[7] & = f[2] + f[6] \\
g[8] & = f[1] + f[3] \\
g[9] & = f[2] + f[4]
\end{aligned}
$$

然后，我们将 $f$ 更新为 $g$，继续计算下一个长度的电话号码，直到计算出长度为 $n$ 的电话号码的数量。

最后，我们将 $f$ 中所有元素相加，取模 $10^9 + 7$，即为答案。

时间复杂度 $O(n)$，其中 $n$ 为电话号码的长度。空间复杂度 $O(|\Sigma|)$，其中 $\Sigma$ 为数字集合，本题中 $|\Sigma| = 10$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def knightDialer(self, n: int) -> int:
        f = [1] * 10
        for _ in range(n - 1):
            g = [0] * 10
            g[0] = f[4] + f[6]
            g[1] = f[6] + f[8]
            g[2] = f[7] + f[9]
            g[3] = f[4] + f[8]
            g[4] = f[0] + f[3] + f[9]
            g[6] = f[0] + f[1] + f[7]
            g[7] = f[2] + f[6]
            g[8] = f[1] + f[3]
            g[9] = f[2] + f[4]
            f = g
        return sum(f) % (10**9 + 7)
```

#### Java

```java
class Solution {
    public int knightDialer(int n) {
        final int mod = (int) 1e9 + 7;
        long[] f = new long[10];
        Arrays.fill(f, 1);
        while (--n > 0) {
            long[] g = new long[10];
            g[0] = (f[4] + f[6]) % mod;
            g[1] = (f[6] + f[8]) % mod;
            g[2] = (f[7] + f[9]) % mod;
            g[3] = (f[4] + f[8]) % mod;
            g[4] = (f[0] + f[3] + f[9]) % mod;
            g[6] = (f[0] + f[1] + f[7]) % mod;
            g[7] = (f[2] + f[6]) % mod;
            g[8] = (f[1] + f[3]) % mod;
            g[9] = (f[2] + f[4]) % mod;
            f = g;
        }
        return (int) (Arrays.stream(f).sum() % mod);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int knightDialer(int n) {
        const int mod = 1e9 + 7;
        vector<long long> f(10, 1);
        while (--n) {
            vector<long long> g(10);
            g[0] = (f[4] + f[6]) % mod;
            g[1] = (f[6] + f[8]) % mod;
            g[2] = (f[7] + f[9]) % mod;
            g[3] = (f[4] + f[8]) % mod;
            g[4] = (f[0] + f[3] + f[9]) % mod;
            g[6] = (f[0] + f[1] + f[7]) % mod;
            g[7] = (f[2] + f[6]) % mod;
            g[8] = (f[1] + f[3]) % mod;
            g[9] = (f[2] + f[4]) % mod;
            f = g;
        }
        return accumulate(f.begin(), f.end(), 0LL) % mod;
    }
};
```

#### Go

```go
func knightDialer(n int) (ans int) {
	f := make([]int, 10)
	for i := range f {
		f[i] = 1
	}
	const mod int = 1e9 + 7
	for i := 1; i < n; i++ {
		g := make([]int, 10)
		g[0] = (f[4] + f[6]) % mod
		g[1] = (f[6] + f[8]) % mod
		g[2] = (f[7] + f[9]) % mod
		g[3] = (f[4] + f[8]) % mod
		g[4] = (f[0] + f[3] + f[9]) % mod
		g[6] = (f[0] + f[1] + f[7]) % mod
		g[7] = (f[2] + f[6]) % mod
		g[8] = (f[1] + f[3]) % mod
		g[9] = (f[2] + f[4]) % mod
		f = g
	}
	for _, x := range f {
		ans = (ans + x) % mod
	}
	return
}
```

#### TypeScript

```ts
function knightDialer(n: number): number {
    const mod = 1e9 + 7;
    const f: number[] = Array(10).fill(1);
    while (--n) {
        const g: number[] = Array(10).fill(0);
        g[0] = (f[4] + f[6]) % mod;
        g[1] = (f[6] + f[8]) % mod;
        g[2] = (f[7] + f[9]) % mod;
        g[3] = (f[4] + f[8]) % mod;
        g[4] = (f[0] + f[3] + f[9]) % mod;
        g[6] = (f[0] + f[1] + f[7]) % mod;
        g[7] = (f[2] + f[6]) % mod;
        g[8] = (f[1] + f[3]) % mod;
        g[9] = (f[2] + f[4]) % mod;
        f.splice(0, 10, ...g);
    }
    return f.reduce((a, b) => (a + b) % mod);
}
```

#### C#

```cs
public class Solution {
    public int KnightDialer(int n) {
        const int mod = 1000000007;
        long[] f = new long[10];
        for (int i = 0; i < 10; i++) {
            f[i] = 1;
        }

        while (--n > 0) {
            long[] g = new long[10];
            g[0] = (f[4] + f[6]) % mod;
            g[1] = (f[6] + f[8]) % mod;
            g[2] = (f[7] + f[9]) % mod;
            g[3] = (f[4] + f[8]) % mod;
            g[4] = (f[0] + f[3] + f[9]) % mod;
            g[6] = (f[0] + f[1] + f[7]) % mod;
            g[7] = (f[2] + f[6]) % mod;
            g[8] = (f[1] + f[3]) % mod;
            g[9] = (f[2] + f[4]) % mod;
            f = g;
        }

        return (int)(f.Sum() % mod);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：矩阵快速幂加速递推

我们假设 $T(n)$ 表示一个 $1 \times 10$ 的矩阵 $\begin{bmatrix} F_0 & F_1 & F_2 \cdots F_9 \end{bmatrix}$，其中 $F_i$ 表示第 $i$ 个电话号码的数量。我们希望根据 $T(n - 1)$ 推出 $T(n)$。也即是说，我们需要一个矩阵 $\textit{base}$，使得 $T(n - 1) \times \textit{base} = T(n)$，即：

$$
\begin{bmatrix}
F_0 & F_1 & F_2 \cdots F_9
\end{bmatrix} \times \textit{base} = \begin{bmatrix} F_0' & F_1' & F_2' \cdots F_9' \end{bmatrix}
$$

由于 $F_i' = \sum_{j} F_j$，其中 $j$ 是 $i$ 的上一个数字，所以矩阵 $\textit{base}$ 的第 $1$ 列为：

$$
\begin{bmatrix}
0 \\
0 \\
0 \\
0 \\
1 \\
0 \\
1 \\
0 \\
0 \\
0
\end{bmatrix}
$$

依次类推，我们可以得到矩阵 $\textit{base}$ 如下：

$$
\begin{bmatrix}
0 & 0 & 0 & 0 & 1 & 0 & 1 & 0 & 0 & 0 \\
0 & 0 & 0 & 0 & 0 & 0 & 1 & 0 & 1 & 0 \\
0 & 0 & 0 & 0 & 0 & 0 & 0 & 1 & 0 & 1 \\
0 & 0 & 0 & 0 & 1 & 0 & 0 & 0 & 1 & 0 \\
1 & 0 & 0 & 1 & 0 & 0 & 0 & 0 & 0 & 1 \\
0 & 0 & 0 & 0 & 0 & 0 & 0 & 0 & 0 & 0 \\
1 & 1 & 0 & 0 & 0 & 0 & 0 & 1 & 0 & 0 \\
0 & 0 & 1 & 0 & 0 & 0 & 1 & 0 & 0 & 0 \\
0 & 1 & 0 & 1 & 0 & 0 & 0 & 0 & 0 & 0 \\
0 & 0 & 1 & 0 & 1 & 0 & 0 & 0 & 0 & 0
\end{bmatrix}
$$

我们定义初始矩阵 $res = \begin{bmatrix} 1 & 1 & 1 \cdots 1 \end{bmatrix}$，与 $n - 1$ 个 $\textit{base}$ 矩阵相乘，即可得到 $T(n)$。最后，我们将 $T(n)$ 中所有元素相加，取模 $10^9 + 7$，即为答案。求 $\textit{base}^{n - 1}$，可以通过矩阵快速幂的方式，时间复杂度为 $O(\log n)$。

时间复杂度 $O(\log n)$，空间复杂度 $O(|\Sigma|^2)$，其中 $\Sigma$ 为数字集合，本题中 $|\Sigma| = 10$。

<!-- tabs:start -->

#### Python3

```python
import numpy as np

base = [
    (0, 0, 0, 0, 1, 0, 1, 0, 0, 0),
    (0, 0, 0, 0, 0, 0, 1, 0, 1, 0),
    (0, 0, 0, 0, 0, 0, 0, 1, 0, 1),
    (0, 0, 0, 0, 1, 0, 0, 0, 1, 0),
    (1, 0, 0, 1, 0, 0, 0, 0, 0, 1),
    (0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    (1, 1, 0, 0, 0, 0, 0, 1, 0, 0),
    (0, 0, 1, 0, 0, 0, 1, 0, 0, 0),
    (0, 1, 0, 1, 0, 0, 0, 0, 0, 0),
    (0, 0, 1, 0, 1, 0, 0, 0, 0, 0),
]


class Solution:
    def knightDialer(self, n: int) -> int:
        factor = np.asmatrix(base, np.dtype("O"))
        res = np.asmatrix([[1] * 10], np.dtype("O"))
        n -= 1
        mod = 10**9 + 7
        while n:
            if n & 1:
                res = res * factor % mod
            factor = factor * factor % mod
            n >>= 1
        return res.sum() % mod
```

#### Java

```java
class Solution {
    private final int mod = (int) 1e9 + 7;
    private final int[][] base = {{0, 0, 0, 0, 1, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
        {0, 0, 0, 0, 0, 0, 0, 1, 0, 1}, {0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
        {1, 0, 0, 1, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {1, 1, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
        {0, 1, 0, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 1, 0, 0, 0, 0, 0}};

    public int knightDialer(int n) {
        int[][] res = pow(base, n - 1);
        int ans = 0;
        for (int x : res[0]) {
            ans = (ans + x) % mod;
        }
        return ans;
    }

    private int[][] mul(int[][] a, int[][] b) {
        int m = a.length, n = b[0].length;
        int[][] c = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < b.length; ++k) {
                    c[i][j] = (int) ((c[i][j] + 1L * a[i][k] * b[k][j] % mod) % mod);
                }
            }
        }
        return c;
    }

    private int[][] pow(int[][] a, int n) {
        int[][] res = new int[1][a.length];
        Arrays.fill(res[0], 1);
        while (n > 0) {
            if ((n & 1) == 1) {
                res = mul(res, a);
            }
            a = mul(a, a);
            n >>= 1;
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int knightDialer(int n) {
        const int mod = 1e9 + 7;
        vector<vector<int>> base = {
            {0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 1, 0, 0, 0, 0, 0}};
        vector<vector<int>> res = pow(base, n - 1, mod);
        return accumulate(res[0].begin(), res[0].end(), 0LL) % mod;
    }

private:
    vector<vector<int>> mul(const vector<vector<int>>& a, const vector<vector<int>>& b, int mod) {
        int m = a.size(), n = b[0].size();
        vector<vector<int>> c(m, vector<int>(n, 0));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < b.size(); ++k) {
                    c[i][j] = (c[i][j] + (1LL * a[i][k] * b[k][j]) % mod) % mod;
                }
            }
        }
        return c;
    }

    vector<vector<int>> pow(vector<vector<int>>& a, int n, int mod) {
        int size = a.size();
        vector<vector<int>> res(1, vector<int>(size, 1));
        while (n > 0) {
            if (n % 2 == 1) {
                res = mul(res, a, mod);
            }
            a = mul(a, a, mod);
            n /= 2;
        }
        return res;
    }
};
```

#### Go

```go
const mod = 1e9 + 7

func knightDialer(n int) int {
	base := [][]int{
		{0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
		{0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
		{0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
		{1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{1, 1, 0, 0, 0, 0, 0, 1, 0, 0},
		{0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
		{0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
		{0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
	}

	res := pow(base, n-1)
	ans := 0
	for _, x := range res[0] {
		ans = (ans + x) % mod
	}
	return ans
}

func mul(a, b [][]int) [][]int {
	m := len(a)
	n := len(b[0])
	c := make([][]int, m)
	for i := range c {
		c[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			for k := 0; k < len(b); k++ {
				c[i][j] = (c[i][j] + a[i][k]*b[k][j]) % mod
			}
		}
	}
	return c
}

func pow(a [][]int, n int) [][]int {
	size := len(a)
	res := make([][]int, 1)
	res[0] = make([]int, size)
	for i := 0; i < size; i++ {
		res[0][i] = 1
	}

	for n > 0 {
		if n%2 == 1 {
			res = mul(res, a)
		}
		a = mul(a, a)
		n /= 2
	}

	return res
}
```

#### TypeScript

```ts
const mod = 1e9 + 7;

function knightDialer(n: number): number {
    const base: number[][] = [
        [0, 0, 0, 0, 1, 0, 1, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 1, 0, 1, 0],
        [0, 0, 0, 0, 0, 0, 0, 1, 0, 1],
        [0, 0, 0, 0, 1, 0, 0, 0, 1, 0],
        [1, 0, 0, 1, 0, 0, 0, 0, 0, 1],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [1, 1, 0, 0, 0, 0, 0, 1, 0, 0],
        [0, 0, 1, 0, 0, 0, 1, 0, 0, 0],
        [0, 1, 0, 1, 0, 0, 0, 0, 0, 0],
        [0, 0, 1, 0, 1, 0, 0, 0, 0, 0],
    ];

    const res = pow(base, n - 1);
    let ans = 0;
    for (const x of res[0]) {
        ans = (ans + x) % mod;
    }
    return ans;
}

function mul(a: number[][], b: number[][]): number[][] {
    const m = a.length;
    const n = b[0].length;
    const c: number[][] = Array.from({ length: m }, () => Array(n).fill(0));

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            for (let k = 0; k < b.length; k++) {
                c[i][j] =
                    (c[i][j] + Number((BigInt(a[i][k]) * BigInt(b[k][j])) % BigInt(mod))) % mod;
            }
        }
    }
    return c;
}

function pow(a: number[][], n: number): number[][] {
    const size = a.length;
    let res: number[][] = Array.from({ length: 1 }, () => Array(size).fill(1));

    while (n > 0) {
        if (n % 2 === 1) {
            res = mul(res, a);
        }
        a = mul(a, a);
        n = Math.floor(n / 2);
    }

    return res;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
