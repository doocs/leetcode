# [29. 两数相除](https://leetcode.cn/problems/divide-two-integers)

[English Version](/solution/0000-0099/0029.Divide%20Two%20Integers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数，被除数&nbsp;<code>dividend</code>&nbsp;和除数&nbsp;<code>divisor</code>。将两数相除，要求 <strong>不使用</strong> 乘法、除法和取余运算。</p>

<p>整数除法应该向零截断，也就是截去（<code>truncate</code>）其小数部分。例如，<code>8.345</code> 将被截断为 <code>8</code> ，<code>-2.7335</code> 将被截断至 <code>-2</code> 。</p>

<p>返回被除数&nbsp;<code>dividend</code>&nbsp;除以除数&nbsp;<code>divisor</code>&nbsp;得到的 <strong>商</strong> 。</p>

<p><strong>注意：</strong>假设我们的环境只能存储 <strong>32 位</strong> 有符号整数，其数值范围是 <code>[−2<sup>31</sup>,&nbsp; 2<sup>31&nbsp;</sup>− 1]</code> 。本题中，如果商 <strong>严格大于</strong> <code>2<sup>31&nbsp;</sup>− 1</code> ，则返回 <code>2<sup>31&nbsp;</sup>− 1</code> ；如果商 <strong>严格小于</strong> <code>-2<sup>31</sup></code> ，则返回 <code>-2<sup>31</sup></code><sup> </sup>。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> dividend = 10, divisor = 3
<strong>输出:</strong> 3
<strong>解释: </strong>10/3 = 3.33333.. ，向零截断后得到 3 。</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> dividend = 7, divisor = -3
<strong>输出:</strong> -2
<strong>解释:</strong> 7/-3 = -2.33333.. ，向零截断后得到 -2 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;= dividend, divisor &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>divisor != 0</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟 + 快速幂**

除法本质上就是减法，题目要求我们计算出两个数相除之后的取整结果，其实就是计算被除数是多少个除数加上一个小于除数的数构成的。但是一次循环只能做一次减法，效率太低会导致超时，可借助快速幂的思想进行优化。

需要注意的是，由于题目明确要求最大只能使用 32 位有符号整数，所以需要将除数和被除数同时转换为负数进行计算。因为转换正数可能会导致溢出，如当被除数为 `INT32_MIN` 时，转换为正数时会大于 `INT32_MAX`。

假设被除数为 `a`，除数为 `b`，则时间复杂度为 $O(\log a \times \log b)$，空间复杂度 $O(1)$。

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
	sign, ans, INT32_MAX, INT32_MIN, LIMIT := false, 0, 1<<31-1, -1<<31, -1<<31/2
	if (a > 0 && b < 0) || (a < 0 && b > 0) {
		sign = true
	}
	a, b = convert(a), convert(b)
	for a <= b {
		cnt := 0
		// (b<<cnt) >= LIMIT 是为了避免 b<<(cnt+1) 发生溢出
		for (b<<cnt) >= LIMIT && a <= (b<<(cnt+1)) {
			cnt++
		}
		ans = ans + -1<<cnt
		a = a - b<<cnt
	}
	if sign {
		return ans
	}
	if ans == INT32_MIN {
		return INT32_MAX
	}
	return -ans
}

func convert(v int) int {
	if v > 0 {
		return -v
	}
	return v
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
