---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1137.N-th%20Tribonacci%20Number/README.md
rating: 1142
source: 第 147 场周赛 Q1
tags:
    - 记忆化搜索
    - 数学
    - 动态规划
---

<!-- problem:start -->

# [1137. 第 N 个泰波那契数](https://leetcode.cn/problems/n-th-tribonacci-number)

[English Version](/solution/1100-1199/1137.N-th%20Tribonacci%20Number/README_EN.md)

## 题目描述

<!-- description:start -->

<p>泰波那契序列&nbsp;T<sub>n</sub>&nbsp;定义如下：&nbsp;</p>

<p>T<sub>0</sub> = 0, T<sub>1</sub> = 1, T<sub>2</sub> = 1, 且在 n &gt;= 0&nbsp;的条件下 T<sub>n+3</sub> = T<sub>n</sub> + T<sub>n+1</sub> + T<sub>n+2</sub></p>

<p>给你整数&nbsp;<code>n</code>，请返回第 n 个泰波那契数&nbsp;T<sub>n </sub>的值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 4
<strong>输出：</strong>4
<strong>解释：</strong>
T_3 = 0 + 1 + 1 = 2
T_4 = 1 + 1 + 2 = 4
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 25
<strong>输出：</strong>1389537
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 37</code></li>
	<li>答案保证是一个 32 位整数，即&nbsp;<code>answer &lt;= 2^31 - 1</code>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

根据题目中给出的递推式，我们可以使用动态规划求解。

我们定义三个变量 $a$, $b$, $c$，分别表示 $T_{n-3}$, $T_{n-2}$, $T_{n-1}$，初始值分别为 $0$, $1$, $1$。

然后从 $n$ 减小到 $0$，每次更新 $a$, $b$, $c$ 的值，直到 $n$ 为 $0$ 时，答案即为 $a$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为给定的整数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def tribonacci(self, n: int) -> int:
        a, b, c = 0, 1, 1
        for _ in range(n):
            a, b, c = b, c, a + b + c
        return a
