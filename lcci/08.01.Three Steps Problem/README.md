---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/08.01.Three%20Steps%20Problem/README.md
---

<!-- problem:start -->

# [面试题 08.01. 三步问题](https://leetcode.cn/problems/three-steps-problem-lcci)

[English Version](/lcci/08.01.Three%20Steps%20Problem/README_EN.md)

## 题目描述

<!-- description:start -->

<p>三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。</p>

<p> <strong>示例1:</strong></p>

<pre>
<strong> 输入</strong>：n = 3
<strong> 输出</strong>：4
<strong> 说明</strong>: 有四种走法
</pre>

<p> <strong>示例2:</strong></p>

<pre>
<strong> 输入</strong>：n = 5
<strong> 输出</strong>：13
</pre>

<p> <strong>提示:</strong></p>

<ol>
<li>n范围在[1, 1000000]之间</li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递推

我们定义 $f[i]$ 表示上第 $i$ 阶台阶的方法数，初始时 $f[1]=1$, $f[2]=2$, $f[3]=4$。答案为 $f[n]$。

递推公式为 $f[i] = f[i-1] + f[i-2] + f[i-3]$。

由于 $f[i]$ 只与 $f[i-1]$, $f[i-2]$, $f[i-3]$ 有关，因此我们可以使用三个变量 $a$, $b$, $c$ 来存储 $f[i-1]$, $f[i-2]$, $f[i-3]$ 的值，使得空间复杂度降低到 $O(1)$。

时间复杂度 $O(n)$，其中 $n$ 为给定的整数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def waysToStep(self, n: int) -> int:
        a, b, c = 1, 2, 4
        mod = 10**9 + 7
        for _ in range(n - 1):
            a, b, c = b, c, (a + b + c) % mod
        return a
