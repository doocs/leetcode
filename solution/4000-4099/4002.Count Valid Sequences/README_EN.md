---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4002.Count%20Valid%20Sequences/README_EN.md
rating: 1912
source: Weekly Contest 512 Q3
tags:
    - Math
    - Combinatorics
---

<!-- problem:start -->

# [4002. Count Valid Sequences](https://leetcode.com/problems/count-valid-sequences)

[中文文档](/solution/4000-4099/4002.Count%20Valid%20Sequences/README.md)

## Description

<!-- description:start -->

<p>You are given two <strong>positive</strong> integers <code>n</code> and <code>k</code>.</p>

<p>A <strong>valid sequence</strong> is a sequence of <code>k</code> positive integers such that:</p>

<ul>
	<li>The <strong>sum</strong> of all integers in the sequence is equal to <code>n</code>.</li>
	<li>The <strong>product</strong> of all integers in the sequence is <strong>even</strong>.</li>
</ul>

<p>Return the number of valid sequences. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup>​​​​​​​ + 7</code>.</p>

<p>Two sequences are considered <strong>different</strong> if they differ at any index. For example, <code>[1, 1, 2]</code> and <code>[1, 2, 1]</code> are considered different sequences.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The sequences of length <code>k = 3</code> whose sum is 5 are:</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Sequence</th>
			<th style="border: 1px solid black;">Product</th>
			<th style="border: 1px solid black;">Parity</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1, 1, 3]</code></td>
			<td style="border: 1px solid black;"><code>1 * 1 * 3 = 3</code></td>
			<td style="border: 1px solid black;">Odd</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1, 2, 2]</code></td>
			<td style="border: 1px solid black;"><code>1 * 2 * 2 = 4</code></td>
			<td style="border: 1px solid black;">Even</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[2, 1, 2]</code></td>
			<td style="border: 1px solid black;"><code>2 * 1 * 2 = 4</code></td>
			<td style="border: 1px solid black;">Even</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[2, 2, 1]</code></td>
			<td style="border: 1px solid black;"><code>2 * 2 * 1 = 4</code></td>
			<td style="border: 1px solid black;">Even</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1, 3, 1]</code></td>
			<td style="border: 1px solid black;"><code>1 * 3 * 1 = 3</code></td>
			<td style="border: 1px solid black;">Odd</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[3, 1, 1]</code></td>
			<td style="border: 1px solid black;"><code>3 * 1 * 1 = 3</code></td>
			<td style="border: 1px solid black;">Odd</td>
		</tr>
	</tbody>
</table>

<p>There are 3 sequences with an even product, thus the answer is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The sequences of length <code>k = 2</code> whose sum is 3 are:</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Sequence</th>
			<th style="border: 1px solid black;">Product</th>
			<th style="border: 1px solid black;">Parity</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1, 2]</code></td>
			<td style="border: 1px solid black;"><code>1 * 2 = 2</code></td>
			<td style="border: 1px solid black;">Even</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[2, 1]</code></td>
			<td style="border: 1px solid black;"><code>2 * 1 = 2</code></td>
			<td style="border: 1px solid black;">Even</td>
		</tr>
	</tbody>
</table>

<p>There are 2 sequences with an even product, thus the answer is 2.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, k = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The only possible sequence of length <code>k = 5</code> whose sum is 5 is <code>[1, 1, 1, 1, 1]</code>, which has an odd product. Thus, the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Combinatorics

The number of ordered ways to write $n$ as a sum of $k$ positive integers is $\binom{n-1}{k-1}$. An even product means "at least one even number"; the complement is "all odd".

Therefore the answer is:

$$
\binom{n-1}{k-1} - \textit{(number of all-odd sequences)}
$$

If every number is odd, write the $i$-th number as $2a_i + 1$ ($a_i \ge 0$). Then:

$$
\sum_{i=1}^{k}(2a_i + 1) = n \implies \sum_{i=1}^{k} a_i = \frac{n-k}{2}
$$

All-odd sequences exist only when $n$ and $k$ have the same parity (i.e., $n + k$ is even), and their count is $\binom{\frac{n+k}{2}-1}{k-1}$; otherwise the count is $0$.

After precomputing factorials and modular inverses, each combination can be evaluated in $O(1)$. Return the answer modulo $10^9+7$.

