---
comments: true
difficulty: Easy
tags:
    - Bit Manipulation
    - Divide and Conquer
---

<!-- problem:start -->

# [191. Number of 1 Bits](https://leetcode.com/problems/number-of-1-bits)

[中文文档](/solution/0100-0199/0191.Number%20of%201%20Bits/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một số nguyên dương <code>n</code>, hãy viết một hàm trả về số lượng <span data-keyword="set-bit">bit được đặt</span> trong biểu diễn nhị phân của nó (còn được gọi là <a href="http://en.wikipedia.org/wiki/Hamming_weight" target="_blank">trọng số Hamming</a>).</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">n = 11</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">3</span></p>

<p><strong>Giải thích:</strong></p>

<p>Chuỗi nhị phân đầu vào <strong>1011</strong> có tổng cộng ba bit được đặt.</p>
</div>

<p><strong class="example">Ví dụ 2:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">n = 128</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">1</span></p>

<p><strong>Giải thích:</strong></p>

<p>Chuỗi nhị phân đầu vào <strong>10000000</strong> có tổng cộng một bit được đặt.</p>
</div>

<p><strong class="example">Ví dụ 3:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">n = 2147483645</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">30</span></p>

<p><strong>Giải thích:</strong></p>

<p>Chuỗi nhị phân đầu vào <strong>1111111111111111111111111111101</strong> có tổng cộng ba mươi bit được đặt.</p>
</div>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>
<strong>Câu hỏi mở rộng:</strong> Nếu hàm này được gọi nhiều lần, bạn sẽ tối ưu nó như thế nào?

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Đếm các bit được đặt. Việc kiểm tra mọi bit luôn xét đủ $32$ vị trí. $n\mathbin{\&}(n-1)$ xóa bit $1$ thấp nhất, nên vòng lặp chạy một lần cho mỗi bit được đặt.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def hammingWeight(self, n: int) -> int:
        ans = 0
        while n:
            n &= n - 1
            ans += 1
        return ans
```

#### Java

```java
public class Solution {
    // cần xử lý n như một giá trị không dấu
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            n &= n - 1;
            ++ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int hammingWeight(uint32_t n) {
        int ans = 0;
        while (n) {
            n &= n - 1;
            ++ans;
        }
        return ans;
    }
};
```

#### Go

```go
func hammingWeight(num uint32) int {
	ans := 0
	for num != 0 {
		num &= num - 1
		ans++
	}
	return ans
}
```

#### TypeScript

```ts
function hammingWeight(n: number): number {
    let ans: number = 0;
    while (n !== 0) {
        ans++;
        n &= n - 1;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn hammingWeight(n: u32) -> i32 {
        n.count_ones() as i32
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n - một số nguyên dương
 * @return {number}
 */
var hammingWeight = function (n) {
    let ans = 0;
    while (n) {
        n &= n - 1;
        ++ans;
    }
    return ans;
};
```

#### C

```c
int hammingWeight(uint32_t n) {
    int ans = 0;
    while (n) {
        n &= n - 1;
        ans++;
    }
    return ans;
}
```

#### Kotlin

```kotlin
class Solution {
    fun hammingWeight(n: Int): Int {
        var count = 0
        var num = n
        while (num != 0) {
            num = num and (num - 1)
            count++
        }
        return count
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 sử dụng $n\mathbin{\&}(n-1)$. $\textit{lowbit}=n\mathbin{\&}-n$ cô lập bit $1$ thấp nhất; trừ đi rồi lặp lại. Cùng một ý tưởng, đây là dạng được dùng trong cây Fenwick.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def hammingWeight(self, n: int) -> int:
        ans = 0
        while n:
            n -= n & -n
            ans += 1
        return ans
```

#### Java

```java
public class Solution {
    // cần xử lý n như một giá trị không dấu
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            n -= (n & -n);
            ++ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int hammingWeight(uint32_t n) {
        int ans = 0;
        while (n) {
            n -= (n & -n);
            ++ans;
        }
        return ans;
    }
};
```

#### Go

```go
func hammingWeight(num uint32) int {
	ans := 0
	for num != 0 {
		num -= (num & -num)
		ans++
	}
	return ans
}
```

#### TypeScript

```ts
function hammingWeight(n: number): number {
    let count = 0;
    while (n) {
        n -= n & -n;
        count++;
    }
    return count;
}
```

#### Rust

```rust
impl Solution {
    pub fn hammingWeight(mut n: u32) -> i32 {
        let mut res = 0;
        while n != 0 {
            n &= n - 1;
            res += 1;
        }
        res
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {number}
 */
var hammingWeight = function (n) {
    let count = 0;
    while (n) {
        n -= n & -n;
        count++;
    }
    return count;
};
```

#### Kotlin

```kotlin
class Solution {
    fun hammingWeight(n: Int): Int {
        var count = 0
        var num = n
        while (num != 0) {
            num -= num and (-num)
            count++
        }
        return count
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
