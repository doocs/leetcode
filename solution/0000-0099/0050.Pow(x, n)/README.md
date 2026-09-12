---
comments: true
difficulty: 中等
tags:
    - 递归
    - 数学
---

<!-- problem:start -->

# [50. Pow(x, n)](https://leetcode.cn/problems/powx-n)

[English Version](/solution/0000-0099/0050.Pow%28x%2C%20n%29/README_EN.md)

## 题目描述

<!-- description:start -->

<p>实现&nbsp;<a href="https://www.cplusplus.com/reference/valarray/pow/" target="_blank">pow(<em>x</em>, <em>n</em>)</a>&nbsp;，即计算 <code>x</code> 的整数&nbsp;<code>n</code> 次幂函数（即，<code>x<sup>n</sup></code><sup><span style="font-size:10.8333px"> </span></sup>）。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>x = 2.00000, n = 10
<strong>输出：</strong>1024.00000
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>x = 2.10000, n = 3
<strong>输出：</strong>9.26100
</pre>

<p><strong class="example">示例 3：</strong></p>

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
	<li>要么 <code>x</code> 不为零，要么 <code>n &gt; 0</code> 。</li>
	<li><code>-10<sup>4</sup> &lt;= x<sup>n</sup> &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学（快速幂）

<!-- thinking:start -->

> **思考**
>
> 若把 $x$ 连乘 $|n|$ 次，$|n|$ 最大约 $2^{31}$，线性乘法容易超时；还要单独处理 $n < 0$ 时取倒数。
>
> 一次只乘一个 $x$ 没有利用幂的倍增结构。注意到 $x^{2k} = (x^k)^2$，$x^{2k+1} = x \cdot (x^k)^2$，指数每次可以折半。
>
> 因此按 $n$ 的二进制位累乘：当前底不断平方，遇到 $1$ 位就乘进答案。负数指数先算正幂再取倒数。快速幂把 $O(|n|)$ 压成 $O(\log |n|)$。

<!-- thinking:end -->

快速幂算法的核心思想是将幂指数 $n$ 拆分为若干个二进制位上的 $1$ 的和，然后将 $x$ 的 $n$ 次幂转化为 $x$ 的若干个幂的乘积。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。其中 $n$ 为幂指数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def myPow(self, x: float, n: int) -> float:
        def qpow(a: float, n: int) -> float:
            ans = 1
            while n:
                if n & 1:
                    ans *= a
                a *= a
                n >>= 1
            return ans

        return qpow(x, n) if n >= 0 else 1 / qpow(x, -n)
```

#### Java

```java
class Solution {
    public double myPow(double x, int n) {
        return n >= 0 ? qpow(x, n) : 1 / qpow(x, -(long) n);
    }

    private double qpow(double a, long n) {
        double ans = 1;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ans = ans * a;
            }
            a = a * a;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    double myPow(double x, int n) {
        auto qpow = [](double a, long long n) {
            double ans = 1;
            for (; n; n >>= 1) {
                if (n & 1) {
                    ans *= a;
                }
                a *= a;
            }
            return ans;
        };
        return n >= 0 ? qpow(x, n) : 1 / qpow(x, -(long long) n);
    }
};
```

#### Go

```go
func myPow(x float64, n int) float64 {
	qpow := func(a float64, n int) float64 {
		ans := 1.0
		for ; n > 0; n >>= 1 {
			if n&1 == 1 {
				ans *= a
			}
			a *= a
		}
		return ans
	}
	if n >= 0 {
		return qpow(x, n)
	}
	return 1 / qpow(x, -n)
}
```

#### TypeScript

```ts
function myPow(x: number, n: number): number {
    const qpow = (a: number, n: number): number => {
        let ans = 1;
        for (; n; n >>>= 1) {
            if (n & 1) {
                ans *= a;
            }
            a *= a;
        }
        return ans;
    };
    return n >= 0 ? qpow(x, n) : 1 / qpow(x, -n);
}
```

#### Rust

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn my_pow(x: f64, n: i32) -> f64 {
        let mut x = x;
        let n = n as i64;
        if n >= 0 {
            Self::quick_pow(&mut x, n)
        } else {
            1.0 / Self::quick_pow(&mut x, -n)
        }
    }

    #[allow(dead_code)]
    fn quick_pow(x: &mut f64, mut n: i64) -> f64 {
        // `n` should greater or equal to zero
        let mut ret = 1.0;
        while n != 0 {
            if (n & 0x1) == 1 {
                ret *= *x;
            }
            *x *= *x;
            n >>= 1;
        }
        ret
    }
}
```

#### JavaScript

```js
/**
 * @param {number} x
 * @param {number} n
 * @return {number}
 */
var myPow = function (x, n) {
    const qpow = (a, n) => {
        let ans = 1;
        for (; n; n >>>= 1) {
            if (n & 1) {
                ans *= a;
            }
            a *= a;
        }
        return ans;
    };
    return n >= 0 ? qpow(x, n) : 1 / qpow(x, -n);
};
```

#### C#

```cs
public class Solution {
    public double MyPow(double x, int n) {
        return n >= 0 ? qpow(x, n) : 1.0 / qpow(x, -(long)n);
    }

    private double qpow(double a, long n) {
        double ans = 1;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ans *= a;
            }
            a *= a;
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
