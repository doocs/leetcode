# [50. Pow(x, n)](https://leetcode.cn/problems/powx-n)

[English Version](/solution/0000-0099/0050.Pow%28x%2C%20n%29/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>实现&nbsp;<a href="https://www.cplusplus.com/reference/valarray/pow/" target="_blank">pow(<em>x</em>, <em>n</em>)</a>&nbsp;，即计算 <code>x</code> 的整数&nbsp;<code>n</code> 次幂函数（即，<code>x<sup>n</sup></code><sup><span style="font-size:10.8333px"> </span></sup>）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>x = 2.00000, n = 10
<strong>输出：</strong>1024.00000
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>x = 2.10000, n = 3
<strong>输出：</strong>9.26100
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>x = 2.00000, n = -2
<strong>输出：</strong>0.25000
<strong>解释：</strong>2<sup>-2</sup> = 1/2<sup>2</sup> = 1/4 = 0.25
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-100.0 &lt; x &lt; 100.0</code></li>
	<li><code>-2<sup>31</sup> &lt;= n &lt;= 2<sup>31</sup>-1</code></li>
	<li><code>n</code>&nbsp;是一个整数</li>
	<li><code>-10<sup>4</sup> &lt;= x<sup>n</sup> &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学（快速幂）**

快速幂算法的核心思想是将幂指数 $n$ 拆分为若干个二进制位上的 $1$ 的和，然后将 $x$ 的 $n$ 次幂转化为 $x$ 的若干个幂的乘积。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。其中 $n$ 为幂指数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def myPow(self, x: float, n: int) -> float:
        def qmi(a, k):
            res = 1
            while k:
                if k & 1:
                    res *= a
                a *= a
                k >>= 1
            return res

        return qmi(x, n) if n >= 0 else 1 / qmi(x, -n)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public double myPow(double x, int n) {
        long N = n;
        return n >= 0 ? qmi(x, N) : 1.0 / qmi(x, -N);
    }

    private double qmi(double a, long k) {
        double res = 1;
        while (k != 0) {
            if ((k & 1) != 0) {
                res *= a;
            }
            a *= a;
            k >>= 1;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    double myPow(double x, int n) {
        long long N = n;
        return N >= 0 ? qmi(x, N) : 1.0 / qmi(x, -N);
    }

    double qmi(double a, long long k) {
        double res = 1;
        while (k) {
            if (k & 1) {
                res *= a;
            }
            a *= a;
            k >>= 1;
        }
        return res;
    }
};
```

### **Go**

```go
func myPow(x float64, n int) float64 {
	if n >= 0 {
		return qmi(x, n)
	}
	return 1.0 / qmi(x, -n)
}

func qmi(a float64, k int) float64 {
	var res float64 = 1
	for k != 0 {
		if k&1 == 1 {
			res *= a
		}
		a *= a
		k >>= 1
	}
	return res
}
```

### **JavaScript**

```js
/**
 * @param {number} x
 * @param {number} n
 * @return {number}
 */
var myPow = function (x, n) {
    return n >= 0 ? qmi(x, n) : 1 / qmi(x, -n);
};

function qmi(a, k) {
    let res = 1;
    while (k) {
        if (k & 1) {
            res *= a;
        }
        a *= a;
        k >>>= 1;
    }
    return res;
}
```

### **TypeScript**

```ts
function myPow(x: number, n: number): number {
    return n >= 0 ? qmi(x, n) : 1 / qmi(x, -n);
}

function qmi(a: number, k: number): number {
    let res = 1;
    while (k) {
        if (k & 1) {
            res *= a;
        }
        a *= a;
        k >>>= 1;
    }
    return res;
}
```

### **C#**

```cs
public class Solution {
    public double MyPow(double x, int n) {
        long N = n;
        return n >= 0 ? qmi(x, N) : 1.0 / qmi(x, -N);
    }

    private double qmi(double a, long k) {
        double res = 1;
        while (k != 0) {
            if ((k & 1) != 0) {
                res *= a;
            }
            a *= a;
            k >>= 1;
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
