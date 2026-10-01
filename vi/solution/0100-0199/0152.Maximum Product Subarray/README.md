---
comments: true
difficulty: Medium
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [152. Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray)

[中文文档](/solution/0100-0199/0152.Maximum%20Product%20Subarray/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng số nguyên <code>nums</code>, hãy tìm một <span data-keyword="subarray-nonempty">mảng con</span> có tích lớn nhất và trả về <em>tích đó</em>.</p>

<p>Các bộ kiểm thử được tạo sao cho đáp án vừa với số nguyên <strong>32-bit</strong>.</p>

<p><strong>Lưu ý</strong> rằng tích của một mảng chỉ có một phần tử là giá trị của phần tử đó.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [2,3,-2,4]
<strong>Đầu ra:</strong> 6
<strong>Giải thích:</strong> [2,3] có tích lớn nhất là 6.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [-2,0,-1]
<strong>Đầu ra:</strong> 0
<strong>Giải thích:</strong> Kết quả không thể là 2, vì [-2,-1] không phải là một mảng con.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
	<li>Tích của mọi mảng con của <code>nums</code> được <strong>đảm bảo</strong> vừa với một số nguyên <strong>32-bit</strong>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Kadane hoạt động với tổng; tích sẽ đổi dấu khi xuất hiện số âm, còn số 0 đặt lại đoạn đang xét. $n\le 2\times 10^4$. Duy trì tích lớn nhất và nhỏ nhất kết thúc tại đây; giá trị mới có thể nhân với một trong hai tích hoặc bắt đầu một đoạn mới. Đáp án là giá trị lớn nhất của max đã thấy.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        ans = f = g = nums[0]
        for x in nums[1:]:
            ff, gg = f, g
            f = max(x, ff * x, gg * x)
            g = min(x, ff * x, gg * x)
            ans = max(ans, f)
        return ans
```

#### Java

```java
class Solution {
    public int maxProduct(int[] nums) {
        int f = nums[0], g = nums[0], ans = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int ff = f, gg = g;
            f = Math.max(nums[i], Math.max(ff * nums[i], gg * nums[i]));
            g = Math.min(nums[i], Math.min(ff * nums[i], gg * nums[i]));
            ans = Math.max(ans, f);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxProduct(vector<int>& nums) {
        int f = nums[0], g = nums[0], ans = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            int ff = f, gg = g;
            f = max({nums[i], ff * nums[i], gg * nums[i]});
            g = min({nums[i], ff * nums[i], gg * nums[i]});
            ans = max(ans, f);
        }
        return ans;
    }
};
```

#### Go

```go
func maxProduct(nums []int) int {
	f, g, ans := nums[0], nums[0], nums[0]
	for _, x := range nums[1:] {
		ff, gg := f, g
		f = max(x, max(ff*x, gg*x))
		g = min(x, min(ff*x, gg*x))
		ans = max(ans, f)
	}
	return ans
}
```

#### TypeScript

```ts
function maxProduct(nums: number[]): number {
    let [f, g, ans] = [nums[0], nums[0], nums[0]];
    for (let i = 1; i < nums.length; ++i) {
        const [ff, gg] = [f, g];
        f = Math.max(nums[i], ff * nums[i], gg * nums[i]);
        g = Math.min(nums[i], ff * nums[i], gg * nums[i]);
        ans = Math.max(ans, f);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_product(nums: Vec<i32>) -> i32 {
        let mut f = nums[0];
        let mut g = nums[0];
        let mut ans = nums[0];
        for &x in nums.iter().skip(1) {
            let (ff, gg) = (f, g);
            f = x.max(x * ff).max(x * gg);
            g = x.min(x * ff).min(x * gg);
            ans = ans.max(f);
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var maxProduct = function (nums) {
    let [f, g, ans] = [nums[0], nums[0], nums[0]];
    for (let i = 1; i < nums.length; ++i) {
        const [ff, gg] = [f, g];
        f = Math.max(nums[i], ff * nums[i], gg * nums[i]);
        g = Math.min(nums[i], ff * nums[i], gg * nums[i]);
        ans = Math.max(ans, f);
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int MaxProduct(int[] nums) {
        int f = nums[0], g = nums[0], ans = nums[0];
        for (int i = 1; i < nums.Length; ++i) {
            int ff = f, gg = g;
            f = Math.Max(nums[i], Math.Max(ff * nums[i], gg * nums[i]));
            g = Math.Min(nums[i], Math.Min(ff * nums[i], gg * nums[i]));
            ans = Math.Max(ans, f);
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
