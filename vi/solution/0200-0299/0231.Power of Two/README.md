---
comments: true
difficulty: Easy
tags:
    - Bit Manipulation
    - Recursion
    - Math
---

<!-- problem:start -->

# [231. Power of Two](https://leetcode.com/problems/power-of-two)

[中文文档](/solution/0200-0299/0231.Power%20of%20Two/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một số nguyên <code>n</code>, hãy trả về <em><code>true</code> nếu đó là lũy thừa của hai. Nếu không, trả về <code>false</code></em>.</p>

<p>Một số nguyên <code>n</code> là lũy thừa của hai nếu tồn tại một số nguyên <code>x</code> sao cho <code>n == 2<sup>x</sup></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 1
<strong>Đầu ra:</strong> true
<strong>Giải thích: </strong>2<sup>0</sup> = 1
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 16
<strong>Đầu ra:</strong> true
<strong>Giải thích: </strong>2<sup>4</sup> = 16
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 3
<strong>Đầu ra:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>
<strong>Câu hỏi mở rộng:</strong> Bạn có thể giải bài toán mà không dùng vòng lặp/đệ quy không?

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Thao tác bit

<!-- thinking:start -->

> **Tư duy**
>
> Phép chia lặp cho $2$ có thể giải quyết bài toán, nhưng chỉ cần kiểm tra một bit là đủ. Lũy thừa của hai có đúng một bit $1$, tức là $n>0$ và $n\mathrel{\&}(n-1)=0$.

<!-- thinking:end -->

Theo các tính chất của thao tác bit, thực hiện $\texttt{n\&(n-1)}$ có thể loại bỏ bit $1$ cuối cùng trong dạng nhị phân của $n$. Do đó, nếu $n > 0$ và $\texttt{n\&(n-1)}$ cho kết quả là $0$, thì $n$ là lũy thừa của $2$.

Độ phức tạp thời gian là $O(1)$ và độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPowerOfTwo(self, n: int) -> bool:
        return n > 0 and (n & (n - 1)) == 0
```

#### Java

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
};
```

#### Go

```go
func isPowerOfTwo(n int) bool {
	return n > 0 && (n&(n-1)) == 0
}
```

#### TypeScript

```ts
function isPowerOfTwo(n: number): boolean {
    return n > 0 && (n & (n - 1)) === 0;
}
```

#### Rust

```rust
impl Solution {
    pub fn is_power_of_two(n: i32) -> bool {
        n > 0 && (n & (n - 1)) == 0
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {boolean}
 */
var isPowerOfTwo = function (n) {
    return n > 0 && (n & (n - 1)) == 0;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Lowbit

<!-- thinking:start -->

> **Tư duy**
>
> Xóa bit 1 thấp nhất tương đương với việc lấy $\mathrm{lowbit}$. Nếu $n>0$ và $n=n\mathrel{\&}(-n)$, thì bit đơn lẻ đó chính là toàn bộ số.

<!-- thinking:end -->

Theo định nghĩa của $\text{lowbit}$, ta biết rằng $\text{lowbit}(x) = x \& (-x)$, phép toán này có thể lấy số thập phân được biểu diễn bởi bit $1$ cuối cùng của $n$. Do đó, nếu $n > 0$ và $\text{lowbit}(n)$ bằng $n$, thì $n$ là lũy thừa của $2$.

Độ phức tạp thời gian là $O(1)$ và độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPowerOfTwo(self, n: int) -> bool:
        return n > 0 and n == n & (-n)
```

#### Java

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && n == (n & (-n));
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isPowerOfTwo(int n) {
        return n > 0 && n == (n & (-n));
    }
};
```

#### Go

```go
func isPowerOfTwo(n int) bool {
	return n > 0 && n == (n&(-n))
}
```

#### TypeScript

```ts
function isPowerOfTwo(n: number): boolean {
    return n > 0 && n === (n & -n);
}
```

#### Rust

```rust
impl Solution {
    pub fn is_power_of_two(n: i32) -> bool {
        n > 0 && n == (n & (-n))
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {boolean}
 */
var isPowerOfTwo = function (n) {
    return n > 0 && n === (n & -n);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
