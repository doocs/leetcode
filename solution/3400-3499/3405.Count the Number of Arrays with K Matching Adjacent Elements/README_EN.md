---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3405.Count%20the%20Number%20of%20Arrays%20with%20K%20Matching%20Adjacent%20Elements/README_EN.md
rating: 2309
source: Weekly Contest 430 Q4
tags:
    - Math
    - Combinatorics
---

<!-- problem:start -->

# [3405. Count the Number of Arrays with K Matching Adjacent Elements](https://leetcode.com/problems/count-the-number-of-arrays-with-k-matching-adjacent-elements)

[中文文档](/solution/3400-3499/3405.Count%20the%20Number%20of%20Arrays%20with%20K%20Matching%20Adjacent%20Elements/README.md)

## Description

<!-- description:start -->

<p>You are given three integers <code>n</code>, <code>m</code>, <code>k</code>. A <strong>good array</strong> <code>arr</code> of size <code>n</code> is defined as follows:</p>

<ul>
	<li>Each element in <code>arr</code> is in the <strong>inclusive</strong> range <code>[1, m]</code>.</li>
	<li><em>Exactly</em> <code>k</code> indices <code>i</code> (where <code>1 &lt;= i &lt; n</code>) satisfy the condition <code>arr[i - 1] == arr[i]</code>.</li>
</ul>

<p>Return the number of <strong>good arrays</strong> that can be formed.</p>

<p>Since the answer may be very large, return it <strong>modulo </strong><code>10<sup>9 </sup>+ 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, m = 2, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>There are 4 good arrays. They are <code>[1, 1, 2]</code>, <code>[1, 2, 2]</code>, <code>[2, 1, 1]</code> and <code>[2, 2, 1]</code>.</li>
	<li>Hence, the answer is 4.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, m = 2, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The good arrays are <code>[1, 1, 1, 2]</code>, <code>[1, 1, 2, 2]</code>, <code>[1, 2, 2, 2]</code>, <code>[2, 1, 1, 1]</code>, <code>[2, 2, 1, 1]</code> and <code>[2, 2, 2, 1]</code>.</li>
	<li>Hence, the answer is 6.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, m = 2, k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The good arrays are <code>[1, 2, 1, 2, 1]</code> and <code>[2, 1, 2, 1, 2]</code>. Hence, the answer is 2.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= n - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Combinatorics + Fast Power

For an array of length $n$, there are $n - 1$ pairs of adjacent elements. We need to select $k$ of these $n - 1$ adjacent pairs such that the two elements in each of these $k$ pairs are equal, and the remaining $n - 1 - k$ adjacent pairs have different elements.

This is equivalent to splitting the array $n - 1 - k$ times, resulting in $n - k$ segments, where all elements in each segment are equal. The number of ways to split is $C_{n - 1}^{n - 1 - k} = C_{n - 1}^{k}$.

For the first segment, we can choose any element from $[1, m]$. For the remaining $n - k - 1$ segments, we just need to ensure that the element in each segment is different from the previous segment, so each of these segments has $m - 1$ choices. In total, there are $m \times (m - 1)^{n - k - 1}$ ways to choose.

Combining the two parts above, we get the answer:

$$
C_{n - 1}^{k} \times m \times (m - 1)^{n - k - 1} \bmod (10^9 + 7)
$$

In the code implementation, we can precompute factorials and inverses, and use fast power to calculate combinations.

Ignoring the preprocessing time and space, the time complexity is $O(\log (n - k))$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
mx = 10**5 + 10
mod = 10**9 + 7
f = [1] + [0] * mx
g = [1] + [0] * mx

for i in range(1, mx):
    f[i] = f[i - 1] * i % mod
    g[i] = pow(f[i], mod - 2, mod)


def comb(m: int, n: int) -> int:
    return f[m] * g[n] * g[m - n] % mod


class Solution:
    def countGoodArrays(self, n: int, m: int, k: int) -> int:
        return comb(n - 1, k) * m * pow(m - 1, n - k - 1, mod) % mod
```

#### Java

```java
class Solution {
    private static final int N = (int) 1e5 + 10;
    private static final int MOD = (int) 1e9 + 7;
    private static final long[] f = new long[N];
    private static final long[] g = new long[N];

    static {
        f[0] = 1;
        g[0] = 1;
        for (int i = 1; i < N; ++i) {
            f[i] = f[i - 1] * i % MOD;
            g[i] = qpow(f[i], MOD - 2);
        }
    }

