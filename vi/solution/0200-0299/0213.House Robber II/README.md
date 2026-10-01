---
comments: true
difficulty: Medium
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [213. House Robber II](https://leetcode.com/problems/house-robber-ii)

[中文文档](/solution/0200-0299/0213.House%20Robber%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn là một tên trộm chuyên nghiệp đang lên kế hoạch cướp các ngôi nhà dọc theo một con phố. Mỗi ngôi nhà cất giữ một khoản tiền nhất định. Tất cả các ngôi nhà ở đây <strong>được xếp thành một vòng tròn.</strong> Điều đó có nghĩa là ngôi nhà đầu tiên là hàng xóm của ngôi nhà cuối cùng. Đồng thời, các ngôi nhà liền kề có một hệ thống an ninh được kết nối với nhau, và&nbsp;<b>hệ thống sẽ tự động báo cảnh sát nếu hai ngôi nhà liền kề bị đột nhập trong cùng một đêm</b>.</p>

<p>Cho một mảng số nguyên <code>nums</code> biểu diễn số tiền của mỗi ngôi nhà, hãy trả về <em>số tiền lớn nhất bạn có thể cướp tối nay <strong>mà không báo động cho cảnh sát</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [2,3,2]
<strong>Đầu ra:</strong> 3
<strong>Giải thích:</strong> Bạn không thể cướp ngôi nhà 1 (số tiền = 2) rồi cướp ngôi nhà 3 (số tiền = 2), vì chúng là hai ngôi nhà liền kề.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1,2,3,1]
<strong>Đầu ra:</strong> 4
<strong>Giải thích:</strong> Cướp ngôi nhà 1 (số tiền = 1) rồi cướp ngôi nhà 3 (số tiền = 3).
Tổng số tiền có thể cướp = 1 + 3 = 4.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1,2,3]
<strong>Đầu ra:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Quy hoạch động

<!-- thinking:start -->

> **Tư duy**
>
> Các ngôi nhà tạo thành một vòng tròn, nên không thể đồng thời cướp ngôi nhà đầu tiên và ngôi nhà cuối cùng, và công thức truy hồi tuyến tính không áp dụng trực tiếp được.
>
> Chúng ta chia thành hai trường hợp tuyến tính: bỏ ngôi nhà đầu tiên hoặc bỏ ngôi nhà cuối cùng, rồi chọn kết quả tốt hơn trong hai trường hợp. Nếu chỉ có một ngôi nhà, trả về giá trị của ngôi nhà đó.

<!-- thinking:end -->

Cách sắp xếp theo vòng tròn có nghĩa là nhiều nhất một trong ngôi nhà đầu tiên và ngôi nhà cuối cùng có thể được chọn để cướp, vì vậy bài toán cướp nhà theo vòng tròn này có thể được quy về hai bài toán cướp các ngôi nhà trên một hàng.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của mảng. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rob(self, nums: List[int]) -> int:
        def _rob(nums):
            f = g = 0
            for x in nums:
                f, g = max(f, g), f + x
            return max(f, g)

        if len(nums) == 1:
            return nums[0]
        return max(_rob(nums[1:]), _rob(nums[:-1]))
```

#### Java

```java
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        return Math.max(rob(nums, 0, n - 2), rob(nums, 1, n - 1));
    }

    private int rob(int[] nums, int l, int r) {
        int f = 0, g = 0;
        for (; l <= r; ++l) {
            int ff = Math.max(f, g);
            g = f + nums[l];
            f = ff;
        }
        return Math.max(f, g);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int rob(vector<int>& nums) {
        int n = nums.size();
        if (n == 1) {
            return nums[0];
        }
        return max(robRange(nums, 0, n - 2), robRange(nums, 1, n - 1));
    }

    int robRange(vector<int>& nums, int l, int r) {
        int f = 0, g = 0;
        for (; l <= r; ++l) {
            int ff = max(f, g);
            g = f + nums[l];
            f = ff;
        }
        return max(f, g);
    }
};
```

#### Go

```go
func rob(nums []int) int {
	n := len(nums)
	if n == 1 {
		return nums[0]
	}
	return max(robRange(nums, 0, n-2), robRange(nums, 1, n-1))
}

func robRange(nums []int, l, r int) int {
	f, g := 0, 0
	for _, x := range nums[l : r+1] {
		f, g = max(f, g), f+x
	}
	return max(f, g)
}
```

#### TypeScript

```ts
function rob(nums: number[]): number {
    const n = nums.length;
    if (n === 1) {
        return nums[0];
    }
    const robRange = (l: number, r: number): number => {
        let [f, g] = [0, 0];
        for (; l <= r; ++l) {
            [f, g] = [Math.max(f, g), f + nums[l]];
        }
        return Math.max(f, g);
    };
    return Math.max(robRange(0, n - 2), robRange(1, n - 1));
}
```

#### Rust

```rust
impl Solution {
    pub fn rob(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        if n == 1 {
            return nums[0];
        }
        let rob_range = |l, r| {
            let mut f = [0, 0];
            for i in l..r {
                f = [f[0].max(f[1]), f[0] + nums[i]];
            }
            f[0].max(f[1])
        };
        rob_range(0, n - 1).max(rob_range(1, n))
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
