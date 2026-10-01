---
comments: true
difficulty: Easy
tags:
    - Math
    - Binary Search
    - Newton's Method
---

<!-- problem:start -->

# [69. Sqrt(x)](https://leetcode.com/problems/sqrtx)

[中文文档](/solution/0000-0099/0069.Sqrt%28x%29/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một số nguyên không âm <code>x</code>, hãy trả về <em>căn bậc hai của </em><code>x</code><em> được làm tròn xuống đến số nguyên gần nhất</em>. Số nguyên được trả về cũng phải <strong>không âm</strong>.</p>

<p><strong>Không được sử dụng</strong> bất kỳ hàm hoặc toán tử lũy thừa tích hợp sẵn nào.</p>

<ul>
	<li>Ví dụ, không sử dụng <code>pow(x, 0.5)</code> trong c++ hoặc <code>x ** 0.5</code> trong python.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> x = 4
<strong>Đầu ra:</strong> 2
<strong>Giải thích:</strong> Căn bậc hai của 4 là 2, vì vậy chúng ta trả về 2.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> x = 8
<strong>Đầu ra:</strong> 2
<strong>Giải thích:</strong> Căn bậc hai của 8 là 2.82842..., và vì chúng ta làm tròn xuống đến số nguyên gần nhất, 2 được trả về.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>0 &lt;= x &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tìm kiếm nhị phân

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là duyệt $i$ từ $0$ đến $x$ và giữ lại giá trị $i$ lớn nhất sao cho $i^2 \le x$. $x$ có thể bằng $2^{31}-1$, vì vậy việc duyệt tuyến tính quá chậm, $i^2$ dễ bị tràn số, và `pow` bị cấm.
>
> Đáp án có tính đơn điệu: nếu $mid^2 \le x$, một ứng viên lớn hơn vẫn có thể phù hợp; nếu không, chúng ta phải tìm về bên trái. Vì vậy, chúng ta tìm kiếm nhị phân trên $[0, x]$.
>
> So sánh bằng $mid > x / mid$ để tránh tràn số. Dùng mid phía trên và thu hẹp về phía giá trị khả thi lớn nhất; đầu mút trái là căn bậc hai nguyên.

<!-- thinking:end -->

Chúng ta xác định biên trái của phép tìm kiếm nhị phân là $l = 0$ và biên phải là $r = x$, sau đó tìm căn bậc hai trong phạm vi $[l, r]$.

Ở mỗi bước tìm kiếm, chúng ta tìm giá trị giữa $mid = (l + r + 1) / 2$. Nếu $mid > x / mid$, điều đó có nghĩa là căn bậc hai nằm trong phạm vi $[l, mid - 1]$, vì vậy chúng ta đặt $r = mid - 1$. Ngược lại, căn bậc hai nằm trong phạm vi $[mid, r]$, vì vậy chúng ta đặt $l = mid$.

Sau khi phép tìm kiếm kết thúc, chúng ta trả về $l$.

Độ phức tạp thời gian là $O(\log x)$, còn độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def mySqrt(self, x: int) -> int:
        l, r = 0, x
        while l < r:
            mid = (l + r + 1) >> 1
            if mid > x // mid:
                r = mid - 1
            else:
                l = mid
        return l
```

#### Java

```java
class Solution {
    public int mySqrt(int x) {
        int l = 0, r = x;
        while (l < r) {
            int mid = (l + r + 1) >>> 1;
            if (mid > x / mid) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int mySqrt(int x) {
        int l = 0, r = x;
        while (l < r) {
            int mid = (l + r + 1ll) >> 1;
            if (mid > x / mid) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return l;
    }
};
```

#### Go

```go
func mySqrt(x int) int {
	return sort.Search(x+1, func(i int) bool { return i*i > x }) - 1
}
```

#### Rust

```rust
impl Solution {
    pub fn my_sqrt(x: i32) -> i32 {
        let mut l = 0;
        let mut r = x;

        while l < r {
            let mid = (l + r + 1) / 2;

            if mid > x / mid {
                r = mid - 1;
            } else {
                l = mid;
            }
        }

        l
    }
}
```

#### JavaScript

```js
/**
 * @param {number} x
 * @return {number}
 */
var mySqrt = function (x) {
    let [l, r] = [0, x];
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (mid > x / mid) {
            r = mid - 1;
        } else {
            l = mid;
        }
    }
    return l;
};
```

#### C#

```cs
public class Solution {
    public int MySqrt(int x) {
        int l = 0, r = x;
        while (l < r) {
            int mid = (l + r + 1) >>> 1;
            if (mid > x / mid) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return l;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
