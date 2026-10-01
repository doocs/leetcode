---
comments: true
difficulty: Medium
tags:
    - Math
---

<!-- problem:start -->

# [172. Factorial Trailing Zeroes](https://leetcode.com/problems/factorial-trailing-zeroes)

[中文文档](/solution/0100-0199/0172.Factorial%20Trailing%20Zeroes/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một số nguyên <code>n</code>, hãy trả về <em>số lượng chữ số 0 ở cuối </em><code>n!</code>.</p>

<p>Lưu ý rằng <code>n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 3
<strong>Đầu ra:</strong> 0
<strong>Giải thích:</strong> 3! = 6, không có chữ số 0 ở cuối.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 5
<strong>Đầu ra:</strong> 1
<strong>Giải thích:</strong> 5! = 120, có một chữ số 0 ở cuối.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 0
<strong>Đầu ra:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng:</strong> Bạn có thể viết một lời giải có độ phức tạp thời gian logarit không?</p>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Toán học

<!-- thinking:start -->

> **Tư duy**
>
> Các chữ số 0 ở cuối của $n!$ đến từ các thừa số $10=2\times 5$. Luôn có nhiều thừa số 2 hơn thừa số 5, vì vậy số lượng cần tìm là số lượng thừa số $5$ trong $[1,n]$. Việc tính $n!$ gây tràn số ngay cả khi $n\le 10^4$. Câu hỏi mở rộng yêu cầu độ phức tạp logarit: liên tục thay $n$ bằng $\lfloor n/5\rfloor$ để cộng thêm đóng góp của $5,5^2,5^3,\ldots$.

<!-- thinking:end -->

Bài toán thực chất đang hỏi có bao nhiêu thừa số $5$ trong $[1,n]$.

Hãy lấy $130$ làm ví dụ để phân tích:

1. Chia cho $5$ lần thứ nhất, nhận được $26$, cho biết có $26$ số chứa thừa số $5$;
2. Chia cho $5$ lần thứ hai, nhận được $5$, cho biết có $5$ số chứa thừa số $5^2$;
3. Chia cho $5$ lần thứ ba, nhận được $1$, cho biết có $1$ số chứa thừa số $5^3$;
4. Cộng lại để nhận được số lượng của tất cả các thừa số $5$ trong $[1,n]$.

Độ phức tạp thời gian là $O(\log n)$, và độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def trailingZeroes(self, n: int) -> int:
        ans = 0
        while n:
            n //= 5
            ans += n
        return ans
```

#### Java

```java
class Solution {
    public int trailingZeroes(int n) {
        int ans = 0;
        while (n > 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int trailingZeroes(int n) {
        int ans = 0;
        while (n) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
};
```

#### Go

```go
func trailingZeroes(n int) int {
	ans := 0
	for n > 0 {
		n /= 5
		ans += n
	}
	return ans
}
```

#### TypeScript

```ts
function trailingZeroes(n: number): number {
    let ans = 0;
    while (n > 0) {
        n = Math.floor(n / 5);
        ans += n;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
