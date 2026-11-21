---
comments: true
difficulty: 困难
---

<!-- problem:start -->

# [4115. 区间内的兼容数字之和 II 🔒](https://leetcode.cn/problems/sum-of-compatible-numbers-in-range-ii)

[English Version](/solution/4100-4199/4115.Sum%20of%20Compatible%20Numbers%20in%20Range%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数 <code>n</code> 和 <code>k</code>。</p>

<p>如果一个<strong>&nbsp;正&nbsp;</strong>整数 <code>x</code> 同时满足以下两个条件，则称其为&nbsp;<strong>兼容</strong>：</p>

<ul>
	<li><code>abs(n - x) &lt;= k</code></li>
	<li><code>(n &amp; x) == 0</code></li>
</ul>

<p>返回所有&nbsp;<strong>兼容&nbsp;</strong>整数 <code>x</code> 的总和。</p>

<p>由于答案可能很大，请返回其对 <code>10<sup>9</sup> + 7</code> 取模后的结果。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>这里，<code>&amp;</code> 表示&nbsp;<strong>按位与</strong>&nbsp;运算符。</li>
	<li>整数 <code>i</code> 和 <code>j</code> 之间的&nbsp;<strong>绝对&nbsp;</strong>差定义为 <code>abs(i - j)</code>。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 2, k = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">10</span></p>

<p><strong>解释：</strong></p>

<p>兼容整数为：</p>

<ul>
	<li><code>x = 1</code>，因为 <code>abs(2 - 1) = 1</code> 且 <code>2 &amp; 1 = 0</code>。</li>
	<li><code>x = 4</code>，因为 <code>abs(2 - 4) = 2</code> 且 <code>2 &amp; 4 = 0</code>。</li>
	<li><code>x = 5</code>，因为 <code>abs(2 - 5) = 3</code> 且 <code>2 &amp; 5 = 0</code>。</li>
</ul>

<p>因此，答案为 <code>1 + 4 + 5 = 10</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 5, k = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>区间 <code>[4, 6]</code> 中没有兼容整数。因此，答案为 0。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>15</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数位 DP

<!-- thinking:start -->

> **思考**
>
> 兼容数都落在 $[\max(1, n-k), n+k]$ 中，区间最长可达 $2\times 10^{15}$，逐个检查无法通过。
>
> 按位与为 $0$ 只约束二进制位： $n$ 中为 $1$ 的位， $x$ 上必须为 $0$。这个限制与 $x$ 的大小无关。
>
> 区间内的和等于不超过上界的和，减去不超过下界减一的和。从高位往低位填二进制时，剩余部分是否仍然贴着上界，只取决于已经确定的前缀。
>
> 于是按二进制做数位 DP，状态只保留当前位以及是否贴着上界。当前位在 $n$ 中为 $1$ 时不能填 $1$。某一位填 $1$ 的贡献是该位权重乘以后续方案数，因此每个状态同时记录方案数和这些整数的和。

<!-- thinking:end -->

我们令 $lo=\max(1, n-k)$， $hi=n+k$。答案为 $\textit{calc}(hi)-\textit{calc}(lo-1)$ 对 $10^9+7$ 取模后的结果。 $\textit{calc}(x)$ 表示 $[0, x]$ 中与 $n$ 按位与为 $0$ 的整数之和， $0$ 的贡献为 $0$。

从高位到低位做记忆化搜索。 $\textit{dfs}(i, \textit{lim})$ 表示正在确定第 $i$ 位（从 $0$ 编号）， $\textit{lim}$ 表示是否仍然贴着上界。它返回这一位及更低位能组成的方案数，以及这些位组成的数值之和。

贴着上界时，当前位不能超过上界的对应位，否则可以填 $0$ 或 $1$。若 $n$ 的第 $i$ 位为 $1$，则不能填 $1$。当前位取 $\textit{cur}$ 后，向第 $i-1$ 位递归，得到方案数 $\textit{cnt1}$ 与低位和 $\textit{s1}$。方案数加上 $\textit{cnt1}$，和加上 $\textit{s1}+\textit{cnt1}\cdot\textit{cur}\cdot 2^i$，并在累加时对 $10^9+7$ 取模。

位数取 $hi$ 的二进制长度。计算 $\textit{calc}(lo-1)$ 时从同一高位开始：较小上界的这些高位是 $0$，贴着上界就只能填 $0$。两次调用的上界不同，所以计算前要清空记忆化。

时间复杂度 $O(\log (n+k))$，空间复杂度 $O(\log (n+k))$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumOfGoodIntegers(self, n: int, k: int) -> int:
        @cache
        def dfs(i: int, lim: bool) -> tuple[int, int]:
            if i < 0:
                return 1, 0
            up = (num >> i & 1) if lim else 1
            cnt = s = 0
            for cur in range(up + 1):
                if (n >> i & 1) == 1 and cur == 1:
                    continue
                cnt1, s1 = dfs(i - 1, lim and cur == up)
                cnt += cnt1
                s += s1 + cnt1 * (cur << i) % mod
            return cnt, s % mod

        mod = 10**9 + 7
        lo, hi = max(1, n - k), n + k
        m = hi.bit_length()

        num = hi
        right = dfs(m - 1, True)[1]
        dfs.cache_clear()

        num = lo - 1
        left = dfs(m - 1, True)[1]
        dfs.cache_clear()

        return (right - left + mod) % mod
```

#### Java

```java
class Solution {
    private static final long MOD = 1_000_000_007;
    private long n, num;
    private long[][][] memo;

    private long[] dfs(int i, boolean lim) {
        if (i < 0) {
            return new long[] {1, 0};
        }
        int j = lim ? 1 : 0;
        if (memo[i][j][0] != -1) {
            return memo[i][j];
        }
        long up = lim ? (num >> i & 1) : 1;
        long cnt = 0, s = 0;
        for (int cur = 0; cur <= up; cur++) {
            if ((n >> i & 1) == 1 && cur == 1) {
                continue;
            }
            long[] res = dfs(i - 1, lim && cur == up);
            long cnt1 = res[0], s1 = res[1];
            cnt = (cnt + cnt1) % MOD;
            s = (s + s1 + cnt1 * ((1L << i) % MOD) % MOD * cur) % MOD;
        }
        return memo[i][j] = new long[] {cnt, s};
    }

    private long calc(long num, int m) {
        this.num = num;
        memo = new long[m][2][2];
        for (long[][] a : memo) {
            for (long[] b : a) {
                b[0] = -1;
            }
        }
        return dfs(m - 1, true)[1];
    }

    public int sumOfGoodIntegers(long n, long k) {
        this.n = n;
        long lo = Math.max(1, n - k), hi = n + k;
        int m = 64 - Long.numberOfLeadingZeros(hi);
        long right = calc(hi, m);
        long left = calc(lo - 1, m);
        return (int) ((right - left + MOD) % MOD);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int sumOfGoodIntegers(long long n, long long k) {
        const long long MOD = 1'000'000'007;
        long long num;
        vector<vector<array<long long, 2>>> memo;

        auto calc = [&](long long x, int m) -> long long {
            num = x;
            memo.assign(m, vector<array<long long, 2>>(2, {-1, -1}));

            auto dfs = [&](this auto&& dfs, int i, bool lim) -> pair<long long, long long> {
                if (i < 0) {
                    return {1, 0};
                }
                int j = lim ? 1 : 0;
                if (memo[i][j][0] != -1) {
                    return {memo[i][j][0], memo[i][j][1]};
                }
                long long up = lim ? (num >> i & 1) : 1;
                long long cnt = 0, s = 0;
                for (int cur = 0; cur <= up; cur++) {
                    if ((n >> i & 1) == 1 && cur == 1) {
                        continue;
                    }
                    auto [cnt1, s1] = dfs(i - 1, lim && cur == up);
                    cnt = (cnt + cnt1) % MOD;
                    s = (s + s1 + cnt1 * ((1LL << i) % MOD) % MOD * cur) % MOD;
                }
                memo[i][j] = {cnt, s};
                return {cnt, s};
            };

            return dfs(m - 1, true).second;
        };

        long long lo = max(1LL, n - k), hi = n + k;
        int m = 64 - __builtin_clzll(hi);
        long long right = calc(hi, m);
        long long left = calc(lo - 1, m);
        return (right - left + MOD) % MOD;
    }
};
```

#### Go

```go
func sumOfGoodIntegers(n int64, k int64) int {
	const MOD int64 = 1_000_000_007
	var num int64
	var memo [64][2][2]int64

	calc := func(x int64, m int) int64 {
		num = x
		for i := range memo {
			for j := range memo[i] {
				memo[i][j] = [2]int64{-1, -1}
			}
		}

		var dfs func(int, bool) (int64, int64)
		dfs = func(i int, lim bool) (int64, int64) {
			if i < 0 {
				return 1, 0
			}
			j := 0
			if lim {
				j = 1
			}
			if memo[i][j][0] != -1 {
				return memo[i][j][0], memo[i][j][1]
			}
			up := int64(1)
			if lim {
				up = num >> i & 1
			}
			var cnt, s int64
			for cur := int64(0); cur <= up; cur++ {
				if (n>>i&1) == 1 && cur == 1 {
					continue
				}
				cnt1, s1 := dfs(i-1, lim && cur == up)
				cnt = (cnt + cnt1) % MOD
				s = (s + s1 + cnt1*((1<<i)%MOD)%MOD*cur) % MOD
			}
			memo[i][j] = [2]int64{cnt, s}
			return cnt, s
		}

		_, s := dfs(m-1, true)
		return s
	}

	lo := max(int64(1), n-k)
	hi := n + k
	m := 64 - bits.LeadingZeros64(uint64(hi))
	right := calc(hi, m)
	left := calc(lo-1, m)
	return int((right - left + MOD) % MOD)
}
```

#### TypeScript

```ts
function sumOfGoodIntegers(n: number, k: number): number {
    const MOD = 1000000007n;
    const N = BigInt(n),
        K = BigInt(k);
    let num: bigint;
    let memo: bigint[][][];

    const calc = (x: bigint, m: number): bigint => {
        num = x;
        memo = Array.from({ length: m }, () => Array.from({ length: 2 }, () => [-1n, -1n]));

        const dfs = (i: number, lim: boolean): [bigint, bigint] => {
            if (i < 0) {
                return [1n, 0n];
            }
            const j = lim ? 1 : 0;
            if (memo[i][j][0] !== -1n) {
                return memo[i][j] as [bigint, bigint];
            }
            const up = lim ? Number((num >> BigInt(i)) & 1n) : 1;
            let cnt = 0n,
                s = 0n;
            for (let cur = 0; cur <= up; cur++) {
                if (((N >> BigInt(i)) & 1n) === 1n && cur === 1) {
                    continue;
                }
                const [cnt1, s1] = dfs(i - 1, lim && cur === up);
                cnt = (cnt + cnt1) % MOD;
                s = (s + s1 + ((cnt1 * ((1n << BigInt(i)) % MOD)) % MOD) * BigInt(cur)) % MOD;
            }
            memo[i][j] = [cnt, s];
            return [cnt, s];
        };

        return dfs(m - 1, true)[1];
    };

    const lo = N - K > 1n ? N - K : 1n;
    const hi = N + K;
    const m = hi.toString(2).length;
    const right = calc(hi, m);
    const left = calc(lo - 1n, m);
    return Number((right - left + MOD) % MOD);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
