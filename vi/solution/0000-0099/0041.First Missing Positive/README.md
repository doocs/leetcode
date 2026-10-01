---
comments: true
difficulty: Hard
tags:
    - Array
    - Hash Table
---

<!-- problem:start -->

# [41. First Missing Positive](https://leetcode.com/problems/first-missing-positive)

[中文文档](/solution/0000-0099/0041.First%20Missing%20Positive/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng số nguyên chưa được sắp xếp <code>nums</code>. Hãy trả về <em>số nguyên dương nhỏ nhất</em> <em>không xuất hiện</em> trong <code>nums</code>.</p>

<p>Bạn phải triển khai một thuật toán chạy trong thời gian <code>O(n)</code> và sử dụng không gian phụ <code>O(1)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1,2,0]
<strong>Đầu ra:</strong> 3
<strong>Giải thích:</strong> Các số trong khoảng [1,2] đều nằm trong mảng.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [3,4,-1,1]
<strong>Đầu ra:</strong> 2
<strong>Giải thích:</strong> 1 nằm trong mảng nhưng 2 bị thiếu.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [7,8,9,11,12]
<strong>Đầu ra:</strong> 1
<strong>Giải thích:</strong> Số nguyên dương nhỏ nhất 1 bị thiếu.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Hoán đổi tại chỗ

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là dùng một hash set để lưu các số đã gặp, sau đó lần lượt kiểm tra $1, 2, 3, \ldots$. Cách này đúng và có thời gian $O(n)$, nhưng cần thêm không gian $O(n)$. Đề bài yêu cầu thời gian $O(n)$ và không gian phụ hằng số; với $n \le 10^5$, hash set không đáp ứng được giới hạn không gian.
>
> Điểm nghẽn là phải ghi nhận sự xuất hiện của các số $1..n$ bằng bộ nhớ bổ sung. Số dương bị thiếu phải nằm trong $[1, n+1]$, nên chúng ta chỉ cần quan tâm đến $1..n$ — chính các chỉ số của mảng có thể đóng vai trò như bảng đó.
>
> Hoán đổi giá trị $x$ đến chỉ số $x-1$ khi $x \in [1, n]$. Hoán đổi tại chỗ biến mảng thành một bảng băm; một lượt duyệt nữa sẽ tìm ra vị trí trống đầu tiên.

<!-- thinking:end -->

Chúng ta giả sử độ dài của mảng $nums$ là $n$, khi đó số nguyên dương nhỏ nhất phải nằm trong khoảng $[1, .., n + 1]$. Chúng ta có thể duyệt qua mảng và hoán đổi mỗi số $x$ vào vị trí đúng của nó, tức là vị trí $x - 1$. Nếu $x$ không nằm trong khoảng $[1, n + 1]$, chúng ta có thể bỏ qua nó.

Sau lượt duyệt đó, chúng ta duyệt qua mảng một lần nữa. Nếu $i+1$ không bằng $nums[i]$, thì $i+1$ là số nguyên dương nhỏ nhất mà chúng ta đang tìm.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của mảng. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def firstMissingPositive(self, nums: List[int]) -> int:
        n = len(nums)
        for i in range(n):
            while 1 <= nums[i] <= n and nums[i] != nums[nums[i] - 1]:
                j = nums[i] - 1
                nums[i], nums[j] = nums[j], nums[i]
        for i in range(n):
            if nums[i] != i + 1:
                return i + 1
        return n + 1
```

#### Java

```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int firstMissingPositive(vector<int>& nums) {
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                swap(nums[i], nums[nums[i] - 1]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }
};
```

#### Go

```go
func firstMissingPositive(nums []int) int {
	n := len(nums)
	for i := range nums {
		for 0 < nums[i] && nums[i] <= n && nums[i] != nums[nums[i]-1] {
			nums[i], nums[nums[i]-1] = nums[nums[i]-1], nums[i]
		}
	}
	for i, x := range nums {
		if x != i+1 {
			return i + 1
		}
	}
	return n + 1
}
```

#### TypeScript

```ts
function firstMissingPositive(nums: number[]): number {
    const n = nums.length;
    for (let i = 0; i < n; i++) {
        while (nums[i] >= 1 && nums[i] <= n && nums[i] !== nums[nums[i] - 1]) {
            const j = nums[i] - 1;
            [nums[i], nums[j]] = [nums[j], nums[i]];
        }
    }
    for (let i = 0; i < n; i++) {
        if (nums[i] !== i + 1) {
            return i + 1;
        }
    }
    return n + 1;
}
```

#### Rust

```rust
impl Solution {
    pub fn first_missing_positive(mut nums: Vec<i32>) -> i32 {
        let n = nums.len();
        for i in 0..n {
            while nums[i] > 0 && nums[i] <= n as i32 && nums[i] != nums[nums[i] as usize - 1] {
                let j = nums[i] as usize - 1;
                nums.swap(i, j);
            }
        }
        for i in 0..n {
            if nums[i] != (i + 1) as i32 {
                return (i + 1) as i32;
            }
        }
        return (n + 1) as i32;
    }
}
```

#### C#

```cs
public class Solution {
    public int FirstMissingPositive(int[] nums) {
        int n = nums.Length;
        for (int i = 0; i < n; ++i) {
            while (nums[i] >= 1 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                Swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (i + 1 != nums[i]) {
                return i + 1;
            }
        }
        return n + 1;
    }

    private void Swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

#### C

```c
int firstMissingPositive(int* nums, int numsSize) {
    for (int i = 0; i < numsSize; ++i) {
        while (nums[i] > 0 && nums[i] <= numsSize && nums[i] != nums[nums[i] - 1]) {
            int j = nums[i] - 1;
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
    for (int i = 0; i < numsSize; ++i) {
        if (nums[i] != i + 1) {
            return i + 1;
        }
    }
    return numsSize + 1;
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function firstMissingPositive($nums) {
        $n = count($nums);
        for ($i = 0; $i < $n; $i++) {
            while ($nums[$i] >= 1 && $nums[$i] <= $n && $nums[$i] != $nums[$nums[$i] - 1]) {
                $j = $nums[$i] - 1;
                $t = $nums[$i];
                $nums[$i] = $nums[$j];
                $nums[$j] = $t;
            }
        }
        for ($i = 0; $i < $n; $i++) {
            if ($nums[$i] != $i + 1) {
                return $i + 1;
            }
        }
        return $n + 1;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
