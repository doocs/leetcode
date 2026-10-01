---
comments: true
difficulty: Easy
tags:
    - Array
    - Math
---

<!-- problem:start -->

# [66. Plus One](https://leetcode.com/problems/plus-one)

[中文文档](/solution/0000-0099/0066.Plus%20One/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cung cấp một <strong>số nguyên lớn</strong> được biểu diễn dưới dạng một mảng số nguyên <code>digits</code>, trong đó mỗi <code>digits[i]</code> là chữ số <code>i<sup>th</sup></code> của số nguyên. Các chữ số được sắp xếp từ chữ số có ý nghĩa lớn nhất đến chữ số có ý nghĩa nhỏ nhất theo thứ tự từ trái sang phải. Số nguyên lớn không chứa bất kỳ <code>0</code>&#39;s nào ở đầu.</p>

<p>Tăng số nguyên lớn lên một đơn vị và trả về <em>mảng chữ số thu được</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> digits = [1,2,3]
<strong>Đầu ra:</strong> [1,2,4]
<strong>Giải thích:</strong> Mảng biểu diễn số nguyên 123.
Tăng một đơn vị cho 123 ta được 123 + 1 = 124.
Do đó, kết quả phải là [1,2,4].
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> digits = [4,3,2,1]
<strong>Đầu ra:</strong> [4,3,2,2]
<strong>Giải thích:</strong> Mảng biểu diễn số nguyên 4321.
Tăng một đơn vị cho 4321 ta được 4321 + 1 = 4322.
Do đó, kết quả phải là [4,3,2,2].
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> digits = [9]
<strong>Đầu ra:</strong> [1,0]
<strong>Giải thích:</strong> Mảng biểu diễn số nguyên 9.
Tăng một đơn vị cho 9 ta được 9 + 1 = 10.
Do đó, kết quả phải là [1,0].
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= digits.length &lt;= 100</code></li>
	<li><code>0 &lt;= digits[i] &lt;= 9</code></li>
	<li><code>digits</code> không chứa bất kỳ <code>0</code>&#39;s nào ở đầu.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Mô phỏng

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là nối các chữ số thành một số nguyên rồi cộng một. $n \le 100$, vì vậy giá trị này sẽ tràn các kiểu số nguyên thông thường.
>
> Nút thắt là phép nhớ có thể chạy xuyên suốt đến chữ số có ý nghĩa lớn nhất. Cộng một không bao giờ tạo ra phép nhớ nào khác ngoài $1$: duyệt từ chữ số có ý nghĩa nhỏ nhất; dừng ở chữ số đầu tiên không phải $9$; chỉ mảng toàn các chữ số $9$ mới cần thêm một chữ số $1$ ở đầu.
>
> Vì vậy, chúng ta mô phỏng từ phải sang trái và trả về ngay khi một chữ số không tạo phép nhớ, mà không chuyển toàn bộ số sang một dạng biểu diễn khác.

<!-- thinking:end -->

Chúng ta bắt đầu duyệt từ phần tử cuối cùng của mảng, cộng một vào phần tử hiện tại, rồi lấy phần dư khi chia cho $10$. Nếu kết quả khác $0$, điều đó có nghĩa là không có phép nhớ cho phần tử hiện tại, và chúng ta có thể trả về trực tiếp mảng. Nếu không, phần tử hiện tại là $0$ và cần được truyền sang phần tử trước đó. Chúng ta tiếp tục duyệt phần tử trước đó và lặp lại thao tác trên. Nếu vẫn chưa trả về sau khi duyệt hết mảng, điều đó có nghĩa là tất cả phần tử trong mảng đều là $0$, và chúng ta cần chèn một $1$ vào đầu mảng.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của mảng. Bỏ qua phần không gian tiêu tốn cho đáp án, độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def plusOne(self, digits: List[int]) -> List[int]:
        n = len(digits)
        for i in range(n - 1, -1, -1):
            digits[i] += 1
            digits[i] %= 10
            if digits[i] != 0:
                return digits
        return [1] + digits
```

#### Java

```java
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; --i) {
            ++digits[i];
            digits[i] %= 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        digits = new int[n + 1];
        digits[0] = 1;
        return digits;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> plusOne(vector<int>& digits) {
        for (int i = digits.size() - 1; i >= 0; --i) {
            ++digits[i];
            digits[i] %= 10;
            if (digits[i] != 0) return digits;
        }
        digits.insert(digits.begin(), 1);
        return digits;
    }
};
```

#### Go

```go
func plusOne(digits []int) []int {
	n := len(digits)
	for i := n - 1; i >= 0; i-- {
		digits[i]++
		digits[i] %= 10
		if digits[i] != 0 {
			return digits
		}
	}
	return append([]int{1}, digits...)
}
```

#### TypeScript

```ts
function plusOne(digits: number[]): number[] {
    const n = digits.length;
    for (let i = n - 1; i >= 0; i--) {
        if (10 > ++digits[i]) {
            return digits;
        }
        digits[i] %= 10;
    }
    return [1, ...digits];
}
```

#### Rust

```rust
impl Solution {
    pub fn plus_one(mut digits: Vec<i32>) -> Vec<i32> {
        let n = digits.len();
        for i in (0..n).rev() {
            digits[i] += 1;
            if 10 > digits[i] {
                return digits;
            }
            digits[i] %= 10;
        }
        digits.insert(0, 1);
        digits
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} digits
 * @return {number[]}
 */
var plusOne = function (digits) {
    for (let i = digits.length - 1; i >= 0; --i) {
        ++digits[i];
        digits[i] %= 10;
        if (digits[i] != 0) {
            return digits;
        }
    }
    return [1, ...digits];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
