---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1808.Maximize%20Number%20of%20Nice%20Divisors/README_EN.md
rating: 2070
source: Weekly Contest 234 Q4
tags:
    - Recursion
    - Math
---

<!-- problem:start -->

# [1808. Maximize Number of Nice Divisors](https://leetcode.com/problems/maximize-number-of-nice-divisors)

[中文文档](/solution/1800-1899/1808.Maximize%20Number%20of%20Nice%20Divisors/README.md)

## Description

<!-- description:start -->

<p>You are given a positive integer <code>primeFactors</code>. You are asked to construct a positive integer <code>n</code> that satisfies the following conditions:</p>

<ul>

  <li>The number of prime factors of <code>n</code> (not necessarily distinct) is <strong>at most</strong> <code>primeFactors</code>.</li>

  <li>The number of nice divisors of <code>n</code> is maximized. Note that a divisor of <code>n</code> is <strong>nice</strong> if it is divisible by every prime factor of <code>n</code>. For example, if <code>n = 12</code>, then its prime factors are <code>[2,2,3]</code>, then <code>6</code> and <code>12</code> are nice divisors, while <code>3</code> and <code>4</code> are not.</li>

</ul>

<p>Return <em>the number of nice divisors of</em> <code>n</code>. Since that number can be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>Note that a prime number is a natural number greater than <code>1</code> that is not a product of two smaller natural numbers. The prime factors of a number <code>n</code> is a list of prime numbers such that their product equals <code>n</code>.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> primeFactors = 5

<strong>Output:</strong> 6

<strong>Explanation:</strong> 200 is a valid value of n.

It has 5 prime factors: [2,2,2,5,5], and it has 6 nice divisors: [10,20,40,50,100,200].

There is not other value of n that has at most 5 prime factors and more nice divisors.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> primeFactors = 8

<strong>Output:</strong> 18

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>1 &lt;= primeFactors &lt;= 10<sup>9</sup></code></li>

</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Problem Transformation + Fast Power

We can factorize $n$ into prime factors, i.e., $n = a_1^{k_1} \times a_2^{k_2} \times\cdots \times a_m^{k_m}$, where $a_i$ is a prime factor and $k_i$ is the exponent of the prime factor $a_i$. Since the number of prime factors of $n$ does not exceed `primeFactors`, we have $k_1 + k_2 + \cdots + k_m \leq primeFactors$.

According to the problem description, we know that a good factor of $n$ must be divisible by all prime factors, which means that a good factor of $n$ needs to include $a_1 \times a_2 \times \cdots \times a_m$ as a factor. Then the number of good factors $k= k_1 \times k_2 \times \cdots \times k_m$, i.e., $k$ is the product of $k_1, k_2, \cdots, k_m$. To maximize the number of good factors, we need to split `primeFactors` into $k_1, k_2, \cdots, k_m$ to make $k_1 \times k_2 \times \cdots \times k_m$ the largest. Therefore, the problem is transformed into: split the integer `primeFactors` into the product of several integers to maximize the product.

Next, we just need to discuss different cases.

-   If $primeFactors \lt 4$, then directly return `primeFactors`.
-   If $primeFactors$ is a multiple of $3$, then we split `primeFactors` into multiples of $3$, i.e., $3^{\frac{primeFactors}{3}}$.
-   If $primeFactors$ modulo $3$ equals $1$, then we split `primeFactors` into $\frac{primeFactors}{3} - 1$ multiples of $3$, and then multiply by $4$, i.e., $3^{\frac{primeFactors}{3} - 1} \times 4$.
-   If $primeFactors$ modulo $3$ equals $2$, then we split `primeFactors` into $\frac{primeFactors}{3}$ multiples of $3$, and then multiply by $2$, i.e., $3^{\frac{primeFactors}{3}} \times 2$.

In the above process, we use fast power to calculate the modulus.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxNiceDivisors(self, primeFactors: int) -> int:
        mod = 10**9 + 7
        if primeFactors < 4:
            return primeFactors
        if primeFactors % 3 == 0:
            return pow(3, primeFactors // 3, mod) % mod
        if primeFactors % 3 == 1:
            return 4 * pow(3, primeFactors // 3 - 1, mod) % mod
        return 2 * pow(3, primeFactors // 3, mod) % mod
```

#### Java

```java
class Solution {
    private final int mod = (int) 1e9 + 7;

    public int maxNiceDivisors(int primeFactors) {
        if (primeFactors < 4) {
            return primeFactors;
        }
        if (primeFactors % 3 == 0) {
            return qpow(3, primeFactors / 3);
        }
        if (primeFactors % 3 == 1) {
            return (int) (4L * qpow(3, primeFactors / 3 - 1) % mod);
        }
        return 2 * qpow(3, primeFactors / 3) % mod;
    }

    private int qpow(long a, long n) {
        long ans = 1;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ans = ans * a % mod;
            }
            a = a * a % mod;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxNiceDivisors(int primeFactors) {
        if (primeFactors < 4) {
            return primeFactors;
        }
        const int mod = 1e9 + 7;
        auto qpow = [&](long long a, long long n) {
            long long ans = 1;
            for (; n; n >>= 1) {
                if (n & 1) {
                    ans = ans * a % mod;
                }
                a = a * a % mod;
            }
            return (int) ans;
        };
        if (primeFactors % 3 == 0) {
            return qpow(3, primeFactors / 3);
        }
        if (primeFactors % 3 == 1) {
            return qpow(3, primeFactors / 3 - 1) * 4L % mod;
        }
        return qpow(3, primeFactors / 3) * 2 % mod;
    }
};
```

#### Go

```go
func maxNiceDivisors(primeFactors int) int {
	if primeFactors < 4 {
		return primeFactors
	}
	const mod = 1e9 + 7
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
	if primeFactors%3 == 0 {
		return qpow(3, primeFactors/3)
	}
	if primeFactors%3 == 1 {
		return qpow(3, primeFactors/3-1) * 4 % mod
	}
	return qpow(3, primeFactors/3) * 2 % mod
}
```

#### JavaScript

```js
/**
 * @param {number} primeFactors
 * @return {number}
 */
var maxNiceDivisors = function (primeFactors) {
    if (primeFactors < 4) {
        return primeFactors;
    }
    const mod = 1e9 + 7;
    const qpow = (a, n) => {
        let ans = 1;
        for (; n; n >>= 1) {
            if (n & 1) {
                ans = Number((BigInt(ans) * BigInt(a)) % BigInt(mod));
            }
            a = Number((BigInt(a) * BigInt(a)) % BigInt(mod));
        }
        return ans;
    };
    const k = Math.floor(primeFactors / 3);
    if (primeFactors % 3 === 0) {
        return qpow(3, k);
    }
    if (primeFactors % 3 === 1) {
        return (4 * qpow(3, k - 1)) % mod;
    }
    return (2 * qpow(3, k)) % mod;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
