---
comments: true
difficulty: Easy
tags:
    - Bit Manipulation
    - Array
---

<!-- problem:start -->

# [136. Single Number](https://leetcode.com/problems/single-number)

[中文文档](/solution/0100-0199/0136.Single%20Number/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng số nguyên <code>nums</code> <strong>không rỗng</strong>, mọi phần tử đều xuất hiện <em>hai lần</em> ngoại trừ một phần tử. Hãy tìm phần tử xuất hiện một lần đó.</p>

<p>Bạn phải triển khai một lời giải có độ phức tạp thời gian tuyến tính và chỉ sử dụng không gian bổ sung hằng số.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">nums = [2,2,1]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">1</span></p>
</div>

<p><strong class="example">Ví dụ 2:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">nums = [4,1,2,1,2]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">4</span></p>
</div>

<p><strong class="example">Ví dụ 3:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">nums = [1]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">1</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-3 * 10<sup>4</sup> &lt;= nums[i] &lt;= 3 * 10<sup>4</sup></code></li>
	<li>Mỗi phần tử trong mảng xuất hiện hai lần ngoại trừ một phần tử chỉ xuất hiện một lần.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Thao tác bit

<!-- thinking:start -->

> **Tư duy**
>
> Mỗi số xuất hiện hai lần ngoại trừ một số; chúng ta cần thời gian tuyến tính và không gian hằng số. Bảng tần suất sử dụng không gian $O(n)$. Phép XOR có $x\oplus x=0$, $x\oplus 0=x$, và có tính giao hoán, nên các cặp sẽ triệt tiêu và phần tử còn lại là số duy nhất.

<!-- thinking:end -->

Phép XOR có các tính chất sau:

- Bất kỳ số nào XOR với 0 vẫn là số ban đầu, tức là $x \oplus 0 = x$;
- Bất kỳ số nào XOR với chính nó đều bằng 0, tức là $x \oplus x = 0$;

Thực hiện phép XOR trên tất cả các phần tử trong mảng sẽ cho ra số chỉ xuất hiện một lần.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của mảng. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        return reduce(xor, nums)
```

#### Java

```java
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int v : nums) {
            ans ^= v;
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
        for (int v : nums) {
            ans ^= v;
        }
        return ans;
    }
};
```

#### Go

```go
func singleNumber(nums []int) (ans int) {
	for _, v := range nums {
		ans ^= v
	}
	return
}
```

#### TypeScript

```ts
function singleNumber(nums: number[]): number {
    return nums.reduce((r, v) => r ^ v);
}
```

#### Rust

```rust
impl Solution {
    pub fn single_number(nums: Vec<i32>) -> i32 {
        nums.into_iter().reduce(|r, v| r ^ v).unwrap()
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var singleNumber = function (nums) {
    return nums.reduce((a, b) => a ^ b);
};
```

#### C#

```cs
public class Solution {
    public int SingleNumber(int[] nums) {
        return nums.Aggregate(0, (a, b) => a ^ b);
    }
}
```

#### C

```c
int singleNumber(int* nums, int numsSize) {
    int ans = 0;
    for (int i = 0; i < numsSize; i++) {
        ans ^= nums[i];
    }
    return ans;
}
```

#### Swift

```swift
class Solution {
    func singleNumber(_ nums: [Int]) -> Int {
        return nums.reduce(0, ^)
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
> Lời giải 1 vốn đã là phép gộp XOR. Biến thể này sử dụng phép reduction của ngôn ngữ để thực hiện cùng một thao tác.

<!-- thinking:end -->

<!-- tabs:start -->

#### Java

```java
class Solution {
    public int singleNumber(int[] nums) {
        return Arrays.stream(nums).reduce(0, (a, b) -> a ^ b);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