    public static long qpow(long a, int k) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % MOD;
            }
            k >>= 1;
            a = a * a % MOD;
        }
        return res;
    }

    public static long comb(int m, int n) {
        return (int) f[m] * g[n] % MOD * g[m - n] % MOD;
    }

    public int countGoodArrays(int n, int m, int k) {
        return (int) (comb(n - 1, k) * m % MOD * qpow(m - 1, n - k - 1) % MOD);
    }
}
```

#### C++

```cpp
const int MX = 1e5 + 10;
const int MOD = 1e9 + 7;
long long f[MX];
long long g[MX];

long long qpow(long a, int k) {
    long res = 1;
    while (k != 0) {
        if ((k & 1) == 1) {
            res = res * a % MOD;
        }
        k >>= 1;
        a = a * a % MOD;
    }
    return res;
}

int init = []() {
    f[0] = g[0] = 1;
    for (int i = 1; i < MX; ++i) {
        f[i] = f[i - 1] * i % MOD;
        g[i] = qpow(f[i], MOD - 2);
    }
    return 0;
}();

long long comb(int m, int n) {
    return f[m] * g[n] % MOD * g[m - n] % MOD;
}

class Solution {
public:
    int countGoodArrays(int n, int m, int k) {
        return comb(n - 1, k) * m % MOD * qpow(m - 1, n - k - 1) % MOD;
    }
};
```

#### Go

```go
const MX = 1e5 + 10
const MOD = 1e9 + 7

var f [MX]int64
var g [MX]int64

func qpow(a int64, k int) int64 {
	res := int64(1)
	for k != 0 {
		if k&1 == 1 {
			res = res * a % MOD
		}
		a = a * a % MOD
		k >>= 1
	}
	return res
}

func init() {
	f[0], g[0] = 1, 1
	for i := 1; i < MX; i++ {
		f[i] = f[i-1] * int64(i) % MOD
		g[i] = qpow(f[i], MOD-2)
	}
}

func comb(m, n int) int64 {
	return f[m] * g[n] % MOD * g[m-n] % MOD
}

func countGoodArrays(n int, m int, k int) int {
	ans := comb(n-1, k) * int64(m) % MOD * qpow(int64(m-1), n-k-1) % MOD
	return int(ans)
}
```

#### TypeScript

```ts
const MX = 1e5 + 10;
const MOD = BigInt(1e9 + 7);

const f: bigint[] = Array(MX).fill(1n);
const g: bigint[] = Array(MX).fill(1n);

function qpow(a: bigint, k: number): bigint {
    let res = 1n;
    while (k !== 0) {
        if ((k & 1) === 1) {
            res = (res * a) % MOD;
        }
        a = (a * a) % MOD;
        k >>= 1;
    }
    return res;
}

(function init() {
    for (let i = 1; i < MX; ++i) {
        f[i] = (f[i - 1] * BigInt(i)) % MOD;
        g[i] = qpow(f[i], Number(MOD - 2n));
    }
})();

function comb(m: number, n: number): bigint {
    return (((f[m] * g[n]) % MOD) * g[m - n]) % MOD;
}

export function countGoodArrays(n: number, m: number, k: number): number {
    const ans = (((comb(n - 1, k) * BigInt(m)) % MOD) * qpow(BigInt(m - 1), n - k - 1)) % MOD;
    return Number(ans);
}
```

#### Rust

```rust
impl Solution {
    pub fn count_good_arrays(n: i32, m: i32, k: i32) -> i32 {
        const N: usize = 1e5 as usize + 10;
        const MOD: i64 = 1_000_000_007;
        use std::sync::OnceLock;

        static F: OnceLock<Vec<i64>> = OnceLock::new();
        static G: OnceLock<Vec<i64>> = OnceLock::new();

        fn qpow(mut a: i64, mut k: i64, m: i64) -> i64 {
            let mut res = 1;
            while k != 0 {
                if k & 1 == 1 {
                    res = res * a % m;
                }
                a = a * a % m;
                k >>= 1;
            }
            res
        }

        fn init() -> (&'static Vec<i64>, &'static Vec<i64>) {
            F.get_or_init(|| {
                let mut f = vec![1i64; N];
                for i in 1..N {
                    f[i] = f[i - 1] * i as i64 % MOD;
                }
                f
            });

            G.get_or_init(|| {
                let f = F.get().unwrap();
                let mut g = vec![1i64; N];
                for i in 1..N {
                    g[i] = qpow(f[i], MOD - 2, MOD);
                }
                g
            });

            (F.get().unwrap(), G.get().unwrap())
        }

        fn comb(f: &[i64], g: &[i64], m: usize, n: usize) -> i64 {
            f[m] * g[n] % MOD * g[m - n] % MOD
        }

        let (f, g) = init();
        let n = n as usize;
        let m = m as i64;
        let k = k as usize;

        let c = comb(f, g, n - 1, k);
        let pow = qpow(m - 1, (n - 1 - k) as i64, MOD);
        (c * m % MOD * pow % MOD) as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
