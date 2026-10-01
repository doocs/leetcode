---
comments: true
difficulty: Medium
tags:
    - Math
---

<!-- problem:start -->

# [7. Reverse Integer](https://leetcode.com/problems/reverse-integer)

[中文文档](/solution/0000-0099/0007.Reverse%20Integer/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một số nguyên có dấu 32 bit <code>x</code>, hãy trả về <code>x</code><em> với các chữ số được đảo ngược</em>. Nếu việc đảo ngược <code>x</code> khiến giá trị nằm ngoài miền số nguyên có dấu 32 bit <code>[-2<sup>31</sup>, 2<sup>31</sup> - 1]</code>, hãy trả về <code>0</code>.</p>

<p><strong>Giả sử môi trường không cho phép bạn lưu trữ các số nguyên 64 bit (có dấu hoặc không dấu).</strong></p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> x = 123
<strong>Đầu ra:</strong> 321
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> x = -123
<strong>Đầu ra:</strong> -321
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> x = 120
<strong>Đầu ra:</strong> 21
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;= x &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Toán học

<!-- thinking:start -->

> **Tư duy**
>
> Đảo ngược bằng chuỗi là ý tưởng hiển nhiên, nhưng giả sử rằng môi trường không thể lưu số nguyên 64 bit. Chúng ta không thể tính bằng một kiểu lớn hơn rồi kiểm tra tràn số sau đó.
>
> Tách chữ số cuối rồi nối nó vào cuối là $\textit{ans} \leftarrow 10\cdot\textit{ans}+y$. Trước khi nhân với $10$, chúng ta phải biết liệu giá trị tiếp theo có vượt ra ngoài $[-2^{31}, 2^{31}-1]$ hay không; nếu có, hãy trả về $0$.
>
> Điều kiện kiểm tra đó quy về việc $\textit{ans}$ vẫn nằm trong $[\lfloor mi/10 \rfloor, \lfloor mx/10 \rfloor]$ hay không. Khi còn nằm trong miền, việc thêm một chữ số nữa là an toàn; khi nằm ngoài miền, nó đã bị tràn số.

<!-- thinking:end -->

Ta ký hiệu $mi$ và $mx$ lần lượt là $-2^{31}$ và $2^{31} - 1$, khi đó kết quả đảo ngược của $x$, $ans$, cần thỏa mãn $mi \le ans \le mx$.

Chúng ta có thể liên tục lấy phần dư của $x$ để nhận được chữ số cuối $y$ của $x$, rồi thêm $y$ vào cuối $ans$. Trước khi thêm $y$, chúng ta cần kiểm tra xem $ans$ có bị tràn số hay không. Nghĩa là, kiểm tra xem $ans \times 10 + y$ có nằm trong miền $[mi, mx]$ hay không.

Nếu $x \gt 0$, nó cần thỏa mãn $ans \times 10 + y \leq mx$, tức là $ans \times 10 + y \leq \left \lfloor \frac{mx}{10} \right \rfloor \times 10 + 7$. Biến đổi tương đương cho ta $(ans - \left \lfloor \frac{mx}{10} \right \rfloor) \times 10 \leq 7 - y$.

Tiếp theo, chúng ta thảo luận các điều kiện để bất đẳng thức đúng:

- Khi $ans \lt \left \lfloor \frac{mx}{10} \right \rfloor$, bất đẳng thức hiển nhiên đúng;
- Khi $ans = \left \lfloor \frac{mx}{10} \right \rfloor$, điều kiện cần và đủ để bất đẳng thức đúng là $y \leq 7$. Nếu $ans = \left \lfloor \frac{mx}{10} \right \rfloor$ và chúng ta vẫn có thể thêm chữ số, điều đó có nghĩa là số đang ở chữ số cao nhất, tức là $y$ không được vượt quá $2$, do đó bất đẳng thức chắc chắn đúng;
- Khi $ans \gt \left \lfloor \frac{mx}{10} \right \rfloor$, bất đẳng thức hiển nhiên không đúng.

Tóm lại, khi $x \gt 0$, điều kiện cần và đủ để bất đẳng thức đúng là $ans \leq \left \lfloor \frac{mx}{10} \right \rfloor$.

Tương tự, khi $x \lt 0$, điều kiện cần và đủ để bất đẳng thức đúng là $ans \geq \left \lfloor \frac{mi}{10} \right \rfloor$.

Do đó, chúng ta có thể kiểm tra xem $ans$ có bị tràn số hay không bằng cách kiểm tra xem $ans$ có nằm trong miền $[\left \lfloor \frac{mi}{10} \right \rfloor, \left \lfloor \frac{mx}{10} \right \rfloor]$ hay không. Nếu bị tràn số, hãy trả về $0$. Nếu không, thêm $y$ vào cuối $ans$, sau đó loại bỏ chữ số cuối của $x$, tức là $x \gets \left \lfloor \frac{x}{10} \right \rfloor$.

Độ phức tạp thời gian là $O(\log |x|)$, trong đó $|x|$ là giá trị tuyệt đối của $x$. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverse(self, x: int) -> int:
        ans = 0
        mi, mx = -(2**31), 2**31 - 1
        while x:
            if ans < mi // 10 + 1 or ans > mx // 10:
                return 0
            y = x % 10
            if x < 0 and y > 0:
                y -= 10
            ans = ans * 10 + y
            x = (x - y) // 10
        return ans
```

#### Java

```java
class Solution {
    public int reverse(int x) {
        int ans = 0;
        for (; x != 0; x /= 10) {
            if (ans < Integer.MIN_VALUE / 10 || ans > Integer.MAX_VALUE / 10) {
                return 0;
            }
            ans = ans * 10 + x % 10;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int reverse(int x) {
        int ans = 0;
        for (; x; x /= 10) {
            if (ans < INT_MIN / 10 || ans > INT_MAX / 10) {
                return 0;
            }
            ans = ans * 10 + x % 10;
        }
        return ans;
    }
};
```

#### Go

```go
func reverse(x int) (ans int) {
	for ; x != 0; x /= 10 {
		if ans < math.MinInt32/10 || ans > math.MaxInt32/10 {
			return 0
		}
		ans = ans*10 + x%10
	}
	return
}
```

#### Rust

```rust
impl Solution {
    pub fn reverse(mut x: i32) -> i32 {
        let is_minus = x < 0;
        match x
            .abs()
            .to_string()
            .chars()
            .rev()
            .collect::<String>()
            .parse::<i32>()
        {
            Ok(x) => x * (if is_minus { -1 } else { 1 }),
            Err(_) => 0,
        }
    }
}
```

#### JavaScript

```js
/**
 * @param {number} x
 * @return {number}
 */
var reverse = function (x) {
    const mi = -(2 ** 31);
    const mx = 2 ** 31 - 1;
    let ans = 0;
    for (; x != 0; x = ~~(x / 10)) {
        if (ans < ~~(mi / 10) || ans > ~~(mx / 10)) {
            return 0;
        }
        ans = ans * 10 + (x % 10);
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int Reverse(int x) {
        int ans = 0;
        for (; x != 0; x /= 10) {
            if (ans < int.MinValue / 10 || ans > int.MaxValue / 10) {
                return 0;
            }
            ans = ans * 10 + x % 10;
        }
        return ans;
    }
}
```

#### C

```c
int reverse(int x) {
    int ans = 0;
    for (; x != 0; x /= 10) {
        if (ans > INT_MAX / 10 || ans < INT_MIN / 10) {
            return 0;
        }
        ans = ans * 10 + x % 10;
    }
    return ans;
}
```

#### PHP

```php
class Solution {
    /**
     * @param int $x
     * @return int
     */

    function reverse($x) {
        $isNegative = $x < 0;
        $x = abs($x);

        $reversed = 0;

        while ($x > 0) {
            $reversed = $reversed * 10 + ($x % 10);
            $x = (int) ($x / 10);
        }

        if ($isNegative) {
            $reversed *= -1;
        }
        if ($reversed < -pow(2, 31) || $reversed > pow(2, 31) - 1) {
            return 0;
        }

        return $reversed;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
