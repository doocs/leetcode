# [204. Count Primes](https://leetcode.com/problems/count-primes)

[中文文档](/solution/0200-0299/0204.Count%20Primes/README.md)

## Description

<p>Count the number of prime numbers less than a non-negative number, <b><i>n</i></b>.</p>

<p><strong>Example:</strong></p>

<pre>

<strong>Input:</strong> 10

<strong>Output:</strong> 4

<strong>Explanation:</strong> There are 4 prime numbers less than 10, they are 2, 3, 5, 7.

</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countPrimes(self, n: int) -> int:
        if n < 2:
            return 0
        res = 0
        primes = [True for _ in range(n)]
        for i in range(2, n):
            if primes[i]:
                res += 1
                for j in range(i * i, n, i):
                    primes[j] = False
        return res
```

### **Java**

```java
class Solution {
    public int countPrimes(int n) {
        if (n < 2) return 0;
        boolean[] primes = new boolean[n];
        Arrays.fill(primes, true);
        int res = 0;
        for (int i = 2; i < n; ++i) {
            if (primes[i]) {
                ++res;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        primes[j] = false;
                    }
                }
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
