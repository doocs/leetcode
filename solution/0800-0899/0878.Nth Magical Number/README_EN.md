# [878. Nth Magical Number](https://leetcode.com/problems/nth-magical-number)

[中文文档](/solution/0800-0899/0878.Nth%20Magical%20Number/README.md)

## Description

<p>A positive integer is <em>magical</em> if it is divisible by either <code>a</code> or <code>b</code>.</p>

<p>Given the three integers <code>n</code>, <code>a</code>, and <code>b</code>, return the <code>n<sup>th</sup></code> magical number. Since the answer may be very large, <strong>return it modulo </strong><code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1, a = 2, b = 3
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 4, a = 2, b = 3
<strong>Output:</strong> 6
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>2 &lt;= a, b &lt;= 4 * 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def nthMagicalNumber(self, n: int, a: int, b: int) -> int:
        mod = 10**9 + 7
        c = lcm(a, b)
        r = (a + b) * n
        return bisect_left(range(r), x=n, key=lambda x: x // a + x // b - x // c) % mod
```

### **Java**

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int nthMagicalNumber(int n, int a, int b) {
        int c = a * b / gcd(a, b);
        long l = 0, r = (long) (a + b) * n;
        while (l < r) {
            long mid = l + r >>> 1;
            if (mid / a + mid / b - mid / c >= n) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return (int) (l % MOD);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **C++**

```cpp
using ll = long long;

class Solution {
public:
    const int mod = 1e9 + 7;

    int nthMagicalNumber(int n, int a, int b) {
        int c = lcm(a, b);
        ll l = 0, r = 1ll * (a + b) * n;
        while (l < r) {
            ll mid = l + r >> 1;
            if (mid / a + mid / b - mid / c >= n) r = mid;
            else l = mid + 1;
        }
        return l % mod;
    }
};
```

### **Go**

```go
func nthMagicalNumber(n int, a int, b int) int {
	c := a * b / gcd(a, b)
	const mod int = 1e9 + 7
	r := (a + b) * n
	return sort.Search(r, func(x int) bool { return x/a+x/b-x/c >= n }) % mod
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **...**

```

```

<!-- tabs:end -->
