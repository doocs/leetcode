---
comments: true
difficulty: Easy
tags:
    - Bit Manipulation
    - Divide and Conquer
---

<!-- problem:start -->

# [190. Reverse Bits](https://leetcode.com/problems/reverse-bits)

[中文文档](/solution/0100-0199/0190.Reverse%20Bits/README.md)

## Mô tả

<!-- description:start -->

<p>Đảo các bit của một số nguyên có dấu 32 bit cho trước.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">n = 43261596</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">964176192</span></p>

<p><strong>Giải thích:</strong></p>

<table>
	<tbody>
		<tr>
			<th>Số nguyên</th>
			<th>Nhị phân</th>
		</tr>
		<tr>
			<td>43261596</td>
			<td>00000010100101000001111010011100</td>
		</tr>
		<tr>
			<td>964176192</td>
			<td>00111001011110000010100101000000</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">Ví dụ 2:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">n = 2147483644</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">1073741822</span></p>

<p><strong>Giải thích:</strong></p>

<table>
	<tbody>
		<tr>
			<th>Số nguyên</th>
			<th>Nhị phân</th>
		</tr>
		<tr>
			<td>2147483644</td>
			<td>01111111111111111111111111111100</td>
		</tr>
		<tr>
			<td>1073741822</td>
			<td>00111111111111111111111111111110</td>
		</tr>
	</tbody>
</table>
</div>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 2<sup>31</sup> - 2</code></li>
	<li><code>n</code> là số chẵn.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng:</strong> Nếu hàm này được gọi nhiều lần, bạn sẽ tối ưu nó như thế nào?</p>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Thao tác bit

<!-- thinking:start -->

> **Tư duy**
>
> Đảo $32$ bit của một số nguyên không dấu. Lấy từng bit và ghi nó vào chỉ số đối xứng. Quét $32$ lần từ đầu thấp: lấy bit thấp nhất của $n$, ghi nó vào vị trí $31-i$, sau đó dịch phải $n$. Độ rộng là cố định.

<!-- thinking:end -->

Chúng ta có thể trích xuất từng bit của $n$ từ bit thấp nhất đến bit cao nhất, sau đó đặt nó vào vị trí tương ứng của $\textit{ans}$.

Ví dụ, với bit thứ $i$, chúng ta có thể trích xuất bit thứ $i$ của $n$ và đặt nó vào bit thứ $(31 - i)$ của $\textit{ans}$ bằng $(n \& 1) \ll (31 - i)$, sau đó dịch phải $n$ một bit.

Độ phức tạp thời gian là $O(\log n)$, và độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverseBits(self, n: int) -> int:
        ans = 0
        for i in range(32):
            ans |= (n & 1) << (31 - i)
            n >>= 1
        return ans
```

#### Java

```java
public class Solution {
    // cần xử lý n như một giá trị không dấu
    public int reverseBits(int n) {
        int ans = 0;
        for (int i = 0; i < 32 && n != 0; ++i) {
            ans |= (n & 1) << (31 - i);
            n >>>= 1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    uint32_t reverseBits(uint32_t n) {
        uint32_t ans = 0;
        for (int i = 0; i < 32 && n; ++i) {
            ans |= (n & 1) << (31 - i);
            n >>= 1;
        }
        return ans;
    }
};
```

#### Go

```go
func reverseBits(n uint32) (ans uint32) {
	for i := 0; i < 32; i++ {
		ans |= (n & 1) << (31 - i)
		n >>= 1
	}
	return
}
```

#### TypeScript

```ts
function reverseBits(n: number): number {
    let ans = 0;
    for (let i = 0; i < 32 && n; ++i) {
        ans |= (n & 1) << (31 - i);
        n >>= 1;
    }
    return ans >>> 0;
}
```

#### Rust

```rust
impl Solution {
    pub fn reverse_bits(mut n: u32) -> u32 {
        let mut ans = 0;
        for i in 0..32 {
            ans |= (n & 1) << (31 - i);
            n >>= 1;
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n - một số nguyên dương
 * @return {number} - một số nguyên dương
 */
var reverseBits = function (n) {
    let ans = 0;
    for (let i = 0; i < 32 && n; ++i) {
        ans |= (n & 1) << (31 - i);
        n >>= 1;
    }
    return ans >>> 0;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
