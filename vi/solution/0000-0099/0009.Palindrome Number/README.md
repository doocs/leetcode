---
comments: true
difficulty: Easy
tags:
    - Math
---

<!-- problem:start -->

# [9. Palindrome Number](https://leetcode.com/problems/palindrome-number)

[中文文档](/solution/0000-0099/0009.Palindrome%20Number/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một số nguyên <code>x</code>, hãy trả về <code>true</code> nếu <code>x</code> là một <span data-keyword="palindrome-integer"><strong>số đối xứng</strong></span>, và <code>false</code> nếu ngược lại.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> x = 121
<strong>Đầu ra:</strong> true
<strong>Giải thích:</strong> 121 được đọc là 121 từ trái sang phải và từ phải sang trái.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> x = -121
<strong>Đầu ra:</strong> false
<strong>Giải thích:</strong> Từ trái sang phải, nó được đọc là -121. Từ phải sang trái, nó trở thành 121-. Vì vậy, nó không phải là số đối xứng.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> x = 10
<strong>Đầu ra:</strong> false
<strong>Giải thích:</strong> Đọc từ phải sang trái là 01. Vì vậy, nó không phải là số đối xứng.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>-2<sup>31</sup>&nbsp;&lt;= x &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
</ul>

<p>&nbsp;</p>
<strong>Câu hỏi mở rộng:</strong> Bạn có thể giải bài toán mà không chuyển số nguyên thành chuỗi không?

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Đảo ngược một nửa số

<!-- thinking:start -->

> **Tư duy**
>
> Chuyển đổi thành một chuỗi rồi so sánh với chuỗi đảo ngược là ý tưởng hiển nhiên; câu hỏi mở rộng yêu cầu chúng ta không làm vậy. Đảo ngược toàn bộ $x$ rồi so sánh cũng có thể gây tràn số nguyên 32-bit.
>
> Chúng ta chỉ cần xử lý một nửa: liên tục nối chữ số cuối của $x$ vào $y$ cho đến khi $y$ không còn nhỏ hơn phần tiền tố còn lại. Một số âm không thể là số đối xứng; một số khác 0 kết thúc bằng $0$ sẽ làm mất số 0 đó khi đảo ngược, vì vậy những số đó cũng không phải là số đối xứng.
>
> Vòng lặp dừng khi hai nửa có cùng độ dài. Với số có số chữ số chẵn, chúng ta so sánh $x$ với $y$; với số có số chữ số lẻ, chữ số ở giữa nằm trong $y$, vì vậy chúng ta so sánh $x$ với $y/10$.

<!-- thinking:end -->

Trước hết, chúng ta xác định các trường hợp đặc biệt:

- Nếu $x < 0$, thì $x$ không phải là số đối xứng, trả về `false` ngay;
- Nếu $x > 0$ và chữ số cuối của $x$ là $0$, thì $x$ không phải là số đối xứng, trả về `false` ngay;
- Nếu chữ số cuối của $x$ không phải là $0$, thì $x$ có thể là số đối xứng, tiếp tục các bước sau.

Chúng ta đảo ngược nửa sau của $x$ rồi so sánh với nửa đầu. Nếu chúng bằng nhau, thì $x$ là số đối xứng; nếu không, $x$ không phải là số đối xứng.

Ví dụ, với $x = 1221$, chúng ta có thể đảo ngược nửa sau từ "21" thành "12" rồi so sánh với nửa đầu "12". Vì chúng bằng nhau, chúng ta biết rằng $x$ là số đối xứng.

Hãy xem cách đảo ngược nửa sau.

Với số $1221$, nếu thực hiện $1221 \bmod 10$, chúng ta sẽ nhận được chữ số cuối $1$. Để lấy chữ số áp chót, trước tiên chúng ta có thể loại bỏ chữ số cuối khỏi $1221$ bằng cách chia cho $10$, $1221 / 10 = 122$, sau đó lấy phần dư của kết quả trước đó khi chia cho $10$, $122 \bmod 10 = 2$, để lấy chữ số áp chót.

Nếu tiếp tục quá trình này, chúng ta sẽ nhận được nhiều chữ số được đảo ngược hơn.

Bằng cách liên tục nhân biến $y$ với 10 rồi cộng chữ số cuối vào, chúng ta có thể thu được các chữ số theo thứ tự ngược lại.

Trong phần triển khai bằng code, chúng ta có thể liên tục "lấy ra" chữ số cuối của $x$ rồi "thêm" nó vào cuối $y$, lặp cho đến khi $y \ge x$. Tại thời điểm này, nếu $x = y$ hoặc $x = y / 10$, thì $x$ là số đối xứng.

Độ phức tạp thời gian là $O(\log_{10}(n))$, trong đó $n$ là $x$. Ở mỗi vòng lặp, chúng ta chia đầu vào cho $10$, vì vậy độ phức tạp thời gian là $O(\log_{10}(n))$. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPalindrome(self, x: int) -> bool:
        if x < 0 or (x and x % 10 == 0):
            return False
        y = 0
        while y < x:
            y = y * 10 + x % 10
            x //= 10
        return x in (y, y // 10)
```

#### Java

```java
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x > 0 && x % 10 == 0)) {
            return false;
        }
        int y = 0;
        for (; y < x; x /= 10) {
            y = y * 10 + x % 10;
        }
        return x == y || x == y / 10;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isPalindrome(int x) {
        if (x < 0 || (x && x % 10 == 0)) {
            return false;
        }
        int y = 0;
        for (; y < x; x /= 10) {
            y = y * 10 + x % 10;
        }
        return x == y || x == y / 10;
    }
};
```

#### Go

```go
func isPalindrome(x int) bool {
	if x < 0 || (x > 0 && x%10 == 0) {
		return false
	}
	y := 0
	for ; y < x; x /= 10 {
		y = y*10 + x%10
	}
	return x == y || x == y/10
}
```

#### TypeScript

```ts
function isPalindrome(x: number): boolean {
    if (x < 0 || (x > 0 && x % 10 === 0)) {
        return false;
    }
    let y = 0;
    for (; y < x; x = ~~(x / 10)) {
        y = y * 10 + (x % 10);
    }
    return x === y || x === ~~(y / 10);
}
```

#### Rust

```rust
impl Solution {
    pub fn is_palindrome(mut x: i32) -> bool {
        if x < 0 || (x != 0 && x % 10 == 0) {
            return false;
        }
        let mut y = 0;
        while x > y {
            y = y * 10 + x % 10;
            x /= 10;
        }
        x == y || x == y / 10
    }
}
```

#### JavaScript

```js
/**
 * @param {number} x
 * @return {boolean}
 */
var isPalindrome = function (x) {
    if (x < 0 || (x > 0 && x % 10 === 0)) {
        return false;
    }
    let y = 0;
    for (; y < x; x = ~~(x / 10)) {
        y = y * 10 + (x % 10);
    }
    return x === y || x === ~~(y / 10);
};
```

#### C#

```cs
public class Solution {
    public bool IsPalindrome(int x) {
        if (x < 0 || (x > 0 && x % 10 == 0)) {
            return false;
        }
        int y = 0;
        for (; y < x; x /= 10) {
            y = y * 10 + x % 10;
        }
        return x == y || x == y / 10;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer $x
     * @return Boolean
     */
    function isPalindrome($x) {
        if ($x < 0 || ($x && $x % 10 == 0)) {
            return false;
        }
        $y = 0;
        while ($x > $y) {
            $y = $y * 10 + ($x % 10);
            $x = (int) ($x / 10);
        }
        return $x == $y || $x == (int) ($y / 10);
    }
}
```

#### C

```c
bool isPalindrome(int x) {
    if (x < 0 || (x != 0 && x % 10 == 0)) {
        return false;
    }

    int y = 0;
    while (y < x) {
        y = y * 10 + x % 10;
        x /= 10;
    }

    return (x == y || x == y / 10);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
