---
comments: true
difficulty: Medium
tags:
    - Array
    - Divide and Conquer
    - Dynamic Programming
---

<!-- problem:start -->

# [53. Maximum Subarray](https://leetcode.com/problems/maximum-subarray)

[中文文档](/solution/0000-0099/0053.Maximum%20Subarray/README.md)

## Mô tả

<!-- description:start -->

<p>Với một mảng số nguyên <code>nums</code>, hãy tìm <span data-keyword="subarray-nonempty">mảng con</span> có tổng lớn nhất và trả về <em>tổng của nó</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [-2,1,-3,4,-1,2,1,-5,4]
<strong>Đầu ra:</strong> 6
<strong>Giải thích:</strong> Mảng con [4,-1,2,1] có tổng lớn nhất là 6.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1]
<strong>Đầu ra:</strong> 1
<strong>Giải thích:</strong> Mảng con [1] có tổng lớn nhất là 1.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [5,4,-1,7,8]
<strong>Đầu ra:</strong> 23
<strong>Giải thích:</strong> Mảng con [5,4,-1,7,8] có tổng lớn nhất là 23.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng:</strong> Nếu bạn đã tìm ra lời giải <code>O(n)</code>, hãy thử lập trình một lời giải khác sử dụng phương pháp <strong>chia để trị</strong>, vốn tinh tế hơn.</p>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Quy hoạch động

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là liệt kê mọi cặp điểm đầu và cuối rồi tính tổng, trong $O(n^2)$ hoặc $O(n^3)$. Với $n \le 10^5$, cách này sẽ bị quá thời gian.
>
> Điểm lãng phí nằm ở việc tính lại các mảng con chồng lấn. Khi cố định điểm kết thúc ở $i$, lựa chọn điểm bắt đầu tốt nhất là tiếp tục đoạn trước (nếu tổng của nó dương) hoặc bắt đầu lại tại $i$.
>
> Do đó, đặt $f[i]$ là tổng lớn nhất của một mảng con kết thúc tại $i$. Giá trị này chỉ phụ thuộc vào $f[i-1]$, nên chỉ cần một biến cuộn; đáp án là giá trị lớn nhất trong suốt quá trình.

<!-- thinking:end -->

Ta định nghĩa $f[i]$ biểu diễn tổng lớn nhất của một mảng con liên tiếp kết thúc tại phần tử $\textit{nums}[i]$. Ban đầu, $f[0] = \textit{nums}[0]$. Đáp án cuối cùng cần tìm là $\max_{0 \leq i < n} f[i]$.

Xét $f[i]$ với $i \geq 1$. Phương trình chuyển trạng thái của nó là:

$$
f[i] = \max(f[i - 1] + \textit{nums}[i], \textit{nums}[i])
$$

Tức là:

$$
f[i] = \max(f[i - 1], 0) + \textit{nums}[i]
$$

Vì $f[i]$ chỉ liên quan đến $f[i - 1]$, ta có thể dùng một biến duy nhất $f$ để duy trì giá trị hiện tại của $f[i]$ và thực hiện chuyển trạng thái. Đáp án là $\max_{0 \leq i < n} f$.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của mảng $\textit{nums}$. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        ans = f = nums[0]
        for x in nums[1:]:
            f = max(f, 0) + x
            ans = max(ans, f)
        return ans
```

#### Java

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        for (int i = 1, f = nums[0]; i < nums.length; ++i) {
            f = Math.max(f, 0) + nums[i];
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
    int maxSubArray(vector<int>& nums) {
        int ans = nums[0], f = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            f = max(f, 0) + nums[i];
            ans = max(ans, f);
        }
        return ans;
    }
};
```

#### Go

```go
func maxSubArray(nums []int) int {
	ans, f := nums[0], nums[0]
	for _, x := range nums[1:] {
		f = max(f, 0) + x
		ans = max(ans, f)
	}
	return ans
}
```

