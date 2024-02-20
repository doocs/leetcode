# [2930. 重新排列后包含指定子字符串的字符串数目](https://leetcode.cn/problems/number-of-strings-which-can-be-rearranged-to-contain-substring)

[English Version](/solution/2900-2999/2930.Number%20of%20Strings%20Which%20Can%20Be%20Rearranged%20to%20Contain%20Substring/README_EN.md)

<!-- tags:数学,动态规划,组合数学 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>&nbsp;。</p>

<p>如果一个字符串&nbsp;<code>s</code>&nbsp;只包含小写英文字母，<strong>且</strong>&nbsp;将 <code>s</code>&nbsp;的字符重新排列后，新字符串包含&nbsp;<strong>子字符串</strong>&nbsp;<code>"leet"</code> ，那么我们称字符串 <code>s</code>&nbsp;是一个 <strong>好</strong>&nbsp;字符串。</p>

<p>比方说：</p>

<ul>
	<li>字符串&nbsp;<code>"lteer"</code>&nbsp;是好字符串，因为重新排列后可以得到&nbsp;<code>"leetr"</code>&nbsp;。</li>
	<li><code>"letl"</code>&nbsp;不是好字符串，因为无法重新排列并得到子字符串&nbsp;<code>"leet"</code>&nbsp;。</li>
</ul>

<p>请你返回长度为 <code>n</code>&nbsp;的好字符串 <strong>总</strong>&nbsp;数目。</p>

<p>由于答案可能很大，将答案对<strong>&nbsp;</strong><code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p><strong>子字符串</strong>&nbsp;是一个字符串中一段连续的字符序列。</p>

<div class="notranslate" style="all: initial;">&nbsp;</div>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>n = 4
<b>输出：</b>12
<b>解释：</b>总共有 12 个字符串重新排列后包含子字符串 "leet" ："eelt" ，"eetl" ，"elet" ，"elte" ，"etel" ，"etle" ，"leet" ，"lete" ，"ltee" ，"teel" ，"tele" 和 "tlee" 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>n = 10
<b>输出：</b>83943898
<b>解释：</b>长度为 10 的字符串重新排列后包含子字符串 "leet" 的方案数为 526083947580 。所以答案为 526083947580 % (10<sup>9</sup> + 7) = 83943898 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：记忆化搜索

我们设计一个函数 $dfs(i, l, e, t)$，表示当前剩余字符串长度为 $i$，且已至少有 $l$ 个字符 `'l'`, $e$ 个字符 `'e'` 和 $t$ 个字符 `'t'`，构成的字符串是一个好字符串的方案数。那么答案为 $dfs(n, 0, 0, 0)$。

函数 $dfs(i, l, e, t)$ 的执行逻辑如下：

如果 $i = 0$，说明当前字符串已经构造完毕，如果 $l = 1$, $e = 2$ 且 $t = 1$，说明当前字符串是一个好字符串，返回 $1$，否则返回 $0$。

否则，我们可以考虑在当前位置添加除 `'l'`, `'e'`, `'t'` 以外的任意一个小写字母，一共有 $23$ 种，那么此时得到的方案数为 $dfs(i - 1, l, e, t) \times 23$。

我们也可以考虑在当前位置添加 `'l'`，此时得到的方案数为 $dfs(i - 1, \min(1, l + 1), e, t)$。同理，添加 `'e'` 和 `'t'` 的方案数分别为 $dfs(i - 1, l, \min(2, e + 1), t)$ 和 $dfs(i - 1, l, e, \min(1, t + 1))$。累加起来，并对 $10^9 + 7$ 取模，即可得到 $dfs(i, l, e, t)$ 的值。

为了避免重复计算，我们可以使用记忆化搜索。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串长度。

<!-- tabs:start -->

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

### 方法二：逆向思维 + 容斥原理

我们可以考虑逆向思维，即计算不包含子字符串 `"leet"` 的字符串数目，然后用总数减去该数目即可。

我们分成以下几种情况：

-   情况 $a$：表示字符串中不包含字符 `'l'` 的方案数，那么有 $a = 25^n$。
-   情况 $b$：与 $a$ 类似，表示字符串中不包含字符 `'t'` 的方案数，那么有 $b = 25^n$。
-   情况 $c$：表示字符串中不包含字符 `'e'` 或者只包含一个字符 `'e'` 的方案数，那么有 $c = 25^n + n \times 25^{n - 1}$。
-   情况 $ab$：表示字符串中不包含字符 `'l'` 和 `'t'` 的方案数，那么有 $ab = 24^n$。
-   情况 $ac$：表示字符串中不包含字符 `'l'` 和 `'e'` 或者只包含一个字符 `'e'` 的方案数，那么有 $ac = 24^n + n \times 24^{n - 1}$。
-   情况 $bc$：与 $ac$ 类似，表示字符串中不包含字符 `'t'` 和 `'e'` 或者只包含一个字符 `'e'` 的方案数，那么有 $bc = 24^n + n \times 24^{n - 1}$。
-   情况 $abc$：表示字符串中不包含字符 `'l'`、`'t'` 和 `'e'` 或者只包含一个字符 `'e'` 的方案数，那么有 $abc = 23^n + n \times 23^{n - 1}$。

那么根据容斥原理，可以得到 $a + b + c - ab - ac - bc + abc$，就是不包含子字符串 `"leet"` 的字符串数目。

而总数 $tot = 26^n$，所以答案为 $tot - (a + b + c - ab - ac - bc + abc)$，注意要对 $10^9 + 7$ 取模。

时间复杂度 $O(\log n)$，其中 $n$ 为字符串长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

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

<!-- end -->
