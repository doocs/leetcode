# [866. Prime Palindrome](https://leetcode.com/problems/prime-palindrome)

[中文文档](/solution/0800-0899/0866.Prime%20Palindrome/README.md)

## Description

<p>Given an integer n, return <em>the smallest <strong>prime palindrome</strong> greater than or equal to </em><code>n</code>.</p>

<p>An integer is <strong>prime</strong> if it has exactly two divisors: <code>1</code> and itself. Note that <code>1</code> is not a prime number.</p>

<ul>
	<li>For example, <code>2</code>, <code>3</code>, <code>5</code>, <code>7</code>, <code>11</code>, and <code>13</code> are all primes.</li>
</ul>

<p>An integer is a <strong>palindrome</strong> if it reads the same from left to right as it does from right to left.</p>

<ul>
	<li>For example, <code>101</code> and <code>12321</code> are palindromes.</li>
</ul>

<p>The test cases are generated so that the answer always exists and is in the range <code>[2, 2 * 10<sup>8</sup>]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> n = 6
<strong>Output:</strong> 7
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> n = 8
<strong>Output:</strong> 11
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> n = 13
<strong>Output:</strong> 101
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>8</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def primePalindrome(self, n: int) -> int:
        def is_prime(x):
            if x < 2:
                return False
            v = 2
            while v * v <= x:
                if x % v == 0:
                    return False
                v += 1
            return True

        def reverse(x):
            res = 0
            while x:
                res = res * 10 + x % 10
                x //= 10
            return res

        while 1:
            if reverse(n) == n and is_prime(n):
                return n
            if 10**7 < n < 10**8:
                n = 10**8
            n += 1
```

### **Java**

```java
class Solution {
    public int primePalindrome(int n) {
        while (true) {
            if (reverse(n) == n && isPrime(n)) {
                return n;
            }
            if (n > 10000000 && n < 100000000) {
                n = 100000000;
            }
            ++n;
        }
    }

    private boolean isPrime(int x) {
        if (x < 2) {
            return false;
        }
        for (int v = 2; v * v <= x; ++v) {
            if (x % v == 0) {
                return false;
            }
        }
        return true;
    }

    private int reverse(int x) {
        int res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int primePalindrome(int n) {
        while (1) {
            if (reverse(n) == n && isPrime(n)) return n;
            if (n > 10000000 && n < 100000000) n = 100000000;
            ++n;
        }
    }

    bool isPrime(int x) {
        if (x < 2) return false;
        for (int v = 2; v * v <= x; ++v)
            if (x % v == 0)
                return false;
        return true;
    }

    int reverse(int x) {
        int res = 0;
        while (x) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }
};
```

### **Go**

```go
func primePalindrome(n int) int {
	isPrime := func(x int) bool {
		if x < 2 {
			return false
		}
		for v := 2; v*v <= x; v++ {
			if x%v == 0 {
				return false
			}
		}
		return true
	}

	reverse := func(x int) int {
		res := 0
		for x != 0 {
			res = res*10 + x%10
			x /= 10
		}
		return res
	}
	for {
		if reverse(n) == n && isPrime(n) {
			return n
		}
		if n > 10000000 && n < 100000000 {
			n = 100000000
		}
		n++
	}
}
```

### **...**

```

```

<!-- tabs:end -->
