---
comments: true
difficulty: Medium
tags:
    - Array
    - Math
    - Two Pointers
---

<!-- problem:start -->

# [189. Rotate Array](https://leetcode.com/problems/rotate-array)

[中文文档](/solution/0100-0199/0189.Rotate%20Array/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng số nguyên <code>nums</code>, hãy xoay mảng sang phải <code>k</code> bước, trong đó <code>k</code> là số không âm.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1,2,3,4,5,6,7], k = 3
<strong>Đầu ra:</strong> [5,6,7,1,2,3,4]
<strong>Giải thích:</strong>
xoay 1 bước sang phải: [7,1,2,3,4,5,6]
xoay 2 bước sang phải: [6,7,1,2,3,4,5]
xoay 3 bước sang phải: [5,6,7,1,2,3,4]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [-1,-100,3,99], k = 2
<strong>Đầu ra:</strong> [3,99,-1,-100]
<strong>Giải thích:</strong>
xoay 1 bước sang phải: [99,-1,-100,3]
xoay 2 bước sang phải: [3,99,-1,-100]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng:</strong></p>

<ul>
	<li>Hãy cố gắng tìm ra nhiều cách giải nhất có thể. Có ít nhất <strong>ba</strong> cách khác nhau để giải bài toán này.</li>
	<li>Bạn có thể thực hiện việc này tại chỗ với không gian phụ <code>O(1)</code> không?</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Đảo ngược ba lần

<!-- thinking:start -->

> **Tư duy**
>
> Xoay phải $k$ bước. Sử dụng một mảng thứ hai sẽ cần không gian $O(n)$; $n\le 10^5$, trong khi câu hỏi mở rộng yêu cầu thực hiện tại chỗ. Xoay phải $k$ bước đưa $k$ phần tử cuối lên đầu. Đảo ngược toàn bộ mảng, sau đó đảo ngược $k$ phần tử đầu và $n-k$ phần tử cuối. Chỉ cần thực hiện các phép hoán đổi qua ba lần đảo ngược.

<!-- thinking:end -->

Chúng ta có thể giả sử độ dài của mảng là $n$ và tính số bước thực tế cần thực hiện bằng cách lấy phần dư của $k$ cho $n$, tức là $k \bmod n$.

Tiếp theo, chúng ta đảo ngược ba lần để nhận được kết quả cuối cùng:

1. Đảo ngược toàn bộ mảng.
2. Đảo ngược $k$ phần tử đầu tiên.
3. Đảo ngược $n - k$ phần tử cuối cùng.

Ví dụ, với mảng $[1, 2, 3, 4, 5, 6, 7]$, $k = 3$, $n = 7$, $k \bmod n = 3$.

1. Ở lần đảo ngược thứ nhất, đảo ngược toàn bộ mảng. Ta nhận được $[7, 6, 5, 4, 3, 2, 1]$.
2. Ở lần đảo ngược thứ hai, đảo ngược $k$ phần tử đầu tiên. Ta nhận được $[5, 6, 7, 4, 3, 2, 1]$.
3. Ở lần đảo ngược thứ ba, đảo ngược $n - k$ phần tử cuối cùng. Ta nhận được $[5, 6, 7, 1, 2, 3, 4]$, đây là kết quả cuối cùng.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của mảng. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rotate(self, nums: List[int], k: int) -> None:
        def reverse(i: int, j: int):
            while i < j:
                nums[i], nums[j] = nums[j], nums[i]
                i, j = i + 1, j - 1

        n = len(nums)
        k %= n
        reverse(0, n - 1)
        reverse(0, k - 1)
        reverse(k, n - 1)
```

#### Java

```java
class Solution {
    private int[] nums;

    public void rotate(int[] nums, int k) {
        this.nums = nums;
        int n = nums.length;
        k %= n;
        reverse(0, n - 1);
        reverse(0, k - 1);
        reverse(k, n - 1);
    }

    private void reverse(int i, int j) {
        for (; i < j; ++i, --j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    void rotate(vector<int>& nums, int k) {
        int n = nums.size();
        k %= n;
        reverse(nums.begin(), nums.end());
        reverse(nums.begin(), nums.begin() + k);
        reverse(nums.begin() + k, nums.end());
    }
};
```

#### Go

```go
func rotate(nums []int, k int) {
	n := len(nums)
	k %= n
	reverse := func(i, j int) {
		for ; i < j; i, j = i+1, j-1 {
			nums[i], nums[j] = nums[j], nums[i]
		}
	}
	reverse(0, n-1)
	reverse(0, k-1)
	reverse(k, n-1)
}
```

#### TypeScript

```ts
/**
 Không trả về gì, thay vào đó hãy sửa đổi nums tại chỗ.
 */
function rotate(nums: number[], k: number): void {
    const n: number = nums.length;
    k %= n;
    const reverse = (i: number, j: number): void => {
        for (; i < j; ++i, --j) {
            const t: number = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    };
    reverse(0, n - 1);
    reverse(0, k - 1);
    reverse(k, n - 1);
}
```

#### Rust

```rust
impl Solution {
    pub fn rotate(nums: &mut Vec<i32>, k: i32) {
        let n = nums.len();
        let k = (k as usize) % n;
        nums.reverse();
        nums[..k].reverse();
        nums[k..].reverse();
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {void} Không trả về gì, thay vào đó hãy sửa đổi nums tại chỗ.
 */
var rotate = function (nums, k) {
    const n = nums.length;
    k %= n;
    const reverse = (i, j) => {
        for (; i < j; ++i, --j) {
            [nums[i], nums[j]] = [nums[j], nums[i]];
        }
    };
    reverse(0, n - 1);
    reverse(0, k - 1);
    reverse(k, n - 1);
};
```

#### C#

```cs
public class Solution {
    private int[] nums;

    public void Rotate(int[] nums, int k) {
        this.nums = nums;
        int n = nums.Length;
        k %= n;
        reverse(0, n - 1);
        reverse(0, k - 1);
        reverse(k, n - 1);
    }

    private void reverse(int i, int j) {
        for (; i < j; ++i, --j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
