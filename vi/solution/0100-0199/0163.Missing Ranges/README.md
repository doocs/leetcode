---
comments: true
difficulty: Easy
tags:
    - Array
---

<!-- problem:start -->

# [163. Missing Ranges 🔒](https://leetcode.com/problems/missing-ranges)

[中文文档](/solution/0100-0199/0163.Missing%20Ranges/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cung cấp một đoạn bao gồm cả hai đầu mút <code>[lower, upper]</code> và một mảng số nguyên <code>nums</code> đã được <strong>sắp xếp, không trùng lặp</strong>, trong đó mọi phần tử đều nằm trong đoạn bao gồm cả hai đầu mút.</p>

<p>Một số <code>x</code> được coi là <strong>thiếu</strong> nếu <code>x</code> nằm trong đoạn <code>[lower, upper]</code> và <code>x</code> không nằm trong <code>nums</code>.</p>

<p>Trả về <em>danh sách các đoạn <strong>ngắn nhất và đã sắp xếp</strong> bao phủ <b>chính xác tất cả các số bị thiếu</b></em>. Nghĩa là không có phần tử nào của <code>nums</code> được chứa trong bất kỳ đoạn nào, và mỗi số bị thiếu đều được bao phủ bởi một trong các đoạn đó.</p>

<p>&nbsp;</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [0,1,3,50,75], lower = 0, upper = 99
<strong>Đầu ra:</strong> [[2,2],[4,49],[51,74],[76,99]]
<strong>Giải thích:</strong> Các đoạn là:
[2,2]
[4,49]
[51,74]
[76,99]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [-1], lower = -1, upper = -1
<strong>Đầu ra:</strong> []
<strong>Giải thích:</strong> Không có đoạn bị thiếu nào vì không có số bị thiếu.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>-10<sup>9</sup> &lt;= lower &lt;= upper &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= nums.length &lt;= 100</code></li>
	<li><code>lower &lt;= nums[i] &lt;= upper</code></li>
	<li>Tất cả các giá trị của <code>nums</code> đều <strong>duy nhất</strong>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Mô phỏng

<!-- thinking:start -->

> **Tư duy**
>
> Liệt kê các đoạn trong $[\textit{lower},\textit{upper}]$ mà $nums$ không bao phủ. Mảng đã được sắp xếp, các phần tử phân biệt và có độ dài nhiều nhất là $100$, vì vậy chúng ta mô phỏng các khoảng trống: trước giá trị đầu tiên, giữa các phần tử lân cận và sau giá trị cuối cùng. Hiệu lớn hơn $1$ tương ứng với một đoạn bị thiếu.

<!-- thinking:end -->

Chúng ta có thể mô phỏng trực tiếp bài toán theo đúng yêu cầu.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của mảng $nums$. Bỏ qua phần không gian được sử dụng cho đáp án, độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMissingRanges(
        self, nums: List[int], lower: int, upper: int
    ) -> List[List[int]]:
        n = len(nums)
        if n == 0:
            return [[lower, upper]]
        ans = []
        if nums[0] > lower:
            ans.append([lower, nums[0] - 1])
        for a, b in pairwise(nums):
            if b - a > 1:
                ans.append([a + 1, b - 1])
        if nums[-1] < upper:
            ans.append([nums[-1] + 1, upper])
        return ans
```

#### Java

```java
class Solution {
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        int n = nums.length;
        if (n == 0) {
            return List.of(List.of(lower, upper));
        }
        List<List<Integer>> ans = new ArrayList<>();
        if (nums[0] > lower) {
            ans.add(List.of(lower, nums[0] - 1));
        }
        for (int i = 1; i < n; ++i) {
            if (nums[i] - nums[i - 1] > 1) {
                ans.add(List.of(nums[i - 1] + 1, nums[i] - 1));
            }
        }
        if (nums[n - 1] < upper) {
            ans.add(List.of(nums[n - 1] + 1, upper));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> findMissingRanges(vector<int>& nums, int lower, int upper) {
        int n = nums.size();
        if (n == 0) {
            return {{lower, upper}};
        }
        vector<vector<int>> ans;
        if (nums[0] > lower) {
            ans.push_back({lower, nums[0] - 1});
        }
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] - nums[i - 1] > 1) {
                ans.push_back({nums[i - 1] + 1, nums[i] - 1});
            }
        }
        if (nums[n - 1] < upper) {
            ans.push_back({nums[n - 1] + 1, upper});
        }
        return ans;
    }
};
```

#### Go

```go
func findMissingRanges(nums []int, lower int, upper int) (ans [][]int) {
	n := len(nums)
	if n == 0 {
		return [][]int{{lower, upper}}
	}
	if nums[0] > lower {
		ans = append(ans, []int{lower, nums[0] - 1})
	}
	for i, b := range nums[1:] {
		if a := nums[i]; b-a > 1 {
			ans = append(ans, []int{a + 1, b - 1})
		}
	}
	if nums[n-1] < upper {
		ans = append(ans, []int{nums[n-1] + 1, upper})
	}
	return
}
```

#### TypeScript

```ts
function findMissingRanges(nums: number[], lower: number, upper: number): number[][] {
    const n = nums.length;
    if (n === 0) {
        return [[lower, upper]];
    }
    const ans: number[][] = [];
    if (nums[0] > lower) {
        ans.push([lower, nums[0] - 1]);
    }
    for (let i = 1; i < n; ++i) {
        if (nums[i] - nums[i - 1] > 1) {
            ans.push([nums[i - 1] + 1, nums[i] - 1]);
        }
    }
    if (nums[n - 1] < upper) {
        ans.push([nums[n - 1] + 1, upper]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
