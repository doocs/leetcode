---
comments: true
difficulty: Hard
tags:
    - Math
    - String
---

<!-- problem:start -->

# [564. Find the Closest Palindrome](https://leetcode.com/problems/find-the-closest-palindrome)

[中文文档](/solution/0500-0599/0564.Find%20the%20Closest%20Palindrome/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>n</code> representing an integer, return <em>the closest integer (not including itself), which is a palindrome</em>. If there is a tie, return <em><strong>the smaller one</strong></em>.</p>

<p>The closest is defined as the absolute difference minimized between two integers.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = &quot;123&quot;
<strong>Output:</strong> &quot;121&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = &quot;1&quot;
<strong>Output:</strong> &quot;0&quot;
<strong>Explanation:</strong> 0 and 2 are the closest palindromes but we return the smallest which is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n.length &lt;= 18</code></li>
	<li><code>n</code> consists of only digits.</li>
	<li><code>n</code> does not have leading zeros.</li>
	<li><code>n</code> is representing an integer in the range <code>[1, 10<sup>18</sup> - 1]</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> Find the closest palindrome, not equal to $n$. Scanning nearby integers has no clear bound.
>
> The nearest palindromes come from mirroring the prefix and that prefix $\pm 1$, plus the length-change sentinels $99\ldots9$ and $100\ldots001$. Mirror each candidate, drop $n$ itself, and pick by distance then by value. Only a constant number of candidates exist.

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
