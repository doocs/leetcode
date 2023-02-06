# [1808. Maximize Number of Nice Divisors](https://leetcode.com/problems/maximize-number-of-nice-divisors)

[中文文档](/solution/1800-1899/1808.Maximize%20Number%20of%20Nice%20Divisors/README.md)

## Description

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

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Java**

```java
class Solution {
    public int maxNiceDivisors(int primeFactors) {
        if (primeFactors < 4) {
            return primeFactors;
        }
        final int mod = (int) 1e9 + 7;
        if (primeFactors % 3 == 0) {
            return (int) qmi(3, primeFactors / 3, mod);
        }
        if (primeFactors % 3 == 1) {
            return (int) (4 * qmi(3, primeFactors / 3 - 1, mod) % mod);
        }
        return (int) (2 * qmi(3, primeFactors / 3, mod) % mod);
    }

    private long qmi(long a, long k, long p) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % p;
            }
            k >>= 1;
            a = a * a % p;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxNiceDivisors(int primeFactors) {
        if (primeFactors < 4) {
            return primeFactors;
        }
        const int mod = 1e9 + 7;
        if (primeFactors % 3 == 0) {
            return qmi(3, primeFactors / 3, mod);
        }
        if (primeFactors % 3 == 1) {
            return 4 * qmi(3, primeFactors / 3 - 1, mod) % mod;
        }
        return 2 * qmi(3, primeFactors / 3, mod) % mod;
    }

    long qmi(long a, long k, long p) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % p;
            }
            k >>= 1;
            a = a * a % p;
        }
        return res;
    }
};
```

### **Go**

```go
func maxNiceDivisors(primeFactors int) int {
	if primeFactors < 4 {
		return primeFactors
	}
	const mod int = 1e9 + 7
	if primeFactors%3 == 0 {
		return qmi(3, primeFactors/3, mod)
	}
	if primeFactors%3 == 1 {
		return 4 * qmi(3, primeFactors/3-1, mod) % mod
	}
	return 2 * qmi(3, primeFactors/3, mod) % mod
}

func qmi(a, k, p int) int {
	res := 1
	for k != 0 {
		if k&1 == 1 {
			res = res * a % p
		}
		k >>= 1
		a = a * a % p
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
