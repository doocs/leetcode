---
comments: true
difficulty: 困难
tags:
    - 数学
    - 字符串
---

<!-- problem:start -->

# [564. 寻找最近的回文数](https://leetcode.cn/problems/find-the-closest-palindrome)

[English Version](/solution/0500-0599/0564.Find%20the%20Closest%20Palindrome/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个表示整数的字符串&nbsp;<code>n</code> ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。</p>

<p>“最近的”定义为两个整数<strong>差的绝对值</strong>最小。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> n = "123"
<strong>输出:</strong> "121"
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> n = "1"
<strong>输出:</strong> "0"
<strong>解释:</strong> 0 和 2是最近的回文，但我们返回最小的，也就是 0。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n.length &lt;= 18</code></li>
	<li><code>n</code>&nbsp;只由数字组成</li>
	<li><code>n</code>&nbsp;不含前导 0</li>
	<li><code>n</code>&nbsp;代表在&nbsp;<code>[1, 10<sup>18</sup>&nbsp;- 1]</code> 范围内的整数</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 要找与 $n$ 数值最接近且不等于 $n$ 的回文数。在附近枚举整数再判断回文，范围漫无边界。
>
> 最近回文只可能来自「镜像前半」及其 $\pm 1$，再加上位数变化的 $99\ldots9$ 与 $100\ldots001$。对前半加减一后按长度镜像，丢掉 $n$ 本身，再按距离、再按数值选取。候选只有常数个。

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def nearestPalindromic(self, n: str) -> str:
        x = int(n)
        l = len(n)
        res = {10 ** (l - 1) - 1, 10**l + 1}
        left = int(n[: (l + 1) >> 1])
        for i in range(left - 1, left + 2):
            j = i if l % 2 == 0 else i // 10
            while j:
                i = i * 10 + j % 10
                j //= 10
            res.add(i)
        res.discard(x)

        ans = -1
        for t in res:
            if (
                ans == -1
                or abs(t - x) < abs(ans - x)
                or (abs(t - x) == abs(ans - x) and t < ans)
            ):
                ans = t
        return str(ans)
```

#### Java

```java
import java.math.BigInteger;

class Solution {
    public String nearestPalindromic(String n) {
        BigInteger x = new BigInteger(n);
        int l = n.length();
        Set<BigInteger> res = new HashSet<>();
        res.add(BigInteger.TEN.pow(l - 1).subtract(BigInteger.ONE));
        res.add(BigInteger.TEN.pow(l).add(BigInteger.ONE));
        BigInteger left = new BigInteger(n.substring(0, (l + 1) / 2));
        for (int d = -1; d <= 1; ++d) {
            BigInteger i = left.add(BigInteger.valueOf(d));
            BigInteger j = l % 2 == 0 ? i : i.divide(BigInteger.TEN);
            while (j.signum() > 0) {
                i = i.multiply(BigInteger.TEN).add(j.mod(BigInteger.TEN));
                j = j.divide(BigInteger.TEN);
            }
            res.add(i);
        }
        res.remove(x);
        BigInteger ans = null;
        for (BigInteger t : res) {
            BigInteger dist = t.subtract(x).abs();
            if (ans == null || dist.compareTo(ans.subtract(x).abs()) < 0
                || (dist.compareTo(ans.subtract(x).abs()) == 0 && t.compareTo(ans) < 0)) {
                ans = t;
            }
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
using i128 = __int128_t;

class Solution {
public:
    string nearestPalindromic(string n) {
        i128 x = parse(n);
        int l = n.size();
        set<i128> res;
        res.insert(pow10(l - 1) - 1);
        res.insert(pow10(l) + 1);
        i128 left = parse(n.substr(0, (l + 1) / 2));
        for (int d = -1; d <= 1; ++d) {
            i128 i = left + d;
            i128 j = l % 2 == 0 ? i : i / 10;
            while (j) {
                i = i * 10 + j % 10;
                j /= 10;
            }
            res.insert(i);
        }
        res.erase(x);
        i128 ans = -1;
        for (i128 t : res) {
            i128 dt = iabs(t - x);
            i128 da = iabs(ans - x);
            if (ans == -1 || dt < da || (dt == da && t < ans)) {
                ans = t;
            }
        }
        return toStr(ans);
    }

private:
    i128 parse(const string& s) {
        i128 x = 0;
        for (char c : s) {
            x = x * 10 + (c - '0');
        }
        return x;
    }

    i128 pow10(int k) {
        i128 x = 1;
        while (k--) {
            x *= 10;
        }
        return x;
    }

    i128 iabs(i128 x) {
        return x < 0 ? -x : x;
    }

    string toStr(i128 x) {
        if (x == 0) {
            return "0";
        }
        string s;
        while (x) {
            s.push_back(char('0' + x % 10));
            x /= 10;
        }
        reverse(s.begin(), s.end());
        return s;
    }
};
```

#### Go

```go
func nearestPalindromic(n string) string {
	x := new(big.Int)
	x.SetString(n, 10)
	l := len(n)
	ten := big.NewInt(10)
	base := new(big.Int).Exp(ten, big.NewInt(int64(l-1)), nil)
	res := []*big.Int{
		new(big.Int).Sub(new(big.Int).Set(base), big.NewInt(1)),
		new(big.Int).Add(new(big.Int).Mul(new(big.Int).Set(base), ten), big.NewInt(1)),
	}
	left := new(big.Int)
	left.SetString(n[:(l+1)/2], 10)
	for d := int64(-1); d <= 1; d++ {
		i := new(big.Int).Add(left, big.NewInt(d))
		j := new(big.Int).Set(i)
		if l&1 == 1 {
			j.Quo(j, ten)
		}
		for j.Sign() > 0 {
			i.Mul(i, ten)
			i.Add(i, new(big.Int).Mod(j, ten))
			j.Quo(j, ten)
		}
		res = append(res, i)
	}
	var ans *big.Int
	for _, t := range res {
		if t.Cmp(x) == 0 {
			continue
		}
		dist := new(big.Int).Abs(new(big.Int).Sub(t, x))
		if ans == nil {
			ans = new(big.Int).Set(t)
			continue
		}
		best := new(big.Int).Abs(new(big.Int).Sub(ans, x))
		if dist.Cmp(best) < 0 || (dist.Cmp(best) == 0 && t.Cmp(ans) < 0) {
			ans = new(big.Int).Set(t)
		}
	}
	return ans.String()
}
```

#### JavaScript

```js
/**
 * @param {string} n
 * @return {string}
 */

function nearestPalindromic(n) {
    const x = BigInt(n);
    let ans = null;

    for (const t of getCandidates(n)) {
        if (
            ans === null ||
            absDiff(t, x) < absDiff(ans, x) ||
            (absDiff(t, x) === absDiff(ans, x) && t < ans)
        ) {
            ans = t;
        }
    }

    return ans.toString();
}

function getCandidates(n) {
    const length = n.length;
    const res = new Set();

    res.add(10n ** BigInt(length - 1) - 1n);
    res.add(10n ** BigInt(length) + 1n);

    const left = BigInt(n.substring(0, Math.ceil(length / 2)));

    for (let i = left - 1n; i <= left + 1n; i++) {
        const prefix = i.toString();
        const t =
            prefix +
            prefix
                .split('')
                .reverse()
                .slice(length % 2)
                .join('');
        res.add(BigInt(t));
    }

    res.delete(BigInt(n));
    return res;
}

function absDiff(a, b) {
    return a > b ? a - b : b - a;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