#### TypeScript

```ts
function maxSubArray(nums: number[]): number {
    let [ans, f] = [nums[0], nums[0]];
    for (let i = 1; i < nums.length; ++i) {
        f = Math.max(f, 0) + nums[i];
        ans = Math.max(ans, f);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_sub_array(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut ans = nums[0];
        let mut f = nums[0];
        for i in 1..n {
            f = f.max(0) + nums[i];
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
var maxSubArray = function (nums) {
    let [ans, f] = [nums[0], nums[0]];
    for (let i = 1; i < nums.length; ++i) {
        f = Math.max(f, 0) + nums[i];
        ans = Math.max(ans, f);
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int MaxSubArray(int[] nums) {
        int ans = nums[0], f = nums[0];
        for (int i = 1; i < nums.Length; ++i) {
            f = Math.Max(f, 0) + nums[i];
            ans = Math.Max(ans, f);
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Chia để trị

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 đã có độ phức tạp thời gian $O(n)$ và độ phức tạp không gian $O(1)$. Câu hỏi mở rộng yêu cầu chia để trị, nhưng công thức truy hồi của quy hoạch động tuyến tính theo điểm kết thúc bên phải và không chia thành các nửa rời nhau.
>
> Điều còn thiếu là một nhận xét khác: mảng con có tổng lớn nhất nằm hoàn toàn ở bên trái, hoàn toàn ở bên phải hoặc cắt qua điểm giữa. Trường hợp cắt qua điểm giữa có nghĩa là hậu tố lớn nhất bên trái cộng với tiền tố lớn nhất bên phải. Độ phức tạp thời gian trở thành $O(n \log n)$; ta dùng cách này cho câu hỏi mở rộng, không phải để tăng tốc.

<!-- thinking:end -->

Chia mảng tại điểm giữa. Đáp án là giá trị lớn nhất trong ba trường hợp: nửa trái, nửa phải và mảng con tốt nhất cắt qua điểm giữa (hậu tố lớn nhất của bên trái cộng với tiền tố lớn nhất của bên phải).

Độ phức tạp thời gian là $O(n \log n)$ và độ phức tạp không gian là $O(\log n)$, trong đó $n$ là độ dài của mảng.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        def crossMaxSub(nums, left, mid, right):
            lsum = rsum = 0
            lmx = rmx = -inf
            for i in range(mid, left - 1, -1):
                lsum += nums[i]
                lmx = max(lmx, lsum)
            for i in range(mid + 1, right + 1):
                rsum += nums[i]
                rmx = max(rmx, rsum)
            return lmx + rmx

        def maxSub(nums, left, right):
            if left == right:
                return nums[left]
            mid = (left + right) >> 1
            lsum = maxSub(nums, left, mid)
            rsum = maxSub(nums, mid + 1, right)
            csum = crossMaxSub(nums, left, mid, right)
            return max(lsum, rsum, csum)

        left, right = 0, len(nums) - 1
        return maxSub(nums, left, right)
```

#### Java

```java
class Solution {
    public int maxSubArray(int[] nums) {
        return maxSub(nums, 0, nums.length - 1);
    }

    private int maxSub(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = (left + right) >>> 1;
        int lsum = maxSub(nums, left, mid);
        int rsum = maxSub(nums, mid + 1, right);
        return Math.max(Math.max(lsum, rsum), crossMaxSub(nums, left, mid, right));
    }

    private int crossMaxSub(int[] nums, int left, int mid, int right) {
        int lsum = 0, rsum = 0;
        int lmx = Integer.MIN_VALUE, rmx = Integer.MIN_VALUE;
        for (int i = mid; i >= left; --i) {
            lsum += nums[i];
            lmx = Math.max(lmx, lsum);
        }
        for (int i = mid + 1; i <= right; ++i) {
            rsum += nums[i];
            rmx = Math.max(rmx, rsum);
        }
        return lmx + rmx;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
