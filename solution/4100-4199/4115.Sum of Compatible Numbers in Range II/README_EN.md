---
comments: true
difficulty: Hard
---

<!-- problem:start -->

# [4115. Sum of Compatible Numbers in Range II 🔒](https://leetcode.com/problems/sum-of-compatible-numbers-in-range-ii)

[中文文档](/solution/4100-4199/4115.Sum%20of%20Compatible%20Numbers%20in%20Range%20II/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>n</code> and <code>k</code>.</p>

<p>A <strong>positive</strong> integer <code>x</code> is called <strong>compatible</strong> if it satisfies both of the following conditions:</p>

<ul>
	<li><code>abs(n - x) &lt;= k</code></li>
	<li><code>(n &amp; x) == 0</code></li>
</ul>

<p>Return the sum of all <strong>compatible</strong> integers <code>x</code>.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p><strong>Note</strong>:</p>

<ul>
	<li>Here, <code>&amp;</code> denotes the <strong>bitwise AND</strong> operator.</li>
	<li>The <strong>absolute</strong> difference between integers <code>i</code> and <code>j</code> is defined as <code>abs(i - j)</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>The compatible integers are:</p>

<ul>
	<li><code>x = 1</code>, since <code>abs(2 - 1) = 1</code> and <code>2 &amp; 1 = 0</code>.</li>
	<li><code>x = 4</code>, since <code>abs(2 - 4) = 2</code> and <code>2 &amp; 4 = 0</code>.</li>
	<li><code>x = 5</code>, since <code>abs(2 - 5) = 3</code> and <code>2 &amp; 5 = 0</code>.</li>
</ul>

<p>Thus, the answer is <code>1 + 4 + 5 = 10</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no compatible integers in the range <code>[4, 6]</code>. Thus, the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>15</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Digit DP

<!-- thinking:start -->

> **Thinking**
>
> Every compatible integer lies in $[\max(1, n-k), n+k]$. The interval can hold up to $2\times 10^{15}$ integers, so checking them one by one does not fit.
>
> A bitwise AND of $0$ only constrains bits: wherever $n$ has a $1$, $x$ must have a $0$. That restriction does not depend on the size of $x$.
>
> The sum over an interval is the sum of integers up to the right endpoint, minus the sum of integers up to one less than the left endpoint. While filling bits from high to low, whether the rest stays tight against the upper bound is decided by the prefix already written.
>
> Binary digit DP therefore keeps only the current bit and whether the number is still tight. A bit that is $1$ in $n$ cannot be filled with $1$. Placing a $1$ contributes that bit's weight times the number of ways to finish the suffix, so each state records both the count and the sum of those integers.

<!-- thinking:end -->

Let $lo=\max(1, n-k)$ and $hi=n+k$. The answer is $\textit{calc}(hi)-\textit{calc}(lo-1)$ modulo $10^9+7$. $\textit{calc}(x)$ is the sum of integers in $[0, x]$ whose bitwise AND with $n$ is $0$. The value $0$ contributes nothing.

Search from the high bit downward, with memoization. $\textit{dfs}(i, \textit{lim})$ is choosing bit $i$, numbered from $0$. $\textit{lim}$ means the prefix is still tight against the upper bound. The call returns how many values the current bit and the lower bits can form, and the sum of those values.

When the prefix is tight, the current bit cannot exceed the same bit of the upper bound; otherwise it may be $0$ or $1$. If bit $i$ of $n$ is $1$, the choice $1$ is forbidden. After placing $\textit{cur}$, recurse to bit $i-1$ and obtain the count $\textit{cnt1}$ and the lower sum $\textit{s1}$. Add $\textit{cnt1}$ to the count and add $\textit{s1}+\textit{cnt1}\cdot\textit{cur}\cdot 2^i$ to the sum, reducing modulo $10^9+7$ while accumulating.

The bit length is that of $hi$. The call $\textit{calc}(lo-1)$ starts at the same high bit: those higher bits of the smaller bound are $0$, so a tight prefix can place only $0$. The two upper bounds differ, so the memoization is cleared before each call.

The time complexity is $O(\log (n+k))$, and the space complexity is $O(\log (n+k))$.

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