```

#### Java

```java
class Solution {
    public int tribonacci(int n) {
        int a = 0, b = 1, c = 1;
        while (n-- > 0) {
            int d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        return a;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int tribonacci(int n) {
        long long a = 0, b = 1, c = 1;
        while (n--) {
            long long d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        return (int) a;
    }
};
```

#### Go

```go
func tribonacci(n int) int {
	a, b, c := 0, 1, 1
	for i := 0; i < n; i++ {
		a, b, c = b, c, a+b+c
	}
	return a
}
```

#### TypeScript

```ts
function tribonacci(n: number): number {
    if (n === 0) {
        return 0;
    }
    if (n < 3) {
        return 1;
    }
    const a = [
        [1, 1, 0],
        [1, 0, 1],
        [1, 0, 0],
    ];
    return pow(a, n - 3)[0].reduce((a, b) => a + b);
}

function mul(a: number[][], b: number[][]): number[][] {
    const [m, n] = [a.length, b[0].length];
    const c = Array.from({ length: m }, () => Array.from({ length: n }, () => 0));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            for (let k = 0; k < b.length; ++k) {
                c[i][j] += a[i][k] * b[k][j];
            }
        }
    }
    return c;
}

function pow(a: number[][], n: number): number[][] {
    let res = [[1, 1, 0]];
    while (n) {
        if (n & 1) {
            res = mul(res, a);
        }
        a = mul(a, a);
        n >>= 1;
    }
    return res;
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {number}
 */
var tribonacci = function (n) {
    let a = 0;
    let b = 1;
    let c = 1;
    while (n--) {
        let d = a + b + c;
        a = b;
        b = c;
        c = d;
    }
    return a;
};
```

#### PHP

```php
class Solution {
    /**
     * @param Integer $n
     * @return Integer
     */
    function tribonacci($n) {
        if ($n == 0) {
            return 0;
        } elseif ($n == 1 || $n == 2) {
            return 1;
        }
        $dp = [0, 1, 1];
        for ($i = 3; $i <= $n; $i++) {
            $dp[$i] = $dp[$i - 1] + $dp[$i - 2] + $dp[$i - 3];
        }
        return $dp[$n];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：矩阵快速幂加速递推

我们设 $Tib(n)$ 表示一个 $1 \times 3$ 的矩阵 $\begin{bmatrix} T_n & T_{n - 1} & T_{n - 2} \end{bmatrix}$，其中 $T_n$, $T_{n - 1}$ 和 $T_{n - 2}$ 分别表示第 $n$ 个、第 $n - 1$ 个和第 $n - 2$ 个泰波那契数。

我们希望根据 $Tib(n-1) = \begin{bmatrix} T_{n - 1} & T_{n - 2} & T_{n - 3} \end{bmatrix}$ 推出 $Tib(n)$。也即是说，我们需要一个矩阵 $base$，使得 $Tib(n - 1) \times base = Tib(n)$，即：

$$
\begin{bmatrix}
T_{n - 1} & T_{n - 2} & T_{n - 3}
\end{bmatrix} \times base = \begin{bmatrix} T_n & T_{n - 1} & T_{n - 2} \end{bmatrix}
$$

由于 $T_n = T_{n - 1} + T_{n - 2} + T_{n - 3}$，所以矩阵 $base$ 为：

$$
\begin{bmatrix}
 1 & 1 & 0 \\
 1 & 0 & 1 \\
 1 & 0 & 0
\end{bmatrix}
$$

我们定义初始矩阵 $res = \begin{bmatrix} 1 & 1  & 0 \end{bmatrix}$，那么 $T_n$ 等于 $res$ 乘以 $base^{n - 3}$ 的结果矩阵中所有元素之和。使用矩阵快速幂求解即可。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
import numpy as np


class Solution:
    def tribonacci(self, n: int) -> int:
        if n == 0:
            return 0
        if n < 3:
            return 1
        factor = np.asmatrix([(1, 1, 0), (1, 0, 1), (1, 0, 0)], np.dtype("O"))
        res = np.asmatrix([(1, 1, 0)], np.dtype("O"))
        n -= 3
        while n:
            if n & 1:
                res *= factor
            factor *= factor
            n >>= 1
        return res.sum()
```

#### Java

```java
class Solution {
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n < 3) {
            return 1;
        }
        int[][] a = {{1, 1, 0}, {1, 0, 1}, {1, 0, 0}};
        int[][] res = pow(a, n - 3);
        int ans = 0;
        for (int x : res[0]) {
            ans += x;
        }
        return ans;
    }

    private int[][] mul(int[][] a, int[][] b) {
        int m = a.length, n = b[0].length;
        int[][] c = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < b.length; ++k) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    private int[][] pow(int[][] a, int n) {
        int[][] res = {{1, 1, 0}};
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
    int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n < 3) {
            return 1;
        }
        vector<vector<ll>> a = {{1, 1, 0}, {1, 0, 1}, {1, 0, 0}};
        vector<vector<ll>> res = pow(a, n - 3);
        return accumulate(res[0].begin(), res[0].end(), 0);
    }

private:
    using ll = long long;
    vector<vector<ll>> mul(vector<vector<ll>>& a, vector<vector<ll>>& b) {
        int m = a.size(), n = b[0].size();
        vector<vector<ll>> c(m, vector<ll>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < b.size(); ++k) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    vector<vector<ll>> pow(vector<vector<ll>>& a, int n) {
        vector<vector<ll>> res = {{1, 1, 0}};
        while (n) {
            if (n & 1) {
                res = mul(res, a);
            }
            a = mul(a, a);
            n >>= 1;
        }
        return res;
    }
};
```

#### Go

```go
func tribonacci(n int) (ans int) {
	if n == 0 {
		return 0
	}
	if n < 3 {
		return 1
	}
	a := [][]int{{1, 1, 0}, {1, 0, 1}, {1, 0, 0}}
	res := pow(a, n-3)
	for _, x := range res[0] {
		ans += x
	}
	return
}

func mul(a, b [][]int) [][]int {
	m, n := len(a), len(b[0])
	c := make([][]int, m)
	for i := range c {
		c[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			for k := 0; k < len(b); k++ {
				c[i][j] += a[i][k] * b[k][j]
			}
		}
	}
	return c
}

func pow(a [][]int, n int) [][]int {
	res := [][]int{{1, 1, 0}}
	for n > 0 {
		if n&1 == 1 {
			res = mul(res, a)
		}
		a = mul(a, a)
		n >>= 1
	}
	return res
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {number}
 */
var tribonacci = function (n) {
    if (n === 0) {
        return 0;
    }
    if (n < 3) {
        return 1;
    }
    const a = [
        [1, 1, 0],
        [1, 0, 1],
        [1, 0, 0],
    ];
    return pow(a, n - 3)[0].reduce((a, b) => a + b);
};

function mul(a, b) {
    const [m, n] = [a.length, b[0].length];
    const c = Array.from({ length: m }, () => Array.from({ length: n }, () => 0));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            for (let k = 0; k < b.length; ++k) {
                c[i][j] += a[i][k] * b[k][j];
            }
        }
    }
    return c;
}

function pow(a, n) {
    let res = [[1, 1, 0]];
    while (n) {
        if (n & 1) {
            res = mul(res, a);
        }
        a = mul(a, a);
        n >>= 1;
    }
    return res;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
