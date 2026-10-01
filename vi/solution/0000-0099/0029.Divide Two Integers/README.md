---
comments: true
difficulty: Medium
tags:
    - Bit Manipulation
    - Math
---

<!-- problem:start -->

# [29. Divide Two Integers](https://leetcode.com/problems/divide-two-integers)

[中文文档](/solution/0000-0099/0029.Divide%20Two%20Integers/README.md)

## Mô tả

<!-- description:start -->

<p>Cho hai số nguyên <code>dividend</code> và <code>divisor</code>, hãy chia hai số nguyên mà <strong>không</strong> sử dụng phép nhân, phép chia và toán tử mod.</p>

<p>Phép chia số nguyên phải cắt bỏ về phía 0, nghĩa là loại bỏ phần thập phân. Ví dụ, <code>8.345</code> sẽ được cắt bỏ thành <code>8</code>, còn <code>-2.7335</code> sẽ được cắt bỏ thành <code>-2</code>.</p>

<p>Trả về <em><strong>thương</strong> sau khi chia </em><code>dividend</code><em> cho </em><code>divisor</code>.</p>

<p><strong>Lưu ý: </strong>Giả sử chúng ta đang làm việc trong một môi trường chỉ có thể lưu trữ các số nguyên trong phạm vi số nguyên có dấu <strong>32-bit</strong>: <code>[&minus;2<sup>31</sup>, 2<sup>31</sup> &minus; 1]</code>. Đối với bài toán này, nếu thương <strong>lớn hơn nghiêm ngặt</strong> <code>2<sup>31</sup> - 1</code>, hãy trả về <code>2<sup>31</sup> - 1</code>, còn nếu thương <strong>nhỏ hơn nghiêm ngặt</strong> <code>-2<sup>31</sup></code>, hãy trả về <code>-2<sup>31</sup></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> dividend = 10, divisor = 3
<strong>Đầu ra:</strong> 3
<strong>Giải thích:</strong> 10/3 = 3.33333.. được cắt bỏ thành 3.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> dividend = 7, divisor = -3
<strong>Đầu ra:</strong> -2
<strong>Giải thích:</strong> 7/-3 = -2.33333.. được cắt bỏ thành -2.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;= dividend, divisor &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>divisor != 0</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Mô phỏng + Lũy thừa nhanh

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là phép trừ lặp lại: mỗi lần số bị chia vẫn lớn hơn hoặc bằng số chia, ta tăng thương thêm $1$. $|a|$ có thể xấp xỉ $2^{31}$, nên cách này sẽ quá thời gian, đồng thời chúng ta không thể sử dụng phép nhân, phép chia hoặc mod.
>
> Điểm nghẽn là mỗi bước chỉ loại bỏ $1\times b$. Nếu có thể loại bỏ $2^k \times b$ cùng lúc, thương sẽ tăng theo dạng nhị phân.
>
> Đó cũng là ý tưởng của lũy thừa nhanh: trong khi phần dư vẫn đủ lớn, dịch trái $b$ để nhân đôi nó, trừ đi khối lớn nhất như vậy, rồi lặp lại với phần còn lại.
>
> Ghi nhận dấu riêng và tính toán trên các số âm để việc đổi dấu $a= -2^{31}$ không gây tràn số. Xử lý riêng trường hợp $b=1$ và $a= -2^{31},\, b=-1$, giới hạn kết quả ở $2^{31}-1$.

<!-- thinking:end -->

Phép chia về bản chất là phép trừ. Bài toán yêu cầu chúng ta tính kết quả số nguyên sau khi chia hai số, thực chất là tính xem có bao nhiêu lần số chia và một số nhỏ hơn số chia hợp thành số bị chia. Tuy nhiên, mỗi vòng lặp chỉ thực hiện được một phép trừ, quá kém hiệu quả và sẽ dẫn đến hết thời gian. Có thể tối ưu điều này bằng ý tưởng lũy thừa nhanh.

Cần lưu ý rằng vì bài toán nêu rõ chỉ được sử dụng tối đa các số nguyên có dấu 32-bit, số chia và số bị chia cần được chuyển thành số âm để tính toán. Vì chuyển thành số dương có thể gây tràn số, chẳng hạn khi số bị chia là `INT32_MIN`, nó sẽ lớn hơn `INT32_MAX` sau khi chuyển thành số dương.

