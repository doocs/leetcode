# [剑指 Offer II 001. 整数除法](https://leetcode.cn/problems/xoh6Oh)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个整数 <code>a</code> 和 <code>b</code> ，求它们的除法的商 <code>a/b</code> ，要求不得使用乘号 <code>'*'</code>、除号 <code>'/'</code> 以及求余符号 <code>'%'</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>注意：</strong></p>

<ul>
	<li>整数除法的结果应当截去（<code>truncate</code>）其小数部分，例如：<code>truncate(8.345) = 8</code>&nbsp;以及&nbsp;<code>truncate(-2.7335) = -2</code></li>
	<li>假设我们的环境只能存储 32 位有符号整数，其数值范围是 <code>[−2<sup>31</sup>,&nbsp;2<sup>31</sup>−1]</code>。本题中，如果除法结果溢出，则返回 <code>2<sup>31&nbsp;</sup>− 1</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>a = 15, b = 2
<strong>输出：</strong>7
<strong><span style="white-space: pre-wrap;">解释：</span></strong>15/2 = truncate(7.5) = 7
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>a = 7, b = -3
<strong>输出：</strong><span style="white-space: pre-wrap;">-2</span>
<strong><span style="white-space: pre-wrap;">解释：</span></strong>7/-3 = truncate(-2.33333..) = -2</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>a = 0, b = 1
<strong>输出：</strong><span style="white-space: pre-wrap;">0</span></pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>a = 1, b = 1
<strong>输出：</strong><span style="white-space: pre-wrap;">1</span></pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>-2<sup>31</sup>&nbsp;&lt;= a, b &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
	<li><code>b != 0</code></li>
</ul>

<p>&nbsp;</p>

<p>注意：本题与主站 29&nbsp;题相同：<a href="https://leetcode.cn/problems/divide-two-integers/">https://leetcode.cn/problems/divide-two-integers/</a></p>

<p>&nbsp;</p>

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
        long x = abs(a);
        long y = abs(b);
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

    private long abs(long a) {
        if (a < 0) {
            return -a;
        }
        return a;
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
