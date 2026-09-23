---
comments: true
difficulty: 困难
---

<!-- problem:start -->

# [4060. 计算偶好数 🔒](https://leetcode.cn/problems/count-evenly-good-integers)

[English Version](/solution/4000-4099/4060.Count%20Evenly%20Good%20Integers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数 <code>l</code> 和 <code>r</code>。</p>

<p>如果一个整数包含偶数个偶数数字，则称这个整数为&nbsp;<strong>偶好数</strong>。</p>

<p>返回闭区间 <code>[l, r]</code> 中偶好数的数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">l = 18, r = 22</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>区间 <code>[18, 22]</code> 中的偶好数为：</p>

<ul>
	<li>19，因为它包含 0 个偶数数字。</li>
	<li>20，因为它包含 2 个偶数数字。</li>
	<li>22，因为它包含 2 个偶数数字。</li>
</ul>

<p>因此，答案为 3。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">l = 98, r = 101</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>区间 <code>[98, 101]</code> 中的偶好数为：</p>

<ul>
	<li>99，因为它包含 0 个偶数数字。</li>
	<li>100，因为它包含 2 个偶数数字。</li>
</ul>

<p>因此，答案为 2。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">l = 1, r = 10</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p>区间 <code>[1, 10]</code> 中的偶好数为 1、3、5、7 和 9，因为它们都包含 0 个偶数数字。因此，答案为 5。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= l &lt;= r &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数位 DP

<!-- thinking:start -->

> **思考**
>
> $r$ 可以到 $10^{15}$，逐个检查 $[l, r]$ 中每个整数含有多少个偶数数字，无法在时限内完成。把区间计数写成 $F(r)-F(l-1)$ 之后，剩下的是对单个上界计数。
>
> 偶好数只由偶数数字个数的奇偶性决定，可以从高位到低位填每一位。
>
> $x$ 有 $d$ 位时，位数更少的整数会带上前导零，而这些零本身是偶数数字。区间 $[0, 10^{d-1}-1]$ 在计入前导零和按真实写法两种统计下个数相同，已经写满 $d$ 位的整数则没有前导零，所以两种统计在 $[0, x]$ 上给出同一个数。
>
> 搜索状态于是取三个量：当前位置、偶数数字个数模 $2$、是否贴着上界。每一位在 $0$ 到当前上界之间枚举，偶数数字翻转奇偶性，填完且奇偶性为偶时记入答案。

<!-- thinking:end -->

我们用 $F(x)$ 表示区间 $[0, x]$ 中偶好数的个数，答案是 $F(r)-F(l-1)$。$0$ 的十进制是单个数字 $0$，偶数数字的个数为奇数，因此 $F(0)=0$。

把 $x$ 写成十进制字符串 $s$，从高位往低位做记忆化搜索。$dfs(pos, st, lim)$ 表示正在填第 $pos$ 位，已经放入的偶数数字个数模 $2$ 等于 $st$，并且 $lim$ 标记当前前缀是否贴着 $x$。

搜索走到末尾时，若 $st=0$ 则返回 $1$，否则返回 $0$。当前数位的上界 $up$ 在贴着上界时等于 $s[pos]$，否则等于 $9$。数字 $i$ 从 $0$ 枚举到 $up$：它是偶数时，下一位的奇偶性取 $(st+1)\bmod 2$，它是奇数时奇偶性仍为 $st$。只有 $lim$ 为真且 $i=up$ 时，下一位继续贴着上界。

$0$ 到 $9$ 中偶数数字、奇数数字各五个，自由数位上两种奇偶性的方案数相同。$x$ 有 $d$ 位且 $d\ge 2$ 时，位数更少的整数都会带着前导零出现在这次搜索里，它们正好覆盖 $[0, 10^{d-1}-1]$。补足到 $d$ 位以后最高位是偶数数字 $0$，其余位自由，偶数数字个数为偶数的字符串有 $10^{d-1}/2$ 个。按真实十进制写法，一位偶好数有 $5$ 个，位数 $k\ge 2$ 的整数中偶好数恰好占一半。从 $1$ 位累加到 $d-1$ 位，得到

$$
5+\sum_{k=2}^{d-1}\frac{9}{2}\times 10^{k-1}=\frac{10^{d-1}}{2}.
$$

已经写满 $d$ 位且不超过 $x$ 的整数没有前导零，两种写法完全一致。因此这次搜索数出的就是 $F(x)$。

时间复杂度 $O(\log r)$，空间复杂度 $O(\log r)$。

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