Giả sử số bị chia là $a$ và số chia là $b$, độ phức tạp thời gian là $O(\log a \times \log b)$, còn độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def divide(self, a: int, b: int) -> int:
        if b == 1:
            return a
        if a == -(2**31) and b == -1:
            return 2**31 - 1
        sign = (a > 0 and b > 0) or (a < 0 and b < 0)
        a = -a if a > 0 else a
        b = -b if b > 0 else b
        ans = 0
        while a <= b:
            x = b
            cnt = 1
            while x >= (-(2**30)) and a <= (x << 1):
                x <<= 1
                cnt <<= 1
            a -= x
            ans += cnt
        return ans if sign else -ans
```

#### Java

```java
class Solution {
    public int divide(int a, int b) {
        if (b == 1) {
            return a;
        }
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        boolean sign = (a > 0 && b > 0) || (a < 0 && b < 0);
        a = a > 0 ? -a : a;
        b = b > 0 ? -b : b;
        int ans = 0;
        while (a <= b) {
            int x = b;
            int cnt = 1;
            while (x >= (Integer.MIN_VALUE >> 1) && a <= (x << 1)) {
                x <<= 1;
                cnt <<= 1;
            }
            ans += cnt;
            a -= x;
        }
        return sign ? ans : -ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int divide(int a, int b) {
        if (b == 1) {
            return a;
        }
        if (a == INT_MIN && b == -1) {
            return INT_MAX;
        }
        bool sign = (a > 0 && b > 0) || (a < 0 && b < 0);
        a = a > 0 ? -a : a;
        b = b > 0 ? -b : b;
        int ans = 0;
        while (a <= b) {
            int x = b;
            int cnt = 1;
            while (x >= (INT_MIN >> 1) && a <= (x << 1)) {
                x <<= 1;
                cnt <<= 1;
            }
            ans += cnt;
            a -= x;
        }
        return sign ? ans : -ans;
    }
};
```

#### Go

```go
func divide(a int, b int) int {
	if b == 1 {
		return a
	}
	if a == math.MinInt32 && b == -1 {
		return math.MaxInt32
	}

	sign := (a > 0 && b > 0) || (a < 0 && b < 0)
	if a > 0 {
		a = -a
	}
	if b > 0 {
		b = -b
	}
	ans := 0

	for a <= b {
		x := b
		cnt := 1
		for x >= (math.MinInt32>>1) && a <= (x<<1) {
			x <<= 1
			cnt <<= 1
		}
		ans += cnt
		a -= x
	}

	if sign {
		return ans
	}
	return -ans
}
```

#### TypeScript

```ts
function divide(a: number, b: number): number {
    if (b === 1) {
        return a;
    }
    if (a === -(2 ** 31) && b === -1) {
        return 2 ** 31 - 1;
    }

    const sign: boolean = (a > 0 && b > 0) || (a < 0 && b < 0);
    a = a > 0 ? -a : a;
    b = b > 0 ? -b : b;
    let ans: number = 0;

    while (a <= b) {
        let x: number = b;
        let cnt: number = 1;

        while (x >= -(2 ** 30) && a <= x << 1) {
            x <<= 1;
            cnt <<= 1;
        }

        ans += cnt;
        a -= x;
    }

    return sign ? ans : -ans;
}
```

#### C#

```cs
public class Solution {
    public int Divide(int a, int b) {
        if (b == 1) {
            return a;
        }
        if (a == int.MinValue && b == -1) {
            return int.MaxValue;
        }
        bool sign = (a > 0 && b > 0) || (a < 0 && b < 0);
        a = a > 0 ? -a : a;
        b = b > 0 ? -b : b;
        int ans = 0;
        while (a <= b) {
            int x = b;
            int cnt = 1;
            while (x >= (int.MinValue >> 1) && a <= (x << 1)) {
                x <<= 1;
                cnt <<= 1;
            }
            ans += cnt;
            a -= x;
        }
        return sign ? ans : -ans;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param integer $a
     * @param integer $b
     * @return integer
     */

    function divide($a, $b) {
        if ($b == 0) {
            throw new Exception('Can not divide by 0');
        } elseif ($a == 0) {
            return 0;
        }
        if ($a == -2147483648 && $b == -1) {
            return 2147483647;
        }
        $sign = $a < 0 != $b < 0;

        $a = abs($a);
        $b = abs($b);
        $ans = 0;
        while ($a >= $b) {
            $x = $b;
            $cnt = 1;
            while ($a >= $x << 1) {
                $x <<= 1;
                $cnt <<= 1;
            }
            $a -= $x;
            $ans += $cnt;
        }

        return $sign ? -$ans : $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
