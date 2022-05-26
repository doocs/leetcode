# [29. 两数相除](https://leetcode.cn/problems/divide-two-integers)

[English Version](/solution/0000-0099/0029.Divide%20Two%20Integers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个整数，被除数&nbsp;<code>dividend</code>&nbsp;和除数&nbsp;<code>divisor</code>。将两数相除，要求不使用乘法、除法和 mod 运算符。</p>

<p>返回被除数&nbsp;<code>dividend</code>&nbsp;除以除数&nbsp;<code>divisor</code>&nbsp;得到的商。</p>

<p>整数除法的结果应当截去（<code>truncate</code>）其小数部分，例如：<code>truncate(8.345) = 8</code> 以及 <code>truncate(-2.7335) = -2</code></p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入:</strong> dividend = 10, divisor = 3
<strong>输出:</strong> 3
<strong>解释: </strong>10/3 = truncate(3.33333..) = truncate(3) = 3</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> dividend = 7, divisor = -3
<strong>输出:</strong> -2
<strong>解释:</strong> 7/-3 = truncate(-2.33333..) = -2</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>被除数和除数均为 32 位有符号整数。</li>
	<li>除数不为&nbsp;0。</li>
	<li>假设我们的环境只能存储 32 位有符号整数，其数值范围是 [&minus;2<sup>31</sup>,&nbsp; 2<sup>31&nbsp;</sup>&minus; 1]。本题中，如果除法结果溢出，则返回 2<sup>31&nbsp;</sup>&minus; 1。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

通过下面这段伪代码，不难理解除法本质上就是减法，但是一次循环只能做一次减法，效率太低会导致超时，所以再加上快速幂的思想优化即可

```py
sign = -1 if a * b < 0 else 1
a = abs(a)
b = abs(b)
cnt = 0
while a >= b:
    a -= b
    cnt += 1
return sign * cnt
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
