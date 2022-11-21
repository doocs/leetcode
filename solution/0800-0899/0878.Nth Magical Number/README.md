# [878. 第 N 个神奇数字](https://leetcode.cn/problems/nth-magical-number)

[English Version](/solution/0800-0899/0878.Nth%20Magical%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个正整数如果能被 <code>a</code> 或 <code>b</code> 整除，那么它是神奇的。</p>

<p>给定三个整数 <code>n</code> ,&nbsp;<code>a</code> , <code>b</code> ，返回第 <code>n</code> 个神奇的数字。因为答案可能很大，所以返回答案&nbsp;<strong>对&nbsp;</strong><code>10<sup>9</sup>&nbsp;+ 7</code> <strong>取模&nbsp;</strong>后的值。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1, a = 2, b = 3
<strong>输出：</strong>2
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>n = 4, a = 2, b = 3
<strong>输出：</strong>6
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>2 &lt;= a, b &lt;= 4 * 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学 + 二分查找**

根据题目描述，神奇数字是能被 $a$ 或 $b$ 整除的正整数。

而我们知道，对于任意正整数 $x$，在 $[1,..x]$ 范围内，能被 $a$ 整除的数有 $\lfloor \frac{x}{a} \rfloor$ 个，能被 $b$ 整除的数有 $\lfloor \frac{x}{b} \rfloor$ 个，能被 $a$ 和 $b$ 同时整除的数有 $\lfloor \frac{x}{c} \rfloor$ 个，其中 $c$ 是 $a$ 和 $b$ 的最小公倍数。最小公倍数的计算公式为 $c = lcm(a, b) = \frac{a \times b}{gcd(a, b)}$。

因此，对于任意正整数 $x$，在 $[1,..x]$ 范围内，神奇数字的个数为：

$$
\lfloor \frac{x}{a} \rfloor + \lfloor \frac{x}{b} \rfloor - \lfloor \frac{x}{c} \rfloor
$$

为什么要减去 $\lfloor \frac{x}{c} \rfloor$ 呢？可以这样理解，在 $[1,..x]$ 范围内，能被 $a$ 和 $b$ 同时整除的数，它们既能被 $a$ 整除，也能被 $b$ 整除，因此它们被计算了两次，需要减去一次。

题目要我们找到第 $n$ 个神奇数字，也即是说，要找到一个最小的正整数 $x$，使得以下式子成立：

$$
\lfloor \frac{x}{a} \rfloor + \lfloor \frac{x}{b} \rfloor - \lfloor \frac{x}{c} \rfloor \geq n
$$

随着 $x$ 的增大，神奇数字的个数也会增大，因此我们可以使用二分查找的方法，找到最小的正整数 $x$，使得上述式子成立。

注意答案的取模操作。

时间复杂度 $O(\log M)$，空间复杂度 $O(1)$。其中 $M$ 是二分查找的上界，本题可以取 $M=(a+b) \times n$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def nthMagicalNumber(self, n: int, a: int, b: int) -> int:
        mod = 10**9 + 7
        c = lcm(a, b)
        r = (a + b) * n
        return bisect_left(range(r), x=n, key=lambda x: x // a + x // b - x // c) % mod
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