```

#### Java

```java
class Solution {
    public int waysToStep(int n) {
        final int mod = (int) 1e9 + 7;
        int a = 1, b = 2, c = 4;
        for (int i = 1; i < n; ++i) {
            int t = a;
            a = b;
            b = c;
            c = (((a + b) % mod) + t) % mod;
        }
        return a;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int waysToStep(int n) {
        const int mod = 1e9 + 7;
        int a = 1, b = 2, c = 4;
        for (int i = 1; i < n; ++i) {
            int t = a;
            a = b;
            b = c;
            c = (((a + b) % mod) + t) % mod;
        }
        return a;
    }
};
```

#### Go

```go
func waysToStep(n int) int {
	const mod int = 1e9 + 7
	a, b, c := 1, 2, 4
	for i := 1; i < n; i++ {
		a, b, c = b, c, (a+b+c)%mod
	}
	return a
}
```

#### Rust

```rust
impl Solution {
    pub fn ways_to_step(n: i32) -> i32 {
        let (mut a, mut b, mut c) = (1, 2, 4);
        let m = 1000000007;
        for _ in 1..n {
            let t = a;
            a = b;
            b = c;
            c = (((a + b) % m) + t) % m;
        }
        a
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {number}
 */
var waysToStep = function (n) {
    let [a, b, c] = [1, 2, 4];
    const mod = 1e9 + 7;
    for (let i = 1; i < n; ++i) {
        [a, b, c] = [b, c, (a + b + c) % mod];
    }
    return a;
};
```

#### C

```c
int waysToStep(int n) {
    const int mod = 1e9 + 7;
    int a = 1, b = 2, c = 4;
    for (int i = 1; i < n; ++i) {
        int t = a;
        a = b;
        b = c;
        c = (((a + b) % mod) + t) % mod;
    }
    return a;
}
```

#### Swift

```swift
class Solution {
    func waysToStep(_ n: Int) -> Int {
        let mod = Int(1e9) + 7
        var a = 1, b = 2, c = 4
        if n == 1 { return a }
        if n == 2 { return b }
        if n == 3 { return c }

        for _ in 1..<n {
            let t = a
            a = b
            b = c
            c = ((a + b) % mod + t) % mod
        }
        return a
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法二：矩阵快速幂加速递推

我们设 $F(n)$ 表示一个 $1 \times 3$ 的矩阵 $\begin{bmatrix} F_{n - 1} & F_{n - 2} & F_{n - 3} \end{bmatrix}$，其中 $F_{n - 1}$, $F_{n - 2}$ 和 $F_{n - 3}$ 分别表示上第 $n - 1$ 阶、第 $n - 2$ 阶和第 $n - 3$ 阶台阶的方法数。

我们希望根据 $F(n-1) = \begin{bmatrix} F_{n - 2} & F_{n - 3} & F_{n - 4} \end{bmatrix}$ 推出 $F(n)$。也即是说，我们需要一个矩阵 $base$，使得 $F(n - 1) \times base = F(n)$，即：

$$
\begin{bmatrix}
F_{n - 2} & T_{n - 3} & T_{n - 4}
\end{bmatrix} \times base = \begin{bmatrix} F_{n - 1} & F_{n - 2} & F_{n - 3} \end{bmatrix}
$$

由于 $F_n = F_{n - 1} + F_{n - 2} + F_{n - 3}$，所以矩阵 $base$ 为：

$$
\begin{bmatrix}
 1 & 1 & 0 \\
 1 & 0 & 1 \\
 1 & 0 & 0
\end{bmatrix}
$$

我们定义初始矩阵 $res = \begin{bmatrix} 1 & 1  & 0 \end{bmatrix}$，那么 $F_n$ 等于 $res$ 乘以 $base^{n - 4}$ 的结果矩阵中所有元素之和。使用矩阵快速幂求解即可。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
import numpy as np


class Solution:
    def waysToStep(self, n: int) -> int:
        if n < 4:
            return 2 ** (n - 1)
        mod = 10**9 + 7
        factor = np.asmatrix([(1, 1, 0), (1, 0, 1), (1, 0, 0)], np.dtype("O"))
        res = np.asmatrix([(4, 2, 1)], np.dtype("O"))
        n -= 4
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

    public int waysToStep(int n) {
        if (n < 4) {
            return (int) Math.pow(2, n - 1);
        }
        long[][] a = {{1, 1, 0}, {1, 0, 1}, {1, 0, 0}};
        long[][] res = pow(a, n - 4);
        long ans = 0;
        for (long x : res[0]) {
            ans = (ans + x) % mod;
        }
        return (int) ans;
    }

    private long[][] mul(long[][] a, long[][] b) {
        int m = a.length, n = b[0].length;
        long[][] c = new long[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < b.length; ++k) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j] % mod) % mod;
                }
            }
        }
        return c;
    }

    private long[][] pow(long[][] a, int n) {
        long[][] res = {{4, 2, 1}};
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
    int waysToStep(int n) {
        if (n < 4) {
            return pow(2, n - 1);
        }
        vector<vector<ll>> a = {{1, 1, 0}, {1, 0, 1}, {1, 0, 0}};
        vector<vector<ll>> res = qpow(a, n - 4);
        ll ans = 0;
        for (ll x : res[0]) {
            ans = (ans + x) % mod;
        }
        return ans;
    }

private:
    using ll = long long;
    const int mod = 1e9 + 7;
    vector<vector<ll>> mul(vector<vector<ll>>& a, vector<vector<ll>>& b) {
        int m = a.size(), n = b[0].size();
        vector<vector<ll>> c(m, vector<ll>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < b.size(); ++k) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j] % mod) % mod;
                }
            }
        }
        return c;
    }

    vector<vector<ll>> qpow(vector<vector<ll>>& a, int n) {
        vector<vector<ll>> res = {{4, 2, 1}};
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
const mod = 1e9 + 7

func waysToStep(n int) (ans int) {
	if n < 4 {
		return int(math.Pow(2, float64(n-1)))
	}
	a := [][]int{{1, 1, 0}, {1, 0, 1}, {1, 0, 0}}
	res := pow(a, n-4)
	for _, x := range res[0] {
		ans = (ans + x) % mod
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
				c[i][j] = (c[i][j] + a[i][k]*b[k][j]%mod) % mod
			}
		}
	}
	return c
}

func pow(a [][]int, n int) [][]int {
	res := [][]int{{4, 2, 1}}
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

const mod = 1e9 + 7;

var waysToStep = function (n) {
    if (n < 4) {
        return Math.pow(2, n - 1);
    }
    const a = [
        [1, 1, 0],
        [1, 0, 1],
        [1, 0, 0],
    ];
    let ans = 0;
    const res = pow(a, n - 4);
    for (const x of res[0]) {
        ans = (ans + x) % mod;
    }
    return ans;
};

function mul(a, b) {
    const [m, n] = [a.length, b[0].length];
    const c = Array.from({ length: m }, () => Array.from({ length: n }, () => 0));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            for (let k = 0; k < b.length; ++k) {
                c[i][j] =
                    (c[i][j] + Number((BigInt(a[i][k]) * BigInt(b[k][j])) % BigInt(mod))) % mod;
            }
        }
    }
    return c;
}

function pow(a, n) {
    let res = [[4, 2, 1]];
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
