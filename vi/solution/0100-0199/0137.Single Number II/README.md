---
comments: true
difficulty: Medium
tags:
    - Bit Manipulation
    - Array
---

<!-- problem:start -->

# [137. Single Number II](https://leetcode.com/problems/single-number-ii)

[中文文档](/solution/0100-0199/0137.Single%20Number%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng số nguyên <code>nums</code>, trong đó <strong>mỗi phần tử xuất hiện ba lần</strong> ngoại trừ một phần tử xuất hiện <strong>chính xác một lần</strong>. <em>Hãy tìm phần tử xuất hiện một lần và trả về phần tử đó</em>.</p>

<p>Bạn phải&nbsp;triển khai một lời giải có độ phức tạp thời gian tuyến tính và chỉ sử dụng&nbsp;không gian phụ hằng số.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<pre><strong>Đầu vào:</strong> nums = [2,2,3,2]
<strong>Đầu ra:</strong> 3
</pre><p><strong class="example">Ví dụ 2:</strong></p>
<pre><strong>Đầu vào:</strong> nums = [0,1,0,1,0,1,99]
<strong>Đầu ra:</strong> 99
</pre>
<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li>Mỗi phần tử trong <code>nums</code> xuất hiện chính xác <strong>ba lần</strong>, ngoại trừ một phần tử xuất hiện <strong>một lần</strong>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Phép toán bit

<!-- thinking:start -->

> **Tư duy**
>
> Các số xuất hiện ba lần ngoại trừ một số; XOR không còn đủ vì $x\oplus x\oplus x=x$. Câu hỏi mở rộng vẫn yêu cầu không gian hằng số. Đếm các bit $1$ theo từng vị trí modulo $3$: các bit từ những bộ ba sẽ biến mất, phần dư chính là đáp án. Bit dấu được xử lý riêng để tránh tràn số.

<!-- thinking:end -->

Ta có thể liệt kê từng bit nhị phân $i$, và với mỗi bit nhị phân, tính tổng của tất cả các số trên bit đó. Nếu tổng các số trên bit đó chia hết cho 3, thì bit đó của số chỉ xuất hiện một lần là 0, ngược lại là 1.

Độ phức tạp thời gian là $O(n \times \log M)$, trong đó $n$ và $M$ lần lượt là độ dài mảng và miền giá trị của các phần tử trong mảng. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        ans = 0
        for i in range(32):
            cnt = sum(num >> i & 1 for num in nums)
            if cnt % 3:
                if i == 31:
                    ans -= 1 << i
                else:
                    ans |= 1 << i
        return ans
```

#### Java

```java
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int cnt = 0;
            for (int num : nums) {
                cnt += num >> i & 1;
            }
            cnt %= 3;
            ans |= cnt << i;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int singleNumber(vector<int>& nums) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int cnt = 0;
            for (int num : nums) {
                cnt += ((num >> i) & 1);
            }
            cnt %= 3;
            ans |= cnt << i;
        }
        return ans;
    }
};
```

#### Go

```go
func singleNumber(nums []int) int {
	ans := int32(0)
	for i := 0; i < 32; i++ {
		cnt := int32(0)
		for _, num := range nums {
			cnt += int32(num) >> i & 1
		}
		cnt %= 3
		ans |= cnt << i
	}
	return int(ans)
}
```

#### TypeScript

```ts
function singleNumber(nums: number[]): number {
    let ans = 0;
    for (let i = 0; i < 32; i++) {
        const count = nums.reduce((r, v) => r + ((v >> i) & 1), 0);
        ans |= (count % 3) << i;
    }
    return ans;
}
```

#### JavaScript

```js
function singleNumber(nums) {
    let ans = 0;
    for (let i = 0; i < 32; i++) {
        const count = nums.reduce((r, v) => r + ((v >> i) & 1), 0);
        ans |= (count % 3) << i;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn single_number(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        for i in 0..32 {
            let count = nums.iter().map(|v| (v >> i) & 1).sum::<i32>();
            ans |= count % 3 << i;
        }
        ans
    }
}
```

#### C

```c
int singleNumber(int* nums, int numsSize) {
    int ans = 0;
    for (int i = 0; i < 32; i++) {
        int count = 0;
        for (int j = 0; j < numsSize; j++) {
            if (nums[j] >> i & 1) {
                count++;
            }
        }
        ans |= (uint) (count % 3) << i;
    }
    return ans;
}
```

#### Swift

```swift
class Solution {
    func singleNumber(_ nums: [Int]) -> Int {
        var a = nums.sorted()
        var n = a.count
        for i in stride(from: 0, through: n - 2, by: 3) {
            if a[i] != a[i + 1] {
                return a[i]
            }
        }
        return a[n - 1]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Mạch số

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 quét lại mảng cho từng bit. Hai số nguyên $a,b$ lưu số lần xuất hiện của mỗi bit modulo $3$; một bảng chân trị cập nhật chúng khi từng số đi qua, vì vậy một lượt duyệt thực hiện cùng phép đếm.

<!-- thinking:end -->

Ta có thể sử dụng một phương pháp hiệu quả hơn, dùng các mạch số để mô phỏng phép toán theo từng bit ở trên.

Mỗi bit nhị phân của một số nguyên chỉ có thể biểu diễn 2 trạng thái, 0 hoặc 1. Tuy nhiên, ta cần biểu diễn tổng của bit thứ $i$ của tất cả các số nguyên đã duyệt cho đến hiện tại theo modulo 3. Vì vậy, ta có thể dùng hai số nguyên $a$ và $b$ để biểu diễn giá trị đó. Có ba trường hợp:

1. Bit thứ $i$ của số nguyên $a$ là 0 và bit thứ $i$ của số nguyên $b$ là 0, nghĩa là kết quả modulo 3 là 0;
2. Bit thứ $i$ của số nguyên $a$ là 0 và bit thứ $i$ của số nguyên $b$ là 1, nghĩa là kết quả modulo 3 là 1;
3. Bit thứ $i$ của số nguyên $a$ là 1 và bit thứ $i$ của số nguyên $b$ là 0, nghĩa là kết quả modulo 3 là 2.

Ta dùng số nguyên $c$ để biểu diễn số đang được đọc vào, và bảng chân trị như sau:

| $a_i$ | $b_i$ | $c_i$ | $a_i$ mới | $b_i$ mới |
| ----- | ----- | ----- | --------- | --------- |
| 0     | 0     | 0     | 0         | 0         |
| 0     | 0     | 1     | 0         | 1         |
| 0     | 1     | 0     | 0         | 1         |
| 0     | 1     | 1     | 1         | 0         |
| 1     | 0     | 0     | 1         | 0         |
| 1     | 0     | 1     | 0         | 0         |

Dựa trên bảng chân trị, ta có thể viết biểu thức logic:

$$
a_i = a_i' b_i c_i + a_i b_i' c_i'
$$

và:

$$
b_i = a_i' b_i' c_i + a_i' b_i c_i' = a_i' (b_i \oplus c_i)
$$

Kết quả cuối cùng là $b$, vì khi bit nhị phân của $b$ là 1, điều đó có nghĩa là số đó chỉ xuất hiện một lần.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài mảng. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        a = b = 0
        for c in nums:
            aa = (~a & b & c) | (a & ~b & ~c)
            bb = ~a & (b ^ c)
            a, b = aa, bb
        return b
```

#### Java

```java
class Solution {
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int c : nums) {
            int aa = (~a & b & c) | (a & ~b & ~c);
            int bb = ~a & (b ^ c);
            a = aa;
            b = bb;
        }
        return b;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int singleNumber(vector<int>& nums) {
        int a = 0, b = 0;
        for (int c : nums) {
            int aa = (~a & b & c) | (a & ~b & ~c);
            int bb = ~a & (b ^ c);
            a = aa;
            b = bb;
        }
        return b;
    }
};
```

#### Go

```go
func singleNumber(nums []int) int {
	a, b := 0, 0
	for _, c := range nums {
		aa := (^a & b & c) | (a & ^b & ^c)
		bb := ^a & (b ^ c)
		a, b = aa, bb
	}
	return b
}
```

#### TypeScript

```ts
function singleNumber(nums: number[]): number {
    let a = 0;
    let b = 0;
    for (const c of nums) {
        const aa = (~a & b & c) | (a & ~b & ~c);
        const bb = ~a & (b ^ c);
        a = aa;
        b = bb;
    }
    return b;
}
```

#### JavaScript

```js
function singleNumber(nums) {
    let a = 0;
    let b = 0;
    for (const c of nums) {
        const aa = (~a & b & c) | (a & ~b & ~c);
        const bb = ~a & (b ^ c);
        a = aa;
        b = bb;
    }
    return b;
}
```

#### Rust

```rust
impl Solution {
    pub fn single_number(nums: Vec<i32>) -> i32 {
        let mut a = 0;
        let mut b = 0;

        for c in nums {
            let aa = (!a & b & c) | (a & !b & !c);
            let bb = !a & (b ^ c);
            a = aa;
            b = bb;
        }

        return b;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 3: Tập hợp + Toán học

<!-- thinking:start -->

> **Tư duy**
>
> Nếu cho phép dùng thêm không gian, giá trị duy nhất là $(3\sum_{\mathrm{unique}}-\sum_{\mathrm{all}})/2$. Loại bỏ trùng lặp, tính tổng hai lần rồi chia. Cách này trực tiếp, nhưng cần $O(n)$ không gian.

<!-- thinking:end -->

<!-- tabs:start -->

#### TypeScript

```ts
function singleNumber(nums: number[]): number {
    const sumOfUnique = [...new Set(nums)].reduce((a, b) => a + b, 0);
    const sum = nums.reduce((a, b) => a + b, 0);
    return (sumOfUnique * 3 - sum) / 2;
}
```

#### JavaScript

```js
function singleNumber(nums) {
    const sumOfUnique = [...new Set(nums)].reduce((a, b) => a + b, 0);
    const sum = nums.reduce((a, b) => a + b, 0);
    return (sumOfUnique * 3 - sum) / 2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 4: Thao tác bit

<!-- thinking:start -->

> **Tư duy**
>
> Máy trạng thái ở Lời giải 2 được rút gọn thành hai mask: $\textit{ans}$ và $\textit{acc}$ lưu các bit đã thấy một lần và hai lần. Chúng luôn không giao nhau khi $x$ đi qua; $\textit{ans}$ là số đã xuất hiện một lần. Mã ngắn hơn.

<!-- thinking:end -->

<!-- tabs:start -->

#### TypeScript

```ts
function singleNumber(nums: number[]): number {
    let [ans, acc] = [0, 0];

    for (const x of nums) {
        ans ^= x & ~acc;
        acc ^= x & ~ans;
    }

    return ans;
}
```

#### JavaScript

```ts
function singleNumber(nums) {
    let [ans, acc] = [0, 0];

    for (const x of nums) {
        ans ^= x & ~acc;
        acc ^= x & ~ans;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
