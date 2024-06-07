---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9816.%20%E6%95%B0%E5%80%BC%E7%9A%84%E6%95%B4%E6%95%B0%E6%AC%A1%E6%96%B9/README.md
---

<!-- problem:start -->

# [面试题 16. 数值的整数次方](https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/)

## 题目描述

<!-- description:start -->

<p>实现 <a href="https://www.cplusplus.com/reference/valarray/pow/">pow(<em>x</em>, <em>n</em>)</a> ，即计算 x 的 n 次幂函数（即，x<sup>n</sup>）。不得使用库函数，同时不需要考虑大数问题。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>x = 2.00000, n = 10
<strong>输出：</strong>1024.00000
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>x = 2.10000, n = 3
<strong>输出：</strong>9.26100</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>x = 2.00000, n = -2
<strong>输出：</strong>0.25000
<strong>解释：</strong>2<sup>-2</sup> = 1/2<sup>2</sup> = 1/4 = 0.25</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-100.0 < x < 100.0</code></li>
	<li><code>-2<sup>31</sup> <= n <= 2<sup>31</sup>-1</code></li>
	<li><code>-10<sup>4</sup> <= x<sup>n</sup> <= 10<sup>4</sup></code></li>
</ul>

<p> </p>

<p>注意：本题与主站 50 题相同：<a href="https://leetcode.cn/problems/powx-n/">https://leetcode.cn/problems/powx-n/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学（快速幂）

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

#### Swift

```swift
class Solution {
    func myPow(_ x: Double, _ n: Int) -> Double {
        return n >= 0 ? qpow(x, Int64(n)) : 1 / qpow(x, -Int64(n))
    }

    private func qpow(_ a: Double, _ n: Int64) -> Double {
        var ans: Double = 1
        var base: Double = a
        var exponent: Int64 = n

        while exponent > 0 {
            if (exponent & 1) == 1 {
                ans *= base
            }
            base *= base
            exponent >>= 1
        }

        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
