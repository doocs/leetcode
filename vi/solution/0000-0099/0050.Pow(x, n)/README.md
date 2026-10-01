---
comments: true
difficulty: Medium
tags:
    - Recursion
    - Math
---

<!-- problem:start -->

# [50. Pow(x, n)](https://leetcode.com/problems/powx-n)

[中文文档](/solution/0000-0099/0050.Pow%28x%2C%20n%29/README.md)

## Mô tả

<!-- description:start -->

<p>Triển khai <a href="http://www.cplusplus.com/reference/valarray/pow/" target="_blank">pow(x, n)</a>, tính <code>x</code> lũy thừa <code>n</code> (tức là <code>x<sup>n</sup></code>).</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> x = 2.00000, n = 10
<strong>Đầu ra:</strong> 1024.00000
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> x = 2.10000, n = 3
<strong>Đầu ra:</strong> 9.26100
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> x = 2.00000, n = -2
<strong>Đầu ra:</strong> 0.25000
<strong>Giải thích:</strong> 2<sup>-2</sup> = 1/2<sup>2</sup> = 1/4 = 0.25
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>-100.0 &lt; x &lt; 100.0</code></li>
	<li><code>-2<sup>31</sup> &lt;= n &lt;= 2<sup>31</sup>-1</code></li>
	<li><code>n</code> là một số nguyên.</li>
	<li><code>x</code> không bằng 0 hoặc <code>n &gt; 0</code>.</li>
	<li><code>-10<sup>4</sup> &lt;= x<sup>n</sup> &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Toán học (Lũy thừa nhanh)

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là nhân $x$ với chính nó $|n|$ lần. $|n|$ có thể đạt tới $2^{31}$, vì vậy vòng lặp tuyến tính sẽ bị quá thời gian; $n$ âm cũng cần lấy nghịch đảo.
>
> Nút thắt nằm ở việc mỗi lần chỉ nhân thêm một $x$. $x^{2k} = (x^k)^2$ và $x^{2k+1} = x \cdot (x^k)^2$ — số mũ giảm đi một nửa sau mỗi bước.
>
> Duyệt qua các bit của $n$: bình phương cơ số, và khi một bit bằng $1$ thì nhân vào đáp án. Với $n$ âm, tính lũy thừa dương rồi lấy nghịch đảo. Lũy thừa nhanh giảm độ phức tạp từ $O(|n|)$ xuống $O(\log |n|)$.

<!-- thinking:end -->

Ý tưởng cốt lõi của thuật toán lũy thừa nhanh là phân rã số mũ $n$ thành tổng của các số $1$ trên một số bit nhị phân, sau đó biến lũy thừa bậc $n$ của $x$ thành tích của một số lũy thừa của $x$.

Độ phức tạp thời gian là $O(\log n)$, còn độ phức tạp không gian là $O(1)$. Ở đây, $n$ là số mũ.

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
