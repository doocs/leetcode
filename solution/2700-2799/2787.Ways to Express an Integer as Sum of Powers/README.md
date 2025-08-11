---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2787.Ways%20to%20Express%20an%20Integer%20as%20Sum%20of%20Powers/README.md
rating: 1817
source: 第 109 场双周赛 Q4
tags:
    - 动态规划
---

<!-- problem:start -->

# [2787. 将一个数字表示成幂的和的方案数](https://leetcode.cn/problems/ways-to-express-an-integer-as-sum-of-powers)

[English Version](/solution/2700-2799/2787.Ways%20to%20Express%20an%20Integer%20as%20Sum%20of%20Powers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个 <strong>正</strong>&nbsp;整数&nbsp;<code>n</code> 和&nbsp;<code>x</code>&nbsp;。</p>

<p>请你返回将<em>&nbsp;</em><code>n</code>&nbsp;表示成一些&nbsp;<strong>互不相同</strong>&nbsp;正整数的<em>&nbsp;</em><code>x</code>&nbsp;次幂之和的方案数。换句话说，你需要返回互不相同整数&nbsp;<code>[n<sub>1</sub>, n<sub>2</sub>, ..., n<sub>k</sub>]</code>&nbsp;的集合数目，满足&nbsp;<code>n = n<sub>1</sub><sup>x</sup> + n<sub>2</sub><sup>x</sup> + ... + n<sub>k</sub><sup>x</sup></code>&nbsp;。</p>

<p>由于答案可能非常大，请你将它对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;取余后返回。</p>

<p>比方说，<code>n = 160</code> 且&nbsp;<code>x = 3</code>&nbsp;，一个表示&nbsp;<code>n</code>&nbsp;的方法是&nbsp;<code>n = 2<sup>3</sup> + 3<sup>3</sup> + 5<sup>3</sup></code><sup>&nbsp;</sup>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>n = 10, x = 2
<b>输出：</b>1
<b>解释：</b>我们可以将 n 表示为：n = 3<sup>2</sup> + 1<sup>2</sup> = 10 。
这是唯一将 10 表达成不同整数 2 次方之和的方案。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>n = 4, x = 1
<b>输出：</b>2
<b>解释：</b>我们可以将 n 按以下方案表示：
- n = 4<sup>1</sup> = 4 。
- n = 3<sup>1</sup> + 1<sup>1</sup> = 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 300</code></li>
	<li><code>1 &lt;= x &lt;= 5</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][j]$ 表示在前 $i$ 个正整数中选取一些数的 $x$ 次幂之和等于 $j$ 的方案数，初始时 $f[0][0] = 1$，其余均为 $0$。答案为 $f[n][n]$。

对于每个正整数 $i$，我们可以选择不选它或者选它：

-   不选它：此时方案数为 $f[i-1][j]$；
-   选它：此时方案数为 $f[i-1][j-i^x]$（前提是 $j \geq i^x$）。

因此状态转移方程为：

$$
f[i][j] = f[i-1][j] + (j \geq i^x ? f[i-1][j-i^x] : 0)
$$

注意到答案可能非常大，我们需要对 $10^9 + 7$ 取余。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是题目中给定的整数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfWays(self, n: int, x: int) -> int:
        mod = 10**9 + 7
        f = [[0] * (n + 1) for _ in range(n + 1)]
        f[0][0] = 1
        for i in range(1, n + 1):
            k = pow(i, x)
            for j in range(n + 1):
                f[i][j] = f[i - 1][j]
                if k <= j:
                    f[i][j] = (f[i][j] + f[i - 1][j - k]) % mod
        return f[n][n]
```

#### Java

```java
class Solution {
    public int numberOfWays(int n, int x) {
        final int mod = (int) 1e9 + 7;
        int[][] f = new int[n + 1][n + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            long k = (long) Math.pow(i, x);
            for (int j = 0; j <= n; ++j) {
                f[i][j] = f[i - 1][j];
                if (k <= j) {
                    f[i][j] = (f[i][j] + f[i - 1][j - (int) k]) % mod;
                }
            }
        }
        return f[n][n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfWays(int n, int x) {
        const int mod = 1e9 + 7;
        int f[n + 1][n + 1];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            long long k = (long long) pow(i, x);
            for (int j = 0; j <= n; ++j) {
                f[i][j] = f[i - 1][j];
                if (k <= j) {
                    f[i][j] = (f[i][j] + f[i - 1][j - k]) % mod;
                }
            }
        }
        return f[n][n];
    }
};
```

#### Go

```go
func numberOfWays(n int, x int) int {
	const mod int = 1e9 + 7
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	f[0][0] = 1
	for i := 1; i <= n; i++ {
		k := int(math.Pow(float64(i), float64(x)))
		for j := 0; j <= n; j++ {
			f[i][j] = f[i-1][j]
			if k <= j {
				f[i][j] = (f[i][j] + f[i-1][j-k]) % mod
			}
		}
	}
	return f[n][n]
}
```

#### TypeScript

```ts
function numberOfWays(n: number, x: number): number {
    const mod = 10 ** 9 + 7;
    const f = Array.from({ length: n + 1 }, () => Array(n + 1).fill(0));
    f[0][0] = 1;
    for (let i = 1; i <= n; ++i) {
        const k = Math.pow(i, x);
        for (let j = 0; j <= n; ++j) {
            f[i][j] = f[i - 1][j];
            if (k <= j) {
                f[i][j] = (f[i][j] + f[i - 1][j - k]) % mod;
            }
        }
    }
    return f[n][n];
}
```

#### Rust

```rust
impl Solution {
    pub fn number_of_ways(n: i32, x: i32) -> i32 {
        const MOD: i64 = 1_000_000_007;
        let n = n as usize;
        let x = x as u32;
        let mut f = vec![vec![0; n + 1]; n + 1];
        f[0][0] = 1;
        for i in 1..=n {
            let k = (i as i64).pow(x);
            for j in 0..=n {
                f[i][j] = f[i - 1][j];
                if j >= k as usize {
                    f[i][j] = (f[i][j] + f[i - 1][j - k as usize]) % MOD;
                }
            }
        }
        f[n][n] as i32
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @param {number} x
 * @return {number}
 */
var numberOfWays = function (n, x) {
    const mod = 10 ** 9 + 7;
    const f = Array.from({ length: n + 1 }, () => Array(n + 1).fill(0));
    f[0][0] = 1;
    for (let i = 1; i <= n; ++i) {
        const k = Math.pow(i, x);
        for (let j = 0; j <= n; ++j) {
            f[i][j] = f[i - 1][j];
            if (k <= j) {
                f[i][j] = (f[i][j] + f[i - 1][j - k]) % mod;
            }
        }
    }
    return f[n][n];
};
```

#### C#

```cs
public class Solution {
    public int NumberOfWays(int n, int x) {
        const int mod = 1000000007;
        int[,] f = new int[n + 1, n + 1];
        f[0, 0] = 1;
        for (int i = 1; i <= n; ++i) {
            long k = (long)Math.Pow(i, x);
            for (int j = 0; j <= n; ++j) {
                f[i, j] = f[i - 1, j];
                if (k <= j) {
                    f[i, j] = (f[i, j] + f[i - 1, j - (int)k]) % mod;
                }
            }
        }
        return f[n, n];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
