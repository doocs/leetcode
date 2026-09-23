---
comments: true
difficulty: Hard
---

<!-- problem:start -->

# [4060. Count Evenly Good Integers 🔒](https://leetcode.com/problems/count-evenly-good-integers)

[中文文档](/solution/4000-4099/4060.Count%20Evenly%20Good%20Integers/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>l</code> and <code>r</code>.</p>

<p>An integer is called <strong>evenly good</strong> if it contains an even number of even digits.</p>

<p>Return the number of evenly good integers in the inclusive range <code>[l, r]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">l = 18, r = 22</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The evenly good integers in the range <code>[18, 22]</code> are:</p>

<ul>
	<li>19, because it contains 0 even digits.</li>
	<li>20, because it contains 2 even digits.</li>
	<li>22, because it contains 2 even digits.</li>
</ul>

<p>Thus, the answer is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">l = 98, r = 101</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The evenly good integers in the range <code>[98, 101]</code> are:</p>

<ul>
	<li>99, because it contains 0 even digits.</li>
	<li>100, because it contains 2 even digits.</li>
</ul>

<p>Thus, the answer is 2.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">l = 1, r = 10</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>The evenly good integers in the range <code>[1, 10]</code> are 1, 3, 5, 7, and 9, because each of them contains 0 even digits. Thus, the answer is 5.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= l &lt;= r &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Digit DP

<!-- thinking:start -->

> **Thinking**
>
> $r$ can be as large as $10^{15}$, so checking every integer in $[l, r]$ does not finish in time. The range count equals $F(r)-F(l-1)$, so it is enough to count integers up to one upper bound.
>
> An integer is evenly good exactly when its number of even digits is even, so the digits can be filled from high to low.
>
> When $x$ has $d$ digits, every shorter integer shows up with leading zeros, and those zeros are even digits. On $[0, 10^{d-1}-1]$ the count that includes those zeros equals the count from the ordinary decimal form, and a full $d$-digit integer has no leading zero, so the two counts agree on $[0, x]$.
>
> The search state is then the position, the even-digit count modulo $2$, and whether the prefix is tight. Each digit is chosen from $0$ through the current limit, an even digit flips the parity, and a finished number is counted when the parity is even.

<!-- thinking:end -->

Let $F(x)$ be the number of evenly good integers in $[0, x]$. The answer is $F(r)-F(l-1)$. The decimal form of $0$ is the single digit $0$, which has an odd number of even digits, so $F(0)=0$.

Write $x$ as a decimal string $s$ and search from high digits to low digits with memoization. $dfs(pos, st, lim)$ is the number of ways to fill position $pos$ when the even digits placed so far number $st$ modulo $2$, and $lim$ says whether the prefix is tight against $x$.

At the end of the string, return $1$ when $st=0$ and return $0$ otherwise. The upper digit $up$ equals $s[pos]$ when the prefix is tight, and equals $9$ otherwise. For a digit $i$ from $0$ to $up$, the next parity is $(st+1)\bmod 2$ when $i$ is even and stays $st$ when $i$ is odd. The next position stays tight only when $lim$ is true and $i=up$.

Among $0$ through $9$, five digits are even and five are odd, so a free run of digits splits evenly between the two parities. When $x$ has $d\ge 2$ digits, every integer with fewer digits appears in this search with leading zeros, and those integers are exactly $[0, 10^{d-1}-1]$. After padding to $d$ digits the leading digit is the even digit $0$ and the rest are free, which gives $10^{d-1}/2$ strings with an even number of even digits. In the ordinary decimal form there are $5$ one-digit evenly good integers, and a length $k\ge 2$ contributes exactly half of its integers. Summing lengths $1$ through $d-1$ gives

$$
5+\sum_{k=2}^{d-1}\frac{9}{2}\times 10^{k-1}=\frac{10^{d-1}}{2}.
$$

An integer that already has $d$ digits and does not exceed $x$ has no leading zero, so the two writings match. The search therefore returns $F(x)$.

The time complexity is $O(\log r)$ and the space complexity is $O(\log r)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countEvenlyGoodIntegers(self, l: int, r: int) -> int:
        @cache
        def dfs(pos: int, st: int, lim: bool) -> int:
            if pos >= len(s):
                return st ^ 1
            up = int(s[pos]) if lim else 9
            return sum(
                dfs(pos + 1, (st + (i & 1 ^ 1)) % 2, lim and i == up)
                for i in range(up + 1)
            )

        s = str(l - 1)
        a = dfs(0, 0, True)
        dfs.cache_clear()
        s = str(r)
        b = dfs(0, 0, True)
        return b - a
```

#### Java

```java
class Solution {
    String s;
    long[][][] f;

    public long countEvenlyGoodIntegers(long l, long r) {
        return calc(r) - calc(l - 1);
    }

    private long calc(long x) {
        s = String.valueOf(x);
        f = new long[s.length()][2][2];
        for (long[][] a : f) {
            for (long[] b : a) {
                Arrays.fill(b, -1);
            }
        }
        return dfs(0, 0, true);
    }

    private long dfs(int pos, int st, boolean lim) {
        if (pos >= s.length()) {
            return st ^ 1;
        }
        int k = lim ? 1 : 0;
        if (f[pos][st][k] != -1) {
            return f[pos][st][k];
        }
        int up = lim ? s.charAt(pos) - '0' : 9;
        long res = 0;
        for (int i = 0; i <= up; ++i) {
            res += dfs(pos + 1, (st + (i & 1 ^ 1)) % 2, lim && i == up);
        }
        return f[pos][st][k] = res;
    }
}
```

#### C++

```cpp
class Solution {
    string s;
    long long f[20][2][2];

    long long dfs(int pos, int st, bool lim) {
        if (pos >= s.size()) {
            return st ^ 1;
        }
        if (f[pos][st][lim] != -1) {
            return f[pos][st][lim];
        }
        int up = lim ? s[pos] - '0' : 9;
        long long res = 0;
        for (int i = 0; i <= up; ++i) {
            res += dfs(pos + 1, (st + (i & 1 ^ 1)) % 2, lim && i == up);
        }
        return f[pos][st][lim] = res;
    }

    long long calc(long long x) {
        s = to_string(x);
        memset(f, -1, sizeof(f));
        return dfs(0, 0, true);
    }

public:
    long long countEvenlyGoodIntegers(long long l, long long r) {
        return calc(r) - calc(l - 1);
    }
};
```

#### Go

```go
func countEvenlyGoodIntegers(l int64, r int64) int64 {
	var s string
	var f [20][2][2]int64

	calc := func(x int64) int64 {
		s = strconv.FormatInt(x, 10)
		for i := range f {
			for j := range f[i] {
				for k := range f[i][j] {
					f[i][j][k] = -1
				}
			}
		}

		var dfs func(pos, st int, lim bool) int64
		dfs = func(pos, st int, lim bool) int64 {
			if pos >= len(s) {
				return int64(st ^ 1)
			}
			k := 0
			if lim {
				k = 1
			}
			if f[pos][st][k] != -1 {
				return f[pos][st][k]
			}
			up := 9
			if lim {
				up = int(s[pos] - '0')
			}
			var res int64
			for i := 0; i <= up; i++ {
				res += dfs(pos+1, (st+(i&1^1))%2, lim && i == up)
			}
			f[pos][st][k] = res
			return res
		}

		return dfs(0, 0, true)
	}

	return calc(r) - calc(l-1)
}
```

#### TypeScript

```ts
function countEvenlyGoodIntegers(l: number, r: number): number {
    let s: string;
    let f: number[][][];

    const calc = (x: number): number => {
        s = String(x);
        f = Array.from({ length: s.length }, () =>
            Array.from({ length: 2 }, () => Array(2).fill(-1)),
        );

        const dfs = (pos: number, st: number, lim: boolean): number => {
            if (pos >= s.length) {
                return st ^ 1;
            }
            const k = lim ? 1 : 0;
            if (f[pos][st][k] !== -1) {
                return f[pos][st][k];
            }
            const up = lim ? Number(s[pos]) : 9;
            let res = 0;
            for (let i = 0; i <= up; ++i) {
                res += dfs(pos + 1, (st + ((i & 1) ^ 1)) % 2, lim && i === up);
            }
            return (f[pos][st][k] = res);
        };

        return dfs(0, 0, true);
    };

    return calc(r) - calc(l - 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
