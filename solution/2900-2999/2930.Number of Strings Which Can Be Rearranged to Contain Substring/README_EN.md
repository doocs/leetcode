---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2930.Number%20of%20Strings%20Which%20Can%20Be%20Rearranged%20to%20Contain%20Substring/README_EN.md
rating: 2227
source: Biweekly Contest 117 Q3
tags:
    - Math
    - Dynamic Programming
    - Combinatorics
---

<!-- problem:start -->

# [2930. Number of Strings Which Can Be Rearranged to Contain Substring](https://leetcode.com/problems/number-of-strings-which-can-be-rearranged-to-contain-substring)

[中文文档](/solution/2900-2999/2930.Number%20of%20Strings%20Which%20Can%20Be%20Rearranged%20to%20Contain%20Substring/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>.</p>

<p>A string <code>s</code> is called <strong>good </strong>if it contains only lowercase English characters <strong>and</strong> it is possible to rearrange the characters of <code>s</code> such that the new string contains <code>&quot;leet&quot;</code> as a <strong>substring</strong>.</p>

<p>For example:</p>

<ul>
	<li>The string <code>&quot;lteer&quot;</code> is good because we can rearrange it to form <code>&quot;leetr&quot;</code> .</li>
	<li><code>&quot;letl&quot;</code> is not good because we cannot rearrange it to contain <code>&quot;leet&quot;</code> as a substring.</li>
</ul>

<p>Return <em>the <strong>total</strong> number of good strings of length </em><code>n</code>.</p>

<p>Since the answer may be large, return it <strong>modulo </strong><code>10<sup>9</sup> + 7</code>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within a string.</p>

<div class="notranslate" style="all: initial;">&nbsp;</div>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> 12
<strong>Explanation:</strong> The 12 strings which can be rearranged to have &quot;leet&quot; as a substring are: &quot;eelt&quot;, &quot;eetl&quot;, &quot;elet&quot;, &quot;elte&quot;, &quot;etel&quot;, &quot;etle&quot;, &quot;leet&quot;, &quot;lete&quot;, &quot;ltee&quot;, &quot;teel&quot;, &quot;tele&quot;, and &quot;tlee&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> 83943898
<strong>Explanation:</strong> The number of strings with length 10 which can be rearranged to have &quot;leet&quot; as a substring is 526083947580. Hence the answer is 526083947580 % (10<sup>9</sup> + 7) = 83943898.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memorization Search

We design a function $dfs(i, l, e, t)$, which represents the number of good strings that can be formed when the remaining string length is $i$, and there are at least $l$ characters 'l', $e$ characters 'e' and $t$ characters 't'. The answer is $dfs(n, 0, 0, 0)$.

The execution logic of the function $dfs(i, l, e, t)$ is as follows:

If $i = 0$, it means that the current string has been constructed. If $l = 1$, $e = 2$ and $t = 1$, it means that the current string is a good string, return $1$, otherwise return $0$.

Otherwise, we can consider adding any lowercase letter other than 'l', 'e', 't' at the current position, there are 23 in total, so the number of schemes obtained at this time is $dfs(i - 1, l, e, t) \times 23$.

We can also consider adding 'l' at the current position, and the number of schemes obtained at this time is $dfs(i - 1, \min(1, l + 1), e, t)$. Similarly, the number of schemes for adding 'e' and 't' are $dfs(i - 1, l, \min(2, e + 1), t)$ and $dfs(i - 1, l, e, \min(1, t + 1))$ respectively. Add them up and take the modulus of $10^9 + 7$ to get the value of $dfs(i, l, e, t)$.

To avoid repeated calculations, we can use memorization search.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def stringCount(self, n: int) -> int:
        @cache
        def dfs(i: int, l: int, e: int, t: int) -> int:
            if i == 0:
                return int(l == 1 and e == 2 and t == 1)
            a = dfs(i - 1, l, e, t) * 23 % mod
            b = dfs(i - 1, min(1, l + 1), e, t)
            c = dfs(i - 1, l, min(2, e + 1), t)
            d = dfs(i - 1, l, e, min(1, t + 1))
            return (a + b + c + d) % mod

        mod = 10**9 + 7
        return dfs(n, 0, 0, 0)
```

#### Java

```java
class Solution {
    private final int mod = (int) 1e9 + 7;
    private Long[][][][] f;

    public int stringCount(int n) {
        f = new Long[n + 1][2][3][2];
        return (int) dfs(n, 0, 0, 0);
    }

    private long dfs(int i, int l, int e, int t) {
        if (i == 0) {
            return l == 1 && e == 2 && t == 1 ? 1 : 0;
        }
        if (f[i][l][e][t] != null) {
            return f[i][l][e][t];
        }
        long a = dfs(i - 1, l, e, t) * 23 % mod;
        long b = dfs(i - 1, Math.min(1, l + 1), e, t);
        long c = dfs(i - 1, l, Math.min(2, e + 1), t);
        long d = dfs(i - 1, l, e, Math.min(1, t + 1));
        return f[i][l][e][t] = (a + b + c + d) % mod;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int stringCount(int n) {
        const int mod = 1e9 + 7;
        using ll = long long;
        ll f[n + 1][2][3][2];
        memset(f, -1, sizeof(f));
        function<ll(int, int, int, int)> dfs = [&](int i, int l, int e, int t) -> ll {
            if (i == 0) {
                return l == 1 && e == 2 && t == 1 ? 1 : 0;
            }
            if (f[i][l][e][t] != -1) {
                return f[i][l][e][t];
            }
            ll a = dfs(i - 1, l, e, t) * 23 % mod;
            ll b = dfs(i - 1, min(1, l + 1), e, t) % mod;
            ll c = dfs(i - 1, l, min(2, e + 1), t) % mod;
            ll d = dfs(i - 1, l, e, min(1, t + 1)) % mod;
            return f[i][l][e][t] = (a + b + c + d) % mod;
        };
        return dfs(n, 0, 0, 0);
    }
};
```

#### Go

```go
func stringCount(n int) int {
	const mod int = 1e9 + 7
	f := make([][2][3][2]int, n+1)
	for i := range f {
		for j := range f[i] {
			for k := range f[i][j] {
				for l := range f[i][j][k] {
					f[i][j][k][l] = -1
				}
			}
		}
	}
	var dfs func(i, l, e, t int) int
	dfs = func(i, l, e, t int) int {
		if i == 0 {
			if l == 1 && e == 2 && t == 1 {
				return 1
			}
			return 0
		}
		if f[i][l][e][t] == -1 {
			a := dfs(i-1, l, e, t) * 23 % mod
			b := dfs(i-1, min(1, l+1), e, t)
			c := dfs(i-1, l, min(2, e+1), t)
			d := dfs(i-1, l, e, min(1, t+1))
			f[i][l][e][t] = (a + b + c + d) % mod
		}
		return f[i][l][e][t]
	}
	return dfs(n, 0, 0, 0)
}
```

#### TypeScript

```ts
function stringCount(n: number): number {
    const mod = 10 ** 9 + 7;
    const f: number[][][][] = Array.from({ length: n + 1 }, () =>
        Array.from({ length: 2 }, () =>
            Array.from({ length: 3 }, () => Array.from({ length: 2 }, () => -1)),
        ),
    );
    const dfs = (i: number, l: number, e: number, t: number): number => {
        if (i === 0) {
            return l === 1 && e === 2 && t === 1 ? 1 : 0;
        }
        if (f[i][l][e][t] !== -1) {
            return f[i][l][e][t];
        }
        const a = (dfs(i - 1, l, e, t) * 23) % mod;
        const b = dfs(i - 1, Math.min(1, l + 1), e, t);
        const c = dfs(i - 1, l, Math.min(2, e + 1), t);
        const d = dfs(i - 1, l, e, Math.min(1, t + 1));
        return (f[i][l][e][t] = (a + b + c + d) % mod);
    };
    return dfs(n, 0, 0, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Reverse Thinking + Inclusion-Exclusion Principle

We can consider reverse thinking, that is, calculate the number of strings that do not contain the substring "leet", and then subtract this number from the total.

We divide it into the following cases:

- Case $a$: represents the number of schemes where the string does not contain the character 'l', so we have $a = 25^n$.
- Case $b$: similar to $a$, represents the number of schemes where the string does not contain the character 't', so we have $b = 25^n$.
- Case $c$: represents the number of schemes where the string does not contain the character 'e' or only contains one character 'e', so we have $c = 25^n + n \times 25^{n - 1}$.
- Case $ab$: represents the number of schemes where the string does not contain the characters 'l' and 't', so we have $ab = 24^n$.
- Case $ac$: represents the number of schemes where the string does not contain the characters 'l' and 'e' or only contains one character 'e', so we have $ac = 24^n + n \times 24^{n - 1}$.
- Case $bc$: similar to $ac$, represents the number of schemes where the string does not contain the characters 't' and 'e' or only contains one character 'e', so we have $bc = 24^n + n \times 24^{n - 1}$.
- Case $abc$: represents the number of schemes where the string does not contain the characters 'l', 't' and 'e' or only contains one character 'e', so we have $abc = 23^n + n \times 23^{n - 1}$.

Then according to the inclusion-exclusion principle, $a + b + c - ab - ac - bc + abc$ is the number of strings that do not contain the substring "leet".

The total number $tot = 26^n$, so the answer is $tot - (a + b + c - ab - ac - bc + abc)$, remember to take the modulus of $10^9 + 7$.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$. Here, $n$ is the length of the string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def stringCount(self, n: int) -> int:
        mod = 10**9 + 7
        a = b = pow(25, n, mod)
        c = pow(25, n, mod) + n * pow(25, n - 1, mod)
        ab = pow(24, n, mod)
        ac = bc = (pow(24, n, mod) + n * pow(24, n - 1, mod)) % mod
        abc = (pow(23, n, mod) + n * pow(23, n - 1, mod)) % mod
        tot = pow(26, n, mod)
        return (tot - (a + b + c - ab - ac - bc + abc)) % mod
```

#### Java

```java
class Solution {
    private final int mod = (int) 1e9 + 7;

    public int stringCount(int n) {
        long a = qpow(25, n);
        long b = a;
        long c = (qpow(25, n) + n * qpow(25, n - 1) % mod) % mod;
        long ab = qpow(24, n);
        long ac = (qpow(24, n) + n * qpow(24, n - 1) % mod) % mod;
        long bc = ac;
        long abc = (qpow(23, n) + n * qpow(23, n - 1) % mod) % mod;
        long tot = qpow(26, n);
        return (int) ((tot - (a + b + c - ab - ac - bc + abc)) % mod + mod) % mod;
    }

    private long qpow(long a, int n) {
        long ans = 1;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ans = ans * a % mod;
            }
            a = a * a % mod;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int stringCount(int n) {
        const int mod = 1e9 + 7;
        using ll = long long;
        auto qpow = [&](ll a, int n) {
            ll ans = 1;
            for (; n; n >>= 1) {
                if (n & 1) {
                    ans = ans * a % mod;
                }
                a = a * a % mod;
            }
            return ans;
        };
        ll a = qpow(25, n);
        ll b = a;
        ll c = (qpow(25, n) + n * qpow(25, n - 1) % mod) % mod;
        ll ab = qpow(24, n);
        ll ac = (qpow(24, n) + n * qpow(24, n - 1) % mod) % mod;
        ll bc = ac;
        ll abc = (qpow(23, n) + n * qpow(23, n - 1) % mod) % mod;
        ll tot = qpow(26, n);
        return ((tot - (a + b + c - ab - ac - bc + abc)) % mod + mod) % mod;
    }
};
```

#### Go

```go
func stringCount(n int) int {
	const mod int = 1e9 + 7
	qpow := func(a, n int) int {
		ans := 1
		for ; n > 0; n >>= 1 {
			if n&1 == 1 {
				ans = ans * a % mod
			}
			a = a * a % mod
		}
		return ans
	}
	a := qpow(25, n)
	b := a
	c := qpow(25, n) + n*qpow(25, n-1)
	ab := qpow(24, n)
	ac := (qpow(24, n) + n*qpow(24, n-1)) % mod
	bc := ac
	abc := (qpow(23, n) + n*qpow(23, n-1)) % mod
	tot := qpow(26, n)
	return ((tot-(a+b+c-ab-ac-bc+abc))%mod + mod) % mod
}
```

#### TypeScript

```ts
function stringCount(n: number): number {
    const mod = BigInt(10 ** 9 + 7);
    const qpow = (a: bigint, n: number): bigint => {
        let ans = 1n;
        for (; n; n >>>= 1) {
            if (n & 1) {
                ans = (ans * a) % mod;
            }
            a = (a * a) % mod;
        }
        return ans;
    };
    const a = qpow(25n, n);
    const b = a;
    const c = (qpow(25n, n) + ((BigInt(n) * qpow(25n, n - 1)) % mod)) % mod;
    const ab = qpow(24n, n);
    const ac = (qpow(24n, n) + ((BigInt(n) * qpow(24n, n - 1)) % mod)) % mod;
    const bc = ac;
    const abc = (qpow(23n, n) + ((BigInt(n) * qpow(23n, n - 1)) % mod)) % mod;
    const tot = qpow(26n, n);
    return Number((((tot - (a + b + c - ab - ac - bc + abc)) % mod) + mod) % mod);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