The time complexity is $O(N + \log M)$ for preprocessing, and the space complexity is $O(N)$, where $N = 5 \times 10^5$ and $M = 10^9+7$. Each query is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
MX = 5 * 10**5 + 1
MOD = 10**9 + 7
f = [1] * MX
g = [1] * MX
for i in range(1, MX):
    f[i] = f[i - 1] * i % MOD
    g[i] = pow(f[i], MOD - 2, MOD)


def comb(n: int, k: int) -> int:
    return f[n] * g[k] * g[n - k] % MOD


class Solution:
    def countValidSequences(self, n: int, k: int) -> int:
        ans = comb(n - 1, k - 1)
        if (n + k) % 2 == 0:
            ans = (ans - comb((n + k) // 2 - 1, k - 1)) % MOD
        return ans
```

#### Java

```java
class Solution {
    static final int MX = 500001;
    static final long MOD = 1000000007L;
    static long[] f = new long[MX];
    static long[] g = new long[MX];

    static {
        f[0] = 1;
        g[0] = 1;
        for (int i = 1; i < MX; i++) {
            f[i] = f[i - 1] * i % MOD;
            g[i] = pow(f[i], MOD - 2);
        }
    }

    static long pow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = res * a % MOD;
            }
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }

    static long comb(int n, int k) {
        return f[n] * g[k] % MOD * g[n - k] % MOD;
    }

    public int countValidSequences(int n, int k) {
        long ans = comb(n - 1, k - 1);
        if ((n + k) % 2 == 0) {
            ans = (ans - comb((n + k) / 2 - 1, k - 1) + MOD) % MOD;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
const int MX = 500001;
const long long MOD = 1000000007LL;

long long f[MX];
long long g[MX];

long long qpow(long long a, long long b) {
    long long res = 1;
    while (b > 0) {
        if (b & 1) {
            res = res * a % MOD;
        }
        a = a * a % MOD;
        b >>= 1;
    }
    return res;
}

int init = []() {
    f[0] = 1;
    g[0] = 1;

    for (int i = 1; i < MX; i++) {
        f[i] = f[i - 1] * i % MOD;
        g[i] = qpow(f[i], MOD - 2);
    }

    return 0;
}();

long long comb(int n, int k) {
    return f[n] * g[k] % MOD * g[n - k] % MOD;
}

class Solution {
public:
    int countValidSequences(int n, int k) {
        long long ans = comb(n - 1, k - 1);

        if ((n + k) % 2 == 0) {
            ans = (ans - comb((n + k) / 2 - 1, k - 1) + MOD) % MOD;
        }

        return (int) ans;
    }
};
```

#### Go

```go
const MX = 500001
const MOD int64 = 1000000007

var f [MX]int64
var g [MX]int64

func init() {
	f[0] = 1
	g[0] = 1

	for i := 1; i < MX; i++ {
		f[i] = f[i-1] * int64(i) % MOD
		g[i] = pow(f[i], MOD-2)
	}
}

func pow(a, b int64) int64 {
	res := int64(1)
	for b > 0 {
		if b&1 == 1 {
			res = res * a % MOD
		}
		a = a * a % MOD
		b >>= 1
	}
	return res
}

func comb(n, k int) int64 {
	return f[n] * g[k] % MOD * g[n-k] % MOD
}

func countValidSequences(n int, k int) int {
	ans := comb(n-1, k-1)

	if (n+k)%2 == 0 {
		ans = (ans - comb((n+k)/2-1, k-1) + MOD) % MOD
	}

	return int(ans)
}
```

#### TypeScript

```ts
const MX = 500001;
const MOD = 1000000007n;

const f: bigint[] = new Array(MX).fill(1n);
const g: bigint[] = new Array(MX).fill(1n);

function pow(a: bigint, b: bigint): bigint {
    let res = 1n;
    while (b > 0n) {
        if (b & 1n) {
            res = (res * a) % MOD;
        }
        a = (a * a) % MOD;
        b >>= 1n;
    }
    return res;
}

for (let i = 1; i < MX; i++) {
    f[i] = (f[i - 1] * BigInt(i)) % MOD;
    g[i] = pow(f[i], MOD - 2n);
}

function comb(n: number, k: number): bigint {
    return (((f[n] * g[k]) % MOD) * g[n - k]) % MOD;
}

function countValidSequences(n: number, k: number): number {
    let ans = comb(n - 1, k - 1);

    if ((n + k) % 2 === 0) {
        ans = (ans - comb((n + k) / 2 - 1, k - 1) + MOD) % MOD;
    }

    return Number(ans);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
