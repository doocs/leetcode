# [面试题 14- II. 剪绳子 II](这里是题目链接，如：https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/)

## 题目描述

<p>给你一根长度为 <code>n</code> 的绳子，请把绳子剪成整数长度的 <code>m</code>&nbsp;段（m、n都是整数，n&gt;1并且m&gt;1），每段绳子的长度记为 <code>k[0],k[1]...k[m - 1]</code> 。请问 <code>k[0]*k[1]*...*k[m - 1]</code> 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。</p>

<p>答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入: </strong>2
<strong>输出: </strong>1
<strong>解释: </strong>2 = 1 + 1, 1 &times; 1 = 1</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入: </strong>10
<strong>输出: </strong>36
<strong>解释: </strong>10 = 3 + 3 + 4, 3 &times;&nbsp;3 &times;&nbsp;4 = 36</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
</ul>

<p>注意：本题与主站 343 题相同：<a href="https://leetcode.cn/problems/integer-break/">https://leetcode.cn/problems/integer-break/</a></p>

## 解法

**方法一：数学（快速幂）**

当 $n \lt 4$，此时 $n$ 不能拆分成至少两个正整数的和，因此 $n - 1$ 是最大乘积。当 $n \ge 4$ 时，我们尽可能多地拆分 $3$，当剩下的最后一段为 $4$ 时，我们将其拆分为 $2 + 2$，这样乘积最大。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def cuttingRope(self, n: int) -> int:
        mod = 10**9 + 7
        if n < 4:
            return n - 1
        if n % 3 == 0:
            return pow(3, n // 3, mod)
        if n % 3 == 1:
            return (pow(3, n // 3 - 1, mod) * 4) % mod
        return pow(3, n // 3, mod) * 2 % mod
```

### **Java**

```java
class Solution {
    public int cuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }
        final int mod = (int) 1e9 + 7;
        if (n % 3 == 0) {
            return (int) qmi(3, n / 3, mod);
        }
        if (n % 3 == 1) {
            return (int) (qmi(3, n / 3 - 1, mod) * 4 % mod);
        }
        return (int) (qmi(3, n / 3, mod) * 2 % mod);
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
}
```

### **C++**

```cpp
class Solution {
public:
    int cuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }
        const int mod = 1e9 + 7;
        if (n % 3 == 0) {
            return qmi(3, n / 3, mod);
        }
        if (n % 3 == 1) {
            return qmi(3, n / 3 - 1, mod) * 4 % mod;
        }
        return qmi(3, n / 3, mod) * 2 % mod;
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

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number}
 */
var cuttingRope = function (n) {
    if (n <= 3) return n - 1;
    let a = ~~(n / 3);
    let b = n % 3;
    const MOD = 1e9 + 7;
    function myPow(x) {
        let r = 1;
        for (let i = 0; i < x; i++) {
            r = (r * 3) % MOD;
        }
        return r;
    }
    if (b === 1) {
        return (myPow(a - 1) * 4) % MOD;
    }
    if (b === 0) return myPow(a) % MOD;
    return (myPow(a) * 2) % MOD;
};
```

### **Go**

```go
func cuttingRope(n int) int {
	if n < 4 {
		return n - 1
	}
	const mod = 1e9 + 7
	if n%3 == 0 {
		return qmi(3, n/3, mod)
	}
	if n%3 == 1 {
		return qmi(3, n/3-1, mod) * 4 % mod
	}
	return qmi(3, n/3, mod) * 2 % mod
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

### **Rust**

```rust
impl Solution {
    pub fn cutting_rope(mut n: i32) -> i32 {
        if n < 4 {
            return n - 1;
        }
        let mut res = 1i64;
        while n > 4 {
            res = (res * 3) % 1000000007;
            n -= 3;
        }
        ((res * n as i64) % 1000000007) as i32
    }
}
```

### **C#**

```cs
public class Solution {
    public int CuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }
        int res = 1;
        while (n > 4) {
            res *= 3;
            n -= 3;
        }
        if (n == 4) {
            return (res << 2) % 1000000007;
        }
        return (res * n) % 1000000007;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
