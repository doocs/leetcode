---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1220.Count%20Vowels%20Permutation/README.md
rating: 1729
source: 第 157 场周赛 Q4
tags:
    - 动态规划
---

<!-- problem:start -->

# [1220. 统计元音字母序列的数目](https://leetcode.cn/problems/count-vowels-permutation)

[English Version](/solution/1200-1299/1220.Count%20Vowels%20Permutation/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数&nbsp;<code>n</code>，请你帮忙统计一下我们可以按下述规则形成多少个长度为&nbsp;<code>n</code>&nbsp;的字符串：</p>

<ul>
	<li>字符串中的每个字符都应当是小写元音字母（<code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, <code>&#39;u&#39;</code>）</li>
	<li>每个元音&nbsp;<code>&#39;a&#39;</code>&nbsp;后面都只能跟着&nbsp;<code>&#39;e&#39;</code></li>
	<li>每个元音&nbsp;<code>&#39;e&#39;</code>&nbsp;后面只能跟着&nbsp;<code>&#39;a&#39;</code>&nbsp;或者是&nbsp;<code>&#39;i&#39;</code></li>
	<li>每个元音&nbsp;<code>&#39;i&#39;</code>&nbsp;后面&nbsp;<strong>不能</strong> 再跟着另一个&nbsp;<code>&#39;i&#39;</code></li>
	<li>每个元音&nbsp;<code>&#39;o&#39;</code>&nbsp;后面只能跟着&nbsp;<code>&#39;i&#39;</code>&nbsp;或者是&nbsp;<code>&#39;u&#39;</code></li>
	<li>每个元音&nbsp;<code>&#39;u&#39;</code>&nbsp;后面只能跟着&nbsp;<code>&#39;a&#39;</code></li>
</ul>

<p>由于答案可能会很大，所以请你返回 模&nbsp;<code>10^9 + 7</code>&nbsp;之后的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 1
<strong>输出：</strong>5
<strong>解释：</strong>所有可能的字符串分别是：&quot;a&quot;, &quot;e&quot;, &quot;i&quot; , &quot;o&quot; 和 &quot;u&quot;。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 2
<strong>输出：</strong>10
<strong>解释：</strong>所有可能的字符串分别是：&quot;ae&quot;, &quot;ea&quot;, &quot;ei&quot;, &quot;ia&quot;, &quot;ie&quot;, &quot;io&quot;, &quot;iu&quot;, &quot;oi&quot;, &quot;ou&quot; 和 &quot;ua&quot;。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 5
<strong>输出：</strong>68</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2 * 10^4</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

根据题目描述，我们先列出每个元音字母的后一个字母：

```
a [e]
e [a|i]
i [a|e|o|u]
o [i|u]
u [a]
```

那么我们可以推出每个元音字母的前一个字母：

```
[e|i|u]	a
[a|i]	e
[e|o]	i
[i]	o
[i|o]	u
```

我们定义 $f[i]$ 表示当前长度以第 $i$ 个元音字母结尾的字符串的个数，如果长度为 $1$，那么 $f[i]=1$。

当长度大于 $1$ 时，我们定义 $g[i]$ 表示当前长度以第 $i$ 个元音字母结尾的字符串的个数，那么 $g[i]$ 可以由 $f$ 转移而来，即：

$$
g[i]=
\begin{cases}
f[1]+f[2]+f[4] & i=0 \\
f[0]+f[2] & i=1 \\
f[1]+f[3] & i=2 \\
f[2] & i=3 \\
f[2]+f[3] & i=4
\end{cases}
$$

最终答案为 $\sum_{i=0}^{4}f[i]$。注意由于答案可能会很大，所以需要对 $10^9+7$ 取模。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 是字符串的长度，而 $C$ 是元音字母的个数。本题中 $C=5$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countVowelPermutation(self, n: int) -> int:
        f = [1] * 5
        mod = 10**9 + 7
        for _ in range(n - 1):
            g = [0] * 5
            g[0] = (f[1] + f[2] + f[4]) % mod
            g[1] = (f[0] + f[2]) % mod
            g[2] = (f[1] + f[3]) % mod
            g[3] = f[2]
            g[4] = (f[2] + f[3]) % mod
            f = g
        return sum(f) % mod
```

#### Java

```java
class Solution {
    public int countVowelPermutation(int n) {
        long[] f = new long[5];
        Arrays.fill(f, 1);
        final int mod = (int) 1e9 + 7;
        for (int i = 1; i < n; ++i) {
            long[] g = new long[5];
            g[0] = (f[1] + f[2] + f[4]) % mod;
            g[1] = (f[0] + f[2]) % mod;
            g[2] = (f[1] + f[3]) % mod;
            g[3] = f[2];
            g[4] = (f[2] + f[3]) % mod;
            f = g;
        }
        long ans = 0;
        for (long x : f) {
            ans = (ans + x) % mod;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countVowelPermutation(int n) {
        using ll = long long;
        vector<ll> f(5, 1);
        const int mod = 1e9 + 7;
        for (int i = 1; i < n; ++i) {
            vector<ll> g(5);
            g[0] = (f[1] + f[2] + f[4]) % mod;
            g[1] = (f[0] + f[2]) % mod;
            g[2] = (f[1] + f[3]) % mod;
            g[3] = f[2];
            g[4] = (f[2] + f[3]) % mod;
            f = move(g);
        }
        return accumulate(f.begin(), f.end(), 0LL) % mod;
    }
};
```

#### Go

```go
func countVowelPermutation(n int) (ans int) {
	const mod int = 1e9 + 7
	f := make([]int, 5)
	for i := range f {
		f[i] = 1
	}
	for i := 1; i < n; i++ {
		g := make([]int, 5)
		g[0] = (f[1] + f[2] + f[4]) % mod
		g[1] = (f[0] + f[2]) % mod
		g[2] = (f[1] + f[3]) % mod
		g[3] = f[2] % mod
		g[4] = (f[2] + f[3]) % mod
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
function countVowelPermutation(n: number): number {
    const f: number[] = Array(5).fill(1);
    const mod = 1e9 + 7;
    for (let i = 1; i < n; ++i) {
        const g: number[] = Array(5).fill(0);
        g[0] = (f[1] + f[2] + f[4]) % mod;
        g[1] = (f[0] + f[2]) % mod;
        g[2] = (f[1] + f[3]) % mod;
        g[3] = f[2];
        g[4] = (f[2] + f[3]) % mod;
        f.splice(0, 5, ...g);
    }
    return f.reduce((a, b) => (a + b) % mod);
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {number}
 */
var countVowelPermutation = function (n) {
    const mod = 1e9 + 7;
    const f = Array(5).fill(1);
    for (let i = 1; i < n; ++i) {
        const g = Array(5).fill(0);
        g[0] = (f[1] + f[2] + f[4]) % mod;
        g[1] = (f[0] + f[2]) % mod;
        g[2] = (f[1] + f[3]) % mod;
        g[3] = f[2];
        g[4] = (f[2] + f[3]) % mod;
        f.splice(0, 5, ...g);
    }
    return f.reduce((a, b) => (a + b) % mod);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：矩阵快速幂加速递推

时间复杂度 $O(C^3 \times \log n)$，空间复杂度 $O(C^2)$，其中 $C$ 是元音字母的个数，本题中 $C=5$。

<!-- tabs:start -->

#### Python3

```python
import numpy as np


class Solution:
    def countVowelPermutation(self, n: int) -> int:
        mod = 10**9 + 7
        factor = np.asmatrix(
            [
                (0, 1, 0, 0, 0),
                (1, 0, 1, 0, 0),
                (1, 1, 0, 1, 1),
                (0, 0, 1, 0, 1),
                (1, 0, 0, 0, 0),
            ],
            np.dtype("O"),
        )
        res = np.asmatrix([(1, 1, 1, 1, 1)], np.dtype("O"))
        n -= 1
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

    public int countVowelPermutation(int n) {
        long[][] a
            = {{0, 1, 0, 0, 0}, {1, 0, 1, 0, 0}, {1, 1, 0, 1, 1}, {0, 0, 1, 0, 1}, {1, 0, 0, 0, 0}};
        long[][] res = pow(a, n - 1);
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
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % mod;
                }
            }
        }
        return c;
    }

    private long[][] pow(long[][] a, int n) {
        long[][] res = new long[1][a.length];
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
    int countVowelPermutation(int n) {
        vector<vector<ll>> a = {
            {0, 1, 0, 0, 0},
            {1, 0, 1, 0, 0},
            {1, 1, 0, 1, 1},
            {0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0}};
        vector<vector<ll>> res = pow(a, n - 1);
        return accumulate(res[0].begin(), res[0].end(), 0LL) % mod;
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
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % mod;
                }
            }
        }
        return c;
    }

    vector<vector<ll>> pow(vector<vector<ll>>& a, int n) {
        vector<vector<ll>> res;
        res.push_back({1, 1, 1, 1, 1});
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

func countVowelPermutation(n int) (ans int) {
	a := [][]int{
		{0, 1, 0, 0, 0},
		{1, 0, 1, 0, 0},
		{1, 1, 0, 1, 1},
		{0, 0, 1, 0, 1},
		{1, 0, 0, 0, 0}}
	res := pow(a, n-1)
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
				c[i][j] = (c[i][j] + a[i][k]*b[k][j]) % mod
			}
		}
	}
	return c
}

func pow(a [][]int, n int) [][]int {
	res := [][]int{{1, 1, 1, 1, 1}}
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

#### TypeScript

```ts
const mod = 1e9 + 7;

function countVowelPermutation(n: number): number {
    const a: number[][] = [
        [0, 1, 0, 0, 0],
        [1, 0, 1, 0, 0],
        [1, 1, 0, 1, 1],
        [0, 0, 1, 0, 1],
        [1, 0, 0, 0, 0],
    ];
    const res = pow(a, n - 1);
    return res[0].reduce((a, b) => (a + b) % mod);
}

function mul(a: number[][], b: number[][]): number[][] {
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

function pow(a: number[][], n: number): number[][] {
    let res: number[][] = [[1, 1, 1, 1, 1]];
    while (n) {
        if (n & 1) {
            res = mul(res, a);
        }
        a = mul(a, a);
        n >>>= 1;
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

const mod = 1e9 + 7;

var countVowelPermutation = function (n) {
    const a = [
        [0, 1, 0, 0, 0],
        [1, 0, 1, 0, 0],
        [1, 1, 0, 1, 1],
        [0, 0, 1, 0, 1],
        [1, 0, 0, 0, 0],
    ];
    const res = pow(a, n - 1);
    return res[0].reduce((a, b) => (a + b) % mod);
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
    let res = [[1, 1, 1, 1, 1]];
    while (n) {
        if (n & 1) {
            res = mul(res, a);
        }
        a = mul(a, a);
        n >>>= 1;
    }
    return res;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
