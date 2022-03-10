# [29. Divide Two Integers](https://leetcode.com/problems/divide-two-integers)

[中文文档](/solution/0000-0099/0029.Divide%20Two%20Integers/README.md)

## Description

<p>Given two integers <code>dividend</code> and <code>divisor</code>, divide two integers <strong>without</strong> using multiplication, division, and mod operator.</p>

<p>The integer division should truncate toward zero, which means losing its fractional part. For example, <code>8.345</code> would be truncated to <code>8</code>, and <code>-2.7335</code> would be truncated to <code>-2</code>.</p>

<p>Return <em>the <strong>quotient</strong> after dividing </em><code>dividend</code><em> by </em><code>divisor</code>.</p>

<p><strong>Note: </strong>Assume we are dealing with an environment that could only store integers within the <strong>32-bit</strong> signed integer range: <code>[&minus;2<sup>31</sup>, 2<sup>31</sup> &minus; 1]</code>. For this problem, if the quotient is <strong>strictly greater than</strong> <code>2<sup>31</sup> - 1</code>, then return <code>2<sup>31</sup> - 1</code>, and if the quotient is <strong>strictly less than</strong> <code>-2<sup>31</sup></code>, then return <code>-2<sup>31</sup></code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> dividend = 10, divisor = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> 10/3 = 3.33333.. which is truncated to 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> dividend = 7, divisor = -3
<strong>Output:</strong> -2
<strong>Explanation:</strong> 7/-3 = -2.33333.. which is truncated to -2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;= dividend, divisor &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>divisor != 0</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def divide(self, a: int, b: int) -> int:
        INT_MAX = (1 << 31) - 1
        INT_MIN = -(1 << 31)
        sign = -1 if a * b < 0 else 1
        a = abs(a)
        b = abs(b)
        tot = 0
        while a >= b:
            cnt = 0
            while a >= (b << (cnt + 1)):
                cnt += 1
            tot += 1 << cnt
            a -= b << cnt
        return sign * tot if INT_MIN <= sign * tot <= INT_MAX else INT_MAX
```

### **Java**

```java
class Solution {
    public int divide(int a, int b) {
        int sign = 1;
        if ((a < 0) != (b < 0)) {
            sign = -1;
        }
        long x = Math.abs((long) a);
        long y = Math.abs((long) b);
        long tot = 0;
        while (x >= y) {
            int cnt = 0;
            while (x >= (y << (cnt + 1))) {
                cnt++;
            }
            tot += 1L << cnt;
            x -= y << cnt;
        }
        long ans = sign * tot;
        if (ans >= Integer.MIN_VALUE && ans <= Integer.MAX_VALUE) {
            return (int) ans;
        }
        return Integer.MAX_VALUE;
    }
}
```

### **Go**

```go
func divide(a int, b int) int {
	sign := 1
	if a*b < 0 {
		sign = -1
	}

	a = abs(a)
	b = abs(b)

	tot := 0
	for a >= b {
		cnt := 0
		for a >= (b << (cnt + 1)) {
			cnt++
		}
		tot += 1 << cnt
		a -= b << cnt
	}

	ans := sign * tot
	if ans >= math.MinInt32 && ans <= math.MaxInt32 {
		return ans
	}
	return math.MaxInt32
}

func abs(a int) int {
	if a < 0 {
		return -a
	}
	return a
}
```

### **C++**

```cpp
class Solution {
public:
    int divide(int a, int b) {
        int sign = 1;
        if (a < 0 ^ b < 0) {
            sign = -1;
        }

        auto x = abs(static_cast<long long>(a));
        auto y = abs(static_cast<long long>(b));
        auto tot = 0ll;
        while (x >= y) {
            int cnt = 0;
            while (x >= (y << (cnt + 1))) {
                ++cnt;
            }
            tot += 1ll << cnt;
            x -= y << cnt;
        }

        auto ans = sign * tot;
        if (ans >= INT32_MIN && ans <= INT32_MAX) {
            return static_cast<int>(ans);
        }
        return INT32_MAX;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
