---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1922.Count%20Good%20Numbers/README_EN.md
rating: 1674
source: Weekly Contest 248 Q3
tags:
    - Recursion
    - Math
---

<!-- problem:start -->

# [1922. Count Good Numbers](https://leetcode.com/problems/count-good-numbers)

[中文文档](/solution/1900-1999/1922.Count%20Good%20Numbers/README.md)

## Description

<!-- description:start -->

<p>A digit string is <strong>good</strong> if the digits <strong>(0-indexed)</strong> at <strong>even</strong> indices are <strong>even</strong> and the digits at <strong>odd</strong> indices are <strong>prime</strong> (<code>2</code>, <code>3</code>, <code>5</code>, or <code>7</code>).</p>

<ul>
	<li>For example, <code>&quot;2582&quot;</code> is good because the digits (<code>2</code> and <code>8</code>) at even positions are even and the digits (<code>5</code> and <code>2</code>) at odd positions are prime. However, <code>&quot;3245&quot;</code> is <strong>not</strong> good because <code>3</code> is at an even index but is not even.</li>
</ul>

<p>Given an integer <code>n</code>, return <em>the <strong>total</strong> number of good digit strings of length </em><code>n</code>. Since the answer may be large, <strong>return it modulo </strong><code>10<sup>9</sup> + 7</code>.</p>

<p>A <strong>digit string</strong> is a string consisting of digits <code>0</code> through <code>9</code> that may contain leading zeros.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 5
<strong>Explanation:</strong> The good numbers of length 1 are &quot;0&quot;, &quot;2&quot;, &quot;4&quot;, &quot;6&quot;, &quot;8&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> 400
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 50
<strong>Output:</strong> 564908303
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Fast Exponentiation

For a "good number" of length $n$, the even-indexed positions have $\lceil \frac{n}{2} \rceil = \lfloor \frac{n + 1}{2} \rfloor$ digits, and these positions can be filled with $5$ different digits ($0, 2, 4, 6, 8$). The odd-indexed positions have $\lfloor \frac{n}{2} \rfloor$ digits, and these positions can be filled with $4$ different digits ($2, 3, 5, 7$). Therefore, the total number of "good numbers" of length $n$ is:

$$
ans = 5^{\lceil \frac{n}{2} \rceil} \times 4^{\lfloor \frac{n}{2} \rfloor}
$$

We can use fast exponentiation to compute $5^{\lceil \frac{n}{2} \rceil}$ and $4^{\lfloor \frac{n}{2} \rfloor}$. The time complexity is $O(\log n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countGoodNumbers(self, n: int) -> int:
        mod = 10**9 + 7
        return pow(5, (n + 1) >> 1, mod) * pow(4, n >> 1, mod) % mod
```

#### Java

```java
class Solution {
    private final int mod = (int) 1e9 + 7;

    public int countGoodNumbers(long n) {
        return (int) (qpow(5, (n + 1) >> 1) * qpow(4, n >> 1) % mod);
    }

    private long qpow(long x, long n) {
        long res = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                res = res * x % mod;
            }
            x = x * x % mod;
            n >>= 1;
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countGoodNumbers(long long n) {
        const int mod = 1e9 + 7;
        auto qpow = [](long long x, long long n) -> long long {
            long long res = 1;
            while (n) {
                if ((n & 1) == 1) {
                    res = res * x % mod;
                }
                x = x * x % mod;
                n >>= 1;
            }
            return res;
        };
        return qpow(5, (n + 1) >> 1) * qpow(4, n >> 1) % mod;
    }
};
```

#### Go

```go
const mod int64 = 1e9 + 7

func countGoodNumbers(n int64) int {
	return int(myPow(5, (n+1)>>1) * myPow(4, n>>1) % mod)
}

func myPow(x, n int64) int64 {
	var res int64 = 1
	for n != 0 {
		if (n & 1) == 1 {
			res = res * x % mod
		}
		x = x * x % mod
		n >>= 1
	}
	return res
}
```

#### TypeScript

```ts
function countGoodNumbers(n: number): number {
    const mod = 1000000007n;
    const qpow = (x: bigint, n: bigint): bigint => {
        let res = 1n;
        while (n > 0n) {
            if (n & 1n) {
                res = (res * x) % mod;
            }
            x = (x * x) % mod;
            n >>= 1n;
        }
        return res;
    };
    const a = qpow(5n, BigInt(n + 1) / 2n);
    const b = qpow(4n, BigInt(n) / 2n);
    return Number((a * b) % mod);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
