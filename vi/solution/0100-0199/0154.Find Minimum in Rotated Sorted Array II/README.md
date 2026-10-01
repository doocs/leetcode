---
comments: true
difficulty: Hard
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [154. Find Minimum in Rotated Sorted Array II](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii)

[中文文档](/solution/0100-0199/0154.Find%20Minimum%20in%20Rotated%20Sorted%20Array%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Giả sử một mảng có độ dài <code>n</code> được sắp xếp theo thứ tự tăng dần được <strong>xoay</strong> từ <code>1</code> đến <code>n</code> lần. Ví dụ, mảng <code>nums = [0,1,4,4,5,6,7]</code> có thể trở thành:</p>

<ul>
	<li><code>[4,5,6,7,0,1,4]</code> nếu được xoay <code>4</code> lần.</li>
	<li><code>[0,1,4,4,5,6,7]</code> nếu được xoay <code>7</code> lần.</li>
</ul>

<p>Lưu ý rằng việc <strong>xoay</strong> một mảng <code>[a[0], a[1], a[2], ..., a[n-1]]</code> 1 lần sẽ tạo ra mảng <code>[a[n-1], a[0], a[1], a[2], ..., a[n-2]]</code>.</p>

<p>Cho mảng đã sắp xếp và xoay <code>nums</code>, trong đó có thể chứa các <strong>phần tử trùng lặp</strong>, hãy trả về <em>phần tử nhỏ nhất của mảng này</em>.</p>

<p>Bạn phải giảm số bước thao tác tổng thể nhiều nhất có thể.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<pre><strong>Đầu vào:</strong> nums = [1,3,5]
<strong>Đầu ra:</strong> 1
</pre><p><strong class="example">Ví dụ 2:</strong></p>
<pre><strong>Đầu vào:</strong> nums = [2,2,2,0,1]
<strong>Đầu ra:</strong> 0
</pre>
<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
	<li><code>-5000 &lt;= nums[i] &lt;= 5000</code></li>
	<li><code>nums</code> được sắp xếp và xoay từ <code>1</code> đến <code>n</code> lần.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng:</strong> Bài toán này tương tự <a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/" target="_blank">Find Minimum in Rotated Sorted Array</a>, nhưng <code>nums</code> có thể chứa <strong>các phần tử trùng lặp</strong>. Điều này có ảnh hưởng đến độ phức tạp thời gian không? Như thế nào và tại sao?</p>

<p>&nbsp;</p>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tìm kiếm nhị phân

<!-- thinking:start -->

> **Tư duy**
>
> Các phần tử trùng lặp được cho phép, không giống như bài toán trước. Khi $\textit{nums}[\textit{mid}]=\textit{nums}[r]$, chúng ta không thể biết phía nào chứa giá trị nhỏ nhất, vì vậy giảm $r$. Đầu vào mà mọi phần tử đều bằng nhau suy biến thành $O(n)$; đó là cái giá của các giá trị bằng nhau. Các nhánh khác vẫn thu hẹp như trong tìm kiếm nhị phân trên mảng xoay.

<!-- thinking:end -->

Chúng ta xác định biên trái $l = 0$ và biên phải $r = n - 1$ cho tìm kiếm nhị phân. Ở mỗi lần lặp, chúng ta tính vị trí giữa $mid = (l + r) \gg 1$ và so sánh $nums[mid]$ với $nums[r]$:

- Nếu $nums[mid] > nums[r]$, giá trị nhỏ nhất nằm ở bên phải $mid$, vì vậy chúng ta cập nhật $l$ thành $mid + 1$.
- Nếu $nums[mid] = nums[r]$, chúng ta không thể xác định vị trí của giá trị nhỏ nhất, nhưng có thể di chuyển $r$ sang trái một vị trí, tức là $r = r - 1$, để thu hẹp phạm vi tìm kiếm.
- Nếu $nums[mid] < nums[r]$, giá trị nhỏ nhất nằm ở bên trái $mid$ hoặc chính tại $mid$, vì vậy chúng ta cập nhật $r$ thành $mid$.

Khi $l$ và $r$ gặp nhau, con trỏ $l$ trỏ tới vị trí của giá trị nhỏ nhất, và chúng ta trả về $nums[l]$.

Độ phức tạp thời gian là $O(n)$, vì trong trường hợp xấu nhất khi tất cả phần tử trong mảng đều giống nhau, chúng ta cần duyệt qua toàn bộ mảng. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMin(self, nums: List[int]) -> int:
        l, r = 0, len(nums) - 1
        while l < r:
            mid = (l + r) >> 1
            if nums[mid] > nums[r]:
                l = mid + 1
            elif nums[mid] == nums[r]:
                r -= 1
            else:
                r = mid
        return nums[l]
```

#### Java

```java
class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else if (nums[mid] == nums[r]) {
                r--;
            } else {
                r = mid;
            }
        }
        return nums[l];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findMin(vector<int>& nums) {
        int l = 0, r = nums.size() - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else if (nums[mid] == nums[r]) {
                r--;
            } else {
                r = mid;
            }
        }
        return nums[l];
    }
};
```

#### Go

```go
func findMin(nums []int) int {
	l, r := 0, len(nums)-1
	for l < r {
		mid := (l + r) >> 1
		if nums[mid] > nums[r] {
			l = mid + 1
		} else if nums[mid] == nums[r] {
			r--
		} else {
			r = mid
		}
	}
	return nums[l]
}
```

#### TypeScript

```ts
function findMin(nums: number[]): number {
    let l = 0,
        r = nums.length - 1;
    while (l < r) {
        let mid = (l + r) >> 1;
        if (nums[mid] > nums[r]) {
            l = mid + 1;
        } else if (nums[mid] == nums[r]) {
            r--;
        } else {
            r = mid;
        }
    }
    return nums[l];
}
```

#### Rust

```rust
impl Solution {
    pub fn find_min(nums: Vec<i32>) -> i32 {
        let (mut l, mut r) = (0, nums.len() - 1);
        while l < r {
            let mid = (l + r) >> 1;
            if nums[mid] > nums[r] {
                l = mid + 1;
            } else if nums[mid] == nums[r] {
                r -= 1;
            } else {
                r = mid;
            }
        }
        nums[l]
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findMin = function (nums) {
    let l = 0,
        r = nums.length - 1;
    while (l < r) {
        let mid = (l + r) >> 1;
        if (nums[mid] > nums[r]) {
            l = mid + 1;
        } else if (nums[mid] == nums[r]) {
            r--;
        } else {
            r = mid;
        }
    }
    return nums[l];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
