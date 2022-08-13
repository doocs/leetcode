# [479. Largest Palindrome Product](https://leetcode.com/problems/largest-palindrome-product)

[中文文档](/solution/0400-0499/0479.Largest%20Palindrome%20Product/README.md)

## Description

<p>Given an integer n, return <em>the <strong>largest palindromic integer</strong> that can be represented as the product of two <code>n</code>-digits integers</em>. Since the answer can be very large, return it <strong>modulo</strong> <code>1337</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 987
Explanation: 99 x 91 = 9009, 9009 % 1337 = 987
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 9
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 8</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largestPalindrome(self, n: int) -> int:
        mx = 10**n - 1
        for a in range(mx, mx // 10, -1):
            b = x = a
            while b:
                x = x * 10 + b % 10
                b //= 10
            t = mx
            while t * t >= x:
                if x % t == 0:
                    return x % 1337
                t -= 1
        return 9
```

### **Java**

```java
class Solution {
    public int largestPalindrome(int n) {
        int mx = (int) Math.pow(10, n) - 1;
        for (int a = mx; a > mx / 10; --a) {
            int b = a;
            long x = a;
            while (b != 0) {
                x = x * 10 + b % 10;
                b /= 10;
            }
            for (long t = mx; t * t >= x; --t) {
                if (x % t == 0) {
                    return (int) (x % 1337);
                }
            }
        }
        return 9;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int largestPalindrome(int n) {
        int mx = pow(10, n) - 1;
        for (int a = mx; a > mx / 10; --a) {
            int b = a;
            long x = a;
            while (b) {
                x = x * 10 + b % 10;
                b /= 10;
            }
            for (long t = mx; t * t >= x; --t)
                if (x % t == 0)
                    return x % 1337;
        }
        return 9;
    }
};
```

### **Go**

```go
func largestPalindrome(n int) int {
	mx := int(math.Pow10(n)) - 1
	for a := mx; a > mx/10; a-- {
		x := a
		for b := a; b != 0; b /= 10 {
			x = x*10 + b%10
		}
		for t := mx; t*t >= x; t-- {
			if x%t == 0 {
				return x % 1337
			}
		}
	}
	return 9
}
```

### **...**

```

```

<!-- tabs:end -->
