# [2507. Smallest Value After Replacing With Sum of Prime Factors](https://leetcode.com/problems/smallest-value-after-replacing-with-sum-of-prime-factors)

[中文文档](/solution/2500-2599/2507.Smallest%20Value%20After%20Replacing%20With%20Sum%20of%20Prime%20Factors/README.md)

## Description

<p>You are given a positive integer <code>n</code>.</p>

<p>Continuously replace <code>n</code> with the sum of its <strong>prime factors</strong>.</p>

<ul>
	<li>Note that if a prime factor divides <code>n</code> multiple times, it should be included in the sum as many times as it divides <code>n</code>.</li>
</ul>

<p>Return <em>the smallest value </em><code>n</code><em> will take on.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 15
<strong>Output:</strong> 5
<strong>Explanation:</strong> Initially, n = 15.
15 = 3 * 5, so replace n with 3 + 5 = 8.
8 = 2 * 2 * 2, so replace n with 2 + 2 + 2 = 6.
6 = 2 * 3, so replace n with 2 + 3 = 5.
5 is the smallest value n will take on.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> Initially, n = 3.
3 is the smallest value n will take on.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def smallestValue(self, n: int) -> int:
        while 1:
            t, s, i = n, 0, 2
            while i <= n // i:
                while n % i == 0:
                    n //= i
                    s += i
                i += 1
            if n > 1:
                s += n
            if s == t:
                return t
            n = s
```

### **Java**

```java
class Solution {
    public int smallestValue(int n) {
        while (true) {
            int t = n, s = 0;
            for (int i = 2; i <= n / i; ++i) {
                while (n % i == 0) {
                    s += i;
                    n /= i;
                }
            }
            if (n > 1) {
                s += n;
            }
            if (s == t) {
                return s;
            }
            n = s;
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int smallestValue(int n) {
        while (1) {
            int t = n, s = 0;
            for (int i = 2; i <= n / i; ++i) {
                while (n % i == 0) {
                    s += i;
                    n /= i;
                }
            }
            if (n > 1) s += n;
            if (s == t) return s;
            n = s;
        }
    }
};
```

### **Go**

```go
func smallestValue(n int) int {
	for {
		t, s := n, 0
		for i := 2; i <= n/i; i++ {
			for n%i == 0 {
				s += i
				n /= i
			}
		}
		if n > 1 {
			s += n
		}
		if s == t {
			return s
		}
		n = s
	}
}
```

### **...**

```

```

<!-- tabs:end -->
