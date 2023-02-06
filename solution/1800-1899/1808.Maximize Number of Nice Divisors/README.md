# [1808. 好因子的最大数目](https://leetcode.cn/problems/maximize-number-of-nice-divisors)

[English Version](/solution/1800-1899/1808.Maximize%20Number%20of%20Nice%20Divisors/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数 <code>primeFactors</code> 。你需要构造一个正整数 <code>n</code> ，它满足以下条件：</p>

<ul>
	<li><code>n</code> 质因数（质因数需要考虑重复的情况）的数目 <strong>不超过 </strong><code>primeFactors</code> 个。</li>
	<li><code>n</code> 好因子的数目最大化。如果 <code>n</code> 的一个因子可以被 <code>n</code> 的每一个质因数整除，我们称这个因子是 <strong>好因子</strong> 。比方说，如果 <code>n = 12</code> ，那么它的质因数为 <code>[2,2,3]</code> ，那么 <code>6</code> 和 <code>12</code> 是好因子，但 <code>3</code> 和 <code>4</code> 不是。</li>
</ul>

<p>请你返回 <code>n</code> 的好因子的数目。由于答案可能会很大，请返回答案对 <code>10<sup>9</sup> + 7</code> <b>取余</b> 的结果。</p>

<p>请注意，一个质数的定义是大于 <code>1</code> ，且不能被分解为两个小于该数的自然数相乘。一个数 <code>n</code> 的质因子是将 <code>n</code> 分解为若干个质因子，且它们的乘积为 <code>n</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>primeFactors = 5
<strong>输出：</strong>6
<b>解释：</b>200 是一个可行的 n 。
它有 5 个质因子：[2,2,2,5,5] ，且有 6 个好因子：[10,20,40,50,100,200] 。
不存在别的 n 有至多 5 个质因子，且同时有更多的好因子。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>primeFactors = 8
<b>输出：</b>18
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= primeFactors <= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：问题转换 + 快速幂**

我们可以将 $n$ 进行质因数分解，即 $n = a_1^{k_1} \times a_2^{k_2} \times\cdots \times a_m^{k_m}$，其中 $a_i$ 为质因子，而 $k_i$ 为质因子 $a_i$ 的指数。由于 $n$ 的质因子个数不超过 $primeFactors$ 个，因此 $k_1 + k_2 + \cdots + k_m \leq primeFactors$。

而根据题意描述，我们知道 $n$ 的好因子要满足能被所有的质因子整除，也即是说 $n$ 的好因子需要包含 $a_1 \times a_2 \times \cdots \times a_m$ 作为因数。那么好因子的个数 $k= k_1 \times k_2 \times \cdots \times k_m$，即 $k$ 为 $k_1, k_2, \cdots, k_m$ 的乘积。要最大化好因子的个数，也即是说我们要将 `primeFactors` 拆分成 $k_1, k_2, \cdots, k_m$，使得 $k_1 \times k_2 \times \cdots \times k_m$ 最大。因此问题转换为：将整数 `primeFactors` 拆分成若干个整数的乘积，使得乘积最大。

接下来，我们只需要分情况讨论。

-   如果 $primeFactors \lt 4$，那么直接返回 `primeFactors` 即可。
-   如果 $primeFactors$ 为 $3$ 的倍数，那么我们将 `primeFactors` 拆分成 $3$ 的倍数个 $3$，即 $3^{\frac{primeFactors}{3}}$。
-   如果 $primeFactors$ 除以 $3$ 余 $1$，那么我们将 `primeFactors` 拆分成 $\frac{primeFactors}{3} - 1$ 个 $3$，再乘以 $4$，即 $3^{\frac{primeFactors}{3} - 1} \times 4$。
-   如果 $primeFactors$ 除以 $3$ 余 $2$，那么我们将 `primeFactors` 拆分成 $\frac{primeFactors}{3}$ 个 $3$，再乘以 $2$，即 $3^{\frac{primeFactors}{3}} \times 2$。

以上过程中，我们利用快速幂取模求解。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxNiceDivisors(self, primeFactors: int) -> int:
        mod = 10**9 + 7
        if primeFactors < 4:
            return primeFactors
        if primeFactors % 3 == 0:
            return pow(3, primeFactors // 3, mod) % mod
        if primeFactors % 3 == 1:
            return 4 * pow(3, primeFactors // 3 - 1, mod) % mod
        return 2 * pow(3, primeFactors // 3, mod) % mod
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxNiceDivisors(int primeFactors) {
        if (primeFactors < 4) {
            return primeFactors;
        }
        final int mod = (int) 1e9 + 7;
        if (primeFactors % 3 == 0) {
            return (int) qmi(3, primeFactors / 3, mod);
        }
        if (primeFactors % 3 == 1) {
            return (int) (4 * qmi(3, primeFactors / 3 - 1, mod) % mod);
        }
        return (int) (2 * qmi(3, primeFactors / 3, mod) % mod);
    }

    private long qmi(long a, long k, long p) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % p;
            }
            k >>= 1;
            a = a * a % p;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxNiceDivisors(int primeFactors) {
        if (primeFactors < 4) {
            return primeFactors;
        }
        const int mod = 1e9 + 7;
        if (primeFactors % 3 == 0) {
            return qmi(3, primeFactors / 3, mod);
        }
        if (primeFactors % 3 == 1) {
            return 4 * qmi(3, primeFactors / 3 - 1, mod) % mod;
        }
        return 2 * qmi(3, primeFactors / 3, mod) % mod;
    }

    long qmi(long a, long k, long p) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % p;
            }
            k >>= 1;
            a = a * a % p;
        }
        return res;
    }
};
```

### **Go**

```go
func maxNiceDivisors(primeFactors int) int {
	if primeFactors < 4 {
		return primeFactors
	}
	const mod int = 1e9 + 7
	if primeFactors%3 == 0 {
		return qmi(3, primeFactors/3, mod)
	}
	if primeFactors%3 == 1 {
		return 4 * qmi(3, primeFactors/3-1, mod) % mod
	}
	return 2 * qmi(3, primeFactors/3, mod) % mod
}

func qmi(a, k, p int) int {
	res := 1
	for k != 0 {
		if k&1 == 1 {
			res = res * a % p
		}
		k >>= 1
		a = a * a % p
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
